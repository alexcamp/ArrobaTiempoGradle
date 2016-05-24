package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.CierreActuacionTOASTServicio;

/**
 * Bean implementation class for Enterprise Bean: CierreActuacionTOASTMDB
 */
public class CierreActuacionTOASTMDBBean
extends
co.com.telefonica.atiempo.utiles.MDServicioBean {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Apéndice de método generado automáticamente
		return new CierreActuacionTOASTServicio();
	}

}