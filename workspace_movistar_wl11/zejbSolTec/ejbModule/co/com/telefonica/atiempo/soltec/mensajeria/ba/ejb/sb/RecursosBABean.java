
package co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb;


import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.telefonica.acs.NBIWebServicesInvoker;
import co.com.telefonica.acs.NBIWebServicesInvokerAsync;
import co.com.telefonica.acs.ParametrosMotiveDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_homologacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal;
import co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.MunicipioKey;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
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
import co.com.telefonica.atiempo.interfaces.atiempo.TR708E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711SMaterials;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719S;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaKey;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.RecursosBADTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierre_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_modemLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoLocal;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.ActivarRecursosSCSTMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.ActualizacionInventarioBAMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.CreaActuacionSCSTMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudConfiguracionBAMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudConfiguracionBASigresMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudCuentaCorreoBAMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudModemMQ;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.servicios.ActivarModemSCSTMQ;
import co.com.telefonica.atiempo.soltec.servicios.ConfiguracionModemAutoInstalacion;
import co.com.telefonica.atiempo.soltec.servicios.EnviarRefrecarDatosMQ;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSKey;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

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
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.soltec.grabacion.GrabadorSolTecLocal;
import com.telefonica_chile.soltec.grabacion.GrabadorSolTecLocalHome;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 19, 2009
/**
 * Bean implementation class for Enterprise Bean: RecursosBA
 */
public class RecursosBABean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements RecursosBAInterfaces  {
	
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009i
	//          private Mensaje_estado_stLocalHome msgEstadoLineaLocalHome ;
	//          private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome;
	private Peticion_stLocalHome peticionLocalHome ;
	private Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome;
	private Tipo_ModemLocalHome tipo_ModemLocalHome;	
	//          private Mensaje_estadoLocalHome mensajeEstadoLocalHome ;
	//
	//          private Recursos_baLocalHome recursos_baLocalHome;
	//          private Mensaje_estadoLocalHome mensaje_estadoLocalHome;
	//          private Dslam_apsc_lineaLocalHome dslam_apscLocalHome;
	//          private Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome;
	//          private ConectorLocalHome conectorLocalHome;
	//          private LocalidadLocalHome localidadLocalHome;
	//          private DepartamentoLocalHome departamentoLocalHome;
	//          private ModemLocalHome modemLocalHome;
	//          private Tmp_modemLocalHome tmp_modemHome;
	private DBManager dbSeq ;
	private SimpleDateFormat df ;
	
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
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
		
		// TODO: revisar este formato
		df = new SimpleDateFormat ("dd/MM/yyyy") ;
		
