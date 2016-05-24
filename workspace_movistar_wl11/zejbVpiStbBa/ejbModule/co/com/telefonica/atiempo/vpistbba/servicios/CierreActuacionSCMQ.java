/*
 * Created on Aug 31, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CierreActuacionSCMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/RT_BUS_CIERRE_ACT_SC_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public CierreActuacionSCMQ(String queue) throws ATiempoAppEx {
		super(queue);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO Auto-generated method stub
		return obj;
	}

}
