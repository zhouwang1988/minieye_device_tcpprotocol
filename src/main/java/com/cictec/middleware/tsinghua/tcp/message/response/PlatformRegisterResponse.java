package com.cictec.middleware.tsinghua.tcp.message.response;


import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

/**
 * 0x8100 注册应答  平台通用应答应答PlatformRegisterResponse类
 * @file  PlatformRegisterResponse.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
public class PlatformRegisterResponse extends TerminalMessage {

	/*
	 * 成功 
	 */
	public static final int RESPONSE_RESULT_SUCCESS=0;
	/*
	 * 车辆以被注册
	 */
    public static final int RESPONSE_RESULT_CAR_REGISTERED=1;
    /*
	 * 数据库中无该车辆
	 */
    public static final int RESPONSE_RESULT_WITHOUT_CAR=2;
    /*
	 * 终端已被注册
	 */    
    public static final int RESPONSE_RESULT_DEVICE_REGISTERED=3;
    /*
	 * 数据库中无该终端
	 */
    public static final int RESPONSE_RESULT_WITHOUT_DEVICE=4;



    /*应答流水号*/
	private int  responseSequence;
	/*应答类型*/
	private int  responseResult;
	/*预留*/
	private String  key;

	public PlatformRegisterResponse(Header header) {
		super(header);
	}

    public int getResponseSequence() {
        return responseSequence;
    }

    public void setResponseSequence(int responseSequence) {
        this.responseSequence = responseSequence;
    }

    public int getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(int responseResult) {
        this.responseResult = responseResult;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
