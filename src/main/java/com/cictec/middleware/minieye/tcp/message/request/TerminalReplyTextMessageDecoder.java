package com.cictec.middleware.minieye.tcp.message.request;

import com.cictec.middleware.minieye.tcp.message.TerminalMessage;
public class TerminalReplyTextMessageDecoder extends TerminalMessage {
	
	private int mark;//	标志
	private String text; //文本信息
	
	public TerminalReplyTextMessageDecoder(Header header) {
        super(header);
    }

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
