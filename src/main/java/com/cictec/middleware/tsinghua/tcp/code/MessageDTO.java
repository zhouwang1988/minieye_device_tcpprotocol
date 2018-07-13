package com.cictec.middleware.tsinghua.tcp.code;


import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

import java.nio.ByteBuffer;

public class MessageDTO {

    private ByteBuffer head;

    private ByteBuffer body;

    private byte checkCode;

    private TerminalMessage.Header header;

    public MessageDTO(ByteBuffer head, ByteBuffer body, byte checkCode, TerminalMessage.Header header) {
        this.head = head;
        this.body = body;
        this.checkCode = checkCode;
        this.header = header;
    }

    public ByteBuffer getHead() {
        return head;
    }

    public void setHead(ByteBuffer head) {
        this.head = head;
    }

    public ByteBuffer getBody() {
        return body;
    }

    public void setBody(ByteBuffer body) {
        this.body = body;
    }

    public byte getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(byte checkCode) {
        this.checkCode = checkCode;
    }

    public TerminalMessage.Header getHeader() {
        return header;
    }

    public void setHeader(TerminalMessage.Header header) {
        this.header = header;
    }

}
