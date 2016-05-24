
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR515E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizaInventarioBAPEMQ extends QManagerService {
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_ACT_INV_BA_PE_Q";

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public ActualizaInventarioBAPEMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		if (obj instanceof TR515E)
		{
			TR515E tr515e = (TR515E) obj;
			tr515e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr515e.getId())));
		}
		return obj;
	}

}