		buscaHome ();
	}
	
	/*
	 * Metodo Generador de Home
	 */
	private void buscaHome (){
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		//                      try {
		//          
		//                      //          Creacion de los Home
		//                      msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
		//                      recursos_linea_basicaLocalHome =       (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
		//                      mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
		//                      peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
		//
		//                      dslam_apscLocalHome = (Dslam_apsc_lineaLocalHome) HomeFactory.getHome(Dslam_apsc_lineaLocalHome.JNDI_NAME);
		//                      servicio_basico_supl_apsc_lineaLocalHome = (Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
		//                      conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
		//                      
		//                      recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
		//                      
		//                      localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
		//                      departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
		//
		//                      modemLocalHome=(ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
		//                      tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
		//                      } catch (NamingException e) {
		//                                 log.error(" Creacion de Local Home Nulls");
		//                      }
	}
	
	/*
	 * Metodo validador Home
	 */
	//          private void validaHome ()
	//                      throws ATiempoAppEx
	//          {
	//                      // Validacion de los Home
	//                      if (
	//                                 msgEstadoLineaLocalHome == null || recursos_linea_basicaLocalHome == null
	//                                 || mensajeEstadoLocalHome == null || peticionLocalHome == null 
	//                                 || localidadLocalHome == null 
	//                                 || departamentoLocalHome == null 
	//                                 || conectorLocalHome == null 
	//                                 || modemLocalHome == null
	//                                 || recursos_baLocalHome == null
	//                                 )
	//                                 throw new ATiempoAppEx (ATiempoAppEx.NAMING);
	//          }
	
	
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx{
		
		try{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mensajeEsperaLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			
			boolean esRefrescar=false;
			if(id_actividad.equals(""))
			{
				esRefrescar=true;
				id_actividad=STConfig.getVariable("COD_ACTIVIDAD_REC_BA_AUTOMATICA");
			}
			
			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
			Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
			Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();
			
			//Proceso para la validacion de los tipos de ps, que estan en el mensaje
			StringBuffer append = new StringBuffer();
			boolean bip = true;
			Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
			Producto_servicio_stKey productoServicostKey = null;
			
			for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){
				
				producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
				productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();
				
				if(bip){
					append.append(productoServicostKey.ps_id);
					bip = false;
				}else{
					append.append(" ,"+ productoServicostKey.ps_id .toString());
				}
				
			}
			
			// TODO:  IMPLEMENTAR --> Condicion que valida los tipos de PS
			
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR014E tr014e = new TR014E();
			tr014e.setId(String.valueOf(IdCorrelativo));
			
			String phoneNumber = peticionLocal.getNum_ide_nu();
			if (phoneNumber!=null && phoneNumber.trim()!= ""){
				if (phoneNumber.length()>8){ 
					phoneNumber=phoneNumber.substring(0,8);
				}
			} else{
				phoneNumber="0";
			}           
			
			tr014e.setPhoneNumber(Integer.parseInt(phoneNumber));
			tr014e.setAtisRequestNumber(((Peticion_stKey)peticionLocal.getPrimaryKey()).cod_ave_cd.longValue());
			
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			mensajeEstadoLocal.setPeticion_st(peticionLocal);
			
			Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
			mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
			
			ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorCuatro))).getPrimaryKey();
			mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
			
			if(esRefrescar)
				mensajeEstadoLocal.setAccion("R");
			
			mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
			Mensaje_estadoKey mensaje_estadoKey =  (Mensaje_estadoKey) mensajeEsperaLocal.getPrimaryKey();
			mensajeEstadoLocal.setCod_estado(new Integer(mensaje_estadoKey.cod_estado.intValue()));
			mensajeEstadoLocal.setCod_actividad_generadora(id_actividad);
			//TODO: Revisar luego estos campos y su mapeo.
			if(peticionLocal.getCod_are_ges_cd()!=null)
				mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
			if(peticionLocal.getTel_cot_ds()!=null)
				mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
			
			SolicitudConfiguracionBAMQ  configuracionActualBAMQ = new SolicitudConfiguracionBAMQ();
			configuracionActualBAMQ.enviarMensajeReplyToQ(tr014e);
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} 
		catch(Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		
	}
	
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx{
		try{                   
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			
			Mensaje_estado_stKey key = new Mensaje_estado_stKey(new Long(tr014s.getId()));
			
			Mensaje_estado_stLocal mensajeEstadoLineaLocal = null;
			
			try {
				mensajeEstadoLineaLocal = msgEstadoLineaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoLineaLocal = null ;
			}
			
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			
			if (mensajeEstadoLineaLocal == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr014s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr014s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			int cod_estado=mensajeEstadoLineaLocal.getCod_estado().intValue();
			//Validacion del estado de la respuesta 
			if (cod_estado == estadoOk || cod_estado == estadoEsperaManual) {
				log.warn(
						"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr014s));
				return;
			}
			
			Peticion_stLocal petLocal= mensajeEstadoLineaLocal.getPeticion_st();
			Peticion_stKey pk= (Peticion_stKey) petLocal.getPrimaryKey();
			
			boolean errorCode = false;
			String errorCode2 = tr014s.getErrorCode();
			if (errorCode2!= null && !"".equals(errorCode2) && !"0".equals(errorCode2)){
				errorCode=true;
			}
			
			if (tr014s.isError() != true && !errorCode){
				Recursos_baLocal recursos_baLocal = null;
				Mensaje_estado_stLocal mensajeEstadoLineaLocalFor = null;
				boolean insert = false;
				
				Collection recursosLineaBaBasica = petLocal.getRecursos_ba();
				log.debug("Existen Recursos " + recursosLineaBaBasica.size());
				if ( recursosLineaBaBasica.size() == 0){
					insert = true;
				}else{
					log.debug("Actualiza Recursos BA");
					insert = false;
					recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
				}
				
				if (insert){
					log.debug("Inserta Recursos BA");
					Long idConDos =          new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_baLocalHome recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
					recursos_baLocal = recursos_baLocalHome.create(idConDos,petLocal);
				}
				Mensaje_estado_stKey mensaje_estado_stKey = (Mensaje_estado_stKey) mensajeEstadoLineaLocal.getPrimaryKey();       
				//                                             recursos_baLocal.setMensaje_estado_st(mensajeEstadoLineaLocal);
				recursos_baLocal.setPeti_numero(pk.cod_ave_cd);
				//                                             recursos_baLocal.setCod_error(new Integer(String.valueOf(tr014s.getErrorCode())));
				//                                             recursos_baLocal.setDesc_error(tr014s.getErrorMessage());
				//                                             recursos_baLocal.setCod_error(mensajeEstadoLineaLocal.getCod_estado());
				
				recursos_baLocal.setAdsl_actual(tr014s.getAdsl());
				recursos_baLocal.setDir_ip_dslam_actual(tr014s.getDslamIp());
				recursos_baLocal.setDir_ip_wan_actual(tr014s.getWanIp());
				recursos_baLocal.setFrame_actual(tr014s.getFrame());
				recursos_baLocal.setMasc_lan_actual(tr014s.getMask());
				recursos_baLocal.setPost_actual(tr014s.getPots());
				recursos_baLocal.setPuerto_actual(tr014s.getPort());
				recursos_baLocal.setSlot_actual(tr014s.getSlot());
				recursos_baLocal.setVpivci_actual(tr014s.getVpiVciClient());
				recursos_baLocal.setVpivci_red_actual(tr014s.getVpiVciNetwork());
				recursos_baLocal.setFather_email_actual(tr014s.getFatherEmail());
				mensajeEstadoLineaLocal.setTelefono(String.valueOf(tr014s.getPhoneNumber()));
				//                                                         mensajeEstadoLineaLocal.getPeticion_st().setNum_ide_nu(""+tr014s.getPhoneNumber());
				
				
				/**
				 * DAVID: Dic 22 2010, se implementa lo siguiente tal com en VPI. Se almacenan los módems que llegan en esta tr en la tabla Modem, para
				 * extraerlos y ponerlos en la tr701e de agenda sc.
				 */
				ArrayList listaModems=new ArrayList();
				ModemSTDTO modemSTDTO=new ModemSTDTO();
				
				modemSTDTO.setSerial(tr014s.getModemSerial());
				modemSTDTO.setAccion(new Integer(ComunInterfaces.accionModemLiberar));                                        
				
				modemSTDTO.setMarca(tr014s.getModemBrand());
				modemSTDTO.setModelo(tr014s.getModemModel());
				modemSTDTO.setTipo(new Long(tr014s.getModemType())); 
				/*RQ.8595 - mfmendez*/
				modemSTDTO.setPostingDateSAP(tr014s.getModemPostingDateSAP());
				modemSTDTO.setMoveTypeSAP(tr014s.getModemMoveTypeSAP());
				modemSTDTO.setMaterialCodeSAP(tr014s.getModemMaterialCodeSAP());
				modemSTDTO.setMaterialSAP(tr014s.getModemMaterialSAP());
				modemSTDTO.setPlantSAP(tr014s.getModemPlantSAP());
				modemSTDTO.setStorageSAP(tr014s.getModemStorageSAP());
				modemSTDTO.setBatchCodeSAP(tr014s.getModemBatchCodeSAP());
				modemSTDTO.setMeasurementUnitSAP(tr014s.getModemMeasurementUnitSAP());
				modemSTDTO.setCostCenterSAP(tr014s.getModemCostCenterSAP());
				modemSTDTO.setFuncAreaLongSAP(tr014s.getModemFuncAreaLongSAP());
				modemSTDTO.setPepElementSAP(tr014s.getModemPepElementSAP());
				modemSTDTO.setFlagMatSAP(tr014s.getModemFlagMatSAP());
				/*FIN - RQ.8595 - mfmendez*/
				
				
				listaModems.add(modemSTDTO);                                             
				
				grabaModemsBaST(new Long(tr014s.getAtisRequestNumber()),new Long(mensajeEstadoLineaLocal.getTelefono()),listaModems);
				
				//Fin: DAVID dic 22 2010
			}
			mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			
			if (tr014s.isError() || errorCode) {
				mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
				if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
					return;
				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd, mensajeEstadoLineaLocal.getCod_actividad_generadora());
				String errorCodeMsg = "";
				if (tr014s.getErrorCodeMessage()!=null){
					errorCodeMsg=tr014s.getErrorCodeMessage() + ".";
				}
				if (tr014s.getErrorMessage()!=null){
					errorCodeMsg=tr014s.getErrorMessage();
				}
				//recursos_baLocal.setDesc_error(errorCodeMsg);
				//actDto.setObservacion("Error en la Obtención de Recursos BA. " + errorCodeMsg);
				
				//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
				//actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
				//actividadEJB.terminar(actDto);
				//return;
				//REQ TOA FASE III Dcardena
				//METODO QUE ENVIA A PGI LA AVERIA
				RecursosDelegate rd = new RecursosDelegate();
				rd.hayPGIAveria(tr014s.getErrorCode(),tr014s.getErrorMessage(),"TR014S",mensajeEstadoLineaLocal.getPeti_numero().toString(),mensajeEstadoLineaLocal.getCod_actividad_generadora()  ,mensajeEstadoLineaLocal);
				return;
				//FIN REQ TOA FASE III 
			}
			
			mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
			if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
				return;
			
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoLineaLocal.getCod_actividad_generadora());
			actividadEJB.terminar(actDto);
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}catch(Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	
	/* (no Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviarActualizacionInventarioBA(java.lang.String, java.lang.String)
	 */
	public void enviarActualizacionInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad) throws ATiempoAppEx {
		try{
			boolean esInhibirActividad = false;
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome=(ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mensajeEsperaLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			
			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
			Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
			Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();
			
			//Proceso para la validacion de los tipos de ps, que estan en el mensaje
			StringBuffer append = new StringBuffer();
			boolean bip = true;
			Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
			Producto_servicio_stKey productoServicostKey = null;
			
			for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){
				
				producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
				productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();
				
				if(bip){
					append.append(productoServicostKey.ps_id);
					bip = false;
				}else{
					append.append(" ,"+ productoServicostKey.ps_id .toString());
				}
				
			}
			
			// TODO:  IMPLEMENTAR --> Condicion que valida los tipos de PS
			
			Collection modems = modemLocalHome.findPeticion(peticion_stkey.cod_ave_cd);
			
			if(modems != null && modems.size() > 0){
				
				for(Iterator iter = modems.iterator();iter.hasNext();){
					
					ModemLocal modemLocal = (ModemLocal) iter.next();
					
					//Veo si para esta peticion ya he enviado un mensaje con el serial del modem.
					//Si el estado es OK, significa que este es un reintento y no tengo que enviarlo de nuevo.
					//solo reintento a quellos cuyo estado es Error.
					if(!this.esMensajeParaEnviar(peticion_stkey.cod_ave_cd,((ModemKey)modemLocal.getPrimaryKey()).serial,id_actividad)){
						continue;
					}
					Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
					TR007E tr007e = new TR007E();
					tr007e.setId(String.valueOf(IdCorrelativo));
					
					
					String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && phoneNumber.trim()!= ""){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					} else{
						phoneNumber="0";
					}           
					if(modemLocal.getTelefono().intValue()!=new Integer(phoneNumber).intValue())
						tr007e.setPhoneNumber(new Integer(phoneNumber).intValue());
					else
						tr007e.setPhoneNumber(modemLocal.getTelefono().intValue());                                                              
					//                                                         tr007e.setPhoneNumber(Integer.parseInt(phoneNumber));
					tr007e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					if(modemLocal.getAccion().shortValue()==0)
						continue;
					//                                                         if( modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemOcupar || 
					//                                                                     modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemCambioNoRec ||
					//                                                                     modemLocal.getAccion().shortValue()==ComunInterfaces.accionModemCambioAveriado )
					tr007e.setSerialNumber(modemKey.serial);
					
					if(modemKey.serial != null && 
							(modemKey.serial.equals(ComunInterfaces.VALOR_NO_SERIAL_ESPACIO)
							|| modemKey.serial.equals(ComunInterfaces.VALOR_NO_SERIAL_HC))){
						
						esInhibirActividad = true;
					}
					tr007e.setModel(modemLocal.getModelo());
					tr007e.setType(modemLocal.getTipo().toString());
					tr007e.setBrand(modemLocal.getModem_marca());					
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
					
					/*Se obtiene la lista de acciones que se homologan al codigo 6 para hc*/
					Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
					Properties_BDLocal propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey("ACCIONES_MODEM");
					String accionesModem = propertiesBDLocal.getValor();
					List listaAccionesModem = null;
			        
					if(accionesModem != null && !accionesModem.equals("")){
						listaAccionesModem = Arrays.asList(accionesModem.split(","));
					}
					if(listaAccionesModem != null && !listaAccionesModem.isEmpty() 
							&& listaAccionesModem.contains(modemLocal.getAccion().toString())){
						tr007e.setModemAction(accionModemWiFi);
					}else{
						tr007e.setModemAction(modemLocal.getAccion().intValue());	
					}
					
		
					if(!esInhibirActividad){
						// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
						Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
						mensajeEstadoLocal.setPeticion_st(peticionLocal);
						Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
						mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
						ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorTres))).getPrimaryKey();
						mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
						mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
						Mensaje_estadoKey mensaje_estadoKey =  (Mensaje_estadoKey) mensajeEsperaLocal.getPrimaryKey();
						mensajeEstadoLocal.setCod_estado(new Integer(mensaje_estadoKey.cod_estado.intValue()));
						mensajeEstadoLocal.setCod_actividad_generadora(id_actividad);
						mensajeEstadoLocal.setSerial(tr007e.getSerialNumber());                                                           
						
						int areaPhone= 0;
						int numeroPhone= 0;
						if (phoneNumber.length()>1){
							areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
							numeroPhone=Integer.parseInt(phoneNumber.substring(1));
						}
						mensajeEstadoLocal.setArea(new Integer(areaPhone));
						mensajeEstadoLocal.setTelefono(String.valueOf(numeroPhone));
						
						ActualizacionInventarioBAMQ   actualizacionInventarioBAMQ = new ActualizacionInventarioBAMQ();
						actualizacionInventarioBAMQ.enviarMensajeReplyToQ(tr007e);    
					}else{
						/*Se inhibe la actividad porque el modem tiene como serial "NO SERIAL" o "NO_SERIAL"*/
						log.debug("Se inhibe la actividad porque el modem tiene como serial NO SERIAL o NO_SERIAL");
		 				act.setRealizarTerminoInmediato(true);
		 				act.setObservacion("Se inhibe la actividad de Actualizar Inventario BA porque el serial del modem es NO SERIAL o NO_SERIAL");
		 				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"N");
					}
				}
				
				if(!esInhibirActividad){
					//Cambio el estado de los mensajes anteriores que estaban con error.
					Collection mensajesErr = msgEstadoLineaLocalHome.findEstadoPeticion(new Long(peticion), new Integer(estadoError),id_actividad);
					if (mensajesErr!=null && mensajesErr.size()>0){
						for(Iterator iterMsgErr=mensajesErr.iterator(); iterMsgErr.hasNext();){
							Mensaje_estado_stLocal msgLocal = (Mensaje_estado_stLocal) iterMsgErr.next();
							msgLocal.setCod_estado(new Integer(estadoReintentado));//Marco que sera reintentado
							break; // en la primera posicion esta el ultimo mensaje enviado para ese modem
						}                                                          
					}
				}
			}else{
				log.debug("Despues de la Validacion, No se encontron modems" + peticion);
			}
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException;",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			
		} catch (CreateException e) {
			log.error("CreateException;",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			
		} catch (FinderException e) {
			log.error("FinderException;",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		} 
		
	}
	
	private boolean esMensajeParaEnviar(Long idPeticion, String serial, String codActividad) throws ATiempoAppEx{
		boolean esOk=false;
		try{
			Mensaje_estado_stLocalHome msgHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Collection mensajes = msgHome.findByPeticionSerial(idPeticion,serial,codActividad);
			if (mensajes == null || mensajes.size()<1){
				esOk = true;// si nunca ha sido enviado, entonces si se puede enviar 
			}else{
				for(Iterator iter=mensajes.iterator(); iter.hasNext();){
					Mensaje_estado_stLocal msgLocal = (Mensaje_estado_stLocal) iter.next();
					if (msgLocal.getCod_estado().intValue()==estadoError){
						msgLocal.setCod_estado(new Integer(estadoReintentado));//Marco que sera reintentado
						esOk=true; // se puede reenviar el mensaje pk fue error.
					}
					break; // en la primera posicion esta el ultimo mensaje enviado para ese modem
				}
			}
		}catch(Exception e){
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
		return esOk;
	}
	
	private boolean hayMsgError(Long idPeticion, String codActividad) throws ATiempoAppEx{
		boolean hayHorror = false;
		try{
			Mensaje_estado_stLocalHome msgHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Collection mensajes = msgHome.findEstadoPeticion(idPeticion, new Integer(estadoError),codActividad);
			if (mensajes == null || mensajes.size()<1){
				hayHorror = false;
			}else{
				hayHorror = true;           
			}
		}catch(Exception e){
			log.error("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);                                
		}
		return hayHorror;
	}
	
	/* (no Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#recepcionActualizacionInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR007S)
	 */
	public void recepcionActualizacionInventarioBA(TR007S tr007s) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try{       
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			
			Mensaje_estado_stKey key = new Mensaje_estado_stKey(Long.valueOf(tr007s.getId()));
			
			Mensaje_estado_stLocal mensajeEstadoLineaLocal ;
			
			try {
				mensajeEstadoLineaLocal = (Mensaje_estado_stLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
			}
			catch (FinderException fex)
			{
				mensajeEstadoLineaLocal = null ;
			}
			
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			
			if (mensajeEstadoLineaLocal == null) {
				log.warn(
						"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr007s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr007s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			//Validacion del estado de la respuesta 
			if (mensajeEstadoLineaLocal.getCod_estado().intValue() == estadoOk ) {
				log.warn(
						"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr007s));
				return;
			}
			
			if(!tr007s.isError() && tr007s.getErrorCode() == 0){
				
				if(this.getEstadoMultipleMensajes(mensajeEstadoLineaLocal , new Integer(estadoEspera))){
					log.debug("Mensaje Ultimo Correcto : Se Procesara el Mensaje");
					mensajeEstadoLineaLocal.setCod_estado(new Integer(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado.intValue()));
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					if(!this.hayMsgError(mensajeEstadoLineaLocal.getPeti_numero(),mensajeEstadoLineaLocal.getCod_actividad_generadora())){
						actDto.setObservacion("Actualizacion Inventario OK");                            
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"N");                                                                   
					}else{
						actDto.setObservacion("Al menos un mensaje de actualizacion tiene error:"+tr007s.getErrorCode()+"."+tr007s.getErrorMessageInv()+"."+tr007s.getErrorMessage()+".Debe Reintentar");
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"S");                                                                                                                                         
					}
					actividadEJB.terminar(actDto);
				}else{
					mensajeEstadoLineaLocal.setCod_estado(new Integer(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado.intValue()));
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Mensaje Correcto : Faltan Mensajes del Grupo Enviado");
					
				}
			}else{
				
				//TODO al fallar se debe reenviar
				if(this.getEstadoMultipleMensajes(mensajeEstadoLineaLocal , new Integer(estadoEspera))){
					log.debug("Mensaje Ultimo Incorrecto: Se Procesara el Mensaje");
					mensajeEstadoLineaLocal.setCod_estado(new Integer(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado.intValue()));
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setObservacion("Al menos un mensaje de actualizacion tiene error:"+tr007s.getErrorCode()+"."+tr007s.getErrorMessageInv()+"."+tr007s.getErrorMessage()+".Debe Reintentar");
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"S");
					actividadEJB.terminar(actDto);
				}else{
					mensajeEstadoLineaLocal.setCod_estado(new Integer(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado.intValue()));
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Mensaje Incorrecto : Faltan Mensajes del Grupo Enviado");
				}
			}
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (TnProcesoExcepcion e) {
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
	}
	
	public boolean getEstadoMultipleMensajes(Mensaje_estado_stLocal mensajeEstadoLineaLocal, Integer estado){
		
		Mensaje_estado_stLocal mensaje_estado_stLocal2 = null;  
		Mensaje_estado_stKey mensaje_estado_stKey = (Mensaje_estado_stKey) mensajeEstadoLineaLocal.getPrimaryKey();
		
		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			
			//Arreglo de registros de peticion pendientes
			Collection estadoMensajes = msgEstadoLineaLocalHome.findEstadoPeticion(mensajeEstadoLineaLocal.getPeti_numero(), estado, mensajeEstadoLineaLocal.getCod_actividad_generadora());
			
			//Si queda el 1, siempre sera el ultimo
			if(estadoMensajes.size() == 1){
				
				for (Iterator iter= estadoMensajes.iterator();iter.hasNext();){
					
					mensaje_estado_stLocal2 = (Mensaje_estado_stLocal)iter.next();
					Mensaje_estado_stKey mensaje_estado_stKey2 = (Mensaje_estado_stKey) mensaje_estado_stLocal2.getPrimaryKey();
					//Comparacion de corralativos de mensajes
					if(mensaje_estado_stKey2.correlativo.longValue() == mensaje_estado_stKey.correlativo.longValue()){
						
						return true;
					}
					
				}
				
				return false;
				
			}else{
				
				return false;
			}
		}
		catch (FinderException fex) {
			return false;
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
			return false;
		}
		
		
	}
	
	public ArrayList getModemSt(Long peticion) throws ATiempoAppEx{
		
		ArrayList modems = new ArrayList();
		
		try{
			
			log.debug("Numero de Peticion de Busqueda de Modems "+ peticion);
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
			Peticion_stLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new Peticion_stKey(peticion));
			
			log.debug("Cantidad de Modem "+ peticionLocal.getModem().size());
			
			for(Iterator iter = peticionLocal.getModem().iterator(); iter.hasNext();){
				
				ModemLocal modemLocal = (ModemLocal) iter.next();
				
				ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
				ModemSTDTO modemSTDTO = new ModemSTDTO();
				
				modemSTDTO.setNum_peticion(modemKey.peticion_st_cod_ave_cd);
				modemSTDTO.setSerial(modemKey.serial);
				modemSTDTO.setTelefono(modemLocal.getTelefono());
				modemSTDTO.setAccion(new Integer(modemLocal.getAccion().intValue()));
				modemSTDTO.setModelo(modemLocal.getModelo());
				// CR 00026148
				modemSTDTO.setMarca(modemLocal.getModem_marca());
				modemSTDTO.setCod_material(modemLocal.getCod_material());				
				/*RQ.8595 - mfmendez*/
				if(modemLocal.getFec_cont_sap() != null)
					modemSTDTO.setPostingDateSAP(modemLocal.getFec_cont_sap());
				else
					modemSTDTO.setPostingDateSAP("");
				
				if(modemLocal.getClase_mov_sap() != null)
					modemSTDTO.setMoveTypeSAP(modemLocal.getClase_mov_sap());
				else
					modemSTDTO.setMoveTypeSAP("");
				
				modemSTDTO.setMaterialCodeSAP(Integer.toString(modemLocal.getPos_doc_sap()));
				
				if(modemLocal.getNum_material_sap() != null)
					modemSTDTO.setMaterialSAP(modemLocal.getNum_material_sap());
				else
					modemSTDTO.setMaterialSAP("");
					
				if(modemLocal.getCentro_sap() != null)
					modemSTDTO.setPlantSAP(modemLocal.getCentro_sap());
				else
					modemSTDTO.setPlantSAP("");
				
				if(modemLocal.getAlmacen_sap() != null)
					modemSTDTO.setStorageSAP(modemLocal.getAlmacen_sap());
				else
					modemSTDTO.setStorageSAP("");
				
				if(modemLocal.getCod_lote_sap() != null)
					modemSTDTO.setBatchCodeSAP(modemLocal.getCod_lote_sap());
				else
					modemSTDTO.setBatchCodeSAP("");
				
				if(modemLocal.getUnd_medida_sap() != null)
					modemSTDTO.setMeasurementUnitSAP(modemLocal.getUnd_medida_sap());
				else
					modemSTDTO.setMeasurementUnitSAP("");
				
				if(modemLocal.getCentr_cost_sap() != null)
					modemSTDTO.setCostCenterSAP(modemLocal.getCentr_cost_sap());
				else
					modemSTDTO.setCostCenterSAP("");
				
				if(modemLocal.getArea_func_sap() != null)
					modemSTDTO.setFuncAreaLongSAP(modemLocal.getArea_func_sap());
				else
					modemSTDTO.setFuncAreaLongSAP("");
				
				if(modemLocal.getElement_pep_sap() != null)
					modemSTDTO.setPepElementSAP(modemLocal.getElement_pep_sap());
				else
					modemSTDTO.setPepElementSAP("");
				
				if(modemLocal.getFlag_mat_sap() != null)
					modemSTDTO.setFlagMatSAP(modemLocal.getFlag_mat_sap());
				else
					modemSTDTO.setFlagMatSAP("");
				/*FIN - RQ.8595 - mfmendez*/
				
				modems.add(modemSTDTO);
				log.debug("Sacando Modem");
			}					
		}catch (Exception e) {
			log.error("Exception", e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		}
		return modems;
	}
	
	public String getTipoModemSt(Long peticion) throws ATiempoAppEx{
		
		try{
			PeticionesInterfaces peticionesInterfaces=new PeticionesDelegate();
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new Peticion_stKey(peticion));
			Long psAlta = peticionLocal.getCod_pro_ser_cd();
			
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			Tipo_ModemLocalHome tipo_ModemLocalHome = tipo_ModemLocalHome = (Tipo_ModemLocalHome) HomeFactory.getHome(Tipo_ModemLocalHome.JNDI_NAME);
			
			int tipoModem = 1;
			Tipo_ModemLocal tipo_ModemLocal = null;
			try {
				
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				if (ps_Tipo_ModemLocal!= null){
					tipoModem = ps_Tipo_ModemLocal.getId_tipo_modem().intValue();
				}                                          
			}catch (FinderException e){
			}
			
			tipo_ModemLocal = tipo_ModemLocalHome.findByPrimaryKey(new Integer(tipoModem));
			
			
			return tipo_ModemLocal.getDesc_tipo();
		}catch(Exception e)
		{
			log.debug("No se pudo encontrar el tipo de modem para la petición: "+ peticion,e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}           
	}
	
	public void grabaModemsBaST(Long nroPeticion,Long telAsignado,ArrayList modems) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);            
			Peticion_stLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));
			
			for(Iterator iterator=peticionLocal.getModem().iterator();iterator.hasNext();)
			{
				ModemLocal modemLocal=(ModemLocal) iterator.next();
				modemLocal.remove();
			}
			for(Iterator iterator=modems.iterator();iterator.hasNext();)
			{
				log.debug("Ingresando Modem");
				ModemSTDTO modemSTDTO=(ModemSTDTO) iterator.next();
				ModemLocal modemLocal = null;
				
				if(modemSTDTO.getAccion().intValue() != 0){
					
					modemLocal=(ModemLocal) modemLocalHome.create(modemSTDTO.getSerial(),peticionLocal,telAsignado,new Short(modemSTDTO.getAccion().shortValue()));
				}
				
				if(modemLocal!=null){
					modemLocal.setModem_marca(modemSTDTO.getMarca());
					modemLocal.setModem_marca(modemSTDTO.getMarca());
					modemLocal.setModelo(modemSTDTO.getModelo());
						
					modemLocal.setTipo(new Integer (modemSTDTO.getTipo().intValue()));
					modemLocal.setCod_material(modemSTDTO.getCod_material());
					
					/*RQ.8595 - mfmendez*/
					modemLocal.setFec_cont_sap(modemSTDTO.getPostingDateSAP());
					modemLocal.setClase_mov_sap(modemSTDTO.getMoveTypeSAP());
					if(modemSTDTO.getMaterialCodeSAP() != null){
						modemLocal.setPos_doc_sap(Integer.parseInt(modemSTDTO.getMaterialCodeSAP()));
					}else{
						modemLocal.setPos_doc_sap(0);
					}
					modemLocal.setNum_material_sap(modemSTDTO.getMaterialSAP());
					modemLocal.setCentro_sap(modemSTDTO.getPlantSAP());
					modemLocal.setAlmacen_sap(modemSTDTO.getStorageSAP());
					modemLocal.setCod_lote_sap(modemSTDTO.getBatchCodeSAP());
					modemLocal.setUnd_medida_sap(modemSTDTO.getMeasurementUnitSAP());
					modemLocal.setCentr_cost_sap(modemSTDTO.getCostCenterSAP());
					modemLocal.setArea_func_sap(modemSTDTO.getFuncAreaLongSAP());
					modemLocal.setElement_pep_sap(modemSTDTO.getPepElementSAP());
					modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					modemLocal.setFlag_mat_sap(modemSTDTO.getFlagMatSAP());
					/*FIN - RQ.8595 - mfmendez*/
					log.debug("Modem Ingresado Exitosamente.");   
				}
			}
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		}
	}
	
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx{
		RecursosBADTO recursosBADTO = null;
		
		try {
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(new Peticion_stKey(codAveOri));
			
			Recursos_baLocal  recursos_baLocal = null;
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			Tipo_ModemLocalHome tipo_ModemLocalHome = tipo_ModemLocalHome = (Tipo_ModemLocalHome) HomeFactory.getHome(Tipo_ModemLocalHome.JNDI_NAME);
			
			
			Collection recBA = peticion_stLocal.getRecursos_ba();
			if ( recBA==null || recBA.size()==0 ){
				log.debug("No existen recursos BA para esta Peticion:" + codAveOri);
			}else{
				recursos_baLocal = (Recursos_baLocal) recBA.iterator().next();
				
				Recursos_baKey recursos_baKey = (Recursos_baKey) recursos_baLocal.getPrimaryKey();
				log.debug("Existen recursos para esta Peticion BA:" + codAveOri);
				recursosBADTO = new RecursosBADTO();
				recursosBADTO.setIdConector(recursos_baKey.id_conector);
				recursosBADTO.setCodError(recursos_baLocal.getCod_error());
				recursosBADTO.setPuertoActual(recursos_baLocal.getPuerto_actual());
				recursosBADTO.setPostActual(recursos_baLocal.getPost_actual());
				recursosBADTO.setAdslActual(recursos_baLocal.getAdsl_actual());
				recursosBADTO.setMascLanActual(recursos_baLocal.getMasc_lan_actual());
				recursosBADTO.setDirIpDslamActual(recursos_baLocal.getDir_ip_dslam_actual());
				recursosBADTO.setDirIpWanActual(recursos_baLocal.getDir_ip_wan_actual());
				recursosBADTO.setFrameActual(recursos_baLocal.getFrame_actual());
				recursosBADTO.setPuertoNvo(recursos_baLocal.getPuerto_nuevo());
				recursosBADTO.setPostNvo(recursos_baLocal.getPost_nuevo());
				recursosBADTO.setAdslNvo(recursos_baLocal.getAdsl_nuevo());
				recursosBADTO.setMascLanNva(recursos_baLocal.getMasc_lan_nueva());
				recursosBADTO.setDirIpDslamNva(recursos_baLocal.getDir_ip_dslam_nueva());
				recursosBADTO.setDirIpWanNva(recursos_baLocal.getDir_ip_wan_nueva());
				recursosBADTO.setFrameNvo(recursos_baLocal.getFrame_nuevo());
				recursosBADTO.setCorrelativo(recursos_baLocal.getCorrelativo());
				recursosBADTO.setPetiNumero(recursos_baLocal.getPeti_numero());
				recursosBADTO.setVpiVciActual(recursos_baLocal.getVpivci_actual());
				recursosBADTO.setVpiVciRedActual(recursos_baLocal.getVpivci_red_actual());
				recursosBADTO.setSlotActual(recursos_baLocal.getSlot_actual());
				recursosBADTO.setDirIpLanNva(recursos_baLocal.getDir_ip_lan_nueva());
				recursosBADTO.setVpiVciNvo(recursos_baLocal.getVpivci_nuevo());
				recursosBADTO.setVpiVciRedNvo(recursos_baLocal.getVpivci_red_nuevo());
				recursosBADTO.setSlotNvo(recursos_baLocal.getSlot_nuevo());
				recursosBADTO.setDescError(recursos_baLocal.getDesc_error());
				recursosBADTO.setFatherEmail(recursos_baLocal.getFather_email_actual());
				recursosBADTO.setDirIpLanActual(recursos_baLocal.getDir_ip_lan_actual());//CR- 24100 agonz 30 junio 2009 correccion AT-2378
				/*
				 *          CR - 00026148 - Jun 29, 2009
				 *                     - German P.
				 */
				recursosBADTO.setModem_marca(recursos_baLocal.getModem_marca());
				recursosBADTO.setSerial(recursos_baLocal.getSerial());
				recursosBADTO.setModelo(recursos_baLocal.getModelo());
				
				String dsTipoModem = "Convencional";
				try{
					Long psAlta = peticion_stLocal.getCod_pro_ser_cd();
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
					log.debug("No se ha encontrado el Tipo de Modem");
				}
				recursosBADTO.setTipoModem(dsTipoModem);
				
			}
			
		} catch (EJBException e) {
			log.error("EJBException",e);
			recursosBADTO = null;
		} catch (NamingException e) {
			log.error("NamingException",e);
			recursosBADTO = null;
		} catch (FinderException e) {
			log.error("FinderException",e);
			recursosBADTO = null;
		}
		
		return recursosBADTO;
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviaModemPorUtilizar(long, java.lang.String)
	 */
	public Long enviaModemPorUtilizar(long codAveCd, String modemConsulta, long idContratista) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			Ps_Tipo_ModemLocalHome ps_Tipo_ModemLocalHome = ps_Tipo_ModemLocalHome = (Ps_Tipo_ModemLocalHome) HomeFactory.getHome(Ps_Tipo_ModemLocalHome.JNDI_NAME);
			Tipo_ModemLocalHome tipo_ModemLocalHome = tipo_ModemLocalHome = (Tipo_ModemLocalHome) HomeFactory.getHome(Tipo_ModemLocalHome.JNDI_NAME);
			
			Tmp_modemLocalHome tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mensajeOk=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			Peticion_stKey key = new Peticion_stKey (new Long (codAveCd)) ;
			
			Peticion_stLocal peticion = peticionLocalHome.findByPrimaryKey (key) ;
			
			Collection colTmpModem = tmp_modemHome.findByCodAveCd(new Long (codAveCd)) ;
			Iterator iterTmpModem = colTmpModem.iterator () ;
			while (iterTmpModem.hasNext ())
			{
				Tmp_modemLocal tmp_modemLocal= (Tmp_modemLocal) iterTmpModem.next();
				
				try
				{
					tmp_modemLocal.remove();
				}
				catch (EJBException e1)
				{
					log.error("Error Respuesta Modem.",e1);
					
				}
				catch (RemoveException e1)
				{
					log.error("Error Respuesta Modem.",e1);
					
				}
			}
			//          
			TR022E tr022e=new TR022E();
			tr022e.setId(String.valueOf(idCorrelativoMensaje));
			tr022e.setAtisRequestNumber(codAveCd);
			tr022e.setCityCode(peticion.getCod_loc());
			tr022e.setModemFinalDigits(modemConsulta);                           
			
			Long psAlta = peticion.getCod_pro_ser_cd();
			
			tr022e.setId_plan(psAlta.longValue());
			//TCS-gquevedo May 18, 2009 CR.23338 INICIO
			tr022e.setModemType(new Integer(1));
			//DAVID: se adiciona parámetro idContratista, req 32354, Febrero 8 2010
			tr022e.setContractorId(idContratista);
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
			try {
				Ps_Tipo_ModemLocal ps_Tipo_ModemLocal = ps_Tipo_ModemLocalHome.findByNroPs(psAlta);
				if (ps_Tipo_ModemLocal!= null){
					tr022e.setModemType(ps_Tipo_ModemLocal.getId_tipo_modem());
				}
			}catch (FinderException e){
			}
			//TCS-gquevedo May 18, 2009 CR.23338 FIN
			////    
			Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
			mensajeEstadoLocal.setPeticion_st(peticion);
			mensajeEstadoLocal.setFecha_envio(df.format(new java.util.Date ()));
			Mensaje_estadoKey mensaje_estadoKey=(Mensaje_estadoKey) mensajeOk.getPrimaryKey();
			mensajeEstadoLocal.setCod_estado(new Integer(mensaje_estadoKey.cod_estado.intValue()));
			////    
			new SolicitudModemMQ ().enviarMensajeReplyToQ(tr022e) ;
			//
			return (idCorrelativoMensaje) ;
		}
		catch (NumberFormatException e)
		{
			log.error("Error Envio Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT);
		}
		catch (FinderException e)
		{
			log.error("Error Envio Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (CreateException cex)
		{
			log.error ("error creando registro", cex) ;
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		} catch (NamingException e) {
			log.error("Error Envio Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}
		catch(Exception e)
		{
			log.error("Error Envio Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION) ;
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#actualizaModemPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR022S)
	 */
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			// busca el registro del mensaje
			log.debug("Primero si es mensaje de error lo obviamos.");
			if(tr022s.isError())
			{
				log.debug("Respuesta con error:"+XMLUtilities.marshall(tr022s));
				return;
			}
			
			Mensaje_estado_stLocal mensajeEstadoSt = null;
			
			try
			{
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				mensajeEstadoSt = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr022s.getId())));
			}
			catch (FinderException fex)
			{
				mensajeEstadoSt = null ;
			}
			
			if (mensajeEstadoSt == null)
			{
				log.debug (
						"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall (tr022s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr022s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			// Validacion del estado de la respuesta
			
			
			if (mensajeEstadoSt.getCod_estado().intValue() != estadoOk)
			{
				log.debug ("Es estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall (tr022s));
				return;
			}
			
			// TODO: ver si procesamos los errores o si la interfaz al usuario se va a encargar de
			//       parsear el XML
			
			// todo ok, se graba la respuesta
			
			Long idTmpModem = new Long (dbSeq.seqNextValLong ("ATIEMPO.CORRELATIVO_TMP_MODEM")) ;
			
			Tmp_modemLocalHome tmp_modemHome=(Tmp_modemLocalHome) HomeFactory.getHome(Tmp_modemLocalHome.JNDI_NAME);
			
			Tmp_modemLocal tmpDecoTarjetaLocal = tmp_modemHome.create (idTmpModem,mensajeEstadoSt.getPeticion_st(),XMLUtilities.marshall (tr022s));
			
		}
		catch (NumberFormatException e)
		{
			log.error("Error Actualiza Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NUMBER_FORMAT) ;
		}
		catch (CreateException e)
		{
			log.error("Error Actualiza Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.CREATE) ;
		}
		catch (NamingException e)
		{
			log.error("Error Actualiza Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING) ;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#buscarRespuestaMensajeModem(java.lang.Long, java.lang.Long)
	 */
	public TR022S buscarRespuestaMensajeModem(Long codAveCd, Long idMensaje) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
		try
		{
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome=(Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal msgMoLocal = msgEstadoLineaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(idMensaje));
			
			if (msgMoLocal == null)
				return null;
			
			Peticion_stLocal pLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(codAveCd));
			Collection c = pLocal.getTmp_modem();
			for (Iterator it = c.iterator(); it.hasNext();)
			{
				Tmp_modemLocal tmpdtLocal = (Tmp_modemLocal) it.next();
				TR022S tr022s = (TR022S) XMLUtilities.unmarshall(tmpdtLocal.getXml());
				return tr022s;
			}
		}
		catch (FinderException e)
		{
			log.error("Error Respuesta Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER);
		}
		catch (NamingException e)
		{
			log.error("Error Respuesta Modem.",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING);
		}
		return null;
	}
	
	// inicio SIGRES: adecarlini
	
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad) throws ATiempoAppEx{
		
		try{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mensajeEsperaLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			
			log.debug("** SIGRES ** Actividad: configuracion actual SIGRES");                                                        
			/*
			 * verifico si esta refrescando recursos, o es un registro nuevo
			 */
			boolean esRefrescarRecursosBA = id_actividad.equals("");
			if(esRefrescarRecursosBA) id_actividad=STConfig.getVariable("COD_ACTIVIDAD_REC_BA_SIGRES");
			
			/*
			 * traigo la peticion y busco el PS
			 */                                
			log.debug("** SIGRES ** buscando peticion st: "+peticion);
			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
			Peticion_stLocal peticion_stLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
			
			Collection ProductoServicioPeticionStArray = peticion_stLocal.getProducto_servicio_peticion();
			Producto_servicio_peticionLocal productoServicioPeticionStLocal  = (Producto_servicio_peticionLocal)ProductoServicioPeticionStArray.iterator().next();
			Familia_producto_servicio_stKey familiaProductoServicioStKey = (Familia_producto_servicio_stKey) productoServicioPeticionStLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
			Integer faps_id = new Integer(familiaProductoServicioStKey.faps_id.intValue());
			
			log.debug("** SIGRES ** familia= "+faps_id);
			
			/*
			 * cargo los datos a enviar, y creo la TR 
			 */
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			String phoneNumber = peticion_stLocal.getNum_ide_nu();
			if (phoneNumber!=null && phoneNumber.trim()!= ""){
				if (phoneNumber.length()>8)       phoneNumber=phoneNumber.substring(0,8);
			}else{
				phoneNumber="0";
			};
			
			TR035E tr035e = new TR035E();
			tr035e.setId(String.valueOf(IdCorrelativo));
			tr035e.setServiceNumber(phoneNumber);
			log.info("enviando a SIGRES:\n"+  XMLUtilities.marshall(tr035e)); 
			
			/*
			 *  Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			 */
			Mensaje_estado_stLocal mensajeEstadoStLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorCuatro))).getPrimaryKey();
			
			mensajeEstadoStLocal.setPeticion_st(peticion_stLocal);
			mensajeEstadoStLocal.setCod_familia_ps(faps_id);
			mensajeEstadoStLocal.setCod_conector(conectorKey.cod_conector);
			mensajeEstadoStLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoStLocal.setCod_estado(((Mensaje_estadoKey) mensajeEsperaLocal.getPrimaryKey()).cod_estado);
			mensajeEstadoStLocal.setCod_actividad_generadora(id_actividad);
			if(peticion_stLocal.getCod_are_ges_cd()!=null) mensajeEstadoStLocal.setArea(new Integer(peticion_stLocal.getCod_are_ges_cd().intValue()));
			if(peticion_stLocal.getTel_cot_ds()!=null) mensajeEstadoStLocal.setTelefono(peticion_stLocal.getTel_cot_ds());
			if(esRefrescarRecursosBA) mensajeEstadoStLocal.setAccion("R");
			
			/*
			 * envio la TR035E 
			 */
			log.info("----------------------------------------------------------------");
			log.info("mensaje recibido de SIGRES:\n"+ XMLUtilities.marshall(tr035e));                                   
			log.info("----------------------------------------------------------------");
			SolicitudConfiguracionBASigresMQ  configuracionActualBASigresMQ = new SolicitudConfiguracionBASigresMQ();
			configuracionActualBASigresMQ.enviarMensajeReplyToQ(tr035e);
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} 
		catch(Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}           
	}
	
	public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx{
		try{                   
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estado_stLocal mensajeEstadoStLocal = null;
			try {
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				Mensaje_estado_stKey key = new Mensaje_estado_stKey(new Long(tr035s.getId()));
				mensajeEstadoStLocal = msgEstadoLineaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoStLocal = null ;
			}
			
			if (mensajeEstadoStLocal == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr035s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr035s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			
			int cod_estado=mensajeEstadoStLocal.getCod_estado().intValue();
			if (cod_estado == estadoOk || cod_estado == estadoEsperaManual) {
				log.warn("El estado de la respuesta es diferente para ser procesado\n "   + XMLUtilities.marshall(tr035s));
				return;
			}
			
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			Peticion_stLocal petLocal= mensajeEstadoStLocal.getPeticion_st();
			Peticion_stKey pk= (Peticion_stKey) petLocal.getPrimaryKey();
			
			/*
			 * traigo mensajes de estado a utilizar
			 */
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			
			/*
			 * chequeo condiciones, para ver si hay error
			 */
			IActividadEJB actividadEJB = null;
			ActividadEJBDTO actDto = null;
			boolean errorCode = false;
			if (tr035s.getCode()!= null && !"".equals(tr035s.getCode()) && !"0000".equals(tr035s.getCode())) errorCode=true;
			
			/*
			 * si no hay error
			 */
			if (tr035s.isError() != true && !errorCode){
				log.debug("** SIGRES ** no hay error");
				/*
				 * verifica si crea un registro o actualiza recursos ba: en caso que sea actualizacion, me quedo con el primer registro de recursos ba
				 */
				Collection recursosBaSt = petLocal.getRecursos_ba();
				boolean insert = recursosBaSt.size()==0;
				Recursos_baLocal recursos_baLocal = insert?null:(Recursos_baLocal) recursosBaSt.iterator().next();
				
				log.debug("** SIGRES ** recursos ba -->"+(insert?"nuevo registro":"actualiza registro, cantidad de recursos= " + recursosBaSt.size()));
				
				/*
				 * crea o actualiza recursos ba
				 */
				if (insert){
					Recursos_baLocalHome recursos_baLocalHome = (Recursos_baLocalHome) HomeFactory.getHome(Recursos_baLocalHome.JNDI_NAME);
					Long idConDos =          new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					recursos_baLocal = recursos_baLocalHome.create(idConDos,petLocal);
				}
				
				
				recursos_baLocal.setPeti_numero(pk.cod_ave_cd);         
				
				if (tr035s.getAdsl() != null && tr035s.getAdsl().length()>0){
					recursos_baLocal.setAdsl_actual(tr035s.getAdsl());
				}
				
				if (tr035s.getIpWan() != null && tr035s.getIpWan().length()>0){
					recursos_baLocal.setDir_ip_wan_actual(tr035s.getIpWan());
				}
				
				if (tr035s.getIpLanMask() != null && tr035s.getIpLanMask().length()>0){
					recursos_baLocal.setMasc_lan_actual(tr035s.getIpLanMask());
				}
				
				if (tr035s.getPots()!=null && tr035s.getPots().length()>0){
					recursos_baLocal.setPost_actual(tr035s.getPots());
				}
				
				if (tr035s.getPortId()!=null && tr035s.getPortId().length()>0){
					recursos_baLocal.setPuerto_actual(tr035s.getPortId());
				}
				
				if (tr035s.getSlot() != null && tr035s.getSlot().length()>0){
					recursos_baLocal.setSlot_actual(tr035s.getSlot());
				}
				
				if (tr035s.getVpiVci() != null && tr035s.getVpiVci().length()>0){
					recursos_baLocal.setVpivci_actual(tr035s.getVpiVci());
				}
				
				if (tr035s.getVpiVciNetwork() != null && tr035s.getVpiVciNetwork().length()>0){
					recursos_baLocal.setVpivci_red_actual(tr035s.getVpiVciNetwork());
				}
				
				if (tr035s.getIp() != null && tr035s.getIp().length() >0){
					recursos_baLocal.setDir_ip_dslam_actual(tr035s.getIp());//CR- 24100 agonz 20 marzo 2009
				}
				
				if (tr035s.getSubrack() != null && tr035s.getSubrack().length()>0){
					recursos_baLocal.setFrame_actual(tr035s.getSubrack());//CR- 24100 agonz 20 marzo 2009
				}
				
				/*
				 * cambio el estado
				 */
				mensajeEstadoStLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
				
				if(mensajeEstadoStLocal.getAccion()!=null && mensajeEstadoStLocal.getAccion().equals("R")) return;
				
				actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoStLocal.getCod_actividad_generadora());
				actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoStLocal.getCod_actividad_generadora());
				actDto.setObservacion(".");
			}else{
				log.debug("** SIGRES ** hay error en la TR");
				mensajeEstadoStLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
				/*
				 * obtengo mensaje de error
				 */
				String errorCodeMsg = "";
				
				if (tr035s.getErrorMessage()!=null)
					{
					errorCodeMsg=tr035s.getErrorMessage() + ".";
					}
				if (tr035s.getDescription()!=null)  
					{
					errorCodeMsg=tr035s.getDescription();
					}
				
				log.debug("Error en la Obtención de Recursos BA. " + errorCodeMsg);
				
				if(mensajeEstadoStLocal.getAccion()!=null && mensajeEstadoStLocal.getAccion().equals("R")) return;
				
				
				actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoStLocal.getCod_actividad_generadora());
				actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoStLocal.getCod_actividad_generadora());
				//actDto.setObservacion("Error en la Obtención de Recursos BA. " + errorCodeMsg);
				//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
				//actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
				
				//REQ TOA FASE III Dcardena
				//METODO QUE ENVIA A PGI LA AVERIA
				RecursosDelegate rd = new RecursosDelegate();
				rd.hayPGIAveria(tr035s.getCode(),tr035s.getErrorMessage(),"TR035S",mensajeEstadoStLocal.getPeti_numero().toString(),mensajeEstadoStLocal.getCod_actividad_generadora()  ,mensajeEstadoStLocal);
				return;
				//FIN REQ TOA FASE III 
			}
			
			log.debug("** SIGRES ** terminando la actividad recepcion de configuracion BA de sigres");
			
			mensajeEstadoStLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoStLocal.setCod_actividad_generadora(mensajeEstadoStLocal.getCod_actividad_generadora());
			
			
			actividadEJB.terminar(actDto);
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}catch(Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		
	}
	
	
	public void recepcionCuentaCorreoBA(TR043S tr043s) throws ATiempoAppEx{
		try{
			Mensaje_estado_stLocal mensajeEstadoStLocal = null;
			try {
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				Mensaje_estado_stKey key = new Mensaje_estado_stKey(new Long(tr043s.getId()));
				mensajeEstadoStLocal = msgEstadoLineaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoStLocal = null ;
			}
			
			if (mensajeEstadoStLocal == null) {
				log.warn("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr043s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr043s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			int cod_estado=mensajeEstadoStLocal.getCod_estado().intValue();
			if (cod_estado == estadoOk || cod_estado == estadoEsperaManual) {
				log.warn("El estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr043s));
				return;
			}
			
			/*
			 * chequeo condiciones, para ver si hay error
			 */
			boolean errorCode = false;
			if (tr043s.getCode()!= null && !"".equals(tr043s.getCode()) && !"0000".equals(tr043s.getCode())) errorCode=true;
			
			
			/*
			 * traigo la peticion ST y la Actividad
			 */
			Peticion_stLocal peticion_stLocal= mensajeEstadoStLocal.getPeticion_st();
			Peticion_stKey pk= (Peticion_stKey) peticion_stLocal.getPrimaryKey();
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			
			
			/*
			 * levanto de la bd los posibles mensajes estados que se utilizan 
			 */
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			
			IActividadEJB actividadEJB = null;
			ActividadEJBDTO actDto = null;
			/*
			 * si no hay error
			 */
			if (tr043s.isError() != true && !errorCode){
				Collection recursosLineaBaBasica = peticion_stLocal.getRecursos_ba();
				log.debug("** SIGRES ** Existen Recursos BA: " + recursosLineaBaBasica.size());
				mensajeEstadoStLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
				if ( recursosLineaBaBasica.size() == 0){
					/*
					 * si no tiene recursos BA, tampoco tiene cuenta de correo
					 */
					if(mensajeEstadoStLocal.getAccion() != null && mensajeEstadoStLocal.getAccion().equals("F"))
						enviarRefrecarDatos(mensajeEstadoStLocal.getPeti_numero());
					if(mensajeEstadoStLocal.getAccion()!=null && (mensajeEstadoStLocal.getAccion().equals("R") || mensajeEstadoStLocal.getAccion().equals("F"))) return;
					
					actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoStLocal.getCod_actividad_generadora());
					actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoStLocal.getCod_actividad_generadora());
					
					log.warn("no se encuentra los recursos ba para agregar la cuenta correo para la incidencia: "+actDto.getNumeroPeticion());
					actDto.setObservacion("no existe registro de recursos BA");
				}else{
					/*
					 * agrego la cuenta correo al registro de recursos BA
					 */
					log.debug("Actualiza Recursos BA --> agrego cuenta correo (SIGRES)");
					Recursos_baLocal recursos_baLocal = (Recursos_baLocal) recursosLineaBaBasica.iterator().next();
					recursos_baLocal.setFather_email_actual(tr043s.getFatherEmail());
					log.debug("Accion Mensaje Estado ST: "+mensajeEstadoStLocal.getAccion());
					if(mensajeEstadoStLocal.getAccion() != null && (mensajeEstadoStLocal.getAccion().equals("F") || mensajeEstadoStLocal.getAccion().equals("R"))){
				
						if(mensajeEstadoStLocal.getAccion().equals("R")){	
							if (mensajeEstadoStLocal.getCod_actividad_generadora().equals(ComunInterfaces.PLANTA_EXTERNA_TOA)) {
									log.debug("La actividad corresponde a TOA");
									ServicioTOASTDelegate toaDelegate = new ServicioTOASTDelegate();
									toaDelegate.enviaRefrescarRecursosBA(recursos_baLocal);
								} else {
									enviarRefrecarDatos(mensajeEstadoStLocal.getPeti_numero());
								}
								return;
								}
						}
					if(mensajeEstadoStLocal.getAccion()!=null && (mensajeEstadoStLocal.getAccion().equals("R") || mensajeEstadoStLocal.getAccion().equals("F"))) return;
					
					actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoStLocal.getCod_actividad_generadora());
					actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoStLocal.getCod_actividad_generadora());
					actDto.setObservacion(".");                    
				}
				
			}else{
				//Hay error        
				mensajeEstadoStLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
				
				String errorCodeMsg = "";
				if (tr043s.getDescription()!=null){
					errorCodeMsg=tr043s.getDescription() + ".";
				}
				if (tr043s.getErrorMessage()!=null){
					errorCodeMsg=tr043s.getErrorMessage();
				}
				log.debug("Error obteniendo la cuenta de correo: " +errorCodeMsg );
				if(mensajeEstadoStLocal.getAccion() != null && mensajeEstadoStLocal.getAccion().equals("F"))
					enviarRefrecarDatos(mensajeEstadoStLocal.getPeti_numero());
				if(mensajeEstadoStLocal.getAccion()!=null && (mensajeEstadoStLocal.getAccion().equals("R") || mensajeEstadoStLocal.getAccion().equals("F"))) return;
				
				actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoStLocal.getCod_actividad_generadora());
				actDto = actividadEJB.getActividadEJBDTO(pk.cod_ave_cd , mensajeEstadoStLocal.getCod_actividad_generadora());
				//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
				//actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
				//actDto.setObservacion("Error en la Obtención Cuenta Correo. " + errorCodeMsg);  
				
				//REQ TOA FASE III Dcardena
				//METODO QUE ENVIA A PGI LA AVERIA
				RecursosDelegate rd = new RecursosDelegate();
				rd.hayPGIAveria(tr043s.getCode(),tr043s.getErrorMessage(),"TR043S",mensajeEstadoStLocal.getPeti_numero().toString(),mensajeEstadoStLocal.getCod_actividad_generadora()  ,mensajeEstadoStLocal);
				return;
				//FIN REQ TOA FASE III 
			}
			
			/*
			 * cierro la actividad
			 */
			mensajeEstadoStLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoStLocal.setCod_actividad_generadora(mensajeEstadoStLocal.getCod_actividad_generadora());
			
			if(mensajeEstadoStLocal.getAccion()!=null && (mensajeEstadoStLocal.getAccion().equals("R") || mensajeEstadoStLocal.getAccion().equals("F"))) return;
			actividadEJB.terminar(actDto);
			
		}catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		}catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		
	}
	
	
	public void enviarCuentaCorreo(String peticion, String id_actividad, String codActividad) throws ATiempoAppEx{
		try{
			log.debug("SIGRES: enviando peticion de cuenta correo");
			/*
			 * traigo mensaje estado, para el "estado de espera"
			 */
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			Mensaje_estadoLocal mensaje_estadoEsperaLocal = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			
			/*
			 * verificacion si se invoca este metodo, porque esta refrescando recursos ba o si es la primera vez que se llama
			 */
			String temIdAct = "";
			boolean refrescarRecursos = false;
			if(id_actividad.equals("")||id_actividad.equals("F")||id_actividad.equals("RF")){
				refrescarRecursos = true;
				temIdAct = id_actividad;
				//id_actividad=STConfig.getVariable("COD_ACTIVIDAD_REC_BA_AUTOMATICA_SI");
			}
			
			log.debug("SIGRES: refrescando recursos?"+ refrescarRecursos);
			if(codActividad != null){
				id_actividad = codActividad;
			}else{
				id_actividad=STConfig.getVariable("COD_ACTIVIDAD_REC_BA_AUTOMATICA_SI");//agonz 6 abril 2009 AT-2028 CR-24573
			}
			/*
			 * obtengo el numero de telefono, para la incidencia (supongo 8 digitos)
			 */
			Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
			Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
			String phoneNumber = peticionLocal.getNum_ide_nu();
			if (phoneNumber!=null && phoneNumber.trim()!= ""){
				if (phoneNumber.length()>8){ 
					phoneNumber=phoneNumber.substring(0,8);
				}
			} else{
				phoneNumber="0";
			}                                              
			
			
			log.debug("SIGRES: nro Incidencia= "+peticion+ ", nro telefono= "+phoneNumber);
			/*
			 *  Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado ST
			 */
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			Mensaje_estado_stLocal mensajeEstadoStLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			
			/*
			 *me quedo con el unico ps, para ver que familia es. Uso el iterator para no cambiar como estaba antes
			 *tambien "cargo" los datos a insertar en la tabla 
			 */
			Iterator iter = peticionLocal.getProducto_servicio_peticion().iterator();
			Producto_servicio_peticionLocal producto_servicio_peticionLocal =(Producto_servicio_peticionLocal)iter.next();
			Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
			ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorCuatro))).getPrimaryKey();
			Mensaje_estadoKey mensaje_estadoKey =  (Mensaje_estadoKey) mensaje_estadoEsperaLocal.getPrimaryKey();
			
			log.debug("SIGRES: cantidad de prod_servicios="+peticionLocal.getProducto_servicio_peticion().size()+", familia de ps="+familia_producto_serviciostKey.faps_id.intValue());
			/*
			 * escribo los datos en "mensaje estado st"
			 */
			mensajeEstadoStLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoStLocal.setCod_estado(new Integer(mensaje_estadoKey.cod_estado.intValue()));
			mensajeEstadoStLocal.setCod_actividad_generadora(id_actividad);
			mensajeEstadoStLocal.setCod_conector(conectorKey.cod_conector);                             
			mensajeEstadoStLocal.setPeticion_st(peticionLocal);
			mensajeEstadoStLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
			if(refrescarRecursos){
				if(temIdAct.equals("F")){
					mensajeEstadoStLocal.setAccion(temIdAct);
				}else{
					mensajeEstadoStLocal.setAccion("R");
				}
			}
				
			if(peticionLocal.getCod_are_ges_cd()!=null)
				mensajeEstadoStLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
			if(peticionLocal.getTel_cot_ds()!=null)
				mensajeEstadoStLocal.setTelefono(peticionLocal.getTel_cot_ds());
			
			
			/*
			 * creo la TR a enviar, y se envia
			 */
			TR043E tr043e = new TR043E();
			tr043e.setId(String.valueOf(IdCorrelativo));
			tr043e.setAtiempoRequestNumber(new Long(peticion).longValue());
			tr043e.setServiceNumber(phoneNumber);
			
			log.info("----------------------------------------------------------------");
			log.info("mensaje enviado a SIGRES:\n"+ XMLUtilities.marshall(tr043e));  
			log.info("----------------------------------------------------------------");
			new SolicitudCuentaCorreoBAMQ().enviarMensajeReplyToQ(tr043e);
			
			
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} 
		catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	//fin SIGRES
	/***
	 * DAVID: Generación de alta de actuación de agenda SC.
	 */
	public void creacionActuacionAgendaSC(Long codAve, ActividadEJBDTO act)throws ATiempoAppEx{
		log.info("Se ingresa al método de envío de mensaje de alta de actuación: TR701E");
		try{			
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);                                    
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			SubsegmentoLocalHome subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			SegmentoLocalHome segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME); 
			//Piden que se ponga el último diagnóstico en summary
			Prueba_lineaLocalHome prueba_lineaLocalHome = (Prueba_lineaLocalHome) HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
			ElementoLocalHome elementoLocalHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
			Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			Elemento_agenda_scLocalHome elementoAgendaSCLocalHome = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
			Codigo_stLocalHome codigoSTLocalHome = (Codigo_stLocalHome) HomeFactory.getHome(Codigo_stLocalHome.JNDI_NAME);
			String descripcion = "";
			
			boolean noTieneCentral = false;
			/*RQ.8595 - mfmendez*/
			Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
			
			Codigo_stLocalHome codigo_stLocalHome  = (Codigo_stLocalHome) HomeFactory.getHome(Codigo_stLocalHome.JNDI_NAME);
			
			Peticion_stKey peticion_stKey=new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal=peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			
			Collection recursosLBLista= peticion_stLocal.getRecursos_linea_basica();
			Collection recursosBALista= peticion_stLocal.getRecursos_ba();
			
			Recursos_linea_basicaLocal rlb = null;
			Recursos_baLocal rba=null;
			
			if(recursosLBLista!=null&&recursosLBLista.size()>0){
				rlb=(Recursos_linea_basicaLocal)recursosLBLista.iterator().next();
			}
			if(recursosBALista!=null&&recursosBALista.size()>0){
				rba=(Recursos_baLocal)recursosBALista.iterator().next();
			}
			
			
			Collection deco_tarjeta=peticion_stLocal.getDeco_tarjeta();
			String numDecos="0";
			if(deco_tarjeta!=null){
				numDecos=deco_tarjeta.size()+"";
			}
			
			//Se extrae el tipo de deco
			Iterator decoTarjetaListIt=null;
			String tipoDeco="";
			if(deco_tarjeta!=null){
				for(decoTarjetaListIt=deco_tarjeta.iterator();decoTarjetaListIt.hasNext();){
					Deco_tarjetaLocal deco_tarjetaLocal=(Deco_tarjetaLocal)decoTarjetaListIt.next();
					if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoHDTV)){
						tipoDeco=ComunInterfaces.desHCDecoHDTV;
						break;
					}else if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoPVR)){
						tipoDeco=ComunInterfaces.desHCDecoPVR;
						break;
					}else if(deco_tarjetaLocal.getDeco_reference().equals(ComunInterfaces.desHCDecoSTD)){
						tipoDeco=ComunInterfaces.desHCDecoSTD;
						break;
					}
				}
			}
			
			String listaPs="";                                  
			String listaTematicosTV="";
			String emailCaracteristicas="";
			Collection productoServicioPetList=peticion_stLocal.getProducto_servicio_peticion();
			
			Collection peticionFlujo = peticion_stLocal.getPeticion_flujo();
			Collection productsService = new ArrayList();
			
			Iterator listaProductoServicioPetIt=null;
			for(listaProductoServicioPetIt=productoServicioPetList.iterator();listaProductoServicioPetIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)listaProductoServicioPetIt.next();
				Producto_servicio_stLocal producto_servicio_stLocal=producto_servicio_peticionLocal.getProducto_servicio_st();
				Producto_servicio_stKey  producto_servicio_stKey=(Producto_servicio_stKey)producto_servicio_stLocal.getPrimaryKey();
				listaPs=listaPs+producto_servicio_stLocal.getPs_nombre()+" ";//producto_servicio_stKey.ps_id.toString()+" ";  
				//Se extraen los temáticos         
				Familia_producto_servicio_stKey familia_producto_servicio_stKey = (Familia_producto_servicio_stKey)producto_servicio_stLocal.getFamilia_producto_servicio_st().getPrimaryKey();
				if(familia_producto_servicio_stKey.faps_id.intValue()==ComunInterfaces.familiaTematicoTV){
					listaTematicosTV+=" " + producto_servicio_stLocal.getPs_nombre();
					
				}
				/**
				 * Campo nuevo, manejado en VPI con los ps's no quebrados, acá se insertan todos los ps's teniendo en 
				 * cuenta que hay un solo ps por petición y una sola operación comercial.
				 */
				TR701EProductService productService = new TR701EProductService();
				productService.setPsId(producto_servicio_stKey.ps_id.toString());
				Operacion_comercial_stKey operacion_comercial_stKey = (Operacion_comercial_stKey)producto_servicio_peticionLocal.getOperacion_comercial_st().getPrimaryKey();
				productService.setOperationComercial(operacion_comercial_stKey.opco_id.toString());
				productsService.add(productService);
				
			}
			
			TR701E tr701e = new TR701E();            
			
			//Se insertan los ps's con su oc.
			tr701e.setProductsService(productsService);                              
			TR701EAccessTime tr701EAccessTime = new TR701EAccessTime();
			tr701EAccessTime.setJorney("");
			TR701EAdressData tr701EAdressData = new TR701EAdressData();
			tr701EAdressData.setAddress(peticion_stLocal.getNom_cal_ds()+" "+peticion_stLocal.getNum_cal_nu()+" "+peticion_stLocal.getDsc_cmp_pri_ds());
			tr701EAdressData.setAgencyName("");//Aplica para SISGOT, se fija en vacío
			
			
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadKey localidadKey = new LocalidadKey(codLoc);
			LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(localidadKey);
			
			MunicipioLocal municipioLocal=localidadLocal.getMunicipio();
			MunicipioKey municipioKey=(MunicipioKey)municipioLocal.getPrimaryKey();
			
			String codDepto=peticion_stLocal.getCod_dpt();
			DepartamentoKey departamentoKey=new DepartamentoKey(codDepto);
			
			String barrio="N.A.";
			if(peticion_stLocal.getObs_ave_mas_ds()!=null&&!peticion_stLocal.getObs_ave_mas_ds().equals("")){
				barrio=peticion_stLocal.getObs_ave_mas_ds();
			}
			
			tr701EAdressData.setCity(municipioKey.cod_mun);
			
			
			//DAVID: Feb 14 2011, se hace para setear coord x Y y para agenda SC.
			/*Yumbleiner: Mayo 31 2011 CR 5448  La informacion de Coordenadas ya se esta recibiendo en la interfaz tr005
			 * String lonLat=peticion_stLocal.getDsc_cmp_pri_ds()+" "+peticion_stLocal.getDsc_cmp_seg_ds() ;
			
			int latId=lonLat.indexOf("LAT:");
			int lonId=lonLat.indexOf("LON:");
			String soloLatitud="";
			String soloLongitud="";
			if(latId!=-1){
				String inicioLatitud=lonLat.substring(latId);
				int finLatId=inicioLatitud.indexOf(" ");
				if(finLatId==-1){
					soloLatitud=inicioLatitud;
				}else{
					soloLatitud=inicioLatitud.substring(0,finLatId);
				}
				soloLatitud=soloLatitud.replaceAll("LAT:","");
				soloLatitud=soloLatitud.replaceAll("[a-zA-Z]","");
				
			}
			
			if(lonId!=-1){
				String inicioLongitud=lonLat.substring(lonId);
				int finLonId=inicioLongitud.indexOf(" ");
				if(finLonId==-1){
					soloLongitud=inicioLongitud;
				}else{
					soloLongitud=inicioLongitud.substring(0,finLonId);
				}
				
				soloLongitud=soloLongitud.replaceAll("LON:","");
				soloLongitud=soloLongitud.replaceAll("[a-zA-Z]","");
				
			}
			log.debug("La latitud es: "+soloLatitud);
			log.debug("La longitud es: "+soloLongitud); */                   
			
			if (peticion_stLocal.getCoordenada_x() != null && peticion_stLocal.getCoordenada_x().length()>0){
				tr701EAdressData.setCoordinateX(peticion_stLocal.getCoordenada_x());
			}else{
				log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
				tr701EAdressData.setCoordinateX("0");
			}
			
			if(peticion_stLocal.getCoordenada_y() != null && peticion_stLocal.getCoordenada_y().length()>0){
				tr701EAdressData.setCoordinateY(peticion_stLocal.getCoordenada_y());
			}else{
				log.debug("No se encuatra especificada la coordenada Y se setea 0 por defecto para evitar conflicots con agenda SC");
				tr701EAdressData.setCoordinateY("0");
			}
			
			tr701EAdressData.setLocation(localidadKey.cod_loc);
			tr701EAdressData.setNeighborhood(barrio);
			tr701EAdressData.setState(departamentoKey.cod_dpt);
			tr701EAdressData.setSubCity(barrio);
			
			
			TR701EContactData tr701EContactData = new TR701EContactData();
			
			
			//DAVID: Enero 24 2011, modificaciones para el teléfono de contacto.
			//                                 
			//String telefonoContactoSinLetras="0";
			//if(peticion_stLocal.getTel_cot_ds()!=null&&!peticion_stLocal.getTel_cot_ds().equals("")){
			//	telefonoContactoSinLetras=peticion_stLocal.getTel_cot_ds();
			//	telefonoContactoSinLetras=telefonoContactoSinLetras.replaceAll("[^\\d]","");//Se quitan los caractéres no numéricos.                                  
			//	if(telefonoContactoSinLetras.equals("")){
			//		telefonoContactoSinLetras="0";
			//	}
			// 	log.debug("El número de teléfono de contacto sin letras es: "+telefonoContactoSinLetras);
			//}
			//                                 
			//if(peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.TV)&&telefonoContactoSinLetras.equals("0")){
			//	telefonoContactoSinLetras="55555555";
			//}else if((peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.LBST)||peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.BA))
			//			&&telefonoContactoSinLetras.equals("0")){
			//	telefonoContactoSinLetras=peticion_stLocal.getNum_ide_nu();
			//}
			
			
			String descFamilia="";
			if(peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.LBST)){
				descFamilia=ComunInterfaces.LB;//Se utiliza esto porque agenda pide LB y en soltec Línea Básica se registra como L.
			}else{
				descFamilia=peticion_stLocal.getIde_pro_cmr_cd();
			}
			String telefonoContactMedia="";
			String telefonoContactoSinLetras="0";
			if(peticion_stLocal.getTel_cot_ds()!=null&&!peticion_stLocal.getTel_cot_ds().equals("")){
				telefonoContactoSinLetras=peticion_stLocal.getTel_cot_ds();
				telefonoContactoSinLetras=telefonoContactoSinLetras.replaceAll("[^\\d]","");//Se quitan los caractéres no numéricos.
			}
			//David: Si el teléfono sigue llegando vacío o 0 con la lógica anterior, entones se hace lo siguiente.
			if(telefonoContactoSinLetras.equals("0")||telefonoContactoSinLetras.equals("")){
				// Si no es LB ni BA y al quitarle las letras quedó cero o vacío, se setea un valor por defecto.
				telefonoContactoSinLetras=ComunInterfaces.TELEFONO_DUMMY_TV;
				if(descFamilia.equals(ComunInterfaces.BA)||descFamilia.equals(ComunInterfaces.LB)){
					if(peticion_stLocal.getNum_ide_nu()!=null&&!peticion_stLocal.getNum_ide_nu().equals("")){
						telefonoContactoSinLetras=peticion_stLocal.getNum_ide_nu().replaceAll("[^\\d]","");
					}
				}                                              
			}                       
			
			telefonoContactMedia=telefonoContactoSinLetras;
			
			log.debug("El número de teléfono de contacto sin letras es: "+telefonoContactoSinLetras);
			
			
			
			tr701EContactData.setContactCellPhone(telefonoContactoSinLetras);
			//Fin, DAVID: Enero 24 2011, modificaciones para el teléfono de contacto.
			
			tr701EContactData.setContactEmail("");
			/**
			 * Se saca el contact media a través del contactPhone, si es de 10 dígitos y empieza por 3, es un celular, etonces se envía 1, que es 
			 * SMS, si es otro tipo de número se envía 3, que es teléfono fijo.
			 */
			//                                 String telefono="";
			String contactMedia=CONTACT_MEDIA_TELEFONO;
