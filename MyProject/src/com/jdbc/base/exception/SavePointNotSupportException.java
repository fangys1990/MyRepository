package com.jdbc.base.exception;
/**
 * @Author Fangys
 * @Desc  
 * 		保存点（savepoint）是事务过程中的一个逻辑点
 * 		我们可以把事务回退到这个点
 * 		而不必回退整个事务
 * @Date 2016年1月21日 下午3:20:37
 * @Version 1.x 
 */
public class SavePointNotSupportException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	
	public SavePointNotSupportException(){
		
	}
	
	SavePointNotSupportException(int code, String message){
		super(message);
		this.code = code;
	}
	
	SavePointNotSupportException(String message){
		super(message);
	}
	
	SavePointNotSupportException(int code){
		this.code = code;
	}
	
	SavePointNotSupportException(int code, String message, Throwable t){
		super(message,t);
		this.code = code;
	}
	
	SavePointNotSupportException(String message, Throwable t){
		super(message,t);
	}
	
	SavePointNotSupportException(int code, Throwable t){
		super(t);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
