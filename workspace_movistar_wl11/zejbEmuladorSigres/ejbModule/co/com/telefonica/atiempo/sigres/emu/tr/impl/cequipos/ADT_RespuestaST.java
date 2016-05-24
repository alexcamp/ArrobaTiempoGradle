/*
 * Created on May 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;
import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ADT_RespuestaST extends TRResponse_ST {
	public static final String SERVICE_CLASS_NAME =
		"co.com.telefonica.atiempo.soltec.servicios.ConfiguracionServiciosTVSTServicio";

	public ADT_RespuestaST(String message) {
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}
}
