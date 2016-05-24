package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

/**
 * @author TCS
 * 
 * Mensaje: TR_514_S
 */
public class SolicitudPuntosRedGraniteSTBServicio extends ServicioBasico {

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
	//Mapeo variables
		TR514S tr514s = (TR514S) obj[0];
		
		RecursosDelegate puntosRedDelegate = new RecursosDelegate();
		puntosRedDelegate.consultaPuntosRedGraniteSTB(tr514s);
		
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO-TCS: MARSHALL MENSAJE SALIDA TR_012_S                                                                                                                                                                     
		Object[] obj = new Object[1];
		TR514S tr514s  = (TR514S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr514s;
		return obj;
	}

}
