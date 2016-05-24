/*
 * Creado el 14/12/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confZTE;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ActivarCamaraAgendaSCEmuRespuesta extends TRResponse {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ActivarCamaraAgendaSCServicio";

	public ActivarCamaraAgendaSCEmuRespuesta(String message) {
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}