/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR004E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_018_E
 */
public class ActualizacionInventarioSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_ACT_INV_LB_MOD_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public ActualizacionInventarioSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_004_E
		if (obj instanceof TR004E)
		{
			TR004E tr004e = (TR004E) obj;
			tr004e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr004e.getId()))); 
		}
		return obj;
		
	}

}
