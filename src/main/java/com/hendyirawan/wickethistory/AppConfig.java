package com.hendyirawan.wickethistory;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author mahendri
 *
 */
@Configuration
//WEB-INF is not yet supported: https://jira.springsource.org/browse/SPR-10401
//@PropertySource({"classpath:/META-INF/hub.properties"})
@ComponentScan({"com.hendyirawan.wickethistory"})
public class AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	@Inject
	private Environment env;
	
	@Bean
	public SoluvasWebApplication webApp() {
		return new SoluvasWebApplication();
	}
	
}
