package com.cictec.middleware.tsinghua.model.vo;

public class AppendMessageState {
	
	private int webcam1;//0 0：摄像头 1 正常 1：摄像头 1 断开 标准维持至报警条件解除
	private int webcam2;//1 0：摄像头 2 正常 1：摄像头 2 断开 标准维持至报警条件解除
	private int webcam3;//2 0：摄像头 3 正常 1：摄像头 3 断开 标准维持至报警条件解除
	private int webcam4;//3 0：摄像头 4 正常 1：摄像头 4 断开 标准维持至报警条件解除
	private int webcam5;//4 0：摄像头 5 正常 1：摄像头 5 断开 标准维持至报警条件解除
	private int webcam6;//5 0：摄像头 6 正常 1：摄像头 6 断开 标准维持至报警条件解除
	private int webcam7;//6 0：摄像头 7 正常 1：摄像头 7 断开 标准维持至报警条件解除
	private int webcam8;//7 0：摄像头 8 正常 1：摄像头 8 断开 标准维持至报警条件解除
	private int webcam9;//8 0：高清摄像头正常 1：高清摄像头断开 标准维持至报警条件解除
	private int radar;//9 0：雷达正常 1：雷达断开 标准维持至报警条件解除
	private int GPS;//10 0：GPS 正常 1：GPS 断开 标准维持至报警条件解除
	private int alarmDisplay;//11 0：报警显示器正常 1：报警显示器故障 标准维持至报警条件解除
	private int voiceSpeaker;//12 0：语音喇叭正常 1：语音喇叭故障 标准维持至报警条件解除
	private int infraredLamp;//13 0：红外灯正常 1：红外灯故障 标准维持至报警条件解除
//14-47 保留
	public AppendMessageState(int[] ints) {
		super();
		this.webcam1 = ints[0];
		this.webcam2 = ints[1];
		this.webcam3 = ints[2];
		this.webcam4 = ints[3];
		this.webcam5 = ints[4];
		this.webcam6 = ints[5];
		this.webcam7 = ints[6];
		this.webcam8 = ints[7];
		this.webcam9 = ints[8];
		this.radar = ints[9];
		this.GPS = ints[10];
		this.alarmDisplay = ints[11];
		this.voiceSpeaker = ints[12];
		this.infraredLamp = ints[13];
	}
	
	
	public AppendMessageState() {
		
	}


	public int getWebcam1() {
		return webcam1;
	}
	public void setWebcam1(int webcam1) {
		this.webcam1 = webcam1;
	}
	public int getWebcam2() {
		return webcam2;
	}
	public void setWebcam2(int webcam2) {
		this.webcam2 = webcam2;
	}
	public int getWebcam3() {
		return webcam3;
	}
	public void setWebcam3(int webcam3) {
		this.webcam3 = webcam3;
	}
	public int getWebcam4() {
		return webcam4;
	}
	public void setWebcam4(int webcam4) {
		this.webcam4 = webcam4;
	}
	public int getWebcam5() {
		return webcam5;
	}
	public void setWebcam5(int webcam5) {
		this.webcam5 = webcam5;
	}
	public int getWebcam6() {
		return webcam6;
	}
	public void setWebcam6(int webcam6) {
		this.webcam6 = webcam6;
	}
	public int getWebcam7() {
		return webcam7;
	}
	public void setWebcam7(int webcam7) {
		this.webcam7 = webcam7;
	}
	public int getWebcam8() {
		return webcam8;
	}
	public void setWebcam8(int webcam8) {
		this.webcam8 = webcam8;
	}
	public int getWebcam9() {
		return webcam9;
	}
	public void setWebcam9(int webcam9) {
		this.webcam9 = webcam9;
	}
	public int getRadar() {
		return radar;
	}
	public void setRadar(int radar) {
		this.radar = radar;
	}
	public int getGPS() {
		return GPS;
	}
	public void setGPS(int gPS) {
		GPS = gPS;
	}
	public int getAlarmDisplay() {
		return alarmDisplay;
	}
	public void setAlarmDisplay(int alarmDisplay) {
		this.alarmDisplay = alarmDisplay;
	}
	public int getVoiceSpeaker() {
		return voiceSpeaker;
	}
	public void setVoiceSpeaker(int voiceSpeaker) {
		this.voiceSpeaker = voiceSpeaker;
	}
	public int getInfraredLamp() {
		return infraredLamp;
	}
	public void setInfraredLamp(int infraredLamp) {
		this.infraredLamp = infraredLamp;
	}

}
