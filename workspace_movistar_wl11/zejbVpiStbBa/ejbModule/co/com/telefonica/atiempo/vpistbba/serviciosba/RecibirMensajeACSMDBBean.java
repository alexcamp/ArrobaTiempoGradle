package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.servicios.RecibirMensajeACSServicio;

/**
 * Bean implementation class for Enterprise Bean: RecibirMensajeACSMDB
 */
public class RecibirMensajeACSMDBBean
	extends
		co.com.telefonica.atiempo.utiles.MDServicioBean {	
	
	public IServicio getServicio() {
		return new RecibirMensajeACSServicio();
	}
}
