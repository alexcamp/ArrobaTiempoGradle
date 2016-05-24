package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionAula365BAServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfiguracionAula365BA
 */
public class ConfiguracionAula365BABean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ConfiguracionAula365BAServicio();
	}
}
