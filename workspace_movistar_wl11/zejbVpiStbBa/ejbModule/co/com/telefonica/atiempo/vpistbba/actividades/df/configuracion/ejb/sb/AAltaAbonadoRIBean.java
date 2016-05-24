package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAltaAbonadoRI
 */
public class AAltaAbonadoRIBean	extends	AAltamiraAbstractAutomaticActivityRIBean {

	private static final transient Logger log = LoggerFactory.getLogger(AAltaAbonadoRIBean.class);
	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getATiempoServiceName()
	 */
	protected String getATiempoServiceName() {
		return ComunInterfaces.ALT_SERVICIO_ALTA_ABONADO;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AAltamiraAbstractAutomaticActivityRIBean#getAtiempoServiceNameEnREversa()
	 * Se devuelve lo especificado en "ALTAMIRA Entendimiento.doc" - 13/May/2009 - guido
	 */
	protected String getAtiempoServiceNameEnReversa() {
		return ComunInterfaces.ALT_SERVICIO_BAJA_ABONADO;
	}
}
