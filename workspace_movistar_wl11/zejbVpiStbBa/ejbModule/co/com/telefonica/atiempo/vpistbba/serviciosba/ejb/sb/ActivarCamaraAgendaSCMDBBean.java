package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarCamaraAgendaSCServicio;

/**
 * Bean implementation class for Enterprise Bean: ActivarCamaraAgendaSCMDB
 */
public class ActivarCamaraAgendaSCMDBBean extends MDServicioBean {

	public IServicio getServicio() {
		return new ActivarCamaraAgendaSCServicio();
	}

}