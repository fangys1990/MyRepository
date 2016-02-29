package com.kafka;
/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年2月22日 下午1:40:02
 * @Version 1.x 
 */
public class ProducerDemo {
	public static void main(String[] args) {
		MyProducer producer = new MyProducer(KafkaProperties.topic2);
		producer.start();
	}
}
