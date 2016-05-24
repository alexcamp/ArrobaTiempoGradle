package co.com.telefonica.atiempo.soltec.actividades.df.configuracion.ejb.naked.sb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBABean;
import co.com.telefonica.atiempo.soltec.servicios.EnviarAutoConfiguracionModemACSMQ;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;



/**
 * Bean implementation class for Enterprise Bean: ACSServicioST
 */
public class ACSServicioSTBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements javax.ejb.SessionBean, co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz {

//	Variables de Utils
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private DBManager dbSeq;
	private SimpleDateFormat df;
	
	//Variables del mensaje
	private Long PSCode;
	private Long COCode;
	private boolean tieneVoIP;
	private boolean tieneIPFIJA;

	//Logica de los recursos
	private Long idCorrelativo;
	private Peticion_stLocal peticionSTLocal;
	private Peticion_stKey peticionSTKey;
	private co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate pDelegate;
	private Familia_producto_servicio_stKey fampsKey;
	private Mensaje_estado_stLocalHome mensaje_estado_stHome;
	private Mensaje_estado_stLocal mensaje_estado_stLocal;
	private Mensaje_estadoLocalHome mensaje_estado_LocalHome;
	
	//TR que almacena los datos
	private TR135E tr135e;

	
	/**
	 *
	 */
	public ACSServicioSTBean() {
	}
	
	public ACSServicioSTBean (ACSServicioSTBean acsServicioSTBean){
		this.log = acsServicioSTBean.log;
		this.dbSeq = acsServicioSTBean.dbSeq;
		this.df = acsServicioSTBean.df;
		//this.acsServicioPorOCBean = acsServicioBean.acsServicioPorOCBean;
		this.tr135e = acsServicioSTBean.tr135e;
		this.idCorrelativo = acsServicioSTBean.idCorrelativo;
		this.peticionSTLocal = acsServicioSTBean.peticionSTLocal;
		this.mensaje_estado_stHome = acsServicioSTBean.mensaje_estado_stHome;
		this.mensaje_estado_stLocal = acsServicioSTBean.mensaje_estado_stLocal;
		this.mensaje_estado_LocalHome = acsServicioSTBean.mensaje_estado_LocalHome;
		this.peticionSTKey = acsServicioSTBean.peticionSTKey;
	}

