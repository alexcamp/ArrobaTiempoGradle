/*
 * Created on Jan 17, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtenerEnvioCorreoTeraBox extends TRResponse {
	
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.EnviarCorreoTeraBoxServicio";
	
	public ObtenerEnvioCorreoTeraBox( String message ){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
