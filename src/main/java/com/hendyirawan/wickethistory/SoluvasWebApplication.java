package com.hendyirawan.wickethistory;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * @author mahendri
 *
 */
@Component("webApp")
public class SoluvasWebApplication extends WebApplication {
	
	private static final Logger log = LoggerFactory
			.getLogger(SoluvasWebApplication.class);
	
	@Inject
	private Environment env;
	
	@Override
	protected void init() {
		super.init();
		
		getDebugSettings().setAjaxDebugModeEnabled(false);
		
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		
		mountPages();
	}
	
	private void mountPages() {
		new AnnotatedMountScanner().scanPackage("com.hendyirawan.wickethistory").mount(this);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
