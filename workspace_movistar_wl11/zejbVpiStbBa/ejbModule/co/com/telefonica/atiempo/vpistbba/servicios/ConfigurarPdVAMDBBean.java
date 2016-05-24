package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfigurarPdVAMDB
 */
public class ConfigurarPdVAMDBBean
	extends
	co.com.telefonica.atiempo.utiles.MDServicioBean {
	
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfigurarPdVAServicio();
	}
}