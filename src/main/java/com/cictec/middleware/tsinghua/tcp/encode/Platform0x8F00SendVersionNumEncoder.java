package com.cictec.middleware.tsinghua.tcp.encode;


import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformRespondCommandResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;

import java.nio.ByteBuffer;

/**
 * 0x8F00 服务器回复命令 PlatformRespondCommandEncoder类
 * @file  PlatformRespondCommandEncoder.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8F00)
public class Platform0x8F00SendVersionNumEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformRespondCommandResponse pr = (PlatformRespondCommandResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);

        BinaryUtils.dateToBcd6(pr.getSysTime(),out);
        return toBytes(out);

	}

}
