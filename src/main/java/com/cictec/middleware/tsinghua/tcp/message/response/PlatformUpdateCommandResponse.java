package com.cictec.middleware.tsinghua.tcp.message.response;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class PlatformUpdateCommandResponse extends TerminalMessage {

	private int updateType;//	更新类型
	private int updateFileLength;// 更新文件长度
	private int updateFileCRC;// 更新文件CRC
	private int updateFileVersion;// 更新文件版本
	
    public PlatformUpdateCommandResponse(Header header) {
        super(header);
    }

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public int getUpdateFileLength() {
		return updateFileLength;
	}

	public void setUpdateFileLength(int updateFileLength) {
		this.updateFileLength = updateFileLength;
	}

	public int getUpdateFileCRC() {
		return updateFileCRC;
	}

	public void setUpdateFileCRC(int updateFileCRC) {
		this.updateFileCRC = updateFileCRC;
	}

	public int getUpdateFileVersion() {
		return updateFileVersion;
	}

	public void setUpdateFileVersion(int updateFileVersion) {
		this.updateFileVersion = updateFileVersion;
	}

}
