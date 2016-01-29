package com.jdbc.base.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fangys
 * @Desc  �������ʵ�����ݿ����ز���
 * 1����ҳ��ѯ�ľ���ʵ��
 * 2�����������ľ���ʵ��
 * 3��ʵ���������
 * 
 * @Date 2016��1��20�� ����2:02:27
 * @Version 1.x 
 */
public class JdbcDaoDelegate {
	
	/**
	 * ��ѯ����
	 * @param sql
	 * @param objs
	 * @param mapper
	 * @return
	 * @throws SQLException
	 */
	public Object queryForObject(String sql, Object[] objs, RowMapper mapper){
		Object obj = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++){
				ps.setObject(i+1, objs[i]);
			}
			ResultSet rs = ps.executeQuery();
			if(rs!=null){
				obj = mapper.mapping(rs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				ConnectionManager.getInstance().releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * ��ѯ�б�
	 * @param sql
	 * @param objs
	 * @param mapper
	 * @return
	 * @throws SQLException
	 */
	public List<? extends Object> queryForList(String sql, Object[] objs, RowMapper mapper){
		List<Object> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++){
				ps.setObject(i+1, objs[i]);
			}
			ResultSet rs = ps.executeQuery();
			if(rs!=null){
				list = new ArrayList<Object>();
				while(rs.next()){
					Object obj = mapper.mapping(rs);
					list.add(obj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				ConnectionManager.getInstance().releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * isGenerateKey���Ƿ񷵻�����
	 */
	public int insert(String sql, Object[] objs, boolean isGenerateKey){
		Connection conn = null;
		PreparedStatement ps = null;
		int generatedKeys = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++){
				ps.setObject(i+1, objs[i]);
			}
			boolean result = ps.execute(sql,isGenerateKey?Statement.RETURN_GENERATED_KEYS:Statement.NO_GENERATED_KEYS);
			if(result){
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					generatedKeys = rs.getInt(1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				ConnectionManager.getInstance().releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return generatedKeys;
	}
	
	public int update(String sql, Object[] objs){
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++){
				ps.setObject(i+1, objs[i]);
			}
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				ConnectionManager.getInstance().releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
