package com.cictec.middleware.tsinghua.tcp.code;

import java.nio.ByteBuffer;


public abstract class AbstractMessageEncoder  extends AbstractMessageCodec implements MessageEncoder {

	protected byte[] toBytes(ByteBuffer bb){
		byte[] bytes = new byte[bb.position()];
		bb.flip();
		bb.get(bytes);
		return bytes;
	}
}
