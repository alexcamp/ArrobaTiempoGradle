/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.marav;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MARAV_Respuesta extends TRResponse_ST{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.SolicitudMarcaLineaSTBServicio";
	
			public MARAV_Respuesta(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}
}
