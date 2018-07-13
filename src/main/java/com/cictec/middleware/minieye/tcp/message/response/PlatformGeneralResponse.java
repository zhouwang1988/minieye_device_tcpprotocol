package com.cictec.middleware.minieye.tcp.message.response;


import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

/**
 * 0x8001 平台通用应答编码PlatformGeneralResponse类
 * @file  PlatformGeneralResponse.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
public class PlatformGeneralResponse extends TerminalMessage {

	public static final int RESPONSE_RESULT_SUCCESS=0;//成功/确认
    public static final int RESPONSE_RESULT_FALSE=1;//失败
    public static final int RESPONSE_RESULT_MSG_ERROR=2;//消息有误
    public static final int RESPONSE_RESULT_MSG_NOT_SUPPORT=3;//不支持



    //应答流水号
	private int  responseSequence;
    //应答消息ID
    private int  responseMsgId;
	//应答结果
	private int  responseResult;

	public PlatformGeneralResponse(Header header) {
		super(header);
	}

    public int getResponseSequence() {
        return responseSequence;
    }

    public void setResponseSequence(int responseSequence) {
        this.responseSequence = responseSequence;
    }

    public int getResponseMsgId() {
        return responseMsgId;
    }

    public void setResponseMsgId(int responseMsgId) {
        this.responseMsgId = responseMsgId;
    }

    public int getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(int responseResult) {
        this.responseResult = responseResult;
    }
}
