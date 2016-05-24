package co.com.telefonica.atiempo.vpistbba.servicios.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaSTBServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfiguracionAutomaticaSTB
 */
public class ConfiguracionAutomaticaSTBBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ConfiguracionAutomaticaSTBServicio();
	}
}
