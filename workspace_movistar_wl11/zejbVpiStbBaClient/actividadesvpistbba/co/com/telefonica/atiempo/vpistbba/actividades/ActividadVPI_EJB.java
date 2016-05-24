/*
 * Created on 09-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJB;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.vpistbba.asignacion.session.AsignacionLocal;
import com.telefonica_chile.vpistbba.asignacion.session.AsignacionLocalHome;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;
import com.telefonica_chile.vpistbba.datos_publicacion.session.DatosPublicacionLocal;
import com.telefonica_chile.vpistbba.datos_publicacion.session.DatosPublicacionLocalHome;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ActividadVPI_EJB extends ActividadEJB {

	 
	protected abstract void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion ;
	protected abstract void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion ;
	
	protected final DatosPeticion generaDatosPublicacion(ActividadEJBDTO act){
		DatosPeticion objDatoPeticion=null;
		String logUser="";
		try {
			DatosPublicacionLocalHome datosHome =
				(DatosPublicacionLocalHome)HomeFactory.getHome(DatosPublicacionLocalHome.JNDI_NAME);
			DatosPublicacionLocal datosLocal = datosHome.create();
			objDatoPeticion = datosLocal.getDatosPublicacion(act.getNumeroPeticion(),act.getCodigoActividad());
				
		} catch (NamingException e) {
			
			log.debug("NamingException [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]",e);
		} catch (CreateException e) {
			
			log.debug("CreateException [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]",e);
		}
	
		objDatoPeticion.setUrlDetalle(act.getUrlBandeja());
		Long idUser=this.getIdUsuarioResponsable(act); 
		act.setIdUsuario(idUser); //Seteo el ID del usuario al Cual Publico en la bandeja
		
		// CR29928 - adocarmo - inicio
		// Se setea en true el atributo que indica que ya se realizo la publicación en la bandeja
		act.setSePublicoPeticion(true);
		log.debug(">>>> Indico que se publico la peticion seteando " + act.getSePublicoPeticion() + " al atributo sePublicoPeticion");
		// CR29928 - adocarmo - fin
		
//		TODO: No utilizo el user en la publicacion... uso el Id IUsuario
		objDatoPeticion.setUsernameResponsable(logUser);
//		TODO:El id responsable va con el Id del Usuario
		objDatoPeticion.setIdResponsable(act.getIdUsuario());
		
		return objDatoPeticion;
		
	}
		//TODO ASIGNACION
	private Long getIdUsuarioResponsable(ActividadEJBDTO act) {
		Long usuario = null;
		AsignacionLocalHome home = null;
		AsignacionLocal asignacion = null;
		
		// CR29928 - adocarmo - inicio
		log.debug(">>>> Ya se publico la peticion:" + act.getSePublicoPeticion());
		// CR29928 - adocarmo - fin
		
		try {
			home = (AsignacionLocalHome) HomeFactory.getHome(AsignacionLocalHome.JNDI_NAME);
			asignacion = home.create();
			//usuario = asignacion.obtenerUsuario(idInstanciaProceso, codigoActividad);
			usuario = asignacion.getIdUsuario(act.getNumeroPeticion().toString(), new Long(1), act.getCodigoActividad());
			log.debug("Usuario Responsable:"+usuario);
		} catch (NamingException e) {
			
			log.error("Obteniendo Usuario: ", e);
		} catch (CreateException e) {
			
			log.error("Obteniendo Usuario: ", e);
		}

		// Si el Usuario es NULO.
		if (usuario == null) {
			log.info("No se encontro Usuario para Asignar [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]");
			return act.getIdUserDummy();
		}

		return usuario;
	}	
	
	protected final void onInicio(ActividadEJBDTO actEjb) throws TnProcesoExcepcion {
		log.debug("Inicio onInicio Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
	try {
		//Si es reversa o no es actividad flujo o PGI o PGC, no propaga nada.
		if ( (!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) 
			  || !actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			  && actEjb.getIdActividadFlujo()!=null && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()
			  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue()
			  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue()
			  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue()
			  //&& !"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(actEjb.getCodigoActividad())){
			  && actEjb.getIdActividadFlujo().intValue()!= new Integer("141").intValue() 
			  && actEjb.getIdActividadFlujo().intValue()!= new Integer("142").intValue() 
			 //CR9664 - adocarmo - inicio
			 && (!"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(actEjb.getCodigoActividad()))
			 && (!"Director de Flujos.Configuracion_Aula.Control_Configurar_Aula".equals(actEjb.getCodigoActividad()))) {
			 //CR9664 - adocarmo - fin			  				  	
			  	
			//Heredo los quiebres que viene en los PS que pasan por esta actividad		
			PeticionesInterfaces pI=new PeticionesDelegate();
			pI.propagaCausasPeticion(actEjb.getNumeroPeticion(),actEjb.getActividadBD().getIdActividad(),actEjb.getIdActividadFlujo(),new Long(1));
		}

		//Si hay ps Ok se realiza la actividad	
		if (actEjb.getPsOk().size()>0 && !actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)){      
			this.onInicioActividad(actEjb);
			log.debug("isRealizarTErminoInmediato = "+actEjb.isRealizarTerminoInmediato() );	
			log.debug("Hay PS de la actividad con Estado OK. Se realiza la actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
		}//si no, se realiza el termino de la actividad enseguida
		else{
			//Si es una actividad del director de flujos y no es PGI ni PGC, se da por terminada si no hay PS para trabajar			
			if(!actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)
					&& actEjb.getIdActividadFlujo()!=null && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()
					  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue()
					  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue()
					  && actEjb.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue()){
				actEjb.setRealizarTerminoInmediato(true);    
				
				if(!actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
					actEjb.setObservacion("No hay PS de la actividad con Estado OK. Se cierra la actividad sin realizar trabajos.");
					log.debug("No hay PS de la actividad con Estado OK. Se cierra la actividad sin realizar trabajos (actividad " + actEjb.getCodigoActividad() + ")");
				}
				else{
					actEjb.setObservacion("No hay PS de la actividad con Estado OK que se hayan Cancelado Posteriormente. Se cierra la actividad sin realizar trabajos.");
					log.debug("No hay PS de la actividad con Estado OK que se hayan Cancelado Posteriormente. Se cierra la actividad sin realizar trabajos.");
				}
			}
			
			
		}
//		Se procede a registrar en bitacora si corresponde		
		ActividadDTO actBD= actEjb.getActividadBD();
		boolean grabar = true;

//			ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
//			ActividadSessionLocal actLocal = actHome.create();
//			act = actLocal.recuperaActividad(this.getCodigoActividad(), this.idAplicacion);

			// GRANITE
			// Verifico que sea actividad que Graba en bitacora
			//if ( actBD.getActManual()==null || actBD.getActManual().intValue()!=1 )
			//log.debug("actBD.getActManual():" + actBD.getActManual());
			//log.debug("actEjb.isGrabarEnBitacora():" + actEjb.isGrabarEnBitacora());
			if ( actBD.getActManual()==null || actBD.getActManual().intValue()!=1  || !actEjb.isGrabarEnBitacora())
				grabar = false;

			if (grabar){
				//Cuando es una actividad que publica, el usuario ya está asignado.
				//Si no esta asignado es por que es una actividad automática, entonces se llama al metodo para asignar usuario
				// CR29928 - adocarmo - inicio
				//if (actEjb.getIdUsuario().longValue()==actEjb.getIdUserDummy().longValue()){
				
				// Si ya se publico la peticion en la bandeja, en la bitacora guardo el mismo usuario. 
				// Si no se publico en la bandeja, para setear el usuario en la bitacora invoco al algoritmo de asignacion
				
				log.debug(">>>> Ya se publico la peticion:" + actEjb.getSePublicoPeticion());
				if (!actEjb.getSePublicoPeticion()){
				// CR29928 - adocarmo - fin					
					actEjb.setIdUsuario(this.getIdUsuarioResponsable(actEjb)); //Usuario automático		
				}
				boolean bit=false;
				BitacoraLocalHome creadorBitacora = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
				//boolean bit = creadorBitacora.create().crearRegistroBitacora(getNumeroPeticion(), 
				//	act.getIdActividad(), user.getId());
				if ( actEjb.isRealizarTerminoInmediato() || actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA) ){
					log.debug("Creo Bitacora Cerrada Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					//Si se termina de inmediato se crea una bitacora cerrada y luego se va a terminar. No crea registro en wf_instancia
					bit = creadorBitacora.create().crearRegistroBitacoraCerrado( actEjb.getNumeroPeticion(),	actBD.getIdActividad(), actEjb.getIdUsuario(), actEjb.getObservacion());
				}
				else{
					log.debug("Creo Bitacora Abierta Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					//Se crea un registo bitacora abierto
					bit = creadorBitacora.create().crearRegistroBitacora( actEjb.getNumeroPeticion(),	actBD.getIdActividad(), actEjb.getIdUsuario(),actEjb.getObservacion());
				}
//				TODO: que pasa si falla la bitacora?				
			}
			//Las automáticas pueden o no Terminarse de inmediato. Las Manuales no se terminan de inmediato porque se publican
			if ( actEjb.isRealizarTerminoInmediato() ){
				// Si se termina de inmediato se va a terminar y crea una bitacora cerrada. No crea registro en wf_instancia
				this.terminar(actEjb);
			}
			else{				
//				Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. 
//				Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url 
//				de la tabla BIntegrada.
//				Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
				if (actEjb.isGrabarWfInstancia()){
					log.debug("Creo wfInstanciaActividad para Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					this.crearNuevaIntanciaActividad(actEjb);
					
				}
			}
			if(actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)){
				this.onInicioActividad(actEjb);
				log.debug("isRealizarTErminoInmediato = "+actEjb.isRealizarTerminoInmediato() );	
				log.debug("Hay PS de la actividad con Estado OK. Se realiza la actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
				if ( actEjb.isRealizarTerminoInmediato() ){
					// Si se termina de inmediato se va a terminar y crea una bitacora cerrada. No crea registro en wf_instancia
					this.terminar(actEjb);
				}
			}
			log.debug("Fin onInicio Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());			
		} catch (NamingException e) {
			log.debug("NamingException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("NamingException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);// + "," + getUsuarioAsignado() + "]");
		} catch (CreateException e) {
			log.debug("CreateException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("CreateException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);//+ "," + getUsuarioAsignado() + "]");
//		} catch (UsuariosException e) {
//			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
		} catch (Exception e) {
			log.debug("TnProcesoExcepcion Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);//+ "," + getUsuarioAsignado() + "]");
		}		
		

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#onTermino()
	 */
	protected final void onTermino(ActividadEJBDTO actEjb) throws TnProcesoExcepcion {
		this.onTerminoActividad(actEjb);
		log.debug("Inicio onTermino Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
//		Se cierra la bitacora si corresponde				
		boolean grabar = true;
		Integer ordenFlujo = new Integer(-1); 
		try {
			ActividadDTO act= actEjb.getActividadBD();
			/**
			 * Se adicionan para asistencia las dos instancias siguientes
			 * req, 33626 
			 */
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome (Producto_servicioLocalHome.JNDI_NAME);
			//Fin asistencia
			
			if(actEjb.isNoTerminar()){ //no hago nada mas que modificar la bitacora
				if (actEjb.getObservacion() != null){
					BitacoraLocalHome creadorBitacoraT = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
					BitacoraLocal bpT = creadorBitacoraT.create();
					log.debug("Cambio Observacion Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					boolean resultGrabaBita = bpT.actualizaObservacionBitacora(actEjb.getNumeroPeticion(), act.getIdActividad(), actEjb.getObservacion(),actEjb.getOverwriteObs());
				}
				return;
			}
//			if ( !actEjb.isRealizarTerminoInmediato() ){
//			No es una actividad con cierre inmediato, por lo tanto se debe cerrar la bitacora que dejó abierta.							
				Long userCierreID=actEjb.getIdUsuarioCierre(); 
				if (userCierreID.longValue()==actEjb.getIdUserDummy().longValue()){ //Si no se ha seteado userid de cierre, es por que la cierra el mismo usuario que abrió la bitacora
					userCierreID = null; //Si es null deja el mismo usuario que abrio la bitacora
				}
				
//				ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
//				ActividadSessionLocal actLocal = actHome.create();
//				act = actLocal.recuperaActividad(this.getCodigoActividad(),this.idAplicacion);
				
				// Verifico que sea actividad que Graba en bitacora
				if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
					grabar = false;
				
				if (grabar) {				
					BitacoraLocalHome creadorBitacora = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
					BitacoraLocal bp = creadorBitacora.create();
					// se asume que la bitacora existe para esta actividad
					log.debug("Cierro Bitacora Actividad:" + actEjb.getCodigoActividad());
					boolean resultGrabaBita = bp.cierra(actEjb.getNumeroPeticion(), act.getIdActividad(), userCierreID, actEjb.getObservacion(),actEjb.getIdCausaCierre(),actEjb.getOverwriteObs());
//					TODO: que pasa si falla la bitacora?
				}
//			}
				log.debug("Es actividad vacia: "+actEjb.isVacia());
			if(!actEjb.isVacia()){
				// Marco el paso por el subflujo Actividad. En la reversa no marca nada.
				if (act.getIdActFlujo()!=null){
//						Si no es reversa
					log.debug("Entro a informar el estado del la actividad para peticion: "+actEjb.getNumeroPeticion()+" y actividad: "+act.getIdActFlujo());
					Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
					Collection peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),act.getIdActFlujo());
					if(!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) || actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("")) {
//						Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//						Collection peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),act.getIdActFlujo());
//							Si no va la PGI se setea OK
						//Si no va a PGF se setea OK
						if(!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || ((!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC")))) &&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGF"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGACS")))))){
							for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
								log.debug("Marco OK Subflujo Actividad:" + actEjb.getCodigoActividad());						
								Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
								if(!actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR)
										&& !actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)){
									log.debug("Marco OK Subflujo Actividad:" + actEjb.getCodigoActividad());						
//									Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
									peticionFlujo.setPefl_estado("OK");
									if(actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
											|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
										ordenFlujo = peticionFlujo.getPefl_orden();
										log.debug("Viene cierre de Actuación con orden: "+peticionFlujo.getPefl_orden());
										actEjb.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
									}
									
								}else{
									ordenFlujo = peticionFlujo.getPefl_orden();
									if(actEjb.isRealizarTerminoInmediato())
										peticionFlujo.setPefl_estado("OK");
								}
							}
						}
						else{
							for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
								log.debug("Marco NOOK Subflujo Actividad:" + actEjb.getCodigoActividad());						
								Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
								peticionFlujo.setPefl_estado("NOOK");
								actEjb.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
							}
						}
					}else{
						for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
							log.debug("obtengo el orden de la reversa" );						
							Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
							ordenFlujo = peticionFlujo.getPefl_orden();
						}
					}
				}
			}
			if ( !actEjb.isRealizarTerminoInmediato() ){ // Se cambia estado a la instanciaWF de la bd a las actividades que no se cierran de inmediato y por lo tanto crean un registro wf_instancia
				if (actEjb.isGrabarWfInstancia()){
					log.debug("Elimina Instancia="  + actEjb.isGrabarWfInstancia() + ":" + actEjb.getNumeroPeticion());
//					Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
//					Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad					
					this.eliminarInstanciaActividad(actEjb);
				}
			}
