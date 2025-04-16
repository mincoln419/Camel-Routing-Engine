package org.mermer.camelkafka.config;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelContextConfig {

	@Bean
	public CamelContext camelContext(){
		CamelContext context = new DefaultCamelContext();
		context.getPropertiesComponent().setLocation("application.properties");
		return context;
	}
}
