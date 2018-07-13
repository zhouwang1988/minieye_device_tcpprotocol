package com.cictec.middleware.tsinghua.tcp.decode;

import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalReplyTextMessageDecoder;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0310 终端应答命令TerminalReplyTextMessageDecoder类
 * @file  TerminalReplyTextMessageDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0310)
public class Terminal0x0310ReplyTextMessageDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalReplyTextMessageDecoder tcr = new TerminalReplyTextMessageDecoder(header);

		tcr.setMark(BinaryUtils.unsignedByteToInt(in));
		tcr.setText(BinaryUtils.bytesToString(in, in.limit()-in.position()));
		
		return tcr;
	}
	

}
