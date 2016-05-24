package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudActualizacionInventarioBAServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudActualizacionInventarioBAMD
 */
public class SolicitudActualizacionInventarioBAMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	public IServicio getServicio() {
		return new SolicitudActualizacionInventarioBAServicio();
	}
}
