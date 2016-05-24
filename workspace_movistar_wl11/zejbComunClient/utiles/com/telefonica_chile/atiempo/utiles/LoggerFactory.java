/*
 * Logger.java $Id: LoggerFactory.java,v 1.2.2.1.2.3.2.2 2011/05/20 16:24:32 irincon Exp $
 * 
 * @author amartoq
 * 
 * Created on Aug 9, 2005
 * Copyright (C) 2005 www.xperience.cl www.interplanet.cl
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <P>
 * Factory for getting a LOG4J Logger.
 * </P> 
 * 
 * <P>
 * Whe need a place where the log4j properties file must be loaded. Putting this code in a web init servlet doesn't always work.
 * </P>
 * 
 * <P>
 * Just call getLogger(Class) for getting a Logger instance.
 * </P>
 * 
 * @author Aldrin Martoq <a href="amartoq@interplanet.cl">amartoq@interplanet.cl</a> 
 * @since Created on Aug 9, 2005 @ 3:11:33 PM
 */
public abstract class LoggerFactory {

	//
	// public constants
	//

	/**
	 * Name of the file for configuring the log4j properties
	 */
	public static final String LOG4J_CONFIG_FILE = "log4j.properties";

	//
	// private fields
	//

	/**
	 * Indicates if log4j has been initialized
	 */
	private static boolean log4jinitialized = false;

	//
	// Factory methods
	//

	/**
	 * Creates a new Logger instance. If the properties files has not been read, it will be read now.
	 */
	public static Logger getLogger(Class c) {
		if (log4jinitialized == false) {
			init();
		}
		return Logger.getLogger(c);
	}
	
	private static synchronized void init() {
		if (!log4jinitialized) {
			String path = Constants.INSTALL_ETC_PATH + ConfigRedirect.getRedirect(LOG4J_CONFIG_FILE);
			File file = new File(path);
			if (!file.exists() || !file.canRead()) {
				System.out.println("ERROR: Imposible leer archivo log4j.properties=[" + path + "]");
			}
			else{  
				System.out.println("Archivo log4j.properties=[" + path + "] VersionEar: 4.18.0 18/04/2012");
			}
			PropertyConfigurator.configureAndWatch(path);
			log4jinitialized = true;
			Logger log = Logger.getLogger(LoggerFactory.class);
	
			log.info("Logger inicializado log4j.properties=[" + path + "] VersionEar: 4.18.0 18/04/2012");
		}
	}
}
