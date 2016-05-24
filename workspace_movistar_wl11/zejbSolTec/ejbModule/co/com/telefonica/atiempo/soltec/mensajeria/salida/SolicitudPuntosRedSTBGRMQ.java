
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Utiles;

public class SolicitudPuntosRedSTBGRMQ extends QManagerService {
	
	private static final String QUEUE = "IT_BUS_SOLIS_REC_RED_GR_Q";//"IT.BUS.SOLIS_REC_RED";
	private static final String QUEUEDESTINATION = "RT_AT_SOLIS_REC_RED2_GR_Q";//"RT.AT.SOLIS_REC_RED2";   
	

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudPuntosRedSTBGRMQ() throws ATiempoAppEx {
		super(QUEUE, QUEUEDESTINATION);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		
//		TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_012_E
		 if (obj instanceof TR514E)
		 {
			 TR514E tr514e = (TR514E) obj;
			 //tr012e.setCity(Utiles.ConversorGuion(filtraInvalidaCharacter(tr012e.getCity())));
			 tr514e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr514e.getId())));
		 }
		 return obj;
	
	}

}
