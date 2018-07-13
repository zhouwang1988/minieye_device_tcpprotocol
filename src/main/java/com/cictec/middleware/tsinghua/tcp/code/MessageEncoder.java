package com.cictec.middleware.tsinghua.tcp.code;


import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public interface MessageEncoder {

	byte[] encode(TerminalMessage msg) throws CanntFoundEncoder;
	
	
}
