package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Factory;
import com.ideatec.datagenerator.repository.dto.SearchCond;

import java.util.List;
import java.util.Optional;

public interface FactoryRepository {

	Factory save(Factory factory);
	Optional<Factory> findById(Long id);

	List<Factory> findAll(SearchCond itemSearchCond);

	void saveAll(List<Factory> factories);
}
