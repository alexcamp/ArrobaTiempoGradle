/*
 * Creado el 17/09/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;




/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CreaActuacionSCSTMQ extends QManagerService{
	private static final String QUEUE = "IT_BUS_CREA_ACT_SC_ST_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public CreaActuacionSCSTMQ() throws ATiempoAppEx {
		super(QUEUE);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		return obj;
	}

}
