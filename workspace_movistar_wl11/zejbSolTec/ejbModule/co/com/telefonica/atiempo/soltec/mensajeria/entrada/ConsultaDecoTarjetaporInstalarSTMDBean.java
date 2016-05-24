package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ConsultaDecoTarjetaporInstalarSTServicio;

/**
 * Bean implementation class for Enterprise Bean: ConsultaDecoTarjetaporInstalarSTMD
 */
public class ConsultaDecoTarjetaporInstalarSTMDBean
	extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ConsultaDecoTarjetaporInstalarSTServicio();
	}

}
