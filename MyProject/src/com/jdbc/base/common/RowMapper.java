package com.jdbc.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Fangys
 * @Desc  ��ȡ����ResultSet�;����bean��ӳ��
 * @Date 2016��1��20�� ����2:04:59
 * @Version 1.x 
 */
public interface RowMapper {
	
	public Object mapping(ResultSet rs) throws SQLException;
	
}
