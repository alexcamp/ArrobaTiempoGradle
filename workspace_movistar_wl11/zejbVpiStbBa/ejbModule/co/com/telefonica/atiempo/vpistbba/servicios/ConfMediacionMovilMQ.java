/*
 * Creado el 20/06/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfMediacionMovilMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_MEDIACION_MOVIL_Q";

	public ConfMediacionMovilMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}