//			Propago los erros de los PC a los ps de la agrupacion que no estan con error. Solo para las actividades que estan en una actividad Flujo.
			if (actEjb.getIdActividadFlujo()!=null){
				PeticionesInterfaces pI=new PeticionesDelegate();
				ArrayList psAct= new ArrayList();
				//si es PGC o PGI revisa todos los ps de la peticion.
				if (actEjb.getIdActividadFlujo().intValue()== new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue() ||actEjb.getIdActividadFlujo().intValue()== new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue() || actEjb.getIdActividadFlujo().intValue()== new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue() || actEjb.getIdActividadFlujo().intValue()== new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue())
				{
					psAct = pI.listaDePsDePeticion(actEjb.getNumeroPeticion());
				}
				else{
					psAct = pI.listaDePsDelaActividadEstadoFinal(actEjb.getNumeroPeticion(),actEjb.getIdActividadFlujo()); 
				}
				log.debug("PsAct size:"+psAct);
				Iterator iterPs = psAct.iterator();
				

				while (iterPs.hasNext()){
					PsVsOcVO psTemp= (PsVsOcVO)iterPs.next();
					//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
					Long faPsId = psTemp.getFaPsId();
//					REQ BA NAKED adicion familia PC naked
					if(!psTemp.isOk() && (faPsId.intValue()== ComunInterfaces.familiaPcLinea || faPsId.intValue()== ComunInterfaces.familiaIP  || faPsId.intValue()== ComunInterfaces.familiaPcBA || faPsId.intValue()== ComunInterfaces.familiaPcPsBANaked ||faPsId.intValue()== ComunInterfaces.familiaPcTV))
					{
						log.debug("Voy a propagar Quiebre Pc a Ps:"+actEjb.getNumeroPeticion()+" - "+psTemp.getPsId()+ " - "+psTemp.getOpComId());
						pI.propagaQuiebrePCaPS(actEjb.getNumeroPeticion(),psTemp.getPsId(),psTemp.getOpComId());
						
						/**
						 * Para req de Asistencia, 33626
						 */
						if(!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) || actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("")) {
							Collection listaFAmiliaAS=psPetHome.findByIdFamiliaPs(new Long(ComunInterfaces.familiaAsistencia),actEjb.getNumeroPeticion());
	
							Iterator listaFAmiliaASIt=null;
							Producto_servicioLocal PsFamiliaAS=null;
							Producto_servicio_peticionLocal PsPetFamAS=null;
								for(listaFAmiliaASIt=listaFAmiliaAS.iterator();listaFAmiliaASIt.hasNext();){
									PsPetFamAS=(Producto_servicio_peticionLocal)listaFAmiliaASIt.next();
									PsFamiliaAS=(Producto_servicioLocal)PsPetFamAS.getProducto_servicio();
									log.debug("VIENE PS DE ASISTENCIA en ActividadVPI_EJB: "+PsPetFamAS.getNom_pro_ser_no());
									Producto_servicioKey keyPsAsist=new Producto_servicioKey(PsPetFamAS.getPsId());
		       						int tipoPsAsistencia=pI.esPsAsistenciaBAoLB(keyPsAsist);
//		       					REQ BA NAKED adicion familia PC naked
									if((tipoPsAsistencia==1&&faPsId.intValue()== ComunInterfaces.familiaPcBA)
										|| (tipoPsAsistencia==1&&faPsId.intValue()== ComunInterfaces.familiaPcPsBANaked)){			
										pI.propagaQuiebresLBoBAaAsistencia(actEjb.getNumeroPeticion(),"BA",act.getIdActividad());
									}else if(tipoPsAsistencia==2&&faPsId.intValue()== ComunInterfaces.familiaPcLinea){			
										pI.propagaQuiebresLBoBAaAsistencia(actEjb.getNumeroPeticion(),"LB",act.getIdActividad());
									}else if(tipoPsAsistencia==2&&faPsId.intValue()== ComunInterfaces.familiaIP){
										pI.propagaQuiebresLBoBAaAsistencia(actEjb.getNumeroPeticion(),"IP",act.getIdActividad());
									}
									break;
							}
						}
						/**
						 * Fin: Para req de Asistencia, 33626
						 */
							
					}else{
						log.debug("No se propago quiebre Pc a PS: faPsId= "+faPsId+" ,psTemp.isOk="+psTemp.isOk());
					}
				}

			}
			if((!actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR)
					&& !actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA))
					|| (actEjb.isRealizarTerminoInmediato())){
				
				//Si la actividad es desinstalar y la localidad es TOA, se inhibe la subactividad Control Desinstalacion
				//sfandino - 17/09/2015
				if (actEjb.getCodigoActividad().equals(
						ComunInterfaces.ACT_DES_INSTALAR)) {
					log.debug("Es actividad Desinstalar");
					PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					PeticionLocal petiLocal = peticionLocalHome.findByPetiNumero(actEjb.getNumeroPeticion());
					
					if (petiLocal.getFk_01_localidad().getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA) {
					
						log.debug("Es localidad TOA");
						actEjb.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
					}
				}
				
				if((actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA))
						&& (!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || ((!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC")))) &&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGF"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGACS"))))))
						&& (!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) || actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals(""))){
					log.debug("Se setea orden: "+ordenFlujo+"para actividad: "+actEjb.getCodigoActividad());
					actEjb.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,ordenFlujo.toString() );
				}else{
					if((actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA))
						&& (!actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || ((!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC")))) &&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGF"))))&&(!(actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGACS"))))))
						&& actEjb.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) 
						&& actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
						Integer OrderReversa = new Integer(ordenFlujo.intValue() -1);
						log.debug("Se setea orden de reversa: "+OrderReversa+"para actividad: "+actEjb.getCodigoActividad());
						actEjb.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,OrderReversa.toString() );
					}
				}
					
				
				//SE INGRESA CADA VEZ QUE NO SE NECESITE EMULAR EL WF EN LA SIGUIETNE ACTIVDAD TOA FASE III
