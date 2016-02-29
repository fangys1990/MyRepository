package com.fundation.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Author Fangys
 * @Desc  通过反射获取成员变量
 * @Date 2016年2月26日 上午9:55:57
 * @Version 1.x 
 */
public class FieldTest {
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		BaseClazz baseClazz = new BaseClazz();
		
		Class<? extends BaseClazz> c = baseClazz.getClass();
		
		Field field = c.getField("isOk");
		System.out.println(field.getName());
		System.out.println(field.getInt(baseClazz));
		System.out.println(Modifier.toString(field.getModifiers()));
		System.out.println(field.getGenericType());
		System.out.println("-------------------------");
		
		//只能访问类中声明为公有的字段（包含父类）,私有的字段它无法访问
		Field[] fa = c.getFields();
		for(int i=0;i<fa.length;i++){
			System.out.println(fa[i].getName());
		}
		System.out.println("-------------------------");
		//能访问类中所有的字段，不包含父类
		Field[] fs = c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			System.out.println(fs[i].getName());
		}
		
	}
}