//			if(peticion_stLocal.getTel_cot_ds()!=null){
//				telefono=peticion_stLocal.getTel_cot_ds();
//			}			
			if(telefonoContactMedia.matches("3"+"\\d{9}")){
				log.debug("Es celular, el contact media será SMS...");
				contactMedia=CONTACT_MEDIA_SMS;
			}/*else if(telefonoContactMedia.matches("\\d{7}||\\d{8}||\\d{9}")){
				log.debug("Es un fijo de 7,8 o 9 dígitos");
				contactMedia=CONTACT_MEDIA_TELEFONO;
			}else if(telefonoContactMedia.matches("[^3]\\d{9}")){
				log.debug("Es un fijo de 10 caracteres sin 3 al comienzo porque sería celular...");
				contactMedia=CONTACT_MEDIA_TELEFONO;
			}else{
				log.debug("No es ni fijo ni celular. Se asigna el indicador de Fijo");
				contactMedia=CONTACT_MEDIA_TELEFONO;
			}*/
			
			tr701EContactData.setContactMedia(contactMedia);
			
			if(peticion_stLocal.getNom_psn_cot_no()!=null&&!peticion_stLocal.getNom_psn_cot_no().equals("")){
				tr701EContactData.setContactName(peticion_stLocal.getNom_psn_cot_no());
			}else{
				tr701EContactData.setContactName(peticion_stLocal.getNom_rte_sn());
			}
			
			//DAVID: Enero 18 2011, modificaciones para el telefon ode contacto.
			
//			String contactPhone="0";
//			if(peticion_stLocal.getTel_cot_ds()!=null&&!peticion_stLocal.getTel_cot_ds().equals("")){
//				contactPhone=peticion_stLocal.getTel_cot_ds();
//				contactPhone=contactPhone.replaceAll("[^\\d]","");//Se quitan los caractéres no numéricos.			
//				if(contactPhone.equals("")){
//					contactPhone="0";
//				}
//				log.debug("El teléfono de contacto sin letras es: "+contactPhone);
//			}
			
			tr701EContactData.setContactPhone(telefonoContactoSinLetras);
			
			//Fin, DAVID: Enero 18 2011, modificaciones para el telefon ode contacto.
			
			//Estos campos de dependence no son necesarios en atiempo.
			TR701ECoordinatedActions tr701ECoordinatedActions = new TR701ECoordinatedActions();
			tr701ECoordinatedActions.setDependeceId(new Long(0));
			tr701ECoordinatedActions.setDependenceType("");
			
			TR701ECustomer tr701ECustomer = new TR701ECustomer();
			tr701ECustomer.setCode(new Long(0));
			
			if(peticion_stLocal.getNum_doc_rte_nu()!=null && peticion_stLocal.getNum_doc_rte_nu().length()>0){
				tr701ECustomer.setCode(new Long(peticion_stLocal.getNum_doc_rte_nu()));
				tr701ECustomer.setId(peticion_stLocal.getNum_doc_rte_nu());
			}else{
				tr701ECustomer.setCode(new Long(0));
				tr701ECustomer.setId("0");
			}
			tr701ECustomer.setEmail(emailCaracteristicas);
			tr701ECustomer.setIdType(peticion_stLocal.getTip_doc_rte_cd());
			tr701ECustomer.setName(peticion_stLocal.getNom_rte_sn());
			
			
			//DAVID: Enero 18 2011, modificaciones para el telefon ode contacto.
			//tr701ECustomer.setPhoneNumber(new Long(0));
			
