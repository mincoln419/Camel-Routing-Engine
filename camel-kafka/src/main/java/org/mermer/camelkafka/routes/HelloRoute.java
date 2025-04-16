package org.mermer.camelkafka.routes;

import org.apache.camel.builder.RouteBuilder;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.mermer.camelkafka.process.HelloProcess;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from(ServiceConstants.HELLO_SERVICE_ENDPOINT)
				.id(ServiceConstants.HELLO_ROUTE_ID)
				.log("I'm in the Camel Route!")
				.process(new HelloProcess());
	}

}
