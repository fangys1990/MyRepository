//package com.kafka;
//
//import org.junit.Test;
//
//import com.util.BizLogUtil;
//import com.util.MyLogger;
//
///**
// * @Author Fangys
// * @Desc  
// * @Date 2016年2月23日 下午3:55:29
// * @Version 1.x 
// */
//public class KafkaLogbackTest {
//	@Test
//	public void testKafka() throws InterruptedException{
//		MyLogger logger = MyLogger.getLogger(KafkaLogbackTest.class);
//		for(int i=0;i<10;i++){
//        	BizLogUtil.logBehavior("13433333333", "127.0.0.1", 11);
//        	logger.info("test"+i);
//        	Thread.sleep(100);
//        }
//		logger.info("aaaaa");
//	}
//}
