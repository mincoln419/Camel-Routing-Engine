package org.mermer.camelkafka.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.processor.transformer.DataFormatTransformer;
import org.mermer.camelkafka.constants.ServiceConstants;
import org.mermer.camelkafka.model.Order;
import org.mermer.camelkafka.model.OrderResponse;
import org.mermer.camelkafka.process.HelloProcess;
import org.mermer.camelkafka.process.OrderProcess;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from(ServiceConstants.HELLO_SERVICE_ENDPOINT)
				.id(ServiceConstants.HELLO_ROUTE_ID)
				.log("I'm in the Camel Route!")
				.process(new HelloProcess());


		from(ServiceConstants.DIRECT_JAVA_START)
				.inputType(Order.class)
				.outputType(OrderResponse.class)
				.wireTap(ServiceConstants.DIRECT_CSV_START)
				.process(new OrderProcess());

		from(ServiceConstants.DIRECT_CSV_START)
				.marshal()
				.bindy(BindyType.Csv, Order.class)
				.to("file:target/output?fileExist=Append&fileName=orders.csv");
	}

}
