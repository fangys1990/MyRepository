package com.jdbc.spring.common;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @Author Fangys
 * @Desc  
 * 		扩展dao的功能
 * @Date 2016年2月2日 下午2:19:11
 * @Version 1.x 
 */
public interface SpringJdbcDao extends JdbcDao{
	
	public JdbcTemplate getJdbcTemplate();
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	
	public <T> List<T> query(String sql, Object[] params, RowMapper mapper) throws SQLException;
	
	public int queryForInt(String sql, Object[] params) throws SQLException;
	
	public Map queryForMap(String sql, Object[] params) throws SQLException;
	
	public List queryForList(String sql, Object[] params) throws SQLException;
	
	public Object queryForObject(String sql, Object[] params, Class requiredType) throws SQLException;
	
	public Object queryForObject(String sql, Object[] params, Object obj) throws SQLException;
	
    public Object query(String sql, Object[] args, ResultSetExtractor rse) throws DataAccessException;
	
	/**
     * 描述：批量执行更新。
     * 
     * @param sql SQL语句
     * @param argListList 参数列表
     * @param batchSize 执行批量大小。整批参数再分成小的执行批量。
     * @param isRowFirstParam 行参数优先模式。<br/>
     *            行参数优先模式，以行为单位组织参数，形如{{a1,b1,c1},{a2,b2,c2}}。是我们常规理解的形态。<br/>
     *            列参数优先模式，以列为单位组织参数，形如{a1,a2},{b1,b2},{c1,c2}}。<br/>
     *            列参数优先模式支持压缩参数，形如{a1,a2},{b},{c}}。
     * @return
     * @throws Exception
     */
    public int[] batchUpdate(String sql, List<List> argListList, int batchSize, boolean isRowFirstParam) throws Exception;
	
}
