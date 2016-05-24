/*
 * Created on 03-04-2007
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
public class ActividadFactorySTConfig {
	private static boolean isInicializated = false;
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.actividadFactorySTConfigName;
	protected static final Logger log = LoggerFactory.getLogger(ActividadFactorySTConfig.class);
	private static Properties properties = null;
	
	static
	{
		//new ActividadFactorySTConfigWatchdog(CONFIG_FILENAME).start();
		ActividadFactorySTConfig.init( CONFIG_FILENAME );
	}
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando ActividadFactorySTConfig ###");
			log.info("Cargando archivo de propiedades: file=[" + filename + "] .");

			try {
				properties = new Properties();
				FileInputStream fis = new FileInputStream(filename);
				properties.load(fis);
				log.debug(
					"Archivo cargado correctamente:" + " file=["
						+ filename
						+ "] .");
			} catch (Throwable t) {
				log.info("### ActividadFactorySTConfig NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### ActividadFactorySTConfig inicializado ###");
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
	
//	static class ActividadFactorySTConfigWatchdog extends FileWatchdog
//	{
//		
//		public ActividadFactorySTConfigWatchdog(String filename)
//		{
//			super(filename);
//		}
//
//		public void doOnChange()
//		{
//			ActividadFactorySTConfig.isInicializated = false;
//			ActividadFactorySTConfig.init( getFilename() );
//		}
//	}

}
