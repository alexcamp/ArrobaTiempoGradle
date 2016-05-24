package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioBAServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfiguracionServicioBAMDB
 */
public class ConfiguracionServicioBAMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioBAServicio();
	}
	
}
