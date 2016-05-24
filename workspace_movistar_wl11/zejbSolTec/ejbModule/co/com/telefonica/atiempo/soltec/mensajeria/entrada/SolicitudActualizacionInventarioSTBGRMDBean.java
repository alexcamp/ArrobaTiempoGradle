package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudMarcaLineaSTBGRServicio;


/**
 * Bean implementation class for Enterprise Bean: SolicitudActualizacionInventarioSTBMD
 */
public class SolicitudActualizacionInventarioSTBGRMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

		public IServicio getServicio() {
			return new SolicitudMarcaLineaSTBGRServicio();
		}
}
