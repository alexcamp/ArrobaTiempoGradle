/*
 * Creado el 25/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.naked.ejb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_modemLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionModemAutoInstalacionMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocalHome;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ACSServicio
 */
public class ACSServicioBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements ACSServicioInterfaz {

	//Variables de Utils
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private DBManager dbSeq;
	private SimpleDateFormat df;
	
	//Variables del mensaje
	private Mensaje_estadoLocalHome mensaje_estadoLocalHome;
	private Mensaje_estadoLocal mensajeEspera;
	
	//Variables del mensaje para BA
	private Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome;
	private Mensaje_estado_baLocal mensajeEstadoBALocal;
	
	//Logica de los recursos
	private PeticionesDelegate pDelegate;
	private PeticionLocalHome peticionLocalHome;
	private PeticionLocal peticionLocal;
	private PeticionKey peticionkey;
	private Long idCorrelativo;
	private Producto_servicioLocalHome producto_servicioLocalHome;
	private Familia_producto_servicioKey fampsKey;
	
	//Conector
	private ConectorLocalHome conectorLocalHome;
	
	//	Logica de los recursos para BA
	private Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome;
	private boolean tieneBaEnVuelo;
	private Long PSCode;
	private Long COCode;
	private String velocidadPlan;
	
	//Variables Logica de ACS
	private ACSServicioBean acsServicioPorOCBean;
	
	//TR que almacena los datos
	private TR135E tr135e;
	
	/**
	 *
	 */
	public ACSServicioBean() {
	}
	
	public ACSServicioBean (ACSServicioBean acsServicioBean){
		this.log = acsServicioBean.log;
		this.dbSeq = acsServicioBean.dbSeq;
		this.df = acsServicioBean.df;
		this.mensaje_estadoLocalHome = acsServicioBean.mensaje_estadoLocalHome;
		this.mensajeEspera = acsServicioBean.mensajeEspera;
		this.mensajeEstadoBaLocalHome = acsServicioBean.mensajeEstadoBaLocalHome;
		this.mensajeEstadoBALocal = acsServicioBean.mensajeEstadoBALocal;
		this.pDelegate = acsServicioBean.pDelegate;
		this.peticionLocalHome = acsServicioBean.peticionLocalHome;
		this.peticionLocal = acsServicioBean.peticionLocal;
		this.peticionkey = acsServicioBean.peticionkey;
		this.idCorrelativo = acsServicioBean.idCorrelativo;
		this.producto_servicioLocalHome = acsServicioBean.producto_servicioLocalHome;
		this.fampsKey = acsServicioBean.fampsKey;
		this.conectorLocalHome = acsServicioBean.conectorLocalHome;
		this.ps_Tipo_ModemLocalHome = acsServicioBean.ps_Tipo_ModemLocalHome;
		this.tieneBaEnVuelo = acsServicioBean.tieneBaEnVuelo;
		//this.acsServicioPorOCBean = acsServicioBean.acsServicioPorOCBean;
		this.tr135e = acsServicioBean.tr135e;
	}
	
