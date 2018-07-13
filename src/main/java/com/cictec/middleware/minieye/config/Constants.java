package com.cictec.middleware.minieye.config;

public class Constants {
	/**
	 * 消息开始标志。
	 */
	final static public int MESSAGE_BEGIN_FLAG = 0x7E;
	/**
	 * 消息结束标志。
	 */
	final static public int MESSAGE_END_FLAG = 0x7E;




	final static public int MESSAGE_MAX_LENGTH = 1035;

	final static public int MESSAGE_MIN_LENGTH = 12;

	public static final String LINE_STATION_UUID = "LINE_STATION_UUID"; // 站点UUID
	/**
	 * 消息开始标志转义
	 */
	final static public int MESSAGE_BEGIN_ESCAPE = 0X7d;
	
	/**
	 * 转义字符后缀，代表开始标志字符
	 */
	final static public int MESSAGE_BEGIN_ESCAPE_BEGIN = 0x02;
	
	/**
	 * 转义字符后缀，代表转义字符
	 */
	final static public int MESSAGE_BEGIN_ESCAPE_ESCAPE = 0x01;
	/** 【原始】进出站uuid*/
	public static final String LINE_STATION_UUID_ORG = "LINE_STATION_UUID_ORG";
	/**
	 * 保存在IoSessoin的发送序号。每向终端发送一次消息，该序号加一，并且附加在消息头部。
	 */
	final static public String SESSION_SEND_SEQUENCE = "SendSequence";
	
	/**
	 * 保存在IoSession的接收序号。
	 */
	final static public String SESSION_RECEIVE_SEQUENCE = "ReceiveSequence";

	
	
	
	public static final String HED_DEV_CODE = "HED_DEV_CODE"; // 设备ID

	public static final String HED_DEV_UUID = "HED_DEV_UUID"; // 设备UUID

	public static final String HED_UPLOAD_TIME = "HED_UPLOAD_TIME"; // 上传时间

	public static final String HED_BUS_PALTE = "HED_BUS_PALTE"; // 车牌号码

	public static final String HED_BUS_LINE_UUID = "HED_BUS_LINE_UUID"; // 车辆线路

	public static final String HED_BUS_SELF_CODE = "HED_BUS_SELF_CODE"; // 车辆自编号

	// public static final String HED_BUS = "HED_BUS"; //当前车辆

	public static final String HED_BUS_UUID = "HED_BUS_UUID"; // 当前车辆

	public static final String HED_MSG_TYPE = "HED_MSG_TYPE"; // 消息类型

	public static final String HED_POSITION_VALIDITY = "HED_POSITION_VALIDITY"; // 位置有效性

	public static final String HED_REALTIME_BUS = "REALTIME_BUS";

	/**
	 * 实时消息标志。true 从设备上传的消息，false 系统用缓存重发的消息
	 */
	public static final String HED_REALTIME_FLAG = "REALTIME_FLAG";

	/**
	 * 位置点的数据存储对象
	 */
	public static final String HED_BUS_POSITION = "HED_BUS_POSITION";

	public static final String PROP_SESSION_CLOSE_REASON = "SESSION_CLOSE_REASON";

	// public static final String BODY_MSG = "BODY_MSG"; //消息内容

	public static final String CHAR_ENCODING = "GB2312";

	// public static final String INFO_HED = "$$$"; //帧头
	//
	// public static final String INFO_END = "%0000!"; //帧尾

	public static final String INFO_SPLIT_PER = "%"; // 分隔符 百分号

	public static final String INFO_SPLIT_COMMA = ","; // 分隔符 逗号

	public static final String INFO_SPLIT_PLUS = "+"; // 分隔符 加号

	public static final String INFO_SPLIT_PLUSXG = "\\+"; // 分隔符 加号

	public static final String INFO_SPLIT_MINUS = "-"; // 分隔符 减号

	public static final String INFO_SPLIT_COLON = ":"; // 分隔符 冒号

	public static final String INFO_SPLIT_SEMICOLON = ";"; // 分隔符 冒号

	public static final String INFO_SPLIT_BLANK = " "; // 分隔符 空格

	public static final String INFO_SPLIT_DOT = "."; // 分隔符 点号

	public static final String INFO_SPLIT_LARGER = ">"; // 分隔符 大于号

	public static final String INFO_SPLIT_POUND = "#"; // 分隔符 #号

	public static final String INFO_SPLIT_EQ = "="; // 分隔符=号

