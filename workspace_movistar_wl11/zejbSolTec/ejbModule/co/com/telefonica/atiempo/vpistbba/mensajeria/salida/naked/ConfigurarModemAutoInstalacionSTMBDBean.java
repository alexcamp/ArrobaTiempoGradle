package co.com.telefonica.atiempo.vpistbba.mensajeria.salida.naked;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ConfiguracionModemAutoInstalacion;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: ConfigurarModemAutoInstalacionSTMBD
 */
public class ConfigurarModemAutoInstalacionSTMBDBean extends MDServicioBean {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Apéndice de método generado automáticamente
		return new ConfiguracionModemAutoInstalacion();
	}
	
}