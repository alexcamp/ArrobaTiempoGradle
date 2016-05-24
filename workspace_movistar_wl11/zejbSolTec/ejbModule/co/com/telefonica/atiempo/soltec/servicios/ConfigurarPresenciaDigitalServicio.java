/*
 * Created on Jun 01, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigurarPresenciaDigitalServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR054S tr054s = (TR054S) obj[0];
		/*Llamado al Delegate indicado para procesar la respuesta*/
		RecursosDelegate delegate = new co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate();
		delegate.procesarRespuestaConfiguracionPresenciaDigital(tr054s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR054S tr054s = (TR054S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr054s;
		return obj;
	}
	
	


}
