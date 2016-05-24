/*
 * Creado el 4/09/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;

import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;


/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class RefrescarRecursosBATOAServicio extends ServicioBasico {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		TR804S tr804s = (TR804S) obj[0];
		ServicioTOASTDelegate toaServicio = new ServicioTOASTDelegate();
		toaServicio.recepcionActualizaInventarioBA(tr804s);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR804S tr804s = (TR804S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr804s;

		return obj;
	}

}
