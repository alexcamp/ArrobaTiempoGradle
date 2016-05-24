package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: ActualizacionInventarioBAMDB
 */
public class ActualizacionInventarioBAMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ActualizaInventarioBAService();
	}
	
}
