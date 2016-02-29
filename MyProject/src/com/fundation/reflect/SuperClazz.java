package com.fundation.reflect;
/**
 * @Author Fangys
 * @Desc  通过反射获取父类及接口
 * @Date 2016年2月26日 上午10:33:48
 * @Version 1.x 
 */
public class SuperClazz {
	
	public static void main(String[] args) {
		BaseClazz baseClazz = new BaseClazz();
		Class<? extends BaseClazz> clazz = baseClazz.getClass();
		
		Class sc = clazz.getSuperclass();
		System.out.println("父类：" + sc.getName());
		System.out.println("父类的类加载器：" + sc.getClassLoader());
		System.out.println("子类的类加载器：" + clazz.getClassLoader());
		
		Class[] ci = clazz.getInterfaces();
		for(Class i : ci){
			System.out.println("接口：" + i.getName());
		}
	}
}
