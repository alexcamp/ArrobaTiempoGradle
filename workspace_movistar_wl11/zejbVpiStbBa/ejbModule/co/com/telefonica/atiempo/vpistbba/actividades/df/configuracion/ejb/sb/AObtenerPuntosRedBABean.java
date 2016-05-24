package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AObtenerPuntosRedBA
 */
public class AObtenerPuntosRedBABean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Puntos de Red BA [" + act.getNumeroPeticion() + "]");		
		try {
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				RecursosInterfaces recursosInterfaces = new RecursosDelegate();
				//CR-14657 Granite 20/10/2008
				PeticionesInterfaces pI = new PeticionesDelegate();
			   	boolean usaGranite = pI.usaGranite(act.getNumeroPeticion());
			   	
				if(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos).equals("S")){
					//Hubo asignacion de recursos, por lo tanto la peticion incluye un alta de linea => utilizar conector 2 APSC
					//No tengo los ps para enviar el mensaje.... pk es otra actividad..
					//Primero reviso si la asignacion me entregó dslam, si es asi, no hago ninguna consulta.
					InformacionTecnicaDTO infoTec = recursosInterfaces.obtenerRecursosTecnicos(act.getNumeroPeticion());
					if(infoTec.LineaNueva.getDslams().size()<1 && infoTec.LineaNueva.getZonas().size()<1){
						log.debug("No hay DSLAM asociados en los recursos. Se envia la consulta al APSC para obtener DSLAM.");
						act.setObservacion("Consulta por los DSLAM.");
						
//						CR-14657 Granite 20/10/2008
						if(usaGranite)
							recursosInterfaces.consultaRecursoGraniteSTB_BA(act.getNumeroPeticion(),null,act.getCodigoActividad());
						else
							recursosInterfaces.consultaRecursoSTB_BA(act.getNumeroPeticion(),null,act.getCodigoActividad());
						
					}else{
						String obs="Hay DSLAM/Zonas disponibles. No es necesario realizar la consulta al APSC.";
						log.debug(obs);
						act.setObservacion(obs);
						act.setRealizarTerminoInmediato(true);
					}
				}else{
					//Sino, utlizar conector 6 APSC
					act.setObservacion("Consulta por los Recursos y DSLAM/Zonas.");
//					Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
//					boolean esTrasladoBaja=false;
//					Traslado_solobaLocal traslado_solobaLocal=null;
//					try{
//						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(this.numeroPeticion);
//						esTrasladoBaja=true;
//					} catch (FinderException e) {
//						log.warn("No es traslado BA BAJA");
//					}
//					if(esTrasladoBaja){
//						//si es traslado baja, busco los puntos de red de donde se instalara la BA
//						PeticionKey peticionKey = new PeticionKey(traslado_solobaLocal.getPeti_alta());
//						PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//						PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
//						recursosInterfaces.envioPuntosRedSTB(peticionLocal.getNum_ide_nu_stb(),this.getNumeroPeticion().toString(),this.getCodigoActividad());
//					}else{
					
//					CR-14657 Granite 20/10/2008
					if(usaGranite)
						recursosInterfaces.envioPuntosRedGraniteSTB(act.getNumeroPeticion().toString(),act.getCodigoActividad());
					else
						//16/08/2007 Siempre consulto sobre el Identificador Actual
						recursosInterfaces.envioPuntosRedSTB(act.getNumeroPeticion().toString(),act.getCodigoActividad());
//					}
				}
			}else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);		
			}
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Obtener Puntos de Red BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red BA", e);
		}
// catch (NamingException e) {
//			log.warn("Error en Actividad Obtener Puntos de Red BA",e);
//			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red BA", e);
//		} catch (FinderException e) {
//			log.warn("Error en Actividad Obtener Puntos de Red BA",e);
//			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red BA", e);
//		}		
		log.debug("Fin Actividad Obtener Puntos de Red BA [" + act.getNumeroPeticion() + "]");			
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// si el cierre es OK, ahi pongo que si hubo asignacion de recursos, con esto me salto la actividad de obtener puntos de red STB
		if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI")) && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC"))){
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");
		}
	}

}
