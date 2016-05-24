
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author agonzalez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
// CR4860 - Sigres - agonzalez - 26/05
public class ConfiguracionServicioSigresCambioPlanMQ extends QManagerService {
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_RESE_MODI_BA_Q";
	
	public ConfiguracionServicioSigresCambioPlanMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}
}
