package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalQuerySpecifyParamDecoder;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;

/**
 * 0x0104 查询指定终端参数 TerminalQuerySpecifyParamDecoder类
 * @file  TerminalQuerySpecifyParamDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 */
@TerminalMessageDecoder(0x0104)
public class Terminal0x0104QuerySpecifyParamDecoder extends AbstractMessageDecoder {
	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {
		TerminalQuerySpecifyParamDecoder tcr = new TerminalQuerySpecifyParamDecoder(header);
		
		tcr.setSerialNum(BinaryUtils.unsignedByteToInt(in));
		tcr.setParamCounts(BinaryUtils.unsignedByteToInt(in));
		return tcr;
	}

}
