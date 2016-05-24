package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAltaPeriodificacionRI
 */
public class AAltaPeriodificacionRIBean extends AAltamiraAbstractAutomaticActivityRIBean {
	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getATiempoServiceName()
	 */
	protected String getATiempoServiceName() {
		return ComunInterfaces.ALT_SERVICIO_ALTA_PERIODIFICACION;
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getAtiempoServiceNameEnREversa()
	 * Se devuelve lo especificado en "ALTAMIRA Entendimiento.doc" - 13/May/2009 - guido
	 */
	protected String getAtiempoServiceNameEnReversa() {
		return ComunInterfaces.ALT_SERVICIO_BAJA_PERIODIFICACION;
	}
}
