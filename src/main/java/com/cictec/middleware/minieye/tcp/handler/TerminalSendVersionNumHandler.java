package com.cictec.middleware.minieye.tcp.handler;


import com.cictec.middleware.minieye.tcp.annotation.HandleMessages;
import com.cictec.middleware.minieye.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalTerminalSendVersionNumRequest;
import com.cictec.middleware.minieye.tcp.message.response.PlatformRespondCommandResponse;

import java.util.Date;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 数据上传类TerminalDataUpdateHandler类
 * 
 * @file TerminalDataUpdateHandler.java
 * @author mjj
 */
@HandleMessages(0x0F00)
@Component
public class TerminalSendVersionNumHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalSendVersionNumHandler.class);
	
	
	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {
		
		TerminalTerminalSendVersionNumRequest tcr = (TerminalTerminalSendVersionNumRequest)msg;
//		String id=UUID.randomUUID().toString();
        logger.info("终端 {}  Session {} 版本 ",msg.getTerminalId(),msg.getSessionId());
    	
//        GpsPoint gpsPoint = buildLocationMessage(tcr);

//        positionInfoReponsitory.save(gpsPoint);
        
        
        tcr.getHeader().setTerminalId(msg.getTerminalId());
        TerminalMessage.Header locationResponseHeader = new TerminalMessage.Header(0x8F00);
        locationResponseHeader.setTerminalId(tcr.getHeader().getTerminalId());
        locationResponseHeader.setMessageSequence(tcr.getHeader().getMessageSequence());
        
        PlatformRespondCommandResponse locationResponse = new PlatformRespondCommandResponse(locationResponseHeader);
        locationResponse.setSysTime(new Date());
        sendMessageToTerminal(locationResponse,session);
	}

}
