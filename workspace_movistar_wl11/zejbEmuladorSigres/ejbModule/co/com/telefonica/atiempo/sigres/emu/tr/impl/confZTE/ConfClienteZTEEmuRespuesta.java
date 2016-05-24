package co.com.telefonica.atiempo.sigres.emu.tr.impl.confZTE;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author cacano
 *  
 */
public class ConfClienteZTEEmuRespuesta extends TRResponse {
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ConfClienteZTEServicio";

	public ConfClienteZTEEmuRespuesta(String message) {
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}