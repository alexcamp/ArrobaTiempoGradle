package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudInformacionTecnicaTVServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudInformacionTecnicaTVMD
 */
public class SolicitudInformacionTecnicaTVMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;
	/**
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new SolicitudInformacionTecnicaTVServicio();
	}
}
