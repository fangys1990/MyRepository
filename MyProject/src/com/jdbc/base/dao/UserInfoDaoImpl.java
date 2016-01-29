package com.jdbc.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.base.bean.UserInfo;
import com.jdbc.base.common.JdbcDaoSupport;
import com.jdbc.base.common.SqlConsts;
import com.jdbc.base.mapper.UserInfoRowMapper;

/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午2:01:09
 * @Version 1.x 
 */
public class UserInfoDaoImpl extends JdbcDaoSupport implements UserInfoDao{

	@Override
	public List<UserInfo> queryUserInfoList(Integer limit) throws SQLException {
		return (List<UserInfo>)this.getJdbcDaoClient().queryForList(SqlConsts.queryUserInfo, new Object[]{limit}, new UserInfoRowMapper());
	}

	@Override
	public void updateUserInfo(Integer userId) throws SQLException {
		this.getJdbcDaoClient().update(SqlConsts.updateUserInfo, new Object[]{userId});
	}
	
	public void updateUserStatus(Integer userId) throws SQLException {
		throw new SQLException();
		//this.getJdbcDaoClient().update(SqlConsts.updateUserStatus, new Object[]{userId});
	}

}
