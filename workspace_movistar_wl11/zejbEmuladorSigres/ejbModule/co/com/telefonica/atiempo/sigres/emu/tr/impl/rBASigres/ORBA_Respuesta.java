/*
 * Created on Jul 3, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.rBASigres;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ORBA_Respuesta extends TRResponse{
	public static final String SERVICE_CLASS_NAME =
				"co.com.telefonica.atiempo.soltec.servicios.SolicitudInformacionTecnicaBASigresServicio";

		public ORBA_Respuesta(String message) {
			this.serviceClassName = SERVICE_CLASS_NAME;
			this.setMessage(message);
		}
}
