package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR014E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudConfiguracionBAMQ extends QManagerService {
	
	private static final String QUEUE = "IT_BUS_SOLIC_REC_BA_CONS_Q";
	private static final String QUEUEDESTINATION = "RT_AT_SOLIC_REC_BA_CONS2_Q";

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudConfiguracionBAMQ() throws ATiempoAppEx {
		super(QUEUE,QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR014E)
		{
			TR014E tr014e = (TR014E) obj;
			tr014e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr014e.getId())));
		}
		return obj;
		
	}

}
