package com.jdbc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Fangys
 * @Desc  测试事务的传播特性
 * @Date 2016年1月26日 下午3:32:14
 * @Version 1.x 
 */
@Service
public class UserServiceA {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新userId=5的status状态为9
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUser_required(UserServiceB sb){
		
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		sb.updateUser_required();
		throw new RuntimeException();
	}
	
	/**
	 * a的不变，b的改为requires_new
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update_user_requires_new(UserServiceB sb){
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		
		sb.update_user_requires_new();
		throw new RuntimeException();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update_user_supports(UserServiceB sb) {
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		
		sb.update_user_supports();
		throw new RuntimeException();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update_user_not_supported(UserServiceB sb) {
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		
		sb.update_user_not_supported();
		throw new RuntimeException();
	}
	
	//@Transactional(propagation=Propagation.REQUIRED)
	public void update_user_mandatory(UserServiceB sb) {
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		
		sb.update_user_mandatory();
		throw new RuntimeException();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update_user_never(UserServiceB sb) {
		String sql = "update user_info a set a.Status=9 where a.userId=5";
		jdbcTemplate.update(sql);
		
		sb.update_user_never();
		//throw new RuntimeException();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
