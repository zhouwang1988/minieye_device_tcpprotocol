package com.cictec.middleware.tsinghua.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvcConfig 
        extends WebMvcConfigurerAdapter {

	@Value("${subpackage.data.image}")
	private String basePath;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+basePath);
        super.addResourceHandlers(registry);
    }

}
