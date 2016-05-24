/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author guido
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
// CR4860 - Sigres - guido - 29/Abr
public class ConfiguracionServicioSigresBAMQ extends QManagerService {
	
	//private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_RECU_BA_Q";
	private static final String QUEUE = "jms/QMAT/IT_BUS_RESE_RECU_BA_Q";
	
	public ConfiguracionServicioSigresBAMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}
}
