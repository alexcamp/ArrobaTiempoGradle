package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.acs.NBIWebServicesInvoker;
import co.com.telefonica.acs.NBIWebServicesInvokerAsync;
import co.com.telefonica.acs.ParametrosMotiveDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.*;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam3;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013SError;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR015E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR015S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR023E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR023S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR030E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR031S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR032S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR033E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR034S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR036E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR037S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR038E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR039S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR040S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR041E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR041S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR042E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR042S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044EClient;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR048E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR048S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049EWideBand;
import co.com.telefonica.atiempo.interfaces.atiempo.TR051E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR051S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR137S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR515E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR515S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR610E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR610S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EAccessTime;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EAdressData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EContactData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ECoordinatedActions;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ECustomer;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EDateData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EMassiveBreakdowns;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ENotes;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EProduct;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701EProductService;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701ETechnicalData;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702SSchedule;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711SBreakPairs;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711SMaterials;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR718E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR718S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719S;
import co.com.telefonica.atiempo.interfaces.atiempo.TRSMSE;
import co.com.telefonica.atiempo.interfaces.atiempo.TRSMSEDatos;
import co.com.telefonica.atiempo.interfaces.atiempo.TypeValue;
import co.com.telefonica.atiempo.interfaces.atiempo.TypeValueList;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionesMasivasByFileMSGDTO;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baLocal;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baLocalHome;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudModemMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocal;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocalHome;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarModemSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarRecursosSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionModemAutoInstalacion;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionTeraBoxMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionWebFilterOptenetServicioMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaActuacionSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultaDisponibilidadSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.CreaActuacionSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarCorreoTeraBoxMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarPrimeraFacturaInternetEquipadoMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarRefrecarDatosMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarSMSACSServicioMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOALocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.InternetMovilMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.SolicitudReagendamientoSCMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ActualizaInventarioBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ActualizaInventarioBAPEMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.BajaBASigresMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.CambiarNumeroBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.CambioNumeroSigresMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionAula365BAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionServicioSigresCambioPlanMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ConfiguracionTerraBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.InformarResultadoInstalacionSigresBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ModificarIdOperadoraSigresMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ObtenerCuentaCorreoSigresBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.SolicitudConfiguracionBAMQ;
import co.com.telefonica.atiempo.vpistbba.serviciosba.SuspensionReconexionBASigresMQ;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Ps_Tipo_ModemLocalHome;
import com.telefonica.atiempo.ejb.eb.Tipo_ModemLocal;
import com.telefonica.atiempo.ejb.eb.Tipo_ModemLocalHome;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.OperationStatus;
import com.telefonica_chile.atiempo.utiles.OperationStatusHash;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;
import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocalHome;

/**
 * Bean implementation class for Enterprise Bean: RecursosBA
 */
public class RecursosBABean	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements RecursosBAInterfaces {

	
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;

		private Recursos_baLocalHome recursos_baLocalHome;
		private Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome ;
		private Mensaje_estadoLocalHome mensaje_estadoLocalHome;
		private PeticionLocalHome peticionLocalHome ;
		private LocalidadLocalHome localidadLocalHome;
		private ConectorLocalHome conectorLocalHome;
		private ModemLocalHome modemLocalHome;
		private UsuarioLocalHome usuarioHome;
		private Catalogo_causalLocalHome catalogo_causalHome;
		private Estado_psLocalHome estado_psHome;
		private Causal_peticionLocalHome causal_peticionHome;
		private Estado_ps_peticionLocalHome estado_ps_peticionHome;
		private Tmp_modemLocalHome tmp_modemLocalHome;
		private Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome;					
		private Tipo_ModemLocalHome tipo_ModemLocalHome;	
		private Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome;
	
		private Dslam_conec9_apscLocalHome dslam_conec9_apscLocalHome;
		private Servicio_basico_supl_conec6_apsc_lineaLocalHome servicio_basico_supl_conec6_apsc_lineaLocalHome;
		private PeticionesServicesLocalHome peticionesServicesLocalHome;
		private Producto_servicioLocalHome producto_servicioLocalHome;
		
		private DBManager dbSeq ;
		private SimpleDateFormat df ;

			private OperationStatusHash opStatus = null; // Pablo L

		private static final String DIAS_FECHA_FINAL_DISPONIBILIDAD = "DIAS_FECHA_FINAL_DISPONIBILIDAD";
		private static int cantDecosInst = 0;
		private static int cantDecosDesins = 0;
		private static int cantDecosDesinsSTD = 0;
		private static int cantDecosDesinsHD = 0;
		private static int cantDecosDesinsPVR = 0;
		private static boolean yaSeValidoAltaBajaMigDeco=false;
			/*
			 * Metodo modificador del Session EJB
			 *  (non-Javadoc)
			 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
			 */
			public void setSessionContext (SessionContext ctx)
			throws EJBException, RemoteException
			{
				super.setSessionContext (ctx) ;
				// Creacion de DataSource
				dbSeq = new DBManager ();
				dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
				df = new SimpleDateFormat ("yyyy/MM/dd") ;
			
				opStatus = OperationStatusHash.getInstance(); // Pablo L
				
				buscaHome ();
			}
    
			/*
			 * Metodo Generador de Home
			 */
			private void buscaHome (){
			
				try {
				
				//	Creacion de los Home
				recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
				mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				peticionesServicesLocalHome = (PeticionesServicesLocalHome) HomeFactory.getHome(PeticionesServicesLocalHome.JNDI_NAME);
				producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
				
				dslam_conec9_apscLocalHome = (Dslam_conec9_apscLocalHome) HomeFactory.getHome(Dslam_conec9_apscLocalHome.JNDI_NAME);
				servicio_basico_supl_conec6_apsc_lineaLocalHome = (Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
				tmp_modemLocalHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
				producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				tipo_ModemLocalHome = (Tipo_ModemLocalHome) HomeFactory.getHome(Tipo_ModemLocalHome.JNDI_NAME);
				ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			
				} catch (NamingException e) {
					log.debug(" Creacion de Local Home Nulls");
				}
			}
    
			/*
			 * Metodo validador Home
			 */
			private void validaHome ()
			throws ATiempoAppEx
			{
				// Validacion de los Home
				if ( recursos_baLocalHome == null || mensaje_estadoLocalHome == null || peticionLocalHome == null ||
					localidadLocalHome == null || conectorLocalHome == null
					|| peticionesServicesLocalHome == null
					|| producto_servicioLocalHome == null 
					|| mensajeEstadoBaLocalHome == null 
					|| dslam_conec9_apscLocalHome == null
					|| servicio_basico_supl_conec6_apsc_lineaLocalHome == null
					|| modemLocalHome == null
					|| tipo_ModemLocalHome == null
					|| ps_Tipo_ModemLocalHome == null
					|| producto_servicio_peticionLocalHome == null)
					throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
			
			public boolean tienePsWebFilter(Long nroPeticion) throws ATiempoAppEx
			{
				ArrayList arrayList=new ArrayList();
				try
				{
					PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					
					if(peticionHome==null)
						peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					log.info("Peticion Buscar:" + nroPeticion);
					PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(nroPeticion));
					
					Collection listaPsPet=peticionLocal.getProducto_servicio_peticion();
					Iterator listaPsPetIt=null;
					Long operacionColmercial=new Long(0);
					Long psWebFilterId=new Long(0);
					
					String listaPsWebFilter=VpistbbaConfig.getVariable("PS_WEB_FILTER");
					String[] arrayPsWebFilter=listaPsWebFilter.split(",");
					boolean tieneWebFilter = false;
					for(listaPsPetIt=listaPsPet.iterator();listaPsPetIt.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaPsPetIt.next();
						Producto_servicio_peticionKey producto_servicio_peticionKey = (Producto_servicio_peticionKey)producto_servicio_peticionLocal.getPrimaryKey();
						Producto_servicioLocal producto_servicioLocal = (Producto_servicioLocal) producto_servicio_peticionLocal.getProducto_servicio();
						Producto_servicioKey producto_servicioKey = (Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
						for(int i=0;i<=arrayPsWebFilter.length-1;i++){
							if(arrayPsWebFilter[i].equals(producto_servicioKey.ps_id.toString())){
								Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
								operacionColmercial=((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;
								psWebFilterId=producto_servicioKey.ps_id;
								log.debug("El ps "+psWebFilterId+" de Web filter tiene la siguiente OC: "+operacionColmercial);
								
								tieneWebFilter=true;
								
								break;
							}
							
						}
						
					}
					return tieneWebFilter;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					log.debug("Exception",e);
					throw new ATiempoAppEx(e.getMessage());
				}
			}		
	
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#solicitudConfiguracionBA(java.lang.String, java.lang.String)
	 */
	public void solicitudConfiguracionBA(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo) throws ATiempoAppEx {
		
		try{
			
			validaHome ();
			
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			//log.debug("Creamos los mensajes de estado");
			
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
//			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
			
//			PeticionesServicesLocal peticionesServicesLocal = peticionesServicesLocalHome.create();
			//log.debug("Creamos la peticion local");
			// Object PsVsOcVO
//			ArrayList flujoPeticiones = psOk;//peticionesServicesLocal.listaPsDePeticionQuePasaPorActividad(new Long(peticion), actividadFlujo);
//			PsVsOcVO psprim = (PsVsOcVO) flujoPeticiones.iterator().next();
			
			//log.debug("Sacamos lo ps que pasan por la actividad");
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR013E tr013e = new TR013E();
			//log.debug("Creamos el TR013");
			tr013e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			tr013e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
			
			StringBuffer direccion = new StringBuffer();
			
			if(!peticionLocal.getNom_cal_ds().equals("") && peticionLocal.getNom_cal_ds() != null){
				direccion.append(peticionLocal.getNom_cal_ds()+ " ");
			}
			if(!peticionLocal.getNum_cal_nu().equals("") && peticionLocal.getNum_cal_nu() != null){
				direccion.append(peticionLocal.getNum_cal_nu()+" ");
			}
			if(!peticionLocal.getDsc_cmp_pri_ds().equals("") && peticionLocal.getDsc_cmp_pri_ds() != null){
				direccion.append(peticionLocal.getDsc_cmp_pri_ds()+" ");
			}
			if(!peticionLocal.getDsc_cmp_seg_ds().equals("") && peticionLocal.getDsc_cmp_seg_ds() != null){
				direccion.append(peticionLocal.getDsc_cmp_seg_ds()+" ");
			}
			String direc=direccion.toString();
			if(direc.length()>50)
				direc=direc.substring(0,49);
			tr013e.setAddress(direc);
			String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
			
			if (clientDocument!=null && clientDocument.trim()!=""){
				if (clientDocument.length()>16){
					clientDocument=clientDocument.substring(0,16);
				}
			}      
			else
			{
				log.warn("Advertencia : No existe valor para el Documento de Cliente ");
				clientDocument="-";
			}
			
			tr013e.setClientDocumentType(peticionLocal.getTip_doc_cd());
			tr013e.setClientDocument(clientDocument);
			tr013e.setClientName(peticionLocal.getNom_ds());
			tr013e.setClientFirstLastName(peticionLocal.getPri_ape_ds());
			tr013e.setClientSecondLastName(peticionLocal.getSeg_ape_ds());
			tr013e.setContactPhone(peticionLocal.getTel_cot_ds());
			String tipoCliente = peticionLocal.getTip_cli_cd();
					
			if (tipoCliente!=null && !tipoCliente.trim().equals(""))
			{
				
				tr013e.setClientType(tipoCliente.charAt(0));

			}
			else
			{
				log.warn("Advertencia : No existe valor para el Tipo de Cliente ");
				tr013e.setClientType('-');
			}
			LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey(); 
			tr013e.setCityCode(localidadKey.cod_loc);
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			//Voy a asumir que aca viene uno solo.
			Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
			
			if(!psprim.isSacarFatherEmAlta())
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsId(),psprim.getOpComId()).iterator().next();
			else
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsIdAlta(),psprim.getOpCoAlta()).iterator().next();
								
			Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
			Long psId = subPet.getCod_pro_ser_cd();
			
			Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
			Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
			
			try
			{
				if(psprim.isSacarFatherEmAlta() && psId.longValue()==psprim.getPsIdAlta().longValue())
				{
					Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();
					while (iterCarac.hasNext())
					{
						Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
						Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
						Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
						if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
						log.info("Informacion : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
						tr013e.setFatherEmail(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
						break;
						}
					}
				}
				else if(psId.longValue()==psprim.getPsId().longValue())
				{
					//si la peticion es de ic y tiene id pc se envia el id pc como father email ..sino se saca la caracteristica .. si la caracteristica esta vacia se pone "".
					if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC)
					{
						if( agrupacion_atisLocal.getNum_ide_nu()!=null && !agrupacion_atisLocal.getNum_ide_nu().equals("") )
							tr013e.setFatherEmail(agrupacion_atisLocal.getNum_ide_nu());
						else
						{
							Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();
							while (iterCarac.hasNext())
							{
								Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
								Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
								Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
								if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
								tr013e.setFatherEmail(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								break;
								}
							}
						}
					}
					else
					{
						//si no es IC (cualquier ba) siempre se saca la caracteristica sino va vacio.
						Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext())
						{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
							if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
							tr013e.setFatherEmail(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
							break;
							}
						}
					}
					if(tr013e.getFatherEmail()==null)
						tr013e.setFatherEmail("");
				}
				else
					tr013e.setFatherEmail(producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu());
				if(tr013e.getFatherEmail()==null)
					tr013e.setFatherEmail(subPet.getFk_agru_sub().getNum_ide_nu());
				
				/**
				 * DAVID: Si viene ps de web filter, se extrae el father email de otra manera.
				 */
				if(tienePsWebFilter(new Long(peticion))){
					Collection recursosBaList=peticionLocal.getRecursos_ba();
					Iterator recursosBaListIt=null;
					String fatherEmail=" ";
					for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
						Recursos_baLocal recursos_baLocal = (Recursos_baLocal)recursosBaListIt.next();
						String fatherEmail2=recursos_baLocal.getFather_email_nuevo();
						if(fatherEmail2!=null&&!fatherEmail2.equals("")){
							fatherEmail=fatherEmail2;
							break;
						}
					}
					tr013e.setFatherEmail(fatherEmail);
				}
				
				log.info("Setie el valor de father eemail en:"+tr013e.getFatherEmail());
				
				
			}
			catch(Exception e)
			{
				//TODO: Mejorar esto: Si no viene email, debe ir a PGC la actividad.
				log.warn("No viene el EMAIL",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}
			
			//VMM: Para la reversa se envia el codigo de la OC opuesta segun esta poblado en la BD
			
			if (reversa)
			{
				tr013e.setComercialOperationType(psprim.getOpComRevId().longValue());
			}else{
				tr013e.setComercialOperationType(psprim.getOpComId().longValue()); 
			}
			tr013e.setProductServiceCode(psprim.getPsId().longValue());			
			tr013e.setContactName(peticionLocal.getNom_int_ds());
			tr013e.setContactFirstLastName(peticionLocal.getPri_ape_int_ds());
			tr013e.setContactSecondLastName(peticionLocal.getSeg_ape_int_ds());
			
			
			Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
			Collection dslamArray = new ArrayList();
			
			if (peticionLocal.getRecursos_linea_basica().size()>0){
				
				
				if ( peticionLocal.getRecursos_linea_basica().size() > 1)
				log.warn("tiene mas de una linea basica");
				
				
				for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); )
				{	
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
				}
				
				if(recursos_linea_basicaLocal.getTelefono_ant()!=null)
					tr013e.setPreviousServicePhone(recursos_linea_basicaLocal.getTelefono_ant().intValue());
				else
				{
					log.warn("Advertencia : No existe valor para el Telefono Anterior porque no hay informacion de recursos de linea");
					int previousServicePhone=peticionLocal.getpreviousServicePhone();
					if(previousServicePhone>0)
						tr013e.setPreviousServicePhone(previousServicePhone);	
					else
						tr013e.setPreviousServicePhone(0);
				}
				//Dslam Desconfigurar
				if(cod_actividad.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_INTERNET")))
				{
					InformacionAdslDTO informacionAdsl = null;		
					informacionAdsl = obtenerDatosActualAdsl( new Long(peticion) );
					//Si no tengo IP del Dslam es como si no tuviera nada
					if (informacionAdsl!=null && informacionAdsl.getDirecIpDslam()!=null && !informacionAdsl.getDirecIpDslam().equals("")){
						Dslam3 dslam = new Dslam3();
						dslam.setIp(informacionAdsl.getDirecIpDslam());
						dslamArray.add(dslam);
					}
				}else{
//					Correccion 07/10/2009			
//					if(recursos_linea_basicaLocal.getDslam_conec9_apsc().size()>0)
//					{
//						for (Iterator iter = recursos_linea_basicaLocal.getDslam_conec9_apsc().iterator();iter.hasNext();)
//						{
//	
//							Dslam_conec9_apscLocal dslam_conec9_apscLocal = (Dslam_conec9_apscLocal) iter.next();
//							Dslam3 dslam = new Dslam3();
//						
//							dslam.setIp(dslam_conec9_apscLocal.getIp());
//							dslamArray.add(dslam);
//						}
//					}
	
					Collection zonesArray = new ArrayList();
					
						if(recursos_linea_basicaLocal.getZonas_atendimiento().size() > 0) {
							for (Iterator iter =recursos_linea_basicaLocal.getZonas_atendimiento().iterator();iter.hasNext();) {
								Zonas_atendimientoLocal zonasLocal = (Zonas_atendimientoLocal)iter.next();
							Dslam3 dslam = new Dslam3();
								dslam.setIp(zonasLocal.getIp());
							dslamArray.add(dslam);
						}
					}
				}
			}
			else
			{
				log.warn("Advertencia : No existe valor para el Telefono Anterior porque no hay informacion de recursos de linea");
				int previousServicePhone=peticionLocal.getpreviousServicePhone();
				if(previousServicePhone>0)
					tr013e.setPreviousServicePhone(previousServicePhone);	
				else
					tr013e.setPreviousServicePhone(0);

			}
			
			//Nueva regla para service phone
			
			if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC)
			{
				String valorOcho=String.valueOf(peticion);
				if(valorOcho.length()>8)
					valorOcho=valorOcho.substring(0,8);
				tr013e.setServicePhone(new Integer(valorOcho).intValue());
			}
			else
			{
				if(reversa)
				{ // cambio el tipo para realizar bien la reversa
					if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A"))
					{
						psprim.setOpComTipo("A");
					}else if(psprim.getOpComTipo()!=null){
						psprim.setOpComTipo("B");
					}
				}
				if(psprim.getOpComTipo()!=null && psprim.getOpComTipo().equals("A") && !reversa)
				{
					if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null)
						tr013e.setServicePhone(recursos_linea_basicaLocal.getTelefono_asg().intValue());
					//DAVID: si viene sólo el ps de web filter se extrae el serviphone así:
					else if(tienePsWebFilter(new Long(peticion))){
						tr013e.setServicePhone(new Integer(peticionLocal.getNum_ide_nu_stb()).intValue());
					}else
						throw new ATiempoAppEx("No es posible obtener datos de recursos");		
				}
				else if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A") && !reversa)
				{
					try
					{
						Integer numide=new Integer(agrupacion_atisLocal.getNum_ide_nu());
						tr013e.setServicePhone(numide.intValue());
					}
					catch(NumberFormatException nf)
					{
						throw new ATiempoAppEx("No es posible obtener num ide nu");	
					}
				}
				else if(psprim.getOpComTipo()!=null && psprim.getOpComTipo().equals("A") && reversa)
				{
					try
					{
						Integer numide=new Integer(agrupacion_atisLocal.getNum_ide_nu());
						tr013e.setServicePhone(numide.intValue());
					}
					catch(NumberFormatException nf)
					{
						throw new ATiempoAppEx("No es posible obtener num ide nu");	
					}		
				}
				else if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A") && reversa)
				{
					if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null)
						tr013e.setServicePhone(recursos_linea_basicaLocal.getTelefono_asg().intValue());
//					DAVID: si viene sólo el ps de web filter se extrae el serviphone así:
					else if(tienePsWebFilter(new Long(peticion))){
						tr013e.setServicePhone(new Integer(peticionLocal.getNum_ide_nu_stb()).intValue());
					}else
						throw new ATiempoAppEx("No es posible obtener datos de recursos");
				}
				else
					throw new ATiempoAppEx("No es posible determinar tipo opco");	
			}
//			Si no hay Dslam es pk el PS es de Internet Conmutado. Se pasa una array vacio
			tr013e.setDslams(dslamArray);

			//CorreccionUMTSoperacionComercialUmts
			tr013e.setSecondComercialOperation(0);
			PeticionesDelegate delegate = new PeticionesDelegate();
			if (delegate.esGrupoUmts(new Long(peticion))){
				//DAVID: correctivo duo UMTS reversa - Marzo 4 de 2010, Marzo 19 2010 defecto 34210
					if(reversa){
						if(ComunInterfaces.AltaDuoUmts==delegate.operacionUmts(new Long(peticion))){
	                        tr013e.setSecondComercialOperation(ComunInterfaces.BajaDuoUmts);
	                    }
	                    else if(ComunInterfaces.BajaDuoUmts==delegate.operacionUmts(new Long(peticion))){
	                        tr013e.setSecondComercialOperation(ComunInterfaces.AltaDuoUmts);
	                    }
                	}else{
                		if(!delegate.validaDuoUmtsQuiebre(new Long(peticion))){
                			tr013e.setSecondComercialOperation(delegate.operacionUmts(new Long(peticion)));
                		}else{
            				/**
            				 * Si es dúo pero tiene uno de los dos PS's en quiebre, ya no se manejará como dúo sino como
            				 * un ps normal.
            				 */
                        	log.debug("Hay un ps de dúo que está en quiebre, el second comercial operation ya no se maneja como dúno UMTS y se pondrá en cero...");
                        	//El second comercial operation ya se fijó a cero arriba.
        				}
	                }
				
                //FIN DAVID: correctivo duo UMTS reversa - Marzo 4 de 2010,Marzo 19 2010 defecto 34210
								
				String oper = delegate.operacionComercialUmts(new Long(peticion));
				if(oper.equalsIgnoreCase("BCP")||oper.equalsIgnoreCase("ACP")){
					
					if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null)
						tr013e.setServicePhone(recursos_linea_basicaLocal.getTelefono_asg().intValue());
//					DAVID: si viene sólo el ps de web filter se extrae el serviphone así:
					else if(tienePsWebFilter(new Long(peticion))){
						tr013e.setServicePhone(new Integer(peticionLocal.getNum_ide_nu_stb()).intValue());
					}else
						throw new ATiempoAppEx("No es posible obtener datos de recursos peticion:.. "+peticion );		
				}
			}
			
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);
	
			int areaPhone= 0;
			int numeroPhone= 0;
			try
			{
				
				areaPhone=Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(0,1));
				numeroPhone=Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(1));
					
				mensajeEstadoBALocal.setArea(new Integer(areaPhone));
				mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
			}catch(Exception e)	{
				log.info("Num ide nu stb no numerico:"+peticionLocal.getNum_ide_nu_stb());
			}
			
			log.debug("Envio de Mensaje" +
				"");
			ConfiguracionServicioBAMQ configuracionServicioBAMQ = new ConfiguracionServicioBAMQ();
			configuracionServicioBAMQ.enviarMensaje(tr013e);
			
			
		
		} catch (NumberFormatException e) {
			log.warn("Error al enviar la Configuracion Internet",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			log.warn("Error al enviar la Configuracion Internet",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);	
		} catch (FinderException e) {
			log.warn("Error al enviar la Configuracion Internet",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (NamingException e) {
			log.warn("Error al enviar la Configuracion Internet",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (Exception e) {
			log.warn("Error al enviar la Configuracion Internet",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}  
	}
	

	public void recepcionConfiguracionBA(TR013S tr013s) throws ATiempoAppEx
	{
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
		try
		{
			bExecution = BackendTraceUtil.initExecution(tr013s, log);
			bExecution.setNumPetAtiempo(new Long(tr013s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr013s.getId());
			bExecution.startOperation();

			validaHome ();
			boolean esEspera=false;
			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
	
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr013s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;
        
		   try
		   {
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
		   }
		   catch (FinderException fex)
		   {
				log.debug("No encontró mensaje estado BA Local");
				//pero puede ser un mensaje que estaba en proceso.
				Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr013s.getAtisRequestNumber()),new Integer(estadoEsperaManual));
				if(lista.size()!=1)
					mensajeEstadoBaLocal = null ;
				else
				{
					mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
					esEspera=true;
				}					
		   }
   
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null)
			{
				log.debug(
					"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr013s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr013s.getId(),ATiempoAppEx.EXCEPTION);
			}

	
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;
	
			//Validacion del estado de la respuesta
			if(!esEspera)
			{
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual)
				{
					log.debug(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr013s));
					return;
				}	 
			}
			else
			{
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk )
				{
					log.debug(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr013s));
					return;
				}
			}
			
			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

			if ( recursosLineaBaBasica.size() == 0){
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			}else{
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}			
			//TODO VMM - No setear los recursos cuando hay algun tipo de error mas abajo hay mas validaciones
			if (tr013s.isError() != true)
			{								
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				
				if(tr013s.getActualDslamIp()!=null && !"".equals(tr013s.getActualDslamIp().trim()))
				{
					recursos_baLocal.setAdsl_actual(tr013s.getActualAdsl());
					recursos_baLocal.setDir_ip_dslam_actual(tr013s.getActualDslamIp());
					recursos_baLocal.setDir_ip_wan_actual(tr013s.getActualWanIp());
					recursos_baLocal.setDir_ip_lan_actual(tr013s.getActualLanIp());
					recursos_baLocal.setFrame_actual(tr013s.getActualFrame());
					recursos_baLocal.setMasc_actual(tr013s.getActualLanMask());
					recursos_baLocal.setPost_actual(tr013s.getActualPots());
					recursos_baLocal.setPuerto_actual(tr013s.getActualPort());
					recursos_baLocal.setSlot_actual(tr013s.getActualSlot());
					recursos_baLocal.setVpivci_actual(tr013s.getActualVpiVci());
					recursos_baLocal.setVpivci_red_actual(tr013s.getActualVpiVciNetwork());
					if(tr013s.getFatherEmail()!=null){
						recursos_baLocal.setFather_email_nuevo(tr013s.getFatherEmail());
					}					
				}

				if(tr013s.getNewDslamIpSelectNumber()!=null && !"".equals(tr013s.getNewDslamIpSelectNumber().trim()))
				{
					recursos_baLocal.setAdsl_nuevo(tr013s.getNewAdslNumber());
					recursos_baLocal.setDir_ip_dslam_nueva(tr013s.getNewDslamIpSelectNumber());
					recursos_baLocal.setDir_ip_lan_nueva(tr013s.getNewClientLanIpNumber());
					recursos_baLocal.setDir_ip_wan_nueva(tr013s.getNewClientWanIpNumber());
					recursos_baLocal.setFrame_nuevo(tr013s.getNewFrameNumber());
					recursos_baLocal.setMasc_lan_nueva(tr013s.getNewLanMaskNumber());
					recursos_baLocal.setPost_nuevo(tr013s.getNewPotsNumber());
					recursos_baLocal.setPuerto_nuevo(tr013s.getNewPortNumber());
					recursos_baLocal.setSlot_nuevo(tr013s.getNewSlot());
					recursos_baLocal.setVpivci_nuevo(tr013s.getNewVpiVci());
					recursos_baLocal.setVpivci_red_nuevo(tr013s.getNewVpiVciNetwork());
					if(tr013s.getFatherEmail()!=null){
						recursos_baLocal.setFather_email_nuevo(tr013s.getFatherEmail());
					}
				}
			}
			
			StringBuffer errores = new StringBuffer("Detalle :");
			
			if (tr013s.getErrors() != null)
			{
				//Sacamos el primer valor de error para guardarlo
				TR013SError errorAuxPrim = (TR013SError) tr013s.getErrors().iterator().next();
				recursos_baLocal.setCod_error(new Integer(new Long(errorAuxPrim.getCode()).intValue()));
				
				//Sacamos todos los valores de error, para ser concatenado y despues mostrados
				for(Iterator iterator=tr013s.getErrors().iterator();iterator.hasNext();)
				{
					TR013SError errorAux=(TR013SError) iterator.next();
					errores.append(String.valueOf(errorAux.getCode())+" : "+errorAux.getDescription()+".");
					
				}
			}
			else
			{
				recursos_baLocal.setCod_error(null);
			}
			
			if (tr013s.getErrorMessage() != null && !tr013s.getErrorMessage().equals("") )
			{
				
				recursos_baLocal.setDesc_error(tr013s.getErrorMessage());
			}
			
			
			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
	

			if (tr013s.isError() == true)
			{	
				if(tr013s.getStatus() == 4)
				{
					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad( mensajeEstadoBaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
//					agonz 27/06/2008
					String codError;
					//Iterator iterator=tr013s.getErrors().iterator();
					//codError = iterator.toString();
					
					//SQL -302
					TR013SError errorAuxPrim = (TR013SError) tr013s.getErrors().iterator().next();
					codError = String.valueOf(errorAuxPrim.getCode());
					
					PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
					String nombreClase=TR013S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR013S.class.getName());
					 if(errorLegado != null){
				
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else {
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}


					actDto.setObservacion("Error en la Asignación de Recursos BA = " + tr013s.getErrorMessage()+". "+ errores.toString());
					actividadEJB.terminar(actDto);
 
					return;
				}
				else if(tr013s.getStatus() == 1)//Estado en proceso
				{
					mensajeEstadoBaLocal.setMensaje_estado(mensajeManualLocal);
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
					actDto.setObservacion("Estado en Proceso= " + tr013s.getErrorMessage()+". "+ errores.toString());
					actividadEJB.grabarSinTerminar(actDto);
					return;
				}
			}
				
			if(tr013s.getStatus() == 4)
			{
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				
//				agonz 27/06/2008
				String codError;
				
				//SQL -302
				//Iterator iterator=tr013s.getErrors().iterator();
				//codError = iterator.toString();
				
				TR013SError errorAuxPrim = (TR013SError) tr013s.getErrors().iterator().next();
				codError = String.valueOf(errorAuxPrim.getCode());
				////
				
				
				PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
				String nombreClase=TR013S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR013S.class.getName());
				 if(errorLegado != null){
				
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else {
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
				
				actDto.setObservacion("Error en la Asignación de Recursos BA = " + tr013s.getErrorMessage()+". "+ errores.toString());
				actividadEJB.terminar(actDto);
 
				return;
			}
			
			if(tr013s.getStatus() == 1)//Estado en proceso
			{
				mensajeEstadoBaLocal.setMensaje_estado(mensajeManualLocal);
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				actDto.setObservacion("Estado en Proceso= " + tr013s.getErrorMessage()+". "+ errores.toString());
				actividadEJB.grabarSinTerminar(actDto);
				return;
			}
			
			if (tr013s.isError() == true)
			{
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				//TODO: No existen los directores de Flujo
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				//insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				actDto.setObservacion("Error en la Asignación de Recursos BA = " + tr013s.getErrorMessage()+". "+ errores.toString());

				//agregado por agonz
				String codError;
				//Iterator iterator=tr013s.getErrors().iterator();
				TR013SError errorAuxPrim = (TR013SError) tr013s.getErrors().iterator().next();
				codError = String.valueOf(errorAuxPrim.getCode());
				
				PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
				String nombreClase=TR013S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR013S.class.getName());
				 if(errorLegado != null){
				 	if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
				 		actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr013s.getErrorMessage());
						actividadEJB.terminar (actDto);
						return;
					}
				 	String destino = errorLegado.getPlataforma(); 
				 	actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, destino);
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else {
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
//				fin agregado
				actividadEJB.terminar(actDto);
						
				return;
			}
		
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			actividadEJB.terminar(actDto);
		}
		catch (NumberFormatException e)
		{
			bExecution.setErrorOp(true);
			log.warn("",e);
			
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} 
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}

	}
		
	public boolean noHayModemParaActualizarInventarioBa(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			boolean esPCBAAlta = false;
			
			Collection pspList = peticionLocal.getProducto_servicio_peticion();
			for (Iterator pspIter = pspList.iterator(); pspIter.hasNext();){
				Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)pspIter.next();
				
				Familia_producto_servicioKey fampKey = pspLocal.getFamiliaKey();
//				REQ BA NAKED adicion familia PC naked
				if (fampKey.faps_id.intValue() == ComunInterfaces.familiaPcBA || fampKey.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked){
					Operacion_comercialLocal opComercialLocal = pspLocal.getOperacion_comercial();
					Operacion_comercialKey opComercialKey = (Operacion_comercialKey)opComercialLocal.getPrimaryKey();
					
					if (opComercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
						esPCBAAlta = true;
						break;
					}
				}
			}

			Collection collection=peticionLocal.getModem();
			if(collection.size()==0)
				return true;
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				ModemLocal modemLocal=(ModemLocal) iterator.next();
				if (esPCBAAlta){
					return false;
				}else{
					if(modemLocal.getAccion().shortValue()!=0)
						return false;
				}
				
			}
			return true;
		}
		catch(FinderException finderException)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,finderException);
		}
		catch (Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
	}
		
	public void enviaActualizaInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad, boolean esAgendaSC) throws ATiempoAppEx{
			
			try{			
				boolean esInhibirActividad = false;
				validaHome ();
				
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
	
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
				log.debug("Sacmos la Peticion para actualizar Intentario BA");
				// Proceso para la validacion de los tipos de ps, que estan en el mensaje
				StringBuffer append = new StringBuffer();
				boolean bip = true;
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				Producto_servicioKey productoServicoKey = null;
				boolean esPCBAAlta = false;
					
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
	
					if(bip){
						append.append(productoServicoKey.ps_id);
						bip = false;
					}else{
						append.append(" ,"+ productoServicoKey.ps_id .toString());
					}
				}
	
				Collection pspList = peticionLocal.getProducto_servicio_peticion();
				for (Iterator pspIter = pspList.iterator(); pspIter.hasNext();){
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)pspIter.next();
					
					Familia_producto_servicioKey fampKey = pspLocal.getFamiliaKey();
					//REQ BA NAKED adicion familia PC naked
					if (fampKey.faps_id.intValue() == ComunInterfaces.familiaPcBA 
							&& fampKey.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked ){
						Operacion_comercialLocal opComercialLocal = pspLocal.getOperacion_comercial();
						Operacion_comercialKey opComercialKey = (Operacion_comercialKey)opComercialLocal.getPrimaryKey();
						
						if (opComercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
							esPCBAAlta = true;
							break;
						}
					}
				}
				
				
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				
				log.debug("Sacamos la PS para actualizar Intentario BA");
				
				Collection modems = peticionLocal.getModem();
				
				if (modems.size() ==0){
					
					log.debug("INFO: No existen Modem Asociados a la Peticion");
					
					return;
				}
			
				for(Iterator iter = modems.iterator();iter.hasNext();){
	
					ModemLocal modemLocal = (ModemLocal) iter.next();

					Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
					TR007E tr007e = new TR007E();
					tr007e.setId(String.valueOf(IdCorrelativo));
					
					String phoneNumber = peticionLocal.getNum_ide_nu_stb();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					} else
					{
						phoneNumber="0";
					}
					if(modemLocal.getTelefono().intValue()!=new Integer(phoneNumber).intValue())
						tr007e.setPhoneNumber(new Integer(phoneNumber).intValue());
					else
						tr007e.setPhoneNumber(modemLocal.getTelefono().intValue());
					
					tr007e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					
					if (!esPCBAAlta){
						if(modemLocal.getAccion().shortValue()==0)
							continue;
					}
						
					
					tr007e.setSerialNumber(modemKey.serial);
				
					if(modemKey.serial != null && 
							(modemKey.serial.equals(ComunInterfaces.VALOR_NO_SERIAL_ESPACIO)
							|| modemKey.serial.equals(ComunInterfaces.VALOR_NO_SERIAL_HC))){
						
						esInhibirActividad = true;
					}
					/*
					if( modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemOcupar || 
						modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemCambioNoRec ||
						modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemCambioAveriado )
						tr007e.setSerialNumber(modemKey.serial);
					else
						tr007e.setSerialNumber("");*/
					tr007e.setModemAction(modemLocal.getAccion().intValue());
				
					tr007e.setBrand(modemLocal.getModem_marca());
					tr007e.setModel(modemLocal.getModelo());
					tr007e.setType(modemLocal.getTipo().toString());
					/*RQ.8595 - mfmendez*/
					if(modemLocal.getFec_cont_sap() != null)
						tr007e.setModemPostingDateSAP(modemLocal.getFec_cont_sap());
					else
						tr007e.setModemPostingDateSAP("");
					
					if(modemLocal.getClase_mov_sap() != null)
						tr007e.setModemMoveTypeSAP(modemLocal.getClase_mov_sap());
					else
						tr007e.setModemMoveTypeSAP("");
					
					tr007e.setModemMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
					
					if(modemLocal.getNum_material_sap() != null)
						tr007e.setModemMaterialSAP(modemLocal.getNum_material_sap());
					else
						tr007e.setModemMaterialSAP("");
						
					if(modemLocal.getCentro_sap() != null)
						tr007e.setModemPlantSAP(modemLocal.getCentro_sap());
					else
						tr007e.setModemPlantSAP("");
					
					if(modemLocal.getAlmacen_sap() != null)
						tr007e.setModemStorageSAP(modemLocal.getAlmacen_sap());
					else
						tr007e.setModemStorageSAP("");
					
					if(modemLocal.getCod_lote_sap() != null)
						tr007e.setModemBatchCodeSAP(modemLocal.getCod_lote_sap());
					else
						tr007e.setModemBatchCodeSAP("");
					
					if(modemLocal.getUnd_medida_sap() != null)
						tr007e.setModemMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
					else
						tr007e.setModemMeasurementUnitSAP("");
					
					if(modemLocal.getCentr_cost_sap() != null)
						tr007e.setModemCostCenterSAP(modemLocal.getCentr_cost_sap());
					else
						tr007e.setModemCostCenterSAP("");
					
					if(modemLocal.getArea_func_sap() != null)
						tr007e.setModemFuncAreaLongSAP(modemLocal.getArea_func_sap());
					else
						tr007e.setModemFuncAreaLongSAP("");
					
					if(modemLocal.getElement_pep_sap() != null)
						tr007e.setModemPepElementSAP(modemLocal.getElement_pep_sap());
					else
						tr007e.setModemPepElementSAP("");
					
					if(modemLocal.getFlag_mat_sap() != null)
						tr007e.setModemFlagMatSAP(modemLocal.getFlag_mat_sap());
					else
						tr007e.setModemFlagMatSAP("");
					/*FIN - RQ.8595 - mfmendez*/

					//Se cambia la lógica de enviop de los modem a HC en caso que venga en 0 y que sea PCBA en alta pone ocupar
					if (modemLocal.getAccion() != null){
						if (modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado)
							tr007e.setModemAction(ComunInterfaces.accionModemOcupar);
						else{
							if (!esPCBAAlta){
								tr007e.setModemAction(modemLocal.getAccion().intValue());
							}else{
								if (modemLocal.getAccion().intValue() == ComunInterfaces.accionModemNoAction)
									tr007e.setModemAction(ComunInterfaces.accionModemOcupar);
								else
									tr007e.setModemAction(modemLocal.getAccion().intValue());
							}
						}
					}else{
						tr007e.setModemAction(0);
					}
				
					if(!esInhibirActividad){
						//Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
						 Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
						 mensajeEstadoBALocal.setPeticion(peticionLocal);
						 Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						 mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
						 //TODO: Verificar el Conector Correcto
						 mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
						 mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
						 mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
						 mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);
						 
						 if (esAgendaSC){
						 	mensajeEstadoBALocal.setDesc_error(peticion+"@TR711");
						 }
		
						 int areaPhone= 0;
						 int numeroPhone= 0;
						 if (phoneNumber.length()>1){
							 areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
							 numeroPhone=Integer.parseInt(phoneNumber.substring(1));
						 }
						 mensajeEstadoBALocal.setArea(new Integer(areaPhone));
						 mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
		
						ActualizaInventarioBAMQ actualizaInventarioBAMQ  = new ActualizaInventarioBAMQ();
						actualizaInventarioBAMQ.enviarMensaje(tr007e);
					}else{
						/*Se inhibe la actividad porque el modem tiene como serial "NO SERIAL" o "NO_SERIAL"*/
						log.debug("Se inhibe la actividad porque el modem tiene como serial NO SERIAL o NO_SERIAL");
		 				act.setRealizarTerminoInmediato(true);
		 				act.setObservacion("Se inhibe la actividad de Actualizar Inventario BA porque el serial del modem es NO SERIAL o NO_SERIAL");	 				
					}
				}
			}catch (NumberFormatException e) {
				log.warn("NumberFormatException",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				log.warn("CreateException",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
						
			} catch (FinderException e) {
				log.warn("FinderException",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				
			} 
			catch(Exception e)
			{
				log.debug("Exception",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}	
				
		}
				
	public void recepcionActualizaInventarioBA(TR007S tr007s, boolean esAgendaSC) throws ATiempoAppEx{
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr007s, log);
				bExecution.setNumPetAtiempo(new Long(tr007s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr007s.getId());
				bExecution.startOperation();
		
				validaHome ();

				Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr007s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			   try
			   {
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			   }
			   catch (FinderException fex)
			   {
					mensajeEstadoBaLocal = null ;
			   }
   
				/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null) {
				log.debug(
					"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr007s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr007s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
				log.debug(
					"Es estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr007s));
				return;
			}

			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();

			log.debug("Mensaje recibido.");
			if (this.getEstadoMultipleMensajes(mensajeEstadoBaLocal, new Integer(estadoEspera))){
				log.debug("Ultimo mensaje -> se cierra la acitividad");	
				// todo ok, se graba la respuesta
				Recursos_baLocal recursos_baLocal;
				Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

				if ( recursosLineaBaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					recursos_baLocal = recursos_baLocalHome.create(idConDos);
				}else{
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}			

				/*Procesamiento cuando no hay error*/
				if (tr007s.isError() != true || tr007s.getErrorCode() == 0){
						
					recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
					recursos_baLocal.setCod_error(new Integer(String.valueOf(tr007s.getErrorCode())));
					recursos_baLocal.setDesc_error(tr007s.getErrorMessage());
					recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				}
	
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
	
				/*Procesamiento cuando hay error*/
				if (tr007s.isError() == true || tr007s.getErrorCode() != 0) {
					
					recursos_baLocal.setCod_error(new Integer(String.valueOf(tr007s.getErrorCode())));
					recursos_baLocal.setDesc_error(tr007s.getErrorMessage());
					
					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
					
					//agregado por adecarlini
					String codError = String.valueOf(tr007s.getErrorCode());
					String nombreClase=TR007S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							String descripcion = tr007s.getErrorMessage()+" "+tr007s.getErrorMessageInv();
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr007s.getErrorMessage());
							actividadEJB.terminar(actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else {
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					// fin agregado
					actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a "+bandeja+". Descripcion:"+tr007s.getErrorCode()+" . "+tr007s.getErrorMessageInv()+"." + tr007s.getErrorMessage());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actividadEJB.terminar(actDto);
										
					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					return;
				}
 
				log.debug("Se finaliza con exito la actividad Actualizar Inventario BA.");
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				actividadEJB.terminar(actDto);	

				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			}else{
				log.debug("Existe mas de 1 mensaje en estado de espera, no se puede cerrar la actividad de Actualizar Inventario BA.");	
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), /*Quiebre por defecto*/new Long(701), actDto.getIdActividadFlujo());
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				actDto.setObservacion("Error en la Actualizacion de Inventario. Se deriva a PGI.Descripcion: Se encuentra mas de 1 mensaje en estado de espera.");
				actividadEJB.terminar(actDto);
				
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
			}	
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);
			log.warn("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.warn("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.warn("TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch(Exception e) {
			bExecution.setErrorOp(true);
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		} finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}
	}
		
		public boolean esNumerosDistintosBA(Long idPeticion) throws ATiempoAppEx{
			boolean sonNrosDistintos=false;
			try{
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Collection colConect2 = peticionLocal.getRecursos_linea_basica () ;
				if (colConect2.size () != 1)
				{
					String error = "La peticion " + idPeticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica'" ;
					throw new ATiempoAppEx (error) ;
				}
				Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
				Long phoneNew = recursosStb.getTelefono_asg();
				//Long phoneOld = new  Long(peticionLocal.obtenerCPINAGrupacionOriginal());
				Long phoneOld = new  Long(peticionLocal.getIdentificadorOriLinea());
				if (phoneNew != null && phoneOld!=null){
					if(phoneNew.longValue()!=phoneOld.longValue())
					{
						sonNrosDistintos=true;
					}
				}					
			}catch(Exception e){
				log.debug("Exception",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			}
			return sonNrosDistintos; 
		}
		
	//CR16551 p.cawen
	/*
	 * Se ejecuta siempre la actividad, en los Traslados solo BA y en los cambios de número BA. 
	 * Para los traslados LB + BA la actividad debe ser ejecutada solo si el traslado de la LB se hizo con cambio de numero.
	 */
	public boolean dslamIguales(ActividadEJBDTO act){
		boolean esTrasladoIgualDslam = false;
		try{
		boolean esTrasladoBaja=false;
		
		RecursosBAInterfaces recursosBAInterface = new RecursosBADelegate();
		InformacionAdslDTO informacionAdslActual = null;
		//Se busca si existe la informacion de la linea existente
		try{ 
			informacionAdslActual = recursosBAInterface.obtenerDatosActualAdsl( act.getNumeroPeticion() );
			//Si no tengo IP del Dslam es como si no tuviera nada
			if (informacionAdslActual==null || informacionAdslActual.getDirecIpDslam()==null || informacionAdslActual.getDirecIpDslam().equals("") ){
				informacionAdslActual=null;
			}else
			{
				log.debug("Dslam ADSL:" + informacionAdslActual.getDirecIpDslam());
			}
		}catch (ATiempoAppEx e)
		{
			//Si no es Finder Exception
			if(e.getType() != ATiempoAppEx.FINDER){
				log.warn("Error en Actividad Configurar Internet",e);				
			}
			else{
				//no se encontro informacion
				informacionAdslActual=null;
			}
		}
		//Si existe la informacion de linea actual, entonces se compara con los Dslam de la linea nueva
		if (informacionAdslActual!=null){
			RecursosInterfaces recSTB = new RecursosDelegate();
			InformacionTecnicaDTO infSTB=recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
			
			if (infSTB.LineaNueva!=null && infSTB.LineaExistente != null){// es traslado lb pk tiene las dos lineas
				for (Iterator iter=infSTB.LineaNueva.getDslams().iterator();iter.hasNext();){
					Dslam1 unDslam = (Dslam1) iter.next();
					log.debug("Dslam Rec Linea:" + unDslam.getIp());
					if (unDslam.getIp().trim().equals(informacionAdslActual.getDirecIpDslam().trim())){
						esTrasladoIgualDslam=true;
						break;
					}
				}

			}else{
				//veo si es traslado SOLO BA
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				boolean esTrasladoAlta=false;
				Traslado_solobaLocal traslado_solobaLocal=null;
				try{
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(act.getNumeroPeticion());
					esTrasladoAlta=true;
				} catch (FinderException e) {
					log.warn("No es traslado Alta BA");
					try{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(act.getNumeroPeticion());
						// CR19465 - ana santos  - inicio
						esTrasladoBaja=true;	
						// CR19465 - ana santos  - fin
					} catch (FinderException e1) {
						
					}
				}
				if(esTrasladoAlta){
					//si es traslado BA, Comparo los Dslam del linea nueva con el Dslam ADSL de origen
					if (infSTB.LineaNueva!=null){
						for (Iterator iter=infSTB.LineaNueva.getDslams().iterator();iter.hasNext();){
							Dslam1 unDslam = (Dslam1) iter.next();
							log.debug("Dslam Rec Linea:" + unDslam.getIp());
							if (unDslam.getIp().equals(informacionAdslActual.getDirecIpDslam())){
								esTrasladoIgualDslam=true;
								break;
							}
						}
					}
				}else if (esTrasladoBaja){
					//comparo el dslam del ADSL de Baja con el dslam ADSL de ALTA
					InformacionAdslDTO informacionAdslActualAlta= recursosBAInterface.obtenerDatosActualAdsl ( traslado_solobaLocal.getPeti_alta() );
					InformacionAdslDTO informacionAdslNuevaAlta = recursosBAInterface.obtenerDatosAdsl( traslado_solobaLocal.getPeti_alta() );
					if(informacionAdslNuevaAlta!=null){//Si configure ADSL es pk cambie el dslam. deberian ser distinto 
						if (informacionAdslNuevaAlta.getDirecIpDslam().equals(informacionAdslActual.getDirecIpDslam())){
							esTrasladoIgualDslam=true;
						}
					}else if(informacionAdslActualAlta!=null){//si no tiene nueva es pk no hizo la configuracion internek
						//Deberian ser iguales los dslam 
						if (informacionAdslActualAlta.getDirecIpDslam().equals(informacionAdslActual.getDirecIpDslam())){
							esTrasladoIgualDslam=true;
						}
					}
				}else{
					log.debug("No es traslado y no se puede comparar dslam. Se realiza la actividad actual.");
				}
			}
		}
		} catch (ATiempoAppEx e){
			log.warn("Error en la comparacion de Dslam en la actividad Cambio de Numero",e);
			
		} catch (NamingException e) {
			log.warn("Error en la comparacion de Dslam en la actividad Cambio de Numero",e);
			
		}
		return esTrasladoIgualDslam;
	}
	/*Correcion defecto -- Pablo L 2/12/2008*/
	public boolean envioCambioNro(ActividadEJBDTO act)throws TnProcesoExcepcion{
			boolean envioCambio = false;
			String tipoTraslado = "";
			try{
				
				/*CR 26475-- Juan Pereyra 24/06/2009 se agrego el if para cuiando sea traslado solo ba mande el cambio de numero si o si*/
				if(esTrasladoSoloBA(act.getNumeroPeticion())){
			        return true;
			    }else{
				RecursosInterfaces recSTB = new RecursosDelegate();
				InformacionTecnicaDTO infSTB=recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
			
				PeticionKey pkey = new PeticionKey(act.getNumeroPeticion());
				PeticionLocalHome pLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal plocal = pLocalHome.findByPrimaryKey(pkey);
			
				//verifico si se cambió el numero
				boolean cambioNro = false;
				String lExistente = null;
				lExistente = plocal.getIdentificadorOriLinea();
				String newnum = infSTB.LineaNueva.getTelefono();
				
				
				if(infSTB.LineaNueva!=null && lExistente != null){		
					if(!infSTB.LineaNueva.getTelefono().equals(lExistente)){
						cambioNro = true;
					}
				}
			
				//valido si es transferencia tecnica nueva generacion		
				if(plocal.getTica_id().equals("T")){
					//verifico si se cambió el nro
					if(cambioNro)
						envioCambio = true;
				}else{
//					veo si es traslado solo BA
					boolean esTraslado = esTrasladoBa(act.getNumeroPeticion());
					log.debug("esTraslado : "+ esTraslado);
					if(esTraslado){
						boolean dslamIguales = dslamIguales(act);
						if(dslamIguales && cambioNro)
						envioCambio = true;
					}else if(infSTB.LineaNueva!=null && infSTB.LineaExistente != null){
						//Es cambio de Numero
					    log.debug("infSTB.LineaNueva : "+ infSTB.LineaNueva);
					    log.debug("infSTB.LineaExistente : "+ infSTB.LineaExistente);
						if(cambioNro)
							envioCambio = true;
					}	
				}
				return envioCambio; 
			    }
			}catch(ATiempoAppEx e){
				log.warn("Error en Actividad Cambio Numero BA",e);
				throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
			}catch (NamingException e){
				log.warn("Error en Actividad Cambio Numero BA",e);
				throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
			}catch (FinderException e){
				log.warn("Error en Actividad Cambio Numero BA",e);
				throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
			}
		
			
		}
	
	// CR24166- 24 marzo 2009  
	private boolean esTrasladoSoloBA(Long peticion)throws ATiempoAppEx, TnProcesoExcepcion, NamingException {
		try{
			Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			Traslado_solobaLocal traslado_solobaLocal=null;
			traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(peticion);
			log.debug("Es traslado solo BA");
			return true;
		} catch (FinderException e) {
			log.debug("Es traslado LB y BA");	  
		}
		
		return false;
	}
	
	
	public boolean envioCambioNroSigres(ActividadEJBDTO act)throws TnProcesoExcepcion{
		boolean envioCambio = false;
		String tipoTraslado = "";
		try{
			RecursosInterfaces recSTB = new RecursosDelegate();
			InformacionTecnicaDTO infSTB=recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
		
			PeticionKey pkey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocalHome pLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal plocal = pLocalHome.findByPrimaryKey(pkey);
			Collection PSPCollection = plocal.getProducto_servicio_peticion();
			//Se valida si viene solo VOIP
			if(PSPCollection.size()== 1){
				for(Iterator iter = PSPCollection.iterator();iter.hasNext();){
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) iter.next();
					Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) pspLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					if(familia_producto_servicioKey.faps_id.intValue() == familiaBandaAnchaNaked)
						return true;
				}
			}
		
		    if(esTrasladoSoloBA(act.getNumeroPeticion())){
		        return true;
		    }else{
				//verifico si se cambió el numero
				boolean cambioNro = false;
				String lExistente = null;
				lExistente = plocal.getIdentificadorOriLinea();
				String newnum = infSTB.LineaNueva.getTelefono();
				
				
				if(infSTB.LineaNueva!=null && lExistente != null){		
					if(!infSTB.LineaNueva.getTelefono().equals(lExistente)){
						cambioNro = true;
					}
				}
			
				//valido si es transferencia tecnica nueva generacion		
				if(plocal.getTica_id().equals("T")){
					//verifico si se cambió el nro
					if(cambioNro)
						envioCambio = true;
				}else{
	//				veo si es traslado solo BA
					boolean esTraslado = esTrasladoBa(act.getNumeroPeticion());
					log.debug("esTraslado : "+ esTraslado);
					if(esTraslado){
					//AT-2535 - Corrección de la actividad modificar identificador operadora para los casos de Traslado LB y LB+BA
					
					    boolean esTrasladoIgualZona = false;
					    InformacionAdslDTO informacionAdslActual = null;
					    informacionAdslActual = obtenerDatosActualAdsl( act.getNumeroPeticion() );
						//Si no tengo IP del Dslam es como si no tuviera nada
						if (informacionAdslActual==null || informacionAdslActual.getCodZonaAtend()==null || informacionAdslActual.getCodZonaAtend().equals("") ){
							informacionAdslActual=null;
						}else
						{
							log.debug("Dslam ADSL:" + informacionAdslActual.getCodZonaAtend());
						}
					//RecursosInterfaces recSTB = new RecursosDelegate();
				//	InformacionTecnicaDTO infSTB = recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
					//si es traslado BA, Comparo los Dslam del linea nueva con el Dslam ADSL de origen
					if (infSTB.LineaNueva!= null){
						 if(informacionAdslActual!= null){
							for (Iterator iter=infSTB.LineaNueva.getZonas().iterator();iter.hasNext();){
								Dslam1 zonasLocal = (Dslam1)iter.next();
								log.debug("Zona Rec Linea:" + zonasLocal.getIp());
								if (zonasLocal.getIp().equals(informacionAdslActual.getCodZonaAtend())){
									esTrasladoIgualZona=true;
									break;
								}
							}
						 }else{
						     log.warn("Error en Actividad Configurar Internet Traslado Sigres. No se encontro Zona Origen");
							 throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Traslado de Sigres. No se encontro Zona Origen");
						 }
					}

					    				    
						if(cambioNro)//&& esTrasladoIgualZona)
						envioCambio = true;
					}else if(infSTB.LineaNueva!=null && infSTB.LineaExistente != null){
						//Es cambio de Numero
					    log.debug("infSTB.LineaNueva : "+ infSTB.LineaNueva);
					    log.debug("infSTB.LineaExistente : "+ infSTB.LineaExistente);
						if(cambioNro)
							envioCambio = true;
					}	
				}
				return envioCambio; 
		    }
		}catch(ATiempoAppEx e){
			log.warn("Error en Actividad Cambio Numero BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		}catch (NamingException e){
			log.warn("Error en Actividad Cambio Numero BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		}catch (FinderException e){
			log.warn("Error en Actividad Cambio Numero BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		}
	
		
	}
		
		
		public boolean enviarCambioNumeroBA(String peticion, String  id_actividad ) throws ATiempoAppEx{
			
			boolean esEnviado=true;
			try{
			
				validaHome ();
		
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
//				
//				// Proceso para la validacion de los tipos de ps, que estan en el mensaje
				StringBuffer append = new StringBuffer();
				boolean bip = true;
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				Producto_servicioKey productoServicoKey = null;
//	
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
	
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
	
						if(bip){
							append.append(productoServicoKey.ps_id);
							bip = false;
						}else{
							append.append(" ,"+ productoServicoKey.ps_id .toString());
						}
	
				}
					
//				//Inicio CR-12780
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				Traslado_solobaLocal traslado_solobaLocal=null;
				try{
				   traslado_solobaLocal = traslado_solobaLocalHome.findPetiTraslado(new Long(peticion));
				} catch (FinderException e) {
					log.debug("No es un Traslado");
				}

				// INCIO CR-11825 
				String phoneNumberAsg="";
				if(traslado_solobaLocal != null){							
					PeticionKey peticionKeyTras = new PeticionKey(traslado_solobaLocal.getPeti_alta());
					PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					PeticionLocal peticionLocalTras = peticionLocalHome.findByPrimaryKey(peticionKeyTras);							
					phoneNumberAsg = String.valueOf(peticionLocalTras.getNum_ide_nu_stb());
				}else{
					Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
					Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(new Long(peticion));
					phoneNumberAsg = linea_basicaLocal.getTelefono_asg().toString();					 
				}
				// Fin CR-11825	
//					
//					
//			
//
//			    Collection colConect2 = peticionLocal.getRecursos_linea_basica () ;
//        
//			    if (colConect2.size () != 1)
//			    {
//				    String error = "La peticion " + peticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
//				    throw new ATiempoAppEx (error) ;
//			    }
//			   
//				Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
//				
//				//String phoneNumberAsg = String.valueOf(recursosStb.getTelefono_asg()); TODO CR-12780
				String phoneNumberAnt = peticionLocal.getIdentificadorOriLinea();					
//				//Si no hay numeros, envio una excepcion pk no tengo nada que cambiar!!
//				if(phoneNumberAsg==null || "".equals(phoneNumberAsg.trim()) || phoneNumberAnt==null || "".equals(phoneNumberAnt.trim()))
//				{
//					throw new ATiempoAppEx("No hay numeros para hacer el cambio de numero",ATiempoAppEx.EXCEPTION);
//				}
				
				//Preparo el envio del mensage ------------------------------------------
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				TR015E tr015e = new TR015E();
				tr015e.setId(String.valueOf(IdCorrelativo));
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				tr015e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
				
				if (phoneNumberAsg!=null && !"".equals(phoneNumberAsg.trim())){
					if (phoneNumberAsg.length()>8){ 
						phoneNumberAsg=phoneNumberAsg.substring(0,8);
					}
					tr015e.setActualPhone(Integer.parseInt(phoneNumberAsg));						
				}			
				
				if (phoneNumberAnt!=null && !"".equals(phoneNumberAnt.trim())){
					if (phoneNumberAnt.length()>8){ 
						phoneNumberAnt=phoneNumberAnt.substring(0,8);
					}
					tr015e.setPreviousPhone(Integer.parseInt(phoneNumberAnt));
				}			
		
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				//TODO: Verificar el Conector Correcto
				mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));
				mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);

				int areaPhone= 0;
				int numeroPhone= 0;
				if (phoneNumberAsg.length()>1){
					areaPhone=Integer.parseInt(phoneNumberAsg.substring(0,1));
					numeroPhone=Integer.parseInt(phoneNumberAsg.substring(1));
				}
				mensajeEstadoBALocal.setArea(new Integer(areaPhone));
				mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));

				CambiarNumeroBAMQ  cambiarNumeroBAMQ = new CambiarNumeroBAMQ();
				cambiarNumeroBAMQ.enviarMensaje(tr015e);
			
			
		
			} catch (NumberFormatException e) {
				log.warn("NumberFormatException",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

			} catch (CreateException e) {
				log.warn("CreateException",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		
			} catch (FinderException e) {
				log.warn("FinderException",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

			}
			catch(Exception e)
			{
				log.debug("Exception",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			} 
			return esEnviado;

		}
		
		//******************************************************************************************************
		
	public void recepcionCambioNumeroBA(TR015S tr015s) throws ATiempoAppEx
	{
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
		try
		{
			bExecution = BackendTraceUtil.initExecution(tr015s, log);
			bExecution.setNumPetAtiempo(new Long(tr015s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr015s.getId());
			bExecution.startOperation();
	
			validaHome ();
	
			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
	
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr015s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;
	
			try
			{
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			}
			catch (FinderException fex)
			{
				mensajeEstadoBaLocal = null ;
			}
   
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null)
			{
				log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr015s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr015s.getId(),ATiempoAppEx.EXCEPTION);
			}
	
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;
	
			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual)
			{
				log.debug("El estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr015s));
				return;
			}
	
			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
	
	
			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();
	
			if ( recursosLineaBaBasica.size() == 0){
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			}else{
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}
	
			boolean errorCode = false;
			if (tr015s.getErrorCode()!= null && !"".equals(tr015s.getErrorCode()) && !"0".equals(tr015s.getErrorCode())){
				errorCode=true;
			}
		
			if (tr015s.isError() != true && !errorCode){
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				recursos_baLocal.setCod_error(new Integer(String.valueOf(tr015s.getErrorCode())));
				recursos_baLocal.setDesc_error(tr015s.getErrorMessage());
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
			}

			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();

			if (tr015s.isError() == true || errorCode)
			{
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));

				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto =  actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				String errorCodeMsg = "";
				String code="";
				if(tr015s.getErrorCode()!=null)
					code=tr015s.getErrorCode();
				if (tr015s.getErrorCodeMessage()!=null)
					errorCodeMsg=tr015s.getErrorCodeMessage() + ".";
				if (tr015s.getErrorMessage()!=null)
					errorCodeMsg=tr015s.getErrorMessage();
				actDto.setObservacion("Error en el Cambio de Numero BA. "+code+"." + errorCodeMsg);
				
//				agregado por agonz
				//PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
				String codError = String.valueOf(tr015s.getErrorCode());
				String nombreClase=TR015S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				String destino = VpistbbaConfig.getVariable("IDPGI");
				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR014S.class.getName());
				 if(errorLegado != null){
				 	 if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
				 	 	actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr015s.getErrorMessage());
						actividadEJB.terminar(actDto);
						return;
					 }
					 peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					 destino = errorLegado.getPlataforma();
					 insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else {
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
//				fin agregado
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, destino);
				//insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				actividadEJB.terminar(actDto);
				return;
			}
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
 
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto =  actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			actividadEJB.terminar(actDto);
		}
		catch (NumberFormatException e)
		{
			bExecution.setErrorOp(true);
					log.warn("NumberFormatException",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		}
		catch (CreateException e)
		{
			bExecution.setErrorOp(true);
				log.warn("CreateException",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		}
		catch (FinderException e)
		{
			bExecution.setErrorOp(true);
				log.warn("FinderException",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.warn("TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
	}
		
	}
		
	public void enviarConfiguracionActualBA(String telefonoConsulta, String peticion, String id_actividad) throws ATiempoAppEx{
			
		try
		{
			
			validaHome ();

			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

			boolean esRefrescar=false;
			boolean esRefrescarNew=false;
			if(id_actividad.equals(""))
			{
				esRefrescar=true;
				esRefrescarNew=false;
				id_actividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CONF_BA");
			}else if(id_actividad.equals("RN")){
				esRefrescar=false;
				esRefrescarNew=true;
				id_actividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CONF_BA");
			}

			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();

			// Proceso para la validacion de los tipos de ps, que estan en el mensaje
			//StringBuffer append = new StringBuffer();
//			boolean bip = true;
//			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
//			Producto_servicioKey productoServicoKey = null;
//
//			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
//
//				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
//				productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
//
//					if(bip){
//						append.append(productoServicoKey.ps_id);
//						bip = false;
//					}else{
//						append.append(" ,"+ productoServicoKey.ps_id .toString());
//					}
//
//			}
			//Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR014E tr014e = new TR014E();
			tr014e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			tr014e.setAtisRequestNumber(peticionKey.peti_numero.longValue());

			//Telefono consulta por defecto (num_ide_nu)
//			String phoneNumber = peticionLocal.obtenerCPINAGrupacionOriginal();
			String phoneNumber = peticionLocal.getIdentificadorOriLinea();			
			//si viene un telefono, se consulta por ese telefono y se asocia a la peticion
			if (telefonoConsulta != null && !"".equals(telefonoConsulta.trim()))
			{
				phoneNumber = telefonoConsulta;							
			}
			if (phoneNumber!=null && phoneNumber.length() > 0){
				if (phoneNumber.length()>8) 
					phoneNumber=phoneNumber.substring(0,8);
					//Seteo del TR en caso que exista el numero
				tr014e.setPhoneNumber(Integer.parseInt(phoneNumber));
			}			
			else{
				phoneNumber="";
			}	
		
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
			//mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaPcBA));
			
			//TODO: Verificar el Conector Correcto
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));
			
			if(esRefrescar){
				mensajeEstadoBALocal.setAccion("R");
			}else if(esRefrescarNew){
				mensajeEstadoBALocal.setAccion("RN");
			}
			
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);

			int areaPhone= 0;
			int numeroPhone= 0;
			if (phoneNumber.length()>1){
				areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				numeroPhone=Integer.parseInt(phoneNumber.substring(1));
			}
			mensajeEstadoBALocal.setArea(new Integer(areaPhone));
			mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));

			SolicitudConfiguracionBAMQ  configuracionActualBAMQ = new SolicitudConfiguracionBAMQ();
			configuracionActualBAMQ.enviarMensaje(tr014e);
		}
		catch (NumberFormatException e)
		{
			log.warn("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		}
		catch (CreateException e)
		{
			log.warn("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		}
		catch (FinderException e)
		{
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} 
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}		
	}
		
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx
	{	
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
		try
		{		
			bExecution = BackendTraceUtil.initExecution(tr014s, log);
			bExecution.setNumPetAtiempo(new Long(tr014s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr014s.getId());
			bExecution.startOperation();
				
			validaHome ();

			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr014s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			try
			{
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			}
			catch (FinderException fex)
			{
				mensajeEstadoBaLocal = null ;
			}
   
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null)
			{
				log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr014s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr014s.getId(),ATiempoAppEx.EXCEPTION);
			}

			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual)
			{
				log.debug("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr014s));
				return;
			}

			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

			if ( recursosLineaBaBasica.size() == 0){
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			}else{
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}

			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
			
			boolean errorCode = false;
			if (tr014s.getErrorCode()!= null && !"".equals(tr014s.getErrorCode()) && !"0".equals(tr014s.getErrorCode())){
				errorCode=true;
			}
					
			if (tr014s.isError() != true && !errorCode)
			{
				log.debug("No tengo Error en el mensaje");
		
				//Parte Comun
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				try
				{
					if(tr014s.getErrorCode()!=null)
					{
						recursos_baLocal.setCod_error(new Integer(String.valueOf(tr014s.getErrorCode())));
					}else
					{
						recursos_baLocal.setCod_error(new Integer(0));
					}
				}
				catch(NumberFormatException nf)
				{
					recursos_baLocal.setCod_error(new Integer(0));
				}
				recursos_baLocal.setDesc_error(tr014s.getErrorMessage());
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);				
				
				// Inicio GMarcone CR25839
				
				ArrayList listaModems=new ArrayList();
				ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
				
				modemVpiDTO.setSerial(tr014s.getModemSerial());
				modemVpiDTO.setAccion((short)ComunInterfaces.accionModemLiberar);				
				
				modemVpiDTO.setMarca(tr014s.getModemBrand());
				modemVpiDTO.setModelo(tr014s.getModemModel());
				modemVpiDTO.setTipo((short)tr014s.getModemType());	
				
				/*RQ.8595 - mfmendez*/
				modemVpiDTO.setPostingDateSAP(tr014s.getModemPostingDateSAP());
				modemVpiDTO.setMoveTypeSAP(tr014s.getModemMoveTypeSAP());
				modemVpiDTO.setMaterialCodeSAP(tr014s.getModemMaterialCodeSAP());
				modemVpiDTO.setMaterialSAP(tr014s.getModemMaterialSAP());
				modemVpiDTO.setPlantSAP(tr014s.getModemPlantSAP());
				modemVpiDTO.setStorageSAP(tr014s.getModemStorageSAP());
				modemVpiDTO.setBatchCodeSAP(tr014s.getModemBatchCodeSAP());
				modemVpiDTO.setMeasurementUnitSAP(tr014s.getModemMeasurementUnitSAP());
				modemVpiDTO.setCostCenterSAP(tr014s.getModemCostCenterSAP());
				modemVpiDTO.setFuncAreaLongSAP(tr014s.getModemFuncAreaLongSAP());
				modemVpiDTO.setPepElementSAP(tr014s.getModemPepElementSAP());
				modemVpiDTO.setFlagMatSAP(tr014s.getModemFlagMatSAP());
				/*FIN - RQ.8595 - mfmendez*/
				
				listaModems.add(modemVpiDTO);				

				grabaModemsBaVpi(new Long(tr014s.getAtisRequestNumber()),mensajeEstadoBaLocal.getTelefono(),listaModems);
				
							
				
				/*
				//NuevoFatherEmail
				if(tr014s.getFatherEmail()!=null){
					recursos_baLocal.setFather_email_nuevo(tr014s.getFatherEmail());
				}				
				mensajeEstadoBaLocal.setTelefono(new Long(tr014s.getPhoneNumber()));
//				mensajeEstadoBaLocal.getPeticion().setNum_ide_nu_stb(String.valueOf(tr014s.getPhoneNumber()));

				//Si se trata de refrescar la data NEW
				if(mensajeEstadoBaLocal.getAccion()!=null && "RN".equals(mensajeEstadoBaLocal.getAccion()))
				{
					recursos_baLocal.setAdsl_nuevo(tr014s.getAdsl());
					recursos_baLocal.setDir_ip_dslam_nueva(tr014s.getDslamIp());
					recursos_baLocal.setDir_ip_wan_nueva(tr014s.getWanIp());
					recursos_baLocal.setDir_ip_lan_nueva(tr014s.getLanIp());
					recursos_baLocal.setFrame_nuevo(tr014s.getFrame());
					recursos_baLocal.setMasc_lan_nueva(tr014s.getMask());
					recursos_baLocal.setPost_nuevo(tr014s.getPots());
					recursos_baLocal.setPuerto_nuevo(tr014s.getPort());
					recursos_baLocal.setSlot_nuevo(tr014s.getSlot());
					recursos_baLocal.setVpivci_nuevo(tr014s.getVpiVciClient());
					recursos_baLocal.setVpivci_red_nuevo(tr014s.getVpiVciNetwork());
				}else
				{
					recursos_baLocal.setAdsl_actual(tr014s.getAdsl());
					recursos_baLocal.setDir_ip_dslam_actual(tr014s.getDslamIp());
					recursos_baLocal.setDir_ip_wan_actual(tr014s.getWanIp());
					recursos_baLocal.setDir_ip_lan_actual(tr014s.getLanIp());
					recursos_baLocal.setFrame_actual(tr014s.getFrame());
					recursos_baLocal.setMasc_actual(tr014s.getMask());
					recursos_baLocal.setPost_actual(tr014s.getPots());
					recursos_baLocal.setPuerto_actual(tr014s.getPort());
					recursos_baLocal.setSlot_actual(tr014s.getSlot());
					recursos_baLocal.setVpivci_actual(tr014s.getVpiVciClient());
					recursos_baLocal.setVpivci_red_actual(tr014s.getVpiVciNetwork());
				}
				FIN GMarcone CR25839*/
			}
			else
			{
				log.debug("Tengo un error en el mensaje");
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));

				if(mensajeEstadoBaLocal.getAccion()!=null && (mensajeEstadoBaLocal.getAccion().equals("R")||mensajeEstadoBaLocal.getAccion().equals("RN")))
					return;
					
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				String errorCodeMsg = "";
				String code="";
				
				if(tr014s.getErrorCode()!=null)
					code=tr014s.getErrorCode();
					
				if (tr014s.getErrorCodeMessage()!=null)
				{
					errorCodeMsg+=tr014s.getErrorCodeMessage() + ".";
				}
				if (tr014s.getErrorMessage()!=null){
					errorCodeMsg+="."+tr014s.getErrorMessage()+ ".";
				}
				
//				agregado por agonz
				PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
				String codError = String.valueOf(tr014s.getErrorCode());
				String nombreClase=TR014S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				String destino = VpistbbaConfig.getVariable("IDPGI");
				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR014S.class.getName());
				 if(errorLegado != null){
				 	 if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
				 	 	actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr014s.getErrorMessage());
						actividadEJB.terminar(actDto);
						return;
					 }
					 peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					 destino = errorLegado.getPlataforma();
					 insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else {
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
//				fin agregado
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, destino);
				actDto.setObservacion("Error en la Obtencion Configuracion BA. Code:"+code+"." + errorCodeMsg+".");
				actividadEJB.terminar(actDto);
				return;
			}

			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			if(mensajeEstadoBaLocal.getAccion()!=null && (mensajeEstadoBaLocal.getAccion().equals("R") || mensajeEstadoBaLocal.getAccion().equals("RN")))
				return;
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());

				//actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
			actividadEJB.terminar(actDto);

		}
		catch (NumberFormatException e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.error("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		}
		catch (CreateException e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.error("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		}
		catch(FinderException e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();	
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		}
		catch (TnProcesoExcepcion e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} 
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.debug("EXCEPTION",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
	}

	}

	public InformacionAdslDTO obtenerDatosAdsl(Long idPeticion) throws ATiempoAppEx
	{
		InformacionAdslDTO infoAdslDto = null;
		try
		{
			validaHome();
	
			Recursos_baLocal rbLocal = null;
	
			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);
	
			Collection c = pLocal.getRecursos_ba();
			
//			if ( c==null || c.size()==0 )
//				return null;
			//Obtenemos los datos Tecnico de ADSL, de la tabla recursos_ba
			
			if(c!=null && c.size() > 0 ){
				for (Iterator iter = c.iterator(); iter.hasNext();){
					
					rbLocal = (Recursos_baLocal) iter.next();
				}
				
				//Si no hay dslam actual, es pk no hay recursos actuales(origen)
				if(rbLocal.getDir_ip_dslam_actual()==null && rbLocal.getCod_zona_atend()==null)
				{
					return null;
				}else if(rbLocal.getDir_ip_dslam_actual()!=null && rbLocal.getDir_ip_dslam_actual().trim().equals("") &&
				        (rbLocal.getCod_zona_atend()!=null&&rbLocal.getCod_zona_atend().trim().equals(""))){
					return null;
				}
				
				infoAdslDto=new InformacionAdslDTO();
				//log.debug("Obtencion de datos de ADSL (Recursos_ba)");
				infoAdslDto.setAdsl(rbLocal.getAdsl_nuevo());
				//TODO: Ver donde obtener el dato CNA infoAdslDto.setCna( rbLocal.get() );
				//Para que seteo una descripcion del error? eso va en los quiebres
//					infoAdslDto.setDescripcion(rbLocal.getDesc_error());
				infoAdslDto.setDirecIpLan(rbLocal.getDir_ip_lan_nueva());
				infoAdslDto.setDirecIpDslam(rbLocal.getDir_ip_dslam_nueva());
				infoAdslDto.setDirecIpWan(rbLocal.getDir_ip_wan_nueva());
				infoAdslDto.setFrame(rbLocal.getFrame_nuevo());
				infoAdslDto.setMascaraLan(rbLocal.getMasc_lan_nueva());
				infoAdslDto.setPost(rbLocal.getPost_nuevo());
				infoAdslDto.setPuerto(rbLocal.getPuerto_nuevo());
				infoAdslDto.setSlot(rbLocal.getSlot_nuevo());
				infoAdslDto.setVpiVciCliente(rbLocal.getVpivci_nuevo());
				infoAdslDto.setVpiVciRed(rbLocal.getVpivci_red_nuevo());
				infoAdslDto.setFatherEmail(rbLocal.getFather_email_nuevo());
				
				//CR4860 - Sigres - guido - 21/May - Inicio
				infoAdslDto.setOdsSigres(rbLocal.getOds_sigres());
				infoAdslDto.setCodZonaAtend(rbLocal.getCod_zona_atend());
				//CR4860 - Sigres - guido - 21/May - FIN

				/*
				 *	CR - 00026148 - Jun 29, 2009
				 *		Se setean los campos de Serial Marca Modelo y Tipo de Modem - German P.
				 */
				infoAdslDto.setModem_marca(rbLocal.getModem_marca());
				infoAdslDto.setSerial(rbLocal.getSerial());
				infoAdslDto.setModelo(rbLocal.getModelo());
				String dsTipoModem = "Convencional";
				Long psAlta = null;
				try{
					psAlta = pLocal.getCod_pet_pdr_cd();
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
					Integer tipoModem = new Integer(0);
					if (ps_Tipo_ModemLocal!= null){
				        tipoModem = new Integer(ps_Tipo_ModemLocal.getId_tipo_modem().intValue());
				        Tipo_ModemLocal tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(tipoModem);
				        if(tipo_ModemLocal != null){
				        	dsTipoModem = tipo_ModemLocal.getDesc_tipo();
				        }
				    }
				}catch(FinderException e){
					log.debug("No se ha encontrado el Tipo de Modem: " + psAlta);
				}
				infoAdslDto.setTipoModem(dsTipoModem);
			}

			
			for(Iterator pss = pLocal.getProducto_servicio_peticion().iterator(); pss.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) pss.next();
				Subpeticion_atisLocal subPetLocal = producto_servicio_peticionLocal.getFk_01_subp_atis();
				for (Iterator  carac = subPetLocal.getSubpeticion_caracteristicas().iterator(); carac.hasNext();)
				{
					Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)carac.next();
					Subpeticion_caracteristicasKey subpeticion_caracteristicasKey  = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
					//log.debug("Obetencion de datos de ADSL (Subpeticion_caracteristicas)");
					//Obtenemos los datos para ADSL desde la tabla Subpeticion_caracteristicas
					//Los codigos de caracteristicas se encuentran en configVpiStbBa.properties
					if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMPC")).intValue())
					{
						infoAdslDto.setNumPc(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMTARJ")).intValue())
					{
						infoAdslDto.setNumTarj(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMUSB")).intValue())
					{
						infoAdslDto.setNumUsb(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("SISTOP")).intValue())
					{
						infoAdslDto.setSistOp(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("USUACC")).intValue())
					{
						infoAdslDto.setUsuarioAcc(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DISDUR")).intValue())
					{
						infoAdslDto.setDiscoDuro(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("RAM")).intValue())
					{
						infoAdslDto.setRam(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DIRECCELECTRONICA")).intValue())
					{
						log.debug("hay direccion electronica");
						String dirElec= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dirElec = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
							log.debug("Es :"+dirElec);
						}
						infoAdslDto.setDireccElectronica(dirElec);
						if (dirElec.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					// CR24519 - adocarmo - inicio
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("TIPOSVA")).intValue())
					{
						String tiposVA= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							tiposVA = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setTipoSVA(tiposVA);
						if (tiposVA.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO1")).intValue())
					{
						String dominio1= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio1 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio1(dominio1);
						if (dominio1.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO2")).intValue())
					{
						String dominio2= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio2 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio2(dominio2);
						if (dominio2.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO3")).intValue())
					{
						String dominio3= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio3 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio3(dominio3);
						if (dominio3.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}					
					// CR24519 - adocarmo - fin
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("RANGOHORA")).intValue())
					{
						infoAdslDto.setRangoHora(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("MOTIVOVISITA")).intValue())
					{
						infoAdslDto.setMotivoVisita(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
				}
			}
		}
		catch(FinderException e)
		{
			//log.debug("FinderException",e);
			return null;
		}
		catch (Exception e)
		{
			//log.debug("FinderException",e);
			return null;
		}
		return infoAdslDto;
	
	}
	
	
	public InformacionAdslDTO obtenerDatosActualAdsl(Long idPeticion) throws ATiempoAppEx
	{
		InformacionAdslDTO infoAdslDto = null;
		try
		{
			validaHome();

			Recursos_baLocal rbLocal = null;

			PeticionKey pKey = new PeticionKey(idPeticion);
			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(pKey);

			Collection c = pLocal.getRecursos_ba();
				
//			if ( c==null || c.size()==0 )
//				return null;
			//Obtenemos los datos Tecnico de ADSL, de la tabla recursos_ba
			
			if(c!=null && c.size() > 0 ){
				log.debug("hay recursos BA");
			//Obtenemos los datos Tecnico de ADSL, de la tabla recursos_ba
			for (Iterator iter = c.iterator(); iter.hasNext();){
			
				rbLocal = (Recursos_baLocal) iter.next();
			}

			//Si no hay dslam actual, es pk no hay recursos actuales(origen)
			if(rbLocal.getDir_ip_dslam_actual()==null && rbLocal.getCod_zona_atend()==null)
			{
				return null;
			}else if(rbLocal.getDir_ip_dslam_actual()!=null && rbLocal.getDir_ip_dslam_actual().trim().equals("") &&
			        (rbLocal.getCod_zona_atend()!=null&&rbLocal.getCod_zona_atend().trim().equals(""))){
			    return null;
			}
			
			infoAdslDto=new InformacionAdslDTO();
			//log.debug("Obetencion de datos de  Actual ADSL (Recursos_ba)");
			infoAdslDto.setAdsl(rbLocal.getAdsl_actual());
			//TODO: Ver donde obtener el dato CNA infoAdslDto.setCna( rbLocal.get() );
			//infoAdslDto.setDescripcion(rbLocal.getDesc_error());
			infoAdslDto.setDirecIpDslam(rbLocal.getDir_ip_dslam_actual());
			infoAdslDto.setDirecIpLan(rbLocal.getDir_ip_lan_actual());					
			infoAdslDto.setDirecIpWan(rbLocal.getDir_ip_wan_actual());
			infoAdslDto.setFrame(rbLocal.getFrame_actual());
			infoAdslDto.setMascaraLan(rbLocal.getMasc_actual());//agonz-22577
			infoAdslDto.setPost(rbLocal.getPost_actual());
			infoAdslDto.setPuerto(rbLocal.getPuerto_actual());
			infoAdslDto.setSlot(rbLocal.getSlot_actual());
			infoAdslDto.setVpiVciCliente(rbLocal.getVpivci_actual());
			infoAdslDto.setVpiVciRed(rbLocal.getVpivci_red_actual());
			infoAdslDto.setFatherEmail(rbLocal.getFather_email_nuevo());
			//CR4860 - Sigres - guido - 21/May - Inicio
			infoAdslDto.setOdsSigres(rbLocal.getOds_sigres());
			infoAdslDto.setCodZonaAtend(rbLocal.getCod_zona_atend());
			//CR4860 - Sigres - guido - 21/May - FIN
			
			/*
			 *	CR - 00026148 - Jun 29, 2009
			 *		Se setean los campos de Serial Marca Modelo y Tipo de Modem - German P.
			 */
			infoAdslDto.setSerial(rbLocal.getSerial());
			infoAdslDto.setModem_marca(rbLocal.getModem_marca());
			infoAdslDto.setModelo(rbLocal.getModelo());
			
			String dsTipoModem = "Convencional";
			Long psAlta = null;
			try{
				psAlta = pLocal.getCod_pet_pdr_cd();
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				Integer tipoModem = new Integer(0);
				if (ps_Tipo_ModemLocal!= null){
			        tipoModem = new Integer(ps_Tipo_ModemLocal.getId_tipo_modem().intValue());
			        Tipo_ModemLocal tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(tipoModem);
			        if(tipo_ModemLocal != null){
			        	dsTipoModem = tipo_ModemLocal.getDesc_tipo();
			        }
			    }
			}catch(FinderException e){
				log.debug("No se ha encontrado el Tipo de Modem: " + psAlta);
			}
			infoAdslDto.setTipoModem(dsTipoModem);
			}
			for(Iterator pss = pLocal.getProducto_servicio_peticion().iterator(); pss.hasNext();)
			{			
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) pss.next();
				Subpeticion_atisLocal subPetLocal =producto_servicio_peticionLocal.getFk_01_subp_atis(); 
				for (Iterator  carac = subPetLocal.getSubpeticion_caracteristicas().iterator(); carac.hasNext();)
				{				
					Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)carac.next();
					Subpeticion_caracteristicasKey subpeticion_caracteristicasKey  = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
//						log.debug("Obetencion de datos de ADSL (Subpeticion_caracteristicas)");
					//Obtenemos los datos para ADSL desde la tabla Subpeticion_caracteristicas
					//Los codigos de caracteristicas se encuentran en configVpiStbBa.properties
					if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMPC")).intValue())
					{
						infoAdslDto.setNumPc(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMTARJ")).intValue())
					{
						infoAdslDto.setNumTarj(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("NUMUSB")).intValue())
					{
						infoAdslDto.setNumUsb(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("SISTOP")).intValue())
					{
						infoAdslDto.setSistOp(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("USUACC")).intValue())
					{
						infoAdslDto.setUsuarioAcc(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DISDUR")).intValue())
					{
						infoAdslDto.setDiscoDuro(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("RAM")).intValue())
					{
						infoAdslDto.setRam(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DIRECCELECTRONICA")).intValue())
					{
						log.debug("hay direccion electronica");
						String dirElec= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dirElec = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
							log.debug("Es "+dirElec);
						}
						infoAdslDto.setDireccElectronica(dirElec);
						if (dirElec.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}						}
					}
					
					// CR24519 - adocarmo - inicio
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("TIPOSVA")).intValue())
					{
						String tiposVA= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							tiposVA = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setTipoSVA(tiposVA);
						if (tiposVA.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO1")).intValue())
					{
						String dominio1= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio1 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio1(dominio1);
						if (dominio1.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO2")).intValue())
					{
						String dominio2= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio2 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio2(dominio2);
						if (dominio2.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("DOMINIO3")).intValue())
					{
						String dominio3= "";
						if (subpeticion_caracteristicasLocal.getVal_ral_crc_cd()!=null){
							dominio3 = subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString();
						}
						infoAdslDto.setDominio3(dominio3);
						if (dominio3.trim().length()>0){
						//Es CDS por lo tanto busco el ServReferenceID
							if(subPetLocal.getId_srv_ref_cd()!=null){
								infoAdslDto.setServiceReferenceId(subPetLocal.getId_srv_ref_cd());
							}
						}
					}					
					// CR24519 - adocarmo - fin
					
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("RANGOHORA")).intValue())
					{
						infoAdslDto.setRangoHora(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					else if(subpeticion_caracteristicasKey.cod_crc_cd.intValue() == new Integer(VpistbbaConfig.getVariable("MOTIVOVISITA")).intValue())
					{
						infoAdslDto.setMotivoVisita(subpeticion_caracteristicasLocal.getVal_ral_crc_cd().toString());	
					}
					
				}
			}
		}
		catch (FinderException e)
		{
			//log.debug("FinderException",e);
			return null;
		}
		catch (Exception e)
		{
			//log.debug("FinderException",e);
			return null;
		}
		
		return infoAdslDto;

	}
	
	private boolean getEstadoMultipleMensajes(Mensaje_estado_baLocal mensajeEstadoLineaLocal, Integer estado)
	{
		
		Mensaje_estado_baLocal mensaje_estado_baLocal2 = null;  
		Mensaje_estado_baKey mensaje_estado_baKey = (Mensaje_estado_baKey) mensajeEstadoLineaLocal.getPrimaryKey();
       
	   try
	   {
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();
			//Arreglo de registros de peticion pendientes
			Collection estadoMensajes = mensajeEstadoBaLocalHome.findEstadoPeticion(peticionKey.peti_numero, estado);
	
			//Si que da el 1, siempre sera el ultimo
			log.debug("El numero de mensajes en estado espera es:" + estadoMensajes.size());
			if(estadoMensajes.size() == 1){
		
				for (Iterator iter= estadoMensajes.iterator();iter.hasNext();){
			
					mensaje_estado_baLocal2 = (Mensaje_estado_baLocal) iter.next();
					Mensaje_estado_baKey mensaje_estado_baKey2 = (Mensaje_estado_baKey) mensaje_estado_baLocal2.getPrimaryKey();
				//Comparacion de corralativos de mensajes
					log.debug("Correlativo1:" + mensaje_estado_baKey2.correlativo);
					log.debug("Correlativo2:" + mensaje_estado_baKey.correlativo);
					if(mensaje_estado_baKey2.correlativo.longValue() == mensaje_estado_baKey.correlativo.longValue())
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
	}
	
	public ArrayList recuperaModemsBaVPi(Long nroPeticion)
	{
		try
		{
//			log.debug("Voy a recuperar los modems para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList retorno=new ArrayList();
			ArrayList list=new ArrayList();
			
			//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
			boolean esACP = false;
			//End TODO
			
			//TCS-gquevedo May 18, 2009 CR.23338 INICIO
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			if(peticionLocalHome==null){
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			}
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			ArrayList idsPsAlta = new ArrayList();
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
//				REQ BA NAKED adicion familia PC naked
				if(idFam==familiaPcBA || idFam==familiaBandaAncha ||idFam==familiaPcPsBANaked || idFam==familiaBandaAnchaNaked){
				    if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )  
				            && peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
				    	idsPsAlta.add(pspet.getPsId());
				    	
						//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
				    	if (opco.getOpco_tipo().equals("ACP")){
					    	esACP = true;
					    }
				    	//End TODO
				    }
				}
			}
					
			short tipoModem = 1;
			Tipo_ModemLocal tipo_ModemLocal = null;
			for(Iterator iterator = idsPsAlta.iterator();iterator.hasNext();){
				try {
				    Long psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().shortValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}
			tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(new Integer(tipoModem));
			
			//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
			if (!esACP){
				Collection collection=peticionLocal.getModem();
				for(Iterator iterator=collection.iterator();iterator.hasNext();)
				{
					//log.debug("Sacando modem");
					ModemLocal modemLocal=(ModemLocal) iterator.next();
					ModemKey modemKey=(ModemKey) modemLocal.getPrimaryKey();
					ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
					modemVpiDTO.setPeti_numero(nroPeticion);
					modemVpiDTO.setTelefono(modemLocal.getTelefono());
					modemVpiDTO.setSerial(modemKey.serial);
					modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
					modemVpiDTO.setTipo(tipoModem);
					modemVpiDTO.setDescTipo(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
					modemVpiDTO.setModelo(modemLocal.getModelo());
					/*
					 *	CR - 00026148 - Jun 25, 2009
					 *		- German P.
					 */
					modemVpiDTO.setMarca(modemLocal.getModem_marca());
					/*RQ.8595 - mfmendez*/
					modemVpiDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
					modemVpiDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
					modemVpiDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
					modemVpiDTO.setMaterialSAP(modemLocal.getNum_material_sap());
					modemVpiDTO.setPlantSAP(modemLocal.getCentro_sap());
					modemVpiDTO.setStorageSAP(modemLocal.getAlmacen_sap());
					modemVpiDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
					modemVpiDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
					modemVpiDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
					modemVpiDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
					modemVpiDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
					modemVpiDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
					/*FIN - RQ.8595 - mfmendez*/

					list.add(modemVpiDTO);
				}
			}else{
				//DAVID: Mayo 13 de 2011. Si es ACP vamos a evaluar si la petición además de tener ACP, tiene ps tipo pc de BA o ps tipo modem con OC=1. Si se cumple entonces 
				//se carga la lista de modems para que se muestre en la pestaña de ingreso de modems y deje continuar la petición.
				if(tienePsPCBAoModemWIFI(nroPeticion)){
					Collection collection=peticionLocal.getModem();
					for(Iterator iterator=collection.iterator();iterator.hasNext();)
					{
						ModemLocal modemLocal=(ModemLocal) iterator.next();
						ModemKey modemKey=(ModemKey) modemLocal.getPrimaryKey();
						ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
						modemVpiDTO.setPeti_numero(nroPeticion);
						modemVpiDTO.setTelefono(modemLocal.getTelefono());
						modemVpiDTO.setSerial(modemKey.serial);
						modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
						modemVpiDTO.setTipo(tipoModem);
						modemVpiDTO.setDescTipo(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
						modemVpiDTO.setModelo(modemLocal.getModelo());
						modemVpiDTO.setMarca(modemLocal.getModem_marca());
						/*RQ.8595 - mfmendez*/
						modemVpiDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
						modemVpiDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
						modemVpiDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
						modemVpiDTO.setMaterialSAP(modemLocal.getNum_material_sap());
						modemVpiDTO.setPlantSAP(modemLocal.getCentro_sap());
						modemVpiDTO.setStorageSAP(modemLocal.getAlmacen_sap());
						modemVpiDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
						modemVpiDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
						modemVpiDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
						modemVpiDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
						modemVpiDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
						modemVpiDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
						/*FIN - RQ.8595 - mfmendez*/
						list.add(modemVpiDTO);
					}
				}
			}
			//End TODO
			
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
			retorno.add(list);
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
//				REQ BA NAKED adicion familia PC naked
				if(familia_producto_servicioKey.faps_id.intValue()!=familiaPcBA 
						&& familia_producto_servicioKey.faps_id.intValue()!=familiaPcPsBANaked)
					continue;
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				Collection listaEstado=producto_servicio_peticionLocal.getEstado_ps_peticion();
				if(listaEstado.size()>0)
				{
					Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstado.iterator().next();
					if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError)
					{
						ArrayList otroRet=new ArrayList();
						otroRet.add(new ArrayList());
						Integer valAlta=new Integer(3);
						otroRet.add(valAlta);
						otroRet.add(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
						return otroRet;
					}
				}
				if(operacion_comercialLocal.getOpco_tipo().equals("ACP")){
					esCambioPlan = true;
				}else if(operacion_comercialLocal.getOpco_tipo().equals("A"))
				{
					esAlta=true;
					if(operacion_comercialLocal.getOpco_tras()!=null)
					{
						if(operacion_comercialLocal.getOpco_tras().equals("T"))
							esTraslado=true;
					}
				}				
			}
			Integer valAlta=null;
			if(esAlta)
			{
				if(esTraslado)
					valAlta=new Integer(2);
				else			   
					valAlta=new Integer(1);
				
			}
			else{
				//Correccion defecto Wifi 06/10/2009
				if(esCambioPlan){
				    //si es Alta de WIFI meter un 1
				    if(esWifi(nroPeticion)){
				        valAlta=new Integer(8);
				        //valAlta=new Integer(1);
				    }else{
					valAlta=new Integer(3);
				    }
				}else{
				    //si es Alta de WIFI meter un 1
				    if(esWifiBAja(nroPeticion)){
				        valAlta=new Integer(9);
				    }else{
					valAlta=new Integer(0);
			}
				}
			}
			log.debug("valAlta = "+valAlta);
			retorno.add(valAlta);
			retorno.add(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			ArrayList ret=new ArrayList();
			ret.add(new ArrayList());
			ret.add(new Integer(0));
			ret.add("Convencional");
			return ret;
		}
	}
	/**
	 * DAVID: Mayo 13 de 2011, devuelve true si tiene un ps BA tipo PC o un ps tipo modem en la petición.
	 * @param nroPeticion
	 * @return
	 */
	public boolean tienePsPCBAoModemWIFI(Long nroPeticion){
		boolean tienePsPCBAoModemWIFI=false;

		try{
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			if(peticionLocalHome==null){
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			}
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			ArrayList idsPsAlta = new ArrayList();
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
				
				boolean esPsModem=false;
				Long psId=pspet.getPsId();
				try{
					Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psId);
					esPsModem=true;
				}catch(FinderException e){
					log.debug("No es ps tipo modem...");
				}				
//				REQ BA NAKED adicion familia PC naked
				if(idFam==familiaPcBA ||idFam==familiaPcPsBANaked || esPsModem){
				    if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A"))  
				            && peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
				    	tienePsPCBAoModemWIFI=true;
				    	break;
				    }
				}
			}
		}catch(FinderException e){
			log.debug("FinderException en tienePsPCBAoModemWIFI(): "+e.toString());
		}catch(NamingException e){
			log.debug("NamingException en tienePsPCBAoModemWIFI(): "+e.toString());
		}catch(ATiempoAppEx e){
			log.debug("ATiempoAppEx en tienePsPCBAoModemWIFI(): "+e.toString());
		}
		
		return tienePsPCBAoModemWIFI;
		
	}
	/**
	 * DAVID: Método para recuperar listado de módems en el caso de una petición de autoinstalación en reversa.
	 * basado en recuperaModemsBaVPi();
	 * @param nroPeticion
	 * @return
	 */
	public ArrayList recuperaModemsBaVPiReversaAutoInst(Long nroPeticion)
	{
		try
		{
//			log.debug("Voy a recuperar los modems para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			boolean esCambioPlan = false;
			ArrayList retorno=new ArrayList();
			ArrayList list=new ArrayList();
			
			//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
			boolean esACP = false;
			//End TODO
			
			//TCS-gquevedo May 18, 2009 CR.23338 INICIO
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			if(peticionLocalHome==null){
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			}
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			ArrayList idsPsAlta = new ArrayList();
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
//				REQ BA NAKED adicion familia PC naked
				if(idFam==familiaPcBA || idFam==familiaBandaAncha
						||idFam==familiaPcPsBANaked || idFam==familiaBandaAnchaNaked){
				    if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )  
				            && peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
				    	idsPsAlta.add(pspet.getPsId());
				    	
						//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
				    	if (opco.getOpco_tipo().equals("ACP")){
					    	esACP = true;
					    }
				    	//End TODO
				    }
				}
			}
					
			short tipoModem = 1;
			Tipo_ModemLocal tipo_ModemLocal = null;
			for(Iterator iterator = idsPsAlta.iterator();iterator.hasNext();){
				try {
				    Long psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().shortValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}
			tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(new Integer(tipoModem));
			
			//TODO: RETA - 25012010 - Corrección Altas de TV con Migracion de PS de BA
			if (!esACP){
				Collection collection=peticionLocal.getModem();
				for(Iterator iterator=collection.iterator();iterator.hasNext();)
				{
					//log.debug("Sacando modem");
					ModemLocal modemLocal=(ModemLocal) iterator.next();
					ModemKey modemKey=(ModemKey) modemLocal.getPrimaryKey();
					ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
					modemVpiDTO.setPeti_numero(nroPeticion);
					modemVpiDTO.setTelefono(modemLocal.getTelefono());
					modemVpiDTO.setSerial(modemKey.serial);
					modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
					modemVpiDTO.setTipo(tipoModem);
					modemVpiDTO.setDescTipo(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
					modemVpiDTO.setModelo(modemLocal.getModelo());
					/*
					 *	CR - 00026148 - Jun 25, 2009
					 *		- German P.
					 */
					modemVpiDTO.setMarca(modemLocal.getModem_marca());
					/*RQ.8595 - mfmendez*/
					modemVpiDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
					modemVpiDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
					modemVpiDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
					modemVpiDTO.setMaterialSAP(modemLocal.getNum_material_sap());
					modemVpiDTO.setPlantSAP(modemLocal.getCentro_sap());
					modemVpiDTO.setStorageSAP(modemLocal.getAlmacen_sap());
					modemVpiDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
					modemVpiDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
					modemVpiDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
					modemVpiDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
					modemVpiDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
					modemVpiDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
					/*FIN - RQ.8595 - mfmendez*/

					list.add(modemVpiDTO);
				}
			}
			//End TODO
			
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
			retorno.add(list);
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
//				REQ BA NAKED adicion familia PC naked
				if(familia_producto_servicioKey.faps_id.intValue()!=familiaPcBA 
						&& familia_producto_servicioKey.faps_id.intValue()!=familiaPcPsBANaked )
					continue;
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				
				if(operacion_comercialLocal.getOpco_tipo().equals("ACP")){
					esCambioPlan = true;
				}else if(operacion_comercialLocal.getOpco_tipo().equals("A"))
				{
					esAlta=true;
					if(operacion_comercialLocal.getOpco_tras()!=null)
					{
						if(operacion_comercialLocal.getOpco_tras().equals("T"))
							esTraslado=true;
					}
				}				
			}
			Integer valAlta=null;
			if(esAlta)
			{
				if(esTraslado)
					valAlta=new Integer(2);
				else			   
					valAlta=new Integer(1);
				
			}
			else{
				//Correccion defecto Wifi 06/10/2009
				if(esCambioPlan){
				    //si es Alta de WIFI meter un 1
				    if(esWifi(nroPeticion)){
				        valAlta=new Integer(8);
				        //valAlta=new Integer(1);
				    }else{
				    	valAlta=new Integer(3);
				    }
				}else{
				    //si es Alta de WIFI meter un 1
				    if(esWifiBAja(nroPeticion)){
				        valAlta=new Integer(9);
				    }else{
				    	valAlta=new Integer(0);
				    }
				}
			}
			log.debug("valAlta = "+valAlta);
			retorno.add(valAlta);
			retorno.add(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			ArrayList ret=new ArrayList();
			ret.add(new ArrayList());
			ret.add(new Integer(0));
			ret.add("Convencional");
			return ret;
		}
	}
	
	public ArrayList recuperaModemsAnyWay(Long nroPeticion)
	{
		try
		{
//			log.debug("Voy a recuperar los modems para la peticion:"+nroPeticion);
			boolean esAlta=false;
			boolean esTraslado=false;
			ArrayList retorno=new ArrayList();
			ArrayList list=new ArrayList();
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			if(peticionLocalHome==null)
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			Collection collection=peticionLocal.getModem();
			
			ArrayList idsPsAlta = new ArrayList();			
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
				pspet.getEstado_ps_peticion();
//				REQ BA NAKED adicion familia PC naked
				if(idFam==familiaPcBA || idFam==familiaBandaAncha 
						|| idFam==familiaPcPsBANaked || idFam==familiaBandaAnchaNaked){
				    if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )  
				        && peticionesInterfaces.estaOKProductoServicioPeticion(pspet))
				    idsPsAlta.add(pspet.getPsId());
				}
			}
					
			short tipoModem = 1;
			Tipo_ModemLocal tipo_ModemLocal = null;
			for(Iterator iterator = idsPsAlta.iterator();iterator.hasNext();){
				try {
				    Long psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().shortValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}
			tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(new Integer(tipoModem));
			
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
//				log.debug("Sacando modem");
				ModemLocal modemLocal=(ModemLocal) iterator.next();
				ModemKey modemKey=(ModemKey) modemLocal.getPrimaryKey();
				ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
				modemVpiDTO.setPeti_numero(nroPeticion);
				modemVpiDTO.setTelefono(modemLocal.getTelefono());
				modemVpiDTO.setSerial(modemKey.serial);
				modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
				modemVpiDTO.setTipo(tipoModem);
				modemVpiDTO.setDescTipo(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
				modemVpiDTO.setModelo(modemLocal.getModelo());
				modemVpiDTO.setMarca(modemLocal.getModem_marca());
				/*RQ.8595 - mfmendez*/
				modemVpiDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
				modemVpiDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
				modemVpiDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
				modemVpiDTO.setMaterialSAP(modemLocal.getNum_material_sap());
				modemVpiDTO.setPlantSAP(modemLocal.getCentro_sap());
				modemVpiDTO.setStorageSAP(modemLocal.getAlmacen_sap());
				modemVpiDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
				modemVpiDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
				modemVpiDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
				modemVpiDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
				modemVpiDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
				modemVpiDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
				/*FIN - RQ.8595 - mfmendez*/
				list.add(modemVpiDTO);
			}
			retorno.add(list);
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
//				REQ BA NAKED adicion familia PC naked
				if(familia_producto_servicioKey.faps_id.intValue()!=familiaPcBA && familia_producto_servicioKey.faps_id.intValue()!=familiaPcPsBANaked)
					continue;
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				if(operacion_comercialLocal.getOpco_tipo().equals("ACP") || operacion_comercialLocal.getOpco_tipo().equals("A"))
				{
					esAlta=true;
					if(operacion_comercialLocal.getOpco_tras()!=null)
					{
						if(operacion_comercialLocal.getOpco_tras().equals("T"))
							esTraslado=true;
					}
				}
			}
			Integer valAlta=null;
			if(esAlta)
			{
				if(esTraslado)
					valAlta=new Integer(2);
				else
					valAlta=new Integer(1);
			}
			else
				valAlta=new Integer(0);
			retorno.add(valAlta);
			retorno.add(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			ArrayList ret=new ArrayList();
			ret.add(new ArrayList());
			ret.add(new Integer(0));
			ret.add("Convencional");
			return ret;
		}
	}
	
	public ArrayList recuperaModemsDePet(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			ArrayList list=new ArrayList();
			ArrayList retorno=new ArrayList();
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			if(peticionLocalHome==null)
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
			ArrayList idsPsAlta = new ArrayList();			
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
//				REQ BA NAKED adicion familia PC naked
				if(idFam==familiaPcBA || idFam==familiaBandaAncha
						|| idFam==familiaPcPsBANaked || idFam==familiaBandaAnchaNaked){
				    if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") ) 
				        && peticionesInterfaces.estaOKProductoServicioPeticion(pspet))
				    idsPsAlta.add(pspet.getPsId());
				}
			}
					
			short tipoModem = 1;
			Tipo_ModemLocal tipo_ModemLocal = null;
			for(Iterator iterator = idsPsAlta.iterator();iterator.hasNext();){
				try {
				    Long psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().shortValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}
			tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(new Integer(tipoModem));
			Collection collection=peticionLocal.getModem();
			for(Iterator iterator=collection.iterator();iterator.hasNext();)
			{
				ModemLocal modemLocal=(ModemLocal) iterator.next();
				ModemKey modemKey=(ModemKey) modemLocal.getPrimaryKey();
				ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
				modemVpiDTO.setPeti_numero(nroPeticion);
				modemVpiDTO.setTelefono(modemLocal.getTelefono());
				modemVpiDTO.setSerial(modemKey.serial);
				
				modemVpiDTO.setMarca(modemLocal.getModem_marca());
				modemVpiDTO.setModelo(modemLocal.getModelo());
								
				if (modemLocal.getTipo() == null)
					modemVpiDTO.setTipo((short)0);
				else
					modemVpiDTO.setTipo(modemLocal.getTipo().shortValue());
				if(localidadLocal.getLocalidad_toa().intValue() == LOCALIDAD_TOA &&
						modemLocal.getAccion().intValue() != accionModemLiberar){
					Estados_TOALocalHome estadoTOAHome = (Estados_TOALocalHome) HomeFactory.getHome(Estados_TOALocalHome.JNDI_NAME);
					//Estados_TOAKey estadoTOaKey = new Estados_TOAKey(new Integer(modemLocal.getAccion().shortValue()));
					//dacadena se cambia consulta en tabla estados toa, por estado y familia para retornar la descripcion del estado
					Estados_TOALocal estadoTOALocal = estadoTOAHome.findEstadoFam(new Integer(modemLocal.getAccion().toString()),ComunInterfaces.BA);
					if(modemLocal.getAccion().intValue() == accionModemConfigurado)
						modemVpiDTO.setAccion(Short.parseShort("11"));
					else
						modemVpiDTO.setAccion(Short.parseShort("12"));
					modemVpiDTO.setFechaActivacion(peticionLocal.getPeti_fecha_compromiso().toString());
				}else{
					modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
				}
				
				modemVpiDTO.setAccion(modemLocal.getAccion().shortValue());
				modemVpiDTO.setTipo(tipoModem);
				modemVpiDTO.setDescTipo(tipo_ModemLocal!=null ? tipo_ModemLocal.getDesc_tipo(): "Convencional");				
				/*RQ.8595 - mfmendez*/
				modemVpiDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
				modemVpiDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
				modemVpiDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
				modemVpiDTO.setMaterialSAP(modemLocal.getNum_material_sap());
				modemVpiDTO.setPlantSAP(modemLocal.getCentro_sap());
				modemVpiDTO.setStorageSAP(modemLocal.getAlmacen_sap());
				modemVpiDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
				modemVpiDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
				modemVpiDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
				modemVpiDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
				modemVpiDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
				modemVpiDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
				/*FIN - RQ.8595 - mfmendez*/
				
				list.add(modemVpiDTO);
			}
			return list;
		}
		catch(NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		} catch (FinderException e) {
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch(Exception e)
		{
			log.debug("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	
	public void grabaModemsBaVpi(Long nroPeticion,Long telAsignado,ArrayList modems)
	{
		try
		{
			if(peticionLocalHome==null)
				peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(modemLocalHome==null)
				modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);	
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			for(Iterator iterator=peticionLocal.getModem().iterator();iterator.hasNext();)
			{
				ModemLocal modemLocal=(ModemLocal) iterator.next();
				modemLocal.remove();
				log.debug("Se elimina modem ");
			}
			for(Iterator iterator=modems.iterator();iterator.hasNext();)
			{
//				log.debug("Ingresando Modem");
				ModemVpiDTO modemVpiDTO=(ModemVpiDTO) iterator.next();
				
				ModemLocal modemLocal=modemLocalHome.create(modemVpiDTO.getSerial(),peticionLocal,telAsignado,new Short(modemVpiDTO.getAccion()),
						modemVpiDTO.getMarca(),modemVpiDTO.getModelo(),new Integer((int)modemVpiDTO.getTipo()));
				modemLocal.setModem_marca(modemVpiDTO.getMarca());
				modemLocal.setModelo(modemVpiDTO.getModelo());
				
				/*RQ.8595 - mfmendez*/
				modemLocal.setFec_cont_sap(modemVpiDTO.getPostingDateSAP());
				modemLocal.setClase_mov_sap(modemVpiDTO.getMoveTypeSAP());
				if(modemVpiDTO.getMaterialCodeSAP() != null){
					modemLocal.setPos_doc_sap(Integer.parseInt(modemVpiDTO.getMaterialCodeSAP()));
				}else{
					modemLocal.setPos_doc_sap(0);
				}
				modemLocal.setNum_material_sap(modemVpiDTO.getMaterialSAP());
				modemLocal.setCentro_sap(modemVpiDTO.getPlantSAP());
				modemLocal.setAlmacen_sap(modemVpiDTO.getStorageSAP());
				modemLocal.setCod_lote_sap(modemVpiDTO.getBatchCodeSAP());
				modemLocal.setUnd_medida_sap(modemVpiDTO.getMeasurementUnitSAP());
				modemLocal.setCentr_cost_sap(modemVpiDTO.getCostCenterSAP());
				modemLocal.setArea_func_sap(modemVpiDTO.getFuncAreaLongSAP());
				modemLocal.setElement_pep_sap(modemVpiDTO.getPepElementSAP());	
				modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
				modemLocal.setFlag_mat_sap(modemVpiDTO.getFlagMatSAP());
				
				log.debug("Se adiciona campos de sap para el modem  "+modemVpiDTO.getSerial());
				/*FIN - RQ.8595 - mfmendez*/
//				if(modemLocal!=null)
//					log.debug("Modem Ingresado Exitosamente.");	
			}
		}
		catch(Exception e)
		{
			log.debug("RecursosBABean:grabaModemsBaVpi: Exception",e);
		}
	}
	
	
	public void insertarCausalesCnaPeticion(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException 
	{
//		PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
		PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
		if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!=estadoPeticionEnCurso)
		{
			log.info("En reversa no se almacenan Quiebres Automáticos.PetAtiempo:"+peticionKey.peti_numero);
			return;
		}
		Fecha fecha=new Fecha();
		PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);
		if(usuarioHome==null)
			usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		if(catalogo_causalHome==null)
			catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		if(estado_psHome==null)
			estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		if(estado_ps_peticionHome==null)
			estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		if(causal_peticionHome==null)
			causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);


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
					
					causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
					causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
					causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
					causal_peticionLocal.setCod_actividad(idAct);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionActualBA(java.lang.String, java.lang.String, java.lang.String)
	 */
//	SIGRES - refrescar datos - 03/07/2008 con la implementacion de SIGRES se deja de invocar este metodo
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx {
		this.enviarConfiguracionActualBA(null,peticion,id_actividad);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#actualizaModemPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR022S)
	 */
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx
	{

		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		
		try
		{
			bExecution = BackendTraceUtil.initExecution(tr022s, log);
			bExecution.setNumPetAtiempo(new Long(tr022s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr022s.getId());
			bExecution.startOperation();
	
			validaHome () ;
			// busca el registro del mensaje
			log.debug("Primero si es mensaje de error lo obviamos.");
			if(tr022s.isError())
			{
				log.debug("Respuesta con error:"+XMLUtilities.marshall(tr022s));
				return;
			}
			
			Mensaje_estado_baLocal mensaje_estado_ba;
			try {
				mensaje_estado_ba =
					mensajeEstadoBaLocalHome.findByPrimaryKey(
						new Mensaje_estado_baKey(new Long(tr022s.getId())));
			} catch (FinderException e1)
			{
				mensaje_estado_ba=null;
			}
    
			if (mensaje_estado_ba == null)
			{
				log.debug (
					"No Existe Respuesta en la base de enviados\n "
					+ XMLUtilities.marshall (tr022s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr022s.getId(),ATiempoAppEx.EXCEPTION);
			}
    
			// Validacion del estado de la respuesta
    
			Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_ba.getMensaje_estado ().getPrimaryKey () ;
    
			if (mensaje_estadoKey.cod_estado.intValue () != estadoOk)
			{
				log.debug (
					"Es estado de la respuesta es diferente para ser procesado\n "
					+ XMLUtilities.marshall (tr022s));
				return;
			}
    
			// TODO: ver si procesamos los errores o si la interfaz al usuario se va a encargar de
			//       parsear el XML
    
			// todo ok, se graba la respuesta
    
			Long idTmpModem = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_MODEM")) ;
			
			if(tmp_modemLocalHome==null)
				tmp_modemLocalHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
    
			Tmp_modemLocal tmpDecoTarjetaLocal = tmp_modemLocalHome.create (idTmpModem,mensaje_estado_ba.getPeticion(),XMLUtilities.marshall (tr022s));
			
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
		catch (NamingException e)
		{
			bExecution.setErrorOp(true);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}			
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
	}

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaModemPorUtilizar(long, java.lang.String, long)
	 */
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaModemPorUtilizar(long, java.lang.String, long)
	 */
	
	//DAVID: se adiciona parámetro idContratista, req 32354, Febrero 8 2010
	public Long enviaModemPorUtilizar(long idPeticion, String ult4Digitos, long idContratista) throws ATiempoAppEx
	{
		validaHome();
		
		try
		{
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
    
			// obtiene el id de la peticion Atis
    
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
    
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
    
			Collection colTmpModem = tmp_modemLocalHome.findByNroPeticion (new Long (idPeticion)) ;
//    
			Iterator iterTmpModem = colTmpModem.iterator () ;
//    
			while (iterTmpModem.hasNext ())
			{
				Tmp_modemLocal tmp_modemLocal= (Tmp_modemLocal) iterTmpModem.next();
        
				try
				{
					tmp_modemLocal.remove();
				}
				catch (EJBException e1)
				{
					log.error("Error al enviar Modem.",e1);
				}
				catch (RemoveException e1)
				{
					log.error("Error al enviar Modem.",e1);
				}
			}
			
			TR022E tr022e=new TR022E();
			tr022e.setId(String.valueOf(idCorrelativoMensaje));
			tr022e.setAtisRequestNumber(idPeticion);
			LocalidadKey localidadKey=(LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
			tr022e.setCityCode(localidadKey.cod_loc);
			tr022e.setModemFinalDigits(ult4Digitos);
			//TCS-gquevedo May 18, 2009 CR.23338 INICIO
			PeticionesInterfaces peticionesInterfaces = new PeticionesDelegate();
			ArrayList idsPsAlta = new ArrayList();			
			ArrayList idsPsAltaNoPC = new ArrayList();	
			
			for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();){
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
				//REQ BA NAKED adicion familia PC naked 
				if(idFam==familiaPcBA || idFam==familiaBandaAncha || idFam==familiaPcPsBANaked || idFam==familiaBandaAnchaNaked){
				    //AT-2524 Correccion pdti 10/09/2009
				    EquipoDelegate equipoDelegate = new EquipoDelegate();log.debug("Se Pregunta si es PDTI");
				    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
				    if(pdtiCollection.size()== 0){//debe entrar aqui en caso de que No sea PDTI


				        try {
						    //Valido si es Wifi
						    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());

						    if (ps_Tipo_ModemLocal2!= null){
						        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
						    	//if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )){

						            idsPsAltaNoPC.add(pspet.getPsId());
							    }
							 }

						}catch (FinderException e){
						}

				    }
				}else{
//					REQ BA NAKED adicion familia PC naked
				    if(idFam==familiaPcBA || idFam==familiaPcPsBANaked){
				        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet) ){
					    idsPsAlta.add(pspet.getPsId());
			}
				}
			}
			}
//			Correccion defecto 29884
			if(idsPsAlta.size()==0 && idsPsAltaNoPC.size()==0){
				for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
				{
					Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
					Operacion_comercialLocal opco=pspet.getOperacion_comercial();
					int idFam=pspet.getFamiliaKey().faps_id.intValue();
//					REQ BA NAKED adicion familia PC naked
					if(idFam==familiaBandaAncha|| idFam==familiaBandaAnchaNaked){
					    //AT-2524 Correccion pdti 10/09/2009
					    EquipoDelegate equipoDelegate = new EquipoDelegate();log.debug("Se Pregunta si es PDTI");
					    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
					    if(pdtiCollection.size()== 0){//debe entrar aqui en caso de que No sea PDTI


					        try {
							    //Valido si es Wifi
							    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());

							    if (ps_Tipo_ModemLocal2!= null){
							        //if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
							    	if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )){

							            idsPsAltaNoPC.add(pspet.getPsId());
								    }
								 }

							}catch (FinderException e){
							}

					    }
					}else{
//						REQ BA NAKED adicion familia PC naked
					    if(idFam==familiaPcBA || idFam==familiaPcPsBANaked ){
					        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") ) ){
					            	        	idsPsAlta.add(pspet.getPsId());

					        }
					    }

					}
				}
			}
//			Fin Correccion defecto 29884
			Long psAlta = new Long(0);
			int tipoModem = 1;

			for(Iterator iterator = idsPsAltaNoPC.iterator();iterator.hasNext();){
				try {
				    psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().intValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}			
			
			//TODO: 30/10/2009 - Ajuste arreglo - RETA
			for(Iterator iterator = idsPsAlta.iterator();iterator.hasNext();){
				try {
				    psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().intValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}

			if(idsPsAlta!=null&& idsPsAlta.size()>0){
			    psAlta= (Long)idsPsAlta.get(0);

			}

			//Juan
			if(esWifiBAja(new Long (idPeticion))){
				for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();){
					Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal)iterator.next();
					 psAlta=pspet.getPsId();
					 tipoModem=1;
					 break;
				}
			}
			///Juan

			tr022e.setId_plan(psAlta.longValue());			
			tr022e.setModemType(new Integer (tipoModem));
			//DAVID: se adiciona parámetro idContratista, req 32354, Febrero 8 2010
			tr022e.setContractorId(idContratista);
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
//    
			Mensaje_estado_baLocal msgLocal=mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			msgLocal.setPeticion(peticion);
			msgLocal.setFecha_envio(df.format(new java.util.Date ()));
			msgLocal.setMensaje_estado (mensajeOk) ;
//    
			new SolicitudModemMQ ().enviarMensaje (tr022e) ;
    
			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			log.error("Error al enviar Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (FinderException e)
		{
			log.error("Error al enviar Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}

/*	
	public Long enviaModemPorUtilizar(long idPeticion, String ult4Digitos) throws ATiempoAppEx
	{
		validaHome();
		
		try
		{
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
    
			// obtiene el id de la peticion Atis
    
			PeticionKey key = new PeticionKey (new Long (idPeticion)) ;
    
			PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
    
			Collection colTmpModem = tmp_modemLocalHome.findByNroPeticion (new Long (idPeticion)) ;
//    
			Iterator iterTmpModem = colTmpModem.iterator () ;
//    
			while (iterTmpModem.hasNext ())
			{
				Tmp_modemLocal tmp_modemLocal= (Tmp_modemLocal) iterTmpModem.next();
        
				try
				{
					tmp_modemLocal.remove();
				}
				catch (EJBException e1)
				{
					log.error("Error al enviar Modem.",e1);
				}
				catch (RemoveException e1)
				{
					log.error("Error al enviar Modem.",e1);
				}
			}
			
			TR022E tr022e=new TR022E();
			tr022e.setId(String.valueOf(idCorrelativoMensaje));
			tr022e.setAtisRequestNumber(idPeticion);
			LocalidadKey localidadKey=(LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
			tr022e.setCityCode(localidadKey.cod_loc);
			tr022e.setModemFinalDigits(ult4Digitos);
			//TCS-gquevedo May 18, 2009 CR.23338 INICIO
			PeticionesInterfaces peticionesInterfaces = new PeticionesDelegate();
			ArrayList idsPsAlta = new ArrayList();
			ArrayList idsPsAltaNoPC = new ArrayList();	
			
			for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal opco=pspet.getOperacion_comercial();
				int idFam=pspet.getFamiliaKey().faps_id.intValue();
				
				if(idFam==familiaBandaAncha){
				    //AT-2524 Correccion pdti 10/09/2009
				    EquipoDelegate equipoDelegate = new EquipoDelegate();log.debug("Se Pregunta si es PDTI");
				    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
				    if(pdtiCollection.size()== 0){//debe entrar aqui en caso de que No sea PDTI
					    
				       
				        try {
						    //Valido si es Wifi
						    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());
						   
						    if (ps_Tipo_ModemLocal2!= null){
						        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
						    	//if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )){
							    
						            idsPsAltaNoPC.add(pspet.getPsId());
							    }
							 }
						    			    	
						}catch (FinderException e){
						}
						 	
				    }
				}else{
				    if(idFam==familiaPcBA){
				        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet) ){
				            	        	idsPsAlta.add(pspet.getPsId());
				            	
				        }
				    }
				    
				}
			}
//			Correccion defecto 29884
			if(idsPsAlta.size()==0 && idsPsAltaNoPC.size()==0){

				for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
				{
					Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
					Operacion_comercialLocal opco=pspet.getOperacion_comercial();
					int idFam=pspet.getFamiliaKey().faps_id.intValue();
					
					if(idFam==familiaBandaAncha){
					    //AT-2524 Correccion pdti 10/09/2009
					    EquipoDelegate equipoDelegate = new EquipoDelegate();log.debug("Se Pregunta si es PDTI");
					    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
					    if(pdtiCollection.size()== 0){//debe entrar aqui en caso de que No sea PDTI
						    
					       
					        try {
							    //Valido si es Wifi
							    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());
							   
							    if (ps_Tipo_ModemLocal2!= null){
							        //if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
							    	if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )){
								    
							            idsPsAltaNoPC.add(pspet.getPsId());
								    }
								 }
							    			    	
							}catch (FinderException e){
							}
							 	
					    }
					}else{
					    if(idFam==familiaPcBA){
					        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") ) ){
					            	        	idsPsAlta.add(pspet.getPsId());
					            	
					        }
					    }
					    
					}
				}
			}
//			Fin Correccion defecto 29884
			Long psAlta = new Long(0);
			int tipoModem = 1;
			
			for(Iterator iterator = idsPsAltaNoPC.iterator();iterator.hasNext();){
				try {
				    psAlta = (Long)iterator.next();
				    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				    if (ps_Tipo_ModemLocal!= null){
				        tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().intValue();
				        break;
				    }			    	
				}catch (FinderException e){
				}
			}
			
			if(idsPsAlta!=null&& idsPsAlta.size()>0){
			    psAlta= (Long)idsPsAlta.get(0);
			
			}
			
			tr022e.setId_plan(psAlta.longValue());			
			tr022e.setModemType(new Integer (tipoModem));
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
//			
			Mensaje_estado_baLocal msgLocal=mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			msgLocal.setPeticion(peticion);
			msgLocal.setFecha_envio(df.format(new java.util.Date ()));
			msgLocal.setMensaje_estado (mensajeOk) ;
//    
			new SolicitudModemMQ ().enviarMensaje (tr022e) ;
    
			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			log.error("Error al enviar Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (FinderException e)
		{
			log.error("Error al enviar Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
	}
*/
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#buscarRespuestaMensajeModem(java.lang.Long, java.lang.Long)
	 */
	public TR022S buscarRespuestaMensajeModem(Long idPeticion, Long idMensaje) throws ATiempoAppEx
	{
		validaHome();
		try
		{
			if(mensajeEstadoBaLocalHome==null)
				mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(idMensaje));
			
			if (msgMoLocal == null)
				return null;

			Mensaje_estadoLocal msgEstadoLocal = msgMoLocal.getMensaje_estado();

			PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			Collection c = pLocal.getTmp_modem();
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Tmp_modemLocal tmpdtLocal = (Tmp_modemLocal) it.next();
				TR022S tr016s = (TR022S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
				return tr016s;
			}
		}
		catch (FinderException e)
		{
			log.error("Error al buscar Respuesta Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (NamingException e)
		{
			log.error("Error al buscar Respuesta Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
		return null;
	}
	
	public void enviaRefrescoBA(String nroPeticion)throws ATiempoAppEx{
		try
		{
			Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			boolean esTrasladoAltaSoloBA=false;
			Traslado_solobaLocal traslado_solobaLocal=null;
			try{
				traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(new Long(nroPeticion));
				esTrasladoAltaSoloBA=true;
			} catch (FinderException e) {
				log.debug("No es traslado alta BA");
			}
			if(esTrasladoAltaSoloBA){
				//Envio el num_ide_num, que es la linea nueva y actualizo la seccion de nueva(destino)
				//SIGRES -Refrescar Datos - agonz - 03/07/2008 
				//this.enviarConfiguracionActualBA(nroPeticion,"RN");
				this.enviarConfiguracionActualBASigres(nroPeticion,"RN", null);
			}else{
				//reviso los ps para ver que trae la peticion que se está refrescando
				boolean tieneLB=false;
				boolean tieneBA=false;
				boolean tienePcLineaAlta=false;
				boolean tienePcLineaAltaTraslado=false;
				boolean tienePcBAAlta=false;
				boolean tienePcBAAltaTraslado=false;
				boolean tienePcBAAltaCP=false;
					
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(new Long(nroPeticion)));
				log.debug("Busco en los ps los PC BA y PC LB");
				for(Iterator iterator2=local.getProducto_servicio_peticion().iterator();iterator2.hasNext();)
				{
					Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
					Producto_servicioLocal ps=local2.getProducto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio().getPrimaryKey();
					Operacion_comercialLocal opco=local2.getOperacion_comercial();
					int idFamiliaPs=familia_producto_servicioKey.faps_id.intValue();
					if(idFamiliaPs==ComunInterfaces.familiaLinea || (idFamiliaPs==ComunInterfaces.familiaPcLinea || idFamiliaPs==ComunInterfaces.familiaIP))
						tieneLB=true;
//					REQ BA NAKED adicion familia PC naked
					else if(idFamiliaPs==ComunInterfaces.familiaBandaAncha || idFamiliaPs==ComunInterfaces.familiaPcBA
							||idFamiliaPs==ComunInterfaces.familiaBandaAnchaNaked || idFamiliaPs==ComunInterfaces.familiaPcPsBANaked)
						tieneBA=true;
					if(idFamiliaPs==ComunInterfaces.familiaPcLinea || idFamiliaPs==ComunInterfaces.familiaIP)
					{
						if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
							tienePcLineaAlta=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("T"))//TRASLADO
							tienePcLineaAltaTraslado=true;
					}
//					REQ BA NAKED adicion familia PC naked
					if(idFamiliaPs==ComunInterfaces.familiaPcBA ||idFamiliaPs==ComunInterfaces.familiaPcPsBANaked)
					{
						if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
							tienePcBAAlta=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("T"))//TRASLADO
							tienePcBAAltaTraslado=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("ACP"))
							tienePcBAAltaCP=true;							
					}
				}	
				//Si no es traslado solo BA
				if (tienePcBAAltaTraslado){
					log.debug("Hay un PC BA Alta Traslado");
					if(tienePcLineaAltaTraslado)//Viene con traslado LB, signfica que se asignan los recursos nuevos y previos en la actividad asignacion Manual STb.
					{
						log.debug("Hay un PC STB Alta Traslado");
						//En este caso se hace la alta y la baja en el mismo flujo
	//					Debo enviar el numero de la linea nueva(destino) y refrescar nuevo en ADSL(destino)
						InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
						InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(new Long(nroPeticion));
						if (informacionTecnicaDTO.LineaNueva != null)
						{
							if(informacionTecnicaDTO.LineaNueva.getTelefono()!=null && !"".equals(informacionTecnicaDTO.LineaNueva.getTelefono().trim()))
							{	
								//SIGRES -Refrescar Datos - agonz - 03/07/2008 
								//this.enviarConfiguracionActualBA(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN");
								this.enviarConfiguracionActualBASigres(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN",null); //refresco los datos ADSL Destino
							}//Si no tiene el dato es que todavia no hace la asignacion, por lo tanto no actualiza nada
						} 
					}else
					{  //Este caso no deberia existir, pues cuando no viene con LB los traslados de BA son separadas las altas de las bajas	
	//					Es un alta traslado BA sin LB, por lo tanto en num_id_nu debe ir el numero donde se traslada.
						log.warn("Es un alta traslado BA sin traslado de Linea basica. Deberia estar en la tabla traslado solo BA, separada en dos peticiones");
						//Envio el num_ide_num, que es la linea destino y actualizo la seccion de nueva(destino)
//						SIGRES -Refrescar Datos - agonz - 03/07/2008 
						//this.enviarConfiguracionActualBA(nroPeticion,"RN");
						this.enviarConfiguracionActualBASigres(nroPeticion,"RN", null);
					}
				}else{
					//No es alta traslado BA
					if(tienePcBAAlta)					
					{//Es un alta de BA
						log.debug("Hay un PC BA Alta");
						if(tienePcLineaAlta)
						{//viene con alta de linea basica
							log.debug("Hay un PC STB Alta");
							InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
							InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(new Long(nroPeticion));
							//Obtengo el telefono de los recursos
							if (informacionTecnicaDTO.LineaNueva != null)
							{
								if(informacionTecnicaDTO.LineaNueva.getTelefono()!=null && !"".equals(informacionTecnicaDTO.LineaNueva.getTelefono().trim()))
								{
//									SIGRES -Refrescar Datos - agonz - 03/07/2008
//									this.enviarConfiguracionActualBA(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN"); //refresco los datos ADSL Destino
									this.enviarConfiguracionActualBASigres(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN",null); //refresco los datos ADSL Destino
								}//Si no tiene el dato es que todavia no hace la asignacion, por lo tanto no actualiza nada
							} 
								
						}else
						{
							//Viene un alta sobre una linea de destino. Se actualiza adsl de destino con el num_ide_nu
//							SIGRES -Refrescar Datos - agonz - 03/07/2008 
//							this.enviarConfiguracionActualBA(nroPeticion,"RN");
							this.enviarConfiguracionActualBASigres(nroPeticion,"RN", null);	
						}
					}else{
						if(tienePcBAAltaCP)
						{//Tiene un alta cambio producto
//							SIGRES -Refrescar Datos - agonz - 03/07/2008
//							this.enviarConfiguracionActualBA(nroPeticion,"RN"); 
							this.enviarConfiguracionActualBASigres(nroPeticion,"RN", null);	
						}
						//Para el resto (las bajas), envio el num_ide_nu y actualizo el actual(origen)
//						SIGRES -Refrescar Datos - agonz - 03/07/2008
//						this.enviarConfiguracionActualBA(nroPeticion,"");

						this.enviarConfiguracionActualBASigres(nroPeticion,"", null);
					}
				}
			}
		}
		catch (FinderException e)
		{
			log.error("Error al Refrescar BA.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}		
		catch (NamingException e)
		{
			log.error("Error al Refrescar BA.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
		
	}
	
	public void enviaRefrescoBA(String nroPeticion, String accion, String codActividad)throws ATiempoAppEx{
		try
		{
			Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			boolean esTrasladoAltaSoloBA=false;
			Traslado_solobaLocal traslado_solobaLocal=null;
			try{
				traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(new Long(nroPeticion));
				esTrasladoAltaSoloBA=true;
			} catch (FinderException e) {
				log.debug("No es traslado alta BA");
			}
			if(esTrasladoAltaSoloBA){
				//Envio el num_ide_num, que es la linea nueva y actualizo la seccion de nueva(destino)
				//SIGRES -Refrescar Datos - agonz - 03/07/2008 
				//this.enviarConfiguracionActualBA(nroPeticion,"RN");
				this.enviarConfiguracionActualBASigres(nroPeticion,accion, codActividad);
			}else{
				//reviso los ps para ver que trae la peticion que se está refrescando
				boolean tieneLB=false;
				boolean tieneBA=false;
				boolean tienePcLineaAlta=false;
				boolean tienePcLineaAltaTraslado=false;
				boolean tienePcBAAlta=false;
				boolean tienePcBAAltaTraslado=false;
				boolean tienePcBAAltaCP=false;
					
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal local=peticionHome.findByPrimaryKey(new PeticionKey(new Long(nroPeticion)));
				log.debug("Busco en los ps los PC BA y PC LB");
				for(Iterator iterator2=local.getProducto_servicio_peticion().iterator();iterator2.hasNext();)
				{
					Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
					Producto_servicioLocal ps=local2.getProducto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio().getPrimaryKey();
					Operacion_comercialLocal opco=local2.getOperacion_comercial();
					int idFamiliaPs=familia_producto_servicioKey.faps_id.intValue();
					if(idFamiliaPs==ComunInterfaces.familiaLinea || (idFamiliaPs==ComunInterfaces.familiaPcLinea || idFamiliaPs==ComunInterfaces.familiaIP))
						tieneLB=true;
//					REQ BA NAKED adicion familia PC naked
					else if(idFamiliaPs==ComunInterfaces.familiaBandaAncha || idFamiliaPs==ComunInterfaces.familiaPcBA
							||idFamiliaPs==ComunInterfaces.familiaBandaAnchaNaked || idFamiliaPs==ComunInterfaces.familiaPcPsBANaked)
						tieneBA=true;
					if(idFamiliaPs==ComunInterfaces.familiaPcLinea || idFamiliaPs==ComunInterfaces.familiaIP)
					{
						if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
							tienePcLineaAlta=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("T"))//TRASLADO
							tienePcLineaAltaTraslado=true;
					}
//					REQ BA NAKED adicion familia PC naked
					if(idFamiliaPs==ComunInterfaces.familiaPcBA || idFamiliaPs==ComunInterfaces.familiaPcPsBANaked)
					{
						if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
							tienePcBAAlta=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("T"))//TRASLADO
							tienePcBAAltaTraslado=true;
						else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("ACP"))
							tienePcBAAltaCP=true;							
					}
				}	
				//Si no es traslado solo BA
				if (tienePcBAAltaTraslado){
					log.debug("Hay un PC BA Alta Traslado");
					if(tienePcLineaAltaTraslado)//Viene con traslado LB, signfica que se asignan los recursos nuevos y previos en la actividad asignacion Manual STb.
					{
						log.debug("Hay un PC STB Alta Traslado");
						//En este caso se hace la alta y la baja en el mismo flujo
	//					Debo enviar el numero de la linea nueva(destino) y refrescar nuevo en ADSL(destino)
						InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
						InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(new Long(nroPeticion));
						if (informacionTecnicaDTO.LineaNueva != null)
						{
							if(informacionTecnicaDTO.LineaNueva.getTelefono()!=null && !"".equals(informacionTecnicaDTO.LineaNueva.getTelefono().trim()))
							{	
								//SIGRES -Refrescar Datos - agonz - 03/07/2008 
								//this.enviarConfiguracionActualBA(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN");
								this.enviarConfiguracionActualBASigres(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,accion,null); //refresco los datos ADSL Destino
							}//Si no tiene el dato es que todavia no hace la asignacion, por lo tanto no actualiza nada
						} 
					}else
					{  //Este caso no deberia existir, pues cuando no viene con LB los traslados de BA son separadas las altas de las bajas	
	//					Es un alta traslado BA sin LB, por lo tanto en num_id_nu debe ir el numero donde se traslada.
						log.warn("Es un alta traslado BA sin traslado de Linea basica. Deberia estar en la tabla traslado solo BA, separada en dos peticiones");
						//Envio el num_ide_num, que es la linea destino y actualizo la seccion de nueva(destino)
//						SIGRES -Refrescar Datos - agonz - 03/07/2008 
						//this.enviarConfiguracionActualBA(nroPeticion,"RN");
						this.enviarConfiguracionActualBASigres(nroPeticion,accion, codActividad);
					}
				}else{
					//No es alta traslado BA
					if(tienePcBAAlta)					
					{//Es un alta de BA
						log.debug("Hay un PC BA Alta");
						if(tienePcLineaAlta)
						{//viene con alta de linea basica
							log.debug("Hay un PC STB Alta");
							InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
							InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(new Long(nroPeticion));
							//Obtengo el telefono de los recursos
							if (informacionTecnicaDTO.LineaNueva != null)
							{
								if(informacionTecnicaDTO.LineaNueva.getTelefono()!=null && !"".equals(informacionTecnicaDTO.LineaNueva.getTelefono().trim()))
								{
//									SIGRES -Refrescar Datos - agonz - 03/07/2008
//									this.enviarConfiguracionActualBA(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,"RN"); //refresco los datos ADSL Destino
									this.enviarConfiguracionActualBASigres(informacionTecnicaDTO.LineaNueva.getTelefono(),nroPeticion,accion, codActividad); //refresco los datos ADSL Destino
								}//Si no tiene el dato es que todavia no hace la asignacion, por lo tanto no actualiza nada
							} 
								
						}else
						{
							//Viene un alta sobre una linea de destino. Se actualiza adsl de destino con el num_ide_nu
//							SIGRES -Refrescar Datos - agonz - 03/07/2008 
//							this.enviarConfiguracionActualBA(nroPeticion,"RN");
							this.enviarConfiguracionActualBASigres(nroPeticion,accion, codActividad);	
						}
					}else{
						if(tienePcBAAltaCP)
						{//Tiene un alta cambio producto
//							SIGRES -Refrescar Datos - agonz - 03/07/2008
//							this.enviarConfiguracionActualBA(nroPeticion,"RN"); 
							this.enviarConfiguracionActualBASigres(nroPeticion,accion, codActividad);	
						}
						//Para el resto (las bajas), envio el num_ide_nu y actualizo el actual(origen)
//						SIGRES -Refrescar Datos - agonz - 03/07/2008
//						this.enviarConfiguracionActualBA(nroPeticion,"");

						this.enviarConfiguracionActualBASigres(nroPeticion,accion,codActividad);
					}
				}
			}
		}
		catch (FinderException e)
		{
			log.error("Error al Refrescar BA.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}		
		catch (NamingException e)
		{
			log.error("Error al Refrescar BA.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
		
	}
	

	public boolean enviaConfiguracionTerra (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim,boolean reversa) throws ATiempoAppEx{
			try{
			
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
				//TODO: Se comentarea con efecto del funcionamiento del Req_2009_00030351_TerraCDS_SiteBuilder - RETA - 06/11/2009
				// Si existe mas de un ps para Terra envío la petición a configuración terra para que se configure manualmente
				/*log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>Cant PSs para la actividad: " + act.getPsOk().size());
				if (act.getPsOk().size() > 1) {
					// Envio a configuracion manual
					insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());					
					return false;
				}
				
				Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
				
				PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();				
				*/
				//End TODO
				validaHome ();
			
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

				//PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				
				TR023E tr023e = new TR023E();
			
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
				PeticionesServicesLocal peticionesServicesLocal = peticionesServicesLocalHome.create();
				log.debug("Creamos la peticion local");

				//REQ McAfee @Dcardena 15/10/2014
				
				
				//******* Campos Obligatorios para enviar el mensaje
				//Busco el Service Reference ID... usado solo en el PS de CDS
				Producto_servicio_peticionKey pspK = new Producto_servicio_peticionKey();
				pspK.correlativo = psprim.getCorrelativo();
				pspK.fk_psp_pet_peti_numero = idPeticion; 
				Producto_servicio_peticionLocal prodServLocal = producto_servicio_peticionLocalHome.findByPrimaryKey(pspK);

				String serviceRefId = "";
				String serviceRefIdBaja = "";
				Long idOCCP=new Long(0);
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
				Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				
				if(act.getIdActividadFlujo()!=null){
	        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(idPeticion, act.getIdActividadFlujo());
	        		if(peticionFlujoList != null){
	        			for (Iterator it = peticionFlujoList.iterator(); it.hasNext();) {
	        				Peticion_flujoLocal pf = (Peticion_flujoLocal) it.next();
	        				
							if(act.getCodigoActividad().equals("Director de Flujos.Configuracion_Terra.Configurar_Terra")&& pf.getFk_opco_2_pefl().getOpco_tipo().equals("BCP")){
								Collection prodServBajaList = producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion,pf.getPrse_id(),((Operacion_comercialKey)pf.getFk_opco_2_pefl().getPrimaryKey()).opco_id);
														
								for (Iterator its = prodServBajaList.iterator(); its.hasNext();) {
									Producto_servicio_peticionLocal prodServBajaLocal = (Producto_servicio_peticionLocal) its.next();
										
									if (prodServBajaLocal.getFk_01_subp_atis()!=null)
									{
										if(prodServBajaLocal.getFk_01_subp_atis().getId_srv_ref_cd()!=null){
											
											serviceRefIdBaja=prodServBajaLocal.getFk_01_subp_atis().getId_srv_ref_cd();	
											tr023e.setOldServiceReferenceId(serviceRefIdBaja);
											tr023e.setProductServiceCodeAnt(prodServBajaLocal.getPsId().longValue());
											idOCCP=prodServBajaLocal.getIdOperacionComercial();
										}
									}
									if (serviceRefIdBaja.trim().length()<1){
										//No se envia el mensaje pk faltan datos
										// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
										log.debug("Configuracion Terra: No se encuentra Service Ref ID");
										insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());
										return false;
									}
								}
							}
						}
	        		}
				}
				
				log.debug("Configuracion Terra: Validacion Service Ref ID");
				if (prodServLocal.getFk_01_subp_atis()!=null)
				{
					if(prodServLocal.getFk_01_subp_atis().getId_srv_ref_cd()!=null){
						serviceRefId=prodServLocal.getFk_01_subp_atis().getId_srv_ref_cd();	
					}
				}
				if (serviceRefId.trim().length()<1){
					//No se envia el mensaje pk faltan datos
					// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
					log.debug("Configuracion Terra: No se encuentra Service Ref ID");
					insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());
					return false;
				}
				tr023e.setServiceReferenceId(serviceRefId); //Campo nuevo en tr001s. En atis es MSX-NUM-ICN-LGY-NU
				//Busco los correos
				String fEmail = "";
				String dEmail = "";
				InformacionAdslDTO infoActual = this.obtenerDatosActualAdsl(idPeticion);//baja o modificacion
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
				log.debug("Configuracion Terra: Validacion Father Email, Direcc Electronica");

				if (infoActual!=null) {

					// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
					log.debug("Configuracion Terra: Hay InfoActual");
					//req SVAs se elimina estavalidacion
					//if(infoActual.getFatherEmail()!=null){
						//fEmail=infoActual.getFatherEmail();
					//}
					if(infoActual.getDireccElectronica()!=null){
						dEmail= infoActual.getDireccElectronica();
					}
					
					// CR24519 - adocarmo - inicio
					// Cargo nuevas características
					String tipoSVA = null;
					String dominio1 = null;
					String dominio2 = null;
					String dominio3 = null;
					
					if(!psprim.getPsId().equals(new Long(ComunInterfaces.CENTRO_SEGURIDAD))){
						tipoSVA = infoActual.getTipoSVA();
						dominio1 = infoActual.getDominio1();
						dominio2 = infoActual.getDominio2();
						dominio3 = infoActual.getDominio3();	
					}
					
					//log.debug(">>>>>>>>>>>>>>>>>>>>>>TIPO SVA: " + tipoSVA);
					//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO1: " + dominio1);
					//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO2: " + dominio2);
					//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO3: " + dominio3);
					
					if (tipoSVA != null && !tipoSVA.equals("")) {
						tr023e.setTipoSVA(infoActual.getTipoSVA());
					}
					if (dominio1 != null && !dominio1.equals("")) {
						tr023e.setDominio1(infoActual.getDominio1());
					}
					if (dominio2 != null && !dominio2.equals("")) {
						tr023e.setDominio2(infoActual.getDominio2());
					}
					if (dominio3 != null && !dominio3.equals("")) {
						tr023e.setDominio3(infoActual.getDominio3());
					}	
					// CR24519 - adocarmo - fin					
					
					
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May - Inicio
				}else{
					InformacionAdslDTO infoNueva = this.obtenerDatosAdsl(idPeticion);//alta

					if (infoNueva!=null) {
						log.debug("Configuracion Terra: Hay InfoNueva");
						//Req Svas se elimina esta validacion
						//if(infoNueva.getFatherEmail()!=null){
						//	fEmail=infoNueva.getFatherEmail();
						//}
						if(infoNueva.getDireccElectronica()!=null){
							dEmail= infoNueva.getDireccElectronica();
						}
						
						// CR24519 - adocarmo - inicio
						// Cargo nuevas características
						String tipoSVA = null;
						String dominio1 = null;
						String dominio2 = null;
						String dominio3 = null;
						
						if(psprim.getPsId() != new Long(ComunInterfaces.CENTRO_SEGURIDAD)){
							tipoSVA = infoNueva.getTipoSVA();
							dominio1 = infoNueva.getDominio1();
							dominio2 = infoNueva.getDominio2();
							dominio3 = infoNueva.getDominio3();	
						}
						
						//log.debug(">>>>>>>>>>>>>>>>>>>>>>TIPO SVA: " + tipoSVA);
						//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO1: " + dominio1);
						//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO2: " + dominio2);
						//log.debug(">>>>>>>>>>>>>>>>>>>>>>DOMINIO3: " + dominio3);
						
						if (tipoSVA != null && !tipoSVA.equals("")) {
							tr023e.setTipoSVA(infoNueva.getTipoSVA());
					}
						if (dominio1 != null && !dominio1.equals("")) {
							tr023e.setDominio1(infoNueva.getDominio1());
				}
						if (dominio2 != null && !dominio2.equals("")) {
							tr023e.setDominio2(infoNueva.getDominio2());
						}
						if (dominio3 != null && !dominio3.equals("")) {
							tr023e.setDominio3(infoNueva.getDominio3());
						}	
						// CR24519 - adocarmo - fin
					}
				}
				
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May - FIN
				
				/*
				 *	CR 00024857 - Jun 5, 2009 - 16
				 *		Se comenta control. Se hace en comparacion a Aula - German P.
				 */
				/* 
				  if(fEmail.trim().length()<1 || dEmail.trim().length()<1){
					//No se envia el mensaje pk faltan datos
					insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());
					return false;
				}*/
				//dcardena REquerimiento SVAs
				//se cambia la logica del fatherEmail se envia la cedula de ciudadania 
				fEmail =peticionLocal.getNum_doc_cli_cd();
				tr023e.setFatherEmail(fEmail);
				//fin requerimiento SVAS
				
				
				//consultamos y guardamos los ps en de la peticion en una colletion
				Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
//				se valida que en la colletion hayan ps	
				if(ps!=null && ps.size()>0)
				{
					log.debug("Hay Producto Servicios "+ ps.size());
					//se recorre el colletion de ps de la tabla producto servico peticion
					for (Iterator iter = ps.iterator(); iter.hasNext();) 
					{
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						Producto_servicio_peticionLocal elementproduct = (Producto_servicio_peticionLocal) iter.next();
								//se obtiene por medio de la subpeticion atis la tabla subpeticion_caracteristicas
								Subpeticion_atisLocal subPet = elementproduct.getFk_01_subp_atis();							
								//se las caracteristicas obtenidas se almacenas en una colletion
								Collection subPetCaracteristicas =subPet.getSubpeticion_caracteristicas();
								// se seea en true la consulta de correo para no volver a ejecutar estas consultas

//								variable para contar cuantas veces se ha consultado el correo
								//se valida si hay caracteristicas
								if(subPetCaracteristicas!=null && subPetCaracteristicas.size()>0)
								{
									log.debug("Hay caracteristicas en la peticion,se esta consultando el correo del usuario");
									// se recorren las caracterisiticas obtenidas
									for (Iterator iterCaract = subPetCaracteristicas.iterator(); iterCaract.hasNext();) 
									{				
										//guardamos en la clase Subpeticion_caracteristicasLocal cada caracteristica obtenida de la cual obtendremos los datos de la tabla Subpeticion_caracteristicas
										Subpeticion_caracteristicasLocal elementCaract = (Subpeticion_caracteristicasLocal) iterCaract.next();
										Subpeticion_caracteristicasKey subpeticionCaracKey = (Subpeticion_caracteristicasKey)elementCaract.getPrimaryKey();
										
										//se alamacenan los datos que nesitamos en nuestras variables
										Long codigoCaracteristica=subpeticionCaracKey.getCod_crc_cd();
					
										
										log.debug("Se encontro la siguiente caracteristica en la peticion: "+ elementCaract.getNom_crc_no());
										//se valida que el codigoCaracteristica = 34 y nombreCaracteristica = DIRECC ELECTRONICA y que ademas haya un corrreo electronico
										if(codigoCaracteristica.intValue() == ComunInterfaces.caracteristicaMailAsistencia)
										{
											//seteamos el correo
											dEmail=elementCaract.getVal_ral_crc_cd();
											log.debug("El correo del usuario fue encontrado: "+dEmail);
											break;
										}
										
									}
								}else{
									//else que indica que no hay caracteristicas dentro de las cuales debe estar el correo, se deriba PGI
									log.debug("No hay caracteristicas en la peticion y el correo del usuario no puede ser consultado para configurar Napster");
								}							
				}
			}

				tr023e.setSecondEmail(dEmail);
				//*******
				
				// CR24519 - adocarmo - inicio	
			if(peticionLocal.getTel_cot_ds()!=null && !peticionLocal.getTel_cot_ds().equals("")){
				tr023e.setContactPhone(peticionLocal.getTel_cot_ds());
			}else if(peticionLocal.getTel_cot_ds()==null && peticionLocal.getNum_ide_nu_stb()!=null && !peticionLocal.getNum_ide_nu_stb().equals("")){
				tr023e.setContactPhone(peticionLocal.getNum_ide_nu_stb());
			}else{
				Recursos_linea_basicaLocalHome rlbh =(Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal rlb=rlbh.findByPeticion(idPeticion);
				if(rlb.getTelefono_asg()!=null){
					tr023e.setContactPhone(rlb.getTelefono_asg().toString());
				}else{
					tr023e.setContactPhone("0");//Se coloca 0 como valor por defecto 
				}
			}
				//log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TEL DE CONTACTO:" + peticionLocal.getTel_cot_ds());
				// CR24519 - adocarmo - fin
				
				//Id mensaje
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr023e.setId(String.valueOf(IdCorrelativo));
				
				tr023e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue()); //Atis request number
				tr023e.setClientDocument(peticionLocal.getNum_doc_cli_cd()); //es el numero de documento
				tr023e.setClientName(peticionLocal.getNom_ds());
				tr023e.setClientFirstLastName(peticionLocal.getPri_ape_ds());
				tr023e.setClientSecondLastName(peticionLocal.getSeg_ape_ds());
				tr023e.setContactName(peticionLocal.getNom_int_ds());
				tr023e.setContactFirstLastName(peticionLocal.getPri_ape_int_ds());
				tr023e.setContactSecondLastName(peticionLocal.getSeg_ape_int_ds());
				
				/*
				 *	CR 00024857 - May 25, 2009 - 5
				 *		Se agrega el telefono a la TR - German P.
				 */
				//Requerimiento SVAs se modifica logica del tag phone-number = idpc @cardena
				//				validamos si el numero esta nulo o si contiene datos
				Integer phoneNumber = null;
				if(peticionLocal.getNum_ide_nu_stb()!=null && !peticionLocal.getNum_ide_nu_stb().equals("")){
					//else que nos informa que hay numero en STB
					try{
						log.debug("Hay numero Telefonico de STB: "+peticionLocal.getNum_ide_nu_stb());
						phoneNumber = new Integer(peticionLocal.getNum_ide_nu_stb());
					}catch(NumberFormatException ex){
						log.debug("El id_pc corresponde a un ID de EQUIPO se setea el còdigo del cliente");
						phoneNumber = new Integer(peticionLocal.getCod_cli_cd().intValue());
					}
					
					
				}else if(peticionLocal.getNum_ide_nu_tv()!=null && !peticionLocal.getNum_ide_nu_tv().equals("") )
				{
					log.debug("Hay telefono en TV se debe Dar formato: "+peticionLocal.getNum_ide_nu_tv());
					//si contiene datos se debe eliminar TV y el numero final
					String phoneNumberTV=peticionLocal.getNum_ide_nu_tv();
					phoneNumber=new Integer(phoneNumberTV.substring(3,phoneNumberTV.length()-2));
									
				}
				
				
				log.debug("Se setea a la TR023E el valor phoneNumber: " + phoneNumber);
				tr023e.setPhoneNumber(phoneNumber);
				//fin requerimiento SVAs
				
				tr023e.setPassword(peticionLocal.getNum_doc_cli_cd());
				tr023e.setProductServiceCode(psprim.getPsId().longValue());
			
			/*RQ.6142 - mfmendez - WebService Aula - Terra*/
			String ocSVA = validaOperacionComercialSVA(act, act.getNumeroPeticion());
			if(idOCCP==new Long(0)|| idOCCP.equals(new Long(0)))
			{
				
			if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA)){						
				tr023e.setComercialOperation(Long.parseLong(ComunInterfaces.OC_MODIFICACION_SVA));
			}else{
				
				Operacion_comercialLocalHome ocLocalhome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
				Operacion_comercialKey ocKey = null;
				Operacion_comercialLocal ocLocal = null;
				
				if (reversa){
					ocKey = new Operacion_comercialKey(psprim.getOpComRevId());					
				}else{
					ocKey = new Operacion_comercialKey(psprim.getOpComId());
				}								
				ocLocal = ocLocalhome.findByPrimaryKey(ocKey);
				
				String opCoSVADB = ocLocal.getOpco_sva();
				
				if(opCoSVADB != null && opCoSVADB.equals(ComunInterfaces.VALOR_MODIFICACION_OPCO_SVA)){
					tr023e.setComercialOperation(Long.parseLong(ComunInterfaces.OC_MODIFICACION_SVA));
				}else{
					if (reversa){										
					tr023e.setComercialOperation(psprim.getOpComRevId().longValue());
				}else{
					tr023e.setComercialOperation(psprim.getOpComId().longValue()); 
				}				
				}
			}
			}else{
				tr023e.setComercialOperation(idOCCP.longValue());
			}
			/*FIN RQ.6142 - mfmendez - WebService Aula - Terra*/
						
				// Insertamos el Registro de Mensajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaBandaAncha));
				mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(act.getCodigoActividad());
				mensajeEstadoBALocal.setDesc_error(new Long(psprim.getPsId().longValue()).toString());

				ConfiguracionTerraBAMQ configuracionTerraBAMQ= new ConfiguracionTerraBAMQ();
				configuracionTerraBAMQ.enviarMensaje(tr023e);
				return true;
			}catch (Exception e)
			{
				log.error("Error al enviar Configuracion Terra.",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}

		public void recepcionConfiguracionTerra(TR023S tr023s) throws ATiempoAppEx {

			// CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
		
			try
			{
				bExecution = BackendTraceUtil.initExecution(tr023s, log);
				bExecution.setNumPetAtiempo(new Long(tr023s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr023s.getId());
				bExecution.startOperation();
	
				validaHome () ;
				Mensaje_estado_baLocal mensaje_estado_ba;
				try {
					mensaje_estado_ba =	mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr023s.getId())));
				} catch (FinderException e1)
				{
					mensaje_estado_ba=null;
				}
    
				if (mensaje_estado_ba == null)
				{
					log.debug (
						"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall (tr023s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr023s.getId(),ATiempoAppEx.EXCEPTION);
				}
    
				// Validacion del estado de la respuesta
    
				Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_ba.getMensaje_estado ().getPrimaryKey () ;
    
				if (mensaje_estadoKey.cod_estado.intValue () != estadoEspera)
				{
					log.debug (
						"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall (tr023s));
					return;
				}
				
				PeticionLocal peticion = mensaje_estado_ba.getPeticion();
				PeticionKey pet=(PeticionKey )peticion.getPrimaryKey();
				//PeticionKey pet=(PeticionKey )mensaje_estado_ba.getPeticion().getPrimaryKey();
							
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensaje_estado_ba.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pet.peti_numero, mensaje_estado_ba.getCod_actividad_generadora());
				String codeError= "";
				if (tr023s.getCna()!=null){
					codeError=tr023s.getCna(); 
				}
				if(tr023s.isError() || (codeError!= null && !"".equals(codeError.trim()) && !"000".equals(codeError)))//Viene un quiebre
				{
					// Se elimina ControlTerra
					//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "S");
					
					// Si hay error seteo la habilidad Terra en 1 para que la peticion sea asignada a un usuario que posea dicha habilidad
					peticion.setHab_terra(new Integer(1));
					
					log.debug("Se setea el valor " + peticion.getHab_terra() + " a la habilidad terra de la petición.");
					
					if ((codeError == null) || (codeError.trim().length()==0) || ("000".equals(codeError)))
					{//Error generico
						insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						actDto.setObservacion("Error en la Configuracion Terra. " + tr023s.getErrorMessage()+". ");
					}else
					{//Error especifico
						
						String errorTerra = codeError;
						if(tr023s.getStatusMessage()!=null){
							errorTerra = errorTerra + "." + tr023s.getStatusMessage() + "."; 
						}
						//DAVID: Inicio requerimiento quiebres CDS-SB
						System.out.println("Acá recibimos un error específico que se redireccionará a control terra o a PGI: "+errorTerra);
						if("701".equals(codeError)){
							System.out.println("Error generado por uno de los siguientes códigos: Xml inválido=3, Error comunicación 3=18, Error comunicación 2=19, Error comunicación=20, Campo obligatorio nulo=21, se va para: PGI SOPORTE");
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "N");
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							actDto.setObservacion("Error generado por uno de los siguientes códigos: Xml inválido=3, Error comunicación 3=18, Error comunicación 2=19, Error comunicación=20, Campo obligatorio nulo=21, se deriva a PGI / " + " Respuesta:  tr-id: " + tr023s.getId() + " - cna: " + tr023s.getCna() + " -  mensaje de error: " +  tr023s.getErrorMessage(),true);
						}
						else if("911".equals(codeError)){
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "S");
							System.out.println("Error generado por uno de los siguientes códigos: Error de datos del mensaje=2, Duplicidad de Petición=4, El usuario solicitado para cambio de datos no existe=10, El usuario ya existe=90, Para baja no existe=94, Indefinido=99, se va para: PGI SOPORTE");
						}
						//Fin: DAVID
						actDto.setObservacion("Error en la Configuracion Terra. Codigo:"+ errorTerra);
						
						/*
						 *	CR 00024857 - May 25, 2009 - 14
						 *		Se controla codigo de error para el envio a la plataforma correspondiente - German P.
						 */
						String nombreClase = TR023S.class.getName();
						nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						
						ErrorLegadoLocal errorLegado = getErrorLegado(codeError, nombreClase);
						
						if (errorLegado != null){
							
							String plataforma = errorLegado.getPlataforma();
							if (plataforma != null && !plataforma.equals("")){
								
								plataforma = plataforma.trim();
								
								if (plataforma.equals(VpistbbaConfig.getVariable("IDPGI")) || plataforma.equals(VpistbbaConfig.getVariable("IDPGC"))){
									log.debug("Se envia petición a plataforma " + plataforma);
									actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
								}	
							}	

							Long idCausa = errorLegado.getIdCausa();
							insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), idCausa, actDto.getIdActividadFlujo());
							
						}else{
							// en caso de errorLegado = null se deriva por defecto a PGI
							log.debug("Se envia petición a Soluciones Tecnicas.");
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						}
						mensaje_estado_ba.setId_error("6");						
					}
				}
				
				// Se elimina ControlTerra
				/*
				else{
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "N");
				}
				*/
				// INICIO NB 14517	-- Resuelto 20/06/2008	-- Pablo L		
				Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr023s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal ;
				try
				{
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
				}
				catch (FinderException fex)
				{
					mensajeEstadoBaLocal = null ;
				}
				if (mensajeEstadoBaLocal == null)
				{
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr023s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr023s.getId(),ATiempoAppEx.EXCEPTION);
				}
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));				
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				mensajeEstadoBaLocal.setDesc_error(mensajeEstadoBaLocal.getDesc_error());
				mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
				// FIN NB 14517 -- Resuelto 20/06/2008	-- Pablo L
				
				//TODO: 12/01/2009 - Raúl Ernesto Triviño 
				try{
					Collection listaMensajeLocal =  mensajeEstadoBaLocalHome.findEstadoPeticion(pet.peti_numero, new Integer(3));
					if (listaMensajeLocal.isEmpty()){
						listaMensajeLocal =  mensajeEstadoBaLocalHome.findByPetiNumeroErrorActividad(pet.peti_numero, "6", mensaje_estado_ba.getCod_actividad_generadora());					
						if (!listaMensajeLocal.isEmpty()){
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra, "N");
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							actDto.setObservacion("Error generado por uno de los siguientes códigos: Xml inválido=3, Error comunicación 3=18, Error comunicación 2=19, Error comunicación=20, Campo obligatorio nulo=21, se deriva a PGI / " + " Respuesta:  tr-id: " + tr023s.getId() + " - cna: " + tr023s.getCna() + " -  mensaje de error: " +  tr023s.getStatusMessage(),true);
						}
						actividadEJB.terminar(actDto);
					}
				}catch(Exception ex){
					System.out.println("Error en conexion con tabla mesaje Estado ba: " + ex);
				}
				
				//End TODO
			}
			catch (Exception e)
			{
				bExecution.setErrorOp(true);
				log.error("Error al recibir configuracion Terra",e);
				throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
			}
			finally{  
				bExecution.endAll();
				// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}

		}

	public void marcarNovedadAutomaticaCT(Long numPet) throws ATiempoAppEx
	{
		try
		{
			Fecha fecha=new Fecha();
			Producto_servicio_peticionLocal psCDs=null;
			boolean ciclo=true;
			Long caracDirElec=new Long(VpistbbaConfig.getVariable("DIRECCELECTRONICA"));
			Long novedadAutoCT=new Long(VpistbbaConfig.getVariable("NOVEDADAUTOTERRA"));
			String codActi=VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONFIGTERR");
			
			if(mensajeEstadoBaLocalHome==null)
				mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			if(mensaje_estadoLocalHome==null)
				mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
			Mensaje_estado_baLocal local=mensajeEstadoBaLocalHome.findPeticionActividadEstado(numPet,new Integer(estadoEspera),codActi);
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			local.setMensaje_estado(mensajeOk);
			local.setFecha_cierre(fecha.getFechaconFormato(11));
			if(peticionLocalHome==null)
				peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(numPet));
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal) iterator.next();
				Subpeticion_atisLocal subpeticion_atisLocal=psp.getFk_01_subp_atis();
				for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
				{
					Subpeticion_caracteristicasLocal sub=(Subpeticion_caracteristicasLocal) iterator2.next();
					Subpeticion_caracteristicasKey spck=(Subpeticion_caracteristicasKey) sub.getPrimaryKey();
					if(spck.cod_crc_cd.longValue()==caracDirElec.longValue())
					{
						psCDs=psp;
						ciclo=false;
						break;	
					}
				}
				if(!ciclo)
					break;
			}
			if(psCDs==null)
			{
				log.debug("No se encontró el ps de cds");
				return;
			}
			
			if(usuarioHome==null)
				usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			if(catalogo_causalHome==null)
				catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			if(estado_psHome==null)
				estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			if(estado_ps_peticionHome==null)
				estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			if(causal_peticionHome==null)
				causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
			Long idAct=actividadSessionLocal.getIdActividad(codActi,new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			if(idAct==null)
				return;	
			Collection listaEstadoPsPet=psCDs.getEstado_ps_peticion();
			
			if(listaEstadoPsPet.size()>0)
			{	

				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(novedadAutoCT);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);

				//Tengo una causa asociada tengo que actualizar
				Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
				estado_ps_peticionLocal.setCod_actividad(idAct);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());

				Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);

				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
			else
			{

				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(novedadAutoCT);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);

				long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
				Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),psCDs.getProducto_servicio(),psCDs);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				estado_ps_peticionLocal.setCod_actividad(idAct);


				Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
			
		}
		catch (Exception e)
		{
			log.error("Error al Tratar de Marcar la novedad Automatica para Configurar Terra",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
	}	

//	private ErrorLegadoLocal getErrorLegado(Long codigoError,String codigoTr){
//		ErrorLegadoLocal errorLegado = null;
//		try{
//			ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
//			errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
//		} catch (javax.ejb.FinderException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//	 //TODO ver que pasa con esta excepcion
//			e.printStackTrace();
//		}
//		return errorLegado;
//	}
	// CR9664 - adocarmo - inicio
	//public boolean enviaConfiguracionAula365 (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim,boolean reversa) throws ATiempoAppEx{
	public boolean enviaConfiguracionAula365 (Long idPeticion, ActividadEJBDTO act, PsVsOcVO psprim,Long licenseType,boolean reversa) throws ATiempoAppEx{
			try{
			
				validaHome ();
			
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				
				TR024E tr024e = new TR024E();
				//******* Campos Obligatorios para enviar el mensaje
				//Busco el Service Reference ID... usado solo en el PS de CDS
				Producto_servicio_peticionKey pspK = new Producto_servicio_peticionKey();
				pspK.correlativo = psprim.getCorrelativo();
				pspK.fk_psp_pet_peti_numero = idPeticion; 
				Producto_servicio_peticionLocal prodServLocal = producto_servicio_peticionLocalHome.findByPrimaryKey(pspK);
				String serviceRefId = "";
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
				log.debug("Configuracion Aula365: Validacion Service Ref ID");
				if (prodServLocal.getFk_01_subp_atis()!=null)
				{
					if(prodServLocal.getFk_01_subp_atis().getId_srv_ref_cd()!=null){
						serviceRefId=prodServLocal.getFk_01_subp_atis().getId_srv_ref_cd();		
					}
				}
				if (serviceRefId.trim().length()<1){
					//No se envia el mensaje pk faltan datos
					// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
					log.debug("Configuracion : No se encuentra Service Ref ID");
					insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());
					return false;
				}
				tr024e.setServiceReferenceId(serviceRefId); //Campo nuevo en tr001s. En atis es MSX-NUM-ICN-LGY-NU
				//Busco los correos
				String fEmail = "";
				String dEmail = "";
				InformacionAdslDTO infoActual = this.obtenerDatosActualAdsl(idPeticion);//baja o modificacion
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
				log.debug("Configuracion : Validacion Father Email, Direcc Electronica");
				if (infoActual!=null) {
					// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May
					log.debug("Configuracion : Hay InfoActual");
					//Req Svas se elimina esta validacion
					//if(infoActual.getFatherEmail()!=null){
					//	fEmail=infoActual.getFatherEmail();
					//	log.debug("FATHER EMAIL: " + fEmail );
					//}
					if(infoActual.getDireccElectronica()!=null){
						dEmail= infoActual.getDireccElectronica();
					}
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May - Inicio
				}else{
					InformacionAdslDTO infoNueva = this.obtenerDatosAdsl(idPeticion);//alta
					if (infoNueva!=null) {
						log.debug("Configuracion : Hay InfoNueva");
						//Req Svas se elimina esta validacion
						//if(infoNueva.getFatherEmail()!=null){
							//fEmail=infoNueva.getFatherEmail();
						//}
						if(infoNueva.getDireccElectronica()!=null){
							dEmail= infoNueva.getDireccElectronica();
						}
					}
				}
				// Defecto 12790 - AT-1253 - Error corregido por Victor Molina el 29/May - FIN
				
				/*
				if(fEmail.trim().length()<1 || dEmail.trim().length()<1){
					//No se envia el mensaje pk faltan datos
					insertarCausalesCnaPeticion(peticionLocal, act.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), act.getIdActividadFlujo());
					return false;
				}
				*/
				//dcardena Requerimiento SVAs
				//se cambia la logica del fatherEmail se envia la cedula de ciudadania 
				fEmail =peticionLocal.getNum_doc_cli_cd();
				tr024e.setFatherEmail(fEmail);
				//fin requerimiento SVAS

				//consultamos y guardamos los ps en de la peticion en una colletion
				Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
//				se valida que en la colletion hayan ps	
				if(ps!=null && ps.size()>0)
				{
					log.debug("Hay Producto Servicios "+ ps.size());
					//se recorre el colletion de ps de la tabla producto servico peticion
					for (Iterator iter = ps.iterator(); iter.hasNext();) 
					{
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						Producto_servicio_peticionLocal elementproduct = (Producto_servicio_peticionLocal) iter.next();
								//se obtiene por medio de la subpeticion atis la tabla subpeticion_caracteristicas
								Subpeticion_atisLocal subPet = elementproduct.getFk_01_subp_atis();							
								//se las caracteristicas obtenidas se almacenas en una colletion
								Collection subPetCaracteristicas =subPet.getSubpeticion_caracteristicas();
								// se seea en true la consulta de correo para no volver a ejecutar estas consultas

//								variable para contar cuantas veces se ha consultado el correo
								//se valida si hay caracteristicas
								if(subPetCaracteristicas!=null && subPetCaracteristicas.size()>0)
								{
									log.debug("Hay caracteristicas en la peticion,se esta consultando el correo del usuario");
									// se recorren las caracterisiticas obtenidas
									for (Iterator iterCaract = subPetCaracteristicas.iterator(); iterCaract.hasNext();) 
									{				
										//guardamos en la clase Subpeticion_caracteristicasLocal cada caracteristica obtenida de la cual obtendremos los datos de la tabla Subpeticion_caracteristicas
										Subpeticion_caracteristicasLocal elementCaract = (Subpeticion_caracteristicasLocal) iterCaract.next();
										Subpeticion_caracteristicasKey subpeticionCaracKey = (Subpeticion_caracteristicasKey)elementCaract.getPrimaryKey();
										
										//se alamacenan los datos que nesitamos en nuestras variables
										Long codigoCaracteristica=subpeticionCaracKey.getCod_crc_cd();
					
										
										log.debug("Se encontro la siguiente caracteristica en la peticion: "+ elementCaract.getNom_crc_no());
										//se valida que el codigoCaracteristica = 34 y nombreCaracteristica = DIRECC ELECTRONICA y que ademas haya un corrreo electronico
										if(codigoCaracteristica.intValue() == ComunInterfaces.caracteristicaMailAsistencia)
										{
											//seteamos el correo
											dEmail=elementCaract.getVal_ral_crc_cd();
											log.debug("El correo del usuario fue encontrado: "+dEmail);
											break;
										}
										
									}
								}else{
									//else que indica que no hay caracteristicas dentro de las cuales debe estar el correo, se deriba PGI
									log.debug("No hay caracteristicas en la peticion y el correo del usuario no puede ser consultado para configurar Napster");
								}							
				}
			}

				tr024e.setSecondEmail(dEmail);

				
				//Id mensaje
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr024e.setId(String.valueOf(IdCorrelativo));
				
				tr024e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue()); //Atis request number
				tr024e.setClientDocument(peticionLocal.getNum_doc_cli_cd()); //es el numero de documento
				tr024e.setClientName(peticionLocal.getNom_ds());
				tr024e.setClientFirstLastName(peticionLocal.getPri_ape_ds());
				tr024e.setClientSecondLastName(peticionLocal.getSeg_ape_ds());
				tr024e.setContactName(peticionLocal.getNom_int_ds());
				tr024e.setContactFirstLastName(peticionLocal.getPri_ape_int_ds());
				//TODO: 18022010 - RETA - Adición del campo phone number
				//Requerimiento SVAs se modifica logica del tag phone-number = idpc @cardena
				//				validamos si el numero esta nulo o si contiene datos
				Integer phoneNumber = null;
				if(peticionLocal.getNum_ide_nu_stb()!=null && !peticionLocal.getNum_ide_nu_stb().equals("")){
					//else que nos informa que hay numero en STB
					try{
						log.debug("Hay numero Telefonico de STB: "+peticionLocal.getNum_ide_nu_stb());
						phoneNumber = new Integer(peticionLocal.getNum_ide_nu_stb());
					}catch(NumberFormatException ex){
						log.debug("El id_pc corresponde a un ID de EQUIPO se setea el còdigo del cliente");
						phoneNumber = new Integer(peticionLocal.getCod_cli_cd().intValue());
					}
					
					
				}else if(peticionLocal.getNum_ide_nu_tv()!=null && !peticionLocal.getNum_ide_nu_tv().equals("") )
				{
					log.debug("Hay telefono en TV se debe Dar formato: "+peticionLocal.getNum_ide_nu_tv());
					//si contiene datos se debe eliminar TV y el numero final
					String phoneNumberTV=peticionLocal.getNum_ide_nu_tv();
					phoneNumber=new Integer(phoneNumberTV.substring(3,phoneNumberTV.length()-2));
									
				}
				
				log.debug("Se setea a la TR024E el valor phoneNumber: " + phoneNumber);
				tr024e.setPhoneNumber(phoneNumber.intValue());
				//fin requerimiento SVAs
				//tr024e.setPhoneNumber(new Integer(peticionLocal.getNum_ide_nu_stb()).intValue());
				//End TODO
				tr024e.setContactSecondLastName(peticionLocal.getSeg_ape_int_ds()); 

				//tr024e.setPassword(peticionLocal.getNum_doc_cli_cd());
				
				//if (psprim.getPsId() != null) {
				if (licenseType != null) {
					tr024e.setLicenseType(licenseType.toString());
				}
				else {
					tr024e.setLicenseType("");
				}
								
			/*RQ.6142 - mfmendez - WebService Aula - Aula*/
			String ocSVA = validaOperacionComercialSVA(act, act.getNumeroPeticion());
			
			if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA)){				
				tr024e.setComercialOperation(Long.parseLong(ComunInterfaces.OC_MODIFICACION_SVA));
			}else{
				Operacion_comercialLocalHome ocLocalhome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
				Operacion_comercialKey ocKey = null;
				Operacion_comercialLocal ocLocal = null;
				
				if (reversa){
					ocKey = new Operacion_comercialKey(psprim.getOpComRevId());					
				}else{
					ocKey = new Operacion_comercialKey(psprim.getOpComId());
				}								
				
				ocLocal = ocLocalhome.findByPrimaryKey(ocKey);
				
				String opCoSVADB = ocLocal.getOpco_sva();
				
				if(opCoSVADB != null && opCoSVADB.equals(ComunInterfaces.VALOR_MODIFICACION_OPCO_SVA)){
					tr024e.setComercialOperation(Long.parseLong(ComunInterfaces.OC_MODIFICACION_SVA));
				}else{
					if (reversa){
					tr024e.setComercialOperation(psprim.getOpComRevId().longValue());
				}else{
					tr024e.setComercialOperation(psprim.getOpComId().longValue()); 
				}				
				}
				
			}
			/*FIN RQ.6142 - mfmendez - WebService Aula - Aula*/
				// Insertamos el Registro de Mensajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaBandaAncha));
				mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(act.getCodigoActividad());

				ConfiguracionAula365BAMQ configuracionAula365BAMQ= new ConfiguracionAula365BAMQ();
				configuracionAula365BAMQ.enviarMensaje(tr024e);
								
				return true;
			}catch (Exception e)
			{
				log.error("Error al enviar Configuracion .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}
	// CR9664 - adocarmo - fin
	
	
	// CR9664 - adocarmo - inicio		
	public void recepcionConfiguracionAula365(TR024S tr024s) throws ATiempoAppEx {
		validaHome ();
		try
		{
			Mensaje_estado_baLocal mensaje_estado_ba;
			try {
				mensaje_estado_ba =
					mensajeEstadoBaLocalHome.findByPrimaryKey(
						new Mensaje_estado_baKey(new Long(tr024s.getId())));
			} catch (FinderException e1)
			{
				mensaje_estado_ba=null;
			}
    
			if (mensaje_estado_ba == null)
			{
				log.debug (
					"No Existe Respuesta en la base de enviados\n "
					+ XMLUtilities.marshall (tr024s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr024s.getId(),ATiempoAppEx.EXCEPTION);
			}
    
			// Validacion del estado de la respuesta
    
			Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensaje_estado_ba.getMensaje_estado ().getPrimaryKey () ;
    
			if (mensaje_estadoKey.cod_estado.intValue () != estadoEspera)
			{
				log.debug (
					"El estado de la respuesta es diferente para ser procesado\n "
					+ XMLUtilities.marshall (tr024s));
				return;
			}
			PeticionKey pet=(PeticionKey )mensaje_estado_ba.getPeticion().getPrimaryKey();
				
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensaje_estado_ba.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pet.peti_numero, mensaje_estado_ba.getCod_actividad_generadora());
			String codeError= "";
			if (tr024s.getErrorCode()>0){
				codeError= Long.toString(tr024s.getErrorCode());
			}
			
			// Si respuesta fue error envío petición a Control Aula
			if(tr024s.isError() || (codeError!= null && !"".equals(codeError.trim()) && !"000".equals(codeError)))//Viene un quiebre
			{
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA.control_aula, "AulaS");
				if ((codeError == null) || (codeError.trim().length()==0) || ("000".equals(codeError)))
				{//Error generico
					insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					actDto.setObservacion("Error en la Configuracion Aula365. " + tr024s.getErrorMessage()+". ");
				}else
				{//Error especifico
					insertarCausalesCnaPeticion(mensaje_estado_ba.getPeticion(), mensaje_estado_ba.getCod_actividad_generadora(), new Long(codeError), actDto.getIdActividadFlujo());
					String errorAula365 = codeError;
					if(tr024s.getErrorCodeMessage()!=null){
						errorAula365 = errorAula365 + "." + tr024s.getErrorCodeMessage() + "."; 
					}
					actDto.setObservacion("Error en la Configuracion Aula365. Codigo:"+ errorAula365);
				}
			}else{
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA.control_aula, "AulaN");
			}
			
			// INICIO NB 14517	-- Resuelto 20/06/2008	-- Pablo L		
			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr024s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;
			try
			{
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			}
			catch (FinderException fex)
			{
				mensajeEstadoBaLocal = null ;
			}
			if (mensajeEstadoBaLocal == null)
			{
				log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr024s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr024s.getId(),ATiempoAppEx.EXCEPTION);
			}
			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));				
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			// FIN NB 14517 -- Resuelto 20/06/2008	-- Pablo L			
			
			
			actividadEJB.terminar(actDto);
		}
		catch (Exception e)
		{
			log.error("Error al recibir configuracion Aula365",e);
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
		}
	}
	// CR9664 - adocarmo - fin

//	CR10394 - Pablo Cawen - Inicio
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#getDatosPetAMxFile(long)
	 */
	public AccionesMasivasByFileMSGDTO getDatosPetAMxFile(long nroPet) throws ATiempoAppEx {
		String sqlPetiAtis = "SELECT VPISTBBA.PETICION.COD_PET_CD  FROM VPISTBBA.PETICION " +
												"WHERE VPISTBBA.PETICION.PETI_NUMERO = ?";
				
		String sqlCodActiv = "SELECT AC.ACT_CODIGO  AS COD " +
							 "FROM ATIEMPO.ACTIVIDAD AC, VPISTBBA.BITACORA_PETICION BP " +
							 "WHERE BP.BIPE_ID = (" +
							 "SELECT max(b.bipe_id) " +
							 "FROM VPISTBBA.BITACORA_PETICION B " + 
							 "WHERE B.PETI_NUMERO = ?" +
							 ") " +
							 "AND AC.ACT_ID = BP.ACT_ID";

		String sqlInstAct = "SELECT BI.BI_URL_DETALLE " +
							"FROM ATIEMPO.BINTEGRADA BI " +
							"WHERE BI_NRO_PETICION = ? " +
							"AND BI_VISIBLE = 1";

		String codAtis = "";
		String codAct = "";
		String urlInstAct = ""; 

		Connection conn=null;
		PreparedStatement pstmt=null;			
		ResultSet rs=null;
		RecursosBALocalHome rbaHome = null;
		RecursosBALocal rba = null;

//				long nroPet = new Long(strPeti).longValue();

		try
		{
			rbaHome = (RecursosBALocalHome)HomeFactory.getHome(RecursosBALocalHome.JNDI_NAME);
			rba = rbaHome.create();
			conn=DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			//******Obtengo nro peticion atis*****
			pstmt=conn.prepareStatement(sqlPetiAtis);
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			if(rs.next())
				codAtis = rs.getString("COD_PET_CD");
			//************************************
			//******obtengo codigo actividad******
			pstmt=conn.prepareStatement(sqlCodActiv);
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			if(rs.next())
				codAct = rs.getString("COD");
			//************************************
			//********Obtenco Instancia actividad*****
			pstmt=conn.prepareStatement(sqlInstAct);
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			if(rs.next())
				urlInstAct = rs.getString("BI_URL_DETALLE");
			//****************************************
		}
		catch(Exception e){
//					log.error("Exception", e);
		}
		finally
		{
			com.telefonica_chile.atiempo.utiles.Utiles.closeConn(rs, pstmt, conn);
		}

		AccionesMasivasByFileMSGDTO aM = new AccionesMasivasByFileMSGDTO();
		aM.setNroPeticionAtis(codAtis);
		aM.setCodigoActividad(codAct);
		aM.setInstanciaActividad(urlInstAct);
		return aM;
	}
//	CR10394 - Pablo Cawen - Fin
	public void marcarNovedadAutomaticaCTAula(Long numPet) throws ATiempoAppEx
	{
		try
		{
			Fecha fecha=new Fecha();
			Producto_servicio_peticionLocal psCDs=null;
			boolean ciclo=true;
			Long caracDirElec=new Long(VpistbbaConfig.getVariable("DIRECCELECTRONICA"));
			Long novedadAutoCT=new Long(VpistbbaConfig.getVariable("NOVEDADAUTOTERRA"));
			String codActi=VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONFIGAULA");
			
			if(mensajeEstadoBaLocalHome==null)
				mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			if(mensaje_estadoLocalHome==null)
				mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
			Mensaje_estado_baLocal local=mensajeEstadoBaLocalHome.findPeticionActividadEstado(numPet,new Integer(estadoEspera),codActi);
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			local.setMensaje_estado(mensajeOk);
			local.setFecha_cierre(fecha.getFechaconFormato(11));
			if(peticionLocalHome==null)
				peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(numPet));
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal) iterator.next();
				Subpeticion_atisLocal subpeticion_atisLocal=psp.getFk_01_subp_atis();
				for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
				{
					Subpeticion_caracteristicasLocal sub=(Subpeticion_caracteristicasLocal) iterator2.next();
					Subpeticion_caracteristicasKey spck=(Subpeticion_caracteristicasKey) sub.getPrimaryKey();
					if(spck.cod_crc_cd.longValue()==caracDirElec.longValue())
					{
						psCDs=psp;
						ciclo=false;
						break;	
					}
				}
				if(!ciclo)
					break;
			}
			if(psCDs==null)
			{
				log.debug("No se encontró el ps de cds");
				return;
			}
			
			if(usuarioHome==null)
				usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			if(catalogo_causalHome==null)
				catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			if(estado_psHome==null)
				estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			if(estado_ps_peticionHome==null)
				estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			if(causal_peticionHome==null)
				causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
			Long idAct=actividadSessionLocal.getIdActividad(codActi,new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			if(idAct==null)
				return;	
			Collection listaEstadoPsPet=psCDs.getEstado_ps_peticion();
			
			if(listaEstadoPsPet.size()>0)
			{	

				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(novedadAutoCT);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);

				//Tengo una causa asociada tengo que actualizar
				Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) listaEstadoPsPet.iterator().next();
				estado_ps_peticionLocal.setCod_actividad(idAct);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());

				Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);

				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
			else
			{

				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(novedadAutoCT);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);

				long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
				Estado_ps_peticionLocal estado_ps_peticionLocal=estado_ps_peticionHome.create(new Long(correlativo),psCDs.getProducto_servicio(),psCDs);
				estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
				estado_ps_peticionLocal.setCod_estado_cierre(new Integer(1));
				estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				estado_ps_peticionLocal.setCod_actividad(idAct);


				Estado_psKey estado_psKey=new Estado_psKey(new Long(1));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(idAct);
			}
			
		}
		catch (Exception e)
		{
			log.error("Error al Tratar de Marcar la novedad Automatica para Configurar Aula",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
	}	


//	TODO: CR4860 SIGES - GUSTAVOG
	//DESDE AQUI OBTENER CUENTA DE CORREO
	//Envio Obtener Cuenta de Correo
	//Desde aqui con parametro idServicio
	public void obtenerCuentaCorreoSigres(Long idPeticion,String idServicio,String idActividad,PsVsOcVO psprim) throws ATiempoAppEx {

		try {

			validaHome();

			Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

			boolean esRefrescar = false;
			boolean esRefrescarNew = false;
			if (idActividad.equals("")) {
				esRefrescar = true;
				esRefrescarNew = false;
				idActividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CORREO_BA");
			} else if (idActividad.equals("RN")) {
				esRefrescar = false;
				esRefrescarNew = true;
				idActividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CORREO_BA");
			}

			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
			
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR043E tr043e = new TR043E();
			tr043e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			tr043e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());

			//String phoneNumber = getServiceNumber(peticionLocal, idServicio);	
			// se obtiene los parametros agrupacion_atisLocal y tipoPc
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
			if(!psprim.isSacarFatherEmAlta()) {
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion,psprim.getPsId(),psprim.getOpComId()).iterator().next();
			} else {
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion,psprim.getPsIdAlta(),psprim.getOpCoAlta()).iterator().next();
			}
			Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
			Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
			Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
			
			
			
			// se obtiene el parametro recursos_lines_basicaLocal
			Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
			Collection zonesArray = new ArrayList();
			if (peticionLocal.getRecursos_linea_basica().size()>0){
				if ( peticionLocal.getRecursos_linea_basica().size() > 1) {
					log.warn("solicituConfiguracionSigresBA() tiene mas de una linea basica");
				}
				for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ) {
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
				}
				//TODO: CR4860 - OJO Si la actividad es DesConfigurar Internet, los DSLAM/Zonas se obtienen de otra forma
				if(recursos_linea_basicaLocal.getZonas_atendimiento().size() > 0) {
					for (Iterator iter =recursos_linea_basicaLocal.getZonas_atendimiento().iterator();iter.hasNext();) {
						Zonas_atendimientoLocal zonasLocal = (Zonas_atendimientoLocal)iter.next();
						zonesArray.add(zonasLocal.getIp());
					}
				}
			} else {
				log.warn("solicitudConfiguracionSigresBA() Advertencia : No existe valor para el Telefono Anterior porque no hay informacion de recursos de linea");
			}
			
			String peticion = idPeticion.toString();
			//Correccion defecto 00023671 - 9 marzo 2009 
			String servicePhone;
			if (idServicio==null){
			//se invoca el metodo obtenerServicePhone con el valor de la reversa harcodeado con falso por que para este caso no hay reversa
			 servicePhone = obtenerServicePhone(peticion, psprim, false, recursos_linea_basicaLocal, agrupacion_atisLocal, tipoPc);
			}else{
			    servicePhone=idServicio;
			}
			//Fin Correccion defecto 00023671 - 9 marzo 2009  
			tr043e.setServiceNumber(servicePhone);
			

			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
			//mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaPcBA));

			//TODO: Verificar el Conector Correcto
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));

			if (esRefrescar) {
				mensajeEstadoBALocal.setAccion("R");
			} else if (esRefrescarNew) {
				mensajeEstadoBALocal.setAccion("RN");
			}

			mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(idActividad);

			int areaPhone = 0;
			int numeroPhone = 0;
			if (servicePhone.length() > 1) {
				areaPhone = Integer.parseInt(servicePhone.substring(0, 1));
				numeroPhone = Integer.parseInt(servicePhone.substring(1));
			}
			mensajeEstadoBALocal.setArea(new Integer(areaPhone));
			mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
			
			
			enviarTR043E(peticionLocal, IdCorrelativo, servicePhone);
			
		} catch (NumberFormatException e) {
			log.warn("NumberFormatException", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (CreateException e) {
			log.warn("CreateException", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		} catch (FinderException e) {
			log.warn("FinderException", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (Exception e) {
			log.debug("Exception", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	//Hasta aqui con parametro idServicio

	//Desde aqui sin parametro idServicio
	public void obtenerCuentaCorreoSigres(Long idPeticion, String idActividad,PsVsOcVO psprim) throws ATiempoAppEx {
		this.obtenerCuentaCorreoSigres(idPeticion, null, idActividad,psprim);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#actualizaModemPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR022S)
	 */
	//HASTA aqui sin parametro idServicio
	
	public void enviarTR043E(PeticionLocal peticionLocal,Long IdCorrelativo,String phoneNumber) throws ATiempoAppEx {
		TR043E tr043e = new TR043E();
		tr043e.setId(String.valueOf(IdCorrelativo));
		
		PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
		tr043e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());
		
		if (phoneNumber != null && phoneNumber.trim() != "") {
			if (phoneNumber.length() > 8)
				phoneNumber = phoneNumber.substring(0, 8);
				//Seteo del TR en caso que exista el numero
				tr043e.setServiceNumber(phoneNumber);
			} else {
				phoneNumber = "";
		}
		
		ObtenerCuentaCorreoSigresBAMQ obtenerCuentaCorreoMQ = new ObtenerCuentaCorreoSigresBAMQ();
		obtenerCuentaCorreoMQ.enviarMensaje(tr043e);
	}

	
	public void recepcionObtenerCuentaCorreoSigres(TR043S tr043s) throws ATiempoAppEx {
//		 CR15338 - @Trace - Inicio 
		BackendExecution bExecution = null;
		try {
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			bExecution = BackendTraceUtil.initExecution(tr043s, log);
			bExecution.setIdMensajeOp(tr043s.getId());
			bExecution.startOperation();
			
			validaHome();

			Mensaje_estadoLocal mesajeOkLocal =	mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal =	mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal =mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estado_baKey key =new Mensaje_estado_baKey(Long.valueOf(tr043s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal;

			try {
				mensajeEstadoBaLocal =(Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoBaLocal = null;
			}

			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_BA
			 */
			if (mensajeEstadoBaLocal == null) {
				log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr043s));
				throw new ATiempoAppEx(	"No Existe Respuesta en la base de enviados:"+ tr043s.getId(),ATiempoAppEx.EXCEPTION);
			}

			Mensaje_estadoKey mensaje_estadoKey =(Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey();

			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk	|| mensaje_estadoKey.cod_estado.intValue()== estadoEsperaManual) {
				log.debug("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr043s));
				return;
			}
			
			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
			
			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica =mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

			if (recursosLineaBaBasica.size() == 0) {
				Long idConDos =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			} else {
				recursos_baLocal =(Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}

			mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey =(PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();

			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB =actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto =actividadEJB.getActividadEJBDTO(peticionKey.peti_numero,mensajeEstadoBaLocal.getCod_actividad_generadora());

			boolean errorCode = false;
			//OperationStatus op = opStatus.getOperationStatus(tr043s , tr043s.getCode() ,tr043s.getDescription());
			if (tr043s.getCode() != null && !"".equals(tr043s.getCode())&& !"0000".equals(tr043s.getCode())) {
				errorCode = true;
			}

		
			int codigoAccion = Integer.parseInt(tr043s.getAction());
			if(!tr043s.isError() && codigoAccion == 0){
				log.debug("No tengo Error en el mensaje");

				//Parte Comun recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());

				try {
					//if (tr043s.getCode() != null && !"0000".equals(tr043s.getCode())) {
					//if (!op.isOk()) {
					if(tr043s.getCode() != null && !"0000".equals(tr043s.getCode())){
						recursos_baLocal.setCod_error(new Integer(String.valueOf(tr043s.getCode())));
					} else {
						recursos_baLocal.setCod_error(new Integer(0));
					}
				} catch (NumberFormatException nf) {
					recursos_baLocal.setCod_error(new Integer(0));
				}
				
				recursos_baLocal.setDesc_error(tr043s.getErrorMessage());
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				
				//NuevoFatherEmail
				if (tr043s.getFatherEmail() != null) {
					recursos_baLocal.setFather_email_nuevo(
					tr043s.getFatherEmail());
					log.debug("Cargando el Father_email_nuevo");
				}else{
					log.debug("Cargando el Father_email_nuevo es null");
				}					
				actDto.setObservacion("Operacion Exitosa /"+" Respuesta:  " + tr043s.getCode() + " -  " +  tr043s.getErrorMessage());
			} else {
				//Estoy en espera
				if(codigoAccion == 3){
					actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr043s.getCode() + " -  " +  tr043s.getErrorMessage());
					actividadEJB.grabarSinTerminar(actDto);	
					return;
				}
				
				log.debug("Tengo un error en el mensaje");
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(
					df.format(new java.util.Date()));

				if (mensajeEstadoBaLocal.getAccion() != null && (mensajeEstadoBaLocal.getAccion().equals("R") 
						|| mensajeEstadoBaLocal.getAccion().equals("RN")))
					return;

						
				String codError = String.valueOf(tr043s.getCode());
				String nombreClase = TR043S.class.getName();
				nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado =getErrorLegado(codError, nombreClase);

				boolean tieneCodActividadNoAvance = peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				if (!tieneCodActividadNoAvance){
					if (errorLegado != null) {
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(),	mensajeEstadoBaLocal.getCod_actividad_generadora(),
						errorLegado.getIdCausa(),actDto.getIdActividadFlujo());
					} else {
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(),	mensajeEstadoBaLocal.getCod_actividad_generadora(),
						new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")),actDto.getIdActividadFlujo());
					}
				}
				
				if(codigoAccion == 1){
					if (tieneCodActividadNoAvance){
				    	actDto.setObservacion("ERROR, proceso peticiones en vuelo en Obtener Cuenta de Correo TR043 / " + " Respuesta:  " + tr043s.getCode() + " -  " +   tr043s.getErrorMessage()+" -- \n",false);
				    	actividadEJB.grabarSinTerminar(actDto);	
				    }else{
				    	log.debug("Error en Obtener Cuenta de Correo.  Se debe derivar a PGI ");
					    actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					    actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + tr043s.getCode() + " -  " +  tr043s.getErrorMessage(),true);
					    
						actividadEJB.terminar(actDto);
				    }
				}else{
					if (tieneCodActividadNoAvance){
				    	actDto.setObservacion("ERROR, proceso peticiones en vuelo en Obtener Cuenta de Correo TR043/ " + " Respuesta:  " + tr043s.getCode() + " -  " +   tr043s.getErrorMessage()+" -- \n",false);
				    	actividadEJB.grabarSinTerminar(actDto);	
				    }else{
				    	log.debug("Error en Obtener Cuenta de Correo.  Se debe derivar a PGC ");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
						actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " + tr043s.getCode() + " -  " +  tr043s.getErrorMessage(),true);
						
						actividadEJB.terminar(actDto);
				    }
					
				}
				return;
			}

			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			if (mensajeEstadoBaLocal.getAccion() != null && (mensajeEstadoBaLocal.getAccion().equals("R")|| mensajeEstadoBaLocal.getAccion().equals("RN")))
				return;
				
			 if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
				actividadEJB.terminar(actDto);
			 }else{
			 	actividadEJB.grabarSinTerminar(actDto);	
			 }
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.error("NumberFormatException", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.error("CreateException", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.warn("FinderException", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.error("", e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (Exception e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.debug("EXCEPTION", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace - FIN 
		}
	}

		
	//DESDE AQUI INFORMAR RESULTADO INSTALACION
	//Desde aqui envio Informar Resultado Instalacion
 
	public void informarResultadoInstalacionSigres(Long idPeticion, String idActividad,ActividadEJBDTO actDto) throws ATiempoAppEx {

		try {

			validaHome();
			
			Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));					
			
			Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(IdCorrelativo);
			
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaPcBA));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));

			mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(idActividad);
									
//			Comentarerar esta linea y lanzar un trio de BA - Ajuste de peticiones en vuelo
			
			if(peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBALocal.getCod_actividad_generadora())){
				try{	        
			    	Collection c = peticionLocal.getRecursos_ba();
			    	
					for (Iterator iter = c.iterator(); iter.hasNext();){
						
						Recursos_baLocal recursosbaLocal= (Recursos_baLocal) iter.next();
						recursosbaLocal.remove();
					}
			    }catch(Exception ex){
			    	ex.printStackTrace();
			    }
			}
		    
			
			boolean reversa = false;
			PeticionKey peticionKey =(PeticionKey) mensajeEstadoBALocal.getPeticion().getPrimaryKey();
//			ActividadFactoryEJB actividadFactoryEJB =new ActividadFactoryEJB();
//			IActividadEJB actividadEJB =actividadFactoryEJB.getActividad(mensajeEstadoBALocal.getCod_actividad_generadora());
//			ActividadEJBDTO actDto =actividadEJB.getActividadEJBDTO(peticionKey.peti_numero,mensajeEstadoBALocal.getCod_actividad_generadora());
	        
			if(actDto == null || actDto.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			    reversa = true;
			enviarTR033E(idPeticion, peticionLocal, IdCorrelativo,reversa);
				
				
		} catch (NumberFormatException e) {
			log.warn("NumberFormatException", e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (CreateException e) {
			log.warn("CreateException", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		} catch (FinderException e) {
			log.warn("FinderException", e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (Exception e) {
			log.debug("Exception", e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	
	public void enviarTR033E(Long idPeticion,PeticionLocal peticionLocal,Long IdCorrelativo,boolean reversa) throws ATiempoAppEx, FinderException, NamingException {
			TR033E tr033e = new TR033E();
			tr033e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			tr033e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());

			//TODO: validar de donde tomaremos el valor de la ods Sigres
			InformacionAdslDTO informacionAdsl = null;		
			informacionAdsl = obtenerDatosActualAdsl(idPeticion);	
			tr033e.setTicketNumber(peticionKey.peti_numero.toString());				
			tr033e.setReverse(reversa);
			
			int estadoActual = peticionLocal.getEspe_id().intValue();
			if (estadoActual == estadoPeticionCancelada || reversa == true){
				tr033e.setCode("0005");
				tr033e.setDescription("Petición Cancelada");
			}else{
				boolean tieneQuiebre=false;
				for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
				{
					Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
					Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
					Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
					int idFamilia=familia_producto_servicioKey.faps_id.intValue();
//					REQ BA NAKED adicion familia PC naked
					if(idFamilia!=familiaPcBA && idFamilia!=familiaPcPsBANaked)
						continue;
					Collection colEstadoPsPeticion=producto_servicio_peticionLocal.getEstado_ps_peticion();
					if(colEstadoPsPeticion.size()>0)
					{
						Estado_ps_peticionLocal primero=(Estado_ps_peticionLocal) colEstadoPsPeticion.iterator().next();
						Integer codEstadoCierre = primero.getCod_estado_cierre();
							
						if(primero.getCod_estado_cierre().intValue()==estadoCierreError)
							tieneQuiebre=true;
						if (codEstadoCierre == null || codEstadoCierre.intValue () == estadoCierreOk)
						{
							Long codigoCausal = primero.getCod_causal () ;
							if (codigoCausal != null)
							{
								Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;
								llaveCausa.cod_causal = codigoCausal ;
								if(catalogo_causalHome==null){
								
									catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
								}

								Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;
								tr033e.setDescription(catalogCausa.getDescripcion_causal());
							}
							else{
								
								tr033e.setDescription("Instalación OK");
							}
						}
						else{
							
							tr033e.setDescription("Instalación OK")	;
						}
					}
				}
				if(tieneQuiebre){
					tr033e.setCode("0001");
				}else
					tr033e.setCode("0000");
					tr033e.setDescription("Instalación OK");
			}						
			//tomo fecha de finalizacion de control de visita para fecFinInstalacion O ES ESTA O ES LA ANTERIOR
			ControlvisitaLocalHome cvHome = (ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			Collection cvLocal = cvHome.findByPetiNumero(idPeticion);
							  
			String fecFinInstalacion=  new Fecha().getTimestamp().toString();
			if (cvLocal.iterator().hasNext()){
							
				ControlvisitaLocal conVLocal = (ControlvisitaLocal) cvLocal.iterator().next();
				fecFinInstalacion = conVLocal.getFechahora_salida().toString();
			}
			tr033e.setInstallationEndDate(fecFinInstalacion);
						
			InformarResultadoInstalacionSigresBAMQ informarResultadoInstalacionSigresBAMQ = new InformarResultadoInstalacionSigresBAMQ();
			informarResultadoInstalacionSigresBAMQ.enviarMensaje(tr033e);
	}		
		//Hasta aqui envio Informar Resultado Instalacion
		
	  //Desde aqui recepcion Informar Resultado Instalacion
	
    //CR19590 -- Pablo L -- 11/11/2008
	public void recepcionInformarResultadoInstalacionSigres(TR034S tr034s) throws ATiempoAppEx {
		  try {
			  validaHome();	

			  //TODO: RETA - Bandera para saber que se envio ya la interfaz tr-033 - Peticiones en vuelo 
			  PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			  Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			  Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(new Long(tr034s.getAtiempoRequestNumber()));
			  //TODO
			  
			  Mensaje_estadoLocal mesajeOkLocal =	mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			  Mensaje_estadoLocal mensajeErrorLocal =	mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			  Mensaje_estadoLocal mensajeManualLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			  Mensaje_estado_baKey key =new Mensaje_estado_baKey(Long.valueOf(tr034s.getId()));
			  Mensaje_estado_baLocal mensajeEstadoBaLocal;
	
			  try {
				  mensajeEstadoBaLocal =(Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			  } catch (FinderException fex) {
				  mensajeEstadoBaLocal = null;
			  }
	
			  /*
			   * Validacion de existencia de la respuesta en la Base de Datos 
			   * en la tabla Mensaje_Estado_BA
			   */
			  if (mensajeEstadoBaLocal == null) {
				  log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr034s));
					
				  throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr034s.getId(),ATiempoAppEx.EXCEPTION);
			  }
	
			  Mensaje_estadoKey mensaje_estadoKey =(Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey();

			  if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  	if (linea_basicaLocal.getReinstalacion_internet().equals("true")){
				  	linea_basicaLocal.setReinstalacion_internet("true-tr030");
				  }
			  }
		  
			  
			  //Validacion del estado de la respuesta 
			  if (mensaje_estadoKey.cod_estado.intValue() == estadoOk	|| mensaje_estadoKey.cod_estado.intValue()== estadoEsperaManual) {
				  log.debug("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr034s));
				  return;
			  }
		
			  PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
			  mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
			  mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			  PeticionKey peticionKey =(PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
	
			
			  
			  ActividadFactoryEJB actividadFactoryEJB =new ActividadFactoryEJB();
			  IActividadEJB actividadEJB =actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			  ActividadEJBDTO actDto =actividadEJB.getActividadEJBDTO(peticionKey.peti_numero,mensajeEstadoBaLocal.getCod_actividad_generadora());
	          
			  int codigoAccion = Integer.parseInt(tr034s.getAction());
			  
			  String errorMsg = "";
			  String code = "";
			  
			  if (tr034s.getCode() != null)
			  	code = tr034s.getCode();
				
			  if (tr034s.getDescription() != null) {
			  	errorMsg += tr034s.getDescription() + ".";
			  }
			  	
			  
			  //Si no es PGI o PGC
			  if( codigoAccion == 0 ){
			  	actDto.setObservacion("Operacion Exitosa /"+"Respuesta:  " + tr034s.getCode() + " -  " +  tr034s.getErrorMessage());
			  } else if(codigoAccion != 3){
			  	//No es espera
			  	log.debug("Tengo un error en el mensaje");
			  	mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
			  	mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
			  	
			  	if (mensajeEstadoBaLocal.getAccion() != null && (mensajeEstadoBaLocal.getAccion().equals("R") 
			  			|| mensajeEstadoBaLocal.getAccion().equals("RN")))
			  		return;
	
			  	String codError = String.valueOf(tr034s.getCode());
			  	String nombreClase = TR034S.class.getName();
			  	nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
			  	ErrorLegadoLocal errorLegado =getErrorLegado(codError, nombreClase);
			  	
			  	 if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  	 	if (errorLegado != null) {
				  		peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
				  		insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(),	mensajeEstadoBaLocal.getCod_actividad_generadora(),
				  				errorLegado.getIdCausa(),actDto.getIdActividadFlujo());
				  	} else {
				  		insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(),	mensajeEstadoBaLocal.getCod_actividad_generadora(),
				  				new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")),actDto.getIdActividadFlujo());
				  	}
			  	 }
			  	
			  	
			  	if(codigoAccion == 1){
			  		log.debug("Error en Informar Resultado de Instalacion por Broker.  Se debe derivar a PGI ");
			  		actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
			  		
			  		if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  			actDto.setObservacion("ERROR, proceso peticiones en vuelo en TR034/ " + " Respuesta:  " + code + " -  " +  errorMsg+" -- \n",false);
			  			actividadEJB.grabarSinTerminar(actDto);
			  		}else{
			  			actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + code + " -  " +  errorMsg+" -- ",false);
			  			actividadEJB.terminar(actDto);
			  		}
			  		
			  		linea_basicaLocal.setReinstalacion_internet(linea_basicaLocal.getReinstalacion_internet()+"-action:1");
			  		linea_basicaLocal.setCambio_zonas("true");
			  	}else{
			  		log.debug("Error en Informar Resultado de Instalacion por Broker.  Se debe derivar a PGC ");
			  		actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
			  		
			  		if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  			actDto.setObservacion("ERROR, proceso peticiones en vuelo en TR034S/ " + " Respuesta:  " + code + " -  " +  errorMsg+" -- \n",false);
			  			actividadEJB.grabarSinTerminar(actDto);
			  		}else{
			  			actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " + code + " -  " +  errorMsg+" -- ",false);
			  			actividadEJB.terminar(actDto);
			  		}
			  		
			  		linea_basicaLocal.setReinstalacion_internet(linea_basicaLocal.getReinstalacion_internet()+"-action:2");
			  		linea_basicaLocal.setCambio_zonas("true");
			  	}
			  	return;
			  }else{
			  	actDto.setObservacion("Novedad de avance / "+ " Respuesta:  " + code + " -  " + errorMsg +" -- ",false);
			  	actividadEJB.grabarSinTerminar(actDto);
			  	return;
			  }
					
			  mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
				  
			  //TODO: 01022010 - RETA - Ajuste para requerimiento Req_2009_00031777_CambioVelocidadEnVuelo
			  if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  	actividadEJB.terminar(actDto);
			  }else{
			  	actividadEJB.grabarSinTerminar(actDto);
			  }
			  //End TODO
				
		  } catch (NumberFormatException e) {
			  e.printStackTrace();
			  log.error("NumberFormatException", e);
			  throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		  } catch (CreateException e) {
			e.printStackTrace();
			log.error("CreateException", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		  } catch (FinderException e) {
			  e.printStackTrace();
			  log.warn("FinderException", e);
			  throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		  } catch (TnProcesoExcepcion e) {
			  e.printStackTrace();
			  log.error("", e);
			  throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		  } catch (Exception e) {
			  e.printStackTrace();
			  log.debug("EXCEPTION", e);
			  throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		  }
	  }
	
	
	public String getNombreBandeja(String plataforma){
		return VpistbbaConfig.getVariable("BANDEJA_"+plataforma);
	}
	
	public ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
		ErrorLegadoLocal errorLegado = null;
		try{
			ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
			errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
		} catch (javax.ejb.FinderException e) {
			log.debug("No se encontro el error legado..codigo..: " + codigoError +" TR..:"+ codigoTr + " se inserta tipo de error por defecto");
		} catch (NamingException e) {
			log.debug("No se encontro el error legado..codigo..: " + codigoError +" TR..:"+ codigoTr + " se inserta tipo de error por defecto");
			
		}
		return errorLegado;
	}
	// CR4860 - Sigres - guido - 28/Abr
	public void solicitudConfiguracionSigresBA(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion,boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx {
		try {
			validaHome ();
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			log.debug("Sigres - Creamos los mensajes de estado");
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

			//Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			//TODO: CR4860 Insertar en tabla nueva para Sigres???
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);

			int areaPhone= 0;
			int numeroPhone= 0;
			try	{
				areaPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(0,1));
				numeroPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(1));

				mensajeEstadoBALocal.setArea(new Integer(areaPhone));
				mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
				//Seteo que aun no he recibido ninguna respuesta de Sigres de mas de una respuesta que devuelve Sigres en este envio
				mensajeEstadoBALocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_SIN_MENSAJE));
			} catch(Exception e) {
				log.info("solicitudConfiguracionSigresBA() Num ide nu stb no numerico:"+peticionLocal.getNum_ide_nu_stb());
			}


			enviarTR030E(peticion,psprim,psIPFijaPeticion,reversa,peticionLocal,IdCorrelativo, act);

		} catch (FinderException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (CreateException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (NamingException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
	}
	public void enviarTR030E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion,boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx {
		TR030E tr030e = new TR030E();
		tr030e.setId(String.valueOf(IdCorrelativo));
		PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
		tr030e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());
		boolean asignaIPFija = false;
		
		//TODO: CR4860 - tr030e.setCityName(); - German
		// Datos del cliente
		String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
		if (clientDocument!=null && clientDocument.trim()!=""){
			if (clientDocument.length()>16){
				clientDocument=clientDocument.substring(0,16);
			}
		} else {
			log.warn("solicitudConfiguracionSigresBA(): Atencion -  No existe valor para el Documento de Cliente peticion=" + peticion);
			//clientDocument="-";
		}
		TypeValueList valueList = new  TypeValueList();
		addTypeValue(valueList, "client-document-number", peticionLocal.getNum_doc_cli_cd());
		String tc = peticionLocal.getTip_cli_cd();
		if (tc != null && tc.trim().length()!=0 ) {
			addTypeValue(valueList, "client-type", String.valueOf(tc.charAt(0)));
		}
		addTypeValue(valueList, "client-document-type", peticionLocal.getTip_doc_cd());
		addTypeValue(valueList, "client-name", peticionLocal.getNom_ds());
		addTypeValue(valueList, "client-first-lastname", peticionLocal.getPri_ape_ds());
		addTypeValue(valueList, "client-second-lastname", peticionLocal.getSeg_ape_ds());
		LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
		//Antes city-name
		addTypeValue(valueList, "code-name", localidadKey.cod_loc);
		tr030e.setPssbaDataList(valueList);
		tr030e.setCustomerSegment(String.valueOf(peticionLocal.getCod_sgm_cli_cd()));
		//ServiceType era tr013e.setProductServiceCode 
		String serviceType = calcularPS(psprim, peticion,peticionLocal,IdCorrelativo, act);
		if(serviceType != null)
			tr030e.setServiceType(serviceType);
		//TODO: CR4860 - ¿RangeZoneList es lo que en tr013 era Dslams?
		Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
		Collection zonesArray = new ArrayList();
		if (peticionLocal.getRecursos_linea_basica().size()>0){
			if ( peticionLocal.getRecursos_linea_basica().size() > 1) {
				log.warn("solicituConfiguracionSigresBA() tiene mas de una linea basica");
			}
			for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ) {
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
			}
			//TODO: CR4860 - OJO Si la actividad es DesConfigurar Internet, los DSLAM/Zonas se obtienen de otra forma
			if(recursos_linea_basicaLocal.getZonas_atendimiento().size() > 0) {
				for (Iterator iter =recursos_linea_basicaLocal.getZonas_atendimiento().iterator();iter.hasNext();) {
					Zonas_atendimientoLocal zonasLocal = (Zonas_atendimientoLocal)iter.next();
					zonesArray.add(zonasLocal.getIp());
				}
			}
		} else {
			log.warn("solicitudConfiguracionSigresBA() Advertencia : No existe valor para el Telefono Anterior porque no hay informacion de recursos de linea");
		}
		tr030e.setRangeZoneList(zonesArray);
		// Start father-email ........
		String fatherEmail = null;
		Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		//Voy a asumir que aca viene uno solo.
		Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
		if(!psprim.isSacarFatherEmAlta()) {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsId(),psprim.getOpComId()).iterator().next();
		} else {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsIdAlta(),psprim.getOpCoAlta()).iterator().next();
		}
		Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
		Long psId = subPet.getCod_pro_ser_cd();
		Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
		Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
		try {
			if(psprim.isSacarFatherEmAlta() && psId.longValue()==psprim.getPsIdAlta().longValue()) {
				fatherEmail = extraerFatherEmail(subPet);
			} else if(psId.longValue()==psprim.getPsId().longValue())	{
				//si la peticion es de ic y tiene id pc se envia el id pc como father email ..sino se saca la caracteristica .. si la caracteristica esta vacia se pone "".
				if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC) {
					if( agrupacion_atisLocal.getNum_ide_nu()!=null && !agrupacion_atisLocal.getNum_ide_nu().equals("") ) {
						fatherEmail = agrupacion_atisLocal.getNum_ide_nu();
					} else	{
						fatherEmail = extraerFatherEmail(subPet);
					}
				} else {
					//si no es IC (cualquier ba) siempre se saca la caracteristica sino va vacio.
					fatherEmail = extraerFatherEmail(subPet);
				}
				//if(tr013e.getFatherEmail()==null)
					//tr013e.setFatherEmail("");
			}else {
				fatherEmail = producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu();
			}
			if(fatherEmail == null) {
				fatherEmail = subPet.getFk_agru_sub().getNum_ide_nu();
			}
			log.info("Setie el valor de father eemail en:"+ fatherEmail);
		}
		catch(Exception e) {
		//TODO: Mejorar esto: Si no viene email, debe ir a PGC la actividad.
			log.warn("No viene el EMAIL",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}
		addTypeValue(valueList, "father-email", fatherEmail);
		//End father-email
		String servicePhone = obtenerServicePhone(peticion, psprim, reversa, recursos_linea_basicaLocal, agrupacion_atisLocal, tipoPc);
		tr030e.setServiceNumber(servicePhone);
		tr030e.setTerminalNumber(servicePhone);
		
		//***************** FAVG - Req 25189 - Sep 29/2009
		//
		String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
		if(psIpFija != null && psIpFija.length() > 0){
			String[] tokens = null;
			tokens = psIpFija.split(",");
			for (int i = 0 ; i < tokens.length ; i++) {
				if(tokens[i].equals(String.valueOf(psIPFijaPeticion))){
					tr030e.setIpServiceType(tokens[i]);
					asignaIPFija = true;
					break;
				}
			}
			
			if (asignaIPFija == false){
				tr030e.setIpServiceType("");
			}
		}
		else{
			log.debug("No existe la variable PS_IPFIJA en el archivo de propiedades configVpiStbBa.properties");
		}
		//***************** Fin - Req 25189 - Sep 29/2009
		
		//Requierimiento 22539- Try and Buy Cesar Remigio
		String pcPadre = ObtenerPCpadre(peticionLocal);
		tr030e.setPcPadre("");
		if(pcPadre != null)
			tr030e.setPcPadre(pcPadre);

		log.debug("solicitudConfiguracionSigresBA() Enviando mensaje TR030E de Mensaje");
		ConfiguracionServicioSigresBAMQ configuracionServicioSigresBAMQ = new ConfiguracionServicioSigresBAMQ();
		if(serviceType != null){
			configuracionServicioSigresBAMQ.enviarMensaje(tr030e);
			if(act != null)
				act.setObservacion("Se envia el mensaje de Configuracion Internet.");
		}
		
	}

	/**
	 * @param peticionLocal
	 */	
	private String ObtenerPCpadre(PeticionLocal peticionLocal) {
		// TODO Apéndice de método generado automáticamente
		boolean InfPCpadre =  false;
		
		try {
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			Collection pps = peticionLocal.getProducto_servicio_peticion();
			String psSVABA[] = propertiesLocal.getValor().split(",");
			String PCpadre = null;
			
			for(Iterator iter = pps.iterator();iter.hasNext()&& pps.size()>1;){
				Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)iter.next();
				Familia_producto_servicioKey familia_PC = pspLocal.getFamiliaKey();
				for(int i = 0; i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(pspLocal.getPsId())){
						InfPCpadre = true;
					}
					if(familia_PC.faps_id.equals(new Long (ComunInterfaces.familiaPcBA))
							 ||familia_PC.faps_id.equals(new Long (ComunInterfaces.familiaPcPsBANaked)))
						PCpadre = String.valueOf(pspLocal.getPsId());
				}
			}
			log.debug("Se informa PC Padre: "+InfPCpadre);
			if(InfPCpadre)
				return PCpadre;
			else
				return null;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al buscar el pc padre : "+ e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al buscar el pc padre : "+ e);
			return null;
		}
		
		
	}

	/**
	 * @param psprim
	 * @param peticion
	 * @param peticionLocal
	 * @param idCorrelativo
	 * @return
	 */
	private String calcularPS(PsVsOcVO psprim, String peticion, PeticionLocal peticionLocal, Long idCorrelativo, ActividadEJBDTO act) {
		// TODO Apéndice de método generado automáticamente
		try {
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			Homologacion_ps_sva_baLocalHome ps_homologacion = (Homologacion_ps_sva_baLocalHome) HomeFactory.getHome(Homologacion_ps_sva_baLocalHome.JNDI_NAME);
			Homologacion_ps_sva_baLocal ps_homologacionlocal = null;
			Collection pps = peticionLocal.getProducto_servicio_peticion();
			PeticionKey petiKey= (PeticionKey)peticionLocal.getPrimaryKey();
			log.debug("Peticion: "+petiKey.peti_numero);
			String psSVABA[] = propertiesLocal.getValor().split(",");
			boolean esSVABA = false;
			
			for(int i = 0; i<psSVABA.length;i++){
				if(new Long(psSVABA[i]).equals( psprim.getPsId()))
					esSVABA = true;
			}
			
			log.debug("Resultado de SVA BA: "+esSVABA + "Para el PS:"+psprim.getPsId());
			if(esSVABA){
				for(Iterator iter = pps.iterator();iter.hasNext()&& pps.size()>1;){
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)iter.next();
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) pspLocal.getFamiliaKey();
//					REQ BA NAKED adicion familia PC naked
					log.debug("Familia ps id: "+familia_producto_servicioKey.faps_id);
					if(familia_producto_servicioKey.faps_id == new Long( ComunInterfaces.familiaPcBA)
							|| familia_producto_servicioKey.faps_id == new Long( ComunInterfaces.familiaPcPsBANaked)){
						ps_homologacionlocal = ps_homologacion.findByPrimaryKey(new Homologacion_ps_sva_baKey(pspLocal.getPsId(),psprim.getPsId()));
						return ps_homologacionlocal.getPs_homologado().toString();
					}
				}
				Long pc_evaluado;
				String opComTipo = psprim.getOpComTipo();
				Recursos_baLocalHome recursosBAHome =(Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
				
				log.debug("Se valida para operaciòn Comercial "+opComTipo);
				Recursos_baLocal recursosBALocal = recursosBAHome.findbyPeti_numero(new Long(peticion));
				
				//se valida si es traslado
				
				if(psprim.getOpComId().equals(new Long(ComunInterfaces.ID_OP_ALTA_TRASLADO))
						|| psprim.getOpComId().equals(new Long(ComunInterfaces.ID_OP_BAJA_TRASLADO)))
						return "0";
				
				if(opComTipo.equals("A")||opComTipo.equals("ACP"))
					pc_evaluado = recursosBALocal.getPsActual();
				else
					pc_evaluado = recursosBALocal.getPsAnterior();
				ps_homologacionlocal = ps_homologacion.findByPrimaryKey(new Homologacion_ps_sva_baKey(pc_evaluado,psprim.getPsId()));
				
				log.debug("Se calcula ps: "+ps_homologacionlocal.getPs_homologado()+" para PC: "+pc_evaluado);
				return ps_homologacionlocal.getPs_homologado().toString();
			}else{
				for(Iterator iter = pps.iterator();iter.hasNext()&& pps.size()>1;){
					Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal)iter.next();
					for(int i = 0; i<psSVABA.length;i++){
						if(new Long(psSVABA[i]).equals(pspLocal.getPsId())){
							if(psprim.getOpComId().equals(new Long(ComunInterfaces.ID_OP_ALTA_TRASLADO))
									|| psprim.getOpComId().equals(new Long(ComunInterfaces.ID_OP_BAJA_TRASLADO)))
									return "0";
							Operacion_comercialLocal ocLocal = pspLocal.getOperacion_comercial();
							Operacion_comercialKey ockey = (Operacion_comercialKey) ocLocal.getPrimaryKey();
							if(psprim.getOpComId().intValue() == ockey.opco_id.intValue()){
								ps_homologacionlocal = ps_homologacion.findByPrimaryKey(new Homologacion_ps_sva_baKey(psprim.getPsId(),pspLocal.getPsId()));
								return ps_homologacionlocal.getPs_homologado().toString();
							}	
						}
					}
				}
				return String.valueOf(psprim.getPsId());
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en Calcular PS: "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se encuentra PS Homologado es Homologacion_BA_SVA");
		}
		
		try {
			
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			Mensaje_estado_baLocal mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(idCorrelativo));
			ActividadFactoryEJB actividadFactoryEJB =new ActividadFactoryEJB();
			IActividadEJB actividadEJB =actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			//ActividadEJBDTO actDto =actividadEJB.getActividadEJBDTO(peticionKey.peti_numero,mensajeEstadoBaLocal.getCod_actividad_generadora());
			insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(),	mensajeEstadoBaLocal.getCod_actividad_generadora(),new Long("701"),act.getIdActividadFlujo());
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
			act.setObservacion("PS No se encuentra Homologado.", true);
			act.setRealizarTerminoInmediato(true);
			actividadEJB.terminar(act);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al derivar a PGI en Calcular PS: "+e);
		}
		
		return null;
	}

	private String obtenerServicePhone(String peticion, PsVsOcVO psprim, boolean reversa, Recursos_linea_basicaLocal recursos_linea_basicaLocal, Agrupacion_atisLocal agrupacion_atisLocal, Long tipoPc) throws ATiempoAppEx {
		//Nueva regla para service phone
		String servicePhone = null;
		if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC)	{
			String valorOcho=String.valueOf(peticion);
			if(valorOcho.length()>8) {
				valorOcho=valorOcho.substring(0,8);
			}
			servicePhone = valorOcho;
		} else {
		    
			if(reversa) { // cambio el tipo para realizar bien la reversa
				if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A")) {
					psprim.setOpComTipo("A");
				} else if(psprim.getOpComTipo()!=null) {
					psprim.setOpComTipo("B");
				}
			}
			try {
            
				if(psprim.getOpComTipo()!=null && psprim.getOpComTipo().equals("A") && !reversa) {
					if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null) {
					    log.info("1 - Obtener Service Phone ");
					    servicePhone = String.valueOf(recursos_linea_basicaLocal.getTelefono_asg());
					} else {
					    //AT-2037 Error al obtener Cuenta correo
					    if(recursos_linea_basicaLocal!=null){//throw new ATiempoAppEx("No es posible obtener datos de recursos");
					    log.info("2 - Obtener Service Phone ");
					    servicePhone = String.valueOf(recursos_linea_basicaLocal.getTelefono_ant());
					    }else{
					        log.info("3 - Obtener Service Phone ");
					        servicePhone = agrupacion_atisLocal.getNum_ide_nu();   
					    }
					}
				} else if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A") && !reversa) {
				    	log.info("4 - Obtener Service Phone ");
						servicePhone = agrupacion_atisLocal.getNum_ide_nu();
				} else if(psprim.getOpComTipo()!=null && psprim.getOpComTipo().equals("A") && reversa) {
				    	log.info("5 - Obtener Service Phone ");
						servicePhone = agrupacion_atisLocal.getNum_ide_nu();
				} else if(psprim.getOpComTipo()!=null && !psprim.getOpComTipo().equals("A") && reversa) {
					if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null) {
					    log.info("6 - Obtener Service Phone ");
						servicePhone = String.valueOf(recursos_linea_basicaLocal.getTelefono_asg());
					} else {log.info("7 - Obtener Service Phone ");
						throw new ATiempoAppEx("No es posible obtener datos de recursos");
					}
				} else {log.info("8 - Obtener Service Phone ");
					throw new ATiempoAppEx("No es posible determinar tipo opco");
				}
			} catch (Exception e) {
			    throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
            }
		}
		return servicePhone;
	}

	private String extraerFatherEmail(Subpeticion_atisLocal subPet) {
		String fatherEmail = null;
		Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();
		while (iterCarac.hasNext())	{
			Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
			Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
			Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
			if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
				log.info("Informacion : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
				//tr013e.setFatherEmail(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
				//addTypeValue(valueList, "father-email", subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
				fatherEmail = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
				break;
			}
		}
		return fatherEmail;
	}

	private void addTypeValue(TypeValueList valueList, String name, String value) {
		if (value == null || value.trim().length()==0) {
			value = ""; //TODO: CR4860 Validar si no enviar o enviar vacio
			//return;
		}
		TypeValue typeValue = new TypeValue();
		typeValue.setName(name);
		typeValue.setValue(value);
		if (valueList.getPssbaDataList()==null) {
			valueList.setPssbaDataList(new ArrayList());
		}
		valueList.getPssbaDataList().add(typeValue);
	}

	//	 CR4860 - Sigres - guido - 30/Abr
	public void recepcionConfiguracionSigresBAAck(TR031S tr031s) throws ATiempoAppEx {

		//		 CR15338 - @Trace  Inicio 
		BackendExecution bExecution = null;
		try	{
			bExecution = BackendTraceUtil.initExecution(tr031s, log);
			bExecution.setIdMensajeOp(tr031s.getId());
			bExecution.startOperation();
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			
			log.info("recepcionConfiguracionSigresBAAck() START TR031s peticionAtis=" + tr031s.getAtiempoRequestNumber() + " isMsg=" + tr031s.getId());
			validaHome ();
			boolean esEspera=false;
			Mensaje_estadoLocal mesajeOkLocal =	mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));

			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr031s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

		   try {
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
		   } catch (FinderException fex) {
				log.debug("recepcionConfiguracionSigresBAAck() No encontró mensaje estado BA Local");
				//pero puede ser un mensaje que estaba en proceso.
				Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr031s.getAtiempoRequestNumber()),new Integer(estadoEsperaManual));
				if(lista.size() != 1) {
					log.warn("recepcionConfiguracionSigresBAAck() Mensajes encontrados = "+lista.size()+" para peticionAtis=" + tr031s.getAtiempoRequestNumber() + " y estado es espera manual (valor=" + estadoEsperaManual + ")");
					mensajeEstadoBaLocal = null ;
				} else {
					mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
					esEspera=true;
				}
		   }


			if (mensajeEstadoBaLocal == null) {
				log.debug("recepcionConfiguracionSigresBAAck() No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr031s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr031s.getId(),ATiempoAppEx.EXCEPTION);
			}
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;


			if(!esEspera) {
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "
							+ XMLUtilities.marshall(tr031s));
					return;
				}
			} 
			
			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

			if (recursosLineaBaBasica.size() == 0) {
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
				log.debug("Creando recurso BA TR=031S");
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			} else {
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}
			
			String errorCode = tr031s.getCode();
			String errorMsg = tr031s.getErrorMessage();

			if (errorMsg == null || errorMsg.length()==0) {
				errorMsg = tr031s.getDescription();
			}

			
			recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
			StringBuffer errores = new StringBuffer("");

			//Seteo que recibí una primer respuesta de Sigres, de mas de una respuesta que devuelve Sigres en este caso
			mensajeEstadoBaLocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_CON_MENSAJE));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
			PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();

			//CR-19590 - agonz - 11/06/2008 
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			log.debug(" El valor correspondiente al Action es "+tr031s.getAction()+" ... peticionAtis=" + tr031s.getAtiempoRequestNumber());
			
			switch (Integer.parseInt(tr031s.getAction())) {
		      case 0:
		          	actDto.setObservacion("Actividad con exito /"+" Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg);
			    	actividadEJB.grabarSinTerminar(actDto);			      			
			      	return;
		      case 1://caso PGI
		      case 2://caso PGC
		      		
					recursos_baLocal.setCod_error(new Integer(errorCode));
					recursos_baLocal.setDesc_error(errorMsg);
					errores.append("Código: " + errorCode + ", Descripción: " + errorMsg);
					
					
						mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
						mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						
						String codError = String.valueOf(errorCode);
						String nombreClase=TR031S.class.getName();
						nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
						Long codCausa =  new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM"));
					    if(errorLegado != null){
					       peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					       codCausa = errorLegado.getIdCausa();
					    } else {
					    	log.warn("recepcionConfiguracionSigresBAAck() No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + codError);
					    }

						if(tr031s.getAction().equals("1")){
							log.debug("recepcionConfiguracionSigresBAAck() TR031 derivando a PGI ... Action: "+tr031s.getAction());

							
							if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
						    	actDto.setObservacion("ERROR, proceso peticiones en vuelo en recepcion Configuracion Sigres TR 031 / " + " Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg+" -- \n",false);
						    	actividadEJB.grabarSinTerminar(actDto);	
						    }else{
						    	actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
								actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg,true);
								
								insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
								actividadEJB.terminar(actDto);
						    }
						}else{
							if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
						    	actDto.setObservacion("ERROR, proceso peticiones en vuelo recepcion Configuracion Sigres TR 031 / " + " Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg+" -- \n",false);
						    	actividadEJB.grabarSinTerminar(actDto);	
						    }else{
						    	log.debug("recepcionConfiguracionSigresBAAck() TR031 derivando a PGC ... Action: "+tr031s.getAction());
						    	actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
								actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg,true);;
								
								insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
								actividadEJB.terminar(actDto);
						    }
							
						}
						return;
				case 3:
				    actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr031s.getCode() + " -  " +  errorMsg);
		    	    actividadEJB.grabarSinTerminar(actDto);			      			
		    		return;
		    	
		      			
		      default:
		           log.warn(" El valor "+tr031s.getAction()+" correspondiente al Action es Erroneo ... peticionAtis=" + tr031s.getAtiempoRequestNumber());
		           break;
			}
			
			log.debug("recepcionConfiguracionSigresBAAck() Grabando mensaje en espera peticionAtis=" + tr031s.getAtiempoRequestNumber());
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			actividadEJB.terminar(actDto);	

		} catch (NumberFormatException e) {
			log.warn("recepcionConfiguracionSigresBAAck() Error parseo de nro",e);
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch(Exception e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace  FIN 
		}
	}
	
	

	public void recepcionConfiguracionSigresBANovedad(TR034S tr034s) throws ATiempoAppEx {
//		 CR15338 - @Trace - Inicio 
		BackendExecution bExecution = null;
		try	{
			bExecution = BackendTraceUtil.initExecution(tr034s, log);
			bExecution.setIdMensajeOp(tr034s.getId());
			bExecution.startOperation();
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			
			log.info("recepcionConfiguracionSigresBANovedad() START TR034s peticionAtiempo=" + tr034s.getAtiempoRequestNumber() + " isMsg=" + tr034s.getId());
			validaHome ();
			boolean esEspera=false;
			Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
	
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr034s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;
	
		   try {
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
		   } catch (FinderException fex) {
				log.debug("recepcionConfiguracionSigresBANovedad() No encontró mensaje estado BA Local. Buscando en estado espera manual");
				//pero puede ser un mensaje que estaba en proceso.
				Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr034s.getAtiempoRequestNumber()),new Integer(estadoEsperaManual));
				if(lista.size() != 1) {
					log.warn("recepcionConfiguracionSigresBANovedad() Mensajes encontrados = "+lista.size()+" para peticionAtis=" + tr034s.getAtiempoRequestNumber() + " y estado es espera manual (valor=" + estadoEsperaManual + ")");
					mensajeEstadoBaLocal = null ;
				} else {
					mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
					esEspera=true;
				}
		   }
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos
			 * en la tabla Mensaje_Estado_BA
			 */
			if (mensajeEstadoBaLocal == null) {
				log.debug("recepcionConfiguracionSigresBANovedad() No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr034s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados: "+tr034s.getId(),ATiempoAppEx.EXCEPTION);
			}
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;
	
			//Validacion del estado de la respuesta
			if(!esEspera) {
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "
							+ XMLUtilities.marshall(tr034s));
					return;
				}
			} else {
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk ) {
					log.debug("El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr034s));
					return;
				}
			}
			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();
	
			if (recursosLineaBaBasica.size() == 0) {
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			} else {
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}
			boolean error = false;
			String errorCode = tr034s.getCode();
			String errorMsg = tr034s.getErrorMessage();
			if (errorMsg == null || errorMsg.length()==0) {
				errorMsg = tr034s.getDescription();
			}

			log.debug("recepcionConfiguracionSigresBANovedad()TR034S Hay error?? " +  " code='" + tr034s.getCode() + "' errorMessage=" + tr034s.getErrorMessage() + " tr034s.getDescription()=" + tr034s.getDescription() + " id tr034s: " + tr034s.getId());
	
			recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
			
			//Seteo que recibí una primer respuesta de Sigres, de mas de una respuesta que devuelve Sigres en este caso
			mensajeEstadoBaLocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_CON_MENSAJE));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
			PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();
	
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad( mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());

			if (tr034s.getAction().equals("1") || tr034s.getAction().equals("2")) {
			    recursos_baLocal.setCod_error(new Integer(errorCode));
				recursos_baLocal.setDesc_error(errorMsg);
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				String codError = String.valueOf(errorCode);
				String nombreClase=TR034S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				Long codCausa =  new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM"));

				if(errorLegado != null){
			       peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
			       codCausa = errorLegado.getIdCausa();
			    } else {
			    	log.warn("recepcionConfiguracionSigresBANovedad() No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + codError);
			    }
			    
				
			    if(tr034s.getAction().equals("1")) {
			    	
			    	if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
				    	actDto.setObservacion("ERROR, proceso peticiones en vuelo TR 034 en recepcion Configuracion Sigres BA Novedad/ " + " Respuesta:  " + tr034s.getCode() + " -  " +  errorMsg+" -- \n",false);
				    	actividadEJB.grabarSinTerminar(actDto);	
				    }else{
				    	log.debug("recepcionConfiguracionSigresBANovedad() TR034 derivando a PGI ");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + tr034s.getCode() + " -  " +  tr034s.getErrorMessage()+" -- ",false);
						
						insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
						actividadEJB.terminar(actDto);
				    }
			    }else {
			    	if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
				    	actDto.setObservacion("ERROR, proceso peticiones en vuelo TR 034 en recepcion Configuracion Sigres BA Novedad/ " + " Respuesta:  " + tr034s.getCode() + " -  " +  errorMsg+" -- \n",false);
				    	actividadEJB.grabarSinTerminar(actDto);	
			    	}else{
						log.debug("recepcionConfiguracionSigresBANovedad() TR034 derivando a PGC !!! ");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
						actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " + tr034s.getCode() + " -  " +  tr034s.getErrorMessage()+" -- ",false);
						
						insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
						actividadEJB.terminar(actDto);
			    	}
				}
			    
				return;
			}
			if(tr034s.getAction().equals("3")){//Estoy en espera
				actDto.setObservacion("Novedad de avance /" + " Respuesta:  " + tr034s.getCode() + " -  " +  tr034s.getErrorMessage()+" -- ",false);
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
		    	actividadEJB.grabarSinTerminar(actDto);	
				
			}else{
			    recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				log.debug("recepcionConfiguracionSigresBANovedad() Grabando mensaje en espera peticionAtis=" + tr034s.getAtiempoRequestNumber());
				mensajeEstadoBaLocal.setMensaje_estado(mesajeEspera);
				recursos_baLocal.setCod_error(null);
				actDto.setObservacion("Operacion Exitosa /" + " Respuesta:  " + tr034s.getCode() + " -  " +  tr034s.getErrorMessage(),false);
				actividadEJB.grabarSinTerminar(actDto);
			}
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);	
			log.warn("recepcionConfiguracionSigresBANovedad() Error parseo de nro",e);
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			log.warn("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch(Exception e) {
			bExecution.setErrorOp(true);	
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace - FIN 
		}
	}
	

	//	 CR4860 - Sigres - guido - 30/Abr
	public void recepcionConfiguracionSigresBAOk(TR032S tr032s) throws ATiempoAppEx{
//		 CR15338 - @Trace - Inicio 
		BackendExecution bExecution = null;
		try {
			bExecution = BackendTraceUtil.initExecution(tr032s, log);
			bExecution.setIdMensajeOp(tr032s.getId());
			bExecution.startOperation();
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			
			log.info("recepcionConfiguracionSigresBAOk() START TR032s peticionAtiempo=" + tr032s.getAtiempoRequestNumber() + " idMsg=" + tr032s.getId());
			validaHome ();
			boolean esEspera=false;
			Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));

			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr032s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

		   try {
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
		   } catch (FinderException fex) {
				log.debug("recepcionConfiguracionSigresBAOk() No encontró mensaje estado BA Local");
				//pero puede ser un mensaje que estaba en proceso.
				Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr032s.getAtiempoRequestNumber()),new Integer(estadoEspera));
				if(lista.size() != 1) {
					log.warn("recepcionConfiguracionSigresBAOk() Mensajes encontrados = "+lista.size()+" para peticionAtis=" + tr032s.getAtiempoRequestNumber() + " y estado es espera manual (valor=" + estadoEsperaManual + ")");
					mensajeEstadoBaLocal = null ;
				} else {
					mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
					esEspera=true;
				}
		   }

		/*
		 * Validacion de existencia de la respuesta en la Base de Datos
		 * en la tabla Mensaje_Estado_BA
		 */
		if (mensajeEstadoBaLocal == null) {
			log.debug("recepcionConfiguracionSigresBAOk() No Existe Respuesta en la base de enviados\n "
					+ XMLUtilities.marshall(tr032s));
			throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr032s.getId(),ATiempoAppEx.EXCEPTION);
		}
		Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

		//Validacion del estado de la respuesta
		if(!esEspera) {
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
				log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "
						+ XMLUtilities.marshall(tr032s));
				return;
			}
		} 
		
		Recursos_baLocal recursos_baLocal;
		Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();
		
		if (recursosLineaBaBasica.size() == 0) {
			Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
			System.out.println("Creando recurso BA TR=032S");
			recursos_baLocal = recursos_baLocalHome.create(idConDos);
		} else {
			recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
		}
		boolean error = false;
		String errorCode = tr032s.getStatusCode();
		String errorMsg = tr032s.getErrorMessage();
		OperationStatus op = opStatus.getOperationStatus(tr032s , errorCode, tr032s.getErrorMessage()); //Pablo L

		log.debug("recepcionConfiguracionSigresBAOk()TR032S Hay error?? error=" + error + " code='" + //TODO: tr032s.getCode() +
				"' errorMessage=" + tr032s.getErrorMessage() + " tr032s.getErrorMessage()=" + tr032s.getErrorMessage() + " id tr032s: " + tr032s.getId());

		mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
		mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
		PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
		PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();

		ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
		IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
		ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
		
//		CR-19590 - agonz - 11/06/2008
		switch (Integer.parseInt(tr032s.getAction())) {
	      case 0:
				recursos_baLocal.setCod_error(null);
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				if(tr032s.getIp()!=null && tr032s.getIp().trim().length()!=0) 	{
//					recursos_baLocal.setMasc_actual(tr032s.getIpLanMask());//tr013s.getActualLanMask()
//					recursos_baLocal.setDir_ip_wan_actual(tr032s.getIpWan()); //tr013s.getActualWanIp()
//					recursos_baLocal.setDir_ip_lan_actual(tr032s.getIpLan()); //tr013s.getActualDslamIp()
//					recursos_baLocal.setDir_ip_dslam_actual(tr032s.getIp());//tr013s.getActualDslamIp()
//					recursos_baLocal.setFrame_actual(tr032s.getSubrack());
//					recursos_baLocal.setSlot_actual(tr032s.getSlot());
//					recursos_baLocal.setOds_sigres(tr032s.getTicketNumber());
//					recursos_baLocal.setCod_zona_atend(tr032s.getRangeZoneId());
//					recursos_baLocal.setPuerto_actual(tr032s.getPortId()); // tr013s.getActualPort()
//					recursos_baLocal.setPost_actual(tr032s.getPots());
//					recursos_baLocal.setAdsl_actual(tr032s.getAdsl());
//					recursos_baLocal.setVpivci_actual(tr032s.getVpiVci());
//					recursos_baLocal.setVpivci_red_actual(tr032s.getVpiVciNetwork());
					
					recursos_baLocal.setMasc_lan_nueva(tr032s.getIpLanMask());//tr013s.getActualLanMask()
					recursos_baLocal.setDir_ip_wan_nueva(tr032s.getIpWan()); //tr013s.getActualWanIp()
					recursos_baLocal.setDir_ip_lan_nueva(tr032s.getIpLan()); //tr013s.getActualDslamIp()
					recursos_baLocal.setDir_ip_dslam_nueva(tr032s.getIp());//tr013s.getActualDslamIp()
					recursos_baLocal.setFrame_nuevo(tr032s.getSubrack());
					recursos_baLocal.setSlot_nuevo(tr032s.getSlot());
					recursos_baLocal.setOds_sigres(tr032s.getTicketNumber());
					recursos_baLocal.setCod_zona_atend(tr032s.getRangeZoneId());
					recursos_baLocal.setPuerto_nuevo(tr032s.getPortId()); // tr013s.getActualPort()
					recursos_baLocal.setPost_nuevo(tr032s.getPots());
					recursos_baLocal.setAdsl_nuevo(tr032s.getAdsl());
					
					recursos_baLocal.setVpivci_nuevo(tr032s.getVpiVci());
					recursos_baLocal.setVpivci_red_nuevo(tr032s.getVpiVciNetwork());
					
					TypeValueList typeValueList = tr032s.getOthers();
					Map map = getTypeValuAsMap(typeValueList);
					String fEmail = (String)map.get("father-email");
					if (fEmail != null && fEmail.length() != 0) {
						recursos_baLocal.setFather_email_nuevo(fEmail);
					}
					
				}

				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				mensajeEstadoBaLocal.setMensaje_estado(mensajeOkLocal);
				
				if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
					actividadEJB.terminar(actDto);
				 }
	      		break;
	      case 1://caso PGI
	      case 2://caso PGC

				recursos_baLocal.setCod_error(new Integer(errorCode));
				recursos_baLocal.setDesc_error(errorMsg);
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				
				String codError = String.valueOf(errorCode);
				String nombreClase=TR032S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				Long codCausa =  new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM"));
			    if(errorLegado != null) {
			       peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
			       codCausa = errorLegado.getIdCausa();
			    } else {
			    	log.warn("recepcionConfiguracionSigresBAOk() No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + codError);
			    }
			   
				if(tr032s.getAction().equals("1")){
					log.debug("recepcionConfiguracionSigresBAOk() TR032 derivando a PGI ... Action: "+tr032s.getAction());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + tr032s.getErrorMessage(),true);
				}else{
					log.debug("recepcionConfiguracionSigresBAOk() TR032 derivando a PGC ... Action: "+tr032s.getAction());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
					actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " +  tr032s.getErrorMessage(),true);
				}
				
				actDto.setObservacion(op.getMensaje(),true);
				
				 if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
				 	insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
				 	actividadEJB.terminar(actDto);
				 }
				 
				return;

	      case 3://aviso o novedad
	      	    actDto.setObservacion("Novedad de avance /"+  tr032s.getErrorMessage());
	    	    actividadEJB.grabarSinTerminar(actDto);	
				return;
				
	      			
	      default:
	          throw new ATiempoAppEx(" El valor "+tr032s.getAction()+" correspondiente al Action es Erroneo ... peticionAtis=" + tr032s.getAtiempoRequestNumber());
	           
		}

		
	}catch (NumberFormatException e) 	{
		bExecution.setErrorOp(true);	
		log.warn("",e);
		e.printStackTrace();
		throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

	} catch (CreateException e) {
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.warn("",e);
		throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

	} catch (FinderException e) {
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.warn("",e);
		throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

	} catch (TnProcesoExcepcion e) {
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.warn("",e);
		throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
	}
	catch(Exception e)
	{
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
	}finally{  
		bExecution.endAll();
		// CR15338 - @Trace - FIN 
	}
}

	private Map getTypeValuAsMap(TypeValueList typeValueList) {
		Map map = new HashMap();
		if (typeValueList == null) {
			return map;
		}
		Collection coll = typeValueList.getPssbaDataList();
		if (coll == null) {
			return map;
		}
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			TypeValue typeValue = (TypeValue) it.next();
			map.put(typeValue.getName(), typeValue.getValue());
		}
		return map;
	}
	
	
	
//	CR4860 - Sigres -inicio- agonzalez- 14-05-2008 	
	public void solicitudConfiguracionSigresCambioPlan(String peticion, String cod_actividad, PsVsOcVO psprim,long psIPFijaPeticion,boolean reversa, Integer actividadFlujo, boolean noIncluirPSPrim, ActividadEJBDTO act) throws ATiempoAppEx {
	
		try {
					validaHome ();
					Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
					log.debug("Sigres - Creamos los mensajes de estado");
					PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
					Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

					// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
					//TODO: CR4860 Insertar en tabla nueva para Sigres???
					Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
					mensajeEstadoBALocal.setPeticion(peticionLocal);
					Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
					Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
					mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
					mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
					mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
					mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
					mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);
					//Seteo que aun no he recibido ninguna respuesta de Sigres de mas de una respuesta que devuelve Sigres en este envio
					mensajeEstadoBALocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_SIN_MENSAJE));

					int areaPhone= 0;
					int numeroPhone= 0;
					try	{
						areaPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(0,1));
						numeroPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(1));

						mensajeEstadoBALocal.setArea(new Integer(areaPhone));
						mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
					} catch(Exception e) {
						log.info("solicitudConfiguracionSigres() Num ide nu stb no numerico:"+peticionLocal.getNum_ide_nu_stb());
					}

				enviarTR038E(peticion,psprim,psIPFijaPeticion,reversa,peticionLocal,IdCorrelativo,noIncluirPSPrim, act);

				} catch (FinderException e) {
					log.warn("Error al enviar la Configuracion Internet Sigres",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				} catch (CreateException e) {
					log.warn("Error al enviar la Configuracion Internet Sigres",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
				} catch (NamingException e) {
					log.warn("Error al enviar la Configuracion Internet Sigres",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
				}
		
	}
public void enviarTR038E(String peticion,PsVsOcVO psprim,long psIPFijaPeticion,boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, boolean noIncluirPSPrim, ActividadEJBDTO act) throws NamingException, FinderException, ATiempoAppEx {
		TR038E tr038e = new TR038E();
		tr038e.setId(String.valueOf(IdCorrelativo));
		PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
		tr038e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());
		boolean esIPFija = false;
		
		// Datos del cliente
		String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
		if (clientDocument!=null && clientDocument.trim()!=""){
			if (clientDocument.length()>16){
				clientDocument=clientDocument.substring(0,16);
			}
		} else {
			log.warn("solicitudConfiguracionSigresCambioPlan(): Atencion -  No existe valor para el Documento de Cliente peticion=" + peticion);
			//clientDocument="-";
		}
		TypeValueList valueList = new  TypeValueList();
		addTypeValue(valueList, "client-document-number", peticionLocal.getNum_doc_cli_cd());

		//Se envia client-type consultar con Fernando 21-08-2008 
		String tc = peticionLocal.getTip_cli_cd();
		if (tc != null && tc.trim().length()!=0 ) {
			addTypeValue(valueList, "client-type", String.valueOf(tc.charAt(0)));
		}
		//addTypeValue(valueList, "client-type", "");//AT-1480 Envio de la tr038 para cambio de plan y traslado
		addTypeValue(valueList, "client-document-type", peticionLocal.getTip_doc_cd());
		addTypeValue(valueList, "client-name", peticionLocal.getNom_ds());
		addTypeValue(valueList, "client-first-lastname", peticionLocal.getPri_ape_ds());
		addTypeValue(valueList, "client-second-lastname", peticionLocal.getSeg_ape_ds());
		LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
		//Antes city-name
		addTypeValue(valueList, "code-name", localidadKey.cod_loc);
		tr038e.setPssbaDataList(valueList);
		tr038e.setCustomerSegment("");//agonz AT-1579 18-08-2008
		
		String serviceTypeCalculado = null;
		String opComTipo = psprim.getOpComTipo();
		
		log.debug("Debe incluir PS primario: "+noIncluirPSPrim+ " con op: "+opComTipo);
		if (!noIncluirPSPrim){
			serviceTypeCalculado = calcularPS(psprim, peticion,peticionLocal,IdCorrelativo, act);
			long serviceType = 0;
			if(serviceTypeCalculado != null && (opComTipo.equals("A") || opComTipo.equals("ACP"))){
				serviceType = Long.parseLong(serviceTypeCalculado);
				tr038e.setServiceType(serviceType);
				long psAnterior = 0;
				if(opComTipo.equals("ACP")){
					Collection rbaArray = peticionLocal.getRecursos_ba();
					for(Iterator iter = rbaArray.iterator();iter.hasNext(); ){
						Recursos_baLocal rbdLocal = (Recursos_baLocal)iter.next();
						psAnterior = rbdLocal.getPsActual().longValue();
					}
				}else{
					psAnterior = retornarCambioPlanBaBaja(peticionKey.peti_numero).longValue();
				}
				tr038e.setPreviousServiceType(psAnterior);
			}else{
				if(serviceTypeCalculado != null ){
					Collection rbaArray = peticionLocal.getRecursos_ba();
					long psAnterior = 0;
					for(Iterator iter = rbaArray.iterator();iter.hasNext(); ){
						Recursos_baLocal rbdLocal = (Recursos_baLocal)iter.next();
						psAnterior = rbdLocal.getPsAnterior().longValue();
					}
					serviceType = Long.parseLong(serviceTypeCalculado);
					tr038e.setServiceType(psAnterior);
					tr038e.setPreviousServiceType(serviceType);
				}
				
			}
		}else{
			tr038e.setServiceType(0);
			tr038e.setPreviousServiceType(retornarCambioPlanBaBaja(peticionKey.peti_numero).longValue());//CR-23809 12 marzo 2009
			serviceTypeCalculado = "";
		}
				
			//tr038e.setServiceType(psprim.getPsId().longValue());//AT-1480 Envio de la tr038 para cambio de plan y traslado
//		INICIO RQ IP FIJAS				
		//Obtener la mascara de red del cliente
		String mascara = obtenerMascaraActualCliente(peticion);
		//Calcular el número de IP Fijas
		String cantidadIPFijas = calcularCantidadIpFijas(mascara);
		tr038e.setIpAmount(cantidadIPFijas);
		//Obtener operacion comercial
		log.debug("Operacion comercial IP FIJA = "+ psprim.getOpComId().toString());
		tr038e.setComercialOperation(psprim.getOpComId().toString());					
	//FIN RQ IP FIJAS
		
//		***************** FAVG - Req 25189 - Sep 29/2009
		//
		String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
		if(psIpFija != null && psIpFija.length() > 0){
			String[] tokens = null;
			tokens = psIpFija.split(",");
			for (int i = 0 ; i < tokens.length ; i++) {
				if(tokens[i].equals(String.valueOf(psIPFijaPeticion))){
					tr038e.setIpServiceType(tokens[i]);
					esIPFija = true;
					break;
				}
			}
			
			if (esIPFija == false){
				tr038e.setIpServiceType("");
			}
		}
		else{
			log.debug("No existe la variable PS_IPFIJA en el archivo de propiedades configVpiStbBa.properties");
		}
		//***************** Fin - Req 25189 - Sep 29/2009
		
		//TODO: CR4860 - ¿RangeZoneList es lo que en tr013 era Dslams?
		//TODO: Raúl Ernesto Triviño - Ajuste error Peticiones con Error en Broker
		Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
		Recursos_linea_basicaLocal recursos_linea_basicaLocal = linea_basicaHome.findByPeticion(new Long(peticion));
		//End TODO
		
		//Se valida si viene solo un PS de TRY AND BUY
		boolean seinformaZA = this.esSoloVelocidadAdicional(act) || esIPFija;
		log.debug("esIPFIJA = "+esIPFija+ " se informaZA ="+seinformaZA + " esSoloVelocidadAdicional="+esSoloVelocidadAdicional(act));
		
		tr038e.setRangeZoneList(null);//AT-1480 Envio de la tr038 para cambio de plan y traslado
		// Ingresa en nulo a menos que encuentre una similar entre LB y BA
		//for (Iterator iter = peticionLocal.getRecursos_ba().iterator(); iter.hasNext()&& !esIPFija;) {
		for (Iterator iter = peticionLocal.getRecursos_ba().iterator(); iter.hasNext()&& !seinformaZA;) {
			Recursos_baLocal rba =(Recursos_baLocal)iter.next();
			//for (Iterator iter2 = recursos_linea_basicaLocal.getZonas_atendimiento().iterator(); iter2.hasNext();) {
			for (Iterator iter2 = peticionLocal.getRecursos_linea_basica().iterator(); iter2.hasNext();) {
			
				Recursos_linea_basicaLocal lb = (Recursos_linea_basicaLocal) iter2.next();
				log.debug("cantidad de zonas en rlb: "+lb.getZonas_atendimiento().size());
				
				if(lb.getZonas_atendimiento() != null){
					for (Iterator iter3 = lb.getZonas_atendimiento().iterator(); iter3.hasNext();) {
						Zonas_atendimientoLocal za = (Zonas_atendimientoLocal) iter3.next();
						log.debug("ZA mire: " +za.getIp());
						log.debug("RBA mire: " +rba.getCod_zona_atend());
						if(!za.getIp().equalsIgnoreCase(rba.getCod_zona_atend())){
							// Le envia la coleccion de String que necesita la TR con el primero que encuentre igual
							Collection zarec = new ArrayList();
							zarec.add(new String(za.getIp().toString()));
							tr038e.setRangeZoneList(zarec);//Para Zonas de cobertura cuando es traslado
							break;
						}
					}			
				}				
			}
		}

		
		 	
		String fatherEmail = null;
		Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		//Voy a asumir que aca viene uno solo.
		Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
		if(!psprim.isSacarFatherEmAlta()) {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsId(),psprim.getOpComId()).iterator().next();
		} else {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsIdAlta(),psprim.getOpCoAlta()).iterator().next();
		}
		Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
		Long psId = subPet.getCod_pro_ser_cd();
		Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
		Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
		try {
			if(psprim.isSacarFatherEmAlta() && psId.longValue()==psprim.getPsIdAlta().longValue()) {
				fatherEmail = extraerFatherEmail(subPet);
			} else if(psId.longValue()==psprim.getPsId().longValue())	{
						//si la peticion es de ic y tiene id pc se envia el id pc como father email ..sino se saca la caracteristica .. si la caracteristica esta vacia se pone "".
						if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC) {
							if( agrupacion_atisLocal.getNum_ide_nu()!=null && !agrupacion_atisLocal.getNum_ide_nu().equals("") ) {
								fatherEmail = agrupacion_atisLocal.getNum_ide_nu();
							} else	{
								fatherEmail = extraerFatherEmail(subPet);
							}
						} else {
							//si no es IC (cualquier ba) siempre se saca la caracteristica sino va vacio.
							fatherEmail = extraerFatherEmail(subPet);
						}
					}else {
						fatherEmail = producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu();
					}
					if(fatherEmail == null) {
						fatherEmail = subPet.getFk_agru_sub().getNum_ide_nu();
					}
						log.info("Setie el valor de father eemail en:"+ fatherEmail);
		}
		catch(Exception e) {
			//TODO: Mejorar esto: Si no viene email, debe ir a PGC la actividad.
			log.warn("No viene el EMAIL",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}
		addTypeValue(valueList, "father-email", fatherEmail);
//		End father-email
		String servicePhone = obtenerServicePhone(peticion, psprim, reversa, recursos_linea_basicaLocal, agrupacion_atisLocal, tipoPc);
		tr038e.setServiceNumber(servicePhone);
		tr038e.setTerminalNumber("");//agonz AT-1579 18-08-2008"
			
//		tr038e.setPreviousServiceType(retornarCambioPlanBaBaja(peticionKey.peti_numero).longValue());//CR-23809 12 marzo 2009
		log.debug("solicitudConfiguracionSigresCambioPlan() Enviando mensaje TR038E de Mensaje");
		ConfiguracionServicioSigresCambioPlanMQ configuracionServicioSigresCambioPlanMQ = new ConfiguracionServicioSigresCambioPlanMQ();
		if(serviceTypeCalculado != null){
			configuracionServicioSigresCambioPlanMQ.enviarMensaje(tr038e);
			if(act != null)
				act.setObservacion("Se envia el mensaje de Configuracion Internet.");
		}
		
	}

//	CR4860 - Sigres -inicio- agonzalez- 15-05-2008 
	
	public void recepcionConfiguracionSigresCambioPlanBAAck(TR039S tr039s) throws ATiempoAppEx {
//		 CR15338 - @Trace  - Inicio 
			BackendExecution bExecution = null;
			try	{
				bExecution = BackendTraceUtil.initExecution(tr039s, log);
				bExecution.setIdMensajeOp(tr039s.getId());
				bExecution.startOperation();
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				log.info("recepcionConfiguracionSigresCambioPlanBAAck() START TR039s peticionATiempo=" + tr039s.getAtiempoRequestNumber() + " idMsg=" + tr039s.getId());
				validaHome ();
				boolean esEspera=false;
				Mensaje_estadoLocal mesajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr039s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			   try {
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			   } catch (FinderException fex) {
					log.debug("recepcionConfiguracionSigresCambioPlanBAAck() No encontró mensaje estado BA Local");
					//pero puede ser un mensaje que estaba en proceso.
					Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr039s.getAtiempoRequestNumber()),new Integer(estadoEsperaManual));
					if(lista.size() != 1) {
						log.warn("recepcionConfiguracionSigresCambioPlanBAAck() Mensajes encontrados = "+lista.size()+" para peticionAtis=" + tr039s.getAtiempoRequestNumber() + " y estado es espera manual (valor=" + estadoEsperaManual + ")");
						mensajeEstadoBaLocal = null ;
					} else {
						mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
						esEspera=true;
					}
			   }

				/*
				 * Validacion de existencia de la respuesta en la Base de Datos
				 * en la tabla Mensaje_Estado_BA
				 */
				if (mensajeEstadoBaLocal == null) {
					log.debug("recepcionConfiguracionSigresCambioPlanBAAck() No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr039s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr039s.getId(),ATiempoAppEx.EXCEPTION);
				}
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

				//Validacion del estado de la respuesta
				
				if(!esEspera) {
					if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
						log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "
								+ XMLUtilities.marshall(tr039s));
						return;
					}
				}

				Recursos_baLocal recursos_baLocal;
				Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

				if (recursosLineaBaBasica.size() == 0) {
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
					recursos_baLocal = recursos_baLocalHome.create(idConDos);
				} else {
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}
				boolean error = false;
				String errorCode = tr039s.getCode();
				String errorMsg = tr039s.getErrorMessage();
				
				if (errorMsg == null || errorMsg.length()==0) {
					errorMsg = tr039s.getDescription();
				}
			
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				StringBuffer errores = new StringBuffer("");
			
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				//Seteo que recibí una primer respuesta de Sigres, de mas de una respuesta que devuelve Sigres en este caso
				mensajeEstadoBaLocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_CON_MENSAJE));
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();

				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				//CR-19590 - agonz - 11/06/2008
				switch (Integer.parseInt(tr039s.getAction())){
			      case 0:
			      	recursos_baLocal.setCod_error(null);
			      	actDto.setObservacion("Actividad Exitosa /"+" Respuesta:  " + tr039s.getCode() + " -  " +  tr039s.getErrorMessage());
  	      		  break;
			      case 1://caso PGI
			      case 2://caso PGC

				      	recursos_baLocal.setCod_error(new Integer(errorCode));
						recursos_baLocal.setDesc_error(errorMsg);
						errores.append("Código: " + errorCode + ", Descripción: " + errorMsg);
					
						mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
						mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
						String codError = String.valueOf(errorCode);
						String nombreClase=TR039S.class.getName();
						nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
						Long codCausa =  new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM"));
						if(errorLegado != null){
						   peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						   codCausa = errorLegado.getIdCausa();
						} else {
							log.warn("recepcionConfiguracionSigresCambioPlanBAAck() No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + codError);
						}
											
						if(tr039s.getAction().equals("1")){
							log.debug("recepcionConfiguracionSigresBAOk() TR039 derivando a PGI ... Action: "+tr039s.getAction());
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " + tr039s.getCode() + " -  " +  tr039s.getErrorMessage(),true);
						}else{
							log.debug("recepcionConfiguracionSigresBAOk() TR039 derivando a PGC ... Action: "+tr039s.getAction());
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
							actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " + tr039s.getCode() + " -  " +  tr039s.getErrorMessage(),true);
						}
						
				        if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
							insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
				      		actividadEJB.terminar(actDto);
						}
				        return;
			      case 3:
			      		actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr039s.getCode() + " -  " +  tr039s.getErrorMessage());
			      		actividadEJB.grabarSinTerminar(actDto);	
			      		log.debug("recepcionConfiguracionSigresCambioPlanBAAck() Grabando mensaje en espera peticionAtis=" + tr039s.getAtiempoRequestNumber());
						mensajeEstadoBaLocal.setMensaje_estado(mesajeEspera);
						return;
								
			      default:
			           log.warn(" El valor "+tr039s.getAction()+" correspondiente al Action es Erroneo ... peticionAtis=" + tr039s.getAtiempoRequestNumber());
			           	break;
				}

				
				
				actividadEJB.grabarSinTerminar(actDto);

			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.warn("recepcionConfiguracionSigresCambioPlanBAAck() Error parseo de nro",e);
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.warn("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.warn("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.warn("",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} catch(Exception e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
				// CR15338 - @Trace -  FIN 
			}
		}
		
//	CR4860 - Sigres -inicio- agonzalez- 15-05-2008 	
		public void recepcionConfiguracionSigresCambioPlanBAOk(TR040S tr040s) throws ATiempoAppEx 	{
//			 CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
			try {
				bExecution = BackendTraceUtil.initExecution(tr040s, log);
				bExecution.setIdMensajeOp(tr040s.getId());
				bExecution.startOperation();
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				
				log.info("recepcionConfiguracionSigresCambioPlanBAOk() START  TR040s peticionAtis=" + tr040s.getAtiempoRequestNumber() + " isMsg=" + tr040s.getId());
				validaHome ();
				boolean esEspera=false;
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));

				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr040s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			   try {
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
			   } catch (FinderException fex) {
					log.debug("recepcionConfiguracionSigresCambioPlanBAOk() No encontró mensaje estado BA Local");
					//pero puede ser un mensaje que estaba en proceso.
					Collection lista=mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr040s.getAtiempoRequestNumber()),new Integer(estadoEsperaManual));
					if(lista.size() != 1) {
						log.warn("recepcionConfiguracionSigresCambioPlanBAOk() Mensajes encontrados = "+lista.size()+" para peticionAtis=" + tr040s.getAtiempoRequestNumber() + " y estado es espera manual (valor=" + estadoEsperaManual + ")");
						mensajeEstadoBaLocal = null ;
					} else {
						mensajeEstadoBaLocal=(Mensaje_estado_baLocal) lista.iterator().next();
						esEspera=true;
					}
			   }

			/*
			 * Validacion de existencia de la respuesta en la Base de Datos
			 * en la tabla Mensaje_Estado_BA
			 */
			if (mensajeEstadoBaLocal == null) {
				log.debug("recepcionConfiguracionSigresCambioPlanBAOk() No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr040s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr040s.getId(),ATiempoAppEx.EXCEPTION);
			}
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;


			if(!esEspera) {
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "
							+ XMLUtilities.marshall(tr040s));
					return;
				}
			} 

			Recursos_baLocal recursos_baLocal;
			Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

			if (recursosLineaBaBasica.size() == 0) {
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
				recursos_baLocal = recursos_baLocalHome.create(idConDos);
			} else {
				recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
			}
			boolean error = false;

			String errorCode = tr040s.getStatusCode();
			String errorMsg = tr040s.getErrorMessage();
			OperationStatus op = opStatus.getOperationStatus(tr040s , errorCode , tr040s.getErrorMessage()); //Pablo L

			log.debug("recepcionConfiguracionSigresCambioPlanBAOk()TR040S Hay error?? error=" + op.isError() + " code='" + //TODO: tr040s.getCode() +
					"' errorMessage=" + tr040s.getErrorMessage() + " tr040s.getErrorMessage()=" + tr040s.getErrorMessage() + " id tr040s: " + tr040s.getId());

			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
			PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
			PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();

			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			
			switch (Integer.parseInt(tr040s.getAction())){
		      case 0://no hay error
		          // NO hay error
					recursos_baLocal.setCod_error(null);
					recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
					recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);

					if(tr040s.getIp()!=null && tr040s.getIp().trim().length()!=0) 	{

						recursos_baLocal.setOds_sigres(tr040s.getTicketNumber());//ok
						recursos_baLocal.setDir_ip_dslam_actual(tr040s.getId());
						
						//campos Actuales
						recursos_baLocal.setDir_ip_dslam_actual(tr040s.getPreviousIp());
						recursos_baLocal.setMasc_actual(tr040s.getPreviousIpLanMask());//agonz- 02/10/2009- 22577
						recursos_baLocal.setDir_ip_wan_actual(tr040s.getPreviousIpWan());//agonz- 02/10/2009- 22577 
						recursos_baLocal.setDir_ip_lan_actual(tr040s.getPreviousIpLan()); //agonz- 02/10/2009- 22577
						recursos_baLocal.setPuerto_actual(tr040s.getPreviousPortId());//ok
						recursos_baLocal.setPost_actual(tr040s.getPreviousPots());//ok
						recursos_baLocal.setAdsl_actual(tr040s.getPreviousAdsl());//ok
						recursos_baLocal.setVpivci_actual(tr040s.getPreviousVpiVci());//ok
						recursos_baLocal.setVpivci_red_actual(tr040s.getPreviousVpiVciNetwork());//ok
						recursos_baLocal.setPort_modification_flag(tr040s.getPortModificationFlag());				
						recursos_baLocal.setFrame_actual(tr040s.getPreviousSubrack());
						recursos_baLocal.setSlot_actual(tr040s.getPreviousSlot());						
						
						//campos Nuevos
						recursos_baLocal.setDir_ip_dslam_nueva(tr040s.getIp());
						recursos_baLocal.setMasc_lan_nueva(tr040s.getIpLanMask());
						recursos_baLocal.setDir_ip_wan_nueva(tr040s.getIpWan());
						recursos_baLocal.setDir_ip_lan_nueva(tr040s.getIpLan());
						recursos_baLocal.setPuerto_nuevo(tr040s.getPortId());
						recursos_baLocal.setPost_nuevo(tr040s.getPots());
						recursos_baLocal.setAdsl_nuevo(tr040s.getAdsl());
						recursos_baLocal.setVpivci_nuevo(tr040s.getVpiVci());
						recursos_baLocal.setVpivci_red_nuevo(tr040s.getVpiVciNetwork());
						recursos_baLocal.setSlot_nuevo(tr040s.getSlot());//agonz- 02/09/2009- defecto 22577
						recursos_baLocal.setFrame_nuevo(tr040s.getSubrack());//agonz- 02/09/2009- defecto 22577
						
						TypeValueList typeValueList = tr040s.getOthers();
						Map map = getTypeValuAsMap(typeValueList);
						String fEmail = (String)map.get("father-email");
						if (fEmail != null && fEmail.length() != 0) {
							recursos_baLocal.setFather_email_nuevo(fEmail);
						}
						


					}
					mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
					mensajeEstadoBaLocal.setMensaje_estado(mensajeOkLocal);
					
					actDto.setObservacion("Actividad exitosa /"+" Respuesta:  " +  tr040s.getErrorMessage());
		      		break;
		      case 1://caso PGI
		      case 2://caso PGC	
			      	
						recursos_baLocal.setCod_error(new Integer(errorCode));
						recursos_baLocal.setDesc_error(errorMsg);
						mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
						String codError = String.valueOf(errorCode);
						String nombreClase=TR040S.class.getName();
						nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
						Long codCausa =  new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM"));
						if(errorLegado != null) {
						   peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						   codCausa = errorLegado.getIdCausa();
						} else {
							log.warn("recepcionConfiguracionSigresCambioPlanBAOk() No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + codError);
						}
						
						 if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
						 	insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
						 }
						
						if(tr040s.getAction().equals("1")){
							log.debug("recepcionConfiguracionSigresBAOk() TR040 derivando a PGI ... Action: "+tr040s.getAction());
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							actDto.setObservacion("ERROR, se deriva a PGI / " + " Respuesta:  " +  tr040s.getErrorMessage(),true);
						}else{
							log.debug("recepcionConfiguracionSigresBAOk() TR040 derivando a PGC ... Action: "+tr040s.getAction());
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
							actDto.setObservacion("ERROR, se deriva a PGC / " + " Respuesta:  " +  tr040s.getErrorMessage(),true);
						}
						
				  		 if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
						 	actividadEJB.terminar(actDto);
						 }else{
						  	 actividadEJB.grabarSinTerminar(actDto);
						  }
						 return;
					
		      case 3:
		      	    actDto.setObservacion("Novedad de avance /"+" Respuesta:  " +  tr040s.getErrorMessage());
		      	    actividadEJB.grabarSinTerminar(actDto);	
		      	    return;
		      		
		      default:
		          throw new ATiempoAppEx(" El valor "+tr040s.getAction()+" correspondiente al Action es Erroneo ... peticionAtis=" + tr040s.getAtiempoRequestNumber());
		           	
			}		
			
		
			 //TODO: 01022010 - RETA - Ajuste para requerimiento Req_2009_00031777_CambioVelocidadEnVuelo
			  if (!peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBaLocal.getCod_actividad_generadora())){
			  	actividadEJB.terminar(actDto);
			  }else{
			  	 actividadEJB.grabarSinTerminar(actDto);
			  }
			  //End TODO
			
		}catch (NumberFormatException e) 	{
			bExecution.setErrorOp(true);
			log.warn("",e);
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("Error",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("Error",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			e.printStackTrace();
			log.warn("Error",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			e.printStackTrace();
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace  - FIN 
		}
	}
	
	/**
	 * @author 808026
	 * Metodo Auxiliar "huboCambiosPuertoIp" retorna true sii 
	 * hubieron cambios en ips o puertos
	 */
	
	public boolean huboCambiosPuertoIp (Long nroPeticion)throws ATiempoAppEx{
		
		try {
			if (tieneCambioPlanBa(nroPeticion)){
				if(peticionLocalHome==null)
					peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				
				PeticionLocal petiLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
				Recursos_baLocal recursos_ba = null;
	
				if (petiLocal.getRecursos_ba().size()>0){
					if ( petiLocal.getRecursos_ba().size() > 1) {
						log.debug("solicituConfiguracionSigresCambioPlan() tiene mas de una Banda ancha");
					}	
					for(Iterator iter = petiLocal.getRecursos_ba().iterator();iter.hasNext(); ) {
						recursos_ba = (Recursos_baLocal) iter.next();
					}
				}
			boolean huboModificacionIP = false;
			if(recursos_ba.getDir_ip_dslam_actual()!=null && recursos_ba.getDir_ip_dslam_nueva()!=null)
			huboModificacionIP = !recursos_ba.getDir_ip_dslam_actual().equalsIgnoreCase( recursos_ba.getDir_ip_dslam_nueva());
			
			
			
			boolean huboModificacionPuerto=false;
			if(recursos_ba.getPort_modification_flag()!=null)
				 huboModificacionPuerto= recursos_ba.getPort_modification_flag().equalsIgnoreCase("Si" );
				
			log.debug("huboModificacionIP "+huboModificacionIP);
			log.debug("huboModificacionPuerto "+huboModificacionPuerto);				
			return (huboModificacionPuerto||huboModificacionIP);
			//retorna true sii hubo algún cambio en las ip o puertos
			}else{
				return true;
				
				
			}
		}catch (NamingException e){
			
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}catch (Exception e){
			
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}
	
	/**
	 * @author juan 
	 */
	// TODO: AT-1035 - Cambio de plan Sigres - Inicio - Juan - 27/06/2008 
	public boolean tieneCambioPlanBa(Long idPeticion) {
		
		boolean resultado=false;
		try {
			InfoComunColombiaBusinessInterface infoComunColombiaBI;
		
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
			ProductoDTO psDto;
		for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
		{
			
			 psDto = (ProductoDTO) listadoPS.get(i);
			
			//log.debug("Operacion Comercial..: "+ psDto.getTipoOperacionComercial()+" Peticion..:" + idPeticion);
//			REQ BA NAKED adicion familia PC naked
			if ((psDto.getIdFaps().intValue()== familiaPcBA && psDto.getTipoOperacionComercial().equalsIgnoreCase("ACP"))
					||psDto.getIdFaps().intValue()== familiaPcPsBANaked && psDto.getTipoOperacionComercial().equalsIgnoreCase("ACP")) {
			
					resultado= true;
			}
		}
		} catch (ATiempoAppEx e) {
					log.debug("Error validando si tiene cambio de Plan..:",e);
					e.printStackTrace();
		}
		return resultado;
		
	}
	
	/**
	 * @author juan 
	 * Recorre un array de PS's
	 	lo itera hasta encontrar un PS de familia BA y operacion BAJA
		si lo encuentra entonces el resultado es el id del PS
		si no lo encuentra retorna 0 
	 */
	// CR-23809 12 marzo 2009
	public Long retornarCambioPlanBaBaja(Long idPeticion) {
		
	    Long resultado=new Long("0");
		try {
			InfoComunColombiaBusinessInterface infoComunColombiaBI;
		
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
			ProductoDTO psDto;
		for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
		{
			
		    psDto = (ProductoDTO) listadoPS.get(i);
			
			log.debug("Operacion Comercial..: "+ psDto.getTipoOperacionComercial()+" Peticion..:" + idPeticion);
//			REQ BA NAKED adicion familia PC naked
			if ((psDto.getIdFaps().intValue()== familiaPcBA && psDto.getTipoOperacionComercial().equalsIgnoreCase("BCP"))
					||(psDto.getIdFaps().intValue()== familiaPcPsBANaked && psDto.getTipoOperacionComercial().equalsIgnoreCase("BCP"))) {
			
					resultado= psDto.getId();
					break;
			}
		}
		} catch (ATiempoAppEx e) {
					log.debug("Error validando si tiene cambio de Plan..:",e);
					e.printStackTrace();
		}
		return resultado;
		
	}
	/**
	 *  @author 808026
	 *	Defecto 20476 - 24/11/2008 - 
	 **/
	public boolean esTrasladoBa(Long idPeticion)throws ATiempoAppEx{
	    boolean resultado = false;
	    try {
			InfoComunColombiaBusinessInterface infoComunColombiaBI;
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
		
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
			{
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				
				boolean tipoOpCoAlta = (psDto.getTipoOperacionComercial()!=null) && (psDto.getTipoOperacionComercial().equalsIgnoreCase("A"));
				boolean tasladoOpCo = (psDto.getTrasladoOperCom()!=null) && (psDto.getTrasladoOperCom().equalsIgnoreCase("T"));
//				REQ BA NAKED adicion familia PC naked
				if ( ((psDto.getIdFaps().intValue()== familiaPcBA || psDto.getIdFaps().intValue()== familiaPcPsBANaked) && tipoOpCoAlta && tasladoOpCo)) {
				     log.debug("Operacion Comercial..: "+ psDto.getTipoOperacionComercial()+" Peticion..:" + idPeticion);
				     resultado= true;
				}
			}
		} catch (ATiempoAppEx e) {
					log.debug("Error validando si tiene Traslado.. peticion: "+idPeticion,e);
					e.printStackTrace();
		}
	    
	    return resultado;
	}
	
	/*
	 * @author 808026
	 * Metodo Auxiliar "seDebeEntrarAInstalar" retorna true sii es necesario entrar a la Actividad Instalar
	 * Controla que,solo si se debe ir a la actividad instalación si existen ps que no son de la familia "familiaPcBA" 
	 * que necesitan intalacion 
	 */
	 public boolean seDebeEntrarAInstalar (Long nroPeticion,Integer idActividadFlujo) throws ATiempoAppEx{	
		try
		{
			int cantPSInternetMovil = 0;
			int cantPSInstalar = 0;
			Ps_Tipo_ModemLocalHome psTipoModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
		
			if(peticionLocalHome==null)
				peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			PeticionLocal petiLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPeticion));
			//Hasta aqui lo que se hizo fue obtener la peticion
			Collection collectionPS = petiLocal.getProducto_servicio_peticion();
			//Se obtiene de la peticion  una coleccion de ps 
			
			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			PeticionKey peticionKey=(PeticionKey) petiLocal.getPrimaryKey();
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.LLAVE_PROPERTIES_PS_INTERNET_MOVIL);
			String psIntTotal = propertiesBDLocal.getValor();
			String[] listaPsIntTotal = psIntTotal.split(",");
			
			for(Iterator iterator=collectionPS.iterator();iterator.hasNext();)
			{//se itera la coleccion 
				Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
				
				Producto_servicioLocal producto_servicioLocal=ps.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=ps.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				
				Long idFamilia=ps.getFamiliaKey().faps_id;
//				REQ BA NAKED adicion familia PC naked
			if(idFamilia.intValue() == familiaPcBA || idFamilia.intValue() == familiaPcPsBANaked){
				try{
					psTipoModemLocalHome.findByNroPs(producto_servicioKey.ps_id);
				}catch(FinderException ex){
					log.debug("El ps :"+producto_servicioKey.ps_id+" no se encuentra catalogado como un tipo modem, por lo tanto no se tiene " +
							"en cuenta para validar si debe ejecutarse la actividad de instalación");
					continue;
				}
			}

//				Correccion de error en Produccion AT-2487 - 18/08/2009 
				if(peticionesDelegate.pasaPSyOcXActividad(peticionKey.peti_numero,producto_servicioKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo)){	
				Operacion_comercialLocal op=ps.getOperacion_comercial();
				if(op.getOpco_tipo()!=null && op.getOpco_tipo().equals("A")|| op.getOpco_tipo()!=null && op.getOpco_tipo().equals("ACP"))
					return true;
					if(op.getOpco_tipo()!=null && op.getOpco_tipo().equals("A")|| op.getOpco_tipo()!=null && op.getOpco_tipo().equals("ACP")){

						cantPSInstalar = cantPSInstalar+1;
	        			for(int contPs=0;contPs<=listaPsIntTotal.length-1;contPs++){
	        				if (ps.getPsId().toString().equals(listaPsIntTotal[contPs])){
	        					cantPSInternetMovil = cantPSInternetMovil +1;
	        				}
	        			}
					}
				}
			}

			if (cantPSInstalar == 0) return false; //Inhibe la Actividad
			else if (cantPSInstalar == 1  &&  cantPSInternetMovil == 1) return false; //Inhibe la Actividad 
			else if (cantPSInstalar >= 1 ) return true;  //No Inhibe la Actividad
			
			return false;
		}
		catch(NamingException e)
		{
			log.debug("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	 }


	 
		public void recepcionSuspensionReconexionBASigres(TR042S tr042s) throws ATiempoAppEx {
//			 CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
			try {
				bExecution = BackendTraceUtil.initExecution(tr042s, log);
				bExecution.setIdMensajeOp(tr042s.getId());
				bExecution.startOperation();
				
				log.info("recepcionSuspensionReconexionBASigres START TR042s peticionAtiempo=" + tr042s.getAtiempoRequestNumber() + " idMsg=" + tr042s.getId());
				validaHome();
				boolean esEspera = false;
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));

				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr042s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal;

				try {
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
				} catch (FinderException fex) {
										
					log.debug("recepcionSuspensionReconexionBASigres No encontró mensaje estado BA Local");
					//pero puede ser un mensaje que estaba en proceso.
					Collection lista = mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr042s.getAtiempoRequestNumber()), new Integer(estadoEsperaManual));
					if (lista.size() != 1) {
						mensajeEstadoBaLocal = null;
					} else {
						mensajeEstadoBaLocal = (Mensaje_estado_baLocal) lista.iterator().next();
						esEspera = true;
					}
				}

				/*
				 * Validacion de existencia de la respuesta en la Base de Datos
				 * en la tabla Mensaje_Estado_BA
				 */
				if (mensajeEstadoBaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr042s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr042s.getId(), ATiempoAppEx.EXCEPTION);
				}
				Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey();

				//Validacion del estado de la respuesta
				if (!esEspera) {
					if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
						log.debug("El estado de la respuesta es diferente para ser procesado (" + mensaje_estadoKey.cod_estado + ")\n " + XMLUtilities.marshall(tr042s));
						return;
					}
				} else {
					if (mensaje_estadoKey.cod_estado.intValue() == estadoOk) {
						log.debug("El estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr042s));
						return;
					}
				}

				mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			    

				
				int codigoAccion = Integer.parseInt(tr042s.getAction());
				if (codigoAccion !=1 && codigoAccion!=2) {
//				  	agonz- AT-1775- 19/11/2008
					if(codigoAccion==0){//no hay error
						mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
						mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
						mensajeEstadoBaLocal.setMensaje_estado(mensajeOkLocal);
						
						actDto.setObservacion("Operacion Exitosa /"+" Respuesta:  " + tr042s.getCode() + " -  " +  tr042s.getErrorMessage());
						actividadEJB.terminar(actDto);
				    }else{//estado en espera
				    	actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr042s.getCode() + " -  " +  tr042s.getErrorMessage());
				    	actividadEJB.grabarSinTerminar(actDto);
				    	return;
				        
				    }
					
				} else {

					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					String nombreClase = TR042S.class.getName();
					nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(tr042s.getCode(), nombreClase);
					Long codCausa = null;
					if (errorLegado != null) {
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						codCausa = errorLegado.getIdCausa();
					} else {
						codCausa = new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO"));
						log.warn("recepcionSuspensionReconexionBASigres: No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + tr042s.getCode());
					}
					
					if(codigoAccion == 1){
						log.debug("recepcionSuspensionReconexionBASigres: derivando a PGI");					
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					
					} else {
						log.debug("recepcionSuspensionReconexionBASigres: derivando a PGC");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
					}
					insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
					actDto.setObservacion("Código: " + tr042s.getCode() + ", Descripción: " + tr042s.getErrorMessage()); //Pablo L
					
					
					actividadEJB.terminar(actDto);					
				}
			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.debug("", e);
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.debug("", e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.debug("", e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				log.debug("", e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} catch (Exception e) {
				bExecution.setErrorOp(true);
				e.printStackTrace();
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
				// CR15338 - @Trace - FIN 
			}
		}
	 
	 
		/**
		 * enviaSuspensionReconexionBASigres
		 * 
		 * Envía la suspension y reconexion de SIGRES, TR042E.
		 * 
		 * @author Gonzalo Arreche CR-4860
		 */
		public void enviaSuspensionReconexionBASigres(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx{
			PeticionLocal peticionLocal = null;
			Long IdCorrelativo = null;
						
			try {
						
				peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(peticion));
				IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				guardarMensajeEstadoBA(IdCorrelativo, peticionLocal, psprim, id_actividad);
				
				enviarTR042E(peticion, psprim, peticionLocal, IdCorrelativo);
			
				
			} catch (FinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//metodo extraido para ser reutilizado - Bandeja Reintentos - Gustavo G
		public void enviarTR042E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
			Producto_servicio_peticionLocal ps =null;
			Producto_servicio_peticionLocalHome psHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			ps=(Producto_servicio_peticionLocal) psHome.findByPetiNumeroPsYOpCo(peticion,psprim.getPsId(),psprim.getOpComId()).iterator().next();
					
			
				/*
				 * Genera el mensaje a enviar (tr042e), con los datos de ID, AtiempoRequestNumber
				 * y ServiceNumber (que es el numero de telefono).
				 */				
			PeticionKey peticionKey = null;
				peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();				
				
			TR042E tr042e =null;
				tr042e = new TR042E();
				tr042e.setId(String.valueOf(IdCorrelativo));
				tr042e.setAtiempoRequestNumber(peticionKey.peti_numero.longValue());
				tr042e.setServiceNumber(getServiceNumber(peticionLocal, null));
				tr042e.setComercialOperation(ps.getIdOperacionComercial().longValue());
				
				SuspensionReconexionBASigresMQ mq = new SuspensionReconexionBASigresMQ();
				mq.enviarMensaje(tr042e);
				log.info("MENSAJE DE SUSPENSION ENVIADO...");
			}
		/**
		 * getServiceNumber
		 * 
		 * Obtiene el numero telefonico de la peticion.
		 * El parametro idServicio es utilizado para traslados.
		 * Para el alta no se utiliza esta función porque se requieren mas validaciones.
		 * 
		 * @author Gonzalo Arreche CR4860
		 * @param peticionLocal
		 * @param idServicio
		 * 
		 * @return el numero de servicio
		 */
		private String getServiceNumber(PeticionLocal peticionLocal, String idServicio){
			String phoneNumber = peticionLocal.getIdentificadorOriLinea();
			//si viene un telefono, se consulta por ese telefono y se asocia a la peticion
			if (idServicio != null && !"".equals(idServicio.trim())) {
				phoneNumber = idServicio;
			}
			if (phoneNumber != null && phoneNumber.trim() != "") {
				if (phoneNumber.length() > 8)
					phoneNumber = phoneNumber.substring(0, 8);
					//Seteo del TR en caso que exista el numero
				} else {
					phoneNumber = "";
				}
			return phoneNumber;
		}
		/**
		 * guardarMensajeEstadoBA
		 * 
		 * Guarda el mensaje en la tabla MENSAJE_ESTADO_BA.
		 * 
		 * @author Gonzalo Arreche
		 * @param idCorrelativo
		 * @param peticionLocal
		 * @param psprim
		 * @param cod_actividad
		 * @throws ATiempoAppEx
		 */
		private void guardarMensajeEstadoBA(Long idCorrelativo, PeticionLocal peticionLocal, PsVsOcVO psprim, String cod_actividad) throws ATiempoAppEx {
			Mensaje_estado_baLocal mensajeEstadoBALocal = null;
			Mensaje_estadoLocal mensajeEspera = null;
			try {
				mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(idCorrelativo);
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
				mensajeEstadoBALocal.setMensaje_estado(mensajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);
			} catch (FinderException e) {
				log.warn("Error guardar mensaje estado", e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (CreateException e) {
				log.warn("Error guardar mensaje estado", e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (NamingException e) {
				log.warn("Error guardar mensaje estado", e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
			}
		}
		
		/** 
		* recepcion de Baja BA
		* @author agonz
		*/
			public void recepcionBajaBASigres(TR037S tr037s) throws ATiempoAppEx {
//				 CR15338 - @Trace - Inicio 
				BackendExecution bExecution = null;
				try {
					bExecution = BackendTraceUtil.initExecution(tr037s, log);
					bExecution.setIdMensajeOp(tr037s.getId());
					bExecution.startOperation();
					
					log.info("recepcionBajaBASigres START TR037s peticionAtiempo=" + tr037s.getAtiempoRequestNumber() + " idMsg=" + tr037s.getId());
					validaHome();
					boolean esEspera = false;
					Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
					Mensaje_estadoLocal mensajeErrorLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
					Mensaje_estadoLocal mensajeOkLocal = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));

					Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr037s.getId()));
					Mensaje_estado_baLocal mensajeEstadoBaLocal;

					try {
						mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
					} catch (FinderException fex) {
						log.debug("recepcionBajaBASigres No encontró mensaje estado BA Local");
						//pero puede ser un mensaje que estaba en proceso.
						Collection lista = mensajeEstadoBaLocalHome.findEstadoPeticion(new Long(tr037s.getAtiempoRequestNumber()), new Integer(estadoEsperaManual));
						if (lista.size() != 1) {
							mensajeEstadoBaLocal = null;
						} else {
							mensajeEstadoBaLocal = (Mensaje_estado_baLocal) lista.iterator().next();
							esEspera = true;
						}
					}

					/*
					 * Validacion de existencia de la respuesta en la Base de Datos
					 * en la tabla Mensaje_Estado_BA
					 */
					if (mensajeEstadoBaLocal == null) {
						log.debug("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr037s));
						throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr037s.getId(), ATiempoAppEx.EXCEPTION);
					}
					Mensaje_estadoKey mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey();

					
					if (!esEspera) {
						if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
							log.debug("El estado de la respuesta es diferente para ser procesado (" + mensaje_estadoKey.cod_estado + ")\n " + XMLUtilities.marshall(tr037s));
							return;
						}
					} 
				  	Recursos_baLocal recursos_baLocal;
					Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();
						
					if (recursosLineaBaBasica.size() == 0) {
						Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC")); //TODO: CR4860 Verificar si esta bien usar CORRELATIVO_APSC para SIGRES
						System.out.println("Creando recurso BA TR=037S");
						recursos_baLocal = recursos_baLocalHome.create(idConDos);
					} else {
						recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
					}
					

					mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
					PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
					PeticionLocal peticionLocal = (PeticionLocal) mensajeEstadoBaLocal.getPeticion();
					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			      	IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());					
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
					
								
					log.debug(" El valor correspondiente al Action es "+tr037s.getAction()+" ... peticionAtis=" + tr037s.getAtiempoRequestNumber());
					switch (Integer.parseInt(tr037s.getAction())){
				      case 0://no hay error

							mensajeEstadoBaLocal.setFecha_cierre(df.format(new java.util.Date()));
							mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
							mensajeEstadoBaLocal.setMensaje_estado(mensajeOkLocal);
							recursos_baLocal.setCod_error(null);
							recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
							recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
							if(tr037s.getIp()!=null && tr037s.getIp().trim().length()!=0) 	{
								recursos_baLocal.setDir_ip_dslam_actual(tr037s.getIp());
								recursos_baLocal.setPuerto_actual(tr037s.getPortId()); 
								recursos_baLocal.setPost_actual(tr037s.getPots());
								recursos_baLocal.setAdsl_actual(tr037s.getAdsl());
								recursos_baLocal.setDesc_error(tr037s.getDescription());
								
							}
							actividadEJB.terminar(actDto);
						
				      		break;
				      case 1://caso PGI
				      case 2://caso PGC	
							
							recursos_baLocal.setCod_error(new Integer(tr037s.getCode()));
							mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
							String nombreClase = TR037S.class.getName();
							nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
							ErrorLegadoLocal errorLegado = getErrorLegado(tr037s.getCode(), nombreClase);
							Long codCausa = null;
							if (errorLegado != null) {
								peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
								codCausa = errorLegado.getIdCausa();
							} else {
								codCausa = new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO"));
								log.warn("recepcionBajaBASigres: No hay registro en ERROR_LEGADO para nombreClase=" + nombreClase + " codError=" + tr037s.getCode());
							}
							
							if(tr037s.getAction().equals("1")){
								log.debug("recepcionConfiguracionSigresBAOk() TR037 derivando a PGI ... Action: "+tr037s.getAction());
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
								actDto.setObservacion("Error, se deriva a PGI / " + " Respuesta:  " + tr037s.getCode() + " -  " +  tr037s.getErrorMessage(),true);
							}else{
								log.debug("recepcionConfiguracionSigresBAOk() TR037 derivando a PGC ... Action: "+tr037s.getAction());
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
								actDto.setObservacion("Error, se deriva a PGC / " + " Respuesta:  " + tr037s.getCode() + " -  " +  tr037s.getErrorMessage(),true);
							}
							insertarCausalesCnaPeticion(peticionLocal, mensajeEstadoBaLocal.getCod_actividad_generadora(), codCausa, actDto.getIdActividadFlujo());
							actividadEJB.terminar(actDto);					
						
							break;
				      case 3:
					      	log.debug("Grabando mensaje en espera peticionAtis=" + tr037s.getAtiempoRequestNumber());
					      	actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr037s.getCode() + " -  " +  tr037s.getErrorMessage());
							actividadEJB.grabarSinTerminar(actDto);							
								return;
						
				      default:
				      	throw new ATiempoAppEx(" El valor "+tr037s.getAction()+" correspondiente al Action es Erroneo ... peticionAtis=" + tr037s.getAtiempoRequestNumber());
				           	
					}					
					
			  		}catch (NumberFormatException e) {
			  			bExecution.setErrorOp(true);
						log.debug("", e);
						e.printStackTrace();
						throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
					} catch (CreateException e) {
						bExecution.setErrorOp(true);
						e.printStackTrace();
						log.debug("", e);
						throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
					} catch (FinderException e) {
						bExecution.setErrorOp(true);
						e.printStackTrace();
						log.debug("", e);
						throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
					} catch (TnProcesoExcepcion e) {
						bExecution.setErrorOp(true);
						e.printStackTrace();
						log.debug("", e);
						throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
					} catch (Exception e) {
						bExecution.setErrorOp(true);
						e.printStackTrace();
						throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
					}finally{  
						bExecution.endAll();
						// CR15338 - @Trace - FIN 
					}
			}
		
			/** 
			 * envio de Baja BA
			 * 
			 * @author agonz
			 */
			public void enviaBajaBASigres(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx {
				
				PeticionLocal peticionLocal = null;
				
				Long IdCorrelativo = null;
						
				try {
						
					peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(peticion));
					IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
					//guardarMensajeEstadoBA(IdCorrelativo, peticionLocal, psprim, id_actividad);
					
  					Mensaje_estado_baLocal mensajeEstadoBALocal = null;
			  		Mensaje_estadoLocal mensajeEspera = null;
			
				  	mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				  	mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(IdCorrelativo);
				  	mensajeEstadoBALocal.setPeticion(peticionLocal);
				  	Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
				  	Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				  	Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				  	Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				  	mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				  	mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				  	mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
				  	mensajeEstadoBALocal.setMensaje_estado(mensajeEspera);
				  	mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);
			  											
					enviarTR036E(peticion,psprim,peticionLocal,IdCorrelativo);
				
				} catch (FinderException e) {
				  log.warn("", e);
				  throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				} catch (CreateException e) {
				  log.warn("", e);
				  throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
				} catch (NamingException e) {
				  log.warn("", e);
				  throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
				}		
	
			}
			
			//metodo extraido para reutilizar en el reenvio de mensajes - Bandeja de Reintentos - Gustavo
			public void enviarTR036E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
				TR036E tr036e =null;
				PeticionKey peticionKey = null;
				Producto_servicio_peticionLocal ps =null;
					peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();				
				Producto_servicio_peticionLocalHome psHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				ps=(Producto_servicio_peticionLocal) psHome.findByPetiNumeroPsYOpCo(peticion,psprim.getPsId(),psprim.getOpComId()).iterator().next();
				
					tr036e = new TR036E();
					tr036e.setId(String.valueOf(IdCorrelativo));
					tr036e.setAtiempoRequestNumber(peticionKey.peti_numero.longValue());
					tr036e.setServiceNumber(getServiceNumber(peticionLocal, null));
					//TODO averiguar si se esta cargando bien el PssbaDataList

  					TypeValueList valueList = new  TypeValueList();
					addTypeValue(valueList, "client-document-number", peticionLocal.getNum_doc_cli_cd());
					String tc = peticionLocal.getTip_cli_cd();
					if (tc != null && tc.trim().length()!=0 ) {
						addTypeValue(valueList, "client-type", String.valueOf(tc.charAt(0)));
					}
					addTypeValue(valueList, "client-document-type", peticionLocal.getTip_doc_cd());
					addTypeValue(valueList, "client-name", peticionLocal.getNom_ds());
					addTypeValue(valueList, "client-first-lastname", peticionLocal.getPri_ape_ds());
					addTypeValue(valueList, "client-second-lastname", peticionLocal.getSeg_ape_ds());
					LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();		  
					addTypeValue(valueList, "code-name", localidadKey.cod_loc);
		  
					tr036e.setPssbaDataList(valueList);
				
					BajaBASigresMQ mq = new BajaBASigresMQ();
					mq.enviarMensaje(tr036e);
					log.info("MENSAJE DE BAJA ENVIADO...");				
			  	}		
	
	
//CR19590 -- Pablo L -- 11/11/2008

public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx{

	
	BackendExecution bExecution = null;
	try
		{	
			bExecution = BackendTraceUtil.initExecution(tr035s, log);
			bExecution.setIdMensajeOp(tr035s.getId());
			bExecution.startOperation();
			validaHome ();

			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			//Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr035s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

			try{
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
				
			}catch (FinderException fex){
					mensajeEstadoBaLocal = null ;
			}
  
						/*
		 * Validacion de existencia de la respuesta en la Base de Datos 
		 * en la tabla Mensaje_Estado_Linea
		 */
		if (mensajeEstadoBaLocal == null)
		{
			log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr035s));
			throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr035s.getId(),ATiempoAppEx.EXCEPTION);
		}

		Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

		//Validacion del estado de la respuesta 
		if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual)
		{
			log.debug("Es estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr035s));
			return;
		}

		Recursos_baLocal recursos_baLocal;
		Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

		if ( recursosLineaBaBasica.size() == 0){
			Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
			recursos_baLocal = recursos_baLocalHome.create(idConDos);
		}else{
			recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
		}

		mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
		mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
		PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
        
		int codigoAccion = Integer.parseInt(tr035s.getAction());
	    log.debug("El codigo Action es "+codigoAccion);	
	    mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);//agonz 6 abril 2009 AT-2028 CR-24573
		if (codigoAccion == 0){
			log.debug("No tengo Error en el mensaje");

			
			recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
			try
			{
				if(tr035s.getCode()!=null)
				{
					recursos_baLocal.setCod_error(new Integer(String.valueOf(tr035s.getCode())));
				}else
				{
					recursos_baLocal.setCod_error(new Integer(0));
				}
			}
			catch(NumberFormatException nf)
			{
				recursos_baLocal.setCod_error(new Integer(0));
			}

			recursos_baLocal.setDesc_error(tr035s.getErrorMessage());
			recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
			recursos_baLocal.setAdsl_actual(tr035s.getAdsl());
			recursos_baLocal.setDir_ip_dslam_actual(tr035s.getIp());
			recursos_baLocal.setDir_ip_wan_actual(tr035s.getIpWan());
			recursos_baLocal.setDir_ip_lan_actual(tr035s.getIpLan());
			recursos_baLocal.setMasc_actual(tr035s.getIpLanMask());
			recursos_baLocal.setPost_actual(tr035s.getPots());
			recursos_baLocal.setPuerto_actual(tr035s.getPortId());
			recursos_baLocal.setSlot_actual(tr035s.getSlot());
			recursos_baLocal.setVpivci_actual(tr035s.getVpiVci());
			recursos_baLocal.setVpivci_red_actual(tr035s.getVpiVciNetwork());
			recursos_baLocal.setCod_zona_atend(tr035s.getCodeRangeZone());
			recursos_baLocal.setFrame_actual(tr035s.getSubrack());
			
//			se setean los ps actuales y los anteriores
			if(tr035s.getPSActual()!=null){
				recursos_baLocal.setPsActual(tr035s.getPSActual());
				recursos_baLocal.setPsAnterior(tr035s.getPSAnterior());
			}else{
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());//agonz 6 abril 2009 AT-2028 CR-24573

				log.debug("Tengo un error en el mensaje");
				mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				actDto.setObservacion("Error, se deriva a PGI / La Respuesta no trae PS Actual" ,true);
				actividadEJB.terminar(actDto);
				return;
			}
			
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			
			if(mensajeEstadoBaLocal.getAccion()!=null && mensajeEstadoBaLocal.getAccion().equals("RF")){
				
				if (mensajeEstadoBaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)) {
					log.debug("La actividad corresponde a TOA");
					TOADelegate toaDelegate = new TOADelegate();
					toaDelegate.enviaRefrescarRecursosBA(recursos_baLocal);
				} else {
					enviarRefrecarDatos(peticionKey.peti_numero);
				}
				return;
			}
			//actividadEJB.terminar(actDto);
		}
		
		if (codigoAccion == 1 || codigoAccion == 2 || codigoAccion > 3 ){

			if(mensajeEstadoBaLocal.getAccion()!=null && (mensajeEstadoBaLocal.getAccion().equals("R")||mensajeEstadoBaLocal.getAccion().equals("RN")))
				return;
		    ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());//agonz 6 abril 2009 AT-2028 CR-24573

			log.debug("Tengo un error en el mensaje");
			mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));

			if(codigoAccion == 1){
			
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				actDto.setObservacion("Error, se deriva a PGI / " + " Respuesta:  " + tr035s.getCode() + " -  " +  tr035s.getErrorMessage(),true);
			}
			if(codigoAccion == 2){
			    actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
				actDto.setObservacion("Error, se deriva a PGC / " + " Respuesta:  " + tr035s.getCode() + " -  " +  tr035s.getErrorMessage(),true);
			}

			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
			String codError = String.valueOf(tr035s.getCode());
			String nombreClase=TR035S.class.getName();
			nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
			ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
			
		    if(errorLegado != null){
				 peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
				 insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				 actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, errorLegado.getPlataforma());
				 actDto.setObservacion("Error, se deriva con error / " + " Respuesta:  " + tr035s.getCode() + " -  " +  tr035s.getErrorMessage(),true);
			}else {
				insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
			}				
			
			//insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
			actividadEJB.terminar(actDto);
			return;
		}
		if(codigoAccion == 3){

			
		    if(mensajeEstadoBaLocal.getAccion()!=null && (mensajeEstadoBaLocal.getAccion().equals("R")||mensajeEstadoBaLocal.getAccion().equals("RN")))
				return;
		    mensajeEstadoBaLocal.setMensaje_estado(mesajeEspera);
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());

			actDto.setObservacion("Novedad de avance /"+" Respuesta:  " + tr035s.getCode() + " -  " +  tr035s.getErrorMessage());
	    	actividadEJB.grabarSinTerminar(actDto);	
			return;
			
		}
		
		mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
		if (mensajeEstadoBaLocal.getAccion() != null&& (mensajeEstadoBaLocal.getAccion().equals("R")|| mensajeEstadoBaLocal.getAccion().equals("RN") || mensajeEstadoBaLocal.getAccion().equals("RF"))) {
		
			if (mensajeEstadoBaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)) {
					log.debug("La actividad corresponde a TOA");
					TOADelegate toaDelegate = new TOADelegate();
					toaDelegate.enviaRefrescarRecursosBA(recursos_baLocal);
				} else {
					enviarRefrecarDatos(peticionKey.peti_numero);
				}
				return;
				
			}
		ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
		IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
		ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());

			//actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
		actividadEJB.terminar(actDto);
	}
	catch (NumberFormatException e){
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.error("NumberFormatException",e);
		throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
	}
	catch (CreateException e){
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.error("CreateException",e);
		throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
	}
	catch(FinderException e){
		bExecution.setErrorOp(true);	
		e.printStackTrace();	
		log.warn("FinderException",e);
		throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
	}
	catch (TnProcesoExcepcion e){
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.error("",e);
		throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
	} 
	catch(Exception e){
		bExecution.setErrorOp(true);	
		e.printStackTrace();
		log.debug("EXCEPTION",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
			
			}
}			
			
		/**
		 * refrescar datos 
		 * @author agonz 03/07/2008
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad, String codActividad) throws ATiempoAppEx {
			this.enviarConfiguracionActualBASigres(null,peticion,id_actividad, codActividad);	
	}
		
	public void enviarConfiguracionActualBASigres(String telefonoConsulta, String peticion, String id_actividad, String codActividad) throws ATiempoAppEx{		
		try
		{	
			validaHome ();

			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

			boolean esRefrescar=false;
			boolean esRefrescarNew=false;
			String id_actividad_temp ="";
			if(id_actividad.equals(""))
			{
				esRefrescar=true;
				esRefrescarNew=false;
//				id_actividad=ComunInterfaces.COD_ACTIVIDAD_OBT_CONF_BA_SI;//agonz 6 abril 2009 AT-2028 CR-24573
			}else if(id_actividad.equals("RN")|| id_actividad.equals("RF")){
				esRefrescar=false;
				esRefrescarNew=true;
				id_actividad_temp = id_actividad;
//				id_actividad=ComunInterfaces.COD_ACTIVIDAD_OBT_CONF_BA_SI;//agonz 6 abril 2009 AT-2028 CR-24573
			}
			
			if(codActividad != null){
				id_actividad = codActividad;
			}else{
				id_actividad=ComunInterfaces.COD_ACTIVIDAD_OBT_CONF_BA_SI;//agonz 6 abril 2009 AT-2028 CR-24573
			}

			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();

			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR035E tr035e = new TR035E();
			tr035e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
	
			String phoneNumber = peticionLocal.getIdentificadorOriLinea();			
			log.debug("Valor de phoneNumber: " + phoneNumber);
			
			
			
			//si viene un telefono, se consulta por ese telefono y se asocia a la peticion
			if (telefonoConsulta != null && !"".equals(telefonoConsulta.trim()))
			{
				phoneNumber = telefonoConsulta;							
			}
			if (phoneNumber!=null && phoneNumber.trim()!= ""){
				if (phoneNumber.length()>8) 
					phoneNumber=phoneNumber.substring(0,8);
				
			}else{
			    Collection listaPsPet=peticionLocal.getProducto_servicio_peticion();
			    Iterator itPSPet=null;
			   
			    if(listaPsPet!=null){
			    	for(itPSPet=listaPsPet.iterator();itPSPet.hasNext();){
			    		Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)itPSPet.next();
			    		Familia_producto_servicioKey fKey=producto_servicio_peticionLocal.getFamiliaKey();
			    		
			    		if(fKey.faps_id.intValue()==ComunInterfaces.familiaMantenimiento){
			    			Long psId=producto_servicio_peticionLocal.getPsId();
			    			if(psId.longValue()==ComunInterfaces.psMNTTV){
			    				phoneNumber=peticionLocal.getNum_ide_nu_tv();
			    			}else{
			    				phoneNumber=peticionLocal.getNum_ide_nu_stb();
			    			}
			    		}else{
			    			phoneNumber="";
			    		}
			    	}
			    }else{
			    	phoneNumber="";
			    }
			}	
			
			tr035e.setServiceNumber(phoneNumber);
			
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
			//mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familiaPcBA));
			
			//TODO: Verificar el Conector Correcto
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));
			
			if(esRefrescar){
				mensajeEstadoBALocal.setAccion("R");
			}else if(esRefrescarNew){
				if(id_actividad_temp.equals("RN")){
					mensajeEstadoBALocal.setAccion("RN");
				}else{
					if(id_actividad_temp.equals("RF")){
						mensajeEstadoBALocal.setAccion("RF");
					}
				}
				
			}
			
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);

			int areaPhone= 0;
			int numeroPhone= 0;
			if (phoneNumber.length()>1){
				areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				numeroPhone=Integer.parseInt(phoneNumber.substring(1));
			}
			mensajeEstadoBALocal.setArea(new Integer(areaPhone));
			mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
			//AT-1633 12-09-2008
			//SolicitudConfiguracionBAMQ  configuracionActualBAMQ = new SolicitudConfiguracionBAMQ();
			SolicitudConfiguracionBASigresMQ  configuracionActualBASigresMQ = new SolicitudConfiguracionBASigresMQ();
			configuracionActualBASigresMQ.enviarMensaje(tr035e);
		}
		catch (NumberFormatException e){
			log.warn("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		}
		catch (CreateException e){
			log.warn("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		}
		catch (FinderException e){
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} 
		catch(Exception e){
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}		
	}
	
	
	/** 
	 * envio de Baja BA
	 * 
	 * @author agonz
	 */
	public void enviaNotificacionCambioNumero(Long peticion, String id_actividad, PsVsOcVO psprim) throws ATiempoAppEx {

		
		PeticionLocal peticionLocal = null;
		Long IdCorrelativo = null;
						
		try {
						
			peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(peticion));
			IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			//guardarMensajeEstadoBA(IdCorrelativo, peticionLocal, psprim, id_actividad);
					
			Mensaje_estado_baLocal mensajeEstadoBALocal = null;
			Mensaje_estadoLocal mensajeEspera = null;
			
			mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
			mensajeEstadoBALocal.setMensaje_estado(mensajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);
			  											
			enviarTR041E(peticion, psprim, peticionLocal, IdCorrelativo);
				
		} catch (FinderException e) {
		  log.warn("", e);
		  throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (CreateException e) {
		  log.warn("", e);
		  throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (NamingException e) {
		  log.warn("", e);
		  throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}		
	
	}
	public void enviarTR041E(Long peticion,PsVsOcVO psprim,PeticionLocal peticionLocal,Long IdCorrelativo) throws NamingException, FinderException, ATiempoAppEx {
		Producto_servicio_peticionLocal ps =null;
		PeticionKey peticionKey = null;
		TR041E tr041e =null;
		
		Producto_servicio_peticionLocalHome psHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		ps=(Producto_servicio_peticionLocal) psHome.findByPetiNumeroPsYOpCo(peticion,psprim.getPsId(),psprim.getOpComId()).iterator().next();
		
		
		peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();				
			
		tr041e = new TR041E();
		tr041e.setId(String.valueOf(IdCorrelativo));
		tr041e.setAtiempoRequestNumber(peticionKey.peti_numero.longValue());
		tr041e.setActualOperatorId(getServiceNumber(peticionLocal, null));
		tr041e.setActualTerminalNumber(getServiceNumber(peticionLocal, null));
		Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
		
		//CR-19262 agonz- 10/29/2008
		Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
		Traslado_solobaLocal traslado_solobaLocal=null;
		try{
		   traslado_solobaLocal = traslado_solobaLocalHome.findPetiTraslado(peticion);
		} catch (FinderException e) {
		log.debug("No es un Traslado");
		}
		String phoneNumberAnt = peticionLocal.getIdentificadorOriLinea();
		String phoneNumberAsg="";
		if(traslado_solobaLocal != null){							
			PeticionKey peticionKeyTras = new PeticionKey(traslado_solobaLocal.getPeti_alta());
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocalTras = peticionLocalHome.findByPrimaryKey(peticionKeyTras);
			phoneNumberAsg = String.valueOf(peticionLocalTras.getNum_ide_nu_stb());
		}else{
			Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(peticion);
			phoneNumberAsg = linea_basicaLocal.getTelefono_asg().toString();					 
		}
		tr041e.setNewOperatorId("");
		tr041e.setNewTerminalNumber("");
		if (phoneNumberAsg!=null && !"".equals(phoneNumberAsg.trim())){
			if (phoneNumberAsg.length()>8){ 
				phoneNumberAsg=phoneNumberAsg.substring(0,8);
			}
			tr041e.setNewTerminalNumber(phoneNumberAsg);
			tr041e.setNewOperatorId(phoneNumberAsg);
		}else{
		    throw new ATiempoAppEx("No es posible obtener datos de recursos");
		}			
		
		if (phoneNumberAnt!=null && !"".equals(phoneNumberAnt.trim())){
			if (phoneNumberAnt.length()>8){ 
				phoneNumberAnt=phoneNumberAnt.substring(0,8);
			}
			tr041e.setActualTerminalNumber(phoneNumberAnt);
		}
		//fin CR-19262 agonz- 10/29/2008

						
		CambioNumeroSigresMQ mq = new CambioNumeroSigresMQ();
		mq.enviarMensaje(tr041e);
		log.info("MENSAJE DE BAJA ENVIADO...");				
	}		
	

	
	/**
	 * Modificar Identificador Operadora
	 * @author 808026 10/07/2008
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	
	
	public boolean enviarModificarIdOperadora(String peticion, String  id_actividad ) throws ATiempoAppEx{
			
				boolean esEnviado=true;
				try{
			
						validaHome ();
			
						Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			
			
						PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
						Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
					
						// Proceso para la validacion de los tipos de ps, que estan en el mensaje
						StringBuffer append = new StringBuffer();
						boolean bip = true;
						Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
						Producto_servicioKey productoServicoKey = null;
	
						for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){

							producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
							productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();

								if(bip){
									append.append(productoServicoKey.ps_id);
									bip = false;
								}else{
									append.append(" ,"+ productoServicoKey.ps_id .toString());
								}

						}

						// TODO:  IMPLEMENTAR --> Condicion que valida los tipos de PS

						Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					
						//Inicio CR-12780
						Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
						Traslado_solobaLocal traslado_solobaLocal=null;
						try{
						   traslado_solobaLocal = traslado_solobaLocalHome.findPetiTraslado(new Long(peticion));
						} catch (FinderException e) {
							throw new ATiempoAppEx("No se encuentra la peticion");
						}
						PeticionKey peticionKeyTras = new PeticionKey(traslado_solobaLocal.getPeti_alta());
						PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
						PeticionLocal peticionLocalTras = peticionLocalHome.findByPrimaryKey(peticionKeyTras);
						String phoneNumberAsg = String.valueOf(peticionLocalTras.getNum_ide_nu_stb());
						//Fin CR-12780
			

						Collection colConect2 = peticionLocal.getRecursos_linea_basica () ;
            
					   if (colConect2.size () != 1)
					   {
						   String error = "La peticion " + peticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
						   throw new ATiempoAppEx (error) ;
					   }
				   
						Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
					
						//String phoneNumberAsg = String.valueOf(recursosStb.getTelefono_asg()); TODO CR-12780
//						String phoneNumberAnt = peticionLocal.obtenerCPINAGrupacionOriginal();
						String phoneNumberAnt = peticionLocal.getIdentificadorOriLinea();					
						//Si no hay numeros, envio una excepcion pk no tengo nada que cambiar!!
						if(phoneNumberAsg==null || "".equals(phoneNumberAsg.trim()) || phoneNumberAnt==null || "".equals(phoneNumberAnt.trim()))
						{
							throw new ATiempoAppEx("No hay numeros para hacer el cambio de numero",ATiempoAppEx.EXCEPTION);
						}
					
						//Si son iguales no hago ningun cambio inutil
						if(phoneNumberAnt.equals(phoneNumberAsg)){
							esEnviado=false;
							return esEnviado;
						}
					
						Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
						TR041E tr041e = new TR041E();
						tr041e.setId(String.valueOf(IdCorrelativo));
						PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
						//tr041e.setAtisRequestNumber(peticionKey.peti_numero.longValue());			
			
						// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
						Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
						mensajeEstadoBALocal.setPeticion(peticionLocal);
						Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
						//TODO: Verificar el Conector Correcto
						mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSiete))));
						mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
						mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
						mensajeEstadoBALocal.setCod_actividad_generadora(id_actividad);
	
						int areaPhone= 0;
						int numeroPhone= 0;
						if (phoneNumberAsg.length()>1){
							areaPhone=Integer.parseInt(phoneNumberAsg.substring(0,1));
							numeroPhone=Integer.parseInt(phoneNumberAsg.substring(1));
						}
						mensajeEstadoBALocal.setArea(new Integer(areaPhone));
						mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
	
						//CambiarNumeroBAMQ  cambiarNumeroBAMQ = new CambiarNumeroBAMQ();
						//cambiarNumeroBAMQ.enviarMensaje(tr041e);
						ModificarIdOperadoraSigresMQ modificarIdOperadora = new ModificarIdOperadoraSigresMQ();
						modificarIdOperadora.enviarMensaje(tr041e);
			
		
				} catch (NumberFormatException e) {
					log.warn("NumberFormatException",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

				} catch (CreateException e) {
					log.warn("CreateException",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		
				} catch (FinderException e) {
					log.warn("FinderException",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

				}
				catch(Exception e)
				{
					log.debug("Exception",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
				return esEnviado;

			}
		
		public void recepcionModificarIdOperadora(TR041S tr041s) throws ATiempoAppEx
		{
//			 CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
			try
			{
				bExecution = BackendTraceUtil.initExecution(tr041s, log);
				bExecution.setIdMensajeOp(tr041s.getId());
				bExecution.startOperation();
				
				validaHome ();
	
				Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
	
				Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr041s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal ;
	
				try
				{
					mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
				}
				catch (FinderException fex)
				{
					mensajeEstadoBaLocal = null ;
				}
   
				if (mensajeEstadoBaLocal == null)
				{
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr041s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr041s.getId(),ATiempoAppEx.EXCEPTION);
				}
	
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;
	
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual)
				{
					log.debug("El estado de la respuesta es diferente para ser procesado\n "+ XMLUtilities.marshall(tr041s));
					return;
				}
	
				PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
	
	
				Recursos_baLocal recursos_baLocal;
				Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();
	
				if ( recursosLineaBaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					recursos_baLocal = recursos_baLocalHome.create(idConDos);
				}else{
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}

				
				int codigoAccion = Integer.parseInt(tr041s.getAction());
				
				if(codigoAccion==0 || codigoAccion==3){//no hay error
					recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
					recursos_baLocal.setCod_error(new Integer(String.valueOf(tr041s.getCode())));
					recursos_baLocal.setDesc_error(tr041s.getErrorMessage());
					recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
				}

				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto =  actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				//if (tr041s.isError() == true || op.isError()){
				if(codigoAccion==1 || codigoAccion==2){//hay error

					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));

					if(codigoAccion == 1){
				    log.debug("Error en Informar Resultado de Instalacion por Broker.  Se debe derivar a PGI ");
				    actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				    //actDto.setObservacion("Error en Informar Resultado Instalacion. Se deriva a PGI. Descripción: "	+ code + "." + errorCodeMsg	+ ".");
				    actDto.setObservacion("Error, se deriva a PGI / " + " Respuesta:  " +  tr041s.getErrorMessage());
				  }else{
				  	log.debug("Error en Informar Resultado de Instalacion por Broker.  Se debe derivar a PGC ");
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
					actDto.setObservacion("Error, se deriva a PGC / " + " Respuesta:  " +  tr041s.getErrorMessage());
				  }  
					
					String codError = String.valueOf(tr041s.getCode());
					String nombreClase=TR041S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR014S.class.getName());
					 if(errorLegado != null){
						 peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						 insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else {
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
//					fin agregado
					//insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					actividadEJB.terminar(actDto);
					return;
				}
				if(codigoAccion == 3){
				    actDto.setObservacion("Novedad de avance /"+"Respuesta:  " + tr041s.getCode() + " -  " +  tr041s.getErrorMessage());
				  	actividadEJB.grabarSinTerminar(actDto);	
				  	return;
				}
				
				mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
				actividadEJB.terminar(actDto);
			}
			catch (NumberFormatException e)
			{		bExecution.setErrorOp(true);		
					log.warn("NumberFormatException",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

			}
			catch (CreateException e)
			{		bExecution.setErrorOp(true);	
					log.warn("CreateException",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

			}
			catch (FinderException e)
			{		bExecution.setErrorOp(true);	
					log.warn("FinderException",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);	
				log.warn("TnProcesoExcepcion",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}
			catch(Exception e)
			{	bExecution.setErrorOp(true);	
				log.debug("Exception",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
				// CR15338 - @Trace - FIN 
			}
		}
	
	/**
	 * AT-1480 - Envio de la TR038E para Traslado
	 * @author 808026
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	
	public void solicitudConfiguracionSigresTraslado(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, ActividadEJBDTO act) throws ATiempoAppEx {
	
		try {
			validaHome ();
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			log.debug("Sigres - Creamos los mensajes de estado");
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));

			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			//TODO: CR4860 Insertar en tabla nueva para Sigres???
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);
			//Seteo que aun no he recibido ninguna respuesta de Sigres de mas de una respuesta que devuelve Sigres en este envio
			mensajeEstadoBALocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_SIN_MENSAJE));

			int areaPhone= 0;
			int numeroPhone= 0;
			try	{
				areaPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(0,1));
				numeroPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(1));

				mensajeEstadoBALocal.setArea(new Integer(areaPhone));
				mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
			} catch(Exception e) {
				log.info("solicitudConfiguracionSigres() Num ide nu stb no numerico:"+peticionLocal.getNum_ide_nu_stb());
			}

			enviarTR038ETraslado(peticion,psprim,reversa,peticionLocal,IdCorrelativo, act);

			} catch (FinderException e) {
				log.warn("Error al enviar la Configuracion Internet Sigres para Traslado ",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (CreateException e) {
				log.warn("Error al enviar la Configuracion Internet Sigres para Traslado ",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (NamingException e) {
				log.warn("Error al enviar la Configuracion Internet Sigres para Traslado ",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
			} catch (ATiempoAppEx e) {
			    log.warn("Error al enviar la Configuracion Internet Sigres para Traslado ",e);
            } catch (TnProcesoExcepcion e) {
                log.warn("Error al enviar la Configuracion Internet Sigres para Traslado ",e);
            }
		
		}
	// agonz - Corrección defecto 24146 - 03 marzo 2009
	private String obtenerServicePhoneTraslado(String peticion)throws ATiempoAppEx, TnProcesoExcepcion, NamingException {
		String numeroServicio=null; 
		RecursosBADelegate recursosBADelegate=new RecursosBADelegate();	
		boolean esTraslado = recursosBADelegate.esTrasladoBa(new Long(peticion));
			
		if(esTraslado){
			try{
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				Traslado_solobaLocal traslado_solobaLocal=null;
				traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(new Long(peticion));
				numeroServicio=traslado_solobaLocal.getLinea_anterior();
				log.debug("Es traslado solo BA");
			} catch (FinderException e) {
				   log.debug("Es traslado LB y BA");
				   PeticionLocal petiLocal;
		           try {
		               if(peticionLocalHome==null)
		                   peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		                
		               petiLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
		               numeroServicio=petiLocal.getIdentificadorOriLinea();
		            } catch (FinderException e1) {
		                log.debug("Error en Obtener service-phone para traslado LB y BA",e);
		    			throw new TnProcesoExcepcion("Error en Obtener service-phone para traslado LB y BA");
		            }
			} catch (NamingException e) {
			    	log.warn("Error en obtenerServicePhoneTraslado ",e);
                    e.printStackTrace();
            }
		}
		return numeroServicio;
	}
	
	public void enviarTR038ETraslado(String peticion,PsVsOcVO psprim,boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, ActividadEJBDTO act) throws FinderException, ATiempoAppEx, TnProcesoExcepcion, NamingException {
			TR038E tr038e = new TR038E();
			tr038e.setId(String.valueOf(IdCorrelativo));
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			tr038e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());

			
			// Datos del cliente
			String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
			if (clientDocument!=null && clientDocument.trim()!=""){
				if (clientDocument.length()>16){
					clientDocument=clientDocument.substring(0,16);
				}
			} else {
				log.warn("solicitudConfiguracionSigresTraslado(): Atencion -  No existe valor para el Documento de Cliente peticion=" + peticion);
				//clientDocument="-";
			}
			TypeValueList valueList = new  TypeValueList();
			addTypeValue(valueList, "client-document-number", peticionLocal.getNum_doc_cli_cd());
			
			addTypeValue(valueList, "client-document-type", peticionLocal.getTip_doc_cd());
			addTypeValue(valueList, "client-name", peticionLocal.getNom_ds());
			addTypeValue(valueList, "client-first-lastname", peticionLocal.getPri_ape_ds());
			addTypeValue(valueList, "client-second-lastname", peticionLocal.getSeg_ape_ds());
			LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			addTypeValue(valueList, "code-name", localidadKey.cod_loc);
			tr038e.setPssbaDataList(valueList);
			//tr038e.setServiceType(psprim.getPsId().longValue());
			String serviceType = calcularPS(psprim,peticion,peticionLocal,IdCorrelativo, act);
			if(serviceType != null)
				tr038e.setServiceType(Long.parseLong(serviceType)); 
			//TODO: CR4860 - ¿RangeZoneList es lo que en tr013 era Dslams?
			Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
			
			Collection zonesArray = new ArrayList();
			if (peticionLocal.getRecursos_linea_basica().size()>0){
				if ( peticionLocal.getRecursos_linea_basica().size() > 1) {
					log.warn("solicituConfiguracionSigresBA() tiene mas de una linea basica");
				}
				for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ) {
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
				}
				//Defecto 19914 el arreglo RangeZoneList no se estaba llenando
				if(recursos_linea_basicaLocal.getZonas_atendimiento().size() > 0) {
					for (Iterator iter =recursos_linea_basicaLocal.getZonas_atendimiento().iterator();iter.hasNext();) {
						Zonas_atendimientoLocal zonasLocal = (Zonas_atendimientoLocal)iter.next();
						zonesArray.add(zonasLocal.getIp());
					}
				}
			} else {
				log.warn("solicitudConfiguracionSigresBA() Advertencia : No existe valor para el Telefono Anterior porque no hay informacion de recursos de linea");
			}
			tr038e.setRangeZoneList(zonesArray);
					
//			   Start father-email ........
			String fatherEmail = null;
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			//Voy a asumir que aca viene uno solo.
			Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
			if(!psprim.isSacarFatherEmAlta()) {
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsId(),psprim.getOpComId()).iterator().next();
			} else {
				producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprim.getPsIdAlta(),psprim.getOpCoAlta()).iterator().next();
			}
			Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
			Long psId = subPet.getCod_pro_ser_cd();
			Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
			Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
			try {
				if(psprim.isSacarFatherEmAlta() && psId.longValue()==psprim.getPsIdAlta().longValue()) {
					fatherEmail = extraerFatherEmail(subPet);
				} else if(psId.longValue()==psprim.getPsId().longValue())	{
							//si la peticion es de ic y tiene id pc se envia el id pc como father email ..sino se saca la caracteristica .. si la caracteristica esta vacia se pone "".
							if(tipoPc!=null && tipoPc.intValue()==ComunInterfaces.tipoIC) {
								if( agrupacion_atisLocal.getNum_ide_nu()!=null && !agrupacion_atisLocal.getNum_ide_nu().equals("") ) {
									fatherEmail = agrupacion_atisLocal.getNum_ide_nu();
								} else	{
									fatherEmail = extraerFatherEmail(subPet);
								}
							} else {
								//si no es IC (cualquier ba) siempre se saca la caracteristica sino va vacio.
								fatherEmail = extraerFatherEmail(subPet);
							}
						}else {
							fatherEmail = producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu();
						}
						if(fatherEmail == null) {
							fatherEmail = subPet.getFk_agru_sub().getNum_ide_nu();
						}
							log.info("Setie el valor de father email en:"+ fatherEmail);
			}
			catch(Exception e) {
				//TODO: Mejorar esto: Si no viene email, debe ir a PGC la actividad.
				log.warn("No viene el EMAIL",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}
			addTypeValue(valueList, "father-email", fatherEmail);
//			End father-email
			
			String servicePhone = obtenerServicePhoneTraslado(peticion);
			tr038e.setServiceNumber(servicePhone);

			tr038e.setCustomerSegment("");//agonz AT-1579 18-08-2008"
			tr038e.setTerminalNumber("");
//			Se envia client-type consultar con Fernando 21-08-2008 
			String tcd = peticionLocal.getTip_cli_cd();
			if (tcd != null && tcd.trim().length()!=0 ) {
				addTypeValue(valueList, "client-type", String.valueOf(tcd.charAt(0)));
			}
				
		
			// Averiguo si la peticion de Traslado es solo BA
			log.debug("Se verifica si la peticion de Traslado es solo BA");
			Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
			boolean esTrasladoAltaSoloBA=false;
			Traslado_solobaLocal traslado_solobaLocal=null;
			try{
				traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(new Long(peticion));
				esTrasladoAltaSoloBA=true;
			} catch (FinderException e) {
				log.debug("No es traslado alta BA");
			}
				
			//Defecto 19914 -agonz 10/11/2008
			//tr038e.setTerminalNumber(servicePhoneOri);
			//String servicePhoneNew = String.valueOf(recursos_linea_basicaLocal.getTelefono_asg());	
			//if(servicePhoneNew!=null && !servicePhoneNew.equalsIgnoreCase("")&& !servicePhoneOri.equalsIgnoreCase(servicePhoneNew))
				//tr038e.setTerminalNumber(servicePhoneNew);
			
			//Pregunto si es traslado solo BA
							
			//Pregunto si los numeros de documentos de cliente de las peticiones (alta y baja) de traslado son diferentes 
			log.debug("Pregunto si el cliente es el mismo.");
			//RQ-1778 - No aplica para peticiones de traslado mantener el numero de ip fijas, por eso se envia en 0
//			tr038e.setIpAmount("0");
			
			//Se modifica para que en traslados calcule las IP`s
			String mascara = obtenerMascaraActualCliente(peticion);
			String cantidadIPFijas = calcularCantidadIpFijas(mascara);
			tr038e.setIpAmount(cantidadIPFijas);
			//Obtener operacion comercial
			log.debug("Operacion comercial IP FIJA Vuelo= "+ psprim.getOpComId().toString());
			tr038e.setComercialOperation(psprim.getOpComId().toString());			
		
			log.debug("solicitudConfiguracionSigresTraslado() Enviando mensaje TR038E de Mensaje");
			ConfiguracionServicioSigresCambioPlanMQ configuracionServicioSigresCambioPlanMQ = new ConfiguracionServicioSigresCambioPlanMQ();
			if(serviceType != null){
				configuracionServicioSigresCambioPlanMQ.enviarMensaje(tr038e);
				if(act != null)
					act.setObservacion("Se envia el mensaje de Configuracion Internet.");
			}
//			configuracionServicioSigresCambioPlanMQ.enviarMensaje(tr038e);
		}

	
		/*
		 * Retorna true en caso de que el listadoPS contenga una operacion comercial de baja
		 * CR-27922 - agonz - 28/07/2009
		 */
		public boolean esBajaBA(ArrayList listadoPS)throws ATiempoAppEx{
		   try{
		    log.debug("inicio del metodo esBajaBA");
			String opTipo = null;
            
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++) { 
            	ProductoDTO psDto = (ProductoDTO) listadoPS.get(i); 
            	opTipo = psDto.getTipoOperacionComercial(); 
            	if(opTipo != null && opTipo.equals("B")) {
            	    log.debug("esBajaBA = true ... opTipo = "+opTipo);
            		return true; 
}
            }
			log.debug("esBajaBA = false opTipo = "+opTipo);
            return false;
			}
			catch(Exception e) {
				log.warn("Error al intentar saber si es baja de BA",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}
		}
		

		//Correccion defecto Cambio de Plan Wifi 06/10/2009
		public boolean esWifi(Long idPeticion)throws ATiempoAppEx{
			   try{
				   	boolean resultado = false;
			       	PeticionKey key = new PeticionKey (idPeticion) ;
			       	PeticionesInterfaces peticionesInterfaces = new PeticionesDelegate();
					PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			       	
					for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
					{
						Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
						Operacion_comercialLocal opco=pspet.getOperacion_comercial();
						int idFam=pspet.getFamiliaKey().faps_id.intValue();
						log.debug("Peticion :"+idPeticion+", idFam = "+idFam);
//						REQ BA NAKED adicion familia PC naked
						if(idFam==familiaBandaAncha || idFam==familiaBandaAncha){
						    	
						    EquipoDelegate equipoDelegate = new EquipoDelegate();
						    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
						    if(pdtiCollection.size()== 0){
						        try {
								    //Valido si es Wifi
								    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());
								   
								    if (ps_Tipo_ModemLocal2!= null){
								        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("A") ||   opco.getOpco_tipo().equals("ACP") )&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
								            resultado = true;								    
									    }
									 }
								    			    	
								}catch (FinderException e){
								}
								 	
						    }
						}
					}
					log.debug("Es Wifi = "+resultado);
					return resultado;

				}
				catch(Exception e) {
					log.warn("Error al intentar saber si es Wifi",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				}
			}
		
//		TODO JUAN
		public boolean esWifiBAja(Long idPeticion)throws ATiempoAppEx{
			   try{
				   	boolean resultado = false;
			       	PeticionKey key = new PeticionKey (idPeticion) ;
			       	PeticionesInterfaces peticionesInterfaces = new PeticionesDelegate();
					PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			       	if(peticion.getProducto_servicio_peticion().size()==1){
						for(Iterator iterator=peticion.getProducto_servicio_peticion().iterator();iterator.hasNext();)
						{
							Producto_servicio_peticionLocal pspet=(Producto_servicio_peticionLocal) iterator.next();
							Operacion_comercialLocal opco=pspet.getOperacion_comercial();
							int idFam=pspet.getFamiliaKey().faps_id.intValue();
							log.debug("Peticion :"+idPeticion+", idFam = "+idFam);
//							REQ BA NAKED adicion familia PC naked
							if(idFam==familiaBandaAncha || idFam==familiaBandaAnchaNaked){

							    EquipoDelegate equipoDelegate = new EquipoDelegate();
							    Collection pdtiCollection = equipoDelegate.esTipoPDTI(pspet.getPsId());
							    if(pdtiCollection.size()== 0){
							        try {
									    //Valido si es Wifi
									    Ps_Tipo_ModemLocal ps_Tipo_ModemLocal2 = ps_Tipo_ModemLocalHome.findByNroPs(pspet.getPsId());

									    if (ps_Tipo_ModemLocal2!= null){
									        if(opco.getOpco_tipo()!=null && ( opco.getOpco_tipo().equals("B"))&& peticionesInterfaces.estaOKProductoServicioPeticion(pspet)){
									            resultado = true;								    
										    }
										 }

									}catch (FinderException e){
									}

							    }
							}
						}
			       	}
					log.debug("Es Baja solo Wifi = "+resultado);
					return resultado;

				}
				catch(Exception e) {
					log.warn("Error al intentar saber si es Wifi",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				}
			}
//	CR14657 - Granite - agonz- 16-10-2008
	public void enviaActualizaInventarioBAGranite(String peticion, String idActividad, ActividadEJBDTO act) throws ATiempoAppEx {
		try{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Mensaje_estadoLocalHome mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));

			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();

			// Proceso para la validacion de los tipos de ps, que estan en el mensaje
			StringBuffer append = new StringBuffer();
			boolean bip = true;
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			Producto_servicioKey productoServicoKey = null;
//			REQ BA NAKED adicion familia PC naked
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				productoServicoKey = (Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				if  (((((Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey()).faps_id.intValue() ==   ComunInterfaces.familiaPcBA)
						||(((Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey()).faps_id.intValue() ==   ComunInterfaces.familiaPcPsBANaked))
						&&(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)
								|| producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)
								|| producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja))) {
					
					break;
				}
			}

			Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
			
		
			
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			
			log.debug("Sacamos la PS para actualizar Intentario BA");
			
//			Collection modems = peticionLocal.getModem();
//			
//			if (modems.size() ==0){
//				
//				log.debug("INFO: No existen Modem Asociados a la Peticion");
//				
//				return;
//			}
		
			
		//	for(Iterator iter = modems.iterator();iter.hasNext();){

		//		ModemLocal modemLocal = (ModemLocal) iter.next();

				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
//				Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				 Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
				 mensajeEstadoBALocal.setPeticion(peticionLocal);
				 Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				 mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				 //TODO: Verificar el Conector Correcto
				 mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				 mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				 mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
				 mensajeEstadoBALocal.setCod_actividad_generadora(idActividad);

				 
//				se validan los ps asociados a la peticiòn
				String PScalculado =  null;
				PeticionesInterfaces pI = new PeticionesDelegate();
				Collection psOK=pI.listaDePsDelaActividadEstadoOKFinal(act.getNumeroPeticion(),act.getIdActividadFlujo());
				for(Iterator iterador = psOK.iterator();iterador.hasNext();){
					PsVsOcVO psTemp= (PsVsOcVO) iterador.next();
					log.debug("Se compara "+psTemp.getPsId().intValue()+ " con "+ productoServicoKey.ps_id.intValue());
					if(psTemp.getPsId().intValue() == productoServicoKey.ps_id.intValue())
						PScalculado = calcularPS(psTemp, peticionKey.peti_numero.toString(),peticionLocal,IdCorrelativo,act);
				}
				TR515E tr515e = new TR515E();
				tr515e.setId(String.valueOf(IdCorrelativo));
				tr515e.setPsCode(PScalculado);
				//tr515e.setPsCode(productoServicoKey.ps_id.toString());
				tr515e.setActionType(operacion_comercialKey.opco_id.intValue());
				
				tr515e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());
				
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				tr515e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());

				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && phoneNumber.trim()!= ""){
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				} else
				{
					phoneNumber="0";
				}
				
				
				tr515e.setPhoneNumber(new Integer(phoneNumber).intValue());
				LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey(); 
				tr515e.setCity(Integer.parseInt(localidadKey.cod_loc));
				tr515e.setSubCity("");
				tr515e.setRequestDate(new java.util.Date ());
				
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				Traslado_solobaLocal traslado_solobaLocal=null;
				try{
				   traslado_solobaLocal = traslado_solobaLocalHome.findPetiTraslado(new Long(peticion));
				} catch (FinderException e) {
				log.debug("No es un Traslado");
				}
				String phoneNumberAsg="";
				if(traslado_solobaLocal != null){							
					PeticionKey peticionKeyTras = new PeticionKey(traslado_solobaLocal.getPeti_alta());
				//PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					PeticionLocal peticionLocalTras = peticionLocalHome.findByPrimaryKey(peticionKeyTras);
					phoneNumberAsg = String.valueOf(peticionLocalTras.getNum_ide_nu_stb());
					tr515e.setNewPhoneNumber(new Integer(phoneNumberAsg).intValue());
				}else{
					tr515e.setNewPhoneNumber(0);
				}
				//At-1924 esta pasando lo mismo que con la tr512e
				//tr515e.setAtisRequestNumber(peticionKey.peti_numero.longValue());

			 int areaPhone= 0;
			 int numeroPhone= 0;
			 if (phoneNumber.length()>1){
				 areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				 numeroPhone=Integer.parseInt(phoneNumber.substring(1));
			 }
			 mensajeEstadoBALocal.setArea(new Integer(areaPhone));
			 mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));

			 ActualizaInventarioBAPEMQ actualizaInventarioBAPEMQ  = new ActualizaInventarioBAPEMQ();
			 if(PScalculado != null){
			 	actualizaInventarioBAPEMQ.enviarMensaje(tr515e);
			 	if(act != null)
			 		act.setObservacion("Se envia el mensaje de Configuracion Internet.");
			 }
			 
			
			
			
		}catch (NumberFormatException e) {
			log.warn("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			log.warn("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
					
		} catch (FinderException e) {
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			
		} 
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}			
	}		

//	CR14657 - Granite - agonz- 17-10-2008
	public void recepcionActualizaInventarioBAGranite(TR515S tr515s) throws ATiempoAppEx{
		// CR15338 - @Trace -  Inicio 
		BackendExecution bExecution = null;
		
		try
		{		
			bExecution = BackendTraceUtil.initExecution(tr515s, log);
			bExecution.setNumPetAtiempo(new Long(tr515s.getAtiempoRequestNumber()));
			bExecution.setIdMensajeOp(tr515s.getId());
			bExecution.startOperation();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Mensaje_estadoLocalHome mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
	
			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

			Mensaje_estado_baKey key = new Mensaje_estado_baKey(Long.valueOf(tr515s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal ;

		   try
		   {
				mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(key);
		   }
		   catch (FinderException fex)
		   {
				mensajeEstadoBaLocal = null ;
		   }

			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (mensajeEstadoBaLocal == null) {
				log.debug(
					"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr515s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr515s.getId(),ATiempoAppEx.EXCEPTION);
			}



			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoBaLocal.getMensaje_estado().getPrimaryKey() ;

			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
				log.debug(
					"Es estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr515s));
				return;
			}

			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();

			log.debug("Mensaje recibido.");
			if (this.getEstadoMultipleMensajes(mensajeEstadoBaLocal, new Integer(estadoEspera))){
				log.debug("Ultimo mensaje -> se cierra la acitividad");	
				// todo ok, se graba la respuesta
			Recursos_baLocal recursos_baLocal;
				Collection recursosLineaBaBasica = mensajeEstadoBaLocal.getPeticion().getRecursos_ba();

				if ( recursosLineaBaBasica.size() == 0){
					Recursos_baLocalHome recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					recursos_baLocal = recursos_baLocalHome.create(idConDos);
				}else{
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}			

			
			if ( tr515s.getErrorCode() == 0){
//				Si entra por aqui es por que hay error	
				recursos_baLocal.setPeticion(mensajeEstadoBaLocal.getPeticion());
				recursos_baLocal.setCod_error(new Integer(String.valueOf(tr515s.getErrorCode())));
				recursos_baLocal.setDesc_error(tr515s.getErrorMessage());
				recursos_baLocal.setMensaje_estado_ba(mensajeEstadoBaLocal);
	
	 
			}
	
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
	
				
				if (tr515s.getErrorCode() != 0) {
					//Si entra por aqui es por que hay error
					recursos_baLocal.setCod_error(new Integer(String.valueOf(tr515s.getErrorCode())));
					recursos_baLocal.setDesc_error(tr515s.getErrorMessage());
					
					mensajeEstadoBaLocal.setMensaje_estado(mensajeErrorLocal);
					mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
					
					//agregado por adecarlini
					String codError = String.valueOf(tr515s.getErrorCode());
					String nombreClase=TR515S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr515s.getErrorMessage());
							actividadEJB.terminar(actDto);
							return;
						}
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else {
						insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					// fin agregado
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en la Actualizacion de Inventario.Se deriva a "+bandeja+".Descripcion:"+tr515s.getErrorCode()+" . "+tr515s.getErrorMessage()+"." + tr515s.getErrorMessage());
					//modificcado adecarlini
					//insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					//fin
					actividadEJB.terminar(actDto);
	 
					return;

				}

				 ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				 IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				 actividadEJB.terminar(actDto);
			}
			
			mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoBaLocal.setMensaje_estado(mesajeOkLocal);
			

		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);
			log.warn("NumberFormatException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.warn("CreateException",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);

		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.warn("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);

		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.warn("TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} 
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}	
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - FIN 
	}
	}
	
	public void solicitudConfiguracionSigresCambioPlanEnVuelo(String peticion, String cod_actividad, PsVsOcVO psprim,boolean reversa, Integer actividadFlujo, PsVsOcVO psprimOld) throws ATiempoAppEx {
		try {
			validaHome ();
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			log.debug("Sigres - Creamos los mensajes de estado");
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			//TODO: CR4860 Insertar en tabla nueva para Sigres???
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprimOld.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(cod_actividad);
			//Seteo que aun no he recibido ninguna respuesta de Sigres de mas de una respuesta que devuelve Sigres en este envio
			mensajeEstadoBALocal.setCod_sub_estado(new Integer(ComunInterfaces.SUB_ESTADO_ESPERA_SIN_MENSAJE));
			
			int areaPhone= 0;
			int numeroPhone= 0;
			try	{
				areaPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(0,1));
				numeroPhone = Integer.parseInt(peticionLocal.getNum_ide_nu_stb().substring(1));
				
				mensajeEstadoBALocal.setArea(new Integer(areaPhone));
				mensajeEstadoBALocal.setTelefono(new Long(numeroPhone));
			} catch(Exception e) {
				log.info("solicitudConfiguracionSigres() Num ide nu stb no numerico:"+peticionLocal.getNum_ide_nu_stb());
			}
			
			enviarTR038EEnVuelo(peticion,psprim,reversa,peticionLocal,IdCorrelativo, psprimOld);
			
		} catch (FinderException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (CreateException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (NamingException e) {
			log.warn("Error al enviar la Configuracion Internet Sigres",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
	}
	
	public void enviarTR038EEnVuelo(String peticion,PsVsOcVO psprim,boolean reversa,PeticionLocal peticionLocal,Long IdCorrelativo, PsVsOcVO psprimOld) throws NamingException, FinderException, ATiempoAppEx {
		TR038E tr038e = new TR038E();
		tr038e.setId(String.valueOf(IdCorrelativo));
		PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
		tr038e.setAtiempoRequestNumber(peticionKey.peti_numero.intValue());
		Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
		
		
		// Datos del cliente
		String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
		if (clientDocument!=null && clientDocument.trim()!=""){
			if (clientDocument.length()>16){
				clientDocument=clientDocument.substring(0,16);
			}
		} else {
			log.warn("solicitudConfiguracionSigresCambioPlan(): Atencion -  No existe valor para el Documento de Cliente peticion=" + peticion);
			//clientDocument="-";
		}
		TypeValueList valueList = new  TypeValueList();
		addTypeValue(valueList, "client-document-number", peticionLocal.getNum_doc_cli_cd());

		//Se envia client-type consultar con Fernando 21-08-2008 
		String tc = peticionLocal.getTip_cli_cd();
		if (tc != null && tc.trim().length()!=0 ) {
			addTypeValue(valueList, "client-type", String.valueOf(tc.charAt(0)));
		}
		//addTypeValue(valueList, "client-type", "");//AT-1480 Envio de la tr038 para cambio de plan y traslado
		addTypeValue(valueList, "client-document-type", peticionLocal.getTip_doc_cd());
		addTypeValue(valueList, "client-name", peticionLocal.getNom_ds());
		addTypeValue(valueList, "client-first-lastname", peticionLocal.getPri_ape_ds());
		addTypeValue(valueList, "client-second-lastname", peticionLocal.getSeg_ape_ds());
		LocalidadKey localidadKey=(LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
		//Antes city-name
		addTypeValue(valueList, "code-name", localidadKey.cod_loc);
		tr038e.setPssbaDataList(valueList);
		tr038e.setCustomerSegment("");//agonz AT-1579 18-08-2008
		
		Collection novedadEnVuelo  = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(peticionKey.peti_numero, ComunInterfaces.BA_EN_VUELO);
		if (!novedadEnVuelo.isEmpty()){
			if (!novedadEnVuelo.isEmpty()){
				Iterator iterPss = novedadEnVuelo.iterator();
					
				while (iterPss.hasNext ()){
					Producto_servicio_en_vueloLocal psevDTO = (Producto_servicio_en_vueloLocal) iterPss.next();
					tr038e.setServiceType(psevDTO.getPs_id().longValue());
				}
			}
		}else{
			tr038e.setServiceType(psprim.getPsId().longValue());//AT-1480 Envio de la tr038 para cambio de plan y traslado
		}
		//RQ-1778 - No aplica para peticiones en vuelo mantener el numero de ip fijas, por eso se envia en 0
		tr038e.setIpAmount("0");
		//Obtener operacion comercial
		log.debug("Operacion comercial IP FIJA Vuelo= "+ psprim.getOpComId().toString());
		tr038e.setComercialOperation(psprim.getOpComId().toString());		
		
		
		
//		***************** FAVG - Req 25189 - Sep 29/2009
		//
		String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
		if(psIpFija != null && psIpFija.length() > 0){
			String[] tokens = null;
			tokens = psIpFija.split(",");
			for (int i = 0 ; i < tokens.length ; i++) {
				if(tokens[i].equals(String.valueOf(psprim.getPsId()))){
					tr038e.setIpServiceType(tokens[i]);
					break;
				}
			}
			
		}
		else{
			log.debug("No existe la variable PS_IPFIJA en el archivo de propiedades configVpiStbBa.properties");
		}
		//***************** Fin - Req 25189 - Sep 29/2009
		
		//TODO: CR4860 - ¿RangeZoneList es lo que en tr013 era Dslams?
		Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
		
		tr038e.setRangeZoneList(null);//AT-1480 Envio de la tr038 para cambio de plan y traslado
		// Ingresa en nulo a menos que encuentre una similar entre LB y BA
		for (Iterator iter = peticionLocal.getRecursos_ba().iterator(); iter.hasNext();) {
			Recursos_baLocal rba =(Recursos_baLocal)iter.next();
			for (Iterator iter2 = peticionLocal.getRecursos_linea_basica().iterator(); iter2.hasNext();) {
			
				Recursos_linea_basicaLocal lb = (Recursos_linea_basicaLocal) iter2.next();
				log.debug("cantidad de zonas en rlb: "+lb.getZonas_atendimiento().size());
				
				if(lb.getZonas_atendimiento() != null){
					for (Iterator iter3 = lb.getZonas_atendimiento().iterator(); iter3.hasNext();) {
						Zonas_atendimientoLocal za = (Zonas_atendimientoLocal) iter3.next();
						log.debug("ZA mire: " +za.getIp());
						log.debug("RBA mire: " +rba.getCod_zona_atend());
						if(!za.getIp().equalsIgnoreCase(rba.getCod_zona_atend())){
							// Le envia la coleccion de String que necesita la TR con el primero que encuentre igual
							Collection zarec = new ArrayList();
							zarec.add(new String(za.getIp()));
							tr038e.setRangeZoneList(zarec);//Para Zonas de cobertura cuando es traslado
							break;
						}
					}			
				}				
			}
		}
		
		String fatherEmail = null;
		Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		//Voy a asumir que aca viene uno solo.
		Producto_servicio_peticionLocal producto_servicio_peticionLocal =null;
		
		
		if(!psprimOld.isSacarFatherEmAlta()) {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprimOld.getPsId(),psprimOld.getOpComId()).iterator().next();
		} else {
			producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) producto_servicio_peticionLocalHome.findByPetiNumeroPsYOpCo(new Long(peticion),psprimOld.getPsIdAlta(),psprimOld.getOpCoAlta()).iterator().next();
		}
		Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
		Long psId = subPet.getCod_pro_ser_cd();
		Agrupacion_atisLocal agrupacion_atisLocal=subPet.getFk_agru_sub();
		Long tipoPc=agrupacion_atisLocal.getTip_pro_cmr_cd();
		
		fatherEmail = extraerFatherEmail(subPet);
		addTypeValue(valueList, "father-email", fatherEmail);
		
		if (peticionLocal.getRecursos_linea_basica().size()>0){
			if ( peticionLocal.getRecursos_linea_basica().size() > 1)
				log.warn("tiene mas de una linea basica");
			
			for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ){	
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
			}
		}

		
		String servicePhone = obtenerServicePhone(peticion, psprimOld, reversa, recursos_linea_basicaLocal, agrupacion_atisLocal, tipoPc);
		tr038e.setServiceNumber(servicePhone);
		tr038e.setTerminalNumber("");//agonz AT-1579 18-08-2008"

		tr038e.setPreviousServiceType(retornarCambioPlanBaBaja(peticionKey.peti_numero).longValue());//CR-23809 12 marzo 2009
		log.debug("solicitudConfiguracionSigresCambioPlan() Enviando mensaje TR038E de Mensaje");
		ConfiguracionServicioSigresCambioPlanMQ configuracionServicioSigresCambioPlanMQ = new ConfiguracionServicioSigresCambioPlanMQ();
		configuracionServicioSigresCambioPlanMQ.enviarMensaje(tr038e);
	}

	   public void removeProductoPeticionEnVuelo(Long nroPeticion, String tipoPeticion) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection equiposInternet = new ArrayList();
						
		try {
			String query = "DELETE FROM VPISTBBA.PRODUCTO_SERVICIO_EN_VUELO WHERE PETI_NUMERO = ? AND TIPO_PETICION= ?";
			conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);	
			ps = conn.prepareStatement(query);			
			ps.setLong(1, nroPeticion.longValue());		
			ps.setString(2, tipoPeticion);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error("Error en removeProductoPeticionEnVuelo: " + e);
			throw new ATiempoAppEx(e.getMessage());
		} finally {
			try{
				if (rs != null){
					rs.close();
				}
				if (ps != null){
					ps.close();
				}
				if (conn != null){
					conn.close();
				}
			}catch(SQLException e){
				log.error("Error en removeProductoPeticionEnVuelo: " + e);
				throw new ATiempoAppEx(e.getMessage());	
			}
		}
    }

		public Producto_servicio_en_vueloLocal almacenaProductoPeticionEnVuelo(Integer numeroPeticion, Integer psNuevo, String tipoPeticion, String PsViejo) throws ATiempoAppEx {
			// TODO Auto-generated method stub
			try{
				Integer IdCorrelativo = new Integer(dbSeq.seqNextValInteger("CORRELATIVO_MENSAJE"));
				Long numPeticion = new Long(numeroPeticion.toString());
				Producto_servicio_en_vueloLocalHome productoEnVueloHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			    Producto_servicio_en_vueloLocal productoEnVueloLocal = productoEnVueloHome.create(numPeticion,new Long(IdCorrelativo.longValue()));
			    productoEnVueloLocal.setPs_id(new Long(psNuevo.longValue()));
			    productoEnVueloLocal.setPspe_cantidad(Integer.getInteger("1"));
			    productoEnVueloLocal.setOpco_id(new Integer(1));
			    productoEnVueloLocal.setTipo_peticion(tipoPeticion);
			    productoEnVueloLocal.setPs_id_ant(PsViejo);
			    	    
			    return productoEnVueloLocal;
			}catch(CreateException e){
				log.debug("Error al crear un registro en la tabla ",e);
			}catch (NamingException e) {
				log.debug("Error en Actividad Obtener Cuenta Correo Sigres BA Bean ",e);
			}
			
			return null;
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#solicitarPrimeraFacturaInternetEquipado(java.lang.Long)
		 */
		public Long solicitarPrimeraFacturaInternetEquipado(Long numeroPeticion, Collection equiposList, String codActividad, String tipoOperacion,Integer actiID) throws ATiempoAppEx {
			log.debug("Entro a Solicitar la primera factura de la peticion:"+numeroPeticion);
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			try{
				PeticionKey key = new PeticionKey (numeroPeticion) ;
	            PeticionLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
				long peticionAtis = ((Peticion_atisKey) peticion.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd.longValue () ;
				
				TR044E tr044e = new TR044E();
				tr044e.setId (idCorrelativoMensaje.toString ());
				
				TR044EClient tr044Cliente = new TR044EClient();
				tr044Cliente.setApellido(peticion.getPri_ape_ds() + " "+peticion.getSeg_ape_ds());
				tr044Cliente.setNombre(peticion.getNom_ds());
				
				LocalidadKey localidadKey=(LocalidadKey) peticion.getFk_01_localidad().getPrimaryKey();
				tr044Cliente.setCiudad(localidadKey.cod_loc);
				tr044Cliente.setCodClienteAtis(" ");
				tr044Cliente.setCodCuentaAtis(" ");
				
				DepartamentoKey departamentoKey = (DepartamentoKey) peticion.getFk_02_departamento().getPrimaryKey();
				tr044Cliente.setDepartamento(departamentoKey.cod_dpt);
				
				StringBuffer direccion = new StringBuffer();
				
				if(!peticion.getNom_cal_ds().equals("") && peticion.getNom_cal_ds() != null){
					direccion.append(peticion.getNom_cal_ds()+ " ");
				}
				if(!peticion.getNum_cal_nu().equals("") && peticion.getNum_cal_nu() != null){
					direccion.append(peticion.getNum_cal_nu()+" ");
				}
				if(!peticion.getDsc_cmp_pri_ds().equals("") && peticion.getDsc_cmp_pri_ds() != null){
					direccion.append(peticion.getDsc_cmp_pri_ds()+" ");
				}
				if(!peticion.getDsc_cmp_seg_ds().equals("") && peticion.getDsc_cmp_seg_ds() != null){
					direccion.append(peticion.getDsc_cmp_seg_ds()+" ");
				}
				String direc=direccion.toString();
				if(direc.length()>50)
					direc=direc.substring(0,49);
				tr044Cliente.setDireccion(direc);
				
				tr044Cliente.setDocumento(peticion.getNum_doc_cli_cd());
				tr044Cliente.setTipoDocumento(peticion.getTip_doc_cd());
				
				String phoneNumber = peticion.getTel_cot_ds();			
				if (phoneNumber!=null && phoneNumber.length()> 0){
				/*if (phoneNumber.length()>8) 
					phoneNumber=phoneNumber.substring(0,8);*/
						//Seteo del TR en caso que exista el numero
					tr044Cliente.setTelefono(phoneNumber);
				}else{
					tr044Cliente.setTelefono("0");
				}
				
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(numeroPeticion);
				Iterator listaSubpeticionesIt=null;
				String email= "";
				
				forMayor: for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
					Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
					if(subpeticion_atisLocal!=null){
						Iterator iterCarac=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext())	{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codEmail=new Long (VpistbbaConfig.getVariable("DIRECCELECTRONICA"));
							if (spk.cod_crc_cd.longValue()== codEmail.longValue()){
								log.info("Informacion : Se obtuvo email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								//tr013e.setFatherEmail(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								//addTypeValue(valueList, "father-email", subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								email = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
								break forMayor;
							}
						}
					}
				}
				tr044Cliente.setEmail(email);
				
				tr044e.setCliente(tr044Cliente);
				tr044e.setNumeroPedido(numeroPeticion.longValue());
				
				tr044e.setProductos(equiposList);
				
				tr044e.setSegmento(peticion.getCod_sgm_cta_cd().toString());
				
				tr044e.setUsuario(VpistbbaConfig.getVariable("USUARIO_GESREC"));
				tr044e.setNumeroAtis(new Long(peticionAtis).toString());
				tr044e.setTipoOperacion(tipoOperacion);
				
				Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				if(actiID!=null){
	        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(numeroPeticion, actiID);
	        		if(peticionFlujoList != null){
	        			for (Iterator it = peticionFlujoList.iterator(); it.hasNext();) {
							Peticion_flujoLocal pf = (Peticion_flujoLocal) it.next();
							tr044e.setOperacionComercial(((Operacion_comercialKey)pf.getFk_opco_2_pefl().getPrimaryKey()).opco_id);	
						}
	        		}
				}
				
				Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estado_baLocal msgLocal=mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
				msgLocal.setPeticion(peticion);
				msgLocal.setFecha_envio(df.format(new java.util.Date ()));

				String prod_serv = "0";
				
				for (Iterator equiposIter = equiposList.iterator(); equiposIter.hasNext();){
					TR044EEquipment tr044equipos = (TR044EEquipment)equiposIter.next();
					
					prod_serv = new Long(tr044equipos.getCodigoPS()).toString();
				}
				
				msgLocal.setArea(new Integer(prod_serv));
				msgLocal.setMensaje_estado (mensajeOk) ;		
				msgLocal.setCod_actividad_generadora(codActividad);
				
				
				EnviarPrimeraFacturaInternetEquipadoMQ enviarPrimeraFacturaMQ = new EnviarPrimeraFacturaInternetEquipadoMQ();
				enviarPrimeraFacturaMQ.enviarMensaje(tr044e);
			}catch(Exception ex){
				log.error("Error en consulta solicitarPrimeraFacturaInternetEquipado", ex);
				ex.printStackTrace();
			}
			return idCorrelativoMensaje;
		}
		
		public TR044S buscarRespuestaFacturaInternetEquipado(Long idPeticion, Long idMensaje) throws ATiempoAppEx
		{
			validaHome();
			TR044S tr044s = null;
			try
			{	
				if(mensajeEstadoBaLocalHome==null)
					mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(idMensaje));
				
				if (msgMoLocal == null)
					return null;

				Mensaje_estadoLocal msgEstadoLocal = msgMoLocal.getMensaje_estado();

				PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Collection c = pLocal.getTmp_equipo();
				for (Iterator it = c.iterator(); it.hasNext();)
				{	
					Tmp_equipoLocal tmpdtLocal = (Tmp_equipoLocal) it.next();
					try{
						tr044s = (TR044S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
						if (!tr044s.getId().equals(idMensaje.toString())){
							tr044s = null;
						}else{
							return tr044s;
						}
					}catch(ClassCastException ex){
						tr044s = null;
					}				
				}			
			}
			catch (FinderException e)
			{
				log.error("Error al buscar Respuesta Modem.",e);
				throw new ATiempoAppEx (ATiempoAppEx.FINDER);
			}
			catch (NamingException e)
			{
				log.error("Error al buscar Respuesta Modem.",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
			
			return tr044s;
		}
		
		
		public boolean validarEstadosMensajesInternetEquipado (Long idPeticion) throws ATiempoAppEx{
			log.debug("Entro al método como corresponde");
			
			return true;
		}
		
		public void creacionActuacionAgendaSC(Long idPeticion, Timestamp fechaReagendamiento, String tipoOC, String codActividad, ActividadEJBDTO act){
			log.info("Se ingresa al método de envío de mensaje de alta de actuación: TR701E");
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Deco_tarjetaLocalHome deco_tarjetaLocalHome = (Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
				Recursos_baLocalHome recursos_baLocalHome = 	(Recursos_baLocalHome) HomeFactory.getHome( Recursos_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocalHome mensaje_estado_baLocalHome = 	(Mensaje_estado_baLocalHome) HomeFactory.getHome( Mensaje_estado_baLocalHome.JNDI_NAME);
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				SubsegmentoLocalHome subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
				SegmentoLocalHome segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME); 
				Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome = (Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				ElementoLocalHome elementoLocalHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
				Ps_Tipo_EqLocalHome psTipoEquipoLocalHome = (Ps_Tipo_EqLocalHome)HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
				Elemento_agenda_scLocalHome elementoAgendSCLocalHome = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
				
				Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome) HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
				/*RQ.8595 - mfmendez*/
				Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
				
				boolean puertosModificados = true;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat formatterRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Recursos_linea_basicaLocal rlb = null;
				try{
					rlb = recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
					Recursos_baKey Recursos_baKey=new Recursos_baKey();
				}
			    catch (FinderException e) {
					log.debug("No encontró recursos LB en la petición en alta actuación de Agenda SC: "+e.toString());
				}
				
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));

				//DAVID: Dic 15 2010, se cambia la forma de contar decos y de extraer el tipo de deco dominante, se hace por familias y ps 281.
//				Collection deco_tarjeta=peticionLocal.getDeco_tarjeta();
//				String numDecos="0";
//				if(deco_tarjeta!=null){
//					numDecos=deco_tarjeta.size()+"";
//				}
				
				Collection equipos = peticionLocal.getElemento_peticion();
				
				
				Collection psPeticion=peticionLocal.getProducto_servicio_peticion();
				Iterator psPeticionIt=null;
				
				int contadorDecos=0;
				String numDecos="0";
				boolean solucionTV = false;
				boolean esTraslado = false;
				for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
					Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
					if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV
							||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV
							||familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
						log.debug("El ps pertenece a una familia de tipo Deco");
						contadorDecos++;
						
					}
					if(familia_producto_servicioKey.faps_id.intValue() == ComunInterfaces.familiaPaqueteTV)
						solucionTV = true;
					if(familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.altaMigracionPS &&
							familia_producto_servicioKey.faps_id.intValue() != ComunInterfaces.bajaMigracionPS)
						esTraslado = true;
					/*if(producto_servicio_peticionLocal.getPsId().intValue()==ComunInterfaces.psTVACCESOBASE){
					//	contadorDecos++;
					}*/
				}
				numDecos=""+contadorDecos;
				log.debug("El número de decos resultante es: "+numDecos);
				
				int esTV = peticionLocal.getPeti_id_instancia().indexOf("TV");
				if(contadorDecos == 0 && esTV > -1 && !solucionTV && !esTraslado){
					Properties_BDLocalHome bandejaErrorHome = (Properties_BDLocalHome) HomeFactory.getHome( Properties_BDLocalHome.JNDI_NAME);
					Properties_BDLocal bandejaErrorLocal = bandejaErrorHome.findByPrimaryKey("BANDEJA_ERROR_TV");
					insertarCausalesCnaPeticion(peticionLocal, codActividad, new Long(701), act.getIdActividadFlujo());
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (codActividad) ;
					String plataforma = bandejaErrorLocal.getValor();
					log.debug("La plataforma a la que se deriva es: "+plataforma);
					String bandeja = getNombreBandeja(plataforma);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					act.setObservacion("Error en el envio a Agenda SC, se deriva a "+bandeja+": La cantidad de Decos informados en la peticiòn es: " + contadorDecos);
					act.setRealizarTerminoInmediato(true);
					actividadEJB.terminar(act);
					return;
				}
				
				// Se extrae el tipo de deco
//				Iterator decoTarjetaListIt=null;
//				String tipoDeco="";
//				if(deco_tarjeta!=null){
//					for(decoTarjetaListIt=deco_tarjeta.iterator();decoTarjetaListIt.hasNext();){
//						Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal)decoTarjetaListIt.next();
//						if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoHDTV)){
//							tipoDeco=ComunInterfaces.desHCDecoHDTV;
//							break;
//						}else if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoPVR)){
//							tipoDeco=ComunInterfaces.desHCDecoPVR;
//							break;
//						}else if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoSTD)){
//							tipoDeco=ComunInterfaces.desHCDecoSTD;
//							break;
//						}
//					}
//				}
				String tipoDeco="";
				boolean tieneHD=false;
				boolean tienePVR=false;
				boolean tieneSTD=false;
				for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
					Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
					if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
						tieneHD=true;						
						log.debug("Tiene deco HD");
					}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
						tienePVR=true;						
						log.debug("Tiene deco PVR");
					}
					else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
						tieneSTD=true;
						log.debug("Tiene deco STD");
					}/*else if(producto_servicio_peticionLocal.getPsId().intValue()==ComunInterfaces.psTVACCESOBASE){
						tieneSTD=true;
						log.debug("Tiene deco STD");
					}*/
				}
				
				if(tieneHD){
					tipoDeco=ComunInterfaces.desHCDecoHDTV;
				}else if(tienePVR){
					tipoDeco=ComunInterfaces.desHCDecoPVR;
				}else if(tieneSTD){
					tipoDeco=ComunInterfaces.desHCDecoSTD;
				}
				log.debug("El tipo de deco resultante es: "+tipoDeco);
				
				Peticion_atisLocal peticion_atisLocal=peticionLocal.getFk_01_pet_atis();				
				/**
				 * Otra vez pidieron cambio, se usa de nuevo la agrupación, Nov 4 2010
				 */
				String infoContactYMedia="";
				Collection agrupacionAtisList=peticion_atisLocal.getAgrupacion_atis();
				Iterator agrupacionAtisListIt=null;
				/**
				 * Pidieron cambio, ya no se saca infoContactYMedia de la agrupación sino de la petición.
				 */
//				
//				if(peticionLocal.getObs_pet_ds()!=null){
//					infoContactYMedia=peticionLocal.getObs_pet_ds();
//				}
				/**
				 * Piden ahora que se saque de la subpeticion atis ya no de la agrupación Nov 12
				 */
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				
				ArrayList listaSubpeticiones=peticionesDelegate.obtenerSubpeticionesDesdePeticion(idPeticion);
				Iterator listaSubpeticionesIt=null;
				
				CaracteristicaPSLocal caracteristicaPSEquipo=null;
				Subpeticion_caracteristicasLocal caracteristicaPSCuota=null;
				
				boolean isCaracteristica = false;
				int plazoFinanciamiento =0;
				List caracteristicas = new ArrayList();
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				String[] id_ps = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CAMARA_MONITOREO").split(",");
				for(listaSubpeticionesIt=listaSubpeticiones.iterator();listaSubpeticionesIt.hasNext();){
					Subpeticion_atisLocal subpeticion_atisLocal = (Subpeticion_atisLocal)listaSubpeticionesIt.next();
					
					CaracteristicaPSLocal caracteristicaPS = obtenerProductoServicio(subpeticion_atisLocal);
					//Se sacan las caracteristicas pertenecientes a cámaras
					for (int i = 0; i < id_ps.length; i++) {
						Long idPsCamara = new Long(id_ps[i]);
						if(caracteristicaPS!= null && idPsCamara.equals(caracteristicaPS.getPsPeticion())){
							caracteristicas.add(caracteristicaPS);
							log.debug("caracteristica camara agregada: "+caracteristicaPS.getCaracteristicaPS()+" PS_PETICION: "+caracteristicaPS.getPsPeticion()+" PS_ID: "+ caracteristicaPS.getPsId());
							break;
						}
					}

					if(caracteristicaPSEquipo==null)
						caracteristicaPSEquipo=obtenerProductoServicio(subpeticion_atisLocal);
					Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
					Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_CUOTAS_QW),(Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey());
					try{
						if(!isCaracteristica)
							caracteristicaPSCuota=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
						
						if(caracteristicaPSCuota.getCod_val_crc_cd().intValue()>0 ){
							isCaracteristica = true;
							plazoFinanciamiento = caracteristicaPSCuota.getCod_val_crc_cd().intValue();
						}
						
					}catch(FinderException e){
						log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica de cuotas"+e.getMessage());
					}
					if(subpeticion_atisLocal!=null){  
						if(subpeticion_atisLocal.getObs_sub_ds()!=null&&!subpeticion_atisLocal.getObs_sub_ds().equals("")){
							infoContactYMedia=subpeticion_atisLocal.getObs_sub_ds();
						}
						
					}
				}
				Long idPsCaracteristica = null;
				Long idPs = null;
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				TR701EProduct tr701EProduct=new TR701EProduct();
				tr701EProduct.setAtisRequestNumber(petAtisK.cod_pet_cd.toString());
				tr701EProduct.setProductCode(new Long(0));//ME FALTA ESTE, también está en el catálogo de agenda.
				if(caracteristicaPSEquipo!=null){
					Producto_servicioLocal ps=caracteristicaPSEquipo.getProducto_servicio();
					tr701EProduct.setNombrePSProducto(ps.getPs_nombre());
					tr701EProduct.setCaracteristicaProducto(caracteristicaPSEquipo.getCaracteristicaPS());
					idPsCaracteristica = caracteristicaPSEquipo.getPsId();
					idPs = caracteristicaPSEquipo.getPsPeticion();
					tr701EProduct.setProductCode(idPsCaracteristica);
				}
				if(caracteristicaPSCuota!=null && caracteristicaPSCuota.getCod_val_crc_cd()!=null){
					tr701EProduct.setPlazoFinanciacion(plazoFinanciamiento);
				}
				//Yumbleiner CR 6842 Agregar IdPCTV en la tr-701
				tr701EProduct.setIdPcTv("0");
				if(peticionLocal.getNum_ide_nu_tv()!=null && peticionLocal.getNum_ide_nu_tv().length()>0){
					tr701EProduct.setIdPcTv(peticionLocal.getNum_ide_nu_tv());
				}

//				if(agrupacionAtisList!=null){
//					for(agrupacionAtisListIt=agrupacionAtisList.iterator();agrupacionAtisListIt.hasNext();){
//						Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) agrupacionAtisListIt.next();
//						if(agrupacion_atisLocal.getObs_agr_sub_ds()!=null&&!agrupacion_atisLocal.getObs_agr_sub_ds().equals("")){
//							infoContactYMedia=agrupacion_atisLocal.getObs_agr_sub_ds();
//							break;
//						}
//					}
//				}
				
				//Se extrae el email de contacto y el medio de contacto.
				

				
				String emailContacto="";
				String medioDeContacto="";
				String telefonoContacto="";
				String celularContacto="";
				if(!infoContactYMedia.equals("")){
					String[] datosGrupo=infoContactYMedia.split("\\|");
					if(datosGrupo.length == 2){						
						medioDeContacto=datosGrupo[0];
						if(medioDeContacto.equals("2")){
							emailContacto=datosGrupo[1];
						}else if(medioDeContacto.equals("1")){
							celularContacto=datosGrupo[1];
						}else if(medioDeContacto.equals("3")){
							telefonoContacto=datosGrupo[1];
						}else if(medioDeContacto.equals("4")){
							celularContacto=datosGrupo[1];
						}
					}else{
						//Si infoContactYMedia no tiene nada, entonces se hace lógica para extraer el medio de contacto a través del teléfono de contacto.
						String telefono="";
						if(peticionLocal.getTel_cot_ds()!=null){
							telefono=peticionLocal.getTel_cot_ds();
						}			
						if(telefono.matches("3"+"\\d{9}")){
							log.debug("Es celular, el contact media será SMS...");
							medioDeContacto=CONTACT_MEDIA_SMS;
							celularContacto=telefono;
						}else if(telefono.matches("\\d{6}||\\d{7}||\\d{8}||\\d{9}")){
							log.debug("Es un fijo de 7,8 o 9 dígitos");
							medioDeContacto=CONTACT_MEDIA_TELEFONO;
							telefonoContacto=telefono;
						}else if(telefono.matches("[^3]\\d{9}")){
							log.debug("Es un fijo de 10 caracteres sin 3 al comienzo porque sería celular...");
							medioDeContacto=CONTACT_MEDIA_TELEFONO;
							telefonoContacto=telefono;
						}else{
							medioDeContacto=CONTACT_MEDIA_TELEFONO;
							log.debug("No es ni fijo ni celular");
						}
					}					
				}else{
					//	Si no viene info separada por |, entonces se hace lógica para extraer el medio de contacto a través del teléfono de contacto.
					String telefono="";
					if(peticionLocal.getTel_cot_ds()!=null){
						telefono=peticionLocal.getTel_cot_ds();
					}			
					if(telefono.matches("3"+"\\d{9}")){
						log.debug("Es celular, el contact media será SMS...");
						medioDeContacto=CONTACT_MEDIA_SMS;
						celularContacto=telefono;
					}else if(telefono.matches("\\d{6}||\\d{7}||\\d{8}||\\d{9}")){
						log.debug("Es un fijo de 6,7,8 o 9 dígitos");
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						telefonoContacto=telefono;
					}else if(telefono.matches("[^3]\\d{9}")){
						log.debug("Es un fijo de 10 caracteres sin 3 al comienzo porque sería celular...");
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						telefonoContacto=telefono;
					}else{
						medioDeContacto=CONTACT_MEDIA_TELEFONO;
						log.debug("No es ni fijo ni celular");
					}
				}				
				
				//Se extraen datos de ADSL
				InformacionAdslDTO adsl=this.obtenerDatosAdsl(idPeticion);
				
				if (adsl == null){
					adsl = this.obtenerDatosActualAdsl(idPeticion);
				}else if (adsl != null && adsl.getDirecIpDslam()== null){
					adsl = this.obtenerDatosActualAdsl(idPeticion);
				}else if (adsl != null && adsl.getDirecIpDslam() != null && adsl.getDirecIpDslam().length() <= 0){
					adsl = this.obtenerDatosActualAdsl(idPeticion);
				}
					
			//Raúl Triviño: 23-06-2011 - Ajuste para colocar el padre email en el acceso de usuario
			if (adsl != null && adsl.getFatherEmail() != null){
				adsl.setUsuarioAcc(adsl.getFatherEmail());
			}
			//End
				
				//Se obtienen los PS de la petíción
				String listaPs="";
				String listaTematicosTV="";
				String emailCaracteristicas="";
				Collection productoServicioPetList=peticionLocal.getProducto_servicio_peticion();
				Iterator listaProductoServicioPetIt=null;
				
				Collection recursosBACollection = peticionLocal.getRecursos_ba();
				for (Iterator recursosBAIterator = recursosBACollection.iterator();recursosBAIterator.hasNext();){
					Recursos_baLocal recursosBaLocal = (Recursos_baLocal)recursosBAIterator.next();
					
					if (recursosBaLocal.getPort_modification_flag()!= null && !recursosBaLocal.getPort_modification_flag().equalsIgnoreCase("si")){
						puertosModificados = false;
						break;
					}
				}
				
				for(listaProductoServicioPetIt=productoServicioPetList.iterator();listaProductoServicioPetIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)listaProductoServicioPetIt.next();
					
					//Se extrae la info de email
					Subpeticion_atisLocal subPet = producto_servicio_peticionLocal.getFk_01_subp_atis();
					Iterator iterCarac=subPet.getSubpeticion_caracteristicas().iterator();

					while (iterCarac.hasNext())
					{
						Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
						Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
						Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
						if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue()){
							log.info("Información : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
							emailCaracteristicas=subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
							break;
						}
					}
					
					
					Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
					Producto_servicioKey  producto_servicioKey=(Producto_servicioKey)producto_servicioLocal.getPrimaryKey();
					
					Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
					if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaTematicoTV){
						listaTematicosTV+=" " + producto_servicioLocal.getPs_nombre();
						
					}
					
					if (puertosModificados){
						listaPs = listaPs+producto_servicioLocal.getPs_nombre()+"-";			
					}else{
						Familia_producto_servicioLocal familiaProductoServicioLocal = producto_servicioLocal.getFamilia_producto_servicio();
						Familia_producto_servicioKey familiaProductoServicioKey = (Familia_producto_servicioKey)familiaProductoServicioLocal.getPrimaryKey();
						
							listaPs = listaPs+producto_servicioLocal.getPs_nombre()+"-";
					}
				}
				
				if (listaPs.length()>0){
					listaPs = listaPs.substring(0,listaPs.length()-1);
				}

				TR701E tr701e = new TR701E();
				
				/*mfmendez-Se inicializa el transactionType en "", para que cuando no se setee vaya el tag*/
				tr701e.setTransactionType("");
				/*FIN mfmendez-Se inicializa el transactionType en "", para que cuando no se setee vaya el tag*/
			
				TR701EAccessTime tr701EAccessTime = new TR701EAccessTime();
				
				if (peticionLocal.getJornada_agnd_sc()!= null  && peticionLocal.getJornada_agnd_sc().length()>0){
					tr701EAccessTime.setJorney(peticionLocal.getJornada_agnd_sc());
				}else{
					log.debug("No se encuentra especificada la jornada se setea 14.00.00 por defecto para evitar conflicots con agenda SC");
					tr701EAccessTime.setJorney("14.00.00");
				}
				
				TR701EAdressData tr701EAdressData = new TR701EAdressData();
				tr701EAdressData.setAddress(peticionLocal.getNom_cal_ds()+" "+peticionLocal.getNum_cal_nu()+" "+peticionLocal.getDsc_cmp_pri_ds()+" "+peticionLocal.getNom_slo_no());
				tr701EAdressData.setAgencyName("");//Aplica para SISGOT, se fija en vacío
				
				LocalidadLocal  localidadLocal=(LocalidadLocal)peticionLocal.getFk_01_localidad();
				LocalidadKey localidadKey=(LocalidadKey)localidadLocal.getPrimaryKey();
				
				MunicipioLocal municipioLocal=localidadLocal.getMunicipio();
				MunicipioKey municipioKey=(MunicipioKey)municipioLocal.getPrimaryKey();
				
				DepartamentoKey deptoKey=(DepartamentoKey)peticionLocal.getFk_02_departamento().getPrimaryKey();
				
				tr701EAdressData.setCity(municipioKey.cod_mun);
				if (peticionLocal.getCoord_x_agnd_sc() != null && peticionLocal.getCoord_x_agnd_sc().length()>0){
					tr701EAdressData.setCoordinateX(peticionLocal.getCoord_x_agnd_sc());
				}else{
					log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
					tr701EAdressData.setCoordinateX("0");
				}
				
				if(peticionLocal.getCoord_y_agnd_sc() != null && peticionLocal.getCoord_y_agnd_sc().length()>0){
					tr701EAdressData.setCoordinateY(peticionLocal.getCoord_y_agnd_sc());
				}else{
					log.debug("No se encuatra especificada la coordenada Y se setea 0 por defecto para evitar conflicots con agenda SC");
					tr701EAdressData.setCoordinateY("0");
				}
				
				tr701EAdressData.setLocation(localidadKey.cod_loc);
				tr701EAdressData.setNeighborhood(peticionLocal.getNom_slo_no());
				tr701EAdressData.setState(deptoKey.cod_dpt);
				tr701EAdressData.setSubCity(peticionLocal.getNom_slo_no());
				
				
				TR701EContactData tr701EContactData = new TR701EContactData();
				tr701EContactData.setContactCellPhone(celularContacto);
				tr701EContactData.setContactEmail(emailContacto);
				tr701EContactData.setContactMedia(medioDeContacto);
				tr701EContactData.setContactName(peticionLocal.getNom_ds());
				tr701EContactData.setContactPhone(telefonoContacto);
				tr701EContactData.setDatosAgendamiento(infoContactYMedia);
				
				//Estos campos de dependence no son necesarios en atiempo.
				TR701ECoordinatedActions tr701ECoordinatedActions = new TR701ECoordinatedActions();
				tr701ECoordinatedActions.setDependeceId(new Long(0));
				tr701ECoordinatedActions.setDependenceType("");
				
				TR701ECustomer tr701ECustomer = new TR701ECustomer();
				tr701ECustomer.setCode(new Long(0));
				if(peticionLocal.getNum_doc_cli_cd()!=null){
					tr701ECustomer.setCode(new Long(peticionLocal.getNum_doc_cli_cd()));
				}				
				tr701ECustomer.setEmail(emailCaracteristicas);
				tr701ECustomer.setId(peticionLocal.getNum_doc_cli_cd());
				tr701ECustomer.setIdType(peticionLocal.getTip_doc_cd());
				tr701ECustomer.setName(peticionLocal.getNom_ds());
				
				tr701ECustomer.setPhoneNumber(new Long(0));
				if(peticionLocal.getNum_ide_nu_stb()!=null && peticionLocal.getNum_ide_nu_stb().length()>0){
					tr701ECustomer.setPhoneNumber(new Long(peticionLocal.getNum_ide_nu_stb()));
				}
				
				SubsegmentoKey subsegmentoKey = new SubsegmentoKey(peticionLocal.getCod_sbg_cta_cd());
				SubsegmentoLocal subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
				tr701ECustomer.setSubSeg(subsegmentoLocal.getDescripcion());	
				SegmentoKey segmentoKey = new SegmentoKey(peticionLocal.getCod_sgm_cta_cd());
				SegmentoLocal segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
				
				String sufijoSegmento = codActividad.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGACS")) ?
					peticionesDelegate.recuperarParametroFromPropertiesBD("AUTOINS_SEGMENTO") : "" ;

				tr701ECustomer.setType(segmentoLocal.getSegm_descripcion() + sufijoSegmento);
				
				TR701EDateData tr701EDateData = new TR701EDateData();
				
				//Este campo es para incidencias técnicas
			    //tr701EDateData.setBreakdownDate("");
				tr701EDateData.setBreakdownDate(formatter.format(peticionLocal.getPeti_fecha_ingreso()));
				
//			if(fechaReagendamiento != null){
//				tr701EDateData.setBreakdownCommitmentDate(formatter.format(fechaReagendamiento));					
//			}else{
				tr701EDateData.setBreakdownCommitmentDate("");
//			}
				
				
				TR701EMassiveBreakdowns tr701EMassiveBreakdowns=new TR701EMassiveBreakdowns();
				tr701EMassiveBreakdowns.setBreakdownMassiveType("");
				tr701EMassiveBreakdowns.setFinalRange("");
				tr701EMassiveBreakdowns.setInitialRange("");
				
				TR701ENotes tr701ENotes=new TR701ENotes();
				
				//Raúl Triviño
				String estrato = "";
				if(agrupacionAtisList!=null){
					for(agrupacionAtisListIt=agrupacionAtisList.iterator();agrupacionAtisListIt.hasNext();){
						Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) agrupacionAtisListIt.next();
						if(agrupacion_atisLocal.getNom_tip_uso_no()!=null&&!agrupacion_atisLocal.getNom_tip_uso_no().equals("")){
							estrato = agrupacion_atisLocal.getNom_tip_uso_no();
							break;
						}
					}
				}
				
				String tipoIncidencia = null;
				for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
					tipoIncidencia = obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (ComunInterfaces.CARACINCIDENCIA));
				}
				String notaDescripcion = "";
				String nombreCliente = Utiles.sinNull(peticionLocal.getNom_ds(),"") 
				+ " " +Utiles.sinNull(peticionLocal.getPri_ape_ds(),"") 
				+ " " + Utiles.sinNull(peticionLocal.getSeg_ape_ds(),"");
				String nombreSolicitante = peticionLocal.getNom_int_ds() 
					+ " " + peticionLocal.getPri_ape_int_ds()
					+ " " + peticionLocal.getSeg_ape_int_ds();
				notaDescripcion = notaDescripcion+ComunInterfaces.NOMBRE_SOLICITANTE+nombreSolicitante+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.TEL_SOLICITANTE+peticionLocal.getTel_cot_ds()+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_SOLICITANTE+peticionLocal.getTel_cot_ds_1()+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.DATOS_AGENDAMIENTO+infoContactYMedia+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.NOMBRE_CLIENTE+nombreCliente+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.TEL_CLIENTE+peticionLocal.getTel_cot_ds_1()+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TEL_CLIENTE+peticionLocal.getTel_cot_ds_2()+";";
//				notaDescripcion = notaDescripcion+ComunInterfaces.TEL_CONTACTO_INTERLOCUTOR+peticionLocal.getTel_cot_ds()+";";
//				notaDescripcion = notaDescripcion+ComunInterfaces.SEG_TELEFONO_CONTACTO+peticionLocal.getTel_cot_ds_1()+";";
//				notaDescripcion = notaDescripcion+ComunInterfaces.TER_TELEFONO_CONTACTO+peticionLocal.getTel_cot_ds_2()+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACIONES+peticionLocal.getObs_pet_ds()+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.ESTRATO+estrato+";";
				notaDescripcion = notaDescripcion+ComunInterfaces.TIPOINCIDENCIA+tipoIncidencia+";";
				
			Bitacora_peticionLocalHome bitacoraPeticionLocalHome = (Bitacora_peticionLocalHome)HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Collection listaPeticionesList = bitacoraPeticionLocalHome.findByPetiOrden(idPeticion);
			String observacionActividad = "";
				
			for (Iterator listaPeticionesIterator = listaPeticionesList.iterator();listaPeticionesIterator.hasNext();){
				Bitacora_peticionLocal bitacoraPeticionLocal = (Bitacora_peticionLocal)listaPeticionesIterator.next();

				if (bitacoraPeticionLocal.getBipe_fecha_fin() != null){
					observacionActividad = bitacoraPeticionLocal.getBipe_observacion();
				}
			}

			notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACION_BITACORA+observacionActividad;
			
				tr701ENotes.setNoteDescription(notaDescripcion);
				tr701ENotes.setNoteDetail("");
				tr701ENotes.setNoteType("ACT_COMENT");
				//End Raúl Triviño
			
				TR701ETechnicalData tr701ETechnicalData = new TR701ETechnicalData();
				//Req 2012 - 00015182 Jorge Roa
				tr701ETechnicalData.setNomInterlocutor(peticionLocal.getNom_int_ds());
				tr701ETechnicalData.setTelInterlocutor(peticionLocal.getTel_cot_ds());
				tr701ETechnicalData.setTel1Contacto(peticionLocal.getTel_cot_ds());
				tr701ETechnicalData.setTel2Contacto(peticionLocal.getTel_cot_ds_1());
				tr701ETechnicalData.setTel3Contacto(peticionLocal.getTel_cot_ds_2());
				//Fin Req 2012 - 00015182 Jorge Roa	
				tr701ETechnicalData.setAdsl("");
				tr701ETechnicalData.setBox("");
				tr701ETechnicalData.setBoxAddress("");
				tr701ETechnicalData.setBoxDistance("");
				tr701ETechnicalData.setBoxPair("");
				tr701ETechnicalData.setBoxType("");
				tr701ETechnicalData.setCable("");
				tr701ETechnicalData.setCablePair("");
				tr701ETechnicalData.setCard("");
				tr701ETechnicalData.setCentralCode("");
				tr701ETechnicalData.setCloset("");
				tr701ETechnicalData.setClosetAddress("");
				tr701ETechnicalData.setDecosNumber(numDecos);
				tr701ETechnicalData.setDecosType(tipoDeco);	
				tr701ETechnicalData.setDistributorAddress("");
				tr701ETechnicalData.setDistributorCode("");
				tr701ETechnicalData.setDistributorDescription("");
				tr701ETechnicalData.setFrame("");
				tr701ETechnicalData.setHorizontalPosicion("");
				tr701ETechnicalData.setIpDslam("");
				tr701ETechnicalData.setIpLan("");
				tr701ETechnicalData.setIpLanMask("");
				tr701ETechnicalData.setIpType("");
				tr701ETechnicalData.setIpWan("");
				tr701ETechnicalData.setLatitude("");
				tr701ETechnicalData.setLen("");
				tr701ETechnicalData.setLongitude("");
				tr701ETechnicalData.setPackages(listaTematicosTV);
				tr701ETechnicalData.setPhoneNumber("");
				tr701ETechnicalData.setPortId("");
				tr701ETechnicalData.setPots("");
				tr701ETechnicalData.setRack("");			
				tr701ETechnicalData.setSpeed("");
				tr701ETechnicalData.setStrip("");
				tr701ETechnicalData.setStripPair("");
				tr701ETechnicalData.setSubrack("");
				tr701ETechnicalData.setUserAccess("");
				tr701ETechnicalData.setVpiVci("");
				tr701ETechnicalData.setVpiVciNetwork("");
				tr701ETechnicalData.setZone("");
				
				/*Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/
				String velocidadPlanYPS = extraerVelocidadPlanYPSPrioridadAlta(idPeticion);
				String velocidadPlan="";
				String psPlan="";
				if(velocidadPlanYPS!=null && !velocidadPlanYPS.equals("")){
					String[] velYPS = velocidadPlanYPS.split("#");
					velocidadPlan = velYPS[0];
					psPlan = velYPS[1];
				}
				
				if(velocidadPlan != null && !velocidadPlan.equals(""))
					tr701ETechnicalData.setSpeed(velocidadPlan);
				else
					tr701ETechnicalData.setSpeed("");
				/*FIN Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/
				
				if(adsl!=null){
					if(adsl.getAdsl()!=null){
						String adslAux[] = adsl.getAdsl().split("-");
						tr701ETechnicalData.setAdsl(adsl.getAdsl());
						tr701ETechnicalData.setRack(adslAux[0]);
					}else{
						tr701ETechnicalData.setAdsl("");
					}if(adsl.getSlot()!=null){
						tr701ETechnicalData.setCard(adsl.getSlot());
					}else{
						tr701ETechnicalData.setCard("");
					}if(adsl.getFrame()!=null){
						tr701ETechnicalData.setFrame(adsl.getFrame());
						tr701ETechnicalData.setSubrack(adsl.getFrame());
					}else{
						tr701ETechnicalData.setFrame("");
					}if(adsl.getDirecIpDslam()!=null){
						tr701ETechnicalData.setIpDslam(adsl.getDirecIpDslam());
					}else{
						tr701ETechnicalData.setIpDslam("");
					}if(adsl.getDirecIpLan()!=null){
						tr701ETechnicalData.setIpLan(adsl.getDirecIpLan());
					}else{
						tr701ETechnicalData.setIpLan("");
					}if(adsl.getMascaraLan()!=null){
						tr701ETechnicalData.setIpLanMask(adsl.getMascaraLan());
					}else{
						tr701ETechnicalData.setIpLanMask("");
					}if(adsl.getDirecIpWan()!=null){
						tr701ETechnicalData.setIpWan(adsl.getDirecIpWan());
					}else{
						tr701ETechnicalData.setIpWan("");
					}if(adsl.getPuerto()!=null){
						tr701ETechnicalData.setPortId(adsl.getPuerto());
					}else{
						tr701ETechnicalData.setPortId("");
					}if(adsl.getPost()!=null){
						tr701ETechnicalData.setPots(adsl.getPost());
					}else{
						tr701ETechnicalData.setPots("");
					}if(adsl.getUsuarioAcc()!=null){
						tr701ETechnicalData.setUserAccess(adsl.getUsuarioAcc());
					}else{
						tr701ETechnicalData.setUserAccess("");
					}if(adsl.getVpiVciCliente()!=null){
						tr701ETechnicalData.setVpiVci(adsl.getVpiVciCliente());
					}else{
						tr701ETechnicalData.setVpiVci("");
					}if(adsl.getVpiVciRed()!=null){
						tr701ETechnicalData.setVpiVciNetwork(adsl.getVpiVciRed());
					}else{
						tr701ETechnicalData.setVpiVciNetwork("");
					}if(adsl.getCodZonaAtend()!=null){
						tr701ETechnicalData.setZone(adsl.getCodZonaAtend());
					}else{
						tr701ETechnicalData.setZone("");
					}
				}if(rlb!=null){
					//Req. 13420 - Jesus Carvajal - 10/04/2012
					
					if (rlb.getTelefono_asg()!= null) 
						tr701ETechnicalData.setPhoneNumber(rlb.getTelefono_asg().toString());
					else 
						tr701ETechnicalData.setPhoneNumber("");
					if (rlb.getLen()!=null) 
						tr701ETechnicalData.setLen(rlb.getLen());
					else 
						tr701ETechnicalData.setLen("");
					if (rlb.getPosicion_horizontal_asg()!=null) 
						tr701ETechnicalData.setHorizontalPosicion(rlb.getPosicion_horizontal_asg());
					else 
						tr701ETechnicalData.setHorizontalPosicion("");
					if (rlb.getDist_prim_asg()!=null)
						tr701ETechnicalData.setDistributorCode(rlb.getDist_prim_asg().toString());
					else
						tr701ETechnicalData.setDistributorCode("");
					if (rlb.getDesc_dist_prim_asg()!=null)
						tr701ETechnicalData.setDistributorDescription(rlb.getDesc_dist_prim_asg());
					else
						tr701ETechnicalData.setDistributorDescription("");
					if (rlb.getDir_distribuidor()!=null)
						tr701ETechnicalData.setDistributorAddress(rlb.getDir_distribuidor());
					else
						tr701ETechnicalData.setDistributorAddress("");
					if (rlb.getListon_asg()!=null)
						tr701ETechnicalData.setStrip(rlb.getListon_asg());
					else
						tr701ETechnicalData.setDistributorAddress("");
					if (rlb.getPar_liston_asg()!=null)
						tr701ETechnicalData.setStripPair(rlb.getPar_liston_asg().toString());
					else
						tr701ETechnicalData.setStripPair("");
					
					if (rlb.getInd_dedicado() != null && rlb.getInd_dedicado().intValue() == 1){
						tr701ETechnicalData.setParDedicated("Si");
						if(rlb.getCaja_dedicado()!=null){
							tr701ETechnicalData.setBox(rlb.getCaja_dedicado());
						}else{
							tr701ETechnicalData.setBox("");
						}if(rlb.getArmario_dedicado()!=null){
							tr701ETechnicalData.setCloset(rlb.getArmario_dedicado());
						}else{
							tr701ETechnicalData.setCloset("");
						}if(rlb.getPar_caja_dedicado()!=null){
							tr701ETechnicalData.setBoxPair(""+rlb.getPar_caja_dedicado());
						}else{
							tr701ETechnicalData.setBoxPair("");
						}if(rlb.getCable_dedicado()!=null){
							tr701ETechnicalData.setCable(rlb.getCable_dedicado());
						}else{
							tr701ETechnicalData.setCable("");
						}if(rlb.getPar_cable_dedicado()!=null){
							tr701ETechnicalData.setCablePair(""+rlb.getPar_cable_dedicado());
						}else{
							tr701ETechnicalData.setCablePair("");
						}if(rlb.getDir_armario_dedicado()!=null){
							tr701ETechnicalData.setClosetAddress(rlb.getDir_armario_dedicado());
						}else{
							tr701ETechnicalData.setClosetAddress("");
						}if(rlb.getDir_caja_dedicado()!=null){
							tr701ETechnicalData.setBoxAddress(rlb.getDir_caja_dedicado());
						}else{
							tr701ETechnicalData.setBoxAddress("");
						}if(rlb.getTipo_caja_dedicado()!=null){
							tr701ETechnicalData.setBoxType(""+rlb.getTipo_caja_dedicado());
						}else{
							tr701ETechnicalData.setBoxType("");
						}if(rlb.getCod_central_dedicado()!=null){
							tr701ETechnicalData.setCentralCode(""+rlb.getCod_central_dedicado());
						}else{
							tr701ETechnicalData.setCentralCode("");
						}
						log.debug("Envia información de red dedicada");
						
					}else{
						tr701ETechnicalData.setParDedicated("No");
						if(rlb.getCaja_asg()!=null){
							tr701ETechnicalData.setBox(rlb.getCaja_asg());
						}else{
							tr701ETechnicalData.setBox("");
						}if(rlb.getArmario_asg()!=null){
							tr701ETechnicalData.setCloset(rlb.getArmario_asg());
						}else{
							tr701ETechnicalData.setCloset("");
						}if(rlb.getPar_caja_asg()!=null){
							tr701ETechnicalData.setBoxPair(""+rlb.getPar_caja_asg());
						}else{
							tr701ETechnicalData.setBoxPair("");
						}if(rlb.getCable()!=null){
							tr701ETechnicalData.setCable(rlb.getCable());
						}else{
							tr701ETechnicalData.setCable("");
						}if(rlb.getPar_cable()!=null){
							tr701ETechnicalData.setCablePair(""+rlb.getPar_cable());
						}else{
							tr701ETechnicalData.setCablePair("");
						}if(rlb.getDir_armario()!=null){
							tr701ETechnicalData.setClosetAddress(rlb.getDir_armario());
						}else{
							tr701ETechnicalData.setClosetAddress("");
						}if(rlb.getDir_caja()!=null){
							tr701ETechnicalData.setBoxAddress(rlb.getDir_caja());
						}else{
							tr701ETechnicalData.setBoxAddress("");
						}if(rlb.getTipo_caja()!=null){
							tr701ETechnicalData.setBoxType(""+rlb.getTipo_caja());
						}else{
							tr701ETechnicalData.setBoxType("");
						}if(rlb.getCod_central()!=null){
							tr701ETechnicalData.setCentralCode(""+rlb.getCod_central());
						}else{
							tr701ETechnicalData.setCentralCode("");
						}
						log.debug("Envia información de red origen");
						
					}
					//Fin Req. 13420
				}
				
				//Raúl Triviño 21/12/2010: Se setean los equipos ya instalados en la petición
				//Para los decos - tarjetas

				//Verificacion si la peticion es una postventa
				/*boolean esPostventa = true;
				Collection psPeticionPSP = peticionLocal.getProducto_servicio_peticion();
				for (Iterator psPeticionIterator = psPeticionPSP.iterator(); psPeticionIterator.hasNext();){
					Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
					
					Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
					Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
					
					if (familiaPSKey != null && 
							((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()))
							|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
						esPostventa = false;
						break;
					}
				}*/
				

				Collection decosTarjetaPeticionList = peticionLocal.getDeco_tarjeta();
				Collection listaEquipos = new ArrayList();
				
				for (Iterator decosTarjetasPeticionIter = decosTarjetaPeticionList.iterator(); decosTarjetasPeticionIter.hasNext();){
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosTarjetasPeticionIter.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
					
					if (decoTarjetaLocal.getSerial_deco() != null && decoTarjetaLocal.getSerial_deco().length() > 0 && !decoTarjetaLocal.getSerial_deco().equals("0")){
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(decoTarjetaLocal.getSerial_deco());
						tr701eEquipment.setBrand(decoTarjetaLocal.getDeco_marca());
						if (decoTarjetaLocal.getDeco_reference()!=null && decoTarjetaLocal.getDeco_reference().length()>0){
							tr701eEquipment.setModel(decoTarjetaLocal.getDeco_reference());
						}else{
							tr701eEquipment.setModel("");
						}
						
						
						if (desHCDecoSTD.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECODTHSTD);
						}else if (desHCDecoPVR.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECODTHPVR);
						}else if (desHCDecoHDTV.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
							tr701eEquipment.setType(ComunInterfaces.DECOHD);
						}
						
						tr701eEquipment.setCassId(decoTarjetaKey.id_deco);
						tr701eEquipment.setCardSerialNumber(decoTarjetaKey.id_tarjeta);
						tr701eEquipment.setInventoryType("");
						tr701eEquipment.setPartType("");
						tr701eEquipment.setId(ComunInterfaces.ID_DECO_TARJETA);
						
						/*RQ.8595 - mfmendez*/
						tr701eEquipment = this.setDatosSAPDecoTarjeta(deco_tar_inf_sapLocalHome, tr701eEquipment, decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta, idPeticion);
						
						listaEquipos.add(tr701eEquipment);
					}
					
				}
				
				//Para los elementos de la petición
				Collection elementoPeticionList = peticionLocal.getElemento_peticion();
				for (Iterator elementoPeticionIter = elementoPeticionList.iterator(); elementoPeticionIter.hasNext();){
					Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal)elementoPeticionIter.next();
										
					
					if (elementoPeticionLocal.getSerial() != null && elementoPeticionLocal.getSerial().length() > 0 && !elementoPeticionLocal.getSerial().equals("0")){
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(elementoPeticionLocal.getSerial());
						tr701eEquipment.setBrand("");
						tr701eEquipment.setModel("");
					
					try{
						Ps_Tipo_EqKey psTipoEquipoKey = new Ps_Tipo_EqKey(new Integer(elementoPeticionLocal.getPs_id().toString()),new Integer(elementoPeticionLocal.getTipo_elemento().toString()));
						Ps_Tipo_EqLocal psTipoEquipoLocal = psTipoEquipoLocalHome.findByPrimaryKey(psTipoEquipoKey);

						Elemento_agenda_scKey elementoAgendaSCKey = new Elemento_agenda_scKey(psTipoEquipoLocal.getId_elemento_agenda());

						if (elementoAgendaSCKey.id_correlativo != null){
							Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendSCLocalHome.findByPrimaryKey(elementoAgendaSCKey);
							tr701eEquipment.setType(elementoAgendaSCLocal.getDesc_equipo());
						}else{
							log.debug("No existe un equipo de agenda asociado al ps:"+elementoPeticionLocal.getPs_id()+" y tipo de elemento:"+elementoPeticionLocal.getTipo_elemento());
						tr701eEquipment.setType("");
						}
					}catch(Exception ex){
						log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio:"+ex);
						tr701eEquipment.setType("");
					}

						tr701eEquipment.setCassId("");
						tr701eEquipment.setCardSerialNumber("");
						tr701eEquipment.setInventoryType(elementoPeticionLocal.getTipo_inventario());
						
						ElementoLocal elementoLocal = elementoLocalHome.findElemento(elementoPeticionLocal.getTipo_elemento().longValue());
						tr701eEquipment.setPartType(elementoLocal.getDesc_elemento());

						tr701eEquipment.setId(ComunInterfaces.ID_OTROS);
						
						/*RQ.8595 - mfmendez*/
						// datos del equipo
						if(elementoPeticionLocal.getFec_cont_sap() != null)
							tr701eEquipment.setPostingDateSAP(elementoPeticionLocal.getFec_cont_sap());
						else
							tr701eEquipment.setPostingDateSAP("");
						
						if(elementoPeticionLocal.getClase_mov_sap() != null)
							tr701eEquipment.setMoveTypeSAP(elementoPeticionLocal.getClase_mov_sap());
						else
							tr701eEquipment.setMoveTypeSAP("");
						
						tr701eEquipment.setMaterialCodeSAP(Integer.toString(elementoPeticionLocal.getPos_doc_sap()));
						
						if(elementoPeticionLocal.getNum_material_sap() != null)
							tr701eEquipment.setMaterialSAP(elementoPeticionLocal.getNum_material_sap());
						else
							tr701eEquipment.setMaterialSAP("");
							
						if(elementoPeticionLocal.getCentro_sap() != null)
							tr701eEquipment.setPlantSAP(elementoPeticionLocal.getCentro_sap());
						else
							tr701eEquipment.setPlantSAP("");
						
						if(elementoPeticionLocal.getAlmacen_sap() != null)
							tr701eEquipment.setStorageSAP(elementoPeticionLocal.getAlmacen_sap());
						else
							tr701eEquipment.setStorageSAP("");
						
						if(elementoPeticionLocal.getCod_lote_sap() != null)
							tr701eEquipment.setBatchCodeSAP(elementoPeticionLocal.getCod_lote_sap());
						else
							tr701eEquipment.setBatchCodeSAP("");
						
						if(elementoPeticionLocal.getUnd_medida_sap() != null)
							tr701eEquipment.setMeasurementUnitSAP(elementoPeticionLocal.getUnd_medida_sap());
						else
							tr701eEquipment.setMeasurementUnitSAP("");
						
						if(elementoPeticionLocal.getCentr_cost_sap() != null)
							tr701eEquipment.setCostCenterSAP(elementoPeticionLocal.getCentr_cost_sap());
						else
							tr701eEquipment.setCostCenterSAP("");
						
						if(elementoPeticionLocal.getArea_func_sap() != null)
							tr701eEquipment.setFuncAreaLongSAP(elementoPeticionLocal.getArea_func_sap());
						else
							tr701eEquipment.setFuncAreaLongSAP("");
						
						if(elementoPeticionLocal.getElement_pep_sap() != null)
							tr701eEquipment.setPepElementSAP(elementoPeticionLocal.getElement_pep_sap());
						else
							tr701eEquipment.setPepElementSAP("");
						
						if(elementoPeticionLocal.getFlag_mat_sap() != null)
							tr701eEquipment.setFlagMatSAP(elementoPeticionLocal.getFlag_mat_sap());
						else
							tr701eEquipment.setFlagMatSAP("");
						
						// Datos de la tarjeta vacios
						tr701eEquipment.setCardPostingDateSAP("");
						tr701eEquipment.setCardMoveTypeSAP("");
						tr701eEquipment.setCardMaterialCodeSAP("");
						tr701eEquipment.setCardMaterialSAP("");
						tr701eEquipment.setCardPlantSAP("");
						tr701eEquipment.setCardStorageSAP("");
						tr701eEquipment.setCardBatchCodeSAP("");
						tr701eEquipment.setCardMeasurementUnitSAP("");
						tr701eEquipment.setCardCostCenterSAP("");
						tr701eEquipment.setCardFuncAreaLongSAP("");
						tr701eEquipment.setCardPepElementSAP("");
						tr701eEquipment.setCardFlagMatSAP("");
						/*FIN - RQ.8595 - mfmendez*/
						
						listaEquipos.add(tr701eEquipment);

					}
				}
				
				//Para las cámaras
				CamaraLocalHome camaraLH = (CamaraLocalHome)HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				Collection camaras = camaraLH.findByPeticion(idPeticion);
				if(camaras!=null && !camaras.isEmpty()){
					for (Iterator iter = camaras.iterator(); iter.hasNext();) {
						CamaraLocal camara = (CamaraLocal) iter.next();
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						setValoresCamara(tr701eEquipment,camara);
						listaEquipos.add(tr701eEquipment);
					}
				}
				
				//Para los modems
				Collection modemPeticionList = peticionLocal.getModem();
				for (Iterator modemPeticionIter = modemPeticionList.iterator(); modemPeticionIter.hasNext();){
					ModemLocal modemLocal = (ModemLocal)modemPeticionIter.next();
					ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
					
					if (modemKey.serial != null && modemKey.serial.length() > 0 && !modemKey.serial.equals("0") && !modemKey.serial.equals("NO SERIAL")){	
						TR701EEquipment tr701eEquipment = new TR701EEquipment();
						tr701eEquipment.setSerialNumber(modemKey.serial);
						tr701eEquipment.setBrand(modemLocal.getModem_marca());
						if (modemLocal.getModelo()!=null && modemLocal.getModelo().length()>0){
							tr701eEquipment.setModel(modemLocal.getModelo());
						}else{
							tr701eEquipment.setModel("");
						}

						if (modemLocal.getTipo() != null && modemLocal.getTipo().intValue()==ComunInterfaces.identificadorWiFi){
							tr701eEquipment.setType(ComunInterfaces.MODEM_WIFI);
						}else{
							tr701eEquipment.setType(ComunInterfaces.MODEM_STD);
						}
						
						tr701eEquipment.setCassId("");
						tr701eEquipment.setCardSerialNumber("");
						tr701eEquipment.setInventoryType("");
						tr701eEquipment.setPartType("");
						tr701eEquipment.setId(ComunInterfaces.ID_MODEM);
						
						/*RQ.8595 - mfmendez*/
						// datos del modem
						if(modemLocal.getFec_cont_sap() != null)
							tr701eEquipment.setPostingDateSAP(modemLocal.getFec_cont_sap());
						else
							tr701eEquipment.setPostingDateSAP("");
						
						if(modemLocal.getClase_mov_sap() != null)
							tr701eEquipment.setMoveTypeSAP(modemLocal.getClase_mov_sap());
						else
							tr701eEquipment.setMoveTypeSAP("");
						
						tr701eEquipment.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
						
						if(modemLocal.getNum_material_sap() != null)
							tr701eEquipment.setMaterialSAP(modemLocal.getNum_material_sap());
						else
							tr701eEquipment.setMaterialSAP("");
							
						if(modemLocal.getCentro_sap() != null)
							tr701eEquipment.setPlantSAP(modemLocal.getCentro_sap());
						else
							tr701eEquipment.setPlantSAP("");
						
						if(modemLocal.getAlmacen_sap() != null)
							tr701eEquipment.setStorageSAP(modemLocal.getAlmacen_sap());
						else
							tr701eEquipment.setStorageSAP("");
						
						if(modemLocal.getCod_lote_sap() != null)
							tr701eEquipment.setBatchCodeSAP(modemLocal.getCod_lote_sap());
						else
							tr701eEquipment.setBatchCodeSAP("");
						
						if(modemLocal.getUnd_medida_sap() != null)
							tr701eEquipment.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
						else
							tr701eEquipment.setMeasurementUnitSAP("");
						
						if(modemLocal.getCentr_cost_sap() != null)
							tr701eEquipment.setCostCenterSAP(modemLocal.getCentr_cost_sap());
						else
							tr701eEquipment.setCostCenterSAP("");
						
						if(modemLocal.getArea_func_sap() != null)
							tr701eEquipment.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
						else
							tr701eEquipment.setFuncAreaLongSAP("");
						
						if(modemLocal.getElement_pep_sap() != null)
							tr701eEquipment.setPepElementSAP(modemLocal.getElement_pep_sap());
						else
							tr701eEquipment.setPepElementSAP("");
						
						if(modemLocal.getFlag_mat_sap() != null)
							tr701eEquipment.setFlagMatSAP(modemLocal.getFlag_mat_sap());
						else
							tr701eEquipment.setFlagMatSAP("");
						
						// Datos de la tarjeta vacios
						tr701eEquipment.setCardPostingDateSAP("");
						tr701eEquipment.setCardMoveTypeSAP("");
						tr701eEquipment.setCardMaterialCodeSAP("");
						tr701eEquipment.setCardMaterialSAP("");
						tr701eEquipment.setCardPlantSAP("");
						tr701eEquipment.setCardStorageSAP("");
						tr701eEquipment.setCardBatchCodeSAP("");
						tr701eEquipment.setCardMeasurementUnitSAP("");
						tr701eEquipment.setCardCostCenterSAP("");
						tr701eEquipment.setCardFuncAreaLongSAP("");
						tr701eEquipment.setCardPepElementSAP("");
						tr701eEquipment.setCardFlagMatSAP("");
						/*FIN - RQ.8595 - mfmendez*/
						
						listaEquipos.add(tr701eEquipment);
					}
				}
				
				tr701e.setEquipments(listaEquipos);
								
				//End Raúl Triviño 21/12/2010: Se setean los equipos ya instalados en la petición
				
				tr701e.setAccessTime(tr701EAccessTime);
				tr701e.setActionName(peticionLocal.getPeti_id_instancia());//Es la familia o grupo de familias (LBBA,BA,TV,etc) 
				tr701e.setAdressData(tr701EAdressData);
				tr701e.setAffectedArea("20");//Si es planta interna o externa, Se pone 20 por ahora (planta interna)
				tr701e.setCodeAction("");//Código tipo tarea, depende de catálogo no definido todavía
				tr701e.setCodeScheduleType(peticionLocal.getTica_id());//Código tipo actuación
				tr701e.setContactData(tr701EContactData);
				tr701e.setCoordinatedActions(tr701ECoordinatedActions);
				tr701e.setCustomer(tr701ECustomer);
				tr701e.setDateData(tr701EDateData);
				tr701e.setDescriptionAction("");//Inicialmente vacío
				tr701e.setDestination("ESB");
				
			//CR-7390 - Yumbleiner - Linea Precableada
			String tmpCarac;
			for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
				tmpCarac = obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT")));
				if (tmpCarac == null){
					tr701e.setProjectCode("");
				}else{
					tr701e.setProjectCode(tmpCarac);
					break;
				}
			}
			
			
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr701e.setId(IdCorrelativo.toString());
				
				Date dateAhora=new Date();
				if(fechaReagendamiento!=null && fechaReagendamiento.before(dateAhora)){
					dateAhora=fechaReagendamiento;
				}
				
				Timestamp timestampAhora=new Timestamp(dateAhora.getTime());
				
				if(tipoOC.equals("esPGACS")||tipoOC.equals("esReversaAutoInst")){
					tr701e.setIdSchedule("AP"+idPeticion.toString()+"-"+formatter.format(fechaReagendamiento));
					tr701e.setScheduleDate(formatter.format(fechaReagendamiento));
				
				Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(tr701e.getIdSchedule().toString());
				agenda_SCLocal.setPeticion(peticionLocal);
				agenda_SCLocal.setEstado(new Integer(1));
				agenda_SCLocal.setPeti_numero(idPeticion);
				agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}else if(tipoOC.equals("esActuacionCCF")){
					try{

						Collection agendamientos = agenda_SCLocalHome.findByPetiNumero(idPeticion);
						for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
							Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
							if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
									|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
								/*mfmendez*/
								Agenda_scKey agendaScKey = (Agenda_scKey) agendaSCLocal.getPrimaryKey();
								String [] campos = agendaScKey.id_actuacion.split("-");
								/*FIN mfmendez*/
								if (formatter.format(agendaSCLocal.getFecha_mod()) != campos[1]){
									log.debug("El Codigo de actuacion de CCF  : " + agendaSCLocal.getPrimaryKey().toString());
									tr701e.setIdSchedule(agendaScKey.id_actuacion);
									tr701e.setScheduleDate(formatter.format(agendaSCLocal.getFecha_mod()));
									break;
								}
							}
						}
					}catch(FinderException e){
						log.debug("FinderException en esActuacionCCF(): "+e.toString());
					}

					tr701e.setTransactionType("MOD");
				}else{
					tr701e.setIdSchedule("AP"+idPeticion.toString()+"-"+formatter.format(dateAhora));
					tr701e.setScheduleDate(formatter.format(dateAhora));
				
				Agenda_scLocal agenda_SCLocal=agenda_SCLocalHome.create(tr701e.getIdSchedule().toString());
				agenda_SCLocal.setPeticion(peticionLocal);
				agenda_SCLocal.setEstado(new Integer(1));
				agenda_SCLocal.setPeti_numero(idPeticion);
				agenda_SCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}
				tr701e.setIdSystemOrigin("ATIEMPO");
				tr701e.setInterfaz("ACT_ALTA");
				tr701e.setMassiveBreakdowns(tr701EMassiveBreakdowns);
				tr701e.setNotes(tr701ENotes);
				tr701e.setProduct(tr701EProduct);
				//Fecha cuando es una actuacion nueva
				//tr701e.setScheduleDate(formatter.format(new Date()));

				//tr701e.setSeverity("");
				tr701e.setSource("ATIEMPO");
				
				if (listaPs.length()>240){
					listaPs = listaPs.substring(0, 240);
				}
					
				tr701e.setSummary(listaPs);//Todos los ps's
				log.debug("Se informa a Agenda que la red es de reuso (par dedicado) : " + tr701ETechnicalData.getParDedicated());
				tr701e.setTechnicalData(tr701ETechnicalData);
				//tr701e.setUrgency("");
				tr701e.setVersion("1.0");
				
				//TODO: Raúl Triviño: Adición de los campo de producto servicio
				Collection peticionFlujo = peticionLocal.getPeticion_flujo();
				Collection productsService = new ArrayList();
				
				Long ocAutoInstalacion=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(OPCO_AUTOINSTALACION));
				
				Collection psActividadCollection = peticionFlujoLocalHome.findByActividad(idPeticion,new Integer(ID_ACTIVIDAD_INSTALACION),new Integer(ID_ACTIVIDAD_DESINSTALACION));
				boolean hayAltBajMigrDEco=false;	
				
				int psCamara = 0;
				Long idPsCaracteristicaTemp = idPsCaracteristica;
				for (Iterator psActividadIterator=psActividadCollection.iterator(); psActividadIterator.hasNext();){
					Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal) psActividadIterator.next();
					boolean psConQuiebre = false;
					int famOpLocal = 0;
				
					Operacion_comercialKey opcoKey = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
					Collection productosCollection = productoServicioPeticionLocalHome.findByPetiNumeroPsYOpCo(idPeticion, peticionFlujoLocal.getPrse_id(), opcoKey.opco_id);
					for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
						Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
						idPsCaracteristica = idPsCaracteristicaTemp;
						
						//Se buscan sí el ps es de cámara; entonces se buscan el id_ps correcto, por que pueden venir varias cámaras
						psCamara1: for (int i = 0; i < id_ps.length; i++) {
							Long idPsCamara = new Long(id_ps[i]);
							log.debug("idPsCamara: "+idPsCamara+", productoLocal.getPsId:"+productoLocal.getPsId());
							if(idPsCamara.equals(productoLocal.getPsId())){
								if(caracteristicas!=null && caracteristicas.size()>psCamara){
									CaracteristicaPSLocal caracteristicaPS = (CaracteristicaPSLocal)caracteristicas.get(psCamara);
									idPsCaracteristica = caracteristicaPS.getPsId();
									log.debug("idPsCaracteristica: "+ idPsCaracteristica);
									log.debug("psCamara: "+ psCamara);
									psCamara++;
									break psCamara1;
								}
							}
						}
						String accion = "";
						famOpLocal = productoLocal.getFamiliaKey().faps_id.intValue();		
						Collection estadoPSCollection = productoLocal.getEstado_ps_peticion();
						if(estadoPSCollection != null && estadoPSCollection.size()>0){
							for (Iterator estadoPSIterator = estadoPSCollection.iterator(); estadoPSIterator.hasNext();){
								Estado_ps_peticionLocal estadoPSLocal = (Estado_ps_peticionLocal)estadoPSIterator.next();
								
								if (estadoPSLocal.getCod_estado_cierre() != null && estadoPSLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError){
									psConQuiebre = true;
									Familia_producto_servicioKey familia_producto_servicioKey =  productoLocal.getFamiliaKey();
									Long familiaPsId = familia_producto_servicioKey.faps_id;
									Operacion_comercialLocal operacion_comercialLocal = productoLocal.getOperacion_comercial();
									Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
									
									//DAVID: si vienen los ps's con quiebre, vemos si es OC=96 y ps de BA para setearlos en la tr701e.
//									REQ BA NAKED adicion familia PC naked
									if((familiaPsId.intValue()==ComunInterfaces.familiaPcBA || familiaPsId.intValue()==ComunInterfaces.familiaPcPsBANaked)&&operacion_comercialKey.opco_id.longValue() == ocAutoInstalacion.longValue()){
										TR701EProductService productService = new TR701EProductService();
										productService.setPsId(peticionFlujoLocal.getPrse_id().equals(idPs) ? idPsCaracteristica.toString() : peticionFlujoLocal.getPrse_id().toString());
										productService.setOperationComercial(OC_REVERSA_AUT_INST);
										productsService.add(productService);
									}
									
								}else{
//									psConQuiebre = false;
									//mfmendez
									TR701EProductService productService = new TR701EProductService();
									productService.setPsId(peticionFlujoLocal.getPrse_id().equals(idPs) ? idPsCaracteristica.toString() : peticionFlujoLocal.getPrse_id().toString());
									//productService.setPsId(peticionFlujoLocal.getPrse_id().toString());
									
									if( tipoOC.equals("esPGACS") ){
										productService.setOperationComercial(OC_PGACS);
									}else if(tipoOC.equals("esReversaAutoInst")){
										productService.setOperationComercial(OC_REVERSA_AUT_INST);
									}else{
										productService.setOperationComercial(opcoKey.opco_id.toString());
									}
//									REQ migracion deco dcardena 15/01/2015
									if(!yaSeValidoAltaBajaMigDeco){
										hayAltBajMigrDEco=validaBajAltaMigrDeco(peticionLocal.getProducto_servicio_peticion());
										yaSeValidoAltaBajaMigDeco=true;
									}
									
									if(hayAltBajMigrDEco){
										accion = accionDecoAdicional(productService.getOperationComercial(), famOpLocal);
										
										if(accion != null && famOpLocal==ComunInterfaces.familiaDecoHDTV&&accion.equals("D")){
											cantDecosDesinsHD++;
										}
										
										if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoPVRTV&&accion.equals("D")){
											
											cantDecosDesinsPVR++;
										}
										
										if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoTV&&accion.equals("D"))
										{
											cantDecosDesinsSTD++;
										}

									}
									productService.setAccion(accion);
									//fin REQ DECO
									
									if (puertosModificados){
										productsService.add(productService);	
//										REQ BA NAKED adicion familia PC naked
									}else if(famOpLocal != familiaPcBA  && famOpLocal != familiaPcPsBANaked){
											productsService.add(productService);	
									}
									//fin mfmendez
								}
							}
						}else{
//							//mfmendez
							TR701EProductService productService = new TR701EProductService();
							productService.setPsId(peticionFlujoLocal.getPrse_id().equals(idPs) ? idPsCaracteristica.toString() : peticionFlujoLocal.getPrse_id().toString());
							//productService.setPsId(peticionFlujoLocal.getPrse_id().toString());
							//productService.setOperationComercial(opcoKey.opco_id.toString());
							if( tipoOC.equals("esPGACS") ){
								productService.setOperationComercial(OC_PGACS);
							}else if(tipoOC.equals("esReversaAutoInst")){
								productService.setOperationComercial(OC_REVERSA_AUT_INST);
							}else{
								productService.setOperationComercial(opcoKey.opco_id.toString());
							}
							

							//REQ migracion deco dcardena 15/01/2015
							if(!yaSeValidoAltaBajaMigDeco){
								hayAltBajMigrDEco=validaBajAltaMigrDeco(peticionLocal.getProducto_servicio_peticion());
								yaSeValidoAltaBajaMigDeco=true;
							}
							
							if(hayAltBajMigrDEco){
								accion = accionDecoAdicional(productService.getOperationComercial(), famOpLocal);
								
								if(accion != null && famOpLocal==ComunInterfaces.familiaDecoHDTV&&accion.equals("D")){
									cantDecosDesinsHD++;
								}
								
								if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoPVRTV&&accion.equals("D")){
									
									cantDecosDesinsPVR++;
								}
								
								if(accion !=null && famOpLocal==ComunInterfaces.familiaDecoTV&&accion.equals("D"))
								{
									cantDecosDesinsSTD++;
								}
							
							}
							productService.setAccion(accion);
							//fin REQ DECO
							
							//REQ BA Naked 06/04/2015
							productService.setAccion(accionModemNaked(productService.getOperationComercial(),famOpLocal));
							//FIN BA Naked
							
							if (puertosModificados){
								productsService.add(productService);
//								REQ BA NAKED adicion familia PC naked
							}else if(famOpLocal != familiaPcBA && famOpLocal != familiaPcPsBANaked ){
									productsService.add(productService);	
							}
							//fin mfmendez
						}
					}
					
//					if (!psConQuiebre){
//						TR701EProductService productService = new TR701EProductService();
//						
//						//@idrincon req 3709
//						productService.setPsId(peticionFlujoLocal.getPrse_id().toString());
//						//boolean esAutoInstalacionSoloBA = peticionesDelegate.esAutoInstalacionSoloBA( idPeticion );
//						
//						if( esPgacs ){
//							productService.setOperationComercial("0");
//						}else{
//							productService.setOperationComercial(opcoKey.opco_id.toString());
//						}
//						//fin req 3709
//						if (puertosModificados){
//							productsService.add(productService);		
//						}else{
//							if(famOpLocal != familiaPcBA && famOpLocal != familiaBandaAncha){
//								productsService.add(productService);	
//							}
//						}	
//					}
					
				}
				//REQ migracion deco dcardena 20/01/2015
				
				if(cantDecosDesinsHD>0){
					int catidadHD =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoHDTV,cantDecosDesinsHD);	
					if (catidadHD < cantDecosDesinsHD)
					{	
							notaDescripcion=notaDescripcion+";Cantidad DECOs HC HD "+catidadHD+" Cantidad DECOs HD a Des-instalar "+(catidadHD - cantDecosDesinsHD)+" favor gestionar regularizacion.";
					
					}
				}
				 if(cantDecosDesinsPVR>0){
					int cantidadPVR =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoPVRTV,cantDecosDesinsPVR);	
					if (cantidadPVR < cantDecosDesinsPVR)
					{
						notaDescripcion=notaDescripcion+";Cantidad DECOs HC PVR "+cantidadPVR+" Cantidad DECOs PVR a Des-instalar "+(cantidadPVR - cantDecosDesinsPVR)+" favor gestionar regularizacion.";				
					}
				}
				if(cantDecosDesinsSTD>0){
					int cantidadSTD =cantidadDecosHC(idPeticion,ComunInterfaces.familiaDecoTV,cantDecosDesinsSTD);	
					if (cantidadSTD < cantDecosDesinsSTD)
					{
					notaDescripcion=notaDescripcion+";Cantidad DECOs HC STD "+cantidadSTD+" Cantidad DECOs STD a Des-instalar "+(cantidadSTD - cantDecosDesinsSTD)+" favor gestionar regularizacion.";
					}
					
				}	
				tr701ENotes.setNoteDescription(notaDescripcion);
				tr701ENotes.setNoteDetail("");
				tr701ENotes.setNoteType("ACT_COMENT");
				tr701e.setNotes(tr701ENotes);
				yaSeValidoAltaBajaMigDeco=false;
				//FIN REQ migracion deco dcardena 20/01/2015
				tr701e.setProductsService(productsService);
				
				
				/*for (Iterator peticionFlujoIterator=peticionFlujo.iterator(); peticionFlujoIterator.hasNext();){
					Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal) peticionFlujoIterator.next();
					
					if ((peticionFlujoLocal.getIdActividad() != null && peticionFlujoLocal.getIdActividad().intValue() == ID_ACTIVIDAD_INSTALACION)){
						TR701EProductService productService = new TR701EProductService();
						productService.setPsId(peticionFlujoLocal.getPrse_id().toString());
						
						Operacion_comercialKey opcoKey = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
						productService.setOperationComercial(opcoKey.opco_id.toString());
						
						productsService.add(productService);
					}
				}*/
				

				
				//End TODO
				
				//Raúl Triviño: 24-06-2011: Adición para almacenar los mensajes de agenda
				Mensaje_agenda_scLocal mensajeAgendaSCLocal = mensajeAgendaSCLocalHome.create(IdCorrelativo);
				mensajeAgendaSCLocal.setCod_actividad_generadora(codActividad);
				mensajeAgendaSCLocal.setPeti_numero(idPeticion);
				mensajeAgendaSCLocal.setId_agenda(tr701e.getIdSchedule());
				mensajeAgendaSCLocal.setTipo_mensaje(ComunInterfaces.TR701);
				
				Date dateRegistro=new Date();
				Timestamp timestampRegistro=new Timestamp(dateRegistro.getTime());
				mensajeAgendaSCLocal.setFecha_envio(formatterRegistro.format(timestampRegistro));
				
				CreaActuacionSCMQ creacionActuacionSCMQ = new CreaActuacionSCMQ();
				creacionActuacionSCMQ.enviarMensaje(tr701e);

			}
			 catch (FinderException e) {
				log.error("Error en la búsqueda de objeto en la petición en alta actuación de Agenda SC: ",e);
			}
			catch (ATiempoAppEx e) {
				log.error("Error de aplicación de atiempo en alta actuación de Agenda SC: ",e);
			}catch (NamingException e) {
				log.error("Error buscando objeto de BD alta actuación de Agenda SC: ",e);
			}
			catch (CreateException e) {
				log.error("Error creando objeto de BD en alta actuación de Agenda SC: ",e);
			}
			catch (Exception e){
				log.error("Error: RecursosBABean: creacionActuacionAgendaSC. ",e);
			}
			
		}
		//REQ Ba Naked dcardena 06/04/2015
		public String accionModemNaked(String opc,int famOpLocal) throws FinderException, NamingException{
			
			Operacion_comercialLocalHome  operacion_comercialLocalHome = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			Operacion_comercialKey Operacion_comercialKey = new Operacion_comercialKey(new Long (opc));
			Operacion_comercialLocal operacion_comercialLocal = operacion_comercialLocalHome.findByPrimaryKey(Operacion_comercialKey);
			String accion = "";
			if((famOpLocal==ComunInterfaces.familiaBandaAnchaNaked)|| (famOpLocal==ComunInterfaces.familiaPcPsBANaked)||(famOpLocal==ComunInterfaces.familiaBandaAncha)||(famOpLocal==ComunInterfaces.familiaPcBA))
			{
				if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)){
			
				accion="A";
				
				}else if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)){ 
				accion="B";

				}else{
				accion=null;
				}
			}else{
				accion=null;
			}
			return accion;
		}
		//
		
		//REQ migracion deco dcardena 15/01/2015
		private String accionDecoAdicional(String opc,int famOpLocal) throws FinderException, NamingException{
			
			Operacion_comercialLocalHome  operacion_comercialLocalHome = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			Operacion_comercialKey Operacion_comercialKey = new Operacion_comercialKey(new Long (opc));
			Operacion_comercialLocal operacion_comercialLocal = operacion_comercialLocalHome.findByPrimaryKey(Operacion_comercialKey);
			String accion = "";
			if((famOpLocal==ComunInterfaces.familiaDecoHDTV)|| (famOpLocal==ComunInterfaces.familiaDecoPVRTV)||(famOpLocal==ComunInterfaces.familiaDecoTV))
			{
				if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)||operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
			
				accion="I";
				
				}else if(operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)|| operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)){ 
				accion="D";
				
				
				}else{
				accion=null;
				}
			}else{
				accion=null;
			}
			return accion;
		}
		
		
		
		public int cantidadDecosHC (Long petiNum,int famOpLocal,int cantDesisntalar) throws NamingException, FinderException{
			
			Deco_tarjetaLocalHome  deco_tarjetaLocalHome = (Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			
			Collection decosInstalados = deco_tarjetaLocalHome.findByPeticion(petiNum);
			String tipodeco="";
			int cantidadDecos=0;
			if(famOpLocal==ComunInterfaces.familiaDecoHDTV){
				tipodeco="HD";
			}
			else if(famOpLocal==ComunInterfaces.familiaDecoPVRTV){
				
				tipodeco="PVR";
			}else if(famOpLocal==ComunInterfaces.familiaDecoTV)
			{
				tipodeco="STD";
			}
			
			if(decosInstalados != null && decosInstalados.size()>0){
				for (Iterator decosInstaladosIter = decosInstalados.iterator(); decosInstaladosIter.hasNext();){
					Deco_tarjetaLocal deco_tarjetaLocal = (Deco_tarjetaLocal)decosInstaladosIter.next();
					
					if (deco_tarjetaLocal.getDeco_reference().equals(tipodeco)){
						cantidadDecos++;
					}
				}
			}
			
			log.debug("El deco de tipo "+tipodeco + " tiene la siguiente cantidad de decos instalados "+cantidadDecos+ ", se esta mandando a desisntalar la siguiente cantidad de decos "+cantDesisntalar);
			return cantidadDecos;
//			if(cantDesisntalar>cantidadDecos){	
//				log.debug("el deco de tipo "+tipodeco+" esta descompensado se envia nota");
//				cantDecosInst = cantidadDecos;
//				cantDecosDesins=cantDesisntalar;
//				return true;
//			}else{
//				log.debug("el deco de tipo "+tipodeco+" no esta descompensado");
//			return false;
//
//			}
		}
		
		public boolean validaBajAltaMigrDeco(Collection productosCollection){
			log.debug("Entra a validar si hay alta con migracion de deco o baja con migracion de deco");
			boolean hayAlta= false;
			boolean hayBaja= false;
			boolean hayAltaMigracion = false;
			boolean hayBajaMigracion = false;
			int contador=0;
			log.debug("hay "+productosCollection.size()+" Ps");
			
			for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
				Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
				if(productoLocal.getOperacion_comercial().getOpco_tras()!= null && 
						productoLocal.getOperacion_comercial().getOpco_tras().equals(ComunInterfaces.opCoTras_Traslado)){
					log.debug("Contiene operacion de Traslado no se realiza validacion");
					return false;
				}else{
					
					if(hayDeco(productoLocal.getFamiliaKey().faps_id)){
						if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)){
							log.debug("Hay alta Deco");
							hayAlta=true;
								
						}else if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)){ 
							log.debug("Hay Baja Deco");
							hayBaja=true;
		
						}else if (productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd)){
							log.debug("Hay BajaMigracion Deco");
							hayBajaMigracion=true;
							
						}else if(productoLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd)){
							log.debug("Hay AltaMigracion Deco");
							hayAltaMigracion=true;
						}
					}
						contador++;		
				}
			}	
				
			if(contador==productosCollection.size()&&((hayAltaMigracion&&hayBajaMigracion))){
				log.debug("Hay entre los PS migracion de deco");
				return true;
				
			}else{
				log.debug("No hay entre los PS migracion de deco");
				return false;
			}
			
		}
		//FIN REQ Migracion Deco 
		
		public boolean hayDeco(Long familia){
			log.debug("se valida si hay deco "+familia);
				if(familia.intValue()==ComunInterfaces.familiaDecoTV
						||familia.intValue()==ComunInterfaces.familiaDecoPVRTV
						||familia.intValue()==ComunInterfaces.familiaDecoHDTV){
					log.debug("El ps pertenece a una familia de tipo Deco");
					return true;
				}
				return false;
		}
		
		/**
		 * @param tr701eEquipment
		 * @param camara
		 */
		public void setValoresCamara(TR701EEquipment tr701eEquipment, CamaraLocal camara) throws Exception{
			Ps_Tipo_EqLocalHome psTipoEquipoLocalHome = (Ps_Tipo_EqLocalHome)HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			Elemento_agenda_scLocalHome elementoAgendSCLocalHome = (Elemento_agenda_scLocalHome)HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
	    	CamaraKey key = (CamaraKey) camara.getPrimaryKey();
			tr701eEquipment.setSerialNumber(key.cameraSerial);
			tr701eEquipment.setBrand(camara.getCameraBrand());
			tr701eEquipment.setModel(camara.getCameraModel());
		
			try{
				Ps_Tipo_EqKey psTipoEquipoKey = new Ps_Tipo_EqKey(new Integer(camara.getPsId().toString()),new Integer(camara.getTipoEquipo().toString()));
				Ps_Tipo_EqLocal psTipoEquipoLocal = psTipoEquipoLocalHome.findByPrimaryKey(psTipoEquipoKey);
	
				Elemento_agenda_scKey elementoAgendaSCKey = new Elemento_agenda_scKey(psTipoEquipoLocal.getId_elemento_agenda());
	
				if (elementoAgendaSCKey.id_correlativo != null){
					Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendSCLocalHome.findByPrimaryKey(elementoAgendaSCKey);
					tr701eEquipment.setType(elementoAgendaSCLocal.getDesc_equipo());
				}else{
					log.debug("No existe una cámara de agenda asociado al ps:"+camara.getPsId()+" y tipo de elemento:"+camara.getTipoElemento());
				tr701eEquipment.setType("");
				}
			}catch(Exception ex){
				log.debug("Ocurrió un problema detectando el tipo de cámara en la tr-701-e, se setea vacio:"+ex);
				tr701eEquipment.setType("");
			}

			tr701eEquipment.setCassId("");
			tr701eEquipment.setCardSerialNumber("");
			tr701eEquipment.setInventoryType(camara.getTipoInventario());
			
			tr701eEquipment.setPartType("");

			tr701eEquipment.setId(ComunInterfaces.ID_OTROS);
			
			if(camara.getFecContSap() != null)
				tr701eEquipment.setPostingDateSAP(camara.getFecContSap());
			else
				tr701eEquipment.setPostingDateSAP("");
			
			if(camara.getClaseMovSap() != null)
				tr701eEquipment.setMoveTypeSAP(camara.getClaseMovSap());
			else
				tr701eEquipment.setMoveTypeSAP("");
			
			if(camara.getPosDocSap()!=null)
				tr701eEquipment.setMaterialCodeSAP(camara.getPosDocSap().toString());
			else
				tr701eEquipment.setMaterialCodeSAP("");
				
			
			if(camara.getNumMaterialSap() != null)
				tr701eEquipment.setMaterialSAP(camara.getNumMaterialSap());
			else
				tr701eEquipment.setMaterialSAP("");
				
			if(camara.getCentroSap() != null)
				tr701eEquipment.setPlantSAP(camara.getCentroSap());
			else
				tr701eEquipment.setPlantSAP("");
			
			if(camara.getAlmacenSap() != null)
				tr701eEquipment.setStorageSAP(camara.getAlmacenSap());
			else
				tr701eEquipment.setStorageSAP("");
			
			if(camara.getCodLoteSap() != null)
				tr701eEquipment.setBatchCodeSAP(camara.getCodLoteSap());
			else
				tr701eEquipment.setBatchCodeSAP("");
			
			if(camara.getUndMedidaSap() != null)
				tr701eEquipment.setMeasurementUnitSAP(camara.getUndMedidaSap());
			else
				tr701eEquipment.setMeasurementUnitSAP("");
			
			if(camara.getCentrCostSap() != null)
				tr701eEquipment.setCostCenterSAP(camara.getCentrCostSap());
			else
				tr701eEquipment.setCostCenterSAP("");
			
			if(camara.getAreaFuncSap() != null)
				tr701eEquipment.setFuncAreaLongSAP(camara.getAreaFuncSap());
			else
				tr701eEquipment.setFuncAreaLongSAP("");
			
			if(camara.getElementPepSap() != null)
				tr701eEquipment.setPepElementSAP(camara.getElementPepSap());
			else
				tr701eEquipment.setPepElementSAP("");
			
			if(camara.getFlagMapSap() != null)
				tr701eEquipment.setFlagMatSAP(camara.getFlagMapSap());
			else
				tr701eEquipment.setFlagMatSAP("");
		}

		/*RQ.8595 - mfmendez*/
		/**
		 * Metodo para completar los datos de SAP para un Deco de tipo TR018EEquipment
		 * @param deco_tar_inf_sapLocalHome, localHome de la tabla DECO_TARJETA_INFO_SAP
		 * @param equipment, el equipo que ya tiene seteados algunos datos
		 * @param idDeco, id del Deco que se debe buscar en base de datos
		 * @param idPeticion, id de la peticion para buscar el equipo correspondiente
		 * @return el equipo que entro con los datos de SAP seteados
		 */
		public TR701EEquipment setDatosSAPDecoTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, TR701EEquipment equipment, String idDeco, String idCard, Long idPeticion){
			Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
		
			Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
			
			/*RQ.8595 - mfmendez*/
			// Datos del Deco
			try{			
				keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(idDeco, idPeticion);
				infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
				
				if(infoSAPTmp.getFec_cont_sap() != null)
					equipment.setPostingDateSAP(infoSAPTmp.getFec_cont_sap());
				else
					equipment.setPostingDateSAP("");
				
				if(infoSAPTmp.getClase_mov_sap() != null)
					equipment.setMoveTypeSAP(infoSAPTmp.getClase_mov_sap());
				else
					equipment.setMoveTypeSAP("");
				
				equipment.setMaterialCodeSAP(Integer.toString(infoSAPTmp.getPos_doc_sap()));
				
				if(infoSAPTmp.getNum_material_sap() != null)
					equipment.setMaterialSAP(infoSAPTmp.getNum_material_sap());
				else
					equipment.setMaterialSAP("");
				
				if(infoSAPTmp.getCentro_sap() != null)
					equipment.setPlantSAP(infoSAPTmp.getCentro_sap());
				else
					equipment.setPlantSAP("");
				
				if(infoSAPTmp.getAlmacen_sap() != null)
					equipment.setStorageSAP(infoSAPTmp.getAlmacen_sap());
				else
					equipment.setStorageSAP("");
				
				if(infoSAPTmp.getCod_lote_sap() != null)
					equipment.setBatchCodeSAP(infoSAPTmp.getCod_lote_sap());
				else
					equipment.setBatchCodeSAP("");
				
				if(infoSAPTmp.getUnd_medida_sap() != null)
					equipment.setMeasurementUnitSAP(infoSAPTmp.getUnd_medida_sap());
				else
					equipment.setMeasurementUnitSAP("");
				
				if(infoSAPTmp.getCentr_cost_sap() != null)
					equipment.setCostCenterSAP(infoSAPTmp.getCentr_cost_sap());
				else
					equipment.setCostCenterSAP("");
				
				if(infoSAPTmp.getArea_func_sap() != null)
					equipment.setFuncAreaLongSAP(infoSAPTmp.getArea_func_sap());
				else
					equipment.setFuncAreaLongSAP("");
				
				if(infoSAPTmp.getElement_pep_sap() != null)
					equipment.setPepElementSAP(infoSAPTmp.getElement_pep_sap());
				else
					equipment.setPepElementSAP("");
				
				if(infoSAPTmp.getFlag_mat_sap() != null)
					equipment.setFlagMatSAP(infoSAPTmp.getFlag_mat_sap());
				else
					equipment.setFlagMatSAP("");
				
			} catch (FinderException e) {
				log.debug("No se encontraron Decos para deco con id: "+idDeco+". Y id de peticion: "+idPeticion);
				equipment.setPostingDateSAP("");
				equipment.setMoveTypeSAP("");
				equipment.setMaterialCodeSAP("");
				equipment.setMaterialSAP("");
				equipment.setPlantSAP("");
				equipment.setStorageSAP("");
				equipment.setBatchCodeSAP("");
				equipment.setMeasurementUnitSAP("");
				equipment.setCostCenterSAP("");
				equipment.setFuncAreaLongSAP("");
				equipment.setPepElementSAP("");
				equipment.setFlagMatSAP("");
			} catch (Exception e) {
				log.error("RecursosTVServiciosBean: setDatosSAPEquipo: Se presento Error seteando los datos de SAP para un Deco. "+e);
			}
			
			// Datos de la tarjeta
			try{
				keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(idCard, idPeticion);
				infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
				
				if(infoSAPCard.getFec_cont_sap() != null)
					equipment.setCardPostingDateSAP(infoSAPCard.getFec_cont_sap());
				else
					equipment.setCardPostingDateSAP("");
				
				if(infoSAPCard.getClase_mov_sap() != null)
					equipment.setCardMoveTypeSAP(infoSAPCard.getClase_mov_sap());
				else
					equipment.setCardMoveTypeSAP("");
				
				equipment.setCardMaterialCodeSAP(Integer.toString(infoSAPCard.getPos_doc_sap()));
				
				if(infoSAPCard.getNum_material_sap() != null)
					equipment.setCardMaterialSAP(infoSAPCard.getNum_material_sap());
				else
					equipment.setCardMaterialSAP("");
				
				if(infoSAPCard.getCentro_sap() != null)
					equipment.setCardPlantSAP(infoSAPCard.getCentro_sap());
				else
					equipment.setCardPlantSAP("");
				
				if(infoSAPCard.getAlmacen_sap() != null)
					equipment.setCardStorageSAP(infoSAPCard.getAlmacen_sap());
				else
					equipment.setCardStorageSAP("");
				
				if(infoSAPCard.getCod_lote_sap() != null)
					equipment.setCardBatchCodeSAP(infoSAPCard.getCod_lote_sap());
				else
					equipment.setCardBatchCodeSAP("");
				
				if(infoSAPCard.getUnd_medida_sap() != null)
					equipment.setCardMeasurementUnitSAP(infoSAPCard.getUnd_medida_sap());
				else
					equipment.setCardMeasurementUnitSAP("");
				
				if(infoSAPCard.getCentr_cost_sap() != null)
					equipment.setCardCostCenterSAP(infoSAPCard.getCentr_cost_sap());
				else
					equipment.setCardCostCenterSAP("");
				
				if(infoSAPCard.getArea_func_sap() != null)
					equipment.setCardFuncAreaLongSAP(infoSAPCard.getArea_func_sap());
				else
					equipment.setCardFuncAreaLongSAP("");
				
				if(infoSAPCard.getElement_pep_sap() != null)
					equipment.setCardPepElementSAP(infoSAPCard.getElement_pep_sap());
				else
					equipment.setCardPepElementSAP("");
				
				if(infoSAPCard.getFlag_mat_sap() != null)
					equipment.setCardFlagMatSAP(infoSAPCard.getFlag_mat_sap());
				else
					equipment.setCardFlagMatSAP("");
				
			}catch (FinderException e) {
				log.debug("No se encontraron Tarjetas para Card con id: "+idCard+". Y id de peticion: "+idPeticion);
				equipment.setCardPostingDateSAP("");
				equipment.setCardMoveTypeSAP("");
				equipment.setCardMaterialCodeSAP("");
				equipment.setCardMaterialSAP("");
				equipment.setCardPlantSAP("");
				equipment.setCardStorageSAP("");
				equipment.setCardBatchCodeSAP("");
				equipment.setCardMeasurementUnitSAP("");
				equipment.setCardCostCenterSAP("");
				equipment.setCardFuncAreaLongSAP("");
				equipment.setCardPepElementSAP("");
				equipment.setCardFlagMatSAP("");
			} catch (Exception e) {
				log.error("RecursosTVServiciosBean: setDatosSAPTarjeta: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
			}			
			/*FIN - RQ.8595 - mfmendez*/
			
			return equipment;
		}		
		/*FIN - RQ.8595 - mfmendez*/

		public void recepcionCreacionActuacionAgendaSC(TR701S tr701s){
			
			String codActividad=ComunInterfaces.ACT_INSTALAR;
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			BackendExecution bExecution = null;
			long idActividad = ComunInterfaces.idActividadInstalar;
			
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr701s, log);
				//bExecution.setNumPetAtiempo(new Long(tr701s.getId()));
				bExecution.setIdMensajeOp(tr701s.getId());
				bExecution.startOperation();
				
				Agenda_scLocalHome  agenda_scLocalHome= (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				
				//Raúl Triviño: Req. Req_2011_00007730_AgendamientoFront - Ajuste para que la respuesta sea compatible con cualquier tipo de actividad
				Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				
				Mensaje_agenda_scKey mensajeAgendaSCKey = new Mensaje_agenda_scKey(new Long(tr701s.getId().toString()));
				Mensaje_agenda_scLocal mensajeAgendaSCLocal = mensajeAgendaSCLocalHome.findByPrimaryKey(mensajeAgendaSCKey);
							
				if (mensajeAgendaSCLocal.getCod_actividad_generadora() != null){
					codActividad = mensajeAgendaSCLocal.getCod_actividad_generadora();
				}
				
				ActividadLocal actividadLocal  = actividadLocalHome.findByCodigoActividadIdAplicacion(codActividad, idAplicacion);
				ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();
				idActividad = actividadKey.act_id.longValue();
				//End Todo
				
				Agenda_scKey agenda_scKey=new Agenda_scKey(tr701s.getIdSchedule());
				Agenda_scLocal agenda_scLocal=agenda_scLocalHome.findByPrimaryKey(agenda_scKey);
				
				Long peti_numero=agenda_scLocal.getPeti_numero();
				
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(new Long(idActividad),peti_numero,idAplicacion);
				
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
				
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peti_numero, codActividad,idCorrelativo,null);
			//ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peti_numero, codActividad);
			log.debug("El codigo de error es: "+tr701s.getErrorCode());
				if ( tr701s.getErrorCode().equals("0")){
					//No hay error	
					
					PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
					boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(peti_numero);
					
					if (codActividad.equals(ACT_INSTALAR) || codActividad.equals(ACT_DES_INSTALAR)){
						if(esAutoInstalacionSoloBA)
							actDto.setObservacion("Se envía la petición directamente a Control Cruzada BA por respuesta desde AgendaSC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						else
							actDto.setObservacion("Se envía la petición directamente a Control instalación por respuesta desde AgendaSC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						actividadEJB.terminar(actDto);
					}else{
						actDto.setObservacion("Se creó una nueva actuación ID: "+tr701s.getIdSchedule()+" . "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						actividadEJB.grabarSinTerminar(actDto);
					}

			}else {
					//Hay error
					if (codActividad.equals(ACT_INSTALAR)|| codActividad.equals(ACT_DES_INSTALAR)){
						PeticionLocal peticionLocal = agenda_scLocal.getPeticion();
						
						ErrorLegadoLocal errorLegado = getErrorLegado(tr701s.getErrorCode(),"TR701S");
						String plataforma = VpistbbaConfig.getVariable("IDPGI");
						String bandeja = "PGI";
						if(errorLegado != null){
							if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
								actDto.setObservacion("Error: "
						 				+ errorLegado.getCodigoError() + "Descripcion: "+tr701s.getErrorMessage());
								actividadEJB.terminar(actDto);
								return;
							}
							plataforma = errorLegado.getPlataforma(); 
							bandeja	= getNombreBandeja(plataforma); 
						}
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						actDto.setObservacion("Error en respuesta desde Agenda SC, se deriva a "+bandeja+": "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						
											
						insertarCausalesCnaPeticion(peticionLocal, codActividad, new Long(742), actDto.getIdActividadFlujo());
						
						actividadEJB.terminar(actDto);
					}else{
						actDto.setObservacion("Error en respuesta desde Agenda SC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
						actividadEJB.grabarSinTerminar(actDto);
					}
					
					mensajeAgendaSCLocal.setError(tr701s.getErrorCode());
				String error =tr701s.getErrorMessage();
				if(error!=null && error.length()>24){
					error=error.substring(0,23);
				}
				mensajeAgendaSCLocal.setError(error);
					agenda_scLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
				}

			} catch (NamingException e) {
				bExecution.setErrorOp(true);
				log.error(" Creacion de Local Home Nulls",e);

			}  catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.warn("FinderException",e);

			} catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.debug("Exception",e);
			}	
			finally{  
				bExecution.endAll();
			}			
			
		}
		
		public void recepcionCreacionActuacionAgendaSCEnPGACS(TR701S tr701s){
			
			String codActividad = ComunInterfaces.ACT_AUTO_INST;
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			BackendExecution bExecution = null;
			
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr701s, log);
				//bExecution.setNumPetAtiempo(new Long(tr701s.getId()));
				bExecution.setIdMensajeOp(tr701s.getId());
				bExecution.startOperation();
				
				Agenda_scLocalHome  agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome = (BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				
				Agenda_scKey agenda_scKey = new Agenda_scKey(tr701s.getIdSchedule());
				Agenda_scLocal agenda_scLocal = agenda_scLocalHome.findByPrimaryKey(agenda_scKey);
				
				Long peti_numero=agenda_scLocal.getPeti_numero();
				
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				Long idActAutoInstalacion=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(ID_ACT_AUTO_INSTALACION));
				
				BintegradaLocal bintegradaLocal = bintegradaLocalHome.findByIdApliNroPeticionIdActiVisibleDos(idActAutoInstalacion,peti_numero,idAplicacion);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
			
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio = idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo = idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin = idCorrelativo.indexOf("&");
					if(idFin != -1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo = idCorrelativo.replaceAll("%2B","+");
				idCorrelativo = idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peti_numero, codActividad,idCorrelativo,null);
				
//				if ( tr701s.getErrorCode().equals("0")){
//					//No hay error	
//					actDto.setObservacion("Se envía la petición directamente a Control instalación por respuesta desde AgendaSC: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
//					actividadEJB.terminar(actDto);
//				}
				if (!tr701s.getErrorCode().equals("0")) {
					//Hay error
					PeticionLocal peticionLocal = agenda_scLocal.getPeticion();
					
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("Error en respuesta desde Agenda SC, se deriva a PGI: "+tr701s.getErrorCode()+" . "+tr701s.getErrorMessage()+"." + tr701s.getErrorMessage());
					
										
					insertarCausalesCnaPeticion(peticionLocal, codActividad, new Long(742), actDto.getIdActividadFlujo());
					
					actividadEJB.terminar(actDto);
					agenda_scLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
				}

			} catch (NamingException e) {
				bExecution.setErrorOp(true);
				log.error(" Creacion de Local Home Nulls",e);

			}  catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.warn("FinderException",e);

			} catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.debug("Exception",e);
			}	
			finally{  
				bExecution.endAll();
			}			
			
		}
		
		public void recepcionActivarDecosTarjetasAgendaSC(TR708S tr708s) throws ATiempoAppEx {
			BackendExecution bExecution = null;
			
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				
				Agenda_scLocal agendaSCLocal = null;
				boolean esPostventa = true;
			
				try{
					Agenda_scKey agendaSCKey = new Agenda_scKey(tr708s.getIdSchedule());
					agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				}catch(FinderException ex){
					log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
					String idPeticionAux = tr708s.getIdSchedule().substring(2,tr708s.getIdSchedule().indexOf("-"));
					
					Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
					for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
						Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
						
						if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
							agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
						}
					}
					
					PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
					PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
										
					agendaSCLocal = agendaSCLocalHome.create(tr708s.getIdSchedule());
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
					agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
					agendaSCLocal.setPeticion(peticionLocal);
					agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}
														
				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
				
				//Verificacion si la peticion es una postventa
				/*Collection psPeticion = peticionLocal.getProducto_servicio_peticion();
				for (Iterator psPeticionIterator = psPeticion.iterator(); psPeticionIterator.hasNext();){
					Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
					
					Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
					Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
					
					if (familiaPSKey != null && 
							((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()))
							|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
						esPostventa = false;
						break;
					}
				}*/
				
				esPostventa = false;
				
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
				
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				String codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
				

				if ((agendaSCLocal.getEstado().intValue() == ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ACTUACION_REAGENDADA)
						&& tr708s.getEquipments()!=null){
					Collection decoTarjetaOriginal = peticionLocal.getDeco_tarjeta();
					ArrayList decoTarjetaInstall = new ArrayList();
					ArrayList decoTarjetaOld = new ArrayList();
					
					//Aqui se valida que los decos que traen el mensaje hallan para bajar es decir en la tabla hay 3 
					//y el mensaje trae tres se baja el que no trae el mensaje 
					for (Iterator decoTarjetaOldIterator = decoTarjetaOriginal.iterator(); decoTarjetaOldIterator.hasNext();){
						boolean estaInstalado = false;
						Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
						Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
						
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
						decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						decoTarjetaDTO.setAccion(decoTarjetaLocal.getAccion());
						decoTarjetaDTO.setEstado(decoTarjetaLocal.getEstado());
						
						decoTarjetaOld.add(decoTarjetaDTO);
							
						Collection equipos = tr708s.getEquipments();
						for (Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
							TR708SEquipment equipo = (TR708SEquipment)equiposIterator.next();
								
							if (!decoTarjetaKey.id_deco.equals(equipo.getDecoCassId()) && !decoTarjetaKey.id_tarjeta.equals(equipo.getCardCode())){
								estaInstalado = false;
							}else{
								estaInstalado = true;
								break;
							}
						}
						
						if (!estaInstalado && !esPostventa && decoTarjetaLocal.getEstado().intValue() != estadoParNoOk && 
								decoTarjetaLocal.getAccion().intValue() != ComunInterfaces.accionParEliminar){
							decoTarjetaLocal.setEstado(new Integer(ComunInterfaces.estadoOk));	//Se deja en estado par desactivado.				
							decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
							
							decoTarjetaInstall.add(decoTarjetaDTO);
						}
					}
						
					Collection decoTarjetaList = peticionLocal.getDeco_tarjeta();
					Collection decoTarjetaEliminarList = new ArrayList();
//					Aqui se elimina el deco que se dio de baja en la primera parte
					for (Iterator decoTarjetaInstallIterator = decoTarjetaList.iterator(); decoTarjetaInstallIterator.hasNext();){
						Deco_tarjetaLocal decoTarjetaDTO = (Deco_tarjetaLocal)decoTarjetaInstallIterator.next();
						
						if (decoTarjetaDTO.getEstado() != null && decoTarjetaDTO.getEstado().intValue() == estadoParNoOk && !esPostventa){
							Deco_tarjetaKey decoTarjetaKeyEliminar = (Deco_tarjetaKey)decoTarjetaDTO.getPrimaryKey();
							Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKeyEliminar);					
							decoTarjetaEliminarList.add(decoTarjetaKeyEliminar);
						}
					}
					
					for (Iterator decoTarjetaEliminarIterator = decoTarjetaEliminarList.iterator(); decoTarjetaEliminarIterator.hasNext();){
						Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaEliminarIterator.next();
						
						Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
						/*
						 *No se deben eliminar porque si hay un error en HC ya no se podrian volver a enviar a 
						 *eliminar, en cambio se setea la accion en eliminar para identificar que se queria hacer
						 *con los decos en caso de una falla 
						 */
//						decoTarjetaAuxLocal.remove();
						decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
						
						
					}
					
					//Aqui se valida que el mensaje traiga nuevos decos, se da de alta el nuevo
					Collection equipos = tr708s.getEquipments();
					Collection ps=peticionLocal.getProducto_servicio_peticion();
					
					for(Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
						TR708SEquipment equipo = (TR708SEquipment)equiposIterator.next();
						boolean estaSinInstalar = true;
						boolean estaNoOK = false;
						for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
							DecoTarDTO decoTarjetaLocal = (DecoTarDTO)decoTarjetaOldIterator.next();
							//Se verifica si un equipo que se envia en la tr708s ya existia previamente y si tiene un estado de activacion fallido
							//En ese caso se vuelve a enviar a activar
							if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
									&& decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.estadoParNoOk){
								estaNoOK = true;
							}
							
							if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
									&& decoTarjetaLocal.getEstado().intValue() != ComunInterfaces.estadoParNoOk){
								estaSinInstalar = false;
								break;
							}
						}
						
						if (estaSinInstalar){
							Deco_tarjetaLocal decoTarjetaLocal = null;
							if (estaNoOK){
								Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionKey);
								decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
							}else{
								decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionLocal);
							}						
							////														
							decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParActivar));
							decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
							decoTarjetaLocal.setOpco_id(new Long(ComunInterfaces.accionParActivar));
							decoTarjetaLocal.setEstado(new Integer(ComunInterfaces.accionParActivar));
							
							if (equipo.getDecoType().indexOf(desHCDecoSTD) != -1){
								decoTarjetaLocal.setDeco_reference(desHCDecoSTD);
							}else if (equipo.getDecoType().indexOf(desHCDecoPVR) != -1){
								decoTarjetaLocal.setDeco_reference(desHCDecoPVR);
							}else if (equipo.getDecoType().indexOf(desHCDecoHDTV) != -1){
								decoTarjetaLocal.setDeco_reference(desHCDecoHDTV);
							}
							
							decoTarjetaLocal.setDeco_marca(equipo.getDecoBrand());
							decoTarjetaLocal.setSerial_deco(equipo.getDecoSerialNumber());
							decoTarjetaLocal.setSerial_tarjeta(equipo.getCardCode());
							decoTarjetaLocal.setCodigo_deco(equipo.getDecoCode());
							
							DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardSerialNumber());
							decoTarjetaDTO.setAccion(new Integer(ComunInterfaces.accionParActivar));
							decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
							decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
							decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
							decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
							
							decoTarjetaInstall.add(decoTarjetaDTO);
						}else{
							try{
								Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionKey);
								Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
								
								if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.accionParEliminar){
									decoTarjetaLocal.setAccion(new Integer(ComunInterfaces.accionParActivar));
									decoTarjetaLocal.setCodigo_error(new Integer(0));
									decoTarjetaLocal.setMensaje_error("");
									decoTarjetaLocal.setOpco_id(new Long(ComunInterfaces.accionParActivar));
									decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
									
									
									DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardSerialNumber());
									decoTarjetaDTO.setAccion(new Integer(ComunInterfaces.accionParActivar));
									decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
									decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
									decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
									decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
									
									decoTarjetaInstall.add(decoTarjetaDTO);
								}	
							}catch(FinderException ex){
								
							}
							
						}
					}
					
					if (decoTarjetaInstall != null && decoTarjetaInstall.size() > 0){
						boolean haEnviadoMensaje = false;
						Collection mensajesEstadoTV = peticionLocal.getMensaje_estado_tv();
						
						for(Iterator mensajeEstadoTVIterator = mensajesEstadoTV.iterator(); mensajeEstadoTVIterator.hasNext();){
							Mensaje_estado_tvLocal mensajeEstadoTVLocal = (Mensaje_estado_tvLocal) mensajeEstadoTVIterator.next();
							
							if (mensajeEstadoTVLocal.getDesc_error() != null && mensajeEstadoTVLocal.getDesc_error().indexOf("@tr708") != -1){
								haEnviadoMensaje = true;
								break;
							}
						}
						
						String idAgendaSC = tr708s.getIdSchedule()+"@"+tr708s.getId()+"@tr708";
						
						RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
						recursosTVDelegate.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaInstall, haEnviadoMensaje, idAgendaSC);
					}else{
						log.debug("No se envía el mensaje: "+ tr708s.getId() +" a HC porque los equipos recibidos ya están instalados");
						String observaciones = actDto.getObservacion(); 
						if(observaciones != null && !observaciones.equals("")){
							actDto.setObservacion(observaciones + " No se envía el mensaje: "+ tr708s.getId() +" a HC porque los equipos recibidos ya están instalados");
						}else{
							actDto.setObservacion("No se envía el mensaje: "+ tr708s.getId() +" a HC porque los equipos recibidos ya están instalados");
						}
												
						RecursosBAInterfaces recursosBA = new RecursosBADelegate();
						recursosBA.enviaActivarDecosTarjetasAgendaSC(tr708s.getIdSchedule(), tr708s.getId());
						
						actividadEJB.grabarSinTerminar(actDto);
					}
				}else{
					log.debug("El mensaje: "+tr708s.getId()+" no se puede procesar porque la actuación: "+tr708s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae equipos asociados");
					actDto.setObservacion("El mensaje: "+tr708s.getId()+" no se puede procesar porque la actuación: "+tr708s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae equipos asociados");
					
					RecursosBAInterfaces recursosBA = new RecursosBADelegate();
					recursosBA.enviaActivarDecosTarjetasAgendaSC(tr708s.getIdSchedule(), tr708s.getId());
					
					actividadEJB.grabarSinTerminar(actDto);
				}
			}catch(NamingException ex){
				log.debug("Error instanciado el bean en la recepción de activar decos Tarjetas: " + ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error buscando elementos en la recepción de activar decos Tarjetas: " + ex);
				ex.printStackTrace();
//			}catch(RemoveException ex){
//				log.debug("Error eliminando elementos en la recepción de activar decos Tarjetas: " + ex);
//				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + ex);
				ex.printStackTrace();
			}catch(ATiempoAppEx ex){
				log.debug("Error en el llamado de los métodos de la petición: " + ex);
				ex.printStackTrace();
			}catch(TnProcesoExcepcion ex){
				log.debug("Error en el llamado de los métodos de la petición: " + ex);
				ex.printStackTrace();
			}
		}
		
		public ArrayList diferenciaDecosMensaje(PeticionLocal peticionLocal, ArrayList equipos){
			ArrayList decoTarjetaInstall = new ArrayList();
			
			try{
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
				
				Collection decoTarjetaOld = peticionLocal.getDeco_tarjeta();
				
				
				//Aqui se valida que los decos que traen el mensaje hallan para bajar es decir en la tabla hay 3 
				//y el mensaje trae tres se baja el que no trae el mensaje 
				for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
					boolean estaInstalado = false;
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
					
					for (Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
						TR708SEquipment equipo = (TR708SEquipment)equiposIterator.next();
						
						if (!decoTarjetaKey.id_deco.equals(equipo.getDecoCassId()) && !decoTarjetaKey.id_tarjeta.equals(equipo.getCardCode())){
							estaInstalado = false;
						}else{
							estaInstalado = true;
							break;
						}
					}
					
					if (!estaInstalado){
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
						decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}
				}

				//Aqui se elimina el deco que se dio de baja en la primera parte
				for (Iterator decoTarjetaInstallIterator = decoTarjetaInstall.iterator(); decoTarjetaInstallIterator.hasNext();){
					DecoTarDTO decoTarjetaDTO = (DecoTarDTO)decoTarjetaInstallIterator.next();
					
					if (decoTarjetaDTO.getAccion().intValue() == operacionParDesactivar){
						
						Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(decoTarjetaDTO.getTarjeta(), 
								decoTarjetaDTO.getDeco(), (PeticionKey)peticionLocal.getPrimaryKey());
						Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
						decoTarjetaAuxLocal.remove();
					}
				}
				
				//Aqui se valida que el mensaje traiga nuevos decos, se da de alta el nuevo
				Collection ps=peticionLocal.getProducto_servicio_peticion();
				
				for(Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
					TR708SEquipment equipo = (TR708SEquipment)equiposIterator.next();
					boolean estaSinInstalar = true;
					
					for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
						Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
						Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
						
						if (decoTarjetaKey.id_deco.equals(equipo.getDecoCassId()) && decoTarjetaKey.id_tarjeta.equals(equipo.getCardCode())){
							estaSinInstalar = false;
							break;
						}
					}
					
					if (estaSinInstalar){
						Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getCardCode(), equipo.getDecoCassId(), peticionLocal);
						
						decoTarjetaLocal.setAccion(new Integer(accionParActivar));
						decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
						
						decoTarjetaLocal.setOpco_id(new Long(accionParActivar));
						
						if (equipo.getDecoType().indexOf(desHCDecoSTD) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoSTD);
						}else if (equipo.getDecoType().indexOf(desHCDecoPVR) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoPVR);
						}else if (equipo.getDecoType().indexOf(desHCDecoHDTV) != -1){
							decoTarjetaLocal.setDeco_reference(desHCDecoHDTV);
						}
						
						decoTarjetaLocal.setDeco_marca(equipo.getDecoBrand());
						decoTarjetaLocal.setSerial_deco(equipo.getDecoSerialNumber());
						decoTarjetaLocal.setSerial_tarjeta(equipo.getCardSerialNumber());
						decoTarjetaLocal.setCodigo_deco(equipo.getDecoCode());
						
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardCode());
						decoTarjetaDTO.setAccion(new Integer(accionParActivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}
				}	
			}catch(NamingException ex){
				log.debug("Error llamando la instancia en la obtención de la diferencia de decos: "+ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error buscando registros en la obtención de la diferencia de decos: "+ex);
				ex.printStackTrace();
			}catch(RemoveException ex){
				log.debug("Error eliminando registros en la obtención de la diferencia de decos: "+ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("Error creando registros en la obtención de la diferencia de decos: "+ex);
				ex.printStackTrace();
			}
			
			
			return decoTarjetaInstall;
		}
		
		/**
		 * Obtiene la mascara actual del cliente, en caso de tener ip fijas retorna una direccion
		 * red, ie 255.255.255.248 o vacio  
		 * @param peticion
		 * @return mascara
		 * @throws ATiempoAppEx
		 */
		private String obtenerMascaraActualCliente(String peticion){
			String mascara = "";
			try {
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(peticion)));
				Recursos_baLocal recursos_baLocal;
				if(peticionLocal.getRecursos_ba().iterator().hasNext()){
					recursos_baLocal = (Recursos_baLocal) peticionLocal.getRecursos_ba().iterator().next();
					mascara = recursos_baLocal.getMasc_actual();
				}				
			} catch (NumberFormatException e) {
				log.error("Error al buscar Mascara del cliente. Tipo de dato incorrecto",e);				
			} catch (FinderException e) {
				log.error("Error al buscar Mascara del cliente. No se encontraron registros RecursosBA pet "+peticion,e);				
			}
			return mascara;
		}
		
		public void enviaActivarDecosTarjetasAgendaSC(String idActuacion, String idMensajePeticion) throws ATiempoAppEx{
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				
				ArrayList equipos = new ArrayList();
				boolean tieneErrores = false;
				TR708E tr708e = new TR708E();
							
				tr708e.setIdSourceSystem(sistemaAtiempo);
				tr708e.setIdSchedule(idActuacion);
				
				Agenda_scKey agendaSCKey = new Agenda_scKey(idActuacion);
				Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				Collection decos = peticionLocal.getDeco_tarjeta();
				ArrayList decosEliminar = new ArrayList();
				
				for (Iterator decosIterator = decos.iterator(); decosIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
					
					
					if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == estadoParNoOk
							&& decoTarjetaLocal.getCodigo_error() != null && decoTarjetaLocal.getCodigo_error().intValue() != ComunInterfaces.ERROR_OK_FALLIDA_OK){
						TR708EEquipment tr708Equipment = new TR708EEquipment();
						String mensajeError=decoTarjetaLocal.getMensaje_error();
						if(mensajeError!=null & mensajeError.length()>63){
							mensajeError=mensajeError.substring(0,63);
						}
						// Envio descripcion del error que se pierde al momento de borrar los decos
						tr708Equipment.setDecoCode(decoTarjetaLocal.getCodigo_deco());
						tr708Equipment.setDecoSerialNumber(decoTarjetaLocal.getSerial_deco());
						tr708Equipment.setCardCode(decoTarjetaLocal.getSerial_tarjeta());
						tr708Equipment.setCardSerialNumber(decoTarjetaKey.id_tarjeta);
						tr708Equipment.setDescripcionError(mensajeError);
						equipos.add(tr708Equipment);
						tieneErrores = true;
						if(decoTarjetaLocal.getEstado().intValue()==ComunInterfaces.estadoParNoOk &&
								(decoTarjetaLocal.getAccion().intValue()==ComunInterfaces.accionParActivar	
									||	decoTarjetaLocal.getAccion().intValue()==ComunInterfaces.accionParEliminar)){
							
							Deco_tarjetaLocalHome dtlc= (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
							 decosEliminar.add(decoTarjetaLocal);
							
						}
					}				
				}
				
				for (Iterator iter = decosEliminar.iterator(); iter.hasNext();) {
					Deco_tarjetaLocal dtl = (Deco_tarjetaLocal) iter.next();
					try { 
					log.debug("Deco a eliminar "+dtl.getSerial_deco()+" tarjeta "+dtl.getSerial_tarjeta());
					 dtl.remove();
			        
					}catch (Exception e) {
						log.error("Error al eliminar el decoTarjeta "+dtl.getPrimaryKey(),e);
					}
				}
					
				if (tieneErrores){
					tr708e.setResponse("ERROR");
					tr708e.setError("1");
					tr708e.setErrorMessage("La petición presentó un error en el proceso");
				}else{
					tr708e.setResponse("OK");	
					tr708e.setError("0");
					tr708e.setErrorMessage("");
				}
				
				tr708e.setEquipments(equipos);
				
				tr708e.setId(idMensajePeticion);
				tr708e.setDestination(sistemaAgendaSC);
				tr708e.setSource(sistemaAtiempo);
				tr708e.setInterfaz("ACT_RES_ACTIVACION");
				tr708e.setVersion("1.0");
					
				ActivarRecursosSCMQ activarRecursosSCMQ = new ActivarRecursosSCMQ();
				activarRecursosSCMQ.enviarMensaje(tr708e);
				
				log.debug("Se ha enviado con exito la tr-708-e, quedo en espera de recibir un cierre de actuación....");
			}catch(NamingException ex){
				log.debug("Error instanciando el bean en el envío de activar decos tarjetas:" + ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error buscando elementos en el envío de activar decos Tarjetas: " + ex);
				ex.printStackTrace();
			}
		}
		
		public boolean esAgendaSC(Long atisRequestNumber) throws ATiempoAppEx{
			boolean esAgendaSC = false; 
			Long codLocalidad = new Long(0);
			
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Localidad_agenda_scLocalHome localidadAgendaSCLocalHome = (Localidad_agenda_scLocalHome) HomeFactory.getHome(Localidad_agenda_scLocalHome.JNDI_NAME);
				Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);

				Peticion_atisKey peticionAtisKey = new Peticion_atisKey(atisRequestNumber);
				Peticion_atisLocal peticionAtisLocal = peticionAtisLocalHome.findByPrimaryKey(peticionAtisKey);
				Collection peticiones = peticionAtisLocal.getPeticion();
				
				for(Iterator peticionesIterator = peticiones.iterator(); peticionesIterator.hasNext();){
					Localidad_agenda_scKey localidadAgendaSCKey = null;
					PeticionLocal peticionLocal = (PeticionLocal) peticionesIterator.next();
					LocalidadLocal localidadLocal= peticionLocal.getFk_01_localidad();
					if(localidadLocal.getLocalidad_toa().intValue() == LOCALIDAD_TOA)
						return true;
					
					Localidad_agenda_scLocal localidadAgendaSCLocal = localidadLocal.getLocalidad_agenda_sc();
					localidadAgendaSCKey = (Localidad_agenda_scKey)localidadAgendaSCLocal.getPrimaryKey();	
					
					if (localidadAgendaSCKey.cod_loc != null || localidadAgendaSCKey.cod_loc.length()>0){
						esAgendaSC = true;
					}
				}
								
			}catch(NamingException ex){
				log.debug("Error obteniendo la instancia del bean en la configuración de servicios TV: "+ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("La localidad no hace parte de las estipuladas para agenda SC");
				esAgendaSC = false;
			}catch(Exception ex){
				log.debug("La localidad no hace parte de las estipuladas para agenda SC");
				esAgendaSC = false;
			}
			
			return esAgendaSC;
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionCierreActuacion(co.com.telefonica.atiempo.interfaces.atiempo.TR711S)
		 */
		public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx {
			try{
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				String idPeticion = tr711s.getIdSchedule().substring(2,tr711s.getIdSchedule().indexOf("-"));
				
				HashMap estadosHomologados = new HashMap();
				Estado_homologacionLocalHome estHomLocalHome = (Estado_homologacionLocalHome)HomeFactory.getHome(Estado_homologacionLocalHome.JNDI_NAME);
				PeticionesDelegate peticiones = new PeticionesDelegate();
				String codigoApp = peticiones.recuperarParametroFromPropertiesBD("EST_AGENDSC");
				Collection estados = estHomLocalHome.findByCodApp(codigoApp);
				for (Iterator iter = estados.iterator(); iter.hasNext();) {
					Estado_homologacionLocal element = (Estado_homologacionLocal) iter.next();
					Estado_homologacionKey key = (Estado_homologacionKey)element.getPrimaryKey();
					estadosHomologados.put(key.cod_estado, element.getCod_homologado());
				}
				
				ArrayList listaEquipos = new ArrayList();
				ArrayList listaCamaras = new ArrayList();
				ArrayList listaDecos = new ArrayList();
				ArrayList listaModems = new ArrayList();
				ArrayList listaFranqueoOK = new ArrayList();
				ArrayList listaEquiposNoSerializados = new ArrayList();
				boolean esEquipo = false;
				boolean esCamara = false;
				boolean esDeco = false;
				boolean esModem = false;
				boolean franqueoOK = false;
				boolean cierroActividad = true;
				boolean esPostventa = true;
				boolean tieneEquipoValido = false;
				boolean yaSeGuardoModem = false;
				boolean envioTr701EPGC = true;
				
				//Obtención de los tipos de equipo que se tendrán en cuanta para el envio de mensajes
				String materialesEquipos = VpistbbaConfig.getVariable("MATERIALES_EQUIPOS");
				String arrayMaterialesEquipos[] = materialesEquipos.split(",");
				
				String materialesDecos = VpistbbaConfig.getVariable("MATERIALES_DECOS");
				String arrayMaterialesDecos[] = materialesDecos.split(",");
				
				String materialesModems = VpistbbaConfig.getVariable("MATERIALES_MODEMS");
				String arrayMaterialesModems[] = materialesModems.split(",");
				
				String materialesCamara = VpistbbaConfig.getVariable("MATERIALES_CAMARA");
				log.debug("MATERIALES_CAMARA"+materialesCamara);
				String arrayMaterialesCamara[] = materialesCamara.split(",");
				
				Agenda_scLocal agendaSCLocal = null;
				
				String strObservaciones = "";
				//Obtención de la información basica del agendameinto
				try{
					String strQuiebres = "";
					
					if( tr711s.getBreaks()!= null && tr711s.getBreaks().getBreakPairs() != null && tr711s.getBreaks().getBreakPairs().size() > 0 ){
						strQuiebres = this.recuperarQuiebresAgendaSc(tr711s.getBreaks().getBreakPairs());
						strObservaciones = this.recuperarObservacionesQuiebresAgendaSc(tr711s.getBreaks().getBreakPairs());
					}
					Agenda_scKey agendaSCKey = new Agenda_scKey(tr711s.getIdSchedule());
					agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
					//@idrincon  req 4990 gestion quiebres
					agendaSCLocal.setNombre_contratista(tr711s.getTechnician().getTechnicianName()+" "+tr711s.getTechnician().getTechnicianLastname());
					agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
					agendaSCLocal.setCod_franqueo(tr711s.getPostageCode());
					agendaSCLocal.setQuiebre( strQuiebres );
					//fin modificacion
				}catch(FinderException ex){
					log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
					String idPeticionAux = tr711s.getIdSchedule().substring(2,tr711s.getIdSchedule().indexOf("-"));
					
					Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
					for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
						Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
						
						if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
							agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
						}
					}
					
					PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
					PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
					
					
					agendaSCLocal = agendaSCLocalHome.create(tr711s.getIdSchedule());
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
					agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
					agendaSCLocal.setPeticion(peticionLocal);
					agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}
				
				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
				//@idrincon 
				Collection productoSrvicioPeticionCollection = peticionLocal.getProducto_servicio_peticion();
				//fin modificacion
					
				//Implementación del llamado de la actividad donde me encuentro 
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
				
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				String codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				
				Collection estado_ps_peticion = null;
				
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
				
				//Verificación si viene el mensaje con código de franqueo exitoso
				Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
				
				
				if (tr711s.getItAnswer() != null && tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
					//Proceso para agregar un traslado
					log.debug("Voy a ingresar los siguientes datos a la peticion: Area de traspaso:"+tr711s.getItComplement()+" y diagnóstico:"+tr711s.getItClosing());
					Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByDescripcion(tr711s.getItClosing()) ;
					if(catalogCausa != null){
						Catalogo_causalKey catalogo_causalKey= (Catalogo_causalKey) catalogCausa.getPrimaryKey();
						insertarCausalesCnaPeticion(peticionLocal, actDto.getCodigoActividad(),catalogo_causalKey.cod_causal, actDto.getIdActividadFlujo());
						
					}
					
		
				}
				
				//@idrincon req 4990 date 01/01/2011
				String codBandeja = null;
				//fin modificacion
				try{
					Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr711s.getPostageCode());
					Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
					//@idrincon req 4990 date 01/01/2011
					codBandeja = codFranqueoOKLocal.getBandeja();
					//fin modificacion
					franqueoOK = true;
				}catch(FinderException ex){
					log.debug("El codigo de franqueo: "+tr711s.getPostageCode()+" no es considerado como exitoso, se valida si tiene marca y si es así se envía a configurar inventarios");
					franqueoOK = false;
				}
				
				if (agendaSCLocal.getEstado() != null &&
						(agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA)){
					//Ejecuto el proceso solo si viene el código de franqueo OK o si viene el codigo de franqueo mal pero la petición tiene marca
					if (franqueoOK){
						//Proceso de organización de cada uno de los materiales 
						
						//@idrincon req 4990 date 01/01/2011
						if(tr711s.getBreaks()!= null && tr711s.getBreaks().getBreakPairs() != null && tr711s.getBreaks().getBreakPairs().size() > 0){
							this.setQuiebrePcAgendaSc( tr711s.getBreaks().getBreakPairs(), peticionKey.peti_numero, productoSrvicioPeticionCollection, actDto.getCodigoActividad() );
						}
						//fin modificacion
						
						Collection materiales = tr711s.getMaterials();
						if (materiales != null){
							granFor: for(Iterator materialesIterator = materiales.iterator(); materialesIterator.hasNext();){
								esEquipo = false;
								esDeco = false;
								esModem = false;
								esCamara = false;
								TR711SMaterials material = (TR711SMaterials) materialesIterator.next();
								
								if (!esDeco && !esModem){
									for (int i=0; i<arrayMaterialesEquipos.length;i++){
										if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesEquipos[i]) != -1){							
											listaEquipos.add(material);
											esEquipo = true;
											log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un equipo");
											continue granFor;
										}
									}
								}
								
								if (!esEquipo && !esModem){
									for (int i=0; i<arrayMaterialesDecos.length;i++){
										if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesDecos[i])  != -1){
											listaDecos.add(material);
											esDeco = true;
											log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un deco");
											continue granFor;
										}
									}
								}
								
								if (!esEquipo && !esDeco){
									for (int i=0; i<arrayMaterialesModems.length;i++){
										if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesModems[i]) != -1){
											listaModems.add(material);
											esModem = true;
											log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es un modem");
											continue granFor;
										}
									}
								}
								log.debug("esEquipo: "+esEquipo+" ,esModem: "+esModem+", esDeco: "+esDeco+", material.getTypeEquipment: "+material.getTypeEquipment());
								if(!esEquipo && !esModem && !esDeco){
									for (int i=0; i<arrayMaterialesCamara.length;i++){
										if (material.getTypeEquipment() != null && material.getTypeEquipment().indexOf(arrayMaterialesCamara[i]) != -1){
											listaCamaras.add(material);
											esCamara = true;
											log.debug("El agendamiento:"+tr711s.getIdSchedule()+" viene con el material: "+material.getEquipmentSerialNumber()+" y es una cámara");
											continue granFor;
										}
									}
								}
								
								if (!esEquipo && !esDeco && !esModem){
									listaEquiposNoSerializados.add(material);
								}
							}
						}
						
						PeticionesDelegate pDelegate = new PeticionesDelegate();
						String deltaTicaId=pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.DELTA_TICA_ID);
						boolean hacerDelta = false;
						if(deltaTicaId != null){
							String[] arrayDeltaTicaId = deltaTicaId.trim().split(",");
							String ticaId = peticionLocal.getTica_id();
							for(int i = 0; i < arrayDeltaTicaId.length; i++){
								if(ticaId.equalsIgnoreCase(arrayDeltaTicaId[i])){
									hacerDelta = true;
									break;
								}
							}
						}
						if(tr711s.getItAnswer() == null || !tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
						//Si la operacion comercial es autoinstalacion no se aplica delta de modem
						boolean esOCAutoInstalacion = esAutoinstalacion(agendaSCLocal);
				        if(!esOCAutoInstalacion){
//				        	Se envía mensaje tr-007 para actualizar inventario de modems
							ModemLocalHome modemLocalhome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
							Collection modemsCollection = modemLocalhome.findPeticion(peticionKey.peti_numero);
							if (listaModems != null && listaModems.size()>0){
								log.debug("Voy a configurar y registrar modems para el agendamiento:"+tr711s.getIdSchedule());
								//Adición de los elementos actuales
								for(int i = 0; listaModems.size() > i; i++){
									TR711SMaterials modem = (TR711SMaterials)listaModems.get(i);
									Short accion = new Short("0");
									if(estadosHomologados.containsKey(modem.getActionType())){
										accion = new Short(estadosHomologados.get(modem.getActionType()).toString());									
									}
									
									//Valido si el modem ya lo guarde previamente
									for (Iterator modemsIter = modemsCollection.iterator(); modemsIter.hasNext();){
										ModemLocal modemLocal = (ModemLocal) modemsIter.next();
										ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
										
										if (modemKey.serial.equals(modem.getEquipmentSerialNumber())){
											/*RQ.8595 - mfmendez*/
											modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
											modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
											if(modem.getMaterialCodeSAP() != null){
												modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
											}else{
												modemLocal.setPos_doc_sap(0);
											}
											modemLocal.setNum_material_sap(modem.getMaterialSAP());
											modemLocal.setCentro_sap(modem.getPlantSAP());
											modemLocal.setAlmacen_sap(modem.getStorageSAP());
											modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
											modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
											modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
											modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
											modemLocal.setElement_pep_sap(modem.getPepElementSAP());
											modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
											modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
											/*FIN - RQ.8595 - mfmendez*/
											
											yaSeGuardoModem = true;
											break;
										}
									}
									
									//Si no lo guarde entonces lo adiciono
									//Raúl Triviño: Cambio para mandar el mensje de configuración d modems en el cierre de actuación
									if (!yaSeGuardoModem){
										//								Asignación de variables del modem
										ModemVpiDTO modemDTO = new ModemVpiDTO();
										modemDTO.setPeti_numero(agendaSCLocal.getPeti_numero());
										modemDTO.setMarca(modem.getBrandEquipment());
										modemDTO.setSerial(modem.getEquipmentSerialNumber());
										
										//Se adiciona este cambio para tener en cuenta el codigo de material y el modelo del modem
										modemDTO.setModelo(modem.getModelEquipment());
										modemDTO.setCodMaterial(modem.getCodeMaterial());
										
										if (modem.getTypeEquipment().equals("MODEMSTD2P") || modem.getTypeEquipment().equals("MODEMSTD4P"))
											modemDTO.setTipo(new Integer(ComunInterfaces.identificadorConvencional).shortValue());
										else if (modem.getTypeEquipment().equals("MODEMWIFI")){
											modemDTO.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
										}else{
											modemDTO.setTipo(new Integer(0).shortValue());
										}
										
										InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
										InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(agendaSCLocal.getPeti_numero());
										Long telefono = null;
										
										if (informacionTecnicaDTO.LineaNueva.getTelefono()!= null && informacionTecnicaDTO.LineaNueva.getTelefono().length()>0){
											modemDTO.setTelefono(new Long(informacionTecnicaDTO.LineaNueva.getTelefono()));
										}else{
											modemDTO.setTelefono(new Long("0"));
										}
										
										modemDTO.setAccion(new Integer(ComunInterfaces.accionModemNoAction).shortValue());
										
										
										/*RQ.8595 - mfmendez*/
										modemDTO.setPostingDateSAP(modem.getPostingDateSAP());
										modemDTO.setMoveTypeSAP(modem.getMoveTypeSAP());
										modemDTO.setMaterialCodeSAP(modem.getMaterialCodeSAP());
										modemDTO.setMaterialSAP(modem.getMaterialSAP());
										modemDTO.setPlantSAP(modem.getPlantSAP());
										modemDTO.setStorageSAP(modem.getStorageSAP());
										modemDTO.setBatchCodeSAP(modem.getBatchCodeSAP());
										modemDTO.setMeasurementUnitSAP(modem.getMeasurementUnitSAP());
										modemDTO.setCostCenterSAP(modem.getCostCenterSAP());
										modemDTO.setFuncAreaLongSAP(modem.getFuncAreaLongSAP());
										modemDTO.setPepElementSAP(modem.getPepElementSAP());
										modemDTO.setFlagMatSAP(modem.getFlagMatSAP());
										/*FIN - RQ.8595 - mfmendez*/
										
										//Envío el mensaje de autoinstalación
										//RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
										//rbaInterfaces.llamadoConfModemAutoInstalacion(modemDTO, actDto.getCodigoActividad(), tr711s.getId()+"#"+tr711s.getIdSchedule(),false, true);
										//REQ BA NAKED 
										//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
										ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
										aCSServicioDelegate.enviarAutoConfiguracion(modemDTO,actDto.getCodigoActividad(), tr711s.getId()+"#"+tr711s.getIdSchedule());
										//FIN REQ NAKED
									}
									//End
								}
							}else{//Se eliminan los modems previamente configurados
								log.debug("No vienen Modems, voy a eliminar modems para el agendamiento:"+tr711s.getIdSchedule());
								for (Iterator modemsIter = modemsCollection.iterator(); modemsIter.hasNext();){
									ModemLocal modemLocal = (ModemLocal) modemsIter.next();
									ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
									ModemVpiDTO modemDTO = new ModemVpiDTO();
									modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemLiberar).shortValue()));
									modemDTO.setAccion(new Integer(ComunInterfaces.accionModemLiberar).shortValue());
									modemDTO.setPeti_numero(peticionKey.peti_numero);
									modemDTO.setMarca(modemLocal.getModem_marca());
									modemDTO.setModelo(modemLocal.getModelo());
									modemDTO.setSerial(modemKey.serial);
									this.llamadoConfModemAutoInstalacion(modemDTO, actDto.getCodigoActividad(), tr711s.getId()+"#"+tr711s.getIdSchedule(),false, true);

								}
							}
				        }
						
						
						//Se envía mensaje tr-017 para actualizar inventario de decos/tarjeta
						if (listaDecos != null && listaDecos.size()>0){
							log.debug("Voy a configurar y registrar decos para el agendamiento:"+tr711s.getIdSchedule());
							//Gestion de Quiebres y Novedades Peticiones en Vuelo LFMM
							RecursosTVDelegate recursosTVDelegate = new RecursosTVDelegate();
							if (!this.esPostventa(peticionLocal)){
								recursosTVDelegate.marcarNovedadQuiebrePeticionesVueloAgendaSC(agendaSCLocal.getPeti_numero(),listaDecos);
							}
							this.agendamientoDecosTR711(listaDecos, peticionLocal, tr711s, actDto, actividadEJB,true ,codBandeja);
							//cierroActividad = false;
						}else if(hacerDelta){//se eliminan todos los decos
							log.debug("No vienen decos, Voy a eliminar los decos para el agendamiento:"+tr711s.getIdSchedule());
							Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
							Collection decoTarjeta = peticionLocal.getDeco_tarjeta();
							ArrayList decoTarjetaEliminar = new ArrayList(); 
							for(Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator.hasNext();){
								Deco_tarjetaLocal decoTarjetaAuxLocal = (Deco_tarjetaLocal)decoTarjetaIterator.next();
								Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaAuxLocal.getPrimaryKey();
								/*RQ.8595 - mfmendez*/
								Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
								Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
								
								/*RQ.8595 - mfmendez*/
								// Se borran datos de SAP del deco
								try{			
									Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, peticionKey.peti_numero);
									Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
									infoSAPDeco.remove();
								} catch (FinderException e) {
									log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+peticionKey.peti_numero);
								} catch (Exception e) {
									log.error("RecursosBABean: Se presento Error borrando los datos de SAP para un Deco. "+e);
								}
								// se borran datos de SAP de la tarjeta
								try{			
									Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
									Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
									infoSAPCard.remove();
								} catch (FinderException e) {
									log.debug("No se encontraron Tarjetas para deco con id: "+decoTarjetaKey.id_tarjeta+". Y id de peticion: "+peticionKey.peti_numero);
								} catch (Exception e) {
									log.error("RecursosBABean: Se presento Error borrando los datos de SAP para una Tarjeta. "+e);
								}
								decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
								decoTarjetaAuxLocal.setEstado(new Integer(ComunInterfaces.estadoParOk));
								decoTarjetaAuxLocal.setOpco_id(new Long(operacionParDesactivar));
								
								DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
								decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
								decoTarjetaDTO.setDeco_reference(decoTarjetaAuxLocal.getDeco_reference());
								decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
								decoTarjetaDTO.setDecoSerial(decoTarjetaAuxLocal.getSerial_deco());
								decoTarjetaDTO.setDecoBrand(decoTarjetaAuxLocal.getDeco_marca());		
								decoTarjetaDTO.setDecoType(decoTarjetaAuxLocal.getDeco_reference());
								/*RQ.8595 - mfmendez*/
								decoTarjetaDTO = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
								decoTarjetaDTO = this.setDatosSAPDeco(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_deco, peticionKey.peti_numero);
								/*FIN - RQ.8595 - mfmendez*/
								decoTarjetaEliminar.add(decoTarjetaDTO);
							}
							//Se envia mensaje tr-017-e para desactivacion de decos
							if(decoTarjetaEliminar != null && !decoTarjetaEliminar.isEmpty()){
								String idAgendaSC = tr711s.getIdSchedule()+"@"+tr711s.getId()+"@tr711"+"@"+true;
								RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
								recursosTVDelegate.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaEliminar, true, idAgendaSC);
								//cierroActividad = false;
							}
						}
						
						if(listaCamaras != null && listaCamaras.size()>0){
							log.debug("Voy a configurar y registrar cámaras para el agendamiento:"+tr711s.getIdSchedule());
							CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
							Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
							for (int i = 0; i < listaCamaras.size(); i++) {
								TR711SMaterials materialCamara = (TR711SMaterials)listaCamaras.get(i);
								CamaraKey camaraKey = new CamaraKey(new Long(idPeticion),materialCamara.getEquipmentSerialNumber());
								try{
									CamaraLocal camaraLocal = camaraLocalHome.findByPrimaryKey(camaraKey);
									if(ComunInterfaces.CAMARA_ACTIVA.equals(camaraLocal.getCameraState()) || ComunInterfaces.CAMARA_CAMBIO_PLAN_ACTIVO.equals(camaraLocal.getCameraState())){
										
										Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
										Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
										Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSC.findByDescEquipo(materialCamara.getTypeEquipment());
										Collection tipoEqElementoCollection = tipoEqElementoLocalHome.findByIdElemento(elementoAgendaSCLocal.getId_elemento_atiempo());
										for (Iterator iter = tipoEqElementoCollection.iterator(); iter.hasNext();) {
											Tipo_Eq_ElementoLocal tipoEqElementoLocal = (Tipo_Eq_ElementoLocal)  iter.next();
											Tipo_Eq_ElementoKey tipoEqElementoKey = (Tipo_Eq_ElementoKey)tipoEqElementoLocal.getPrimaryKey();
											camaraLocal.setTipoEquipo(tipoEqElementoLocal.getId_tipo_eq().toString());
											camaraLocal.setTipoElemento(Long.valueOf(tipoEqElementoLocal.getId_elemento().toString()));
											
											Collection psTipoEquipoCollection = psTipoEqLocalHome.findByIdTipoEquipo(tipoEqElementoKey.id_tipo_eq);
											Collection productoServicioPeticion = peticionLocal.getProducto_servicio_peticion();
											
											forPS: for(Iterator psTipoEquipoIterator = psTipoEquipoCollection.iterator(); psTipoEquipoIterator.hasNext();){
												Ps_Tipo_EqLocal psTipoEquipoLocal = (Ps_Tipo_EqLocal) psTipoEquipoIterator.next();
												
												Ps_Tipo_EqKey psTipoKey = (Ps_Tipo_EqKey)psTipoEquipoLocal.getPrimaryKey();
												
												for (Iterator productoServicioPeticionIterator = productoServicioPeticion.iterator(); productoServicioPeticionIterator.hasNext();){
													Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIterator.next();
													Short accion = new Short("0");
													
													log.debug("materialCamara.getActionType: "+materialCamara.getActionType());
													if(materialCamara.getActionType()!=null){
														 accion = new Short(estadosHomologados.get(materialCamara.getActionType()).toString());
													}
													camaraLocal.setAccion(accion);
													
													log.debug("psTipoKey.ps_id.intValue: "+psTipoKey.ps_id.intValue()+", productoServicioPeticionLocal.getPsId: "+productoServicioPeticionLocal.getPsId().intValue());
													if (psTipoKey.ps_id.intValue() == productoServicioPeticionLocal.getPsId().intValue()){
	
														Long psId = new Long (psTipoKey.ps_id.longValue());
														String tipoInventario = "";
														Grpe_PsLocalHome grpePsLocalHome = null;
														Grpe_PsLocal grpePsLocal = null;
														try{
															grpePsLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
															grpePsLocal = grpePsLocalHome.findGrupoByPS(new Long(psTipoKey.ps_id.longValue()));													
														}catch(FinderException ex){
															log.debug("El PS "+psTipoKey.ps_id+" no se encuentra configurado en la tabla GRPE_PS en donde se asocia al tipo de inventario. " + ex);
															ex.printStackTrace();
														}finally{
															
															if(grpePsLocal != null && grpePsLocal.getGrpe_id() != null)
																tipoInventario = grpePsLocal.getGrpe_id().toString();
															else
																tipoInventario = "8";
														}
														camaraLocal.setPsId(psId);
														camaraLocal.setTipoInventario(tipoInventario);
	
														if(materialCamara.getBrandEquipment() != null)
															camaraLocal.setMarca(materialCamara.getBrandEquipment());
														
														if(materialCamara.getModelEquipment() != null)
															camaraLocal.setModelo(materialCamara.getModelEquipment());
														
														if(materialCamara.getExternalCodeEquipment() != null){
															camaraLocal.setNumCelular(materialCamara.getExternalCodeEquipment());
														}
														camaraLocal.setFecContSap(materialCamara.getPostingDateSAP());
														camaraLocal.setClaseMovSap(materialCamara.getMoveTypeSAP());
														if(materialCamara.getMaterialCodeSAP() != null){
															camaraLocal.setPosDocSap(Integer.valueOf(materialCamara.getMaterialCodeSAP()));
														}else{
															camaraLocal.setPosDocSap(Integer.valueOf("0"));
														}
														camaraLocal.setNumMaterialSap(materialCamara.getMaterialSAP());
														camaraLocal.setCentroSap(materialCamara.getPlantSAP());
														camaraLocal.setAlmacenSap(materialCamara.getStorageSAP());
														camaraLocal.setCodLoteSap(materialCamara.getBatchCodeSAP());
														camaraLocal.setUndMedidaSap(materialCamara.getMeasurementUnitSAP());
														camaraLocal.setCentrCostSap(materialCamara.getCostCenterSAP());
														camaraLocal.setAreaFuncSap(materialCamara.getFuncAreaLongSAP());
														camaraLocal.setElementPepSap(materialCamara.getPepElementSAP());
														camaraLocal.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
														camaraLocal.setFlagMapSap(materialCamara.getFlagMatSAP());
														break forPS;
													}
												}
											}

										}
										
										
									}else{
										log.error("La cámara con serial " +  materialCamara.getEquipmentSerialNumber() + " no se encuentra activa.");
									}
								}catch(FinderException ex){
									log.error("La cámara con serial " +  materialCamara.getEquipmentSerialNumber() + " no fue encontrada en la tabla ATIEMPO.CAMARA");
								}
							}
						}
						
						
						if (listaEquipos != null && listaEquipos.size()>0){
							log.debug("Voy a configurar y registrar equipos para el agendamiento:"+tr711s.getIdSchedule());							
							Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
							Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
							Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
							Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
							
							for(int i = 0; listaEquipos.size() > i; i++){
								TR711SMaterials equipo = (TR711SMaterials)listaEquipos.get(i);
								
								Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSC.findByDescEquipo(equipo.getTypeEquipment());
								Collection tipoEqElementoCollection = tipoEqElementoLocalHome.findByIdElemento(elementoAgendaSCLocal.getId_elemento_atiempo());
								
								boolean tienePSTipoEqElem = false;
								
								for(Iterator tipoEqElementoIterator = tipoEqElementoCollection.iterator(); tipoEqElementoIterator.hasNext();){
									Tipo_Eq_ElementoLocal tipoEqElementoLocal = (Tipo_Eq_ElementoLocal)  tipoEqElementoIterator.next();
									Tipo_Eq_ElementoKey tipoEqElementoKey = (Tipo_Eq_ElementoKey)tipoEqElementoLocal.getPrimaryKey();
									Integer elementoAtiempo = elementoAgendaSCLocal.getId_elemento_atiempo();
									
									Collection psTipoEquipoCollection = psTipoEqLocalHome.findByIdTipoEquipo(tipoEqElementoKey.id_tipo_eq);
									Collection productoServicioPeticion = peticionLocal.getProducto_servicio_peticion();
									
									forPS: for(Iterator psTipoEquipoIterator = psTipoEquipoCollection.iterator(); psTipoEquipoIterator.hasNext();){
										Ps_Tipo_EqLocal psTipoEquipoLocal = (Ps_Tipo_EqLocal) psTipoEquipoIterator.next();
										
										Ps_Tipo_EqKey psTipoKey = (Ps_Tipo_EqKey)psTipoEquipoLocal.getPrimaryKey();
										
										for (Iterator productoServicioPeticionIterator = productoServicioPeticion.iterator(); productoServicioPeticionIterator.hasNext();){
											Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIterator.next();
											Short accion = new Short("0");
											
											if (psTipoKey.ps_id.intValue() == productoServicioPeticionLocal.getPsId().intValue()){
												String serial = equipo.getEquipmentSerialNumber();
												Long noTel = null;
												
												if (peticionLocal.getNum_ide_nu_stb()!= null)
													noTel = new Long (peticionLocal.getNum_ide_nu_stb());
												else
													noTel = new Long (0);
												
												if(estadosHomologados.containsKey(equipo.getActionType())){
													accion = new Short(estadosHomologados.get(equipo.getActionType()).toString());												
												}
												
												String tipoEquipo = tipoEqElementoKey.id_tipo_eq.toString();
												Long elemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());
												Long psId = new Long (psTipoKey.ps_id.longValue());
												
												/* mfmendez - RQ5606 - Internet Movil*/
												String tipoInventario = "";
												Grpe_PsLocalHome grpePsLocalHome = null;
												Grpe_PsLocal grpePsLocal = null;
												try{
													grpePsLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
													grpePsLocal = grpePsLocalHome.findGrupoByPS(new Long(psTipoKey.ps_id.longValue()));													
												}catch(FinderException ex){
													log.debug("El PS "+psTipoKey.ps_id+" no se encuentra configurado en la tabla GRPE_PS en donde se asocia al tipo de inventario. " + ex);
													ex.printStackTrace();
												}finally{
													
													if(grpePsLocal != null && grpePsLocal.getGrpe_id() != null)
														tipoInventario = grpePsLocal.getGrpe_id().toString();
													else
														tipoInventario = "7";
												}
												
												
												Elemento_PeticionLocal elementoPeticionLocal = elementoPeticionLocalHome.create(serial,peticionLocal,noTel, accion,tipoEquipo,tipoInventario,elemento,psId);
												
												if(equipo.getBrandEquipment() != null)
													elementoPeticionLocal.setMarca(equipo.getBrandEquipment());
												
												if(equipo.getModelEquipment() != null)
													elementoPeticionLocal.setModelo(equipo.getModelEquipment());
												
												if(equipo.getExternalCodeEquipment() != null){
													elementoPeticionLocal.setNum_celular(equipo.getExternalCodeEquipment());
												}
												
												/* FIN mfmendez - RQ5606 - Internet Movil*/
												/*RQ.8595 - mfmendez*/
												elementoPeticionLocal.setFec_cont_sap(equipo.getPostingDateSAP());
												elementoPeticionLocal.setClase_mov_sap(equipo.getMoveTypeSAP());
												if(equipo.getMaterialCodeSAP() != null){
													elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getMaterialCodeSAP()));
												}else{
													elementoPeticionLocal.setPos_doc_sap(0);
												}
												elementoPeticionLocal.setNum_material_sap(equipo.getMaterialSAP());
												elementoPeticionLocal.setCentro_sap(equipo.getPlantSAP());
												elementoPeticionLocal.setAlmacen_sap(equipo.getStorageSAP());
												elementoPeticionLocal.setCod_lote_sap(equipo.getBatchCodeSAP());
												elementoPeticionLocal.setUnd_medida_sap(equipo.getMeasurementUnitSAP());
												elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenterSAP());
												elementoPeticionLocal.setArea_func_sap(equipo.getFuncAreaLongSAP());
												elementoPeticionLocal.setElement_pep_sap(equipo.getPepElementSAP());
												elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
												elementoPeticionLocal.setFlag_mat_sap(equipo.getFlagMatSAP());
												/*FIN - RQ.8595 - mfmendez*/
												tienePSTipoEqElem = true;
												tieneEquipoValido = true;
												break forPS;
											}
										}
									}
								}
								/*mfmendez - Cambio solicitado por padilla para que guarde todos los equipos que lleguen en el cierre*/
								if(!tienePSTipoEqElem){
									log.debug("TR711S: ENTRO POR LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE.");
									Collection prodServPeticion = peticionLocal.getProducto_servicio_peticion();
									boolean encontroPS = false;
									Long psId = null;
									Ps_Tipo_EqLocal psTipEqLocal = null;
									
									if(prodServPeticion != null && prodServPeticion.size() > 0){
										
										/*Logica para buscar si alguno de los PS de la petición se encuentra en la tabla PS_TIPO_EQ,
										 * en caso de encontrar alguno guarda el equipo con ese PS*/
										for(Iterator iter = prodServPeticion.iterator(); iter.hasNext() && !encontroPS;){
											Producto_servicio_peticionLocal prodSerPetLocal = (Producto_servicio_peticionLocal) iter.next();
											if(prodSerPetLocal != null && prodSerPetLocal.getProducto_servicio() != null){
												Producto_servicioLocal prodSerLocal = (Producto_servicioLocal) prodSerPetLocal.getProducto_servicio();
												Producto_servicioKey prodSerKey = (Producto_servicioKey) prodSerLocal.getPrimaryKey();
												if(prodSerKey != null && prodSerKey.ps_id != null){
													try{
														psTipEqLocal = psTipoEqLocalHome.findTipoByPs(prodSerKey.ps_id.longValue());
														if(psTipEqLocal != null){
															log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, ENCONTRO PS QUE ESTA EN PS_TIPO_EQ.");
															encontroPS = true;
															psId = prodSerKey.ps_id;
														}
													}catch(FinderException e){
														log.debug("FinderException: El PS "+prodSerKey.ps_id+" no se encuentra configurado en la tabla PS_TIPO_EQ.");
													}catch(Exception e){
														log.debug("Problema desconocido: El PS "+prodSerKey.ps_id+" no se encuentra configurado en la tabla PS_TIPO_EQ.");
													}
												}
											}
										}
									}
									
									/*Valida si encontró un PS de la petición en la tabla PS_TIPO_EQ*/
									if(encontroPS && psId != null && psTipEqLocal != null){
										log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR GUARDAR EL EQUIPO EN ELEMENTO_PETICION.");
										
										Short accion = new Short("0");
										Long noTel = null;
										
										if (peticionLocal.getNum_ide_nu_stb()!= null && !peticionLocal.getNum_ide_nu_stb().equals(""))
											noTel = new Long (peticionLocal.getNum_ide_nu_stb());
										else
											noTel = new Long (0);
										
										if(estadosHomologados.containsKey(equipo.getActionType())){
											accion = new Short(estadosHomologados.get(equipo.getActionType()).toString());
										}
										
										String tipoEquipo = "";
										if(psTipEqLocal != null && psTipEqLocal.getId_tipo_eq() != null){
											tipoEquipo = psTipEqLocal.getId_tipo_eq().toString();
										}
										Long elemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());
										
										/*Por indicacion de Padilla por ahora se dena el tipo de inventario en 7, por corresponder a PDTI*/
										String tipoInventario = "7";
										//										
										String serialEq = equipo.getEquipmentSerialNumber();
										
										log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE VA A INTENTAR CREAR EL REGISTRO EN LA TABLA.");
										Elemento_PeticionLocal elementoPeticionLocal = elementoPeticionLocalHome.create(serialEq,peticionLocal,noTel, accion,tipoEquipo,tipoInventario,elemento,psId);
										
										log.debug("TR711S: EN LA VALIDACION ALTERNA DEL ELEMENTO_PETICION PARA QUE SE GUARDE, SE CREO EL REGISTRO EN LA TABLA Y SE VAN A GUARDAR LOS DEMAS DATOS.");
										if(equipo.getBrandEquipment() != null)
											elementoPeticionLocal.setMarca(equipo.getBrandEquipment());
										
										if(equipo.getModelEquipment() != null)
											elementoPeticionLocal.setModelo(equipo.getModelEquipment());
										
										if(equipo.getExternalCodeEquipment() != null){
											elementoPeticionLocal.setNum_celular(equipo.getExternalCodeEquipment());
										}
										elementoPeticionLocal.setFec_cont_sap(equipo.getPostingDateSAP());
										elementoPeticionLocal.setClase_mov_sap(equipo.getMoveTypeSAP());
										if(equipo.getMaterialCodeSAP() != null){
											elementoPeticionLocal.setPos_doc_sap(Integer.parseInt(equipo.getMaterialCodeSAP()));
										}else{
											elementoPeticionLocal.setPos_doc_sap(0);
										}
										elementoPeticionLocal.setNum_material_sap(equipo.getMaterialSAP());
										elementoPeticionLocal.setCentro_sap(equipo.getPlantSAP());
										elementoPeticionLocal.setAlmacen_sap(equipo.getStorageSAP());
										elementoPeticionLocal.setCod_lote_sap(equipo.getBatchCodeSAP());
										elementoPeticionLocal.setUnd_medida_sap(equipo.getMeasurementUnitSAP());
										elementoPeticionLocal.setCentr_cost_sap(equipo.getCostCenterSAP());
										elementoPeticionLocal.setArea_func_sap(equipo.getFuncAreaLongSAP());
										elementoPeticionLocal.setElement_pep_sap(equipo.getPepElementSAP());
										elementoPeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
										elementoPeticionLocal.setFlag_mat_sap(equipo.getFlagMatSAP());
										
										tieneEquipoValido = true;
									}
								}
								/*FIN mfmendez - Cambio solicitado por padilla para que guarde todos los equipos que lleguen en el cirre*/
							}
						}else{//si no llegan equipos en la tr-711-s se eliminan los actuales de la base de datos
							log.debug("No vienen equipos, Voy a eliminar los equipos para el agendamiento:"+tr711s.getIdSchedule());
							Collection equiposBDList=peticionLocal.getElemento_peticion();
							if(equiposBDList != null && !equiposBDList.isEmpty()){
								Iterator equiposBDIt=null;
								List equiposEliminarList = new ArrayList();
								for(equiposBDIt=equiposBDList.iterator();equiposBDIt.hasNext();){
									Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)equiposBDIt.next();
									equiposEliminarList.add(elemento_PeticionLocal);
								}
								Iterator equiposEliminarIterator = equiposEliminarList.iterator();
								while(equiposEliminarIterator.hasNext()){
									Elemento_PeticionLocal elemento_PeticionLocal = 
										(Elemento_PeticionLocal)equiposEliminarIterator.next();
									elemento_PeticionLocal.remove();
								}
							}else{
								log.debug("No se encontraron equipos para el agendamiento:"+tr711s.getIdSchedule());
							}
							
						}
						
						for (Iterator iter = listaEquiposNoSerializados.iterator(); iter.hasNext();) {
							TR711SMaterials materialTR = (TR711SMaterials) iter.next();
							log.debug("Voy a configurar y registrar equipos no serializados para el agendamiento:"+tr711s.getIdSchedule());
							DBManager manager;
							manager=new DBManager();
							manager.setDataSource(DBManager.JDBC_VPISTBBA);
							Long bi_id=new Long(manager.seqNextValLong("VPI_ELEM_NO_SERIAL_SEQ"));
							ElementoNoSerializadoLocalHome ensl = (ElementoNoSerializadoLocalHome)HomeFactory.getHome(ElementoNoSerializadoLocalHome.JNDI_NAME);
							ElementoNoSerializadoLocal elemento = ensl.create(bi_id,peticionLocal);
							elemento.setPeticion(peticionLocal);
							elemento.setAlmacenSap(materialTR.getStorageSAP());
							elemento.setAreaFuncSap(materialTR.getFuncAreaLongSAP());
							Integer cantidad = materialTR.getMaterialAmount();
							if(cantidad!=null){
								elemento.setCantidad(new Long(cantidad.longValue()));
							}
							elemento.setCentrCostSap(materialTR.getCostCenterSAP());
							elemento.setCentroSap(materialTR.getPlantSAP());
							elemento.setClaseMovSap(materialTR.getMoveTypeSAP());
							elemento.setCodLoteSap(materialTR.getBatchCodeSAP());
							elemento.setElementPepSap(materialTR.getPepElementSAP());
							elemento.setFecContSap(materialTR.getPostingDateSAP());
							elemento.setFlagMatSap(materialTR.getFlagMatSAP());
							elemento.setMarca(materialTR.getBrandEquipment());
							elemento.setModelo(materialTR.getModelEquipment());
							elemento.setNumMaterialSap(materialTR.getMaterialSAP());
							String posDocSap= materialTR.getMaterialCodeSAP();
							if(posDocSap!=null && !posDocSap.equals("")){
								elemento.setPosDocSap(new Integer(posDocSap));
							}
							elemento.setUndMedidaSap(materialTR.getMeasurementUnitSAP());
						}
						}
						peticionLocal.setFlag_cp(tr711s.getCambioPlan());
						//Cierro la actuación
						agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
						peticionLocal.setEstado_agend_sc(new Integer(ComunInterfaces.ESTADO_AGENDA_SC_SIN_MARCA));
						
						if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
							if(strObservaciones.length() > 1000){
								strObservaciones = strObservaciones.substring(0, 999);
							}
							actDto.setObservacion(strObservaciones);
						}
						
						//Cierro la actividad solamente si no ejecute una actualización de decos y si estoy en control instalación 
						if (cierroActividad && codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
							//idrincon @req 4990
							//							if (tieneEquipoValido)
							//								actDto.setObservacion(tr711s.getBreaks().getObservations());
							//							else
							//								actDto.setObservacion("No se pueden procesar los equipos traidos con el mensaje:"+tr711s.getId()
							//										+" por lo tanto no se guardan en la BD, y el fujo continua normalmente. "+tr711s.getBreaks().getObservations());
							
							
							String[] llaveWF = null;
							if (codBandeja != null){
								llaveWF = codBandeja.split("-");					
								actDto.setDato(llaveWF[0],llaveWF[1]);
							}
							actividadEJB.terminar(actDto);
						}else{
							if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
								log.debug("Quedo en espera de la respuesta de la activación de decos y tarjetas (TR-017-S)");
							}else{
								log.debug("Me encuentro en la actividad :"+codActividad+" por lo tanto no la cierro automáticamente, debe hacerse por la aplicación");
							}
						}
						
					}else{
						String codCancelacion = VpistbbaConfig.getVariable("CAUSALES_CANCELACION");
						String codigos[]=codCancelacion.split(",");
						boolean esCancelacion = false;
						
						for(int i =0; i<codigos.length;i++){
							if (tr711s.getPostageCode().equals(codigos[i])){
								esCancelacion=true;
								break;
							}
						}
						
						if (esCancelacion){
							log.debug("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación, Se debe derivar a PGC : Franqueo: "+tr711s.getPostageCode());
							actDto.setObservacion("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación: Franqueo: "+tr711s.getPostageCode()
									+" Por favor revise, se deriva a bandeja PGC");
							agendaSCLocal.setEstado(new Integer(ACTUACION_CANCELADA));
							
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
							actividadEJB.terminar(actDto);
						}else{
							log.debug("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode());
							actDto.setObservacion("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode()
									+" Por favor revise, y realice la actualización de inventarios de forma manual");
							agendaSCLocal.setEstado(new Integer(ACTUACION_CERRADA));
						}
					}
				}else{
					log.debug("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
					actDto.setObservacion("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
				}
				//REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 24/07/2014
				try {
					
					//instanciamos el local home
					Producto_servicio_peticionLocalHome  pss = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
					//traemos los PS's de la peticion
					Collection psPeticion = pss.findAllByPetiNumero(actDto.getNumeroPeticion());
					Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
					//recorremos el arreglo de PS's
					for(Iterator iter = psPeticion.iterator();iter.hasNext();)
					{
						//instanciamos cada ps del itrerator en la clase local
						producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
						//validamos que la operacion comercial sea una alta o un traslado
						//log.debug("Averias de infancia: se valida si es una alta o un traslado");
						log.debug("La operacion comercial "+producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo()+
								"del ps "+producto_servicio_peticionLocal.getPsId()+ "de la peticion "+actDto.getNumeroPeticion());
//						if((producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo()!= null 
//								&& producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta))
//								|| (producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo()!= null 
//								&& producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd))){
//							//funcion que inserta en la nueva tabla el registro para llevar control y obtener peticiones de infancia
//							
							insertarAltaTraslado(actDto);
							break;
					//	}	
					}
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error al consultar los PS's de la peticion "+e.toString());
				} catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error al inicar el Producto_servicio_peticionLocalHome "+e.toString());
				}
				
			}catch(NamingException ex){
				log.error("Error en la instancia en la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.error("Error en la búsqueda en la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			}catch(TnProcesoExcepcion ex){
				log.error("Error en los procesos de la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.error("Error en creación de la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			} catch (EJBException ex) {
				log.error("Error en creación de la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			} catch (RemoveException ex) {
				log.error("Error en eliminación de registro en la recepción del cierre de actuación " , ex);
				ex.printStackTrace();
			}
		}		
		
//		REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 24/07/2014
		//funcion que inserta en la tabla instalaciones_vip un registro para llevar control de las instalaciones y poder consultar si una averia de infancia
		public void insertarAltaTraslado (ActividadEJBDTO act)
		{
			try {
				log.debug("Se procedera a insertar un nuevo registro en instalaciones VIP");
				//instanciamos el localhome de instalaciones_vip
				Instalaciones_VIPLocalHome instalaciones_VIPLocalHome = (Instalaciones_VIPLocalHome) HomeFactory.getHome(Instalaciones_VIPLocalHome.JNDI_NAME);
				//instanciamos el localhome de peticion
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				//creamos la key con el numero depeticion
				PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
				//consultamos con la PK la peticion
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				//obtenemos la PK de peticion_atis
				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				//obtenemos la PK de departamento de la peticion
				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				//obtenemos la PK de la localidad de la peticion
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				//creamos el nuevo registro en la tabla instalacion_VIP
				Instalaciones_VIPLocal instalaciones_VIPLocal=instalaciones_VIPLocalHome.create(act.getNumeroPeticion(),
						peticion_atisKey.cod_pet_cd,peticionLocal.getNum_ide_nu_stb(),peticionLocal.getNum_ide_nu_tv(),localidadKey.cod_loc,
						departamentoKey.cod_dpt,peticionLocal.getEspe_id(),peticionLocal.getTica_id());
				//setemos los campos no obligatorios fechas
				instalaciones_VIPLocal.setFecha_ingreso(peticionLocal.getPeti_fecha_ingreso());
				Calendar calendar = Calendar.getInstance();
				Date date = calendar.getTime();
				Timestamp currentTimestamp = new Timestamp(date.getTime());
				instalaciones_VIPLocal.setFecha_fin(currentTimestamp);
				log.debug("Se creo registro con exito los datos de la peticion "+act.getNumeroPeticion()+" en la tabla instalaciones_VIP");
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al iniciar el localHome "+e.toString());
			} catch (FinderException e) {
				//TODO Bloque catch generado automáticamente
				log.debug("Error al consultar la peticion "+e.toString());
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al crear el registro en la tabla Instalaciones_vip "+e.toString());
			}
		}	
		//FIN REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA
		/**
		 * @param peticionLocal
		 * @param listaDecos
		 * @param actDto
		 * @param tipoNuevaFamiliaTV
		 */
		

		/**
		 * @param agendaSCLocal
		 * @return
		 * @throws NamingException
		 * @throws FinderException
		 * @throws ATiempoAppEx
		 */
		public boolean esAutoinstalacion(Agenda_scLocal agendaSCLocal) throws NamingException, FinderException, ATiempoAppEx {
			Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) 
				HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection productosServicioPeticion = psph.findAllByPetiNumero(agendaSCLocal.getPeti_numero());
			PeticionesDelegate pDelegate = new PeticionesDelegate();
			Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
			boolean esOCAutoInstalacion = false;
			for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
				Familia_producto_servicioKey llaveFamilia = (Familia_producto_servicioKey) psp.getFamiliaKey();
			    int idFamiliaPsp = llaveFamilia.faps_id.intValue();
				
				Operacion_comercialKey opcoKey = (Operacion_comercialKey)psp.getOperacion_comercial().getPrimaryKey();
				if(opcoKey.opco_id.equals(OCAutoInstalacion)){
					esOCAutoInstalacion = true;
					break;
				}
			}
			return esOCAutoInstalacion;
		}		
		/**
		 * Método que crea un modem en la base de datos y envia la configuracion automatica del modem.
		 */
		private ModemLocal crearConfigurarModem(TR711S tr711s, Agenda_scLocal agendaSCLocal, 
				PeticionLocal peticionLocal, ActividadEJBDTO actDto, TR711SMaterials materialModemUtilizado, 
				Short accion) throws ATiempoAppEx, CreateException {
			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(agendaSCLocal.getPeti_numero());
			Long telefono = null;
			
			String telefonoModem="0";
			if(peticionLocal.getTel_cot_ds()!=null){
				telefonoModem=peticionLocal.getTel_cot_ds();
			}
			
			ModemLocal modemNuevoLocal = modemLocalHome.create(materialModemUtilizado.getEquipmentSerialNumber(), peticionLocal,new Long(telefonoModem), accion);
			log.debug("Se crea modem "+materialModemUtilizado.getEquipmentSerialNumber());
			modemNuevoLocal.setModem_marca(materialModemUtilizado.getBrandEquipment());								
			modemNuevoLocal.setCodigo_material(materialModemUtilizado.getCodeMaterial());
			modemNuevoLocal.setModelo(materialModemUtilizado.getModelEquipment());
			if (materialModemUtilizado.getTypeEquipment().equals("MODEMSTD2P") || materialModemUtilizado.getTypeEquipment().equals("MODEMSTD4P")
					|| materialModemUtilizado.getTypeEquipment().equals("MODEMSTD1P"))
				modemNuevoLocal.setTipo(new Integer(ComunInterfaces.identificadorConvencional));
			else if (materialModemUtilizado.getTypeEquipment().equals("MODEMWIFI")){
				modemNuevoLocal.setTipo(new Integer(ComunInterfaces.identificadorWiFi));
			}else{
				modemNuevoLocal.setTipo(new Integer(0));
			}
			//Envío el mensaje de autoinstalación
			//Obtención de la información del agendamiento
			RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
			ModemVpiDTO modem = new ModemVpiDTO();
			modem.setPeti_numero(agendaSCLocal.getPeti_numero());
			modem.setMarca(modemNuevoLocal.getModem_marca());
			modem.setSerial(materialModemUtilizado.getEquipmentSerialNumber());
			modem.setModelo(modemNuevoLocal.getModelo());
			modem.setCodMaterial(modemNuevoLocal.getCodigo_material());
			
			//rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), 
			//		tr711s.getId()+"#"+tr711s.getIdSchedule(),false, true);
			//REQ BA NAKED 
			//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
			ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
			aCSServicioDelegate.enviarAutoConfiguracion(modem,actDto.getCodigoActividad(), tr711s.getId()+"#"+tr711s.getIdSchedule());
			//FIN REQ NAKED
			return modemNuevoLocal;
		}

		/**
		 * Cierre de actuación para Autoinstalación cuando está en PGACS
		 * @param tr711s
		 * @throws ATiempoAppEx
		 */
		public void recepcionCierreActuacionPGACS(TR711S tr711s) throws ATiempoAppEx {
			try{
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
								
				ArrayList listaFranqueoOK = new ArrayList();
				
				boolean franqueoOK = false;
	
				Agenda_scLocal agendaSCLocal = null;
				
				//Obtención de la información basica del agendameinto
				try{
					Agenda_scKey agendaSCKey = new Agenda_scKey(tr711s.getIdSchedule());
					agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				}catch(FinderException ex){
					log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
					String idPeticionAux = tr711s.getIdSchedule().substring(2,tr711s.getIdSchedule().indexOf("-"));
					
					Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
					for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
						Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
						
						if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
							agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
						}
					}
					
					PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
					PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
					
					
					agendaSCLocal = agendaSCLocalHome.create(tr711s.getIdSchedule());
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
					agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
					agendaSCLocal.setPeticion(peticionLocal);
					agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}

				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
				Collection productoSrvicioPeticionCollection = peticionLocal.getProducto_servicio_peticion();
				
				//Implementación del llamado de la actividad donde me encuentro 
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero,idAplicacion);
				
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				String codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				
				
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codActividad,idCorrelativo,null);
				
				//Verificación si viene el mensaje con código de franqueo exitoso
				Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
				
				String codBandeja=null;
				try{
					Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr711s.getPostageCode());
					Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
					codBandeja = codFranqueoOKLocal.getBandeja();
					franqueoOK = true;
				}catch(FinderException ex){
					log.debug("El codigo de franqueo: "+tr711s.getPostageCode()+" no es considerado como exitoso, se valida si tiene marca y si es así se envía a configurar inventarios");
					franqueoOK = false;
				}

				if (agendaSCLocal.getEstado() != null && (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA) ){
					//Ejecuto el proceso solo si viene el código de franqueo OK o si viene el codigo de franqueo mal pero la petición tiene marca
					if (franqueoOK || (!franqueoOK && peticionLocal.getEstado_agend_sc() != null &&  peticionLocal.getEstado_agend_sc().intValue() == ESTADO_AGENDA_SC_CON_MARCA)){
						
						if(tr711s.getBreaks()!= null && tr711s.getBreaks().getBreakPairs() != null && tr711s.getBreaks().getBreakPairs().size() > 0){
							this.setQuiebrePcAgendaSc( tr711s.getBreaks().getBreakPairs(), peticionKey.peti_numero, productoSrvicioPeticionCollection, actDto.getCodigoActividad() );
						}
						
						//Cierro la actuación
						agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
						//Cierro la actividad solamente si no ejecute una actualización de decos y si estoy en control instalación 
						if (codActividad.equals(ComunInterfaces.COD_ACT_PGACS)){
							String[] llaveWF = null;
							if (codBandeja != null){
								llaveWF = codBandeja.split("-");					
								actDto.setDato(llaveWF[0],llaveWF[1]);
							}
							actividadEJB.terminar(actDto);
						}
					}else{
						String codCancelacion = VpistbbaConfig.getVariable("CAUSALES_CANCELACION");
						String codigos[]=codCancelacion.split(",");
						boolean esCancelacion = false;
						
						for(int i =0; i<codigos.length;i++){
							if (tr711s.getPostageCode().equals(codigos[i])){
								esCancelacion=true;
								break;
							}
						}
						
						if (esCancelacion){
							log.debug("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación, Se debe derivar a PGC : Franqueo: "+tr711s.getPostageCode());
							actDto.setObservacion("Se cancela la actuación pues el mensaje trae un código de franqueo de cancelación: Franqueo: "+tr711s.getPostageCode()
									+" Por favor revise, se deriva a bandeja PGC");
							agendaSCLocal.setEstado(new Integer(ACTUACION_CANCELADA));
							
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
							actividadEJB.terminar(actDto);
						}else{
							log.debug("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode());
							actDto.setObservacion("Se cierra la actuación pues el mensaje trae un código de franqueo no existoso: Franqueo: "+tr711s.getPostageCode()
									+" Por favor revise, y realice la actualización de inventarios de forma manual");
							agendaSCLocal.setEstado(new Integer(ACTUACION_CERRADA));
						}
					}
				}else{
					log.debug("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
					actDto.setObservacion("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
				}

				
			}catch(NamingException ex){
				log.debug("Error en la instancia en la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error en la búsqueda en la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(TnProcesoExcepcion ex){
				log.debug("Error en los procesos de la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("Error en creación de la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}/*catch(RemoveException ex){
				log.debug("Error en eliminación de la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}*/
		}
		
		
		public void agendamientoDecosTR711 (ArrayList listaDecos, PeticionLocal peticionLocal, TR711S tr711s, ActividadEJBDTO actDto, IActividadEJB actividadEJB, boolean modemsOK, String codBandeja){
			try{
				Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);				
				Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
				Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal = null;
				ArrayList ArrayDecos = new ArrayList();
				ArrayList ArrayTarjetas = new ArrayList();
				ArrayList ArrayDiscosDuros = new ArrayList();
				ArrayList decoTarjetaTR711 = new ArrayList();
				ArrayList decoTarjetaInstall = new ArrayList();
				PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
				boolean esPostventa = true;
				
				Collection psPeticion = peticionLocal.getProducto_servicio_peticion();
				for (Iterator psPeticionIterator = psPeticion.iterator(); psPeticionIterator.hasNext();){
					Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
					
					Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
					Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
//					REQ BA NAKED adicion familia PC naked
					if (familiaPSKey != null && 
							((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) ||familiaPSKey.faps_id.toString().equals(new Integer(familiaPcPsBANaked).toString())||  familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaIP).toString()) )
							|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
						esPostventa = false;
						break;
					}
				}

				String tiposDiscoDuro = VpistbbaConfig.getVariable("TIPOS_DISCOS_DUROS");
				List listTiposDiscoDuro = Arrays.asList(tiposDiscoDuro.split(","));
				
				for (int i=0; i < listaDecos.size(); i++){
					TR711SMaterials decosTarjetas = (TR711SMaterials)listaDecos.get(i);
					
					if(listTiposDiscoDuro.contains(decosTarjetas.getTypeEquipment())){
						ArrayDiscosDuros.add(decosTarjetas);
					}else if (decosTarjetas.getTypeEquipment().indexOf("DECO")!=-1){
						ArrayDecos.add(decosTarjetas);
					}else if (decosTarjetas.getTypeEquipment().indexOf("TARJETA")!=-1){
						ArrayTarjetas.add(decosTarjetas);
					}
				}
				
				String decosDiscoDuro = VpistbbaConfig.getVariable("DECO_DISCO_DURO");
				List listDecosDiscoDuro = Arrays.asList(decosDiscoDuro.split(","));
				
				//Si el mensaje trae decos Tarjetas
				if (ArrayDecos.size() == ArrayTarjetas.size()){
					//Creación de los elementos de Decos Tarjetas
					for (int j=0;j<ArrayDecos.size();j++){
						TR711SMaterials deco = (TR711SMaterials)ArrayDecos.get(j);
						TR711SMaterials tarjeta = (TR711SMaterials)ArrayTarjetas.get(j);
						
						TR711SMaterials discoDuro = null;
						if(!ArrayDiscosDuros.isEmpty()){
							//Se verifica si la marca de deco soporta disco duro
							if(listDecosDiscoDuro.contains(deco.getBrandEquipment())){
								discoDuro = (TR711SMaterials)ArrayDiscosDuros.get(0);
								ArrayDiscosDuros.remove(0);
							}
						}

						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(deco.getCassId(), tarjeta.getEquipmentSerialNumber());
						decoTarjetaDTO.setAccion(new Integer(operacionParActivar));
						
						if (deco.getTypeEquipment().indexOf(desHCDecoSTD) != -1){
							decoTarjetaDTO.setDeco_reference(desHCDecoSTD);
						}else if (deco.getTypeEquipment().indexOf(desHCDecoPVR) != -1){
							decoTarjetaDTO.setDeco_reference(desHCDecoPVR);
						}else if (deco.getTypeEquipment().indexOf(desHCDecoHDTV) != -1){
							decoTarjetaDTO.setDeco_reference(desHCDecoHDTV);
						}
						
						decoTarjetaDTO.setOperationComercial(new Long(operacionParActivar));
						decoTarjetaDTO.setDecoSerial(deco.getEquipmentSerialNumber());
						decoTarjetaDTO.setDecoBrand(deco.getBrandEquipment());	
						
						/*RQ.8595 - mfmendez*/
						// Datos del Deco
						decoTarjetaDTO.setDecoPostingDateSAP(deco.getPostingDateSAP());					
						decoTarjetaDTO.setDecoMoveTypeSAP(deco.getMoveTypeSAP());					
						decoTarjetaDTO.setDecoMaterialCodeSAP(deco.getMaterialCodeSAP());					
						decoTarjetaDTO.setDecoMaterialSAP(deco.getMaterialSAP());											
						decoTarjetaDTO.setDecoPlantSAP(deco.getPlantSAP());																	
						decoTarjetaDTO.setDecoStorageSAP(deco.getStorageSAP());											
						decoTarjetaDTO.setDecoBatchCodeSAP(deco.getBatchCodeSAP());					
						decoTarjetaDTO.setDecoMeasurementUnitSAP(deco.getMeasurementUnitSAP());					
						decoTarjetaDTO.setDecoCostCenterSAP(deco.getCostCenterSAP());					
						decoTarjetaDTO.setDecoFuncAreaLongSAP(deco.getFuncAreaLongSAP());					
						decoTarjetaDTO.setDecoPepElementSAP(deco.getPepElementSAP());
						decoTarjetaDTO.setDecoFlagMatSAP(deco.getFlagMatSAP());
						// Datos de la Tarjeta
						decoTarjetaDTO.setCardPostingDateSAP(tarjeta.getPostingDateSAP());					
						decoTarjetaDTO.setCardMoveTypeSAP(tarjeta.getMoveTypeSAP());					
						decoTarjetaDTO.setCardMaterialCodeSAP(tarjeta.getMaterialCodeSAP());					
						decoTarjetaDTO.setCardMaterialSAP(tarjeta.getMaterialSAP());											
						decoTarjetaDTO.setCardPlantSAP(tarjeta.getPlantSAP());																	
						decoTarjetaDTO.setCardStorageSAP(tarjeta.getStorageSAP());											
						decoTarjetaDTO.setCardBatchCodeSAP(tarjeta.getBatchCodeSAP());					
						decoTarjetaDTO.setCardMeasurementUnitSAP(tarjeta.getMeasurementUnitSAP());					
						decoTarjetaDTO.setCardCostCenterSAP(tarjeta.getCostCenterSAP());					
						decoTarjetaDTO.setCardFuncAreaLongSAP(tarjeta.getFuncAreaLongSAP());					
						decoTarjetaDTO.setCardPepElementSAP(tarjeta.getPepElementSAP());
						decoTarjetaDTO.setCardFlagMatSAP(tarjeta.getFlagMatSAP());
						//Datos del disco duro
						if(discoDuro != null){
							decoTarjetaDTO.setDdtvSerial(discoDuro.getEquipmentSerialNumber());
							if(discoDuro.getBrandEquipment().length() > 30){
								decoTarjetaDTO.setDdtvMarca(discoDuro.getBrandEquipment().substring(0,29));
							}else{
								decoTarjetaDTO.setDdtvMarca(discoDuro.getBrandEquipment());	
							}
							if(discoDuro.getModelEquipment().length() > 30){
								decoTarjetaDTO.setDdtvModelo(discoDuro.getModelEquipment().substring(0,29));
							}else{
								decoTarjetaDTO.setDdtvModelo(discoDuro.getModelEquipment());	
							}
						}
						/*FIN - RQ.8595 - mfmendez*/
						decoTarjetaTR711.add(decoTarjetaDTO);
					}
				}
				
				//Obtención de la diferencia entre los decos instalados y los que llegan nuevos
				Collection decoTarjetaOld = peticionLocal.getDeco_tarjeta();
				
				
				//Aqui se valida que los decos que traen el mensaje hallan para bajar es decir en la tabla hay 3 
				//y el mensaje trae tres se baja el que no trae el mensaje 
				for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
					boolean estaInstalado = false;
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
					
					for (Iterator equiposIterator=decoTarjetaTR711.iterator(); equiposIterator.hasNext();){
						DecoTarDTO equipo = (DecoTarDTO)equiposIterator.next();
							
						//Se cambio este if, para comparar seriales decos y no cass id
						if (!decoTarjetaLocal.getSerial_deco().equals(equipo.getDecoSerial())){
							estaInstalado = false;
						}else{
							 if (!decoTarjetaKey.id_tarjeta.equals(equipo.getTarjeta())){
							 	estaInstalado = false;
							 }else{
							 	estaInstalado = true;
							 	decoTarjetaLocal.setSerial_ddtv(equipo.getDdtvSerial());
							 	decoTarjetaLocal.setMarca_ddtv(equipo.getDdtvMarca());
							 	decoTarjetaLocal.setModelo_ddtv(equipo.getDdtvModelo());
							 	break;
							 }
						}
					}
						
					if (!estaInstalado && !esPostventa){
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
						decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());						
						/*RQ.8595 - mfmendez*/
						decoTarjetaDTO = this.setDatosSAPTarjeta(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_tarjeta, peticionKey.peti_numero);
						decoTarjetaDTO = this.setDatosSAPDeco(deco_tar_inf_sapLocalHome, decoTarjetaDTO, decoTarjetaKey.id_deco, peticionKey.peti_numero);
						/*FIN - RQ.8595 - mfmendez*/
						decoTarjetaInstall.add(decoTarjetaDTO);
					}		
				}

				//Aqui se elimina el deco que se dio de baja en la primera parte
				for (Iterator decoTarjetaInstallIterator = decoTarjetaInstall.iterator(); decoTarjetaInstallIterator.hasNext();){
					DecoTarDTO decoTarjetaDTO = (DecoTarDTO)decoTarjetaInstallIterator.next();
					
					if (decoTarjetaDTO.getAccion().intValue() == operacionParDesactivar){
						
						Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(decoTarjetaDTO.getTarjeta(), 
								decoTarjetaDTO.getDeco(), (PeticionKey)peticionLocal.getPrimaryKey());
						Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
						/*RQ.8595 - mfmendez*/
						Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
						Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
						
						/*RQ.8595 - mfmendez*/
						// Se borran datos de SAP del deco
						try{			
							Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(decoTarjetaDTO.getDeco(), peticionKey.peti_numero);
							Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
							infoSAPDeco.remove();
						} catch (FinderException e) {
							log.debug("No se encontraron Decos para deco con id: "+decoTarjetaDTO.getDeco()+". Y id de peticion: "+peticionKey.peti_numero);
						} catch (Exception e) {
							log.error("RecursosBABean: Se presento Error borrando los datos de SAP para un Deco. "+e);
						}
						// se borran datos de SAP de la tarjeta
						try{			
							Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaDTO.getTarjeta(), peticionKey.peti_numero);
							Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
							infoSAPCard.remove();
						} catch (FinderException e) {
							log.debug("No se encontraron Tarjetas para deco con id: "+decoTarjetaDTO.getTarjeta()+". Y id de peticion: "+peticionKey.peti_numero);
						} catch (Exception e) {
							log.error("RecursosBABean: Se presento Error borrando los datos de SAP para una Tarjeta. "+e);
						}
						/*RQ.8595 - mfmendez*/
						
						decoTarjetaAuxLocal.remove();
					}
				}
				int contadorHD = 0;
				int contadorPVR = 0;
				int contadorSTD = 0;
				int reporteHD = 0;
				int reportePVR = 0;
				int reporteSTD = 0;
				int diaAdicional = 1;
				for(Iterator psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)psPeticionIt.next();
					Familia_producto_servicioKey familia_producto_servicioKey =  producto_servicio_peticionLocal.getFamiliaKey();
					if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoHDTV){
						contadorHD += 1;						
						log.debug("Tiene deco HD ");
					}else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoPVRTV){
						contadorPVR += 1;						
						log.debug("Tiene deco PVR");
					}
					else if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaDecoTV){
						contadorSTD += 1;
						log.debug("Tiene deco STD");
					}
				}			
				//Aqui se valida que el mensaje traiga nuevos decos, se da de alta el nuevo
				Collection ps=peticionLocal.getProducto_servicio_peticion();
				
				for(Iterator equiposIterator=decoTarjetaTR711.iterator(); equiposIterator.hasNext();){
					DecoTarDTO equipo = (DecoTarDTO)equiposIterator.next();
					boolean estaSinInstalar = true;
					
					try{
						Deco_Tarjeta_Info_SapKey decoTarjetaSAPKey = new Deco_Tarjeta_Info_SapKey(equipo.getDeco(),peticionKey.peti_numero );
						dec_tar_sapdecoLocal = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(decoTarjetaSAPKey);
						ingresoInformacionSAP(dec_tar_sapdecoLocal, equipo, peticionKey, deco_tar_inf_sapLocalHome);
					}catch(FinderException ex){
						log.debug("El elemento no se encuentra registrado en la tabla DECO_TARJETA_INF_SAP");
						dec_tar_sapdecoLocal = null;
					}
									
					for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
						Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
						Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
						
						if (decoTarjetaLocal.getSerial_deco().equals(equipo.getDecoSerial()) && decoTarjetaKey.id_tarjeta.equals(equipo.getTarjeta())){
							/*RQ.8595 - mfmendez*/
							if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoHDTV) != -1 && contadorHD > reporteHD){
								decoTarjetaLocal.setDeco_adicionales(new Long(0));
								reporteHD++;
							}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoPVR) != -1 && contadorPVR > reportePVR){
								decoTarjetaLocal.setDeco_adicionales(new Long(0));
								reportePVR++;
							}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoSTD) != -1 && contadorSTD > reporteSTD){
								decoTarjetaLocal.setDeco_adicionales(new Long(0));
								reporteSTD++;
							}else{
								SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
								Calendar fechaActual = Calendar.getInstance();
								fechaActual.add(Calendar.DATE,diaAdicional);
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaActual.getTime()));
								decoTarjetaLocal.setDeco_adicionales(new Long(1));
								decoTarjetaLocal.setFec_ejec_atis(new Timestamp(date.getTime()));
								diaAdicional++;
								
							}
								
							try{
								decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
								// se crean los datos del deco
								if(dec_tar_sapdecoLocal == null )
									ingresoInformacionSAP(dec_tar_sapdecoLocal, equipo, peticionKey, deco_tar_inf_sapLocalHome);
							} catch(Exception e){
								log.debug("recursosBaBean: Existente: No se pudieron crear los datos de SAP para el Deco con id: "+equipo.getDeco()+" y para la Tarjeta con id: "+equipo.getTarjeta()+". "+e);
							}
							/*FIN - RQ.8595 - mfmendez*/	
							estaSinInstalar = false;
							break;
						}
					}
					
					if (estaSinInstalar){
						Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getTarjeta(), equipo.getDeco(), peticionLocal);
						
						decoTarjetaLocal.setAccion(new Integer(accionParActivar));
						decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
						
						decoTarjetaLocal.setOpco_id(new Long(accionParActivar));
						decoTarjetaLocal.setDeco_reference(equipo.getDeco_reference());
						decoTarjetaLocal.setDeco_marca(equipo.getDecoBrand());
						decoTarjetaLocal.setSerial_deco(equipo.getDecoSerial());
						decoTarjetaLocal.setSerial_tarjeta(equipo.getTarjeta());
						decoTarjetaLocal.setCodigo_deco(equipo.getDecoType());
						decoTarjetaLocal.setSerial_ddtv(equipo.getDdtvSerial());
					 	decoTarjetaLocal.setMarca_ddtv(equipo.getDdtvMarca());
					 	decoTarjetaLocal.setModelo_ddtv(equipo.getDdtvModelo());
						/*RQ.8595 - mfmendez*/
						decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
						if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoHDTV) != -1 && contadorHD > reporteHD){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reporteHD++;
						}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoPVR) != -1 && contadorPVR > reportePVR){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reportePVR++;
						}else if (decoTarjetaLocal.getDeco_reference().indexOf(desHCDecoSTD) != -1 && contadorSTD > reporteSTD){
							decoTarjetaLocal.setDeco_adicionales(new Long(0));
							reporteSTD++;
						}else{
							SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
							Calendar fechaActual = Calendar.getInstance();
							fechaActual.add(Calendar.DATE,diaAdicional);
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaActual.getTime()));
							decoTarjetaLocal.setDeco_adicionales(new Long(1));
							decoTarjetaLocal.setFec_ejec_atis(new Timestamp(date.getTime()));
							diaAdicional++;
							
						}
						try{
							decoTarjetaLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
//							 se crean los datos del deco
							if(dec_tar_sapdecoLocal == null )
								ingresoInformacionSAP(dec_tar_sapdecoLocal, equipo, peticionKey, deco_tar_inf_sapLocalHome);
						} catch(Exception e){
							log.debug("recursosBaBean: Nuevo: No se pudieron crear los datos de SAP para el Deco con id: "+equipo.getDeco()+" y para la Tarjeta con id: "+equipo.getTarjeta()+". "+e);
						}
						/*FIN - RQ.8595 - mfmendez*/			
						
						DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDeco(), equipo.getTarjeta());
						decoTarjetaDTO.setAccion(new Integer(accionParActivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(decoTarjetaLocal.getOpco_id());
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						/*RQ.8595 - mfmendez*/
						// Datos del Deco
						decoTarjetaDTO.setDecoPostingDateSAP(equipo.getDecoPostingDateSAP());					
						decoTarjetaDTO.setDecoMoveTypeSAP(equipo.getDecoMoveTypeSAP());					
						decoTarjetaDTO.setDecoMaterialCodeSAP(equipo.getDecoMaterialCodeSAP());					
						decoTarjetaDTO.setDecoMaterialSAP(equipo.getDecoMaterialSAP());											
						decoTarjetaDTO.setDecoPlantSAP(equipo.getDecoPlantSAP());																	
						decoTarjetaDTO.setDecoStorageSAP(equipo.getDecoStorageSAP());											
						decoTarjetaDTO.setDecoBatchCodeSAP(equipo.getDecoBatchCodeSAP());					
						decoTarjetaDTO.setDecoMeasurementUnitSAP(equipo.getDecoMeasurementUnitSAP());					
						decoTarjetaDTO.setDecoCostCenterSAP(equipo.getDecoCostCenterSAP());					
						decoTarjetaDTO.setDecoFuncAreaLongSAP(equipo.getDecoFuncAreaLongSAP());					
						decoTarjetaDTO.setDecoPepElementSAP(equipo.getDecoPepElementSAP());
						decoTarjetaDTO.setDecoFlagMatSAP(equipo.getDecoFlagMatSAP());
						// Datos de la Tarjeta
						decoTarjetaDTO.setCardPostingDateSAP(equipo.getCardPostingDateSAP());					
						decoTarjetaDTO.setCardMoveTypeSAP(equipo.getCardMoveTypeSAP());					
						decoTarjetaDTO.setCardMaterialCodeSAP(equipo.getCardMaterialCodeSAP());					
						decoTarjetaDTO.setCardMaterialSAP(equipo.getCardMaterialSAP());											
						decoTarjetaDTO.setCardPlantSAP(equipo.getCardPlantSAP());																	
						decoTarjetaDTO.setCardStorageSAP(equipo.getCardStorageSAP());											
						decoTarjetaDTO.setCardBatchCodeSAP(equipo.getCardBatchCodeSAP());					
						decoTarjetaDTO.setCardMeasurementUnitSAP(equipo.getCardMeasurementUnitSAP());					
						decoTarjetaDTO.setCardCostCenterSAP(equipo.getCardCostCenterSAP());					
						decoTarjetaDTO.setCardFuncAreaLongSAP(equipo.getCardFuncAreaLongSAP());					
						decoTarjetaDTO.setCardPepElementSAP(equipo.getCardPepElementSAP());
						decoTarjetaDTO.setCardFlagMatSAP(equipo.getCardFlagMatSAP());
						/*FIN - RQ.8595 - mfmendez*/
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}
				}	
			
				if (decoTarjetaInstall != null && decoTarjetaInstall.size() > 0){
					Collection mensajesEstadoTV = peticionLocal.getMensaje_estado_tv();
									
					String idAgendaSC = tr711s.getIdSchedule()+"@"+tr711s.getId()+"@tr711"+"@"+modemsOK;
					
					RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
					recursosTVDelegate.enviaConfiguracionServiciosTVAgendaSC(peticionKey.peti_numero.longValue(), decoTarjetaInstall, true, idAgendaSC);
				}else{
					log.debug("No se envía el mensaje: "+ tr711s.getId() +" a HC porque los equipos recibidos ya están instalados");
					String observaciones = actDto.getObservacion(); 
					if(observaciones != null && !observaciones.equals("")){
						actDto.setObservacion(observaciones + " No se envía el mensaje: "+ tr711s.getId() +" a HC porque los equipos recibidos ya están instalados");
					}else{
						actDto.setObservacion("No se envía el mensaje: "+ tr711s.getId() +" a HC porque los equipos recibidos ya están instalados");
					}
					
//					String[] llaveWF = null;
//					if (codBandeja != null){
//						llaveWF = codBandeja.split("-");					
//						actDto.setDato(llaveWF[0],llaveWF[1]);
//					}
//					actividadEJB.terminar(actDto);
				}
			}catch(NamingException ex){
				log.debug("Error en la instancia en la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("Error en la creación en la recepción del cierre de actuación " + ex);;
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error en la búsqueda en la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(RemoveException ex){
				log.debug("Error en la eliminación en la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
			}catch(ATiempoAppEx ex){
				log.debug("Error en los procesos de la recepción del cierre de actuación " + ex);
				ex.printStackTrace();
//			}catch(TnProcesoExcepcion ex){
//				log.debug("Error en los procesos de la recepción del cierre de actuación " + ex);
//				ex.printStackTrace();
			} catch (ParseException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en los procesos de la recepción del cierre de actuación " + e);
			}
		}
			
		/**
		 * @param dec_tar_sapdecoLocal
		 * @param equipo
		 * @param peticionKey
		 * @param deco_tar_inf_sapLocalHome
		 */
		public void ingresoInformacionSAP(Deco_Tarjeta_Info_SapLocal dec_tar_sapdecoLocal, DecoTarDTO equipo, PeticionKey peticionKey, Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome) {
			try {
				// TODO Apéndice de método generado automáticamente
				boolean crearTarjeta = false;
				if(dec_tar_sapdecoLocal == null){
					dec_tar_sapdecoLocal = deco_tar_inf_sapLocalHome.create(equipo.getDeco(), peticionKey.peti_numero);
					crearTarjeta =  true;
				}
					
				dec_tar_sapdecoLocal.setFec_cont_sap(equipo.getDecoPostingDateSAP());
				dec_tar_sapdecoLocal.setClase_mov_sap(equipo.getDecoMoveTypeSAP());
				if(equipo.getDecoMaterialCodeSAP() != null)
					dec_tar_sapdecoLocal.setPos_doc_sap(Integer.parseInt(equipo.getDecoMaterialCodeSAP()));
				else
					dec_tar_sapdecoLocal.setPos_doc_sap(0);
				dec_tar_sapdecoLocal.setNum_material_sap(equipo.getDecoMaterialSAP());
				dec_tar_sapdecoLocal.setCentro_sap(equipo.getDecoPlantSAP());
				dec_tar_sapdecoLocal.setAlmacen_sap(equipo.getDecoStorageSAP());
				dec_tar_sapdecoLocal.setCod_lote_sap(equipo.getDecoBatchCodeSAP());
				dec_tar_sapdecoLocal.setUnd_medida_sap(equipo.getDecoMeasurementUnitSAP());
				dec_tar_sapdecoLocal.setCentr_cost_sap(equipo.getDecoCostCenterSAP());
				dec_tar_sapdecoLocal.setArea_func_sap(equipo.getDecoFuncAreaLongSAP());
				dec_tar_sapdecoLocal.setElement_pep_sap(equipo.getDecoPepElementSAP());
				dec_tar_sapdecoLocal.setFlag_mat_sap(equipo.getDecoFlagMatSAP());
				
				// se crean los datos de la Tarjeta
				Deco_Tarjeta_Info_SapLocal dec_tar_sap_cardLocal = null;
				if(crearTarjeta){
					dec_tar_sap_cardLocal = deco_tar_inf_sapLocalHome.create(equipo.getTarjeta(), peticionKey.peti_numero);
				}else{
					Deco_Tarjeta_Info_SapKey decoTarjetaSAPKey = new Deco_Tarjeta_Info_SapKey(equipo.getTarjeta(),peticionKey.peti_numero );
					dec_tar_sap_cardLocal = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(decoTarjetaSAPKey);
				}
					
				
				dec_tar_sap_cardLocal.setFec_cont_sap(equipo.getCardPostingDateSAP());
				dec_tar_sap_cardLocal.setClase_mov_sap(equipo.getCardMoveTypeSAP());
				if(equipo.getCardMaterialCodeSAP() != null)
					dec_tar_sap_cardLocal.setPos_doc_sap(Integer.parseInt(equipo.getCardMaterialCodeSAP()));
				else
					dec_tar_sap_cardLocal.setPos_doc_sap(0);
				dec_tar_sap_cardLocal.setNum_material_sap(equipo.getCardMaterialSAP());
				dec_tar_sap_cardLocal.setCentro_sap(equipo.getCardPlantSAP());
				dec_tar_sap_cardLocal.setAlmacen_sap(equipo.getCardStorageSAP());
				dec_tar_sap_cardLocal.setCod_lote_sap(equipo.getCardBatchCodeSAP());
				dec_tar_sap_cardLocal.setUnd_medida_sap(equipo.getCardMeasurementUnitSAP());
				dec_tar_sap_cardLocal.setCentr_cost_sap(equipo.getCardCostCenterSAP());
				dec_tar_sap_cardLocal.setArea_func_sap(equipo.getCardFuncAreaLongSAP());
				dec_tar_sap_cardLocal.setElement_pep_sap(equipo.getCardPepElementSAP());
				dec_tar_sap_cardLocal.setFlag_mat_sap(equipo.getCardFlagMatSAP());
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al crear el registro SAP: "+ e);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al crear el registro SAP: "+ e);
			}
			
		}
		/*RQ.8595 - mfmendez*/
		/**
		 * Metodo para completar los datos de SAP para un Deco de tipo DecoTarDTO
		 * @param deco_tar_inf_sapLocalHome, localHome de la tabla DECO_TARJETA_INFO_SAP
		 * @param equipment, el equipo que ya tiene seteados algunos datos
		 * @param idDeco, id del Deco que se debe buscar en base de datos
		 * @param idPeticion, id de la peticion para buscar el equipo correspondiente
		 * @return el equipo que entro con los datos de SAP seteados
		 */
		private DecoTarDTO setDatosSAPDeco(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, DecoTarDTO equipment, String idDeco, Long idPeticion){
			Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
			
			/*RQ.8595 - mfmendez*/
			// Datos del Deco
			try{			
				keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(idDeco, idPeticion);
				infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
				
				if(infoSAPTmp.getFec_cont_sap() != null)
					equipment.setDecoPostingDateSAP(infoSAPTmp.getFec_cont_sap());
				else
					equipment.setDecoPostingDateSAP("");
				
				if(infoSAPTmp.getClase_mov_sap() != null)
					equipment.setDecoMoveTypeSAP(infoSAPTmp.getClase_mov_sap());
				else
					equipment.setDecoMoveTypeSAP("");
				
				equipment.setDecoMaterialCodeSAP(Integer.toString(infoSAPTmp.getPos_doc_sap()));
				
				if(infoSAPTmp.getNum_material_sap() != null)
					equipment.setDecoMaterialSAP(infoSAPTmp.getNum_material_sap());
				else
					equipment.setDecoMaterialSAP("");
				
				if(infoSAPTmp.getCentro_sap() != null)
					equipment.setDecoPlantSAP(infoSAPTmp.getCentro_sap());
				else
					equipment.setDecoPlantSAP("");
				
				if(infoSAPTmp.getAlmacen_sap() != null)
					equipment.setDecoStorageSAP(infoSAPTmp.getAlmacen_sap());
				else
					equipment.setDecoStorageSAP("");
				
				if(infoSAPTmp.getCod_lote_sap() != null)
					equipment.setDecoBatchCodeSAP(infoSAPTmp.getCod_lote_sap());
				else
					equipment.setDecoBatchCodeSAP("");
				
				if(infoSAPTmp.getUnd_medida_sap() != null)
					equipment.setDecoMeasurementUnitSAP(infoSAPTmp.getUnd_medida_sap());
				else
					equipment.setDecoMeasurementUnitSAP("");
				
				if(infoSAPTmp.getCentr_cost_sap() != null)
					equipment.setDecoCostCenterSAP(infoSAPTmp.getCentr_cost_sap());
				else
					equipment.setDecoCostCenterSAP("");
				
				if(infoSAPTmp.getArea_func_sap() != null)
					equipment.setDecoFuncAreaLongSAP(infoSAPTmp.getArea_func_sap());
				else
					equipment.setDecoFuncAreaLongSAP("");
				
				if(infoSAPTmp.getElement_pep_sap() != null)
					equipment.setDecoPepElementSAP(infoSAPTmp.getElement_pep_sap());
				else
					equipment.setDecoPepElementSAP("");
				
				if(infoSAPTmp.getFlag_mat_sap() != null)
					equipment.setDecoFlagMatSAP(infoSAPTmp.getFlag_mat_sap());
				else
					equipment.setDecoFlagMatSAP("");
				
			} catch (FinderException e) {
				log.debug("No se encontraron Decos para deco con id: "+idDeco+". Y id de peticion: "+idPeticion);
				equipment.setDecoPostingDateSAP("");
				equipment.setDecoMoveTypeSAP("");
				equipment.setDecoMaterialCodeSAP("");
				equipment.setDecoMaterialSAP("");
				equipment.setDecoPlantSAP("");
				equipment.setDecoStorageSAP("");
				equipment.setDecoBatchCodeSAP("");
				equipment.setDecoMeasurementUnitSAP("");
				equipment.setDecoCostCenterSAP("");
				equipment.setDecoFuncAreaLongSAP("");
				equipment.setDecoPepElementSAP("");
				equipment.setDecoFlagMatSAP("");
			} catch (Exception e) {
				log.error("RecursosBABean: setDatosSAPEquipo: Se presento Error seteando los datos de SAP para un Deco. "+e);
			}
			/*FIN - RQ.8595 - mfmendez*/
			
			return equipment;
		}
		
		/**
		 * Metodo para completar los datos de SAP para una tarjeta de tipo DecoTarDTO
		 * @param deco_tar_inf_sapLocalHome, localHome de la tabla DECO_TARJETA_INFO_SAP
		 * @param tarjeta
		 * @param idCard
		 * @param idPeticion
		 * @return
		 */
		private DecoTarDTO setDatosSAPTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, DecoTarDTO tarjeta, String idCard, Long idPeticion){
			Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
			Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
			
			/*RQ.8595 - mfmendez*/
			// Datos de la tarjeta
			try{
				keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(idCard, idPeticion);
				infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
				
				if(infoSAPCard.getFec_cont_sap() != null)
					tarjeta.setCardPostingDateSAP(infoSAPCard.getFec_cont_sap());
				else
					tarjeta.setCardPostingDateSAP("");
				
				if(infoSAPCard.getClase_mov_sap() != null)
					tarjeta.setCardMoveTypeSAP(infoSAPCard.getClase_mov_sap());
				else
					tarjeta.setCardMoveTypeSAP("");
				
				tarjeta.setCardMaterialCodeSAP(Integer.toString(infoSAPCard.getPos_doc_sap()));
				
				if(infoSAPCard.getNum_material_sap() != null)
					tarjeta.setCardMaterialSAP(infoSAPCard.getNum_material_sap());
				else
					tarjeta.setCardMaterialSAP("");
				
				if(infoSAPCard.getCentro_sap() != null)
					tarjeta.setCardPlantSAP(infoSAPCard.getCentro_sap());
				else
					tarjeta.setCardPlantSAP("");
				
				if(infoSAPCard.getAlmacen_sap() != null)
					tarjeta.setCardStorageSAP(infoSAPCard.getAlmacen_sap());
				else
					tarjeta.setCardStorageSAP("");
				
				if(infoSAPCard.getCod_lote_sap() != null)
					tarjeta.setCardBatchCodeSAP(infoSAPCard.getCod_lote_sap());
				else
					tarjeta.setCardBatchCodeSAP("");
				
				if(infoSAPCard.getUnd_medida_sap() != null)
					tarjeta.setCardMeasurementUnitSAP(infoSAPCard.getUnd_medida_sap());
				else
					tarjeta.setCardMeasurementUnitSAP("");
				
				if(infoSAPCard.getCentr_cost_sap() != null)
					tarjeta.setCardCostCenterSAP(infoSAPCard.getCentr_cost_sap());
				else
					tarjeta.setCardCostCenterSAP("");
				
				if(infoSAPCard.getArea_func_sap() != null)
					tarjeta.setCardFuncAreaLongSAP(infoSAPCard.getArea_func_sap());
				else
					tarjeta.setCardFuncAreaLongSAP("");
				
				if(infoSAPCard.getElement_pep_sap() != null)
					tarjeta.setCardPepElementSAP(infoSAPCard.getElement_pep_sap());
				else
					tarjeta.setCardPepElementSAP("");
				
				if(infoSAPCard.getFlag_mat_sap() != null)
					tarjeta.setCardFlagMatSAP(infoSAPCard.getFlag_mat_sap());
				else
					tarjeta.setCardFlagMatSAP("");
				
			}catch (FinderException e) {
				log.debug("No se encontraron Tarjetas para Card con id: "+idCard+". Y id de peticion: "+idPeticion);
				tarjeta.setCardPostingDateSAP("");
				tarjeta.setCardMoveTypeSAP("");
				tarjeta.setCardMaterialCodeSAP("");
				tarjeta.setCardMaterialSAP("");
				tarjeta.setCardPlantSAP("");
				tarjeta.setCardStorageSAP("");
				tarjeta.setCardBatchCodeSAP("");
				tarjeta.setCardMeasurementUnitSAP("");
				tarjeta.setCardCostCenterSAP("");
				tarjeta.setCardFuncAreaLongSAP("");
				tarjeta.setCardPepElementSAP("");
				tarjeta.setCardFlagMatSAP("");
			} catch (Exception e) {
				log.error("RecursosBABean: setDatosSAPTarjeta: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
			}			
			/*FIN - RQ.8595 - mfmendez*/
			
			return tarjeta;
		}
		/*FIN - RQ.8595 - mfmendez*/
		
		public void reagendamientoAgendaSC(TR705S tr705s){
			log.info("Se ingresa al metodo de envio de mensaje reagendar: TR705S idAgenda: "+tr705s.getIdSchedule());
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				String strIdActuacion=tr705s.getIdSchedule();
				Agenda_scKey agendaSCKey = new Agenda_scKey(strIdActuacion);
				Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey); 
				agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
					
				Long idPeticionAux = agendaSCLocal.getPeti_numero();
				
					
				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				
				Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
				Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (tr705s.getId()));
				tmpAgendaSCLocal.setAgenda_sc(agendaSCLocal);
				tmpAgendaSCLocal.setPeticion(agendaSCLocal.getPeticion());
				tmpAgendaSCLocal.setXml(XMLUtilities.marshall (tr705s));
				
				
			}catch(FinderException e){
				log.debug("Error en la búsqueda de objeto en la peticion en alta reagendamiento de Agenda SC: "+e.toString());
			} catch (NamingException e) {
				log.debug("Error buscando objeto de BD en reagendamiento de Agenda SC"+e.toString());
			} catch(CreateException e){
				log.debug("Error creando objeto de BD en reagendamiento de Agenda SC"+e.toString());
			} catch (ATiempoAppEx e) {
				log.debug("Error creando objeto de BD en reagendamiento de Agenda SC"+e.toString());
			}
		}
		
		public Long consultarDisponibilidadAgendaSC (String petiNumero, String idSchedule, String fechaIni, String fechaFin, String actividad) throws ATiempoAppEx{
			log.info("Ingresa al metodo de envio de mensaje consultarDisponibilidad: TR703E idAgenda: "+idSchedule+" Número petición:"+petiNumero);
			
			try{
				Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome) HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME); 
				SimpleDateFormat sdf= new SimpleDateFormat(ComunInterfaces.formatoFechaTR703S);
				Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));	
				PeticionesDelegate pDelegate = new PeticionesDelegate();
				
				TR703E tr703e = new TR703E();
				tr703e.setIdSystemOrigin("ATIEMPO");
				tr703e.setIdSchedule(idSchedule);
				tr703e.setInitialDate(fechaIni);
				if(fechaFin!=null && !fechaFin.trim().equals("")){
					tr703e.setFinalDate(fechaFin);
				}else{
					String diasFinalizacion=pDelegate.recuperarParametroFromPropertiesBD(DIAS_FECHA_FINAL_DISPONIBILIDAD);
					int dias= Integer.parseInt(diasFinalizacion);
					GregorianCalendar gc=new GregorianCalendar();
					gc.setTime(new Date());
					gc.add(Calendar.DATE,dias);
					gc.set(Calendar.HOUR_OF_DAY,23);
					gc.set(Calendar.MINUTE,59);
					gc.set(Calendar.SECOND,0);
					tr703e.setFinalDate(sdf.format(gc.getTime()));
				}
				
				tr703e.setId(idCorrelativoMensaje.toString());
				tr703e.setDestination("ESB");
				tr703e.setSource("ATIEMPO");
				tr703e.setVersion("1.0");
				tr703e.setInterfaz("CONSULTA_DISP");
				
				Mensaje_agenda_scLocal mensajeAgendaSCLocal = mensajeAgendaSCLocalHome.create(idCorrelativoMensaje);
				mensajeAgendaSCLocal.setPeti_numero(new Long(petiNumero));
				mensajeAgendaSCLocal.setCod_actividad_generadora(actividad);
				mensajeAgendaSCLocal.setId_agenda(idSchedule);
				mensajeAgendaSCLocal.setTipo_mensaje(ComunInterfaces.TR703);
				
				Date dateRegistro=new Date();
				Timestamp timestampRegistro=new Timestamp(dateRegistro.getTime());
				SimpleDateFormat formatterRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mensajeAgendaSCLocal.setFecha_envio(formatterRegistro.format(timestampRegistro));

				ConsultaDisponibilidadSCMQ consultaDisponibilidadMQ = new ConsultaDisponibilidadSCMQ();
				consultaDisponibilidadMQ.enviarMensaje(tr703e);
				
				return idCorrelativoMensaje;
			}catch(ATiempoAppEx ex){
				log.debug("Se presento un error de tipo ATiempoAppEx en el método consultarDisponibilidadAgendaSC: "+ex.toString());
				return new Long("-1");
			}catch(NamingException ex){
				log.debug("Se presento un error de tipo NamingException en el método consultarDisponibilidadAgendaSC: "+ex.toString());
				return new Long("-1");
			}catch(CreateException ex){
				log.debug("Se presento un error de tipo CreateException en el método consultarDisponibilidadAgendaSC: "+ex.toString());
				return new Long("-1");
			}
		}
		
		public void respuestaDisponibilidadAgendaSC(TR703S tr703s)throws ATiempoAppEx {
			log.debug("Entro a método respuestaDisponibilidadAgendaSC, para la actuación:"+tr703s.getIdSchedule());
			
			try{
				Mensaje_agenda_scLocalHome mensajeAgendaSCLocalHome = (Mensaje_agenda_scLocalHome)HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				
				Mensaje_agenda_scKey mensajeAgendaSCKey = new Mensaje_agenda_scKey(new Long (tr703s.getId()));
				Mensaje_agenda_scLocal mensajeAgendaLocal = mensajeAgendaSCLocalHome.findByPrimaryKey(mensajeAgendaSCKey);
				
				Agenda_scKey agendaSCKey = new Agenda_scKey(tr703s.getIdSchedule());
				Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(mensajeAgendaLocal.getCod_actividad_generadora(),idAplicacion);
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeAgendaLocal.getCod_actividad_generadora());
				ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();
				
				ActividadEJBDTO actDto=recuperaActividadDeBandejaIntegrada(agendaSCLocal.getPeti_numero(),actividadKey.act_id,mensajeAgendaLocal.getCod_actividad_generadora());
				
				if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
					|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
					if(!tr703s.getError().equals("0")){
						log.debug("Error al recibir la disponibilidad de Agenda SC: "+tr703s.getErrorMessage());
						actDto.setObservacion("Error al recibir la disponibilidad de Agenda SC: "+tr703s.getErrorMessage());
						
						mensajeAgendaLocal.setError(tr703s.getError());
						String errorMessage=tr703s.getErrorMessage();
						if(errorMessage!=null && errorMessage.length()>24){
							errorMessage=errorMessage.substring(0,23);
						}
						mensajeAgendaLocal.setDesc_error(errorMessage);
						mensajeAgendaLocal.setCod_estado(new Integer(ComunInterfaces.estadoError));
					}else{
						mensajeAgendaLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));
						
						actDto.setObservacion("Se recibe respuesta de la disponibilidad de Agenda SC sin error");
						log.debug("Se recibe respuesta de la disponibilidad de Agenda SC sin error");
					}
				Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
				
				Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (tr703s.getId()));
				tmpAgendaSCLocal.setAgenda_sc(agendaSCLocal);
				tmpAgendaSCLocal.setPeticion(agendaSCLocal.getPeticion());
				tmpAgendaSCLocal.setXml(XMLUtilities.marshall (tr703s));
				}else{
					actDto.setObservacion("No se procesa el mensaje con id:"+tr703s.getId()+" porque la actuación:"+tr703s.getIdSchedule()+" esta cerrada");
				}
				
				actividadEJB.grabarSinTerminar(actDto);
			}catch(NamingException ex){
				log.error("Se presento un error de tipo NamingException en el método respuestaDisponibilidadAgendaSC: ",ex);
			}catch (FinderException ex){
				log.error("Se presento un error de tipo FinderException en el método respuestaDisponibilidadAgendaSC: ",ex);
			}catch(ATiempoAppEx ex){
				log.error("Se presento un error de tipo ATiempoAppEx en el método respuestaDisponibilidadAgendaSC: ",ex);
			}catch(TnProcesoExcepcion ex){
				log.error("Se presento un error de tipo TnProcesoExcepcion en el método respuestaDisponibilidadAgendaSC: ",ex);
			}catch(CreateException ex){
				log.error("Se presento un error de tipo CreateException en el método respuestaDisponibilidadAgendaSC: ",ex);
			}
		}
		
		public CaracteristicaPSLocal obtenerProductoServicio(Subpeticion_atisLocal subpeticion_atisLocal) throws NamingException, ATiempoAppEx {
			Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
			Subpeticion_atisKey subpeticion_atisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
			log.debug("subpeticion_atisKey. COD_PET_CD: "+subpeticion_atisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd+", COD_AGR_SUB_NU: "+subpeticion_atisKey.fk_agru_sub_cod_agr_sub_nu+", COD_SUB_CD: "+subpeticion_atisKey.cod_sub_cd);
			Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW),subpeticion_atisKey);
			Collection tmp = new ArrayList();
			Long ps= subpeticion_atisLocal.getCod_pro_ser_cd();
			try{
				Subpeticion_caracteristicasLocal sc=subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
				String valorCaracteristica=sc.getVal_ral_crc_cd();
				CaracteristicaPSLocalHome cps =(CaracteristicaPSLocalHome)HomeFactory.getHome(CaracteristicaPSLocalHome.JNDI_NAME);
				log.debug("Valor caracteristica: "+valorCaracteristica);
				return cps.findByCaracteristica(valorCaracteristica,ps);
			}catch (FinderException e) { 
				log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica"+e.getMessage());
				return null;
			}
			
		}

		
		/**
		 * Calcula la cantidad de ip fijas a partir de la mascara
		 * @param mascaraActualCliente
		 * @return cantidad Ip Fijas
		 */
		private String calcularCantidadIpFijas(String mascaraActualCliente){
			Double cantidadIpFijas = new Double(0);			
			String[] tokens = null;
			if(mascaraActualCliente!=null){
				tokens = mascaraActualCliente.split("\\.");
				int octetos = tokens.length;
				if(octetos == 4){
					String ultimoOcteto =  tokens[3];
					System.out.println("ultimo octecto " + ultimoOcteto);
					String val = Integer.toString(Integer.parseInt(ultimoOcteto),2);
					System.out.println("binario " + val);
					char[] toCharArray = val.toCharArray();
					char[] ipFijas =  new char[toCharArray.length];
					int j = 0;
					for(int i = toCharArray.length-1;i!=0;i--,j++ ){
						if(toCharArray[i]=='0'){
							ipFijas[j] =  toCharArray[i];
							System.out.println(ipFijas[j]);
						}else{
							break;
						}				
					}
					String ipfijass = new String(ipFijas).toString();
					System.out.println("numero de ceros " + j);
					if(j>0){
						cantidadIpFijas = new Double(Math.pow(2,j));
						System.out.println("Numero de ipfijas " + cantidadIpFijas);					
					}						
				}
			}			
			return String.valueOf(cantidadIpFijas.intValue());
		}
		
	public void enviarConfiguracionWebFilterOptenet(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim) throws ATiempoAppEx{
			log.debug("Entro a enviarConfiguracionWebFilterOptenet de la peticion:"+numeroPeticion);
			
			try{
				Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));				
				
			SubsegmentoLocalHome subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			SegmentoLocalHome segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME); 
			
				PeticionKey key = new PeticionKey (numeroPeticion) ;
	            PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey (key) ;
				Long idPeticionAtis = ((Peticion_atisKey) peticionLocal.getFk_01_pet_atis ().getPrimaryKey ()).cod_pet_cd ;
				
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
				
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoBALocal.setFecha_envio(df.format(new java.util.Date()));
				mensajeEstadoBALocal.setMensaje_estado(mensajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(codActividad);
				
				
				
				InformacionAdslDTO informacionAdslDTO = obtenerDatosAdsl(numeroPeticion);
				
				Collection listaPsPet=peticionLocal.getProducto_servicio_peticion();
				Iterator listaPsPetIt=null;
				Long operacionColmercial=new Long(0);
				Long psWebFilterId=new Long(0);
				String listaPsWebFilter=VpistbbaConfig.getVariable("PS_WEB_FILTER");
				String[] arrayPsWebFilter=listaPsWebFilter.split(",");
				
				for(listaPsPetIt=listaPsPet.iterator();listaPsPetIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaPsPetIt.next();
					Producto_servicioLocal producto_servicioLocal2 = (Producto_servicioLocal) producto_servicio_peticionLocal.getProducto_servicio();
					Producto_servicioKey producto_servicioKey = (Producto_servicioKey)producto_servicioLocal2.getPrimaryKey();
					for(int i=0;i<=arrayPsWebFilter.length-1;i++){
						if(arrayPsWebFilter[i].equals(producto_servicioKey.ps_id.toString())){
							/*RQ.6142 - mfmendez - WebService Aula - WebFilterOptenet*/
						String ocSVA = validaOperacionComercialSVA(act, act.getNumeroPeticion());
						
						if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA)){				
							operacionColmercial = new Long(ComunInterfaces.OC_MODIFICACION_SVA);
							
							PsVsOcVO psPrioridadAlta = devuelvePSPrioridadAltaSVA(act);
							
							if(psPrioridadAlta != null && psPrioridadAlta.getPsId() != null){
								psWebFilterId = psPrioridadAlta.getPsId();
							}else{
								psWebFilterId = producto_servicioKey.ps_id;
							}	
						}else{
							Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();	
							
							String opCoSVADB = operacion_comercialLocal.getOpco_sva();
							
							if(opCoSVADB != null && opCoSVADB.equals(ComunInterfaces.VALOR_MODIFICACION_OPCO_SVA)){
								operacionColmercial = new Long(ComunInterfaces.OC_MODIFICACION_SVA);
							}else{
								operacionColmercial=((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;		
							}
	
							psWebFilterId=producto_servicioKey.ps_id;
						}
						/*FIN RQ.6142 - mfmendez - WebService Aula - WebFilterOptenet*/

							log.debug("El ps "+psWebFilterId+" de Web filter tiene la siguiente OC: "+operacionColmercial);
							break;
						}
						
					}
					
				}
				
				TR048E tr048e=new TR048E();
				tr048e.setAtisRequestNumber(idPeticionAtis.toString());
				tr048e.setComercialOperation(operacionColmercial.toString());
				tr048e.setDestination("ESB");
				String email="";
				String usuario="";
				if(informacionAdslDTO.getDireccElectronica()!=null){
					email=informacionAdslDTO.getDireccElectronica();
				}
				if(informacionAdslDTO.getFatherEmail()!=null){
					usuario=informacionAdslDTO.getFatherEmail();
				}
				tr048e.setEmail(email);
			
			//CR-8607  Yumbleiner Calderon -- Campos necesarios para  Web Service con Optenet
			
			SubsegmentoKey subsegmentoKey = new SubsegmentoKey(peticionLocal.getCod_sbg_cta_cd());
			SubsegmentoLocal subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
			SegmentoKey segmentoKey = new SegmentoKey(peticionLocal.getCod_sgm_cta_cd());
			SegmentoLocal segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
			tr048e.setSegment(segmentoLocal.getSegm_descripcion());
			tr048e.setSubSegment(subsegmentoLocal.getDescripcion());

			Integer phoneNumber = null;
			if (peticionLocal.getNum_ide_nu_stb() != null && !peticionLocal.getNum_ide_nu_stb().equals("")){
				phoneNumber = new Integer(peticionLocal.getNum_ide_nu_stb());
			}
			log.debug("Se setea a la TR023E el valor phoneNumber: " + phoneNumber.intValue());
			tr048e.setPhoneNumber(phoneNumber.intValue());

			
				tr048e.setId(idCorrelativoMensaje.toString());
				tr048e.setInterfaz("AP_OPTENET");//RES_AP_OPTENET
				tr048e.setPassword("");
				tr048e.setProductService(psWebFilterId.toString());
				tr048e.setSource("ATIEMPO");
				tr048e.setUsername(usuario);
				tr048e.setVersion("1.0");
				
				ConfiguracionWebFilterOptenetServicioMQ configuracionWebFilterOptenetServicioMQ = new ConfiguracionWebFilterOptenetServicioMQ();
				configuracionWebFilterOptenetServicioMQ.enviarMensaje(tr048e);
				
			}catch(Exception e){
				log.debug("Se presento un problema en el método enviarConfiguracionWebFilterOptenet: "+e);
				e.printStackTrace();
			}
		}
		
		public void recibirConfiguracionWebFilterOptenet(TR048S tr048s) throws ATiempoAppEx{
			log.debug("Entro a recibirConfiguracionWebFilterOptenet de la peticion:"+tr048s.getAtisRequestNumber());
			BackendExecution bExecution = null;
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr048s, log);
				bExecution.setNumPetAtiempo(new Long(tr048s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr048s.getId());
				bExecution.startOperation();
				
				Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				
				Mensaje_estado_baKey mensaje_estado_baKey = new Mensaje_estado_baKey(Long.valueOf(tr048s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(mensaje_estado_baKey);
				mensajeEstadoBaLocal.setMensaje_estado(mensajeOk);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				ErrorLegadoLocal errorLegado = getErrorLegado(tr048s.getErrorCode(),"TR048S");
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				if(!tr048s.getErrorCode().equals("0")){
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr048s.getErrorMessage());
							actividadEJB.terminar(actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
					log.debug("Error al recibir configuración WEB filter optenet, se deriva a "+bandeja+": "+tr048s.getErrorMessage());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(701), actDto.getIdActividadFlujo());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error al recibir configuración WEB filter optenet, se deriva a "+bandeja+": " + " Respuesta:  " + tr048s.getErrorCode() + " -  " +  tr048s.getErrorMessage(),true);
				}else{
					actDto.setObservacion("Se recibe respuesta de configuración WEB filter optenet sin error: " + tr048s.getErrorCode() + " -  " +  tr048s.getErrorMessage(),true);
					log.debug("Se recibe respuesta de configuración WEB filter optenet sin error: " + tr048s.getErrorCode() + " -  " +  tr048s.getErrorMessage());
				}
				actividadEJB.terminar(actDto);
								
			}catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.warn("FinderException",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.warn("NumberFormatException",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);

			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.warn("TnProcesoExcepcion",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} 
			catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.debug("Exception",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}	
			finally{  
				bExecution.endAll();
			}
		}
		

		/* (non-Javadoc)
		 * idrincon - req 1038
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#esTrasladoTv(java.lang.Long)
		 */
		public boolean esTrasladoTv(Long idPeticion) throws ATiempoAppEx {
			boolean resultado = false;
			try {
				InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
				
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
				{
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
					
					boolean tipoOpCoAlta = (psDto.getTipoOperacionComercial()!=null) && (psDto.getTipoOperacionComercial().equalsIgnoreCase("A"));
					boolean tasladoOpCo = (psDto.getTrasladoOperCom()!=null) && (psDto.getTrasladoOperCom().equalsIgnoreCase("T"));
					
					if ( ( psDto.getIdFaps().intValue() == familiaTV || psDto.getIdFaps().intValue() ==  familiaPcTV ) && tipoOpCoAlta && tasladoOpCo) {
						log.debug("Operacion Comercial..: "+ psDto.getTipoOperacionComercial()+" Peticion..:" + idPeticion);
						resultado= true;
					}
				}
			} catch (ATiempoAppEx e) {
				log.debug("Error validando si tiene Traslado.. peticion: "+idPeticion,e);
				e.printStackTrace();
			}
			return resultado;
		}
		
		/* (non-Javadoc)
		 * idrincon - req 3183
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionTeraBox()
		 */
	public void enviarConfiguracionTeraBox(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim ) throws ATiempoAppEx {
			log.debug("Entro a enviarConfiguracionTeraBox de la peticion: "+numeroPeticion);
			
			try {
				String email = "";
				String country = "";
				String usuario = "";
				Long telephone = new Long(0);
				Long operacionComercial = new Long(0);
				Long psTeraBoxId = new Long(0);
				String listaPsTeraBox = VpistbbaConfig.getVariable("PS_TERA_BOX");
				String[] arrayPsTeraBox = listaPsTeraBox.split(",");
				country = VpistbbaConfig.getVariable("COUNTRY");
				String accountExpiration = VpistbbaConfig.getVariable("ACCOUNTEXPIRATION");
				Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = null; 
					
				PeticionKey key = new PeticionKey(numeroPeticion) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				Long idPeticionAtis = ((Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey()).cod_pet_cd;
				LocalidadLocal  localidadLocal =(LocalidadLocal)peticionLocal.getFk_01_localidad();
				
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey((new Mensaje_estadoKey(new Integer(estadoEspera))));
				Mensaje_estado_baLocal mensaje_estado_baLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
				mensaje_estado_baLocal.setPeticion(peticionLocal);
				
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				
				mensaje_estado_baLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensaje_estado_baLocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensaje_estado_baLocal.setFecha_envio(df.format(new Date()));
				mensaje_estado_baLocal.setMensaje_estado(mensajeEspera);
				mensaje_estado_baLocal.setCod_actividad_generadora(codActividad);
				
				InformacionAdslDTO informacionAdslDTO = obtenerDatosAdsl(numeroPeticion);
				Collection listaPsPet = peticionLocal.getProducto_servicio_peticion();
				
				/*Se busca uno a uno los ps's en la peticion y se compara con los ps's de terabox
				 * si en la peticion hay un ps de migracion se detien la busqueda y se retorna la operacion
				 * comercial de ese ps, si no es de migracion se continua la busqueda ya que en la peticion
				 * solo hay un ps de tera box
				 */
				boolean flag=false;
			    for (Iterator iter = listaPsPet.iterator(); iter.hasNext() && !flag ;) {
			    	Producto_servicio_peticionLocal  producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
			    	Producto_servicioLocal producto_servicioLocal2 = (Producto_servicioLocal)producto_servicio_peticionLocal.getProducto_servicio();
			    	Producto_servicioKey producto_servicioKey = (Producto_servicioKey)producto_servicioLocal2.getPrimaryKey();
			    	for (int i = 0; i < arrayPsTeraBox.length; i++) {
			    		if(arrayPsTeraBox[i].equals(producto_servicioKey.ps_id.toString())){
			    			Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
			    			if(operacionComercial.intValue() != bajaMigracionPS){//altaMigracionPS){
		    				
		    				/*RQ.6142 - mfmendez - WebService Aula - TeraBox*/
		    				String ocSVA = validaOperacionComercialSVA(act, act.getNumeroPeticion());
							
							if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA)){				
								operacionComercial = new Long(ComunInterfaces.OC_MODIFICACION_SVA);

								PsVsOcVO psPrioridadAlta = devuelvePSPrioridadAltaSVA(act);
								if(psPrioridadAlta != null && psPrioridadAlta.getPsId() != null){
									psTeraBoxId = psPrioridadAlta.getPsId();
								}else{
									psTeraBoxId = producto_servicioKey.ps_id;
								}				
							}else{								
								String opCoSVADB = operacion_comercialLocal.getOpco_sva();
								
								if(opCoSVADB != null && opCoSVADB.equals(ComunInterfaces.VALOR_MODIFICACION_OPCO_SVA)){
									operacionComercial = new Long(ComunInterfaces.OC_MODIFICACION_SVA);
								}else{
									operacionComercial=((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;
								}									
								psTeraBoxId = producto_servicioKey.ps_id;
							}
							
		    				flag = true;
		    				/*FIN RQ.6142 - mfmendez - WebService Aula - TeraBox*/
			    			} 
			    		}
			    	}
			    }
				
				TR051E tr051e = new TR051E();
				
				tr051e.setId(idCorrelativoMensaje.toString());
				tr051e.setSource("ATIEMPO");
				tr051e.setDestination("ESB");
				tr051e.setVersion("1.0");
				tr051e.setInterfaz("AP_TERABOX");
				
				LocalidadKey localidadKey=(LocalidadKey) peticionLocal. getFk_01_localidad().getPrimaryKey();
				
				tr051e.setZip(localidadKey.cod_loc);
				
				if(informacionAdslDTO.getDireccElectronica() != null){
					email=informacionAdslDTO.getDireccElectronica();
				}
				
				if(informacionAdslDTO.getFatherEmail()!=null ){
					usuario=informacionAdslDTO.getFatherEmail();
				}
				
				if(peticionLocal.getNum_doc_cli_cd() != null ){
					tr051e.setPassword(peticionLocal.getNum_doc_cli_cd());
				}else{
					tr051e.setPassword("");
				}
				
				if(peticionLocal.getNom_ds() != null ){
					tr051e.setFirstname(peticionLocal.getNom_ds());
				}else{
					tr051e.setFirstname("");
				}
				
				if( peticionLocal.getRecursos_linea_basica().size()>0 ){
					
					for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); )
					{	
						recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
					}
					
					if( recursos_linea_basicaLocal.getTelefono_asg() != null ){
						 telephone = recursos_linea_basicaLocal.getTelefono_asg();
					}
					
				}
				
				tr051e.setTelephone(telephone);
				tr051e.setProductService(psTeraBoxId.toString());
				tr051e.setAccountExpiration(accountExpiration);
				tr051e.setCountry(country);
				tr051e.setCity(localidadLocal.getNombre_localidad());
				tr051e.setEmail(email);
				tr051e.setUsername(usuario);
				tr051e.setLastname(peticionLocal.getPri_ape_ds());
				tr051e.setState("Other");
				tr051e.setComercialOperation(operacionComercial.toString());
				tr051e.setAddress(peticionLocal.getNom_cal_ds()+" "+peticionLocal.getNum_cal_nu()+" "+peticionLocal.getDsc_cmp_pri_ds()+" "+peticionLocal.getNom_slo_no());
				
				ConfiguracionTeraBoxMQ configuracionTeraBoxMQ = new ConfiguracionTeraBoxMQ();
				configuracionTeraBoxMQ.enviarMensaje( tr051e );
				
			} catch (FinderException fe) {
				log.debug("RecursosBABean: enviarConfiguracionTeraBox: "+fe);
				fe.printStackTrace();
			} catch (CreateException ce) {
				log.debug("RecursosBABean: enviarConfiguracionTeraBox: "+ce);
				ce.printStackTrace();
			} 
				
		}

		/* (non-Javadoc)
		 * idrincon - req 3183
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recibirConfiguracionTeraBox(co.com.telefonica.atiempo.interfaces.atiempo.TR051S)
		 */
		public void recibirConfiguracionTeraBox(TR051S tr051s) throws ATiempoAppEx {
			log.debug("Entro a recibirConfiguracionTeraBox ");
			BackendExecution bExecution = null;
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr051s, log);
				bExecution.setIdMensajeOp(tr051s.getId());
				bExecution.startOperation();
				
				Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				
				Mensaje_estado_baKey mensaje_estado_baKey = new Mensaje_estado_baKey(Long.valueOf(tr051s.getId()));
				Mensaje_estado_baLocal mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(mensaje_estado_baKey);
				mensajeEstadoBaLocal.setMensaje_estado(mensajeOk);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
				
				PeticionKey peticionKey = ( PeticionKey ) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
				ActividadEJBDTO actividadEJBDTO = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
				ErrorLegadoLocal errorLegado = getErrorLegado(tr051s.getError(),"TR051S");
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				if(!tr051s.getError().equals("0")){
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actividadEJBDTO.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr051s.getErrorMessage());
							actividadEJB.terminar(actividadEJBDTO);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
					log.debug("Error al recibir configuración terabox, se deriva a "+bandeja+": "+tr051s.getErrorMessage());
					insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(701), actividadEJBDTO.getIdActividadFlujo());
					actividadEJBDTO.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actividadEJBDTO.setObservacion("Error al recibir configuración terabox, se deriva a "+bandeja+": "+" Respuesta: "+tr051s.getErrorMessage()+" - "+tr051s.getError(), true);
				}else{
					actividadEJBDTO.setObservacion("Se recibe respuesta de configuración terabox sin error: "+tr051s.getError()+" - "+tr051s.getErrorMessage());
					log.debug("Se recibe respuesta de configuración terabox sin error: " + tr051s.getError() + " - "+tr051s.getErrorMessage());
				}
				actividadEJB.terminar(actividadEJBDTO);
				
			} catch (FinderException fe) {
				bExecution.setErrorOp(true);
				log.warn("RecursosBABean: recibirConfiguracionTeraBox: FinderException: ",fe);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, fe);
			} catch (TnProcesoExcepcion tnpe) {
				bExecution.setErrorOp(true);
				log.warn("RecursosBABean: recibirConfiguracionTeraBox:TnProcesoExcepcion",tnpe);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, tnpe);
			} catch (ATiempoAppEx e) {
				bExecution.setErrorOp(true);
				log.warn("RecursosBABean: recibirConfiguracionTeraBox:TnProcesoExcepcion",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} catch (NamingException e) {
				bExecution.setErrorOp(true);
				log.warn("RecursosBABean: recibirConfiguracionTeraBox:TnProcesoExcepcion",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				log.warn("RecursosBABean: recibirConfiguracionTeraBox:TnProcesoExcepcion",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}finally{
				bExecution.endAll();
			}
		}
		
		/**
		 * Fernando: Funcion para devolver los datos de una banda ancha para enviar el mensaje TR049S
		 * @return
		 */
		public TR049EWideBand getWideBand(PeticionLocal peticionLocal){
			TR049EWideBand bandaAncha = new TR049EWideBand();	
			try{
				if(peticionLocal != null){
					Collection collectionBA = peticionLocal.getRecursos_ba();
					if (collectionBA != null && collectionBA.size() > 0) {
						for (Iterator iterator = collectionBA.iterator(); iterator.hasNext();) {
							Recursos_baLocal recursos_baLocal = (Recursos_baLocal) iterator.next();
							
							/**
							 * Datos de linea basica
							 */
							bandaAncha = new TR049EWideBand();
							if(recursos_baLocal != null && recursos_baLocal.getOds_sigres() != null)
								bandaAncha.setTicketNumber(recursos_baLocal.getOds_sigres());
							else
								bandaAncha.setTicketNumber("");
							
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_dslam_nueva() != null)
								bandaAncha.setIp(recursos_baLocal.getDir_ip_dslam_nueva());
							else
								bandaAncha.setIp("");
							
							if(recursos_baLocal != null && recursos_baLocal.getFrame_nuevo() != null)
								bandaAncha.setSubrack(recursos_baLocal.getFrame_nuevo());
							else
								bandaAncha.setSubrack("");
							
							if(recursos_baLocal != null && recursos_baLocal.getSlot_nuevo() != null)
								bandaAncha.setSlot(recursos_baLocal.getSlot_nuevo());
							else
								bandaAncha.setSlot("");
								
							if(recursos_baLocal != null && recursos_baLocal.getPuerto_nuevo() != null)
								bandaAncha.setPortId(recursos_baLocal.getPuerto_nuevo());
							else
								bandaAncha.setPortId("");
							
							if(recursos_baLocal != null && recursos_baLocal.getVpivci_nuevo() != null)
								bandaAncha.setVpiVci(recursos_baLocal.getVpivci_nuevo());
							else
								bandaAncha.setVpiVci("");
								
							if(recursos_baLocal != null && recursos_baLocal.getVpivci_red_nuevo() != null)
								bandaAncha.setVpiVciNetwork(recursos_baLocal.getVpivci_red_nuevo());
							else
								bandaAncha.setVpiVciNetwork("");
							
							if(recursos_baLocal != null && recursos_baLocal.getPost_nuevo() != null)
								bandaAncha.setPots(recursos_baLocal.getPost_nuevo());
							else
								bandaAncha.setPots("");
							
							if(recursos_baLocal != null && recursos_baLocal.getAdsl_nuevo() != null)
								bandaAncha.setAdsl(recursos_baLocal.getAdsl_nuevo());
							else
								bandaAncha.setAdsl("");
							
							if(recursos_baLocal != null && recursos_baLocal.getMasc_lan_nueva() != null)
								bandaAncha.setIpLanMask(recursos_baLocal.getMasc_lan_nueva());
							else
								bandaAncha.setIpLanMask("");
							
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_lan_nueva() != null)
								bandaAncha.setIpLan(recursos_baLocal.getDir_ip_lan_nueva());
							else
								bandaAncha.setIpLan("");
								
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_wan_nueva()!= null)
								bandaAncha.setIpWan(recursos_baLocal.getDir_ip_wan_nueva());
							else
								bandaAncha.setIpWan("");
							
							if(recursos_baLocal != null && recursos_baLocal.getPort_modification_flag() != null)
								bandaAncha.setPortModificationFlag(recursos_baLocal.getPort_modification_flag());
							else
								bandaAncha.setPortModificationFlag("");
							
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_dslam_actual() != null)
								bandaAncha.setPreviousIp(recursos_baLocal.getDir_ip_dslam_actual());
							else
								bandaAncha.setPreviousIp("");
							
							if(recursos_baLocal != null && recursos_baLocal.getFrame_actual() != null)
								bandaAncha.setPreviousSubrack(recursos_baLocal.getFrame_actual());
							else
								bandaAncha.setPreviousSubrack("");
							
							if(recursos_baLocal != null && recursos_baLocal.getSlot_actual() != null)
								bandaAncha.setPreviousSlot(recursos_baLocal.getSlot_actual());
							else
								bandaAncha.setPreviousSlot("");
								
							if(recursos_baLocal != null && recursos_baLocal.getPuerto_actual() != null)
								bandaAncha.setPreviousPortId(recursos_baLocal.getPuerto_actual());
							else 
								bandaAncha.setPreviousPortId("");
							
							if(recursos_baLocal != null && recursos_baLocal.getVpivci_actual() != null)
								bandaAncha.setPreviousVpiVci(recursos_baLocal.getVpivci_actual());
							else
								bandaAncha.setPreviousVpiVci("");
								
							if(recursos_baLocal != null && recursos_baLocal.getVpivci_red_actual() != null)
								bandaAncha.setPreviousVpiVciNetwork(recursos_baLocal.getVpivci_red_actual());
							else
								bandaAncha.setPreviousVpiVciNetwork("");
							
							if(recursos_baLocal != null && recursos_baLocal.getPost_actual() != null)
								bandaAncha.setPreviousPots(recursos_baLocal.getPost_actual());
							else
								bandaAncha.setPreviousPots("");
								
							if(recursos_baLocal != null && recursos_baLocal.getAdsl_actual() != null)
								bandaAncha.setPreviousAdsl(recursos_baLocal.getAdsl_actual());
							else
								bandaAncha.setPreviousAdsl("");
													
							if(recursos_baLocal != null && recursos_baLocal.getMasc_actual() != null)
								bandaAncha.setPreviousIpLanMask(recursos_baLocal.getMasc_actual());
							else
								bandaAncha.setPreviousIpLanMask("");
							
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_lan_actual() != null)
								bandaAncha.setPreviousIpLan(recursos_baLocal.getDir_ip_lan_actual());
							else
								bandaAncha.setPreviousIpLan("");
							
							if(recursos_baLocal != null && recursos_baLocal.getDir_ip_wan_actual() != null)
								bandaAncha.setPreviousIpWan(recursos_baLocal.getDir_ip_wan_actual());	
							else
								bandaAncha.setPreviousIpWan("");																	
						}
					}					
				}
			}catch(Exception e){
				log.debug("RecursosBABean: getWideBand: Error obteniendo datos de banda ancha: "+e);
			}
						
			return bandaAncha;
		}

		/**
		 * Itera por la coleccion de quiebres que trae la tr 711s por caga quiebre consulta en la bd por el cod del quiebere  y trae el id
		 * de los pcs que se deben quebrar deacuerdo al cod del la familia.
		 * @ idrincon
		 * req 4990
		 * @param collectionBreaks
		 * @param petiNumero
		 * @param productoServicioPeticionCollection
		 * @param codigoActividad
		 */
		public void setQuiebrePcAgendaSc( Collection collectionBreaks, Long petiNumero, Collection productoServicioPeticionCollection, String codActividad ){
			
			try {
				
				dbSeq = new DBManager ();
				dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
				df = new SimpleDateFormat ("yyyy/MM/dd") ;
				UsuarioLocalHome usuarioHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				UsuarioLocal usuarioLocal = usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(1)));
				
				if(estado_ps_peticionHome == null){
					estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
				}
				
				if(catalogo_causalHome == null){
					catalogo_causalHome = (Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
				}
				
				if(estado_psHome == null){
					estado_psHome = (Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
				}

				if(causal_peticionHome==null){
					causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
				}
				
				ActividadLocal actividadLocal = null;
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(codActividad,new Long (3));
				
				for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
					
					TR711SBreakPairs breakPairs = (TR711SBreakPairs) iter.next();
					String breakCode = breakPairs.getBreakCode();
					String breakFamily = breakPairs.getFamily();
					String observations = breakPairs.getObservations()!=null && breakPairs.getObservations().length() > 255 ? breakPairs.getObservations().substring(0,255) : breakPairs.getObservations();
					

					Homologacion_qb_Agd_scLocalHome agd_scLocalHome = (Homologacion_qb_Agd_scLocalHome) HomeFactory.getHome(Homologacion_qb_Agd_scLocalHome.JNDI_NAME);
					Homologacion_qb_Agd_scKey agd_scKey = new Homologacion_qb_Agd_scKey( breakFamily );
					Homologacion_qb_Agd_scLocal agd_scLocal = agd_scLocalHome.findByPrimaryKey( agd_scKey );
					String[] arrayAgd_scLocal = agd_scLocal.getHq_agd_id_familia_ps().split("-");
					
					for (int i = 0; i < arrayAgd_scLocal.length; i++) {
						for( Iterator iterator = productoServicioPeticionCollection.iterator(); iterator.hasNext();){
							Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)iterator.next();
							Producto_servicio_peticionKey producto_servicio_peticionKey = (Producto_servicio_peticionKey)producto_servicio_peticionLocal.getPrimaryKey();
							Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey)producto_servicio_peticionLocal.getFamiliaKey();
							if( familia_producto_servicioKey.faps_id.equals( new Long(arrayAgd_scLocal[i]))){

								Collection listaEstadoPsPeti = producto_servicio_peticionLocal.getEstado_ps_peticion();
								
								if( listaEstadoPsPeti.size() > 0 ){
									Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey( new Long( breakCode ) );
									Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey( catalogo_causalKey );
									
									//Tengo una causa asociada tengo que actualizar
									Estado_ps_peticionLocal estado_ps_peticionLocal = (Estado_ps_peticionLocal) listaEstadoPsPeti.iterator().next();
									estado_ps_peticionLocal.setCod_causal(catalogo_causalKey.cod_causal);
									estado_ps_peticionLocal.setCod_estado_cierre(new Integer(3));
									estado_ps_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
								    estado_ps_peticionLocal.setCod_actividad(((ActividadKey)actividadLocal.getPrimaryKey()).act_id);
									
									Estado_psKey estado_psKey=new Estado_psKey( new Long( 3 ) );
									Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
									long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
									Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estado_ps_peticionLocal,usuarioLocal);
									
									causal_peticionLocal.setFecha_inicio(new Fecha().getFechaconFormato(9));
									causal_peticionLocal.setFecha_termino(new Fecha().getFechaconFormato(9));
									causal_peticionLocal.setNovedad(observations);
									causal_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );
									if(catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_SIRS)
											||catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_AGENDA)){
										PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
										peticionLocal.setTipoErrorId(catalogo_causalKey.cod_causal);
									}
										
									
								}else{
									Catalogo_causalKey catalogo_causalKey = new Catalogo_causalKey( new Long( breakCode ) );
									Catalogo_causalLocal catalogo_causalLocal = catalogo_causalHome.findByPrimaryKey( catalogo_causalKey );
									
									long correlativo = dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
									Estado_ps_peticionLocal estado_ps_peticionLocal = estado_ps_peticionHome.create( new Long(correlativo), producto_servicio_peticionLocal.getProducto_servicio(), producto_servicio_peticionLocal );
									estado_ps_peticionLocal.setCod_causal( catalogo_causalKey.cod_causal );
									estado_ps_peticionLocal.setCod_estado_cierre( new Integer(3) );
									estado_ps_peticionLocal.setNovedad( catalogo_causalLocal.getDescripcion_causal() );
									estado_ps_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );//va el codigo de la actividad
									
									Estado_psKey estado_psKey = new Estado_psKey( new Long( 3 ) );
									Estado_psLocal estado_psLocal = estado_psHome.findByPrimaryKey( estado_psKey );
									
									long id_causal_peticion = dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
									Causal_peticionLocal causal_peticionLocal = causal_peticionHome.create( new Long(id_causal_peticion), catalogo_causalLocal , estado_psLocal, estado_ps_peticionLocal,usuarioLocal );
									
									causal_peticionLocal.setFecha_inicio( new Fecha().getFechaconFormato(9) );
									causal_peticionLocal.setFecha_termino( new Fecha().getFechaconFormato(9) );
									causal_peticionLocal.setNovedad(observations);
									causal_peticionLocal.setCod_actividad( ((ActividadKey)actividadLocal.getPrimaryKey()).act_id );
									if(catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_SIRS)
											||catalogo_causalKey.cod_causal.equals(ComunInterfaces.CUASAL_AGENDA)){
										PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
										peticionLocal.setTipoErrorId(catalogo_causalKey.cod_causal);
									}
								}
							}
							
						}
					}
				}
				
			} catch ( NamingException ne ) {
				log.debug("RecurosBABean setQuiebrePcAgendaSc: "+ne);
			} catch ( FinderException fe ) {
				log.debug("RecurosBABean setQuiebrePcAgendaSc: "+fe);
			} catch (CreateException ce) {
				log.debug("RecurosBABean setQuiebrePcAgendaSc: "+ce);
			} 
		}
		
		public boolean esPeticionEnVuelo(Long petiNumero) {
			try {
				Producto_servicio_en_vueloLocalHome producto_servicio_en_vueloLocalHome = (Producto_servicio_en_vueloLocalHome) HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
				Collection peticionesEnVueloColection = producto_servicio_en_vueloLocalHome.findByPeti_Numero(petiNumero);
				if (peticionesEnVueloColection.size() > 0) {
					return true;	
				}	
			} catch (NamingException e) {
				log.debug("RecursosBABean: esPeticionEnVuelo: Error al crear la instancia producto_servicio_en_vueloLocalHome "+ e);	
			} catch (FinderException fe) {
				log.debug("RecursosBABean: esPeticionEnVuelo: FinderException: No se encontraron datos: "+ fe);
				return false;
			}
			return false;
		}
		
		private String recuperarQuiebresAgendaSc( Collection collectionBreaks ){
			String strReturn = "";
			if(collectionBreaks != null && collectionBreaks.size() > 0){
				for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
					TR711SBreakPairs breakPairs = (TR711SBreakPairs) iter.next();
					strReturn += breakPairs.getFamily() +"-"+ breakPairs.getBreakCode()+",";
				}
				
				return strReturn.substring(0, strReturn.length()-1); 
			}
			return strReturn;
		}
		
		private String recuperarObservacionesQuiebresAgendaSc( Collection collectionBreaks ){
			String strReturn = "";
			String observation = "";
			Set conjunto = new HashSet();
			if(collectionBreaks != null && collectionBreaks.size() > 0){
				for (Iterator iter = collectionBreaks.iterator(); iter.hasNext();) {
					TR711SBreakPairs breakPairs = (TR711SBreakPairs) iter.next();
					conjunto.add(breakPairs.getObservations());
				}
				
				if(conjunto.size() > 0){
					for (Iterator iter = conjunto.iterator(); iter.hasNext();) {
						String obs = (String) iter.next();
						strReturn += obs+"."; 
					}
				}
			}
			return strReturn;
		}
		
		public boolean esPostventa (PeticionLocal peticionLocal){
			boolean esPostventa = true;
			Collection psPeticion = peticionLocal.getProducto_servicio_peticion();
			for (Iterator psPeticionIterator = psPeticion.iterator(); psPeticionIterator.hasNext();){
				Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)psPeticionIterator.next();
				
				Producto_servicioLocal productoServicioLocal = productoServicioPeticionLocal.getProducto_servicio();
				Familia_producto_servicioKey familiaPSKey = (Familia_producto_servicioKey)productoServicioLocal.getFamilia_producto_servicio().getPrimaryKey();
				
				if (familiaPSKey != null && 
						((familiaPSKey.faps_id.toString().equals(new Integer(familiaPcBA).toString()) || (familiaPSKey.faps_id.toString().equals(new Integer(familiaPcPsBANaked).toString())) || familiaPSKey.faps_id.toString().equals(new Integer(familiaPcLinea).toString()) || familiaPSKey.faps_id.toString().equals(new Integer(familiaIP).toString()) )
						|| familiaPSKey.faps_id.toString().equals(new Integer(familiaPcTV).toString()))){
					esPostventa = false;
					break;
				}
			}
			return esPostventa;
		}

	public String  llamadoConfModemAutoInstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre, boolean esBaja, boolean esCierreActuacion)throws ATiempoAppEx{
			String [] valores = this.enviarConfiguracionModemAutoinstalacion(modem,codActividad,idMensajePadre);
			String retorno = valores[1];
			String respuesta = new String();
			
			TR135S tr135s = new TR135S();
			tr135s.setId(valores[0]);
			tr135s.setAtiempoRequestNumber(modem.getPeti_numero().toString());
			
			if (retorno!= null){
				if (retorno.indexOf("<faultcode>") != -1){
					tr135s.setErrorCode("1");
					tr135s.setErrorDescription(retorno.substring(retorno.indexOf("<faultcode>")+11, retorno.indexOf("</faultcode>")));
				}else if (retorno.indexOf("SOAPFaultException")!=-1){
					tr135s.setErrorCode("1");
					retorno = retorno.replaceAll("\\n", "");
					if (retorno.indexOf("Detail") != -1)
						tr135s.setErrorDescription("Se presento un error en la configuración de modems: SOAPFaultException");
					else
						tr135s.setErrorDescription(retorno.substring(retorno.indexOf("SOAPFaultException")+20));
				}else if (retorno.indexOf("<faultstring>") != -1){
					tr135s.setErrorDescription(retorno.substring(retorno.indexOf("<faultstring>")+13, retorno.indexOf("</faultstring>")));
				}
			}else{
				tr135s.setErrorCode("1");
				tr135s.setErrorDescription("Error de comunicación con el web service");
			}

			String mensajeRespuesta = XMLUtilities.marshall(tr135s);
			log.debug(mensajeRespuesta);
			ConfiguracionModemAutoInstalacion confModem = new ConfiguracionModemAutoInstalacion();
			Object [] obj = confModem.convertirMensaje(mensajeRespuesta);
		confModem.procesar(obj, esBaja, esCierreActuacion);
			
			if(esBaja){
				if(tr135s.getErrorDescription()!=null&&!tr135s.getErrorDescription().equals("")){
					respuesta = tr135s.getErrorDescription();
				}else{
					respuesta = "Mensaje recibido en ACS sin errores para el mensaje con id = "+valores[0];
				}
				
			}else{
				respuesta = valores[0];
			}
			
			return respuesta;
		}
		
		/* (non-Javadoc)
		 * rtrivino - req 3709
		 */
		public String[] enviarConfiguracionModemAutoinstalacion(ModemVpiDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx{
			log.debug("Entro a enviarConfiguracionModemAutoinstalacion");
					
			try{
				String [] valores = new String[2];
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome)HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
				PeticionKey peticionkey = new PeticionKey(modem.getPeti_numero());
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionkey);
				
				PeticionesDelegate pDelegate = new PeticionesDelegate();
				Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
								
				TR135E tr135e = new TR135E();
				String fatherEmail = new String();
				String fatherEmailAux = new String();
				Long PSCode = new Long("0");
				Long COCode = new Long("0");
				boolean modemYaExistente = false;
				boolean tieneBaEnVuelo = false;
				Short accionModemLocal = new Short("0"); 
				String cadenaInf = new String();
				Producto_servicio_en_vueloLocal psev=null;
				
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
				
				//Creación de la cabecera
				tr135e.setId(IdCorrelativo.toString());
				tr135e.setDestination("ESB");
				tr135e.setSource("ATIEMPO");
				tr135e.setInterfaz("CONFIG_MODEMACS"); 
				tr135e.setVersion("1.0");
				
				//Datos del cuerpo del mensaje
				tr135e.setBrandModem(modem.getMarca());
				tr135e.setModelModem(modem.getModelo());
				tr135e.setSerialNumber(modem.getSerial());
				tr135e.setAtiempoRequestNumber(modem.getPeti_numero().toString());
				
				Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome)HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
				Collection psevList = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(peticionkey.peti_numero, ComunInterfaces.BA_EN_VUELO);
				
				for(Iterator psevIter=psevList.iterator(); psevIter.hasNext();){
					Producto_servicio_en_vueloLocal psevLocal = (Producto_servicio_en_vueloLocal)psevIter.next();
					tieneBaEnVuelo = true;
					psev=psevLocal;
				}
				
				
				Collection PSPCollecton = peticionLocal.getProducto_servicio_peticion();
				for (Iterator PSPIterator=PSPCollecton.iterator(); PSPIterator.hasNext();){
					Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
					Familia_producto_servicioKey fampsKey = PSPLocal.getFamiliaKey();
					
					Operacion_comercialKey opcoKey = (Operacion_comercialKey)PSPLocal.getOperacion_comercial().getPrimaryKey();
					boolean esPsTipoModem = false;
					
					try{
						Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = null;
						/*new Long(psev.getOpco_id().toString())*/
						if(tieneBaEnVuelo)
							ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(new Long(psev.getOpco_id().toString()));
						else
							ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(PSPLocal.getPsId());
						
						esPsTipoModem = true;
					}catch(FinderException e){
						esPsTipoModem = false;					
					}catch(Exception e){
						esPsTipoModem = false;
					}
//					REQ BA NAKED adicion familia PC y PS naked
					if (opcoKey.opco_id.longValue() == OCAutoInstalacion.longValue()
							|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaPcBA
							|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaBandaAncha
							|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked
							|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
							|| esPsTipoModem){
						
						if (tieneBaEnVuelo){
							PSCode = psev.getPs_id();
							COCode = new Long (psev.getOpco_id().toString());
						}else{
							PSCode = PSPLocal.getPsId();
							COCode = opcoKey.opco_id;
						}
						
						
						if (COCode.intValue()==OCAutoInstalacion.intValue())
							COCode = new Long(ComunInterfaces.operacionParActivar);
						
						//Subpeticion_atisLocal subPet = PSPLocal.getFk_01_subp_atis();
						//fatherEmail = extraerFatherEmail(subPet);
						//fatherEmailAux = PSPLocal.getFk_01_subp_atis().getFk_agru_sub().getNum_ide_nu();
						break;
					}
				}
				
				tr135e.setCommercialOperationType(COCode);
				//DAVID: Comento esto pa ponerlo más abajo, tr135e.setProductServiceCode(PSCode);

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
							
				Collection recursosLineaBasicaCollection = peticionLocal.getRecursos_linea_basica();
				
				if (recursosLineaBasicaCollection.size()!=0){
					for (Iterator recursosLineaBasicaIterator = recursosLineaBasicaCollection.iterator(); recursosLineaBasicaIterator.hasNext();){
						Recursos_linea_basicaLocal recursosLineaBasicaLocal = (Recursos_linea_basicaLocal)recursosLineaBasicaIterator.next();
												
						if (recursosLineaBasicaLocal.getTelefono_ant()!=null 
								&& !recursosLineaBasicaLocal.getTelefono_ant().equals(new Long("0"))){
							tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_ant().toString()));
							if (recursosLineaBasicaLocal.getTelefono_asg()!=null){
								tr135e.setNewPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asg().toString()));
							}else{
								tr135e.setNewPhoneNumber(new Integer("0"));
							}
						}else if (recursosLineaBasicaLocal.getTelefono_asg()!=null 
								&& !recursosLineaBasicaLocal.getTelefono_asg().equals(new Long("0"))){
							tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asg().toString()));
							tr135e.setNewPhoneNumber(new Integer("0"));
						}else{
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
				
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
				mensajeEstadoBALocal.setPeticion(peticionLocal);
				Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(PSCode));
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
				mensajeEstadoBALocal.setCod_actividad_generadora(codActividad);
				
				String velocidadPlanYPS = extraerVelocidadPlanYPSAutoInstalacion(modem.getPeti_numero());
				String velocidadPlan="";
				String psPlan="0";
				if(velocidadPlanYPS!=null&&!velocidadPlanYPS.equals("")){
					String[] velYPS=velocidadPlanYPS.split("#");
					velocidadPlan=velYPS[0];
					psPlan=velYPS[1];
				}
				
				
				tr135e.setProductServiceCode(new Long(psPlan));
		
				cadenaInf = modem.getSerial()+":" ;
								
				//Guardo el modem en la base de datos
				Collection modemList = peticionLocal.getModem();
				if (modemList.size() > 0){
					for (Iterator modemIter = modemList.iterator(); modemIter.hasNext();){
						ModemLocal modemLocal = (ModemLocal) modemIter.next();
						ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
						accionModemLocal = modemLocal.getAccion();
						
						if (modemKey.serial.equals(modem.getSerial())){
							/*RQ.8595 - mfmendez*/
							modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
							modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
							if(modem.getMaterialCodeSAP() != null){
								modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
							}else{
								modemLocal.setPos_doc_sap(0);
							}
							modemLocal.setNum_material_sap(modem.getMaterialSAP());
							modemLocal.setCentro_sap(modem.getPlantSAP());
							modemLocal.setAlmacen_sap(modem.getStorageSAP());
							modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
							modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
							modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
							modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
							modemLocal.setElement_pep_sap(modem.getPepElementSAP());
							modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
							modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
							/*FIN - RQ.8595 - mfmendez*/
							
							modemYaExistente = true;							
							break;
						}
					}
					
					boolean tienePeticionesConfModems = false;
					
					ArrayList mensajesEnviadosList = pDelegate.recuperarListaMensajesConfModemsACS(modem.getPeti_numero());
					if (mensajesEnviadosList != null && mensajesEnviadosList.size()>0){
						tienePeticionesConfModems = true;
					}
					
					if (modemYaExistente){
						if (accionModemLocal.intValue() == ComunInterfaces.accionModemConfigurado){
							tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParDesactivar));
							cadenaInf = cadenaInf+ComunInterfaces.operacionParDesactivar;
						}//Opción usada para la baja en desconfiguración de modem.
						else if (accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar){
							tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParDesactivar));
							cadenaInf = cadenaInf+ComunInterfaces.operacionParDesactivar;
						}else{
							if (!tienePeticionesConfModems && accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar){
								tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParDesactivar));
								cadenaInf = cadenaInf+ComunInterfaces.operacionParDesactivar;
							}else{
								tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParActivar));
								cadenaInf = cadenaInf+ComunInterfaces.operacionParActivar;							
							}
						}
					}else{
						if (accionModemLocal.intValue() == ComunInterfaces.accionModemConfigurado
								|| accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar
								|| accionModemLocal.intValue() == ComunInterfaces.accionModemOcupar){
							tr135e.setCommercialOperationType(new Long(ComunInterfaces.altaMigracionPS));
							cadenaInf = cadenaInf+ComunInterfaces.altaMigracionPS;
						}else{
							tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParActivar));
							cadenaInf = cadenaInf+ComunInterfaces.operacionParActivar;							
							
							try{
								Collection modemAuxList = peticionLocal.getModem();
								if (modemAuxList.size() > 0){
									for (Iterator modemIter = modemAuxList.iterator(); modemIter.hasNext();){
										ModemLocal modemAuxLocal = (ModemLocal) modemIter.next();
										
										modemAuxLocal.remove();
									}
								}
							}catch(RemoveException ex){
								log.debug("Error en remover los modems:"+ex);
							}
						}
						
						ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,modem.getTelefono(),new Short(modem.getAccion()),
								modem.getMarca(),modem.getModelo(),new Integer((int)modem.getTipo()));
						modemLocal.setCodigo_material(modem.getCodMaterial());
						
						/*RQ.8595 - mfmendez*/
						modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
						modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
						if(modem.getMaterialCodeSAP() != null){
							modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
						}else{
							modemLocal.setPos_doc_sap(0);
						}
						modemLocal.setNum_material_sap(modem.getMaterialSAP());
						modemLocal.setCentro_sap(modem.getPlantSAP());
						modemLocal.setAlmacen_sap(modem.getStorageSAP());
						modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
						modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
						modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
						modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
						modemLocal.setElement_pep_sap(modem.getPepElementSAP());
						modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
						modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
						/*FIN - RQ.8595 - mfmendez*/
					}
				}else{
					ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,modem.getTelefono(),new Short(modem.getAccion()),
							modem.getMarca(),modem.getModelo(),new Integer((int)modem.getTipo()));
					modemLocal.setCodigo_material(modem.getCodMaterial());
					cadenaInf = cadenaInf+"Nuevo";
					
					/*RQ.8595 - mfmendez*/
					modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
					modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
					if(modem.getMaterialCodeSAP() != null){
						modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
					}else{
						modemLocal.setPos_doc_sap(0);
					}
					modemLocal.setNum_material_sap(modem.getMaterialSAP());
					modemLocal.setCentro_sap(modem.getPlantSAP());
					modemLocal.setAlmacen_sap(modem.getStorageSAP());
					modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
					modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
					modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
					modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
					modemLocal.setElement_pep_sap(modem.getPepElementSAP());
					modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
					/*FIN - RQ.8595 - mfmendez*/
				}
				
				if (idMensajePadre != null)
					cadenaInf = cadenaInf+":"+idMensajePadre;
				
				mensajeEstadoBALocal.setDesc_error(cadenaInf);
				
				log.debug(XMLUtilities.marshall(tr135e));
				//Envio de la configuración del modem por web service
				String urlWebService = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.URL_WEB_SERVICE_CONF_MODEM);
				String userWebService = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.USER_WEB_SERVICE_CONF_MODEM);
				String passwdWebService = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.PASSWD_WEB_SERVICE_CONF_MODEM);
				String certifacadeWebService = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.CERTIFICADE_WEB_SERVICE_CONF_MODEM);
				String protocolModem = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.PROTOCOL_CONF_MODEM);
				String nameWifi = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.NAME_WIFI_CONF_MODEM);
				String securityModem = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.SECURITY_MODEM_CONF_MODEM);
				String passwordModem = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.PASSWORD_MODEM_CONF_MODEM);
			String passwordModemWIFI  = ComunInterfaces.INDICATIVO_PASSWORD_MODEM + peticionLocal.getNum_doc_cli_cd();
				
				String retorno = new String();
							
				try{
					/*Para el timeout: NBIWebServicesInvoker invocador = new NBIWebServicesInvokerAsync();*/
					PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
					int timeOut = Integer.valueOf(peticionesDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.MILISECONDS_TIMEOUT_NBIWS)).intValue();
					log.debug("TimeOut NBIWebService: "+timeOut);
					NBIWebServicesInvoker invocador = new NBIWebServicesInvokerAsync(timeOut);
					ParametrosMotiveDTO parametros = invocador.obtenerDatosParametricos(urlWebService, userWebService, passwdWebService,
							certifacadeWebService,protocolModem, nameWifi, securityModem, passwordModem);

					String OUI = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OUI+tr135e.getModelModem());
					
					if (OUI!= null && velocidadPlan!=null){
						if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.operacionParActivar
								||tr135e.getCommercialOperationType().intValue() == ComunInterfaces.ALTA_CERO_CNEX){
//							retorno = "";
							retorno = invocador.configurarModem(tr135e.getSerialNumber(), OUI, tr135e.getModelModem(), tr135e.getFatherEmail(),
								tr135e.getPhoneNumber().toString(), tr135e.getNewPhoneNumber().toString(), velocidadPlan, tr135e.getAtiempoRequestNumber(), 
								parametros, passwordModemWIFI);
						}else if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.altaMigracionPS){
							retorno = invocador.modificarModem(tr135e.getSerialNumber(), OUI, tr135e.getModelModem(), tr135e.getFatherEmail(),
								tr135e.getPhoneNumber().toString(), tr135e.getNewPhoneNumber().toString(), velocidadPlan, tr135e.getAtiempoRequestNumber(), 
								parametros, passwordModemWIFI);
						}else if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.operacionParDesactivar){
							retorno = invocador.desconfigurarModem(tr135e.getPhoneNumber().toString(), parametros);
						}
						log.debug("La respuesta del mensaje: "+IdCorrelativo.toString()+" en el web service es: "+retorno);
					}else{
						if (OUI == null){
							log.debug("No se envía configuracion para el mensaje: "+IdCorrelativo.toString()+" porque la marca: "+tr135e.getModelModem()
									+" no tiene OUI asociado o velocidad asociados, por favor retifique en la tabla propertiesBD que halla un valor asociado al valor: "
									+ComunInterfaces.OUI+tr135e.getModelModem());
							retorno = "<faultcode><faultstring>No se envía mensaje de configuración porque "+ComunInterfaces.OUI+tr135e.getModelModem()
									+" no esta configurado en la base de datos</faultstring></faultcode>";
						}else if (velocidadPlan == null){
							log.debug("No se envía configuracion para el mensaje: "+IdCorrelativo.toString()+" No se envía mensaje de configuración porque el PS " +
									"no tiene una velocidad asociada");
							retorno = "<faultcode><faultstring>No se envía mensaje de configuración porque el PS"+
									" no tiene una velocidad asociada</faultstring></faultcode>";
						}
					}

					valores[0] = tr135e.getId();
					valores[1] = retorno;
				}catch(Exception ex){
					log.debug("Se presentó un problema al invocar el web service para el mensaje: "+IdCorrelativo.toString()+": error "+ex);
					ex.printStackTrace();
					valores[0] = tr135e.getId();
					valores[1] = null;
				}
				
				return (valores) ;
			}catch (NamingException ex){
				log.warn("RecursosBABean: enviarConfiguracionModemAutoinstalacion:NamingException:",ex);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, ex);
			}catch (FinderException ex){
				log.warn("RecursosBABean: enviarConfiguracionModemAutoinstalacion:FinderException:",ex);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, ex);
			}catch (CreateException ex){
				log.warn("RecursosBABean: enviarConfiguracionModemAutoinstalacion:CreateException:",ex);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, ex);
			}
			
		}
		
		public boolean validarConfiguracionModemAutoinstalacion (Long petiNumero, String codActividad){
			boolean deboValidar = false;
			
			try{
				PeticionesDelegate delegate = new PeticionesDelegate();
				String validarModemConfiguracion = delegate.recuperarParametroFromPropertiesBD(ComunInterfaces.VALIDAR_MODEM_CONFIGURADO);
				
				if (validarModemConfiguracion.equals("true")){
					deboValidar=true;
				}else{
					PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome)HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
					Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					
					PeticionKey peticionkey = new PeticionKey(petiNumero);
					PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionkey);
					
					PeticionesDelegate pDelegate = new PeticionesDelegate();
					Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
					
					Collection listaDePs=peticionLocal.getProducto_servicio_peticion();
					Iterator listaDePsIt=null;
					for(listaDePsIt=listaDePs.iterator();listaDePsIt.hasNext();){
						Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaDePsIt.next();
						Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
						Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
						Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
						
						Long psId = producto_servicio_peticionLocal.getPsId();					
						Long familiaId = familia_producto_servicioKey.faps_id;
						Long operacionComercial = operacion_comercialKey.opco_id;
//						REQ BA NAKED adicion familia PC naked
						if ((familiaId.intValue() == ComunInterfaces.familiaPcBA || familiaId.intValue() == ComunInterfaces.familiaPcPsBANaked ) && operacion_comercialLocal.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)
								&& codActividad.equals(ComunInterfaces.ACT_ALISTAR_KIT)){
							deboValidar = true;
							break;
						}else{
							deboValidar = false;
						}
					}
				}
				
			}catch(ATiempoAppEx ex){
				log.debug("RecursosBaBean: validarConfiguracionModemAutoinstalacion: AtiempoAppEx: "+ex);
			}catch(NamingException ex){
				log.debug("RecursosBaBean: validarConfiguracionModemAutoinstalacion: NamingException: "+ex);
			}catch(FinderException ex){
				log.debug("RecursosBaBean: validarConfiguracionModemAutoinstalacion: FinderException: "+ex);
			}
			
			return deboValidar;
		}
			
		/**
		 * DAVID: Método usado para extraer la velocidad de un ps de BA en auto instalación, se extrae la velocidad del ps de BA con OC de BA, si no vien PS de BA con OC ba
		 * se extrae la velocidad del ps tipo modem que venga en la petición.
		 * @param petiNumero
		 * @return
		 */
		public String extraerVelocidadPlanYPSAutoInstalacion(Long petiNumero){
			String velocidadPlan=null;
			String velocidadPlanModem=null;
			String velocidad=null;
			
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome)HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
				Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				
				PeticionKey peticionkey = new PeticionKey(petiNumero);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionkey);
				
				PeticionesDelegate pDelegate = new PeticionesDelegate();
				Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
				
				Collection listaDePs=peticionLocal.getProducto_servicio_peticion();
				Iterator listaDePsIt=null;
				for(listaDePsIt=listaDePs.iterator();listaDePsIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaDePsIt.next();
					Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
					Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
					Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
					
					Long psId = producto_servicio_peticionLocal.getPsId();					
					Long familiaId = familia_producto_servicioKey.faps_id;
					Long operacionComercial = operacion_comercialKey.opco_id;
					boolean esPsTipoModem = false;
					
					try{
						Ps_Tipo_ModemLocal ps_Tipo_ModemLocalValidacion = ps_Tipo_ModemLocalHome.findByNroPs(psId);
						esPsTipoModem = true;
					}catch(FinderException e){
						esPsTipoModem = false;					
					}	
//					REQ BA NAKED adicion familia PC naked
					if (/*operacionComercial.longValue() == OCAutoInstalacion.longValue()
							&& */familiaId.intValue() == ComunInterfaces.familiaPcBA || familiaId.intValue() == ComunInterfaces.familiaPcPsBANaked || esPsTipoModem){
//						log.debug("Es ps de BA y OC auto instalación, evaluamos si es de tipo módem o no..");
						log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
						
						Producto_servicioKey producto_servicioKey = new Producto_servicioKey(psId);							
					    Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);						
						
						try{
							Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psId);							
							velocidadPlanModem=producto_servicioLocal.getVelocidad()+"#"+psId;
						}catch(FinderException e){
							log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
							velocidadPlan=producto_servicioLocal.getVelocidad()+"#"+psId;
							break;
						}					
					}
				}
				
			}catch(FinderException e){
				log.debug("FinderException en método extraerVelocidadPlanAutoInstalacion() de RecursosBABean.."+e.toString());
			}catch(NamingException e){
				log.debug("NamingException en método extraerVelocidadPlanAutoInstalacion() de RecursosBABean.."+e.toString());
			}catch(ATiempoAppEx e){
				log.debug("ATiempoAppEx en método extraerVelocidadPlanAutoInstalacion() de RecursosBABean.."+e.toString());
			}
			
			if(velocidadPlan!=null){
				velocidad=velocidadPlan;
			}else if(velocidadPlanModem!=null){
				velocidad=velocidadPlanModem;
			}
			
			return velocidad;
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recibirConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
		 */
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActacion) throws ATiempoAppEx {
			log.debug("Entro a recibirConfiguracionModemAutoinstalacion");
			BackendExecution bExecution = null;
			
			try
			{
				bExecution = BackendTraceUtil.initExecution(tr135s, log);
				bExecution.setNumPetAtiempo(new Long(tr135s.getAtiempoRequestNumber()));
				bExecution.setIdMensajeOp(tr135s.getId());
				bExecution.startOperation();
		
				validaHome () ;
				// busca el registro del mensaje
				log.debug("Primero si es mensaje de error lo obviamos.");
				
				Mensaje_estado_baLocal mensaje_estado_ba;
				try {
					mensaje_estado_ba = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr135s.getId())));
				} catch (FinderException e1){
					mensaje_estado_ba=null;
				}

								
				if (mensaje_estado_ba == null){
					log.debug ("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall (tr135s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr135s.getId(),ATiempoAppEx.EXCEPTION);
				}
	    
				PeticionLocal peticionLocal = mensaje_estado_ba.getPeticion();
				PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
				
				String [] infoAdicional = mensaje_estado_ba.getDesc_error().split(":");
				String serialModem = infoAdicional[0];
				
				String accionRealizada  = null;
				try{
					accionRealizada = infoAdicional[1];
				}catch(Exception ex){
					accionRealizada  = null;
				}
				
				String idMensajePadre;
				
				try{
					idMensajePadre = infoAdicional[2];
				}catch(Exception ex){
					idMensajePadre = null;
				}
				
				
				ModemLocalHome modemLocalHome = (ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
				
				
				
				if(tr135s.getErrorCode() != null && !tr135s.getErrorCode().equals("0")){
					log.debug("Respuesta tr-135-s con error: "+tr135s.getId());
				
					ModemKey modemKey =  null;
					modemKey = new ModemKey(serialModem,peticionKey );
					ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);
					
					//Se quita este bloque porque estaba buscando el modem con el campo que no correspondía.
					/*if (idMensajePadre != null){
						String [] propiedadesTR135S = idMensajePadre.split("#");
						
						if (propiedadesTR135S.length>1)
							modemKey = new ModemKey(propiedadesTR135S[1],peticionKey );
						else
							modemKey = new ModemKey(serialModem,peticionKey );
					}else{
						modemKey = new ModemKey(serialModem,peticionKey );
					}*/
					
					
					
						
					mensaje_estado_ba.setId_error(tr135s.getErrorCode());
					mensaje_estado_ba.setDesc_error(tr135s.getErrorDescription());
					
					Mensaje_estadoLocal mensajeError=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
					mensaje_estado_ba.setMensaje_estado(mensajeError);
					
					if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado){
						if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
							
							Collection modemsList = modemLocalHome.findPeticion(peticionKey.peti_numero);
							for (Iterator modemsIter = modemsList.iterator(); modemsIter.hasNext();){
								ModemLocal modem = (ModemLocal) modemsIter.next();
								ModemKey key = (ModemKey)modem.getPrimaryKey();
								if (key.serial.equals(serialModem)){
									modem.remove();
								}
							}
						}
						//Si al hacer la desconfiguración de modem ACS en el baja ocurre un error, entonces se deja el modem actual en estado 1,accionModemLiberar
						else if (accionRealizada.equals(new Integer(ComunInterfaces.bajaModemACS).toString())){
							modemLocal.setAccion(new Short(new Integer(accionModemLiberar).toString()));
						}
						else{
							modemLocal.setAccion(new Short(new Integer(accionModemNoAction).toString()));
						}
					}else{
						modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
					}
					
					
				}else{
					log.debug("Respuesta tr-135-s sin error"+tr135s.getId());
					
					ModemKey modemKey = new ModemKey(serialModem,peticionKey );
					ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);
					
					if (accionRealizada == null || accionRealizada.equals(new Integer(ComunInterfaces.operacionParActivar).toString())
							|| accionRealizada.equals("Nuevo")){
						modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
					}else if (accionRealizada.equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
						modemLocal.setAccion(new Short(new Integer(accionModemLiberar).toString()));
					if (!esBaja)
						modemLocal.remove();
					}else if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
						modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
						
						Collection modemInterno = modemLocalHome.findPeticion(peticionKey.peti_numero);
						for (Iterator modemIter = modemInterno.iterator();modemIter.hasNext();){
							ModemLocal modem = (ModemLocal)modemIter.next();
							ModemKey modemLocalKey = (ModemKey)modem.getPrimaryKey(); 
							
							if (!modemLocalKey.serial.equals(modemKey.serial)){
								modem.remove();
							}
						}
					}
							
					Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
					mensaje_estado_ba.setMensaje_estado(mensajeOK);
				}
				
				Long idTmpModem = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_MODEM")) ;
				
				if(tmp_modemLocalHome==null)
					tmp_modemLocalHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
	    
				Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
				Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.create(new Integer(tr135s.getId()));
				mensajeConfACSLocal.setXml(XMLUtilities.marshall (tr135s));
				mensajeConfACSLocal.setAccion(accionRealizada);
				mensajeConfACSLocal.setSerial_modem(serialModem);
				mensajeConfACSLocal.setPeti_numero(peticionKey.peti_numero);
				
				Date dateHoy = new Date();
				Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
				mensajeConfACSLocal.setFecha_ingreso(timestampHoy);
				
			if (idMensajePadre != null && !esCierreActacion){
					String [] propiedadesTR135S = idMensajePadre.split("#");
					
					this.enviaActivarModemsAgendaSC(propiedadesTR135S[1], propiedadesTR135S[0], tr135s.getId());
				}else {
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

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#buscarRespuestaConfiguracionModemAutoInstalacion(java.lang.Long, java.lang.Long)
		 */
		public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
			validaHome();
			TR135S tr135s = null;
			try
			{	
				if(mensajeEstadoBaLocalHome==null)
					mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocal msgMoLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(idMensaje));
				
				if (msgMoLocal == null)
					return null;

				Mensaje_estadoLocal msgEstadoLocal = msgMoLocal.getMensaje_estado();

				PeticionLocal pLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Collection c = pLocal.getTmp_modem();
				for (Iterator it = c.iterator(); it.hasNext();)
				{	
					Tmp_modemLocal tmpModemLocal = (Tmp_modemLocal) it.next();
					try{
						tr135s = (TR135S) XMLUtilities.unmarshall(tmpModemLocal.getXml());
						if (!tr135s.getId().equals(idMensaje.toString())){
							tr135s = null;
						}else{
							return tr135s;
						}
					}catch(ClassCastException ex){
						tr135s = null;
					}				
				}			
			}
			catch (FinderException e){
				log.error("Error al buscar Respuesta Modem.",e);
				throw new ATiempoAppEx (ATiempoAppEx.FINDER);
			}catch (NamingException e){
				log.error("Error al buscar Respuesta Modem.",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
			
			return tr135s;
		}
		/**
		 * DAVID: Método para guardar la fecha de auto instalación en tabla Recursos BA.
		 * @param petiNumero
		 * @param fechaEntregaKit
		 */
		public void grabarFechaAutoInst(Long petiNumero, Fecha fechaAutoInst) throws ATiempoAppEx{
			try{
				
				PeticionKey key = new PeticionKey(petiNumero) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
				Collection recursosBaList=peticionLocal.getRecursos_ba();
				Iterator recursosBaListIt=null;
				
				for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosBaListIt.next();
					recursos_baLocal.setFecha_auto_inst(fechaAutoInst.getTimestamp());
				}
				
				if(recursosBaList == null || recursosBaList.isEmpty()){
					Collection elementoList=peticionLocal.getElemento_peticion();
					for(Iterator elementoPetList=elementoList.iterator();elementoPetList.hasNext();){
						Elemento_PeticionLocal elemento = (Elemento_PeticionLocal) elementoPetList.next();
						elemento.setFecha_guia_auto_inst(fechaAutoInst.getTimestamp());
					}
				}
				
			}catch(FinderException e){
				log.debug("Error de finder en método grabarFechaEntregaKitAutoInst()"+e.toString());
			}
		}
		/**
		 * DAVID: Método para guardar la fecha de entrega de kit de auto instalación en tabla Recursos BA.
		 * @param petiNumero
		 * @param fechaEntregaKit
		 */
		public void grabarFechaEntregaKitAutoInst(Long petiNumero, Fecha fechaEntregaKit) throws ATiempoAppEx{
			try{
				
				PeticionKey key = new PeticionKey(petiNumero) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
				Collection recursosBaList=peticionLocal.getRecursos_ba();
				Iterator recursosBaListIt=null;
				
				for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosBaListIt.next();
					recursos_baLocal.setFecha_entrega_kit_auto_inst(fechaEntregaKit.getTimestamp());
				}
				
			}catch(FinderException e){
				log.debug("Error de finder en método grabarFechaEntregaKitAutoInst()"+e.toString());
			}
		}
		
		public void grabarFechaGuiaKitAutoInst(Long petiNumero, Fecha fechaGuiaKit) throws ATiempoAppEx {
			try{
				
				PeticionKey key = new PeticionKey(petiNumero) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
				Collection recursosBaList=peticionLocal.getRecursos_ba();
				Iterator recursosBaListIt=null;
				
				for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosBaListIt.next();
					recursos_baLocal.setFecha_guia_auto_inst(fechaGuiaKit.getTimestamp());
				}
				
			}catch(FinderException e){
				log.debug("Error de finder en método grabarFechaGuiaKitAutoInst()"+e.toString());
			}
		}
		
		/**
		 * DAVID: Método para guardar en BD los mensajes recibidos de ACS.
		 * @param tr137s
		 * @throws ATiempoAppEx
		 */
		public void recibirmensajeACS(TR137S tr137s) throws ATiempoAppEx{
			log.debug("Entro a recibirmensajeACS de la peticion:"+tr137s.getAtiempoRequestNumber());
			BackendExecution bExecution = null;
			bExecution = BackendTraceUtil.initExecution(tr137s, log);
			bExecution.setNumPetAtiempo(new Long(tr137s.getAtiempoRequestNumber()));
			bExecution.startOperation();
			
			try
			{	
				
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Mensaje_ACSLocalHome mensaje_ACSLocalHome = (Mensaje_ACSLocalHome) HomeFactory.getHome(Mensaje_ACSLocalHome.JNDI_NAME);
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				
				Mensaje_ACSLocal mensaje_ACSLocal = mensaje_ACSLocalHome.create(IdCorrelativo);
				mensaje_ACSLocal.setPeti_numero(new Long(tr137s.getAtiempoRequestNumber()));
				mensaje_ACSLocal.setXml(XMLUtilities.marshall (tr137s));
				Date dateHoy = new Date();
				Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
				mensaje_ACSLocal.setFecha_ingreso(timestampHoy);
				
				PeticionesDelegate pDelegate = new PeticionesDelegate();
				String mensajeOk=pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.MENSAJE_OK_ACS);
				//Si llega el mensaje Ok se cierra la actividad de autoinstalación.
				if(tr137s.getErrorDescription()!=null&&tr137s.getErrorDescription().equals(mensajeOk)){
					ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(COD_ACT_AUTO_INSTALACION,idAplicacion);
					
					ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();				
					ActividadEJBDTO actividadEJBDTO=recuperaActividadDeBandejaIntegrada(new Long(tr137s.getAtiempoRequestNumber()),actividadKey.act_id,COD_ACT_AUTO_INSTALACION);
					if(actividadEJBDTO!=null){
						ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actividadEJBDTO.getCodigoActividad()); 
						actividadEJBDTO.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_auto_inst,"N");
						actividadEJB.terminar(actividadEJBDTO);
					}
				}
								
			}catch(FinderException e){
				bExecution.setErrorOp(true);
				log.debug("FinderException en metodo recibirmensajeACS(): "+e.toString());
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}catch(NamingException e){
				bExecution.setErrorOp(true);
				log.debug("NamingException en metodo recibirmensajeACS(): "+e.toString());
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}catch(Exception e){
				bExecution.setErrorOp(true);
				log.debug("Exception en metodo recibirmensajeACS(): ",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}finally{  
				bExecution.endAll();
			}
		}
		/**
		 * DAVID: Envía el mensaje SMS de auto instalación.
		 * @param petiNumero
		 * @throws ATiempoAppEx
		 */
		public void enviarSMSAutoInstalacion(Long petiNumero, String usuario)throws ATiempoAppEx{
			log.debug("Entro a enviarSMSAutoInstalacion de la peticion:"+petiNumero);

			try{
				
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				String telefonoCelular=peticionesDelegate.extraerCellPhone(petiNumero);
				
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Mensaje_SMS_ACSLocalHome mensaje_SMS_ACSLocalHome = (Mensaje_SMS_ACSLocalHome) HomeFactory.getHome(Mensaje_SMS_ACSLocalHome.JNDI_NAME);
				Mensaje_SMS_ACSLocal mensaje_SMS_ACSLocal = mensaje_SMS_ACSLocalHome.create(IdCorrelativo);
				
				
				Properties_BDLocalHome properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal properties_BDLocal = properties_BDLocalHome.findByPrimaryKey(MENSAJE_SMS_ACS);
				
				
				UsuarioLocalHome usuarioHome=(UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				
				TRSMSE trsmse = new TRSMSE();
				TRSMSEDatos datos = new TRSMSEDatos();
				
				datos.setDestino(sistemaAgendaSC);
				datos.setInterfaz("ENVIAR_SMS");
				datos.setNumero(telefonoCelular);
				datos.setOrigen(sistemaAtiempo);
				datos.setTexto(properties_BDLocal.getValor());
				datos.setVersion("1.0");
				
				trsmse.setDatos(datos);
				
				Date dateHoy = new Date();
				Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
				mensaje_SMS_ACSLocal.setFecha_envio(timestampHoy);
				mensaje_SMS_ACSLocal.setPeti_numero(petiNumero);
				mensaje_SMS_ACSLocal.setXml(XMLUtilities.marshall (trsmse));
				mensaje_SMS_ACSLocal.setUsuario(usuario);
				
				EnviarSMSACSServicioMQ enviarSMSACSServicioMQ = new EnviarSMSACSServicioMQ();
				enviarSMSACSServicioMQ.enviarMensaje(trsmse);
				
				
			}catch(NamingException e){
				log.debug("NamingException en metodo enviarSMSAutoInstalacion(): "+e.toString());
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}catch(Exception e){
				log.debug("Exception en metodo enviarSMSAutoInstalacion(): ",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}
			
		}	
		
		
		/**
		 * DAVID: Recupera una actividad desde la tabla bandejaIntegrada, se usa para actividades que son manuales y se deben cerrar automáticamente.
		 * @param petiNumero
		 * @param idActividad
		 * @param codigoActividad
		 * @return
		 * @throws ATiempoAppEx
		 */
		public ActividadEJBDTO recuperaActividadDeBandejaIntegrada(Long petiNumero, Long idActividad, String codigoActividad)throws ATiempoAppEx{
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			ActividadEJBDTO actDto=null;
			try{
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(idActividad,petiNumero,idAplicacion);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codigoActividad);
			
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				actDto = actividadEJB.getActividadEJBDTO(petiNumero,codigoActividad,idCorrelativo,null);			
			}catch (NamingException e) {
				log.error("NamingException en recuperaActividadDeBandejaIntegrada()"+e.toString());
			}catch (FinderException e) {
				log.warn("FinderException en recuperaActividadDeBandejaIntegrada()"+e.toString());
			}catch(Exception e)	{
				log.debug("Exception en recuperaActividadDeBandejaIntegrada()",e);
			}	
			return actDto;
		}
		
		/**
		 * Raul: Método para guardar la la guia de kit de auto instalación en tabla Recursos BA.
		 * @param petiNumero
		 * @param numGuia
		 */
		public void grabarGuiaKitAutoInst(Long petiNumero, String numGuia) throws ATiempoAppEx{
			try{
				
				PeticionKey key = new PeticionKey(petiNumero) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
				Collection recursosBaList=peticionLocal.getRecursos_ba();
				Iterator recursosBaListIt=null;
				
				for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosBaListIt.next();
					recursos_baLocal.setGuia_autoinstalacion(numGuia);
				}
				
				//Cambio para que guarde Guia para los PS diferentes de BA
				
				if(recursosBaList == null || recursosBaList.isEmpty()){
					Collection elementoList=peticionLocal.getElemento_peticion();
					for(Iterator elementoPetList=elementoList.iterator();elementoPetList.hasNext();){
						Elemento_PeticionLocal elemento = (Elemento_PeticionLocal) elementoPetList.next();
						elemento.setGuiaAutoInst(numGuia);
					}
				}
				
			}catch(FinderException e){
				log.debug("Error de finder en método grabarFechaEntregaKitAutoInst()"+e.toString());
			}
		}
		
		public String obtenerGuiaKitAutoInst(Long petiNumero) throws ATiempoAppEx{
			String numGuia = "";
			
			try{
				PeticionKey key = new PeticionKey(petiNumero) ;
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
				Collection recursosBaList=peticionLocal.getRecursos_ba();
				Iterator recursosBaListIt=null;
				
				for(recursosBaListIt=recursosBaList.iterator();recursosBaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosBaListIt.next();
					numGuia = recursos_baLocal.getGuia_autoinstalacion();
				}
				
			}catch(FinderException e){
				log.debug("Error de finder en método grabarFechaEntregaKitAutoInst()"+e.toString());
			}
		
			return numGuia;
		}
		
		

		/* Ra+ul Triviño: Método encargado de la configuración de los modems en caso de agenda SC
		 */
		public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx {
			log.debug("Entro a recepcionActivarModemsAgendaSC de la actuación:"+tr717s.getIdSchedule());

			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = null;
				Agenda_scLocal agendaSCLocal = null;
				
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				
				//Obtención de la información del agendamiento
				try{
					Agenda_scKey agendaSCKey = new Agenda_scKey(tr717s.getIdSchedule());
					agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
					PeticionKey peticionKey = new PeticionKey(agendaSCLocal.getPeti_numero());
					peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				}catch(FinderException ex){
					log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
					String idPeticionAux = tr717s.getIdSchedule().substring(2,tr717s.getIdSchedule().indexOf("-"));
					
					Collection agendaSCCollection = agendaSCLocalHome.findByPetiNumero(new Long(idPeticionAux));
					for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
						Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
						
						if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
							agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
						}
					}
					
					PeticionKey peticionKey = new PeticionKey(new Long(idPeticionAux));
					peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
										
					agendaSCLocal = agendaSCLocalHome.create(tr717s.getIdSchedule());
					agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
					agendaSCLocal.setPeti_numero(new Long(idPeticionAux));
					agendaSCLocal.setPeticion(peticionLocal);
					agendaSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
				}
				
				//Obtención de la actividad
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSCLocal.getPeti_numero(),idAplicacion);
				
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				String codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSCLocal.getPeti_numero(), codActividad,idCorrelativo,null);
				
				if ((agendaSCLocal.getEstado().intValue() == ACTUACION_ABIERTA || agendaSCLocal.getEstado().intValue() == ACTUACION_REAGENDADA) && tr717s.getModemSerial()!=null){
					RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
					
					//Obtención de la información del teléfono asociado
					InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
					InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(agendaSCLocal.getPeti_numero());
					
					//Asignación de variables del modem
					ModemVpiDTO modem = new ModemVpiDTO();
					modem.setPeti_numero(agendaSCLocal.getPeti_numero());
					modem.setMarca(tr717s.getModemBrand());
					modem.setSerial(tr717s.getModemSerial());
					
					//Se adiciona este cambio para tener en cuenta el codigo de material y el modelo del modem
					modem.setModelo(tr717s.getModelModem());
					modem.setCodMaterial(tr717s.getMaterialCode());
					
					if (tr717s.getModemType()!= null && tr717s.getModemType().equals(ComunInterfaces.MODEM_WIFI)){
						modem.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
					}else if (tr717s.getModemType()!= null && 
							(tr717s.getModemType().equals(ComunInterfaces.MODEM_STD)||tr717s.getModemType().equals(ComunInterfaces.MODEM_STD_2)
									|| tr717s.getModemType().equals(ComunInterfaces.MODEM_STD1P))){
						modem.setTipo(new Integer(ComunInterfaces.identificadorConvencional).shortValue());
					}else{
						modem.setTipo(new Integer(ComunInterfaces.identificadorWiFi).shortValue());
					}
					
					if (informacionTecnicaDTO.LineaNueva.getTelefono()!= null && informacionTecnicaDTO.LineaNueva.getTelefono().length()>0){
						modem.setTelefono(new Long(informacionTecnicaDTO.LineaNueva.getTelefono()));
					}else{
						modem.setTelefono(new Long("0"));
					}
					
					modem.setAccion(new Integer(ComunInterfaces.accionModemNoAction).shortValue());
					
					Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(new Long(tr717s.getId()));
					mensajeEstadoBALocal.setPeticion(agendaSCLocal.getPeticion());
					/*Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(PSCode));
					Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
					mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));*/
					mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
					mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
					
					Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
					mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);					
					mensajeEstadoBALocal.setCod_actividad_generadora(codActividad);
					
					//Envío el mensaje de autoinstalación
				//rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), tr717s.getId()+"#"+tr717s.getIdSchedule(),false, false);
				//REQ BA NAKED 
				//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
					Long  telAsignado = new Long (peticionLocal.getNum_ide_nu_stb());
					ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,telAsignado,new Short(modem.getAccion()),
							modem.getMarca(),modem.getModelo(),new Integer((int)modem.getTipo()));
					modemLocal.setModem_marca(modem.getMarca());
					modemLocal.setModelo(modem.getModelo());
					modemLocal.setCodigo_material(modem.getCodMaterial());
					/*RQ.8595 - mfmendez*/
					modemLocal.setFec_cont_sap(modem.getPostingDateSAP());
					modemLocal.setClase_mov_sap(modem.getMoveTypeSAP());
					if(modem.getMaterialCodeSAP() != null){
						modemLocal.setPos_doc_sap(Integer.parseInt(modem.getMaterialCodeSAP()));
					}else{
						modemLocal.setPos_doc_sap(0);
					}
					modemLocal.setNum_material_sap(modem.getMaterialSAP());
					modemLocal.setCentro_sap(modem.getPlantSAP());
					modemLocal.setAlmacen_sap(modem.getStorageSAP());
					modemLocal.setCod_lote_sap(modem.getBatchCodeSAP());
					modemLocal.setUnd_medida_sap(modem.getMeasurementUnitSAP());
					modemLocal.setCentr_cost_sap(modem.getCostCenterSAP());
					modemLocal.setArea_func_sap(modem.getFuncAreaLongSAP());
					modemLocal.setElement_pep_sap(modem.getPepElementSAP());	
					modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					modemLocal.setFlag_mat_sap(modem.getFlagMatSAP());
					
					log.debug("Se adiciona campos de sap para el modem  "+modem.getSerial());
				ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
				aCSServicioDelegate.enviarAutoConfiguracion(modem,actDto.getCodigoActividad(), tr717s.getId()+"#"+tr717s.getIdSchedule());
				//FIN REQ NAKED
				}else{
					log.debug("El mensaje: "+tr717s.getId()+" no se puede procesar porque la actuación: "+tr717s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
					actDto.setObservacion("El mensaje: "+tr717s.getId()+" no se puede procesar porque la actuación: "+tr717s.getIdSchedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
					
					this.enviaActivarModemsAgendaSC(tr717s.getIdSchedule(), tr717s.getId(), null);
					
					actividadEJB.grabarSinTerminar(actDto);
				}
			}catch(NamingException ex){
				log.error("NamingException en recepcionActivarModemsAgendaSC()"+ex.toString());
				ex.printStackTrace();
			}catch(FinderException ex){
				log.error("FinderException en recepcionActivarModemsAgendaSC()"+ex.toString());
				ex.printStackTrace();				
			}catch(CreateException ex){
				log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
				ex.printStackTrace();				
			}catch(TnProcesoExcepcion ex){
				log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
				ex.printStackTrace();
			}
			
			
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaActivarModemsAgendaSC(java.lang.String, java.lang.String)
		 */
		public void enviaActivarModemsAgendaSC(String idActuacion, String idMensajePeticion, String idMensajePeticionHijo) throws ATiempoAppEx {
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				log.debug("entro a generar la tr717e para ña actuacion: "+idActuacion +" y id mensaje: "+idMensajePeticion);
				Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				
				boolean tieneErrores = false;
				ArrayList equipos = new ArrayList();
				TR717E tr717e = new TR717E();
				String idModem = new String();
				String codeMaterial = new String();
				String error = new String();
				String descError = new String();
				
				Agenda_scKey agendaSCKey = new Agenda_scKey(idActuacion);
				Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
				PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
				
				//Obtención de la actividad
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSCLocal.getPeti_numero(),idAplicacion);
				
				String idCorrelativo=bintegradaLocal.getBi_url_detalle();
				int idInicio=idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				if(mensaje_estadoLocalHome==null)
					mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				String codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
				
				/**
				 * Base64 traduce los caracteres '+' y '/' en las secuencias especiales en hexadecimal %XX ('+' = '%2B' y '/' = '%2F'). 
				 * Si posteriormente se usa para almacenamiento en base de datos, producirán un conflicto 
				 * en el carácter '%' generado por el codificador de URL (debido a que este carácter es usado en ANSI SQL como comodín).
				 * 
				 * Por eso desde la base de datos vienen ya sea %2B o %2F  porque en algún punto de la aplicación se almaenó esta URL en la 
				 * tabla bintegrada, y se deben reconvertir a + o /.
				 */
				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSCLocal.getPeti_numero(), codActividad,idCorrelativo,null);

				Mensaje_estado_baKey mensajeEstadoBaKey;
				Mensaje_estado_baLocal mensajeEstadoBALocal;
				
				log.debug("Calculo mensaje Hijo: "+ idMensajePeticionHijo);
				
				if (idMensajePeticionHijo!=null){
					mensajeEstadoBaKey = new Mensaje_estado_baKey(new Long(idMensajePeticionHijo));
					mensajeEstadoBALocal = mensajeEstadoBaLocalHome.findByPrimaryKey(mensajeEstadoBaKey);
					
					error =  mensajeEstadoBALocal.getId_error();
					descError = mensajeEstadoBALocal.getDesc_error();
				}
				
				
				tr717e.setIdSourceSystem(sistemaAtiempo);
				tr717e.setIdSchedule(idActuacion);
				
				Collection modems = peticionLocal.getModem();
				
				log.debug("obtengo informaciòn de Modem");
				for (Iterator modemsIterator = modems.iterator(); modemsIterator.hasNext();){
					ModemLocal modemLocal = (ModemLocal) modemsIterator.next();

					ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
					idModem = modemKey.serial;
					
					//Se cambia la obtención del mensaje ya no de modelo si no del campo codigo material 
					codeMaterial = modemLocal.getCodigo_material();
					
					if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado){
						tieneErrores = true;
						break;
					}
				}
				
				if (tieneErrores){
					tr717e.setResponse("ERROR");
					tr717e.setError(error);
					tr717e.setErrorMessage(descError);
					tr717e.setResponseDescription(descError);
					tr717e.setModemSerial(idModem);
					tr717e.setMaterialCode(codeMaterial);

					actDto.setObservacion("El modem "+idModem+" presentó errores en la configuración: "+descError, true);
					
					Mensaje_estadoLocal mensajeError=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				//mensajeEstadoBALocal.setMensaje_estado(mensajeError);
					
					actividadEJB.grabarSinTerminar(actDto);
					
				}else{
					log.debug("No hay errores para reportar para el modem con serial: "+idModem + " y codigo: "+codeMaterial);
					tr717e.setResponse("OK");	
					tr717e.setResponseDescription("");
					tr717e.setError("0");
					tr717e.setErrorMessage("");
					tr717e.setModemSerial(idModem);
					tr717e.setMaterialCode(codeMaterial);
					
//					Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
//					Mensaje_conf_ACSKey mensajeConfACS = new Mensaje_conf_ACSKey(new Integer(idMensajePeticionHijo));
//					Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.findByPrimaryKey(mensajeConfACS);
//					
//					if (mensajeConfACSLocal.getAccion().equals("Nuevo") 
//							|| mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParActivar).toString())){
//						actDto.setObservacion("El modem "+idModem+" se configuró con exito", true);
//					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
//						actDto.setObservacion("El modem "+idModem+" se desconfiguró con exito", true);
//					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
//						actDto.setObservacion("El modem "+idModem+" fue trasladado con exito", true);
//					}
					Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				//mensajeEstadoBALocal.setMensaje_estado(mensajeOK);
					
					actividadEJB.grabarSinTerminar(actDto);
				}
				
				
				tr717e.setId(idMensajePeticion);
				tr717e.setDestination(sistemaAgendaSC);
				tr717e.setSource(sistemaAtiempo);
				tr717e.setInterfaz("RES_CONFIG_MODEM");
				tr717e.setVersion("1.0");
				
				ActivarModemSCMQ activarModemSCMQ = new ActivarModemSCMQ();
				activarModemSCMQ.enviarMensaje(tr717e);
				
				Mensaje_estado_baLocal mensaje_estado_ba;
				   try {
				   	log.debug("Se realiza parte final del proceso");
				   	if(mensajeEstadoBaLocalHome==null)
						mensajeEstadoBaLocalHome=(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				   	if(mensaje_estadoLocalHome==null)
						mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				    mensaje_estado_ba = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(idMensajePeticion)));
				    Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				    mensaje_estado_ba.setMensaje_estado(mensajeOK);
				    df = new SimpleDateFormat ("yyyy/MM/dd") ;
				    mensaje_estado_ba.setFecha_cierre(df.format (new java.util.Date ()));
				   } catch (FinderException e1){
				    log.error("No se encontro el mensaje Estado BA local con id "+idMensajePeticion,e1);
				   }
				
				log.debug("Se ha enviado con exito la tr-717-e, quedo en espera de recibir un cierre de actuación....");
			}catch(NamingException ex){
				log.debug("Error instanciando el bean en el envío de activar modems:" + ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("Error buscando elementos en el envío de activar modems: " + ex);
				ex.printStackTrace();
			}catch(TnProcesoExcepcion ex){
				log.debug("Error Ubicando actividad en el envío de activar modems: " + ex);
				ex.printStackTrace();
			}
		}
		/**
		 * DAVID: Devuelve el número de la guía de una petición de autoinstalación
		 * @param petiNumero
		 * @return
		 */
		public String recuperaNumeroGuiaAutoInst(Long petiNumero) throws ATiempoAppEx{
			try{
				String numeroGuia=null;
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
				Collection rbaList=peticionLocal.getRecursos_ba();
				Iterator rbaListIt=null;
				for(rbaListIt=rbaList.iterator();rbaListIt.hasNext();){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal)rbaListIt.next();
					numeroGuia=recursos_baLocal.getGuia_autoinstalacion();
					if(numeroGuia!=null){
						break;
					}
				}
				if(rbaList == null || rbaList.isEmpty()){
					Collection elementoList=peticionLocal.getElemento_peticion();
					for(Iterator elementoPetList=elementoList.iterator();elementoPetList.hasNext();){
						Elemento_PeticionLocal elemento = (Elemento_PeticionLocal) elementoPetList.next();
						numeroGuia = elemento.getGuiaAutoInst();
					}
				}
				return numeroGuia;
			}catch(FinderException e){
				log.debug("No se encontró ningún número de guía para la petición:" + petiNumero);
				return null;
			}catch(NamingException e){
				log.debug("No se encontró ningún número de guía para la petición:" + petiNumero);
				return null;
			}
		}

		public void actualizarRecursosBA(Long petiNumero) throws ATiempoAppEx {
			try {
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey peticionKey = new PeticionKey( petiNumero );
				PeticionLocal peticionLocal =peticionLocalHome.findByPrimaryKey( peticionKey ); 
				
				Collection collectionRecursosBa = peticionLocal.getRecursos_ba();
				
				for( Iterator iterator = collectionRecursosBa.iterator();iterator.hasNext(); ){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) iterator.next();
					if(recursos_baLocal.getDe_autoinst_a_pgasc() == null){
						recursos_baLocal.setDe_autoinst_a_pgasc( new Integer ( 1 ) );
					}
				}
			} catch (NamingException e) {
				log.debug("Error NamingException "+e.toString());
			} catch (FinderException e) {
				log.debug("Error FinderException "+e.toString());
			}	
		}

		public boolean validarEnvioTrEnPGACS( String idActuacion ) throws ATiempoAppEx {
			Agenda_scLocalHome agenda_scLocalHome = null;
			try{
				if(peticionLocalHome == null ){
					peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				}
				
				agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				Agenda_scKey agenda_scKey = new Agenda_scKey( idActuacion );
				Agenda_scLocal agenda_scLocal = agenda_scLocalHome.findByPrimaryKey( agenda_scKey );
				PeticionKey peticionKey = new PeticionKey( agenda_scLocal.getPeti_numero() );
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey( peticionKey );
				
				Collection collectionRecursosBa = peticionLocal.getRecursos_ba();
				
				for( Iterator iterator = collectionRecursosBa.iterator(); iterator.hasNext(); ){
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) iterator.next();
					if( recursos_baLocal.getDe_autoinst_a_pgasc() != null && recursos_baLocal.getDe_autoinst_a_pgasc().intValue() == 1 ){
						return true;
					}
				}
				
			}catch(NamingException ne ){
				log.debug("Error al crear objeto LocalHome "+ne.toString());
				return false;
			}catch(FinderException fe){
				log.debug("Error no se encontro registro en la base de datos: "+fe.toString());
				return false;
			}
			return false;
		}
		/**
		 * DAVID: Método para verificar si una petición es de cambio de número.
		 * @param petiNumero
		 * @return
		 */
		public boolean esCambioDeLinea(Long petiNumero){
			boolean esCambioDeLinea = false;
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey peticionKey = new PeticionKey( petiNumero );
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey( peticionKey );
				
				Collection productoServicioPeticionList=peticionLocal.getProducto_servicio_peticion();
				
				
				Iterator productoServicioPeticionIt=null;
				
				for(productoServicioPeticionIt=productoServicioPeticionList.iterator();productoServicioPeticionIt.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIt.next();
					Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
					Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
					
					Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
					Long familiaId=familia_producto_servicioKey.faps_id;
					
					if(operacion_comercialKey.opco_id.longValue()==ComunInterfaces.OPCO_CAMBIO_NUMERO&&familiaId.intValue()==ComunInterfaces.familiaPcLinea){
						log.debug("Tiene operación comercial de cambio de número..");
						esCambioDeLinea=true;
						break;
					}
				}
			}catch(FinderException e){
				log.debug("Error de finder en esCambioDeLinea().."+e.toString());
			}catch(NamingException e){
				log.debug("Error de naming en esCambioDeLinea().."+e.toString());
			}
			return esCambioDeLinea;
			 
		}
		
		/*RQ 5606 - Internet Movil*/
		/**
		 * 
		 */
		public void enviarConfiguracionInternetMovil(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx{
			TR610E tr610e = new TR610E();
			
			try{
				log.debug("Ingresa a enviarConfiguracionInternetMovil, en la actividad "+ act.getCodigoActividad() +", para la petición Atiempo No. "+nroPeticion);
				
				/*Se obtiene propiedad de base de datos con PSs para los que aplica Internet Movil*/
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.LLAVE_PROPERTIES_PS_INTERNET_MOVIL);
				
				String psEnvioIntMovil = propertiesBDLocal.getValor();
				String[] listaPsEnvioIntMovil = null;
		        boolean esPSInternetMovil = false;
		        
				if(psEnvioIntMovil != null && !psEnvioIntMovil.equals("")){
					listaPsEnvioIntMovil = psEnvioIntMovil.split(",");        
			        
			        Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
	        		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_INTERNET_MOVIL));
	        		/*Valida que la petición tenga por lo menos un PS de los configurados en la tabla de propiedades (PS_INTERNET_MOVIL)*/
	        		forPadre: for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
	        			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
	        			
	        			for(int contPsIntEquipado=0;contPsIntEquipado<=listaPsEnvioIntMovil.length-1;contPsIntEquipado++){
	        				if (peticionFlujoLocal.getPrse_id().toString().equals(listaPsEnvioIntMovil[contPsIntEquipado])){
	        					esPSInternetMovil = true;
		    					break forPadre;
	        				}
	        			}
	        		}
				}
				
				 /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(nroPeticion);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
			
		        /*Valida que la petición tenga equipos configurados en caso contrario se inhibe la actividad*/
				boolean tieneEquipos = true;
				Collection equiposElPet = peticionLocal.getElemento_peticion();
		        if (equiposElPet.size() == 0) {
		            log.debug("INFO: No existen Equipos Asociados a la Peticion. No se enviará la mensajeria de Internet Movil.");
		            tieneEquipos = false;	            
		        }
				
		        /*Se valida si se debe inhibir la actividad o no*/
		        String mensajeBitacora = new String("");
		        if(!tieneEquipos){
		        	/*Se manda a PGI por no tener equipos configurados*/
		        	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					act.setObservacion("Se deriva la petición a PGI porque no trae equipos para enviar configurar internet movil");
					act.setRealizarTerminoInmediato(true);
		        } else if(!esPSInternetMovil){ 
		        	/*Se inhibe la actividad por no tener PSs de Internet Movil*/
		        	log.debug("Se inhibe la actividad, para la petición Atiempo No. "+nroPeticion);
		        	mensajeBitacora = "Se inhibe la actividad porque no existen PS's de Internet Movil asociados a la petición.";
					
					act.setObservacion(mensajeBitacora, true);
					act.setRealizarTerminoInmediato(true);
		        } else if((esPSInternetMovil && tieneEquipos && act.getCodigoActividad() != null && act.getCodigoActividad().equals(ComunInterfaces.COD_ACT_CONFIG_INTERNET_MOVIL)) 
		        	|| (esPSInternetMovil && tieneEquipos && act.getCodigoActividad() != null && act.getCodigoActividad().equals(ComunInterfaces.COD_ACT_LEGALI_INTERNET_MOVIL))){
		        	
					/*Se asignan los datos del encabezado*/
					Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
					Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
					
					/*Asigna unos datos de acuerdo a la actividad en la que se encuentra*/
					if(act.getCodigoActividad() != null && act.getCodigoActividad().equals(ComunInterfaces.COD_ACT_CONFIG_INTERNET_MOVIL)){
						tr610e.setInterfaz("CONFIG_INTERNET_MOVIL");
						if(peticionLocal.getNom_ds() != null)
							tr610e.setClientLastName(peticionLocal.getNom_ds());
						else
							tr610e.setClientLastName("");
						
					}else if(act.getCodigoActividad() != null && act.getCodigoActividad().equals(ComunInterfaces.COD_ACT_LEGALI_INTERNET_MOVIL)){
						tr610e.setInterfaz("LEGALI_INTERNET_MOVIL");
						
						String dir = new String();
						String compDir = new String();
						if(peticionLocal.getNom_cal_ds() != null){
							String direccion = peticionLocal.getNom_cal_ds();
							if(direccion.length() > 44){
								dir = direccion.substring(0,44);
								compDir = direccion.substring(44);
							}else{
								dir = direccion;
								compDir = "";
							}
						}else{
							dir = "";
							compDir = "";
						}
						
						tr610e.setClientAddress(dir);
						tr610e.setClientAddressCom(compDir);
						
						LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
						LocalidadKey localidadPK = (LocalidadKey)localidadLocal.getPrimaryKey();
		
						if(localidadPK != null && localidadPK.cod_loc != null)
							tr610e.setCityCode(localidadPK.cod_loc);
						else
							tr610e.setCityCode("");
						
						if(peticionLocal.getNum_ide_nu_stb() != null)
							tr610e.setPhoneNumber(peticionLocal.getNum_ide_nu_stb());
						else
							tr610e.setPhoneNumber("");
						
						tr610e.setSalesNumber(peticionLocal.getNum_venta_int_movil());

					}else{
						tr610e.setInterfaz("SIN_DEFINIR");
					}
					
					/*CAMPOS COMUNES INDEPENDIENTE DE LA ACTIVIDAD*/
					
					Collection equipos = peticionLocal.getElemento_peticion();
					
					for (Iterator iter = equipos.iterator(); iter.hasNext();) {
			           Elemento_PeticionLocal elementoLocal = (Elemento_PeticionLocal) iter.next();
			           
			           if(elementoLocal.getTipo_elemento() != null && elementoLocal.getTipo_elemento().toString().equals("17")){
			           		tr610e.setModemSerialNumber(elementoLocal.getSerial());
			           		tr610e.setProductService(elementoLocal.getPs_id().intValue());
			           		tr610e.setModemBrand(elementoLocal.getMarca());
			           		tr610e.setModemModel(elementoLocal.getModelo());
			           }else if(elementoLocal.getTipo_elemento() != null && elementoLocal.getTipo_elemento().toString().equals("18")){
			           		tr610e.setSimSerialNumber(elementoLocal.getSerial());
			           		tr610e.setProductService(elementoLocal.getPs_id().intValue());
			           		if(elementoLocal.getNum_celular() != null)
			           			tr610e.setCellPhone(elementoLocal.getNum_celular());
			           		else
			           			tr610e.setCellPhone("");
			           }else{
			           		
			           }
					}
				
					tr610e.setId(idCorrelativoMensaje.toString());
					tr610e.setSource("ATIEMPO");
					tr610e.setDestination("ESB");
					tr610e.setVersion("1.0");
			        
			        /*Obtiene los datos a enviar*/
					tr610e.setAtiempoRequestNumber(nroPeticion.longValue());					
					
					if(peticionLocal.getTip_doc_cd() != null)
						tr610e.setClientIdType(peticionLocal.getTip_doc_cd());
					else
						tr610e.setClientIdType("");
					
					if(peticionLocal.getNum_doc_cli_cd() != null)
						tr610e.setClientIdNumber(peticionLocal.getNum_doc_cli_cd());
					else
						tr610e.setClientIdNumber("");
					
					
					

					/*CAMPOS COMUNES INDEPENDIENTE DE LA ACTIVIDAD*/


			        /*Se guardan los datos del mensaje en la tabla */
					Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion(peticionLocal);
			        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setMensaje_estado(mensajeOk);
			        
			        InternetMovilMQ internetMovilMQ = new InternetMovilMQ();
			        internetMovilMQ.enviarMensaje(tr610e);
		        }
		        
			}catch (FinderException e) {
	            log.error("Error al enviar configuración de Internet Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (CreateException e) {
	        	log.error("Error al enviar configuración de Internet Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (NamingException e) {
				log.error("Error al enviar configuración de Internet Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.NAMING);
			} catch (ATiempoAppEx e) {
				log.error("Error al enviar configuración de Internet Movil.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar configuración de Internet Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 
			
		}
		
		/**
		 * Metodo para procesar la respuesta de configuracion internet movil y de legalizar internet movil
		 * @author mfmendez
		 * @param tr610s
		 * @throws ATiempoAppEx
		 */
		public void procesarRespuestaInternetMovil(TR610S tr610s) throws ATiempoAppEx{
			
			BackendExecution bExecution = null;
			

			try {
				//df = new SimpleDateFormat ("yyyy/MM/dd") ;
		        bExecution = BackendTraceUtil.initExecution(tr610s, log);
		        bExecution.setNumPetAtiempo(new Long(tr610s.getAtiempoRequestNumber()));
		        bExecution.setIdMensajeOp(tr610s.getId());
		        bExecution.startOperation();
		        
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	//mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr610s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr610s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr610s.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto ;	   		    	
		    	
		    	actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
		    	
		    	if(tr610s != null && tr610s.getError() != null && tr610s.getError().equals("0")){
		    		/*Procesamiento de la respuesta*/
		    		/*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
			        PeticionKey key = new PeticionKey(new Long(tr610s.getAtiempoRequestNumber()));
			        //peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
			        
			        if(actDto != null && actDto.getCodigoActividad() != null && actDto.getCodigoActividad().equals(ComunInterfaces.COD_ACT_CONFIG_INTERNET_MOVIL)){
			        	if(tr610s.getSalesNumber() > 0){
				        	peticionLocal.setNum_venta_int_movil(tr610s.getSalesNumber());
				        }
			        	
			        	actDto.setObservacion("Se recibe respuesta de Configuración de Internet Movil sin error.", true);
			        }else if(actDto != null && actDto.getCodigoActividad() != null && actDto.getCodigoActividad().equals(ComunInterfaces.COD_ACT_LEGALI_INTERNET_MOVIL)){
			        	if(tr610s.getObservation() != null && !tr610s.getObservation().equals(""))
			        		actDto.setObservacion("Observaciones de Legalización de Internet Movil: "+tr610s.getObservation(), true);
			        	else
			        		actDto.setObservacion("Se recibe respuesta de Legalización de Internet Movil sin observaciones y sin error.", true);
			        }
			        
		        	/*Cierre de la actividad exitoso*/
					mensajeEstadoBa.setId_error(tr610s.getError());
					mensajeEstadoBa.setDesc_error(tr610s.getErrorMessage());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));					
					actividadEJB.terminar (actDto);
		        }else{
		        	ErrorLegadoLocal errorLegado = getErrorLegado(tr610s.getError(),"TR610S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr610s.getErrorMessage());
							actividadEJB.terminar(actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					/*Inserción del quiebre*/
					insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					mensajeEstadoBa.setId_error(tr610s.getError());
					mensajeEstadoBa.setDesc_error(tr610s.getErrorMessage());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					if(actDto != null && actDto.getCodigoActividad() != null && actDto.getCodigoActividad().equals(ComunInterfaces.COD_ACT_CONFIG_INTERNET_MOVIL)){
						actDto.setObservacion("Error al recibir respuesta de Configuración Internet Movil, se deriva a "+bandeja+". Respuesta: "+tr610s.getError()+" - "+tr610s.getErrorMessage(), true);
			        }else if(actDto != null && actDto.getCodigoActividad() != null && actDto.getCodigoActividad().equals(ComunInterfaces.COD_ACT_LEGALI_INTERNET_MOVIL)){
			        	actDto.setObservacion("Error al recibir respuesta de Legalización Internet Movil, se deriva a "+bandeja+". Respuesta: "+tr610s.getError()+" - "+tr610s.getErrorMessage(), true);
			        }
					
					actividadEJB.terminar (actDto);
		        }
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion con id: " + tr610s.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion. con id: " + tr610s.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion. con id: " + tr610s.getId() +".", e);
			} catch (CreateException e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion. con id: " + tr610s.getId() +".", e);
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion. con id: " + tr610s.getId() +".", e);
			} catch (ATiempoAppEx e) {
				throw e;
			} catch (Exception e) {
				log.error("Error procesando la respuesta TR610S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR610S o cerrando la peticion. con id: " + tr610s.getId() +".", e);
			}finally{
				bExecution.endAll();
				
			}
			
		}
		/*FIN - RQ 5606 - Internet Movil*/
		
	/*RQ 6142 - mfmendez - WS Aula*/
	/**
	  * Metodo para validar si se debe enviar modificación para los SVAs (Terra_CDS, Terra_Sonora, Terra_SiteBuilder,
	  * Aula, WebTutor, TeraBox y WebFilterOptenet)
	  * @param act
	  * @param nroPeticion
	  * @return -1 si no aplica la modificacion, M o 200 si se debe ir por modificación
	  * @throws ATiempoAppEx
	  */ 
	 public String validaOperacionComercialSVA(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx{
	 	log.debug("Ingreso a validaOperacionComercialSVA para la actividad con Id. igual a "+act.getIdActividadFlujo());
	 	String psEnvio = ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA;
	 	
	 	Peticion_flujoLocalHome peticionFlujoLocalHome;
	 	boolean esModificacion = false;
	 	try {
	 		peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
	 		
	 		Integer idActividad = act.getIdActividadFlujo();
	 		Collection peticionFlujoList = null;
	 		int intIdAct = 0;
	   
	 		if(idActividad != null){
	 			intIdAct = idActividad.intValue();
	 			if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_CDS){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_TERRA_CDS));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_SONORA){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_TERRA_SONORA));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_SITEBUILDER){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_TERRA_SITEBUILDER));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_ANTIVIRUS){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_TERRA_ANTIVIRUS));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_AULA){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_AULA));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_WEBTUTOR){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_WEBTUTOR));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_WEBFILTER_OPTENET){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_WEBFILTER_OPTENET));
	 			}else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERABOX){
	 				peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, new Integer(ComunInterfaces.ID_ACT_CONFIG_TERABOX));
	 			}else{
	 				log.debug("Se devuelve como Operación Comercial -1 ya que la actividad en la que se llamo el metodo, " +
	 						"no corresponde a una actividad de SVA.");
	 				return ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA;
	}
	 		}
	 		//int contadorPetFlujo = 0;
	 		ArrayList ocPsActividad = new ArrayList();
	 		boolean ocPsExiste = false;
	   
	 		for (Iterator psOkIter = act.getPsOk().iterator(); psOkIter.hasNext();){
	 			PsVsOcVO psTemp= (PsVsOcVO)psOkIter.next();
	 			ocPsExiste = false;
	 			
	 			for(Iterator ocPsActIter = ocPsActividad.iterator();ocPsActIter.hasNext() && !ocPsExiste;){
	 				if(psTemp.getOpComId().equals((Long)ocPsActIter.next())){
	 					ocPsExiste = true;
	 				}
	 			}
	    
	 			if(!ocPsExiste){
	 				ocPsActividad.add(psTemp.getOpComId());
	 			}
	 			//Operacion_comercialKey opK = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
	 		}

	 		/*Si a la actividad llego mas de un PS con operaciones comerciales diferentes es modificacion*/
	 		if(ocPsActividad.size() > 1){
	 			esModificacion = true;
	 			if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_CDS ||
	 				intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_SONORA ||
					intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_SITEBUILDER ||
					intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERRA_ANTIVIRUS ||
					intIdAct == ComunInterfaces.ID_ACT_CONFIG_AULA ||
					intIdAct == ComunInterfaces.ID_ACT_CONFIG_TERABOX ||
					intIdAct == ComunInterfaces.ID_ACT_CONFIG_WEBFILTER_OPTENET){
	 				psEnvio = ComunInterfaces.OC_MODIFICACION_SVA;
	 			} else if(intIdAct == ComunInterfaces.ID_ACT_CONFIG_WEBTUTOR){
	 				psEnvio = ComunInterfaces.OPERACION_MODIFICAR;
	 			}
	 		}else{
	 			psEnvio = ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA;
	 		}
	 		
	 	return psEnvio;
	   
	 	} catch (NamingException e) {
	 		log.error("NamingException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".");
	 		throw new ATiempoAppEx("NamingException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".", e);
	 	} catch (FinderException e) {
	 		log.error("FinderException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".");
	 		throw new ATiempoAppEx("FinderException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".", e);
	 	} catch (EJBException e) {
	 		log.error("EJBException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".");
	 		throw new ATiempoAppEx("EJBException: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".", e);
	 	} catch (Exception e) {
	 		log.error("Exception: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".");
	 		throw new ATiempoAppEx("Exception: Error validando la operación comercial para SVA, en la petición con id. "+nroPeticion+".", e);
	 	}
	 }
	
	/**
	 * Metodo para devolver un PsVsOcVO que tenga un PS de alta como prioridad, para ser usado
	 * cuando se vaya a enviar una modificacion, enviar un PS de alta si tiene, si no tiene devuelve
	 * uno al azar de los que se encuentran OK en la actividad
	 * @param act
	 * @return PsVsOcVO con un PS con OC de alta, uno al azar si no hay de alta, null si no hay PS Ok en esta actividad
	 * @throws ATiempoAppEx
	 */
	public PsVsOcVO devuelvePSPrioridadAltaSVA(ActividadEJBDTO act) throws ATiempoAppEx{
		log.debug("Ingreso a devuelvePSPrioridadAltaSVA para la actividad con Id. igual a "+act.getIdActividadFlujo());
		PsVsOcVO psAlta = null;
		
		Peticion_flujoLocalHome peticionFlujoLocalHome;
		boolean encontroOCAlta = false;
		try {					 
			ArrayList ocPsOk = new ArrayList();
			boolean ocPsEsta = false;
			
			for( Iterator iterTemp = act.getPsOk().iterator(); iterTemp.hasNext() && !encontroOCAlta; ){
				//Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
				PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
				ocPsEsta = false;
				for(Iterator ocPsIter = ocPsOk.iterator();ocPsIter.hasNext() && !ocPsEsta;){
					PsVsOcVO psAlamcen= (PsVsOcVO) ocPsIter.next();
					if(psTemp.equals(psAlamcen)){
						ocPsEsta = true;
					}
				}
				
				if(!ocPsEsta){
					if(psTemp.getOpComTipo() != null && (psTemp.getOpComTipo().equals("A") || psTemp.getOpComTipo().equals("ACP"))){
						psAlta = psTemp;
						encontroOCAlta = true;
					}
					ocPsOk.add(psTemp);
				}	
			}			
			
			/*Si no encontró ningun PS con algun tipo de Alta, se devuelve un PsVsOcVO al azar si hay PS Ok en la actividad*/
			if(!encontroOCAlta){
				if(ocPsOk.size() > 0){
					psAlta = (PsVsOcVO) ocPsOk.get(0);
				}else{
					ArrayList psOkAct = act.getPsOk();
					
					if(psOkAct.size() > 0){
						return ((PsVsOcVO)psOkAct.get(0));
					}else{
						return null;
					}
				}
			}

			return psAlta;
			
		} catch (EJBException e) {
			log.error("EJBException: RecursosBABean: devuelvePSPrioridadAltaSVA: en la petición con id. "+act.getNumeroPeticion()+".");
            throw new ATiempoAppEx("EJBException: RecursosBABean: devuelvePSPrioridadAltaSVA: en la petición con id. "+act.getNumeroPeticion()+".", e);
		} catch (Exception e) {
			log.error("Exception: RecursosBABean: devuelvePSPrioridadAltaSVA: en la petición con id. "+act.getNumeroPeticion()+".");
            throw new ATiempoAppEx("Exception: RecursosBABean: devuelvePSPrioridadAltaSVA: en la petición con id. "+act.getNumeroPeticion()+".", e);
		}

	}
	/*FIN RQ 6142 - mfmendez - WS Aula*/
	public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica)throws ATiempoAppEx{
		String valor = null;

		if (producto_servicio_peticionLocal != null){
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			for(Iterator iterator2=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
			{
				Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterator2.next();
				Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
				if (spk.cod_crc_cd.longValue()== caracteristica.longValue())
				{
					log.debug("Informacion : Se obtuvo La Caracteristica " + caracteristica.longValue() + " : " +subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
					valor = subpeticion_caracteristicasLocal.getVal_ral_crc_cd();
					break;
				}
			}
		}
		return valor;
	}
	
	/**
	 * Metodo para el envío de la consulta de actuación a Agenda SC
	 * @author mfmendez
	 * @param act, actividad
	 * @param nroPeticion, numero de peticion atiempo
	 * @param actGeneradora, cadena de actividad generadora
	 * @throws ATiempoAppEx
	 */
	public void enviarConsultaActuacionASC(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
		try{
			log.debug("Ingresa a enviarConsultaActuacionASC, para la petición Atiempo No. "+nroPeticion);
		
			Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			boolean encontroActuacionAbierta = false;
			Agenda_scLocal agendaScLocalAbierta = null;
			
			try{
				Collection agendamientos = agenda_SCLocalHome.findByPetiNumero(nroPeticion);
				
				for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
					Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
					if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
							|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
						encontroActuacionAbierta = true;
						agendaScLocalAbierta = agendaSCLocal;
						break;
					}
				}
			} catch (FinderException e) {
	            log.debug("No se encontraron actuaciones de Agenda asociadas a la petición atiempo No. "+nroPeticion+". ", e);
	            encontroActuacionAbierta = false;
	        }
			
			/*Envia la consulta de actuación solo si encontró alguna actuación abierta*/
			if(encontroActuacionAbierta){
				Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
		        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(nroPeticion);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
		        
		        TR702E tr702e = new TR702E();
		        /*Datos del encabezado*/
		        tr702e.setId(idCorrelativoMensaje.toString());
		        tr702e.setDestination("ESB");
		        tr702e.setSource(ComunInterfaces.sistemaAtiempo);
		        tr702e.setInterfaz(ComunInterfaces.INTERFAZ_CONSULTA_ACTUACION);
		        tr702e.setVersion("1.0");		
				
				/*Datos del body*/
		        tr702e.setIdSystemOrigin("ATIEMPO");
		        
		        Agenda_scKey ascKey = (Agenda_scKey) agendaScLocalAbierta.getPrimaryKey();
		        tr702e.setIdSchedule(ascKey.id_actuacion.toString());
		        
		        
		        if(peticionLocal.getTip_doc_cd() != null){
		        	tr702e.setIdType(peticionLocal.getTip_doc_cd());
		        }else{
		        	tr702e.setIdType("");	
		        }
		        
		        if(peticionLocal.getNum_doc_cli_cd() != null){
		        	tr702e.setCustomerId(peticionLocal.getNum_doc_cli_cd());
		        }else{
		        	tr702e.setCustomerId("");
		        }
		        		     
		        /*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
					        
				if(pkAtis.cod_pet_cd != null)
					tr702e.setAtisRequestNumber(pkAtis.cod_pet_cd.toString());
				else
					tr702e.setAtisRequestNumber("0");
					
				if(peticionLocal.getNum_ide_nu_stb() != null)
					tr702e.setTelephoneNumber(peticionLocal.getNum_ide_nu_stb());
				else
					tr702e.setTelephoneNumber("");			
	
		        /**/
	        	if(!tr702e.getIdSchedule().equals("")){
	        		
	        		ConsultaActuacionSCMQ consultaActMQ = new ConsultaActuacionSCMQ();
	        		consultaActMQ.enviarMensaje(tr702e);
			        
					Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion(peticionLocal);
			        msgLocal.setCod_actividad_generadora(actGeneradora);
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setMensaje_estado(mensajeOk);			        			 
				}else{
					log.error("Error al enviar el mensaje para la consulta de la actuación por no tener un id de agendmiento valido, para la petición: "+ nroPeticion +". Se deriva a PGI.");
					insertarCausalesCnaPeticion(peticionLocal,actGeneradora,/*Quiebre por defecto*/new Long(701),act.getIdActividadFlujo());
					
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					act.setObservacion("Error al enviar la consulta de actuacion de Agenda por no contener los datos necesarios para la consulta, se deriva a PGI.", true);
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				log.debug("Se inhibe la actividad de Validación Actuación ASC por no tener ninguna actuación abierta asociada a la petición: "+ nroPeticion);				
				act.setObservacion("Se inhibe la actividad por no tener asociada ninguna actuacion abierta.", true);
				act.setRealizarTerminoInmediato(true);
			}
		} catch (FinderException e) {
            log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		} 		
	}
	
	/**
	 * Metodo para procesar la respuesta de la consulta de actuación a Agenda SC
	 * @param tr702s, Mensaje TR702S en el que llega la respuesta
	 * @throws ATiempoAppEx
	 */
	public void procesarRespuestaConsultaActuacionASC(TR702S tr702s) throws ATiempoAppEx{
		BackendExecution bExecution = null;

		try {
	        bExecution = BackendTraceUtil.initExecution(tr702s, log);	        
	        bExecution.setIdMensajeOp(tr702s.getId());
	        bExecution.startOperation();
	        
	        Mensaje_estado_baLocal mensajeEstadoBa = null;
	        PeticionKey peticionKey = null;
	        try {
	        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr702s.getId())));
	        } catch (FinderException e1) {
	        	mensajeEstadoBa = null;
	        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr702s));
	            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr702s.getId(), ATiempoAppEx.FINDER);
	        }
	        			
	        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora()) ;
	    	ActividadEJBDTO actDto ;	   		    	
	    	
	    	if(tr702s != null && tr702s.getError() != null && tr702s.getError().equals("0")){
	    		/*Se valida el estado de la actuación*/
	    		PeticionLocal peticionLocal = mensajeEstadoBa.getPeticion();
	    		
	    		Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
				Agenda_scLocal agendaScLocalAbierta = null;
				
				try{
					peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
					Collection agendamientos = agenda_SCLocalHome.findByPetiNumero(peticionKey.peti_numero);
					if(agendamientos != null){
						for (Iterator agendamientoIterator = agendamientos.iterator(); agendamientoIterator.hasNext();){
							Agenda_scLocal agendaSCLocal = (Agenda_scLocal)agendamientoIterator.next();
							if (agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
									|| agendaSCLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
								agendaScLocalAbierta = agendaSCLocal;
								break;
							}
						}
					}
					
				} catch (FinderException e) {
		            log.debug("No se encontraron actuaciones de Agenda asociadas a la petición atiempo No. "+peticionKey.peti_numero.toString()+". ", e);
		            agendaScLocalAbierta = null;
		        }
				
				boolean estaCancelada = false;
				boolean estaCerrada = false;
				if(agendaScLocalAbierta != null){
					
					Agenda_scKey agendaAbiertaKey = (Agenda_scKey) agendaScLocalAbierta.getPrimaryKey();
					Collection agendamientosTr = tr702s.getSchedules();
					if(agendamientosTr != null){
						for(Iterator it = agendamientosTr.iterator();it.hasNext();){
							TR702SSchedule schedule702s = (TR702SSchedule) it.next();
							if(agendaAbiertaKey.id_actuacion.equals(schedule702s.getIdSchedule())){
								if(schedule702s.getCodeStateSchedule().equals(new Long(ComunInterfaces.ACTUACION_CANCELADA))){
									estaCancelada = true;
									agendaScLocalAbierta.setEstado(new Integer(ComunInterfaces.ACTUACION_CANCELADA));
								}else if(schedule702s.getCodeStateSchedule().equals(new Long(ComunInterfaces.ACTUACION_CERRADA))){
									estaCerrada = true;
									agendaScLocalAbierta.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
								}
							}
						}
					}
				}
				
	        	/*Cierre de la actividad exitoso*/
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				mensajeEstadoBa.setId_error(tr702s.getError());
				mensajeEstadoBa.setDesc_error(tr702s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				if(estaCancelada){
					actDto.setObservacion("Se procesa correctamente la respuesta de la consulta de la actuacion. Se cancela la actuacion por encontrarse cancelada en Agenda.", true);
				}else if(estaCerrada){
					actDto.setObservacion("Se procesa correctamente la respuesta de la consulta de la actuacion. Se cierra la actuacion por encontrarse cerrada en Agenda.", true);
				}else{
					actDto.setObservacion("Se procesa correctamente la respuesta de la consulta de la actuacion. Se mantiene abierta la actuacion por encontrarse activa en Agenda", true);
				}
				
				actividadEJB.terminar (actDto);
	        }else{	        	
	        	/*Cierre de la actividad con error*/	        	
				actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
				/*Inserción del quiebre*/
				insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
				ErrorLegadoLocal errorLegado = getErrorLegado(tr702s.getError(),"TR702S");
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				if(errorLegado != null){
					if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
						actDto.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr702s.getErrorMessage());
						actividadEJB.terminar(actDto);
						return;
					}
					plataforma = errorLegado.getPlataforma(); 
					bandeja	= getNombreBandeja(plataforma); 
				}
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				mensajeEstadoBa.setId_error(tr702s.getError());
				mensajeEstadoBa.setDesc_error(tr702s.getErrorMessage());
				mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
				actDto.setObservacion("Error al recibir respuesta de la consulta de actuación, se deriva a "+bandeja+". Respuesta: "+tr702s.getError()+" - "+tr702s.getErrorMessage(), true);
				actividadEJB.terminar (actDto);
	        }
		} catch (EJBException e) {
			log.error("Error procesando la respuesta TR702S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR702S o cerrando la peticion con id: " + tr702s.getId() +".", e);
		} catch (TnProcesoExcepcion e) {
			log.error("Error procesando la respuesta TR702S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR702S o cerrando la peticion. con id: " + tr702s.getId() +".", e);
		} catch (NamingException e) {
			log.error("Error procesando la respuesta TR702S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR702S o cerrando la peticion. con id: " + tr702s.getId() +".", e);
		} catch (CreateException e) {
			log.error("Error procesando la respuesta TR702S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR702S o cerrando la peticion. con id: " + tr702s.getId() +".", e);
		} catch (FinderException e) {
			log.error("Error procesando la respuesta TR702S o cerrando la peticion.");
            throw new ATiempoAppEx("Error procesando la respuesta TR702S o cerrando la peticion. con id: " + tr702s.getId() +".", e);
		}  catch (ATiempoAppEx e) {
			throw e;
		} finally{
			bExecution.endAll();		
		}		
	}
	
	/**
	 * Metodo para el envío de la solicitud de reagendamiento a Agenda SC
	 * @author mfmendez
	 * @param act, actividad
	 * @param nroPeticion, numero de peticion atiempo
	 * @param actGeneradora, cadena de actividad generadora
	 * @throws ATiempoAppEx
	 */
	public Long enviarSolicitudReagendamientoASC(Long nroPeticion, String idActuacion,String opcionReagendamiento) throws ATiempoAppEx{
		Long retorno = null;
		try{
			log.debug("Ingresa a enviarSolicitudReagendamientoASC, para la petición Atiempo No. "+nroPeticion);
			String[] opcionReag=opcionReagendamiento.split("_");
			Agenda_scLocalHome agenda_SCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			boolean encontroActuacionAbierta = false;
			Agenda_scLocal agendaScLocalAbierta = null;
			
			try{
				Agenda_scKey keyAgenda = new Agenda_scKey(idActuacion);
				Agenda_scLocal agendaScLocal = agenda_SCLocalHome.findByPrimaryKey(keyAgenda);
				
				if (agendaScLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA
						|| agendaScLocal.getEstado().intValue() == ComunInterfaces.ACTUACION_REAGENDADA){
					encontroActuacionAbierta = true;
					agendaScLocalAbierta = agendaScLocal;
				}
			} catch (FinderException e) {
	            log.debug("No se encontro la actuación con Id. "+idActuacion);
	            encontroActuacionAbierta = false;
	        }
			
			/*Envia la consulta de actuación solo si encontró alguna actuación abierta*/
			if(encontroActuacionAbierta){
				Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
		        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(nroPeticion);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
		        
		        TR705E tr705e = new TR705E();
		        /*Datos del encabezado*/
		        tr705e.setId(idCorrelativoMensaje.toString());
		        tr705e.setDestination("ESB");
		        tr705e.setSource(ComunInterfaces.sistemaAtiempo);
		        tr705e.setInterfaz(ComunInterfaces.INTERFAZ_SOLICITUD_REAGENDAMIENTO);
		        tr705e.setVersion("1.0");		
				
				/*Datos del body*/
		        tr705e.setIdSystemOrigin("ATIEMPO");		        
		        tr705e.setIdSchedule(idActuacion);		        
		        
		       	tr705e.setIdOptionReschedule(opcionReag[0]);
	
		        /**/
	        	if(!tr705e.getIdSchedule().equals("") && !tr705e.getIdOptionReschedule().equals("")){
	        		
	        		SolicitudReagendamientoSCMQ solicReagendMQ = new SolicitudReagendamientoSCMQ();
	        		solicReagendMQ.enviarMensaje(tr705e);
			        
					Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion(peticionLocal);
			        msgLocal.setCod_actividad_generadora("FRONT_VPI");
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setDesc_error(opcionReag[1]);
			        msgLocal.setMensaje_estado(mensajeOk);	
			        
			        retorno= idCorrelativoMensaje ;
				}else{
					log.error("Error al enviar la solicitud de reagendamiento, algunos valores son nulos y/o vacios, para la petición: "+ nroPeticion +".");
				}
			}else{
				log.error("Error al enviar la solicitud de reagendamiento, la actuación con id: "+ idActuacion +" no se encuentra abierta, para la petición: "+ nroPeticion +".");							
			}
			
			return retorno;
		} catch (FinderException e) {
            log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
        } catch (CreateException e) {
        	log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (ATiempoAppEx e) {
			log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw e;
		} catch (Exception e) {
			log.error("Error al enviar la consulta de la actuación de Agenda.", e);
            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}
	
	/**
	 * Metodo para procesar la respuesta de la solicitud de reagendamiento a Agenda SC
	 * @param tr705s, Mensaje TR705S en el que llega la respuesta
	 * @throws ATiempoAppEx
	 */
	public void procesarRespuestaSolicitudReagendamientoASC(TR705S tr705s) throws ATiempoAppEx{
		try{
			
			Long idCorrelativoMensaje=new Long(tr705s.getId());
			Mensaje_estado_baKey mensajeKey= new Mensaje_estado_baKey(idCorrelativoMensaje);
			Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(mensajeKey);
			msgLocal.setFecha_cierre(df.format(new java.util.Date()));
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome)HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			String strIdActuacion=tr705s.getIdSchedule();
			Agenda_scKey agendaSCKey = new Agenda_scKey(strIdActuacion);
			Agenda_scLocal agendaSCLocal = agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
			if(tr705s.getError()==null || tr705s.getError().equals("0")){
				Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
				agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
				SimpleDateFormat formatoFecha = new SimpleDateFormat(ComunInterfaces.formatoFechaTR703S);
					
				try {
					Date d = formatoFecha.parse(tr705s.getRescheduleDate());
					Timestamp t= new Timestamp(d.getTime());
					agendaSCLocal.setFecha_reagm(t);
				} catch (ParseException e1) {
					log.error("Error al convertir la fecha de reagendamiento "+tr705s.getRescheduleDate(),e1);
				}
				agendaSCLocal.setNombre_contratista(msgLocal.getDesc_error());
				msgLocal.setDesc_error(null);
			}else{
				Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				msgLocal.setDesc_error(tr705s.getErrorMessage());
				msgLocal.setMensaje_estado(mensajeError);
				
			}
			
			Long idPeticionAux = agendaSCLocal.getPeti_numero();
			
			
			PeticionLocal peticionLocal = agendaSCLocal.getPeticion();
			
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (tr705s.getId()));
			tmpAgendaSCLocal.setAgenda_sc(agendaSCLocal);
			tmpAgendaSCLocal.setPeticion(agendaSCLocal.getPeticion());
			tmpAgendaSCLocal.setXml(XMLUtilities.marshall (tr705s));

			
		}catch(FinderException e){
			log.error("Error en la búsqueda de objeto en la peticion en alta reagendamiento de Agenda SC: ",e);
		} catch (NamingException e) {
			log.error("Error buscando objeto de BD en reagendamiento de Agenda SC",e);
		} catch(CreateException e){
			log.error("Error creando objeto de BD en reagendamiento de Agenda SC",e);
		}
	}
	
	/**
	 * Metodo para validar si los PS y sus estados en la BD son validos para el envío a Agenda
	 * @param idPeticionAT, id de petición Atiempo
	 * @return esValido, true si existe por lo menos un ps que se envía a Agenda
	 * @return esValido, false si no se envía ningun PS a Agenda 
	 */
	public boolean validarPSEnvioAltaActuacionPGC(Long idPeticionAT){
		
		log.debug("Ingreso al metodo validarPSEnvioAltaActuacionPGC en RecursosBABean.");
		boolean esValido = false; 	
		try {
			Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			
			Producto_servicio_peticionLocalHome productoServicioPeticionLocalHome = (Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection productsService = new ArrayList();
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			Long ocAutoInstalacion=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(OPCO_AUTOINSTALACION));
			
			Collection psActividadCollection = peticionFlujoLocalHome.findActividad(idPeticionAT,new Integer(ID_ACTIVIDAD_INSTALACION));
			for (Iterator psActividadIterator=psActividadCollection.iterator(); psActividadIterator.hasNext();){
				Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal) psActividadIterator.next();
				boolean psConQuiebre = false;
				int famOpLocal = 0;
			
				Operacion_comercialKey opcoKey = (Operacion_comercialKey)peticionFlujoLocal.getFk_opco_2_pefl().getPrimaryKey();
				Collection productosCollection = productoServicioPeticionLocalHome.findByPetiNumeroPsYOpCo(idPeticionAT, peticionFlujoLocal.getPrse_id(), opcoKey.opco_id);
				
				for (Iterator productosIterator = productosCollection.iterator(); productosIterator.hasNext(); ){
					Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
					famOpLocal = productoLocal.getFamiliaKey().faps_id.intValue();		
					Collection estadoPSCollection = productoLocal.getEstado_ps_peticion();
					if(estadoPSCollection != null && estadoPSCollection.size()>0){
						for (Iterator estadoPSIterator = estadoPSCollection.iterator(); estadoPSIterator.hasNext();){
							Estado_ps_peticionLocal estadoPSLocal = (Estado_ps_peticionLocal)estadoPSIterator.next();
							
							if (estadoPSLocal.getCod_estado_cierre() != null && estadoPSLocal.getCod_estado_cierre().intValue() == ComunInterfaces.estadoCierreError){
								psConQuiebre = true;
								Familia_producto_servicioKey familia_producto_servicioKey =  productoLocal.getFamiliaKey();
								Long familiaPsId = familia_producto_servicioKey.faps_id;
								Operacion_comercialLocal operacion_comercialLocal = productoLocal.getOperacion_comercial();
								Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
								
								//DAVID: si vienen los ps's con quiebre, vemos si es OC=96 y ps de BA para setearlos en la tr701e.
//								REQ BA NAKED adicion familia PC naked
								if((familiaPsId.intValue()==ComunInterfaces.familiaPcBA || familiaPsId.intValue()==ComunInterfaces.familiaPcPsBANaked)&&operacion_comercialKey.opco_id.longValue() == ocAutoInstalacion.longValue()){
									TR701EProductService productService = new TR701EProductService();
									productService.setPsId(peticionFlujoLocal.getPrse_id().toString());
									productService.setOperationComercial(OC_REVERSA_AUT_INST);
									productsService.add(productService);
								}							
							}else{
								psConQuiebre = false;							
								TR701EProductService productService = new TR701EProductService();
								productService.setPsId(peticionFlujoLocal.getPrse_id().toString());							
								productService.setOperationComercial(opcoKey.opco_id.toString());							
								productsService.add(productService);		
							}
						}
					}else{
						TR701EProductService productService = new TR701EProductService();
						productService.setPsId(peticionFlujoLocal.getPrse_id().toString());					
						productService.setOperationComercial(opcoKey.opco_id.toString());
						productsService.add(productService);	
					}
				}				
			}
			
			esValido = false;
			if(productsService != null && productsService.size() > 0){
				for(Iterator i = productsService.iterator(); i.hasNext(); ){
					TR701EProductService ps = (TR701EProductService) i.next();
					
					if(ps.getPsId() != null && !ps.getPsId().equals("")
							&& ps.getOperationComercial() != null && !ps.getOperationComercial().equals("")){
						
						esValido = true;
						break;
					}
				}				
			}else{
				esValido = false;
			}
			
			return esValido;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return esValido;
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return esValido;
		} catch (ATiempoAppEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return esValido;
		}
	}
	/**
	 * Metodo para obtener la velocidad del plan de BA 
	 * @author mfmendez
	 * @param petiNumero, número de petición Atiempo
	 * @return velocidad, la velocidad del primer PS que este en la petición y que este en Alta (A o ACP)
	 */
	public String extraerVelocidadPlanYPSPrioridadAlta(Long petiNumero)throws ATiempoAppEx{
		String velocidadPlan=null;
		String velocidadPlanModem=null;
		String velocidad=null;
		
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome)HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			
			PeticionKey peticionkey = new PeticionKey(petiNumero);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionkey);
			
			Collection listaDePs=peticionLocal.getProducto_servicio_peticion();
			Iterator listaDePsIt=null;
			for(listaDePsIt=listaDePs.iterator();listaDePsIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaDePsIt.next();
				Familia_producto_servicioKey familia_producto_servicioKey = producto_servicio_peticionLocal.getFamiliaKey();
				Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey();
				
				Long psId = producto_servicio_peticionLocal.getPsId();					
				Long familiaId = familia_producto_servicioKey.faps_id;
				Long operacionComercial = operacion_comercialKey.opco_id;
				
				/*Se valida el PS es de la familia PcBA*/
//				REQ BA NAKED adicion familia PC naked
				if (familiaId.intValue() == ComunInterfaces.familiaPcBA || familiaId.intValue() == ComunInterfaces.familiaPcPsBANaked){
					log.debug("Es ps de BA, evaluamos si es de tipo módem o no..");
					
					Producto_servicioKey producto_servicioKey = new Producto_servicioKey(psId);							
				    Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);						
					
				    /*Se valida si la operación comercial del ps es tipo "A" o "ACP"*/
				    if(operacion_comercialLocal.getOpco_tipo() != null && 
				    		(operacion_comercialLocal.getOpco_tipo().equals("A") || 
				    				operacion_comercialLocal.getOpco_tipo().equals("ACP"))){
				    	try{
							Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psId);	
							if(producto_servicioLocal.getVelocidad() != null)
								velocidadPlanModem = producto_servicioLocal.getVelocidad()+"#"+psId;
							else
								velocidadPlanModem = ""+"#"+psId;
							
							break;
						}catch(FinderException e){
							log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
							if(producto_servicioLocal.getVelocidad() != null)							
								velocidadPlan = producto_servicioLocal.getVelocidad()+"#"+psId;
							else
								velocidadPlan = ""+"#"+psId;
							
							break;
						}
				    }else{
				    	try{
							Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psId);							
							if(producto_servicioLocal.getVelocidad() != null)
								velocidadPlanModem = producto_servicioLocal.getVelocidad()+"#"+psId;
							else
								velocidadPlanModem = ""+"#"+psId;
							
						}catch(FinderException e){
							log.debug("No es ps tipo módem pero si de BA, se extraerá su velocidad..");
							if(producto_servicioLocal.getVelocidad() != null)							
								velocidadPlan = producto_servicioLocal.getVelocidad()+"#"+psId;
							else
								velocidadPlan = ""+"#"+psId;
						}
				    }
				}
			}
		}catch(FinderException e){
			log.debug("FinderException en método extraerVelocidadPlanYPSPrioridadAlta() de RecursosBABean.."+e.toString());
		}catch(NamingException e){
			log.debug("NamingException en método extraerVelocidadPlanYPSPrioridadAlta() de RecursosBABean.."+e.toString());
		}
		
		if(velocidadPlan != null){
			velocidad = velocidadPlan;
		}else if(velocidadPlanModem != null){
			velocidad = velocidadPlanModem;
		}
		
		log.debug("La velocidad y PS devueltos en el metodo extraerVelocidadPlanYPSPrioridadAlta (Formato: Velocidad#PS) es: "+velocidad);
		
		return velocidad;
	}
	//Cesar Remigio (INDRA): se recibe la información de la configuración de Terabox
	public void enviarCorreoTeraBox(ActividadEJBDTO act, Long numeroPeticion, String codActividad, PsVsOcVO psprim ) throws ATiempoAppEx {
		log.debug("Entro a enviarCorreoTeraBox de la peticion: "+numeroPeticion);
		
		try {
			String email = "";
			String usuario = "";
			Long operacionComercial = new Long(0);
			Long psTeraBoxId = new Long(0);
			String country = VpistbbaConfig.getVariable("COUNTRY");
			boolean flag=false;
			String listaPsTeraBox = VpistbbaConfig.getVariable("PS_TERA_BOX");
			String[] arrayPsTeraBox = listaPsTeraBox.split(",");
			String accountExpiration = VpistbbaConfig.getVariable("ACCOUNTEXPIRATION");
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			Recursos_linea_basicaLocal recursos_linea_basicaLocal = null; 
				
			PeticionKey key = new PeticionKey(numeroPeticion) ;
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
			Long idPeticionAtis = ((Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey()).cod_pet_cd;
			LocalidadLocal  localidadLocal =(LocalidadLocal)peticionLocal.getFk_01_localidad();
			
			Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey((new Mensaje_estadoKey(new Integer(estadoEspera))));
			Mensaje_estado_baLocal mensaje_estado_baLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			mensaje_estado_baLocal.setPeticion(peticionLocal);
			
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
			Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			
			mensaje_estado_baLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensaje_estado_baLocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensaje_estado_baLocal.setFecha_envio(df.format(new Date()));
			mensaje_estado_baLocal.setMensaje_estado(mensajeEspera);
			mensaje_estado_baLocal.setCod_actividad_generadora(codActividad);
			
			Collection listaPsPet = peticionLocal.getProducto_servicio_peticion();
			
			for (Iterator iter = listaPsPet.iterator(); iter.hasNext() && !flag ;) {
				Producto_servicio_peticionLocal  producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioLocal producto_servicioLocal2 = (Producto_servicioLocal)producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey = (Producto_servicioKey)producto_servicioLocal2.getPrimaryKey();
				for (int i = 0; i < arrayPsTeraBox.length; i++) {
					if(arrayPsTeraBox[i].equals(producto_servicioKey.ps_id.toString())){
						Operacion_comercialLocal operacion_comercialLocal = producto_servicio_peticionLocal.getOperacion_comercial();
						operacionComercial=((Operacion_comercialKey)operacion_comercialLocal.getPrimaryKey()).opco_id;
						if(operacionComercial.intValue() != bajaMigracionPS){//altaMigracionPS){
							psTeraBoxId = producto_servicioKey.ps_id;
							flag = true;
						}
					}
				}
			}
			
			InformacionAdslDTO informacionAdslDTO = obtenerDatosAdsl(numeroPeticion);
						
			TR718E tr718e = new TR718E();
			tr718e.setId(idCorrelativoMensaje.toString());
			tr718e.setSource("ATIEMPO");
			tr718e.setDestination("ESB");
			tr718e.setVersion("1.0");
			tr718e.setInterfaz("RES_IN_EVOL_TERABOX");			
			
			if(informacionAdslDTO.getDireccElectronica() != null){
				email=informacionAdslDTO.getDireccElectronica();
			}
			
			if(informacionAdslDTO.getFatherEmail()!=null ){
				usuario=informacionAdslDTO.getFatherEmail();
			}
			
			if(peticionLocal.getNum_doc_cli_cd() != null ){
				tr718e.setPassword(peticionLocal.getNum_doc_cli_cd());
			}else{
				tr718e.setPassword("");
			}
			
			if(peticionLocal.getNom_ds() != null ){
				tr718e.setFirstname(peticionLocal.getNom_ds());
			}else{
				tr718e.setFirstname("");
			}

			tr718e.setAccountExpiration(accountExpiration);
			tr718e.setEmail(email);
			tr718e.setUsername(usuario);
			tr718e.setLastname(peticionLocal.getPri_ape_ds());
			tr718e.setProductService(psTeraBoxId.toString());
			tr718e.setComercialOperation(operacionComercial.toString());
			tr718e.setCountry(country);
			tr718e.setState("Other");
			
			EnviarCorreoTeraBoxMQ enviarCorreoTeraBoxMQ = new EnviarCorreoTeraBoxMQ();
			enviarCorreoTeraBoxMQ.enviarMensaje( tr718e );
			
		} catch (FinderException fe) {
			log.debug("RecursosBABean: enviarCorreoTeraBox: "+fe);
			fe.printStackTrace();
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} 
			
	}
//	Cesar Remigio (INDRA): se recibe la confirmación del envio del correo Terabox
	public void configuracionEnvioCorreoTeraBox(TR718S tr718s) throws ATiempoAppEx {
		log.debug("Entro a confirmacionEnvioTeraBox ");
		BackendExecution bExecution = null;
		
		try
		{		
			bExecution = BackendTraceUtil.initExecution(tr718s, log);
			bExecution.setIdMensajeOp(tr718s.getId());
			bExecution.startOperation();
			
			Mensaje_estadoLocal mensajeOk = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			
			Mensaje_estado_baKey mensaje_estado_baKey = new Mensaje_estado_baKey(Long.valueOf(tr718s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBaLocal = (Mensaje_estado_baLocal) mensajeEstadoBaLocalHome.findByPrimaryKey(mensaje_estado_baKey);
			mensajeEstadoBaLocal.setMensaje_estado(mensajeOk);
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			
			PeticionKey peticionKey = ( PeticionKey ) mensajeEstadoBaLocal.getPeticion().getPrimaryKey();
			ActividadEJBDTO actividadEJBDTO = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			if(!tr718s.getError().equals("0")){
				ErrorLegadoLocal errorLegado = getErrorLegado(tr718s.getError(),"TR718S");
				String plataforma = VpistbbaConfig.getVariable("IDPGI");
				String bandeja = "PGI";
				if(errorLegado != null){
					if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
						actividadEJBDTO.setObservacion("Error: "
				 				+ errorLegado.getCodigoError() + "Descripcion: "+tr718s.getErrorMessage());
						actividadEJB.terminar(actividadEJBDTO);
						return;
					}
					plataforma = errorLegado.getPlataforma(); 
					bandeja	= getNombreBandeja(plataforma); 
				}
				log.debug("Error al enviar el correo  terabox, se deriva a "+bandeja+": "+tr718s.getErrorMessage());
				insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long(701), actividadEJBDTO.getIdActividadFlujo());
				actividadEJBDTO.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				actividadEJBDTO.setObservacion("Error al recibir configuración terabox, se deriva a "+bandeja+": "+" Respuesta: "+tr718s.getErrorMessage()+" - "+tr718s.getError(), true);
			}else{
				actividadEJBDTO.setObservacion("Se recibe respuesta de envio correo terabox sin error: "+tr718s.getError()+" - "+tr718s.getErrorMessage());
				log.debug("Se recibe respuesta de envio correo terabox sin error: " + tr718s.getError() + " - "+tr718s.getErrorMessage());
			}
			actividadEJB.terminar(actividadEJBDTO);
			
		} catch (FinderException fe) {
			bExecution.setErrorOp(true);
			log.warn("RecursosBABean: confirmacionEnvioTeraBox: FinderException: ",fe);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, fe);
		} catch (TnProcesoExcepcion tnpe) {
			bExecution.setErrorOp(true);
			log.warn("RecursosBABean: confirmacionEnvioTeraBox:TnProcesoExcepcion",tnpe);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, tnpe);
		} catch (ATiempoAppEx e) {
			bExecution.setErrorOp(true);
			log.warn("RecursosBABean: confirmacionEnvioTeraBox:TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			log.warn("RecursosBABean: confirmacionEnvioTeraBox:TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.warn("RecursosBABean: confirmacionEnvioTeraBox:TnProcesoExcepcion",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}finally{
			bExecution.endAll();
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void enviarRefrecarDatos(Long idPeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
			// TODO Apéndice de método generado automáticamente
			log.debug("Entro a enviarRefrecarDatos ");
			String dato;
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(idPeticion);
			Recursos_baLocalHome recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
			Recursos_baLocal recursos_baLocal = recursos_baLocalHome.findbyPeti_numero(idPeticion);
			String puerto = recursos_baLocal.getPuerto_actual();
			dato = "1. Puerto: "+puerto+";";
			String POTs = recursos_baLocal.getPost_actual();
			dato = dato+"2. POTs: "+POTs+";";
			String adsl = recursos_baLocal.getAdsl_actual();
			dato = dato+"3. ADSL: "+adsl+";";
			String direccionIPdslam = recursos_baLocal.getDir_ip_dslam_actual();
			dato = dato+"4. Direccion IP DSLAM: "+direccionIPdslam+";";
			String direccionIPwan = recursos_baLocal.getDir_ip_wan_actual();
			dato = dato+"5. Direccion IP WAN: "+direccionIPwan+";";
			String direccionIPlan = recursos_baLocal.getDir_ip_lan_actual();
			dato = dato+"6. Direccion IP LAN: "+direccionIPlan+";";
			String mascaraLan = recursos_baLocal.getMasc_actual();
			dato = dato+"7. Mascara Lan: "+mascaraLan+";";
			String frame = recursos_baLocal.getFrame_actual();
			dato = dato+"8. FRAME: "+frame+";";
			String tarjeta = recursos_baLocal.getSlot_actual();
			dato = dato+"9. Tarjeta: "+tarjeta+";";
			String VPI_VCI_Cliente = recursos_baLocal.getVpivci_actual();
			dato = dato+"10. VPI/VCI Cliente: "+VPI_VCI_Cliente+";";
			String VPI_VCI_Red = recursos_baLocal.getVpivci_red_actual();
			dato = dato+"11. VPI/VCI Red: "+VPI_VCI_Red+";";
			String usuario = recursos_baLocal.getFather_email_nuevo();
			dato = dato+"12. Usuario: "+usuario+";";
			String modemSerial = recursos_baLocal.getSerial();
			dato = dato+"13. Serial Modem: "+modemSerial+";";
			String modemMarca = recursos_baLocal.getModem_marca();
			dato = dato+"14. Marca Modem: "+modemMarca+";";
			String modemModelo = recursos_baLocal.getModelo();
			dato = dato+"15. Modelo Modem: "+modemModelo+";";
			
			TR719S tr719s = (TR719S)XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
			TR719E tr719e =  new TR719E();
			tr719e.setId(tr719s.getId());
			tr719e.setDestination(sistemaAgendaSC);
			tr719e.setSource(sistemaAtiempo);
			tr719e.setInterfaz("ACT_RES_REFRESHBA");
			tr719e.setVersion("1.0");
			tr719e.setId_sistema_origen("ATIEMPO");
			tr719e.setId_actuacion(tr719s.getId_actuacion());
			tr719e.setNotif_refresh(dato);
			if(recursos_baLocal.getCod_error() != null)
				tr719e.setCodigo_error(recursos_baLocal.getCod_error().toString());
			tr719e.setDescripcion(recursos_baLocal.getDesc_error());
			
			tmp_agenda_scLocal.remove();
			
			EnviarRefrecarDatosMQ enviarRefrecarDatosMQ = new EnviarRefrecarDatosMQ();
			enviarRefrecarDatosMQ.enviarMensaje(tr719e);
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error instanciando el bean en el Recursos_baLocalHome:" + e);
			e.printStackTrace();
		}
		catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al buscar la petición en Recursos_baLocal:" + e);
			e.printStackTrace();
		}
		 catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
		 	log.debug("Error enviando la TR de respuesta a Agenda: " + e);
			e.printStackTrace();
		}catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error instanciando el bean en el Recursos_baLocalHome:" + e);
			e.printStackTrace();
		} catch (RemoveException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error removiendo el bean en el Recursos_baLocalHome:" + e);
			e.printStackTrace();
		}
	}

	public void ejecutarVelocidadAdicionalTMP(ActividadEJBDTO act)throws ATiempoAppEx{
		
		log.debug("Entro a ejecutarVelocidadAdicionalTMP");
//		PS de velocidad adicional
		
		try {
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			PeticionLocalHome petiHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal petiLocal = petiHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			String psSVABA[] = propertiesLocal.getValor().split(",");
			boolean esSVABA = false;
			boolean esCierreInmediato = false;
			//se valida si la peticiòn trae SVA de velocidad Adicional
			
			Collection pspPeticion = petiLocal.getProducto_servicio_peticion();
			for(Iterator iter = pspPeticion.iterator();iter.hasNext();){
//				Se valida si el PS pertenece a un SVA de aumento de velocidad
				Producto_servicio_peticionLocal psTemp= (Producto_servicio_peticionLocal) iter.next();
				
				for(int i = 0; i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(psTemp.getPsId()) ){
						esSVABA = true;
						break;
					}
				}
			}
			log.debug("Trae ps de velocidad adicional: "+esSVABA);
			
			if(esSVABA){
				
				//Se obtiene las peticiones abiertas del id_pc de la peticòn consultad
				PeticionLocalHome petiHome2 = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Collection openPeti = petiHome2.findByPetOpenAndIdPC(new Integer(1),petiLocal.getNum_ide_nu_stb() );
				//se recorre el arreglo para validar cuales de las peticiones abiertas se encuentran en baja de lo contrario poner las en estado de espera
				
				log.debug("Trae: "+openPeti.size()+ " peticiones abiertas");
				
				if(openPeti != null && openPeti.size()>1){
					petiLocal.setEspe_id(new Integer(ComunInterfaces.estadoEspera));
				}
					
				for(Iterator iter = openPeti.iterator();iter.hasNext();){
					PeticionLocal petiLocaltemp = (PeticionLocal)iter.next();
					if(petiLocaltemp.getTica_id().equals("B") && ((PeticionKey)petiLocaltemp.getPrimaryKey()).peti_numero.intValue() != act.getNumeroPeticion().intValue()){
						Collection pspCollection = petiLocaltemp.getProducto_servicio_peticion();
						for(Iterator pspIter= pspCollection.iterator();pspIter.hasNext();){
							Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) pspIter.next();
							Familia_producto_servicioKey familiakey =  pspLocal.getFamiliaKey();
//							REQ BA NAKED adicion familia PC naked
							if(familiakey.faps_id.intValue() == ComunInterfaces.familiaPcBA || familiakey.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked){
								esCierreInmediato = true;
								break;
							}
													
						}
					}
				}
				
			}
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			
			log.debug("Es cierre inmediato: "+esCierreInmediato);
			
			if(esCierreInmediato){
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(ComunInterfaces.ACT_TMP_VELOCIDAD_ADICIONAL);
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
				act.setObservacion("Se cierra actividad por tener una peticion de baja para PC en vuelo");
				act.setRealizarTerminoInmediato(true);
				actividadEJB.terminar(act);
					
			}else{
				log.debug("Peticion en estado = "+ petiLocal.getEspe_id() );
				if(petiLocal.getEspe_id().intValue() != ComunInterfaces.estadoEspera){
					log.debug("Proceso ok, se continua con la siguiente actividad");
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(ComunInterfaces.ACT_TMP_VELOCIDAD_ADICIONAL);
					act.setObservacion("Proceso ok, se continua con la siguiente actividad");
					act.setRealizarTerminoInmediato(true);
					actividadEJB.terminar(act);
				}
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al procesar peticiòn en ejecutarVelocidadAdicionalTMP: "+ e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al procesar peticiòn en ejecutarVelocidadAdicionalTMP: "+ e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al procesar peticiòn en ejecutarVelocidadAdicionalTMP: "+ e);
		}
		
	}
	
	//	REQ TOA FASE I @DCARDENA 15/05/2015
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviaInstalarTOA(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void enviaInstalarTOA(ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionInstalarTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR717S)
	 */
	public void recepcionInstalarTOA(TR717S tr717s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}
	//FIN REQ TOA FASE I
	private boolean esSoloVelocidadAdicional(ActividadEJBDTO act){
		log.debug("Entro a ejecutarVelocidadAdicionalTMP");
		
		try {
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			PeticionLocalHome petiHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal petiLocal = petiHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			String psSVABA[] = propertiesLocal.getValor().split(",");
			boolean esSVABA = false;
			boolean tienePCBA = false;
			boolean esPsUnico = false;
			//se valida si la peticiòn trae SVA de velocidad Adicional
			
			Collection pspPeticion = petiLocal.getProducto_servicio_peticion();
			for(Iterator iter = pspPeticion.iterator();iter.hasNext();){
//			Se valida si el PS pertenece a un SVA de aumento de velocidad
				Producto_servicio_peticionLocal psTemp= (Producto_servicio_peticionLocal) iter.next();
				Familia_producto_servicioKey familiaKey = psTemp.getFamiliaKey();
				
				if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaBandaAncha)
					tienePCBA = true;
				
				for(int i = 0; i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(psTemp.getPsId()) ){
						esSVABA = true;
					}
				}
			}
			return esSVABA && tienePCBA;
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("se retorna un error de NumberFormatException"+e);
			return false;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("se retorna un error de NamingException"+e);
			return false;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("se retorna un error de FinderException"+e);
			return false;
		}
		
	}
}