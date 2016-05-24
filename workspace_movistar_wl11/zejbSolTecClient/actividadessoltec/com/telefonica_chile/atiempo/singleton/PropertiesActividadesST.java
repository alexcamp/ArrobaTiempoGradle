/*
 * Created on Feb 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.singleton;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.Constants;
//import com.telefonica_chile.atiempo.utiles.FileWatchdog;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author mrcanal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class PropertiesActividadesST
{   	
	private static PropertiesActividadesST m_SingletonInstance = null;
	private static boolean isInicializated = false;
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.actividadesSTConfigName;
	protected static final Logger log = LoggerFactory.getLogger(PropertiesActividadesST.class);
	private static Properties properties = null;
	
	static
	{
		//new PropertiesActividadesWatchdog(CONFIG_FILENAME).start();
		PropertiesActividadesST.init( CONFIG_FILENAME );
	}
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando PropertiesActividades ###");
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
				log.info("### PropertiesActividades NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### PropertiesActividades inicializado ###");
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
	
	public static Properties getProperties()
	{
		return properties;
	}
	
//	static class PropertiesActividadesWatchdog extends FileWatchdog
//	{
//		
//		public PropertiesActividadesWatchdog(String filename)
//		{
//			super(filename);
//		}
//
//		public void doOnChange()
//		{
//			PropertiesActividadesST.isInicializated = false;
//			PropertiesActividadesST.init( getFilename() );
//		}
//	}

}
