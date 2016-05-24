package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTActualizarInventarioTV
 */
public class ASTActualizarInventarioTVBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio enviar Actualizacion Inventario TV [" + act.getNumeroPeticion() + "]");

		try {
			RecursosTVDelegate delegate=new RecursosTVDelegate();
			if(delegate.isIdpcTV(act.getNumeroPeticion())){
				if(!delegate.enviaActualizacionInventarioTV(act.getNumeroPeticion().longValue(),act.getCodigoActividad())){
					//no se actualiza inventario pues no se activó ni desactivo ningun deco
					act.setRealizarTerminoInmediato(true);
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_TV.control_act_inv_tv,"N");
					act.setObservacion("No se activo un deco nuevo ni desactivo un deco original. No se actualiza inventario. ");
				}
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC para TV");
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_TV.control_act_inv_tv,"N");
			}
		} catch (ATiempoAppEx e) {
			log.warn("Error al enviar actualizacion inventario TV",e);
			throw new TnProcesoExcepcion("Error al enviar actualizacion inventario TV", e);
		}

		log.debug("Fin enviar actualizacion inventario TV [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

}
