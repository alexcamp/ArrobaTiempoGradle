package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.AutoConfiguracionModemACSSTServicio;



/**
 * Bean implementation class for Enterprise Bean: AutoconfiguracionModemACSSTMDB
 */
public class AutoconfiguracionModemACSSTMDBBean
extends co.com.telefonica.atiempo.utiles.MDServicioBean
implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

	public IServicio getServicio() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return new AutoConfiguracionModemACSSTServicio();
	}
}