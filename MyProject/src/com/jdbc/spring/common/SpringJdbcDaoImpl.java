package com.jdbc.spring.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @Author Fangys
 * @Desc  实现具体的操作
 * @Date 2016年2月2日 下午2:20:40
 * @Version 1.x 
 */
public abstract class SpringJdbcDaoImpl implements SpringJdbcDao{
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(final String sql, final Object[] params, InsertKeyHolder insertKeyHolder) throws SQLException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				if(params!=null){
					for(int i=0;i<params.length;i++){
						StatementCreatorUtils.setParameterValue(ps, i+1, SqlTypeValue.TYPE_UNKNOWN, params[i]);
					}
				}
				return ps;
			}
		}, keyHolder);
		insertKeyHolder.set(keyHolder.getKey());
		return count;
	}

    public Object query(String sql, Object[] args, ResultSetExtractor rse) throws DataAccessException {
        return jdbcTemplate.query(sql, args, rse);
    }
	
	@Override
	public int insert(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delete(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public <T> List<T> query(String sql, Object[] params, RowMapper mapper)
			throws SQLException {
		return jdbcTemplate.query(sql, params, mapper);
	}

	@Override
	public int queryForInt(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public Map queryForMap(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.queryForMap(sql, params);
	}

	@Override
	public List queryForList(String sql, Object[] params) throws SQLException {
		return jdbcTemplate.queryForList(sql, params);
	}
	
	@Override
	public Object queryForObject(String sql, Object[] params, Class requiredType) throws SQLException{
		return jdbcTemplate.queryForObject(sql, params, requiredType);
	}
	
	/**
	 * ParameterizedBeanPropertyRowMapper
	 * 		通过反射将ResultSet的字段映射到Obj实例
	 * 
	 */
	@Override
	public Object queryForObject(String sql, Object[] params, Object obj) throws SQLException{
		return jdbcTemplate.queryForObject(sql, params, ParameterizedBeanPropertyRowMapper.newInstance(obj.getClass()));
	}

	/**
     * 描述：分批次执行更新。如要更新100000笔，每批1000笔，则会分100次更新。
     */
	@Override
	public int[] batchUpdate(String sql, final List<List> argListList, int batchSize,final boolean isRowFirstParam) throws Exception {
		Integer totalBatchSize = 0;
		//{{a1,b1,c1},{a2,b2,c2}}
		if(isRowFirstParam){
			totalBatchSize = argListList.size();
		}else{
			//{a1,a2},{b1,b2},{c1,c2}}
			for(List arglist : argListList){
				if(arglist.size()>totalBatchSize){
					totalBatchSize = arglist.size();
				}
			}
		}
		
		if(batchSize > totalBatchSize){
			batchSize = totalBatchSize;
		}
		
		int[] result = new int[totalBatchSize];
		BatchBatchPreparedStatementSetter bbpss = new BatchBatchPreparedStatementSetter(argListList, totalBatchSize, batchSize, isRowFirstParam);
		for(int i=0,n=totalBatchSize; i<n; i=i+batchSize){
			int[] tempResult = jdbcTemplate.batchUpdate(sql, bbpss);
			System.arraycopy(tempResult, 0, result, i, tempResult.length);
		}
		return result;
	}

}
