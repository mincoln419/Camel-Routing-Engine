package org.mermer.camelkafka.config;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	@Bean("kafka")
	public KafkaComponent kafka(CamelContext camelContext) {
		//camelContext.getPropertiesComponent().setLocation("classpath:application.properties");
		return ComponentsBuilderFactory
				.kafka()
				.brokers("{{kafka.brokers}}")
				.build();
	}
}
