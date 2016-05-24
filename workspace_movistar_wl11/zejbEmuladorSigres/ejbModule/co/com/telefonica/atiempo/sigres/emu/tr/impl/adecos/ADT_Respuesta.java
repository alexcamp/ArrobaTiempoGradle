/*
 * Created on May 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ADT_Respuesta extends TRResponse {
	public static final String SERVICE_CLASS_NAME =
		"co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionServiciosTVSevicio";

	public ADT_Respuesta(String message) {
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}
