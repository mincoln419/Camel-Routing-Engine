package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.SearchCond;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

	Item save(Item item);

	int saveAll(List<Item> items);
	Optional<Item> findById(Long id);

	List<Item> findAll(SearchCond itemSearchCond);

}
