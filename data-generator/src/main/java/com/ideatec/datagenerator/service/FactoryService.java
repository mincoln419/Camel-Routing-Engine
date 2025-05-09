package com.ideatec.datagenerator.service;

import com.ideatec.datagenerator.constants.StaticConstants;
import com.ideatec.datagenerator.entity.Factory;
import com.ideatec.datagenerator.repository.JpaFactoryRepository;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {
	private final JpaFactoryRepository repository;

	public List<Factory> selectItems(SearchCond cond){

		return repository.findAll(cond);
	}

	public void collectFactories() {
		RestClient restClient = RestClient.builder()
				.baseUrl(StaticConstants.HOST_URL)
				.build();

		List<Factory> factories = restClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/busan/factory")
						.queryParam("limit", 100)
						.queryParam("offset", 0)
						.build()
				)
				.retrieve()
				.body(new ParameterizedTypeReference<List<Factory>>(){})
		;
		repository.saveAll(factories);
	}
}
