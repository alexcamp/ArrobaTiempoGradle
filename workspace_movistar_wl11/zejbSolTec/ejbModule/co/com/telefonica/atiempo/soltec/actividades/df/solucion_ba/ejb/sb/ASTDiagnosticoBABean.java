package co.com.telefonica.atiempo.soltec.actividades.df.solucion_ba.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.reglas.ReglasDelegate;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTDiagnosticoBA
 */
public class ASTDiagnosticoBABean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onInicioActividadST()
	 */
	 protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		 log.debug("Inicio Diagnostico BA");
		 try{
			 if(act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba)){
				 if(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba) == null 
				 		|| act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).trim().equals("")){
					 //Es la primera vez del diagnostico, por lo tanto hago el ruteo.
					 long regla=0;
					 ReglasInterfaces rI = new ReglasDelegate();
					 regla = rI.getRegla(act.getNumeroPeticion());
					 log.debug("Regla:" + regla);
					 if(regla == ComunInterfaces.codigoReglaPlantaExterna ){
						 act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba, ""+ComunInterfaces.idActividadPEBA); 
						 act.setObservacion("Se redirige a Planta Externa Automáticamente");
						 act.setRealizarTerminoInmediato(true);	
					 }else if(regla == ComunInterfaces.codigoReglaPlantaInterna){
						 act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba, "SN"); 
						 act.setObservacion("Se redirige a Planta Interna Automáticamente");
						 act.setRealizarTerminoInmediato(true);	
					 }
					 //Si no es una de esas reglas... se queda en diagnostico	
				 //CR11773: si no esla primera vez que pasa por diagnostico, me fijo si viene desde alguna planta y va a la otra
					 //DAVID: req 1060, kiosko y ZWIFI
				 }else if(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).trim().equals("PEKZWF") ||
				 		act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).trim().equals(""+ComunInterfaces.idActividadPEBA) ||
						act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).trim().equals("SN")
						||act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).trim().equals("AM")){
				 act.setObservacion("se dirige a "+act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb));
				 act.setRealizarTerminoInmediato(true);
				 act.setGrabaEnBitacora(false);
				 log.debug("estoy en Diagnostico BA, finalizando automaticamente");
				 }
				 // fin CR11773
			 }
		 }catch(Exception e){
			 log.debug(e);
			 throw new TnProcesoExcepcion("Error en actividad Diagnostico BA");
		 }
		 log.debug("Fin Diagnostico BA");
	 }
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Este Control de Cambio se volvio atras. Se envia la hora de la ultima solucion ingresada.
//		try{
//			if (act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba)){
//				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).equals("S")){
//					//si se va a actualizar inventario, es pk ya tiene solucion y es la utlima vez que se ejecuta esta actividad manual
//					IncidentesInterfaces iI = new IncidentesDelegate();
//					iI.ActividadManualMarcaCierreIncidente(act.getNumeroPeticion());
//				}
//			}
//		}catch(Exception e){
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("Excepcion al terminar Diagnostico BA",e);
//		}		
	}
}
