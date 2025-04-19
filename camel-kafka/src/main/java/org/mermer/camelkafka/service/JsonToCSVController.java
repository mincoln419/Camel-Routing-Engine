package org.mermer.camelkafka.service;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.mermer.camelkafka.model.Order;
import org.mermer.camelkafka.model.OrderResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transfer")
@RestController
@RequiredArgsConstructor
public class JsonToCSVController {

	final private CamelContext camelContext;
	final private ProducerTemplate producer;


	@PostMapping(value = "/jsonToCsv", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<OrderResponse> hello(@RequestBody Order requestBody){

		final OrderResponse response = producer.requestBody(ServiceConstants.DIRECT_JAVA_START, requestBody, OrderResponse.class);
		final int responseCode = 201;
		System.out.println("responseCode :: " + responseCode);
		return ResponseEntity.status(responseCode).body(response);
	}

}
