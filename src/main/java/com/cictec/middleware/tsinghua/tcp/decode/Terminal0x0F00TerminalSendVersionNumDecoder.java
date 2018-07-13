package com.cictec.middleware.tsinghua.tcp.decode;

import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalTerminalSendVersionNumRequest;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0F00 终端发送程序版本号 TerminalTerminalSendVersionNumDecoder类
 * @file  TerminalTerminalSendVersionNumDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0F00)
public class Terminal0x0F00TerminalSendVersionNumDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalTerminalSendVersionNumRequest tcr = new TerminalTerminalSendVersionNumRequest(header);

		tcr.setArmFilecrc(BinaryUtils.wordToInt(in));
		tcr.setArmFileVersion(BinaryUtils.byte2ToStr(in,4));
		tcr.setDspFilecrc(BinaryUtils.wordToInt(in));
		tcr.setDspFileVersion(BinaryUtils.byte2ToStr(in,4));
		
		return tcr;
	}
	

}