	public void setSessionContext (SessionContext ctx) throws EJBException, RemoteException {
	
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		df = new SimpleDateFormat ("yyyy/MM/dd") ;	
	}
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.atiempo.dto.ModemVpiDTO, co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, boolean)
	 */
	public void enviarAutoConfiguracion(ModemVpiDTO modem, ActividadEJBDTO act, Long petiNumero, boolean esAutoinstalacion) throws ATiempoAppEx {
		enviarAutoConfiguracion(modem, act.getCodigoActividad(), "");
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAutoConfiguracion (ModemVpiDTO modem, String act, String idMensajePadre) throws ATiempoAppEx {
		
		idCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		try {
			//contruir las variables que se van a usar
			construirObjetos(modem.getPeti_numero());
			
			//Construcciòn inicial de la TR135E
			inicializarAutoConfiguracion(modem);
			
			//SELECCION DE LA LOGICA DE AUTOCONFIGURACION POR OC
			if (peticionLocal.getTica_id().toUpperCase().equals("A"))
				//acsServicioPorOCBean = new ACSAltaServicioBean(this);
				tr135e =Alta(modem);
			else if (peticionLocal.getTica_id().toUpperCase().equals("B"))
				//acsServicioPorOCBean = new ACSBajaServicioBean(this);
				tr135e =Baja(modem);
			else if (peticionLocal.getTica_id().toUpperCase().equals("P"))
				//acsServicioPorOCBean = new ACSPosVentaServicioBean(this);
				tr135e =Postventa(modem);
			else
				acsServicioPorOCBean = null;
			
			//tr135e = acsServicioPorOCBean.autoConfigurarPorOC(modem);
				
			//Finaliza con la configuracion de las acciones de los modems (SIN COPY PASTE AUN)
			finalizarAutoConfiguracion(modem, act, idMensajePadre);
			
			//Envia la TR135E
			ConfiguracionModemAutoInstalacionMQ enviarMensaje = new ConfiguracionModemAutoInstalacionMQ();
			enviarMensaje.enviarMensaje(tr135e);
		
		} catch (Exception e) {
			log.debug("ACSServicioBean.enviarAutoConfiguracion: " + e);
		}
		
	}
	
	public void construirObjetos (Long petiNumero) throws ATiempoAppEx  {
		try {
			//Se crea el mesansaje de estado para el aprovisionamiento de recursos
			mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
			mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			
			//Se consulta la peticion que viene con el modem
			pDelegate = new PeticionesDelegate();
			peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			peticionkey = new PeticionKey(petiNumero);
			peticionLocal = peticionLocalHome.findByPrimaryKey(peticionkey);
			
			//Instancio un conector de base de datos (LocalHome)
			producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome)HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			
			//esto no se que putas es?
			conectorLocalHome = (ConectorLocalHome)HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			
			//Construccion de Objetos especificos: Sacar esta porcion de codigo si se vuelve a abstraer en patron de diseño fabrica
			tr135e = new TR135E();
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.construirObjetos: " + e);
			log.error("ACSServicioBean.construirObjetos: " + e);
		} catch (FinderException e) {
			log.debug("ACSServicioBean.construirObjetos: " + e);
			log.error("ACSServicioBean.construirObjetos: " + e);
		} catch (Exception e) {
			log.debug("ACSServicioBean.construirObjetos: " + e);
			log.error("ACSServicioBean.construirObjetos: " + e);
		}
		
	}
	
	public void inicializarAutoConfiguracion (ModemVpiDTO modem) throws ATiempoAppEx {
		
		idCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		
		try {
			PSCode = new Long("0");
			COCode = new Long("0");
			velocidadPlan = "";
			
			tieneBaEnVuelo = false;
			
			//Creación de la cabecera
			tr135e.setId(idCorrelativo.toString());
			tr135e.setDestination("ESB");
			tr135e.setSource("ATIEMPO_VPI");
			tr135e.setInterfaz("CONFIG_MODEMACS"); 
			tr135e.setVersion("1.0");
			
			//INFORMACION DE ATIEMPO - PETINUMERO
			tr135e.setAtiempoRequestNumber(modem.getPeti_numero().toString());
			
			//INFORMACION DEL MODEM
			tr135e.setBrandModem(modem.getMarca());
			tr135e.setModelModem(modem.getModelo());
			tr135e.setSerialNumber(modem.getSerial());//PUNTO 1
			
			//Ver si la peticion tiene PSs BA en Vuelo
			Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome)HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			Collection psevList = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(peticionkey.peti_numero, ComunInterfaces.BA_EN_VUELO);
			for(Iterator psevIter=psevList.iterator(); psevIter.hasNext();){
				Producto_servicio_en_vueloLocal psevLocal = (Producto_servicio_en_vueloLocal)psevIter.next();
				tieneBaEnVuelo = true;
				PSCode = psevLocal.getPs_id();
				COCode = new Long (psevLocal.getOpco_id().toString());
			}
			
			//CAMPO EMAIL
			String fatherEmail = null;
			Collection recursosBaList = peticionLocal.getRecursos_ba();
			for (Iterator recursosBAIter = recursosBaList.iterator();recursosBAIter.hasNext();){
				Recursos_baLocal recursosBALocal = (Recursos_baLocal) recursosBAIter.next();
				fatherEmail = recursosBALocal.getFather_email_nuevo();
			}
			
			if (fatherEmail != null && fatherEmail.length()>0){
				tr135e.setFatherEmail(fatherEmail);
			}else{					
				tr135e.setFatherEmail(" ");
			}
						
			//CAMPO TELEFONO Y NUEVO TELEFONO
			Collection recursosLineaBasicaCollection = peticionLocal.getRecursos_linea_basica();
			if (recursosLineaBasicaCollection.size()!=0){
				for (Iterator recursosLineaBasicaIterator = recursosLineaBasicaCollection.iterator(); recursosLineaBasicaIterator.hasNext();){
					Recursos_linea_basicaLocal recursosLineaBasicaLocal = (Recursos_linea_basicaLocal)recursosLineaBasicaIterator.next();

					if(recursosLineaBasicaLocal.getPassword_fttc() != null)
						tr135e.setPasswordIMS(recursosLineaBasicaLocal.getPassword_fttc());
										
					if (recursosLineaBasicaLocal.getTelefono_ant()!=null 
							&& !recursosLineaBasicaLocal.getTelefono_ant().equals(new Long("0"))){
						tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_ant().toString()));
						if (recursosLineaBasicaLocal.getTelefono_asg()!=null){
							//HAY CAMBIO DE NUMERO
							log.debug("ACSServicioBean.inicializarAutoConfiguracion: Hay cambio de numero");
							tr135e.setNewPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asg().toString()));
							tr135e.setOperationAcs("CAMBIO_NUMERO");
						}else{
							tr135e.setNewPhoneNumber(new Integer("0"));
						}
					}else if (recursosLineaBasicaLocal.getTelefono_asg()!=null 
							&& !recursosLineaBasicaLocal.getTelefono_asg().equals(new Long("0"))){
						tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asg().toString()));
						tr135e.setNewPhoneNumber(new Integer("0"));
					}else{
						//NO VIENEN NINGUN NUMERO
						tr135e.setPhoneNumber(new Integer("0"));
						tr135e.setNewPhoneNumber(new Integer("0"));
					}
				}
			}else{
				if (peticionLocal.getNum_ide_nu_stb()!=null){
					tr135e.setPhoneNumber(new Integer(peticionLocal.getNum_ide_nu_stb()));
					tr135e.setNewPhoneNumber(new Integer("0"));
				}else{
					tr135e.setPhoneNumber(new Integer("0"));
					tr135e.setNewPhoneNumber(new Integer("0"));
				}
			}
			
			//Se asigna por solicitud de Broker
			tr135e.setServiceType("SERVICIO");
			
			PeticionesDelegate pDelegate = new PeticionesDelegate();
			String OUI = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OUI+tr135e.getModelModem());
			tr135e.setOUI(OUI);
		
		} catch (NamingException e) {
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (FinderException e) {
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (Exception e) {
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		}	
	}
	
	//public abstract TR135E autoConfigurarPorOC (ModemVpiDTO modem) throws ATiempoAppEx;

	public void finalizarAutoConfiguracion (ModemVpiDTO modem, String act, String idMensajePadre) throws ATiempoAppEx {
		
		try {
			
			boolean modemYaExistente = false;
			boolean tienePeticionesConfModems = false;
			Short accionModemLocal = new Short("0");
			//String cadenaInf = modem.getSerial() + ":";
			ModemLocalHome modemLocalHome  = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			
			//Mennsaje del estado de Banda Ancha (Al parecer cuando le asigna los datos tambien se quema en la BD)
			mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(idCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(fampsKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mensajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(act);

			if (idMensajePadre != null)
				mensajeEstadoBALocal.setDesc_error(idMensajePadre);
			
			log.debug(XMLUtilities.marshall(tr135e));
			
		} catch (CreateException e) {
			log.debug("ACSServicioBean.finalizarAutoConfiguracion: " + e);
		} catch (FinderException e) {
			log.debug("ACSServicioBean.finalizarAutoConfiguracion: " + e);
		} catch (Exception e) {
			log.debug("ACSServicioBean.finalizarAutoConfiguracion: " + e);
		}
		
	}

    /* (sin Javadoc)
     * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#recibirAutoConfiguracion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
     */
     public void recibirAutoConfiguracion(TR135S tr135s) throws ATiempoAppEx {
     	// TODO Apéndice de método generado automáticamente
     	log.debug("Entro al mètodo de respuesta de autoconfiguraciòn para la correlación; " + tr135s.getId());
     	try {
     		construirObjetos(new Long(tr135s.getAtiempoRequestNumber()));
     		RecursosBADelegate recursosbaDelegate = new RecursosBADelegate();
            
     		Mensaje_estadoLocal mensajeok = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
            Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
            mensajeEstadoBALocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr135s.getId())));

            Mensaje_estadoLocal estadoPeticion = mensajeEstadoBALocal.getMensaje_estado();
            Mensaje_estadoKey estadoPeticionKey = (Mensaje_estadoKey) estadoPeticion.getPrimaryKey();

            peticionLocal = mensajeEstadoBALocal.getPeticion();
            peticionkey = (PeticionKey) peticionLocal.getPrimaryKey();

            log.debug("Se inicializan variables para la petición "+peticionkey.peti_numero);

            String nombreClase = TR135S.class.getName();
            nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);

            ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
            IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBALocal.getCod_actividad_generadora());
            ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionkey.peti_numero, mensajeEstadoBALocal.getCod_actividad_generadora());

            Long idTmpModem = new Long(dbSeq.seqNextValLong("CORRELATIVO_TMP_MODEM"));

            String accionRealzada = "";
            String serialModem = "";
                                    
            Tmp_modemLocalHome tmp_modemLocalHome = (Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);

            //Mensaje de estado de base de datos si esta en espera o en error
            if (estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoEspera
            		|| estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoError) {
            	//codigo de error de la TR135S, se valida que haya codigo de error
            	if (tr135s.getErrorCode() != null && !tr135s.getErrorCode().equals("") && !tr135s.getErrorCode().equals("0")) {
            		//Venia de actividad configurar ACS
            		if (actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CONFIGURAR_ACS)
            				|| actDto.getCodigoActividad().equals(ComunInterfaces.ACT_DESCONFIGURAR_MODEM)) {
            			ErrorLegadoLocalHome errorHome = (ErrorLegadoLocalHome) HomeFactory.getHome(ErrorLegadoLocalHome.JNDI_NAME);
            			ErrorLegadoLocal errorLegado = null;
            			String plataforma = null;
            			try {
            				errorLegado = errorHome.findByErrorTr(tr135s.getErrorCode(), nombreClase);
            			} catch (FinderException e) {
            				// TODO Bloque catch generado automáticamente
            				log.debug("No se encuentra registro en la Base de Datos se deriva a PGI");
            				plataforma = VpistbbaConfig.getVariable("IDPGI");
            			}
            			if (errorLegado != null) {
            				plataforma = errorLegado.getPlataforma();
            				peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
            			}
            			actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
                        
            			//Genera procesos de quiebres de los PSs con codigo de error 701
            			//701: error en la configuracion automatica
            			recursosbaDelegate.insertarCausalesCnaPeticion(mensajeEstadoBALocal.getPeticion(), mensajeEstadoBALocal.getCod_actividad_generadora(), new Long("701"), actDto.getIdActividadFlujo());
            			mensajeEstadoBALocal.setMensaje_estado(mensajeError);
            			actDto.setObservacion("No se pudo realizar la configuraciòn en ACS del Mòdem");
            		}else{//Si viene de otra actividad
            			//busca los modems de la peticion y le coloca a cada uno la accion de modem no configurado
            			Collection modem = peticionLocal.getModem();
            			for (Iterator iter = modem.iterator(); iter.hasNext();) {
            				ModemLocal modemLocal =  (ModemLocal) iter.next();
            				if(modemLocal.getAccion().intValue() != ComunInterfaces.accionModemLiberar){
            					modemLocal.setAccion(new Short (String.valueOf(ComunInterfaces.accionModemNoConfigurado)));
                				mensajeEstadoBALocal.setMensaje_estado(mensajeok);
                				ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
//                				accionRealzada = "error";
                				serialModem = modemKey.serial;
            				}else{
            					modemLocal.remove();
            				}
            			}
            			mensajeEstadoBALocal.setMensaje_estado(mensajeok);
            			actDto.setObservacion("No se pudo realizar la configuraciòn en ACS del Mòdem");
            		}
            	} else{
            		//si no fuera un error de la tr135S asigna el estado como ok
            		mensajeEstadoBALocal.setMensaje_estado(mensajeok);
            		Collection modem = peticionLocal.getModem();
            		for (Iterator iter = modem.iterator(); iter.hasNext();) {
            			ModemLocal modemLocal =  (ModemLocal) iter.next();
            			log.debug("se valida modem con accion: "+modemLocal.getAccion().intValue() );
            			if(modemLocal.getAccion().intValue() == ComunInterfaces.accionModemNoAction
            					||(modemLocal.getAccion().intValue() == ComunInterfaces.accionModemNoConfigurado && modem.size() == 1)){
            				modemLocal.setAccion(new Short (String.valueOf(ComunInterfaces.accionModemConfigurado)));
            				ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
//            				accionRealzada = "Conf";
            				serialModem = modemKey.serial;
            			}else{
            				modemLocal.remove();
            			}
            		}
            	}

            	if(peticionLocal.getTica_id().equals("A"))
            		accionRealzada = new Integer(ComunInterfaces.operacionParActivar).toString();
            	if(peticionLocal.getTica_id().equals("B"))
            		accionRealzada = new Integer(ComunInterfaces.operacionParDesactivar).toString();
            	if(peticionLocal.getTica_id().equals("P"))
            		accionRealzada = new Integer(ComunInterfaces.altaMigracionPS).toString();
            	
            	Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome) HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
            	Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.create(new Integer(tr135s.getId()));
            	mensajeConfACSLocal.setXml(XMLUtilities.marshall(tr135s));
            	
            	mensajeConfACSLocal.setAccion(accionRealzada);
            	mensajeConfACSLocal.setSerial_modem(serialModem);
            	mensajeConfACSLocal.setPeti_numero(peticionkey.peti_numero);

                 Date dateHoy = new Date();
                 Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
                 mensajeConfACSLocal.setFecha_ingreso(timestampHoy);
                                                                   
                 String idMensajePadre = mensajeEstadoBALocal.getDesc_error();
                 log.debug("Recibo Mensaje con: "+ idMensajePadre);
                 //si el codigo de actividad es diferente de asistencia remota entonces cierra la actividad
                 //asistencia remota: una bandeja manual (si no se fue por control manual cierrelo)
                 if (!actDto.getCodigoActividad().equals(ComunInterfaces.ACT_ASISTENCIA_REMOTA)
                 		&& !actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
						&& !actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_DESINSTALACION)
						&& !actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)) {
                 	log.debug("Entro a finalizar actividad automática");
                 	mensajeEstadoBALocal.setFecha_cierre(df.format(new java.util.Date()));
                 	mensajeEstadoBALocal.setCod_actividad_generadora(mensajeEstadoBALocal.getCod_actividad_generadora());
                 	mensajeEstadoBALocal.setDesc_error(mensajeEstadoBALocal.getDesc_error());
                 	actividadEJB.terminar(actDto);
                 }else{
                 	if((actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
                 			||actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_DESINSTALACION)
							||actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA))
							&& (idMensajePadre.equals(""))){
                 		log.debug("Entro a finalizar actividad manual");
                 		Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
                 		BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
                 		BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionkey.peti_numero,idAplicacion);
                        
                 		String idCorrelativo=bintegradaLocal.getBi_url_detalle();
                 		int idInicio=idCorrelativo.indexOf("instanciaActividad");
                 		
                 		if(idInicio!=-1){
                 			idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
                 			int idFin=idCorrelativo.indexOf("&");
                 			if(idFin!=-1){
                 				idCorrelativo=idCorrelativo.substring(19,idFin);
                 			}
                 		}
                 		
                 		idCorrelativo=idCorrelativo.replaceAll("%2B","+");
                 		idCorrelativo=idCorrelativo.replaceAll("%2F","/");
                 		actDto.setActImplCorrelID(idCorrelativo);
                 		
                 		if((actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION)
    							||actDto.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA))){
                 			this.setearDatosActividad(actDto);
                 		}
                 		actividadEJB.terminar(actDto);
                 	}else{
                 		if(idMensajePadre != null && !idMensajePadre.equals("")){
                            String [] propiedadesTR135S = idMensajePadre.split("#");
                            if(propiedadesTR135S[1].startsWith("AT")){
                            	String [] propiedadesTOA = propiedadesTR135S[1].split("@");
                            	TOADelegate toaDelegate = new TOADelegate();
                            	Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
                                Agenda_scKey agendaSCKey = new Agenda_scKey(propiedadesTOA[0]);
                                Agenda_scLocal agendaSCLocal =  agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
                                Tmp_agenda_scLocalHome tmp_agenda_home = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
                                Tmp_agenda_scLocal tmp_agenda_scLocal = tmp_agenda_home.create(new Long(propiedadesTR135S[0]));
                                tmp_agenda_scLocal.setPeti_numero(peticionkey.peti_numero);
                                tmp_agenda_scLocal.setAgenda_sc(agendaSCLocal);
                                tmp_agenda_scLocal.setXml(XMLUtilities.marshall (tr135s));
                                toaDelegate.enviaActivarModemTOA(propiedadesTOA[0],propiedadesTR135S[0]+"-"+propiedadesTOA[1]);
                            }else{
                                recursosbaDelegate.enviaActivarModemsAgendaSC(propiedadesTR135S[1],propiedadesTR135S[0],null);
                            }
                 		}
                 	}
                 }
                } else //si los estados no estan en estado de espera o en estado de error entonces no genera ningun tipo de accion
                                                    log.debug("El estado de la peticiòn es diferente para ser procesado....");
            } catch (FinderException e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (NamingException e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (TnProcesoExcepcion e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (NumberFormatException e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (ATiempoAppEx e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (CreateException e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            } catch (Exception e) {
            	log.debug("ACSServicioBean.recibirAutoConfiguracion: " + e);
            }
     }


	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActacion) throws ATiempoAppEx {
		log.debug("Entro a recibirConfiguracionModemAutoinstalacion");
		BackendExecution bExecution = null;
		Tmp_modemLocalHome tmp_modemLocalHome = null;
		RecursosBADelegate recursosBADelegate = null;

		try {
			tmp_modemLocalHome = (Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
			recursosBADelegate = new RecursosBADelegate();
			
			bExecution = BackendTraceUtil.initExecution(tr135s, log);
			bExecution.setNumPetAtiempo(new Long(tr135s.getAtiempoRequestNumber()));
			bExecution.setIdMensajeOp(tr135s.getId());
			bExecution.startOperation();

			
			// busca el registro del mensaje
			log.debug("Primero si es mensaje de error lo obviamos.");

			Mensaje_estado_baLocal mensaje_estado_ba;
			try {
				mensaje_estado_ba = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr135s.getId())));
			} catch (FinderException e1) {
				mensaje_estado_ba = null;
			}

			if (mensaje_estado_ba == null) {
				log.debug("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr135s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr135s.getId(), ATiempoAppEx.EXCEPTION);
			}
			
			
			
			PeticionLocal peticionLocal = mensaje_estado_ba.getPeticion();
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			
			construirObjetos(peticionKey.peti_numero);

			String[] infoAdicional = mensaje_estado_ba.getDesc_error().split(":");
			String serialModem = infoAdicional[0];

			String accionRealizada = null;
			try {
				accionRealizada = infoAdicional[1];
			} catch (Exception ex) {
				accionRealizada = null;
			}

			String idMensajePadre;

			try {
				idMensajePadre = infoAdicional[2];
			} catch (Exception ex) {
				idMensajePadre = null;
			}

			ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);

			if (tr135s.getErrorCode() != null && !tr135s.getErrorCode().equals("0")) {
				log.debug("Respuesta tr-135-s con error: " + tr135s.getId());

				ModemKey modemKey = null;
				modemKey = new ModemKey(serialModem, peticionKey);
				ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);

				//Se quita este bloque porque estaba buscando el modem con el
				// campo que no correspondía.
				/*
				 * if (idMensajePadre != null){ String [] propiedadesTR135S =
				 * idMensajePadre.split("#");
				 * 
				 * if (propiedadesTR135S.length>1) modemKey = new
				 * ModemKey(propiedadesTR135S[1],peticionKey ); else modemKey =
				 * new ModemKey(serialModem,peticionKey ); }else{ modemKey = new
				 * ModemKey(serialModem,peticionKey ); }
				 */

				mensaje_estado_ba.setId_error(tr135s.getErrorCode());
				mensaje_estado_ba.setDesc_error(tr135s.getErrorDescription());

				Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				mensaje_estado_ba.setMensaje_estado(mensajeError);

				if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado) {
					if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())) {

						Collection modemsList = modemLocalHome.findPeticion(peticionKey.peti_numero);
						for (Iterator modemsIter = modemsList.iterator(); modemsIter.hasNext();) {
							ModemLocal modem = (ModemLocal) modemsIter.next();
							ModemKey key = (ModemKey) modem.getPrimaryKey();
							if (key.serial.equals(serialModem)) {
								modem.remove();
							}
						}
					}
					//Si al hacer la desconfiguración de modem ACS en el baja
					// ocurre un error, entonces se deja el modem actual en
					// estado 1,accionModemLiberar
					else if (accionRealizada.equals(new Integer(ComunInterfaces.bajaModemACS).toString())) {
						modemLocal.setAccion(new Short(new Integer(accionModemLiberar).toString()));
					} else {
						modemLocal.setAccion(new Short(new Integer(accionModemNoAction).toString()));
					}
				} else {
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
				}

			} else {
				log.debug("Respuesta tr-135-s sin error" + tr135s.getId());

				ModemKey modemKey = new ModemKey(serialModem, peticionKey);
				ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);

				if (accionRealizada == null
						|| accionRealizada.equals(new Integer(ComunInterfaces.operacionParActivar).toString())
						|| accionRealizada.equals("Nuevo")) {
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
				} else if (accionRealizada.equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())) {
					modemLocal.setAccion(new Short(new Integer(accionModemLiberar).toString()));
					if (!esBaja)
						modemLocal.remove();
				} else if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())) {
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));

					Collection modemInterno = modemLocalHome.findPeticion(peticionKey.peti_numero);
					for (Iterator modemIter = modemInterno.iterator(); modemIter.hasNext();) {
						ModemLocal modem = (ModemLocal) modemIter.next();
						ModemKey modemLocalKey = (ModemKey) modem.getPrimaryKey();

						if (!modemLocalKey.serial.equals(modemKey.serial)) {
							modem.remove();
						}
					}
				}

				Mensaje_estadoLocal mensajeOK = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				mensaje_estado_ba.setMensaje_estado(mensajeOK);
			}

			Long idTmpModem = new Long(dbSeq.seqNextValLong("CORRELATIVO_TMP_MODEM"));

			if (tmp_modemLocalHome == null)
				tmp_modemLocalHome = (Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);

			Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome) HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.create(new Integer(tr135s.getId()));
			mensajeConfACSLocal.setXml(XMLUtilities.marshall(tr135s));
			mensajeConfACSLocal.setAccion(accionRealizada);
			mensajeConfACSLocal.setSerial_modem(serialModem);
			mensajeConfACSLocal.setPeti_numero(peticionKey.peti_numero);

			Date dateHoy = new Date();
			Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
			mensajeConfACSLocal.setFecha_ingreso(timestampHoy);

			if (idMensajePadre != null && !esCierreActacion) {
				String[] propiedadesTR135S = idMensajePadre.split("#");
				recursosBADelegate.enviaActivarModemsAgendaSC(propiedadesTR135S[1],propiedadesTR135S[0], tr135s.getId());
			} else {
				log.debug("No es un mensaje de agenda o es un cierre de actuación por lo tanto no se envia mensaje de respuesta TR717E");
			}

			}catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
			}catch (CreateException e){
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
			}catch (NamingException e){
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
			}catch (FinderException e){
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
			}catch (RemoveException e){
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.REMOVEEXCEPTION) ;
			}catch(Exception e){
				bExecution.setErrorOp(true);
				throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
			}finally{  
				bExecution.endAll();
				// CR15338 - @Trace - ana santos - 04/08 - FIN 
			}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarCambiodProducto(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCambiodProducto(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#recibirCambioProducto(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirCambioProducto(TR135S tr135s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	/**
	 * @return Devuelve conectorLocalHome.
	 */
	public ConectorLocalHome getConectorLocalHome() {
		return conectorLocalHome;
	}
	/**
	 * @param conectorLocalHome El conectorLocalHome a establecer.
	 */
	public void setConectorLocalHome(ConectorLocalHome conectorLocalHome) {
		this.conectorLocalHome = conectorLocalHome;
	}
	/**
	 * @return Devuelve dbSeq.
	 */
	public DBManager getDbSeq() {
		return dbSeq;
	}
	/**
	 * @param dbSeq El dbSeq a establecer.
	 */
	public void setDbSeq(DBManager dbSeq) {
		this.dbSeq = dbSeq;
	}
	/**
	 * @return Devuelve df.
	 */
	public SimpleDateFormat getDf() {
		return df;
	}
	/**
	 * @param df El df a establecer.
	 */
	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}
	/**
	 * @return Devuelve fampsKey.
	 */
	public Familia_producto_servicioKey getFampsKey() {
		return fampsKey;
	}
	/**
	 * @param fampsKey El fampsKey a establecer.
	 */
	public void setFampsKey(Familia_producto_servicioKey fampsKey) {
		this.fampsKey = fampsKey;
	}
	/**
	 * @return Devuelve idCorrelativo.
	 */
	public Long getIdCorrelativo() {
		return idCorrelativo;
	}
	/**
	 * @param idCorrelativo El idCorrelativo a establecer.
	 */
	public void setIdCorrelativo(Long idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
	}
	/**
	 * @return Devuelve log.
	 */
	public Logger getLog() {
		return log;
	}
	/**
	 * @param log El log a establecer.
	 */
	public void setLog(Logger log) {
		this.log = log;
	}
	/**
	 * @return Devuelve mensaje_estadoLocalHome.
	 */
	public Mensaje_estadoLocalHome getMensaje_estadoLocalHome() {
		return mensaje_estadoLocalHome;
	}
	/**
	 * @param mensaje_estadoLocalHome El mensaje_estadoLocalHome a establecer.
	 */
	public void setMensaje_estadoLocalHome(
			Mensaje_estadoLocalHome mensaje_estadoLocalHome) {
		this.mensaje_estadoLocalHome = mensaje_estadoLocalHome;
	}
	/**
	 * @return Devuelve mensajeEspera.
	 */
	public Mensaje_estadoLocal getMensajeEspera() {
		return mensajeEspera;
	}
	/**
	 * @param mensajeEspera El mensajeEspera a establecer.
	 */
	public void setMensajeEspera(Mensaje_estadoLocal mensajeEspera) {
		this.mensajeEspera = mensajeEspera;
	}
	/**
	 * @return Devuelve mensajeEstadoBALocal.
	 */
	public Mensaje_estado_baLocal getMensajeEstadoBALocal() {
		return mensajeEstadoBALocal;
	}
	/**
	 * @param mensajeEstadoBALocal El mensajeEstadoBALocal a establecer.
	 */
	public void setMensajeEstadoBALocal(
			Mensaje_estado_baLocal mensajeEstadoBALocal) {
		this.mensajeEstadoBALocal = mensajeEstadoBALocal;
	}
	/**
	 * @return Devuelve mensajeEstadoBaLocalHome.
	 */
	public Mensaje_estado_baLocalHome getMensajeEstadoBaLocalHome() {
		return mensajeEstadoBaLocalHome;
	}
	/**
	 * @param mensajeEstadoBaLocalHome El mensajeEstadoBaLocalHome a establecer.
	 */
	public void setMensajeEstadoBaLocalHome(
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome) {
		this.mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome;
	}
	/**
	 * @return Devuelve pDelegate.
	 */
	public PeticionesDelegate getPDelegate() {
		return pDelegate;
	}
	/**
	 * @param delegate El pDelegate a establecer.
	 */
	public void setPDelegate(PeticionesDelegate delegate) {
		pDelegate = delegate;
	}
	/**
	 * @return Devuelve peticionkey.
	 */
	public PeticionKey getPeticionkey() {
		return peticionkey;
	}
	/**
	 * @param peticionkey El peticionkey a establecer.
	 */
	public void setPeticionkey(PeticionKey peticionkey) {
		this.peticionkey = peticionkey;
	}
	/**
	 * @return Devuelve peticionLocal.
	 */
	public PeticionLocal getPeticionLocal() {
		return peticionLocal;
	}
	/**
	 * @param peticionLocal El peticionLocal a establecer.
	 */
	public void setPeticionLocal(PeticionLocal peticionLocal) {
		this.peticionLocal = peticionLocal;
	}
	/**
	 * @return Devuelve peticionLocalHome.
	 */
	public PeticionLocalHome getPeticionLocalHome() {
		return peticionLocalHome;
	}
	/**
	 * @param peticionLocalHome El peticionLocalHome a establecer.
	 */
	public void setPeticionLocalHome(PeticionLocalHome peticionLocalHome) {
		this.peticionLocalHome = peticionLocalHome;
	}
	/**
	 * @return Devuelve producto_servicioLocalHome.
	 */
	public Producto_servicioLocalHome getProducto_servicioLocalHome() {
		return producto_servicioLocalHome;
	}
	/**
	 * @param producto_servicioLocalHome El producto_servicioLocalHome a establecer.
	 */
	public void setProducto_servicioLocalHome(
			Producto_servicioLocalHome producto_servicioLocalHome) {
		this.producto_servicioLocalHome = producto_servicioLocalHome;
	}
	/**
	 * @return Devuelve ps_Tipo_ModemLocalHome.
	 */
	public Ps_Tipo_ModemLocalHome getPs_Tipo_ModemLocalHome() {
		return ps_Tipo_ModemLocalHome;
	}
	/**
	 * @param ps_Tipo_ModemLocalHome El ps_Tipo_ModemLocalHome a establecer.
	 */
	public void setPs_Tipo_ModemLocalHome(
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome) {
		this.ps_Tipo_ModemLocalHome = ps_Tipo_ModemLocalHome;
	}
	/**
	 * @return Devuelve tieneBaEnVuelo.
	 */
	public boolean isTieneBaEnVuelo() {
		return tieneBaEnVuelo;
	}
	/**
	 * @param tieneBaEnVuelo El tieneBaEnVuelo a establecer.
	 */
	public void setTieneBaEnVuelo(boolean tieneBaEnVuelo) {
		this.tieneBaEnVuelo = tieneBaEnVuelo;
	}
	/**
	 * @return Devuelve tr135e.
	 */
	public TR135E getTr135e() {
		return tr135e;
	}
	/**
	 * @param tr135e El tr135e a establecer.
	 */
	public void setTr135e(TR135E tr135e) {
		this.tr135e = tr135e;
	}
	
	
	/**
	 * @return Devuelve cOCode.
	 */
	public Long getCOCode() {
		return COCode;
	}
	/**
	 * @param code El cOCode a establecer.
	 */
	public void setCOCode(Long code) {
		COCode = code;
	}
	/**
	 * @return Devuelve pSCode.
	 */
	public Long getPSCode() {
		return PSCode;
	}
	/**
	 * @param code El pSCode a establecer.
	 */
	public void setPSCode(Long code) {
		PSCode = code;
	}
	/**
	 * @return Devuelve velocidadPlan.
	 */
	public String getVelocidadPlan() {
		return velocidadPlan;
	}
	/**
	 * @param velocidadPlan El velocidadPlan a establecer.
	 */
	public void setVelocidadPlan(String velocidadPlan) {
		this.velocidadPlan = velocidadPlan;
	}
	
	
	
	public TR135E Alta (ModemVpiDTO modem) throws ATiempoAppEx {
		
		//Consultamos los productos servicios de la peticion
		Collection pSPCollecton = getPeticionLocal().getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));

		//Identifica que es una operacion comercial de ALTA
		getTr135e().setOperationAcs(ComunInterfaces.ALT_SERVICIO);
				
		//Asignamos el producto que se le envia  a la TR135E 
		getTr135e().setServiceValue(asignarProductoDeAltaEnTR135E(pSPCollecton));
		
		//con el fin de obtener el id del PS y la OC de una peticion de ALTAS para BA o BA Naked
		//if(!isTieneBaEnVuelo())
			//getTr135e().setCommercialOperationType(asignarAccionYProductoServicioAlta(pSPCollecton, opComAutoInstalacion));
		
		//se mapea la cedula del ciente en la operacion comercial solucion claver WIFI dcardena
		getTr135e().setCommercialOperationType(new Long (getPeticionLocal().getNum_doc_cli_cd()));

		//Control de cambio y asignacion de la velocidad
		getTr135e().setSpeed(new Integer(asignarVelocidadDelPlan(pSPCollecton, opComAutoInstalacion)));
		getTr135e().setProductServiceCode(getPSCode());
		
		try {
			Recursos_baLocalHome recursosBAHome= (Recursos_baLocalHome)HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
			Recursos_baLocal recursosBALocal = recursosBAHome.findbyPeti_numero(modem.getPeti_numero());
			if(recursosBALocal.getDir_ip_lan_nueva() != null)
				getTr135e().setIpService(recursosBALocal.getDir_ip_lan_nueva());
			if(recursosBALocal.getMasc_lan_nueva() != null)
				getTr135e().setMaskService(recursosBALocal.getMasc_lan_nueva());
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		}
		
		
		return getTr135e();
	}
	
	private String asignarProductoDeAltaEnTR135E (Collection pSPCollecton) {
		//Flags para determinar el producto que se envia a ACS por medio de BROKER
		boolean isBAConvencional = false;
		boolean isIPFija = false;
		boolean isBANaked = false;
		boolean isVozIP = false;
		
//		Asignacion de producto servicios
		for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
			//Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
			//Obtenemos la familia del producto servicio de la peticion
			setFampsKey(PSPLocal.getFamiliaKey());
			//Obtenemos la llave de la Operacion Comercial
			Operacion_comercialKey opcoKey = (Operacion_comercialKey)PSPLocal.getOperacion_comercial().getPrimaryKey();
			
			//ES BA? ES BANaked? Es Ip_Fija? Es Voip?
			if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA)
				isBAConvencional = true;
			if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)
				isBANaked = true;
			if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))
				isIPFija = true;
			if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
				isVozIP = true;
		}
		//Asignacion de productos para ACS segun los PS de la peticion para altas
		if (isBAConvencional) {
			if (isIPFija)
				return "IPFIJA";
			else 
				return "IPDINAMICA";
		} else if (isBANaked){
			if (isIPFija){
				if (isVozIP)
					return "VOIPFIJA";
				else 
					return "IPFIJA";
			} else {
				if (isVozIP)
					return "VOIPDINAMICA";
				else 
					return "IPDINAMICA";
			}
		} else {
			setTr135e(null);
			log.error("ACSAltaServicioBean.seleccionarAutoConfiguracionPorOC: No es una Alta");
			return null;
		}
	}

	private Long asignarAccionYProductoServicioAlta(Collection pSPCollecton, Long opComAutoInstalacion) {

		try {
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);

			for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal) PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(PSPLocal.getFamiliaKey());
				//Obtenemos la llave de la Operacion Comercial
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) PSPLocal.getOperacion_comercial().getPrimaryKey();
				
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(PSPLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ps: "+ PSPLocal.getPsId() +" no es un ps tipo mòdem");	
					esPsTipoModem = false;
				}

				//REQ BA NAKED adicion familia PC y PS naked
				//Si es Autoinstalcion, Tipo Modem o de la familia de altas para BA,
				//hace una reasignacion del COCode y PSCode
				if (opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAncha
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked) {

					setCOCode((getCOCode().intValue() == opComAutoInstalacion.intValue()) ? opcoKey.opco_id : new Long(ComunInterfaces.operacionParActivar));
					break;
				}
			}
			
			return getCOCode();

		} catch (NamingException namingException) {
			log.error("ACSAltaServicioBean.asignarAccionYProductoServicio: " + namingException);
		}
		return getCOCode();
	}
	
	private int asignarVelocidadDelPlan(Collection pSPCollecton, Long opComAutoInstalacion) {
		
		String velocidadPlanModem = "4096";//SUGERENCIA DE CESAR, Preguntar a Hector 2014.04.14
		
		for (Iterator listaDePsIt = pSPCollecton.iterator(); listaDePsIt.hasNext();) {
//			Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) listaDePsIt.next();
			//Obtenemos la familia del producto servicio de la peticion
			setFampsKey(prodServPetiLocal.getFamiliaKey());
			
			//consulta el modem por PsId, si no existe va al catch para
			// decir que el PS no es tipo modem
			boolean esPsTipoModem = false;
			try {
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = getPs_Tipo_ModemLocalHome().findByNroPs(prodServPetiLocal.getPsId());
				esPsTipoModem = true;
			} catch (FinderException e) {
				esPsTipoModem = false;
			}
			//REQ BA NAKED adicion familia PC naked
			if (esPsTipoModem){
				
			} else {
				if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked) {
					setPSCode(prodServPetiLocal.getPsId());
					velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
				}
			}
			
			if (esPsTipoModem
					|| (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)) {
				
				setPSCode(prodServPetiLocal.getPsId());
				velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
			}
		}
		
		//si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		if(velocidadPlanModem != null)
			return Integer.parseInt(velocidadPlanModem);
		else return 0;
	}
	
	
