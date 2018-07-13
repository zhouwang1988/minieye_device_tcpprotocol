package com.cictec.middleware.minieye.tcp.encode;


import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.response.PlatformResponsePacketResponse;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 0x8F02 服务器响应数据包  PlatformResponsePacketResponse类
 * @file  PlatformResponsePacketResponse.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8F02)
public class Platform0x8F02ResponsePacketEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformResponsePacketResponse pr = (PlatformResponsePacketResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.intToUnsignedByte(pr.getUpdateType(), out);
        BinaryUtils.intToWord(pr.getUpdateFileCRC(), out);
        BinaryUtils.intToWord(pr.getUpdatePackageNum(), out);
        BinaryUtils.intToWord(pr.getUpdatePackageLength(), out);
        List<Integer> packageContents = pr.getUpdatePackageContent();
        for (int i = 0; i < packageContents.size(); i++) {
        	BinaryUtils.intToUnsignedByte(packageContents.get(i),out);
		}
        return toBytes(out);

	}

}
