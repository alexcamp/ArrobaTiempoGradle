package co.com.telefonica.atiempo.soltec.actividades.df.solucion_stb.ejb.sb;

import javax.ejb.FinderException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePIKey;
import co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocal;
import co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ASTControlReparacionSTB
 */
public class ASTControlReparacionSTBBean
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
		// CR11267, AT-1167 adecarlini
		log.debug("CONTROL DE REPARACION: comienzo del metodo On Inicio Actividad");
		try{
			log.debug("CONTROL DE REPARACION: contiene  <solucion_stb>? "+act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb));
			if(act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb)){
							
				String redireccionarHacia = act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb);
				String proximaBandeja = "Cierre de Incidencia";
				//log.debug("Inicio Control Reparacion --> se finalizará automáticamente");
				act.setObservacion("Inicio Control Reparacion --> se finalizará automáticamente");
				log.debug("CONTROL DE REPARACION: por ejecutar terminoInmediato");
				act.setRealizarTerminoInmediato(true);
				log.debug("CONTROL DE REPARACION: terminoInmediato ejecutado");
				log.debug("CONTROL DE REPARACION: redireccionar hacia? "+ redireccionarHacia);
				if(redireccionarHacia.equals("N")){
					log.debug("CONTROL DE REPARACION: redireccionar hacia = N");
					//si es N, es porque viene de PI.. esto es para manejar las actividades instanciadas previas al fdl v2
					//log.debug("SOLUCION_STB: N");
					IncidenciasDesdePILocalHome incidenciasDesdePILocalHome = (IncidenciasDesdePILocalHome)HomeFactory.getHome(IncidenciasDesdePILocalHome.JNDI_NAME);
					try{
						IncidenciasDesdePILocal incidencia =  incidenciasDesdePILocalHome.findByKey(act.getNumeroPeticion());
						log.debug("registro en Incidencia desde PI: nroIncidencia= "+((IncidenciasDesdePIKey)incidencia.getPrimaryKey()).nroIncidencia.longValue()
								  +", destino= "+incidencia.getDestino());
						String destinoVerdadero = incidencia.getDestino().trim();
						
						log.debug("enviando a: "+destinoVerdadero);
						log.debug("CONTROL DE REPARACION: redireccionar hacia destino verdadero = " + destinoVerdadero);
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, destinoVerdadero);
						
					} catch (FinderException e2){
						log.debug("excepcion en el finder, al buscar el destino de la incidencia: "+ ((act.getNumeroPeticion()==null)?" nro peticion es nulo":""+act.getNumeroPeticion())
									+" -- se cierra la incidencia");
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, "S");
  													 
					};
				}else if(redireccionarHacia.equals("PE")){
					proximaBandeja = "Planta Externa";
				}else if(redireccionarHacia.equals("PI")){
					proximaBandeja = "Planta Interna";
				}else if(redireccionarHacia.equals("RI")){
					proximaBandeja = "Gestion de Abonado";
				}
				//TCS-gquevedo May 28, 2009 CR24382 INICIO
				else if(redireccionarHacia.equals("ARI")){
					proximaBandeja = "Aprovisionamiento RI";
				}
				else if(redireccionarHacia.equals("MRI")){
						proximaBandeja = "Mantenimiento RI";
				//TCS-gquevedo May 28, 2009 CR24382 FIN
				}else if(redireccionarHacia.equals("S")){
					proximaBandeja = "Cierre de Incidencia";
				};
				redireccionarHacia = "Se redirige a - "+ proximaBandeja+" - Automaticamente";
				log.debug("Estoy en Control de Reparacion -->redireccionando hacia: "+ redireccionarHacia);
				act.setGrabaEnBitacora(false);					
			}
		}catch(Exception e){
			log.debug(e);
			log.debug("Error en el flujo, deveria terminarse la actividad CONTROL REPARACION");
			throw new TnProcesoExcepcion("Error en el flujo, deveria terminarse la actividad CONTROL REPARACION");
		};
		log.debug("CONTROL DE REPARACION: fin del metodo On Inicio Actividad");
		//fin AT-1167  
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Este Control de Cambio se volvio atras. Se envia la hora de la ultima solucion ingresada.
//		try{
//			if (act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb)){
//				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).equals("S")){
//					//si se va a actualizar inventario, es pk ya tiene solucion y es la utlima vez que se ejecuta esta actividad manual
//					IncidentesInterfaces iI = new IncidentesDelegate();
//					iI.ActividadManualMarcaCierreIncidente(act.getNumeroPeticion());
//				}
//			}
//		}catch(Exception e){
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("Excepcion al terminar Control Reparacion STB",e);
//		}
	}
}
