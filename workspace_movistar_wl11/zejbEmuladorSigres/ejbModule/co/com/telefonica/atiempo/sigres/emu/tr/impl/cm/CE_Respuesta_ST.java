/*
 * Created on Mar 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cm;
import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CE_Respuesta_ST extends TRResponse_ST{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.ConsultaEquipoServicio";
	
			public CE_Respuesta_ST(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}
}
