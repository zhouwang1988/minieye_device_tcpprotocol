package com.cictec.middleware.tsinghua.tcp.code;


import com.cictec.middleware.tsinghua.tcp.message.request.TerminalAdditionalMessage;

public interface MessageAdditionalEncoder {

	byte[] encode(TerminalAdditionalMessage msg);
	
}
