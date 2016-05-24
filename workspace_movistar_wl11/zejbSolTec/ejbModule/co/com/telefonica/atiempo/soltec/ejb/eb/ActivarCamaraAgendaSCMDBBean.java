package co.com.telefonica.atiempo.soltec.ejb.eb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.soltec.servicios.ActivarCamaraAgendaSCServicio;

/**
 * Bean implementation class for Enterprise Bean: ActivarCamaraAgendaSCMDB
 */
public class ActivarCamaraAgendaSCMDBBean extends MDServicioBean {

	public IServicio getServicio() {
		return new ActivarCamaraAgendaSCServicio();
	}

}