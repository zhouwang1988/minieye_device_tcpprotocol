package com.cictec.middleware.minieye.tcp.message.request;

import java.util.Date;

import com.cictec.middleware.minieye.model.vo.Alarm;
import com.cictec.middleware.minieye.model.vo.AlarmStatus;
import com.cictec.middleware.minieye.model.vo.AppendMessageAlarmMarker;
import com.cictec.middleware.minieye.model.vo.AppendMessageState;
import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

public class TerminalLocationRequest extends TerminalMessage  {
		private Alarm warnMark;//	位置信息汇报
		private AlarmStatus status;//	状态
		private Double lat;//	纬度 
		private Double lng;//	经度 
		private int elevation;//	高程
		private int speed;//	速度
		private int direction;//	方向
		private Date time;//	时间
		
		private int appendMessageAlarmMarkerID;//附加信息A0报警标识位
		private int appendMessageAlarmMarkerLength;//附加信息A0报警标识位长度
		private AppendMessageAlarmMarker appendMessageAlarmMarker;//附加信息A0报警标识位
		
		private int appendMessageStateID;//附加信息A1报警标识位
		private int appendMessageStateLength;//附加信息A1报警标识位长度
		private AppendMessageState appendMessageState;//附加信息A1报警标识位
		
		private String hexLocationBuf;//定位消息
		
		
		public String getHexLocationBuf() {
			return hexLocationBuf;
		}

		public void setHexLocationBuf(String hexLocationBuf) {
			this.hexLocationBuf = hexLocationBuf;
		}

		public TerminalLocationRequest() {

		}
		
		public Alarm getWarnMark() {
			return warnMark;
		}

		public void setWarnMark(Alarm warnMark) {
			this.warnMark = warnMark;
		}

		public AlarmStatus getStatus() {
			return status;
		}

		public void setStatus(AlarmStatus status) {
			this.status = status;
		}

		public AppendMessageAlarmMarker getAppendMessageAlarmMarker() {
			return appendMessageAlarmMarker;
		}

		public void setAppendMessageAlarmMarker(AppendMessageAlarmMarker appendMessageAlarmMarker) {
			this.appendMessageAlarmMarker = appendMessageAlarmMarker;
		}

		public int getAppendMessageAlarmMarkerID() {
			return appendMessageAlarmMarkerID;
		}

		public void setAppendMessageAlarmMarkerID(int appendMessageAlarmMarkerID) {
			this.appendMessageAlarmMarkerID = appendMessageAlarmMarkerID;
		}

		public int getAppendMessageAlarmMarkerLength() {
			return appendMessageAlarmMarkerLength;
		}

		public void setAppendMessageAlarmMarkerLength(int appendMessageAlarmMarkerLength) {
			this.appendMessageAlarmMarkerLength = appendMessageAlarmMarkerLength;
		}

		public int getAppendMessageStateID() {
			return appendMessageStateID;
		}

		public void setAppendMessageStateID(int appendMessageStateID) {
			this.appendMessageStateID = appendMessageStateID;
		}

		public int getAppendMessageStateLength() {
			return appendMessageStateLength;
		}

		public void setAppendMessageStateLength(int appendMessageStateLength) {
			this.appendMessageStateLength = appendMessageStateLength;
		}

		public AppendMessageState getAppendMessageState() {
			return appendMessageState;
		}

		public void setAppendMessageState(AppendMessageState appendMessageState) {
			this.appendMessageState = appendMessageState;
		}

		public TerminalLocationRequest(Header header) {
	        super(header);
	    }

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return lng;
		}

		public void setLng(Double lng) {
			this.lng = lng;
		}

		public int getElevation() {
			return elevation;
		}

		public void setElevation(int elevation) {
			this.elevation = elevation;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int direction) {
			this.direction = direction;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return "TerminalLocationRequest [warnMark=" + warnMark + ", status=" + status + ", lat=" + lat + ", lng="
					+ lng + ", elevation=" + elevation + ", speed=" + speed + ", direction=" + direction + ", time="
					+ time + "]";
		}
		
	    
}
