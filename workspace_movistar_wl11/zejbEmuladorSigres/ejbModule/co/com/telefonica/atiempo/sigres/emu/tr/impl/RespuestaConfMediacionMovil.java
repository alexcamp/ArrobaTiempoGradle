package co.com.telefonica.atiempo.sigres.emu.tr.impl;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author cacano
 * 
 * Permite emular la respuesta para la tR de Configuración Mediación Móvil
 */
public class RespuestaConfMediacionMovil extends TRResponse {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfMediacionMovilServicio";

	public RespuestaConfMediacionMovil(String message) {
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}