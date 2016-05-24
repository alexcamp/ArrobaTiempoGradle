package com.telefonica_chile.bandeja.ejbutiles;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * enclosing_type
 */
public class Boletero {
	private static Boletero instance = null;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public static Boletero getInstance() {
		if (instance == null) {
			instance = new Boletero();
		}
		return instance;
	}

	public synchronized Long obtenerTicket(String nombre) {

		//log.debug("Obteniendo secuencia para " + nombre);

//		try {
//			UtilesLocalHome utilesLocalHome = (UtilesLocalHome) HomeFactory.getHome(UtilesLocalHome.JNDI_NAME);
//			UtilesLocal utiles = utilesLocalHome.create();
//			return utiles.obtenerSecuencia(nombre);
//		} catch (NamingException e) {
//			log.error("Problemas con home secuencia: " + SecuenciaEntityLocalHome.JNDI_NAME,	e);
//			return null;
//		} catch (CreateException e) {
//			log.error("Problemas manipulando secuencia", e);
//			return null;
//		}
		return null;
	}
}
