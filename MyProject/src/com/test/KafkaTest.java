package com.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.util.logreport.BizLogUtil;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年2月22日 下午2:08:29
 * @Version 1.x 
 */
public class KafkaTest {
//	public static void main(String[] args) throws InterruptedException {
//		String log4jFileName = "WebContent/WEB-INF/config/log4j.properties";
//        PropertyConfigurator.configure(log4jFileName);
//        Logger logger = Logger.getLogger(KafkaTest.class);
//        for (int i = 0, n = 10; i < n; i++) {
//            BizLogUtil.logClickEvent("8613900000001", "127.0.0.1", 30148, 0, 0, 0);
//            logger.info("1");
//        }
//        System.out.println("111");
//	}
	
	@Test
	public void testLog(){
		String log4jFileName = "WebContent/WEB-INF/config/log4j.properties";
        PropertyConfigurator.configure(log4jFileName);
        for (int i = 0, n = 10; i < n; i++) {
            BizLogUtil.logClickEvent("8613900000001", "127.0.0.1", 30148, 0, 0, 0);
        }
        System.out.println("111");
	}
}
