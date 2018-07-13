package com.cictec.middleware.minieye.tcp.code;


import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public interface MessageEncoder {

	byte[] encode(TerminalMessage msg) throws CanntFoundEncoder;
	
	
}
