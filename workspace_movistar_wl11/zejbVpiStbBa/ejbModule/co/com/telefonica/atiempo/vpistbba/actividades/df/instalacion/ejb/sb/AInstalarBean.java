package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioDelegate;

import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: AInstalar
 */
public class AInstalarBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("Inicio Actividad Instalar [" + nroPeticion + "]");		
		
		//Ajuste solicitado por JJPadilla 09032011 en reversa la actividad instalar se debe inhibir
		if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
		{
			/*RQ5606 - Internet Total - mfmendez*/
			Properties_BDLocalHome propertiesBDLocalHome;
			try {

				log.debug("Ingreso a Instalar Agenda");
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionHome.findByPetiNumero(act.getNumeroPeticion());
				LocalidadLocal localidad = (LocalidadLocal)peticionLocal.getFk_01_localidad();

				propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.LLAVE_PROPERTIES_PS_INTERNET_MOVIL);
				
				String psIntTotal = propertiesBDLocal.getValor();
				String[] listaPsIntTotal = null;
		        boolean esPSIntTotal = false;
				
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
	        	PeticionKey peticionKey = new PeticionKey(nroPeticion);
				peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
	        						
				Collection peticionFlujoList = peticionLocal.getPeticion_flujo();
				
	        	boolean esSVABA = false;
	        	Properties_BDLocal propertiesSVALocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
	        	String psSVABA[] = propertiesSVALocal.getValor().split(",");
//				Se valida si el PS pertenece a un SVA de aumento de velocidad
	        	for (Iterator iter = peticionFlujoList.iterator(); iter.hasNext();) {
	        		Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)iter.next();
	        		for(int i = 0; i<psSVABA.length;i++){
						if(new Long(psSVABA[i]).intValue() == peticionFlujoLocal.getPrse_id().intValue()){
							esSVABA = true;
						}
					}
	        	}
				
	        	if (esSVABA && !traePSPC(nroPeticion)){
	        		/*Se inhibe la actividad*/
	        		log.debug("Se inhibe la actividad de instalación por tener Internet Solo PS de valor adicional");
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("Se inhibe la actividad de instalación por tener Internet Solo PS de valor adicional");
					return;
	        	}
		        if(psIntTotal != null && !psIntTotal.equals("")){
		        	listaPsIntTotal = psIntTotal.split(",");        			        	
//			        Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//	        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACTIVIDAD_INSTALACION));
	        		/*Valida que la petición tenga por lo menos un PS de los configurados en la tabla de propiedades (PS_ENVIO_EQUIPOS_SAP)*/
	        		for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext() && !esPSIntTotal;){
	        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
	        			
	        			for(int contPs=0;contPs<=listaPsIntTotal.length-1;contPs++){
	        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaPsIntTotal[contPs])){
	        					esPSIntTotal = true;
	        				}
	        			}
	        		}
				}
		        
		        //Se busca si el ps corresponde a monitoreo remoto y es una baja. Sí es así se debe inhibir la actividad de instalación.
		        Collection proSerPeticion = peticionLocal.getProducto_servicio_peticion();
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				String[] idPSMonitoreo = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CONF_CLIENTE_ZTE").split(",");
				boolean inhibirXBajaMonitoreo = false;
				Operacion_comercialLocal operComercialLocal = null;
		        monitoreo: for (Iterator iter = proSerPeticion.iterator(); iter.hasNext();) {
					Producto_servicio_peticionLocal proSerPeticionLocal = (Producto_servicio_peticionLocal) iter.next();
					for (int i = 0; i < idPSMonitoreo.length; i++) {
						operComercialLocal = proSerPeticionLocal.getOperacion_comercial();
						if(proSerPeticionLocal.getPsId().equals(Long.valueOf(idPSMonitoreo[i])) && Pattern.matches("B(CP)?",operComercialLocal.getOpco_tipo())){
							inhibirXBajaMonitoreo = true;
							break monitoreo;
						}
					}
				}
				//RQ Cambio plan BA 25956 @dcardena 13/06/2014
				// se agrega validacion del TAG INS_OK de workflow en el que contenga el valor ASIST
				// para que se inhiba la actvidad Instalar ya que fue ejecutado anteriormente la Asistencia Remota
				if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals("ASIST"))
				{
					String descripcion ="Se inhibe la actividad de instalación por que se ejecuto la actividad Asistencia remota"; 
	        		log.debug(descripcion);
					act.setRealizarTerminoInmediato(true);
					act.setObservacion(descripcion);
				}else{
					
					//REQ TRY AND BUY SE MUEVE LA VALIDACION DE TOA FASE III YA QUE NO SE INHIBIA INSTALAR TOA
					if(localidad.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA){
						log.debug("Se inhibe por ser Localidad TOA");
						act.setRealizarTerminoInmediato(true);
						act.setObservacion("Se inhibe por ser Localidad TOA");
						return;
					}
		        
				//Si el ps es de monitoreo y es una baja se inhibe la actividad
				if(inhibirXBajaMonitoreo){
	        		/*Se inhibe la actividad*/
					String descripcion ="Se inhibe la actividad de instalación por tener Monitoreo Remoto con una operación comercial de baja. "+operComercialLocal.getOpco_nombre(); 
	        		log.debug(descripcion);
					act.setRealizarTerminoInmediato(true);
					act.setObservacion(descripcion);
		        /*Si la petición tiene por lo menos un PS de Internet Total*/
				}else if(esPSIntTotal){
		        	/*Valida que la peticion tenga por lo menos un PS tipo PC o de Internet Equipado*/
		        	boolean esPSTipoPC = this.traePSPC(nroPeticion);
		        	
		        	/*Valida si la peticion tiene por lo menos un PS configurado en la tabla PS_TIPO_MODEM*/
		        	Ps_Tipo_ModemLocalHome psTipoModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
		        	Collection listTipoModemLocal = psTipoModemLocalHome.findAll();
		        	boolean esPsTipoModem = false;
		        	
		        	for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext() && !esPsTipoModem;){
	        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
	        			
	        			for(Iterator psTipoModemIter = listTipoModemLocal.iterator();psTipoModemIter.hasNext();){
	        				Ps_Tipo_ModemLocal psTipoModemLocal = (Ps_Tipo_ModemLocal) psTipoModemIter.next();
	        				if (peticionFlujoLocal.getPrse_id().toString().equals(psTipoModemLocal.getPd_id().toString())){
	        					esPsTipoModem = true;
	        				}
	        			}
	        		}
		        	
		        	/*Si existe por lo menos un PS tipo PC o un PS que se encuentre en la tabla PS_TIPO_MODEM 
		        	 * se ejecuta la actividad normalmente, en caso contrario se inhibe la actividad */
		        	if(!esPSTipoPC && !esPsTipoModem){
		        		/*Se inhibe la actividad*/
		        		log.debug("Se inhibe la actividad de instalación por tener Internet Total y no tener PS Tipo PC, ni PS de Venta de Equipos, ni PS en la tabla PS_TIPO_MODEM");
						act.setRealizarTerminoInmediato(true);
						act.setObservacion("Se inhibe la actividad de instalación por tener PS de Internet Total y no cumplir validaciones adicionales.");
		        	}else{
		        		/*Ejecución del funcionamiento normal de la actividad por tener PSs de Internet Total
		        		 * pero no tiene PS tipo PC ni PS en la tabla PS_TIPO_MODEM*/
			        	ejecutarFuncionamientoActividad(act, nroPeticion);		        		
		        	}
		        }else{
		        	/*Ejecución del funcionamiento normal de la actividad por no tener PSs de Internet Total*/
		        	ejecutarFuncionamientoActividad(act, nroPeticion);
		        }
				}
			} catch (NamingException e) {
				log.debug("Error al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
			} catch (FinderException e) {
				log.debug("Error al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
			} catch (Exception e){
				log.debug("Error desconocido al obtener recursos para ubicar restricciones de Internet Total: "+e.toString());
			}
			/*Fin RQ5606 - Internet Total - mfmendez*/	
		}else{//Caso para cuando es reversa.
		 	boolean esAutoInstalacionSoloBARev = false;
		 	boolean esAgendaSCRev=esAgendaSC(act);
			try{
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				esAutoInstalacionSoloBARev=peticionesDelegate.esAutoInstalacionSoloBA(nroPeticion); 				
			 	
				//Si es agenda y auto instalación enviamos agendamiento SC con
				// OC=300 para los ps's de auto instalación.
				if(esAutoInstalacionSoloBARev&&esAgendaSCRev){
					RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
					Calendar calendar = Calendar.getInstance();
					Date date = calendar.getTime();
					Timestamp currentTimestamp = new Timestamp(date.getTime());
	 				recursosBADelegate.creacionActuacionAgendaSC(act.getNumeroPeticion(), currentTimestamp, "esReversaAutoInst", act.getCodigoActividad(), act);
			 	}else{
			 		log.debug("Seteamos RealizarTerminoInmediato en true!");
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("Se inhibe la actividad de  Cruzada de BA por ir en reversa");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
			 	}
		 	}catch(ATiempoAppEx e){
				log.debug("Error al crear actuación para Auto instalación en reversa: "+e.toString());
			}
		 }	
	}
	
	
	/**
	 * @param nroPeticion
	 * @return
	 */
	private boolean traePSPC(Long nroPeticion) {
		// TODO Apéndice de método generado automáticamente
		
		try {
			boolean esPSTipoPC = false;
			Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection productosServicioPeticion = psph.findAllByPetiNumero(nroPeticion);
			
			for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext() && !esPSTipoPC;) {
				Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
				Familia_producto_servicioKey llaveFamilia = (Familia_producto_servicioKey) psp.getFamiliaKey();
			    int idFamiliaPsp = llaveFamilia.faps_id.intValue();
				
				if(ComunInterfaces.familiaPcLinea == idFamiliaPsp 
						|| ComunInterfaces.familiaPcBA == idFamiliaPsp 
						|| ComunInterfaces.familiaPcTV == idFamiliaPsp 
						|| ComunInterfaces.familiaIntEquipado == idFamiliaPsp){
					esPSTipoPC = true;
				}
			}
			return esPSTipoPC;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			return false;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		//TODO: 05/10/2009 - Raúl Triviño - Requerimiento 00029579
			boolean esAutoInstalacionSoloBA=false;
			LocalidadLocal localidad = null;
			try{
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(act.getNumeroPeticion());
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionHome.findByPetiNumero(act.getNumeroPeticion());
				localidad = (LocalidadLocal)peticionLocal.getFk_01_localidad();
			}catch (Exception e) {
				log.error("ocurrio un error al validar si es autoInstalacion peticion "+ act.getNumeroPeticion(),e);
			}
			//Se inhibe la actividad de control cruzada
			if (act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC")) ||
	                act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI"))
					|| act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGF"))){
	            act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
	        }else if(esAutoInstalacionSoloBA && localidad.getLocalidad_toa().intValue() != ComunInterfaces.LOCALIDAD_TOA){//Se debe inhibir la actividad de control cruzada 
	        	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"AutoInstalacion");
			}else  if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok)){
	            //Para que no haga el Instalar Visita si es que hizo el instalar normal
	            if (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals("S")){
	                act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
	            }
	        }else{//Si no existe.. la seteo en N
					//Para que no haga el Instalar Visita si es que hizo el instalar normal
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
			}
	        
	        
	        Long nroPeticion = act.getNumeroPeticion();
			log.debug("Inicio Actividad Instalar [" + nroPeticion + "]");
			
			try{
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				boolean tieneActuacionesAbiertas = false;
				boolean esAgendaSC=esAgendaSC(act);
				
				if (esAgendaSC){
					Collection agendamientoCollection = agendaSCLocalHome.findByPetiNumero(nroPeticion);
					
					for(Iterator agendamientoIterator=agendamientoCollection.iterator();agendamientoIterator.hasNext();){
						Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
						
						if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
								 || agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
							tieneActuacionesAbiertas = true;
							break;
						}
					}
					
					if (!tieneActuacionesAbiertas){
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
					}
				}
			}catch(NamingException ex){
				log.debug("Error llamando la instancia en la ejecución de la lógica del proceso: "+ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("La petición "+nroPeticion+" como tal no traia agendamiento relacionado queda es espera para su revisión.");
			}
		}
	
	//CR24918 - TV Masivo - PCawen
	private int getNroDecos(Long nroPeticion) {
		int cantidadDeco = 0;
		try{
			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(nroPeticion);
			
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++) {
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				if ( psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoTV || psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoPVRTV ) {
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++cantidadDeco;
				}
				else if(psDto.getIdFaps().intValue()==ComunInterfaces.familiaPcTV){
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++cantidadDeco;
				}
			}
		}catch (Exception e){
	 		log.error("Error al obtener la cantidad de decos");
	 	}
	 	return cantidadDeco;
	}
	/**
	 * DAVID: Método para validar si la petición pertenece a una localidad de agenda SC.
	 * @param act
	 * @return
	 */
	private boolean esAgendaSC(ActividadEJBDTO act){
		boolean esAgendaSC=false;
		try{
			Long petiNumero=act.getNumeroPeticion();
			String codActividad = act.getCodigoActividad(); 
			
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey peticionKey=new PeticionKey(petiNumero);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
			LocalidadKey localidadKey=(LocalidadKey)peticionLocal.getFk_01_localidad().getPrimaryKey();
			
			Localidad_agenda_scLocalHome localidadAgendaSCLocalHome = (Localidad_agenda_scLocalHome) HomeFactory.getHome(Localidad_agenda_scLocalHome.JNDI_NAME);
			Localidad_agenda_scKey key = new Localidad_agenda_scKey(localidadKey.cod_loc);
			Localidad_agenda_scLocal localidadAgendaSCLocal = localidadAgendaSCLocalHome.findByPrimaryKey(key);
			
			//Requerimiento 5565 - Ajuste que no se dispare agenda SC si es una solicitud de venta de equipos
			if (!codActividad.equals(ComunInterfaces.ACT_INSTALAR_IE))
				esAgendaSC = true;
			else{
				log.debug("Aunque la localidad esta catálogada como Agenda SC, se configura para que no sea considerada como tal en la tarea Instalacion venta de equipos");
				esAgendaSC = false;
			}
			//End Requerimiento 5565 
		}catch(FinderException ex){
			log.debug("La localidad no esta entre las catálogadas como Agenda SC");
			esAgendaSC=false;
		}catch(NamingException ex){
			log.debug("La localidad no esta entre las catálogadas como Agenda SC");
			esAgendaSC=false;
		}
		return esAgendaSC;
	}

	private boolean esSoloIT(ActividadEJBDTO act){
		boolean esSoloIT = true; 
		
		try{
			Long petiNumero=act.getNumeroPeticion();
			
			Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			
			Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(petiNumero, new Integer(ComunInterfaces.ID_ACTIVIDAD_INSTALACION));
			
			if(peticionFlujoList==null || peticionFlujoList.isEmpty()){
				peticionFlujoList = peticionFlujoLocalHome.findActividad(petiNumero, new Integer(ComunInterfaces.ID_ACTIVIDAD_DESINSTALACION));
			}
			
			PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
			String[] id_ps = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CAMARA_MONITOREO").split(",");
			log.debug("id_ps: "+id_ps);
			boolean psCamara = false;
			for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
    			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
    		
    			Producto_servicioKey prseKey = new Producto_servicioKey(peticionFlujoLocal.getPrse_id());
    			Producto_servicioLocal productoServicioLocal = productoServicioLocalHome.findByPrimaryKey(prseKey);
    			Familia_producto_servicioKey fampsKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
    			
				for (int i = 0; i < id_ps.length; i++) {
					Long id = Long.valueOf(id_ps[i]);
					log.debug("PS_ID: "+prseKey.ps_id);
					if(id.equals(prseKey.ps_id)){
						psCamara = true;
						break;
					}
				}
				log.debug("psCamara: "+psCamara);
   				if (fampsKey.faps_id.intValue()!=ComunInterfaces.familiaIntEquipado || psCamara){
   					esSoloIT = false;
   				}
   			}
		}catch(FinderException ex){
			log.debug("Se presento un error consultando los ps de la petición");
			esSoloIT=false;
		}catch(NamingException ex){
			log.debug("Se presento un error consultando los ps de la petición");
			esSoloIT=false;
		}catch(Exception ex){
			log.debug("Se presento un error consultando los ps de la petición");
			esSoloIT=false;
		}
		
		
		return esSoloIT;
	}
	

	/**
	 * Metodo encargado de realizar toda la logica que tenia la petición antes de Internet Total
	 * @author mfmendes
	 * @param act Actividad
	 * @param nroPeticion numero de la petición
	 */
	private void ejecutarFuncionamientoActividad(ActividadEJBDTO act, Long nroPeticion){
		String esReversa = act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa);//AT-1665

		String verif_Flujo = act.getDato(DATOS_ATVPISTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok);//AT-1665
		Integer IdActividad = act.getIdActividadFlujo();
		boolean esCRE = false;
		boolean isInhibioInstalar = false;
		boolean peticionTipoBaja = false;
		boolean esPsIPFija = false;
		boolean psUnico = false;
		String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
		Collection psPeticion = null;
		
		PeticionKey peticion_Key = new PeticionKey(act.getNumeroPeticion());
		PeticionLocalHome peticionLocal_Home;
		PeticionLocal peticion_Local = null;
		try {
		   peticionLocal_Home = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			peticion_Local = peticionLocal_Home.findByPrimaryKey(peticion_Key);
			psPeticion = peticion_Local.getProducto_servicio_peticion();
		
		} catch (NamingException e2) {
		    log.debug("No se pudo obtener PeticionLocalHome", e2);
		}
		 catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
		 	log.debug("No se pudo obtener PeticionLocalHome", e);
		}

		//CR24918 - TV Masivo - PCawen
		int nroDecos = 0;
		nroDecos = getNroDecos(nroPeticion);
		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "S");
		 //Si es instalacíon TV con mas de 10 decos, inhibo actividad.
		 if(nroDecos >= ComunInterfaces.NRO_DECOS_MASIVO){
		 	log.debug("Seteamos RealizarTerminoInmediato en true!");
			act.setRealizarTerminoInmediato(true);
			act.setObservacion("Se inhibe la actividad de Instalacion por ser de tipo Masivo");
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
			log.debug("Se finaliza la Actividad Instalar para Instalación TV[" + nroPeticion + "]");
		 }else{
			 //Se agrega la siguiente validación para Cambio de Plan
			if((verif_Flujo!=null) && (act.getIdActividadFlujo()!= null )&&(verif_Flujo.equals("S"))&&(IdActividad.intValue()== 9)){
				log.debug("Inicio Actividad Instalar para Cambio de plan [" + nroPeticion + "]");
				try {
						RecursosBADelegate recursosBADelegate;
						if(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok)){
						// logica
							recursosBADelegate = new RecursosBADelegate();
					
							if(!recursosBADelegate.huboCambiosPuertoIp(nroPeticion)){	
								/*
								 * entra por aca si no hubo modificaciones en
								 * los ips o puertos
								 */
								log.debug("No hubo cambios de puerto ni ip, ahora se pregunta si existen otros PS que necesiten entrar a la Actividad Instalar.");
								//Correccion Error de produccion AT-2487
								// 18/08/2009
								if (!recursosBADelegate.seDebeEntrarAInstalar (act.getNumeroPeticion(),act.getIdActividadFlujo())) {
									log.debug("No existen PS que deban pasar por la Actividad Instalar.");
									/*
									 * si entra a este if es por que NO se debe
									 * entrar a la Actividad instalar entonces
									 * se termina la actividad, este es el caso
									 * en el que no hubo modificaciones en las
									 * ips y puertos , y ademas no hay otros
									 * productos que necesiten instalacion
									 */
									isInhibioInstalar = true;
									log.debug("Seteamos RealizarTerminoInmediato en true!");
									act.setRealizarTerminoInmediato(true);
									act.setObservacion("Se inhibe la actividad de Instalacion por ser el mismo puerto");
									//act.setNoTerminar
									act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
									log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
								}	
							}else{
								//AT-2143 Error en flujo de cambio de numero
								// sin cruzada
								RecursosDelegate recServBean = new RecursosDelegate();
								if(recServBean.tieneCambioNumeroLB(act.getNumeroPeticion())){
									if(!recServBean.esCruzada(act.getNumeroPeticion())){
//										si no hay necesidad de cruzada se inhibe la actividad instalar
										log.debug("Seteamos RealizarTerminoInmediato en true!");
										act.setRealizarTerminoInmediato(true);
										act.setObservacion("Se inhibe la actividad de Instalacion por que no hay necesidad de cruzada");
										isInhibioInstalar = true;
										//act.setNoTerminar
										act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
									}
								}
							}
						}
				
				} 
				catch (Exception e) {
						// TODO: handle exception
				}
				log.debug("Fin Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
			}else if((act.getIdActividadFlujo()!= null )&&(IdActividad.intValue()== 13)){
				try {
					PeticionesInterfaces pI= new PeticionesDelegate();
					esCRE = pI.getPeti_CRE(act.getNumeroPeticion());		
					//Si peticion contiene ps de pdti o ps de linea basica
					// inhibo actividad
					//if (petiId.indexOf("PDTI") > -1) {
					if(esCRE){
						act.setRealizarTerminoInmediato(true);
						act.setObservacion("Se inhibe la actividad de Desinstalar ya que la petición contiene un ps que requiere no ir a la casa del cliente");
					
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok, "N");
						log.debug("Inhibo DesinstalarCRE ya que la petición para por otra actividad que implica ir a la casa del cliente");				
						return;
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			//Raúl: Se hace validación para ver si la peticion solo trae
			// equipos de IT, si es asi se inhibe esta actividad
			try{
				String psIntEquipado=VpistbbaConfig.getVariable("PS_INT_EQUIPADO_PC_TV");
				String psKioscos = VpistbbaConfig.getVariable("PS_ID_KIOSKO_WIFI");
				String[] listaIntEquipado=psIntEquipado.split(",");
				String[] listaKioscos=psKioscos.split(",");
				
            	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
            	PeticionKey peticionKey = new PeticionKey(nroPeticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				boolean esPSPCTV = false;
				boolean esPSKiosco = false;
				
        		Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, act.getActividadBD().getIdActFlujo());
				
        		forPadre: for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
        			
        			for(int contPsIntEquipado=0;contPsIntEquipado<=listaIntEquipado.length-1;contPsIntEquipado++){
        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaIntEquipado[contPsIntEquipado])){
        					esPSPCTV = true;
        					break forPadre;
        				}
        			}
        			
        			for(int contPsKiosco=0;contPsKiosco<=listaKioscos.length-1;contPsKiosco++){
        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaKioscos[contPsKiosco])){
        					esPSKiosco = true;
        					break forPadre;
        				}
        			}
        		}
        		
				if (!esPSPCTV){
					//Si las validaciones anteriores indican que se debe inhibir la actividad se valida si la actividad se
					//requiere para instalar equipos
					if (esSoloIT(act)){
						boolean esVentaCorporativos=esVentaCorporativos(peticionLocal);
    					if (!act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_IE)&&!esVentaCorporativos){
    						log.debug("Se inhibe la actividad de instalación pues tiene solo equipos de familia IT y no tiene ps de camara de monitoreo remoto");
    						act.setRealizarTerminoInmediato(true);
    						act.setObservacion("Se inhibe la actividad de instalación pues tiene solo equipos de familia IT y no tiene ps de camara de monitoreo remoto");
    						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
    						isInhibioInstalar = true;
    						log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
    					}
    					else if (act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_IE)&&esVentaCorporativos){
    						log.debug("Se inhibe la actividad de instalación pues tiene solo equipos de familia IT");
    						act.setRealizarTerminoInmediato(true);
    						act.setObservacion("Se inhibe la actividad de instalación pues tiene solo equipos de familia IT");
    						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
    						isInhibioInstalar = true;
    						log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
    					}

					
					
					}else{
    					boolean esVentaCorporativos=esVentaCorporativos(peticionLocal); 
    					if(act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_IE) && esVentaCorporativos){
    						log.debug("Se inhibe la actividad de instalación equipos pues tiene solo equipos de familia IT");
    						act.setRealizarTerminoInmediato(true);
    						act.setObservacion("Se inhibe la actividad de instalación equipos pues tiene solo equipos de familia IT");
    						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
    						isInhibioInstalar = true;
    						log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
    					}
    					if (!esPSKiosco){
    						if (act.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_IE)){
    							log.debug("Se inhibe la actividad de instalación IE pues tiene equipos de otras familias diferentes a IT");
    							act.setRealizarTerminoInmediato(true);
    							act.setObservacion("Se inhibe la actividad de instalación IE pues tiene equipos de otras familias diferentes a IT");
    							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
    							isInhibioInstalar = true;
    							log.debug("Se finaliza la Actividad Instalar para Cambio de Plan[" + nroPeticion + "]");
    						}
    					}
    				}
				}
			}catch (NamingException e) {
				log.debug("Error al obtener recursos para ubicar restricciones de Venta de equipos: "+e.toString());
			}catch (FinderException e) {
				log.debug("Error al obtener recursos para ubicar restricciones de Venta de equipos: "+e.toString());
			}
			
			//Fin Raúl
			
			Iterator iterador = act.getPsOk().iterator();
			while(iterador.hasNext()){
				PsVsOcVO psTemp= (PsVsOcVO) iterador.next();
				if(psIpFija != null && psIpFija.length() > 0){
					String[] tokens = null;
					log.debug("Los valores de psIpFija: " + psIpFija);
					tokens = psIpFija.split(",");
					for (int i = 0 ; i < tokens.length ; i++) {
						log.debug("se compara: "+ tokens[i] + " con: " + psTemp.getPsId());
						if(tokens[i].equals(String.valueOf(psTemp.getPsId()))){
							esPsIPFija = true;
							log.debug("es PsIPFija");
//							REQ BA NAKED adicion familia PC naked
							if ( ((psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAncha) 
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcBA)
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcPsBANaked)
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaBandaAnchaNaked)) 
									&& (psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoBaja) || peticion_Local.getTica_id().equals(ComunInterfaces.opCoTipoBaja))){
								peticionTipoBaja = true;
								log.debug("es peticionTipoBaja");
							}
						}
					}
				}
			}
			
			Iterator listaPsPetIt=null;
			int numPS = 0;
			for(listaPsPetIt=psPeticion.iterator();listaPsPetIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaPsPetIt.next();
				Producto_servicioLocal producto_servicioLocal = (Producto_servicioLocal) producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
				int FamiliaTem = familia_producto_servicioKey.faps_id.intValue(); ;
//				REQ BA NAKED adicion familia PC y PS naked
				if((FamiliaTem == ComunInterfaces.familiaBandaAncha
						|| FamiliaTem == ComunInterfaces.familiaPcBA
						|| FamiliaTem == ComunInterfaces.familiaPcPsBANaked
						|| FamiliaTem == ComunInterfaces.familiaBandaAnchaNaked)
						&& (peticion_Local.getTica_id().equals(ComunInterfaces.opCoTipoBaja)))
					numPS++;
			}
			
			if(numPS <= 1){
				psUnico = true;
			}
			if(peticionTipoBaja && esPsIPFija && !psUnico){
				log.debug("Se inhibe la actividad de instalación IE pues tiene IP Fija y es una baja");
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad de instalación IE pues tiene IP Fija y es una baja");
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
				isInhibioInstalar = true;
				log.debug("Se finaliza la Actividad Instalar para Baja[" + nroPeticion + "]");			
			}
		 	/**
			  * DAVID: Si es localidad Agenda SC, se procede a enviar la TR 701E
			  * con el método creacionActuacionAgendaSC. La respuesta recibida
			  * de Agenda SC, por el método recepcionCreacionActuacionAgendaSC
			  * derivará la petición a PGI o a Control instalación según el
			  * parámetro error code de la TR701S.
			  */
			boolean esAgendaSC=esAgendaSC(act);		 			 	
			if(esAgendaSC && isInhibioInstalar == false){
		 		try {
		 			log.debug("La Peticion va a Generar la Actuacion ");
		 			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		 			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
		 			boolean tieneActuacionAbiertas = false;
		 			
					PeticionKey peticionKey = new PeticionKey(nroPeticion);
					PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
					
					Collection agendamientos = agendaSCLocalHome.findByPetiNumero(nroPeticion);
					for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
						Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
						
						if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
								|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
							tieneActuacionAbiertas = true;
							break;
						}
					}
		 			
		 			if ((peticionLocal.getEstado_agend_sc() == null) 
		 					|| (peticionLocal.getEstado_agend_sc().intValue() == ComunInterfaces.ESTADO_AGENDA_SC_SIN_MARCA)
							|| !tieneActuacionAbiertas){
		 				Timestamp fechaReagendamiento = null;
		 				
		 				try{
		 					Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findFechasByPetiNum(nroPeticion);
		 					fechaReagendamiento = agendaSCLocal.getFecha_reagm();
		 				}catch (FinderException e) {
							log.debug("La petición no tiene reagendamiento: "+e.toString());
							fechaReagendamiento = null;
						}
		 				
		 				AgendaServicioDelegate agendaDelegate=new AgendaServicioDelegate();
		 				agendaDelegate.enviarCreacionActuacion(act.getNumeroPeticion(), fechaReagendamiento, "", act.getCodigoActividad(), act);
					}else if (peticionLocal.getEstado_agend_sc().intValue() == ComunInterfaces.ESTADO_AGENDA_SC_CON_MARCA 
 							&& tieneActuacionAbiertas){
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
						PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
						boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(nroPeticion);
						boolean esActuacionCCF = peticionesDelegate.esActuacionCCF(nroPeticion);
					
						if(esAutoInstalacionSoloBA){
							//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
							act.setObservacion("Se envía directamente a Control Cruzada BA porque la petición: "+nroPeticion+" es auto instalación");
							act.setRealizarTerminoInmediato(true);
						}else if (esActuacionCCF){
							Timestamp fechaReagendamiento = null;
							AgendaServicioDelegate agendaDelegate=new AgendaServicioDelegate();
			 				agendaDelegate.enviarCreacionActuacion(act.getNumeroPeticion(), fechaReagendamiento, "", act.getCodigoActividad(), act);
						}else{
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
							act.setObservacion("Se envía directamente a Control instalación porque la petición: "+nroPeticion+" trae marca");
							act.setRealizarTerminoInmediato(true);
						}
					}else if (peticionLocal.getEstado_agend_sc().intValue() == ComunInterfaces.ESTADO_AGENDA_SC_CON_MARCA_PGC 
 							&& tieneActuacionAbiertas){
						log.debug("Se inhibe la actividad porque se creo una actuación desde PGC y se encuentra abierta, para la petición Atiempo No. "+nroPeticion);
			        	String mensajeBitacora = "Se inhibe la actividad porque ya existe una actuación abierta, creada desde PGC.";
						
			        	log.debug("Mensaje Bitacora = "+mensajeBitacora);
			        	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
						act.setObservacion(mensajeBitacora, true);
						act.setRealizarTerminoInmediato(true);
					}
		 		}catch (NamingException e) {
					log.debug("Error al obtener recursos BA delegate para cración de actuación AgendaSC: "+e.toString());
				}catch (FinderException e) {
					log.debug("Error al obtener recursos BA delegate para cración de actuación AgendaSC: "+e.toString());
				}catch (ATiempoAppEx e) {
					log.debug("Error al obtener recursos BA delegate para cración de actuación AgendaSC: "+e.toString());
				}
		 		
			}
		 	//Fin DAVID.
			try{
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(nroPeticion);
				if(esAutoInstalacionSoloBA&& isInhibioInstalar == false&&!esAgendaSC){
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
					act.setObservacion("Se envía directamente a Control Cruzada BA porque la petición: "+nroPeticion+" es auto instalación");
					act.setRealizarTerminoInmediato(true);
				}else if(isInhibioInstalar && 
						(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok)==null || 
						act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals(""))){
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
					act.setObservacion("Ningun PS aplica para realiza");
					act.setRealizarTerminoInmediato(true);
				}
			}catch(ATiempoAppEx e){
				log.debug("Error al verificar si es petición de auto instalación solo BA.");
			}
		 }	 
	}

	/**
	 * @param peticionLocal
	 * @return
	 */
	private boolean esVentaCorporativos(PeticionLocal peticionLocal) {
		try{
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			if(peticionLocal.getCod_sgm_cta_cd()==null ){
				return false;
			}
			String segCorporativos=peticionesDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.SEGMENTOS_CORPORATIVOS);
			if(segCorporativos!=null){
				String[] segm= segCorporativos.split(",");
				for (int i = 0; i < segm.length; i++) {
					if(segm[i].equals(peticionLocal.getCod_sgm_cta_cd().toString())){
						return true;
					}
				}
			}
			return false;
		}catch (Exception e) {
			log.error("Erro robteniendo segmentos de la peticion ",e);
		}
		return false;
	}
}