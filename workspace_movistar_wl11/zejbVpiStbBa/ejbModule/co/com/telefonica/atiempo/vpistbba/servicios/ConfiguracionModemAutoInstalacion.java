/*
 * Created on Feb 28, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfiguracionModemAutoInstalacion extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	public void procesar(Object[] obj, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx {
		ACSServicioDelegate ACSServicioDelegate = new ACSServicioDelegate();
		ACSServicioDelegate.recibirConfiguracionModemAutoinstalacion((TR135S) obj[0], esBaja, esCierreActuacion);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	public Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR135S tr135s = (TR135S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr135s;

		return obj;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		ACSServicioDelegate ACSServicioDelegate = new ACSServicioDelegate();
		ACSServicioDelegate.recibirAutoConfiguracion((TR135S) obj[0]);
	}

}
