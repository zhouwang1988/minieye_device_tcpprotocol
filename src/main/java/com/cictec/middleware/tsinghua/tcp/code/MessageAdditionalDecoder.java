package com.cictec.middleware.tsinghua.tcp.code;

import com.cictec.middleware.tsinghua.tcp.message.request.TerminalAdditionalMessage;
import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;


public interface MessageAdditionalDecoder {

	TerminalAdditionalMessage decode(ByteBuffer in, IoSession session);
}
