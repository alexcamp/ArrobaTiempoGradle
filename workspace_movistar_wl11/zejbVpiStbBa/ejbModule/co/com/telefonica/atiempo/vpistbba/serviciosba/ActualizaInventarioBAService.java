/*
 * Created on 05-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizaInventarioBAService extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
//		Mapeo variables
		TR007S tr007s = (TR007S) obj[0];
		
		boolean esAgendaSC = false;
		
		RecursosBAInterfaces recursosBA = new RecursosBADelegate();
		esAgendaSC = recursosBA.esAgendaSC(new Long(tr007s.getAtisRequestNumber()));

		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionActualizaInventarioBA(tr007s, esAgendaSC);

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR007S tr007s = (TR007S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr007s;
			
		return obj;
	}

}
