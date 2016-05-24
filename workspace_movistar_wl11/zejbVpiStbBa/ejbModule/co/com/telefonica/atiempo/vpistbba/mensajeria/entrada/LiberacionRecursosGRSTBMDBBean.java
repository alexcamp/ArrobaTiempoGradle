package co.com.telefonica.atiempo.vpistbba.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.LiberaRecursosSTBServicioGR;


public class LiberacionRecursosGRSTBMDBBean extends MDServicioBean {

	public IServicio getServicio() {
		return new LiberaRecursosSTBServicioGR();
	}
	
}
