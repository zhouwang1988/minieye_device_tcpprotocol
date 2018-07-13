package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalQuerySpecifyParamDecoder extends TerminalMessage {
	
	private int serialNum;//	应答流水号
	private int paramCounts; //应答参数个数
	
	public TerminalQuerySpecifyParamDecoder(Header header) {
        super(header);
    }

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public int getParamCounts() {
		return paramCounts;
	}

	public void setParamCounts(int paramCounts) {
		this.paramCounts = paramCounts;
	}
	
	
}
