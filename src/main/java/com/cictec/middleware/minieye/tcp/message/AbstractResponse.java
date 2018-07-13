package com.cictec.middleware.minieye.tcp.message;


public abstract class AbstractResponse extends TerminalMessage {

	/**
	 * 应答流水号
	 */
	protected int responseSequence;
	/**
	 * 应答消息ID
	 */
	private int responseMessageId;
	
	public AbstractResponse(Header header) {
		super(header);
	}

	public int getResponseSequence() {
		return responseSequence;
	}

	public void setResponseSequence(int responseSequence) {
		this.responseSequence = responseSequence;
	}

	public int getResponseMessageId() {
		return responseMessageId;
	}

	public void setResponseMessageId(int responseMessageId) {
		this.responseMessageId = responseMessageId;
	}

}