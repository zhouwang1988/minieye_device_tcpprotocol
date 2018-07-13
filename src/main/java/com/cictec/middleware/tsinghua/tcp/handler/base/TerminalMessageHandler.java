package com.cictec.middleware.tsinghua.tcp.handler.base;


import com.cictec.middleware.tsinghua.config.Constants;
import com.cictec.middleware.tsinghua.tcp.SessionManager;
import com.cictec.middleware.tsinghua.tcp.annotation.HandleMessages;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class TerminalMessageHandler extends IoHandlerAdapter implements ApplicationContextAware {

	
private static Logger logger = LoggerFactory.getLogger(TerminalMessageHandler.class);
	
	private final AttributeKey SEQUENT_COUNTER = new AttributeKey(getClass(), "SeqentCounter");
	
	private ApplicationContext springContext;
	
	@Autowired
	private SessionManager sessionManager;
	
	private Map<Integer,MessageHandler> msgHandlers = new HashMap<Integer,MessageHandler>();
	
	
	@PostConstruct
	public void init() {
		// System.out.println("注册 BusMileageEngine 到 TimeEventBus");
		logger.debug("加载接口处理");
		loadTaskRunners();
	}

	
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		//初始化发送序列号生成器
		session.setAttribute(Constants.SESSION_SEND_SEQUENCE, new SendSequenceGenerator());
		session.setAttribute(SEQUENT_COUNTER ,new Integer(0));
		logger.debug("发送序列号生成器{},SEQUENT_COUNTER {} ",session.getAttributeKeys(),new Integer(0));
		sessionManager.registerSession(session);
		
		//loadTaskRunners();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)throws Exception {
		if(status == IdleStatus.BOTH_IDLE){
			sessionManager.closeSession(session, true);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		sessionManager.unregisterSession(session);		
	}

	@Override
	public void messageReceived(IoSession session, Object message)throws Exception {
		TerminalMessage tm = (TerminalMessage) message;
		dispatchMessage(session,tm);
	}

	protected void dispatchMessage(IoSession session,TerminalMessage tm) {
		
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("SESSION_ID", session.getId());
		headers.put("REMOTE_ADDRESS", session.getRemoteAddress().toString());
		headers.put("TERMINAL_ID", tm.getTerminalId());
		
		
		//saveRawMessageTemplate.sendBodyAndHeaders(tm, headers);	// 以json格式保存设备上传的原始消息

		MessageHandler handler = getMessageHandler(tm);			// 根据通讯协议获取对于的业务处理方法，处理方法 在 com.cic.md.disp.receiver.handler 包下
		
		try {
			handler.handleMessage(tm, session);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.debug("没有接入接口----------------->消息(十六进制)id：0x"+ Integer.toHexString(getHandlerKey(tm)));
			//e.printStackTrace();
			//System.out.println(getHandlerKey(tm));
		}
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)	throws Exception {
		cause.printStackTrace();
		logger.error("连接出现异常", cause);
	}

	/**
	 * 注册加载：根据通讯协议加载各个协议处理方法 【注解为 @HandleMessages】的处理方法
	 */
	public void loadTaskRunners(){
		ClasspathDiscoverer discoverer = new ClasspathDiscoverer();
		discoverer.addAnnotationListener(new TaskRunnerAnnotationDiscoveryListener());
		discoverer.discover(true, false, false, true, true);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.springContext = applicationContext;	
	}
	
	public MessageHandler getMessageHandler(TerminalMessage msg){
		return msgHandlers.get(getHandlerKey(msg));
	}
	
	public Integer getHandlerKey(TerminalMessage msg) {
		TerminalMessage tm = (TerminalMessage)msg;
		return tm.getHeader().getMessageId() ;
	}

	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}

	class TaskRunnerAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {
		
		@Override
		public void discovered(String clazz, String annotation) {
			
			logger.debug("Discovered Class(" + clazz + ") with Annotation("	+ annotation + ")");
			try {
				Class<?> clz = Class.forName(clazz);

				HandleMessages hm = clz.getAnnotation(HandleMessages.class);
				if(hm != null){
					int[] values =hm.value();
					MessageHandler ttr = (MessageHandler)springContext.getBean(Class.forName(clazz));
					for(Integer value : values){
						msgHandlers.put(value, ttr);
					}
				}else{
					logger.error("没有找到类 {} 中 HandleMessages 定义",clazz);
				}				
			} catch (ClassNotFoundException e) {
				logger.error("找不到类 {}",e.getMessage());
			}
		}

		@Override
		public String[] supportedAnnotations() {
			return new String[] { HandleMessages.class.getName() };
		}
	}

}
