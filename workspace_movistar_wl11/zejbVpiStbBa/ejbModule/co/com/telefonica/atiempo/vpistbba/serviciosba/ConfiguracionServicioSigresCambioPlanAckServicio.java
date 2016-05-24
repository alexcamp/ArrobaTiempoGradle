
package co.com.telefonica.atiempo.vpistbba.serviciosba;


import co.com.telefonica.atiempo.interfaces.atiempo.TR039S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;


//CR4860 - Sigres -inicio- agonzalez- 15-05-2008 
public class ConfiguracionServicioSigresCambioPlanAckServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR039S tr039s = (TR039S) obj[0];
		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionConfiguracionSigresCambioPlanBAAck(tr039s);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL TR_039_S (CREAR DTO)

		Object[] obj = new Object[1];
		TR039S tr039S = (TR039S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr039S;
		return obj;
	}

}