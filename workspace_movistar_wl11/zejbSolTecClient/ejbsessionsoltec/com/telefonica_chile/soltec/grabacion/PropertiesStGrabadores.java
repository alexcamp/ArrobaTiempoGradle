/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.soltec.grabacion;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.Constants;
//import com.telefonica_chile.atiempo.utiles.FileWatchdog;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author dfiguer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PropertiesStGrabadores
{
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.grabadoresSTConfigName;
	private static boolean isInicializated = false;
	protected static final Logger log=LoggerFactory.getLogger(PropertiesStGrabadores.class);
	private static Properties properties = null;
	
	static {
		//new PropertiesSTGrabadoresWatchdog(CONFIG_FILENAME).start();
		PropertiesStGrabadores.init( CONFIG_FILENAME );
	}
		
	public static Map getDatos()
	{
		return properties;
	}
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando grabadores ST ###");
			log.info("1. Cargando archivo de propiedades: file=[" + filename + "] .");

			try
			{
				properties = new Properties();
				FileInputStream fis = new FileInputStream(filename);
				properties.load(fis);
				log.debug(
					"Archivo cargado correctamente:" + " file=["
						+ filename
						+ "] .");
			} catch (Throwable t) {
				log.info("### grabadores NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### grabadores inicializado ###");
			isInicializated = true;
		}
	}
	
//	static class PropertiesSTGrabadoresWatchdog extends FileWatchdog
//	{
//		public PropertiesSTGrabadoresWatchdog(String filename)
//		{
//				super(filename);
//		}
//
//		public void doOnChange()
//		{
//			PropertiesStGrabadores.isInicializated = false;
//			PropertiesStGrabadores.init( getFilename() );
//		}
//	}
	
}
