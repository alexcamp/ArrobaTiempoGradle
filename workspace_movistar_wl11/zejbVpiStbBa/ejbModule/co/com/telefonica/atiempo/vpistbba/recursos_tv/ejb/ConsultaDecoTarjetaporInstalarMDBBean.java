package co.com.telefonica.atiempo.vpistbba.recursos_tv.ejb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaDecoTarjetaporInstalarServicios;

/**
 * Bean implementation class for Enterprise Bean: ConsultaDecoTarjetaporInstalarMDB
 */
public class ConsultaDecoTarjetaporInstalarMDBBean extends MDServicioBean{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConsultaDecoTarjetaporInstalarServicios();
	}
	
}
