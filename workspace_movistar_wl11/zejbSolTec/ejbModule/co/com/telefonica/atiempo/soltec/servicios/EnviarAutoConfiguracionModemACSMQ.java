/*
 * Created on May 4, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarAutoConfiguracionModemACSMQ extends QManagerService {

	/*
<<<<<<< .mine*/
	private static final String QUEUE = "IT_BUS_AUTOCONFIG_ACS_Q";
	
	//private static final String QUEUE = "IT_BUS_AUTOCONFIG_ACS_ST_Q";
	/*
=======
	
>>>>>>> .r44396
*/
	
	public EnviarAutoConfiguracionModemACSMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		return obj;
	}

}
