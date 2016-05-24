package co.com.telefonica.atiempo.soltec.servicios;


import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

public class SolicitudActualizacionInventarioEquipoServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
		//		Mapeo variables
		 TR028S tr028s = (TR028S) obj[0];
		
		 // Invocar Servicio.
		 EquipoSTDelegate eDelegate = new EquipoSTDelegate();
		 eDelegate.recepcionActualizacionInventarioEquipoST(tr028s);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {	
		
	    TR028S tr028s = (TR028S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr028s;
		
		return obj;

	}

}
