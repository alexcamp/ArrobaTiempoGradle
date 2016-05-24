package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarModemSC;

/**
 * Bean implementation class for Enterprise Bean: ActivarModemSCMDB
 */
public class ActivarModemSCMDBBean extends MDServicioBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		// TODO Auto-generated method stub
		return new ActivarModemSC();
	} 
	
}