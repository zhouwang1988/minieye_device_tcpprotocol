package com.cictec.middleware.tsinghua.tcp.code;

public class AbstractMessageCodec {

	private CodecFounder codecFounder;

	public AbstractMessageCodec() {
		super();
	}

	public CodecFounder getCodecFounder() {
		return codecFounder;
	}

	public void setCodecFounder(CodecFounder codecFounder) {
		this.codecFounder = codecFounder;
	}

}