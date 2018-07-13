package com.cictec.middleware.minieye.tcp.decode;

import com.alibaba.fastjson.JSON;
import com.cictec.middleware.minieye.model.vo.Alarm;
import com.cictec.middleware.minieye.model.vo.AlarmStatus;
import com.cictec.middleware.minieye.model.vo.AppendMessageAlarmMarker;
import com.cictec.middleware.minieye.model.vo.AppendMessageState;
import com.cictec.middleware.minieye.model.vo.MediaPacket;
import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.AbstractMessageDecoder;
import com.cictec.middleware.minieye.tcp.code.CanntFoundDecoder;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
import com.cictec.middleware.minieye.tcp.message.request.TerminalDataUploadRequest;
import com.cictec.middleware.minieye.tcp.message.request.TerminalLocationRequest;
import com.cictec.middleware.minieye.utils.BinaryUtils;
import com.cictec.middleware.minieye.utils.FileUtils;
import com.cictec.middleware.minieye.utils.SubcontractUtil;

import net.sf.json.JSONObject;

import org.apache.camel.processor.Traceable;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 0x0801 多媒体数据上传TerminalDataUploadDecoder类
 * @file  TerminalDataUploadDecoder.java
 * @author mjj
 * @version 1.0.0
 * Copyright(C), 2018
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@TerminalMessageDecoder(0x0801)
public class Terminal0x0801DataUploadDecoder extends AbstractMessageDecoder {

	//存储合并包后的对象
	public Map<String, TerminalDataUploadRequest> objs = new ConcurrentHashMap<>();
	
	//存储临时文件
	public Map<String,File> mePackets = new ConcurrentHashMap<>();
	
	@Value("${subpackage.data.direct}")
    private String direct;
	
	@Override
	public TerminalMessage decode(TerminalMessage.Header header, ByteBuffer in, IoSession session) throws CanntFoundDecoder {


		TerminalDataUploadRequest tcr = new TerminalDataUploadRequest(header);
		
    	//判断是否是第一个包
    	if(header.getPackageNum()==1){
    		tcr.setMultimediaID(BinaryUtils.dwordToInt(in));
    		tcr.setMultimediaType(BinaryUtils.unsignedByteToInt(in)+"");
    		tcr.setMultimediaCode(BinaryUtils.unsignedByteToInt(in)+"");
    		tcr.setMultimediaEventCode(BinaryUtils.unsignedByteToInt(in)+"");
    		tcr.setChannelID(BinaryUtils.unsignedByteToInt(in)+"");
    		tcr.setTerminalLocationRequest(decodeTerminalLocationRequest(header, in));
    		byte [] idList  =new byte[in.limit()-in.position()];
    		for(int i=0;i<idList.length;i++){
    			idList[i]=in.get();
    		}
    		tcr.setMultimediaPacket(idList);
    	}else {
    		byte [] idList  =new byte[header.getBodyLength()];
    		for(int i=0;i<idList.length;i++){
    			idList[i]=in.get();
    		}
    		tcr.setMultimediaPacket(idList);
		}

		
		return tcr;

	}
	
	private TerminalLocationRequest decodeTerminalLocationRequest(TerminalMessage.Header header, ByteBuffer in1) {
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
		return tcr;
	}
	
	

}
