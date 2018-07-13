package com.cictec.middleware.tsinghua.model.vo;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class GpsPoint {

    private String uuid;
    private String devCode;
    private double lat;
    private double lng;
    private Date sendTime;
    private Date createTimel;
    private String sendTimeStr;
    private String createTimeStr;
    
    private Alarm alarm;//报警标识位
    
    private AlarmStatus alarmStatus;//报警状态
    
    private AppendMessageAlarmMarker appendMessageAlarmMarker;//附加信息 0xA0
    
    private AppendMessageState appendMessageA1;//附加信息 0xA1
    
    public GpsPoint() {
    }

    public GpsPoint(String uuid, String devCode, double lat, double lng, Date sendTime, Date createTimel, String sendTimeStr, String createTimeStr) {
        this.uuid = uuid;
        this.devCode = devCode;
        this.lat = lat;
        this.lng = lng;
        this.sendTime = sendTime;
        this.createTimel = createTimel;
        this.sendTimeStr = sendTimeStr;
        this.createTimeStr = createTimeStr;
    }

    public GpsPoint(String uuid, double lat, double lng, Date sendTime, Date createTimel, String sendTimeStr, String createTimeStr) {
        this.uuid = uuid;
        this.lat = lat;
        this.lng = lng;
        this.sendTime = sendTime;
        this.createTimel = createTimel;
        this.sendTimeStr = sendTimeStr;
        this.createTimeStr = createTimeStr;
    }
    
	public AppendMessageState getAppendMessageA1() {
		return appendMessageA1;
	}

	public void setAppendMessageA1(AppendMessageState appendMessageA1) {
		this.appendMessageA1 = appendMessageA1;
	}

	public AppendMessageAlarmMarker getAppendMessageAlarmMarker() {
		return appendMessageAlarmMarker;
	}

	public void setAppendMessageAlarmMarker(AppendMessageAlarmMarker appendMessageAlarmMarker) {
		this.appendMessageAlarmMarker = appendMessageAlarmMarker;
	}

	public AlarmStatus getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(AlarmStatus alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTimel() {
        return createTimel;
    }

    public void setCreateTimel(Date createTimel) {
        this.createTimel = createTimel;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }
}
