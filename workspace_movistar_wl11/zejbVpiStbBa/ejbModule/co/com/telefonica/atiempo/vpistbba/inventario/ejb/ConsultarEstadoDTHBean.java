package co.com.telefonica.atiempo.vpistbba.inventario.ejb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultarEstadoDTHServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultarEstadoDTH
 */
public class ConsultarEstadoDTHBean
extends MDServicioBean
{
	public IServicio getServicio() {
		return new ConsultarEstadoDTHServicio();
		}
}