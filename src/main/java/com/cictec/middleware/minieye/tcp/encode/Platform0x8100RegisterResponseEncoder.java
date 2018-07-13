package com.cictec.middleware.minieye.tcp.encode;


import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.response.PlatformRegisterResponse;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import java.nio.ByteBuffer;

/**
 * 0x8100 终端注册应答编码PlatformRegisterResponseEncoder类
 * @file  PlatformRegisterResponseEncoder.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageEncoder(0x8100)
public class Platform0x8100RegisterResponseEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


        PlatformRegisterResponse pr = (PlatformRegisterResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.intToWord(pr.getResponseSequence(),out);
        BinaryUtils.intToUnsignedByte(pr.getResponseResult(),out);
        if(pr.getResponseResult()==0){
            BinaryUtils.stringToBytes(pr.getKey(),out);
        }
        return toBytes(out);

	}

}
