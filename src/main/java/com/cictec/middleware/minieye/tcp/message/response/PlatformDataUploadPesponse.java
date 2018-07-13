package com.cictec.middleware.minieye.tcp.message.response;

import java.util.Date;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class PlatformDataUploadPesponse extends TerminalMessage {

	private int multimediaType;//	多媒体类型
	private String channelID;//	通道ID
	private int multimediaEventCode;//	事件项编码
	private Date startTime;//	起始时间
	private Date endTime;//	结束时间
	private int deleteLogo;//	删除标识
	
	private Date sysTime;//系统时间
	
    public PlatformDataUploadPesponse(Header header) {
        super(header);
    }
    
	public Date getSysTime() {
		return sysTime;
	}



	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}



	public int getMultimediaType() {
		return multimediaType;
	}

	public void setMultimediaType(int multimediaType) {
		this.multimediaType = multimediaType;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public int getMultimediaEventCode() {
		return multimediaEventCode;
	}

	public void setMultimediaEventCode(int multimediaEventCode) {
		this.multimediaEventCode = multimediaEventCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDeleteLogo() {
		return deleteLogo;
	}

	public void setDeleteLogo(int deleteLogo) {
		this.deleteLogo = deleteLogo;
	}
	
}
