package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import java.util.ArrayList;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTActualizarInventarioBA
 */
public class ASTActualizarInventarioBABean
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
		log.debug("Inicio enviar Actualizacion Inventario BA [" + act.getNumeroPeticion() + "]");

//TODO: LOGICA , lca
		
		try {
			RecursosBADelegate delegate=new RecursosBADelegate();
			if(delegate.isIDPC(act.getNumeroPeticion())){
				ArrayList modems = delegate.getModemSt(act.getNumeroPeticion());
				
				if(modems != null && modems.size() > 0){
				
					delegate.enviarActualizacionInventarioBA(act, act.getNumeroPeticion().toString(),act.getCodigoActividad());
		
				}else{
					
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("No hay Modems capturado por lo tanto no se actualiza inventario");
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"N");
				}
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"N");
			}
			
		} catch (ATiempoAppEx e) {
			log.warn("Error al enviar actualizacion inventario BA",e);
			throw new TnProcesoExcepcion("Error al enviar actualizacion inventario BA", e);
		}
		

		log.debug("Fin enviar actualizacion inventario BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

}
