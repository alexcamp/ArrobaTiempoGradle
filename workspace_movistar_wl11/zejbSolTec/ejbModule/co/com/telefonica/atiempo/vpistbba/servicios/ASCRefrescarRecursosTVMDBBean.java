package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;


/**
 * Bean implementation class for Enterprise Bean: ASCRefrescarRecursosTVMDB
 */
public class ASCRefrescarRecursosTVMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new RefrescarRecursosTVTOAServicio();
	} 

}