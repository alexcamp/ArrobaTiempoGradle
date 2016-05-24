/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class STVistaGlobalEmuRespuesta extends TRResponse_ST {
	
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.STVistaGlobalServicio";
	
	public STVistaGlobalEmuRespuesta(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
