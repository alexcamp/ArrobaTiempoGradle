package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author cacano
 *  
 */
public class ConfCamaraZTEMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_CAMARA_ZTE_Q";

	public ConfCamaraZTEMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}