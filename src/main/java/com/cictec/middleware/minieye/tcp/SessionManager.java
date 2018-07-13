package com.cictec.middleware.minieye.tcp;

import com.alibaba.fastjson.JSON;
import com.cictec.middleware.minieye.config.Constants;
import com.cictec.middleware.minieye.config.ResultCode;
import com.cictec.middleware.minieye.entity.po.TDevice;
import com.cictec.middleware.minieye.service.TDeviceService;
import com.cictec.middleware.minieye.utils.DateUtils;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 多媒体中间件SessionManager管理类
 * @file  SessionManager.java
 * @author zoboy
 * @version 2.0.0
 * Copyright(C), 2017
 *			xi'an Coordinates Software Development Co., Ltd.
 */
@Component
public class SessionManager implements ApplicationEventPublisherAware {
	
	private static Logger logger = LoggerFactory.getLogger(SessionManager.class);
	
	private final AttributeKey CONNECT_TIME = new AttributeKey(getClass(), "ConnectTime");
	private final AttributeKey TERMINAL_ID = new AttributeKey(getClass(), "TerminalId");
	private final AttributeKey SERIAL_NUMBER= new AttributeKey(getClass(), "SerialNumber");
	private final AttributeKey REALTIME_BUS= new AttributeKey(getClass(), "RealtimeBus");
	@Autowired
	private TDeviceService tDeviceService;
	/**
	 * 所有的连接到平台的Session
	 */
	private ConcurrentMap<Long,IoSession> sessions = new ConcurrentHashMap<Long,IoSession>();
		
	//private RealTimeEngine realTimeEngine;
	private ApplicationEventPublisher applicationEventPublisher;
	private boolean allowRepeatConnectOneAddress = true;
	private boolean closeOldConnectWhenRepeatConnect = true;
	/**
	 * 获取所有的Session
	 * @return
	 */
	public Collection<IoSession> getAllSession(){
		return sessions.values();
	}
	
	public void showActiveSessions(){

		if(logger.isDebugEnabled()){
			logger.debug("当前SessionManager的连接会话：");
			
			for(IoSession ses : sessions.values()){
				String createTime = DateUtils.formatDateTimes(new Date(ses.getCreationTime()));
				String lastReadTime = DateUtils.formatDateTimes(new Date(ses.getLastReadTime()));
				String lastWriteTime = DateUtils.formatDateTimes(new Date(ses.getLastWriteTime()));
				String addr = ses.getRemoteAddress()==null ? "" : ses.getRemoteAddress().toString();
				
				logger.debug("### Session {} ID {} IP:{} crt:{} lst rad:{} lst wrt:{} 关闭中 {} 连接 {}"
						,ses.getId()
						,ses.getAttribute(TERMINAL_ID)
						,addr
						,createTime
						,lastReadTime
						,lastWriteTime,
						ses.isClosing(),
						ses.isConnected());
			}
		}
	}
	
	
	
	/** 终端出厂序列号*/
	public void setSerialNumber(IoSession session,String sn){
		session.setAttribute(SERIAL_NUMBER, sn);
	}
	
	/** 终端出厂序列号*/
	public String getSerialNumber(IoSession session){
		return (String) session.getAttribute(SERIAL_NUMBER);
	}
	
	public void closeSession(long  sessionId,boolean immediately){
		IoSession session = sessions.get(sessionId);
		if(session != null){
			closeSession(session,immediately);
		}else{
			logger.warn("关闭Session 无法根据Id找到对应的 Session {} ",sessionId);
		}
	}
	
	public void closeSession(IoSession session,boolean immediately){
		logger.debug("关闭连接会话 Session {}",session.getId());
		CloseFuture closeFuture = session.closeNow();
		closeFuture.addListener(new IoFutureListener<IoFuture>() {
			@Override
			public void operationComplete(IoFuture future) {
				logger.debug("连接会话关闭完成 Session {}",future.getSession().getId());
			}
		});
	}
	
	/**
	 * Session是否通过鉴权
	 * @param session
	 * @return
	 */
	public boolean isAuthenticated(IoSession session){
		return session.getAttribute(TERMINAL_ID) == null ? false : true;
	}
	
