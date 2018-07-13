package com.cictec.middleware.tsinghua.tcp.code;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;


public interface MessageDecoder {
	
	TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder;
	
}
