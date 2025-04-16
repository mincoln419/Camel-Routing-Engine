package org.mermer.camelkafka.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class HelloProduceRunner implements CommandLineRunner {

	private final CamelContext camelContext;

	@Override
	public void run(String... args) throws Exception {
		log.info("About to run kafka-camel integration");

		String testKafkaMessage = "Test Message from MessagePublisherClient " + Calendar.getInstance().getTime();


		setUpKafkaComponent(camelContext);
		camelContext.addRoutes(createRouteBuilder());

		try(ProducerTemplate producerTemplate = camelContext.createProducerTemplate()){
			producerTemplate.setDefaultEndpoint(camelContext.getEndpoint(ServiceConstants.DIRECT_KAFKA_START));
			camelContext.start();

			Map<String, Object> headers = new HashMap<>();
			headers.put(KafkaConstants.PARTITION_KEY, 1);
			headers.put(KafkaConstants.KEY, "1");
			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START, testKafkaMessage, headers);


			testKafkaMessage = "TOPIC " + testKafkaMessage;
			headers.put(KafkaConstants.KEY, "2");
			headers.put(KafkaConstants.TOPIC, "TestLog");

			producerTemplate.sendBodyAndHeaders("direct:kafkaStartNoTopic", testKafkaMessage, headers);

			testKafkaMessage = "PART 0 : " + testKafkaMessage;
			Map<String, Object> newHeader = new HashMap<>();
			newHeader.put(KafkaConstants.KEY, "AB");

			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER, testKafkaMessage, newHeader);
			testKafkaMessage = "PART 1 : " + testKafkaMessage;
			newHeader.put(KafkaConstants.KEY, "ABC");

			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER, testKafkaMessage, newHeader);

		}

		log.info("Successfully published event to Kafka.");

	}

	private RoutesBuilder createRouteBuilder() {
		return new RouteBuilder() {

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
		};
	}

	private void setUpKafkaComponent(CamelContext camelContext) {
		ComponentsBuilderFactory.kafka()
				.brokers("{{kafka.brokers}}")
				.register(camelContext, "kafka");
	}
}
