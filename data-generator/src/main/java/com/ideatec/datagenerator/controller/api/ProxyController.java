package com.ideatec.datagenerator.controller.api;

import com.ideatec.datagenerator.config.QueryString;
import com.ideatec.datagenerator.dto.SearchCondWithUrl;
import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proxy")
public class ProxyController {

	@GetMapping
	public ResponseEntity<List<Map<String, Object>>> selectItems(@RequestBody SearchCondWithUrl cond){


		


		return ResponseEntity.ok(List.of());
	}
}
