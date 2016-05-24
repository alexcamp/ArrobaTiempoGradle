/*
 * Created on 04-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR007E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizaInventarioBAMQ extends QManagerService {
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_ACT_INV_BA_MOD_Q";

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public ActualizaInventarioBAMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		if (obj instanceof TR007E)
		{
			TR007E tr007e = (TR007E) obj;
			tr007e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr007e.getId())));
		}
		return obj;
	}

}
