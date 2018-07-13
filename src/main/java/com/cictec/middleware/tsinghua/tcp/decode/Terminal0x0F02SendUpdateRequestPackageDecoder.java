package com.cictec.middleware.tsinghua.tcp.decode;

import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalSendUpdateRequestPackageRequest;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0F02 发送更新请求包TerminalSendUpdateRequestPackageDecoder类
 * @file  TerminalSendUpdateRequestPackageDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0F02)
public class Terminal0x0F02SendUpdateRequestPackageDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {

		TerminalSendUpdateRequestPackageRequest tcr = new TerminalSendUpdateRequestPackageRequest(header);
		
		tcr.setUpdateType(BinaryUtils.unsignedByteToInt(in));
		tcr.setUpdateFileCRC(BinaryUtils.wordToInt(in)+"");
		tcr.setUpdatePackageNum(BinaryUtils.wordToInt(in)+"");
		return tcr;
	}
	

}
