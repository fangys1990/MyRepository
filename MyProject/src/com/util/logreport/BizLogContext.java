package com.util.logreport;

import java.util.Hashtable;

import org.apache.log4j.MDC;

/**
 * 
 * @ClassName: BizLogContext
 * @Description: 日志记录上下文。传递环境、数据。委托给MDC类处理。
 * @author Mr.x
 * @date 2014-5-5 下午03:12:26
 * 
 */
public final class BizLogContext
{

	private BizLogContext()
	{
	}

	public static Hashtable getContext()
	{
		return MDC.getContext();
	}

	public static void put(String key, Object obj)
	{
		MDC.put(key, obj);
	}

	public static Object get(String key)
	{
		return MDC.get(key);
	}

	public static void remove(String key)
	{
		MDC.remove(key);
	}

	public static void clear()
	{
		MDC.clear();
	}

}
