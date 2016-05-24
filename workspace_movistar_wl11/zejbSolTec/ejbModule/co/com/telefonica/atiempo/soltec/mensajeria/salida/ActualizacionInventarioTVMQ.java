/*
 * Created on 30-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR018E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizacionInventarioTVMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_GESTIONAR_ACT_INV_DTH_Q";
		private static final String QUEUEDESTINATION = "RT_AT_GESTIONAR_ACT_INV_DTH2_Q";

	/**
	 * @param queue
	 * @param queueDestination
	 * @throws ATiempoAppEx
	 */
	public ActualizacionInventarioTVMQ()
		throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR018E){
			
			TR018E tr018e = (TR018E) obj;
			tr018e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr018e.getId())));
			tr018e.setPcId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr018e.getPcId())));
		 }
			return obj;
	}

}
