package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.vpistbba.servicioba.ConfigurarSxRxIMSInterface;

/**
 * Local interface for Enterprise Bean: ConfigurarSxRxIMS
 */
public interface ConfigurarSxRxIMSLocal extends ConfigurarSxRxIMSInterface, javax.ejb.EJBLocalObject {

	/**
	 * @param act
	 */
	public void configurarSxRxIMS(ActividadEJBDTO act);


	/**
	 * @param tr606s
	 */
	public void respuestaConfigurarSxRxIMS(TR606S tr606s);
}