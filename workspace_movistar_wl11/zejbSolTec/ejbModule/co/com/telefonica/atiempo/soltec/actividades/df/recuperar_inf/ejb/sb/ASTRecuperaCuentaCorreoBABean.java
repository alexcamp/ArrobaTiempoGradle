package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperaCuentaCorreoBA
 */
public class ASTRecuperaCuentaCorreoBABean extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
			
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Cuenta Correo ST [" + act.getNumeroPeticion() + "]");

//		  TODO: LOGICA , lca

		try {
			RecursosBADelegate delegate=new RecursosBADelegate();
			if(delegate.isIDPC(act.getNumeroPeticion())){
				delegate.enviarCuentaCorreo(act.getNumeroPeticion().toString(),act.getCodigoActividad(), null);
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
			}
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Obtener Cuenta Correo ST",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Cuenta Correo ST", e);
		}	
				
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
			
	}
}
