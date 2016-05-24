/*
 * Created on Jun 5, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.sr;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SR_Respuesta extends TRResponse{
	
	public final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.serviciosba.SuspensionReconexionBASigresServicio";
	
		public SR_Respuesta(String message){
			this.serviceClassName = SERVICE_CLASS_NAME;
			this.setMessage(message);
		}

}
