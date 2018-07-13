package com.cictec.middleware.tsinghua.tcp.decode;

import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalSendVersionNumDecoder;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0F10 终端发送程序版本号TerminalSendVersionNumDecoder类
 * @file  TerminalSendVersionNumDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0F10)
public class Terminal0x0F10SendVersionNumDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalSendVersionNumDecoder tcr = new TerminalSendVersionNumDecoder(header);

		tcr.setCounts(BinaryUtils.wordToInt(in));
		tcr.setProgram1VersionNum(BinaryUtils.byte2ToStr(in,32));
		tcr.setProgram2VersionNum(BinaryUtils.byte2ToStr(in,32));
		tcr.setProgram3VersionNum(BinaryUtils.byte2ToStr(in,32));
		
		return tcr;
	}
	

}
