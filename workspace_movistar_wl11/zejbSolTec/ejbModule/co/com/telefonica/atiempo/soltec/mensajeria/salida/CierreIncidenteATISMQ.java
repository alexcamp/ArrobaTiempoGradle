/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author TCS
 * 
 * Mensaje: TR_001_E
 */
public class CierreIncidenteATISMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_CIERRE_INCI_RESP_Q";
//	private static final String QUEUEDESTINATION = "IF_AT_CIERRE_INCI_RESP_Q";  
//Para cerrar no debo enviar una cola de reply
	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public CierreIncidenteATISMQ() throws ATiempoAppEx {
		super(QUEUE);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_001_E
		return obj;
	}

}
