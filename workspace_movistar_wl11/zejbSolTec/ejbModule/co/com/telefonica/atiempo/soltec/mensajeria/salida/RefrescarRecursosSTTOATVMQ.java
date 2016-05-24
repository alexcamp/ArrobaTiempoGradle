/*
 * Created on Mar 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefrescarRecursosSTTOATVMQ extends QManagerService {
	private static final String QUEUE = "IT_BUS_REFRESH_TV_TOA_ST_Q";
	
	public RefrescarRecursosSTTOATVMQ() throws ATiempoAppEx
	{
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj)
	{
		return obj;
	}

}
