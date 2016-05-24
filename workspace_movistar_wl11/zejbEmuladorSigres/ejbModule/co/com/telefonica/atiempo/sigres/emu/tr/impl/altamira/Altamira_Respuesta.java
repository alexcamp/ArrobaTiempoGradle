/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Altamira_Respuesta extends TRResponse{
	
	// Nombre del servicio de ATiempo que recibe la respuesta del broker (en nuestro caso procesa el mensaje TR601S)
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionServiciosAltamiraSevicio";
	
	public Altamira_Respuesta(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}
