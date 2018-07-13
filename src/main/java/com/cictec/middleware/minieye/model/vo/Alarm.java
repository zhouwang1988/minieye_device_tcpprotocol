package com.cictec.middleware.minieye.model.vo;

public class Alarm {

	private int exigenceAlarmStatus;//0 报警状态 1：紧急报警
    private int speedAlarmStatus;//1 超速报警 1：超速报警
    private int timeoutAlarmStatus;//2 超时报警 1：超时报警
    private int dangerousAlarmStatus;//3 1：危险预警
    private int GNSSModuleAlarm;//4 GNSS 模块发生故障 
    private int GNSSAntennaBreakAlarm;//5 1：GNSS 天线未接或被剪断 
    private int GNSSAntennaShortAlarm;//6 1：GNSS 天线短路 
    private int voltageAlarm;//7 1：终端主电源欠压 
    private int powerDownAlarm;//8 1：终端主电源掉电 
    private int LCDAlarm;//9 1：终端 LCD 或显示器故障
    private int TTSAlarm;//10 1：TTS 模块故障
    private int cameraAlarm;//11 1：摄像头故障 
    private int ICAlarm;//12 1：道路运输证 IC
    private int speedEarlyAlarm;//13 1：超速预警 
    private int fatigueDrivingAlarm;//14 1：疲劳驾驶预警 

    private int closeEyeAlarm;//闭眼 0 0 1
    private int yawnAlarm;//打哈欠 0 1 0
    private int distractionAlarm;//注意力分散 0 1 1
    private int callAlarm;//打手机 1 0 0
    private int smokingAlarm;//抽烟 1 0 1
    private int forwardCrashAlarm;//前向碰撞 1 1 0
    private int departureDrivingAlarm;//车道偏离 1 1 1
    
    
    
    private int drivingTimeoutAlarm;//18 1：当天累计驾驶超时
    
    private int parkingTimeoutAlarm;//19 1：超时停车
    private int inAndoutAreaAlarm;//20 1：进出区域
    private int inAndoutLineAlarm;//21 1：进出路线
    private int drivingTimeShortAlarm;//22 1：路段行驶时间不足/过长
    private int lineLeviateAlarm;//23 1：路线偏离报警 
    private int VSSAlarm;//24 1：车辆 VSS 故障 
    private int oilAlarm;//25 1：车辆油量异常
    private int stolenBusAlarm;//26 1：车辆被盗(通过车辆防盗器)
    private int ignitionAlarm;//27 1：车辆非法点火
    private int displacementAlarm;//28 1：车辆非法位移 
    private int crashAlarm;//29 1：碰撞预警 
    private int turnoverAlarm;//30 1：侧翻预警
    private int openDoorAlarm;//31 1：非法开门报警
    
	public Alarm() {

	}

	public Alarm(int[] ints) {
		super();
		this.exigenceAlarmStatus = ints[0];
		this.speedAlarmStatus = ints[1];
		this.timeoutAlarmStatus =ints[2];
		this.dangerousAlarmStatus = ints[3];
		this.GNSSModuleAlarm = ints[4];
		this.GNSSAntennaBreakAlarm = ints[5];
		this.GNSSAntennaShortAlarm = ints[6];
		this.voltageAlarm = ints[7];
		this.powerDownAlarm = ints[8];
		this.LCDAlarm = ints[9];
		this.TTSAlarm = ints[10];
		this.cameraAlarm = ints[11];
		this.ICAlarm = ints[12];
		this.speedEarlyAlarm = ints[13];
		this.fatigueDrivingAlarm = ints[14];
		
		transform(ints);
		
		this.drivingTimeoutAlarm = ints[18];
		this.parkingTimeoutAlarm = ints[19];
		this.inAndoutAreaAlarm = ints[20];
		this.inAndoutLineAlarm = ints[21];
		this.drivingTimeShortAlarm = ints[22];
		this.lineLeviateAlarm = ints[23];
		this.VSSAlarm = ints[24];
		this.oilAlarm = ints[25];
		this.stolenBusAlarm = ints[26];
		this.ignitionAlarm = ints[27];
		this.displacementAlarm = ints[28];
		this.crashAlarm = ints[29];
		this.turnoverAlarm = ints[30];
		this.openDoorAlarm = ints[31];
	}

