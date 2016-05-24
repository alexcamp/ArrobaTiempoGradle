/*
 * Created on 27-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR516E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MarcaLineaDefectuosaSTBGRMQ extends QManagerService {

	
	private static final String QUEUE = "IT_BUS_ACT_EST_TEC_GR_Q";
	private static final String QUEUEDESTINATION = "RT_AT_ACT_EST_TEC_GR_Q";

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */

	public MarcaLineaDefectuosaSTBGRMQ()
		throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO Auto-generated method stub
		
		 if (obj instanceof TR516E)
		 {
			TR516E tr516e = (TR516E) obj;
			tr516e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr516e.getId())));
		 }

		return obj;
	}

}
