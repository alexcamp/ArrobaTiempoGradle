/*
 * Created on Jul 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


// Creada por GMarcone
public class FamilyTabHash {
	protected transient Logger log = LoggerFactory.getLogger (getClass ());

    private static boolean isInicializated = false;
	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String CONFIG_FILENAME ="/home/atiemweb/etc/umtsFamilyTab.properties";
	
	private static Properties properties = null;
	
	private static FamilyTabHash familyTab = null;
	
	private FamilyTabHash() {	
		 init(CONFIG_FILENAME);
	}	
	
	public static FamilyTabHash getInstance(){
		if(familyTab == null){
			familyTab = new FamilyTabHash();
		}
		return familyTab;
	}
	
	synchronized void init(String filename) {		
		if ( !isInicializated  ) {			
			try {
				properties = new Properties();
				log.debug("Cargando el archivo "+CONFIG_FILENAME+" ...");
				FileInputStream fis = new FileInputStream(filename);
				properties.load(fis);				
			} catch (Throwable t) {
				
			}
			
		}
		
		isInicializated = true;
	}
	
	public static String getVariable(String key) {
		String ret = null;
		if ( properties == null )
			return null;
		try {
			ret = properties.getProperty(key);
			return ret;
		} catch (Exception e) {
			
		}
		return ret;
	}

}

