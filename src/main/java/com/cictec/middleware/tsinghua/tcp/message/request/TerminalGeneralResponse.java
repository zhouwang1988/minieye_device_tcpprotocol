package com.cictec.middleware.tsinghua.tcp.message.request;


import com.cictec.middleware.tsinghua.tcp.message.AbstractResponse;

import java.util.Date;

/**
 * 0x06 指令应答
 * @author Zhibin
 *
 */
public class TerminalGeneralResponse extends AbstractResponse {
	
	private Date responseTime;
	private int responseType;
	private int reserved;
	private String originalMessageUuid;
	
	public TerminalGeneralResponse(Header header) {
		super(header);
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public int getResponseType() {
		return responseType;
	}

	public void setResponseType(int responseType) {
		this.responseType = responseType;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public String getOriginalMessageUuid() {
		return originalMessageUuid;
	}

	public void setOriginalMessageUuid(String originalMessageUuid) {
		this.originalMessageUuid = originalMessageUuid;
	}

	@Override
	public String toString() {
		return "TerminalGeneralResponse [responseTime=" + responseTime
				+ ", responseType=" + responseType + ", reserved=" + reserved
				+ ", getResponseSequence()=" + getResponseSequence()
				+ ", getResponseMessageId()=" + getResponseMessageId() + "]";
	}


	
}