	public static final String INFO_SPLIT_TIME_PREFIX = "T"; // ISO时间格式前缀。
	//
	// public static final String POSITION_STATUS_A = "A"; //有效性A:有效
	//
	// public static final String POSITION_STATUS_V = "V"; //有效性 V:无效
	//
	public static final String MSG_TYPE_POS = "V1.1"; // 消息类型 实时位置数据
	//
	// public static final String MSG_TYPE_RFID = "RFID"; //消息类型 刷卡数据
	//
	// public static final String MSG_TYPE_ALARM = "ALARM"; //消息类型 报警数据
	//
	// public static final String MSG_TYPE_LOGIN = "LOGIN"; //消息类型 上下线数据
	//
	// public static final String MSG_TYPE_FILE = "FILE"; //消息类型 上传标定站点数据
	//
	// public static final String MSG_TYPE_LINE = "LINE"; //消息类型 上传标定站点数据
	//
	// public static final String MSG_TYPE_CONFIG = "CONFIG"; //消息类型 下发数据
	//
	// public static final String MSG_TYPE_DX = "DX"; //消息类型 掉线坐标数据上传

	public static final String MSG_INFO_DRIVER_UUID = "DRIVER_UUID";
	public static final String MSG_INFO_DRIVER_NAME = "DRIVER_NAME"; // 驾驶员姓名

	public static final String MSG_INFO_OUT_QUEUE = "OUT_QUEUE"; // 发送目标队列
	public static final String VAL_OUT_QUEUE_CIC = "CIC";
	public static final String VAL_OUT_QUEUE_STATUS = "STATUS";
	public static final String VAL_OUT_QUEUE_NONE = "NONE";

	/*** ===== POSITION实时位置数据 ==== ****/
	public static final String MSG_INFO_POSITION_LINE_NAME = "POSITION_LINE_NAME"; // 线路名

	public static final String MSG_INFO_POSITION_LINE_UUID = "POSITION_LINE_UUID"; // 线路编号

	public static final String MSG_INFO_POSITION_DIRECTION = "POSITION_DIRECTION"; // 方向

	public static final String MSG_INFO_POSITION_STATION = "POSITION_STATION"; // 站点名

	public static final String MSG_INFO_POSITION_STATION_SEQ = "POSITION_STATION_SEQ"; // 站点序号

	/**
	 * 车辆调度状态
	 */
	public static final String MSG_INFO_DISPATCH_STATUS = "DISPATCH_STATUS";

	/**
	 * 协议中的车辆状态。 车辆运营状态（1Byte），0：非运营，1：运营，2：加油，3：故障，4、事故，5、通话。
	 */
	public static final String MSG_INFO_POSITION_BUS_STATUS = "POSITION_BUS_STATUS"; // 车辆状态

	public static final String MSG_INFO_POSITION_FNO_TYPE = "POSITION_BUS_FNO_TYPE"; // 车辆非运营类型1.加气
																						// 2.事故3.包车4.故障
																						// 5.维修
																						// 6.维护

	public static final String MSG_INFO_GPS = "GPS"; // GPS消息(未解析)

	public static final String MSG_INFO_POSITION_OTHER = "POSITION_OTHER"; // 其他消息内容

	public static final String MSG_INFO_POSITION_LATITUDE = "POSITION_LATITUDE"; // 纬度

	public static final String MSG_INFO_POSITION_LATITUDE_INDICATOR = "POSITION_LATITUDE_INDICATOR"; // 北纬/南纬

	public static final String MSG_INFO_POSITION_LONGITUDE = "POSITION_LONGITUDE"; // 经度

	public static final String MSG_INFO_POSITION_LONGITUDE_INDICATOR = "POSITION_LONGITUDE_INDICATOR"; // 东经/西经

	public static final String MSG_INFO_POSITION_SPEED = "POSITION_SPEED"; // 时速

	public static final String MSG_INFO_POSITION_COURSE = "POSITION_COURSE"; // 角度

	public static final String MSG_INFO_POSITION_GATHER_TIME = "POSITION_UP_TIME"; // gps数据采集时间yyyy-mm-dd
																					// hh:mi:ss:SSS

	/**
	 * 是否是当天的数据
	 */
	public static final String MSG_INFO_POSITION_CURRENT_DAY = "POSITION_CURRENT_DAY";

	/**
	 * 补传的历史数据。
	 */
	public static final String MSG_INFO_POSITION_HISTORY_DATA = "HISTORY_DATA";

	/**
	 * 补传的历史数据。
	 */
	public static final String MSG_INFO_POSITION_HISTORY_DATA_TRUE = "1";

	/**
	 * 非补传的历史数据。
	 */
	public static final String MSG_INFO_POSITION_HISTORY_DATA_FLASE = "0";

	/**
	 * 待发车标志
	 */
	public static final String MSG_INFO_POSITION_STANDBY_FLAG = "STANDBY_FLAG";
	/**
	 * 在待发区
	 */
	public static final String MSG_INFO_POSITION_STANDBY_FLAG_1 = "1";
	/**
	 * 不在在待发区
	 */
	public static final String MSG_INFO_POSITION_STANDBY_FLAG_0 = "0";
	/**
	 * 运营路段的UUID。
	 */
	// public static final String MSG_INFO_POSITION_SECTION_UUID =
	// "SECTION_UUID";

