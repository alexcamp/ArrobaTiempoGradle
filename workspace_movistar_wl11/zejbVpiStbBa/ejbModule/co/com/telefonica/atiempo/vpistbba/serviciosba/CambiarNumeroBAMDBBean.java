package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * Bean implementation class for Enterprise Bean: CambiarNumeroBAMDB
 */
public class CambiarNumeroBAMDBBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new CambiarNumeroBAService();
	}
	
}
