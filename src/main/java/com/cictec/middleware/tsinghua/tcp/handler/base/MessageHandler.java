package com.cictec.middleware.tsinghua.tcp.handler.base;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import org.apache.mina.core.session.IoSession;


public interface MessageHandler {
	void handleMessage(TerminalMessage msg, IoSession session);
}
