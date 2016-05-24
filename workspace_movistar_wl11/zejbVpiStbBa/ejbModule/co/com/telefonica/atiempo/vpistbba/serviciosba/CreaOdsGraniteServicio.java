
package co.com.telefonica.atiempo.vpistbba.serviciosba;


import co.com.telefonica.atiempo.interfaces.atiempo.TR517S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;


/**
 * @author TCS
 */
public class CreaOdsGraniteServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosInterfaces delegate = new RecursosDelegate();		
		TR517S tr517S = (TR517S) obj[0];		
		delegate.respuestaCreaOdsGranite(tr517S);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_013_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR517S tr517S = (TR517S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr517S;
		return obj;
	}

}
