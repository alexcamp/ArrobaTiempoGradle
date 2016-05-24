package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ConfiguracionServiciosTVSTServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfiguracionServiciosTVSTMD
 */
public class ConfiguracionServiciosTVSTMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfiguracionServiciosTVSTServicio();
	}

}
