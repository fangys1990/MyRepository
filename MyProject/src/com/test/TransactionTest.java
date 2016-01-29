package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import com.jdbc.base.dao.UserInfoDao;
import com.jdbc.base.dao.UserInfoDaoImpl;
import com.jdbc.base.exception.TransactionException;
import com.jdbc.base.transaction.Transaction;
import com.jdbc.base.transaction.TransactionCallback;
import com.jdbc.base.transaction.TransactionManager;
import com.jdbc.base.transaction.TransactionTemplate;

/**
 * @Author Fangys
 * @Desc  jdbc����������
 * @Date 2016��1��21�� ����4:12:54
 * @Version 1.x 
 */
public class TransactionTest {
	
	TransactionTemplate transactionTemplate = new TransactionTemplate();
	UserInfoDao userDao = new UserInfoDaoImpl();
	
	@Test
	public void testTransaction() throws TransactionException{
		transactionTemplate.executeTransaction(new TransactionCallback() {
			@Override
			public Object doTransactionEvent() throws Exception{
				//transactionTemplate.addSavePoint("userId=5", new Exception());
				userDao.updateUserInfo(5);
				//����savepoint�����Ҫ��Exceptionȥ�ң�����ִ��sql�����õ�Exceptionʵ����
				//�����߼���ѭ�������ҵ����õĽ�������󣬸��´˴�
				//Savepoint sp = transactionTemplate.addSavePoint("userId=6", new Exception());
				userDao.updateUserStatus(8);
				return null;
			}
		});
	}
}