	public void registerSession(IoSession session){
		
		InetSocketAddress isa = (InetSocketAddress)session.getRemoteAddress();
		
		if(!allowRepeatConnectOneAddress){
			for(IoSession s : sessions.values()){
				InetSocketAddress addr = (InetSocketAddress)s.getRemoteAddress();
				if(addr!=null && addr.getAddress().getHostAddress().equals(isa.getAddress().getHostAddress())){
					if(s.isConnected() && !s.isClosing() ){
						//发现30秒内有数据上传，判定重复连接。
						logger.debug("发现地址为 {} 的主机重复连接。",isa.getAddress().getHostAddress());
						if(!closeOldConnectWhenRepeatConnect){
							closeSession(session,true);
							return;
						}else{
							closeSession(s,true);
						}
					}
				}
			}
		}
		
		session.setAttributeIfAbsent(CONNECT_TIME,new Date());
		sessions.put(session.getId(), session);
		logger.debug("注册新建Session {} {}",session.getId(),session.getRemoteAddress().toString());
	}




	public void unregisterSession(IoSession session){
		
		sessions.remove(session.getId());
		String tId = (String) session.getAttribute(TERMINAL_ID);
		//logger.info("取消注册 SessionId {}  address:{}  TERMINAL_ID:{} ",session.getId(),session.getRemoteAddress().toString(), tId);



		if(tId != null && getSessionByTerminalId(tId) == null ){
			try{


				TDevice device = tDeviceService.findByDevCode(tId);
				device.setDevOnlineStatus(Constants.VAL_LOGIN_OFFLINE);
				device.setDevUpdateTime(new Date());
				tDeviceService.update(device);



				Map<String,String> map=new HashMap<String,String>();
				String id=UUID.randomUUID().toString();
				map=new HashMap<String,String>();
				map.put("esname",ResultCode.ES_NAME);
				map.put("command", ResultCode.MES_OFFLINE_NAME);
				map.put("agreement", ResultCode.JT808);
				map.put("id",id);
				map.put("deviceId", tId);
				map.put("date", DateUtils.formatDateTime(new Date()));
				map.put("time", DateUtils.formatTime(new Date()));
				map.put("type", ResultCode.MES_OFFLINE+"");
				map.put("resultCode", ResultCode.MES_SUCCESS+"");
				String resultContent =  "设备编号："+tId+" 下线成功！状态：‘离线’！";
				map.put("result", resultContent);
				logger.info(JSON.toJSONString(map));
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void tmpToRegistered(long sessionId,String terminalId){
		IoSession session = sessions.get(sessionId);
		if(session != null){
			tmpToRegistered(session,terminalId);
		}else{
			logger.warn("临时Session 转注册Session 无法根据Id找到对应的 Session {} ",sessionId);
		}		
	}
	
	public void tmpToRegistered(IoSession session,String terminalId){
		IoSession oldSession = getSessionByTerminalId(terminalId);
		if(oldSession != null){
			logger.warn("发现终端 {} 之前的Session 立即关闭.");
			oldSession.closeNow();
		}
		session.setAttribute(TERMINAL_ID, terminalId);
	}
	
	public boolean hasSession(String termianlId){
		for(IoSession session : sessions.values()){
			String tId = (String) session.getAttribute(TERMINAL_ID);
			if(tId != null && tId.equals(termianlId)){
				return true;
			}
		}
		return false;
	}
	
	public IoSession getSessionByTerminalId(String terminalId){
		for(IoSession session : sessions.values()){
			String tId = (String) session.getAttribute(TERMINAL_ID);
			if(tId != null && tId.equals(terminalId) && !session.isClosing() && session.isConnected()){
				return session;
			}
		}
		return null;
	}
	
	
	
	public String getTerminalId(IoSession session){
		return (String) session.getAttribute(TERMINAL_ID);
	}
	
	

	public IoSession getSessionById(Long id){
		return sessions.get(id);
	}

	
	public boolean isAllowRepeatConnectOneAddress() {
		return allowRepeatConnectOneAddress;
	}

	public void setAllowRepeatConnectOneAddress(boolean allowRepeatConnectOneAddress) {
		this.allowRepeatConnectOneAddress = allowRepeatConnectOneAddress;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;	
	}
	
}
