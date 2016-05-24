package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresOkServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBAMD
 */
// CR4860 - Sigres - guido - 7/May
public class ConfiguracionServicioSigresOkMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioSigresOkServicio();
	}

}
