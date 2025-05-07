package org.mermer.camelkafka.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.springframework.stereotype.Component;

@Component
public class KafkaRoute extends RouteBuilder {


	@Override
	public void configure() throws Exception {
		from(ServiceConstants.DIRECT_KAFKA_START).routeId("DirectToKafka")
				.to("kafka:{{producer.topic}}").log(ServiceConstants.HEADER);

		from("direct:kafkaStartNoTopic").routeId("kafkaStartNoTopic")
				.to("kafka:dummy")
				.log(ServiceConstants.HEADER);

		from(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER).routeId("kafkaStartWithPartitioner")
				.to("kafka:{{producer.topic}}?partitioner={{producer.partitioner}}")
				.log(ServiceConstants.HEADER);

		from("stream:in").id("input").setHeader(KafkaConstants.PARTITION_KEY, simple("0"))
				.setHeader(KafkaConstants.KEY, simple("1"))
				.to(ServiceConstants.DIRECT_KAFKA_START)
				.log(ServiceConstants.HEADER);
	}
}
