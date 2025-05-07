package org.mermer.camelkafka.process;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HelloProcess implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> body = mapper.convertValue(exchange.getIn().getBody(), new TypeReference<Map<String, String>>() {});
		exchange.getMessage().setBody("Hello from camel processed message! Received payload " + body.get("text"));
		exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);

	}
}
