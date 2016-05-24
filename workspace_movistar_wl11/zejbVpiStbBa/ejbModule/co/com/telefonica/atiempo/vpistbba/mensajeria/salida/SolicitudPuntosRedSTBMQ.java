/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_012_E
 */
public class SolicitudPuntosRedSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLIS_REC_RED_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public SolicitudPuntosRedSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_012_E
		if (obj instanceof TR012E)
		{
			TR012E tr012e = (TR012E) obj;
			//tr012e.setCity(Utiles.ConversorGuion(filtraInvalidaCharacter(tr012e.getCity())));
			tr012e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr012e.getId())));
		}
		return obj;
	}

}
