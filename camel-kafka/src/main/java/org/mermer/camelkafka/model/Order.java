package org.mermer.camelkafka.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Data
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = ",")
@Builder
public class Order {

	@XmlAttribute
	@DataField(pos = 1)
	private String orderId;
	@XmlAttribute
	@DataField(pos = 2)
	private String itemId;
	@XmlAttribute
	@DataField(pos = 3)
	private int quantity;
}
