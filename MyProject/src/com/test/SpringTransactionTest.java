package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jdbc.spring.service.UserServiceA;
import com.jdbc.spring.service.UserServiceB;

/**
 * @Author Fangys
 * @Desc  测试Transaction的传播特性
 * @Date 2016年1月26日 下午3:15:20
 * @Version 1.x 
 */
public class SpringTransactionTest {
	
	//UserServiceA sa = new UserServiceA();
	//UserServiceB sb = new UserServiceB();
	
	FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("/WebContent/WEB-INF/config/application_context.xml");
	
	/**
	 * 本地事务：数据库默认事务，默认为自动提交
	 * Spring事务： 对本地事务操作的一次封装，相当于把使用JDBC代码开启、提交、回滚事务进行了封装。
	 */
	
	@Test
	public void setUp(){
		context.registerShutdownHook();
		context.start();
	}
	/**
	 * Propagation.REQUIRED:
	 * 		方法被调用时，自动开启事务，如果在事务范围内使用，则使用同一个事务
	 * 		否则，新开启一个新的事务
	 * a 设置为 REQUIRED
	 * b 设置为 REQUIRED
	 * 
	 * 在a的方法中执行B的方法，当b执行完成后抛异常，a和b的执行结果都回滚了
	 * 说明b的操作加入到了a的事务当中
	 * 
	 */
	@Test
	public void test_REQUIRED(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.updateUser_required(sb);
	}
	
	/**
	 * Propagation.REQUIRES_NEW
	 * 		无论何时，都会新开一个事务
	 * a 设置为 REQUIRED
	 * b 设置为 REQUIRES_NEW
	 * 
	 * 在a的方法中，执行b的方法时，即使a回滚了，b依旧操作成功
	 * 说明b新开了自己的事务，和a的事务没有关联
	 * 
	 */
	@Test
	public void test_REQUIRES_NEW(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_requires_new(sb);
	}
	
	/**
	 * Propagation.SUPPORTS
	 * 		自身不会开启事务，在事务范围内则使用相同事务，否则不使用事务
	 * a 设置为 REQUIRED
	 * b 设置为 SUPPORTS
	 * 
	 * 如果a没有开启事务，则a和b的update都执行成功了
	 * 如果a开启了事务，则a和b的update都被回滚
	 */
	@Test
	public void test_SUPPORTS(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_supports(sb);
		
	}
	
	/**
	 * Propagation.NOT_SUPPORTED
	 * 		自身不会开启事务，在事务范围内使用挂起事务，运行完毕恢复事务
	 * a 设置为 REQUIRED
	 * b 设置为 NOT_SUPPORTS
	 * 
	 * b的数据被更新，a的数据没有更新
	 */
	@Test
	public void test_NOT_SUPPORTED(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_not_supported(sb);
	}
	
	/**
	 * Propagation.MANDATORY
	 * 		自身不开启事务，必须在事务环境使用否则报错
	 * a 设置为 REQUIRED
	 * b 设置为 MANDATORY
	 * 
	 * a如果没有开启事务，b会抛出异常
	 * 
	 */
	@Test
	public void test_MANDATORY(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_mandatory(sb);
	}
	
	/**
	 * Propagation.NEVER
	 * 		自身不开启事务，必须在事务环境使用否则报错
	 * a 设置为 REQUIRED
	 * b 设置为 NEVER
	 * 
	 * a如果开启事务，b会抛出异常
	 * 
	 */
	@Test
	public void test_NEVER(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_never(sb);
	}
	
	
	//Propagation.NESTED
	//如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。需要JDBC3.0以上支持。
}
