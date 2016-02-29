package com.fundation.reflect;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * @Author Fangys
 * @Desc  用于反射测试的基类
 * @Date 2016年2月25日 下午3:10:12
 * @Version 1.x 
 */
public class BaseClazz extends SuperC implements Serializable{

	private static final long serialVersionUID = 1L;
	//基础类型成员变量
	private int myInt = 0;
	//封装类型成员变量
	private Integer myInteger = new Integer(1);
	
	public int isOk = 20;
	//无参构造方法
	public BaseClazz(){}
	//有参数的构造方法
	public BaseClazz(int myInt, Integer myInteger){
		this.myInt = myInt;
		this.myInteger = myInteger;
	}
	//私有构造方法
	private BaseClazz(Integer myInt) throws SQLException{}
	//测试方法
	public void runMethod(){}

}

class SuperC {
	
	private String name = "fatherName";
	
	public String addr = "Egl";
	
	public void pubMethod(){}
	
	private void priMethod(){}
	
}
