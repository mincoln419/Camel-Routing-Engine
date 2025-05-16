package com.ideatec.datagenerator.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SearchCondWithUrl {

	private String url;
	private Map<String, Object> param;
}
