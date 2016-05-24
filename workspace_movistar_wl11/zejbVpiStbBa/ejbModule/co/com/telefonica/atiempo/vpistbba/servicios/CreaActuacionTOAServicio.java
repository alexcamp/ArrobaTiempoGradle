
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

/**
 * @author Dcardena
 * 
 * Mensaje: TR__S y TR__E
 */
public class CreaActuacionTOAServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object)
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {

		// Mapeo variables
		TR510S tr510s = (TR510S) obj[0];

		// Invocar Servicio.
		RecursosInterfaces recursos = new RecursosDelegate();
		recursos.asignarRecursoGraniteSTB(tr510s);

	}

	/**
	* @see co.com.telefonica.atiempo.intf.IServicio#convertirMensaje(java.lang.String)
	*/
	public Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		
		Object[] obj = new Object[1];
		TR510S tr510S = (TR510S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr510S;
		
		return obj;
	}

}
