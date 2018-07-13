package com.cictec.middleware.tsinghua.tcp.code;

public interface CodecFounder {

	/**
	 * 获取终端消息的解码器，根据消息ID。
	 * @param messageId
	 * @return
	 */
	public abstract MessageDecoder getDecoder(int messageId);

	/**
	 * 获取终端消息的附加消息的解码器，根据消息ID和附加消息ID。
	 * @param messageId
	 * @param addtionalId
	 * @return
	 */
	public abstract MessageAdditionalDecoder getDecoder(int messageId, int addtionalId);
	
	/**
	 * 获取终端消息的编码器，根据消息ID。
	 * @param messageId
	 * @return
	 */
	public abstract MessageEncoder getEecoder(int messageId);

	
	public abstract MessageAdditionalEncoder getEncoder(int messageId, int addtionalId);
}