package com.cictec.middleware.minieye.model.vo;

public class AppendMessageAlarmMarker {

	private int evidence;//0 0：无证据 1：有证据
	private int evidencePhoteOrVideo;//1 0：图片证据 1：视频证据
						//2 保留
						//3 保留
						//4 保留
						//5 保留
						//6 保留
						//7 保留
	private int closeEyes;//8 1：闭眼 收到应答后清零
	private int yawn;//9 1：打哈欠 收到应答后清零
	private int looKAround;//10 1：左顾右看 收到应答后清零
	private int call;//11 1：打手机 收到应答后清零
	private int smoking;//12 1：抽烟 收到应答后清零
	private int undergo;//13 1：离岗 收到应答后清零
	private int shield;//14 1：遮挡 收到应答后清零
	private int timeoutDriving;//15 1：超时驾驶 收到应答后清零
	private int laneDeparture;//16 1：车道偏离 收到应答后清零
	private int collide;//17 1：前向碰撞 收到应答后清零
	private int speedUp;//18 1：急加速 收到应答后清零
	private int speedCut;//19 1：急减速 收到应答后清零
	private int sharpBend;//20 1：急转弯 收到应答后清零
	private int speedAlarmStatus;//21 1：超速报警 收到应答后清零
	private int turnoverAlarm;//22 1：侧翻报警 收到应答后清零
	private int drunkDriving;//23 1：酒驾 收到应答后清零
	private int superheavy;//24 1：超重 收到应答后清零
	private int overload;//25 1：超载 收到应答后清零
	private int pressureHigh;//26 1：胎压过高 收到应答后清零
	private int pressureLow;//27 1：胎压过低 收到应答后清零
	private int openDoorAlarm;//28 1：非法开门（速度不为 0，出现 收到应答后清零//开门）
	private int crashMan;//29 1：行人碰撞预警 收到应答后清零
	private int crossroadsSpeed;//30 1：路口超速 收到应答后清零
					//31-47 保留
	
	
	public AppendMessageAlarmMarker(int[] ints) {
		super();
		this.evidence = ints[0];
		this.evidencePhoteOrVideo = ints[1];
		this.closeEyes = ints[8];
		this.yawn = ints[9];
		this.looKAround = ints[10];
		this.call = ints[11];
		this.smoking = ints[12];
		this.undergo = ints[13];
		this.shield = ints[14];
		this.timeoutDriving = ints[15];
		this.laneDeparture = ints[16];
		this.collide = ints[17];
		this.speedUp = ints[18];
		this.speedCut = ints[19];
		this.sharpBend = ints[20];
		this.speedAlarmStatus = ints[21];
		this.turnoverAlarm = ints[22];
		this.drunkDriving = ints[23];
		this.superheavy = ints[24];
		this.overload = ints[25];
		this.pressureHigh = ints[26];
		this.pressureLow = ints[27];
		this.openDoorAlarm = ints[28];
		this.crashMan = ints[29];
		this.crossroadsSpeed = ints[30];
	}
	
	public AppendMessageAlarmMarker() {
		
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
	public int getCloseEyes() {
		return closeEyes;
	}
	public void setCloseEyes(int closeEyes) {
		this.closeEyes = closeEyes;
	}
	public int getYawn() {
		return yawn;
	}
	public void setYawn(int yawn) {
		this.yawn = yawn;
	}
	public int getLooKAround() {
		return looKAround;
	}
	public void setLooKAround(int looKAround) {
		this.looKAround = looKAround;
	}
	public int getCall() {
		return call;
	}
	public void setCall(int call) {
		this.call = call;
	}
	public int getSmoking() {
		return smoking;
	}
	public void setSmoking(int smoking) {
		this.smoking = smoking;
	}
	public int getUndergo() {
		return undergo;
	}
	public void setUndergo(int undergo) {
		this.undergo = undergo;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getTimeoutDriving() {
		return timeoutDriving;
	}
	public void setTimeoutDriving(int timeoutDriving) {
		this.timeoutDriving = timeoutDriving;
	}
	public int getLaneDeparture() {
		return laneDeparture;
	}
	public void setLaneDeparture(int laneDeparture) {
		this.laneDeparture = laneDeparture;
	}
	public int getCollide() {
		return collide;
	}
	public void setCollide(int collide) {
		this.collide = collide;
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
	public int getSpeedAlarmStatus() {
		return speedAlarmStatus;
	}
	public void setSpeedAlarmStatus(int speedAlarmStatus) {
		this.speedAlarmStatus = speedAlarmStatus;
	}
	public int getTurnoverAlarm() {
		return turnoverAlarm;
	}
	public void setTurnoverAlarm(int turnoverAlarm) {
		this.turnoverAlarm = turnoverAlarm;
	}
	public int getDrunkDriving() {
		return drunkDriving;
	}
	public void setDrunkDriving(int drunkDriving) {
		this.drunkDriving = drunkDriving;
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
	public int getOpenDoorAlarm() {
		return openDoorAlarm;
	}
	public void setOpenDoorAlarm(int openDoorAlarm) {
		this.openDoorAlarm = openDoorAlarm;
	}
	public int getCrashMan() {
		return crashMan;
	}
	public void setCrashMan(int crashMan) {
		this.crashMan = crashMan;
	}
	public int getCrossroadsSpeed() {
		return crossroadsSpeed;
	}
	public void setCrossroadsSpeed(int crossroadsSpeed) {
		this.crossroadsSpeed = crossroadsSpeed;
	}

	
}
