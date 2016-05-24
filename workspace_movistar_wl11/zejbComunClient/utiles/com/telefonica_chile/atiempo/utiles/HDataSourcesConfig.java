/*
 * Applicationconfig.java $Id: HDataSourcesConfig.java,v 1.1.2.1 2011/03/30 22:15:08 lfmartinez Exp $
 * 
 * @author amartoq
 * 
 * Created on Sep 6, 2005
 * Copyright (C) 2005 www.xperience.cl www.interplanet.cl
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class HDataSourcesConfig
{
	private static boolean isInicializated = false;

	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String CONFIG_FILENAME =
		Constants.INSTALL_ETC_PATH + Constants.HDATASOURCES;
		
	//
	// private fields
	//

	/**
	 * Obtencion de logger
	 */
	protected static final Logger log =
		LoggerFactory.getLogger(HDataSourcesConfig.class);

	/**
	 * Properties para acceder a la configuracion.
	 */
	private static Properties properties = null;

	//
	// init code
	//

	static {
		//new HDataSourcesConfigWatchdog(CONFIG_FILENAME).start();
		HDataSourcesConfig.init( CONFIG_FILENAME );
	}

	static synchronized void init(String filename) {
		//if (properties == null) {
		if ( !isInicializated  ) {
			log.info("### Inicializando HDataSourcesConfig ###");
			log.info("1. Cargando archivo de propiedades: file=[" + filename + "] .");

			try {
				properties = new Properties();
				FileInputStream fis = new FileInputStream(filename);
				properties.load(fis);
				log.debug(
					"Archivo cargado correctamente:" + " file=["
						+ filename
						+ "] .");
			} catch (Throwable t) {
				log.info("### HDataSourcesConfig NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### HDataSourcesConfig inicializado ###");
		}
		
		isInicializated = true;
	}


	/**
	 */
	public static String getVariable(String key) {
		String ret = null;
		if ( properties == null )
			return null;
		try {
			ret = properties.getProperty(key);
			return ret;
		} catch (Exception e) {
			log.error("Variable no Encontrada [" + key + "]: " + e);
		}
		return ret;
	}
	
//	static class HDataSourcesConfigWatchdog extends FileWatchdog {
//		
//		public HDataSourcesConfigWatchdog(String filename) {
//			super(filename);
//		}
//
//		/* (non-Javadoc)
//		 * @see com.telefonica_chile.atiempo.utiles.FileWatchdog#doOnChange()
//		 */
//		public void doOnChange() {
//			HDataSourcesConfig.isInicializated = false;
//			HDataSourcesConfig.init( getFilename() );
//		}
//	}
	
}
