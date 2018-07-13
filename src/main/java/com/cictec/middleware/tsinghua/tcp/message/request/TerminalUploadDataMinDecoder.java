package com.cictec.middleware.tsinghua.tcp.message.request;

import java.util.Date;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalUploadDataMinDecoder extends TerminalMessage {
	
	private Date uploadTime;// 时间
	private String speed60;//60 秒速度
	private String centerLineDistance;//60 秒中心线距离
	public TerminalUploadDataMinDecoder(Header header) {
        super(header);
    }
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getSpeed60() {
		return speed60;
	}
	public void setSpeed60(String speed60) {
		this.speed60 = speed60;
	}
	public String getCenterLineDistance() {
		return centerLineDistance;
	}
	public void setCenterLineDistance(String centerLineDistance) {
		this.centerLineDistance = centerLineDistance;
	}
	
	
}
