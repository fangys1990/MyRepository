package com.util.logreport;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.AppenderAttachable;

public class BizLogUtil {

    public static final int MAX_FILES_IN_DIR = 1000;

    public static final long FILE_ROLLING_TIME_INTERVAL = 10L * 60 * 1000;

    public static final long FILE_ROLLING_SIZE_THREHOLD = 100 * 1024 * 1024;

    public static final String LOGGER_NAME = "report";

    public static final String APPENDER_NAME = "reportFile";

    public static final String LOGGER_ASYNC_NAME = "reportAsync";

    public static final String ASYNC_APPENDER_NAME = "reportAsyncFile";

    public static final String FILE_NAME = "biz_report20111231235959.log";

    public static final String FILE_NAME_EXT = ".log";

    public static final String FILE_ENCODING = "UTF8";

    public static final String FORMAT_PART_SEPA = "$";

    public static final String FORMAT_FIELD_SEPA = "|";

    public static final String FORMAT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    public static final String VERSION_BEHAVIOR = "10"; // 锟斤拷为锟斤拷志

    public static final String VERSION_CLICK = "20"; // 锟届迹锟斤拷志

    public static final int LEVEL_BEHAVIOR = 4; // info

    public static final int LEVEL_CLICK = 3; // debug

    public static final int BEHAVIOR_SELF = 10; //单用户主动行为  (A登陆)

    public static final int BEHAVIOR_PROXY = 20; //双用户互相行为  (A作用于B)
    
    private static final Logger logger = Logger.getLogger("report");

    /**
     * 描述：记录轨迹日志。
     * 
     * @param user 用户手机号，不带86
     * @param userIp 用户IP地址
     * @param action 用户操作、业务动作（如投递邮件，进入订阅中心）
     * @param thingId 操作的对象、客体（如栏目期刊）
     * @param result 结果，0：成功，1：失败
     * @param ext1 结果扩展数据（如投递的邮件数量）
     */
    public static void logClickEvent(String user, String userIp, int action, int thingId, int result, int ext1) {
        innerLogClickEvent(true, user, userIp, action, null, thingId, result, ext1);
    }

    public static void logClickEvent(String user, String userIp, int action, int thingId, int result, int ext1,int originid){
        innerLogClickEvent(true, user, userIp, action, null, thingId, result, ext1,originid);
    }

    public static void logClickEventProxy(String user, String userIp, int action, String acceptor, int thingId, int result, int ext1) {
        innerLogClickEvent(false, user, userIp, action, acceptor, thingId, result, ext1);
    }
    
    /**
     * 阅读日志
     * @param user 用户手机号，不带86
     * @param userIp 用户IP地址
     * @param action 用户操作、业务动作（如投递邮件，进入订阅中心）
     * @param thingId操作的对象、客体（如栏目期刊）
     * @param result 结果，0：成功，1：失败
     * @param ext1   结果扩展数据（如投递的邮件数量）
     * @param ext3   栏目id
     * @param ext4   文章id或者期刊
     */
    public static void logReaderEvent(String user, String userIp, int action, int thingId, int result, int ext1,String ext3,String ext4) {
    	readerLog(true, VERSION_CLICK, LEVEL_CLICK, user, userIp, 10, 14, 12, 0, action, null, thingId, result, ext1,ext3,ext4);
    }

    private static void innerLogClickEvent(boolean isUserSelfBehavior, String user, String userIp, int action, String acceptor, int thingId, int result, int ext1) {
    	if(!logger.isDebugEnabled()){
    		return ;
    	}
        log(isUserSelfBehavior, VERSION_CLICK, LEVEL_CLICK, user, userIp, 10, 14, 12, 0, action, acceptor, thingId, result, ext1);
    }

    private static void innerLogClickEvent(boolean isUserSelfBehavior, String user, String userIp, int action, String acceptor, int thingId, int result, int ext1,int originId) {

        log(isUserSelfBehavior, VERSION_CLICK, LEVEL_CLICK, user, userIp, 10, 14, 12, 0, action, acceptor, thingId, result, ext1,originId);
    }

