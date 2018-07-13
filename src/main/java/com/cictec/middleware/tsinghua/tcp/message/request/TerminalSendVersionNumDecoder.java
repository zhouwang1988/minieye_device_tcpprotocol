package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalSendVersionNumDecoder extends TerminalMessage {
	private int counts;//	终端程序个数
	private String program1VersionNum;//	程序 1 版本号
	private String program2VersionNum;//	程序2 版本号
	private String program3VersionNum;//	程序 3 版本号
	
	public TerminalSendVersionNumDecoder(Header header) {
        super(header);
    }

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public String getProgram1VersionNum() {
		return program1VersionNum;
	}

	public void setProgram1VersionNum(String program1VersionNum) {
		this.program1VersionNum = program1VersionNum;
	}

	public String getProgram2VersionNum() {
		return program2VersionNum;
	}

	public void setProgram2VersionNum(String program2VersionNum) {
		this.program2VersionNum = program2VersionNum;
	}

	public String getProgram3VersionNum() {
		return program3VersionNum;
	}

	public void setProgram3VersionNum(String program3VersionNum) {
		this.program3VersionNum = program3VersionNum;
	}
	
}
