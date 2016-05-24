package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ActivarRecursosSCST;

/**
 * Bean implementation class for Enterprise Bean: ActivarRecursosSCSTMDB
 */
public class ActivarRecursosSCSTMDBBean 
extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements
		javax.ejb.MessageDrivenBean,
		javax.jms.MessageListener {
	
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ActivarRecursosSCST();
	}
	

}
