package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;

/**
 * Bean implementation class for Enterprise Bean: ConfClienteZTE
 */
public class ConfClienteZTEMDBBean extends MDServicioBean {

	/*
	 * Retorna el servicio
	 * 
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new ConfClienteZTEServicio();
	}

}