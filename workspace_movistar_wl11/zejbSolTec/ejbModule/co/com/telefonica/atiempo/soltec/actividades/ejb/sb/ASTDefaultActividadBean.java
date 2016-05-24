package co.com.telefonica.atiempo.soltec.actividades.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTDefaultActividad
 */
public class ASTDefaultActividadBean
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
		// TODO Auto-generated method stub
		/**
		 * DAVID: Si es localidad Agenda SC, se procede a enviar la TR 701E con el método creacionActuacionAgendaSC. La respuesta recibida de Agenda SC,
		 * por el método recepcionCreacionActuacionAgendaSC derivará la petición a PGI o a Control instalación según el parámetro error code de la TR701S.
		 */
		if(act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_VE)||act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_TV)||act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_BA)
				||act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_STB)
				//se agrega validacion de la nueva bandeja
				||act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_TOA)){
			boolean esAgendaSC=esAgendaSC(act);                                                                     
			if(esAgendaSC){
				try {
					/**
					 * Se revisa primero si la peticion ya no tiene actuaciones abiertas.
					 */
					AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);                                    
					Collection listaAgendamientos = agendaSCSTLocalHome.findByCodAve(act.getNumeroPeticion());            
					Iterator listaAgendamientosIt=null;
					boolean yaTieneActActiva=false;
					for(listaAgendamientosIt=listaAgendamientos.iterator();listaAgendamientosIt.hasNext();){
						AgendaSCSTLocal agendaSCSTLocal = (AgendaSCSTLocal)listaAgendamientosIt.next();
						if(agendaSCSTLocal.getEstado().intValue()==ComunInterfaces.ACTUACION_ABIERTA){                                                                         
							yaTieneActActiva=true;
							break;
						}
					}
					if(yaTieneActActiva){
						log.debug("Ya exíste una actuación abieta para esta petición, no se enviará la tr 701e.");
					}else{
						if (esLocalidadTOA(act)&& act.getCodigoActividad().equals(ComunInterfaces.PLANTA_EXTERNA_TOA)){
							log.debug("Se va a crear la actuacion TOA");
							ServicioTOASTDelegate servicioTOASTDelegate=new ServicioTOASTDelegate();
							servicioTOASTDelegate.crearActuacionTOA(null,act);
						}else{
							if (esLocalidadTOA(act)){
								log.debug("Se inhibe por ser localidad TOA");
								ActividadLocalHome actividadHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
								ActividadLocal actividadLocal = actividadHome.findByCodigoActividadIdAplicacion(ComunInterfaces.PLANTA_EXTERNA_TOA, new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
								ActividadKey activadkey = (ActividadKey) actividadLocal.getPrimaryKey();
								act.setRealizarTerminoInmediato(true);
								log.debug("Se obtiene la actividad "+activadkey.act_id);
								act.setObservacion("Se inhibe por ser Localidad TOA");
								act.setDato("SOL_TOA", ""+activadkey.act_id); 
								act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,"");
								act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"");
								act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,"");
								return;
							}else{
								co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate recursosBADelegate=new co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate();
								recursosBADelegate.creacionActuacionAgendaSC(act.getNumeroPeticion(), act);
							}
						}
						
					}                                             
					
				}catch (ATiempoAppEx e) {
					log.debug("Error al obtener recursos BA delegate para creación de actuación AgendaSC: "+e.toString());
				}catch (FinderException e) {
					log.debug("Error al buscar objeto en la tabla AgendaSCST: "+e.toString());
				}catch (NamingException e) {
					log.debug("Error de nombrado extrayendo objeto de la tabla AgendaSCST: "+e.toString());
				}
				
