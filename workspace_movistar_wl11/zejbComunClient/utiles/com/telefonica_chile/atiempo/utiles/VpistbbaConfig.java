/*
 * Applicationconfig.java $Id: VpistbbaConfig.java,v 1.1.2.1 2011/03/30 22:15:08 lfmartinez Exp $
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

/**
 * <P>
 * Clase auxiliar para obtener la configuracion de las aplicaciones basandose
 * en el archivo de configuracion "configVpiStbBa.properties".
 * </P> 
 * 
 * 
 * @author Aldrin Martoq <a href="amartoq@interplanet.cl">amartoq@interplanet.cl</a> 
 * @since Created on Sep 6, 2005 @ 3:31:09 PM
 */
public class VpistbbaConfig
{
	private static boolean isInicializated = false;

	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String CONFIG_FILENAME =
		Constants.INSTALL_ETC_PATH + Constants.vpistbbaConfigName;
		
	//
	// private fields
	//

	/**
	 * Obtencion de logger
	 */
	protected static final Logger log =
		LoggerFactory.getLogger(VpistbbaConfig.class);

	/**
	 * Properties para acceder a la configuracion.
	 */
	private static Properties properties = null;

	//
	// init code
	//

	static {
		//new VpistbbaConfigWatchdog(CONFIG_FILENAME).start();
		VpistbbaConfig.init( CONFIG_FILENAME );
	}

	static synchronized void init(String filename) {
		//if (properties == null) {
		if ( !isInicializated  ) {
			log.info("### Inicializando VpistbbaConfig ###");
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
				log.info("### VpistbbaConfig NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### VpistbbaConfig inicializado ###");
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
	
//	static class VpistbbaConfigWatchdog extends FileWatchdog {
//		
//		public VpistbbaConfigWatchdog(String filename) {
//			super(filename);
//		}
//
//		/* (non-Javadoc)
//		 * @see com.telefonica_chile.atiempo.utiles.FileWatchdog#doOnChange()
//		 */
//		public void doOnChange() {
//			VpistbbaConfig.isInicializated = false;
//			VpistbbaConfig.init( getFilename() );
//		}
//	}
	
}
