package com.cictec.middleware.tsinghua.tcp.handler.base;

public class SendSequenceGenerator {
	private int value = -1;

	public int getNextValue() {
		value++;
		value %= 0x100;
		return value;
	}

	public int getCurrentValue() {
		return value;
	}
}
