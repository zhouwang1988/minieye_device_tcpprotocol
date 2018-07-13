package com.cictec.middleware.minieye.tcp.decode;


import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalGeneralResponse;
import com.cictec.middleware.minieye.tcp.message.response.PlatformGeneralResponse;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;


/**
 * 0x0001 终端通用应答TerminalGeneralResponseDecode类
 * @file  TerminalGeneralResponseDecode.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0001)
public class Terminal0x0001GeneralResponseDecoder extends AbstractMessageDecoder {

	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		PlatformGeneralResponse tgp=new PlatformGeneralResponse(header);
		tgp.setResponseSequence(BinaryUtils.wordToInt(in));
		tgp.setResponseMsgId(BinaryUtils.wordToInt(in));
		tgp.setResponseResult(BinaryUtils.unsignedByteToInt(in));

		return tgp;
	}

}
