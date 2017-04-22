
package com.happycar;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ApplicationListener extends ContextLoaderListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {

		String webAppRootKey = event.getServletContext().getRealPath("/");
		System.setProperty("happycar.root", webAppRootKey);
	}
}

