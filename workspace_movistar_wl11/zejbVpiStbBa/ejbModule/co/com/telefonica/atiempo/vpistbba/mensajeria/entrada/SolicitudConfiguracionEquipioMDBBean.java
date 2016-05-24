package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ObtenerEquiposServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionEquipioMDB
 */
public class SolicitudConfiguracionEquipioMDBBean extends MDServicioBean {
    public IServicio getServicio() {
		return new ObtenerEquiposServicio();
	}
	
}
