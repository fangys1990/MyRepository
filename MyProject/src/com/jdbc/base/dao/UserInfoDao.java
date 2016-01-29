package com.jdbc.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.base.bean.UserInfo;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午2:00:21
 * @Version 1.x 
 */
public interface UserInfoDao {

	public List<UserInfo> queryUserInfoList(Integer limit) throws SQLException;
	
	public void updateUserInfo(Integer userId) throws SQLException;
	
	public void updateUserStatus(Integer userId) throws SQLException;
}
