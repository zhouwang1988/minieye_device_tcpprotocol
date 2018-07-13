package com.cictec.middleware.minieye.tcp.decode;

import com.cictec.middleware.minieye.model.vo.Alarm;
import com.cictec.middleware.minieye.model.vo.AlarmStatus;
import com.cictec.middleware.minieye.model.vo.AppendMessageAlarmMarker;
import com.cictec.middleware.minieye.model.vo.AppendMessageState;
import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalLocationRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;

import org.apache.mina.core.session.IoSession;
import java.nio.ByteBuffer;

/**
 * 0x0200 位置信息TerminalLocationDecoder类
 * @file  TerminalLocationDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0200)
public class Terminal0x0200LocationDecoder extends AbstractMessageDecoder {
	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in1, IoSession session) throws CanntFoundDecoder {

		TerminalLocationRequest tcr = new TerminalLocationRequest(header);
		
		StringBuilder sb = new StringBuilder();
		byte[] bytes = new byte[28];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i]=in1.get();
			sb.append(BinaryUtils.byte2HexStr(bytes[i]));
		}
		tcr.setHexLocationBuf(sb.toString());
		ByteBuffer in = ByteBuffer.wrap(bytes);
		
		tcr.setWarnMark(new Alarm(BinaryUtils.dwordToTypeData(in)));
		tcr.setStatus(new AlarmStatus(BinaryUtils.dwordToTypeData(in)));
		tcr.setLat(BinaryUtils.dwordToLatLng(in));
		tcr.setLng(BinaryUtils.dwordToLatLng(in));
		tcr.setElevation(BinaryUtils.wordToInt(in));
		tcr.setSpeed(BinaryUtils.wordToInt(in));
		tcr.setDirection(BinaryUtils.wordToInt(in));
		tcr.setTime(BinaryUtils.bcd6ToDate(in,6));
		while(in.hasRemaining()){
			int messageID = BinaryUtils.unsignedByteToInt(in);
			if(messageID==0xA0){
				setAppendMSGA0(messageID,in, tcr);
			}else if(messageID==0xA1){
				setAppendMSGA1(messageID,in, tcr);
			}else{
				break;
			}
		}
		return tcr;
	}

	private void setAppendMSGA0(int messageID,ByteBuffer in, TerminalLocationRequest tcr) {
		tcr.setAppendMessageAlarmMarkerID(messageID);
		tcr.setAppendMessageAlarmMarkerLength(BinaryUtils.unsignedByteToInt(in));
		tcr.setAppendMessageAlarmMarker(new AppendMessageAlarmMarker(BinaryUtils.dwordToTypeData(in)));
	}
	
	private void setAppendMSGA1(int messageID,ByteBuffer in, TerminalLocationRequest tcr) {
		tcr.setAppendMessageStateID(messageID);
		tcr.setAppendMessageStateLength(BinaryUtils.unsignedByteToInt(in));
		tcr.setAppendMessageState(new AppendMessageState(BinaryUtils.dwordToTypeData(in)));
	}
	

}
