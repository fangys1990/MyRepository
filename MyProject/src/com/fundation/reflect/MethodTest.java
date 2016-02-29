package com.fundation.reflect;

import java.lang.reflect.Method;

/**
 * @Author Fangys
 * @Desc  通过反射获取类中的方法
 * @Date 2016年2月26日 上午10:18:48
 * @Version 1.x 
 */
public class MethodTest {
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		BaseClazz baseClazz = new BaseClazz();
		Class<? extends BaseClazz> clazz = baseClazz.getClass();
		
		Method method = clazz.getMethod("runMethod", null);
		System.out.println(method.getName());
		System.out.println("------------------");
		//所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法
		Method[] ms = clazz.getMethods();
		for(Method m : ms){
			System.out.println(m.getName());
		}
		System.out.println("------------------");
		//包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法
		Method[] ma = clazz.getDeclaredMethods();
		for(Method m : ma){
			System.out.println(m.getName());
		}
	}
}
