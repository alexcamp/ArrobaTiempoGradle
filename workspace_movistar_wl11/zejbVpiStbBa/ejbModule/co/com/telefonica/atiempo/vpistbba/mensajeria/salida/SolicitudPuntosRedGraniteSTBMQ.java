package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_514_E
 */
public class SolicitudPuntosRedGraniteSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLIS_REC_RED_GR_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public SolicitudPuntosRedGraniteSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_514_E
		if (obj instanceof TR514E)
		{
			TR514E tr514e = (TR514E) obj;
			tr514e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr514e.getId())));
		}
		return obj;
	}

}
