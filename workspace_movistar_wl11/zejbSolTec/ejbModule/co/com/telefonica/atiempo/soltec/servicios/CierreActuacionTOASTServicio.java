package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CierreActuacionTOASTServicio extends ServicioBasico {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		ServicioTOASTDelegate toa =new ServicioTOASTDelegate();
//		 Mapeo variables
		TR811S tr811s = (TR811S) obj[0];
		toa.recepcionCierreActuacionTOA(tr811s);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR811S tr811s = (TR811S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr811s;
		return obj;
	}

}