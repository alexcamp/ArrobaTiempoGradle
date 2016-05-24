/*
 * Created on 18-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR019E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudInformacionTecnicaTVMQ extends QManagerService {
	
	private static final String QUEUE = "IT_BUS_GEST_CONF_ACTUAL_Q";
	private static final String QUEUEDESTINATION = "RT_AT_GEST_CONF_ACTUAL2_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudInformacionTecnicaTVMQ() throws ATiempoAppEx {
		super(QUEUE,QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		
		if (obj instanceof TR019E){
			
			TR019E tr019e = (TR019E) obj;
			tr019e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr019e.getId())));
			tr019e.setPcId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr019e.getPcId())));
		 }
		 	return obj;
	}
	

}
