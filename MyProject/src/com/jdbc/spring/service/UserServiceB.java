package com.jdbc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Fangys
 * @Desc  测试事务的传播特性
 * @Date 2016年1月26日 下午3:17:53
 * @Version 1.x 
 */
@Service
public class UserServiceB {
	
	//数据源通过spring启动时注入即可
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新userId=8的用户status=9
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUser_required(){
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update_user_requires_new(){
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void update_user_supports() {
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void update_user_not_supported() {
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void update_user_mandatory() {
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	@Transactional(propagation=Propagation.NEVER)
	public void update_user_never() {
		String sql = "update user_info a set a.Status=9 where a.userId=8";
		jdbcTemplate.update(sql);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
