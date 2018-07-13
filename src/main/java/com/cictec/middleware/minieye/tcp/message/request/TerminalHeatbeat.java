package com.cictec.middleware.minieye.tcp.message.request;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

/**
 * 0x05 心跳连接指令
 * @author Zhibin
 *
 */
public class TerminalHeatbeat extends TerminalMessage {
	
	private int reserved;
	
	public TerminalHeatbeat(Header header) {
		super(header);
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

}