	private void transform(int[] ints) {
		String str =String.valueOf(ints[17])+String.valueOf(ints[16])+String.valueOf(ints[15]);
		
		if("001".equals(str)){
			this.closeEyeAlarm = 1;
		}else{
			this.closeEyeAlarm = 0;
		}
		
		if("010".equals(str)){
			this.yawnAlarm = 1;
		}else{
			this.yawnAlarm = 0;
		}
		
		if("011".equals(str)){
			this.distractionAlarm = 1;
		}else{
			this.distractionAlarm = 0;
		}
		
		if("100".equals(str)){
			this.callAlarm = 1;
		}else{
			this.callAlarm = 0;
		}
		
		if("101".equals(str)){
			this.smokingAlarm = 1;
		}else{
			this.smokingAlarm = 0;
		}
		
		if("110".equals(str)){
			this.forwardCrashAlarm = 1;
		}else{
			this.forwardCrashAlarm = 0;
		}
		
		if("111".equals(str)){
			this.departureDrivingAlarm = 1;
		}else{
			this.departureDrivingAlarm = 0;
		}
	}
	
	

	public int getExigenceAlarmStatus() {
		return exigenceAlarmStatus;
	}


	public void setExigenceAlarmStatus(int exigenceAlarmStatus) {
		this.exigenceAlarmStatus = exigenceAlarmStatus;
	}

	public int getSpeedAlarmStatus() {
		return speedAlarmStatus;
	}

	public void setSpeedAlarmStatus(int speedAlarmStatus) {
		this.speedAlarmStatus = speedAlarmStatus;
	}

	public int getTimeoutAlarmStatus() {
		return timeoutAlarmStatus;
	}

	public void setTimeoutAlarmStatus(int timeoutAlarmStatus) {
		this.timeoutAlarmStatus = timeoutAlarmStatus;
	}

	public int getDangerousAlarmStatus() {
		return dangerousAlarmStatus;
	}

	public void setDangerousAlarmStatus(int dangerousAlarmStatus) {
		this.dangerousAlarmStatus = dangerousAlarmStatus;
	}

	public int getGNSSModuleAlarm() {
		return GNSSModuleAlarm;
	}

	public void setGNSSModuleAlarm(int gNSSModuleAlarm) {
		GNSSModuleAlarm = gNSSModuleAlarm;
	}

	public int getGNSSAntennaBreakAlarm() {
		return GNSSAntennaBreakAlarm;
	}

	public void setGNSSAntennaBreakAlarm(int gNSSAntennaBreakAlarm) {
		GNSSAntennaBreakAlarm = gNSSAntennaBreakAlarm;
	}

	public int getGNSSAntennaShortAlarm() {
		return GNSSAntennaShortAlarm;
	}

	public void setGNSSAntennaShortAlarm(int gNSSAntennaShortAlarm) {
		GNSSAntennaShortAlarm = gNSSAntennaShortAlarm;
	}

	public int getVoltageAlarm() {
		return voltageAlarm;
	}

	public void setVoltageAlarm(int voltageAlarm) {
		this.voltageAlarm = voltageAlarm;
	}

	public int getPowerDownAlarm() {
		return powerDownAlarm;
	}

	public void setPowerDownAlarm(int powerDownAlarm) {
		this.powerDownAlarm = powerDownAlarm;
	}

	public int getLCDAlarm() {
		return LCDAlarm;
	}

	public void setLCDAlarm(int lCDAlarm) {
		LCDAlarm = lCDAlarm;
	}

	public int getTTSAlarm() {
		return TTSAlarm;
	}

	public void setTTSAlarm(int tTSAlarm) {
		TTSAlarm = tTSAlarm;
	}

	public int getCameraAlarm() {
		return cameraAlarm;
	}

	public void setCameraAlarm(int cameraAlarm) {
		this.cameraAlarm = cameraAlarm;
	}

	public int getICAlarm() {
		return ICAlarm;
	}

	public void setICAlarm(int iCAlarm) {
		ICAlarm = iCAlarm;
	}

	public int getSpeedEarlyAlarm() {
		return speedEarlyAlarm;
	}

	public void setSpeedEarlyAlarm(int speedEarlyAlarm) {
		this.speedEarlyAlarm = speedEarlyAlarm;
	}

	public int getFatigueDrivingAlarm() {
		return fatigueDrivingAlarm;
	}

	public void setFatigueDrivingAlarm(int fatigueDrivingAlarm) {
		this.fatigueDrivingAlarm = fatigueDrivingAlarm;
	}

	public int getDrivingTimeoutAlarm() {
		return drivingTimeoutAlarm;
	}

