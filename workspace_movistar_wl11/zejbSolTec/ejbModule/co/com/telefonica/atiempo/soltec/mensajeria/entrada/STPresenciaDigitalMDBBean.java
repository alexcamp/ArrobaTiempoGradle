package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.STNotificacionSAPServicio;
import co.com.telefonica.atiempo.soltec.servicios.STPresenciaDigitalServicio;

/**
 * Bean implementation class for Enterprise Bean: STPresenciaDigitalMDB
 */
public class STPresenciaDigitalMDBBean
extends
co.com.telefonica.atiempo.utiles.MDServicioBean{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new STPresenciaDigitalServicio();
	}
}