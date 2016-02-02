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
 * 		��չdao�Ĺ���
 * @Date 2016��2��2�� ����2:19:11
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
     * ����������ִ�и��¡�
     * 
     * @param sql SQL���
     * @param argListList �����б�
     * @param batchSize ִ��������С�����������ٷֳ�С��ִ��������
     * @param isRowFirstParam �в�������ģʽ��<br/>
     *            �в�������ģʽ������Ϊ��λ��֯����������{{a1,b1,c1},{a2,b2,c2}}�������ǳ���������̬��<br/>
     *            �в�������ģʽ������Ϊ��λ��֯����������{a1,a2},{b1,b2},{c1,c2}}��<br/>
     *            �в�������ģʽ֧��ѹ������������{a1,a2},{b},{c}}��
     * @return
     * @throws Exception
     */
    public int[] batchUpdate(String sql, List<List> argListList, int batchSize, boolean isRowFirstParam) throws Exception;
	
}
