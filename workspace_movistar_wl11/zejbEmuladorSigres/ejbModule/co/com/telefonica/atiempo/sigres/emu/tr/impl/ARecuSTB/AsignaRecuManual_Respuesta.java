/*
 * Created on 18/11/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 808027
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AsignaRecuManual_Respuesta extends TRResponse{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoManualSTBServicioGR";
	//public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.AsignacionRecursoManualSTBServicio";
	
			public AsignaRecuManual_Respuesta(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}

}
