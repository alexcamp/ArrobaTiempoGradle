/*
 * Created on 08-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadFactoryConfig {
	private static boolean isInicializated = false;
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.actividadFactoryConfigName;
	private static final Logger log = LoggerFactory.getLogger(ActividadFactoryConfig.class);
	private static Properties properties = null;
	
	static
	{
		//new ActividadFactoryConfigWatchdog(CONFIG_FILENAME).start();
		ActividadFactoryConfig.init( CONFIG_FILENAME );
	}
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando ActividadFactoryConfig ###");
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
				log.info("### ActividadFactoryConfig NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### ActividadFactoryConfig inicializado ###");
			isInicializated = true;
		}
	}
	
	public static String getVariable(String key)
	{
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
	
//	static class ActividadFactoryConfigWatchdog extends FileWatchdog
//	{
//		
//		public ActividadFactoryConfigWatchdog(String filename)
//		{
//			super(filename);
//		}
//
//		public void doOnChange()
//		{
//			ActividadFactoryConfig.isInicializated = false;
//			ActividadFactoryConfig.init( getFilename() );
//		}
//	}


}
