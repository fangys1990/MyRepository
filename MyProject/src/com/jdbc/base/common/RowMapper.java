package com.jdbc.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Fangys
 * @Desc  将取出的ResultSet和具体的bean做映射
 * @Date 2016年1月20日 下午2:04:59
 * @Version 1.x 
 */
public interface RowMapper {
	
	public Object mapping(ResultSet rs) throws SQLException;
	
}
