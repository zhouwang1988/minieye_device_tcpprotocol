package com.cictec.middleware.minieye.tcp.message.request;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalLocationRequest;

public class TerminalDataUploadRequest extends TerminalMessage {
	private int multimediaID;// 多媒体ID
	private String multimediaType;// 多媒体类型
	private String multimediaCode;// 多媒体格式编码
	private String multimediaEventCode;// 事件项编码
	private String channelID;// 通道ID
	private TerminalLocationRequest terminalLocationRequest;// 位置信息汇报

	private byte[] multimediaPacket;// 多媒体数据包

	public TerminalDataUploadRequest(Header header) {
		super(header);
	}
	public TerminalDataUploadRequest() {

	}

	public int getMultimediaID() {
		return multimediaID;
	}

	public void setMultimediaID(int multimediaID) {
		this.multimediaID = multimediaID;
	}

	public String getMultimediaType() {
		return multimediaType;
	}

	public void setMultimediaType(String multimediaType) {
		this.multimediaType = multimediaType;
	}

	public String getMultimediaCode() {
		return multimediaCode;
	}

	public void setMultimediaCode(String multimediaCode) {
		this.multimediaCode = multimediaCode;
	}

	public String getMultimediaEventCode() {
		return multimediaEventCode;
	}

	public void setMultimediaEventCode(String multimediaEventCode) {
		this.multimediaEventCode = multimediaEventCode;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public TerminalLocationRequest getTerminalLocationRequest() {
		return terminalLocationRequest;
	}

	public void setTerminalLocationRequest(TerminalLocationRequest terminalLocationRequest) {
		this.terminalLocationRequest = terminalLocationRequest;
	}

	public byte[] getMultimediaPacket() {
		return multimediaPacket;
	}

	public void setMultimediaPacket(byte[] multimediaPacket) {
		this.multimediaPacket = multimediaPacket;
	}

}
