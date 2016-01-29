package com.jdbc.base.common;
/**
 * @Author Fangys
 * @Desc  dao���װ��
 * @Date 2016��1��20�� ����2:02:07
 * @Version 1.x 
 */
public class JdbcDaoSupport {
	
	private JdbcDaoDelegate delegate;
	
	public JdbcDaoDelegate getJdbcDaoClient(){
		if(delegate == null){
			delegate = new JdbcDaoDelegate();
		}
		return delegate;
	}
}
