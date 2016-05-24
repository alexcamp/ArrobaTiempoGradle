package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarCorreoTeraBoxServicio;
/**
 * Bean implementation class for Enterprise Bean: EnviarCorreoTeraboxMDB
 */
public class EnviarCorreoTeraboxMDBBean extends MDServicioBean {

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
		 */
		public IServicio getServicio() {
			return new EnviarCorreoTeraBoxServicio();
		}
}