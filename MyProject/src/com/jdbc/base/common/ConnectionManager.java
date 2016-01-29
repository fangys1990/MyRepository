package com.jdbc.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

import com.util.PathUtil;

/**
 * @Author Fangys
 * @Desc JDBC���ӹ���
 * @Date 2016��1��20�� ����2:15:55
 * @Version 1.x
 */
public class ConnectionManager {
	
	private static ConnectionManager connectionManager = new ConnectionManager();

	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance(){
		return connectionManager;
	}
	
	/**
	 * �̶߳����Connection
	 */
	private static ThreadLocal<Connection> conn = new ThreadLocal<Connection>();
	private static Properties prop = null;

	private static String jdbc_driver = "com.mysql.jdbc.Driver";
	private static String usr = null;
	private static String pwd = null;
	private static String url = null;

	// Ĭ�ϲ���������
	private static Boolean isTransactionActive = Boolean.FALSE;

	static {
		try {
			Class.forName(jdbc_driver);
			prop = new Properties();
			prop.load(new FileInputStream(new File(PathUtil.getAppRoot() + "/WEB-INF/config/jdbc.properties")));
			usr = prop.getProperty("jdbc.test.usr").trim();
			pwd = prop.getProperty("jdbc.test.pwd").trim();
			url = prop.getProperty("jdbc.test.url").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ����
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			con = conn.get();
			if (con == null) {
				con = DriverManager.getConnection(url, usr, pwd);
				conn.set(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * �ر�����
	 */
	public void releaseConnection(Connection con) {
		try {
			if (con != null && !isTransactionActive) {
				con.close();
				conn.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ύ
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public void commit(Connection connection) throws SQLException {
		if (!isTransactionActive) {
			connection.commit();
		}
	}

	/**
	 * ����ع�
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public void rollback(Connection connection) throws SQLException {
		if (!isTransactionActive) {
			connection.rollback();
		}
	}

	/**
	 * ����ع������屣���
	 * 
	 * @param connection
	 * @param savepoint
	 * @throws SQLException
	 */
	public void rollback(Connection connection, Savepoint savepoint)
			throws SQLException {
		if (!isTransactionActive) {
			connection.rollback(savepoint);
		}
	}

	/**
	 * ���������Ƿ���
	 * 
	 * @param isTransactionActive
	 */
	public void setTransactionActive(Boolean isTransactionActive) {
		this.isTransactionActive = isTransactionActive;
	}

}
