package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.CreaOdsGraniteServicio;


/**
 * Bean implementation class for Enterprise Bean: SolicitudConfiguracionBASigresMD
 */
public class CreaDosGraniteMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new CreaOdsGraniteServicio();
	}

}
