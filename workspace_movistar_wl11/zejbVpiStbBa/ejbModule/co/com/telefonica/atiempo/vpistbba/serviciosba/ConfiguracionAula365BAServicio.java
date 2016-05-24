/*
 * Created on 08-02-2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR023S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

/**
 * @author Victor
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionAula365BAServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	 
	// OJO ACA HAY QUE SUSTITUIR TR023S POR LA TR QUE CORRESPONDA A AULA365 
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR024S tr024s = (TR024S) obj[0];

		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionConfiguracionAula365(tr024s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR024S tr024S = (TR024S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr024S;
		
		return obj;
	}
}
