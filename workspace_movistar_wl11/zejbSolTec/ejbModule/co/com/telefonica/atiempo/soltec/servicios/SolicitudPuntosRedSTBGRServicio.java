/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author TCS
 * 
 * Mensaje: TR_012_S
 */
public class SolicitudPuntosRedSTBGRServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
	//Mapeo variables
		
		TR514S tr514s = (TR514S) obj[0];
		
		RecursosDelegate puntosRedDelegate = new RecursosDelegate();
		puntosRedDelegate.consultaPuntosRedSTBGR(tr514s);
		
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {		 
		Object[] obj = new Object[1];
		TR514S tr514s  = (TR514S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr514s;
		return obj;
	}

}