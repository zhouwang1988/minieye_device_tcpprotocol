package com.cictec.middleware.tsinghua.tcp.code;

/**
 * 找不到相应的解码
 * @author Zhibin
 *
 */
public class CanntFoundDecoder extends Exception {
	
	public CanntFoundDecoder() {
		super();
	}

	public CanntFoundDecoder(String message) {
		super(message);
	}

}