    /**
     * 描述：记录轨迹日志。
     * 
     * @param user 用户手机号，不带86
     * @param userIp 用户IP地址
     * @param action 用户操作、业务动作（如投递邮件，进入订阅中心）
     * @param thingId 操作的对象、客体（如栏目期刊）
     * @param result 结果，0：成功，1：失败
     * @param ext1 结果扩展数据（如投递的邮件数量）
     */
    public static void logBehavior(String user, String userIp, int action, int thingId, int result, int ext1) {
        innerLogBehavior(true, user, userIp, action, null, thingId, result, ext1);
    }

    public static void logBehaviorProxy(String user, String userIp, int action, String acceptor, int thingId, int result, int ext1) {
        innerLogBehavior(false, user, userIp, action, acceptor, thingId, result, ext1);
    }

    private static void innerLogBehavior(boolean isUserSelfBehavior, String user, String userIp, int action, String acceptor, int thingId, int result, int ext1) {
        log(false, VERSION_BEHAVIOR, LEVEL_BEHAVIOR, user, userIp, 10, 14, 12, 0, action, acceptor, thingId, result, ext1);
    }

    private static void log(boolean isUserSelfBehavior, String version, int priority, String user, String userIp, int serviceId, int moduleId, int portalId, int method, int action, String acceptor,
                    int thingId, int result, int ext1,int originId) {
        Message msg = new Message();
        Date now = new Date();
        msg.head.reportTime = now;
        try {
            msg.head.hostIP = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            //
        }
        msg.head.version = version;
        // msg.head.flowID
        msg.head.priority = priority;
        msg.head.encoding = FILE_ENCODING;
        msg.head.logFormatID = (isUserSelfBehavior ? BEHAVIOR_SELF : BEHAVIOR_PROXY);

        msg.body.when = now;
        msg.body.user = user;
        msg.body.userIp = userIp;
        msg.body.serviceId = serviceId;
        msg.body.moduleId = moduleId;
        msg.body.portalId = portalId;
        msg.body.method = method;
        msg.body.action = action;

        msg.body.acceptor = acceptor;
        msg.body.thingId = thingId;
        // msg.body.thing="abc|def$g";
        msg.body.result = result;
        msg.body.ext1 = ext1;
        msg.body.originId = originId;

        log(msg);
    }

    private static void log(boolean isUserSelfBehavior, String version, int priority, String user, String userIp, int serviceId, int moduleId, int portalId, int method, int action, String acceptor,
                            int thingId, int result, int ext1) {
        Message msg = new Message();
        Date now = new Date();
        msg.head.reportTime = now;
        try {
            msg.head.hostIP = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            //
        }
        msg.head.version = version;
        // msg.head.flowID
        msg.head.priority = priority;
        msg.head.encoding = FILE_ENCODING;
        msg.head.logFormatID = (isUserSelfBehavior ? BEHAVIOR_SELF : BEHAVIOR_PROXY);

        msg.body.when = now;
        msg.body.user = user;
        msg.body.userIp = userIp;
        msg.body.serviceId = serviceId;
        msg.body.moduleId = moduleId;
        msg.body.portalId = portalId;
        msg.body.method = method;
        msg.body.action = action;

        msg.body.acceptor = acceptor;
        msg.body.thingId = thingId;
        // msg.body.thing="abc|def$g";
        msg.body.result = result;
        msg.body.ext1 = ext1;
        
        log(msg);
    }
    
