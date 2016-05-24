package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR516S;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

public class SolicitudMarcaLineaSTBGRServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		//		Mapeo variables
		 TR516S tr516s = (TR516S) obj[0];

		 // Invocar Servicio.
		 RecursosInterfaces recursos = new RecursosDelegate();
		 recursos.recibeMarcaLineaGR(tr516s);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		TR516S tr516s = (TR516S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr516s;
		
		return obj;

	}

}
