package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionTerraBAServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfiguracionTerraBA
 */
public class ConfiguracionTerraBABean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ConfiguracionTerraBAServicio();
	}
}
