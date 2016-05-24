package co.com.telefonica.atiempo.vpistbba.serviciosba;

import com.telefonica_chile.atiempo.utiles.Utiles;

import co.com.telefonica.atiempo.interfaces.atiempo.TR036E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BajaBASigresMQ extends QManagerService{
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLI_BAJA_BA_Q";

	public BajaBASigresMQ() throws ATiempoAppEx {
			super(QUEUE);
		
		}
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR036E)
		{
			TR036E tr036e = (TR036E) obj;
			tr036e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr036e.getId())));
		}
		return obj;
	}

}
