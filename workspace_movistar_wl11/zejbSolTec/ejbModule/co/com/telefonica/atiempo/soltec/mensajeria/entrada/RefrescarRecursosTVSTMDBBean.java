package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.vpistbba.servicios.RefrescarRecursosTVServicio;

/**
 * Bean implementation class for Enterprise Bean: RefrescarRecursosTVSTMDB
 */
public class RefrescarRecursosTVSTMDBBean extends co.com.telefonica.atiempo.utiles.MDServicioBean {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return new RefrescarRecursosTVServicio();
	}

}