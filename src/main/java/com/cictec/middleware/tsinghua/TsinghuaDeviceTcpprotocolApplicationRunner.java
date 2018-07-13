package com.cictec.middleware.tsinghua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** 
 * 注意：一定要有@Component这个注解。要不然SpringBoot扫描不到这个类,是不会执行。 
 * @author wangyong
 * @date 2018年2月9日 下午2:46:23 
 */  
@Component
public class TsinghuaDeviceTcpprotocolApplicationRunner implements ApplicationRunner {

	
	private static final Logger logger = LoggerFactory.getLogger(TsinghuaDeviceTcpprotocolApplicationRunner.class);

	
	
    @Override  
    public void run(ApplicationArguments args) throws Exception {
    	logger.info("程序启动开始...");
    } 
    
} 