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
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OperationStatusHash {
	protected transient Logger log = LoggerFactory.getLogger (getClass ());

    private static boolean isInicializated = false;
	/**
	 * Ruta completa del archivo de configuracion.
	 */
	private static final String CONFIG_FILENAME ="/home/atiemweb/etc/sigresCodeError.properties";
	
	private static Properties properties = null;
	
	private static OperationStatusHash opStatus = null;
	private OperationStatus porDefecto = new OperationStatus(OperationStatus.STATUS_ERROR_PGI);
	private OperationStatusHash() {	
		 init(CONFIG_FILENAME);
	}	
	
	public static OperationStatusHash getInstance(){
		if(opStatus == null){
			opStatus = new OperationStatusHash();
		}
		return opStatus;
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

	
	private OperationStatus getOpStatus(String trName, String codigoError) {
		if(getVariable(trName + "_" + codigoError) == null){
			log.debug(trName + "_" + codigoError +" = "+ null);
			porDefecto.setMensaje(trName + "_" + codigoError +" no encontrada, se crea operation por defecto con la plataforma de gestion asociada PGI");
			return porDefecto;
		}
		log.debug(trName + "_" + codigoError +" = "+ getVariable(trName + "_" + codigoError));		               	
		return new OperationStatus( Short.parseShort(getVariable(trName + "_" + codigoError)));
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
	


	public OperationStatus getOperationStatus(Object t, String codigoError, String mensajeError) {
		String nombreClase = t.getClass().getName();
		nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
		OperationStatus op = opStatus.getOpStatus(nombreClase , codigoError); //Pablo L
		String prefixBitacora = null;
		if (op.isEspera()) {
			prefixBitacora = "Novedad de avance /"; 
		} else if (op.isError()){
			if(op.isErrorPGI())
				prefixBitacora = "Error, se deriva a PGI /";
			else
				prefixBitacora = "Error, se deriva a PGC /";
		} else if(op.isOk()){
			prefixBitacora = "Operacion Exitosa /";
		}
		
		String msjBitacora = prefixBitacora + " Respuesta:  " + codigoError + " -  " +  mensajeError;
		op.setMensaje(msjBitacora);
		return op;
	}
}

