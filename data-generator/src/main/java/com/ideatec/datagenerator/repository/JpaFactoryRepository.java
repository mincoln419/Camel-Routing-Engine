package com.ideatec.datagenerator.repository;


import com.ideatec.datagenerator.entity.Factory;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.ideatec.datagenerator.entity.QFactory.factory;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaFactoryRepository implements FactoryRepository {
	private final EntityManager em;
	private final JPAQueryFactory query;

	@Override
	public Factory save(Factory factory) {
		em.persist(factory);
		return factory;
	}

	@Override
	public Optional<Factory> findById(Long id) {
		return Optional.ofNullable(query
				.select(factory)
						.from(factory)
				.where(factory.id.eq(id))
				.fetchFirst());
	}

	@Override
	public List<Factory> findAll(SearchCond searchCond) {
		return query
				.select(factory)
				.from(factory)
				.limit(searchCond.getLimit())
				.offset(searchCond.getOffset())
				.fetch();
	}

	@Override
	public void saveAll(List<Factory> factories) {
		factories.stream()
				//.peek(factory -> factory.setId(null))
				.forEach(em::persist);
	}
}
