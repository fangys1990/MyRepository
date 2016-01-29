package com.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.jdbc.base.bean.UserInfo;
import com.jdbc.base.dao.UserInfoDao;
import com.jdbc.base.dao.UserInfoDaoImpl;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午3:09:20
 * @Version 1.x 
 */
public class JdbcBaseTest {
	
	@Test
	public void testJdbc(){
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		try {
			List<UserInfo> list = userInfoDao.queryUserInfoList(5);
			if(list!=null && list.size()>0){
				for(UserInfo user : list){
					System.out.println(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateUserInfo(){
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		try {
			userInfoDao.updateUserInfo(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
