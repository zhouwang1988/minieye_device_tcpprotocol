package com.cictec.middleware.tsinghua.tcp.encode;


import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformRespondCommandResponse;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformUpdateCommandResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;

import java.nio.ByteBuffer;

/**
 * 0x8F01 服务器回复命令 PlatformUpdateCommandEncoder类
 * @file  PlatformUpdateCommandEncoder.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8F01)
public class Platform0x8F01UpdateCommandEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformUpdateCommandResponse pr = (PlatformUpdateCommandResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.intToUnsignedByte(pr.getUpdateType(), out);
        BinaryUtils.intToDword(pr.getUpdateFileLength(), out);
        BinaryUtils.intToWord(pr.getUpdateFileCRC(), out);
        BinaryUtils.intToByte4(pr.getUpdateFileVersion(), out);
        return toBytes(out);

	}

}
