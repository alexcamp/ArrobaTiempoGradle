/*
 * Created on Jul 1, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR041E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;
import com.telefonica_chile.atiempo.utiles.Utiles;
/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CambioNumeroSigresMQ extends QManagerService{
	
	public CambioNumeroSigresMQ() throws ATiempoAppEx {
			super(QUEUE);
		
	}
	private static final String QUEUE = "jms/QMAT/IT_BUS_MODI_IDEN_BA";

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR041E)
		{
			TR041E tr041e = (TR041E) obj;
			tr041e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr041e.getId())));
		}
		return obj;
	}

}
