package com.util;

import java.io.Serializable;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Fangys
 * @Desc  日志封装类，代理org.slf4j.Logger
 * @Date 2016年1月28日 下午1:49:47
 * @Version 1.x 
 */
public class MyLogger implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name = null;
	private Logger logger;
	
	private MyLogger(String name){
		this.name = name;
		this.logger = getSlf4jLogger();
	}
	
	private MyLogger(Class clazz){
		this.name = clazz.getName();
		this.logger = LoggerFactory.getLogger(clazz);
	}
	
	public Logger getSlf4jLogger(){
		if(logger == null){
			logger = LoggerFactory.getLogger(this.name);
		}
		return this.logger;
	}
	
	public static MyLogger getLogger(String name){
		return new MyLogger(name);
	}
	
	public static MyLogger getLogger(Class clazz){
		return new MyLogger(clazz);
	}
	
	public boolean isErrorEnable(){
		return getSlf4jLogger().isErrorEnabled();
	}
	
	public boolean isDebugEnable(){
		return getSlf4jLogger().isDebugEnabled();
	}
	
	public boolean isTraceEnable(){
		return getSlf4jLogger().isTraceEnabled();
	}
	
	public boolean isInfoEnable(){
		return getSlf4jLogger().isInfoEnabled();
	}
	
	public boolean isWarnEnable(){
		return getSlf4jLogger().isWarnEnabled();
	}
	
	/**
     * error
     * @param message
     */
    public void error(Object message){
    	getSlf4jLogger().error((String)message);
    }
    
    public void error(Object message,Object[] obj,Throwable t){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().error(msg,t);
    }
    
    public void error(Object message,Object[] obj){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().error(msg);
    }
    
    public void error(Object message,Throwable t){
    	getSlf4jLogger().error((String)message,t);
    }
    
    /**
     * info
     * @param message
     */
    public void info(Object message){
    	getSlf4jLogger().info((String)message);
    }
    
    public void info(Object message,Object[] obj){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().info(msg);
    }
    
    public void info(Object message,Object[] obj,Throwable t){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().info(msg, t);
    }
    
    public void info(Object message,Throwable t){
    	getSlf4jLogger().info((String)message,t);
    }
    
    /**
     * debug
     * @param message
     */
    public void debug(Object message){
    	getSlf4jLogger().debug((String)message);
    }
    
    public void debug(Object message,Object[] obj){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().debug(msg);
    }
    
    public void debug(Object message,Object[] obj,Throwable t){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().debug(msg, t);
    }
    
    public void debug(Object message,Throwable t){
    	getSlf4jLogger().debug((String)message,t);
    }
    
    /**
     * trace
     * @param message
     */
    public void trace(Object message){
    	getSlf4jLogger().trace((String)message);
    }
    
    public void trace(Object message,Object[] obj){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().trace(msg);
    }
    
    public void trace(Object message,Object[] obj,Throwable t){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().trace(msg, t);
    }
    
    
    public void trace(Object message,Throwable t){
    	getSlf4jLogger().warn((String)message,t);
    }
    
    /**
     * warn
     * @param message
     */
    public void warn(Object message){
    	getSlf4jLogger().warn((String)message);
    }
    
    public void warn(Object message,Object...objects){
    	getSlf4jLogger().warn((String)message,objects);
    }
    
    public void warn(Object message,Object[] obj,Throwable t){
    	String msg = messageFormat((String)message,obj);
    	getSlf4jLogger().warn(msg, t);
    }
    
    public void warn(Object message,Throwable t){
    	getSlf4jLogger().warn((String)message,t);
    }
    
    private String messageFormat(String message,Object[] obj){
    	if(message!=null && obj.length>0){
    		message = MessageFormat.format((String)message, obj);
    	}
    	return message;
    }
    
    public static void main(String[] args) {
		MyLogger logger = MyLogger.getLogger(MyLogger.class);
		Exception e = new  Exception("error");
		logger.error("aaaa");
		logger.error("{0},{1}",new String[]{"aaa","bbb"});
		logger.error("{0},{1}",new String[]{"aaa","bbb"},e);
		logger.error("{0},{1}",e);
	}
	
}
