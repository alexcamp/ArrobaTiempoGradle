/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ci;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CI_respuestaHC extends TRResponse {
	
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioBAServicio";
	
	public CI_respuestaHC(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
	
}
