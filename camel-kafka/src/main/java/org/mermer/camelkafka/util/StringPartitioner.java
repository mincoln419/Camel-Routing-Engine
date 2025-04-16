package org.mermer.camelkafka.util;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class StringPartitioner implements Partitioner {

	public StringPartitioner(){
		//noop
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueByte, Cluster cluster) {
		int partId = 0;

		if(key instanceof String){
			String skey = (String) key;
			int len = skey.length();

			partId = len % 2;

		}
		return partId;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
