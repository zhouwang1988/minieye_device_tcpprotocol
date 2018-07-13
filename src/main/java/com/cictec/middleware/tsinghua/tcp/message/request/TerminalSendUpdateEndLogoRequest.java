package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalSendUpdateEndLogoRequest extends TerminalMessage  {
	private int updateType;//	更新类型
	private String updateFileCRC;//	更新文件 CRC
	private int updateResult;//	更新结果
    public TerminalSendUpdateEndLogoRequest(Header header) {
        super(header);
    }
	public String getUpdateFileCRC() {
		return updateFileCRC;
	}
	public void setUpdateFileCRC(String updateFileCRC) {
		this.updateFileCRC = updateFileCRC;
	}
	public int getUpdateResult() {
		return updateResult;
	}
	public void setUpdateResult(int updateResult) {
		this.updateResult = updateResult;
	}
	public int getUpdateType() {
		return updateType;
	}
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
    
}
