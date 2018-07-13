package com.cictec.middleware.minieye.tcp.code;


import com.cictec.middleware.minieye.config.Constants;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.text.MessageFormat;


@Component("terminalProtocolEncoder")
public class TerminalProtocolEncoder extends ProtocolEncoderAdapter {

	private static Logger logger = LoggerFactory.getLogger(TerminalProtocolEncoder.class);

	private int MAX_BODY_LENGTH = 242;

	@Autowired
	private CodecFounder  codecFounder ;

	@Override
	public void encode(IoSession session, Object message,ProtocolEncoderOutput out) throws Exception {


		TerminalMessage tm = (TerminalMessage)message;

		TerminalMessage.Header header = tm.getHeader();

		byte[] bodyBytes;

		try{
			bodyBytes = encodeBody(tm);
		}catch(CanntFoundEncoder cfe){
			logger.error("找不到消息编码器  消息ID{}",header.getMessageId());
			return;
		}

		header.setBodyLength(bodyBytes.length);

		byte[] headerBytes = encodeHeader(header);
		byte[] allBytes = translateFrameData(headerBytes,bodyBytes,true);

		logger.info("发送消息 session {} 序号{} 消息ID {} ： {} ",session.getId(),header.getMessageSequence(), Integer.toHexString(header.getMessageId()) ,BinaryUtils.byte2HexStr(allBytes));

		IoBuffer ioBuffer = IoBuffer.allocate(allBytes.length);
		ioBuffer.setAutoExpand(false);
		ioBuffer.setAutoShrink(true);
		ioBuffer.put(allBytes, 0, allBytes.length);
		ioBuffer.flip();
		logger.debug("编码器 开始向 Session {} 发送数据",session.getId());

		out.write(ioBuffer);
		out.flush();


	}

	/**
	 * 生成校验码
	 * @return
	 */
	private int createValidity(byte[] head,byte[] body){


		int xorResult = 0;

		for (byte in : head) {

			xorResult = xorResult^in;

		}
		for (byte in : body) {

			xorResult = xorResult^in;

		}
		return xorResult;

	}


	/**
	 * 组合完整的byte型的发送数据，包括附加校验码和字符转义。
	 * @param header
	 * @param body
	 * @return
	 */
	public byte[] translateFrameData(byte[] header,byte[] body ,boolean rt){
		//计算校验码
		int valiCode = createValidity(header, body);

		ByteBuffer sendBuffer = ByteBuffer.allocate(512);
		//写入消息开始标志
		sendBuffer.put(BinaryUtils.intToUnsignedByte(Constants.MESSAGE_BEGIN_FLAG));

		//转义头
		for (int i = 0; i < header.length; i++) {

			byteEscape(sendBuffer, BinaryUtils.intToUnsignedByte(header[i]));

		}

		//转义消息体
		for (int i = 0; i < body.length; i++) {

			byteEscape(sendBuffer, BinaryUtils.intToUnsignedByte(body[i]));

		}

		//转义校验码
		byteEscape(sendBuffer, BinaryUtils.intToUnsignedByte(valiCode));


		//写入消息尾
		sendBuffer.put(BinaryUtils.intToUnsignedByte(Constants.MESSAGE_END_FLAG));

		//转换成byte数组
		byte[] bytes = new byte[sendBuffer.position()];
		sendBuffer.flip();
		sendBuffer.get(bytes);
		return bytes;
	}



	private void byteEscape(ByteBuffer buffer, byte b) {
		int unsingnedInt = BinaryUtils.unsignedByteToInt(b);
		if(unsingnedInt == Constants.MESSAGE_BEGIN_FLAG){
			buffer.put(BinaryUtils.intToUnsignedByte(Constants.MESSAGE_BEGIN_ESCAPE)).put(BinaryUtils.intToUnsignedByte(Constants.MESSAGE_BEGIN_ESCAPE_BEGIN));
		}else if(unsingnedInt == Constants.MESSAGE_BEGIN_ESCAPE){
			buffer.put(b).put(BinaryUtils.intToUnsignedByte(Constants.MESSAGE_BEGIN_ESCAPE_ESCAPE));
		}else{
			buffer.put(b);
		}
	}

	public byte[] encodeBody(TerminalMessage tm) throws CanntFoundEncoder{

		MessageEncoder me = this.codecFounder.getEecoder(tm.getHeader().getMessageId());

		if(me == null){
			logger.error("找不到消息 {} 对应的编码器 ",tm.getHeader().getMessageId());
			throw new CanntFoundEncoder(MessageFormat.format("找不到消息 {0} 的附加消息 {1} 对应的编码器 ", tm.getHeader().getMessageId()));
		}

		return me.encode(tm);
	}

	public byte[] encodeHeader(TerminalMessage.Header header){
		//分配消息头最大的占用空间。
		ByteBuffer headerBuffer = ByteBuffer.allocate(12);

		//协议版本号
		//String[] vers = header.getVersion().split("\\.");
		//	int verHigh = Integer.parseInt(vers[0]);
		//	int verLow = Integer.parseInt(vers[1]);
		//	int verNum = (verHigh<<4) | verLow;
		//	headerBuffer.put(BinaryUtils.intToUnsignedByte(verNum));

		//命令字
		headerBuffer.put(BinaryUtils.intToWord(header.getMessageId()));
		//消息体长度
		headerBuffer.put(BinaryUtils.intToWord(header.getBodyLength()));
		//终端编号
		//808协议消息头终端手机不足12位补0
		String terminalId = header.getTerminalId();
		StringBuilder sb  = new StringBuilder(terminalId);
		if(sb.length()<12){
			for(int i=0;i<12-terminalId.length();i++){
				sb.insert(0, "0");
			}
		}
		if(sb.length()>12){
			sb.substring(0, 12);
		}
		byte[] terminalIdBytes = BinaryUtils.hexStr2Bytes(sb.toString()+"");
		headerBuffer.put(terminalIdBytes);
		//消息流水号
		headerBuffer.put(BinaryUtils.intToWord(header.getMessageSequence()));
		//发送时间
//		if(header.getSendTime()==null)
//			header.setSendTime(new Date());
//		headerBuffer.put(BinaryUtils.dateToDwordRM(header.getSendTime()));

		//线路编号
		//headerBuffer.put(BinaryUtils.intToWord(header.getLineId()));

		byte[] bytes = new byte[headerBuffer.position()];

		headerBuffer.limit(headerBuffer.position());
		headerBuffer.flip();

		headerBuffer.get(bytes);

		return bytes;
	}
}
