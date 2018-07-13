package com.cictec.middleware.minieye.tcp.message.response;

import java.util.Date;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class PlatformRespondCommandResponse extends TerminalMessage {

	private Date sysTime;//	系统时间
	
    public PlatformRespondCommandResponse(Header header) {
        super(header);
    }

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
    
}
