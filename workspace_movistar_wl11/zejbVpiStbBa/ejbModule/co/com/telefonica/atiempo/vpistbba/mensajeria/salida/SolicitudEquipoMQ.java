/*
 * Created on Mar 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SolicitudEquipoMQ extends QManagerService {
	private static final String QUEUE = "jms/QMAT/IT_BUS_ALISTAR_EQUIPOS_Q";
	
	public SolicitudEquipoMQ() throws ATiempoAppEx
	{
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj)
	{
		return obj;
	}

}
