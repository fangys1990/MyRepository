//package com.kafka;
//
//import java.util.Properties;
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.AppenderBase;
///**
// * @Author Fangys
// * @Desc  
// * @Date 2016年2月23日 下午3:40:48
// * @Version 1.x 
// */
//public class KafkaLogbackAppender extends AppenderBase<ILoggingEvent>{
//
//	private String topic;
//	//private String zookeeperHost;
//	
//	private String brokerList;
//	private Producer<Integer, String> producer;
//	private Formatter formatter;
//	
//	@Override
//	protected void append(ILoggingEvent eventObject) {
//		String message = this.formatter.format(eventObject);
//		this.producer.send(new KeyedMessage<Integer, String>(topic, message));
//	}
//	
//	@Override
//	public void start() {
//		if (this.formatter == null) {
//			this.formatter = new MessageFormatter();
//		}
//		
//		super.start();
//		Properties props = new Properties();
//		//props.put("zk.connect", this.zookeeperHost);
//		props.put("metadata.broker.list", this.brokerList);
//		props.put("serializer.class", "kafka.serializer.StringEncoder");
//		
//		ProducerConfig config = new ProducerConfig(props);
//		this.producer = new Producer<Integer, String>(config);
//	}
//	
//	@Override
//	public void stop() {
//		super.stop();
//		this.producer.close();
//	}
//	
//	public String getTopic() {
//		return topic;
//	}
//
//	public void setTopic(String topic) {
//		this.topic = topic;
//	}
//
//	public String getBrokerList() {
//		return brokerList;
//	}
//
//	public void setBrokerList(String brokerList) {
//		this.brokerList = brokerList;
//	}
//
//	public Producer<Integer, String> getProducer() {
//		return producer;
//	}
//
//	public void setProducer(Producer<Integer, String> producer) {
//		this.producer = producer;
//	}
//
//	public Formatter getFormatter() {
//		return formatter;
//	}
//
//	public void setFormatter(Formatter formatter) {
//		this.formatter = formatter;
//	}
//
//}
