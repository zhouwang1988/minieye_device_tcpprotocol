package com.cictec.middleware.minieye.tcp.message.request;

import java.util.Arrays;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

/**
 * 0x02 上传GPS 定位数据
 * @author Zhibin
 *
 */
public class TerminalPosition extends TerminalMessage {
	
	private double lat;
	private double lng;
	private int speed;
	private int angle;
	private int altitude;
	private int busTemperature;
	private int engineTemperature;
	private long gpsOdometer;
	private int canBusProtocol;
	private int canBusDataLongth;
	private byte[] canBusData;
	private int voltage1;
	private int current1;
	private int power1;
	private long energy1;
	private int voltage2;
	private int current2;
	private int power2;
	private long energy2;
	private long temperature;
	private long humidity;
	private long reserved1;
	private int positionModel;
	private int reserved2;
	
	
	public TerminalPosition(Header header) {
		super(header);
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public int getBusTemperature() {
		return busTemperature;
	}
	public void setBusTemperature(int busTemperature) {
		this.busTemperature = busTemperature;
	}
	public int getEngineTemperature() {
		return engineTemperature;
	}
	public void setEngineTemperature(int engineTemperature) {
		this.engineTemperature = engineTemperature;
	}
	public long getGpsOdometer() {
		return gpsOdometer;
	}
	public void setGpsOdometer(long gpsOdometer) {
		this.gpsOdometer = gpsOdometer;
	}
	public int getCanBusProtocol() {
		return canBusProtocol;
	}
	public void setCanBusProtocol(int canBusProtocol) {
		this.canBusProtocol = canBusProtocol;
	}
	public int getCanBusDataLongth() {
		return canBusDataLongth;
	}
	public void setCanBusDataLongth(int canBusDataLongth) {
		this.canBusDataLongth = canBusDataLongth;
	}
	public byte[] getCanBusData() {
		return canBusData;
	}
	public void setCanBusData(byte[] canBusData) {
		this.canBusData = canBusData;
	}
	public int getVoltage1() {
		return voltage1;
	}
	public void setVoltage1(int voltage1) {
		this.voltage1 = voltage1;
	}
	public int getCurrent1() {
		return current1;
	}
	public void setCurrent1(int current1) {
		this.current1 = current1;
	}
	public int getPower1() {
		return power1;
	}
	public void setPower1(int power1) {
		this.power1 = power1;
	}
	public long getEnergy1() {
		return energy1;
	}
	public void setEnergy1(long energy1) {
		this.energy1 = energy1;
	}
	public int getVoltage2() {
		return voltage2;
	}
	public void setVoltage2(int voltage2) {
		this.voltage2 = voltage2;
	}
	public int getCurrent2() {
		return current2;
	}
	public void setCurrent2(int current2) {
		this.current2 = current2;
	}
	public int getPower2() {
		return power2;
	}
	public void setPower2(int power2) {
		this.power2 = power2;
	}
	public long getEnergy2() {
		return energy2;
	}
	public void setEnergy2(long energy2) {
		this.energy2 = energy2;
	}
	public long getTemperature() {
		return temperature;
	}
	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	public long getHumidity() {
		return humidity;
	}
	public void setHumidity(long humidity) {
		this.humidity = humidity;
	}
	public long getReserved1() {
		return reserved1;
	}
	public void setReserved1(long reserved1) {
		this.reserved1 = reserved1;
	}
	public int getPositionModel() {
		return positionModel;
	}
	public void setPositionModel(int positionModel) {
		this.positionModel = positionModel;
	}
	public int getReserved2() {
		return reserved2;
	}
	public void setReserved2(int reserved2) {
		this.reserved2 = reserved2;
	}
	@Override
	public String toString() {
		return "TerminalPosition [lat=" + lat + ", lng=" + lng + ", speed="
				+ speed + ", angle=" + angle + ", altitude=" + altitude
				+ ", busTemperature=" + busTemperature + ", engineTemperature="
				+ engineTemperature + ", gpsOdometer=" + gpsOdometer
				+ ", canBusProtocol=" + canBusProtocol + ", canBusDataLongth="
				+ canBusDataLongth + ", canBusData="
				+ Arrays.toString(canBusData) + ", voltage1=" + voltage1
				+ ", current1=" + current1 + ", power1=" + power1
				+ ", energy1=" + energy1 + ", voltage2=" + voltage2
				+ ", current2=" + current2 + ", power2=" + power2
				+ ", energy2=" + energy2 + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", reserved1=" + reserved1
				+ ", positionModel=" + positionModel + ", reserved2="
				+ reserved2 + ", getHeader()=" + getHeader().toString() + "]";
	}

}
