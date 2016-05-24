/*
 * Applicationconfig.java $Id: ApplicationConfig.java,v 1.1.2.1 2011/03/30 22:15:08 lfmartinez Exp $
 * 
 * @author amartoq
 * 
 * Created on Sep 6, 2005
 * Copyright (C) 2005 www.xperience.cl www.interplanet.cl
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <P>
 * Clase auxiliar para obtener la configuracion de las aplicaciones basandose
 * en el archivo de configuracion "application.properties".
 * </P> 
 * 
 * <P>
 * Mantiene el acceso a la configuracion de las aplicaciones en un solo punto.
 * </P>
 * 
 * <P>
 * Llamar a los metodos estaticos, utilizando como nombre de la aplicacion las
 * constantes definidas en esta misma clase. Ej:
 * </P>
 * 
 * <code>
 * String url = ApplicationConfig.getUrlBase(ApplicationConfig.APP_BANDEJA);
 * </code>
 * 
 * @author Aldrin Martoq <a href="amartoq@interplanet.cl">amartoq@interplanet.cl</a> 
 * @since Created on Sep 6, 2005 @ 3:31:09 PM
 */
public class ApplicationConfig {

	//
	// public constants
	//


	private static boolean isInicializated = false; 

	/**
	 * Aplicacion Bandeja
	 */
	public static String APP_BANDEJA_ST="BANDEJA_ST";
	public static String APP_BANDEJA_VPI="BANDEJA_VPI";
	
	public static final String APP_BANDEJA = "BANDEJA";

	/**
	 * Aplicacion Vpi Stb Ba
	 */
	public static final String APP_VPISTBBA = "VPISTBBA";

	/**
	 * Aplicacion Vpi Ss Dd
	 */
	public static final String APP_VPISSDD = "VPISSDD";

	/**
	 * Aplicacion SolTec
	 */
	public static final String APP_ATST = "ATST";

	/**
	 * Aplicacion Workflow
	 */
	public static final String APP_WORKFLOW = "WORKFLOW";

	/**
	 * Aplicacion Workflow
	 */
	public static final String APP_MANTENEDORES = "MANTENEDORES";

	/**
	 * Identificador de la aplicacion Bandeja
	 */
//	public static final Long APP_BANDEJA_ID = new Long(6);

	/**
	 * Identificador de la aplicacion Vpi Stb Ba
	 */
	public static final String APP_VPISTBBA_ID = "APP_VPISTBBA_ID";

	/**
	 * Identificador de la aplicacion Vpi Ss Dd
	 */
//	public static final Long APP_VPISSDD_ID = new Long(4);

	/**
	 * Identificador de la aplicacion SolTec
	 */
	public static final String APP_ATST_ID = "APP_ATST_ID";

	/**
	 * Identificador de la aplicacion Workflow
	 */
//	public static final Long APP_WORKFLOW_ID = null;
	
	//
	// private constants
	//

	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String CONFIG_FILENAME =
		Constants.INSTALL_ETC_PATH + "application.properties";
		
	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String REDIRECT_FILENAME =
		Constants.INSTALL_ETC_PATH + "redirect.properties";		

	/**
	 * Sufijo para las variables de RMI-IIOP
	 */
	private static final String NAMESPACE_SUFFIX = "ns";

	/**
	 * Sufijo para las variables de URL
	 */
	private static final String URLBASE_SUFFIX = "url.base";

	//
	// private fields
	//

	/**
	 * Obtencion de logger
	 */
	protected static final Logger log =
		LoggerFactory.getLogger(ApplicationConfig.class);

	/**
	 * Properties para acceder a la configuracion.
	 */
	private static Properties properties = null;

	//
	// init code
	//

	static {
		//new ApplicationConfigWatchdog(CONFIG_FILENAME).start();
		ApplicationConfig.init( CONFIG_FILENAME );
	}

