package co.com.telefonica.atiempo.vpistbba.servicios.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfCamaraZTEServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfCamaraZTEMDB
 */
public class ConfCamaraZTEMDBBean extends
		co.com.telefonica.atiempo.utiles.MDServicioBean {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfCamaraZTEServicio();
	}

}