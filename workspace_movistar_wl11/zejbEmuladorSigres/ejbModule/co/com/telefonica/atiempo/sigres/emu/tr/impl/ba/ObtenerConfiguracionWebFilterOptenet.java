/*
 * Creado el 22/11/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ObtenerConfiguracionWebFilterOptenet extends TRResponse {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionWebFilterOptenetServicio";
	
	public ObtenerConfiguracionWebFilterOptenet(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}
