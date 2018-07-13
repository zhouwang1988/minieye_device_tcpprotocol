package com.cictec.middleware.minieye.tcp.handler.base;

import org.apache.mina.core.session.IoSession;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;


public interface MessageHandler {
	void handleMessage(TerminalMessage msg, IoSession session);
}
