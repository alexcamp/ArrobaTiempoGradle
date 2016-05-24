/*
 * Created on Jun 30, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ConfAutEOC;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfiguracionAutomaticaEOCEmuRespuesta extends TRResponse {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaEOCServicio";
	
	public ConfiguracionAutomaticaEOCEmuRespuesta(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}
