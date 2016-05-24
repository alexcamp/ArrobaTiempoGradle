package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: RefrescarRecursosBATOAMDB
 */
public class RefrescarRecursosBATOAMDBBean
extends MDServicioBean{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Apéndice de método generado automáticamente
		return new RefrescarRecursosBATOAServicio();
	}

}