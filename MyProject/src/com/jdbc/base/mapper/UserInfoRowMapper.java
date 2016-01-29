package com.jdbc.base.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.base.bean.UserInfo;
import com.jdbc.base.common.RowMapper;

/**
 * @Author Fangys
 * @Desc  实体关系映射
 * @Date 2016年1月20日 下午2:06:39
 * @Version 1.x 
 */
public class UserInfoRowMapper implements RowMapper{

	@Override
	public UserInfo mapping(ResultSet rs) throws SQLException {
		UserInfo user = new UserInfo();
		while(rs.next()){
			user.setAreaCode(rs.getString("areaCode"));
			user.setCreateDate(rs.getDate("createDate"));
			user.setIsImport(rs.getInt("isImport"));
			user.setModifyDate(rs.getDate("modifyDate"));
			user.setNodeId(rs.getInt("nodeId"));
			user.setProvCode(rs.getString("provCode"));
			user.setStatus(rs.getInt("status"));
			user.setUin(rs.getString("uin"));
			user.setUserEmail(rs.getString("userEmail"));
			user.setUserId(rs.getInt("userId"));
			user.setUserNumber(rs.getString("userNumber"));
		}
		return user;
	}

}
