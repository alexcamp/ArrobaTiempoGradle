package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.WFMessageSTServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: WFMessageSTIncidencias
 */
public class WFMessageSTIncidenciasBean extends MDServicioBean {
	public IServicio getServicio() {
		return new WFMessageSTServicio();
	}
}