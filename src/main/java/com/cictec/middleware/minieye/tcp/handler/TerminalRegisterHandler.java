package com.cictec.middleware.minieye.tcp.handler;


import com.alibaba.fastjson.JSON;
import com.cictec.middleware.minieye.config.ResultCode;
import com.cictec.middleware.minieye.entity.po.TDevice;
import com.cictec.middleware.minieye.service.TDeviceService;
import com.cictec.middleware.minieye.tcp.annotation.HandleMessages;
import com.cictec.middleware.minieye.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalRegisterRequest;
import com.cictec.middleware.minieye.tcp.message.response.PlatformRegisterResponse;
import com.cictec.middleware.minieye.utils.DateUtils;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 注册处理类TerminalRegisterHandler类
 * 
 * @file TerminalRegisterHandler.java
 * @author zoboy
 * @version 2.0.0 Copyright(C), 2017 xi'an Coordinates Software Development Co.,
 *          Ltd.
 */
@HandleMessages(0x0100)
@Component
public class TerminalRegisterHandler extends AbstractReceiverHandler {

	@Autowired
	private TDeviceService tDeviceService;
	private static Logger logger = LoggerFactory.getLogger(TerminalRegisterHandler.class);
	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {
		
		TerminalRegisterRequest tcr = (TerminalRegisterRequest)msg;
		String id=UUID.randomUUID().toString();
        logger.info("终端 {}  Session {} 连接请求 设备ID {}  ",msg.getTerminalId(),msg.getSessionId(),tcr.getTerminalId());
		String resultContent =null;
    	
        TDevice device=null;
        device = tDeviceService.findByDevCode(msg.getHeader().getTerminalId());
        tcr.getHeader().setTerminalId(msg.getTerminalId());
        resultContent ="中航讯多媒体设备:"+msg.getTerminalId()+"注册请求";
        recodeLogToEs(tcr.getTerminalId(), id, new Date(), ResultCode.MES_REGISTER+"", resultContent);
        
        TerminalMessage.Header registerResponseHeader = new TerminalMessage.Header(0x8100);
        registerResponseHeader.setTerminalId(tcr.getHeader().getTerminalId());
        registerResponseHeader.setMessageSequence(tcr.getHeader().getMessageSequence());
        PlatformRegisterResponse registerResponse = new PlatformRegisterResponse(registerResponseHeader);

        //设备启用,并且设备存在
        if(device!=null ){
			registerResponse.setKey(device.getDevKey());
			registerResponse.setResponseResult(PlatformRegisterResponse.RESPONSE_RESULT_SUCCESS);
			resultContent ="设备:"+device.getDevCode()+"注册成功！鉴权码："+device.getDevKey();
			recodeLogToEs(tcr.getTerminalId(), id, new Date(), ResultCode.MES_SUCCESS+"", resultContent);

        }else {
        	 registerResponse.setResponseResult(PlatformRegisterResponse.RESPONSE_RESULT_WITHOUT_DEVICE);
        	 resultContent ="设备:"+tcr.getHeader().getTerminalId()+"注册失败！数据库不存在该设备，请在 多媒体管理系统 【设备管理】补充该设备信息 ！";
			 recodeLogToEs(tcr.getTerminalId(), id, new Date(), ResultCode.MES_FAILED+"", resultContent);
        }
        registerResponse.setResponseSequence(tcr.getHeader().getMessageSequence());
        sendMessageToTerminal(registerResponse,session);
	}
	
	private void recodeLogToEs(String deviceId,String id, Date date,String resultCode,String resultContent){
		Map<String,String> map=new HashMap<String,String>();
		map=new HashMap<String,String>();
		map.put("command", "注册命令");
		map.put("esname",ResultCode.ES_NAME);
		map.put("command", ResultCode.MES_REGISTER_NAME);
		map.put("agreement", ResultCode.JT808);
		map.put("id",id);
		map.put("deviceId", deviceId);
		map.put("date", DateUtils.formatDateTime(date));
		map.put("time", DateUtils.formatTime(date));
		map.put("type", ResultCode.MES_REGISTER+"");
		map.put("resultCode", resultCode+"");
		map.put("result", resultContent);
		logger.info(JSON.toJSONString(map));
	}

}
