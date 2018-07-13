package com.cictec.middleware.minieye.tcp.code;

import org.apache.mina.core.session.IoSession;

import com.cictec.middleware.minieye.tcp.message.request.TerminalAdditionalMessage;

import java.nio.ByteBuffer;


public interface MessageAdditionalDecoder {

	TerminalAdditionalMessage decode(ByteBuffer in, IoSession session);
}
