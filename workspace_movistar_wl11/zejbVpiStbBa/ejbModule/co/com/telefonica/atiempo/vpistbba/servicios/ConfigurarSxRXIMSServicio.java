/*
 * Creado el 10/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.ConfigurarSxRxIMSDelegate;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfigurarSxRXIMSServicio extends ServicioBasico{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
		ConfigurarSxRxIMSDelegate configurarSxRxIMSDelegate = new ConfigurarSxRxIMSDelegate();
		configurarSxRxIMSDelegate.respuestaConfigurarSxRxIMS((TR606S) obj[0]);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR606S tr606s = (TR606S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr606s;

		return obj;
	}

}