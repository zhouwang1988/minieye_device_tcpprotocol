package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalUploadDataMinDecoder;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0xFF 终端应答命令TerminalUploadDataMinDecoder类
 * @file  TerminalUploadDataMinDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 */
@TerminalMessageDecoder(0xFF)
public class Terminal0xFFUploadDataMinDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalUploadDataMinDecoder tcr = new TerminalUploadDataMinDecoder(header);
		
		tcr.setUploadTime(BinaryUtils.bcd6ToDate(in, 6));
		tcr.setSpeed60(BinaryUtils.byte2ToStr(in,60));
		tcr.setCenterLineDistance(BinaryUtils.byte2ToStr(in,60));
		return tcr;
	}
	

}
