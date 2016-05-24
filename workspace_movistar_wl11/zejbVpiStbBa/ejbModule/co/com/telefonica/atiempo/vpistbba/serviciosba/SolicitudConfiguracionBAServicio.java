/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;


/**
 * @author TCS
 */
public class SolicitudConfiguracionBAServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosBAInterfaces delegate = new RecursosBADelegate();		
		TR014S tr014S = (TR014S) obj[0];		
		delegate.recepcionConfiguracionActualBA(tr014S);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_013_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR014S tr014S = (TR014S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr014S;
		return obj;
	}

}
