//package com.kafka;
//
//import kafka.api.FetchRequest;
//import kafka.api.FetchRequestBuilder;
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.FetchResponse;
//
//import java.io.UnsupportedEncodingException;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import kafka.javaapi.consumer.ConsumerConnector;
//import kafka.javaapi.consumer.SimpleConsumer;
//import kafka.javaapi.message.ByteBufferMessageSet;
//import kafka.message.MessageAndMetadata;
//import kafka.message.MessageAndOffset;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import com.google.common.collect.ImmutableMap;
//
//public class ConsumerDemo {
//
//	private static void printMessages(ByteBufferMessageSet messageSet)
//			throws UnsupportedEncodingException {
//		for (MessageAndOffset messageAndOffset : messageSet) {
//			ByteBuffer payload = messageAndOffset.message().payload();
//			byte[] bytes = new byte[payload.limit()];
//			payload.get(bytes);
//			System.out.println(new String(bytes, "UTF-8"));
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
////		SimpleConsumer simpleConsumer = new SimpleConsumer(
////				KafkaProperties.kafkaServerURL,
////				KafkaProperties.kafkaServerPort,
////				KafkaProperties.connectionTimeOut,
////				KafkaProperties.kafkaProducerBufferSize,
////				KafkaProperties.clientId);
////		System.out.println("Testing single fetch");
////		FetchRequest req = new FetchRequestBuilder().clientId(KafkaProperties.clientId).addFetch(KafkaProperties.topic2, 0, 0L, 100).build();
////		FetchResponse fetchResponse = simpleConsumer.fetch(req);
////		printMessages((ByteBufferMessageSet) fetchResponse.messageSet(KafkaProperties.topic2, 0));
//
//		Properties prop = new Properties();
//		prop.put("zookeeper.connect", KafkaProperties.zkConnect);
//		prop.put("group.id", KafkaProperties.groupId);
//		ConsumerConfig config = new ConsumerConfig(prop);
//		
//		ConsumerConnector consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(config);
//		ExecutorService executor = Executors.newFixedThreadPool(1);
//		 Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams = consumerConnector.
//				 createMessageStreams(ImmutableMap.of(KafkaProperties.topic2,4));
//		List<KafkaStream<byte[], byte[]>> streams = topicMessageStreams.get(KafkaProperties.topic2);
//		for (final KafkaStream<byte[], byte[]> stream : streams) {
//            executor.submit(new Runnable() {
//                public void run() {
//                    for (MessageAndMetadata<byte[], byte[]> msgAndMetadata : stream) {
//                        System.out.println(new String(msgAndMetadata.message()));
//                    }
//                }
//            });
//        }
//		
//	}
//}
