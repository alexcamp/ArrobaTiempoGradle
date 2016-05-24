/*
 * Created on 03-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR002E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LiberacionRecursoSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_LIB_REC_STB_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public LiberacionRecursoSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		if (obj instanceof TR002E)
		{
			TR002E tr002e = (TR002E) obj;
			tr002e.setCni(Utiles.ConversorGuion(filtraInvalidaCharacter(tr002e.getCni())));
			tr002e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr002e.getId())));
		}
		return obj;
	}

}
