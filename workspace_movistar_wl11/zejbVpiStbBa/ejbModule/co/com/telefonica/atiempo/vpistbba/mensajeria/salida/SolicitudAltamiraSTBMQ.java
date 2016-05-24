/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author guido
 * Solicita invocacíon a servicios de Altmaira (alta bonado, recarga, bajas, etc)
 * El servicio a invocar se envia en la TR
 * Mensaje: TR_601_E
 */
public class SolicitudAltamiraSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLIC_ALTAMIRA_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public SolicitudAltamiraSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		return obj;
	}

}
