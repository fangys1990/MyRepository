package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jdbc.spring.service.UserServiceA;
import com.jdbc.spring.service.UserServiceB;

/**
 * @Author Fangys
 * @Desc  ����Transaction�Ĵ�������
 * @Date 2016��1��26�� ����3:15:20
 * @Version 1.x 
 */
public class SpringTransactionTest {
	
	//UserServiceA sa = new UserServiceA();
	//UserServiceB sb = new UserServiceB();
	
	FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("/WebContent/WEB-INF/config/application_context.xml");
	
	/**
	 * �����������ݿ�Ĭ������Ĭ��Ϊ�Զ��ύ
	 * Spring���� �Ա������������һ�η�װ���൱�ڰ�ʹ��JDBC���뿪�����ύ���ع���������˷�װ��
	 */
	
	@Test
	public void setUp(){
		context.registerShutdownHook();
		context.start();
	}
	/**
	 * Propagation.REQUIRED:
	 * 		����������ʱ���Զ������������������Χ��ʹ�ã���ʹ��ͬһ������
	 * 		�����¿���һ���µ�����
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ REQUIRED
	 * 
	 * ��a�ķ�����ִ��B�ķ�������bִ����ɺ����쳣��a��b��ִ�н�����ع���
	 * ˵��b�Ĳ������뵽��a��������
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
	 * 		���ۺ�ʱ�������¿�һ������
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ REQUIRES_NEW
	 * 
	 * ��a�ķ����У�ִ��b�ķ���ʱ����ʹa�ع��ˣ�b���ɲ����ɹ�
	 * ˵��b�¿����Լ������񣬺�a������û�й���
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
	 * 		�����Ὺ������������Χ����ʹ����ͬ���񣬷���ʹ������
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ SUPPORTS
	 * 
	 * ���aû�п���������a��b��update��ִ�гɹ���
	 * ���a������������a��b��update�����ع�
	 */
	@Test
	public void test_SUPPORTS(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_supports(sb);
		
	}
	
	/**
	 * Propagation.NOT_SUPPORTED
	 * 		�����Ὺ������������Χ��ʹ�ù�������������ϻָ�����
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ NOT_SUPPORTS
	 * 
	 * b�����ݱ����£�a������û�и���
	 */
	@Test
	public void test_NOT_SUPPORTED(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_not_supported(sb);
	}
	
	/**
	 * Propagation.MANDATORY
	 * 		�����������񣬱��������񻷾�ʹ�÷��򱨴�
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ MANDATORY
	 * 
	 * a���û�п�������b���׳��쳣
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
	 * 		�����������񣬱��������񻷾�ʹ�÷��򱨴�
	 * a ����Ϊ REQUIRED
	 * b ����Ϊ NEVER
	 * 
	 * a�����������b���׳��쳣
	 * 
	 */
	@Test
	public void test_NEVER(){
		UserServiceA sa = (UserServiceA) context.getBean("userServiceA");
		UserServiceB sb = (UserServiceB) context.getBean("userServiceB");
		sa.update_user_never(sb);
	}
	
	
	//Propagation.NESTED
	//���һ�����������ڣ���������һ��Ƕ�׵�������. ���û�л����, ��TransactionDefinition.PROPAGATION_REQUIRED ����ִ�С���ҪJDBC3.0����֧�֡�
}