//se teo informacion para Bajas
	public TR135E Baja(ModemVpiDTO modem) throws ATiempoAppEx {
//		Consultamos los productos servicios de la peticion
		Collection pSPCollecton = getPeticionLocal().getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));

		//Identifica que es una opracion comercial de ALTA
		getTr135e().setOperationAcs(ComunInterfaces.BAJ_SERVICIO);
				
		//Asignamos el producto que se le envia  a la TR135E 
		getTr135e().setServiceValue(null);
		
		//con el fin de obtener el id del PS y la OC de una peticion de ALTAS para BA o BA Naked
		//if(!isTieneBaEnVuelo())
		//	getTr135e().setCommercialOperationType(asignarAccionYProductoServicioBaja(pSPCollecton, opComAutoInstalacion));
		//se mapea la cedula del ciente en la operacion comercial solucion claver WIFI dcardena
		getTr135e().setCommercialOperationType(new Long (getPeticionLocal().getNum_doc_cli_cd()));

		//Control de cambio y asignacion de la velocidad
		getTr135e().setSpeed(new Integer(asignarVelocidadDelPlanBaja(pSPCollecton, opComAutoInstalacion)));
		getTr135e().setProductServiceCode(getPSCode());
		
		try {
			Recursos_baLocalHome recursosBAHome= (Recursos_baLocalHome)HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
			Recursos_baLocal recursosBALocal = recursosBAHome.findbyPeti_numero(modem.getPeti_numero());
			if(recursosBALocal.getDir_ip_dslam_actual() != null)
				getTr135e().setIpService(recursosBALocal.getDir_ip_dslam_actual());
			if(recursosBALocal.getMasc_actual() != null)
				getTr135e().setMaskService(recursosBALocal.getMasc_actual());
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		}
		return getTr135e();
	}
	
	private Long asignarAccionYProductoServicioBaja(Collection pSPCollecton, Long opComAutoInstalacion) {

		try {
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);

			for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal) PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(PSPLocal.getFamiliaKey());
				//Obtenemos la llave de la Operacion Comercial
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) PSPLocal.getOperacion_comercial().getPrimaryKey();
				
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(PSPLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ps: "+ PSPLocal.getPsId() +" no es un ps tipo mòdem");	
					esPsTipoModem = false;
				}

				//REQ BA NAKED adicion familia PC y PS naked
				//Si es Autoinstalcion, Tipo Modem o de la familia de altas para BA,
				//hace una reasignacion del COCode y PSCode
				if (opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAncha
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked) {

					setCOCode((getCOCode().intValue() == opComAutoInstalacion.intValue()) ? opcoKey.opco_id : new Long(ComunInterfaces.operacionParActivar));
					break;
				}
			}
			
			return getCOCode();

		} catch (NamingException namingException) {
			log.error("ACSAltaServicioBean.asignarAccionYProductoServicio: " + namingException);
		}
		return getCOCode();
	}
	
	private int asignarVelocidadDelPlanBaja(Collection pSPCollecton, Long opComAutoInstalacion) {
		
		String velocidadPlanModem = null;
		
		for (Iterator listaDePsIt = pSPCollecton.iterator(); listaDePsIt.hasNext();) {
//			Obtenemos el PS de la peticion
			Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) listaDePsIt.next();
			//Obtenemos la familia del producto servicio de la peticion
			setFampsKey(prodServPetiLocal.getFamiliaKey());
			//Obtenemos la llave de la Operacion Comercial
			Operacion_comercialKey opcoKey = (Operacion_comercialKey) prodServPetiLocal.getOperacion_comercial().getPrimaryKey();
			
			//consulta el modem por PsId, si no existe va al catch para
			// decir que el PS no es tipo modem
			boolean esPsTipoModem = false;
			try {
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = getPs_Tipo_ModemLocalHome().findByNroPs(prodServPetiLocal.getPsId());
				esPsTipoModem = true;
			} catch (FinderException e) {
				esPsTipoModem = false;
			}
			//REQ BA NAKED adicion familia PC naked
			if (opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
					|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
					|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked) {
				
				// si es de tipo módem o no..")
				log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
				if (esPsTipoModem) {
					setPSCode(prodServPetiLocal.getPsId());
					velocidadPlanModem = prodServPetiLocal.getProducto_servicio().getVelocidad();
				} else {
					log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
					setPSCode(prodServPetiLocal.getPsId());
					return Integer.parseInt(prodServPetiLocal.getProducto_servicio().getVelocidad());
				}
			}
		}
		//si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		return Integer.parseInt(velocidadPlanModem);
	}
	
	
	
