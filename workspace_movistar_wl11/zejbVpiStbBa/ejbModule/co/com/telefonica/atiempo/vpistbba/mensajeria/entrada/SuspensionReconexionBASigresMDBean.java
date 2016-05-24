package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.SuspensionReconexionBASigresServicio;

/**
 * SuspensionReconexionBASigresMDBean
 * 
 * Message Driven Bean para la recepcion de la interfaz TR042 
 * (Suspension - Reconexion).
 * 
 * @author Gonzalo Arreche CR4860.
 *
 */
public class SuspensionReconexionBASigresMDBean extends MDServicioBean{
	
	public IServicio getServicio() {
		return new SuspensionReconexionBASigresServicio();
	}
}
