package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.RecargaFijoMovilServicio;


/**
 * Bean implementation class for Enterprise Bean: ConfiguracionFijoMovilMDB
 */
public class RecargaFijoMovilMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new RecargaFijoMovilServicio();
	}
}