//----- fin bajas-----
	
//-----Postventas----------
	
	public TR135E Postventa(ModemVpiDTO modem) throws ATiempoAppEx {
		//Consultamos los productos servicios de la peticion
		Collection pSPCollecton = getPeticionLocal().getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
		
		//con el fin de obtener el id del PS y la OC de una peticion de ALTAS para BA o BA Naked
		//if(!isTieneBaEnVuelo())
		//	getTr135e().setCommercialOperationType(asignarAccionYProductoServicioPostventa(pSPCollecton, opComAutoInstalacion));

		//se mapea la cedula del ciente en la operacion comercial solucion claver WIFI dcardena
		getTr135e().setCommercialOperationType(new Long (getPeticionLocal().getNum_doc_cli_cd()));

		//Control de cambio y asignacion de la velocidad  
		//SE OBTIENE PRIMERO PARA VALIDAR LUEGO EL PRODUCTO EN POSVENTAS, direferente de ALTAS Y BAJAS
		getTr135e().setSpeed(new Integer(asignarVelocidadDelPlanPosventa(pSPCollecton, opComAutoInstalacion)));
		getTr135e().setProductServiceCode(getPSCode());

		//Asignamos una opracion comercial de correspondiente a VOIP
		//Asignamos el producto que se le envia  a la TR135E 
		asignarProductoDePosventaEnTR135E(pSPCollecton);
		
		try {
			Recursos_baLocalHome recursosBAHome= (Recursos_baLocalHome)HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
			Recursos_baLocal recursosBALocal = recursosBAHome.findbyPeti_numero(modem.getPeti_numero());
			if(recursosBALocal.getDir_ip_dslam_actual() != null)
				getTr135e().setIpService(recursosBALocal.getDir_ip_dslam_actual());
			if(recursosBALocal.getMasc_actual() != null)
				getTr135e().setMaskService(recursosBALocal.getMasc_actual());
		} catch (NamingException e) {
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (FinderException e) {
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (Exception e){
			log.debug("Error ACSServicioBean:Alta "+ e);
		}
		return getTr135e();
	}

	private void asignarProductoDePosventaEnTR135E (Collection pSPCollection) {
		
		try {
//			Flags para determinar el producto que se envia a ACS por medio de BROKER
			boolean altaVoIP = false;
			boolean bajaVoIP = false;
			boolean altaIPFija = false;
			boolean bajaIPFija = false;
			
			Operacion_comercialKey operacionComercial = null;
			
			//Asignacion de producto servicios
			for (Iterator PSPIterator = pSPCollection.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(PSPLocal.getFamiliaKey());
//				Obtenemos el tipo de operacion comercial (Alta por cambio de Prod, Baja por cambio de Prod)
				String opcoTipo= PSPLocal.getOperacion_comercial().getOpco_tipo();
				
				//Es para validar mas adelante que si es 8, o sea cambio de numero, no haga validaciones
				operacionComercial = (Operacion_comercialKey) PSPLocal.getOperacion_comercial().getPrimaryKey();
				
				//Si el tipo de operacion comercial es de ALTA CAMBIO PRODUCTO entonces
				if (opcoTipo.equals(ComunInterfaces.OPERACION_ALTA) || opcoTipo.equals(ComunInterfaces.operacionACP)){
					if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))
						altaIPFija = true;
					else if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
						altaVoIP = true;

				//Si el tipo de operacion comercial es de BAJA CAMBIO PRODUCTO entonces
				} else if (opcoTipo.equals(ComunInterfaces.OPERACION_BAJA) || opcoTipo.equals(ComunInterfaces.operacionBCP)) {
					if (PSPLocal.getProducto_servicio().getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))
						bajaIPFija = true;
					else if (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
						bajaVoIP = true;
					else {
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
						log.error("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
					}
				}
			}
			
			log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Alta IP Fija: " + altaIPFija);
			log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Alta VoiP: " + altaVoIP);
			log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Baja IP Fija: " + bajaIPFija);
			log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Baja VoiP: " + bajaVoIP);
			
			validarCambioVelocidadYModem(pSPCollection);
			log.debug("fin validarCambioVelocidadYModem");
			if(getTr135e().getOperationAcs() == null){
				getTr135e().setOperationAcs("");
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: Operation ACS viene Null se setea valor vacio ");
			}
			 
			//Asignacion de productos y servicios para ACS segun los ProductoServicios de la peticion para posventas
			if(!getTr135e().getOperationAcs().equals("CAMBIO_VELOCIDAD") && operacionComercial!=null && operacionComercial.opco_id.longValue() != ComunInterfaces.cambioNumero){
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: es diferente de CAMBIO_NUMERO y es diferente de CAMBIO_VELOCIDAD");
				if (altaIPFija) {
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: hay Alta ip fija");
					if (altaVoIP) { 	//ALTA IP FIJA + ALTA VOIP
						getTr135e().setServiceValue("VOIPFIJA");
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_ALTA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA + ALTA VOIP");
					} else if (bajaVoIP){ 	//ALTA IP FIJA + BAJA VOIP
						getTr135e().setServiceValue("IPFIJA");
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_DELTA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA + BAJA VOIP");
					} else { 				//ALTA IP FIJA
						getTr135e().setServiceValue("IPFIJA");
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_ALTA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA");
					}
				} else if (bajaIPFija) {
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: hay BAJA ip fija");
					if (altaVoIP) { 		//BAJA IP FIJA + ALTA VOIP
						//getTr135e().setOperationAcs("VOIP");
						getTr135e().setServiceValue("VOIPDINAMICA");//PETICION DE CESAR, DISCUTIR
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_DELTA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA + ALTA VOIP");
					} else if (bajaVoIP){ 	//BAJA IP FIJA + BAJA VOIP
						getTr135e().setServiceValue("VOIPFIJA");
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_BAJA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA + BAJA VOIP");
					} else { 				//BAJA IP FIJA
						getTr135e().setServiceValue("IPFIJA");
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_BAJA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA IP FIJA");
					}
				} else {
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: hay Alta VOIP");
					
					if (altaVoIP) { 		//ALTA VOIP
						//getTr135e().setOperationAcs("VOIP");
						getTr135e().setServiceValue("VOIPDINAMICA");//PETICION DE CESAR, DISCUTIR
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_ALTA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA VOIP");
					} else if (bajaVoIP){ 	//BAJA VOIP
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: hay BAJA VOIP");
						//getTr135e().setOperationAcs("VOIP");
						getTr135e().setServiceValue("VOIPDINAMICA");//PETICION DE CESAR, DISCUTIR
						if (!getTr135e().getOperationAcs().equals("CAMBIO_MODEM")) getTr135e().setOperationAcs("ACTUALIZACION_SERVICIO_BAJA");
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: BAJA VOIP");
					} else {
						log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
						log.error("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: casos de modems sin implementar");
					}
				}		
			} else {
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: hay CAMBIO NUMERO O SOLO CAMBIO VELOCIDAD");
				if (operacionComercial!=null && operacionComercial.opco_id.longValue() == ComunInterfaces.cambioNumero)
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: SERVICE VALUE FINAL: CAMBIO DE NUMERO");
				else if (getTr135e().getServiceValue().equals("CAMBIO_VELOCIDAD"))
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: SERVICE VALUE FINAL: CAMBIO DE VELOCIDAD");
				else
					log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: SERVICE VALUE FINAL: NO IDENTIFICADO");
			}
		} catch (Exception e) {
			log.debug("ACSServicioBean.asignarProductoDePosventaEnTR135E: " + e);
		}
		
		
	}
	
	/**
	 * @param naked
	 * @param pcBA
	 * @throws FinderException
	 */
	private void validarCambioVelocidadYModem(Collection pSPCollection) {
		
		Integer velocidadAlta = null;
		Integer velocidadBaja = null;
		boolean isBANaked 	  = false;
		boolean isBA 		  = false;
		boolean hayCambioDeFamilia = false;
		boolean esMantenimientoBA = false;
		
		try {
			//Asignacion de producto servicios
			boolean esPsTipoModem = false;
			for (Iterator PSPIterator = pSPCollection.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(PSPLocal.getFamiliaKey());
				//Obtenemos el tipo de operacion comercial (Alta por cambio de Prod, Baja por cambio de Prod)
				String opcoTipo= PSPLocal.getOperacion_comercial().getOpco_tipo();
				
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = getPs_Tipo_ModemLocalHome().findByNroPs(PSPLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					esPsTipoModem = false;
				}
				
				esMantenimientoBA = (PSPLocal.getPsId().longValue() == ComunInterfaces.psMNTBA) ? true: false;
				
				if (PSPLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
						|| PSPLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
							|| esPsTipoModem){
					//Mas adelante evaluaremos si los PSs tienen ambas familias para ver si hay cambio de modem
					//si alguno de los PS tiene familia BA Naked entonces hay BANaked
					isBANaked = (PSPLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked) ? true : false;
					//si alguno de los PS tiene familia BA entonces hay BA
					isBA = (PSPLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaPcBA) ? true: false;
					
					//Si hay un alta o alta por cambio de producto, capturamos la velocidad del alta
					if (opcoTipo.equals(ComunInterfaces.OPERACION_ALTA) || opcoTipo.equals(ComunInterfaces.operacionACP))
						velocidadAlta = (!PSPLocal.getProducto_servicio().getVelocidad().equals(null)) ? new Integer(PSPLocal.getProducto_servicio().getVelocidad()) : new Integer(0);
					//si hay baja o una baja por cambio de producto, capturamos la velocidad de la baja
					if (opcoTipo.equals(ComunInterfaces.OPERACION_BAJA) || opcoTipo.equals(ComunInterfaces.operacionBCP))
						velocidadBaja = (!PSPLocal.getProducto_servicio().getVelocidad().equals(null)) ? new Integer(PSPLocal.getProducto_servicio().getVelocidad()) : new Integer(0);	
				}
			}
			
			//Si hay velocidad de Altas
			if(esPsTipoModem || esMantenimientoBA){
				getTr135e().setOperationAcs("CAMBIO_MODEM");
				velocidadAlta = new Integer(0);
				log.debug("ACSServicioBean.validarCambioVelocidadYModem: ServiceValue: CAMBIO_MODEM");
			}
			if (velocidadAlta != null && velocidadAlta.intValue() != 0){
				//Si las velovidades son diferentes entonces hay cambio de velocidad
				log.debug("ACSServicioBean.validarCambioVelocidadYModem: Hay cambio de velocidad, se valida si se cambia el mòdem");
				if (velocidadBaja != null && velocidadAlta.intValue() != velocidadBaja.intValue()){
					getTr135e().setOperationAcs("CAMBIO_VELOCIDAD");
					log.debug("ACSServicioBean.validarCambioVelocidadYModem: ServiceValue: CAMBIO_VELOCIDAD");
					if (velocidadBaja.intValue() < 20000 && velocidadAlta.intValue() >= 20000){
						getTr135e().setOperationAcs("CAMBIO_MODEM");
						log.debug("ACSServicioBean.validarCambioVelocidadYModem: ServiceValue: CAMBIO_MODEM");
					}
				}
				//Si los PSs poseen ambas familias, independientemente de si son altas o bajas, significa que hay cambio de modem
				//o si hay un cambio de la velocidad de mas 
				if (isBA && isBANaked){
					log.debug("ACSServicioBean.validarCambioVelocidadYModem: ServiceValue: CAMBIO_MODEM");
					getTr135e().setOperationAcs("CAMBIO_MODEM");
				}
			} else {
				//getTr135e().setOperationAcs("0");
				log.debug("ACSServicioBean.validarCambioVelocidadYModem:Entro a posventas pero no hay altas");
				log.error("ACSServicioBean.validarCambioVelocidadYModem:Entro a posventas pero no hay altas");
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			log.error("ACSServicioBean.validarCambioVelocidadYModem:error de metodo"+e);

		}
		
	}
	
	/**
	 * @param naked
	 * @param pcBA
	 * @throws FinderException
	 */
	private boolean validarCambioModem(String naked, String pcBA, boolean hayCambioVelocidad) throws FinderException {
		// TODO Apéndice de método generado automáticamente
		Producto_servicioLocal psNaked = this.getProducto_servicioLocalHome().findByPS_Nombre(naked);
		Producto_servicioLocal psBA = this.getProducto_servicioLocalHome().findByPS_Nombre(pcBA);
		if(psNaked.getFamilia_producto_servicio() != null && psBA.getFamilia_producto_servicio() != null ){
			//Si los dos PS tienen diferentes familias o hay un cambio de velocidad por mas de 20 megas
			if(psNaked.getFamilia_producto_servicio().getPrimaryKey().equals(psBA.getFamilia_producto_servicio().getPrimaryKey())
					|| hayCambioVelocidad)
				return true;
		}
		return false;
	}

	private Long asignarAccionYProductoServicioPostventa(Collection pSPCollecton, Long opComAutoInstalacion) {

		try {
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);

			for (Iterator PSPIterator = pSPCollecton.iterator(); PSPIterator.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal) PSPIterator.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(PSPLocal.getFamiliaKey());
				//Obtenemos la llave de la Operacion Comercial
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) PSPLocal.getOperacion_comercial().getPrimaryKey();
				
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(PSPLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ps: "+ PSPLocal.getPsId() +" no es un ps tipo mòdem");	
					esPsTipoModem = false;
				}

				//REQ BA NAKED adicion familia PC y PS naked
				//Si es Autoinstalcion, Tipo Modem o de la familia de altas para BA,
				//hace una reasignacion del COCode y PSCode
				if (opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAncha
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked) {

					setCOCode((getCOCode().intValue() == opComAutoInstalacion.intValue()) ? opcoKey.opco_id : new Long(ComunInterfaces.operacionParActivar));
					break;
				}
			}
			
			return getCOCode();

		} catch (NamingException namingException) {
			log.error("ACSAltaServicioBean.asignarAccionYProductoServicio: " + namingException);
		} catch (Exception e) {
			log.error("ACSAltaServicioBean.asignarAccionYProductoServicio: " + e);
		}
		return getCOCode();
	}
	
	private int asignarVelocidadDelPlanPosventa(Collection pSPCollecton, Long opComAutoInstalacion) {
		
		String velocidadPlanModem = "0";
		Long PSsuplementario = null;
		
		try {
			for (Iterator listaDePsIt = pSPCollecton.iterator(); listaDePsIt.hasNext();) {
				//Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) listaDePsIt.next();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(prodServPetiLocal.getFamiliaKey());
				//Obtenemos la llave de la Operacion Comercial
				Operacion_comercialKey opcoKey = (Operacion_comercialKey) prodServPetiLocal.getOperacion_comercial().getPrimaryKey();
				Operacion_comercialLocal opcoLocal= (Operacion_comercialLocal ) prodServPetiLocal.getOperacion_comercial();
				//consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = getPs_Tipo_ModemLocalHome().findByNroPs(prodServPetiLocal.getPsId());
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: No es tipo modem");
					esPsTipoModem = false;
				}
				
				if( (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
						|| prodServPetiLocal.getProducto_servicio().getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))){
					log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: Es un VOIP o IPFIJA, no se calcula Velocidad");
					PSsuplementario = prodServPetiLocal.getPsId();
				}
				
				//REQ BA NAKED adicion familia PC naked
				if ((opcoKey.opco_id.longValue() == opComAutoInstalacion.longValue() || esPsTipoModem
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)
						&& (opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)
								|| opcoLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta))) {
					
					// si es de tipo módem o no..")
					//Las siguientes validaciones SON PARECIDAS pero la diferencia es que en la parte verdadera
					//del if permite seguir iterando hasta encontrar un ps padre, si no encuentra igual queda con la velocidad de un hijo
					//mientras que en la parte falsa, si encuentra el ps padre, retorna el valor de una vez.
					log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
					if (esPsTipoModem) {
						setPSCode(prodServPetiLocal.getPsId());
						velocidadPlanModem = (!prodServPetiLocal.getProducto_servicio().getVelocidad().equals(null)) ? prodServPetiLocal.getProducto_servicio().getVelocidad(): "0";
					} else {
						log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
						setPSCode(prodServPetiLocal.getPsId());
						return (!prodServPetiLocal.getProducto_servicio().getVelocidad().equals(null)) ? Integer.parseInt(prodServPetiLocal.getProducto_servicio().getVelocidad()) : 0;
					}
				}
			}
			
			if(PSsuplementario != null){
				log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: Es un servicio suplementario");
				setPSCode(PSsuplementario);
			}
		} catch (Exception e) {
			log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			log.error("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			return 0;
		}
		//si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		return Integer.parseInt(velocidadPlanModem);
	}
	
	/**
	 * @param ordenFlujo
	 * @param act2
	 */
	public void setearDatosActividad(ActividadEJBDTO act) {
		// TODO Apéndice de método generado automáticamente
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
		try {
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,"X");
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"S");
			Estructura_InterfazLocalHome estructura_InterfazLocalHome= (Estructura_InterfazLocalHome)HomeFactory.getHome(Estructura_InterfazLocalHome.JNDI_NAME);
			Collection estructuraCollection = estructura_InterfazLocalHome.findByAci_id("WF", new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			log.debug("Se recorre encontraron un npùmero de campos: "+estructuraCollection.size());
			for (Iterator iter = estructuraCollection.iterator(); iter.hasNext();) {
				Estructura_InterfazLocal element = (Estructura_InterfazLocal) iter.next();
				Estructura_InterfazKey estructura_InterfazKey = (Estructura_InterfazKey) element.getPrimaryKey();
				act.setDato(estructura_InterfazKey.aci_tag,element.getAci_default_value());
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al instanciar el BEAN");
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al formatear proceso en el Bean");
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en la busqueda del la estructura");
		}
	}
}
