package com.ideatec.datagenerator.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchCond {

	private int limit;
	private int offset;
}
