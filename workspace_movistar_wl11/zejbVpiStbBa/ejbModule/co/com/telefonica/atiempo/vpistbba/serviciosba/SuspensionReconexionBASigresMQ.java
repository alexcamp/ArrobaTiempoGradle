/*
 * Created on Jun 5, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import com.telefonica_chile.atiempo.utiles.Utiles;

import co.com.telefonica.atiempo.interfaces.atiempo.TR042E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SuspensionReconexionBASigresMQ extends QManagerService{
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_SOLI_BLOQ_DESB_BA";
 

	public SuspensionReconexionBASigresMQ() throws ATiempoAppEx {
			super(QUEUE);
		
		}
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR042E)
		{
			TR042E tr042e = (TR042E) obj;
			tr042e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr042e.getId())));
		}
		return obj;
	}

}
