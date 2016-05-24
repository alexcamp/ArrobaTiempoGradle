/*
 * Created on 04-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJB;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.accion_masiva.ObtenerSgteTarea;
import co.com.telefonica.atiempo.soltec.asignacion.ejb.sb.AsignacionSTLocal;
import co.com.telefonica.atiempo.soltec.asignacion.ejb.sb.AsignacionSTLocalHome;
import co.com.telefonica.atiempo.soltec.bitacora.ejb.sb.BitacoraSTLocal;
import co.com.telefonica.atiempo.soltec.bitacora.ejb.sb.BitacoraSTLocalHome;
import co.com.telefonica.atiempo.soltec.datos_publicacion.ejb.sb.DatosPublicacionSTLocal;
import co.com.telefonica.atiempo.soltec.datos_publicacion.ejb.sb.DatosPublicacionSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
//import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
//import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ActividadST_EJB extends ActividadEJB {

//	protected abstract void inicializaActividad() throws TnProcesoExcepcion;
//	protected abstract void onInicioActividad() throws TnProcesoExcepcion ;
//	protected abstract void onTerminoActividad() throws TnProcesoExcepcion ;
//
//	protected DatosPeticion generaDatosPublicacion() {
//		DatosPeticion objDatoPeticion=null;
//		String logUser="";
//		try {
//			DatosPublicacionSTLocalHome datosHome =
//				(DatosPublicacionSTLocalHome)HomeFactory.getHome(DatosPublicacionSTLocalHome.JNDI_NAME);
//			DatosPublicacionSTLocal datosLocal = datosHome.create();
//			objDatoPeticion = datosLocal.getDatosPublicacion(this.getNumeroPeticion(),this.getCodigoActividad());
//				
//		} catch (NamingException e) {
//			log.debug("NamingException la levantar DatosPublicacionST.",e);
//		} catch (CreateException e) {
//			log.debug("CreateException la levantar DatosPublicacionST.",e);
//		}
//	
//		objDatoPeticion.setUrlDetalle(this.getUrlBandeja());
//		Long idUser=this.getIdUsuarioResponsable(); 
//		this.idUsuario=idUser; //Seteo el ID del usuario al Cual Publico en la bandeja
//		
////	No utilizo el user en la publicacion... uso el Id IUsuario
//		objDatoPeticion.setUsernameResponsable(logUser);
////	El id responsable va con el Id del Usuario
//		objDatoPeticion.setIdResponsable(this.idUsuario);
//		
//		return objDatoPeticion;
//	}
//
//	private Long getIdUsuarioResponsable() {
//		Long usuario = null;
//		AsignacionSTLocalHome home = null;
//		AsignacionSTLocal asignacion = null;
//		try {
//			home = (AsignacionSTLocalHome) HomeFactory.getHome(AsignacionSTLocalHome.JNDI_NAME);
//			asignacion = home.create();
//			usuario = asignacion.getIdUsuario(this.getNumeroPeticion().toString(), this.getCodigoActividad());
//		} catch (NamingException e) {
//			//TODO Auto-generated catch block
//			log.error("Obteniendo Usuario: ", e);
//		} catch (CreateException e) {
//			//TODO Auto-generated catch block
//			log.error("Obteniendo Usuario: ", e);
//		}
//
//		// Si el Usuario es NULO.
//		if (usuario == null) {
//			log.info("No se encontro Usuario para Asignar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");
//			return idUserDummy;
//		}
//
//		return usuario;
//	}
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#onInicio()
//	 */
//	protected void onInicio() throws TnProcesoExcepcion {
//		this.onInicioActividad();
//		log.debug("Inicio onInicio ActividadST:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());		
////		Se procede a registrar en bitacora si corresponde		
//		ActividadDTO act= this.actividadBD;
//		boolean grabar = true;
//		
//		try {
////			ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
////			ActividadSessionLocal actLocal = actHome.create();
////			act = actLocal.recuperaActividad(this.getCodigoActividad(), this.idAplicacion);
//
//			// Verifico que sea actividad que Graba en bitacora
//			if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
//				grabar = false;
//
//			if (grabar){
//				//Cuando es una actividad que publica, el usuario ya está asignado.
//				//Si no esta asignado es por que es una actividad automática, entonces se llama al metodo para asignar usuario
//				if (this.idUsuario==idUserDummy){
//					
//					this.idUsuario=this.getIdUsuarioResponsable(); //Usuario automático		
//				}
//				boolean bit=false;
//				//TODO: VMM Falta Bitacora ST
//				BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
//				//boolean bit = creadorBitacora.create().crearRegistroBitacora(getNumeroPeticion(), 
//				//	act.getIdActividad(), user.getId());
//				if ( this.realizarTerminoInmediato ){
//					log.debug("Creo Bitacora Cerrada Bitacora Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
////					Si se termina de inmediato se crea una bitacora cerrada y luego se va a terminar. No crea registro en wf_instancia
//					bit = creadorBitacora.create().crearRegistroBitacoraCerrado( this.getNumeroPeticion(),	act.getIdActividad(), this.idUsuario, this.observacion);
//				}
//				else{
//					log.debug("Creo Bitacora Abierta Bitacora Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
//					//Se crea un registo bitacora abierto
//					bit = creadorBitacora.create().crearRegistroBitacora( this.getNumeroPeticion(),	act.getIdActividad(), this.idUsuario,this.observacion);
//				}
////				TODO: que pasa si falla la bitacora?				
//			}
//			//Las automáticas pueden o no Terminarse de inmediato. Las Manuales no se terminan de inmediato porque se publican
//			if ( this.realizarTerminoInmediato ){
//				// Si se termina de inmediato se va a terminar y crea una bitacora cerrada. No crea registro en wf_instancia
//				this.terminar();
//			}
//			else{				
////				Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
////				Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
//				if (this.esGrabarWfInstancia){
//					log.debug("Creo wfInstanciaActividad para Actividad:" + this.getCodigoActividad());
//					this.crearNuevaIntanciaActividad(this);
//				}
//			}
//			log.debug("Fin onInicio ActividadST:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());			
//		} catch (NamingException e) {
//			log.debug("NamingException",e);
//			throw new TnProcesoExcepcion("NamingException Al Iniciar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");// + "," + getUsuarioAsignado() + "]");
//		} catch (CreateException e) {
//			log.debug("CreateException",e);
//			throw new TnProcesoExcepcion("CreateException Al Iniciar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
////		} catch (UsuariosException e) {
////			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
//		} catch (Exception e) {
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Iniciar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
//		}		
//
//	}
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#onTermino()
//	 */
//	protected void onTermino() throws TnProcesoExcepcion {
//		this.onTerminoActividad();
//		log.debug("Inicio onTermino Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
////		Se cierra la bitacora si corresponde				
//
////TODO: Falta agregar lo de no realizar termino
//		boolean grabar = true;
//		try {
//			ActividadDTO act= this.actividadBD;
//			if ( !this.realizarTerminoInmediato ){
////			No es una actividad con cierre inmediato, por lo tanto se debe cerrar la bitacora que dejó abierta.							
//				Long userCierreID=this.idUsuarioCierre; 
//				if (userCierreID==idUserDummy){ //Si no se ha seteado userid de cierre, es por que la cierra el mismo usuario que abrió la bitacora
//					userCierreID = null; //Si es null deja el mismo usuario que abrio la bitacora
//				}
//				
////				ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
////				ActividadSessionLocal actLocal = actHome.create();
////				
////				act = actLocal.recuperaActividad(this.getCodigoActividad(),this.idAplicacion);
//				
//				// Verifico que sea actividad que Graba en bitacora
//				if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
//					grabar = false;
//				
//				if (grabar) {				
//					BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
//					BitacoraSTLocal bp = creadorBitacora.create();
//					// se asume que la bitacora existe para esta actividad
//					log.debug("Cierro Bitacora Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
//					boolean resultGrabaBita = bp.cierra(this.getNumeroPeticion(), act.getIdActividad(), userCierreID, this.observacion,this.idCausaCierre);
////					TODO: que pasa si falla la bitacora?
//				}
//			}
//			// Marco el paso por el subflujo Actividad. En la reversa no debería hacer el ultimo subflujo
//			if (!this.esVacia){
//				if (act.getIdActFlujo()!=null){
//					//En st no hay PGI
//						Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//						Collection peticiones = peticion_flujoLocalHome.findActividad(this.getNumeroPeticion(),act.getIdActFlujo());
//						for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
//							log.debug("Marco OK Subflujo Actividad:" + this.getCodigoActividad());						
//							Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
//							peticionFlujo.setPefl_estado("OK");
//						}
//				}
//			}
////			Si es una actividad con cierre inmediato, su bitacora se creó cerrada, por lo tanto no es necesario cerrala	
////			Se envía en mensaje de cierre
//			log.debug("Envio Cierre Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
//			this.enviaRespuesta(this);
//			if ( !this.realizarTerminoInmediato ){ // Se cambia estado a la instanciaWF de la bd a las actividades que no se cierran de inmediato y por lo tanto crean un registro wf_instancia
//				if (this.esGrabarWfInstancia){
//					log.debug("Elimino Instancia="  + this.esGrabarWfInstancia);
////					Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
////					Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad					
//					this.eliminarInstanciaActividad(this);
//				}
//			}
//			log.debug("Fin onTermino Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());			
//		} catch (NamingException e) {
//			log.debug("NamingException",e);
//			throw new TnProcesoExcepcion("NamingException Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");// + "," + getUsuarioAsignado() + "]");
//		} catch (CreateException e) {
//			log.debug("CreateException",e);
//			throw new TnProcesoExcepcion("CreateException Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
////		} catch (UsuariosException e) {
////			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
//		} catch (Exception e) {
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
//		}			
//
//	}
//	protected final void onCambioUsuario(Long idUser) throws TnProcesoExcepcion{
//		log.debug("Inicio onCambioUsuario Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
////		Se cierra la bitacora si corresponde				
//		boolean grabar = true;
//		try {
//			if ( !this.realizarTerminoInmediato ){
////			No es una actividad con cierre inmediato, por lo tanto se debe cerrar la bitacora que dejó abierta.							
//				ActividadDTO act= this.actividadBD;
//				Long userCierreID=this.idUsuarioCierre; 
//				if (userCierreID==idUserDummy){ //Si no se ha seteado userid de cierre, es por que la cierra el mismo usuario que abrió la bitacora
//					userCierreID = null; //Si es null deja el mismo usuario que abrio la bitacora
//				}
//				
////				ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
////				ActividadSessionLocal actLocal = actHome.create();
////				
////				act = actLocal.recuperaActividad(this.getCodigoActividad(),this.idAplicacion);
//				
//				// Verifico que sea actividad que Graba en bitacora
//				if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
//					grabar = false;
//				
//				if (grabar) {				
//					BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
//					BitacoraSTLocal bp = creadorBitacora.create();
//					// se asume que la bitacora existe para esta actividad
//					log.debug("Cierro Bitacora Actividad:" + this.getCodigoActividad());
//					boolean resultGrabaBita = bp.cierra(this.getNumeroPeticion(), act.getIdActividad(), userCierreID, this.observacion,this.idCausaCierre);
//					boolean bit = false;
//					if (resultGrabaBita){
//						log.debug("Creo Bitacora Abierta Bitacora Actividad:" + this.getCodigoActividad());
//						//Se crea un registo bitacora abierto para el nuevo user
//						bit=creadorBitacora.create().crearRegistroBitacora( this.getNumeroPeticion(),	act.getIdActividad(), idUser,"");
//					}
//					if (bit){
//						//Cambio el usuario publicado
//						this.cambiarUsuarioPublicacion(idUser);
//					}
////					TODO: que pasa si falla la bitacora o el cambio de user publicacion?
//				}
//
//			}
//			log.debug("Fin onCambioUsuario Actividad:" + this.getCodigoActividad() + ":" + this.getNumeroPeticion());
//		} catch (NamingException e) {
//			log.debug("NamingException",e);
//			throw new TnProcesoExcepcion("NamingException Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");// + "," + getUsuarioAsignado() + "]");
//		} catch (CreateException e) {
//			log.debug("CreateException",e);
//			throw new TnProcesoExcepcion("CreateException Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
////		} catch (UsuariosException e) {
////			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
//		} catch (Exception e) {
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Terminar [" + this.getNumeroPeticion() + "," + this.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
//		}	
//	}
//	
//	protected final void postInicializaEJB() throws TnProcesoExcepcion{
//
//	}
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#inicializaEJB()
//	 */
//	protected void inicializaEJB() throws TnProcesoExcepcion {
//		this.inicializaActividad();
//		this.idAplicacion= new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
//		this.nombreAplicacion = ApplicationConfig.APP_ATST;
//		this.nombreEstructuraDatos=STConfig.getVariable("ESTRUCTURA_DATOS");
//		this.ACCION_DESPLIEGA_PANTALLA = STConfig.getVariable("ACCION_DESPLIEGA_PANTALLA");
//	}

	protected abstract void inicializaActividad(ActividadEJBDTO act) throws TnProcesoExcepcion;
	protected abstract void onInicioActividad(ActividadEJBDTO act) throws TnProcesoExcepcion ;
	protected abstract void onTerminoActividad(ActividadEJBDTO act) throws TnProcesoExcepcion ;

	protected DatosPeticion generaDatosPublicacion(ActividadEJBDTO act) {
		DatosPeticion objDatoPeticion=null;
		String logUser="";
		try {
			DatosPublicacionSTLocalHome datosHome =
				(DatosPublicacionSTLocalHome)HomeFactory.getHome(DatosPublicacionSTLocalHome.JNDI_NAME);
			DatosPublicacionSTLocal datosLocal = datosHome.create();
			objDatoPeticion = datosLocal.getDatosPublicacion(act.getNumeroPeticion(),act.getCodigoActividad());
				
		} catch (NamingException e) {
			log.debug("NamingException la levantar DatosPublicacionST.",e);
		} catch (CreateException e) {
			log.debug("CreateException la levantar DatosPublicacionST.",e);
		}
	
		objDatoPeticion.setUrlDetalle(act.getUrlBandeja());
//		Modificacion para obtenciòn del usuario para TOA FASE III Cesar Remigio
		Long idUser=this.getIdUsuarioResponsable(act); 
		log.debug("Se setea el usuario:"+act.getIdUsuario());
		act.setIdUsuario(idUser); //Seteo el ID del usuario al Cual Publico en la bandeja
		
//	No utilizo el user en la publicacion... uso el Id IUsuario
		objDatoPeticion.setUsernameResponsable(logUser);
//	El id responsable va con el Id del Usuario
		objDatoPeticion.setIdResponsable(act.getIdUsuario());
		
		return objDatoPeticion;
	}

	private Long getIdUsuarioResponsable(ActividadEJBDTO act) {
		Long usuario = null;
		AsignacionSTLocalHome home = null;
		AsignacionSTLocal asignacion = null;
		try {
			home = (AsignacionSTLocalHome) HomeFactory.getHome(AsignacionSTLocalHome.JNDI_NAME);
			asignacion = home.create();
			usuario = asignacion.getIdUsuario(act.getNumeroPeticion().toString(), act.getCodigoActividad());
		} catch (NamingException e) {
			//TODO Auto-generated catch block
			log.error("Obteniendo Usuario: ", e);
		} catch (CreateException e) {
			//TODO Auto-generated catch block
			log.error("Obteniendo Usuario: ", e);
		}

		// Si el Usuario es NULO.
		if (usuario == null) {
			log.info("No se encontro Usuario para Asignar [" + act.getNumeroPeticion() + "," + act.getCodigoActividad() + "]");
			return act.getIdUserDummy();
		}

		return usuario;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#onInicio()
	 */
	protected void onInicio(ActividadEJBDTO actEjb) throws TnProcesoExcepcion {
		this.onInicioActividad(actEjb);
		log.debug("Inicio onInicio ActividadST:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());		
//		Se procede a registrar en bitacora si corresponde		
		ActividadDTO act= actEjb.getActividadBD();
		boolean grabar = true;
		
		try {
//			ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
//			ActividadSessionLocal actLocal = actHome.create();
//			act = actLocal.recuperaActividad(this.getCodigoActividad(), this.idAplicacion);

			// Verifico que sea actividad que Graba en bitacora
			if ( act.getActManual()==null || act.getActManual().intValue()!=1 )
				grabar = false;

			if (grabar){
				//Cuando es una actividad que publica, el usuario ya está asignado.
				//Si no esta asignado es por que es una actividad automática, entonces se llama al metodo para asignar usuario
				if (actEjb.getIdUsuario().longValue()==actEjb.getIdUserDummy().longValue()){
					actEjb.setIdUsuario(this.getIdUsuarioResponsable(actEjb)); //Usuario automático		
				}
				boolean bit=false;
				//TODO: VMM Falta Bitacora ST
				BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
				//boolean bit = creadorBitacora.create().crearRegistroBitacora(getNumeroPeticion(), 
				//	act.getIdActividad(), user.getId());
				if ( actEjb.isRealizarTerminoInmediato() ){
					log.debug("Creo Bitacora Cerrada Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
//					Si se termina de inmediato se crea una bitacora cerrada y luego se va a terminar. No crea registro en wf_instancia
					
					//TODO CR11267 si la actividad que se cierra es "CONTROL_REPARACION", o esta en DIAGNOSTICO_STB, debido a que viene de PI, no se graba en la bitacora 
					log.debug("estoy por grabar en la bitacora la incidencia: "+actEjb.getNumeroPeticion().longValue());
					//if (this.grabarEnBitacora(actEjb)) bit = creadorBitacora.create().crearRegistroBitacoraCerrado( actEjb.getNumeroPeticion(),	act.getIdActividad(), actEjb.getIdUsuario(), actEjb.getObservacion());
					//log.debug(!grabarEnBitacora(actEjb)?"se supone que grabo en bitacora":"se supone que NO grabo en Bitacora");
 					if (actEjb.isGrabarEnBitacora()) bit = creadorBitacora.create().crearRegistroBitacoraCerrado( actEjb.getNumeroPeticion(),	act.getIdActividad(), actEjb.getIdUsuario(), actEjb.getObservacion());
				    //
				    log.debug(actEjb.isGrabarEnBitacora()?"se supone que grabo en bitacora":"se supone que NO grabo en Bitacora");
					//fin CR
					
				}
				else{
					log.debug("Creo Bitacora Abierta Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					//Se crea un registo bitacora abierto
					bit = creadorBitacora.create().crearRegistroBitacora( actEjb.getNumeroPeticion(),	act.getIdActividad(), actEjb.getIdUsuario(),actEjb.getObservacion());
				}
//				TODO: que pasa si falla la bitacora?				
			}
			//Las automáticas pueden o no Terminarse de inmediato. Las Manuales no se terminan de inmediato porque se publican
			if ( actEjb.isRealizarTerminoInmediato() ){
				// Si se termina de inmediato se va a terminar y crea una bitacora cerrada. No crea registro en wf_instancia
				this.terminar(actEjb);
			}
			else{				
//				Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
//				Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad
				
				if (actEjb.isGrabarWfInstancia()&& !actEjb.getDato("PGI_AVERIAS_OK").equals("S")){
				log.debug("Creo wfInstanciaActividad para Actividad:" + actEjb.getCodigoActividad());
					this.crearNuevaIntanciaActividad(actEjb);
				}
			}
			log.debug("Fin onInicio ActividadST:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());			
		} catch (NamingException e) {
			log.debug("NamingException",e);
			throw new TnProcesoExcepcion("NamingException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");// + "," + getUsuarioAsignado() + "]");
		} catch (CreateException e) {
			log.debug("CreateException",e);
			throw new TnProcesoExcepcion("CreateException Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
//		} catch (UsuariosException e) {
//			log.error("UsuariosException: Al Crear Bitacora [" + getNumeroPeticion() + "," + getCodigoActividad() + "," + getUsuarioAsignado() + "]");
		} catch (Exception e) {
			log.debug("Exception",e);
			throw new TnProcesoExcepcion("TnProcesoExcepcion Al Iniciar [" + actEjb.getNumeroPeticion() + "," + actEjb.getCodigoActividad() + "]");//+ "," + getUsuarioAsignado() + "]");
		}		

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#onTermino()
	 */
	protected void onTermino(ActividadEJBDTO actEjb) throws TnProcesoExcepcion {
		this.onTerminoActividad(actEjb);
		log.debug("Inicio onTermino Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
//		Se cierra la bitacora si corresponde				

//TODO: Falta agregar lo de no realizar termino
		
		boolean grabar = true;
		Integer ordenFlujo = new Integer(-1); 
		String bandejaPGIAverias	= VpistbbaConfig.getVariable("ID_PGI_AVERIAS"); //REQ TOA FASE III
		String bandejaProcesoBAJAS	= VpistbbaConfig.getVariable("ID_PROCESO_BAJA"); //REQ TOA FASE III
		try {
			ActividadDTO act= actEjb.getActividadBD();
			if(actEjb.isNoTerminar()){ //no hago nada mas que modificar la bitacora
				log.debug("Peticion debe permanecer en la actividad Actividad:" + actEjb.getCodigoActividad() + "numero peticion: " + actEjb.getNumeroPeticion());
				if (actEjb.getObservacion() != null){
					BitacoraSTLocalHome creadorBitacoraT = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
					BitacoraSTLocal bpT = creadorBitacoraT.create();
					log.debug("Cambio Observacion Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					boolean resultGrabaBita = bpT.actualizaObservacionBitacora(actEjb.getNumeroPeticion(), act.getIdActividad(), actEjb.getObservacion(),actEjb.getOverwriteObs());
				}
				return;
			}

			if ( !actEjb.isRealizarTerminoInmediato() ){
//			No es una actividad con cierre inmediato, por lo tanto se debe cerrar la bitacora que dejó abierta.							
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
				
				log.debug(" grabar "+grabar+ " actmanual "+act.getActManual() );
				if (grabar) {				
					BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
					BitacoraSTLocal bp = creadorBitacora.create();
					
					// se asume que la bitacora existe para esta actividad
					log.debug("Cierro Bitacora Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
					boolean resultGrabaBita = bp.cierra(actEjb.getNumeroPeticion(), act.getIdActividad(), userCierreID, actEjb.getObservacion(),actEjb.getIdCausaCierre());
//					TODO: que pasa si falla la bitacora?
				}
			}
			// Marco el paso por el subflujo Actividad. En la reversa no debería hacer el ultimo subflujo
			if (!actEjb.isVacia()){
				log.debug("no esta vacia");	
				if (act.getIdActFlujo()!=null){
					//En st no hay PGI
					log.debug("Se valida la peticion_flujo de: "+actEjb.getNumeroPeticion()+", la actividad: "+act.getIdActFlujo());
						Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
						Collection peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),act.getIdActFlujo());
						
						//REQ TOA FASE III DCARDENA 
						if(act.getIdActFlujo().intValue() == ComunInterfaces.ID_ACTIVIDAD_FLUJO_PE_TOA){
							peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),new Integer(ComunInterfaces.ID_ACTIVIDAD_FLUJO_SOLUCION_STB));
							if(peticiones.size() == 0)
								peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),new Integer(ComunInterfaces.ID_ACTIVIDAD_FLUJO_SOLUCION_BA));
							if(peticiones.size() == 0)
								peticiones = peticion_flujoLocalHome.findActividad(actEjb.getNumeroPeticion(),new Integer(ComunInterfaces.ID_ACTIVIDAD_FLUJO_SOLUCION_TV));
							log.debug("Se obtiene peticion con tamaño  "+peticiones.size());
						}
							
						//Se valida ID_PGI_AVERIAS es igual 23 es el act_id y se valida que sea difetente  el forza para poner las actividades del flujo de la peticion en NOOK o OK
						if(!actEjb.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)
								|| (!actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaPGIAverias)
								&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaProcesoBAJAS))){	
							
							for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
								log.debug("Marco OK Subflujo Actividad:" + actEjb.getCodigoActividad());						
								Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
								peticionFlujo.setPefl_estado("OK");
								ordenFlujo = peticionFlujo.getPefl_orden();
								if(actEjb.getDato("PGI_AVERIAS_OK").equals("S")){
									log.debug("Viene reintento PGI "+peticionFlujo.getPefl_orden());
									actEjb.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
								}
							}
						}else{
							log.debug("Tamaño de arreglo de peticiones: "+peticiones.size());
							for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
								log.debug("Marco NOOK Subflujo Actividad:" + actEjb.getCodigoActividad());						
								Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
								peticionFlujo.setPefl_estado("NOOK");
								actEjb.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
								ordenFlujo = peticionFlujo.getPefl_orden();
							}
						}
						//End 
				}
			}

			log.debug("on terminoinmediato "+ actEjb.isRealizarTerminoInmediato() +" wfgraba "+ actEjb.isGrabarWfInstancia());
			//REQ TOA FASE III DCARDENA 
			//Se agreega validacion para cuando sea reintento en PGI_averias no cambie el estado en eliminar instancia osea el estado de 0 a 1 yaque generaria error nullpointer exception al enviar respuestas de TRs
			if ( !actEjb.isRealizarTerminoInmediato()){
					// Se cambia estado a la instanciaWF de la bd a las actividades que no se cierran de inmediato y por lo tanto crean un registro wf_instancia
				if (actEjb.isGrabarWfInstancia()&& actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)!=null 
						&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaPGIAverias)
						&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaProcesoBAJAS))
					{
					log.debug("Elimino Instancia="  + actEjb.isGrabarWfInstancia());
//					Se graba en WF instancia actividad si es que no se termina la actividad y necesita grabarse en  esta tabla. Por ej: Las actividades manuales(que se publican), no necesitan grabar en esta tabla, pues guardan el IdInstancia del WF en la url de la tabla BIntegrada.
//					Las automáticas que no terminan, necesitan guardarse en esta tabla para obtener el idinstancia del WF y poder cerrar la actividad					
					this.eliminarInstanciaActividad(actEjb);
				}
			}
