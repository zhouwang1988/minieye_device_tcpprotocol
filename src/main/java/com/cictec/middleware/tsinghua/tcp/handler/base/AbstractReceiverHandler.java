package com.cictec.middleware.tsinghua.tcp.handler.base;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import org.apache.mina.core.session.IoSession;


public abstract class  AbstractReceiverHandler implements MessageHandler{

	public AbstractReceiverHandler() {
		super();
	}

	public void sendMessageToTerminal(TerminalMessage msg, IoSession session) {
		session.write(msg);
	}

}
