package com.jdbc.base.exception;
/**
 * @Author Fangys
 * @Desc  
 * 		����㣨savepoint������������е�һ���߼���
 * 		���ǿ��԰�������˵������
 * 		�����ػ�����������
 * @Date 2016��1��21�� ����3:20:37
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
