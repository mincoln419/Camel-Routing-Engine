package com.ideatec.datagenerator.controller.api;

import com.ideatec.datagenerator.config.QueryString;
import com.ideatec.datagenerator.dto.ResponseMessage;
import com.ideatec.datagenerator.entity.Factory;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import com.ideatec.datagenerator.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/factories")
@RequiredArgsConstructor
public class FactoryController {

	private final FactoryService service;

	@GetMapping
	public ResponseEntity<List<Factory>> selectFactories(@QueryString SearchCond cond){

		if(service.selectItems(cond).isEmpty()){
			service.collectFactories();
		}
		List<Factory> factories = service.selectItems(cond);

		return ResponseEntity
				.status(200)
				.body(factories);
	}

	@GetMapping("/collect")
	public ResponseEntity<ResponseMessage> collectFactories(){

		service.collectFactories();

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseMessage.builder()
						.status(HttpStatus.ACCEPTED.value())
						.message("정상처리되었습니다")
				.build());
	}



}
