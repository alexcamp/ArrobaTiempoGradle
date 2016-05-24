/*
 * Created on 16-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudPuntosRedSTBMQ extends QManagerService {
	
	private static final String QUEUE = "IT_BUS_SOLIS_REC_RED_Q";//"IT.BUS.SOLIS_REC_RED";
	private static final String QUEUEDESTINATION = "RT_AT_SOLIS_REC_RED2_Q";//"RT.AT.SOLIS_REC_RED2";   
	

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudPuntosRedSTBMQ() throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		
//		TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_012_E
		 if (obj instanceof TR012E)
		 {
			 TR012E tr012e = (TR012E) obj;
			 //tr012e.setCity(Utiles.ConversorGuion(filtraInvalidaCharacter(tr012e.getCity())));
			 tr012e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr012e.getId())));
		 }
		 return obj;
	
	}

}
