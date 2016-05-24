// @annotations-disabled tagSet="ejb"
 
package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Session Bean: ADesinstalarEquipo
 * Req 1060
 * 
 * @author idrincon
 *
 * @ejb.bean
 *	name="ADesinstalarEquipo"
 *	type="Stateless"
 *	local-jndi-name="ejb/co/com/telefonica/atiempo/vpistbba/actividades/df/instalacion/ejb/sb/ADesinstalarEquipoLocalHome"
 *	view-type="local"
 *	transaction-type="Container"
 *
 * @ejb.home
 *	local-class="co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.ADesinstalarEquipoLocalHome"
 *
 * @ejb.interface
 *	local-class="co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb.ADesinstalarEquipoLocal"
 *
 */
public class ADesinstalarEquipoBean 
		extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB{
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
}