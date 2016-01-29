package com.jdbc.base.common;
/**
 * @Author Fangys
 * @Desc  
 * @Date 2016年1月20日 下午1:59:37
 * @Version 1.x 
 */
public class SqlConsts {
	
	public static String queryUserInfo = "select * from user_info limit ?";
	
	public static String updateUserInfo = "update user_info set status=1 where userId=?";
	
	public static String updateUserStatus = "update user_info set status=2 where userId=? dfdfs";
}
