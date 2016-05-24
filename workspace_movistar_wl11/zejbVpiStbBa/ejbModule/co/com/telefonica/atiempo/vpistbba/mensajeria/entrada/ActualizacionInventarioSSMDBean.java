package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ActualizacionInventarioSSServicio;

/**
 * Bean implementation class for Enterprise Bean: ActualizacionInventarioSSMD
 */
public class ActualizacionInventarioSSMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ActualizacionInventarioSSServicio();
	}

}
