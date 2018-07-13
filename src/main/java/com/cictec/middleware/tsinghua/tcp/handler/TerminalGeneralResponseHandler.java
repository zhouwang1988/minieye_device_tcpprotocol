package com.cictec.middleware.tsinghua.tcp.handler;


import com.cictec.middleware.tsinghua.tcp.annotation.HandleMessages;
import com.cictec.middleware.tsinghua.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalGeneralResponse;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 0x0001 通用回复处理类TerminalGeneralResponseHandler类
 * 
 * @file TerminalGeneralResponseHandler.java
 * @author zoboy
 * @version 2.0.0 Copyright(C), 2017 xi'an Coordinates Software Development Co.,
 *          Ltd.
 */

@HandleMessages(0x0001)
@Component
public class TerminalGeneralResponseHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalGeneralResponseHandler.class);


	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {
		TerminalGeneralResponse tgr = (TerminalGeneralResponse) msg;
		logger.info("收到设备应答 {}  Session {}", tgr.toString(), msg.getSessionId());
	}
}
