package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR056S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

/**
 * Representa el servicio para la configuración de cliente ZTE (Monitoreo
 * remoto)
 * 
 * @author cacano
 *  
 */
public class ConfClienteZTEServicio extends ServicioBasico {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		RecursosDelegate recursos = new RecursosDelegate();
		recursos.respuestaClienteZTE((TR056S) obj[0]);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR056S tr = (TR056S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr;
		return obj;
	}

}