package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ideatec.datagenerator.entity.QItem.item;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepository implements ItemRepository {

	private final EntityManager em;
	private final JPAQueryFactory query;

	@Override
	public Item save(Item item) {
		em.persist(item);
		return item;
	}

	@Override
	public int saveAll(List<Item> items) {

		items.stream()
				//.peek(item -> item.setId(null))
				.forEach(em::persist);
		return 1;
	}

	@Override
	public Optional<Item> findById(Long id) {
		return Optional.ofNullable(query
				.select(item)
						.from(item)
						.where(item.id.eq(id))
				.fetchFirst()
		);
	}

	@Override
	public List<Item> findAll(SearchCond itemSearchCond) {
		return query
				.select(item)
				.from(item)
				.limit(itemSearchCond.getLimit())
				.offset(itemSearchCond.getOffset())
				.fetch();
	}

}