	static synchronized void init(String filename) {
		//if (properties == null) {
		if ( !isInicializated  ) {
			log.info("### Inicializando ApplicationConfig ###");
			log.info(
				"1. Cargando archivo de propiedades: file=[" + filename + "] .");
				
			/* 
			 * Busca archivo redirect. Si existe, lo carga y lee la ubicacion del
			 * archivo application.properties
			 */
			
				try {
					FileInputStream fis = new FileInputStream(REDIRECT_FILENAME);
					Properties prop = new Properties();
					prop.load(fis);
					filename = prop.getProperty("app.file");
				} catch (FileNotFoundException e) {
					log.debug("Archivo redirect.properties no definido");
				} catch (IOException e) {
					log.debug("Archivo redirect.properties no definido");
				}
					
				try {
					FileInputStream fis = new FileInputStream(filename);
				} catch (FileNotFoundException e1) {
					log.debug("Archivo contenido en redirect.properties no esta definido");
					filename = Constants.INSTALL_ETC_PATH + "application.properties";
				}
				
			try {
				properties = new Properties();
				FileInputStream fis = new FileInputStream(filename);
				properties.load(fis);
				log.debug(
					"Archivo cargado correctamente:"
						+ " file=["
						+ filename
						+ "] .");
			} catch (Throwable t) {
				log.fatal(
					"##########################################################");
				log.fatal(
					"Error cargando archivo de propiedades:"
						+ " file=["
						+ filename
						+ "]",
					t);
				for (int i =0; i < 10; i++) {
					log.fatal("LA APLICACION NO FUNCIONARA CORRECTAMENTE.");
				}
				log.fatal(
					"##########################################################");
			}
			log.info("### ApplicationConfig inicializado ###");
		}
		
		isInicializated = true;
	}

	//
	// public methods
	//

	/**
	 * Retorna la url para la aplicacion
	 * @param appName nombre de la aplicacion
	 * @return url base de la aplicacion
	 * @throws Exception si ocurre un error.
	 */
	public static String getUrlBase(String appName) throws Exception {
		String ret = null;
		String key = appName + "." + URLBASE_SUFFIX;
		try {
			ret = properties.getProperty(key);
			if (ret == null) {
				throw new Exception(
					"Variable no encontrada en ApplicationConfig:"
						+ " key=["
						+ key
						+ "]"
						+ " filename=["
						+ CONFIG_FILENAME
						+ "]");
			}
//			if ( log.isDebugEnabled() )
//				log.debug(
//				"getUrlBase: appName=[" + appName + "] urlBase=[" + ret + "]");
			return ret;
		} catch (Exception e) {
			log.fatal(
				"Error al obtener urlBase para appName=[" + appName + "]: " + e,
				e);
			throw e;
		}
	}

	/**
	 * Retorna el string RMI-IIOP para la aplicacion
	 * @param appName nombre de la aplicacion
	 * @return string de namespace
	 * @throws Exception si ocurre un error.
	 */
	public static String getNameSpace(String appName) throws Exception {
		String ret = null;
		String key = appName + "." + NAMESPACE_SUFFIX;
		try {
			ret = properties.getProperty(key);
			if (ret == null) {
				throw new Exception(
					"Variable no encontrada en ApplicationConfig:"
						+ " key=["
						+ key
						+ "]"
						+ " filename=["
						+ CONFIG_FILENAME
						+ "]");
			}
			return ret;
		} catch (Exception e) {
			log.fatal(
				"Error al obtener NameSpace para appName=["
					+ appName
					+ "]: "
					+ e,
				e);
			throw e;
		}
	}
	
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
	
//	static class ApplicationConfigWatchdog extends FileWatchdog {
//		
//		public ApplicationConfigWatchdog(String filename) {
//			super(filename);
//		}
//
//		/* (non-Javadoc)
//		 * @see com.telefonica_chile.atiempo.utiles.FileWatchdog#doOnChange()
//		 */
//		public void doOnChange() {
//			ApplicationConfig.isInicializated = false;
//			ApplicationConfig.init( getFilename() );
//		}
//	}

}
