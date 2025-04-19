package org.mermer.camelkafka.process;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mermer.camelkafka.model.Order;
import org.mermer.camelkafka.model.OrderResponse;

import javax.lang.model.element.TypeElement;
import java.util.Set;

public class OrderProcess implements Processor {


	@Override
	public void process(Exchange exchange) throws Exception {
		Order order = exchange.getIn().getBody(Order.class);
		OrderResponse answer = OrderResponse.builder()
				.accepted(true)
				.orderId(order.getOrderId())
				.description(String.format("Order accepted:[item=%s quantity=%s]",order.getItemId(), order.getQuantity()))
				.build();
		exchange.getIn().setBody(answer);

	}
}
