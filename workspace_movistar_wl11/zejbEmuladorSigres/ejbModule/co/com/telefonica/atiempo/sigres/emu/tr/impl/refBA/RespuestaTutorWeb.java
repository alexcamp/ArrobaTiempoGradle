/*
 * Created on Dec 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RespuestaTutorWeb extends TRResponse {
	public final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.EnviarTutorWeb";
	
		public RespuestaTutorWeb(String message){
			this.serviceClassName = SERVICE_CLASS_NAME;
			this.setMessage(message);
		}

}
