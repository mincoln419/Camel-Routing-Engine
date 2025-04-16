package org.mermer.camelkafka.constants;

public class ServiceConstants {
	public static final String HELLO_SERVICE_ENDPOINT = "direct:hello";
	public static final String HELLO_ROUTE_ID = "helloRoute";
	public static final String QUEUE_READER_ROUTE_ID="QueueReaderRoute";
	public static final String REDELIVERY_COUNT_HEADER_NAME="redelivery";


	public static final String DIRECT_KAFKA_START = "direct:kafkaStart";
	public static final String DIRECT_KAFKA_START_WITH_PARTITIONER = "direct:kafkaStartWithPartitioner";
	public static final String HEADER = "${headers}";
}
