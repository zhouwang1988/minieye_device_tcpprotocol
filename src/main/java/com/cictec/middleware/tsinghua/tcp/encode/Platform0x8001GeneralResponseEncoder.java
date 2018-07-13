package com.cictec.middleware.tsinghua.tcp.encode;


import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformGeneralResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;

import java.nio.ByteBuffer;

/**
 * 0x8001 平台通用应答编码TerminalGeneralResponseEncoder类
 * @file  TerminalGeneralResponseEncoder.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageEncoder(0x8001)
public class Platform0x8001GeneralResponseEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {

        PlatformGeneralResponse pr = (PlatformGeneralResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.intToWord(pr.getResponseSequence(),out);
        BinaryUtils.intToWord(pr.getResponseMsgId(),out);
        BinaryUtils.intToUnsignedByte(pr.getResponseResult(),out);


        return toBytes(out);

	}

}