package com.ideatec.datagenerator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Factory {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String companyName;
	private String factoryRepresentAddress;
	private String workType;
	private String phone;

}
