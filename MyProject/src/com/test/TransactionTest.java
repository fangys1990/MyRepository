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
 * @Desc  jdbc事务管理测试
 * @Date 2016年1月21日 下午4:12:54
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
				//关于savepoint，如果要用Exception去找，得先执行sql才能拿到Exception实例，
				//这里逻辑死循环，待找到更好的解决方案后，更新此处
				//Savepoint sp = transactionTemplate.addSavePoint("userId=6", new Exception());
				userDao.updateUserStatus(8);
				return null;
			}
		});
	}
}