	/**
	 * GPS坐标漂移
	 */
	public static final String MSG_INFO_GPS_DRIFT = "GPS_DRIFT";// 判断此位置点信息是否漂移

	public static final String MSG_INFO_POSITION_DRV_ICCARD = "POSITION_DRV_ICCARD"; // 司机卡号

	public static final String MSG_INFO_POSITION_IS_STOP = "POSITION_IS_STOP"; // 是否停车

	public static final String MSG_INFO_POSITION_OVER_SPEED = "POSITION_OVER_SPEED"; // 是否超速

	public static final String MSG_INFO_POSITION_PX = "POSITION_PX"; // 是否偏线
	/**
	 * 是否进出站
	 */
	public static final String MSG_INFO_POSITION_ENTER_OUT_SATION = "ENTER_OUT_SATION";// 是否进出站

	/**
	 * 是否在偏线计划中
	 */
	public static final String MSG_INFO_POSITION_IN_DEVIATE_PLAN = "IN_DEVIATE_PLAN";

	/**
	 * 是否在运营时间内
	 */
	public static final String MSG_INFO_POSITION_IN_OPER_TIME = "IN_OPER_TIME";
	/**
	 * 所有的报警
	 */
	public static final String MSG_INFO_POSITION_ALL_ALARMS = "ALL_ALARMS"; // 所有的报警
	/**
	 * 所有关闭的报警.
	 */
	public static final String MSG_INFO_POSITION_CLOSED_ALARMS = "CLOSED_ALARMS"; // 所有关闭的报警.
	/**
	 * 设备上传的报警
	 */
	public static final String MSG_INFO_POSITION_DEV_ALARM = "DEV_ALARM";
	/**
	 * 虚拟里程表里程数值
	 */
	public static final String MSG_INFO_POSITION_ODOMETER_VALUE = "ODOMETER_VALUE";
	// /**
	// * 消息是否为破损的。主要是指找不到线路或者站点
	// */
	// public static final String MSG_INFO_POSITION_BROKEN="MESSAGE_BROKEN";
	//
	//
	public static final String MSG_INFO_CALL_PHONE = "CALL_PHONE"; // 通话号码
	
	/**
	 * 下个发车时刻的发车时间
	 */
	public static final String MSG_INFO_SCHEDULE_DEPARTURE_TIME = "START_TIME"; // 发车时间

	/**
	 * 下个发车时刻的班次
	 */
	public static final String MSG_INFO_SCHEDULE_SHIFT = "SCHEDULE_SHIFT";// 班次
	/**
	 * 下个发车时刻的趟次
	 */
	public static final String MSG_INFO_SCHEDULE_TRIP = "SCHEDULE_TRIP"; // 趟次
	/**
	 * 下个发车时刻的上下行标志
	 */
	public static final String MSG_INFO_SCHEDULE_LINE_TYPE = "SCHEDULE_LINE_TYPE";

	/**
	 * 手动报警
	 */
	public static final String MSG_INFO_POSITION_MANUAL_ALARM = "POSITION_IS_BAOJING"; // 是否报警

	/**
	 * 回场报警
	 */
	public static final String MSG_INFO_POSITION_IS_RETURN = "POSITION_IS_RETURN"; // 是否回场
	/**
	 * 是否应该回场
	 */
	public static final String MSG_INFO_POSITION_IS_SHOULD_RETURN = "POSITION_IS_SHOULD_RETURN"; // 是否回场
	/**
	 * 车辆在线路上的相对位置，范围0到1. 0 - 发车站 1 - 终点站
	 */
	public static final String MSG_INFO_POSITION_RATE = "POSITION_RATE"; // 在两个站点之间的比例
	public static final String MSG_INFO_POSITION_STATION_RATE = "POSITION_STATION_RATE"; // 在两个站点之间的比例
	/**
	 * 提前发车标志
	 */
	public static final String MSG_INFO_POSITION_AHEAD_DEPARTURE = "AHEAD_DEPARTURE";
	/**
	 * 延迟发车标志
	 */
	public static final String MSG_INFO_POSITION_DELAY_DEPARTURE = "DELAY_DEPARTURE";
	/**
	 * 即将发车标志 0 - 不是即将发车 1 - 即将发车
	 */
	public static final String MSG_INFO_POSITION_WILL_DEPARTURE = "WILL_DEPARTURE";

