package com.jdbc.base.common;
/**
 * @Author Fangys
 * @Desc  dao层封装类
 * @Date 2016年1月20日 下午2:02:07
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
