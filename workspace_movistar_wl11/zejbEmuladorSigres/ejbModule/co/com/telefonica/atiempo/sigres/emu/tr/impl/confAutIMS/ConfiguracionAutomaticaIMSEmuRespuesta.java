/*
 * Creado el 24/09/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutIMS;


import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfiguracionAutomaticaIMSEmuRespuesta extends TRResponse{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaIMSServicio";
	
			public ConfiguracionAutomaticaIMSEmuRespuesta(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}
}