	/**
	 * 前面车距过近
	 */
	public static final String MSG_INFO_POSITION_FRONT_TOO_CLOSE = "FRONT_TOO_CLOSE";
	/**
	 * 前面车距过远
	 */
	public static final String MSG_INFO_POSITION_FRONT_TOO_FAR = "FRONT_TOO_FAR";
	/**
	 * 后面车距过近
	 */
	public static final String MSG_INFO_POSITION_BACK_TOO_CLOSE = "BACK_TOO_CLOSE";
	/**
	 * 后面车距过远
	 */
	public static final String MSG_INFO_POSITION_BACK_TOO_FAR = "BACK_TOO_FAR";
	/**
	 * 超车标识
	 */
	public static final String MSG_INFO_POSITION_OVERTAKE = "POSITION_OVERTAKE";

	/**
	 * 进出站坐标点
	 */
	public static final String MSG_INFO_ENTER_OUT_POSITION = "ENTER_OUT_POSITION";
	/**
	 * 到最近的前一个点的距离，单位米。
	 */
	public static final String MSG_INFO_DISTANCE_TO_PREPOSITION = "DISTANCE_TO_PREPOSITION";

	/*** ===== 刷卡消息 ==== ****/
	public static final String MSG_INFO_CARD_TIME = "CARD_TIME"; // 刷卡时间
	public static final String MSG_INFO_CARD_INFO = "CARD_INFO"; // 发卡编号

	/*** ===== 警报消息 ==== ****/
	public static final String MSG_INFO_ALARM_TIME = "ALARM_TIME"; // 警报时间

	public static final String MSG_INFO_ALARM_SPEED = "ALARM_SPEED"; // 超速报警速度

	public static final String MSG_INFO_ALARM_LATITUDE = "ALARM_LATITUDE"; // 超速报警
																			// 纬度

	public static final String MSG_INFO_ALARM_LONGITUDE = "ALARM_LONGITUDE"; // 超速报警
																				// 经度

	public static final String MSG_INFO_ALARM_TYPE = "ALARM_TYPE"; // 警报类型0：无，1：超速

	public static final String MSG_INFO_ALARM_LINE_UUID = "ALARM_LINE_UUID";

	public static final String MSG_INFO_ALARM_LINE_DIRECTION = "ALARM_LINE_DIRECTION";

	public static final String MSG_INFO_ALARM_CUR_TYPE = "ALARM_CUR_TYPE"; // 报警标准状态

	/*** ===== 上下线消息 ==== ****/
	public static final String MSG_INFO_LOGIN_STATUS = "LOGIN_STATUS"; // 在/离线状态

	public static final String VAL_LOGIN_ONLINE = "1"; // 在线

	public static final String VAL_LOGIN_OFFLINE = "0"; // 下线

	// /**
	// * 破损的消息
	// */
	// public static final String VAL_MSG_BROKEN_1 = "1";
	// /**
	// * 未破损的消息
	// */
	// public static final String VAL_MSG_BROKEN_0 = "0";

	/*** ===== 标定站点消息 ==== ****/
	public static final String HED_MARK_LINE_NAME = "MARK_LINE_NAME"; // 标定站点名

	public static final String HED_MARK_PKG_SUM = "MARK_PKG_SUM"; // 分包信息--总包数

	public static final String HED_MARK_PKG_CUR = "MARK_PKG_CUR"; // 分包信息--当前包

	public static final String MSG_INFO_MARK_PKG_DATA = "MARK_PKG_DATA"; // 分包数据

	/*** ===== 车辆状态消息 ==== ****/
	public static final String MSG_INFO_STATUS_LINE_UUID = "STATUS_LINE_UUID"; // 线路号

	public static final String MSG_INFO_STATUS_LINE_DIRECTION = "STATUS_LINE_DIRECTION"; // 上下行

	public static final String MSG_INFO_STATUS_LATITUDE = "STATUS_LATITUDE"; // 状态纬度

	public static final String MSG_INFO_STATUS_LONGITUDE = "STATUS_LONGITUDE"; // 状态经度

	public static final String MSG_INFO_STATUS_SPEED = "STATUS_SPEED"; // 时速

	public static final String MSG_INFO_STATUS_TIME = "STATUS_TIME"; // 状态时间

	public static final String MSG_INFO_STATUS_TYPE = "STATUS_TYPE"; // 状态时间

	public static final String MSG_INFO_STATUS_INFO = "STATUS_INFO"; // 状态

	public static final String MSG_INFO_LINE_VAL = "LINE";

	/***************** 杨飞 2016-03-31 添加客流数据 ***********************/

	public static final String MSG_INFO_PASSENGERFLOW_FULLLOADRATE = "FULL_LOAD_RATE";// 满载率
	public static final String MSG_INFO_BUS_ALLPERSON = "BUS_ALLPERSON";// 车上总人数
	public static final String MSG_INFO_BUS_UPPERSON = "BUS_UPPERSON";// 上车人数
	public static final String MSG_INFO_BUS_DOWNPERSON = "BUS_DOWNPERSON";// 下车人数

	/****************************************/

	/***** == 上传设备报站器内线路信息 == *********/
	public static final String MSG_INFO_DL_CUR_LINE = "DL_CUR_LINE";

