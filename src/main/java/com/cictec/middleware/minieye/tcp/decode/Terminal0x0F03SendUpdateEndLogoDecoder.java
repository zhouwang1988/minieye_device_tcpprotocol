package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalSendUpdateEndLogoRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0F03 终端发送更新完成标识TerminalSendUpdateEndLogoDecoder类
 * @file  TerminalSendUpdateEndLogoDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0F03)
public class Terminal0x0F03SendUpdateEndLogoDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalSendUpdateEndLogoRequest tcr = new TerminalSendUpdateEndLogoRequest(header);
		
		tcr.setUpdateType(BinaryUtils.unsignedByteToInt(in));
		tcr.setUpdateFileCRC(BinaryUtils.wordToInt(in)+"");
		tcr.setUpdateResult(BinaryUtils.unsignedByteToInt(in));
		return tcr;
	}
	

}
