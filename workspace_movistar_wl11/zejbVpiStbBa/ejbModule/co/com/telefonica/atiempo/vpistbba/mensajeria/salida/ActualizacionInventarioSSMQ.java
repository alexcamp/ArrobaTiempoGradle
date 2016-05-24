/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR011E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_011_E
 */
public class ActualizacionInventarioSSMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_ACTUA_INV_SS_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public ActualizacionInventarioSSMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_011_E
		if (obj instanceof TR011E)
		{
			TR011E tr011e = (TR011E) obj;
			tr011e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr011e.getId())));
		}
		return obj;
	}

}
