/*
 * Created on Jun 3, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR3_Respuesta extends TRResponse {
	public final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoManualSTBServicio";
	
	public TR3_Respuesta(String message){
	   this.serviceClassName = SERVICE_CLASS_NAME;
	   this.setMessage(message);
	}

}
