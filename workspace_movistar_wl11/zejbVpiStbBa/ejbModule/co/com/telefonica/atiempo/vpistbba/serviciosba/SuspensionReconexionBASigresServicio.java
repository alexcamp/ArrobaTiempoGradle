/*
 * Created on Jun 2, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR042S;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SuspensionReconexionBASigresServicio extends ServicioBasico{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosBAInterfaces delegate = new RecursosBADelegate();		
		TR042S tr042s = (TR042S) obj[0];		
		delegate.recepcionSuspensionReconexionBASigres(tr042s);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR042S tr042s = (TR042S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr042s;
		return obj;
	}

}