//			Si es una actividad con cierre inmediato, su bitacora se creó cerrada, por lo tanto no es necesario cerrala	
//			Se envía en mensaje de cierre
			
			//REQ TOA FASE III DCARDENA 
			//Se agreega dentro de la validacion que no entre ni el forza de PGI, ni cuando la peticion este en PGI, solo se genera respuesta con conexion a WF si hay continuar flujo desde PGI o es una actividad automatica
			//en este if entran las actividades diferente a (manuales y que no tengnan WF) se calcula WF
			log.debug("Envio Cierre Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
			if((!actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).equals(""+ComunInterfaces.idActividadPEBA)
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv).equals(""+ComunInterfaces.idActividadPETV)
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).equals(""+ComunInterfaces.idActividadPESTB)
					&& !actEjb.getDato("SOL_TOA").equals(""+ComunInterfaces.idActividadPETOA)
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaPGIAverias)//BANDEJA PGI
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaProcesoBAJAS)//BANDEJA PROCESO BAJAS)
					&& !actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_PGI_AVERIAS))
					|| (actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_PGI_AVERIAS)&& actEjb.getDato("ACT_OK").equals("S"))
					|| (actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_BANDEJA_PROCESO_BAJA)&& actEjb.getDato("ACT_OK").equals("S"))){
				log.debug("************No simula WF Envio Cierre Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());
				//no se simula WF
				this.enviaRespuesta(actEjb);
				log.debug("************No simula WF Fin onTermino Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());	
				
			}else{
				//se simula WF
				log.debug("Se llama el proceso para instanciar la siguiente tarea para la actividad: "+actEjb.getCodigoActividad()+ "Con orden: "+ ordenFlujo);
				
				if(ordenFlujo.intValue()==-1){
					ordenFlujo=new Integer(0);
				}
				
				if(actEjb.getDato("PGI_AVERIAS_OK").equals("S") || 
						(actEjb.getCodigoActividad().equals(ComunInterfaces.ACT_BANDEJA_PROCESO_BAJA)&& actEjb.getDato("ACT_OK").equals("S"))){
					
					log.debug("*****************Se Continua Actividad Por Bandeja se Simula WF");
					//BA NAKEd FASE dcardena
					//se agrega la obtencion de flujo para solucionar el reintento de PGI este se mapea en la clase SolucionTecnicaAveriasBean funcion onTerminoActividadST
					ordenFlujo =new Integer (actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden));
					log.debug("Se obtiene Orden flujo "+ordenFlujo+" de Reintento PGI averias "+actEjb.getNumeroPeticion());
					
					new ObtenerSgteTarea (actEjb,ordenFlujo);
					
				}else{
					
					log.debug("*****************Se Calcula la siguiente Actividad se Simula WF");
					ObtenerSgteTarea siguientetarea = new ObtenerSgteTarea(actEjb);	
					
				}
			}
			
			log.debug("*****************Fin onTermino Actividad:" + actEjb.getCodigoActividad() + ":" + actEjb.getNumeroPeticion());			
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
	protected final void onCambioUsuario(ActividadEJBDTO actEjb,Long idUser) throws TnProcesoExcepcion{
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
					BitacoraSTLocalHome creadorBitacora = (BitacoraSTLocalHome) HomeFactory.getHome(BitacoraSTLocalHome.JNDI_NAME);
					BitacoraSTLocal bp = creadorBitacora.create();
					// se asume que la bitacora existe para esta actividad
					log.debug("Cierro Bitacora Actividad:" + actEjb.getCodigoActividad());
					boolean resultGrabaBita = bp.cierra(actEjb.getNumeroPeticion(), act.getIdActividad(), userCierreID, actEjb.getObservacion(),actEjb.getIdCausaCierre());
					boolean bit = false;
					if (resultGrabaBita){
						log.debug("Creo Bitacora Abierta Bitacora Actividad:" + actEjb.getCodigoActividad());
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
	
	protected final void postInicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion{

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#inicializaEJB()
	 */
	protected void inicializaEJB(ActividadEJBDTO act) throws TnProcesoExcepcion {
		this.inicializaActividad(act);
		act.setIdTemplateWf(STConfig.getVariable("ID_TEMPLATE"));
		act.setIdAplicacion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID)));
		act.setNombreAplicacion(ApplicationConfig.APP_ATST);
		act.setNombreEstructuraDatos(STConfig.getVariable("ESTRUCTURA_DATOS"));
		act.setACCION_DESPLIEGA_PANTALLA(STConfig.getVariable("ACCION_DESPLIEGA_PANTALLA"));
	}


}
