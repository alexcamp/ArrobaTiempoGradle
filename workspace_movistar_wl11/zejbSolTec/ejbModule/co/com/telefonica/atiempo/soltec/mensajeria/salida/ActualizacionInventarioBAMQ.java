/*
 * Created on 30-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR007E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizacionInventarioBAMQ extends QManagerService {

	//Aqui cambié de RF a IT
	private static final String QUEUE = "IT_BUS_ACT_INV_BA_MOD_Q";
	private static final String QUEUEDESTINATION = "RT_AT_ACT_INV_BA_MOD2_Q";

	
	/**
	 * @param queue
	 * @param queueDestination
	 * @throws ATiempoAppEx
	 */
	public ActualizacionInventarioBAMQ()
		throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof TR007E)
		{
			TR007E tr007e = (TR007E) obj;
			tr007e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr007e.getId())));
			tr007e.setSerialNumber(Utiles.ConversorGuion(filtraInvalidaCharacter(tr007e.getSerialNumber())));
		}
		return obj;
	}

}
