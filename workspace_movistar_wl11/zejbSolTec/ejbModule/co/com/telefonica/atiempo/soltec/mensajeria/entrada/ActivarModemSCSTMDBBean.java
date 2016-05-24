 
package co.com.telefonica.atiempo.soltec.mensajeria.entrada;
import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.ActivarModemSC;

/**
 * Bean implementation class for Message-Driven Bean: ActivarModemSCSTMDB
 *
 * @ejb.bean
 *	name="ActivarModemSCSTMDB"
 *	transaction-type="Container"
 *  destination-type="javax.jms.Queue"
 *  message-selector=""
 *
 */
public class ActivarModemSCSTMDBBean extends co.com.telefonica.atiempo.utiles.MDServicioBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

	public IServicio getServicio() {
		//TODO Auto-generated method stub
		return new ActivarModemSC();
	}

}