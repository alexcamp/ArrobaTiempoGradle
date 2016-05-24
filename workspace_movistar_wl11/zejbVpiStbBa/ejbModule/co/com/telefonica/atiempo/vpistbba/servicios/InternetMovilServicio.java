/*
 * Created on Jun 01, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR610S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InternetMovilServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR610S tr610s= (TR610S) obj[0];
		/*Llamado al Delegate indicado para procesar la respuesta*/
		RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
		recursosBADelegate.procesarRespuestaInternetMovil(tr610s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR610S tr610s = (TR610S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr610s;
		return obj;
	}
	
	


}
