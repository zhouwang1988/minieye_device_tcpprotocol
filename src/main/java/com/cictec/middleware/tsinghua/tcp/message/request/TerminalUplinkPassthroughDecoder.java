package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalUplinkPassthroughDecoder extends TerminalMessage {
	
	private int type;//	透传消息类型
	
	public TerminalUplinkPassthroughDecoder(Header header) {
        super(header);
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
