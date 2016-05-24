package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresNovedadServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBAMD
 */
// CR4860 - Sigres - guido - 12/May
public class ConfiguracionServicioSigresNovedadMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioSigresNovedadServicio();
	}

}
