 
package co.com.telefonica.atiempo.vpistbba.servicios;
import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Message-Driven Bean: EnviaPrimeraFacturaInternetEquipadoMDB
 *
 * @ejb.bean
 *	name="EnviaPrimeraFacturaInternetEquipadoMDB"
 *	transaction-type="Container"
 *  destination-type="javax.jms.Queue"
 *  message-selector=""
 *
 */
public class EnviaPrimeraFacturaInternetEquipadoMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new EnviarPrimeraFacturaInternetEquipado();
	}
	
}