package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.CierreActuacionSCST;

/**
 * Bean implementation class for Enterprise Bean: CierreActuacionSCSTMDB
 */
public class CierreActuacionSCSTMDBBean extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements
		javax.ejb.MessageDrivenBean,
		javax.jms.MessageListener {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new CierreActuacionSCST();
	} 
}
