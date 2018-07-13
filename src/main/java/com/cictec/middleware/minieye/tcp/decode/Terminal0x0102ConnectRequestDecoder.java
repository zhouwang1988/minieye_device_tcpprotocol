package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalAuthenticationRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;

/**
 * 0x0102终端连接请求指令解码TerminalConnectRequestDecoder类
 * @file  TerminalConnectRequestDecoder.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0102)
public class Terminal0x0102ConnectRequestDecoder extends AbstractMessageDecoder {
	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {
		TerminalAuthenticationRequest tcr = new TerminalAuthenticationRequest(header);
		tcr.setAuthenticationKey(BinaryUtils.bytesToString(in,in.limit()-in.position()));
		return tcr;
	}

}
