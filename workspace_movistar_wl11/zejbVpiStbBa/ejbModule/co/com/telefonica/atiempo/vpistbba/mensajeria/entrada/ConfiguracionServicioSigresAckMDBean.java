package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresAckServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBAMD
 */
// CR4860 - Sigres - guido - 2/May
public class ConfiguracionServicioSigresAckMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServicioSigresAckServicio();
	}

}
