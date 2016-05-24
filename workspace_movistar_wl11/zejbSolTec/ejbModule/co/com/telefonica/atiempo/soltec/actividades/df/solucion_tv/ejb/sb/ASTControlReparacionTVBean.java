package co.com.telefonica.atiempo.soltec.actividades.df.solucion_tv.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTControlReparacionTV
 */
public class ASTControlReparacionTVBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	 protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		  //CR11773
		  try{
			  if(act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv)){
				  log.debug("Inicio Control Reparacion TV - nro Incidencia: "+act.getNumeroPeticion()+"--> se finalizará automáticamente");
				  act.setObservacion("Inicio Control Reparacion TV --> se finalizará automáticamente");
				  act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv, "S");
				  act.setGrabaEnBitacora(false);
				  act.setRealizarTerminoInmediato(true);					
			  }
		  }catch(Exception e){
			 log.debug(e);
		  }
		
	 }

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Este Control de Cambio se volvio atras. Se envia la hora de la ultima solucion ingresada.
//		try{
//			if (act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv)){
//				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv).equals("S")){
//					//si se va a actualizar inventario, es pk ya tiene solucion y es la utlima vez que se ejecuta esta actividad manual
//					IncidentesInterfaces iI = new IncidentesDelegate();
//					iI.ActividadManualMarcaCierreIncidente(act.getNumeroPeticion());
//				}
//			}
//		}catch(Exception e){
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("Excepcion al terminar Control Reparacion TV",e);
//		}
	}

}
