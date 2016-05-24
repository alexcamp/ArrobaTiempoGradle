package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfPaqueteMovilMDB
 */
public class ConfPaqueteMovilMDBBean extends
		co.com.telefonica.atiempo.utiles.MDServicioBean {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfPaqueteMovilServicio();
	}
}