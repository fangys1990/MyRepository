package com.jdbc.spring.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @Author Fangys
 * @Desc  
 * 		将ResultSet结果集映射为hashMap
 * @Date 2016年2月2日 下午4:37:06
 * @Version 1.x 
 */
public class HashMapResultSetExtractor implements ResultSetExtractor {

	private String key;
	private RowMapper rowMapper;
	/**
	 * 是否设置定长的hashMap：new HashMap<String,Object>(size);
	 */
	private int rowsExpected;
	
	public HashMapResultSetExtractor(String key, RowMapper rowMapper){
		this(key, rowMapper, 0);
	}
	
	public HashMapResultSetExtractor(String key, RowMapper rowMapper, int rowsExpected){
		this.key = key;
		this.rowMapper = rowMapper;
		this.rowsExpected = rowsExpected;
	}
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		HashMap<String, Object> hashMap = this.rowsExpected>0?new HashMap<String,Object>(rowsExpected):new HashMap<String,Object>();
		int rowNum = 0;
		while(rs.next()){
			hashMap.put(rs.getString(key), rowMapper.mapRow(rs, rowNum++));
		}
		return hashMap;
	}

}
