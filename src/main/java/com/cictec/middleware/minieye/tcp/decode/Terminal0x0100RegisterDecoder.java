package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalRegisterRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0100 设备注册TerminalRegisterDecoder类
 * @file  TerminalRegisterDecoder.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0100)
public class Terminal0x0100RegisterDecoder extends AbstractMessageDecoder {


	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {


		TerminalRegisterRequest tcr = new TerminalRegisterRequest(header);

        tcr.setProvinceId(BinaryUtils.wordToInt(in));
        tcr.setCityId(BinaryUtils.wordToInt(in));
        tcr.setManufacturerId(BinaryUtils.byte2ToStr(in,5));
        tcr.setTerminalVesion(BinaryUtils.byte2ToStr(in,20));
        //设备编号包含有字母
        String ternalId = BinaryUtils.byte2ToStr(in,7);
        tcr.setTerminalId(ternalId.toUpperCase());//全部转换成大写
        tcr.setCarColor(BinaryUtils.unsignedByteToInt(in)+"");
        tcr.setCarNum(BinaryUtils.bytesToString(in, in.limit()-in.position()));

		return tcr;
	}
	

}
