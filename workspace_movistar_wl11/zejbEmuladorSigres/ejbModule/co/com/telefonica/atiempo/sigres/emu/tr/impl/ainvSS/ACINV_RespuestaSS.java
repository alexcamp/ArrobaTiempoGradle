
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvSS;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ACINV_RespuestaSS extends TRResponse{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.ActualizacionInventarioSSServicio";
	
			public ACINV_RespuestaSS(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}
}
