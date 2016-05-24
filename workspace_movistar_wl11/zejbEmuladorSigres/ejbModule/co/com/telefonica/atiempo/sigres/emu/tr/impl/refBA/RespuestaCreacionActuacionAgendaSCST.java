/*
 * Creado el 21/09/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;



/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class RespuestaCreacionActuacionAgendaSCST extends TRResponse_ST {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.CreaActuacionSCST";
	
	public RespuestaCreacionActuacionAgendaSCST(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