	public static final String MSG_INFO_DL_ALL_LINE = "DL_ALL_LINE";

	public static final String MSG_INFO_DL_RESULT = "DL_RESULT";

	/***** == == *********/
	public static final int VAL_INTERVAL_TIME = 6; // POS数据上传间隔时间
	public static final double VAL_MAX_SPEED = 140.0; // 最大时速

	/**
	 * 通用状态 1 0
	 */
	public static final String VAL_YES = "1";
	public static final String VAL_NO = "0";

	/**
	 * 坐标漂移
	 */
	public static final String MSG_INFO_GPS_DRIFT_0 = "0";

	/**
	 * 未漂移
	 */
	public static final String MSG_INFO_GPS_DRIFT_1 = "1";

	/**
	 * 非运营
	 */
	public static final String VAL_BUS_OPER_STATUS_0 = "0"; // 非运营
	/**
	 * 运营
	 */
	public static final String VAL_BUS_OPER_STATUS_1 = "1"; // 运营

	/**
	 * 加油
	 */
	public static final String VAL_BUS_STATUS_2 = "2"; // 加油
	/**
	 * 故障
	 */
	public static final String VAL_BUS_STATUS_3 = "3"; // 故障
	/**
	 * 事故
	 */
	public static final String VAL_BUS_STATUS_4 = "4"; // 事故
	/**
	 * 通话
	 */
	public static final String VAL_BUS_STATUS_5 = "5"; // 通话
	/**
	 * 手动报警
	 */
	public static final String VAL_BUS_STATUS_6 = "6"; // 报警
	/**
	 * 偏线
	 */
	public static final String VAL_BUS_STATUS_7 = "7"; // 偏线
	/**
	 * 超速
	 */
	public static final String VAL_BUS_STATUS_8 = "8"; // 超速
	/**
	 * 停车
	 */
	public static final String VAL_BUS_STATUS_9 = "9"; // 停车
	/**
	 * 回场
	 */
	public static final String VAL_BUS_STATUS_10 = "10";// 回场
	/**
	 * 提前发车
	 */
	public static final String VAL_BUS_STATUS_11 = "11";
	/**
	 * 延迟发车
	 */
	public static final String VAL_BUS_STATUS_12 = "12";
	/**
	 * 甩站报警
	 */
	public static final String VAL_BUS_STATUS_13 = "13";
	/**
	 * 压站报警
	 */
	public static final String VAL_BUS_STATUS_14 = "14";

	/** 漂移 **/
	public static final String VAL_POSITION_OFFSET_0 = "0";
	/** 正常 **/
	public static final String VAL_POSITION_OFFSET_1 = "1";

	public static final String VAL_ALARM_TYPE_0 = "0"; // 报警终止

	/**
	 * 未指定
	 */
	public static final String VAL_LINE_NOT_SPEC = "0";
	/**
	 * 上行
	 */
	public static final String VAL_LINE_UP = "1"; // 上行
	/**
	 * 下行
	 */
	public static final String VAL_LINE_DOWN = "2"; // 下行
	/**
	 * 任务状态，未发。
	 */
	public static final String VAL_TASK_STATUS_0 = "0";
	/**
	 * 任务状态，已发。
	 */
	public static final String VAL_TASK_STATUS_1 = "1";

	/**
	 * 等待
	 */
	public static final String VAL_TASK_SEND_STATUS_1 = "1"; // 等待
	/**
	 * 已发
	 */
	public static final String VAL_TASK_SEND_STATUS_2 = "2"; // 已发
	/**
	 * 成功
	 */
	public static final String VAL_TASK_SEND_STATUS_3 = "3"; // 成功
	/**
	 * 失败
	 */
	public static final String VAL_TASK_SEND_STATUS_4 = "4"; // 失败

	/**
	 * 时刻未下发。
	 */
	public static int VAL_SCHEDULE_TIME_SEND_STATUS_PENDDING = 0;
	/**
	 * 时刻已下发
	 */
	public static int VAL_SCHEDULE_TIME_SEND_STATUS_SENT = 1;

	/**
	 * 未发车时刻
	 */
	public static int VAL_SCHEDULE_DEPARTURE_FLAG_NOT_DEPARTURE = 0;

	/**
	 * 已发车时刻。
	 */
	public static int VAL_SCHEDULE_DEPARTURE_FLAG_DEPARTURED = 1;

	/**
	 * 未完成发车时刻
	 */
	public static int VAL_SCHEDULE_ARRAVE_FLAG_NOT_ARRAVED = 0;

	/**
	 * 已完成发车时刻
	 */
	public static int VAL_SCHEDULE_ARRAVE_FLAG_ARRAVED = 1;

	/**
	 * 不是即将发车
	 */
	public static int VAL_WILL_DEPARTURE_0 = 0;

