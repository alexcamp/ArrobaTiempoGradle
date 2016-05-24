package co.com.telefonica.atiempo.vpistbba.actividades.df.asignacion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ACreaODSGranite
 */
public class ACreaODSGraniteBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		try{
			
		PeticionesInterfaces pI = new PeticionesDelegate();
	   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
	   	int resultado=0;

	   	// Modificacion FTCC SVAs 27/02/15 dcardena
		//se debe inhibir la actividad crear ods cuando hallan campos fttc unicamente para los SVAS
		//Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
	    Recursos_linea_basicaLocalHome  recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
	    Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
		boolean hayFTTC =false;
		boolean hayPcLinea=false;
		//se vlaida si hay recursos fttc
		if((recursos_linea_basicaLocal.getRec_fttc_asg() != null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))||
				((recursos_linea_basicaLocal.getRec_fttc_ant()) != null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))){
			log.debug("hay recursos FTTC en la peticion "+act.getNumeroPeticion());
			hayFTTC=true;
		}
	   	
//		//FTTC se agrega la consulta a la tabla productoservicioPeticion para obtener todos los ps de la peticion
//	    Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome( Producto_servicio_peticionLocalHome.JNDI_NAME);
//	    Collection listaPs = (Collection) producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
//	    for(Iterator iterator2=listaPs.iterator();iterator2.hasNext();)
//		{
//	    	//se valida si hay ps de familia PC para poder ejecutar la actividad o inhibirla
//			Producto_servicio_peticionLocal producto_servicio_peticionLocal =(Producto_servicio_peticionLocal) iterator2.next();
//			if(producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()==(ComunInterfaces.familiaPcLinea)){
//				hayPcLinea=true;
//				break;
//			}
//		}

		if(esGranite){
			//Actividad Asignacion de recursos con Granite
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Inicio Actividad crea ODS con Granite peticion [" + act.getNumeroPeticion() + "]");
				RecursosInterfaces recursosInterfaces = new RecursosDelegate();
				resultado =recursosInterfaces.enviaACrearODS(act);
				if(resultado==1){
					//se valida si no hay pc linea y hay campos fttc para inhibir la actividad
					if(!hayPcLinea&&hayFTTC){
						log.debug("No se envia a crear ODS ya que hay recursos FTTC para los SVAs"+ act.getNumeroPeticion());
						act.setObservacion("No se envia a crear ODS ya que hay recursos FTTC para los SVAs");
						act.setRealizarTerminoInmediato(true);
					}else{
					act.setObservacion("Se envia mensaje de creacion de ODS ");
					}
				}
				if(resultado==2){
					act.setObservacion("No se envia a crear ODS ya que fue creada en la asignacion de recursos");
					act.setRealizarTerminoInmediato(true);
				}
				if(resultado==3){
					act.setObservacion("No se envia reversa de creacion de ODS ya que fue creada en la asignacion de recursos");
					act.setRealizarTerminoInmediato(true);
				}
				if(resultado==4){
					act.setObservacion("No se envia reversa de creacion de ODS ya que la peticion no fue cancelada completamente");
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				log.debug("Inicio Actividad Reversa crea ODS con Granite peticion [" + act.getNumeroPeticion() + "]");
				if (act.getPsOk().size()>0){
					act.setObservacion("No se envia reversa de creacion de ODS ya que la peticion no fue cancelada completamente");
					act.setRealizarTerminoInmediato(true);
				}else{
					RecursosInterfaces recursosInterfaces = new RecursosDelegate();
					resultado=recursosInterfaces.enviaACrearODS(act);
					if(resultado==1){
						//se valida si no hay pc linea y hay campos fttc para inhibir la actividad
						if(!hayPcLinea&&hayFTTC){
							log.debug("No se envia a crear ODS ya que hay recursos FTTC para los SVAs"+ act.getNumeroPeticion());
							act.setObservacion("No se envia a crear ODS ya que hay recursos FTTC para los SVAs");
							act.setRealizarTerminoInmediato(true);
						}else{
						act.setObservacion("Se envia mensaje de creacion de ODS ");
						}
					}
					if(resultado==2){
						act.setObservacion("No se envia a crear ODS ya que fue creada en la asignacion de recursos");
						act.setRealizarTerminoInmediato(true);
					}
					if(resultado==3){
						act.setObservacion("No se envia reversa de creacion de ODS ya que fue creada en la asignacion de recursos");
						act.setRealizarTerminoInmediato(true);
					}
					if(resultado==4){
						act.setObservacion("No se envia reversa de creacion de ODS ya que la peticion no fue cancelada completamente");
						act.setRealizarTerminoInmediato(true);
					}
				}
			}
		}else{
			if(resultado==2){
				act.setObservacion("No se envia a crear ODS ya que fue creada en la asignacion de recursos");
				act.setRealizarTerminoInmediato(true);
			}
			if(resultado==3){
				act.setObservacion("No se envia reversa de creacion de ODS ya que fue creada en la asignacion de recursos");
				act.setRealizarTerminoInmediato(true);
			}
			if(resultado==4){
				act.setObservacion("No se envia reversa de creacion de ODS ya que la peticion no fue cancelada completamente");
				act.setRealizarTerminoInmediato(true);
			}else{
				act.setObservacion("No se envia a crear ODS ya que la peticion no pertence a una localidad de Granite");
				act.setRealizarTerminoInmediato(true);
			}			
		}
	} catch (ATiempoAppEx e) {
		log.warn("Error en Actividad Crear ODS",e);
		throw new TnProcesoExcepcion("Error en crear ods Granite", e);
	} catch (NamingException e) {
		log.warn("Error en Naming recursos_linea_basica",e);
		throw new TnProcesoExcepcion("Error al llamar el naming de la tabla Recursos_linea_Basica", e);
	} catch (FinderException e) {
		log.warn("Error al consultar en recursos linea Basica",e);
		throw new TnProcesoExcepcion("Error al consultar en la Tabla recursos_linea_Basica", e);
	}
	

	log.debug("Fin Actividad Actividad crea ODS Granite [" + act.getNumeroPeticion() + "]");
	
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		
	}

}
