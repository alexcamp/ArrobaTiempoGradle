package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresCambioPlanAckServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBAMD
 */
// CR4860 - Sigres - agonzalez - 15-05-2008
public class ConfiguracionServicioSigresCambioPlanAckMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioSigresCambioPlanAckServicio();
	}

}
