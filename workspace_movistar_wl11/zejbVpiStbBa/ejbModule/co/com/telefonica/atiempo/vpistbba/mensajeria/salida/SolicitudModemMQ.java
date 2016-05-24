/*
 * Created on 28-11-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * @author lcaldera
 *
 */
public class SolicitudModemMQ extends QManagerService{

	private static final String QUEUE = "jms/QMAT/IT_BUS_ALISTAR_MODEM_Q";
	
	public SolicitudModemMQ() throws ATiempoAppEx
	{
		super(QUEUE);
	}

	protected Object procesarDatos(Object obj)
	{
		return obj;
	}

}
