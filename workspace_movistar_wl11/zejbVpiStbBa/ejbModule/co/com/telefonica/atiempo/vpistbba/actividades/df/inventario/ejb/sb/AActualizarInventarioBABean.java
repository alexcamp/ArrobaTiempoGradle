package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioBA
 */
public class AActualizarInventarioBABean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");

		//LCA LOGICA
		RecursosBADelegate recursosBADelegate;
		try
		{
		    //CR 24298 Correccion de actualizar inventario para Cambio de plan
			String verif_Flujo = act.getDato(DATOS_ATVPISTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok);
			Integer IdActividad = act.getIdActividadFlujo();
		    boolean ejecutar =true;
			 if((verif_Flujo!=null) && (act.getIdActividadFlujo()!= null )&&(verif_Flujo.equals("S"))){
			 	try {
			 		//if(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok)){
			 		// logica
			 		recursosBADelegate = new RecursosBADelegate();
			 		
			 		if(!recursosBADelegate.huboCambiosPuertoIp(act.getNumeroPeticion())){
			 			/*entra por aca si no hubo modificaciones en los ips o puertos */
			 			log.debug("No hubo cambios de puerto ni ip, ahora se pregunta si existen otros PS que necesiten entrar a la Actividad Actualizar Inventario.");
			 			//Correccion Error de produccion AT-2487 18/08/2009
			 			if (!recursosBADelegate.seDebeEntrarAInstalar (act.getNumeroPeticion(),act.getIdActividadFlujo())) {
			 				/*si entra a este if es por que NO se debe entrar a la Actividad instalar entonces se termina la actividad,
			 				 *este es el caso en el que no hubo modificaciones en las ips y puertos , y ademas no hay otros productos que necesiten instalacion*/
			 				log.debug("Seteamos RealizarTerminoInmediato en true!");
			 				act.setRealizarTerminoInmediato(true);
			 				act.setObservacion("Se inhibe la actividad de Actualizar Inventario BA por ser el mismo puerto");
			 				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
			 				log.debug("Se finaliza la Actividad Actualizar Inventario BA para Cambio de Plan[" + act.getNumeroPeticion() + "]");
			 				ejecutar = false;
			 			}
			 		}
			 		//}
			 	}catch (Exception e) {
			 		log.warn("Error en Actividad Actualizar Inventario BA",e);
			 		throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
			 	}
			}
			if(ejecutar){
			    recursosBADelegate = new RecursosBADelegate();
				boolean nohaymodem = recursosBADelegate.noHayModemParaActualizarInventarioBa(act.getNumeroPeticion());
				if(nohaymodem){
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("No hay Modems capturado Por lo tanto no tengo que Actualizar inventario");
				}else
					recursosBADelegate.enviaActualizaInventarioBA(act, act.getNumeroPeticion().toString(),act.getCodigoActividad(), false);
			    
			}
		} catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Actualizar Inventario BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		}

		log.debug("Fin Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
