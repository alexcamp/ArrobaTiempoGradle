package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import co.com.telefonica.atiempo.sigres.emu.tr.*;
import co.com.telefonica.atiempo.sigres.emu.tr.*;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OPRGranite_Respuesta extends TRResponse{
	
	public final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.SolicitudPuntosRedGraniteSTBServicio";
	
	public OPRGranite_Respuesta(String message){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
