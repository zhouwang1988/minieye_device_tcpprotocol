package com.cictec.middleware.minieye.tcp.encode;


import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.response.PlatformDataUploadPesponse;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import java.nio.ByteBuffer;

/**
 * 0x8803 存储多媒体数据上传命令PlatformStorageDataUploadEncoder类
 * @file  PlatformStorageDataUploadEncoder.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8803)
public class Platform0x8803StorageDataUploadEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformDataUploadPesponse pr = (PlatformDataUploadPesponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.intToUnsignedByte(pr.getMultimediaType(),out);
        BinaryUtils.stringToBytes(pr.getChannelID(),out);
        BinaryUtils.intToUnsignedByte(pr.getMultimediaEventCode(),out);
        BinaryUtils.dateToBcd6(pr.getStartTime(),out);
        BinaryUtils.dateToBcd6(pr.getEndTime(),out);
        BinaryUtils.intToUnsignedByte(pr.getDeleteLogo(),out);
        return toBytes(out);

	}

}
