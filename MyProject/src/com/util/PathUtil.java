package com.util;
/**
 * @Author Fangys
 * @Desc  
 * @Date 2016��1��20�� ����2:19:32
 * @Version 1.x 
 */
public class PathUtil {
	
	/**
	 * ��ȡ��Ŀ·����E:/MyRepository/MyProject/
	 */
	public static String getAppRoot(){
		String classPath = PathUtil.class.getResource("/").toString();
		classPath = classPath.substring(0, classPath.length()-1);
		classPath = classPath.substring(0,classPath.lastIndexOf("/"));
		classPath = classPath.substring(classPath.indexOf("/") + 1, classPath.length());
		return classPath;
	}
	
}
