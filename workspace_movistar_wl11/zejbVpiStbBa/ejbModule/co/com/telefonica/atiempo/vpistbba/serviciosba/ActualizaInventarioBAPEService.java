
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR515S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizaInventarioBAPEService extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
//		Mapeo variables
		TR515S tr515s = (TR515S) obj[0];

		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionActualizaInventarioBAGranite(tr515s);

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR515S tr515s = (TR515S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr515s;
			
		return obj;
	}

}
