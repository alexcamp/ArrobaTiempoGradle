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
public class STConfig {

	private static boolean isInicializated = false;

		/**
		 * Ruta completa del archivo de configuracion.
		 */
		private static final String CONFIG_FILENAME =
			Constants.INSTALL_ETC_PATH + Constants.stConfigName;
		
		//
		// private fields
		//

		/**
		 * Obtencion de logger
		 */
		protected static final Logger log =
			LoggerFactory.getLogger(STConfig.class);

		/**
		 * Properties para acceder a la configuracion.
		 */
		private static Properties properties = null;

		//
		// init code
		//

		static {
			//new STConfigWatchdog(CONFIG_FILENAME).start();
			STConfig.init( CONFIG_FILENAME );
		}

		static synchronized void init(String filename) {
			//if (properties == null) {
			if ( !isInicializated  ) {
				log.info("### Inicializando STConfig ###");
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
					log.info("### STConfig NO inicializado ### [" + t.getMessage() + "]");
				}
				log.info("### STConfig inicializado ###");
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
	
//		static class STConfigWatchdog extends FileWatchdog {
//		
//			public STConfigWatchdog(String filename) {
//				super(filename);
//			}
//
//			/* (non-Javadoc)
//			 * @see com.telefonica_chile.atiempo.utiles.FileWatchdog#doOnChange()
//			 */
//			public void doOnChange() {
//				STConfig.isInicializated = false;
//				STConfig.init( getFilename() );
//			}
//		}

}
