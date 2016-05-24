/*
 * Created on 2-May-08
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR031S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;


//CR4860 - Sigres - guido - 2/May
public class ConfiguracionServicioSigresAckServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR031S tr031s = (TR031S) obj[0];
		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionConfiguracionSigresBAAck(tr031s);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_013_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR031S tr031S = (TR031S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr031S;
		return obj;
	}

}
