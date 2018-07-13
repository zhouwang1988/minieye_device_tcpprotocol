package com.cictec.middleware.tsinghua.tcp.encode;


import com.cictec.middleware.tsinghua.model.vo.TerminalParams;
import com.cictec.middleware.tsinghua.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.tsinghua.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformSetTerminalParamsResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 0x8106 设置终端参数 PlatformQueryTerminalParamsEncoder类
 * @file  PlatformSetTerminalParamsResponse.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8106)
public class Platform0x8106QueryTerminalParamsEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformSetTerminalParamsResponse pr = (PlatformSetTerminalParamsResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);
        
        BinaryUtils.intToUnsignedByte(pr.getParamsList().size(),out);
        
        List<TerminalParams> params = pr.getParamsList();
        
        for( int i = 0 ; i < params.size() ; i++) {
        	BinaryUtils.intToDword(params.get(i).getParamID(),out);
        }
        return toBytes(out);

	}

}