	/**
	 * @param peti_numero
	 */
	private void construirObjetos(Long peti_numero) {
		// TODO Apéndice de método generado automáticamente
		
		try {
	//		Construccion de Objetos especificos: Sacar esta porcion de codigo si se vuelve a abstraer en patron de diseño fabrica
			tr135e = new TR135E();
			pDelegate = new co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate();
			mensaje_estado_stHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			mensaje_estado_LocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			Peticion_stLocalHome peticion_stLocalHome=(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			peticionSTKey = new Peticion_stKey(peti_numero);
			peticionSTLocal = peticion_stLocalHome.findByPrimaryKey(peticionSTKey);
			tieneVoIP=false;
			tieneIPFIJA=false;
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioSTBean.construirObjetos: " + e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioSTBean.construirObjetos: " + e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioSTBean.construirObjetos: " + e);
		}
		
	}
	public void setSessionContext (SessionContext ctx) throws EJBException, RemoteException {
		
			super.setSessionContext (ctx) ;
			// Creacion de DataSource
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			df = new SimpleDateFormat ("yyyy/MM/dd") ;	
		}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.atiempo.dto.ModemVpiDTO, co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, boolean)
	 */
	public void enviarAutoConfiguracion(ModemSTDTO modem, ActividadEJBDTO act, Long petiNumero, boolean esAutoinstalacion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		enviarAutoConfiguracion(modem, act.getCodigoActividad(), "");
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.atiempo.dto.ModemVpiDTO, java.lang.String, java.lang.String)
	 */
	public void enviarAutoConfiguracion(ModemSTDTO modem, String act, String idMensajePadre) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
//			contruir las variables que se van a usar
			construirObjetos(modem.getNum_peticion());
			log.debug("construirObjetos");
			//Construcciòn inicial de la TR135E
			inicializarAutoConfiguracion(modem);
			log.debug("inicializarAutoConfiguracion");
			tr135e =construiTR(modem);
			log.debug("construiTR");
//			Finaliza con la configuracion de las acciones de los modems (SIN COPY PASTE AUN)
			finalizarAutoConfiguracion(modem, act, idMensajePadre);
			log.debug("finalizarAutoConfiguracion");
//			Envia la TR135E
			EnviarAutoConfiguracionModemACSMQ enviarMensaje = new EnviarAutoConfiguracionModemACSMQ();
			enviarMensaje.enviarMensaje(tr135e);
		}catch (Exception e) {
			log.debug("ACSServicioBean.enviarAutoConfiguracion: " + e);
		}
	}

	/**
	 * @param modem
	 * @param act
	 * @param idMensajePadre
	 */
	private void finalizarAutoConfiguracion(ModemSTDTO modem, String act, String idMensajePadre) {
		try {
			// TODO Apéndice de método generado automáticamente
			Mensaje_estado_stLocalHome mensaje_estado_stHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal mensaje_estado_stLocal = mensaje_estado_stHome.create(idCorrelativo);
			mensaje_estado_stLocal.setPeti_numero(new Long(getTr135e().getAtiempoRequestNumber()));
			mensaje_estado_stLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
			mensaje_estado_stLocal.setObservaciones(idMensajePadre);
			mensaje_estado_stLocal.setCod_actividad_generadora(act);
			log.debug("finalizarAutoConfiguracioninicializa");
			log.debug(XMLUtilities.marshall(tr135e));
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ACSServicioBean:Alta "+ e);
		}
	}

	/**
	 * @param modem
	 * @return
	 */
	private TR135E construiTR(ModemSTDTO modem) {
		// TODO Apéndice de método generado automáticamente
//		Consultamos los productos servicios de la peticion
		
		Collection pspArray = peticionSTLocal.getProducto_servicio_peticion();
		Long opComAutoInstalacion = new Long(getPDelegate().recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
		getTr135e().setCommercialOperationType( new Long("69"));
		log.debug("construiTR inicializa");
//		Control de cambio y asignacion de la velocidad  
		//SE OBTIENE PRIMERO PARA VALIDAR LUEGO EL PRODUCTO EN POSVENTAS, direferente de ALTAS Y BAJAS
		getTr135e().setSpeed(new Integer(asignarVelocidad(pspArray, opComAutoInstalacion)));
		//se mapea velocidad 0 ya que en la tabla producto servicio de sotlec no hay velocidad alguna ligada al ps homologado 2 de pro

		getTr135e().setProductServiceCode(getPSCode());
		
		log.debug("VElocidad");
		asignarProductoEnTR135E(pspArray);
		
		try {
			Recursos_baLocalHome recursosBAHome= (Recursos_baLocalHome)HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
			Recursos_baLocal recursosBALocal = recursosBAHome.findbyPeti_numero(modem.getNum_peticion());
			if(recursosBALocal.getDir_ip_dslam_actual() != null)
				getTr135e().setIpService(recursosBALocal.getDir_ip_dslam_actual());
			if(recursosBALocal.getMasc_lan_actual() != null)
				getTr135e().setMaskService(recursosBALocal.getMasc_lan_actual());
		} catch (NamingException e) {
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (FinderException e) {
			log.debug("Error ACSServicioBean:Alta "+ e);
		} catch (Exception e){
			log.debug("Error ACSServicioBean:Alta "+ e);
		}
		return getTr135e();
	}

	/**
	 * @param pspArray
	 */
	private void asignarProductoEnTR135E(Collection pspArray) {
		// TODO Apéndice de método generado automáticamente
//		Asignacion de producto servicios
		getTr135e().setOperationAcs("CAMBIO_MODEM");
		
		if (tieneVoIP) {
			if (tieneIPFIJA) { 		//ALTA IP FIJA + ALTA VOIP
				getTr135e().setServiceValue("VOIPFIJA");
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA IP FIJA + ALTA VOIP");
			}else{
				getTr135e().setServiceValue("VOIPDINAMICA");//PETICION DE CESAR, DISCUTIR
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA VOIP");
			}
		}else{
			if (tieneIPFIJA) { 		//ALTA IP FIJA + ALTA VOIP
				getTr135e().setServiceValue("IPFIJA");
				log.debug("ACSPosVentaServicioBean.asignarProductoDePosventaEnTR135E: ALTA VOIP");
			}
		}

	}

	/**
	 * @param pspArray
	 * @param opComAutoInstalacion
	 * @return
	 */
	private int asignarVelocidad(Collection pspArray, Long opComAutoInstalacion) {
		// TODO Apéndice de método generado automáticamente
		
		String velocidadPlanModem = "0";
		Long PSsuplementario = null;
		try {
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			for(Iterator iter = pspArray.iterator();iter.hasNext();){
//			Obtenemos el PS de la peticion
				Producto_servicio_peticionLocal prodServPetiLocal = (Producto_servicio_peticionLocal) iter.next();
				Producto_servicio_stKey psKey = new Producto_servicio_stKey(prodServPetiLocal.getIdProductoServicio());
				Producto_servicio_stLocalHome psHome = (Producto_servicio_stLocalHome) HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
				Producto_servicio_stLocal psLocal = psHome.findByPrimaryKey(psKey);
				Familia_producto_servicio_stLocal familiaLocal = psLocal.getFamilia_producto_servicio_st();
				Familia_producto_servicio_stKey familiakey = (Familia_producto_servicio_stKey) familiaLocal.getPrimaryKey();
				//Obtenemos la familia del producto servicio de la peticion
				setFampsKey(familiakey);
				
//			consulta el modem por PsId, si no existe va al catch para
				// decir que el PS no es tipo modem
				boolean esPsTipoModem = false;
				try {
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(psKey.ps_id );
					esPsTipoModem = true;
				} catch (FinderException e) {
					log.debug("ps: "+ psKey.ps_id +" no es un ps tipo mòdem");	
					esPsTipoModem = false;
				}
				
				if( (getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
						|| psLocal.getPs_nombre().toUpperCase().matches(".*IP FIJA.*"))){
					log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: Es un VOIP o IPFIJA, no se calcula Velocidad");
					PSsuplementario = psKey.ps_id;
					if(getFampsKey().faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
						tieneVoIP = true;
				}
				
//			REQ BA NAKED adicion familia PC naked
//				if ((esPsTipoModem
//						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcBA
//						|| getFampsKey().faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked)) {
					
					// si es de tipo módem o no..")
					//Las siguientes validaciones SON PARECIDAS pero la diferencia es que en la parte verdadera
					//del if permite seguir iterando hasta encontrar un ps padre, si no encuentra igual queda con la velocidad de un hijo
					//mientras que en la parte falsa, si encuentra el ps padre, retorna el valor de una vez.
					log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
					if (esPsTipoModem) {
						setPSCode(psKey.ps_id);
						//velocidadPlanModem = (!psLocal.getVelocidad().equals(null)) ? psLocal.getVelocidad(): "0";
						velocidadPlanModem="0";
					} else {
						log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
						setPSCode(psKey.ps_id);
						//return (!psLocal.getVelocidad().equals(null)) ? Integer.parseInt(psLocal.getVelocidad()) : 0;
						return 0;
					}
				}
				
				if(PSsuplementario != null){
					log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: Es un servicio suplementario");
					setPSCode(PSsuplementario);
				}
//			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			log.error("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			return 0;
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			log.error("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			return 0;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			log.error("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			return 0;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			log.error("ACSServicioBean.asignarVelocidadDelPlanPosventa: " + e);
			return 0;
		}
//		si no encuentra velocidad del plan, significa que no hay cambio de velocidad
		//y retorna la velocidad del modem actual
		return Integer.parseInt(velocidadPlanModem);
	}

	/**
	 * @param modem
	 */
	private void inicializarAutoConfiguracion(ModemSTDTO modem) {
		// TODO Apéndice de método generado automáticament
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
		idCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		log.debug("Correlativo "+idCorrelativo);
		try {
//			Creación de la cabecera
			tr135e.setId(idCorrelativo.toString());
			tr135e.setDestination("ESB");
			tr135e.setSource("ATIEMPO_ST");
			tr135e.setInterfaz("CONFIG_MODEMACS"); 
			tr135e.setVersion("1.0");
			
//			INFORMACION DE ATIEMPO - PETINUMERO
			tr135e.setAtiempoRequestNumber(modem.getNum_peticion().toString());
			
//			INFORMACION DEL MODEM
			tr135e.setBrandModem(modem.getMarca());
			tr135e.setModelModem(modem.getModelo());
			tr135e.setSerialNumber(modem.getSerial());//PUNTO 1
			
//			CAMPO EMAIL
			String fatherEmail = null;
			Collection recursosBaList = peticionSTLocal.getRecursos_ba();
			for (Iterator recursosBAIter = recursosBaList.iterator();recursosBAIter.hasNext();){
				Recursos_baLocal recursosBALocal = (Recursos_baLocal) recursosBAIter.next();
				fatherEmail = recursosBALocal.getFather_email_actual();
				if(recursosBALocal.getDir_ip_lan_actual() != null)
					tieneIPFIJA = true;
			}
			
			if (fatherEmail != null && fatherEmail.length()>0){
				tr135e.setFatherEmail(fatherEmail);
			}else{					
				tr135e.setFatherEmail(" ");
			}
						
			//CAMPO TELEFONO Y NUEVO TELEFONO
			Collection recursosLineaBasicaCollection = peticionSTLocal.getRecursos_linea_basica();
			if (recursosLineaBasicaCollection.size()!=0){
				for (Iterator recursosLineaBasicaIterator = recursosLineaBasicaCollection.iterator(); recursosLineaBasicaIterator.hasNext();){
					Recursos_linea_basicaLocal recursosLineaBasicaLocal = (Recursos_linea_basicaLocal)recursosLineaBasicaIterator.next();

//					if(recursosLineaBasicaLocal.getPassword_fttc() != null)
//						tr135e.setPasswordIMS(recursosLineaBasicaLocal.getPassword_fttc());
										
					if (recursosLineaBasicaLocal.getTelefono_asignado()!=null){
						tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asignado().toString()));
					}else{
						//NO VIENEN NINGUN NUMERO
						tr135e.setPhoneNumber(new Integer("0"));
						tr135e.setNewPhoneNumber(new Integer("0"));
					}
				}
			}else{
				if (peticionSTLocal.getNum_ide_nu() !=null){
					tr135e.setPhoneNumber(new Integer(peticionSTLocal.getNum_ide_nu()));
					tr135e.setNewPhoneNumber(new Integer("0"));
				}else{
					tr135e.setPhoneNumber(new Integer("0"));
					tr135e.setNewPhoneNumber(new Integer("0"));
				}
			}
			
			//Se asigna por solicitud de Broker
			tr135e.setServiceType("SERVICIO");
			
			co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate pDelegate = new co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate();
			String OUI = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OUI+tr135e.getModelModem());
			tr135e.setOUI(OUI);
			
			log.debug("Finaliza");
		}catch (Exception e) {
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		}	
	}


	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#recibirAutoConfiguracion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirAutoConfiguracion(TR135S tr135s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Entro al mètodo de respuesta de autoconfiguraciòn para la correlación; " + tr135s.getId());
		
		try {
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			RecursosBABean recursosbaDelegate = new RecursosBABean();
			construirObjetos(new Long(tr135s.getAtiempoRequestNumber()));
			
			Mensaje_estadoLocal mensajeok = mensaje_estado_LocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
			Mensaje_estadoLocal mensajeError = mensaje_estado_LocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoCierreError)));
			
			mensaje_estado_stLocal = mensaje_estado_stHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr135s.getId())));
			Mensaje_estadoLocal estadoPeticion = mensaje_estado_LocalHome.findByPrimaryKey(new Mensaje_estadoKey (mensaje_estado_stLocal.getCod_estado()));
			Mensaje_estadoKey estadoPeticionKey = (Mensaje_estadoKey) estadoPeticion.getPrimaryKey();
			
			peticionSTLocal = mensaje_estado_stLocal.getPeticion_st();
			peticionSTKey = (Peticion_stKey) peticionSTLocal.getPrimaryKey();
			
			log.debug("Se inicializan variables para la petición "+peticionSTKey.cod_ave_cd);
			
			String nombreClase = TR135S.class.getName();
			nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
			
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensaje_estado_stLocal.getCod_actividad_generadora());
			//BA NAKED FASE II DCARDENA
			//se agega validacion toa  para saber si se recupera una activada plantaexterna de agenda o la propia de planta externa de toa
			ServicioTOASTDelegate servicioTOASTDelegate=new ServicioTOASTDelegate();
			boolean esLocTOA=servicioTOASTDelegate.esLocalidadToa(peticionSTKey.cod_ave_cd);
			ActividadEJBDTO actDto=null;
			if(esLocTOA){
				log.debug("Se recupera actividad de bandeja integrada para TOA "+peticionSTKey.cod_ave_cd);
				actDto =servicioTOASTDelegate.recuperaActividadDeBandejaIntegrada(peticionSTLocal);
			}else{
//				log.debug("Se recupera actividad de bandeja integrada para TOA "+peticionSTKey.cod_ave_cd);
//				AgendaServicioSTDelegate  agendaServicioSTDelegate = new AgendaServicioSTDelegate();
//				actDto = agendaServicioSTDelegate.recuperaActividadDeBandejaIntegrada(peticionSTLocal,peticionSTKey.cod_ave_cd);	
			}
			// fin BA NAKED
			String accionRealzada = "69";//solo para cambio de modems en ST
			String serialModem = "";
			String observacion = "";
			if (estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoEspera
					|| estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoError) {
//			codigo de error de la TR135S, se valida que haya codigo de error
				if (tr135s.getErrorCode() != null && !tr135s.getErrorCode().equals("") && !tr135s.getErrorCode().equals("0")) {
//				Venia de actividad configurar ACS
//				busca los modems de la peticion y le coloca a cada uno la accion de modem no configurado
					Collection modem = peticionSTLocal.getModem();
					for (Iterator iter = modem.iterator(); iter.hasNext();) {
						ModemLocal modemLocal =  (ModemLocal) iter.next();
						modemLocal.setAccion(new Short (String.valueOf(ComunInterfaces.accionModemNoConfigurado)));
						mensaje_estado_stLocal.setCod_estado(new Integer(ComunInterfaces.estadoCierreError));
						ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
						//accionRealzada = "error";
						serialModem = modemKey.serial;
					}
					observacion=actDto.getObservacion();
					actDto.setObservacion(observacion+",No se pudo realizar la configuraciòn en ACS del Mòdem");
				} else{
					//si no fuera un error de la tr135S asigna el estado como ok
					mensaje_estado_stLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));
					Collection modem = peticionSTLocal.getModem();
					//Ba NAKED FASE II
					//se agrega arreglo de modems a eliminar
					ArrayList elimModem= new ArrayList();
					for (Iterator iter = modem.iterator(); iter.hasNext();) {
						ModemLocal modemLocal =  (ModemLocal) iter.next();
						log.debug("se valida modem con accion: "+modemLocal.getAccion().intValue() );
						if(modemLocal.getAccion().intValue() != ComunInterfaces.accionModemLiberar){
							modemLocal.setAccion(new Short (String.valueOf(ComunInterfaces.accionModemConfigurado)));
							ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
							//accionRealzada = "Conf";
							serialModem = modemKey.serial;
							//BA naked fase II dcardenas
							//se agrega eliminar para los deltas de modem.
						}else{
							ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
							String serialModem1 = modemKey.serial;
            				elimModem.add(serialModem1);
            			}
						

					}
					//Ba NAKED FASE II 
					//se recorren los seriales a eliminar
					if (elimModem.size()>0 ){
					for (Iterator x= elimModem.iterator(); x.hasNext();){
						String serialElim =(String) x.next();
						log.debug("se elimina modem "+ serialElim +peticionSTKey.cod_ave_cd);
						ModemLocalHome modemLocalHome = (ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
						ModemKey modemKey = new ModemKey(serialElim,peticionSTKey );
						ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);
						modemLocal.remove();
					}
					}else{
						log.debug("no hay modems para eliminar "+ peticionSTKey.cod_ave_cd);
					}
				}
				
				Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome) HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
				Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.create(new Integer(tr135s.getId()));
				mensajeConfACSLocal.setXml(XMLUtilities.marshall(tr135s));
				
				mensajeConfACSLocal.setAccion(accionRealzada);
				mensajeConfACSLocal.setSerial_modem(serialModem);
				mensajeConfACSLocal.setPeti_numero(peticionSTKey.cod_ave_cd);
		        Date dateHoy = new Date();
                Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
                mensajeConfACSLocal.setFecha_ingreso(timestampHoy);
                
				String idMensajePadre = mensaje_estado_stLocal.getObservaciones();
				log.debug("Recibo Mensaje con: "+ idMensajePadre);
				//si el codigo de actividad es diferente de asistencia remota entonces cierra la actividad
				//asistencia remota: una bandeja manual (si no se fue por control manual cierrelo)
				if(idMensajePadre == null || idMensajePadre.equals("")){
					log.debug("Entro a finalizar actividad manual");
					Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
					BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
					BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionSTKey.cod_ave_cd,idAplicacion);
					
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
					
					String familiaPs=peticionSTLocal.getIde_pro_cmr_cd();
					//se agrega este codigo para que se salga del flujo de diagnistoco ba y cierre la actividad de PLanta externa
					if(familiaPs.equals(ComunInterfaces.TV)){
						log.debug("Se deriva a solucion tv");
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,"S");
					}else if(familiaPs.equals(ComunInterfaces.BA)){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"S");
						log.debug("Se deriva a solucion ba");
					}else if(familiaPs.equals(ComunInterfaces.LBST)){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,"S");
						log.debug("Se deriva a solucion lb");
					}
					observacion=actDto.getObservacion();
					actDto.setObservacion(observacion+", Se ha configurado el Modem en ACS");
					actDto.setGrabaEnBitacora(true);
					actividadEJB.terminar(actDto);
				}else{
					if(idMensajePadre != null && !idMensajePadre.equals("")){
						String [] propiedadesTR135S = idMensajePadre.split("#");
						if(propiedadesTR135S[1].startsWith("IT")){
							log.debug("modem toa");
							String [] propiedadesTOA = propiedadesTR135S[1].split("@");
							ServicioTOASTDelegate toaDelegate = new ServicioTOASTDelegate();
							AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
							Agenda_scKey agendaSCKey = new Agenda_scKey(propiedadesTOA[0]);
							AgendaSCSTLocal agendaSCSTLocal =  agendaSCSTLocalHome.findByPrimaryKey(agendaSCKey.id_actuacion);
							Tmp_agenda_scLocalHome tmp_agenda_home = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
							Tmp_agenda_scLocal tmp_agenda_scLocal = tmp_agenda_home.create(new Long(propiedadesTR135S[0]));
							tmp_agenda_scLocal.setPeti_numero(peticionSTKey.cod_ave_cd);
							//tmp_agenda_scLocal.setAgenda_sc(agendaSCSTLocal);
							tmp_agenda_scLocal.setXml(XMLUtilities.marshall (tr135s));
							toaDelegate.enviaActivarModemTOA(propiedadesTOA[0],propiedadesTR135S[0]+"-"+propiedadesTOA[1], tr135s);
						}else{
							//BA NAKED FASE II se envia el TR135S ID para solucionar nullpointer
							log.debug("modem agenda");
							recursosbaDelegate.enviaActivarModemsAgendaSC(propiedadesTR135S[1],propiedadesTR135S[0],tr135s.getId());
						}
						
					}
				}
			}else{//si los estados no estan en estado de espera o en estado de error entonces no genera ningun tipo de accion
				log.debug("El estado de la peticiòn es diferente para ser procesado....");
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		} catch (Exception e) {
		// TODO Bloque catch generado automáticamente
		log.debug("ACSServicioBean.inicializarAutoConfiguracion: " + e);
		}
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#recibirConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S, boolean, boolean)
	 */
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#enviarCambiodProducto(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCambiodProducto(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioInterfaz#recibirCambioProducto(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirCambioProducto(TR135S tr135s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	/**
	 * @return Devuelve pDelegate.
	 */
	public co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate getPDelegate() {
		return pDelegate;
	}
	/**
	 * @param delegate El pDelegate a establecer.
	 */
	public void setPDelegate(co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate delegate) {
		pDelegate = delegate;
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
	 * @return Devuelve fampsKey.
	 */
	public Familia_producto_servicio_stKey getFampsKey() {
		return fampsKey;
	}
	/**
	 * @param fampsKey El fampsKey a establecer.
	 */
	public void setFampsKey(Familia_producto_servicio_stKey fampsKey) {
		this.fampsKey = fampsKey;
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
}