//				Si es una actividad con cierre inmediato, su bitacora se creó cerrada, por lo tanto no es necesario cerrala	
//				Se envía en mensaje de cierre
				log.debug("Envio Cierre Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
				this.enviaRespuesta(actEjb);			
				log.debug("Fin onTermino Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());			

			}else{
				//SE INGRERSA A ESTE ELSE CADA VEZ QUE SE NECESITE EMULAR EL WF DE LA SIGUIENTE ACTIVIDAD
				log.debug("Se llama el proceso para instanciar la siguiente tarea para la actividad: "+actEjb.getCodigoActividad()+ "Con orden: "+ ordenFlujo);
				if(actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_INSTALAR_TOA)
						|| actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA))
					
				new ObtenerSgteTarea2(actEjb, new Integer(ordenFlujo.intValue()));
				else
					new ObtenerSgteTarea2(actEjb, ordenFlujo);
			}
		} catch (NamingException e) {
			log.debug("NamingException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("NamingException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);// + "," + getUsuarioAsignado() + "]");
		} catch (CreateException e) {
			log.debug("CreateException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("CreateException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);//+ "," + getUsuarioAsignado() + "]");
//		} catch (UsuariosException e) {
//			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
		} catch (Exception e) {
			log.debug("TnProcesoExcepcion Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);
			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]",e);//+ "," + getUsuarioAsignado() + "]");
		}			
	}
	
	protected final void onCambioUsuario(ActividadEJBDTO actEjb, Long idUser) throws TnProcesoExcepcion{
		log.debug("Inicio onCambioUsuario Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
//		Se cierra la bitacora si corresponde				
		boolean grabar = true;
		try {
			if ( !actEjb.isRealizarTerminoInmediato() ){
//			No es una actividad con cierre inmediato, por lo tanto se debe cerrar la bitacora que dejó abierta.							
				ActividadDTO act= actEjb.getActividadBD();
				Long userCierreID=actEjb.getIdUsuarioCierre(); 
				if (userCierreID.longValue()==actEjb.getIdUserDummy().longValue()){ //Si no se ha seteado userid de cierre, es por que la cierra el mismo usuario que abrió la bitacora
					userCierreID = null; //Si es null deja el mismo usuario que abrio la bitacora
				}
				
//				ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
//				ActividadSessionLocal actLocal = actHome.create();
//				
//				act = actLocal.recuperaActividad(this.getCodigoActividad(),this.idAplicacion);
				
				// Verifico que sea actividad que Graba en bitacora
				if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
					grabar = false;
				
				if (grabar) {				
					BitacoraLocalHome creadorBitacora = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
					BitacoraLocal bp = creadorBitacora.create();
					// se asume que la bitacora existe para esta actividad
					log.debug("Cierro Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					boolean resultGrabaBita = bp.cierra(actEjb.getNumeroPeticion(), act.getIdActividad(), userCierreID, actEjb.getObservacion(),actEjb.getIdCausaCierre(),actEjb.getOverwriteObs());
					boolean bit = false;
					if (resultGrabaBita){
						log.debug("Creo Bitacora Abierta Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
						//Se crea un registo bitacora abierto para el nuevo user
						bit=creadorBitacora.create().crearRegistroBitacora( actEjb.getNumeroPeticion(),	act.getIdActividad(), idUser,"");
					}
					if (bit){
						//Cambio el usuario publicado
						this.cambiarUsuarioPublicacion(actEjb,idUser);
					}
//					TODO: que pasa si falla la bitacora o el cambio de user publicacion?
				}
			}
			log.debug("Fin onCambioUsuario Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
		} catch (NamingException e) {
			log.debug("NamingException",e);
			throw new TnProcesoExcepcion("NamingException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");// + "," + getUsuarioAsignado() + "]");
		} catch (CreateException e) {
			log.debug("CreateException",e);
			throw new TnProcesoExcepcion("CreateException Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
//		} catch (UsuariosException e) {
//			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
		} catch (Exception e) {
			log.debug("Exception",e);
			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Terminar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
		}	
		
	}


	//	CR9664 - adocarmo - inicio
	//Este metodo retorna los ps  que pasan por la actividad y que estan OK
	private void setPsActividadEstadoOK(ActividadEJBDTO act) throws ATiempoAppEx {
	
		try {
//			DAVID: Abril 27 2011, Si es auto instalación, reversa y está en control instalación con todos los ps's quebrados, no inhiba la actividad. 
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			boolean esAutoInstalacionSoloBA= peticionesDelegate.esAutoInstalacionSoloBA(act.getNumeroPeticion());
			
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				PeticionesInterfaces pI = new PeticionesDelegate();
				
				// adocarmo - CR9664 - inicio
				//if(act.getIdActividadFlujo()!=null && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()  && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue() && !"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad())){
				if(act.getIdActividadFlujo()!=null && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue() &&act.getIdActividadFlujo()!=null && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue() && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()  && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue() 
					&& !"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad())
					&& !"Director de Flujos.Configuracion_Aula.Control_Configurar_Aula".equals(act.getCodigoActividad())
					&& !(esAutoInstalacionSoloBA&&act.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION))
					)			
				{			
					//Recupero los ps asociados a la Actividad por el director de flujos
					act.setPsOk(pI.listaDePsDelaActividadEstadoOKFinal(act.getNumeroPeticion(),act.getIdActividadFlujo()));	
				}
				//else if("Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad()))
				else if(esAutoInstalacionSoloBA&&act.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
						||("Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad()))
						|| ("Director de Flujos.Configuracion_Aula.Control_Configurar_Aula".equals(act.getCodigoActividad())))
				{//Parche picante para pasar a la actividad de control terra con ps con quiebres
					//Busco los ps de la actividad sin importar su estado
					act.setPsOk(pI.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(),act.getIdActividadFlujo()));
				}else{
					//Recupero Todo los PS de la peticion
					act.setPsOk(pI.listaDePsDePeticion(act.getNumeroPeticion()));
				}
			}
			else{
				//Si es reversa
				PeticionesInterfaces pI = new PeticionesDelegate();
				//if(act.getIdActividadFlujo()!=null && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()  && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue() && !"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad())){
				if(act.getIdActividadFlujo()!=null&& act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGACS")).intValue() && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGF")).intValue()  && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGI")).intValue()  && act.getIdActividadFlujo().intValue()!= new Integer(VpistbbaConfig.getVariable("IDPGC")).intValue() 
					&& !"Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad())
					&& !"Director de Flujos.Configuracion_Aula.Control_Configurar_Aula".equals(act.getCodigoActividad())
					&& !(esAutoInstalacionSoloBA&&act.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION))
					){
					log.debug("No es PGI,PGC,GESTION");
					//Recupero los ps asociados a la Actividad por el director de flujos y que fueron puestos como OK en la actividad
					ArrayList psTemp=null;
					ArrayList psOk =new ArrayList();
					psTemp=pI.listaDePsDelaActividadConEstadoOKEnAct(act.getNumeroPeticion(),act.getIdActividadFlujo(),act.getActividadBD().getIdActividad());
					for (Iterator iter=psTemp.iterator();iter.hasNext();){
						PsVsOcVO psVO=(PsVsOcVO)iter.next();
						if(!pI.estaOKProductoServicioPeticion(act.getNumeroPeticion(),psVO)){
							psOk.add(psVO);
						}
					}
					act.setPsOk(psOk);
				}
				//error nullpointer
				//else if("Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad()))
				else if ((act.getIdActividadFlujo()!=null && act.getIdActividadFlujo().intValue()!= new Integer("141").intValue())
						|| (act.getIdActividadFlujo()!=null &&act.getIdActividadFlujo().intValue()!= new Integer("142").intValue())
						||esAutoInstalacionSoloBA && act.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
						||("Director de Flujos.Configuracion_Terra.Control_Configurar_Terra".equals(act.getCodigoActividad()))
				          || ("Director de Flujos.Configuracion_Aula.Control_Configurar_Aula".equals(act.getCodigoActividad())))
				{//Parche picante para pasar a la actividad de control terra con ps con quiebres
					//Busco los ps de la actividad sin importar su estado
					log.debug("Entra por Reversa con la actividad "+act.getIdActividadFlujo()+" de la peticion "+act.getNumeroPeticion());
					act.setPsOk(pI.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(),act.getIdActividadFlujo()));
				}			
				else{
					log.debug("Es Difernten.....");
					//Recupero Todo los PS
					act.setPsOk(pI.listaDePsDePeticion(act.getNumeroPeticion()));
				}			
			}

			for (Iterator iterI=act.getPsOk().iterator();iterI.hasNext();){
				PsVsOcVO psVO=(PsVsOcVO)iterI.next();
				log.debug("PS:" + psVO.getPsId());
			}
		} catch (Exception e) {
			log.debug("ActividadVPI_EJB.setPsActividadEstadoOK: ", e);
			log.error("ActividadVPI_EJB.setPsActividadEstadoOK: ", e);
		}
		
	}
	

	protected final void postInicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion{
		try {
				
			this.setPsActividadEstadoOK(act);
				
		} catch (ATiempoAppEx e) {
			log.debug("ATiempoAppEx Post Inicializar EJB [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]",e);
			log.debug("ActividadVPI_EJB.postInicializaEJB: ",e);
			log.error("ActividadVPI_EJB.postInicializaEJB:",e);
			throw new TnProcesoExcepcion("ATiempoAppEx Al Post Inicializar EJB [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]",e);//+ "," + getUsuarioAsignado() + "]");
		}
	}
	
	protected final void inicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion{
		this.inicializaActividad(act);
		act.setIdTemplateWf(VpistbbaConfig.getVariable("ID_TEMPLATE"));
		act.setIdAplicacion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
		act.setNombreAplicacion(ApplicationConfig.APP_VPISTBBA);
		act.setNombreEstructuraDatos(VpistbbaConfig.getVariable("ESTRUCTURA_DATOS"));
		act.setACCION_DESPLIEGA_PANTALLA(VpistbbaConfig.getVariable("ACCION_DESPLIEGA_PANTALLA"));
	}


}
