package com.cictec.middleware.tsinghua.tcp.filter;


import com.cictec.middleware.tsinghua.tcp.SessionManager;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class  AuthenticationFilter extends IoFilterAdapter {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Autowired
	private SessionManager sessionManager;
	/**
	 * 鉴权消息ID
	 */
	private int authenticationMessageId;
	
	@Value("${allowedMessages.mina.messageid}")
	private String messageId;
	
	private Set<Integer> allowedMessages  = new HashSet<Integer>();;
	
	/**
	 * 
	 */
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,Object message) throws Exception {


		TerminalMessage tm = (TerminalMessage)message;
		
		this.setAllowedMessages(allowedMessages);
		
		if(sessionManager.isAuthenticated(session) 
				|| tm.getHeader().getMessageId() == authenticationMessageId 
				|| allowedMessages.contains(tm.getHeader().getMessageId())){
			
			super.messageReceived(nextFilter, session, message);
			
		}else{
			session.closeNow();
			logger.warn("未授权的终端访问 地址 {} 消息ID {} " 
					,session.getRemoteAddress().toString()
					, Integer.toHexString(tm.getHeader().getMessageId()));
		}
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,WriteRequest writeRequest) throws Exception {
		TerminalMessage tm = (TerminalMessage)writeRequest.getMessage();
		if(!sessionManager.isAuthenticated(session)){
			//终端还没有通过鉴权
			
//			switch (tm.getHeader().getMessageId()){
//				case 0xca :{
//					//获取编号应答
//					PlatformIdResponse pir = (PlatformIdResponse) tm;
//					if(pir.getId() == -1){
//						session.close(true);
//					}else{
//						super.messageSent(nextFilter, session, writeRequest);
//					}
//
//					break;
//				}
//				default :{
//					super.messageSent(nextFilter, session, writeRequest);
//				}
//			}
		}
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public int getAuthenticationMessageId() {
		return authenticationMessageId;
	}

	public void setAuthenticationMessageId(int authenticationMessageId) {
		this.authenticationMessageId = authenticationMessageId;
	}

	public Set<Integer> getAllowedMessages() {
		return allowedMessages;
	}

	public void setAllowedMessages(Set<Integer> allowedMessages) {
		this.allowedMessages = allowedMessages;
		if(messageId!=null && messageId.length()>0){
			String  messageIds [] = messageId.split(",");
			for(String msgId : messageIds){
				Integer parameter = Integer.parseInt(msgId.substring(2,msgId.length()), 16);
				this.allowedMessages.add(parameter);
			}
		}
	}
	
}
