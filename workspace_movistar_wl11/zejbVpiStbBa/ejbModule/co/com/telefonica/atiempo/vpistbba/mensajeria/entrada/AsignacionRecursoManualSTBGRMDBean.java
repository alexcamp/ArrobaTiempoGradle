package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoManualSTBServicioGR;

/**
 * Bean implementation class for Enterprise Bean: AsignacionRecursoManualSTBGRMD
 */
public class AsignacionRecursoManualSTBGRMDBean extends MDServicioBean {
	
	public IServicio getServicio() {
		return new AsignacionRecursoManualSTBServicioGR();
	}
}
