package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

public class SolicitudActualizacionInventarioBAServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		//		Mapeo variables
		 TR007S tr007s = (TR007S) obj[0];
		
		 // Invocar Servicio.
		 RecursosBAInterfaces recursos = new RecursosBADelegate();
		 recursos.recepcionActualizacionInventarioBA(tr007s);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		TR007S tr007s = (TR007S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr007s;
		
		return obj;

	}

}
