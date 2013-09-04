package com.hendyirawan.atmoboot;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.google.common.base.Preconditions;

/**
 * @author mahendri
 *
 */
@Component("webApp")
public class SoluvasWebApplication extends AtmosphereApplication {
	
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
	
    /**
     * This is different than {@link AnnotatedMountScanner} because it works with
     * {@link TenantMode#MULTI_PATH}.
     * @param page
     */
	private void mountPage(Class<? extends Page> page) {
		final MountPath mountPath = Preconditions.checkNotNull(page.getAnnotation(MountPath.class),
				"Page class %s does not have @MountPath annotation", page.getName());
		if ("".equals(mountPath.value())) {
			log.info("Not mounting empty mount point for page {} in {} tenant mode",
					page.getName(), "MULTI_HOST");
		} else {
			mountPage(mountPath.value(), page);
		}
	}

	private void mountPages() {
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