    /**
     * 阅读日志
     * @param isUserSelfBehavior
     * @param version
     * @param priority
     * @param user
     * @param userIp
     * @param serviceId
     * @param moduleId
     * @param portalId
     * @param method
     * @param action
     * @param acceptor
     * @param thingId
     * @param result
     * @param ext1
     * @param ext3
     * @param ext4
     */
    private static void readerLog(boolean isUserSelfBehavior, String version, int priority, String user, String userIp, int serviceId, int moduleId, int portalId, int method, int action, String acceptor,int thingId, int result, int ext1,String ext3,String ext4) {
			Message msg = new Message();
			Date now = new Date();
			msg.head.reportTime = now;
			try {
				msg.head.hostIP = InetAddress.getLocalHost().getHostAddress();
			}catch (UnknownHostException e) {}
			msg.head.version = version;
			msg.head.priority = priority;
			msg.head.encoding = FILE_ENCODING;
			msg.head.logFormatID = (isUserSelfBehavior ? BEHAVIOR_SELF : BEHAVIOR_PROXY);
			msg.body.when = now;
			msg.body.user = user;
			msg.body.userIp = userIp;
			msg.body.serviceId = serviceId;
			msg.body.moduleId = moduleId;
			msg.body.portalId = portalId;
			msg.body.method = method;
			msg.body.action = action;
			msg.body.acceptor = acceptor;
			msg.body.thingId = thingId;
			msg.body.result = result;
			msg.body.ext1 = ext1;
			msg.body.ext3 = ext3;
			msg.body.ext4 = ext4;
			log(msg);
    }
    
    

