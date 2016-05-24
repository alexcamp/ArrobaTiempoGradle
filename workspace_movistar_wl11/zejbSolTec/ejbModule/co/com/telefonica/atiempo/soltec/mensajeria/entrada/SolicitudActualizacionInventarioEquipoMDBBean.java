package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudActualizacionInventarioEquipoServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudActualizacionInventarioEquipoMDB
 */
public class SolicitudActualizacionInventarioEquipoMDBBean extends co.com.telefonica.atiempo.utiles.MDServicioBean
implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	public IServicio getServicio() {
		return new SolicitudActualizacionInventarioEquipoServicio();
	}
}
