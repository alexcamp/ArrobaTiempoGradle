package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudActualizacionInventarioTVServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudActualizacionInventarioTVMD
 */
public class SolicitudActualizacionInventarioTVMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

		public IServicio getServicio() {
			return new SolicitudActualizacionInventarioTVServicio();
		}
}
