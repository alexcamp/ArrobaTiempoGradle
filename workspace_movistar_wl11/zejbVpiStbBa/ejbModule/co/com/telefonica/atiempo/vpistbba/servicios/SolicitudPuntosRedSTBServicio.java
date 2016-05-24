/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

/**
 * @author TCS
 * 
 * Mensaje: TR_012_S
 */
public class SolicitudPuntosRedSTBServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
	//Mapeo variables
		TR012S tr012s = (TR012S) obj[0];
		
		RecursosDelegate puntosRedDelegate = new RecursosDelegate();
		puntosRedDelegate.consultaPuntosRedSTB(tr012s);
		
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL MENSAJE SALIDA TR_012_S                                                                                                                                                                     
		Object[] obj = new Object[1];
		TR012S tr012s  = (TR012S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr012s;
		return obj;
	}

}
