package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.soltec.servicios.EquipoSTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTVistaGlobal
 */
public class ASTVistaGlobalBean
	extends
		co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB
	implements
		javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadST de ASTVistaGlobalBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
		try {
			EquipoSTLocal delegateEquipos = new EquipoSTDelegate();			
			delegateEquipos.enviarSolicitudVistaGlobalST(act, act.getNumeroPeticion(),act.getCodigoActividad());			
		} catch (ATiempoAppEx e) {
			log.error("ASTVistaGlobalBean: onInicioActividadST: Error intentando enviar la solicitud de la Vista Global. "+e);
		} 
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}


}