package com.ideatec.datagenerator.service;

import com.ideatec.datagenerator.constants.StaticConstants;
import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.ItemNativeRepository;
import com.ideatec.datagenerator.repository.JpaItemRepository;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final JpaItemRepository repository;
	private final ItemNativeRepository itemNativeRepository;

	public List<Item> selectItems(SearchCond cond){

		return repository.findAll(cond);
	}

	public List<Item> selectItemsByDblink(SearchCond cond){
		return itemNativeRepository.selectByDblink(cond);
	}

	public void collectItems() {
		RestClient restClient = RestClient
				.builder()
				.baseUrl(StaticConstants.HOST_URL)
				.build();

		List<Item> items = restClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/busan/equip/business")
						.queryParam("limit", 100)
						.queryParam("offset", 0)
						.build()
				)
				.retrieve()
				.body(new ParameterizedTypeReference<List<Item>>(){})
		;
		repository.saveAll(items);
	}


}
