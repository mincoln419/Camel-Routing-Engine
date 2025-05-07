package org.mermer.camelkafka.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.mermer.camelkafka.model.Order;
import org.mermer.camelkafka.model.OrderResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.kafka;

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
//		camelContext.addRoutes(createRouteBuilder());

		try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
			producerTemplate.setDefaultEndpoint(camelContext.getEndpoint(ServiceConstants.DIRECT_KAFKA_START));
			camelContext.start();

			Map<String, Object> headers = new HashMap<>();
			headers.put(KafkaConstants.PARTITION_KEY, 1);
			headers.put(KafkaConstants.KEY, "1");

			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START, testKafkaMessage, headers);
//			producerTemplate
//					.to(kafka("TestLog")
//							.key("1")
//							.partitionKey(1)
//					)
//					.withHeaders(headers)
//					.withBody(testKafkaMessage)
//					.send();

			testKafkaMessage = "TOPIC " + testKafkaMessage;
			headers.put(KafkaConstants.KEY, "2");

			producerTemplate.sendBodyAndHeaders("direct:kafkaStartNoTopic", testKafkaMessage, headers);
//			producerTemplate
//					.to(kafka("TestLog")
//							.key("1")
//							.partitionKey(2))
//					.withHeaders(headers)
//					.withBody(testKafkaMessage)
//					.send();

			testKafkaMessage = "PART 0 : " + testKafkaMessage;
			Map<String, Object> newHeader = new HashMap<>();
			newHeader.put(KafkaConstants.KEY, "AB");

			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER, testKafkaMessage, newHeader);
//			producerTemplate
//					.to(kafka("direct:kafkaStartNoTopic")
//							.key("AB")
//							.partitionKey(2))
//					.withHeaders(newHeader)
//					.withBody(testKafkaMessage)
//					.send();

			testKafkaMessage = "PART 1 : " + testKafkaMessage;
			newHeader.put(KafkaConstants.KEY, "ABC");

			producerTemplate.sendBodyAndHeaders(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER, testKafkaMessage, newHeader);
//			producerTemplate
//					.to(kafka(ServiceConstants.DIRECT_KAFKA_START_WITH_PARTITIONER))
//					.withHeaders(newHeader)
//					.withBody(testKafkaMessage)
//					.send();

			Thread.sleep(1000L);

//			Order order = Order.builder()
//					.orderId("Order-Java-0001")
//					.itemId("MILK")
//					.quantity(10)
//					.build();
//			OrderResponse response = producerTemplate.requestBody(ServiceConstants.DIRECT_JAVA_START, order, OrderResponse.class);
//			log.info("---> Sending '{}' to 'direct:java'", order);
//			log.info("---> Response :'{}'", response);
		}

		log.info("Successfully published event to Kafka.");

	}

	private void setUpKafkaComponent(CamelContext camelContext) {
		ComponentsBuilderFactory.kafka()
				.brokers("{{kafka.brokers}}")
				.register(camelContext, "kafka");
	}
}
