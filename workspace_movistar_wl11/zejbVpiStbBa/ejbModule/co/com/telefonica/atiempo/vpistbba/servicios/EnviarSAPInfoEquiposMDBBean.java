package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: EnviarSAPInfoEquiposMDB
 */
public class EnviarSAPInfoEquiposMDBBean extends MDServicioBean{
	
	public IServicio getServicio() {
		return new EnviarInfoEquiposMMSAPServicio();
	}
}