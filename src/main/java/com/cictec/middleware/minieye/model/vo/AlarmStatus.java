package com.cictec.middleware.minieye.model.vo;

public class AlarmStatus {

	private int ACCSwitch;//0 0：ACC 关；1： ACC 开
	private int GPSSwitch;//1 0：未定位；1：定位
	private int latitude;//2 0：北纬；1：南纬
	private int longitude;//3 0：东经；1：西经
	private int superheavy;//6 超重
	private int overload;//7 超载
	
	private int pressureHigh;//22 胎压过高
	private int pressureLow;//23 胎压过低
	private int evidence;//24 0：无证据；1：有证据
	private int evidencePhoteOrVideo;//25 0：图片证据；1：视频证据
	private int shield;//26 遮挡
	private int speedUp;//27 急加速
	private int speedCut;//28 急减速
	private int sharpBend;//29 急转弯
	private int drunkDriving;//30 酒驾
							//31 保留 
	
	public int getACCSwitch() {
		return ACCSwitch;
	}
	
	public AlarmStatus(int[] ints) {
		super();
		this.ACCSwitch = ints[0];
		this.GPSSwitch = ints[1];
		this.latitude = ints[2];
		this.longitude = ints[3];
		this.superheavy = ints[6];
		this.overload = ints[7];
		this.pressureHigh = ints[22];
		this.pressureLow = ints[23];
		this.evidence = ints[24];
		this.evidencePhoteOrVideo = ints[25];
		this.shield = ints[26];
		this.speedUp = ints[27];
		this.speedCut = ints[28];
		this.sharpBend = ints[29];
		this.drunkDriving = ints[30];
	}
	
	

	public AlarmStatus() {
		
	}

	public void setACCSwitch(int aCCSwitch) {
		ACCSwitch = aCCSwitch;
	}
	public int getGPSSwitch() {
		return GPSSwitch;
	}
	public void setGPSSwitch(int gPSSwitch) {
		GPSSwitch = gPSSwitch;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getSuperheavy() {
		return superheavy;
	}
	public void setSuperheavy(int superheavy) {
		this.superheavy = superheavy;
	}
	public int getOverload() {
		return overload;
	}
	public void setOverload(int overload) {
		this.overload = overload;
	}
	public int getPressureHigh() {
		return pressureHigh;
	}
	public void setPressureHigh(int pressureHigh) {
		this.pressureHigh = pressureHigh;
	}
	public int getPressureLow() {
		return pressureLow;
	}
	public void setPressureLow(int pressureLow) {
		this.pressureLow = pressureLow;
	}
	public int getEvidence() {
		return evidence;
	}
	public void setEvidence(int evidence) {
		this.evidence = evidence;
	}
	public int getEvidencePhoteOrVideo() {
		return evidencePhoteOrVideo;
	}
	public void setEvidencePhoteOrVideo(int evidencePhoteOrVideo) {
		this.evidencePhoteOrVideo = evidencePhoteOrVideo;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getSpeedUp() {
		return speedUp;
	}
	public void setSpeedUp(int speedUp) {
		this.speedUp = speedUp;
	}
	public int getSpeedCut() {
		return speedCut;
	}
	public void setSpeedCut(int speedCut) {
		this.speedCut = speedCut;
	}
	public int getSharpBend() {
		return sharpBend;
	}
	public void setSharpBend(int sharpBend) {
		this.sharpBend = sharpBend;
	}
	public int getDrunkDriving() {
		return drunkDriving;
	}
	public void setDrunkDriving(int drunkDriving) {
		this.drunkDriving = drunkDriving;
	}
	
	

}
