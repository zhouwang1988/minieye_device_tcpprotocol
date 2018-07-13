package com.cictec.middleware.minieye.tcp.code;


import com.cictec.middleware.minieye.tcp.message.request.TerminalAdditionalMessage;

public interface MessageAdditionalEncoder {

	byte[] encode(TerminalAdditionalMessage msg);
	
}
