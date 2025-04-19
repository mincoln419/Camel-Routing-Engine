package org.mermer.camelkafka.routes;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloConsumerRoute extends RouteBuilder {

	private CamelContext camelContext;

	@Override
	public void configure() throws Exception {

		from("kafka:{{consumer.topic}}"
				+ "?maxPollRecords={{consumer.maxPollRecords}}"
				+ "&consumersCount={{consumer.consumersCount}}"
				+ "&seekTo={{consumer.seekTo}}"
				+ "&groupId={{consumer.group}}"
				+ "&autoOffsetReset={{consumer.offset.reset}}"
		).routeId("FromKafka")
				.log("consumer received messages " + " -  ${body} - ${id}")
				.to("direct:receivedMessage");

		from("direct:receivedMessage")
				.log("message received Complete");

	}
}
