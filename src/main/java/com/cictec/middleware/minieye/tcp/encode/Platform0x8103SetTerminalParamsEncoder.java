package com.cictec.middleware.minieye.tcp.encode;


import com.cictec.middleware.minieye.model.vo.TerminalParams;
import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageEncoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundEncoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.response.PlatformSetTerminalParamsResponse;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 0x8103 设置终端参数 PlatformSetTerminalParamsEncoder类
 * @file  PlatformSetTerminalParamsResponse.java
 * @author mjj
 * @version 2.0.0
 * Copyright(C), 2018
 */
@TerminalMessageEncoder(0x8103)
public class Platform0x8103SetTerminalParamsEncoder extends AbstractMessageEncoder {

	@Override
	public byte[] encode(TerminalMessage msg) throws CanntFoundEncoder {


		PlatformSetTerminalParamsResponse pr = (PlatformSetTerminalParamsResponse)msg;

        ByteBuffer out = ByteBuffer.allocate(256);
        
        BinaryUtils.intToUnsignedByte(pr.getParamsList().size(),out);
        
        List<TerminalParams> params = pr.getParamsList();
        
        for( int i = 0 ; i < params.size() ; i++) {
        	int paramID = params.get(i).getParamID();
        	BinaryUtils.intToDword(paramID,out);
        	BinaryUtils.intToUnsignedByte(params.get(i).getParamValue().length(),out);
        	if (paramID==0x0013 || paramID==0x0017 ) {
        		BinaryUtils.stringToBytes(params.get(i).getParamValue(),out);
			}else if(paramID==0x0018 || paramID==0x0028 || paramID==0x0029 || paramID==0x0055 || paramID==0x0056 || paramID==0x0057) {
				BinaryUtils.stringToByte(params.get(i).getParamValue(), 4 ,out);
			}else if(paramID==0xF00c){
				BinaryUtils.stringToByte(params.get(i).getParamValue(), 2 ,out);
			}else {
				BinaryUtils.stringToByte(params.get(i).getParamValue(), 1 ,out);;
			}
        }
        return toBytes(out);

	}

}
