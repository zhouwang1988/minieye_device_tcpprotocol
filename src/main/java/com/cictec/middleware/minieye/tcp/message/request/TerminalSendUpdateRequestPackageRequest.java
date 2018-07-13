package com.cictec.middleware.minieye.tcp.message.request;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class TerminalSendUpdateRequestPackageRequest extends TerminalMessage {

	private int updateType;//	更新类型
	private String updateFileCRC;//	更新文件 CRC
	private String updatePackageNum;//	更新包序号
    public TerminalSendUpdateRequestPackageRequest(Header header) {
        super(header);
    }
	public int getUpdateType() {
		return updateType;
	}
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public String getUpdateFileCRC() {
		return updateFileCRC;
	}
	public void setUpdateFileCRC(String updateFileCRC) {
		this.updateFileCRC = updateFileCRC;
	}
	public String getUpdatePackageNum() {
		return updatePackageNum;
	}
	public void setUpdatePackageNum(String updatePackageNum) {
		this.updatePackageNum = updatePackageNum;
	}
    
    
}
