package com.cictec.middleware.minieye.tcp.handler.base;

import org.apache.mina.core.session.IoSession;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;


public abstract class  AbstractReceiverHandler implements MessageHandler{

	public AbstractReceiverHandler() {
		super();
	}

	public void sendMessageToTerminal(TerminalMessage msg, IoSession session) {
		session.write(msg);
	}

}
