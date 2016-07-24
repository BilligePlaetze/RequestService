package com.tinf15b2.webengineering.boundary;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tinf15b2.webengineering.facade.Control;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartUpService implements ServletContextListener {

	@Inject
	private Control control;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		control.closeDatabaseConnection();
		log.info(">>>>>>>>>>>>>>>>>Twittercollector-Service was closed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info(">>>>>>>>>>>>>>>>>Twittercollector-Service started");
	}

}