//			String phoneNumber="0";
//			if(peticion_stLocal.getTel_cot_ds()!=null&&!peticion_stLocal.getTel_cot_ds().equals("")){
//				phoneNumber=peticion_stLocal.getTel_cot_ds();
//				phoneNumber=phoneNumber.replaceAll("[^\\d]","");//Se quitan los caractéres no numéricos.			
//				if(phoneNumber.equals("")){
//					phoneNumber="0";
//				}
//				log.debug("El número de teléfono sin letras es: "+phoneNumber);
//			}
			//if(peticion_stLocal.getTel_cot_ds()!=null){
			tr701ECustomer.setPhoneNumber(new Long(telefonoContactoSinLetras));
			//}
			//Fin, DAVID: Enero 18 2011, modificaciones para el telefon ode contacto.
			
			tr701ECustomer.setSubSeg(peticion_stLocal.getCod_sbg_cta_cd().toString());
			
			SubsegmentoKey subsegmentoKey = new SubsegmentoKey(peticion_stLocal.getCod_sbg_cta_cd());
			SubsegmentoLocal subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
			tr701ECustomer.setSubSeg(subsegmentoLocal.getDescripcion());           
			
			SegmentoKey segmentoKey = new SegmentoKey(peticion_stLocal.getCod_sgm_cta_cd());
			SegmentoLocal segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
			
			String descripcionSegmento=segmentoLocal.getSegm_descripcion();
			if(peticion_stLocal.getCod_ctz_cd()!=null && peticion_stLocal.getCod_ctz_cd().equalsIgnoreCase("R")){
				tr701ECustomer.setType(descripcionSegmento+"_"+peticion_stLocal.getCod_ctz_cd());	
			}else if(peticion_stLocal.getCod_pra_ave_cd()!=null && peticion_stLocal.getCod_pra_ave_cd().equalsIgnoreCase("5")){
				tr701ECustomer.setType(descripcionSegmento+"_S");
			}else{
				tr701ECustomer.setType(descripcionSegmento);
			}
			
			
			TR701EDateData tr701EDateData = new TR701EDateData();
			
			//La fecha de compromiso y de inicio de la avería.
			//Se ajusta para enviar el formato requerido por agenda ej:20101023152400
			
			tr701EDateData.setBreakdownCommitmentDate("");//formatter.format(peticion_stLocal.getTim_fec_cps_ts())
			tr701EDateData.setBreakdownDate(formatter.format(peticion_stLocal.getFec_apt_ave_ts()));
			
			TR701EMassiveBreakdowns tr701EMassiveBreakdowns=new TR701EMassiveBreakdowns();
			tr701EMassiveBreakdowns.setBreakdownMassiveType(peticion_stLocal.getCod_ave_mas_cd().toString());
			tr701EMassiveBreakdowns.setFinalRange("");
			tr701EMassiveBreakdowns.setInitialRange("");
			
			/*Logica para armar el codigo de apertura con su descripcion*/
			String codigoApertura = new String("");
			codigoApertura += peticion_stLocal.getCod_apt_ave_cd()+"[";
			try{
				Collection codigosST = codigoSTLocalHome.findByCodigo(peticion_stLocal.getCod_apt_ave_cd());
			
				if(codigosST != null){
					Codigo_stLocal codigoSt = null;
					for(Iterator i = codigosST.iterator();i.hasNext();){
						codigoSt = (Codigo_stLocal)i.next();
						break;
					}
					if(codigoSt != null){
						codigoApertura += codigoSt.getDescripcion()+"]";
					}else{
						codigoApertura += "]";
					}
				}else{
					codigoApertura += "]";
				}								
			}catch(FinderException e){
				log.debug("No encontró ningún Codigo_ST asociado al codigo: "+peticion_stLocal.getCod_apt_ave_cd());
				codigoApertura += "]";	
			}
			
			/*Logica para obtener los objetos que contienen el diagnostico y la observacion correspondiente al diagnostico de segundo nivel*/
			Prueba_lineaLocal pruebaLineaLocal = null;
			Catalago_prueba_lineaLocal catalagoPruebaLineaLocal = null;
			try{
				pruebaLineaLocal = prueba_lineaLocalHome.findByMaxFecha(codAve);
			
				if(pruebaLineaLocal != null){
					catalagoPruebaLineaLocal = pruebaLineaLocal.getCatalago_prueba_linea();
				}
			}catch(FinderException e){
				log.debug("No encontró ningún registro de diagnóstico para enviar en las notas de la TR701E.");
			}
			
			//String notaDescripcion = ComunInterfaces.OBSERVACIONES+peticion_stLocal.getObs_ave_ds()+";";			
			String notaDescripcion = new String("");
			/*codigo_apertura[descripcion_codigo_apertura]*/
			notaDescripcion = notaDescripcion+ComunInterfaces.CODIGO_APERTURA_ST+codigoApertura+".";
			
			/*observaciones_apertura*/
			notaDescripcion = notaDescripcion+ComunInterfaces.OBSERVACION_APERTURA_ST+peticion_stLocal.getObs_ave_ds()+".";
			
			/*codigo_diagnostico_primer_nivel[descripcion]*/
			notaDescripcion = notaDescripcion + ComunInterfaces.DIAGNOSTICO_PRIMER_NIVEL_ST + peticion_stLocal.getRpt_pru_ave_cd() + "[";
			if(peticion_stLocal.getDsc_rpt_pru_ds() != null)
				notaDescripcion = notaDescripcion + peticion_stLocal.getDsc_rpt_pru_ds() + "].";
			else
				notaDescripcion = notaDescripcion + "].";
			
			/*codigo_diagnostico_segundo_nivel[descripcion]*/
			notaDescripcion = notaDescripcion + ComunInterfaces.DIAGNOSTICO_SEGUNDO_NIVEL_ST;
			if(catalagoPruebaLineaLocal != null){
				
				Catalago_prueba_lineaKey catalagoPruebaLineaKey = (Catalago_prueba_lineaKey) catalagoPruebaLineaLocal.getPrimaryKey();
				notaDescripcion = notaDescripcion + catalagoPruebaLineaKey.cod_prueba.toString() + "[";
				
				if(catalagoPruebaLineaLocal.getDescripcion() != null){
					notaDescripcion = notaDescripcion + catalagoPruebaLineaLocal.getDescripcion() + "].";
				}else{
					notaDescripcion = notaDescripcion + "].";
				}
			}else{
				notaDescripcion = notaDescripcion+".";
			}
			
			/*observaciones_diagnostico_segundo_nivel*/
			notaDescripcion = notaDescripcion + ComunInterfaces.OBS_DIAGNOSTICO_SEG_NIVEL_ST;
			if(pruebaLineaLocal != null && pruebaLineaLocal.getObservacion() != null){
				notaDescripcion = notaDescripcion + pruebaLineaLocal.getObservacion() + ".";
			}else{
				notaDescripcion = notaDescripcion + ".";
			}
			
			/*categoria_averia*/
			notaDescripcion = notaDescripcion + ComunInterfaces.CATEGORIA_AVERIA_ST;
			if(peticion_stLocal.getCod_ctz_cd() != null && !peticion_stLocal.getCod_ctz_cd().equals("")){
				try{
					notaDescripcion = notaDescripcion + peticion_stLocal.getCod_ctz_cd() + "[";
					
					Codigo_stLocal codigoStLocal = codigoSTLocalHome.findByTipoAndAtributo(new Integer(4), peticion_stLocal.getCod_ctz_cd());
					if(codigoStLocal != null && codigoStLocal.getDescripcion() != null){
						notaDescripcion = notaDescripcion + codigoStLocal.getDescripcion() + "].";
						descripcion = codigoStLocal.getDescripcion();
					}else{
						notaDescripcion = notaDescripcion + "].";
					}
				}catch (Exception e) {
					log.debug("Error nota tr 701 e"+e.getMessage());
					notaDescripcion = notaDescripcion + "].";
				}
			}else{
				notaDescripcion = notaDescripcion + ".";
			}
			
			/*codigo_prioridad*/
			notaDescripcion = notaDescripcion + ComunInterfaces.CODIGO_PRIORIDAD_ST;
			if(peticion_stLocal.getCod_pra_ave_cd() != null){
				notaDescripcion = notaDescripcion + peticion_stLocal.getCod_pra_ave_cd() + ".";
			}else{
				notaDescripcion = notaDescripcion + ".";
			}
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = null;
			TematicoLocalHome tematicoLocalHome = (TematicoLocalHome) HomeFactory.getHome(TematicoLocalHome.JNDI_NAME);
			TematicoLocal tematicoLocal = null;
			
			if(peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.TV)){
				notaDescripcion = notaDescripcion + ComunInterfaces.CODIGO_PS;
				if(peticion_stLocal.getCod_pro_ser_cd()!= null){
					producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(peticion_stLocal.getCod_pro_ser_cd()));
					notaDescripcion = notaDescripcion + producto_servicioLocal.getPs_nombre() + ".";
					Collection tematicos = tematicoLocalHome.findByPeticion(codAve);
					Iterator iterTem =  tematicos.iterator();
					notaDescripcion = notaDescripcion + ComunInterfaces.CODIGO_PAQUETES;
					while(iterTem.hasNext()){
						tematicoLocal = (TematicoLocal) iterTem.next();
						notaDescripcion = notaDescripcion + "," + tematicoLocal.getCod_tematico();
					}					
				}
			}
			
			//Cesar Remigio (Indra): Se valida si la solicitud es una solución en Planta Externa.
			String area_gestion=""+peticion_stLocal.getCod_are_ges_cd();
			String ventaEquipos = ""+peticion_stLocal.getIde_pro_cmr_cd();
			if(area_gestion.equals("17")&& ventaEquipos.equals("IT")){
				log.debug("Se valida el campo de Num_seg_lin_ds: "+peticion_stLocal.getNum_seg_lin_ds());
				if(peticion_stLocal.getNum_seg_lin_ds()!= null){
					notaDescripcion = notaDescripcion + "característica equipo: "+ peticion_stLocal.getNum_seg_lin_ds();
					log.debug("Se asigna campo Num_seg_lin_ds: "+peticion_stLocal.getNum_seg_lin_ds());
				}else{
					log.debug("El campo Num_seg_lin_ds es: "+peticion_stLocal.getNum_seg_lin_ds());
				}
				
			}
			
//			else if(peticion_stLocal != null && peticion_stLocal.getDsc_rpt_pru_ds() != null){
//				notaDescripcion = notaDescripcion+ComunInterfaces.DIAGNOSTICO_ST+peticion_stLocal.getDsc_rpt_pru_ds()+";";
//			}else if(codigoApertura != null && !codigoApertura.equals("")){
//				notaDescripcion = notaDescripcion+ComunInterfaces.DIAGNOSTICO_ST+codigoApertura+";";
//			}else{
//				notaDescripcion = notaDescripcion+ComunInterfaces.DIAGNOSTICO_ST+"Sin diagnóstico."+";";
//			}
			TR701ENotes tr701ENotes=new TR701ENotes();
			tr701ENotes.setNoteDescription(notaDescripcion);
			tr701ENotes.setNoteDetail("");
			tr701ENotes.setNoteType("ACT-COMENT");
			
			TR701EProduct tr701EProduct=new TR701EProduct();
			tr701EProduct.setAtisRequestNumber(codAve.toString());
			tr701EProduct.setProductCode(new Long(0));//ME FALTA ESTE, también está en el catálogo de agenda.     

			//Yumbleiner CR 6842 Agregar IdPCTV en la tr-701
			tr701EProduct.setIdPcTv("0");
			if(peticion_stLocal.getNum_ide_nu_tv()!=null && peticion_stLocal.getNum_ide_nu_tv().length()>0){
				tr701EProduct.setIdPcTv(peticion_stLocal.getNum_ide_nu_tv());
			}
			
			
			
			TR701ETechnicalData tr701ETechnicalData = new TR701ETechnicalData();
			//Req 2012 - 00015182 Jorge Roa
			tr701ETechnicalData.setNomInterlocutor(peticion_stLocal.getNom_psn_cot_no());
			tr701ETechnicalData.setTelInterlocutor(peticion_stLocal.getTel_cot_ds());
			tr701ETechnicalData.setTel1Contacto(peticion_stLocal.getSeg_tel_cot_sn());
			tr701ETechnicalData.setTel2Contacto("0");
			tr701ETechnicalData.setTel3Contacto("0");
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
			
			try{
				Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioKey productoServicioKey = new Producto_servicioKey(peticion_stLocal.getCod_pro_ser_cd());
				Producto_servicioLocal productoServicioLocal = productoServicioLocalHome.findByPrimaryKey(productoServicioKey);
							
				if (productoServicioLocal.getVelocidad() != null && productoServicioLocal.getVelocidad().length() > 0){
					tr701ETechnicalData.setSpeed(productoServicioLocal.getVelocidad());
				}else{
					tr701ETechnicalData.setSpeed("");
				}
				
			}catch(FinderException ex){
				log.debug("No se encontro información relacionada con la velocidad del PS, se setea valor vacío");
				tr701ETechnicalData.setSpeed("");
			}
			
			tr701ETechnicalData.setStrip("");
			tr701ETechnicalData.setStripPair("");
			tr701ETechnicalData.setSubrack("");
			tr701ETechnicalData.setUserAccess("");
			tr701ETechnicalData.setVpiVci("");
			tr701ETechnicalData.setVpiVciNetwork("");
			tr701ETechnicalData.setZone("");
			
			if(rba!=null){
				if(rba.getAdsl_actual()!=null){
					String adslAux[] = rba.getAdsl_actual().split("-");
					tr701ETechnicalData.setAdsl(rba.getAdsl_actual());
					tr701ETechnicalData.setRack(adslAux[0]);
				}else{
					tr701ETechnicalData.setAdsl("");
				}if(rba.getSlot_actual()!=null){
					tr701ETechnicalData.setCard(rba.getSlot_actual());
				}else{
					tr701ETechnicalData.setCard("");
				}if(rba.getFrame_actual()!=null){
					tr701ETechnicalData.setFrame(rba.getFrame_actual());
					tr701ETechnicalData.setSubrack(rba.getFrame_actual());
				}else{
					tr701ETechnicalData.setFrame("");
				}if(rba.getDir_ip_dslam_actual()!=null){
					tr701ETechnicalData.setIpDslam(rba.getDir_ip_dslam_actual());
				}else{
					tr701ETechnicalData.setIpDslam("");
				}if(rba.getDir_ip_lan_actual()!=null){
					tr701ETechnicalData.setIpLan(rba.getDir_ip_lan_actual());
				}else{
					tr701ETechnicalData.setIpLan("");
				}if(rba.getMasc_lan_actual()!=null){
					tr701ETechnicalData.setIpLanMask(rba.getMasc_lan_actual());
				}else{
					tr701ETechnicalData.setIpLanMask("");
				}if(rba.getDir_ip_wan_actual()!=null){
					tr701ETechnicalData.setIpWan(rba.getDir_ip_wan_actual());
				}else{
					tr701ETechnicalData.setIpWan("");
				}if(rba.getPuerto_actual()!=null){
					tr701ETechnicalData.setPortId(rba.getPuerto_actual());
				}else{
					tr701ETechnicalData.setPortId("");
				}if(rba.getPost_actual()!=null){
					tr701ETechnicalData.setPots(rba.getPost_actual());
				}else{
					tr701ETechnicalData.setPots("");
				}if(rba.getFather_email_actual()!=null){
					tr701ETechnicalData.setUserAccess(rba.getFather_email_actual());
				}
			    else{
				   tr701ETechnicalData.setUserAccess("");
				}
				if(rba.getVpivci_actual()!=null){
					tr701ETechnicalData.setVpiVci(rba.getVpivci_actual());
				}else{
					tr701ETechnicalData.setVpiVci("");
				}if(rba.getVpivci_red_actual()!=null){
					tr701ETechnicalData.setVpiVciNetwork(rba.getVpivci_red_actual());
				}else{
					tr701ETechnicalData.setVpiVciNetwork("");
				}
			}if(rlb!=null){
				if(rlb.getCaja()!=null){
					tr701ETechnicalData.setBox(rlb.getCaja());
				}else{
					tr701ETechnicalData.setBox("");
				}if(rlb.getDir_caja()!=null){
					tr701ETechnicalData.setBoxAddress(rlb.getDir_caja());
				}else{
					tr701ETechnicalData.setBoxAddress("");
				}/*if(rlb.getDistancia_caja()!=null){
				tr701ETechnicalData.setBoxDistance(""+rlb.getDistancia_caja());
				}else{
				tr701ETechnicalData.setBoxDistance("");
				}*/if(rlb.getPar_caja()!=null){
					tr701ETechnicalData.setBoxPair(""+rlb.getPar_caja());
				}else{
					tr701ETechnicalData.setBoxPair("");
				}/*if(rlb.getTipo_caja()!=null){
				tr701ETechnicalData.setBoxType(""+rlb.getTipo_caja());
				}else{
				tr701ETechnicalData.setBoxType("");
				}*/if(rlb.getCable()!=null){
					tr701ETechnicalData.setCable(""+rlb.getCable());
				}else{
					tr701ETechnicalData.setCable("");
				}if(rlb.getPar_cable()!=null){
					tr701ETechnicalData.setCablePair(""+rlb.getPar_cable());
				}else{
					tr701ETechnicalData.setCablePair("");
				}if(rlb.getCentral()!=null && rlb.getCentral().intValue()!=0){
					tr701ETechnicalData.setCentralCode(""+rlb.getCentral());
				}else{
					tr701ETechnicalData.setCentralCode("");
					noTieneCentral = true;
				}if(rlb.getArmario()!=null){
					tr701ETechnicalData.setCloset(rlb.getArmario());
				}else{
					tr701ETechnicalData.setCloset("");
				}if(rlb.getDir_armario()!=null){
					tr701ETechnicalData.setClosetAddress(rlb.getDir_armario());
				}else{
					tr701ETechnicalData.setClosetAddress("");
				}if(rlb.getDir_distribuidor()!=null){
					tr701ETechnicalData.setDistributorAddress(rlb.getDir_distribuidor());
				}else{
					tr701ETechnicalData.setDistributorAddress("");
				}if(rlb.getDist_prim()!=null){
					tr701ETechnicalData.setDistributorCode(""+rlb.getDist_prim());
				}else{
					tr701ETechnicalData.setDistributorCode("");
				}if(rlb.getDesc_dist_prim()!=null){
					tr701ETechnicalData.setDistributorDescription(rlb.getDesc_dist_prim());
				}else{
					tr701ETechnicalData.setDistributorDescription("");
				}if(rlb.getPosicion_horizontal()!=null){
					tr701ETechnicalData.setHorizontalPosicion(rlb.getPosicion_horizontal());
				}else{
					tr701ETechnicalData.setHorizontalPosicion("");
				}/*if(rlb.getLatitud()!=null){
				tr701ETechnicalData.setLatitude(""+rlb.getLatitud());
				}else{
				tr701ETechnicalData.setLatitude("");
				}*/if(rlb.getLen()!=null){
					tr701ETechnicalData.setLen(rlb.getLen());
				}else{
					tr701ETechnicalData.setLen("");
				}/*if(rlb.getLongitud()!=null){
				tr701ETechnicalData.setLongitude(""+rlb.getLongitud());
				}else{
				tr701ETechnicalData.setLongitude("");
				}*/if(rlb.getTelefono_asignado()!=null){
					tr701ETechnicalData.setPhoneNumber(""+rlb.getTelefono_asignado());
				}else{
					tr701ETechnicalData.setPhoneNumber("");
				}if(rlb.getListon()!=null){
					tr701ETechnicalData.setStrip(rlb.getListon());
				}else{
					tr701ETechnicalData.setStrip("");
				}if(rlb.getPar_liston()!=null){
					tr701ETechnicalData.setStripPair(""+rlb.getPar_liston());
				}else{
					tr701ETechnicalData.setStripPair("");
				}if(rlb.getZonas_atendimiento()!=null&&rlb.getZonas_atendimiento().size()>0){
					Zonas_atendimientoLocal zonas_atendimientoLocal = (Zonas_atendimientoLocal) rlb.getZonas_atendimiento().iterator().next();
					Zonas_atendimientoKey zonas_atendimientoKey = (Zonas_atendimientoKey)zonas_atendimientoLocal.getPrimaryKey();
					tr701ETechnicalData.setZone(""+zonas_atendimientoKey.id);
				}
				else{
					tr701ETechnicalData.setZone("");
				}
			}else{
				if(descFamilia.equals(ComunInterfaces.BA)||descFamilia.equals(ComunInterfaces.LB)){
					noTieneCentral = true;
				}
			}
			
			tr701e.setAccessTime(tr701EAccessTime);
//			String descFamilia="";
//			if(peticion_stLocal.getIde_pro_cmr_cd().equals("L")){
//				descFamilia="LB";//Se utiliza esto porque agenda pide LB y en soltec Línea Básica se registra como L.
//			}else{
//				descFamilia=peticion_stLocal.getIde_pro_cmr_cd();
//			}
			
			
			//REQ AVERIAS DE INFACIA @DCARDENA 17/10/2014 
			//funcion que valida si la averia actual es reiterada o de infancia, se retorna true cuando es reiterada o de infancia en caso contrario false
			//se retorna true si la localidad y el segmento y subsegmento corresponden y no contiene lo flag de Reiterada e infancia en S
			boolean  hayFlagVIP = validaFlagVIP(peticion_stLocal,codAve);
			//se valida si la averia es de infancia o reiterada
			if(hayFlagVIP)
			{
				// como la averia es de infancia o reiterada se agrega al action name de la averia _VIP para que se notifique que se necesita un tecnico VIP
				// ejemplo LB_VIP, TV_VIP, BA_VIP
				descFamilia=descFamilia+"_VIP";
			}
			//en caso que llegue false se manda la familia tal cual como siempre se manda
			tr701e.setActionName(descFamilia);//Es la familia o grupo de familias (LBBA,BA,TV,etc) 
			// FIN REQ AVERIAS DE INFANCIA Y REITERADAS
			tr701e.setAdressData(tr701EAdressData);
			tr701e.setAffectedArea("20");//Si es planta interna o externa, Se pone 20 por ahora (planta interna)
			tr701e.setCodeAction("");//Código tipo tarea, depende de catálogo no definido todavía
			tr701e.setCodeScheduleType("IT");//Código tipo actuación, antes iba peticion_stLocal.getCod_est_ave_cd()
			tr701e.setContactData(tr701EContactData);
			tr701e.setCoordinatedActions(tr701ECoordinatedActions);
			tr701e.setCustomer(tr701ECustomer);
			tr701e.setDateData(tr701EDateData);
			tr701e.setDescriptionAction("");//Inicialmente vacío
			tr701e.setDestination("ESB");
			
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			tr701e.setId(IdCorrelativo.toString());
			
			Date dateAhora=new Date();
			Timestamp timestampAhora=new Timestamp(dateAhora.getTime());
			
			tr701e.setIdSchedule("AI"+codAve.toString()+"-"+formatter.format(dateAhora));
			tr701e.setIdSystemOrigin(sistemaAtiempoSt);
			tr701e.setInterfaz("ACT_ALTA");
			tr701e.setMassiveBreakdowns(tr701EMassiveBreakdowns);
			tr701e.setNotes(tr701ENotes);
			tr701e.setProduct(tr701EProduct);
			tr701e.setScheduleDate(formatter.format(new Date()));
			
			//Raúl Triviño: 01072011 - Adición de la severidad
			tr701e.setSeverity(tr701ECustomer.getType());
					
			tr701e.setSource(sistemaAtiempoSt);
			
			String descripcionCatalogo="";
			try{
				Prueba_lineaLocal prueba_lineaLocal=prueba_lineaLocalHome.findByMaxFecha(codAve);
				if(prueba_lineaLocal!=null){
					Catalago_prueba_lineaLocal catalago_prueba_lineaLocal=prueba_lineaLocal.getCatalago_prueba_linea();
					descripcionCatalogo=catalago_prueba_lineaLocal.getDescripcion();
				}
			}catch(FinderException e){
				log.debug("No encontró ningún registro de diagnóstico: "+e.toString());
			}
			//Nov 13, perdí cita médica por esto, si no viene diagnóstico va la descripción de la 
			//petición, si no hay descripción se pone sin descripción concatenado con el código de la petición.
			if(descripcionCatalogo.equals("")){
				if(peticion_stLocal.getCod_cie_ave_cd()!=null&&!peticion_stLocal.getCod_cie_ave_cd().equals("")){
					descripcionCatalogo="SIN DIAGNOSTICO: "+peticion_stLocal.getCod_cie_ave_cd();
				}                                              
			}
			if(descripcionCatalogo.equals("")){
				if(peticion_stLocal.getCod_apt_ave_cd()!=null&&!peticion_stLocal.getCod_apt_ave_cd().equals("")){
					String codigoAp=peticion_stLocal.getCod_apt_ave_cd();
					descripcionCatalogo="SIN DIAGNOSTICO: "+codigoAp;
					try{
						Collection codigosStList = codigo_stLocalHome.findByCodigo(codigoAp);
						if(codigosStList!=null&&codigosStList.size()>0){
							Codigo_stLocal codigo_stLocal = (Codigo_stLocal)codigosStList.iterator().next();
							descripcionCatalogo="SIN DIAGNOSTICO: "+codigo_stLocal.getDescripcion();
						}                                                                      
					}catch(FinderException e){
						log.debug("El código ST no exíste en BD: "+codigoAp);
					}catch(Exception e){
						log.debug("Error buscando código ST: "+e.toString());
					}
					
				}                                              
			}
			
			if(descripcionCatalogo.length()>240){
				descripcionCatalogo=descripcionCatalogo.substring(0,240);
			}
			log.debug("summary para agenda SC: "+descripcionCatalogo);
			
			tr701e.setSummary(descripcionCatalogo);
			tr701e.setTechnicalData(tr701ETechnicalData);
			tr701e.setUrgency("");
			tr701e.setVersion("1.0");
			
			Collection tr701Equipments=new ArrayList();
			
			Collection decosTarjetas=peticion_stLocal.getDeco_tarjeta();
			Iterator decosTarjetasIt=null;
			for(decosTarjetasIt=decosTarjetas.iterator();decosTarjetasIt.hasNext();){
				Deco_tarjetaLocal deco_tarjetaLocal = (Deco_tarjetaLocal)decosTarjetasIt.next();
				Deco_tarjetaKey deco_tarjetaKey = (Deco_tarjetaKey)deco_tarjetaLocal.getPrimaryKey();
				TR701EEquipment equipment = new TR701EEquipment();
				
				equipment.setSerialNumber(deco_tarjetaLocal.getSerial_deco());
				equipment.setBrand(deco_tarjetaLocal.getDeco_marca());
				if (deco_tarjetaLocal.getDeco_reference() != null && deco_tarjetaLocal.getDeco_reference().length()>0){
					equipment.setModel(deco_tarjetaLocal.getDeco_reference());					
				}else{
					equipment.setModel("");
				}
				
				if (desHCDecoSTD.equalsIgnoreCase(deco_tarjetaLocal.getDeco_reference())){
					equipment.setPartType(ComunInterfaces.DECODTHSTD);
				}else if (desHCDecoPVR.equalsIgnoreCase(deco_tarjetaLocal.getDeco_reference())){
					equipment.setPartType(ComunInterfaces.DECODTHPVR);
				}else if (desHCDecoHDTV.equalsIgnoreCase(deco_tarjetaLocal.getDeco_reference())){
					equipment.setPartType(ComunInterfaces.DECOHD);
				}
				
				equipment.setCassId(deco_tarjetaKey.id_deco);
				equipment.setCardSerialNumber(deco_tarjetaKey.id_tarjeta);
				equipment.setInventoryType("");
				equipment.setType("");
				equipment.setId(ComunInterfaces.ID_DECO_TARJETA);
				/*RQ.8595 - mfmendez*/
				equipment = this.setDatosSAPDecoTarjeta(deco_tar_inf_sapLocalHome, equipment, deco_tarjetaKey.id_deco, deco_tarjetaKey.id_tarjeta, codAve);
				
				tr701Equipments.add(equipment);
			}
			
			Collection modems=getModemSt(codAve);
			Iterator modemsIt=null;
			for(modemsIt=modems.iterator();modemsIt.hasNext();){
				ModemSTDTO modemSTDTO = (ModemSTDTO)modemsIt.next();
				
				TR701EEquipment equipment = new TR701EEquipment();
				equipment.setSerialNumber(modemSTDTO.getSerial());
				equipment.setBrand(modemSTDTO.getMarca());
				if (modemSTDTO.getModelo() != null && modemSTDTO.getModelo().length()>0){
					equipment.setModel(modemSTDTO.getModelo());
				}else{
					equipment.setModel("");
				}
				
				if (modemSTDTO.getTipo() != null && modemSTDTO.getTipo().intValue()==ComunInterfaces.identificadorWiFi){
					equipment.setPartType(ComunInterfaces.MODEM_WIFI);
				}else{
					equipment.setPartType(ComunInterfaces.MODEM_STD);
				}
				
				equipment.setCassId("");
				equipment.setCardSerialNumber("");
				equipment.setInventoryType("");
				equipment.setType("");
				equipment.setId(ComunInterfaces.ID_MODEM);
				
				/*RQ.8595 - mfmendez*/
				// datos del modem
				equipment.setPostingDateSAP(modemSTDTO.getPostingDateSAP());
				equipment.setMoveTypeSAP(modemSTDTO.getMoveTypeSAP());
				equipment.setMaterialCodeSAP(modemSTDTO.getMaterialCodeSAP());
				equipment.setMaterialSAP(modemSTDTO.getMaterialSAP());
				equipment.setPlantSAP(modemSTDTO.getPlantSAP());
				equipment.setStorageSAP(modemSTDTO.getStorageSAP());
				equipment.setBatchCodeSAP(modemSTDTO.getBatchCodeSAP());
				equipment.setMeasurementUnitSAP(modemSTDTO.getMeasurementUnitSAP());
				equipment.setCostCenterSAP(modemSTDTO.getCostCenterSAP());
				equipment.setFuncAreaLongSAP(modemSTDTO.getFuncAreaLongSAP());
				equipment.setPepElementSAP(modemSTDTO.getPepElementSAP());
				equipment.setFlagMatSAP(modemSTDTO.getFlagMatSAP());
				// Datos de la tarjeta vacios
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
				/*FIN - RQ.8595 - mfmendez*/
				
				tr701Equipments.add(equipment);
			}
			
			Collection elementoPeticionList = peticion_stLocal.getElemento_peticion();
			Iterator elementoPeticionIt=null;
			for (elementoPeticionIt = elementoPeticionList.iterator(); elementoPeticionIt.hasNext();){
				Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)elementoPeticionIt.next();                                     
				TR701EEquipment equipment = new TR701EEquipment();
				equipment.setSerialNumber(elemento_PeticionLocal.getSerial());
				equipment.setBrand("");
				equipment.setModel("");
				
				try{
					Ps_Tipo_EqKey psTipoEquipoKey = new Ps_Tipo_EqKey(new Integer(elemento_PeticionLocal.getPs_id().toString()),new Integer(elemento_PeticionLocal.getTipo_elemento().toString()));
					Ps_Tipo_EqLocal psTipoEquipoLocal = psTipoEqLocalHome.findByPrimaryKey(psTipoEquipoKey);
				
					Elemento_agenda_scKey elementoAgendaSCKey = new Elemento_agenda_scKey(psTipoEquipoLocal.getId_elemento_agenda());
					
					if (elementoAgendaSCKey.id_correlativo != null){
						Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSCLocalHome.findByPrimaryKey(elementoAgendaSCKey);
						equipment.setPartType(elementoAgendaSCLocal.getDesc_equipo());
					}else{
						log.debug("No existe un equipo de agenda asociado al ps:"+elemento_PeticionLocal.getPs_id()+" y tipo de elemento:"+elemento_PeticionLocal.getTipo_elemento());
						equipment.setPartType("");
					}
					
					
				}catch(NullPointerException ex){
					log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio:"+ex);
					equipment.setPartType("");
				}catch(Exception ex){
					log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio");
					equipment.setPartType("");
				}
				
				equipment.setCassId("");
				equipment.setCardSerialNumber("");
				equipment.setInventoryType(elemento_PeticionLocal.getTipo_inventario());
				
				ElementoLocal elementoLocal = elementoLocalHome.findElemento(elemento_PeticionLocal.getTipo_elemento().longValue());
				equipment.setType(elementoLocal.getDesc_elemento());
				
				equipment.setId(ComunInterfaces.ID_OTROS); 
				
				/*RQ.8595 - mfmendez*/
				// datos del equipo
				if(elemento_PeticionLocal.getFec_cont_sap() != null)
					equipment.setPostingDateSAP(elemento_PeticionLocal.getFec_cont_sap());
				else
					equipment.setPostingDateSAP("");
				
				if(elemento_PeticionLocal.getClase_mov_sap() != null)
					equipment.setMoveTypeSAP(elemento_PeticionLocal.getClase_mov_sap());
				else
					equipment.setMoveTypeSAP("");
				
				equipment.setMaterialCodeSAP(Integer.toString(elemento_PeticionLocal.getPos_doc_sap()));
				
				if(elemento_PeticionLocal.getNum_material_sap() != null)
					equipment.setMaterialSAP(elemento_PeticionLocal.getNum_material_sap());
				else
					equipment.setMaterialSAP("");
					
				if(elemento_PeticionLocal.getCentro_sap() != null)
					equipment.setPlantSAP(elemento_PeticionLocal.getCentro_sap());
				else
					equipment.setPlantSAP("");
				
				if(elemento_PeticionLocal.getAlmacen_sap() != null)
					equipment.setStorageSAP(elemento_PeticionLocal.getAlmacen_sap());
				else
					equipment.setStorageSAP("");
				
				if(elemento_PeticionLocal.getCod_lote_sap() != null)
					equipment.setBatchCodeSAP(elemento_PeticionLocal.getCod_lote_sap());
				else
					equipment.setBatchCodeSAP("");
				
				if(elemento_PeticionLocal.getUnd_medida_sap() != null)
					equipment.setMeasurementUnitSAP(elemento_PeticionLocal.getUnd_medida_sap());
				else
					equipment.setMeasurementUnitSAP("");
				
				if(elemento_PeticionLocal.getCentr_cost_sap() != null)
					equipment.setCostCenterSAP(elemento_PeticionLocal.getCentr_cost_sap());
				else
					equipment.setCostCenterSAP("");
				
				if(elemento_PeticionLocal.getArea_func_sap() != null)
					equipment.setFuncAreaLongSAP(elemento_PeticionLocal.getArea_func_sap());
				else
					equipment.setFuncAreaLongSAP("");
				
				if(elemento_PeticionLocal.getElement_pep_sap() != null)
					equipment.setPepElementSAP(elemento_PeticionLocal.getElement_pep_sap());
				else
					equipment.setPepElementSAP("");
				
				if(elemento_PeticionLocal.getFlag_mat_sap() != null)
					equipment.setFlagMatSAP(elemento_PeticionLocal.getFlag_mat_sap());
				else
					equipment.setFlagMatSAP("");
				
				// Datos de la tarjeta vacios
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
				/*FIN - RQ.8595 - mfmendez*/	
				
				tr701Equipments.add(equipment);
			}
			
