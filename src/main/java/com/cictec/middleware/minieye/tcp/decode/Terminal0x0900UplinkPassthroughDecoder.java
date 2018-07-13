package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalUplinkPassthroughDecoder;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0900 上行透传 TerminalUplinkPassthroughDecoder类
 * @file  TerminalUplinkPassthroughDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0900)
public class Terminal0x0900UplinkPassthroughDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalUplinkPassthroughDecoder tcr = new TerminalUplinkPassthroughDecoder(header);

		tcr.setType(BinaryUtils.unsignedByteToInt(in));
		
		return tcr;
	}
	

}
