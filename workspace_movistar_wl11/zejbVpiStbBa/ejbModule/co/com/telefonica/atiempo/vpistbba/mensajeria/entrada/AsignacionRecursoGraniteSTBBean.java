package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoSTBServicio;

/**
 * Bean implementation class for Enterprise Bean: AsignacionRecursoGraniteSTB
 */
public class AsignacionRecursoGraniteSTBBean
	extends
		co.com.telefonica.atiempo.utiles.MDServicioBean{
	
	public IServicio getServicio() {
		return new AsignacionRecursoSTBServicio();
	}
}