//			Para las cámaras
			CamaraLocalHome camaraLH = (CamaraLocalHome)HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
			Collection camaras = camaraLH.findByPeticion(codAve);
			Collection listaEquipos = new ArrayList();
			if(camaras!=null && !camaras.isEmpty()){
				for (Iterator iter = camaras.iterator(); iter.hasNext();) {
					CamaraLocal camara = (CamaraLocal) iter.next();
					TR701EEquipment tr701eEquipment = new TR701EEquipment();
					setValoresCamara(tr701eEquipment,camara);
					tr701Equipments.add(tr701eEquipment);
				}
			}
			Tipo_codigoLocalHome tipo_codigoLocalHome = (Tipo_codigoLocalHome) HomeFactory.getHome(Tipo_codigoLocalHome.JNDI_NAME);
			Tipo_codigoLocal tipo_codigoLocal = tipo_codigoLocalHome.findByAtributo(ComunInterfaces.CATEGORY_CODE);
			Tipo_codigoKey tipo_codigoKey = (Tipo_codigoKey)tipo_codigoLocal.getPrimaryKey();
			tr701e.setBreakdowntype(descripcion);
			
			if (!noTieneCentral){
				tr701e.setEquipments(tr701Equipments);
				AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.create(tr701e.getIdSchedule().toString());
				agendaSCSTLocal.setId_peticion_st(codAve);
				agendaSCSTLocal.setEstado(new Integer(ACTUACION_ABIERTA));
				agendaSCSTLocal.setPeticion_st(peticion_stLocal);
				Date hoy=new Date();
				agendaSCSTLocal.setFecha_ingreso(new Timestamp(hoy.getTime()));
				
				CreaActuacionSCSTMQ creaActuacionSCSTMQ = new CreaActuacionSCSTMQ();
				creaActuacionSCSTMQ.enviarMensaje(tr701e);
			}else{
				log.debug("No se envía mensaje de creación de agenda porque no existe central, quedo en espera de un nuevo mensaje");
				
				//IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBaLocal.getCod_actividad_generadora());
			
				if (act == null){
					act = recuperaActividadDeBandejaIntegrada(peticion_stLocal);
				}
				ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(act.getCodigoActividad());
				
				act.setObservacion("No se envía mensaje de creación de agenda porque no existe central, quedo en espera de un nuevo mensaje");
				actividadEJB.grabarSinTerminar(act);
			}
		}
		catch (Exception e) {
			log.error("Error  ",e);
		}
		
	}
	
	//	REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 23/07/2014
	//funcion que retorna un booleano, true cuando la averia sea de atencion VIP en caso contrario false
	public boolean validaFlagVIP(Peticion_stLocal peticion_stLocal, Long codAve) throws NamingException{
		//importar los JNDI de los localHome
		Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);	
		Peticiones_VIPLocalHome peticiones_VIPLocalHome = (Peticiones_VIPLocalHome)HomeFactory.getHome(Peticiones_VIPLocalHome.JNDI_NAME);
		Peticiones_VIPLocal peticiones_VIPLocal=null;
		//try que cumple con manejar un finderException el cual se dispara cuando no se encuentra resultado alguno o fallo la consulta
		try{
			log.debug("Se comienza a validar los flag de reiteradas e infancia en la tabla peticiones_vip con la localidad"
					+peticion_stLocal.getCod_loc()+" segmento "+peticion_stLocal.getCod_sgm_cta_cd()+" y subSegmento "+peticion_stLocal.getCod_sbg_cta_cd());
			
			// se consulta en la tabla peticiones_vip por la localidad el segmento y el subsgmento para retornar el los flag de reiteradas e infancia
			peticiones_VIPLocal = peticiones_VIPLocalHome.findbyLocSegSUB(peticion_stLocal.getCod_loc(),
					peticion_stLocal.getCod_sgm_cta_cd().toString(),peticion_stLocal.getCod_sbg_cta_cd().toString());
		}catch(Exception e){
			
			try{
				log.debug("No se hallaron registros para la localidad "
						+peticion_stLocal.getCod_loc()+" segmento "+peticion_stLocal.getCod_sgm_cta_cd()+" y subSegmento "+peticion_stLocal.getCod_sbg_cta_cd()+
						" se consultara si el subsegmento esta marcado con *");
					
				peticiones_VIPLocal = peticiones_VIPLocalHome.findByLocSeg(peticion_stLocal.getCod_loc(),
					peticion_stLocal.getCod_sgm_cta_cd().toString());
			}catch (Exception x) {
				
				try{
					log.debug("No se hallaron registros para la localidad "
							+peticion_stLocal.getCod_loc()+" segmento "+peticion_stLocal.getCod_sgm_cta_cd()+" y subSegmento "+peticion_stLocal.getCod_sbg_cta_cd()+
							" se consultara si el subsegmento esta marcado con *");
						
					peticiones_VIPLocal = peticiones_VIPLocalHome.findByLocSub(peticion_stLocal.getCod_loc(),
							peticion_stLocal.getCod_sbg_cta_cd().toString());
					
				}catch (Exception y) {
					try{
						log.debug("No se hallaron registros para la localidad "
								+peticion_stLocal.getCod_loc()+" segmento "+peticion_stLocal.getCod_sgm_cta_cd()+" y subSegmento * se consultara si el segmento esta marcado con *");
							
						peticiones_VIPLocal = peticiones_VIPLocalHome.findByLoc(peticion_stLocal.getCod_loc());
						
					}catch (Exception z) {
	//					en caso de que no se encuentre algun registro o falle la consulta se retorna false ya que no hay flags para esa localidad y segmento
						log.debug("No se encontraron registros en la tabla peticiones_vip o fallo la consulta en esta tabla, para la localidad "
								+peticion_stLocal.getCod_loc()+" segmento * y subSegmento * "+e);
						
						return false;
					}
				}
			}
			
		}
		
		//variable que se retornara y definira sio hay reitero o infancia en la averia
		boolean hayReiteroInfancia=false;
		//se valida que se hallan obtenido algun resultado de la tabla peticiones_vip
		if(peticiones_VIPLocal!=null)
		{
			
			//se obtienen los flag de reitero y de infancia
			String habilitaReitero=peticiones_VIPLocal.getReitero();
			String habilitaInfancia=peticiones_VIPLocal.getInfancia();
			
			log.debug("la localidad contiene los siguientes flags de infancia="+habilitaInfancia+" y de reitero= "+habilitaReitero);
			//se valida que si la infancia y el reitero esta en N se envia que hay VIP ya que se hallo el segmento y el subsegmento de VIP
			if(habilitaInfancia.equals("N")&&habilitaReitero.equals("N"))
			{
				hayReiteroInfancia=true;
			}else{
				//se valida si el flag de reitero es igual a S
				if(habilitaReitero.equalsIgnoreCase("S"))
				{		
					log.debug("El flag de Reitero contiene S se validara si la averias es Reiterada");
					// hay reitero habilitado
					// se valida que el idpcTV contenga datos y que el campo ide_pro_cmr contenga TV para poder validar la averia con TV
					if((peticion_stLocal.getNum_ide_nu_tv()!=null && !peticion_stLocal.getNum_ide_nu_tv().equals(""))
							&& peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.TV)){
						//try que hace manejo de finderException en caso de que falle la consulta o no se retorne ningun elemento
						try{
							log.debug("El idpc de la averia "+codAve+" es de TV= "+peticion_stLocal.getNum_ide_nu_tv());
							// se consulta en la tabla peticion_st si hay averias con ese idpcTV con una fecha maxima y que sean diferentes de la peticion actual
							Peticion_stLocal Peticion_stLocalbyidpcTV =peticion_stLocalHome.findByIdPcTVIDE(peticion_stLocal.getNum_ide_nu_tv(),
									peticion_stLocal.getIde_pro_cmr_cd(),codAve);
							Peticion_stKey pk= (Peticion_stKey) Peticion_stLocalbyidpcTV.getPrimaryKey();
							Long idPeticion = pk.cod_ave_cd;
							// hay averias difenrentes a la actual se valida en esta funcion que la diferencia en la fecha del cierre y la fecha de apeturade 
							//de la averia actual sea menor a 30 dias, si es menor se retorna un true el cual notifica que la averia actual es una averia reiterada
							log.debug("Se va a entrar a valida la si la averia es menor de 30 dias, fecha apertura averia "+codAve+" actual: "
									+peticion_stLocal.getFec_apt_ave_ts()+"fecha de cierre de la ultima averia "+idPeticion+
									" registrada: "+Peticion_stLocalbyidpcTV.getFec_cie_ave_ts());
							hayReiteroInfancia= hayInfaciaReiteradaCalcularFecha(peticion_stLocal.getFec_apt_ave_ts(),Peticion_stLocalbyidpcTV.getFec_cie_ave_ts());
							log.debug("¿la averia actual es reiterada?"+hayReiteroInfancia);
						}catch (Exception e){
							//en caso de que falle la consulta o no se encuentren mas averias para el mismo idpcTV se retorna un false el cual notifica que la a veria
							// no es reiterada
							log.info("No se encontraron registros para el idpcTV "+peticion_stLocal.getNum_ide_nu_tv()+"en la tabla peticion_st se marca el Reitero en FALSE para TV");
							hayReiteroInfancia=false;
						}
					}
	
					if(!hayReiteroInfancia)
					{
						//se valida que el idpc contenga datos y que el campo ide_pro_cmr contenga L o BA para poder validar la averia con STB
						if((peticion_stLocal.getNum_ide_nu()!=null && !peticion_stLocal.getNum_ide_nu().equals(""))
								&& (peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.BA)||
									peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.LBST))){
								
							//try que hace manejo de finderException en caso de que falle la consulta o no se retorne ningun elemento
							try{
								log.debug("El idpc de la averia "+codAve+" es de STB= "+peticion_stLocal.getNum_ide_nu());
								//se consulta en la tabla peticion_st si hay averias con ese idpc con una fecha maxima y que sean diferentes de la peticion actual
								Peticion_stLocal Peticion_stLocalbyidpcIDE=peticion_stLocalHome.findByIdPcIDE(peticion_stLocal.getNum_ide_nu(),peticion_stLocal.getIde_pro_cmr_cd(),
									codAve);
								Peticion_stKey pk= (Peticion_stKey) Peticion_stLocalbyidpcIDE.getPrimaryKey();
								Long idPeticion = pk.cod_ave_cd;
								//hay averias difenrentes a la actual se valida en esta funcion que la diferencia en la fecha del cierre y la fecha de apeturade 
								//de la averia actual sea menor a 30 dias, si es menor se retorna un true el cual notifica que la averia actual es una averia reiterada
								log.debug("Se va a entrar a valida la si la averia es menor de 30 dias, fecha apertura averia "+codAve+" actual: "
										+peticion_stLocal.getFec_apt_ave_ts()+" fecha de cierre de la ultima averia "+idPeticion+
										" registrada: "+Peticion_stLocalbyidpcIDE.getFec_cie_ave_ts());
								hayReiteroInfancia=hayInfaciaReiteradaCalcularFecha(peticion_stLocal.getFec_apt_ave_ts(),Peticion_stLocalbyidpcIDE.getFec_cie_ave_ts());
								log.debug("¿la averia actual es reiterada? "+hayReiteroInfancia);
							}catch (Exception e){
								//en caso de que falle la consulta o no se encuentren mas averias para el mismo idpc se retorna un false el cual notifica que la a veria
								// no es reiterada
								log.info("No se encontraron registros para el idpc "+peticion_stLocal.getNum_ide_nu()+"en la tabla peticion_st se marca el Reitero en FALSE para STB");
								hayReiteroInfancia=false;
							}
						}
					}
				
				}
				
				//se valida si ya la averia se marco como Reiterada si es false se continua a validar si la averia es de infancia
				if(!hayReiteroInfancia){
					// se valida si el flag de reitero es igual a S
					if(habilitaInfancia.equalsIgnoreCase("S"))
					{
						log.debug("El flag de Infancia contiene S se validara si la averias es de Infancia");
						// hay reitero habilitado
							Instalaciones_VIPLocalHome instalaciones_VIPLocalHome =(Instalaciones_VIPLocalHome)HomeFactory.getHome(Instalaciones_VIPLocalHome.JNDI_NAME);
						// se valida que el idpc contenga datos y que el campo ide_pro_cmr contenga TV para poder validar la averia con TV
						if((peticion_stLocal.getNum_ide_nu_tv()!=null && !peticion_stLocal.getNum_ide_nu_tv().equals(""))
								&& peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.TV)){
							//try que hace manejo de finderException en caso de que falle la consulta o no se retorne ningun elemento
							try{
								log.debug("El idpc de la averia "+codAve+" es de TV= "+peticion_stLocal.getNum_ide_nu_tv());
								// se consulta en la tabla instalaciones_vip si hay peticiones con ese idpcTV con una fecha maxima 
								Instalaciones_VIPLocal instalaciones_VIPLocalTV = instalaciones_VIPLocalHome.findByIdPcTVFechMax(peticion_stLocal.getNum_ide_nu_tv());	
								//hay peticiones con ese IDPCTV se valida en esta funcion que la diferencia en la fecha del cierre y la fecha de apeturade 
								//de la averia actual sea menor a 30 dias, si es menor se retorna un true el cual notifica que la averia actual es una averia de infancia
								log.debug("Se va a entrar a valida la si la averia es menor de 30 dias, fecha apertura averia "+codAve+" actual: "
										+peticion_stLocal.getFec_apt_ave_ts()+" fecha de cierre de la ultima peticion "+instalaciones_VIPLocalTV.getAtis()+
										" registrada: "+instalaciones_VIPLocalTV.getFecha_fin());
								hayReiteroInfancia= hayInfaciaReiteradaCalcularFecha(peticion_stLocal.getFec_apt_ave_ts(),instalaciones_VIPLocalTV.getFecha_fin());		
								log.debug("¿la averia actual es de Infancia?"+hayReiteroInfancia);
							}catch (Exception e){
								//en caso de que falle la consulta o no se encuentren mas averias para el mismo idpcTV se retorna un false el cual notifica que la a veria
								// no es reiterada
								log.info("No se encontraron registros para el idpcTV "+peticion_stLocal.getNum_ide_nu_tv()+"en la tabla instalaciones_VIP se marca la Infancia en FALSE para TV");
								hayReiteroInfancia=false;
							}
						}
						if(!hayReiteroInfancia)
						{
							// se valida que el idpc contenga datos y que el campo ide_pro_cmr contenga TV para poder validar la averia con TV
							
							if((peticion_stLocal.getNum_ide_nu()!=null && !peticion_stLocal.getNum_ide_nu().equals(""))
									&& (peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.BA)||
										peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.LBST))){
								//try que hace manejo de finderException en caso de que falle la consulta o no se retorne ningun elemento
	
								try{
									log.debug("El idpc de la averia "+codAve+" es de TSB= "+peticion_stLocal.getNum_ide_nu());
									// se consulta en la tabla instalaciones_vip si hay peticiones con ese idpcTV con una fecha maxima 
									Instalaciones_VIPLocal instalaciones_VIPLocalSTB =instalaciones_VIPLocalHome.findByIdPcFecMax(peticion_stLocal.getNum_ide_nu());
									//hay peticiones con ese IDPC se valida en esta funcion que la diferencia en la fecha del cierre y la fecha de apeturade 
									//de la averia actual sea menor a 30 dias, si es menor se retorna un true el cual notifica que la averia actual es una averia de infancia
									log.debug("Se va a entrar a valida la si la averia es menor de 30 dias, fecha apertura averia "+codAve+" actual: "
											+peticion_stLocal.getFec_apt_ave_ts()+" fecha de cierre de la ultima peticion "+instalaciones_VIPLocalSTB.getAtis()+
											" registrada: "+instalaciones_VIPLocalSTB.getFecha_fin());
									hayReiteroInfancia= hayInfaciaReiteradaCalcularFecha(peticion_stLocal.getFec_apt_ave_ts(),instalaciones_VIPLocalSTB.getFecha_fin());
									log.debug("¿la averia actual es de Infancia?"+hayReiteroInfancia);
								}catch (Exception e){
									//en caso de que falle la consulta o no se encuentren mas averias para el mismo idpc se retorna un false el cual notifica que la a veria
									// no es de infancia
									log.info("No se encontraron registros para el idpc "+peticion_stLocal.getNum_ide_nu()+"en la tabla peticiones_vip se marca la Infancia en FALSE para STB");
								
									hayReiteroInfancia=false;
								}
							}
						}
					}
				}		
			}
		}
		
		return hayReiteroInfancia;
	}


	//REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 23/07/2014
	//funcion que valida la diferencia de dias de las averias y retorna true cuando la diferenca de fechas entre las averias es menos a 30 dias
	public boolean hayInfaciaReiteradaCalcularFecha (Timestamp fechaAptActual, Timestamp fechaCierreAnterior){
		
		long milis1, milis2, diff;
		//INSTANCIA DEL CALENDARIO GREGORIANO
        Calendar cinicio = Calendar.getInstance();
        Calendar cfinal = Calendar.getInstance();
        //ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
        cinicio.setTime(fechaAptActual);
        cfinal.setTime(fechaCierreAnterior);
        
        milis1 = cinicio.getTimeInMillis();
        milis2 = cfinal.getTimeInMillis();
        
        diff = milis2-milis1;
        long diffdias = Math.abs ( diff / (24 * 60 * 60 * 1000) );
        
        log.debug("Diferencia en dias es: "+ diffdias);
        
        if(diffdias<=30)
        {
        	return true;
        }else{
        	return false;
        }
        
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
	private TR701EEquipment setDatosSAPDecoTarjeta(Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome, TR701EEquipment equipment, String idDeco, String idCard, Long idPeticion){
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
	
	public String extraerCoordenadas(String complementos, String coordenada){
        String valor="";
        
        try{
            String cadena = complementos.substring(complementos.indexOf(coordenada)).replaceAll(coordenada,"").replaceAll("[a-zA-Z]","").trim();
            for (int i=0;i<cadena.length();i++){
                  if ( !(cadena.substring(i,i+1).matches("[0-9]*") || cadena.substring(i,i+1).equals(".") || cadena.substring(i,i+1).equals("-")))
                        break;
                        valor = valor + cadena.substring(i,i+1);
            }
        }catch(Exception ex){
        	valor = "0";
        }
        return valor;
  }
	
	/**
	 * DAVID: Este método sólo recibe la respuesta del alta de actuación, y registra el error en la tabla Agenda_sc_st.
	 */
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s) throws ATiempoAppEx{
		
		try
		{                       
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);                                    
			AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr701s.getIdSchedule());                                
			Long codAve=agendaSCSTLocal.getId_peticion_st();
			
			if (!tr701s.getErrorCode().equals("0")) {
				//Hay error
				String errorMsg="Error en resp. de alta de actuación: Code: "+tr701s.getErrorCode()+", Message: "+tr701s.getErrorMessage();
				if(errorMsg.length()>195){
					errorMsg=errorMsg.substring(0,195);
				}
				agendaSCSTLocal.setMensaje_act(errorMsg);
				agendaSCSTLocal.setCierre_por_error(new Integer(ComunInterfaces.CIERRE_POR_ERROR));
				agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
				Date hoy=new Date();
				agendaSCSTLocal.setFecha_ingreso(new Timestamp(hoy.getTime()));
			}else{
				//No hay error
				agendaSCSTLocal.setMensaje_act("Se recibió respuesta de alta de actuación (tr-701-s) sin problemas.");
			}
			
		} catch (NamingException e) {
			log.error("Creacion de Local Home Nulls en recepcionCreacionActuacionAgendaSC() SOLTEC",e);
		}  catch (FinderException e) {
			log.error("FinderException en recepcionCreacionActuacionAgendaSC() SOLTEC",e);
		} catch(Exception e){
			log.debug("Exception en recepcionCreacionActuacionAgendaSC() SOLTEC",e);
		}           
	}
	/***
	 * DAVID: Se recibe la tr708s desde agenda, y se envía la tr017e de activación decos y tarjetas.
	 * Cuando se recibe la tr017s, se envía la tr708e, de  activación de recursos de agenda sc.
	 * Este metodo analiza el delta de los equipos.
	 */
	public void recepcionActivarDecosTarjetasAgendaSC(TR708S tr708s) throws ATiempoAppEx {
		
		try{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			
			String codAve = tr708s.getIdSchedule().substring(2,tr708s.getIdSchedule().indexOf("-"));
			//Peticion_stKey peticion_stKey = new Peticion_stKey(new Long(codAve));
			
			//Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			//Collection decoTarjetaActualBD = peticion_stLocal.getDeco_tarjeta();                                                               
			//Collection equipos = tr708s.getEquipments();
			
			//Iterator equiposIterator=null;
			Iterator decoTarjetaActualBDIterator=null;
			
			Collection decosAgendaConError=new ArrayList();
			
			AgendaSCSTLocal agendaSCSTLocal = null;
			
			try{
				agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr708s.getIdSchedule());
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento");
			}
			
			if (agendaSCSTLocal != null && agendaSCSTLocal.getEstado().intValue() == ACTUACION_ABIERTA && tr708s.getEquipments()!=null){
				Peticion_stLocal peticionSTLocal = agendaSCSTLocal.getPeticion_st();
				Peticion_stKey peticionSTKey = (Peticion_stKey)peticionSTLocal.getPrimaryKey();
				//Se obtienen los pares decotarjeta que tiene el cliente.
				Collection decoTarjetaOriginal = peticionSTLocal.getDeco_tarjeta();
				ArrayList decoTarjetaInstall = new ArrayList();
				ArrayList decoTarjetaOld = new ArrayList();
				
				/* Esta seccion analiza compara cada deco que tiene el cliente contras los equipos que vienen en la tr708s 
				 * Si un deco del cliente no viene en los equipos de la tr708s analiza si su estado es diferente de  par no activo 
				 * y si es asi lo marca para eliminarlo posteriormente
				 */
				for (Iterator decoTarjetaOldIterator = decoTarjetaOriginal.iterator(); decoTarjetaOldIterator.hasNext();){
					boolean estaInstalado = false;
					Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaOldIterator.next();
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey) decoTarjetaLocal.getPrimaryKey();
					
					DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
					//Se marcan para que se eliminen
					decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
					decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
					//Se marcan para que se eliminen
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
					
					if (!estaInstalado && decoTarjetaLocal.getEstado().intValue() != estadoParNoOk &&
							decoTarjetaLocal.getAccion().intValue() != ComunInterfaces.accionParEliminar){
						decoTarjetaLocal.setEstado(new Integer(estadoParNoOk));                                                        
						
						decoTarjetaInstall.add(decoTarjetaDTO);
					}
				}
				//Se obtienen los pares decotarjeta que tiene el cliente.   
				Collection decoTarjetaList = peticionSTLocal.getDeco_tarjeta();
				Collection decoTarjetaEliminarList = new ArrayList();
				/*
				 * En este ciclo se agregan a un arreglo los pares que se marcaron en el ciclo anterior
				 * */
				for (Iterator decoTarjetaInstallIterator = decoTarjetaList.iterator(); decoTarjetaInstallIterator.hasNext();){
					Deco_tarjetaLocal decoTarjetaDTO = (Deco_tarjetaLocal)decoTarjetaInstallIterator.next();                                                           
					if (decoTarjetaDTO.getEstado().intValue() == estadoParNoOk){
						Deco_tarjetaKey decoTarjetaKeyEliminar = (Deco_tarjetaKey)decoTarjetaDTO.getPrimaryKey();
						Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKeyEliminar);                                                                   
						decoTarjetaEliminarList.add(decoTarjetaKeyEliminar);                                                                
					}
				}
				/*
				 * En este ciclo se toma la coleccion de los eliminados y se marcan con la accion de eliminacion 
				 */
				for (Iterator decoTarjetaEliminarIterator = decoTarjetaEliminarList.iterator(); decoTarjetaEliminarIterator.hasNext();){
					Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaEliminarIterator.next();
					
					Deco_tarjetaLocal decoTarjetaAuxLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
					// No se eliminar porque si hay un error en HC se perderian y no se podian volver a enviar
					//decoTarjetaAuxLocal.remove(); LFMM
					decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
				}
				
				/*
				 * En esta seccion se analizan los equipos que vienen en la tr708s contra la coleccion de equipos que tiene el cliente y
				 * que se marcaron para eliminar en el primer ciclo de este algoritmo.
				 */
				Collection equipos = tr708s.getEquipments();
				Collection ps=peticionSTLocal.getProducto_servicio_peticion();                                       
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
						//Se verifica si un equipo que se envia en la tr708s ya existia previamente y si tiene un estado de activacion OK
						//En ese caso mas adelante se revisa si debe enviarse a eliminar
						if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
								&& decoTarjetaLocal.getEstado().intValue() != ComunInterfaces.estadoParNoOk){
							estaSinInstalar = false;
							break;
						}
					}
					//Si el equipo no esta en la base de datos o esta pero con estado de activacion nook se verifica esto para determinar si
					//se crea o no y se manda a activar nuevamente
					if (estaSinInstalar){
						////
						Deco_tarjetaLocal decoTarjetaLocal = null;
						if (estaNoOK){
							Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionSTKey);
							decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
						}else{
							decoTarjetaLocal = decoTarjetaLocalHome.create(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionSTLocal);
						}
						
						////                                                                                                                                         
						decoTarjetaLocal.setAccion(new Integer(accionParActivar));
						decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
						decoTarjetaLocal.setEstado(new Integer(ComunInterfaces.accionParActivar));
						
						decoTarjetaLocal.setOriginal(new Integer(accionParActivar));
						
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
						decoTarjetaDTO.setAccion(new Integer(accionParActivar));
						decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
						decoTarjetaDTO.setOperationComercial(new Long(decoTarjetaLocal.getOriginal().intValue()));
						decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
						decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
						decoTarjetaDTO.setEstado(new Integer(estadoParOk));
						
						decoTarjetaInstall.add(decoTarjetaDTO);
						
					}else{	
						/*
						 * Si el equipo ya esta instalado con activacion ok lo busca y verifica nuevamente si el estado es activacion no ok
						 * lo envia nuevamente a activar.
						 */
						try{
							Deco_tarjetaKey decoTarjetaKey = new Deco_tarjetaKey(equipo.getCardSerialNumber(), equipo.getDecoCassId(), peticionSTKey);
							Deco_tarjetaLocal decoTarjetaLocal = decoTarjetaLocalHome.findByPrimaryKey(decoTarjetaKey);
							log.debug("El estado del deco " + decoTarjetaKey.id_deco + " - " + decoTarjetaKey.id_tarjeta + " es " + decoTarjetaLocal.getEstado().intValue());
							if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.estadoParNoOk){
								decoTarjetaLocal.setAccion(new Integer(accionParActivar));
								decoTarjetaLocal.setCodigo_error(new Integer(0));
								decoTarjetaLocal.setMensaje_error("");
								decoTarjetaLocal.setOriginal(new Integer(accionParActivar));
								decoTarjetaLocal.setMarca_hora(new Timestamp(new Date().getTime()));
								
								
								DecoTarDTO decoTarjetaDTO = new DecoTarDTO(equipo.getDecoCassId(), equipo.getCardSerialNumber());
								decoTarjetaDTO.setAccion(new Integer(accionParActivar));
								decoTarjetaDTO.setDeco_reference(decoTarjetaLocal.getDeco_reference());
								decoTarjetaDTO.setOperationComercial(new Long (decoTarjetaLocal.getOriginal().intValue()));
								decoTarjetaDTO.setDecoSerial(decoTarjetaLocal.getSerial_deco());
								decoTarjetaDTO.setDecoBrand(decoTarjetaLocal.getDeco_marca());
								decoTarjetaDTO.setEstado(new Integer(estadoParOk));
								
								decoTarjetaInstall.add(decoTarjetaDTO);
							}           
						}catch(FinderException ex){
							
						}
						
					}
				}
				
				if (decoTarjetaInstall != null && decoTarjetaInstall.size() > 0){
					Long idTmpDecoTarjeta = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_DECO_TARJ")) ;
					Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = tmp_deco_tarjetaLocalHome.create (idTmpDecoTarjeta,peticionSTLocal,XMLUtilities.marshall (tr708s)) ;
					
					RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
					//Se envía la tr017s
					recursosTVDelegate.enviaConfiguracionServiciosTVSoloEqAgendaSC(new Long(codAve).longValue(), decoTarjetaInstall);
				}else{
					log.debug("No se envía el mensaje: "+ tr708s.getId() +" a HC porque los equipos recibidos ya están instalados");
					enviaActivarDecosTarjetasAgendaSCSinTr017s(new Long(codAve),tr708s.getId(),decosAgendaConError,tr708s.getIdSchedule());
				}
			}
			
			
			
		}catch(NamingException ex){
			log.debug("Error instanciado el bean en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
//		}catch(RemoveException ex){
//			log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + ex);
//			ex.printStackTrace();
		}catch(CreateException ex){
			log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}/*catch(ATiempoAppEx ex){
		log.debug("Error en el llamado de los métodos de la petición: " + ex);
		ex.printStackTrace();
		}*/
	}
	
	public void enviaActivarDecosTarjetasAgendaSCSinTr017s(Long codAve,String idMensaje, Collection equiposAgendaConError,String idActuacion) throws ATiempoAppEx{
		try{
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			ArrayList equipos = new ArrayList();
			TR708E tr708e = new TR708E();
			tr708e.setId(idMensaje); 
			tr708e.setIdSourceSystem(sistemaAtiempo);
			tr708e.setIdSchedule(idActuacion);
			
			if (equiposAgendaConError.size() > 0){
				tr708e.setResponse("ERROR");
				tr708e.setError("9999");
				tr708e.setErrorMessage("Error en activación de Decos y Tarjetas.");
			}else{
				tr708e.setResponse("OK");
				tr708e.setError("0");
				tr708e.setErrorMessage("");
			}
			
			tr708e.setEquipments(equiposAgendaConError);                        
			tr708e.setDestination(sistemaAgendaSC);
			tr708e.setSource(sistemaAtiempoSt);
			tr708e.setInterfaz("ACT_RES_ACTIVACION");
			tr708e.setVersion("1.0");
			
			ActivarRecursosSCSTMQ activarRecursosSCSTMQ = new ActivarRecursosSCSTMQ();
			activarRecursosSCSTMQ.enviarMensaje(tr708e);
			
			log.debug("Se ha enviado con exito la tr-708-e, ahora se espera recibir un cierre de actuación....");
		}catch(NamingException ex){
			log.debug("Error instanciando el bean en el envío de activar decos tarjetas:" + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en el envío de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}
	}
	/**
	 * DAVID: Método para armar y enviar la activación de recursos de agenda SC.  Se envía despues de recibir la tr017s, respuesta de activación de decos 
	 * y tarjetas.
	 */
	public void enviaActivarDecosTarjetasAgendaSC(Long codAve,String idMensaje) throws ATiempoAppEx{
		try{
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			ArrayList equipos = new ArrayList();
			TR708E tr708e = new TR708E();
			boolean tieneErrores = false;
			
			String idActuacion="";
			Collection agendaSCSTList=peticion_stLocal.getAgendascst();
			
			for (Iterator agendasSCIterator = agendaSCSTList.iterator(); agendasSCIterator.hasNext();){
				AgendaSCSTLocal agendaSCSTLocal = (AgendaSCSTLocal) agendasSCIterator.next();
				idActuacion = agendaSCSTLocal.getId_actuacion();
			}

			tr708e.setId(idMensaje);	
			tr708e.setIdSourceSystem(sistemaAtiempo);
			tr708e.setIdSchedule(idActuacion);
			
			Collection decos = peticion_stLocal.getDeco_tarjeta();
			
			for (Iterator decosIterator = decos.iterator(); decosIterator.hasNext();){
				Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosIterator.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
				
				if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == estadoParNoOk){
					TR708EEquipment tr708Equipment = new TR708EEquipment();
					String mensajeError=decoTarjetaLocal.getMensaje_error();
					tr708Equipment.setDecoCode(decoTarjetaLocal.getCodigo_deco());
					tr708Equipment.setDecoSerialNumber(decoTarjetaLocal.getSerial_deco());
					tr708Equipment.setCardCode(decoTarjetaLocal.getSerial_tarjeta());
					tr708Equipment.setCardSerialNumber(decoTarjetaKey.id_tarjeta);
					tr708Equipment.setDescripcionError(mensajeError);
					equipos.add(tr708Equipment);
					tieneErrores = true;
				}                                              
			}                                  
			if (tieneErrores){
				tr708e.setResponse("ERROR");
				tr708e.setError("9999");
				tr708e.setErrorMessage("Error en activación de Decos y Tarjetas.");
			}else{
				tr708e.setResponse("OK");                                                       
				tr708e.setError("0");
				tr708e.setErrorMessage("");
			}                                                                                             
			tr708e.setEquipments(equipos);                         
			tr708e.setDestination(sistemaAgendaSC);
			tr708e.setSource(sistemaAtiempoSt);
			tr708e.setInterfaz("ACT_RES_ACTIVACION");
			tr708e.setVersion("1.0");
			
			ActivarRecursosSCSTMQ activarRecursosSCSTMQ = new ActivarRecursosSCSTMQ();
			activarRecursosSCSTMQ.enviarMensaje(tr708e);
			
			log.debug("Se ha enviado con exito la tr-708-e, ahora se espera recibir un cierre de actuación....");
		}catch(NamingException ex){
			log.debug("Error instanciando el bean en el envío de activar decos tarjetas:" + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en el envío de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}
	}
	/**
	 * DAVID: Método para cerrar la actuación de agenda SC. Se cierra también la actividad planta externa actual (TV,BA,STB).
	 * DIC 10-2010, OJO, NO SE ESTABA LLAMANDO PORQUE NO SE GUARDABA EN NINGUN MOMENTO LA TR711S...
	 */
	public void cierreActividadYActuacionAgendaSC(TR711S tr711s)throws ATiempoAppEx{
		Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));                 
		try
		{           
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);       
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			
			AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr711s.getIdSchedule());
			
			Long codAve=agendaSCSTLocal.getId_peticion_st();
			
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(peticion_stLocal);
			
			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad()); 
			/**
			 * Se ejecuta el paso final que es cerrar la actividad actual y luego se cierra la actuación de agenda SC.
			 */
			agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
			actDto.setObservacion("Se recibe mensaje OK desde Agenda SC, se cierra la actividad Planta externa y se cierra la actuación: "+tr711s.getError()+" . "+tr711s.getErrorMessage()+".");
			String familiaPs=peticion_stLocal.getIde_pro_cmr_cd();
			if(familiaPs.equals(ComunInterfaces.TV)){
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,"S");
			}else if(familiaPs.equals(ComunInterfaces.BA)){
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"S");
			}else if(familiaPs.equals(ComunInterfaces.LBST)){
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,"S");
			}
			actividadEJB.terminar(actDto);
			
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls en cierreActividadYActuacionAgendaSC() SOLTEC",e);
		}  catch (FinderException e){
			log.warn("FinderException en cierreActividadYActuacionAgendaSC() SOLTEC",e);
		} catch(Exception e){
			log.debug("Exception en cierreActividadYActuacionAgendaSC() SOLTEC",e);
		}           
	}
	/**
	 * DAVID: recupera la actividad actual por medio del correlativo almacenado en la tabla bandejaIntegrada, es necesario para actividades manuales
	 * que se gestionan desde el código tal como se hace para las de planta externa en agenda SC.
	 * @param peticion_stLocal
	 * @return
	 * @throws ATiempoAppEx
	 */
	public ActividadEJBDTO recuperaActividadDeBandejaIntegrada(Peticion_stLocal peticion_stLocal)throws ATiempoAppEx{
		Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
		ActividadEJBDTO actDto=null;
		try{
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
			Collection listaPsPet=peticion_stLocal.getProducto_servicio_peticion();
			//Las peticiones de ST sólo tienen un ps, se itera sólo una vez.
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal)listaPsPet.iterator().next();
			Producto_servicio_stLocal producto_servicio_stLocal = producto_servicio_peticionLocal.getProducto_servicio_st();                                 
			Familia_producto_servicio_stLocal familia_producto_servicio_stLocal = producto_servicio_stLocal.getFamilia_producto_servicio_st();
			Familia_producto_servicio_stKey familia_producto_servicio_stKey=(Familia_producto_servicio_stKey)familia_producto_servicio_stLocal.getPrimaryKey();
			
			Long idFamilia=familia_producto_servicio_stKey.faps_id;
			Long idActividadPE=null;
			String codActividadPE="";
			
			if(idFamilia.intValue()==ComunInterfaces.familiaTV){
				idActividadPE=new Long(idActividadPETV);
				codActividadPE=ComunInterfaces.PLANTA_EXTERNA_TV;
//				REQ BA NAKED adicion familia PC naked
			}else if(idFamilia.intValue()==ComunInterfaces.familiaBandaAncha || idFamilia.intValue()==ComunInterfaces.familiaBandaAnchaNaked){
				idActividadPE=new Long(idActividadPEBA);
				codActividadPE=ComunInterfaces.PLANTA_EXTERNA_BA;
			}else if(idFamilia.intValue()==ComunInterfaces.familiaLinea){
				idActividadPE=new Long(idActividadPESTB);
				codActividadPE=ComunInterfaces.PLANTA_EXTERNA_STB;
			}else if(idFamilia.intValue()==ComunInterfaces.familiaIntEquipado){
				idActividadPE=new Long(idActividadPEVE);
				codActividadPE=ComunInterfaces.PLANTA_EXTERNA_VE;
			}
			
			Peticion_stKey peticion_stKey=(Peticion_stKey)peticion_stLocal.getPrimaryKey(); 
			
			if(esLocalidadTOA(peticion_stKey.cod_ave_cd)){
				log.debug("es localidad TOA "+peticion_stKey.cod_ave_cd);
				idActividadPE=new Long(ComunInterfaces.idActividadPETOA);
				codActividadPE=ComunInterfaces.PLANTA_EXTERNA_TOA;
			}
			
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(idActividadPE,peticion_stKey.cod_ave_cd,idAplicacion);
			
			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(codActividadPE);
			
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
			actDto = actividadEJB.getActividadEJBDTO(peticion_stKey.cod_ave_cd, codActividadPE,idCorrelativo,null);                                 
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls en recuperaActividadDeBandejaIntegrada() SOLTEC",e);
			
		}  catch (FinderException e) {
			log.warn("FinderException en recuperaActividadDeBandejaIntegrada() SOLTEC",e);
			
		} catch(Exception e)
		{
			log.debug("Exception en recuperaActividadDeBandejaIntegrada() SOLTEC",e);
		}           
		return actDto;
	}
	
	/**
	 * DAVID: Se hace el delta de módems y decos tarjetas.  Se comparan los decos tarjetas y módems actuales de la petición con los que vienen en 
	 * el mensaje de cierre de actuación desde agendaSC. Los actuales que no vengan en el mensaje se desactivan y borran de la BD y los nuevos que vengan 
	 * en el mensaje se activan. Finalmente se envía tr017e con las modificaciones de decos y tarjetas. Para los módems sólo se actualiza BD y se espera que
	 * la tr 007e se envíe en una actividad posterior a planta externaBA.
	 */
	public void recepcionCierreActuacionAgendaSC(TR711S tr711s) throws ATiempoAppEx{
		
		try
		{            
			String materialesDecos=STConfig.getVariable("MATERIALES_DECOS");
			String materialesTarjetas=STConfig.getVariable("MATERIALES_TARJETAS");
			String materialesModems=STConfig.getVariable("MATERIALES_MODEMS");
			String materialesCamara = VpistbbaConfig.getVariable("MATERIALES_CAMARA");
			log.debug("MATERIALES_CAMARA"+materialesCamara);

			
			boolean franqueoOK=false;
			boolean cierroActividad = true;
			boolean esCierreAM = false;
			
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);                   
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome (ModemLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			
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
			
			AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr711s.getIdSchedule());
			
			String codAveStr = tr711s.getIdSchedule().substring(2,tr711s.getIdSchedule().indexOf("-"));//agendaSCSTLocal.getId_peticion_st();
			
			Long codAve=new Long(codAveStr);
			
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			/**
			 * Se guarda la tr711s para en la clase ConfiguracionServiciosTVSTServicio, evaluar si ya se ha recibido esta tr para la petición
			 * actual y así no volver a enviar la tr708e después de que se envía la tr017e de activación de decos y tarjetas. La tr708e sólo se
			 * envía en la activación de recursos de agenda SC, que es el método que está más arriba.
			 */         
			
			/**
			 * DAVID: Dic 10-2010, Se vuelve a implmentar las siguientes dos líneas, alguien las borró. Se almacena la tr711s en 
			 * Tmp_deco_tarjetaLocal y hacer una validación en la clase ConfiguracionServiciosTVSTServicio, 
			 * para que no se vuelva a enviar la tr708e en este caso, ya que estamos en el cierre.
			 */
			Long idTmpDecoTarjeta = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_DECO_TARJ")) ;
			Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = tmp_deco_tarjetaLocalHome.create (idTmpDecoTarjeta,peticion_stLocal,XMLUtilities.marshall (tr711s)) ;
			
			/**
			 * Se evalúa el código de franqueo entrante.
			 */
			
			Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
			String codigoBandeja = "";
			try{
				Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr711s.getPostageCode());
				Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
				codigoBandeja = codFranqueoOKLocal.getBandeja();
				franqueoOK = true;
			}catch(FinderException ex){
				log.debug("El codigo de franqueo: "+tr711s.getPostageCode()+" no es considerado como exitoso");
				franqueoOK = false;
			}
			String codigoFranqueo = tr711s.getPostageCode();
			Integer estadoAgendamiento = tr711s.getCodeStateSchedule();              
			
			//Req 4990 Se guardan mas datos de control para el cierre de la actuacion, se guardan independientemente del codigo
			//de franqueo
			agendaSCSTLocal.setNombre_contratista(tr711s.getTechnician().getTechnicianName()+tr711s.getTechnician().getTechnicianLastname());
			//cambiar lffecha
			agendaSCSTLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			
			IncidentesDelegate incidentesDelegate = new IncidentesDelegate();
			String listaTiposEquipo=incidentesDelegate.recuperarParametroFromPropertiesBD("MATERIALES_EQUIPOS_ST");
			String [] listaTiposEquipoArray=listaTiposEquipo.split(",");
			
			if(agendaSCSTLocal.getEstado()!=null&&agendaSCSTLocal.getEstado().intValue()==ComunInterfaces.ACTUACION_ABIERTA){
				//Si llega mensaje de error, se cierra la actuación y se continúa la petición normalmente.
				if(franqueoOK){
					Collection materials = tr711s.getMaterials();
					ArrayList listaDecos=new ArrayList();
					ArrayList listaModems=new ArrayList();
					ArrayList listaTarjetas=new ArrayList();
					ArrayList listaEquiposAgendaSC=new ArrayList();
					ArrayList listaCamaras = new ArrayList();
					ArrayList listaEquiposNoSerializados=new ArrayList();
					ArrayList listaDiscosDuros=new ArrayList();
					
					if (materials != null) {
						Iterator materialsIt=null;
						for(materialsIt=materials.iterator();materialsIt.hasNext();){
							TR711SMaterials materials2 = (TR711SMaterials)materialsIt.next();
							
								if(esDiscoDuro(materials2)){
									listaDiscosDuros.add(materials2);
									log.debug("DISCO");
								}else if(materialesDecos.indexOf(materials2.getTypeEquipment())!=-1){
									listaDecos.add(materials2);
									log.debug("DECO");
								}else if(materialesModems.indexOf(materials2.getTypeEquipment())!=-1){
									listaModems.add(materials2);
									log.debug("MODEM");
								}else if(materialesTarjetas.indexOf(materials2.getTypeEquipment())!=-1){
									listaTarjetas.add(materials2);
									log.debug("TARJETA");
								}else if(materialesCamara.indexOf(materials2.getTypeEquipment())!=-1){
									listaCamaras.add(materials2);
									log.debug("CAMARA");
									
								boolean esMaterialST=false;
								for(int i = 0;i<=listaTiposEquipoArray.length-1;i++){
									String tipoEquipo=listaTiposEquipoArray[i];
									if(materials2.getTypeEquipment().indexOf(tipoEquipo)!=-1){
										listaEquiposAgendaSC.add(materials2);
										esMaterialST=true;
										break;
									}
								}
								if(!esMaterialST){
									listaEquiposNoSerializados.add(materials2);
								}
							}
							
						}
					}
					
					if (listaEquiposAgendaSC != null && listaEquiposAgendaSC.size()>0){
						log.debug("Entro a configurar equipos en el cierre");
						equiposEnAgendaSC(listaEquiposAgendaSC,peticion_stLocal,estadosHomologados);
					}else{//si no llegan equipos en la tr-711-s se eliminan los actuales de la base de datos
						Collection equiposBDList=peticion_stLocal.getElemento_peticion();
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
					}
					
					//Camaras en el cierre
					if(listaCamaras != null && listaCamaras.size()>0){
						log.debug("Entro a configurar camaras en el cierre");
						camarasEnAgendaSC(listaCamaras, peticion_stLocal, estadosHomologados);
					}
					
					//Se actualiza informacion de sap y discos duros
					if(listaDecos!=null && !listaDecos.isEmpty() ){
						log.debug("Voy a actualizar sap y discos duros de decos para el agendamiento:"+tr711s.getIdSchedule());
						Deco_Tarjeta_Info_SapLocalHome dtisl = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
						Collection decosSap=dtisl.findByPeticion(codAve);
						Collection DecosYTarjetas=(Collection) listaDecos.clone();
						DecosYTarjetas.addAll(listaTarjetas);
						for (Iterator iter = DecosYTarjetas.iterator(); iter.hasNext();) {
							TR711SMaterials decoTr = (TR711SMaterials)  iter.next();
							boolean estaElemento=false;
							Deco_Tarjeta_Info_SapLocal decoSap =null;
							for (Iterator iterator = decosSap.iterator(); iterator.hasNext();) {
								decoSap = (Deco_Tarjeta_Info_SapLocal) iterator.next();
								Deco_Tarjeta_Info_SapKey sapKey= (Deco_Tarjeta_Info_SapKey) decoSap.getPrimaryKey();
								if(decoTr.getEquipmentSerialNumber().equals(sapKey.id_elemento)
										||decoTr.getCassId().equals(sapKey.id_elemento)){
									estaElemento=true;
									break;
								}
							}
							if(!estaElemento){
								String idElemento;
								if(decoTr.getCassId()!=null && !decoTr.getCassId().equals("")){
									idElemento=decoTr.getCassId();
								}else{
									idElemento=decoTr.getEquipmentSerialNumber();
								}
								decoSap =dtisl.create(idElemento,codAve);
								log.debug("Va a crear el deco tarjeta con serial "+decoTr.getEquipmentSerialNumber());
							}
							actualizarDatosSap(decoSap,decoTr);
							
						}
						//Se verifica que venga el mismo numero de decos y de tarjetas
						//y que vengan discos duros para actualizar la informacion
						if(listaDecos.size() == listaTarjetas.size() && !listaDiscosDuros.isEmpty()){
							String decosDiscoDuro = STConfig.getVariable("DECO_DISCO_DURO");
							List listaDecosDiscoDuro = Arrays.asList(decosDiscoDuro.split(","));
							for (int i=0;i<listaDecos.size();i++){
								TR711SMaterials deco = (TR711SMaterials)listaDecos.get(i);
								TR711SMaterials tarjeta = (TR711SMaterials)listaTarjetas.get(i);
								
								TR711SMaterials discoDuro = null;
								//Se verifica si la marca de deco soporta disco duro
								if(listaDecosDiscoDuro.contains(deco.getBrandEquipment()) && !listaDiscosDuros.isEmpty()){
									discoDuro = (TR711SMaterials)listaDiscosDuros.get(0);
									listaDiscosDuros.remove(0);
								}
								//Se actuliza la información del disco duro con la que viene en el mensaje
								if(discoDuro != null){
									Collection decoTarjeta = peticion_stLocal.getDeco_tarjeta();
									for(Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator.hasNext();){
										Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal)decoTarjetaIterator.next();
										if(decoTarjetaLocal.getSerial_deco().equals(deco.getEquipmentSerialNumber())){
											log.debug("Entro a actualizar la informacion de disco duro para deco "+deco.getEquipmentSerialNumber());
											decoTarjetaLocal.setSerial_ddtv(discoDuro.getEquipmentSerialNumber());
											decoTarjetaLocal.setModelo_ddtv(discoDuro.getModelEquipment());
											decoTarjetaLocal.setMarca_ddtv(discoDuro.getBrandEquipment());
											decoTarjetaLocal.setAccion(new Integer(accionParActivar));
											decoTarjetaLocal.setEstado(new Integer(estadoParOk));
										}
									}
								}
							}
						}
					}else{//si no vienen decos en la tr-711-s se desconfiguran los actuales 
//						se eliminan todos los decos
						/*
						log.debug("No vienen Decos, voy a eliminar decos para el agendamiento:"+tr711s.getIdSchedule());
						Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
						Collection decoTarjeta = peticion_stLocal.getDeco_tarjeta();
						ArrayList decoTarjetaEliminar = new ArrayList(); 
						for(Iterator decoTarjetaIterator = decoTarjeta.iterator(); decoTarjetaIterator.hasNext();){
							Deco_tarjetaLocal decoTarjetaAuxLocal = (Deco_tarjetaLocal)decoTarjetaIterator.next();
							Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaAuxLocal.getPrimaryKey();
							
							Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
							Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
							
							// Se borran datos de SAP del deco
							try{			
								Deco_Tarjeta_Info_SapKey keyInfoSAPDeco = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, peticion_stKey.cod_ave_cd);
								Deco_Tarjeta_Info_SapLocal infoSAPDeco = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPDeco);
								infoSAPDeco.remove();
							} catch (FinderException e) {
								log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+peticion_stKey.cod_ave_cd);
							} catch (Exception e) {
								log.error("RecursosBABean: Se presento Error borrando los datos de SAP para un Deco. "+e);
							}
							// se borran datos de SAP de la tarjeta
							try{			
								Deco_Tarjeta_Info_SapKey keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_tarjeta, peticion_stKey.cod_ave_cd);
								Deco_Tarjeta_Info_SapLocal infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
								infoSAPCard.remove();
							} catch (FinderException e) {
								log.debug("No se encontraron Tarjetas para deco con id: "+decoTarjetaKey.id_tarjeta+". Y id de peticion: "+peticion_stKey.cod_ave_cd);
							} catch (Exception e) {
								log.error("RecursosBABean: Se presento Error borrando los datos de SAP para una Tarjeta. "+e);
							}
							
							log.debug("Se cambia accion a ParEliminar al deco con serial:"+decoTarjetaAuxLocal.getSerial_deco());
							decoTarjetaAuxLocal.setAccion(new Integer(ComunInterfaces.accionParEliminar));
							decoTarjetaAuxLocal.setEstado(new Integer(ComunInterfaces.estadoParOk));
							
							DecoTarDTO decoTarjetaDTO = new DecoTarDTO(decoTarjetaKey.id_deco, decoTarjetaKey.id_tarjeta);
							decoTarjetaDTO.setAccion(new Integer(operacionParDesactivar));
							decoTarjetaDTO.setDeco_reference(decoTarjetaAuxLocal.getDeco_reference());
							decoTarjetaDTO.setOperationComercial(new Long(operacionParDesactivar));
							decoTarjetaDTO.setDecoSerial(decoTarjetaAuxLocal.getSerial_deco());
							decoTarjetaDTO.setDecoBrand(decoTarjetaAuxLocal.getDeco_marca());		
							decoTarjetaDTO.setDecoType(decoTarjetaAuxLocal.getDeco_reference());
							decoTarjetaEliminar.add(decoTarjetaDTO);
						}
//						Se envia mensaje tr-017-e para desactivacion de decos
						if(decoTarjetaEliminar != null && !decoTarjetaEliminar.isEmpty()){
							String idAgendaSC = tr711s.getIdSchedule()+"@"+tr711s.getId()+"@tr711"+"@"+true;
							RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
							recursosTVDelegate.enviaConfiguracionServiciosTVSoloEqAgendaSC(peticion_stKey.cod_ave_cd.longValue(), decoTarjetaEliminar);
							cierroActividad = false;
						}*/
					}
					
					ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(agendaSCSTLocal.getPeticion_st());
					ServicioTOASTDelegate delegate = new ServicioTOASTDelegate();
					delegate.setearDatosActividad(actDto);
					//Si la operacion comercial es autoinstalacion no se aplica delta de modem
					boolean esOCAutoInstalacion = esAutoinstalacion(codAve);
		        	
		        	if(!esOCAutoInstalacion){
		        		Collection modemsActualBD = modemLocalHome.findPeticion(codAve);
						Iterator materialModemIterator=null;
						ArrayList modemsAEliminar=new ArrayList();
						Iterator modemsActualBDIterator=null;
						
						if(listaModems!=null && !listaModems.isEmpty()){
							log.debug("Entro a configurar modems para el cierre del agendamiento");
							//Se obtienen los modems del mensaje de cierre
							TR711SMaterials materialModemUtilizado = null;
							Short accion = new Short(new Integer(ComunInterfaces.accionModemConfigurado).shortValue());
							for(materialModemIterator=listaModems.iterator(); materialModemIterator.hasNext();){
								TR711SMaterials materialModem = (TR711SMaterials)materialModemIterator.next();
								if (materialModem.getActionType().equalsIgnoreCase("Utilizado") 
										|| materialModem.getActionType().equalsIgnoreCase("Reutilizado")){
									materialModemUtilizado = materialModem;
								}
							}
							String serialModemEnviado = null;
							if(materialModemUtilizado != null){
								serialModemEnviado = materialModemUtilizado.getEquipmentSerialNumber();
							}
							String serialModemActual = null;
							ModemKey modemKey = null;
							//Se obtiene el modem de la bd
							ModemLocal modemLocal = null;
							for(modemsActualBDIterator=modemsActualBD.iterator();modemsActualBDIterator.hasNext();){
								modemLocal = (ModemLocal)modemsActualBDIterator.next();
								modemKey = (ModemKey) modemLocal.getPrimaryKey();
								serialModemActual = modemKey.serial;
							}
							//Si el modem se remplaza, se elimina el actual y se crea el nuevo modem
							if(serialModemActual!=null && serialModemEnviado!=null 
									&& !serialModemActual.equalsIgnoreCase(serialModemEnviado)){
								log.debug("El modem existente se va a eliminar, Serial: "+modemKey.serial);
								//se realiza cambio de modemDTO a modemVPIDTO para que sea aceptado en el metodo de acs
								ModemSTDTO modemDTO = new ModemSTDTO();
								modemLocal.setAccion(new Short(new Integer(ComunInterfaces.operacionParDesactivar).shortValue()));
								modemDTO.setAccion(new Integer(ComunInterfaces.accionModemLiberar));
								modemDTO.setNum_peticion(peticion_stKey.cod_ave_cd);
								modemDTO.setMarca(modemLocal.getModem_marca());
								modemDTO.setModelo(modemLocal.getModelo());
								modemDTO.setSerial(modemKey.serial);
								this.llamadoConfModemAutoInstalacion(modemDTO, actDto.getCodigoActividad(), tr711s.getId()+"#"+tr711s.getIdSchedule(),true);
								try{
									modemLocal.remove();
									modemLocal = null;
								}catch(Exception e){
									log.debug("No se puede eliminar el modem con serial: "+modemDTO.getSerial()+" porque ya fue eliminado");
								}
								log.debug("Se crea el modem, Serial: "+serialModemEnviado);
								ModemLocal modemNuevoLocal = crearConfigurarModem(
										tr711s, modemLocalHome, agendaSCSTLocal, peticion_stLocal, actDto, materialModemUtilizado, accion);
								//modemLocal = modemNuevoLocal;
								
							}else if(serialModemActual == null && serialModemEnviado != null){
								log.debug("Se crea el modem, Serial: "+serialModemEnviado);
								ModemLocal modemNuevoLocal = crearConfigurarModem(
										tr711s, modemLocalHome, agendaSCSTLocal, peticion_stLocal, actDto, materialModemUtilizado, accion);
								//modemLocal = modemNuevoLocal;
							}
							Collection modemsActualBD2 = modemLocalHome.findPeticion(codAve);
							log.debug("Nueva cantidad de modem: "+modemsActualBD2.size());
							for(modemsActualBDIterator=modemsActualBD2.iterator();modemsActualBDIterator.hasNext();){
								modemLocal = (ModemLocal)modemsActualBDIterator.next();
							}
							
							if(modemLocal != null && materialModemUtilizado != null){
								/*RQ.8595 - mfmendez*/
								modemKey = (ModemKey) modemLocal.getPrimaryKey();
								serialModemActual = modemKey.serial;
								log.debug("Se actualiza la informacion SAP para el modem con serial: " + serialModemActual);
								modemLocal.setFec_cont_sap(materialModemUtilizado.getPostingDateSAP());
								modemLocal.setClase_mov_sap(materialModemUtilizado.getMoveTypeSAP());
								if(materialModemUtilizado.getMaterialCodeSAP() != null){
									modemLocal.setPos_doc_sap(Integer.parseInt(materialModemUtilizado.getMaterialCodeSAP()));
								}else{
									modemLocal.setPos_doc_sap(0);
								}
								modemLocal.setNum_material_sap(materialModemUtilizado.getMaterialSAP());
								modemLocal.setCentro_sap(materialModemUtilizado.getPlantSAP());
								modemLocal.setAlmacen_sap(materialModemUtilizado.getStorageSAP());
								modemLocal.setCod_lote_sap(materialModemUtilizado.getBatchCodeSAP());
								modemLocal.setUnd_medida_sap(materialModemUtilizado.getMeasurementUnitSAP());
								modemLocal.setCentr_cost_sap(materialModemUtilizado.getCostCenterSAP());
								modemLocal.setArea_func_sap(materialModemUtilizado.getFuncAreaLongSAP());
								modemLocal.setElement_pep_sap(materialModemUtilizado.getPepElementSAP());
								modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
								modemLocal.setFlag_mat_sap(materialModemUtilizado.getFlagMatSAP());
								/*FIN - RQ.8595 - mfmendez*/
							}
						}
		        	}
					
					
					if(listaEquiposNoSerializados != null && !listaEquiposNoSerializados.isEmpty()){
						for (Iterator iter = listaEquiposNoSerializados.iterator(); iter.hasNext();) {
							TR711SMaterials materialTR = (TR711SMaterials) iter.next();
							log.debug("Voy a configurar y registrar equipos no serializados para el agendamiento:"+tr711s.getIdSchedule());
							DBManager manager;
							manager=new DBManager();
							manager.setDataSource(DBManager.JDBC_VPISTBBA);
							Long bi_id=new Long(manager.seqNextValLong("VPI_ELEM_NO_SERIAL_SEQ"));
							co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoLocalHome ensl = (ElementoNoSerializadoLocalHome)HomeFactory.getHome(ElementoNoSerializadoLocalHome.JNDI_NAME);
							ElementoNoSerializadoLocal elemento = ensl.create(bi_id,peticion_stLocal);
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
					
					agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
					
					ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
					
					IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad());
					
					
					//Raul Triviño: Req 5617 Mejora en cierre IT
					try{
						if (tr711s.getItAnswer()!=null && tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_IT_SL)){
							//Proceso para agregar una solucion
							log.debug("Voy a ingresar una solucion con los datos: Localización:"+tr711s.getItComplement()+" y descripción de cierre:"+tr711s.getItClosing());
							Codigo_cierreLocalHome codCierreLocalHome = (Codigo_cierreLocalHome)HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
							Codigo_cierre_peticionLocalHome codCierrePeticionLocalHome = (Codigo_cierre_peticionLocalHome)HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
							
							Codigo_cierreLocal codCierreLocal = codCierreLocalHome.findByDescripcion(tr711s.getItClosing());
							Codigo_cierreKey codCierreKey = (Codigo_cierreKey)codCierreLocal.getPrimaryKey();
							Fecha fecCie= new Fecha(new Timestamp(new Date().getTime()));                                                           
							
							IncidentesInterfaces iI = new  IncidentesDelegate();
							iI.agregarSolucion(codAve,actDto.getCodigoActividad(),actDto.getIdUsuario(),codCierreKey.cod_cierre,fecCie.getFechaconFormato(9));
						}else if (tr711s.getItAnswer() != null && 
								(tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_IT_TR) || tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_IT_AM)
										|| tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM))){
							//Proceso para agregar un traslado
							log.debug("Voy a ingresar un traslado con los datos: Area de traspaso:"+tr711s.getItComplement()+" y diagnóstico:"+tr711s.getItClosing());
							
							Catalago_prueba_lineaLocalHome catalogoPruebaLineaLocalHome = (Catalago_prueba_lineaLocalHome)HomeFactory.getHome(Catalago_prueba_lineaLocalHome.JNDI_NAME);
							
							PruebaLineaDTO pruebaLineaDTO = new PruebaLineaDTO();
							
							String familiaPs=peticion_stLocal.getIde_pro_cmr_cd();
							Long fam = null;
							if(familiaPs.equals(ComunInterfaces.TV)){
								fam = new Long("3");
							}else if(familiaPs.equals(ComunInterfaces.BA)){
								fam = new Long("2");
							}else if(familiaPs.equals(ComunInterfaces.LBST)){
								fam = new Long("1");
							}
							
							pruebaLineaDTO.setIdFamilia(fam);   
							pruebaLineaDTO.setCodAveCd(codAve);
							
							Catalago_prueba_lineaLocal catalogoPruebaLineaLocal = catalogoPruebaLineaLocalHome.findByDescription(tr711s.getItClosing(),fam);
							Catalago_prueba_lineaKey catalogoKey = (Catalago_prueba_lineaKey)catalogoPruebaLineaLocal.getPrimaryKey();
							
							pruebaLineaDTO.setCodPrueba(catalogoKey.cod_prueba.toString()); 
							String obsTest = tr711s.getItComplement();
							obsTest=obsTest.replace('\r','.');
							obsTest=obsTest.replace('\n',' ');
							pruebaLineaDTO.setObservacion(obsTest);
							//pruebaLineaDTO.setUsuario(nomUsuario);
							pruebaLineaDTO.setIdUsuario(actDto.getIdUsuario());
							
							Fecha fecha = new Fecha();
							Timestamp laFecha  = fecha.getTimestamp();
							pruebaLineaDTO.setFecha(fecha.getTimestamp());
							
							GrabadorSolTecLocalHome grabadorSolTecLocalHome = (GrabadorSolTecLocalHome)HomeFactory.getHome(GrabadorSolTecLocalHome.JNDI_NAME);
							GrabadorSolTecLocal grabadorSolTecLocal = grabadorSolTecLocalHome.create();
							grabadorSolTecLocal.setGrabaPruebaLinea(pruebaLineaDTO);
							
							if (tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_IT_AM)||tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
								esCierreAM = true;
							}
							//actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"S");
						}else{
							log.debug("No se registra ningun registro ni en la tabla de Prueba línea para traslados ni Cod_cliente_peticion porque el tipo de resultado " +
									"no esta especificado, tipo resultado: "+tr711s.getItAnswer()+" se espera SL o TR");
							
							actDto.setObservacion("No se cierra la petición pues el tipo de resultado no esta especificado, tipo resultado: "+tr711s.getItAnswer()+" se espera SL o TR");
							agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
							actividadEJB.grabarSinTerminar(actDto);
							cierroActividad = false;
						}
					}  catch (FinderException e) {
						log.warn("FinderException en Mejora en cierre IT() SOLTEC",e);
						actDto.setObservacion("No se cierra la petición porque ocurrió un problema identificando el cierre de la avería o el diagnóstico del técnico, valor a buscar: "+tr711s.getItClosing());
						agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
						actividadEJB.grabarSinTerminar(actDto);
						cierroActividad = false;
					}
					
					//End 
					
					//actDto.setObservacion(tr711s.getBreaks().getObservations());
					//Cierro la actividad solamente si no ejecute una actualización de decos (tr017e) y si estoy en planta externa 
					//if (cierroActividad){
					//Req 4990 Gestion de quiebres si el codigo de franqueo esta en las tablas se redirige a la bandeja especificada
					//Busco la actividad para obtener su descripcion
					//codFranqueoOKLocal.
					if (codigoBandeja != null && codigoBandeja.length() > 0 ){
						log.debug("El codigo de la bandeja existe y se derivara a : " + codigoBandeja);
						if(codigoBandeja.equals("SOLUCION_MASIVA")){
							String familiaPs=peticion_stLocal.getIde_pro_cmr_cd();
							if(familiaPs.equals(ComunInterfaces.TV)){
								//DAVID: Dic 10, 2010. Señores, esto va con S, favor no ponerle N. Se usa para salir de solucion BA,LB o TV automáticamente.
								log.debug("Se deriva a solucion tv");
								actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,"TV");
							}else if(familiaPs.equals(ComunInterfaces.BA)){
								actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"BA");
								log.debug("Se deriva a solucion ba");
							}else if(familiaPs.equals(ComunInterfaces.LBST)){
								actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,"STB");
								log.debug("Se deriva a solucion lb");
							}
						}else{
							String[] llaveWF = null;
							llaveWF = codigoBandeja.split("-");                                                        
							actDto.setDato(llaveWF[0],llaveWF[1]);
						}
						
					}else{
						log.debug("El codigo de la bandeja no existe o es nullo y se derivara a la bandeja de solucion");
						String familiaPs=peticion_stLocal.getIde_pro_cmr_cd();
						if(familiaPs.equals(ComunInterfaces.TV)){
							//DAVID: Dic 10, 2010. Señores, esto va con S, favor no ponerle N. Se usa para salir de solucion BA,LB o TV automáticamente.
							log.debug("Se deriva a solucion tv");
							actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv,"S");
						}else if(familiaPs.equals(ComunInterfaces.BA)){
							actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"S");
							log.debug("Se deriva a solucion ba");
						}else if(familiaPs.equals(ComunInterfaces.LBST)){
							actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb,"S");
							log.debug("Se deriva a solucion lb");
						}
					}                           
					
					if (!esCierreAM){
						actividadEJB.terminar(actDto);
					}else{
						Timestamp fechaLimite = Timestamp.valueOf("2011-06-04 00:00:00");	
						Timestamp fechaPeticion = peticion_stLocal.getFec_apt_ave_ts();
						
						if (fechaPeticion.after(fechaLimite)){
							actividadEJB.terminar(actDto);
						}
					}
					
					/*					}else{
					 log.debug("Quedo en espera de la respuesta de la activación de decos y tarjetas (TR-017-S)");
					 }*/
					
				}//Fin if(franqueoOK)
				//DAVID: Dic 28 2010, si la actuación está abierta y el franqueo es no OK, entonces se cierra la actuación.
				else{
					/**
					 * Se cierra la actuación de agenda SC.
					 */
					log.debug("Se recibió código de franqueo no OK para la actuación: "+agendaSCSTLocal.getId_actuacion()+", y se cerrará..");
					agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
				}
			}//fin if(agendaSCSTLocal.getEstado()!=null&&agendaSCSTLocal.getEstado().intValue()==ComunInterfaces.ACTUACION_ABIERTA)
			else{
				/**
				 * Si la actuación está cerrada, no se debe hacer el proceso para cierre de actuación.
				 */
				log.debug("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
				ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(peticion_stLocal);
				actDto.setObservacion("El mensaje de cierre con ID: "+tr711s.getId()+" no se tiene en cuenta, porque la actuación:"+tr711s.getIdSchedule()+" se encuentra cerrada");
			}
			
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		}  catch (FinderException e) {
			log.warn("FinderException en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		} catch(Exception e){
			log.debug("Exception en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		}	
	}
	
	/**
	 * @param codAve
	 * @return
	 * @throws ATiempoAppEx
	 * @throws NamingException
	 * @throws FinderException
	 */
	private boolean esAutoinstalacion(Long codAve) throws ATiempoAppEx, NamingException, FinderException {
		PeticionesDelegate pDelegate = new PeticionesDelegate();
		Long OCAutoInstalacion=new Long(pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OPCO_AUTOINSTALACION));
		boolean esOCAutoInstalacion = false;
		Producto_servicio_peticionLocalHome  psph = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		Collection productosServicioPeticion = psph.findByPetiNumero(codAve);
		for (Iterator iter = productosServicioPeticion.iterator(); iter.hasNext();) {
			Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
			Long opco = psp.getIdOperacionComercial();
			if(opco.equals(OCAutoInstalacion)){
				esOCAutoInstalacion = true;
				break;
			}
		}
		return esOCAutoInstalacion;
	}
	/**
	 * Método que crea un modem en la base de datos y envia la configuracion automatica del modem.
	 */
	private ModemLocal crearConfigurarModem(TR711S tr711s, ModemLocalHome modemLocalHome, AgendaSCSTLocal agendaSCSTLocal, Peticion_stLocal peticion_stLocal, ActividadEJBDTO actDto, TR711SMaterials materialModemUtilizado, Short accion) throws CreateException, ATiempoAppEx {
		String telefonoModem="0";
		if(peticion_stLocal.getTel_cot_ds()!=null){
			telefonoModem=peticion_stLocal.getTel_cot_ds();
		}
		
		ModemLocal modemNuevoLocal = modemLocalHome.create(materialModemUtilizado.getEquipmentSerialNumber(), peticion_stLocal,new Long(telefonoModem), accion);
		log.debug("Se crea modem "+materialModemUtilizado.getEquipmentSerialNumber());
		modemNuevoLocal.setModem_marca(materialModemUtilizado.getBrandEquipment());								
		modemNuevoLocal.setCod_material(materialModemUtilizado.getCodeMaterial());
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
		ModemSTDTO modem = new ModemSTDTO();
		modem.setNum_peticion(agendaSCSTLocal.getId_peticion_st());
		modem.setMarca(modemNuevoLocal.getModem_marca());
		modem.setSerial(materialModemUtilizado.getEquipmentSerialNumber());
		modem.setModelo(modemNuevoLocal.getModelo());
		modem.setCod_material(modemNuevoLocal.getCod_material());
		rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), 
				tr711s.getId()+"#"+tr711s.getIdSchedule(),true);
		return modemNuevoLocal;
	}
	/**
	 * Se verifica que el material sea de tipo disco duro segun la parametrizacion
	 * en el archivo de propiedades configST.properties, en la propiedad TIPOS_DISCOS_DUROS
	 * @param materials
	 * @return true si el material es disco duro, false en caso contrario
	 */
	private boolean esDiscoDuro(TR711SMaterials materials) {
		
		String tiposDiscoDuro = STConfig.getVariable("TIPOS_DISCOS_DUROS");
		if(tiposDiscoDuro != null){
			List listTiposDiscoDuro = Arrays.asList(tiposDiscoDuro.split(","));
			
			if(listTiposDiscoDuro.contains(materials.getTypeEquipment())){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param decoSap
	 */
	private void actualizarDatosSap(Deco_Tarjeta_Info_SapLocal decoSap,TR711SMaterials decoTr) {
		decoSap.setFec_cont_sap(decoTr.getPostingDateSAP());
		decoSap.setClase_mov_sap(decoTr.getMoveTypeSAP());
		if(decoTr.getMaterialCodeSAP() != null)
			decoSap.setPos_doc_sap(Integer.parseInt(decoTr.getMaterialCodeSAP()));
		else
			decoSap.setPos_doc_sap(0);
		decoSap.setNum_material_sap(decoTr.getMaterialSAP());
		decoSap.setCentro_sap(decoTr.getPlantSAP());
		decoSap.setAlmacen_sap(decoTr.getStorageSAP());
		decoSap.setCod_lote_sap(decoTr.getBatchCodeSAP());
		decoSap.setUnd_medida_sap(decoTr.getMeasurementUnitSAP());
		decoSap.setCentr_cost_sap(decoTr.getCostCenterSAP());
		decoSap.setArea_func_sap(decoTr.getFuncAreaLongSAP());
		decoSap.setElement_pep_sap(decoTr.getPepElementSAP());
		decoSap.setFlag_mat_sap(decoTr.getFlagMatSAP());
		
	}
	
	private void camarasEnAgendaSC(ArrayList listaCamaras, Peticion_stLocal peticion_stLocal,HashMap estadosHomologados){
		Peticion_stKey pk= (Peticion_stKey) peticion_stLocal.getPrimaryKey();
		Long idPeticion = pk.cod_ave_cd;
		log.debug("Voy a configurar y registrar cámaras para el agendamiento en ST para la petición: "+idPeticion);
		for (int i = 0; i < listaCamaras.size(); i++) {
			TR711SMaterials materialCamara = (TR711SMaterials)listaCamaras.get(i);
			CamaraKey camaraKey = new CamaraKey(idPeticion,materialCamara.getEquipmentSerialNumber());
			try{
				CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
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
						Collection productoServicioPeticion = peticion_stLocal.getProducto_servicio_peticion();
						
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
								
								log.debug("psTipoKey.ps_id.intValue: "+psTipoKey.ps_id.intValue()+", productoServicioPeticionLocal.getPsId: "+productoServicioPeticionLocal.getIdProductoServicio().intValue());
								if (psTipoKey.ps_id.intValue() == productoServicioPeticionLocal.getIdProductoServicio().intValue()){

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
									} catch (NamingException ex) {
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
			} catch (NamingException e) {
				log.error("Error en: camarasEnAgendaSC(). ",e);
			}
		}
	}
	
	/**
	 * DAVID: Método que compara los equipos recibidos en el cierre de actuación de Agenda SC con los equipos ya existentes en Base de datos para una petición ST.
	 * Si los equipos recibidos ya están en BD, se actualiza su campo Accion según lo que venga en el cierre. Si no están en BD, entonces se guardan en ésta.
	 * @param listaEquipos
	 * @param peticion_stLocal
	 */
	public void equiposEnAgendaSC(ArrayList listaEquiposAgendaSC, Peticion_stLocal peticion_stLocal,HashMap estadosHomologados){
		try{
			log.debug("Voy a configurar y registrar equipos para el agendamiento en ST..");                                                                          
			Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
			Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
			Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
			
			Iterator equiposAgendaIt=null;
			
			for(equiposAgendaIt=listaEquiposAgendaSC.iterator();equiposAgendaIt.hasNext();){
				TR711SMaterials equipoAgendaSC = (TR711SMaterials)equiposAgendaIt.next();
				Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSC.findByDescEquipo(equipoAgendaSC.getTypeEquipment());
				Short accion = new Short("0");
				if(estadosHomologados.containsKey(equipoAgendaSC.getActionType())){
					accion = new Short(estadosHomologados.get(equipoAgendaSC.getActionType()).toString());
				}
				Long psId=peticion_stLocal.getCod_pro_ser_cd();
				Ps_Tipo_EqLocal ps_Tipo_EqLocal =  psTipoEqLocalHome.findTipoByPs(psId.longValue());
				Ps_Tipo_EqKey ps_Tipo_EqKey = (Ps_Tipo_EqKey)ps_Tipo_EqLocal.getPrimaryKey();
				Integer tipoEquipoDesdePS = ps_Tipo_EqKey.id_tipo_eq;
				
				//Verificamos si el equipo que viene de agenda ya está en BD, si es así, se le cambia el campo Accion por el que viene.
				Collection equiposBDList=peticion_stLocal.getElemento_peticion();
				Iterator equiposBDIt=null;
				
				boolean yaEstaEnBD=false;
				
				for(equiposBDIt=equiposBDList.iterator();equiposBDIt.hasNext();){
					Elemento_PeticionLocal elemento_PeticionLocal = (Elemento_PeticionLocal)equiposBDIt.next();
					
					//Si ya existe en BD le cambiamos el campo accion y le fijamos el que trae el equipo desde Agenda SC.
					if(elemento_PeticionLocal.getSerial().equals(equipoAgendaSC.getEquipmentSerialNumber())&&
							elemento_PeticionLocal.getTipo_equipo().equals(tipoEquipoDesdePS.toString())){                                                                        
						elemento_PeticionLocal.setAccion(accion);
						
						/*RQ.8595 - mfmendez*/
						elemento_PeticionLocal.setFec_cont_sap(equipoAgendaSC.getPostingDateSAP());
						elemento_PeticionLocal.setClase_mov_sap(equipoAgendaSC.getMoveTypeSAP());
						if(equipoAgendaSC.getMaterialCodeSAP() != null){
							elemento_PeticionLocal.setPos_doc_sap(Integer.parseInt(equipoAgendaSC.getMaterialCodeSAP()));
						}else{
							elemento_PeticionLocal.setPos_doc_sap(0);
						}
						elemento_PeticionLocal.setNum_material_sap(equipoAgendaSC.getMaterialSAP());
						elemento_PeticionLocal.setCentro_sap(equipoAgendaSC.getPlantSAP());
						elemento_PeticionLocal.setAlmacen_sap(equipoAgendaSC.getStorageSAP());
						elemento_PeticionLocal.setCod_lote_sap(equipoAgendaSC.getBatchCodeSAP());
						elemento_PeticionLocal.setUnd_medida_sap(equipoAgendaSC.getMeasurementUnitSAP());
						elemento_PeticionLocal.setCentr_cost_sap(equipoAgendaSC.getCostCenterSAP());
						elemento_PeticionLocal.setArea_func_sap(equipoAgendaSC.getFuncAreaLongSAP());
						elemento_PeticionLocal.setElement_pep_sap(equipoAgendaSC.getPepElementSAP());
						elemento_PeticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
						elemento_PeticionLocal.setFlag_mat_sap(equipoAgendaSC.getFlagMatSAP());
						/*FIN - RQ.8595 - mfmendez*/
						
						yaEstaEnBD=true;
						break;
					}
				}
				
				//Si no exíste, entonces lo guardamos en la BD porque es un equipo nuevo que nos evían de Agenda SC.
				if(!yaEstaEnBD){
					String telefonoIt="0";
					if(peticion_stLocal.getNum_ide_nu()!=null){
						//telefonoIt = new Long (ComunInterfaces.TELEFONO_DUMMY_TV);
						//if(!peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.TV)){
						telefonoIt = peticion_stLocal.getNum_ide_nu();
						//}
					}
					//Para objetos elemento_peticionLocal se setea el campo telefon normal en 0 y el telefonoIt en Num_ide_nu
					Long telefonoNormal=new Long(0);
					
					Long tipoElemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());                                                                                 
					Elemento_PeticionLocal elemento_peticionLocal = elementoPeticionLocalHome.create(equipoAgendaSC.getEquipmentSerialNumber(),peticion_stLocal,telefonoNormal,
							accion,tipoEquipoDesdePS.toString(),"8",tipoElemento,new Long(psId.toString()));
					elemento_peticionLocal.setTelefono_it(telefonoIt);
					
					/*RQ.8595 - mfmendez*/
                	elemento_peticionLocal.setFec_cont_sap(equipoAgendaSC.getPostingDateSAP());
                	elemento_peticionLocal.setClase_mov_sap(equipoAgendaSC.getMoveTypeSAP());
					if(equipoAgendaSC.getMaterialCodeSAP() != null){
						elemento_peticionLocal.setPos_doc_sap(Integer.parseInt(equipoAgendaSC.getMaterialCodeSAP()));
					}else{
						elemento_peticionLocal.setPos_doc_sap(0);
					}
					elemento_peticionLocal.setNum_material_sap(equipoAgendaSC.getMaterialSAP());
					elemento_peticionLocal.setCentro_sap(equipoAgendaSC.getPlantSAP());
					elemento_peticionLocal.setAlmacen_sap(equipoAgendaSC.getStorageSAP());
					elemento_peticionLocal.setCod_lote_sap(equipoAgendaSC.getBatchCodeSAP());
					elemento_peticionLocal.setUnd_medida_sap(equipoAgendaSC.getMeasurementUnitSAP());
					elemento_peticionLocal.setCentr_cost_sap(equipoAgendaSC.getCostCenterSAP());
					elemento_peticionLocal.setArea_func_sap(equipoAgendaSC.getFuncAreaLongSAP());
					elemento_peticionLocal.setElement_pep_sap(equipoAgendaSC.getPepElementSAP());
					elemento_peticionLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
					elemento_peticionLocal.setFlag_mat_sap(equipoAgendaSC.getFlagMatSAP());
					/*FIN - RQ.8595 - mfmendez*/
				}
				
			}
		}catch(NamingException e){
			log.debug("NamingException en método: equiposEnAgendaSC()"+e.toString());
		}catch(FinderException e){
			log.debug("FinderException en método: equiposEnAgendaSC()"+e.toString());
		}catch(CreateException e){
			log.debug("CreateException en método: equiposEnAgendaSC()"+e.toString());
		}catch(Exception e){
			log.debug("Exception en método: equiposEnAgendaSC()"+e.toString());
		}
		
	}
	/**
	 * DAVID: Para validar si la petición actual es de de Agenda SC.
	 */
	public boolean esAgendaSC(Long codAve) throws ATiempoAppEx{
		boolean esAgendaSC=false;
		try{
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey peticion_stKey=new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadKey localidadKey=new LocalidadKey(codLoc);
			LocalidadLocal localidadLocal = localidadHome.findByPrimaryKey(localidadKey);
			
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
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#llamadoConfModemAutoInstalacion(co.com.telefonica.atiempo.soltec.dto.ModemSTDTO, java.lang.String, java.lang.String)
	 */
	public String llamadoConfModemAutoInstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre,boolean esCierreActuacion) throws ATiempoAppEx {
		String [] valores = this.enviarConfiguracionModemAutoinstalacion(modem,codActividad,idMensajePadre);
		String retorno = valores[1];
		String respuesta = new String();
		
		TR135S tr135s = new TR135S();
		tr135s.setId(valores[0]);
		tr135s.setAtiempoRequestNumber(modem.getNum_peticion().toString());
		
		if (retorno!= null){
			if (retorno.indexOf("<faultcode>") != -1){
				tr135s.setErrorCode("1");
				tr135s.setErrorDescription(retorno.substring(retorno.indexOf("<faultcode>")+13, retorno.indexOf("</faultcode>")));
			}else if (retorno.indexOf("SOAPFaultException")!=-1){
				tr135s.setErrorCode("1");
				if (retorno.indexOf("Detail") != -1)
					tr135s.setErrorDescription(retorno.substring(retorno.indexOf("SOAPFaultException")+20, retorno.indexOf("Detail")));
				else
					tr135s.setErrorDescription(retorno.substring(retorno.indexOf("SOAPFaultException")+20));
			}
			
			if (retorno.indexOf("<faultstring>") != -1){
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
		confModem.procesar(obj,esCierreActuacion);
		respuesta = valores[0];
		
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviarConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.soltec.dto.ModemSTDTO, java.lang.String, java.lang.String)
	 */
	public String[] enviarConfiguracionModemAutoinstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx {
		log.debug("Entro a enviarConfiguracionModemAutoinstalacion");
		
		try{
			String [] valores = new String[2];
			Peticion_stLocalHome peticionSTLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Producto_servicio_stLocalHome producto_servicioSTLocalHome =  (Producto_servicio_stLocalHome) HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
			ConectorLocalHome  conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			
			Peticion_stKey peticionkey = new Peticion_stKey(modem.getNum_peticion());
			Peticion_stLocal peticionLocal = peticionSTLocalHome.findByPrimaryKey(peticionkey);
			
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
			tr135e.setAtiempoRequestNumber(modem.getNum_peticion().toString());
			
			Producto_servicio_en_vueloLocalHome productoServicioEnVueloLocalHome = (Producto_servicio_en_vueloLocalHome)HomeFactory.getHome(Producto_servicio_en_vueloLocalHome.JNDI_NAME);
			Collection psevList = productoServicioEnVueloLocalHome.findByPetiTipoPeticion(peticionkey.cod_ave_cd, ComunInterfaces.BA_EN_VUELO);
			
			for(Iterator psevIter=psevList.iterator(); psevIter.hasNext();){
				Producto_servicio_en_vueloLocal psevLocal = (Producto_servicio_en_vueloLocal)psevIter.next();
				tieneBaEnVuelo = true;
				psev=psevLocal;
			}
			
			Collection PSPCollecton = peticionLocal.getProducto_servicio_peticion();
			for (Iterator PSPIterator=PSPCollecton.iterator(); PSPIterator.hasNext();){
				Producto_servicio_peticionLocal PSPLocal = (Producto_servicio_peticionLocal)PSPIterator.next();
				Long psId = PSPLocal.getIdProductoServicio();
				Producto_servicio_stLocalHome ps_stLocalHome = (Producto_servicio_stLocalHome) HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
				Producto_servicio_stKey ps_stKey = new Producto_servicio_stKey(psId); 
				Producto_servicio_stLocal ps_stLocal = ps_stLocalHome.findByPrimaryKey(ps_stKey);
				Familia_producto_servicio_stLocal fampsStLocal = ps_stLocal.getFamilia_producto_servicio_st();
				Familia_producto_servicio_stKey fampsKey = (Familia_producto_servicio_stKey)fampsStLocal.getPrimaryKey();
				
				Operacion_comercial_stKey opcoKey = (Operacion_comercial_stKey)PSPLocal.getOperacion_comercial_st().getPrimaryKey();
//				REQ BA NAKED adicion familia PC naked
				if (opcoKey.opco_id.longValue() == OCAutoInstalacion.longValue()
						|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaBandaAncha
						|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
						|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaLinea
						|| (fampsKey.faps_id.intValue()== ComunInterfaces.familiaTV && peticionLocal.getNum_ide_nu_tv() != null)){
					if (tieneBaEnVuelo){
						PSCode = psev.getPs_id();
						COCode = new Long (psev.getOpco_id().toString());
					}else{
						PSCode = PSPLocal.getIdProductoServicio();
						COCode = opcoKey.opco_id;
					}
					
					if(fampsKey.faps_id.intValue()== ComunInterfaces.familiaTV && peticionLocal.getNum_ide_nu_tv() != null){
						PSCode = new Long (ComunInterfaces.familiaBandaAncha);
						COCode = opcoKey.opco_id;
					}
						
					
					if (COCode.intValue()==OCAutoInstalacion.intValue())
						COCode = new Long(ComunInterfaces.operacionParActivar);
					
					
					//fatherEmail = obtenerRecursosBA(modem.getNum_peticion()).getFatherEmail();
					
					break;
				}
			}
			log.debug("COCode: "+COCode);
			tr135e.setCommercialOperationType(COCode);
			tr135e.setProductServiceCode(PSCode);
			
			Collection recursosBaList = peticionLocal.getRecursos_ba();
			for (Iterator recursosBAIter = recursosBaList.iterator();recursosBAIter.hasNext();){
				Recursos_baLocal recursosBALocal = (Recursos_baLocal) recursosBAIter.next();
				
				fatherEmail = recursosBALocal.getFather_email_actual();
			}
			
			if (fatherEmail != null && fatherEmail.length()>0){
				tr135e.setFatherEmail(fatherEmail);
			}else{
				tr135e.setFatherEmail(" ");
			}
			
			/*Collection recursosLineaBasicaCollection = peticionLocal.getRecursos_linea_basica();
			for (Iterator recursosLineaBasicaIterator = recursosLineaBasicaCollection.iterator(); recursosLineaBasicaIterator.hasNext();){
				Recursos_linea_basicaLocal recursosLineaBasicaLocal = (Recursos_linea_basicaLocal)recursosLineaBasicaIterator.next();
				
				if (recursosLineaBasicaLocal.getTelefono_asignado()!=null){
					tr135e.setPhoneNumber(new Integer(recursosLineaBasicaLocal.getTelefono_asignado().toString()));
					tr135e.setNewPhoneNumber(new Integer("0"));
				}
			}*/
			
			
			try{				
				tr135e.setPhoneNumber(new Integer (peticionLocal.getNum_ide_nu()));
				tr135e.setNewPhoneNumber(new Integer("0"));
			}catch(Exception ex){
				log.debug("Se presento un error consultando el teléfono, se setea 0: "+ex);
				tr135e.setPhoneNumber(new Integer("0"));
				tr135e.setNewPhoneNumber(new Integer("0"));
			}
			
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_stLocal mensajeEstadoSTLocal= mensajeEstadoSTLocalHome.create(IdCorrelativo);
			mensajeEstadoSTLocal.setPeticion_st(peticionLocal);
			Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			Producto_servicio_stLocal producto_servicioSTLocal = producto_servicioSTLocalHome.findByPrimaryKey(new Producto_servicio_stKey(PSCode));
			Familia_producto_servicio_stLocal familia_producto_servicioSTLocal = (Familia_producto_servicio_stLocal) producto_servicioSTLocal.getFamilia_producto_servicio_st();
			Familia_producto_servicio_stKey familia_producto_servicioSTKey = (Familia_producto_servicio_stKey) familia_producto_servicioSTLocal.getPrimaryKey();
			mensajeEstadoSTLocal.setCod_familia_ps(new Integer(familia_producto_servicioSTKey.faps_id.intValue()));
			mensajeEstadoSTLocal.setCod_conector(new Integer(codigoConectorDos));
			mensajeEstadoSTLocal.setFecha_envio(df.format (new java.util.Date ()));
			//mensajeEstadoSTLocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoSTLocal.setCod_actividad_generadora(codActividad);
			
			//String valocidadPlan = producto_servicioSTLocal.getVelocidad();
			
			cadenaInf = modem.getSerial()+":" ;
			
			//Guardo el modem en la base de datos
			Collection modemList = peticionLocal.getModem();
			if (modemList.size() > 0){
				for (Iterator modemIter = modemList.iterator(); modemIter.hasNext();){
					ModemLocal modemLocal = (ModemLocal) modemIter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					accionModemLocal = modemLocal.getAccion();
					
					if (modemKey.serial.equals(modem.getSerial())){
						modemYaExistente = true;
						break;
					}
				}
				
				boolean tienePeticionesConfModems = false;
				
				ArrayList mensajesEnviadosList = pDelegate.recuperarListaMensajesConfModemsACS(modem.getNum_peticion());
				if (mensajesEnviadosList != null && mensajesEnviadosList.size()>0){
					tienePeticionesConfModems = true;
				}
				
				//Se coloca no bajas sino traslados asi venga a configurar el mismo modem
				log.debug("modemYaExistente: "+modemYaExistente);
				if (modemYaExistente){
					log.debug("accionModemLocal: "+accionModemLocal);
					if (accionModemLocal.intValue() == ComunInterfaces.accionModemConfigurado ||
							accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar ||
							(!tienePeticionesConfModems && 
									accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar)){
						tr135e.setCommercialOperationType(new Long(ComunInterfaces.altaMigracionPS));
						cadenaInf = cadenaInf+ComunInterfaces.altaMigracionPS;
					}else if(accionModemLocal.intValue() == ComunInterfaces.operacionParDesactivar){
						tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParDesactivar));
						cadenaInf = cadenaInf+ComunInterfaces.operacionParDesactivar;							
					}else{
						tr135e.setCommercialOperationType(new Long(ComunInterfaces.operacionParActivar));
						cadenaInf = cadenaInf+ComunInterfaces.operacionParActivar;	
					}
				}else{
					if (accionModemLocal.intValue() == ComunInterfaces.accionModemConfigurado
							|| accionModemLocal.intValue() == ComunInterfaces.accionModemLiberar){
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
					
					ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,modem.getTelefono(),new Short(modem.getAccion().toString()));
					modemLocal.setModem_marca(modem.getMarca());
					modemLocal.setModelo(modem.getModelo());
					modemLocal.setCod_material(modem.getCod_material());
					modemLocal.setTipo(new Integer (modem.getTipo().intValue()));
				}
			}else{
				ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionLocal,modem.getTelefono(),new Short(modem.getAccion().toString()));
				modemLocal.setModem_marca(modem.getMarca());
				modemLocal.setModelo(modem.getModelo());
				modemLocal.setCod_material(modem.getCod_material());
				modemLocal.setTipo(new Integer (modem.getTipo().intValue()));
				
				cadenaInf = cadenaInf+"Nuevo";
			}
			
			if (idMensajePadre != null)
				cadenaInf = cadenaInf+":"+idMensajePadre;
			
			mensajeEstadoSTLocal.setObservaciones(cadenaInf);
			
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
			String passwordModemWIFI = ComunInterfaces.INDICATIVO_PASSWORD_MODEM + peticionLocal.getNum_doc_rte_nu();
			
			
			try{
				String retorno = new String();
				/*Para el habilitar el manejo del timeout: NBIWebServicesInvoker invocador = new NBIWebServicesInvokerAsync();*/
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				int timeOut = Integer.valueOf(peticionesDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.MILISECONDS_TIMEOUT_NBIWS)).intValue();
				log.debug("TimeOut NBIWebService: "+timeOut);
				NBIWebServicesInvoker invocador = new NBIWebServicesInvokerAsync(timeOut);
				ParametrosMotiveDTO parametros = invocador.obtenerDatosParametricos(urlWebService, userWebService, passwdWebService,
						certifacadeWebService,protocolModem, nameWifi, securityModem, passwordModem);
				
				String OUI = pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.OUI+tr135e.getModelModem());
				
				if (OUI!= null){
					if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.operacionParActivar
							||tr135e.getCommercialOperationType().intValue() == ComunInterfaces.ALTA_CERO_CNEX){
						retorno = invocador.configurarModem(tr135e.getSerialNumber(), OUI, tr135e.getModelModem(), tr135e.getFatherEmail(),
								tr135e.getPhoneNumber().toString(), tr135e.getNewPhoneNumber().toString(), "4096", tr135e.getAtiempoRequestNumber(), 
								parametros, passwordModemWIFI);
					}else if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.altaMigracionPS){
						retorno = invocador.modificarModem(tr135e.getSerialNumber(), OUI, tr135e.getModelModem(), tr135e.getFatherEmail(),
								tr135e.getPhoneNumber().toString(), tr135e.getNewPhoneNumber().toString(), "4096", tr135e.getAtiempoRequestNumber(), 
								parametros, passwordModemWIFI);
					}else if (tr135e.getCommercialOperationType().intValue() == ComunInterfaces.operacionParDesactivar){
						retorno = invocador.desconfigurarModem(tr135e.getPhoneNumber().toString(), parametros);
					}
					log.debug("La respuesta del mensaje: "+IdCorrelativo.toString()+" en el web service es: "+retorno);
				}else{
					log.debug("No se envía configuracion para el mensaje: "+IdCorrelativo.toString()+" porque la marca: "+tr135e.getModelModem()
							+" no tiene OUI asociado o velocidad asociados, por favor retifique en la tabla propertiesBD que halla un valor asociado al valor: "
							+ComunInterfaces.OUI+tr135e.getModelModem());
					retorno = "<faultcode><faultstring>No se envía mensaje de configuración porque "+ComunInterfaces.OUI+tr135e.getModelModem()
					+" no esta configurado en la base de datos</faultstring></faultcode>";
				}

				valores[0] = tr135e.getId();
				valores[1] = retorno;
			}catch(Exception ex){
				log.debug("Se presentó un problema al invocar el web service para el mensaje: "+IdCorrelativo.toString()+": error "+ex);
				ex.printStackTrace();
				valores[0] = tr135e.getId();
				valores[1] = null;
			}
			
			
			return (valores);
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
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#recibirConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esCierreActuacion) throws ATiempoAppEx {
		log.debug("Entro a recibirConfiguracionModemAutoinstalacion");
		BackendExecution bExecution = null;
		
		try{
			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME); 
			Mensaje_estadoLocalHome mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			
			bExecution = BackendTraceUtil.initExecution(tr135s, log);
			bExecution.setNumPetAtiempo(new Long(tr135s.getAtiempoRequestNumber()));
			bExecution.setIdMensajeOp(tr135s.getId());
			bExecution.startOperation();
			
			// busca el registro del mensaje
			log.debug("Primero si es mensaje de error lo obviamos.");
			
			Mensaje_estado_stLocal mensaje_estado_st;
			try {
				mensaje_estado_st = mensajeEstadoStLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr135s.getId())));
			} catch (FinderException e1){
				mensaje_estado_st=null;
			}

							
			if (mensaje_estado_st == null){
				log.debug ("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall (tr135s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr135s.getId(),ATiempoAppEx.EXCEPTION);
			}
    
			Peticion_stLocal peticionSTLocal = mensaje_estado_st.getPeticion_st();
			Peticion_stKey peticionSTKey = (Peticion_stKey)peticionSTLocal.getPrimaryKey();
			
			String [] infoAdicional = mensaje_estado_st.getObservaciones().split(":");
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
			ModemKey modemKey = new ModemKey(serialModem,peticionSTKey );
			ModemLocal modemLocal = modemLocalHome.findByPrimaryKey(modemKey);
			
			
			if(tr135s.getErrorCode() != null && !tr135s.getErrorCode().equals("0")){
				log.debug("Respuesta tr-135-s con error: "+tr135s.getId());
				mensaje_estado_st.setCod_estado(new Integer(ComunInterfaces.estadoError));
				
				if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado){
					if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
						
						Collection modemsList = modemLocalHome.findPeticion(peticionSTKey.cod_ave_cd);
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
						modemLocal.setAccion(new Short(new Integer(accionModemAltaMigracion).toString()));
					}
					else{
						modemLocal.setAccion(new Short(new Integer(accionModemNoAction).toString()));
					}
				}else{
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
				}
				
				
				
			}else{
				log.debug("Respuesta tr-135-s sin error"+tr135s.getId());
				
				if (accionRealizada == null || accionRealizada.equals(new Integer(ComunInterfaces.operacionParActivar).toString())
						|| accionRealizada.equals("Nuevo")){
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
				}else if (accionRealizada.equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
					modemLocal.setAccion(new Short(new Integer(accionModemLiberar).toString()));
					modemLocal.remove();
				}else if (accionRealizada.equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
					modemLocal.setAccion(new Short(new Integer(accionModemConfigurado).toString()));
					
					Collection modemInterno = modemLocalHome.findPeticion(peticionSTKey.cod_ave_cd);
					for (Iterator modemIter = modemInterno.iterator();modemIter.hasNext();){
						ModemLocal modem = (ModemLocal)modemIter.next();
						ModemKey modemLocalKey = (ModemKey)modem.getPrimaryKey(); 
						
						if (!modemLocalKey.serial.equals(modemKey.serial)){
							modem.remove();
						}
					}
				}
						
				mensaje_estado_st.setCod_estado(new Integer(ComunInterfaces.estadoOk));
			}
			
			Long idTmpModem = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_MODEM")) ;
			
		    
			Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.create(new Integer(tr135s.getId()));
			mensajeConfACSLocal.setXml(XMLUtilities.marshall (tr135s));
			mensajeConfACSLocal.setAccion(accionRealizada);
			mensajeConfACSLocal.setSerial_modem(serialModem);
			mensajeConfACSLocal.setPeti_numero(peticionSTKey.cod_ave_cd);
			
			Date dateHoy = new Date();
			Timestamp timestampHoy = new Timestamp(dateHoy.getTime());
			mensajeConfACSLocal.setFecha_ingreso(timestampHoy);
			
			if (idMensajePadre != null && !esCierreActuacion){
				String [] propiedadesTR135S = idMensajePadre.split("#");
				
				if(propiedadesTR135S[1].startsWith("IT")){
					ServicioTOASTDelegate toaDelegate = new ServicioTOASTDelegate();
					toaDelegate.enviaActivarModemTOA(propiedadesTR135S[1], propiedadesTR135S[0], tr135s);
				}else{
					this.enviaActivarModemsAgendaSC(propiedadesTR135S[1], propiedadesTR135S[0], tr135s.getId());
				}
				
			}else {
				log.debug("No es un mensaje de agenda por lo tanto no se envia mensaje de respuesta TR717E");
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
			throw new ATiempoAppEx (ATiempoAppEx.FINDER) ;
		}finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#buscarRespuestaConfiguracionModemAutoInstalacion(java.lang.Long, java.lang.Long)
	 */
	public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
		TR135S tr135s = null;
		try{
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome=(Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal msgMoLocal = mensajeEstadoStLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(idMensaje));
			
			
			if (msgMoLocal == null)
				return null;
			
			//Mensaje_estadoLocal msgEstadoLocal = msgMoLocal.getMensaje_estado();
			
			Peticion_stLocal pLocal = peticionStLocalHome.findByPrimaryKey(new Peticion_stKey(idPeticion));
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
	
	public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx {
		log.debug("Entro a recepcionActivarModemsAgendaSC en soltec de la actuación:"+tr717s.getIdSchedule());
		
		try{
			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			
			AgendaSCSTLocal agendaSCSTLocal = null;
			
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			//Obtención de la información del agendamiento
			agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr717s.getIdSchedule());
			
			ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(agendaSCSTLocal.getPeticion_st());
			
			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad()); 
			
			if (agendaSCSTLocal.getEstado().intValue() == ACTUACION_ABIERTA && tr717s.getModemSerial()!=null){
				RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
				
				//Asignación de variables del modem
				ModemSTDTO modem = new ModemSTDTO();
				modem.setNum_peticion(agendaSCSTLocal.getId_peticion_st());
				modem.setMarca(tr717s.getModemBrand());
				modem.setSerial(tr717s.getModemSerial());
				modem.setModelo(tr717s.getModelModem());
				modem.setCod_material(tr717s.getMaterialCode());

				if (tr717s.getModemType()!= null && tr717s.getModemType().equals(ComunInterfaces.MODEM_WIFI)){
					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
				}else if (tr717s.getModemType()!= null && (tr717s.getModemType().equals(ComunInterfaces.MODEM_STD)
						|| tr717s.getModemType().equals(ComunInterfaces.MODEM_STD_2) 
						|| tr717s.getModemType().equals(ComunInterfaces.MODEM_STD1P))){
					modem.setTipo(new Long(ComunInterfaces.identificadorConvencional));
				}else{
					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
				}
				
				/*Collection recursosLineaBasicaCollection = agendaSCSTLocal.getPeticion_st().getRecursos_linea_basica();
				for (Iterator recursosLineaBasicaIterator = recursosLineaBasicaCollection.iterator(); recursosLineaBasicaIterator.hasNext();){
					Recursos_linea_basicaLocal recursosLineaBasicaLocal = (Recursos_linea_basicaLocal)recursosLineaBasicaIterator.next();
					
					if (recursosLineaBasicaLocal.getTelefono_asignado()!=null){
						modem.setTelefono(recursosLineaBasicaLocal.getTelefono_asignado());
					}else{
						modem.setTelefono(new Long("0"));
					}
				}*/
				
				try{
					Peticion_stKey peticionST = new Peticion_stKey(agendaSCSTLocal.getId_peticion_st());
					Peticion_stLocal peticionSTLocal = peticionStLocalHome.findByPrimaryKey(peticionST);
					modem.setTelefono(new Long (peticionSTLocal.getNum_ide_nu()));
				}catch(Exception ex){
					log.debug("Se presento un error consultando el teléfono, se setea 0: "+ex);
					modem.setTelefono(new Long("0"));
				}
				
				
				modem.setAccion(new Integer(ComunInterfaces.accionModemNoAction));
				
				Mensaje_estado_stLocal mensajeEstadoSTLocal= mensajeEstadoStLocalHome.create(new Long(tr717s.getId()));
				mensajeEstadoSTLocal.setPeticion_st(agendaSCSTLocal.getPeticion_st());
				
				/*Familia_producto_servicioLocalHome localHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(PSCode));
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoBALocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));*/
				mensajeEstadoSTLocal.setCod_conector(new Integer(codigoConectorDos));
				mensajeEstadoSTLocal.setFecha_envio(df.format (new java.util.Date ()));
				
				mensajeEstadoSTLocal.setCod_estado(new Integer(estadoEspera));					
				mensajeEstadoSTLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
				
				//Envío el mensaje de autoinstalación
				rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), tr717s.getId()+"#"+tr717s.getIdSchedule(),false);
				//ACSServicioSTBean aCSServicioSTBean =new ACSServicioSTBean();
				//aCSServicioSTBean.enviarAutoConfiguracion(modem,actDto,actDto.getNumeroPeticion(),true);
				//fin Ba naked
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
	
	public void enviaActivarModemsAgendaSC(String idActuacion, String idMensajePeticion, String idMensajePeticionHijo) throws ATiempoAppEx {
		try{
			Peticion_stLocalHome peticionSTLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_conf_ACSLocalHome mensajeEstadoConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			
			boolean tieneErrores = false;
			ArrayList equipos = new ArrayList();
			TR717E tr717e = new TR717E();
			String idModem = new String();
			String codeMaterial = new String();
			String error = new String();
			String descError = new String();
			
			AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(idActuacion);
			Peticion_stLocal peticionSTLocal = agendaSCSTLocal.getPeticion_st();
			
			//Obtención de la actividad
			ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(agendaSCSTLocal.getPeticion_st());
			
			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad()); 
			
			Mensaje_estado_stKey mensajeEstadoSTKey;
			Mensaje_estado_stLocal mensajeEstadoSTLocal = null;
			
			if (idMensajePeticionHijo!=null){
				mensajeEstadoSTKey = new Mensaje_estado_stKey(new Long(idMensajePeticionHijo));
				mensajeEstadoSTLocal = mensajeEstadoStLocalHome.findByPrimaryKey(mensajeEstadoSTKey);
				
				//error =  mensajeEstadoSTLocal.getId_error();
				//descError = mensajeEstadoBALocal.getDesc_error();
			}
			
			//mensajeEstadoBaKey = new Mensaje_estado_baKey(new Long(idMensajePeticion));
			//mensajeEstadoBALocal = mensajeEstadoBaLocalHome.findByPrimaryKey(mensajeEstadoBaKey);
			
			tr717e.setIdSourceSystem(sistemaAtiempo);
			tr717e.setIdSchedule(idActuacion);
			
			Collection modems = peticionSTLocal.getModem();
			
			for (Iterator modemsIterator = modems.iterator(); modemsIterator.hasNext();){
				ModemLocal modemLocal = (ModemLocal) modemsIterator.next();
				
				ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
				idModem = modemKey.serial;
				codeMaterial = modemLocal.getCod_material();
				
				if (modemLocal.getAccion().intValue() != ComunInterfaces.accionModemConfigurado){
					tieneErrores = true;
					break;
				}else{
					Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
					Mensaje_conf_ACSKey mensajeConfACS = new Mensaje_conf_ACSKey(new Integer(idMensajePeticionHijo));
					Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.findByPrimaryKey(mensajeConfACS);
					
					TR135S tr135s = (TR135S) XMLUtilities.unmarshall(mensajeConfACSLocal.getXml());
					if (tr135s.getErrorCode() != null && tr135s.getErrorCode().equals("1")){
						tieneErrores = true;
						
						error = tr135s.getErrorCode();
						descError = tr135s.getErrorDescription();
					}
				}
			}
			
			if (tieneErrores){
				tr717e.setResponse("ERROR");
				tr717e.setError(error);
				tr717e.setErrorMessage(descError);
				tr717e.setResponseDescription(descError);
				tr717e.setModemSerial(idModem);
				tr717e.setMaterialCode(codeMaterial);

				actDto.setObservacion("El modem "+idModem+" presentó errores en la configuración:"+descError, true);
				
				//Mensaje_estadoLocal mensajeError=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				mensajeEstadoSTLocal.setCod_estado(new Integer(ComunInterfaces.estadoError));
				
				actividadEJB.grabarSinTerminar(actDto);
				
			}else{
				tr717e.setResponse("OK");	
				tr717e.setResponseDescription("");
				tr717e.setError("0");
				tr717e.setErrorMessage("");
				tr717e.setModemSerial(idModem);
				tr717e.setMaterialCode(codeMaterial);
				
				Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
				Mensaje_conf_ACSKey mensajeConfACS = new Mensaje_conf_ACSKey(new Integer(idMensajePeticionHijo));
				Mensaje_conf_ACSLocal mensajeConfACSLocal = mensajeConfACSLocalHome.findByPrimaryKey(mensajeConfACS);
				
				if (mensajeConfACSLocal.getAccion().equals("Nuevo") 
						|| mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParActivar).toString())){
					actDto.setObservacion("El modem "+idModem+" se configuró con exito", true);
				}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
					actDto.setObservacion("El modem "+idModem+" se desconfiguró con exito", true);
				}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
					actDto.setObservacion("El modem "+idModem+" fue trasladado con exito", true);
				}
				
				//Mensaje_estadoLocal mensajeOK=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				mensajeEstadoSTLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));
				
				actividadEJB.grabarSinTerminar(actDto);
			}
			
			
			tr717e.setId(idMensajePeticion);
			tr717e.setDestination(sistemaAgendaSC);
			tr717e.setSource(sistemaAtiempo);
			tr717e.setInterfaz("RES_CONFIG_MODEM");
			tr717e.setVersion("1.0");
			
			ActivarModemSCSTMQ activarModemSCSTMQ = new ActivarModemSCSTMQ();
			activarModemSCSTMQ.enviarMensaje(tr717e);
			
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

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviarRefrecarDatos(java.lang.Long)
	 */
	public void enviarRefrecarDatos(Long idPeticion) {
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
			String mascaraLan = recursos_baLocal.getMasc_lan_actual();
			dato = dato+"7. Mascara Lan: "+mascaraLan+";";
			String frame = recursos_baLocal.getFrame_actual();
			dato = dato+"8. FRAME: "+frame+";";
			String tarjeta = recursos_baLocal.getSlot_actual();
			dato = dato+"9. Tarjeta: "+tarjeta+";";
			String VPI_VCI_Cliente = recursos_baLocal.getVpivci_actual();
			dato = dato+"10. VPI/VCI Cliente: "+VPI_VCI_Cliente+";";
			String VPI_VCI_Red = recursos_baLocal.getVpivci_red_actual();
			dato = dato+"11. VPI/VCI Red: "+VPI_VCI_Red+";";
			String usuario = recursos_baLocal.getFather_email_actual();
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

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#isIDPC(java.lang.Long)
	 */
	public boolean isIDPC(Long idPeticion) throws ATiempoAppEx {
		try {
			Peticion_stLocalHome peticionLH = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticion = peticionLH.findByPrimaryKey(new Peticion_stKey(idPeticion));
			boolean retorno = false;
			if(peticion.getNum_ide_nu()!=null && !peticion.getNum_ide_nu().equals("")){
				retorno = true;
			}
			return retorno;
		} catch (Exception e) {
			log.error("Exception:",e);
			throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION,e);
		}
	}
	/**
	 * @param tr701eEquipment
	 * @param camara
	 */
	private void setValoresCamara(TR701EEquipment tr701eEquipment, CamaraLocal camara) throws Exception{
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
	public boolean esLocalidadTOA(Long codAve) throws ATiempoAppEx{
		boolean esLocTOA=false;
		try{
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey peticion_stKey=new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadKey localidadKey=new LocalidadKey(codLoc);
			LocalidadLocal localidadLocal = localidadHome.findByPrimaryKey(localidadKey);
			
			if(localidadLocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA)
				esLocTOA= true;
			
		}catch(FinderException ex){
			log.debug("La localidad no esta entre las catálogadas como TOA");
			return false;
		}catch(NamingException ex){
			log.debug("La localidad no esta entre las catálogadas como TOA");
			return false;
		}
		return esLocTOA;
	}
}
