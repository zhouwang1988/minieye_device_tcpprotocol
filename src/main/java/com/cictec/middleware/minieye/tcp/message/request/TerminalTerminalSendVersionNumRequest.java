package com.cictec.middleware.minieye.tcp.message.request;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class TerminalTerminalSendVersionNumRequest extends TerminalMessage {
	private int armFilecrc;//	ARM文件CRC
	private String armFileVersion;//	ARM文件版本
	private int dspFilecrc;//	DSP文件 CRC
	private String dspFileVersion;//	DSP文件版本
    public TerminalTerminalSendVersionNumRequest(Header header) {
        super(header);
    }
	public int getArmFilecrc() {
		return armFilecrc;
	}
	public void setArmFilecrc(int armFilecrc) {
		this.armFilecrc = armFilecrc;
	}
	public int getDspFilecrc() {
		return dspFilecrc;
	}
	public void setDspFilecrc(int dspFilecrc) {
		this.dspFilecrc = dspFilecrc;
	}
	public String getArmFileVersion() {
		return armFileVersion;
	}
	public void setArmFileVersion(String armFileVersion) {
		this.armFileVersion = armFileVersion;
	}
	public String getDspFileVersion() {
		return dspFileVersion;
	}
	public void setDspFileVersion(String dspFileVersion) {
		this.dspFileVersion = dspFileVersion;
	}
    
}
