/*
 * Created on Jul 1, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import com.telefonica_chile.atiempo.utiles.Utiles;
/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudCuentaCorreoBAMQ extends QManagerService {

	private static final String QUEUE = "IT_BUS_SOL_CUEN_CORR_BA_ST_Q";
	private static final String QUEUEDESTINATION = "RT_AT_SOL_CUEN_CORR_BA_ST_Q";

	
	public SolicitudCuentaCorreoBAMQ() throws ATiempoAppEx {
		super(QUEUE,QUEUEDESTINATION);
	}

	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR043E)
		{
			TR043E tr043e = (TR043E) obj;
			tr043e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr043e.getId())));
		}
		return obj;
	
	}



}
