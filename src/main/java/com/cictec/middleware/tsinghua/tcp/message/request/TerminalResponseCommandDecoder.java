package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalResponseCommandDecoder extends TerminalMessage {
	
	private int commandReceiveId;//	更新命令接收标识
	
	public TerminalResponseCommandDecoder(Header header) {
        super(header);
    }

	public int getCommandReceiveId() {
		return commandReceiveId;
	}

	public void setCommandReceiveId(int commandReceiveId) {
		this.commandReceiveId = commandReceiveId;
	}

	
	
}
