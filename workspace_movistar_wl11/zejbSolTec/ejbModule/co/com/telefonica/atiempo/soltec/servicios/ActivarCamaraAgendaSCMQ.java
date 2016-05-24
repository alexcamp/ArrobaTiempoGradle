package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author cacano
 *
 */
public class ActivarCamaraAgendaSCMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_ACT_CAMARA_AGENDASC_ST_Q";

	public ActivarCamaraAgendaSCMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}
