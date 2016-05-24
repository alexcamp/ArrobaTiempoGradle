package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author cacano
 *  
 */
public class ActivarCamaraAgendaSCMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_ACT_CAMARA_AGENDASC_Q";

	public ActivarCamaraAgendaSCMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}