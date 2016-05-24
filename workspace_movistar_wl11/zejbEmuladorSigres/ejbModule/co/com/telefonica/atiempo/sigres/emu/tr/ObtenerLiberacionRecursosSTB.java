/*
 * Created on 28/02/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr;

/**
 * @author fmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtenerLiberacionRecursosSTB extends TRResponse {
	
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.vpistbba.servicios.LiberaRecursosSTBServicio";
	
	public ObtenerLiberacionRecursosSTB( String message ){
		this.serviceClassName = SERVICE_CLASS_NAME;
		this.setMessage(message);
	}

}
