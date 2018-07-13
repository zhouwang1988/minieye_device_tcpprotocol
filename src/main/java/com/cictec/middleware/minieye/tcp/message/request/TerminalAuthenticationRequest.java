package com.cictec.middleware.minieye.tcp.message.request;


import com.cictec.middleware.minieye.tcp.message.TerminalMessage;

/**
 * 0x0102 终端鉴权TerminalAuthenticationRequest类
 * @file  TerminalAuthenticationRequest.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
public class TerminalAuthenticationRequest extends TerminalMessage {

	/*
	 * 鉴权码
	 */
    private String authenticationKey;


    public TerminalAuthenticationRequest(Header header) {
        super(header);
    }


    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    @Override
    public String toString() {
        return "TerminalAuthenticationRequest [authenticationKey=" + authenticationKey
               + "]";
    }





}