//			} else if (esLocalidadTOA(act)){ 
//				//REQ TOA FASE I @DCARDENA 01/06/2015
//				try {
//					/**
//					 * Se revisa primero si la peticion ya no tiene actuaciones abiertas.
//					 */
//					AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);                                    
//					Collection listaAgendamientos = agendaSCSTLocalHome.findByCodAve(act.getNumeroPeticion());            
//					Iterator listaAgendamientosIt=null;
//					boolean yaTieneActActiva=false;
//					for(listaAgendamientosIt=listaAgendamientos.iterator();listaAgendamientosIt.hasNext();){
//						AgendaSCSTLocal agendaSCSTLocal = (AgendaSCSTLocal)listaAgendamientosIt.next();
//						if(agendaSCSTLocal.getEstado().intValue()==ComunInterfaces.ACTUACION_ABIERTA){                                                                         
//							yaTieneActActiva=true;
//							break;
//						}
//					}
//					if(yaTieneActActiva){
//						log.debug("Ya exíste una actuación abieta para esta petición, no se enviará la tr 800e.");
//					}else{
//						log.debug("Se va a crear la actuacion TOA");
//						ServicioTOASTDelegate servicioTOASTDelegate=new ServicioTOASTDelegate();
//						servicioTOASTDelegate.crearActuacionTOA(null,act);
//					}                                             
//					
//				}catch (ATiempoAppEx e) {
//					log.debug("Error al obtener recursos BA delegate para creación de actuación TOA: "+e.toString());
//				}catch (FinderException e) {
//					log.debug("Error al buscar objeto en la tabla AgendaSCST: "+e.toString());
//				}catch (NamingException e) {
//					log.debug("Error de nombrado extrayendo objeto de la tabla AgendaSCST: "+e.toString());
//				}
			}
//			//FIN REQ TOA FASE I
		}                      
		//Fin DAVID.
		
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * DAVID: Método para validar si la petición pertenece a una localidad de agenda SC.
	 * @param act
	 * @return
	 */
	private boolean esAgendaSC(ActividadEJBDTO act){
		boolean esAgendaSC=false;
		try{
			Long codAve=act.getNumeroPeticion();
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey peticion_stKey=new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadKey localidadKey=new LocalidadKey(codLoc);
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(localidadKey);
			
			if(localidadLocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA)
				return true;
			
			Localidad_agenda_scLocalHome localidadAgendaSCLocalHome = (Localidad_agenda_scLocalHome) HomeFactory.getHome(Localidad_agenda_scLocalHome.JNDI_NAME);
			Localidad_agenda_scKey key = new Localidad_agenda_scKey(localidadKey.cod_loc);
			Localidad_agenda_scLocal localidadAgendaSCLocal = localidadAgendaSCLocalHome.findByPrimaryKey(key);
			
			esAgendaSC = true;
			
		}catch(FinderException ex){
			log.debug("La localidad no esta entre las catálogadas como Agenda SC");
			esAgendaSC=false;
		}catch(NamingException ex){
			log.debug("La localidad no esta entre las catálogadas como Agenda SC");
			esAgendaSC=false;
		}
		return esAgendaSC;
	}
	// REQ TOA FASE I @DCARDENA
	//Funcion que consulta por medio de la localidad de la  averia si el flag Localidad_TOA de la tabla Localidad se encuentra en 1 para retornar TRUE
	private boolean esLocalidadTOA(ActividadEJBDTO act){
		boolean esLocalidadTOA=false;
		try{
			log.debug("Ingreso a validar la localidad para la peticion: "+act.getNumeroPeticion());
			Long codAve=act.getNumeroPeticion();
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey peticion_stKey=new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadKey localidadKey=new LocalidadKey(codLoc);
			
			
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(localidadKey);
			log.debug("se valida localidad " + localidadLocal.getNombre_localidad());
			if(localidadLocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA){
				log.debug("Es localidad TOA");
				esLocalidadTOA = true;
			}
		}catch(FinderException ex){
			log.debug("La localidad no esta entre las catálogadas como TOA");
			esLocalidadTOA=false;
		}catch(NamingException ex){
			log.debug("La localidad no esta entre las catálogadas como TOA");
			esLocalidadTOA=false;
		}
		return esLocalidadTOA;
	}
	
	
}