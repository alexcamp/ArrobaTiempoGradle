package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

public class SolicitudActualizacionInventarioTVServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
		//		Mapeo variables
		 TR018S tr018s = (TR018S) obj[0];

		 // Invocar Servicio.
		 RecursosTVInterfaces recursos = new RecursosTVDelegate();
		 recursos.recibeActualizacionInventarioTV(tr018s);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		
		TR018S tr018s = (TR018S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr018s;
		
		return obj;

	}

}
