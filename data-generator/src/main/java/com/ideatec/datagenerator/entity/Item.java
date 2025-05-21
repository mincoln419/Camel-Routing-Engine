package com.ideatec.datagenerator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String wingNum;
	private String location;
	private String contractor;
	private String engineer;
	private String generationJoo;
	private String gage;
	private String architect;
	private String areaName;
	private String longPyeyul;
	private String specialists;
	private String businessEntities;
	private String telNo;
	private String areaUnit;
	private String phone;
	private String step;



}
