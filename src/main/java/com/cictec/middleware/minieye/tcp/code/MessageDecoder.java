package com.cictec.middleware.minieye.tcp.code;

import org.apache.mina.core.session.IoSession;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

import java.nio.ByteBuffer;


public interface MessageDecoder {
	
	TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder;
	
}
