package com.cictec.middleware.tsinghua.tcp.code;

/**
 * 
 * 在消息头或者消息体解析过程中如果出现了消息标志位 0X7e，则抛出该异常。
 * @author Zhibin
 *
 */
public class IllegalMessageException extends Exception {

	public IllegalMessageException() {
		super();
	}

	public IllegalMessageException(String message) {
		super(message);
	}

	
}
