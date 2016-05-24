package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ActualizacionInventarioSTBGRServicio;

/**
 * Bean implementation class for Enterprise Bean: ActualizacionInventarioSTBGRMD
 */
public class ActualizacionInventarioSTBGRMDBean extends MDServicioBean {

	
	public IServicio getServicio() {
		return new ActualizacionInventarioSTBGRServicio();
	}
	
}
