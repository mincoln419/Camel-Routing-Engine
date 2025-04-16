package org.mermer.camelkafka.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

public class HelloProcess implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final String body = exchange.getIn().getBody(String.class);
		exchange.getMessage().setBody("Hello from camel processed message! Received payload " + body);
		exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);

	}
}
