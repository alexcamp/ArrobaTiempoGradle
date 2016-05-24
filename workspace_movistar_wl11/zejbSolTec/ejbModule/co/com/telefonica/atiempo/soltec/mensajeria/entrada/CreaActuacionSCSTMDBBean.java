package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.CreaActuacionSCST;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: CreaActuacionSCSTMDB
 */
public class CreaActuacionSCSTMDBBean  extends MDServicioBean
	implements
		javax.ejb.MessageDrivenBean,
		javax.jms.MessageListener {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new CreaActuacionSCST();
	} 
}
