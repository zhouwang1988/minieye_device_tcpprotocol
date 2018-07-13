package com.cictec.middleware.minieye.tcp.filter;

import com.cictec.middleware.minieye.tcp.SessionManager;
import com.cictec.middleware.minieye.utils.BinaryUtils;
import com.cictec.middleware.minieye.utils.DateUtils;
import com.cictec.middleware.minieye.utils.log.MultiLog;
import com.cictec.middleware.minieye.utils.log.MultiLogFileNameUtils;
import com.cictec.middleware.minieye.utils.log.MultiLogUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class LogFilter extends IoFilterAdapter{

	private static Logger logger = LoggerFactory.getLogger(LogFilter.class);


	@Value("${tcpMessage.save}")
	private boolean messageSave;

	@Value("${tcpMessage.address}")
	private String messageAddress;

	@Value("${tcpMessage.content}")
	private String logContent;

	@Autowired
	private SessionManager sessionManager;


	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		if (messageSave) {
			IoBuffer ib = (IoBuffer) message;
			int pos = ib.position();
			try {
				byte[] bytes = new byte[ib.limit()];
				ib.get(bytes);
				logger.debug("Session ID {} Data [{}]", session.getId(), BinaryUtils.byte2HexStr(bytes));

				String terminalId = sessionManager.getTerminalId(session);
				if (!StringUtils.isEmpty(terminalId)) {
					String logPath = MultiLogFileNameUtils.getBinaryLogName(DateUtils.getDate(), terminalId);
					MultiLog log = MultiLogUtils.getMultiLogNotime(messageAddress,logPath,logContent);
					log.debug(BinaryUtils.byte2HexStr(bytes));
				}
			} finally {
				ib.position(pos);
			}

		}

		super.messageReceived(nextFilter, session, message);
	}

}

