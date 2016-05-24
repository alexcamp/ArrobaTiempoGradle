/*
 * Created on Jun 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import com.telefonica_chile.atiempo.utiles.Utiles;

import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudConfiguracionBASigresMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_SOLIC_REC_BA_SIGR_ST_Q";
	private static final String QUEUEDESTINATION = "RT_AT_SOLIC_REC_BA_SIGR_ST_Q";
													
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudConfiguracionBASigresMQ() throws ATiempoAppEx {
		super(QUEUE,QUEUEDESTINATION);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR035E)
		{
			TR035E tr035e = (TR035E) obj;
			tr035e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr035e.getId())));
		}
		return obj;
	
	}







}