	/**
	 * 即将发车
	 */
	public static int VAL_WILL_DEPARTURE_1 = 1;

	/**
	 * 是否被删除，否
	 */
	public static int VAL_SCHEDULE_DEPARTURE_IS_DEL_0 = 0;
	/**
	 * 是否被删除，是
	 */
	public static int VAL_SCHEDULE_DEPARTURE_IS_DEL_1 = 1;

	/**
	 * 主要大站
	 */
	public static int VAL_STATION_MAJOR = 1;
	/**
	 * 普通站点
	 */
	public static int VAL_STATION_COMMON = 0;

	public static final String VAL_TASK_BACK_OK = "OK"; // 返回结果成功
	public static final String VAL_TASK_BACK_ERROR = "ERROR"; // 返回结果失败

	public static final String VAL_TASK_TYPE_1 = "1"; // 路线配置
	public static final String VAL_TASK_TYPE_2 = "2"; // 设备参数
	public static final String VAL_TASK_TYPE_3 = "3"; // 发车时刻
	public static final String VAL_TASK_TYPE_4 = "4"; // 调度指令。
	public static final String VAL_TASK_TYPE_5 = "5"; // 上下行状态。
	public static final String VAL_TASK_TYPE_6 = "6"; // 司机属性。
	public static final String VAL_TASK_TYPE_7 = "7"; // 广播

	public static final String VAL_OVER_SPEED_0 = "0"; // 是否超速 未超速
	public static final String VAL_OVER_SPEED_1 = "1"; // 是否超速 超速

	public static final String VAL_IS_WARN_2 = "2"; // 是否报警 报警
	public static final String VAL_IS_WARN_3 = "3"; // 是否报警 结束报警

	public static final String VAL_IS_STOP_0 = "0"; // 停车标志 没有停车
	public static final String VAL_IS_STOP_1 = "1"; // 停车标志 停车。

	/**
	 * 报警未关闭
	 */
	public static final String VAL_WARN_CLOSED_0 = "0"; // 报警关闭 未关闭
	/**
	 * 报警关闭
	 */
	public static final String VAL_WARN_CLOSED_1 = "1"; // 报警关闭 关闭

	/**
	 * 进出站状态
	 */
	public static final String BUS_ENTER_OUT_SATION = "ENTER_OUT_SATION"; // 进出站状态；
	// public static final String BUS_ENTER_OUT_SATION = "VAL_ENTER_OUT_SATION";
	// //进出站状态；

	/**
	 * 进站
	 */
	public static final String VAL_ENTER_OUT_SATION_ENTER = "1"; // 1 进站；
	/**
	 * 出站
	 */
	public static final String VAL_ENTER_OUT_SATION_OUT = "2"; // 2 出站

	/**
	 * 未提前发车
	 */
	public static final String VAL_IS_AHEAD_DEPARTURE_0 = "0";
	/**
	 * 提前发车
	 */
	public static final String VAL_IS_AHEAD_DEPARTURE_1 = "1";

	/**
	 * 未延时发车
	 */
	public static final String VAL_IS_DELAY_DEPARTURE_0 = "0";
	/**
	 * 延时发车
	 */
	public static final String VAL_IS_DELAY_DEPARTURE_1 = "1";

	/**
	 * 未回场
	 */
	public static final String VAL_BUS_IN_PARK_0 = "0"; // 是否回场 未回场
	/**
	 * 回场
	 */
	public static final String VAL_BUS_IN_PARK_1 = "1"; // 是否回场 回场

	/**
	 * 不必要回场
	 */
	public static final String VAL_BUS_SHOULD_RETURN_0 = "0";

	/**
	 * 应该回场--在回场时间段内。
	 */
	public static final String VAL_BUS_SHOULD_RETURN_1 = "1";

	/**
	 * 偏线开始
	 */
	public static final String VAL_IS_PX_1 = "1"; // 偏线开始
	/**
	 * 偏线结束
	 */
	public static final String VAL_IS_PX_0 = "0"; // 偏线结束

	/**
	 * 在线运营
	 */
	public static final String VAL_INLINE_1 = "1";
	/**
	 * 不在线运营
	 */
	public static final String VAL_INLINE_0 = "0";

	/**
	 * 待发状态上行
	 */
	public static final String VAL_STANDBY_1 = "1";

	/**
	 * 待发状态下行
	 */
	public static final String VAL_STANDBY_2 = "2";

	/**
	 * 非待发状态
	 */
	public static final String VAL_STANDBY_0 = "0";

	/**
	 * 正常车距
	 */
	public static final String VAL_VEHICLE_DISTANCE_NORMAL = "0";

	/**
	 * 车距过近
	 */
	public static final String VAL_VEHICLE_DISTANCE_TOO_CLOSE = "1";

