
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR513S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

/**
 * @author TCS
 * 
 * Mensaje: TR_513_S y TR_513_E
 */
public class ReversaAsignacionRecursoGraniteSTBServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object)
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {

		// Mapeo variables
		TR513S tr513s = (TR513S) obj[0];

		// Invocar Servicio.
		RecursosInterfaces recursos = new RecursosDelegate();
		recursos.reversaAsignarRecursoGraniteSTB(tr513s);

	}

	/**
	* @see co.com.telefonica.atiempo.intf.IServicio#convertirMensaje(java.lang.String)
	*/
	public Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		
		Object[] obj = new Object[1];
		TR513S tr513S = (TR513S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr513S;
		
		return obj;
	}

}
