package com.ideatec.datagenerator.controller.api;

import com.ideatec.datagenerator.config.QueryString;
import com.ideatec.datagenerator.dto.ResponseMessage;
import com.ideatec.datagenerator.entity.Item;
import com.ideatec.datagenerator.repository.dto.SearchCond;
import com.ideatec.datagenerator.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService service;

	@GetMapping
	public ResponseEntity<List<Item>> selectItems(@QueryString SearchCond cond){

		if(service.selectItems(cond).isEmpty()){
			service.collectItems();
		}

		List<Item> items = service.selectItems(cond);

		return ResponseEntity
				.status(200)
				.body(items);
	}

	@GetMapping("/dblink")
	public ResponseEntity<List<Item>> selectItemsByDblink(@QueryString SearchCond cond){

		List<Item> items = service.selectItemsByDblink(cond);
		return ResponseEntity
				.status(200)
				.body(items);
	}



	@GetMapping("/collect")
	public ResponseEntity<ResponseMessage> collectItems(){

		service.collectItems();

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseMessage.builder()
						.status(HttpStatus.ACCEPTED.value())
						.message("정상처리되었습니다")
				.build());
	}




}
