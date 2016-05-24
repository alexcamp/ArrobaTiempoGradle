package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresCambioPlanOkServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBAMD
 */
// CR4860 - Sigres - agonzalez- 16-05-2008
public class ConfiguracionServicioSigresCambioPlanOkMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioSigresCambioPlanOkServicio();
	}

}
