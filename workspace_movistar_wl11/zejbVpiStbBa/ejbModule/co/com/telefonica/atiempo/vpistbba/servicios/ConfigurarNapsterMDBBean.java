/*
 * Creado el 20/02/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.intf.IServicio;
/**
 * @author dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 * Bean implementation class for Enterprise Bean: ConfigurarNapsterMDB
 */
public class ConfigurarNapsterMDBBean extends
co.com.telefonica.atiempo.utiles.MDServicioBean{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	
	public IServicio getServicio() {
		return new ConfigurarNapsterServicio();
	}
	
}