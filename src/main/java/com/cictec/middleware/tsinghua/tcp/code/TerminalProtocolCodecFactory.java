package com.cictec.middleware.tsinghua.tcp.code;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerminalProtocolCodecFactory implements ProtocolCodecFactory {
	 @Autowired
	 private TerminalProtocolDecoder terminalProtocolDecoder;
	 @Autowired
	 private TerminalProtocolEncoder terminalProtocolEncoder;
	
	
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return terminalProtocolEncoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return terminalProtocolDecoder;
	}

	
}
