package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: ASCRefrescarRecursosBAMDB
 */
public class ASCRefrescarRecursosBAMDBBean
extends MDServicioBean{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return new RefrescarRecursosBATOAServicio();
	}
}