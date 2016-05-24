/*
 * Created on Oct 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR512E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActualizacionInventarioSTBGRMQ extends QManagerService{

	private static final String QUEUE = "jms/QMAT/IT_BUS_ACT_INV_LB_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public ActualizacionInventarioSTBGRMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_004_E
		if (obj instanceof TR512E)
		{
			TR512E tr512e = (TR512E) obj;
			tr512e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr512e.getId()))); 
		}
		return obj;
		
	}
}
