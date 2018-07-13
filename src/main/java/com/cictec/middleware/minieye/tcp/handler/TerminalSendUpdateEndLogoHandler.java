package com.cictec.middleware.minieye.tcp.handler;


import com.cictec.middleware.minieye.tcp.annotation.HandleMessages;
import com.cictec.middleware.minieye.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalSendUpdateEndLogoRequest;
import com.cictec.middleware.minieye.tcp.message.response.PlatformGeneralResponse;

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
@HandleMessages(0x0F03)
@Component
public class TerminalSendUpdateEndLogoHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalSendUpdateEndLogoHandler.class);
	
	
	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {
		
		TerminalSendUpdateEndLogoRequest tcr = (TerminalSendUpdateEndLogoRequest)msg;
        logger.info("终端 {}  Session {} 版本 ",msg.getTerminalId(),msg.getSessionId());

        
        tcr.getHeader().setTerminalId(msg.getTerminalId());
        TerminalMessage.Header sendUpdateEndLogoHeader = new TerminalMessage.Header(0x8001);
        sendUpdateEndLogoHeader.setTerminalId(tcr.getHeader().getTerminalId());
        sendUpdateEndLogoHeader.setMessageSequence(tcr.getHeader().getMessageSequence());
        
        PlatformGeneralResponse platformGeneralResponse = new PlatformGeneralResponse(sendUpdateEndLogoHeader);

		platformGeneralResponse.setResponseMsgId(tcr.getHeader().getMessageId());
		platformGeneralResponse.setResponseSequence(tcr.getHeader().getMessageSequence());
        sendMessageToTerminal(platformGeneralResponse,session);
	}

}
