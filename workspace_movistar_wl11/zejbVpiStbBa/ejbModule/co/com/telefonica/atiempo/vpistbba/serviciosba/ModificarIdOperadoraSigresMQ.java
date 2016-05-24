package co.com.telefonica.atiempo.vpistbba.serviciosba;

import com.telefonica_chile.atiempo.utiles.Utiles;

import co.com.telefonica.atiempo.interfaces.atiempo.TR041E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ModificarIdOperadoraSigresMQ extends QManagerService{
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_MODI_IDEN_BA_Q"; 

	public ModificarIdOperadoraSigresMQ() throws ATiempoAppEx {
			super(QUEUE);
		
		}
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR041E)
		{
			TR041E tr041e = (TR041E) obj;
			tr041e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr041e.getId())));
		}
		return obj;
	}

}
