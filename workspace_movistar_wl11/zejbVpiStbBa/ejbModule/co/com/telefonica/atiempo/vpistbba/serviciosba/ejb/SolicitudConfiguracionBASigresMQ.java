
package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;


public class SolicitudConfiguracionBASigresMQ extends QManagerService {


	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLIC_REC_BA_SIGR_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudConfiguracionBASigresMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR035E)
		{
			TR035E tr035e = (TR035E) obj;
			tr035e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr035e.getId())));
		}
		return obj;
	}

}
