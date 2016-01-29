package com.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Fangys
 * @Desc 业务日志工具类
 * @Date 2016年1月27日 下午3:55:18
 * @Version 1.x
 */
public class BizLogUtil {

	private static class Head {
		private Date reportTime;
		private String hostIP = "127.0.0.1";
		private String version;
		private int priority;
		private String encoding;
	}

	private static class Body {
		private Date when;
		private String user;
		private String userIp;
//		private int serviceId;
//		private int moduleId;
//		private int portalId;
//		private int action;
//		private String thing;
//		private int result = 0;
//		private int ext1 = 0;
//		private int ext2 = 0;
//		private String ext3;
//		private String ext4;
//		private String sessionId = "0";
//		private String clientString = "0";
//		private int originId = 0;
//		private int pageId = 0;
//		private String outUrl;
	}

	private static class Message {
		private Head head = new Head();
		private Body body = new Body();
	}

	public static void logBehavior(String user, String userIp, int serivceId){
		readerLog(true, "1.0",1, user, userIp, serivceId);
	}
	
	@SuppressWarnings("unused")
	private static void readerLog(boolean isUserSelfBehavior, String version,
			int priority, String user, String userIp, int serviceId) {
		Message msg = new Message();
		Date now = new Date();
		msg.head.reportTime = now;
		try {
			msg.head.hostIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		msg.head.version = version;
		msg.head.priority = priority;
		msg.head.encoding = "UTF8";
		msg.body.when = now;
		msg.body.user = user;
		msg.body.userIp = userIp;
//		msg.body.serviceId = serviceId;
//		msg.body.moduleId = moduleId;
//		msg.body.portalId = portalId;
//		msg.body.action = action;
//		msg.body.result = result;
//		msg.body.ext1 = ext1;
//		msg.body.ext3 = ext3;
//		msg.body.ext4 = ext4;
		log(msg);
	}

	private static void log(Message msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

		msg.head.reportTime = (msg.head.reportTime == null ? new Date() : msg.head.reportTime);

		BizLogContext.put("reporttime", sdf.format(msg.head.reportTime));
		BizLogContext.put("hostip", msg.head.hostIP);
		BizLogContext.put("version", msg.head.version);
		BizLogContext.put("priority", msg.head.priority);
		BizLogContext.put("encoding", msg.head.encoding);

		BizLogContext.put("when", sdf.format(msg.body.when));
		BizLogContext.put("user", trimNull(msg.body.user, "13500000000"));
		String userIp = msg.body.userIp;
		if (userIp != null && userIp.length() > 0
				&& BizLogContext.get("userip") == null) {
			BizLogContext.put("userip", trimNull(userIp));
		}
//		BizLogContext.put("serviceid", msg.body.serviceId);
//		BizLogContext.put("moduleid", msg.body.moduleId);
//		BizLogContext.put("portalid", msg.body.portalId);
//
//		BizLogContext.put("action", msg.body.action);
//		BizLogContext.put("thing", trimNull(convert(msg.body.thing)));
//		BizLogContext.put("result", msg.body.result);
//		BizLogContext.put("ext1", msg.body.ext1);
//		BizLogContext.put("ext2", msg.body.ext2);
//
//		BizLogContext.put("ext3", trimNull(msg.body.ext3));
//		BizLogContext.put("ext4", trimNull(msg.body.ext4));
//		BizLogContext.put("sessionid", trimNull(msg.body.sessionId));
//		BizLogContext.put("clientstring", trimNull(msg.body.clientString));
//		BizLogContext.put("originid", msg.body.originId);
//		BizLogContext.put("pageid", msg.body.pageId);
//		BizLogContext.put("outurl", trimNull(msg.body.outUrl));

		BizLogContext.clear();
	}

	private static String trimNull(String value) {
		return value == null ? "" : value;
	}

	private static String trimNull(String value, String newValue) {
		return value == null || "".equals(value) ? newValue : value;
	}

	private static String convert(String value) {
		if (value != null && value.length() > 0) {
			value = value.replaceAll("\\|", "[|]");
			value = value.replaceAll("\\$", "[\\$]");
		}
		return value;
	}
}
