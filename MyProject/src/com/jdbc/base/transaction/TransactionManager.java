package com.jdbc.base.transaction;

import java.sql.Connection;
import java.sql.Savepoint;

import com.jdbc.base.common.ConnectionManager;
import com.jdbc.base.exception.SavePointNotSupportException;
import com.jdbc.base.exception.TransactionException;

/**
 * @Author Fangys
 * @Desc
 * @Date 2016年1月21日 下午3:08:42
 * @Version 1.x
 */
public class TransactionManager {

	private ConnectionManager connectionManager = ConnectionManager.getInstance();

	private ThreadLocal<Transaction> transactions = new ThreadLocal<Transaction>();

	public Transaction beginTransaction() throws TransactionException {
		try {
			Transaction tr = this.transactions.get();
			if (tr == null) {
				Connection connection = connectionManager.getConnection();
				connectionManager.setTransactionActive(true);
				tr = new Transaction(connection);
				this.transactions.set(tr);
			} else {
				tr.addNewService();
			}
			return tr;
		} catch (Exception e) {
			throw new TransactionException();
		}
	}

	public void commitTransaction(Transaction transaction) throws TransactionException {
		try {
			if (transaction.isNewTransaction()) {
				connectionManager.setTransactionActive(false);
				connectionManager.commit(transaction.getConnection());
			}
		} catch (Exception e) {
			throw new TransactionException();
		}
	}

	public void rollbackTransaction(Transaction transaction, Exception e) throws TransactionException {
		try {
			if (transaction.isNewTransaction()) {
				connectionManager.setTransactionActive(false);
				if (transaction.containsSavepoint()) {
					Savepoint savepoint = transaction.getSavePointByException(e);
					if (savepoint == null) {
						connectionManager.rollback(transaction.getConnection());
					} else {
						connectionManager.rollback(transaction.getConnection(),savepoint);
					}
				} else {
					connectionManager.rollback(transaction.getConnection());
				}
			}
		} catch (Exception e2) {
			throw new TransactionException();
		}
	}

	public void closeTransaction(Transaction transaction) throws TransactionException {
		try {
			if (transaction.isNewTransaction()) {
				this.transactions.remove();
				connectionManager.setTransactionActive(false);
				connectionManager.releaseConnection(transaction.getConnection());
			} else {
				transaction.completeService();
			}
		} catch (Exception e) {
			throw new TransactionException();
		}

	}

	/**
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	/**
	 * @return the connectionManager
	 */
	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	/**
	 * @param connectionManager
	 *            the connectionManager to set
	 */
	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Savepoint addSavePoint(String savePointName, Exception rollbackException) throws TransactionException {
		Savepoint sp = null;
		Transaction tr = this.transactions.get();
		if (tr == null) {
			tr = this.beginTransaction();
		}
		try {
			if (!tr.isSupportSavepoint()) {
				throw new SavePointNotSupportException();
			}
			sp = tr.addSavepointAndRollbackException(savePointName,rollbackException);
		} catch (Exception e) {
			throw new TransactionException();
		}
		return sp;
	}

}
