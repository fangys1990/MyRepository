package com.jdbc.base.transaction;


/**
 * @Author Fangys
 * @Desc  
 * @Date 2016��1��21�� ����3:07:31
 * @Version 1.x 
 */
public interface TransactionCallback {
	
	public Object doTransactionEvent() throws Exception;
}
