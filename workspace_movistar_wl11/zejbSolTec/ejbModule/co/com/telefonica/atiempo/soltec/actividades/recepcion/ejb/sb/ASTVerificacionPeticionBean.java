package co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.reglas.ReglasDelegate;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTVerificacionPeticion
 */
public class ASTVerificacionPeticionBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Verificar Peticion [" + act.getNumeroPeticion() + "]");
		try{
			long regla=0;
			ReglasInterfaces rI = new ReglasDelegate();
			regla = rI.getRegla(act.getNumeroPeticion());
			if (regla < 0){//Viene con estado terminado
				act.setDato(DATOS_ATSTSTBBA.RECEPCION.recep_es_cancela, "S"); // La envio a anulacion directo
			}
			else if(regla == ComunInterfaces.codigoReglaBloqueo ){
				act.setDato(DATOS_ATSTSTBBA.RECEPCION.recep_es_cancela, "B"); // La envio a bloqueo y despues a anulacion cuando se desbloquea
			}else{
				act.setDato(DATOS_ATSTSTBBA.RECEPCION.recep_es_cancela, "N"); // Genera Flujo
			}
		}catch(Exception e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en actividad Verificar Peticion");
		}
		log.debug("Fin Actividad Verificar Peticion [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}
