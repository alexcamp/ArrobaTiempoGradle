/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.vpistbba.grabacion;

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
public class PropertiesGrabadores
{
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.grabadoresConfigName;
	private static boolean isInicializated = false;
	protected static final Logger log=LoggerFactory.getLogger(PropertiesGrabadores.class);
	private static Properties properties = null;
	
	static {
		//new PropertiesGrabadoresWatchdog(CONFIG_FILENAME).start();
		PropertiesGrabadores.init( CONFIG_FILENAME );
	}
		
	public static Map getDatos()
	{
		return properties;
	}
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando grabadores ###");
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
	
//	static class PropertiesGrabadoresWatchdog extends FileWatchdog
//	{
//		public PropertiesGrabadoresWatchdog(String filename)
//		{
//				super(filename);
//		}
//
//		public void doOnChange()
//		{
//			PropertiesGrabadores.isInicializated = false;
//			PropertiesGrabadores.init( getFilename() );
//		}
//	}
	
}
