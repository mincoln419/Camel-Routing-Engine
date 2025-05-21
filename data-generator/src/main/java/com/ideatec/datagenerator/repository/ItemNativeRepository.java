package com.ideatec.datagenerator.repository;

import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemNativeRepository extends JpaRepository<Item, Long> {

	@Query(value = "SELECT * from dblink('dbname=postgres host=52.79.188.47 user=postgres password=password',\n" +
			"    'SELECT id, wing_num, location, contractor, engineer, generation_joo, gage, architect, area_name, long_pyeyul, specialists, business_entities, tel_no, area_unit, phone, step FROM view_item')\n" +
			"    AS item ( id INT, wing_num VARCHAR, location VARCHAR, contractor VARCHAR, engineer VARCHAR, generation_joo VARCHAR, gage VARCHAR, architect VARCHAR, area_name VARCHAR, long_pyeyul VARCHAR, specialists VARCHAR, business_entities VARCHAR, tel_no VARCHAR, area_unit VARCHAR, phone VARCHAR, step VARCHAR)\n" +
			"limit :#{#cond.limit} offset :#{#cond.offset}", nativeQuery = true)
	public List<Item> selectByDblink(@Param("cond") SearchCond cond);
}
