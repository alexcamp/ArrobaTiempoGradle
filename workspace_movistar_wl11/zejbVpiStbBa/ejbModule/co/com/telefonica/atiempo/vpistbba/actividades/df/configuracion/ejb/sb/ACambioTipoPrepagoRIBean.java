package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ACambioTipoPrepagoRI
 */
public class ACambioTipoPrepagoRIBean extends AAltamiraAbstractAutomaticActivityRIBean {
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getATiempoServiceName()
	 */
	protected String getATiempoServiceName() {
		return ComunInterfaces.ALT_SERVICIO_CAMBIO_TIPO_PREPAGO;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getAtiempoServiceNameEnREversa()
	 * Se devuelve lo especificado en "ALTAMIRA Entendimiento.doc" - 13/May/2009 - guido
	 */
	protected String getAtiempoServiceNameEnReversa() {
		// Estaba la duda pero Fernando optó por no hacer nada en reversa, ver comentario AT-2056
		return null; 
	}
}
