/*
 * Created on 30-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;


import co.com.telefonica.atiempo.interfaces.atiempo.TR028E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;



/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizacionInventarioEquipoMQ extends QManagerService {

	//Aqui cambié de RF a IT
	private static final String QUEUE = "IT_BUS_ACT_INV_EQUIPOS_ST_Q";
	private static final String QUEUEDESTINATION = "IT_BUS_ACT_INV_EQUIPOS_ST_Q";

	
	/**
	 * @param queue
	 * @param queueDestination
	 * @throws ATiempoAppEx
	 */
	public ActualizacionInventarioEquipoMQ()
		throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {		
		if (obj instanceof TR028E)
		{
		    TR028E tr028e = (TR028E) obj;
		}
		return obj;
	}

}
