package com.jdbc.base.transaction;

import java.sql.Savepoint;

import com.jdbc.base.exception.TransactionException;
import com.jdbc.base.exception.TransactionTimeoutException;

/**
 * @Author Fangys
 * @Desc
 * @Date 2016年1月21日 下午3:06:55
 * @Version 1.x
 */
public class TransactionTemplate {

	private TransactionManager trManager = new TransactionManager();;

	// the timeout monitor is used to find out all long time transaction in
	// advance.
	private boolean useTimeoutMonitor = Boolean.TRUE;
	private Long maxTime = Long.valueOf(2000);

	public Savepoint addSavePoint(String savePointName, Exception rollbackException) throws TransactionException {
		return this.trManager.addSavePoint(savePointName, rollbackException);
	}

	public void executeTransaction(TransactionCallback transactionCallback,
			int isolationLevel) throws TransactionException {
		long startTime = System.currentTimeMillis();
		Transaction transaction = null;
		try {
			transaction = trManager.beginTransaction();
			//checkTimeout(startTime);
			transaction.setTransactionIsolationLevel(isolationLevel);
			transactionCallback.doTransactionEvent();
			//checkTimeout(startTime);
			trManager.commitTransaction(transaction);
		} catch (Exception e) {
			trManager.rollbackTransaction(transaction, e);

		} finally {
			trManager.closeTransaction(transaction);
		}

	}

	/**
	 * If the transaction is timeout throw a transaction time out exception.
	 * @throws TransactionTimeoutException 
	 */
	private void checkTimeout(long startTime) throws TransactionTimeoutException {
		if (this.useTimeoutMonitor) {
			if (isTimeout(startTime)) {
				throw new TransactionTimeoutException();
			}
		}
	}

	private boolean isTimeout(long startTime) {
		return System.currentTimeMillis() - startTime > this.maxTime;
	}

	public void executeTransaction(TransactionCallback transactionCallback) throws TransactionException {
		this.executeTransaction(transactionCallback,
				Transaction.DEFAULT_ISOLATION_LEVEL);

	}

	public Long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Long maxTime) {
		this.maxTime = maxTime;
	}

}
