/*
 * Created on Ene 24, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author mfmendez - Req 4659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConsultarCliPotConstructoraMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CONSTRUC_CLI_POT";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public ConsultarCliPotConstructoraMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		return obj;
	}

}
