/*
 * Creado el 27/11/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * Gestiona el envío de un mensaje a la cola parametrizada.
 * 
 * @author cacano
 *  
 */
public class ConfClienteZTEMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_CLIENTE_ZTE_Q";

	public ConfClienteZTEMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj) {
		return obj;
	}

}