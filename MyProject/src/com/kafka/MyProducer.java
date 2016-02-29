package com.kafka;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.Partitioner;
import kafka.producer.ProducerConfig;

public class MyProducer extends Thread {
	private final kafka.javaapi.producer.Producer<Integer, String> producer;
	private final String topic;
	private final Properties props = new Properties();

	public MyProducer(String topic) {
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("metadata.broker.list", "192.168.42.76:9092");
		producer = new kafka.javaapi.producer.Producer<Integer, String>(
				new ProducerConfig(props));
		this.topic = topic;
	}

	public void run() {
		int messageNo = 1;
		while (true) {
			String messageStr = new String("Message_" + messageNo);
			KeyedMessage data = new KeyedMessage<Integer, String>(topic, messageStr);
			producer.send(data);
			messageNo++;
		}
	}
}
