
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
/**
 * @author Dcardena
 * 
 * Mensaje: TR__S y TR__E
 */
public class CierreActuacionTOAServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object)
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {

		// Mapeo variables
		TR811S tr811s = (TR811S) obj[0];

		// Invocar Servicio.-
		TOADelegate toa = new TOADelegate();
		toa.recepcionCierreActuacionTOA(tr811s);

	}

	/**
	* @see co.com.telefonica.atiempo.intf.IServicio#convertirMensaje(java.lang.String)
	*/
	public Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		
		Object[] obj = new Object[1];
		TR811S tr811S = (TR811S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr811S;
		
		return obj;
	}

}
