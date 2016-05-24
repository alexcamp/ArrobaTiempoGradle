package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author lcaldera
 *
 */
public class SolicitudModemMQ  extends QManagerService
{
	private static final String QUEUE = "IT_BUS_ALISTAR_MODEM_Q";
	private static final String QUEUEDESTINATION = "RT_AT_LISTAR_BA_MODEMS2_Q";
	
	public SolicitudModemMQ() throws ATiempoAppEx
	{
		super(QUEUE, QUEUEDESTINATION);
	}
	
	protected Object procesarDatos(Object obj)
	{
		return obj;
	}

}
