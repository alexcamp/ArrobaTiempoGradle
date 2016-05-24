//CR4860 SIGRES - GUSTAVOG 02/05/2008

package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.InformarResultadoInstalacionSigresBAServicio;

/**
 * Bean implementation class for Enterprise Bean: InformarResultadoInstalacionSigresBAMD
 */
public class InformarResultadoInstalacionSigresBAMDBean extends MDServicioBean{

	/**
	 * Devuelve el servicio que procesa los mensajes.
	 * @return el servicio	  
	 */
	public IServicio getServicio() {
		return new InformarResultadoInstalacionSigresBAServicio();
	}


	
}
