package com.jdbc.base.transaction;


/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月21日 下午3:07:31
 * @Version 1.x 
 */
public interface TransactionCallback {
	
	public Object doTransactionEvent() throws Exception;
}
