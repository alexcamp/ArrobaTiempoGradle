package co.com.telefonica.atiempo.vpistbba.inventario.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocal;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_psKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Respuesta_conect4_apscLocal;
import co.com.telefonica.atiempo.ejb.eb.Respuesta_conect4_apscLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Respuesta_conect7_apscLocal;
import co.com.telefonica.atiempo.ejb.eb.Respuesta_conect7_apscLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.SpecialServicesRequest;
import co.com.telefonica.atiempo.interfaces.atiempo.TR004E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR004S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR011E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR011S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioInterfaces;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ActualizacionInventarioSSMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ActualizacionInventarioSTBGRMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ActualizacionInventarioSTBMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: InventarioServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InventarioServicesBean
    extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
    implements InventarioInterfaces
{
    protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
    
//    private Mensaje_estadoLocalHome msgEstadoLocalHome ;
//    private ConectorLocalHome conectorLocalHome ;
//    private Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome ;
//    private Respuesta_conect7_apscLocalHome resConSieteAPSCLocalHome ;
//    private Respuesta_conect4_apscLocalHome resConCuatroAPSCLocalHome ;
//    private PeticionLocalHome peticionLocalHome ;
//    private Mensaje_estado_lineaLocalHome mensaje_estado_lineaLocalHome;
//	private UsuarioLocalHome usuarioHome;
//	private Catalogo_causalLocalHome catalogo_causalHome;
//	private Estado_psLocalHome estado_psHome;
//	private Causal_peticionLocalHome causal_peticionHome;
//	private Estado_ps_peticionLocalHome estado_ps_peticionHome;
    
    private SimpleDateFormat df ;
    private DBManager dbSeq ;
    
    
    /*
     *
     */
    
    public void setSessionContext (SessionContext ctx)
    throws EJBException, RemoteException
    {
        super.setSessionContext (ctx) ;
        
        dbSeq = new DBManager ();
        
        dbSeq.setDataSource (DBManager.JDBC_VPISTBBA);
        
        buscaHome () ;
        
        df = new SimpleDateFormat ("dd/MM/yyyy") ;
    }
    
    /*
     *
     */
    
    private void buscaHome ()
    {
//        try
//        {
//            msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
//            
//            msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
//            
//            resConSieteAPSCLocalHome = (Respuesta_conect7_apscLocalHome) HomeFactory.getHome (Respuesta_conect7_apscLocalHome.JNDI_NAME) ;
//            
//            resConCuatroAPSCLocalHome = (Respuesta_conect4_apscLocalHome) HomeFactory.getHome (Respuesta_conect4_apscLocalHome.JNDI_NAME) ;
//            
//            peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
//            
//            conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
//            
//			mensaje_estado_lineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
//
//        }
//        catch (NamingException nex)
//        {
//            log.error ("error durante inicializacion", nex) ;
//        }
        }
    
//    private void validaHome ()
//    throws ATiempoAppEx
//    {
//        if (
//            msgEstadoLineaLocalHome == null ||
//            resConSieteAPSCLocalHome == null ||
//            resConCuatroAPSCLocalHome == null ||
//            peticionLocalHome == null ||
//            msgEstadoLocalHome == null ||
//		    mensaje_estado_lineaLocalHome == null )
//        {
//            throw new ATiempoAppEx (ATiempoAppEx.NAMING);
//        }
//    }
    
/*
 *  versiones anterior
 *
     comentario anterior
 * Actualizacion Inventario SS
 * Se actualiza  se guarda una bitacora.
 * Luego se invoca al servicio RespuestaActualizarInventariosSS.
    public void actualizarInventarioSS (Integer peticionAtisId, Integer agrupacionId, String respuesta)
    throws ATiempoAppEx
    {
        //respuestaActualizarInventarioSS();
    }
 *
    public void actualizarInventarioSTB (Integer ordenServicioId, Boolean respuesta, String telefono)
    throws ATiempoAppEx
    {
        ActualizacionInventarioSTBMQ aimq = new ActualizacionInventarioSTBMQ ();
        aimq.enviarMensaje ("HOLA!!!");
 
 */
    
    /**
     * fbd:
     * - cambio de interfaz (se recibe el "DTO-XML" TR004S)
     * - implementacion del metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.inventario.business.InventarioServicesBusinessInterface#actualizarInventarioSS()
     */
    
    public boolean getErrorMessageS(TR011S tr011s, Mensaje_estado_lineaLocal mensajeEstadoLinea) throws ATiempoAppEx{
    	
    	boolean activa = true;
		try {
    	boolean activaAct = true;
		
		if(tr011s.isError() == true || tr011s.isResponse() == false){
			activaAct = false;	
		
		}else{
			activaAct = true;
		}
		
		boolean activaAnt = true;
		String accion = "";
		
		PeticionKey peticionKeyEstados = (PeticionKey) mensajeEstadoLinea.getPeticion().getPrimaryKey();
		if (mensajeEstadoLinea.getAccion().equals(altaRecursoLineaBasica)){
			log.debug("Se obtendra el mensaje de estado de Baja");
			accion = bajaRecursoLineaBasica;
		}else if(mensajeEstadoLinea.getAccion().equals(bajaRecursoLineaBasica)){
			log.debug("Se obtendra el mensaje de estado de Alta");
			accion = altaRecursoLineaBasica;
		}
    		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
    		Mensaje_estado_lineaLocalHome mensaje_estado_lineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
		Collection estados = mensaje_estado_lineaLocalHome.findUltimaAcccion( peticionKeyEstados.peti_numero, accion);
		
		if(estados != null && estados.size() != 0){
			log.debug("Se obtuvieron una cantidad de mensajes. Se tomara el primero (DESC SQL)");
			Mensaje_estado_lineaLocal  mensaje_estado_lineaLocal = mensaje_estado_lineaLocal = (Mensaje_estado_lineaLocal) estados.iterator().next();
			Mensaje_estadoKey mensaje_estadoAcKey = mensaje_estadoAcKey = (Mensaje_estadoKey) mensaje_estado_lineaLocal.getF_reference_14().getPrimaryKey();
			if(mensaje_estadoAcKey.cod_estado.intValue() == estadoError){
				log.debug("El Mensaje Estado de Linea Obtenido, Contiene Error");
				activaAnt = false;
		
			}else{
				log.debug("El Mensaje Estado de Linea Obtenido, esta OK");
				activaAnt = true;
			}
		}
				
		if (activaAnt == true && activaAct == true){
			activa = true;
		}else{
			activa = false;
		}
		
		} catch (FinderException e) {
					e.printStackTrace();
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
    	return activa;
    	
    }
    
    public void actualizarInventarioSS (TR011S tr011s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr011s, log);
			bExecution.setNumPetAtiempo(new Long(tr011s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr011s.getId());
			bExecution.startOperation();
				
			log.debug("Metodo Actualizar Inventario SS");
            // busca el registro del mensaje
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
			Mensaje_estadoLocal mensajeErrorLocal=msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeOKLocal=msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
            Mensaje_estado_lineaLocal mensajeEstadoLinea = buscaMensajeEstadoLinea (tr011s.getId ()) ;
            
            if (mensajeEstadoLinea == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr011s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr011s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
            // if (mensajeEstadoLinea.getCodigoEstado ().intValue () == estadoOk)
            
           // Integer codigoEstado = (Integer) mensajeEstadoLinea.getF_reference_14 ().getPrimaryKey () ;
		   Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoLinea.getF_reference_14 ().getPrimaryKey () ;
            
            if (mensaje_estadoKey.cod_estado.intValue() == estadoOk)
            {
                log.debug (
                    "El estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr011s));
                return;
            }
			log.debug("Mensaje Estodo de Linea OK");
            // actualiza el estado del mensaje
            // mensajeEstadoLinea.setCodigoEstado (new Integer (estadoOk)) ;
            // cierra la actividad automatica
            // si es el ultimo en esperar... se cierra la actividad
			
            log.debug("Mensaje recibido.");
            if (this.getEstadoMultipleMensajes(mensajeEstadoLinea, new Integer(estadoEspera))){
				log.debug("Ultimo mensaje -> se cierra la acitividad");	
				// todo ok, se graba la respuesta
				
				
				if(this.getErrorMessageS(tr011s, mensajeEstadoLinea)){
				
				   log.debug("El mensaje de estado Actual (TR011S) y mensaje de estado Anterior, estan OK");
					
				   Long idConSiete = new Long (dbSeq.seqNextValLong ("CORRELATIVO_APSC")) ;
				   //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				   Respuesta_conect7_apscLocalHome resConSieteAPSCLocalHome = (Respuesta_conect7_apscLocalHome) HomeFactory.getHome (Respuesta_conect7_apscLocalHome.JNDI_NAME) ;
				   Respuesta_conect7_apscLocal conSiete = resConSieteAPSCLocalHome.create (idConSiete) ;
				   
				   
				   conSiete.setF_fk_01_respuesta_ (mensajeEstadoLinea) ;
	
				   // TODO revisar si es correcto sacar el numero de peticion de la tabla de mensaje
				   // Â¿no deberia estar en el mensaje XML y compararse con el valor de la tabla?
				   // Â¿sirve de algo tenerlo repetido entre ambas tablas?
	
				   conSiete.setPeti_numero (mensajeEstadoLinea.getPeti_numero ()) ;
	
	
				   // TODO ver que hacer con:
				   // conSiete.setMensajeError () ;
				   // conSiete.setIndicadorError ()
	
				   // no existen desde desde el 27-03-2007 
				   // tr011s.getResponse () ;
				   // tr011s.getSubPetitionCode () ;
	            	
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
	            
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoLinea.getCod_actividad_generadora ()) ;
	            	ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLinea.getPeti_numero (), mensajeEstadoLinea.getCod_actividad_generadora ()) ;
					// TODO ver si hay que setear alguna variable antes de responder al WF
					// actividadEJB.setDato (DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
					// actividadEJB.setObservacion ("Error en la Generaciï¿½n  de Orden de Servicio");
					actividadEJB.terminar (actDto);
				}else{
					
				    log.debug("El mensaje de estado Actual (TR011S) o mensaje de estado Anterior, estan con Error");
				    
					//modificado adecarlini
					PeticionLocal peticionLocal = mensajeEstadoLinea.getPeticion();
					PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
					//
					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLinea.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLinea.getCod_actividad_generadora());
					//modificado adecarlini
					//insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					//fin
									
					//agregado por adecarlini
					String codError = String.valueOf(tr011s.getTypeError());
					String nombreClase=TR011S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR011S.class.getName());
					if(errorLegado != null){
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					// fin agregado
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a PGI.Descripcion:" + tr011s.getErrorMessage());
					actividadEJB.terminar(actDto);

				}
			 
            }else{
				log.debug("El mensaje de estado no es el ultimo");
            }
			
			if(tr011s.isError() == true || tr011s.isResponse() == false){
				
				log.debug("Mensaje Estodo de Linea Con Error");
				mensajeEstadoLinea.setF_reference_14(mensajeErrorLocal);
				mensajeEstadoLinea.setFecha_cierre(df.format (new java.util.Date ()));
	
			}else{
				log.debug("Mensaje Estodo de Linea Sin Error");
				Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
				mensajeEstadoLinea.setF_reference_14 (mensajeEstadoOk) ; 
				mensajeEstadoLinea.setFecha_cierre(df.format (new java.util.Date ()));
			}
        }
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
        }
        catch (CreateException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
        catch (TnProcesoExcepcion e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS) ;
        }
        catch (FinderException e)
        {
			bExecution.setErrorOp(true);			
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}
        catch (NamingException e)
		{
        	bExecution.setErrorOp(true);
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		catch (Exception e)
		{
			bExecution.setErrorOp(true);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
		finally{  			
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    }
    
    /**
     * fbd:
     * - cambio de interfaz (se recibe el "DTO-XML" TR004S)
     * - implementacion del metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.inventario.business.InventarioInterfaces#actualizarInventarioSTB(java.lang.Integer, java.lang.Boolean, java.lang.String)
     */
    


	public void actualizarInventarioSTB (TR004S tr004s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr004s, log);
			//bExecution.setNumPetAtiempo(new Long(tr004s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr004s.getId());
			bExecution.startOperation();
		    //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
            // busca el registro del mensaje
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
			Mensaje_estadoLocal mensajeErrorLocal=msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
            Mensaje_estado_lineaLocal mensajeEstadoLinea = buscaMensajeEstadoLinea (tr004s.getId ()) ;
            
            if (mensajeEstadoLinea == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr004s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr004s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
			Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoLinea.getF_reference_14 ().getPrimaryKey () ;
            
            if ( mensaje_estadoKey.cod_estado.intValue() == estadoOk)
            {
                log.debug (
                    "Es estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr004s));
                return;
            }
            
            // todo ok, se graba la respuesta
		if(tr004s.isError() ==false && tr004s.isResponse()==true)
		{
            Long idConCuatro = new Long (dbSeq.seqNextValLong ("CORRELATIVO_APSC")) ;
            
            Respuesta_conect4_apscLocalHome resConCuatroAPSCLocalHome = (Respuesta_conect4_apscLocalHome) HomeFactory.getHome (Respuesta_conect4_apscLocalHome.JNDI_NAME) ;        
            Respuesta_conect4_apscLocal conCuatro = resConCuatroAPSCLocalHome.create (idConCuatro) ;
            
			
            // conCuatro.setMensaje_estado_linea (mensajeEstadoLinea) ;
            conCuatro.setF_fk_01_respuesta_ (mensajeEstadoLinea) ;
            
            // TODO revisar si es correcto sacar el numero de peticion de la tabla de mensaje
            // Â¿no deberia estar en el mensaje XML y compararse con el valor de la tabla?
            // Â¿sirve de algo tenerlo repetido entre ambas tablas?
            
            // conCuatro.setNumeroPeticion (mensajeEstadoLinea.getNumeroPeticion ()) ;
            
            conCuatro.setPeti_numero (mensajeEstadoLinea.getPeti_numero ()) ;
            
            // conCuatro.setTelefonoAsignado (new Long (tr004s.getPhoneNumber ())) ;
            conCuatro.setTelefono_asg (new Long (tr004s.getPhoneNumber ())) ;
            
            
            // TODO: ver que hacer con
            //
            // conCuatro.setMensaje_error ()
            // conCuatro.setMensajeError (...)  new TR010S ().getErrorMessage ()
            // conCuatro.setInd_error () ;
            // conCuatro.setIndicadorError (...) new TR010S ().isError ()
            //
            
            
            
            // actualiza el estado del mensaje
            // mensajeEstadoLinea.setCodigoEstado (new Integer (estadoOk)) ;
            
            Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
            
            mensajeEstadoLinea.setF_reference_14 (mensajeEstadoOk) ;
            
			if(tr004s.getPhoneNumber() > 0){
				//Actualizo el fono en la BD por el que me envia la actualizacion de inventario
				Collection recursosLineaBasica = mensajeEstadoLinea.getPeticion().getRecursos_linea_basica();
				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				//Si tiene bien los recursos
				if ( recursosLineaBasica.size()==1 ){
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)  recursosLineaBasica.iterator().next();
					recursos_linea_basicaLocal.setTelefono_asg(new Long (tr004s.getPhoneNumber ()));
				}
			}
            // cierra la actividad automatica
            
            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
            
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoLinea.getCod_actividad_generadora ()) ;
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLinea.getPeti_numero (), mensajeEstadoLinea.getCod_actividad_generadora ()) ;

            // TODO ver si hay que setear alguna variable antes de responder al WF
            // actividadEJB.setDato (DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
            // actividadEJB.setObservacion ("Error en la Generaciï¿½n  de Orden de Servicio");
            actividadEJB.terminar (actDto) ;
			}
			else
			{
		
				mensajeEstadoLinea.setF_reference_14(mensajeErrorLocal);
				mensajeEstadoLinea.setFecha_cierre(df.format (new java.util.Date ()));
				
				//modificado adecarlini
				PeticionLocal peticionLocal = mensajeEstadoLinea.getPeticion();
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				//
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLinea.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLinea.getCod_actividad_generadora());
				//agregado por adecarlini
			    String codError = String.valueOf(tr004s.getTypeError());
				String nombreClase=TR004S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR004S.class.getName());
				if (errorLegado != null){
					if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
						actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr004s.getErrorMessage());
						actividadEJB.terminar(actDto);
						return;
					}
					plataforma = errorLegado.getPlataforma(); 
					bandeja	= getNombreBandeja(plataforma);
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else{
					insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());	
				}
				// fin agregado
				//modificado adecarlini
				//insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				//fin modificado
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a "+bandeja+".Descripcion:" + tr004s.getErrorMessage());
				actividadEJB.terminar(actDto);
				return;
			}
        }
        /*
        catch (NamingException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NAMING);
        }
         */
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (NamingException e)
		{
        	bExecution.setErrorOp(true);
        	log.error(" Creacion de Local Home Nulls",e);
        	throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
        }
        catch (CreateException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.CREATE);
        }
        catch (TnProcesoExcepcion e)
        {
			bExecution.setErrorOp(true);
        	e.printStackTrace();
            throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS) ;
        }
        catch (FinderException e)
        {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}
		catch (Exception e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
	//CR14657 - Granite - Pablo L 21/10/2008
	public void actualizarInventarioSTBGR (TR512S tr512s)
    throws ATiempoAppEx
    {
        
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr512s, log);
			bExecution.setNumPetAtiempo(new Long(tr512s.getAtiempoRequestNumber()));
			bExecution.setIdMensajeOp(tr512s.getId());
			bExecution.startOperation();
				
            // busca el registro del mensaje
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
			Mensaje_estadoLocal mensajeErrorLocal=msgEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
            Mensaje_estado_lineaLocal mensajeEstadoLinea = buscaMensajeEstadoLinea (tr512s.getId ()) ;
            
            if (mensajeEstadoLinea == null)
            {
                log.debug (
                    "No Existe Respuesta en la base de enviados\n "
                    + XMLUtilities.marshall (tr512s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr512s.getId(),ATiempoAppEx.EXCEPTION);
            }
            
            // Validacion del estado de la respuesta
            
			Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoLinea.getF_reference_14 ().getPrimaryKey () ;
            
            if ( mensaje_estadoKey.cod_estado.intValue() == estadoOk)
            {
                log.debug (
                    "Es estado de la respuesta es diferente para ser procesado\n "
                    + XMLUtilities.marshall (tr512s));
                return;
            }
            
            // todo ok, se graba la respuesta
		//if(tr512s.isError() ==false && tr512s.isResponse()==true)
            if(tr512s.getErrorCode() == 0)
		{
            Long idConCuatro = new Long (dbSeq.seqNextValLong ("CORRELATIVO_APSC")) ;
            
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Respuesta_conect4_apscLocalHome resConCuatroAPSCLocalHome = (Respuesta_conect4_apscLocalHome) HomeFactory.getHome (Respuesta_conect4_apscLocalHome.JNDI_NAME) ;        
            Respuesta_conect4_apscLocal conCuatro = resConCuatroAPSCLocalHome.create (idConCuatro) ;
            
			
            // conCuatro.setMensaje_estado_linea (mensajeEstadoLinea) ;
            conCuatro.setF_fk_01_respuesta_ (mensajeEstadoLinea) ;
            
            // TODO revisar si es correcto sacar el numero de peticion de la tabla de mensaje
            // Â¿no deberia estar en el mensaje XML y compararse con el valor de la tabla?
            // Â¿sirve de algo tenerlo repetido entre ambas tablas?
            
            // conCuatro.setNumeroPeticion (mensajeEstadoLinea.getNumeroPeticion ()) ;
            
            conCuatro.setPeti_numero (mensajeEstadoLinea.getPeti_numero ()) ;
            
            // conCuatro.setTelefonoAsignado (new Long (tr004s.getPhoneNumber ())) ;
            conCuatro.setTelefono_asg (new Long (tr512s.getPhoneNumber ())) ;
            
            
            // TODO: ver que hacer con
            //
            // conCuatro.setMensaje_error ()
            // conCuatro.setMensajeError (...)  new TR010S ().getErrorMessage ()
            // conCuatro.setInd_error () ;
            // conCuatro.setIndicadorError (...) new TR010S ().isError ()
            //
            
            
            
            // actualiza el estado del mensaje
            // mensajeEstadoLinea.setCodigoEstado (new Integer (estadoOk)) ;
            
            Mensaje_estadoLocal mensajeEstadoOk = buscaMensajeEstadoOk () ;
            
            mensajeEstadoLinea.setF_reference_14 (mensajeEstadoOk) ;
            
			if(tr512s.getPhoneNumber() > 0){
				//Actualizo el fono en la BD por el que me envia la actualizacion de inventario
				Collection recursosLineaBasica = mensajeEstadoLinea.getPeticion().getRecursos_linea_basica();
				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				//Si tiene bien los recursos
				if ( recursosLineaBasica.size()==1 ){
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)  recursosLineaBasica.iterator().next();
					recursos_linea_basicaLocal.setTelefono_asg(new Long (tr512s.getPhoneNumber ()));
				}
			}
            // cierra la actividad automatica
            
            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
            
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoLinea.getCod_actividad_generadora ()) ;
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLinea.getPeti_numero (), mensajeEstadoLinea.getCod_actividad_generadora ()) ;

            // TODO ver si hay que setear alguna variable antes de responder al WF
            // actividadEJB.setDato (DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
            // actividadEJB.setObservacion ("Error en la Generaciï¿½n  de Orden de Servicio");
            actividadEJB.terminar (actDto) ;
			}
			else
			{
		
				mensajeEstadoLinea.setF_reference_14(mensajeErrorLocal);
				mensajeEstadoLinea.setFecha_cierre(df.format (new java.util.Date ()));
				
				//modificado adecarlini
				PeticionLocal peticionLocal = mensajeEstadoLinea.getPeticion();
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				//
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLinea.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLinea.getCod_actividad_generadora());
				//agregado por adecarlini
			    //String codError = String.valueOf(tr512s.getTypeError());
				String codError = String.valueOf(tr512s.getErrorCode());
				String nombreClase=TR512S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR004S.class.getName());
				if (errorLegado != null){
					if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
						actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr512s.getErrorMessage());
						actividadEJB.terminar(actDto);
						return;
					}
				    peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
				    plataforma = errorLegado.getPlataforma();
				    bandeja = getNombreBandeja(plataforma);
					insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else{
					insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());	
				}
				// fin agregado
				//modificado adecarlini
				//insertarCausalesCnaPeticion(mensajeEstadoLinea, mensajeEstadoLinea.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				//fin modificado
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a "+bandeja+".Descripcion:" + tr512s.getErrorMessage());				
				actividadEJB.terminar(actDto);
		
				return;

			}
        }
        /*
        catch (NamingException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NAMING);
        }
         */
        catch (NumberFormatException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
		catch (NamingException e)
		{
			bExecution.setErrorOp(true);
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
        catch (CreateException e)
        {
			bExecution.setErrorOp(true);
            throw new ATiempoAppEx (ATiempoAppEx.CREATE);
        }
        catch (TnProcesoExcepcion e)
        {
			bExecution.setErrorOp(true);
        	e.printStackTrace();
            throw new ATiempoAppEx (ATiempoAppEx.TN_PROCESS) ;
        }
        catch (FinderException e)
        {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}
		catch (Exception e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
    }
    
    }
    /*
     *
     */
	/* CR-14657 Granite - Pablo L - 16/10/2008 */
	/*___Actualizar Inventario STB Granite__*/
	public void enviarInventarioSTBGR (long idPeticion, ActividadEJBDTO act)
    throws ATiempoAppEx
    {
        try
        {
            // obtiene un nuevo id de mensaje            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            // crea y llena la representacion Java del mensaje
            TR512E tr512e = new TR512E ();
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
            PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;  
            Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
            
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;        
            
			/*
			 * GESTION DE BAJAS
			 */
            if(peticion.getTica_id().equals("B")){
				tr512e.setInterfaz("BajaSIRSAct");
			}else{
				tr512e.setInterfaz("AltaSIRSAct");
			}
			
			tr512e.setSource("ATIEMPO");
			tr512e.setVersion("1.0");
			tr512e.setDestination("ESB");
			
            
			Collection producto_servicio_peticionArray = peticion.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			
			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				if(peticionesDelegate.pasaPSyOcXActividad(new Long(idPeticion),productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
					tr512e.setComercialOperation(operacion_comercialKey.opco_id.intValue());
					
					log.debug(">>>>>>>>>>>>>OP COM - ACTUALIZAR INVENTARIO:" + tr512e.getComercialOperation());
					log.debug(">>>>>>>>>>>>>COD ACTIVIDAD - ACTUALIZAR INVENTARIO:" + act.getCodigoActividad());
				}
			}

            tr512e.setId (idCorrelativoMensaje.toString ()) ;
            String departamento = ((DepartamentoKey) peticion.getFk_02_departamento ().getPrimaryKey ()).cod_dpt ; // PETICION.COD_OPT
            String localidad = ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ;       // PETICION.COD_LOC            
            Collection colConect2 = peticion.getRecursos_linea_basica () ;            
            if (colConect2.size () != 1)
            {
                String error = "La peticion " + idPeticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
                throw new ATiempoAppEx (error) ;
            }
            
            Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
			PeticionKey peticionKey = (PeticionKey) peticion.getPrimaryKey();
			Peticion_atisKey petAtisK = (Peticion_atisKey)peticion.getFk_01_pet_atis().getPrimaryKey();
			tr512e.setAtisRequestNumber (petAtisK.cod_pet_cd) ;//At-1924
			//tr512e.setAtisRequestNumber (peticionKey.peti_numero.longValue()) ;
			tr512e.setAtiempoRequestNumber(peticionKey.peti_numero);
			tr512e.setDepartment (Integer.parseInt (departamento)) ;  
			tr512e.setCity (Integer.parseInt (localidad)) ;
			tr512e.setOdsNumber (recursosStb.getOds_apsc ().intValue ()) ;
			tr512e.setRequestDate(new java.util.Date ());
			
			
			tr512e.setHbIndicator(' ');
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			if(operacion_comercialLocal.getOpco_tras()!= null && operacion_comercialLocal.getOpco_tras().equals("T")){
				RecursosInterfaces recSTB = new RecursosDelegate();
				InformacionTecnicaDTO infSTB=recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
				if (infSTB.LineaNueva!=null && infSTB.LineaExistente != null){// es traslado lb pk tiene las dos lineas
					tr512e.setHbIndicator('N');
				}else{
					tr512e.setHbIndicator('S');
				}
			}
			//inicio CR-22569 agonz 25 feb 2009
			//Aqui segun el 22569 habria que settear la coleccion de SS
			//tr512e.setSpecialServices();
			ArrayList psServiciosSuplementarios = new ArrayList();
			SpecialServicesRequest srs = null;
			Long psId=null;
			int action = 0;
			
			for(Iterator iter = peticion.getProducto_servicio_peticion().iterator();iter.hasNext();){
				
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				
				psId = productoServicoKey.ps_id;
				
				if(peticionesDelegate.pasaPSyOcXActividad(new Long(idPeticion),productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
					log.debug("producto_servicio_peticion ID: "+producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue());
					if (producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue()!=familiaPcLinea || producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue()!=familiaIP ){//AT-2101 Servicios suplementarios Correccion - 20 abril 2009
				
						if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0)
						{
							log.debug("producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0 == TRUE");
							Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
							if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError){
								
								action=1;
							}
							
						
						}
						srs = new SpecialServicesRequest();
						srs.setSpecialService(psId.toString());
						srs.setAction(action);					
						psServiciosSuplementarios.add(srs);
						log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());
						action = 0;
					}
				}
			}
			
			tr512e.setSpecialServices(psServiciosSuplementarios);
			
			//fin CR-22569 agonz 25 feb 2009
			
			//n : LB
			//s : LB + BA
			
            Mensaje_estado_lineaLocal dbmsg;
            Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
            dbmsg = msgEstadoLineaLocalHome.create (idCorrelativoMensaje);
            //dbmsg.setCod_actividad_generadora (codigoActividad) ;
            dbmsg.setCod_actividad_generadora (act.getCodigoActividad());
            
            dbmsg.setF_reference_13 (buscaConector (codigoConectorCuatro)) ;
            
            dbmsg.setF_reference_14 (this.buscaMensajeEstado (estadoEspera)) ;
            
            dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
            
            dbmsg.setPeti_numero (new Long (idPeticion)) ;
           
            //requerimiento FTTC
            Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome (Recursos_linea_basicaLocalHome.JNDI_NAME) ;        
            Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(new Long (idPeticion));
            
            if(recursos_linea_basicaLocal.getPassword_fttc() != null)
            	tr512e.setPassword_fttc(recursos_linea_basicaLocal.getPassword_fttc());
            else
            	tr512e.setPassword_fttc("");
            
            new ActualizacionInventarioSTBGRMQ ().enviarMensaje (tr512e) ;
            
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
        catch (NamingException e)
        {
			log.error ("error creando registro", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}
		catch (Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
    }
    public void enviarInventarioSTB (long idPeticion, String codigoActividad)
    throws ATiempoAppEx
    {
        try
        {
            // obtiene un nuevo id de mensaje
            
            Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
            
            
            // crea y llena la representacion Java del mensaje
            
            TR004E tr004e = new TR004E ();
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
            PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;        
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            tr004e.setId (idCorrelativoMensaje.toString ()) ;
            
            //long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
            String departamento = ((DepartamentoKey) peticion.getFk_02_departamento ().getPrimaryKey ()).cod_dpt ; // PETICION.COD_OPT
            String localidad = ((LocalidadKey) peticion.getFk_01_localidad ().getPrimaryKey ()).cod_loc ;       // PETICION.COD_LOC
            
            Collection colConect2 = peticion.getRecursos_linea_basica () ;
            
            if (colConect2.size () != 1)
            {
                String error = "La peticion " + idPeticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
                throw new ATiempoAppEx (error) ;
            }
            
            Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
			PeticionKey peticionKey = (PeticionKey) peticion.getPrimaryKey();
            tr004e.setAtisRequestNumber (peticionKey.peti_numero.longValue()) ;  
            tr004e.setDepartment (Integer.parseInt (departamento)) ;  
            tr004e.setCity (Integer.parseInt (localidad)) ;
            tr004e.setOdsNumber (recursosStb.getOds_apsc ().intValue ()) ;
            
            // TODO ver como mapear ...
            // tr004e.setRequestInternalCode ()
            
            
            Mensaje_estado_lineaLocal dbmsg;
            Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
            dbmsg = msgEstadoLineaLocalHome.create (idCorrelativoMensaje);
            
            // para la version anterior del bean
            // msg.setCodigoActividad (codigoActividad) ;
            // msg.setCodigoConector (codigoConectorCuatro) ;
            // msg.setCodigoEstado (new Integer (estadoEspera)) ;
            // msg.setFechaEnvio (df.format (new java.util.Date ())) ;
            // msg.setNumeroPeticion (new Long (peticion)) ;
            
            dbmsg.setCod_actividad_generadora (codigoActividad) ;
            
            dbmsg.setF_reference_13 (buscaConector (codigoConectorCuatro)) ;
            
            dbmsg.setF_reference_14 (this.buscaMensajeEstado (estadoEspera)) ;
            
            dbmsg.setFecha_envio (df.format (new java.util.Date ())) ;
            
            dbmsg.setPeti_numero (new Long (idPeticion)) ;
            //requerimiento FTTC
            Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome (Recursos_linea_basicaLocalHome.JNDI_NAME) ;        
            Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(new Long (idPeticion));
            tr004e.setPassword_fttc(recursos_linea_basicaLocal.getPassword_fttc());
            
            // TODO ver como llenar
            // msg.setArea () ;
            // msg.setTelefono () ;
            
            // llama a la rutina que envia el mensaje
            new ActualizacionInventarioSTBMQ ().enviarMensaje (tr004e) ;
            
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
        }
        catch (NamingException e)
        {
			log.error ("error creando registro", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}
		catch (Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
    }
    
    /*
     *
     */
    
    public void enviarInventarioSS (long idPeticion, String codigoActividad,Integer actividadFlujo)
    throws ATiempoAppEx
    {
        try
        {
            
			// crea y llena la representacion Java del mensaje
            
			TR011E tr011e = new TR011E ();
            
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;        
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
			// mapeo entre modelo BD y el mensaje
            
			// telefono
			int phoneNumber=0;
			
            Collection colRecursoStb = peticion.getRecursos_linea_basica () ;
            
			if(colRecursoStb.size () ==0)
			{
				if(peticion.getNum_ide_nu_stb()!=null)
					phoneNumber=new Integer(peticion.getNum_ide_nu_stb()).intValue();
			}
			else if(colRecursoStb.size () == 1)
			{
				Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colRecursoStb.iterator ().next () ;
				if(recursosStb.getTelefono_asg()!=null)
					phoneNumber=recursosStb.getTelefono_asg().intValue ();		
			}
			else
			{
				String error = "La peticion " + idPeticion + " referencia a " + colRecursoStb.size () + " registro(s) 'recursos linea basica': no se de donde sacar el telefono" ;
				throw new ATiempoAppEx (error) ;
			}
			
			tr011e.setPhoneNumber (phoneNumber) ;
          
			//Peticion_atisKey paK = (Peticion_atisKey) peticion.getFk_01_pet_atis().getPrimaryKey();
			PeticionKey peticionKey = (PeticionKey) peticion.getPrimaryKey();
			tr011e.setAtisRequestNumber (peticionKey.peti_numero.longValue()) ;   
        	
			// TODO VMM van solo los PS que llaman a la actividad flujo
			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			//Obtengo los ps de SS
//			ArrayList colPSs = peticionesDelegate.listaPsDePeticionQuePasaPorActividad(new Long(idPeticion),actividadFlujo);
//			Iterator iterPSs = colPSs.iterator () ;
			
            //Separo los ps de alta de los de baja
			ArrayList msgPSAlta = new ArrayList () ;
			ArrayList msgPSBaja = new ArrayList () ;
						           
//			log.debug("Numero de Ps que Pasan por la Actividad:" + colPSs.size());
			Collection collection=peticion.getProducto_servicio_peticion();
			Collection estado_ps_peticion = null;
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey = (Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				
				boolean pasaPorActividad=peticionesDelegate.pasaPSyOcXActividad(peticionKey.peti_numero,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,actividadFlujo);
				//si pasas por la actividad revisar el estado
				if (pasaPorActividad){
					estado_ps_peticion = producto_servicio_peticionLocal.getEstado_ps_peticion();
					if(estado_ps_peticion!=null && estado_ps_peticion.size()!=0)
					{
						Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
						Integer cod=estado_ps_peticionLocal.getCod_estado_cierre();
						if (cod.intValue() != estadoCierreError){
							
							log.debug("PS/TipoOperCom:" + producto_servicioKey.ps_id.intValue() + "/" + operacion_comercialKey.opco_id);
							//dependiendo el tipo de operacion comercial es donde guardo el ps
							if (operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_ALTA")) || operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_CP_ALTA"))){
								msgPSAlta.add (new Integer(producto_servicioKey.ps_id.intValue())) ;	
							}
							else if (operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_BAJA")) || operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_CP_BAJA"))){
								msgPSBaja.add (new Integer(producto_servicioKey.ps_id.intValue())) ;
							}
							
						}
						
					}else{
						
						log.debug("PS/TipoOperCom:" + producto_servicioKey.ps_id.intValue() + "/" + operacion_comercialKey.opco_id);
						//dependiendo el tipo de operacion comercial es donde guardo el ps
						if (operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_ALTA")) || operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_CP_ALTA"))){
							msgPSAlta.add (new Integer(producto_servicioKey.ps_id.intValue())) ;	
						}
						else if (operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_BAJA")) || operacion_comercialLocal.getOpco_tipo().equals(VpistbbaConfig.getVariable("TIPO_OC_CP_BAJA"))){
							msgPSBaja.add (new Integer(producto_servicioKey.ps_id.intValue())) ;
						}
					}
				}
				
			}
			
			Long idCorrelativoMensaje = null;
			PeticionKey peticionKeyEstados = (PeticionKey) peticion.getPrimaryKey();

			//Se envian mensajes dependiendo si hay ps SS de bajas y altas
			if(this.getErrorMessageE(peticionKeyEstados.peti_numero, codigoActividad, bajaRecursoLineaBasica))
			{
					if(msgPSBaja.size()>0){
						log.debug("Actualizacion Inventario de SS de baja");
						//envio el mensaje con los ps de BAJA
						tr011e.setAction (2) ; //Seteo la accion Baja
						tr011e.setServices (msgPSBaja) ;
						
						idCorrelativoMensaje=new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
						
						tr011e.setId(String.valueOf(idCorrelativoMensaje));
						// inserta el registro del mensaje (para poder correlacionar la respuesta)				
						//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
						Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
						Mensaje_estado_lineaLocal msg = msgEstadoLineaLocalHome.create (idCorrelativoMensaje) ;
		            
						msg.setCod_actividad_generadora (codigoActividad) ;
		            
						msg.setF_reference_13 (buscaConector (codigoConectorSiete)) ;
		            
						msg.setF_reference_14 (this.buscaMensajeEstado (estadoEspera)) ;
		            
						msg.setFecha_envio (df.format (new java.util.Date ())) ;
		            
						msg.setPeti_numero (new Long (idPeticion)) ;
						msg.setAccion("b");
						// TODO ver como llenar
						// msg.setArea () ;
						// msg.setTelefono () ;					
		
						// llama a la rutina que envia el mensaje
						new ActualizacionInventarioSSMQ ().enviarMensaje (tr011e) ;
					}
			}
			
			if (this.getErrorMessageE(peticionKeyEstados.peti_numero, codigoActividad, altaRecursoLineaBasica)){
					if(msgPSAlta.size()>0){
						log.debug("Actualizacion Inventario de SS de Alta");
						//Envio el mensaje con los ps de  ALTA
						tr011e.setAction (1) ; //Seteo la accion Alta
						tr011e.setServices (msgPSAlta) ;
						
						idCorrelativoMensaje=new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
						
						tr011e.setId(String.valueOf(idCorrelativoMensaje));
						// inserta el registro del mensaje (para poder correlacionar la respuesta)				
						//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
						Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
						Mensaje_estado_lineaLocal msg = msgEstadoLineaLocalHome.create (idCorrelativoMensaje) ;
		            
						msg.setCod_actividad_generadora (codigoActividad) ;
		            
						msg.setF_reference_13 (buscaConector (codigoConectorSiete)) ;
		            
						msg.setF_reference_14 (this.buscaMensajeEstado (estadoEspera)) ;
		            
						msg.setFecha_envio (df.format (new java.util.Date ())) ;
		            
						msg.setPeti_numero (new Long (idPeticion)) ;
						
						msg.setAccion("a");
						// TODO ver como llenar
						// msg.setArea () ;
						// msg.setTelefono () ;					
		
						// llama a la rutina que envia el mensaje
						new ActualizacionInventarioSSMQ ().enviarMensaje (tr011e) ;
					}
			}
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT,e);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER,e);
        }
        catch (CreateException cex)
        {
            log.error ("error creando registro", cex) ;
            throw new ATiempoAppEx (ATiempoAppEx.CREATE,cex) ;
        }
        catch (NamingException e)
        {
			log.error ("error creando registro", e) ;
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e) ;
		}
		catch (Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
    }
    
    
    public boolean getErrorMessageE(Long peticion, String codActividad,String accion) throws ATiempoAppEx{
    	boolean activa = true;
    	
    	try{
    	    //	TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
    		Mensaje_estado_lineaLocalHome mensaje_estado_lineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
			Collection estados = mensaje_estado_lineaLocalHome.findUltimaAcccion(peticion,accion);
			if(estados != null && estados.size() != 0){	
				Mensaje_estado_lineaLocal mensaje_estado_lineaLocal = (Mensaje_estado_lineaLocal) estados.iterator().next();
				Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_lineaLocal.getF_reference_14().getPrimaryKey();
	
				if(mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(codActividad)){
					if(mensaje_estadoKey.cod_estado.intValue() == estadoError){
						activa = true;
	
					}else if(mensaje_estadoKey.cod_estado.intValue() == estadoOk) {
						activa = false;
					}
				}
			}
    	} catch (FinderException e) {
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
    	
    	return activa;
    }
    /*
     *
     */
    
    private Mensaje_estadoLocal buscaMensajeEstadoOk ()
    {
        return (buscaMensajeEstado (estadoOk)) ;
    }
    
    /*
     *
     */
    
    private Mensaje_estadoLocal buscaMensajeEstado (int llave)
    {
        try
        {
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
        	Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
            Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (llave)) ;
            
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstado) ;
        }
        catch (FinderException fex)
        {
            return (null) ;
        }
        catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	return (null);
        }
        
    }
    
    /*
     *
     */
    
    private Mensaje_estado_lineaLocal buscaMensajeEstadoLinea (String correlativo)
    {
        try
        {
            return buscaMensajeEstadoLinea (Long.parseLong (correlativo)) ;
        }
        catch (NumberFormatException nfex)
        {
            return (null) ;
        }
    }
    
    /*
     *
     */
    
    private Mensaje_estado_lineaLocal buscaMensajeEstadoLinea (long correlativo)
    {
        try
        {
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
        	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
            Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey (new Long (correlativo)) ;
            
            Mensaje_estado_lineaLocal mensajeEstadoLinea = msgEstadoLineaLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstadoLinea) ;
        }
        catch (FinderException fex)
        {
            return (null) ;
        }catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	return (null) ;
        }
        
    }
    
    /*
     *
     */
    
    private ConectorLocal buscaConector (int codigoConector)
    {
        try
        {
        	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
            ConectorKey key = new ConectorKey (new Integer (codigoConector)) ;
            ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
            return (ConectorLocal) conectorLocalHome.findByPrimaryKey (key) ;
        }
        catch (FinderException fex)
        {
            return (null) ;
        }catch (NamingException e)
		{
        	log.error(" Creacion de Local Home Nulls",e);
        	return (null) ;
        }
    }
    

	private boolean getEstadoMultipleMensajes(Mensaje_estado_lineaLocal mensajeEstadoLineaLocal, Integer estado) throws ATiempoAppEx
	{
		
			Mensaje_estado_lineaLocal mensaje_estado_lineaLocal2 = null;  
			Mensaje_estado_lineaKey mensaje_estado_lineaKey = (Mensaje_estado_lineaKey) mensajeEstadoLineaLocal.getPrimaryKey();
		       
		   try {
	   	
				//Arreglo de registros de peticion pendientes
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome (Mensaje_estado_lineaLocalHome.JNDI_NAME) ;
				Collection estadoMensajes = msgEstadoLineaLocalHome.findByPeticionEstadoActividad(mensajeEstadoLineaLocal.getPeti_numero(), estado,mensajeEstadoLineaLocal.getCod_actividad_generadora());
			
				//Si que da el 1, siempre sera el ultimo
				log.debug("El numero de mensajes en estado espera es:" + estadoMensajes.size());
				if(estadoMensajes.size() == 1){
				
					for (Iterator iter= estadoMensajes.iterator();iter.hasNext();){
					
						mensaje_estado_lineaLocal2 = (Mensaje_estado_lineaLocal)iter.next();
						Mensaje_estado_lineaKey mensaje_estado_lineaKey2 = (Mensaje_estado_lineaKey) mensaje_estado_lineaLocal2.getPrimaryKey();
					//Comparacion de corralativos de mensajes
						log.debug("Correlativo1:" + mensaje_estado_lineaKey2.correlativo);
						log.debug("Correlativo2:" + mensaje_estado_lineaKey.correlativo);
						if(mensaje_estado_lineaKey2.correlativo.longValue() == mensaje_estado_lineaKey.correlativo.longValue())
						{
							log.debug("Es igual");
							return true;
						}
						else
						log.debug("NO Es igual");
					
					}
				
					return false;
				
				}else{
				
					return false;
				}
		   }
		   catch (FinderException fex)
		   {
				return false;
		   }
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
			catch (Exception e)
			{
				log.debug("Exception",e);
				throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
			}
		}
		
		
	private void insertarCausalesCnaPeticion(Mensaje_estado_lineaLocal mensaje_estado_lineaLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws Exception 
	{
		PeticionLocal peticionLocal=mensaje_estado_lineaLocal.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Automáticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);

		UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
		Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_ps_peticionLocalHome	estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);

		ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
		ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
		Long idAct=actividadSessionLocal.getIdActividad(cod_actividad,new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
		if(idAct==null)
			return;								
		
		log.debug("Estoy insertando los causales de CNA para la peticion:"+peticionKey.peti_numero);
		for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
			if(peticionesDelegate.pasaPSyOcXActividad(peticionKey.peti_numero,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
			{
				//Quiere decir que tengo que asociarle la causal a los ps que invocaron esta actividad.

				Collection listaEstadoPsPet=producto_servicio_peticionLocal.getEstado_ps_peticion();
				if(listaEstadoPsPet.size()>0)
				{	
			
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
			
					//Tengo una causa asociada tengo que actualizar
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
					estado_ps_peticionLocal.setCod_actividad(idAct);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());

					Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					Fecha fecha=new Fecha();
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
				else
				{
			
					Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(codCausal);
					Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);
			
					long correlativo=manager.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
					Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),producto_servicio_peticionLocal.getProducto_servicio(),producto_servicio_peticionLocal);
					estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
					estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
					estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					estado_ps_peticionLocal.setCod_actividad(idAct);

			
					Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
					Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
					UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
					long id_causal_peticion=manager.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
					Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
					Fecha fecha=new Fecha();
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
			}
		}
	}
	
	private String getNombreBandeja(String plataforma){
		return VpistbbaConfig.getVariable("BANDEJA_"+plataforma);
	}
	
	private ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
		ErrorLegadoLocal errorLegado = null;
		try{
			ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
			errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
		} catch (javax.ejb.FinderException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			log.debug("No se encontro el error legado..codigo..: " + codigoError +" TR..:"+ codigoTr + " se inserta tipo de error por defecto");
			
		}
		return errorLegado;
	}
	
    public boolean tieneODS (long idPeticion)   throws ATiempoAppEx
    {
        try
        {
           
            
            PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
            
            //TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
            PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;        
            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
            
            
            Collection colConect2 = peticion.getRecursos_linea_basica () ;
            
            if (colConect2.size () != 1)
            {
                String error = "La peticion " + idPeticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se puede validar la ods" ;
                throw new ATiempoAppEx (error) ;
            }
            
            Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
			
            if (recursosStb.getOds_apsc ()!=null){
            	return true;
            }else{
            	return false;
            }

            
        }
        catch (NumberFormatException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
        }
        catch (FinderException e)
        {
            throw new ATiempoAppEx (ATiempoAppEx.FINDER);
        }
		catch (Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
    }
}
