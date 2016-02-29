package com.fundation.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @Author Fangys
 * @Desc  通过反射获取构造方法
 * @Date 2016年2月25日 下午3:16:28
 * @Version 1.x 
 */
public class ConstructorTest {
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		BaseClazz baseClazz = new BaseClazz();
		
		Class<? extends BaseClazz> clazz = baseClazz.getClass();
		//获取指定参数的构造方法
		Constructor constructor = clazz.getConstructor(int.class,Integer.class);
		System.out.println(constructor.getName());
		
		//只能访问类中声明为public的构造函数
		Constructor[] constructors = clazz.getConstructors();
		for(Constructor c : constructors){
			//获取构造方法名字
			String name = c.getName();
			System.out.println("获取构造方法的名字" + name);//构造方法的名字都一样的
			
			//获取构造方法的Annotation
			Annotation[] annotations = c.getAnnotations();
			for(Annotation a : annotations){
				System.out.println("获取构造方法的anntation(不包含不可见的anntation)" + a.annotationType());
			}
			//获取所有annotation
			Annotation[] as = c.getDeclaredAnnotations();
			for(Annotation a : as){
				System.out.println("获取构造方法所有的anntation：" + a.annotationType());
			}
			//获取异常类型
			Class[] eclazz = c.getExceptionTypes();
			for(Class cl : eclazz){
				System.out.println("获取构造方法的异常类型：" + cl.getName());
			}
			//返回带泛型的ExceptionTypes
			Type[] etypes = c.getGenericExceptionTypes();
			//返回带泛型的ParameterTypes
			Type[] ptypes = c.getGenericParameterTypes();
			if(ptypes.length==0){
				System.out.println("无参构造方法");
			}
			for(int i=0;i<ptypes.length;i++){
				System.out.println("第"+i +"个参数类型:"+ptypes[i]);
			}
			//返回参数类型 Class
			Class[] pc = c.getParameterTypes();
			int m = c.getModifiers();
			System.out.println("权限修饰符：" + Modifier.toString(m));
			
			TypeVariable[] tv = c.getTypeParameters();
			for(int i=0;i<tv.length;i++){
				System.out.println("type variable," +i + ":" + tv);
			}
		}
	}
}