	/**
	 * 车距过远
	 */
	public static final String VAL_VEHICLE_DISTANCE_TOO_FAR = "2";

	/**
	 * 不在偏线计划中
	 */
	public static final String VAL_IN_DEVIATE_PLAN_0 = "0";
	/**
	 * 在偏线计划中
	 */
	public static final String VAL_IN_DEVIATE_PLAN_1 = "1";

	/**
	 * 不在运营时间内
	 */
	public static final String VAL_IN_OPER_TIME_0 = "0";
	/**
	 * 在运营时间范围内
	 */
	public static final String VAL_IN_OPER_TIME_1 = "1";

	/**
	 * 停车开始
	 */
	public static final String VAL_BUS_STOP_0 = "0"; // 停车开始
	/**
	 * 停车结束
	 */
	public static final String VAL_BUS_STOP_1 = "1"; // 停车结束

	/**
	 * 时刻表 单向发车
	 */
	public static final String VAL_FAHRPLAN_TYPE_0 = "0";
	/**
	 * 时刻表 双向发车
	 */
	public static final String VAL_FAHRPLAN_TYPE_1 = "1";
	/**
	 * 时刻表 环线发车
	 */
	public static final String VAL_FAHRPLAN_TYPE_2 = "2";

	/**
	 * 位置消息格式的版本 1
	 */
	public static final String VAL_POSITION_VERSION_1 = "1";
	/**
	 * 位置消息格式的版本 2 ，比1增加了信号强度。
	 */
	public static final String VAL_POSITION_VERSION_2 = "2";
	/**
	 * 位置消息格式的版本 3，比2增加了进出站状态和程序版本号。
	 */
	public static final String VAL_POSITION_VERSION_3 = "3";

	/**
	 * 位置消息格式的版本 4，站名改成S+序号，程序版本号去掉ver：，程序版本号后面加上报警标志 。
	 */
	public static final String VAL_POSITION_VERSION_4 = "4";

	public static final String VAL_SESSION_CLOSE_REASON_1 = "1"; // 超时
	public static final String VAL_SESSION_CLOSE_REASON_2 = "2"; // 异常
	// 换行
	public static final char MSG_CONFIG_LINEFEEDR = '\r';
	public static final char MSG_CONFIG_LINEFEEDN = '\n';

	// 报警状态关闭消息
	public static final String WCU_DEV_UUID = "DEV_UUID";
	public static final String WCU_WARN_TYPE = "WARN_TYPE";
	public static final String WCU_WARN_UUID = "WARN_UUID";

	// 线路发车时刻表更新消息
	public static final String LSU_LINE_UUID = "LINE_UUID";
	public static final String LSU_SCHEDULE_DATE = "SCHEDULE_DATE";

	// 重新推送某线路上所有车辆实时位置的异步请求
	public static final String REPUSH_LINE_UUID = "LINE_UUID";
	// 重新推送某个车辆的位置信息
	public static final String REPUSH_BUS_UUID = "BUS_UUID";

	// 下发调度指令，指令的主键
	public static final String DDC_COMMAND_UUID = "COMMAND_UUID";

	// 强制非运营
	public static final String FNO_COMMAND_CODE = "COMMAND_CODE";
	// 强制非运营
	public static final String FNO_COMMAND_CODE_VAL_FORCE = "1";
	// 取消强制非运营
	public static final String FNO_COMMAND_CODE_VAL_CANCEL = "2";

	// 强制非运营车辆的UUID
	public static final String FNO_COMMAND_BUSUUID = "COMMAND_BUSUUID";

	// 强制非运营结束时间
	public static final String FNO_COMMAND_EXPIRED_TIME = "COMMAND_ENDDATE";

	// 强制非运营类型
	public static final String FNO_COMMAND_TYPE = "COMMAND_FNO_TYPE";

	// 要下载电话簿UUID
	public static final String DFB_COMMAND_BPBUUID = "COMMAND_BPBUUID";
	// 要下周电话簿的线路的UUID
	public static final String DFB_COMMAND_BUSLINEIDS = "COMMAND_BUSLINEIDS";

	// 要广播消息的线路
	public static final String BCC_COMMAND_LINEUUID = "COMMAND_LINEUUID";
	// 要广播消息的车辆，多个车辆用|分隔
	public static final String BCC_COMMAND_BUSUUID = "COMMAND_BUSUUID";
	// 广播内容
	public static final String BCC_COMMAND_MSGCONTENT = "COMMAND_MSGCONTENT";
	// 广播着（调度员）
	public static final String BCC_COMMAND_USERID = "COMMAND_USERID";

	// 调度状态（停运，非停运）代码。
	public static final String DSU_COMMAND_CODE = "COMMAND_CODE";
	public static final String DSU_COMMAND_BUSUUID = "COMMAND_BUSUUID";
	/**
	 * 系统用户。用于标识系统自动生成的记录。
	 */
	public static final String SYSTEM_USER = "System";

