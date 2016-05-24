package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudPuntosRedSTBGRServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudPuntosRedSTBMD
 */
public class SolicitudPuntosRedSTBGRMDBean extends MDServicioBean {

	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new SolicitudPuntosRedSTBGRServicio();
	}

}