	public void setDrivingTimeoutAlarm(int drivingTimeoutAlarm) {
		this.drivingTimeoutAlarm = drivingTimeoutAlarm;
	}

	public int getParkingTimeoutAlarm() {
		return parkingTimeoutAlarm;
	}

	public void setParkingTimeoutAlarm(int parkingTimeoutAlarm) {
		this.parkingTimeoutAlarm = parkingTimeoutAlarm;
	}

	public int getInAndoutAreaAlarm() {
		return inAndoutAreaAlarm;
	}

	public void setInAndoutAreaAlarm(int inAndoutAreaAlarm) {
		this.inAndoutAreaAlarm = inAndoutAreaAlarm;
	}

	public int getInAndoutLineAlarm() {
		return inAndoutLineAlarm;
	}

	public void setInAndoutLineAlarm(int inAndoutLineAlarm) {
		this.inAndoutLineAlarm = inAndoutLineAlarm;
	}

	public int getDrivingTimeShortAlarm() {
		return drivingTimeShortAlarm;
	}

	public void setDrivingTimeShortAlarm(int drivingTimeShortAlarm) {
		this.drivingTimeShortAlarm = drivingTimeShortAlarm;
	}

	public int getLineLeviateAlarm() {
		return lineLeviateAlarm;
	}

	public void setLineLeviateAlarm(int lineLeviateAlarm) {
		this.lineLeviateAlarm = lineLeviateAlarm;
	}

	public int getVSSAlarm() {
		return VSSAlarm;
	}

	public void setVSSAlarm(int vSSAlarm) {
		VSSAlarm = vSSAlarm;
	}

	public int getOilAlarm() {
		return oilAlarm;
	}

	public void setOilAlarm(int oilAlarm) {
		this.oilAlarm = oilAlarm;
	}

	public int getStolenBusAlarm() {
		return stolenBusAlarm;
	}

	public void setStolenBusAlarm(int stolenBusAlarm) {
		this.stolenBusAlarm = stolenBusAlarm;
	}

	public int getIgnitionAlarm() {
		return ignitionAlarm;
	}

	public void setIgnitionAlarm(int ignitionAlarm) {
		this.ignitionAlarm = ignitionAlarm;
	}

	public int getDisplacementAlarm() {
		return displacementAlarm;
	}

	public void setDisplacementAlarm(int displacementAlarm) {
		this.displacementAlarm = displacementAlarm;
	}

	public int getCrashAlarm() {
		return crashAlarm;
	}

	public void setCrashAlarm(int crashAlarm) {
		this.crashAlarm = crashAlarm;
	}

	public int getTurnoverAlarm() {
		return turnoverAlarm;
	}

	public void setTurnoverAlarm(int turnoverAlarm) {
		this.turnoverAlarm = turnoverAlarm;
	}

	public int getOpenDoorAlarm() {
		return openDoorAlarm;
	}

	public void setOpenDoorAlarm(int openDoorAlarm) {
		this.openDoorAlarm = openDoorAlarm;
	}

	public int getCloseEyeAlarm() {
		return closeEyeAlarm;
	}

	public void setCloseEyeAlarm(int closeEyeAlarm) {
		this.closeEyeAlarm = closeEyeAlarm;
	}

	public int getYawnAlarm() {
		return yawnAlarm;
	}

	public void setYawnAlarm(int yawnAlarm) {
		this.yawnAlarm = yawnAlarm;
	}

	public int getDistractionAlarm() {
		return distractionAlarm;
	}

	public void setDistractionAlarm(int distractionAlarm) {
		this.distractionAlarm = distractionAlarm;
	}

	public int getCallAlarm() {
		return callAlarm;
	}

	public void setCallAlarm(int callAlarm) {
		this.callAlarm = callAlarm;
	}

	public int getSmokingAlarm() {
		return smokingAlarm;
	}

	public void setSmokingAlarm(int smokingAlarm) {
		this.smokingAlarm = smokingAlarm;
	}

	public int getForwardCrashAlarm() {
		return forwardCrashAlarm;
	}

	public void setForwardCrashAlarm(int forwardCrashAlarm) {
		this.forwardCrashAlarm = forwardCrashAlarm;
	}

	public int getDepartureDrivingAlarm() {
		return departureDrivingAlarm;
	}

	public void setDepartureDrivingAlarm(int departureDrivingAlarm) {
		this.departureDrivingAlarm = departureDrivingAlarm;
	}
	
}
