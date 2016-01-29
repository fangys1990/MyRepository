package com.util;

import org.slf4j.MDC;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月28日 上午9:51:37
 * @Version 1.x 
 */
public class BizLogContext {
	
	public static void put(String key, Object obj){
		if(key==null){
			MDC.put(key, "");
		}else{
			MDC.put(key, String.valueOf(obj));
		}
	}
	
	public static Object get(String key){
		return MDC.get(key);
	}
	
	public static void remove(String key){
		MDC.remove(key);
	}
	
	public static void clear(){
		MDC.clear();
	}
}
