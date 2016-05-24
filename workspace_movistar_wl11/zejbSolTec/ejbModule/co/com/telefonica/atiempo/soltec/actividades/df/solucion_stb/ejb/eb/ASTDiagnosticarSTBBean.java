package co.com.telefonica.atiempo.soltec.actividades.df.solucion_stb.ejb.eb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.soltec.reglas.ReglasDelegate;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTDiagnosticarSTB
 */
public class ASTDiagnosticarSTBBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
	//TODO: at-1128 CR10780
	log.debug("Inicio Diagnostico STB");
	try{
	   	RecursosDelegate delegate=new RecursosDelegate();
		if(act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb)){
			if(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb) == null || act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals("")){
				//Es la primera vez del diagnostico, por lo tanto hago el ruteo.
				long regla=0;
				ReglasInterfaces rI = new ReglasDelegate();
				regla = rI.getRegla(act.getNumeroPeticion());
				log.debug("Regla:" + regla);
	
				if(regla == ComunInterfaces.codigoReglaPlantaExterna ){
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, "PE"); 
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
					act.setObservacion("Se redirige a Planta Externa Automaticamente");
					act.setRealizarTerminoInmediato(true);
					log.debug("Se redirige a Planta Externa Automaticamente");	
				}else if(regla == ComunInterfaces.codigoReglaPlantaInterna){
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, "PI");
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N"); 
					act.setObservacion("Se redirige a Planta Interna Automáticamente");
					act.setRealizarTerminoInmediato(true);
					log.debug("Se redirige a Planta Interna Automaticamente")	;
				}else if(regla == ComunInterfaces.codigoReglaGestionAbonados){
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, "RI");
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N"); 
					act.setObservacion("Se redirige a Gestion de Abonados Automaticamente");
					act.setRealizarTerminoInmediato(true);
					log.debug("Se redirige a Gestion de Abonados Automaticamente")	;
		
					}
					//Si no es una de esas reglas... se queda en diagnostico	
				}
				//TODO: CR11267 se agrega esto para solucionar el problema surgido con incidencias ya instanciadas
				//si el destino es la bandeja PI, PE o RI, no se marca averia, asi pasa a la siguiente etapa del flujo
				else if(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals(""+ComunInterfaces.idActividadPESTB) ||
						act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals("PI") ||
						act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals("RI") ||
						act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals("S")  ||
						act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).trim().equals("AM")){
                        // CR20759 -- Loop de ST -- Pablo L					
	                    if(!act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb).trim().equals("S")){				
							act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
							act.setObservacion("se dirige a "+act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb));
							act.setRealizarTerminoInmediato(true);
							act.setGrabaEnBitacora(false);
							log.debug("estoy en Diagnostico STB, finalizando automaticamente");
						}
				}
				// fin CR11267
			}
		}catch(Exception e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en actividad Diagnostico STB");
		}
		log.debug("Fin Diagnostico STB");		

		// fin CR10780
		
	}

/* (non-Javadoc)
 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST()
 */
protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		try{
			if (act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb)){
				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).equals("S")){
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
					//si se va a actualizar inventario, es pk ya tiene solucion y es la utlima vez que se ejecuta esta actividad manual
//					Este Control de Cambio se volvio atras. Se envia la hora de la ultima solucion ingresada.
//					IncidentesInterfaces iI = new IncidentesDelegate();
//					iI.ActividadManualMarcaCierreIncidente(act.getNumeroPeticion());
				}else{
					//Si va a alguna plataforma y ya tiene marca no realizo la actividad de marca averia
					RecursosInterfaces ri = new RecursosDelegate();
					if(ri.esMarcadaAveriaSTB(act.getNumeroPeticion())){
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
					}
					else{
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"S");
					}
	//				if(this.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb).equals("N")){
	//					this.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
	//				}else{
	//					//Si no se ha realizado la marca, se hace la actividad.
	//					this.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"S");
	//				}
				}
			}
		}catch(Exception e){
			log.debug("Exception",e);
			throw new TnProcesoExcepcion("Excepcion al terminar Diagnosticar",e);
		}

	}
}
