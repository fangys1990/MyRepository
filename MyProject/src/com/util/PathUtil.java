package com.util;
/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午2:19:32
 * @Version 1.x 
 */
public class PathUtil {
	
	/**
	 * 获取项目路径：E:/MyRepository/MyProject/
	 */
	public static String getAppRoot(){
		String classPath = PathUtil.class.getResource("/").toString();
		classPath = classPath.substring(0, classPath.length()-1);
		classPath = classPath.substring(0,classPath.lastIndexOf("/"));
		classPath = classPath.substring(classPath.indexOf("/") + 1, classPath.length());
		return classPath;
	}
	
}
