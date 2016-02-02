package com.jdbc.spring.common;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Fangys
 * @Desc  
 * 		定义dao的基本功能
 * @Date 2016年2月2日 下午2:18:42
 * @Version 1.x 
 */
public interface JdbcDao {
	
	public int insert(String sql, Object[] params, InsertKeyHolder keyHolder) throws SQLException;
	
	public int insert(String sql, Object[] params) throws SQLException;
	
	public int update(String sql, Object[] params) throws SQLException;
	
	public int delete(String sql, Object[] params) throws SQLException;
	
	public <T> List<T> query(String sql, Object[] params) throws SQLException;
}
