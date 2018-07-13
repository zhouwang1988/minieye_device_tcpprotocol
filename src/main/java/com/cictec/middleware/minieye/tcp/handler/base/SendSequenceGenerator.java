package com.cictec.middleware.minieye.tcp.handler.base;

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
