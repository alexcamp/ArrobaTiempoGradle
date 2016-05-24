
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RefrescarDatosBA_Respuesta_ST extends TRResponse_ST{
	
	public final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.SolicitudInformacionTecnicaBASigresServicio";
	
		public RefrescarDatosBA_Respuesta_ST(String message){
			this.serviceClassName = SERVICE_CLASS_NAME;
			this.setMessage(message);
		}

}
