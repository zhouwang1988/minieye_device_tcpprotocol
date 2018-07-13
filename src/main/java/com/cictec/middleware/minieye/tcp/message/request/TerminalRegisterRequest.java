package com.cictec.middleware.minieye.tcp.message.request;


import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

/**
 * 0x0100 设备注册TerminalRegisterRequest类
 * @file  TerminalRegisterRequest.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
public class TerminalRegisterRequest extends TerminalMessage {


    private int provinceId;//省域
    private int cityId;//市县域
    private String manufacturerId;//制造商
    private String terminalVesion;//终端型号
    private String terminalId;//终端ID
    private String carColor;//车牌颜色
    private String carNum;//车辆标识




    public TerminalRegisterRequest(Header header) {
        super(header);
    }


    @Override
    public String toString() {
        return "TerminalConnectRequest [provinceId=" + provinceId
                + ", cityId=" + cityId + ", terminalVesion="
                + terminalVesion + ", manufacturerId=" + manufacturerId
                + ", terminalId=" + terminalId + ", carColor=" + carColor
                + ", carNum=" + carNum + ", getHeader()=" + getHeader().toString() + "]";
    }


    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getTerminalVesion() {
        return terminalVesion;
    }

    public void setTerminalVesion(String terminalVesion) {
        this.terminalVesion = terminalVesion;
    }

    @Override
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }



}
