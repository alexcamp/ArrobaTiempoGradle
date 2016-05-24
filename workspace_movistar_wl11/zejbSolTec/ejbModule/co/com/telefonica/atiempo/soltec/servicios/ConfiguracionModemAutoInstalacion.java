/*
 * Created on Mar 29, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;


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
	public void procesar(Object[] obj, boolean esCierreActuacion) throws ATiempoAppEx {
		
		//RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
		//recursosBADelegate.recibirConfiguracionModemAutoinstalacion((TR135S) obj[0], esCierreActuacion);
		
		ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
		aCSServicioDelegate.recibirConfiguracionModemAutoinstalacion((TR135S) obj[0], false,esCierreActuacion);
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

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

}