    private static void log(Message msg) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_TIMESTAMP_FORMAT);

        logger.info("记录行为日志成功.");
        
        msg.head.reportTime = (msg.head.reportTime == null ? new Date() : msg.head.reportTime);
        msg.head.flowID = sdf2.format(msg.head.reportTime) + 1;

        BizLogContext.put("reporttime", sdf.format(msg.head.reportTime));
        BizLogContext.put("hostip", msg.head.hostIP);
        BizLogContext.put("version", msg.head.version);
        BizLogContext.put("flowid", msg.head.flowID);
        BizLogContext.put("priority", msg.head.priority);
        BizLogContext.put("encoding", msg.head.encoding);
        BizLogContext.put("logformatid", msg.head.logFormatID);

        BizLogContext.put("when", sdf.format(msg.body.when));
        BizLogContext.put("user", trimNull("13500000000"));
        String userIp = msg.body.userIp;
        if (userIp != null && userIp.length() > 0 && BizLogContext.get("userip") == null) {
            BizLogContext.put("userip", trimNull(userIp)); // O
        }
        BizLogContext.put("serviceid", msg.body.serviceId);
        BizLogContext.put("moduleid", msg.body.moduleId);
        BizLogContext.put("portalid", msg.body.portalId);
        BizLogContext.put("method", msg.body.method); // O

        BizLogContext.put("action", msg.body.action);
        BizLogContext.put("acceptor", trimNull(msg.body.acceptor)); // O
        BizLogContext.put("thingid", msg.body.thingId);
        BizLogContext.put("thing", trimNull(convert(msg.body.thing))); // O
        BizLogContext.put("result", msg.body.result); // O
        BizLogContext.put("ext1", msg.body.ext1); // O
        BizLogContext.put("ext2", msg.body.ext2); // O

        BizLogContext.put("ext3", trimNull(msg.body.ext3)); // O
        BizLogContext.put("ext4", trimNull(msg.body.ext4)); // O
        BizLogContext.put("sessionid", trimNull(msg.body.sessionId));
        BizLogContext.put("clientstring", trimNull(msg.body.clientString));
        BizLogContext.put("originid", msg.body.originId);
        BizLogContext.put("pageid", msg.body.pageId);
        BizLogContext.put("outurl", trimNull(msg.body.outUrl));

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

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷28锟斤拷锟街段★拷
     * 
     * @author xiaopingchun, 2011-11-29
     * @version
     * @see
     * @since
     */
    private static class Message {

        private Head head = new Head();

        private Body body = new Body();
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷息头锟斤拷锟斤拷7锟斤拷锟街段★拷
     * 
     * @author xiaopingchun, 2011-11-29
     * @version
     * @see
     * @since
     */
    private static class Head {

        private Date reportTime;

        private String hostIP = "127.0.0.1";

        private String version = VERSION_CLICK;

        private String flowID;

        private int priority = LEVEL_CLICK;

        private String encoding = FILE_ENCODING;

        private int logFormatID = BEHAVIOR_SELF;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷息锟藉。锟斤拷21锟斤拷锟街段★拷12锟斤拷锟斤拷选锟斤拷
     * 
     * @author xiaopingchun, 2011-11-29
     * @version
     * @see
     * @since
     */
    private static class Body {

        private Date when; // M 时锟戒，什么时锟斤拷

        private String user; // M 锟街伙拷牛锟斤拷锟斤拷锟�6

        private String userIp;

        private int serviceId; // M

        private int moduleId; // M

        private int portalId; // M

        private int method = 0; // 锟斤拷锟斤拷锟斤拷式

        private int action; // M 锟斤拷为锟斤拷锟�

        private String acceptor; // 锟斤拷锟斤拷锟�锟街伙拷锟斤拷耄拷锟斤拷锟�6

        private int thingId = 0; // M

        private String thing; // 锟斤拷锟斤拷锟侥讹拷锟斤拷锟斤拷锟斤拷

        private int result = 0; // 0锟斤拷锟缴癸拷锟斤拷1锟斤拷失锟斤拷

        private int ext1 = 0;

        private int ext2 = 0;

        private String ext3;

        private String ext4;

        private String sessionId = "0"; // M

        private String clientString = "0"; // M

        private int originId = 0; // M

        private int pageId = 0; // M

        private String outUrl; // M
    }

    public static void main(String[] args) throws Exception {
        // 10000锟绞硷拷录
        // 锟斤拷锟竭筹拷
        // testSync(); // 26550
        // testSync2(); // 26550
        // testAsync(); // 26550

        // 锟斤拷锟竭程ｏ拷锟睫伙拷锟藉，锟叫伙拷锟藉，锟届步锟斤拷锟叫伙拷锟藉、锟睫伙拷锟藉）
        // testSync(); // 9050ms
        // testSync2(); // 9050ms
    	String log4jFileName = "subscribeSer/WEB-INF/conf/log4j.properties";
        PropertyConfigurator.configure(log4jFileName);
    	testSync(); // 9050ms
    }

    private static void testSync() {
        final long t1 = System.currentTimeMillis();
        for (int i = 0, n = 10000; i < n; i++) {
            BizLogUtil.logClickEvent("8613900000001", "127.0.0.1", 30148, 0, 0, 0);
        }
        System.out.println(System.currentTimeMillis() - t1);
    }

    private static void testSync2() {
        final long t1 = System.currentTimeMillis();
        for (int i = 0, n = 10000; i < n; i++) {
            BizLogUtil.logClickEvent("8613900000001", "127.0.0.1", 30148, 0, 0, 0);
        }
        System.out.println(System.currentTimeMillis() - t1);
    }

    private static void testAsync() {
        AppenderAttachable aa = (AppenderAttachable) LogManager.getLogger("reportAsync").getAppender("reportFileAsync");
        Appender appender = LogManager.getLogger("report").getAppender("reportFile");
        aa.addAppender(appender);

        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0, n = threads.length; i < n; i++) {
            threads[i] = new TestThread(i);
        }

        final long t1 = System.currentTimeMillis();
        for (int i = 0, n = threads.length; i < n; i++) {
            threads[i].start();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                LogManager.shutdown();
                System.out.println(System.currentTimeMillis() - t1);
            }
        });
    }

    private static class TestThread extends Thread {

        private int index;

        public TestThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println(index + " begin");
            for (int i = 0, n = 100; i < n; i++) {
                BizLogUtil.logClickEvent("8613900000001", "127.0.0.1", 30148, 0, 0, 0);
                BizLogUtil.logBehavior("8613900000001", "127.0.0.1", 9142, 0, 0, 8);
            }
            System.out.println(index + " end");
        }
    }

}
