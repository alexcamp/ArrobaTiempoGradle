/*
 * Creado el 27/12/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfiguracionAutomaticaEOCMQ extends QManagerService {
	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_AUT_EOC_Q";

	public ConfiguracionAutomaticaEOCMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}
}