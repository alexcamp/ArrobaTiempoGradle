package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.serviciosba.BajaBASigresServicio;
import co.com.telefonica.atiempo.vpistbba.serviciosba.SuspensionReconexionBASigresServicio;


public class BajaBASigresMDBean extends MDServicioBean{
	
	public IServicio getServicio() {
		return new BajaBASigresServicio();
	}
}

