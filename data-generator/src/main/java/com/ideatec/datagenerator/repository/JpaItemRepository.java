package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.ItemSearchCond;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepository implements ItemRepository{

	private final EntityManager em;
	private final JPAQueryFactory query;

	@Override
	public Item save(Item item) {
		em.persist(item);
		return item;
	}

	@Override
	public Optional<Item> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public List<Item> findAll(ItemSearchCond itemSearchCond) {
		return null;
	}
}
