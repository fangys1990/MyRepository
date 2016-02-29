package com.util.logreport;
/**
 * @Author Fangys
 * @Desc  
 * @Date 2014-9-18 下午02:29:21
 * @Version 1.x 
 */
public class BehaviorConstant {
	
	
	/**************收费杂志免费体验需求统计id*******************/
	/**
	 * 收费短信下发的次数 （MM计费）
	 */
	public static final Integer MM_FEE_SMS_ACTION_ID = 105862;
	public static final Integer MM_FEE_SMS_THING_ID = 3;
	
	/**
	 * 收费邮件下发的次数 （MM计费）
	 */
	public static final Integer MM_MAIL_ACTION_ID = 105862;
	public static final Integer MM_MAIL_THING_ID = 4;
	
	/**
	 * 收费杂志免费体验方案下发misc短信提醒订阅的人数和次数
	 */
	public static final Integer MISC_FEE_SMS_ACTION_ID = 105910;
	public static final Integer MISC_FEE_SMS_THING_ID = 1;
	
	/**
	 * 短信上行服务-用户关闭邮件到达通知短信的次数
	 */
	public static final Integer SMS_NOTICE_CLOSE_ACTION_ID = 105095;
	
	/**
	 * ODA发送邮件
	 */
	public static final Integer DELIVER_MAIL_ACTION_ID = 9142;
	/**
	 * 邮件到达通知上行接口
	 */
	public static final Integer MAIL_ARRIVE_NOTICE_ACTION_ID = 9143;
}
