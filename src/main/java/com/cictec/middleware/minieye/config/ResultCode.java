package com.cictec.middleware.minieye.config;

public class ResultCode {

	/**
	 * 日志名称
	 */
	final static public String ES_NAME = "mid-log";
	/**
	 * 协议名称
	 */
	final static public String JT808 = "JT808";
	/**
	 * 请求
	 */
	final static public int MES_REQUEST = 1;
	/**
	 * 成功
	 */
	final static public int MES_SUCCESS = 200;
	/**
	 * 失败
	 */
	final static public int MES_FAILED = 400;
	/**
	 * 鉴权编码
	 */
	final static public String MES_LOGIN = "0x0102";
	/**
	 * 鉴权名称
	 */
	final static public String MES_LOGIN_NAME = "终端鉴权";
	/**
	 * 离线
	 */
	final static public String MES_OFFLINE = "0x0101";
	/**
	 * 离线
	 */
	final static public String MES_OFFLINE_NAME = "下线记录";
	/**
	 * 注册
	 */
	final static public String MES_REGISTER = "0x0100";
	/**
	 * 注册
	 */
	final static public String MES_REGISTER_NAME = "注册命令";
	/**
	 *  线路信息请求
	 */
	final static public String MES_LINE_REQUEST = "0x0081";
	/**
	 *  线路信息请求
	 */
	final static public String MES_LINE_REQUEST_NAME = "线路信息请求";
	/**
	 *  播放记录
	 */
	final static public String MES_PLAY_RECDODE = "0x0082";
	/**
	 *  播放记录
	 */
	final static public String MES_PLAY_RECDODE_NAME = "播放记录";

	/**
	 * 线路配置信息
	 */
	final static public String MES_LINE_CONFIG = "0x0087";
	/**
	 * 线路配置信息
	 */
	final static public String MES_LINE_CONFIG_NAME = "线路配置信息";
	
	/**
	 * 文件下载信息
	 */
	final static public String MES_FILE_LOAD_FINISH = "0x0088";
	/**
	 * 文件下载信息
	 */
	final static public String MES_FILE_LOAD_FINISH_NAME = "文件下载信息上传";
	/**
	 *  下发更新
	 */
	final static public String MES_DISPUTE = "0x8801";
	/**
	 *  下发更新
	 */
	final static public String MES_DISPUTE_NAME = "下发更新";
	/**
	 *  下发即时通知
	 */
	final static public String MES_DISPUTE_NOTICE = "0x8300";
	/**
	 *  下发即时通知
	 */
	final static public String MES_DISPUTE_NOTICE_NAME = "下发即时通知";
	
}
