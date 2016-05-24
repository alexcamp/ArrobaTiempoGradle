package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.Stateless;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.CierreActuacionTOAServicio;

/**
 * Bean implementation class for Enterprise Bean: CierreActuacionTOAMDB
 */
public class CierreActuacionTOAMDBBean extends MDServicioBean {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return new CierreActuacionTOAServicio();
	}
	
}