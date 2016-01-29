package com.jdbc.base.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Fangys
 * @Desc   事务
 * @Date 2016年1月21日 下午3:08:10
 * @Version 1.x
 */
public class Transaction {
	
	/**
	 * Isolation 隔离的
	 * propagation 传播的
	 */
	
	public static final int READ_UNCOMMITTED = Connection.TRANSACTION_READ_UNCOMMITTED;
	public static final int READ_COMMITTED = Connection.TRANSACTION_READ_COMMITTED;
	public static final int REPEATALB_READ = Connection.TRANSACTION_REPEATABLE_READ;
	public static final int SERIALIZE = Connection.TRANSACTION_SERIALIZABLE;
	public static final int DEFAULT_ISOLATION_LEVEL = READ_COMMITTED;

	private Map<Exception, Savepoint> savePoints = new HashMap<Exception, Savepoint>();

	//用于实现事务的传播性
	private int newTrCount = 1;

	Connection connection;

	public Transaction(Connection connection) {
		this.connection = connection;
	}

	public void addNewService() {
		this.newTrCount++;
	}

	public void completeService() {
		this.newTrCount--;
	}

	public boolean isNewTransaction() {
		return this.newTrCount == 1;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @param transactionIsolationLevel
	 *            the transactionIsolationLevel to set
	 * @throws SQLException
	 */
	public void setTransactionIsolationLevel(int transactionIsolationLevel)
			throws SQLException {
		this.connection.setAutoCommit(false);
		this.connection.setTransactionIsolation(transactionIsolationLevel);
	}

	public Savepoint addSavepointAndRollbackException(String name, Exception e)
			throws SQLException {
		Savepoint savepoint = this.addSavePoint(name);
		this.savePoints.put(e, savepoint);
		return savepoint;
	}

	public boolean isSupportSavepoint() throws SQLException {
		return this.getConnection().getMetaData().supportsSavepoints();
	}

	private Savepoint addSavePoint(String savepointName) throws SQLException {
		return this.connection.setSavepoint(savepointName);
	}

	public boolean containsSavepoint() {
		return !this.savePoints.isEmpty();
	}

	public Savepoint getSavePointByException(Exception e) {
		return this.savePoints.get(e);
	}

}
