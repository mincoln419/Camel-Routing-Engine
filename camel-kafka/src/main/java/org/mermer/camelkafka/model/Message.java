package org.mermer.camelkafka.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

	String message;
}
