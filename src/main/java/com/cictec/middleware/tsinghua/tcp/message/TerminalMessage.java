package com.cictec.middleware.tsinghua.tcp.message;

import com.cictec.middleware.tsinghua.utils.UUIDGenerator;

import java.util.Date;


public class TerminalMessage {
	private Long sessionId;
	private Header header;
	private final String uuid;

	public TerminalMessage(){
		this.uuid = UUIDGenerator.genUuidStr();
	}

	public TerminalMessage(Header header){
		this.header = header;
		this.uuid = UUIDGenerator.genUuidStr();
	}
	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getTerminalId() {
		return String.valueOf(header.getTerminalId());
	}

	public String getUuid() {
		return uuid;
	}

	static public class Header{
		/**
		 * 命令字
		 */
		private int messageId;
		/**
		 * 消息长度
		 */
		private int bodyLength;
		/**
		 * 协议版本
		 */
		private String version="2.0";
		/**
		 * 流水号
		 */
		private int messageSequence;
		/**
		 * 发送时间
		 */
		private Date sendTime;
		/**
		 * 终端编号
		 */
		private String terminalId;
		/**
		 * 线路编号
		 */
		private int lineId;
		/**
		 * 数据标识 
		 */
		private int dataFlag;
		/**
		 * 校验和
		 */
		private int validateCode;
		/**
		 * 是否为实时数据
		 */
		private boolean realTimeData;
		/**
		 * 消息是否分包
		 */
		private boolean subcontract;
		/**
		 * 消息包总数
		 */
		private int packageCounts;
		/**
		 * 消息包序号
		 */
		private int packageNum;

		private int length;
		


		public Header(){
		}

		public Header(int messageId){
			this.messageId = messageId;
		}

		public int getMessageId() {
			return messageId;
		}
		public void setMessageId(int messageId) {
			this.messageId = messageId;
		}
		public int getBodyLength() {
			return bodyLength;
		}
		public void setBodyLength(int bodyLength) {
			this.bodyLength = bodyLength;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public int getMessageSequence() {
			return messageSequence;
		}
		public void setMessageSequence(int messageSequence) {
			this.messageSequence = messageSequence;
		}
		public Date getSendTime() {
			return sendTime;
		}
		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}
		public String getTerminalId() {
			return terminalId;
		}
		public void setTerminalId(String terminalId) {
			this.terminalId = terminalId;
		}
		public int getDataFlag() {
			return dataFlag;
		}
		public void setDataFlag(int dataFlag) {
			this.dataFlag = dataFlag;
		}
		public int getValidateCode() {
			return validateCode;
		}
		public void setValidateCode(int validateCode) {
			this.validateCode = validateCode;
		}
		public boolean isRealTimeData() {
			return realTimeData;
		}
		public void setRealTimeData(boolean realTimeData) {
			this.realTimeData = realTimeData;
		}
		public int getLineId() {
			return lineId;
		}
		public void setLineId(int lineId) {
			this.lineId = lineId;
		}
		
		public boolean isSubcontract() {
			return subcontract;
		}

		public void setSubcontract(boolean subcontract) {
			this.subcontract = subcontract;
		}

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
		

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		@Override
		public String toString() {
			return "Header [messageId=" + messageId + ", bodyLength="
					+ bodyLength + ", version=" + version
					+ ", messageSequence=" + messageSequence + ", sendTime="
					+ sendTime + ", terminalId=" + terminalId + ", lineId="
					+ lineId + ", dataFlag=" + dataFlag + ", validateCode="
					+ validateCode + ", realTimeData=" + realTimeData + "]";
		}

	}
}
