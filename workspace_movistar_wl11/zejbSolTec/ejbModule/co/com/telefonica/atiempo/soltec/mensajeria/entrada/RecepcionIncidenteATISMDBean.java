package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.RecepcionIncidenteATISServicio;

/**
 * Bean implementation class for Enterprise Bean: RecepcionIncidenteATISMD
 */
public class RecepcionIncidenteATISMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
		/**
		 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
		 */
		public IServicio getServicio() {
			return new RecepcionIncidenteATISServicio();
		}
}
