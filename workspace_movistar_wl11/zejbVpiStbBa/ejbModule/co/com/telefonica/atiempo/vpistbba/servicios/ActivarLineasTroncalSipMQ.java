/*
 * Created on Dec 28, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivarLineasTroncalSipMQ extends QManagerService {
	
	private static final String QUEUE = "jms/QMAT/RT_BUS_TS_ACTIVAR_LINEAS";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public ActivarLineasTroncalSipMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		return obj;
	}
	
}
