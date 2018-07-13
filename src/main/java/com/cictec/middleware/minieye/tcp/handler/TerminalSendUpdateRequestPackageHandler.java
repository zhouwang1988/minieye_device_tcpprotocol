package com.cictec.middleware.minieye.tcp.handler;


import com.cictec.middleware.minieye.tcp.annotation.HandleMessages;
import com.cictec.middleware.minieye.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalSendUpdateEndLogoRequest;
import com.cictec.middleware.minieye.tcp.message.request.TerminalSendUpdateRequestPackageRequest;
import com.cictec.middleware.minieye.tcp.message.response.PlatformGeneralResponse;
import com.cictec.middleware.minieye.tcp.message.response.PlatformResponsePacketResponse;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 终端发送更新请求包TerminalSendUpdateRequestPackageHandler类
 * 
 * @file TerminalSendUpdateRequestPackageRequest.java
 * @author mjj
 */
@HandleMessages(0x0F02)
@Component
public class TerminalSendUpdateRequestPackageHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalSendUpdateRequestPackageHandler.class);
	
	
	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {
		
		TerminalSendUpdateRequestPackageRequest tcr = (TerminalSendUpdateRequestPackageRequest)msg;
        logger.info("终端 {}  Session {} 版本 ",msg.getTerminalId(),msg.getSessionId());

        
        tcr.getHeader().setTerminalId(msg.getTerminalId());
        TerminalMessage.Header sendUpdateRequestPackage = new TerminalMessage.Header(0x8F02);
        sendUpdateRequestPackage.setTerminalId(tcr.getHeader().getTerminalId());
        sendUpdateRequestPackage.setMessageSequence(tcr.getHeader().getMessageSequence());
        
        PlatformResponsePacketResponse platformResponsePacketResponse = new PlatformResponsePacketResponse(sendUpdateRequestPackage);
        platformResponsePacketResponse.setUpdateType(tcr.getUpdateType());
        platformResponsePacketResponse.setUpdateFileCRC(Integer.parseInt(tcr.getUpdateFileCRC()));
        platformResponsePacketResponse.setUpdatePackageNum(Integer.parseInt(tcr.getUpdatePackageNum()));
        sendMessageToTerminal(platformResponsePacketResponse,session);
	}

}
