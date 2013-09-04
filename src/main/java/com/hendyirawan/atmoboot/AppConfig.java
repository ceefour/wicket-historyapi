package com.hendyirawan.atmoboot;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;

/**
 * @author mahendri
 *
 */
@Configuration
//WEB-INF is not yet supported: https://jira.springsource.org/browse/SPR-10401
//@PropertySource({"classpath:/META-INF/hub.properties"})
@ComponentScan({"com.hendyirawan.atmoboot"})
public class AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	@Inject
	private Environment env;
	
	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
	
	@Bean
	public SoluvasWebApplication webApp() {
		return new SoluvasWebApplication();
	}
	
	@Bean @Scope("request")
	public List<String> someRequestBean() {
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		log.info("got request: {}:{} to {} ({}:{})", 
				request.getRemoteHost(), request.getRemotePort(), request.getRequestURL(),
				request.getServerName(), request.getServerPort());
		if ("localhost".equalsIgnoreCase(request.getServerName())) {
			log.error("This web application is NOT meant to be accessed using 'localhost'");
		}
		return ImmutableList.of("Hello World!");
	}
	
}
