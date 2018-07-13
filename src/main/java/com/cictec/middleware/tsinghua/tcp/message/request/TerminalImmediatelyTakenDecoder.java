package com.cictec.middleware.tsinghua.tcp.message.request;

import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class TerminalImmediatelyTakenDecoder extends TerminalMessage {
	
	private int serialNum;//	应答流水号
	private int result; //结果
	private int idCounts;//	多媒体ID个数
	private int[] idLists; //多媒体ID列表
	
	public TerminalImmediatelyTakenDecoder(Header header) {
        super(header);
    }

	public int getSerialNum() {
		return serialNum;
	}


	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}


	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getIdCounts() {
		return idCounts;
	}

	public void setIdCounts(int idCounts) {
		this.idCounts = idCounts;
	}

	public int[] getIdLists() {
		return idLists;
	}

	public void setIdLists(int[] idLists) {
		this.idLists = idLists;
	}

	
}
