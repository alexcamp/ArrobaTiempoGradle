
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ci;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author agonz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CICP_Respuesta extends TRResponse{
	
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresCambioPlanOkServicio";
	
	public CICP_Respuesta(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
