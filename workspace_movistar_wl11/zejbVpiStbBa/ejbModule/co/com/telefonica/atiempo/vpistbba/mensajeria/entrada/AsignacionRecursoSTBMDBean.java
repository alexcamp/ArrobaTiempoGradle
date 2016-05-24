package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoSTBServicio;

/**
 * Bean implementation class for Enterprise Bean: AsignacionRecursoSTBMD
 */
public class AsignacionRecursoSTBMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new AsignacionRecursoSTBServicio();
	}

}
