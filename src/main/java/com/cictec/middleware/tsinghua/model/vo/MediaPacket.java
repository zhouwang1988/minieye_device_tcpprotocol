package com.cictec.middleware.tsinghua.model.vo;

import java.io.File;

import com.cictec.middleware.tsinghua.tcp.message.request.TerminalDataUploadRequest;

public class MediaPacket {

	private TerminalDataUploadRequest terminalDataUploadRequest;

	public MediaPacket() {
		
	}

	/**
	 * 消息包总数
	 */
	private int packageCounts;
	/**
	 * 消息包序号
	 */
	private int packageNum;

	private File multimediaPacket;// 多媒体数据包

	public int getPackageCounts() {
		return packageCounts;
	}

	public void setPackageCounts(int packageCounts) {
		this.packageCounts = packageCounts;
	}

	public int getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	public File getMultimediaPacket() {
		return multimediaPacket;
	}

	public void setMultimediaPacket(File multimediaPacket) {
		this.multimediaPacket = multimediaPacket;
	}

	public TerminalDataUploadRequest getTerminalDataUploadRequest() {
		return terminalDataUploadRequest;
	}

	public void setTerminalDataUploadRequest(TerminalDataUploadRequest terminalDataUploadRequest) {
		this.terminalDataUploadRequest = terminalDataUploadRequest;
	}

}
