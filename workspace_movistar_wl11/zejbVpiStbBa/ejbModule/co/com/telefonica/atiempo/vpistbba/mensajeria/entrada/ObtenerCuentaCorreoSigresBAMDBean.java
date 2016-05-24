//CR4860 SIGRES - GUSTAVOG 28/04/2008

package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ObtenerCuentaCorreoSigresBAServicio;

/**
 * Bean implementation class for Enterprise Bean: ObtenerCuentaCorreoSigresBAMD
 */
public class ObtenerCuentaCorreoSigresBAMDBean extends MDServicioBean {

	/**
	 * Devuelve el servicio que procesa los mensajes.
	 */
	public IServicio getServicio() {
		
		return new ObtenerCuentaCorreoSigresBAServicio();
	}



}
