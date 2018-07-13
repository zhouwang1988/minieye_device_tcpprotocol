package com.cictec.middleware.tsinghua.config;

import java.net.InetSocketAddress;

import com.cictec.middleware.tsinghua.tcp.code.TerminalProtocolCodecFactory;
import com.cictec.middleware.tsinghua.tcp.filter.AuthenticationFilter;
import com.cictec.middleware.tsinghua.tcp.filter.LogFilter;
import com.cictec.middleware.tsinghua.tcp.handler.base.TerminalMessageHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 *
 */
@Configuration
public class MinaConfig {

	@Value("${mina.processor.count}")
	private Integer processorCount;

	@Value("${mina.receiveBuffer.size}")
	private Integer receiveBufferSize;

	@Value("${mina.sendBuffer.size}")
	private Integer sendBufferSize;

	@Value("${mina.maxRead.size}")
	private Integer maxReadSize;

	@Value("${mina.tcp.port}")
	private String port;
	
	@Autowired
	TerminalProtocolCodecFactory terminalProtocolCodecFactory;

	@Autowired
	LogFilter logFilter;
	@Autowired
	AuthenticationFilter authenticationFilter;
	
	@Autowired
	TerminalMessageHandler terminalMessageHandler;

	@Bean
	public InetSocketAddress inetSocketAddress() {
		return new InetSocketAddress(Integer.parseInt(port));
	}

	@Bean
	public IoAcceptor ioAcceptor() throws Exception {
		IoAcceptor acceptor = new NioSocketAcceptor(processorCount);
		acceptor.getFilterChain().addFirst("logger", this.logFilter);
		acceptor.getFilterChain().addAfter("logger", "codeFiter", new ProtocolCodecFilter(terminalProtocolCodecFactory));
		acceptor.getFilterChain().addLast("authenticationFilter", this.authenticationFilter);

		acceptor.setHandler(terminalMessageHandler);
		SocketSessionConfig sessionConfig = (SocketSessionConfig) acceptor.getSessionConfig();
		sessionConfig.setTcpNoDelay(true);
		sessionConfig.setReceiveBufferSize(receiveBufferSize); // 524288
		sessionConfig.setSendBufferSize(sendBufferSize); // 1048576
		sessionConfig.setMaxReadBufferSize(maxReadSize); // 1048576

		//设置通道空闲时间
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 120);
		
		acceptor.bind(inetSocketAddress());
		
		return acceptor;
	}

}
