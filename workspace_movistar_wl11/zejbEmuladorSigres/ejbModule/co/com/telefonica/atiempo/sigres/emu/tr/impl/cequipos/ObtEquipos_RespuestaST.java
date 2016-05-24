/*
 * Created on Mar 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtEquipos_RespuestaST extends TRResponse_ST{
    public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.SolicitudInformacionTecnicaEquipoServicio";
	
			public ObtEquipos_RespuestaST(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}

}
