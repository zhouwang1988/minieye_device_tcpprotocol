package com.cictec.middleware.minieye.tcp.message.response;

import java.util.List;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class PlatformResponsePacketResponse extends TerminalMessage {

	private int updateType;//	更新类型
	private int updateFileCRC;// 更新文件CRC
	private int updatePackageNum;// 更新包序号 
	private int updatePackageLength;// 更新包长度
	private List<Integer> updatePackageContent;// 更新包内容
	
    public PlatformResponsePacketResponse(Header header) {
        super(header);
    }

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public int getUpdateFileCRC() {
		return updateFileCRC;
	}

	public void setUpdateFileCRC(int updateFileCRC) {
		this.updateFileCRC = updateFileCRC;
	}

	public int getUpdatePackageNum() {
		return updatePackageNum;
	}

	public void setUpdatePackageNum(int updatePackageNum) {
		this.updatePackageNum = updatePackageNum;
	}

	public int getUpdatePackageLength() {
		return updatePackageLength;
	}

	public void setUpdatePackageLength(int updatePackageLength) {
		this.updatePackageLength = updatePackageLength;
	}

	public List<Integer> getUpdatePackageContent() {
		return updatePackageContent;
	}

	public void setUpdatePackageContent(List<Integer> updatePackageContent) {
		this.updatePackageContent = updatePackageContent;
	}

}
