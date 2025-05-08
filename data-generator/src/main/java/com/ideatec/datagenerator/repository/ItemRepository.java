package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.ItemSearchCond;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

	Item save(Item item);
	Optional<Item> findById(Long id);

	List<Item> findAll(ItemSearchCond itemSearchCond);
}
