/*
 * Created on Mar 10, 2005
 *
 */
package com.telefonica_chile.soltec.grabacion;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.Constants;
//import com.telefonica_chile.atiempo.utiles.FileWatchdog;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class GrabacionesStClave
{
	private static Map datos;
	private static final String CONFIG_FILENAME = Constants.INSTALL_ETC_PATH + Constants.grabacionesClaveConfigName;
	private static boolean isInicializated = false;
	protected static final Logger log=LoggerFactory.getLogger(GrabacionesStClave.class);
	private static Properties properties = null;
	
	static synchronized void init(String filename)
	{
		if ( !isInicializated  )
		{
			log.info("### Inicializando grabaciones clave ###");
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
				log.info("### grabaciones clave NO inicializado ### [" + t.getMessage() + "]");
			}
			log.info("### grabaciones clave inicializado ###");
			isInicializated = true;
			datos=new HashMap();
			for(Enumeration e=properties.keys();e.hasMoreElements();)
			{
				String element = (String) e.nextElement();
				datos.put(element, properties.getProperty(element));
			}
		}
	}

//	static class GrabacionesClaveWatchdog extends FileWatchdog
//	{
//		public GrabacionesClaveWatchdog(String filename)
//		{
//				super(filename);
//		}
//
//		public void doOnChange()
//		{
//			GrabacionesStClave.isInicializated = false;
//			GrabacionesStClave.init( getFilename() );
//		}
//	}
	
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
}
