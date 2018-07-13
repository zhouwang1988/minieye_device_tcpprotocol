package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalImmediatelyTakenDecoder;
import com.cictec.middleware.minieye.tcp.message.request.TerminalRegisterRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0805 摄像头立即拍摄命令TerminalImmediatelyTakenDecoder类
 * @file  TerminalImmediatelyTakenDecoder.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0805)
public class Terminal0x0805ImmediatelyTakenDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {


		TerminalImmediatelyTakenDecoder tcr = new TerminalImmediatelyTakenDecoder(header);

		tcr.setSerialNum(BinaryUtils.wordToInt(in));
		tcr.setResult(BinaryUtils.unsignedByteToInt(in));
		tcr.setIdCounts(BinaryUtils.wordToInt(in));
		int [] idList  =new int[tcr.getIdCounts()*4];
		for(int i=0;i<idList.length;i++){
			idList[i]=BinaryUtils.unsignedByteToInt(in);
		}
        tcr.setIdLists(idList);

		return tcr;
	}
	

}