	/**
	 * 重新发布位置和状态信息的route
	 */
	public static String ROUTE_REPUBLISH = "seda://msg.position.repush";

	/////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////// 针对发车屏
	/////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 下发数据帧的头 2个$
	 */
	public static final String DB_PROTOCOL_HEADER_DOWN = "$$";

	/**
	 * 上传数据帧的头 3个$
	 */
	public static final String DB_PROTOCOL_HEADER_UP = "$$$";

	/**
	 * 数据帧尾
	 */
	public static final String DB_PROTOCOL_TAIL = "%0000!";

	/**
	 * 百分号分隔符
	 */
	public static final String DB_PROTOCOL_SPLIT_PER = INFO_SPLIT_PER;

	/**
	 * 逗号分隔符
	 */
	public static final String DB_PROTOCOL_SPLIT_COMMA = INFO_SPLIT_COMMA;

	/**
	 * 加号分隔符
	 */
	public static final String DB_PROTOCOL_SPLIT_PLUS = INFO_SPLIT_PLUS;

	/**
	 * 发车屏系统标识
	 */
	public static final String DB_PROTOCOL_SYS_FLAG = "BDMS";
	/**
	 * 配置类型 LED显示
	 */
	public static final String DB_PROTOCOL_CONFIG_TYPE_LED = "1";
	/**
	 * 配置类型 提示发车声音提示
	 */
	public static final String DB_PROTOCOL_CONFIG_TYPE_VOICE_LEAD = "2";

	/**
	 * 配置类型 催发声音提示
	 */
	public static final String DB_PROTOCOL_CONFIG_TYPE_VOICE_HASTEN = "3";

	/**
	 * 设备上线
	 */
	public static final String DB_DEVICE_STATUS_ONLINE = "1";

	/**
	 * 设备离线
	 */
	public static final String DB_DEVICE_STATUS_OFFLINE = "2";

	/**
	 * 设备配置的线路号
	 */
	public static final String DEVICE_LINE_ID = "DEVICE_LINE_ID";

	/**
	 * 线路类型
	 */
	public static final String LINE_TYPE = "LINE_TYPE";

	/**
	 * 上一个点与这个点连线角度
	 */
	public static final String PRE_NOW_ANGLE = "PRE_NOW_ANGLE";

	/**
	 * 当前站点对象
	 */
	// public static final String LINE_STATION="LINE_STATION";
	/**
	 * 进出站数据的站点
	 */
	public static final String LINE_STATION_SEQ = "LINE_STATION_SEQ";
	/** 【原始】进出站站序号*/
	public static final String LINE_STATION_SEQ_ORG = "LINE_STATION_SEQ_ORG";
	/** 【原始】进出站 线路上下行*/
	public static final String LINE_TYPE_ORG = "LINE_TYPE_ORG";
	/** 【原始】进出站名称*/
	public static final String LINE_STATION_NAME_ORG = "LINE_STATION_NAME_ORG";
	/**
	 * 无法判断线路类型
	 */
	public static final String LINE_TYPE_NO = "LINE_TYPE_NO";

	/**
	 * 首数据
	 */
	public static final String FIRST = "FIRST";


	/** 当前趟次（实际跑的趟次） */
	public static final String CURRENT_TRIP = "CURRENT_TRIP";
//	public static final String CURRENT_TRIP_NUMBER = "CURRENT_TRIP_NUMBER";

	/** * 当前趟次发车时间（实际发车时间） */
	public static final String CURRENT_TRIP_DATE = "CURRENT_TRIP_DATE";
	public static final String CURRENT_TRIP_PLAN_DATE = "CURRENT_TRIP_PLAN_DATE";
	
	/**
	 * 下一个班次
	 */
	public static final String NEXT_SHITF = "NEXT_SHITF";

	/**
	 * 下一个趟次
	 */
	public static final String NEXT_TRIP = "NEXT_TRIP";
	/**
	 * 下一个趟次发车时间
	 */
	public static final String NEXT_TRIP_DATE = "NEXT_TRIP_DATE";
	
	/**
	 * 是否覆盖 1 覆盖
	 */
	public static final String COVERED = "1";
	/**
	 * 是否覆盖 2未 覆盖
	 */
	public static final String COVERING = "2";
	/**
	 * 是否下发 1 下发
	 */
	public static final String SENDED = "1";
	/**
	 * 是否下发2未下发
	 */
	public static final String SENDING = "2";


	public static final String REDIS_DEVICE_UPDATE_KEY="DEVICE_UPDATE";

	public static final String REDIS_DEVICE_UPDATE_STOP_KEY="DEVICE_UPDATE_STOP";


	public static final String WEB_RESULT_OK="({code:\"200\"})";

	public static final String WEB_RESULT_ERROR="({code:\"500\"})";


}
