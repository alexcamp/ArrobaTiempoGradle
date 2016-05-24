package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author cacano
 *  
 */
public class ConfCamaraZTEMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_CONF_CAMARA_ZTE_ST_Q";

	public ConfCamaraZTEMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}