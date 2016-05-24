package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.RecepcionPeticionATISServicio;

/**
 * Bean implementation class for Enterprise Bean: RecepcionPeticionATISMD
 */

//TODO: Para el control de rollback, es necesario configurar la colas para que desvíe los mensajes
//      de error a otra cola(cola de mensajes procesados que causan error y rollback) y no intente procesarlos nuevamente.
//		Los parametros a configurar en la cola son: Cola de restitucion y Umbral de restitucion en las propiedades de almacenamiento de la cola.
public class RecepcionPeticionATISMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new RecepcionPeticionATISServicio();
	}

}
