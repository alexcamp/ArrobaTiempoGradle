package co.com.telefonica.atiempo.soltec.actividades.df.solucion;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
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
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
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
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocalHome;
import co.com.telefonica.atiempo.ejb.eb.MunicipioKey;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocal;
import co.com.telefonica.atiempo.ejb.eb.Parametro_actKey;
import co.com.telefonica.atiempo.ejb.eb.Parametro_actLocal;
import co.com.telefonica.atiempo.ejb.eb.Parametro_actLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloLocalHome;
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
import co.com.telefonica.atiempo.interfaces.atiempo.ErrorRefreshTOA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801SEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811SMaterials;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.EQUIPOSCLIENTE;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.OTHERELEMENT;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.OTHERTYPE;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.TR800E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XABROADBANDDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XAREPAIRINFO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XATELEPHONEDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800E.XATVDATA;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR800S.TR800SParser;
import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaKey;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked.ACSServicioDelegate;
import co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.AgendaSCSTLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocalHome;
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
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.TematicoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoLocal;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBABean;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.RefrescarRecursosSTTOATVMQ;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.servicios.ConfiguracionModemAutoInstalacion;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTInterfaz;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.CommunicatorWSDelegate;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionRefrescarRecursosTVST;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosActivarBAST;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosActivarST;
import co.com.telefonica.atiempo.vpistbba.TOA.GestionServiciosSTBBAST;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocalHome;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.soltec.grabacion.GrabadorSolTecLocal;
import com.telefonica_chile.soltec.grabacion.GrabadorSolTecLocalHome;



/**
 * Bean implementation class for Enterprise Bean: ASTServicioTOA
 */
public class ASTServicioTOABean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements ServicioTOASTInterfaz{

	//Se crean variables requridas para el proceso
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private TR800E tr800e = new TR800E();
	private OTHERTYPE otherType = new OTHERTYPE();
	private OTHERELEMENT otherElement = new OTHERELEMENT();
	private Peticion_stLocalHome peticion_stLocalHome;
//	private Peticion_stLocal peticion_stLocal;
	private SubsegmentoKey subsegmentoKey;
	private SubsegmentoLocal subsegmentoLocal;
	private SegmentoKey segmentoKey;
	private SegmentoLocal segmentoLocal;
	private SubsegmentoLocalHome subsegmentoLocalHome;
	private SegmentoLocalHome segmentoLocalHome;
	private Peticion_stKey peticion_stKey;
	private Peticion_atisLocal peticion_atisLocal;
	private Peticion_atisLocalHome peticion_atisLocalHome;
	private Bitacora_peticionLocalHome bitacora;
	private Collection bitacoraLocal;
	private ArrayList datosOpcionalesArray;
	private ArrayList otrosDatosArray;
	private Recursos_linea_basicaLocal rlb;
	private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome; 
	private Long idPetActuacion;
	private XATELEPHONEDATA xaTelephoneData;
	private RecursosBADelegate recursosBADelegate;
	private XABROADBANDDATA xaBroadBandData; 
	private Producto_servicioLocal producto_servicioLocal;
	private Prueba_lineaLocal prueba_lineaLocal;
	private Prueba_lineaLocalHome prueba_lineaLocalHome;
	private XATVDATA xaTvData;
	private EQUIPOSCLIENTE equiposCliente;
	private PeticionesDelegate peticionesDelegate;
	private static int cantDecosInst = 0;
	private static int cantDecosDesins = 0;
	private AgendaSCSTLocalHome agendaSCSTLocalHome;
	private XAREPAIRINFO xaRepairInfo;
	private Codigo_stLocalHome codigoSTLocalHome;
	private Codigo_stLocal codigoSTLocal;
	private ArrayList xa_number_decoders;
	private BintegradaLocalHome bintegradaLocalHome;
	private DBManager dbSeq ;
	private SimpleDateFormat df;
	private Long idAplicacion;
	private Mensaje_estado_stLocalHome mensaje_estado_stHome;
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#crearActuacionTOA(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
		df = new SimpleDateFormat ("yyyy/MM/dd") ;
	}
	
	private void inicializarVariables (){
		try {
			peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			segmentoLocalHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			subsegmentoLocalHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
			agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			codigoSTLocalHome=(Codigo_stLocalHome)HomeFactory.getHome(Codigo_stLocalHome.JNDI_NAME);
			Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			prueba_lineaLocalHome=(Prueba_lineaLocalHome) HomeFactory.getHome(Prueba_lineaLocalHome.JNDI_NAME);
			xaTelephoneData = new XATELEPHONEDATA();
			xaBroadBandData = new XABROADBANDDATA();
			xaTvData = new XATVDATA();
			equiposCliente = new EQUIPOSCLIENTE();
			xaRepairInfo = new XAREPAIRINFO();
			datosOpcionalesArray = new ArrayList();
			idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
			bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			mensaje_estado_stHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	public void crearActuacionTOA(Long idActuacion, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Auto-generated method stub
//		Se instancias las variables Globales
		try {
			peticion_stLocalHome = null;
			inicializarVariables ();
			//se valida si la el objeto ActividadEJBDTO esta null, este puede llegar null desde un boton de la bandeja planta externa TOA
			if (act == null){
				//asignamos el numero de peticion que se envia desde el boton
				idPetActuacion=idActuacion;
			}else{
				//si no se asigna en numero de actuacion desde el objeto
				idPetActuacion=act.getNumeroPeticion();
			}
			log.debug("Ingresa a la acreacion de Actuacion para TOA "+ idPetActuacion);
			// se inicilizan consultas en las tablas principàles
			boolean noTieneCentral = false;
			String descFamilia="";
			rlb = null;
			Recursos_baLocal rba=null;
			
			peticionesDelegate = new PeticionesDelegate();
			peticion_stKey = new Peticion_stKey(idPetActuacion);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			peticionesDelegate = new PeticionesDelegate();
			Collection pruebas= prueba_lineaLocalHome.findByPeticion(idPetActuacion);
			Collection recursosLBLista= peticion_stLocal.getRecursos_linea_basica();
			Collection recursosBALista= peticion_stLocal.getRecursos_ba();

			// se valida si el objeto de la activida viene null para poder asignarle la informacion de la actividad
			if (act == null){
				log.debug("act null");
				act = recuperaActividadDeBandejaIntegrada(peticion_stLocal);
			}
			// se valida si hay recursoos de linea para asignarlas en un local
			if(recursosLBLista!=null&&recursosLBLista.size()>0){
				rlb=(Recursos_linea_basicaLocal)recursosLBLista.iterator().next();
			}
			// se valida si hay recursos de banda ancha para asginarlas en un local
			if(recursosBALista!=null&&recursosBALista.size()>0){
				rba=(Recursos_baLocal)recursosBALista.iterator().next();
			}
//inicio mapeo campos ACTUACION---------------------------------------------------------------------------------------------------------------------------
			//Inicio - Asignacion campos TOA - Juan David Grisales 2015-06-17
			log.debug("inicia mapeo");
//			tr800e.setXA_FAMILIA(peticion_stLocal.getIde_pro_cmr_cd());
			tr800e.setXA_SOURCE_SYSTEM(sistemaAtiempo);
			tr800e.setCname(peticion_stLocal.getNom_rte_sn());
			tr800e.setXA_CUSTOMER_ID_TYPE(peticion_stLocal.getTip_doc_rte_cd());
			//INICIO - Customer_number
			if(peticion_stLocal.getNum_doc_rte_nu()!=null && peticion_stLocal.getNum_doc_rte_nu().length()>0){
				//tr701ECustomer.setCode(new Long(peticion_stLocal.getNum_doc_rte_nu()));
				tr800e.setCustomer_number(peticion_stLocal.getNum_doc_rte_nu());
			}else{
				//tr701ECustomer.setCode(new Long(0));
				tr800e.setCustomer_number("0");
			}
			//FIN - Customer_number
			//INICIO - XA_CUSTOMER_SUBSEGMENT
			tr800e.setXA_CUSTOMER_SUBSEGMENT(peticion_stLocal.getCod_sbg_cta_cd().toString());
			
			SubsegmentoKey subsegmentoKey = new SubsegmentoKey(peticion_stLocal.getCod_sbg_cta_cd());
			SubsegmentoLocal subsegmentoLocal = subsegmentoLocalHome.findByPrimaryKey(subsegmentoKey);
			tr800e.setXA_CUSTOMER_SUBSEGMENT(subsegmentoLocal.getDescripcion());           
			//FIN - XA_CUSTOMER_SUBSEGMENT
			//INICIO - XA_CUSTOMER_SEGMENT
			SegmentoKey segmentoKey = new SegmentoKey(peticion_stLocal.getCod_sgm_cta_cd());
			SegmentoLocal segmentoLocal = segmentoLocalHome.findByPrimaryKey(segmentoKey);
			
			String descripcionSegmento=segmentoLocal.getSegm_descripcion();
			if (peticion_stLocal.getCod_ctz_cd()!=null && peticion_stLocal.getCod_ctz_cd().equalsIgnoreCase("R"))
				tr800e.setXA_CUSTOMER_SEGMENT(descripcionSegmento+"_"+peticion_stLocal.getCod_ctz_cd());	
			else if (peticion_stLocal.getCod_pra_ave_cd()!=null && peticion_stLocal.getCod_pra_ave_cd().equalsIgnoreCase("5"))
				tr800e.setXA_CUSTOMER_SEGMENT(descripcionSegmento+"_S");
			else
				tr800e.setXA_CUSTOMER_SEGMENT(descripcionSegmento);
			//FIN - XA_CUSTOMER_SEGMENT
			if(peticion_stLocal.getTel_cot_ds() != null)
				tr800e.setXA_DAT_AGDMTO_1(peticion_stLocal.getTel_cot_ds());//Se asigna telefono de contacto: Cesar Remigio
			else
				tr800e.setXA_DAT_AGDMTO_1("0");
			if(peticion_stLocal.getSeg_tel_cot_sn() != null)
				tr800e.setXA_DAT_AGDMTO_2(peticion_stLocal.getSeg_tel_cot_sn());//Asignación pendiente
			else
				tr800e.setXA_DAT_AGDMTO_2("0");
			tr800e.setXA_CUSTOMER_VIP("0");//Asignación pendiente
			tr800e.setXA_DAT_OP_CLIENT(null);//Asignación pendiente
			//INICIO - XA_CONTACT_NAME
			if (peticion_stLocal.getNom_psn_cot_no() != null && !peticion_stLocal.getNom_psn_cot_no().equals(""))
				tr800e.setXA_CONTACT_NAME(peticion_stLocal.getNom_psn_cot_no());
			else
				tr800e.setXA_CONTACT_NAME(peticion_stLocal.getNom_rte_sn());
			//FIN - XA_CONTACT_NAME
			//INICIO - cell
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
					if(peticion_stLocal.getNum_ide_nu()!=null&&!peticion_stLocal.getNum_ide_nu().equals(""))
						telefonoContactoSinLetras=peticion_stLocal.getNum_ide_nu().replaceAll("[^\\d]","");
				}                                              
			}                       
			
			telefonoContactMedia=telefonoContactoSinLetras;
			log.debug("El número de teléfono de contacto sin letras es: "+telefonoContactoSinLetras);
			tr800e.setCell(telefonoContactoSinLetras);
			//FIN - cell
			tr800e.setXA_CONTACT_PHONE_NUMBER_2(peticion_stLocal.getSeg_tel_cot_sn());//Pendiente de revisión
			tr800e.setXA_CONTACT_PHONE_NUMBER_3(telefonoContactoSinLetras);
			tr800e.setXA_CONTACT_PHONE_NUMBER_4("0");//Asignación pendiente
			tr800e.setXA_CONTACT_PHONE_NUMBER_5("0");//Asignación pendiente
			tr800e.setXA_CONTACT_PHONE_NUMBER_6("0");//Asignación pendiente
			tr800e.setEmail("");//Asignación pendiente
			//Inicio - Phone
			if (rlb != null && rlb.getTelefono_asignado() != null)
				tr800e.setPhone(""+rlb.getTelefono_asignado());
			else
				tr800e.setPhone("");
			//Fin - phone
			log.debug("1-------------------------------------------");
			tr800e.setXA_DAT_OP_CONTACT(null);//Asignación pendiente
			tr800e.setAddress(peticion_stLocal.getNom_cal_ds()+" "+peticion_stLocal.getNum_cal_nu()+" "+peticion_stLocal.getDsc_cmp_pri_ds());
			//INICIO - XA_CITY_CODE
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			String codLoc=peticion_stLocal.getCod_loc();
			LocalidadKey localidadKey = new LocalidadKey(codLoc);
			LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(localidadKey);
			
			MunicipioLocal municipioLocal=localidadLocal.getMunicipio();
			MunicipioKey municipioKey=(MunicipioKey)municipioLocal.getPrimaryKey();
			
			//FIN - Acoord_x
			//INICIO - Aworktype_label - XA_WORK_TYPE
																//setTipoAccionTOA(peticion_stLocal);
			//FIN - Aworktype_label - XA_WORK_TYPE
			tr800e.setXA_DESCRIPTION(null);//Asignación Pendiente
			tr800e.setXA_DAT_OP_TPACTIVIDAD(null);//Asignación Pendiente
			//Inicio - id_actuacion
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat formatterRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatterDate= new SimpleDateFormat("yyyy-MM-dd");
			Timestamp fechaReagendamiento;//es enviado como Parametro de la funcion creacionActuacionAgendaSC
			String tipoOC;//tambien es enviado como parametro en la funcion creacionActuacionAgendaSC
			Date dateAhora=new Date();			
			//validar por que se hace esta validacion
			Timestamp timestampAhora=new Timestamp(dateAhora.getTime());
			tr800e.setId_actuacion("IT"+idPetActuacion.toString()+"-"+formatter.format(dateAhora));
//			se agrega el formato a la actuacion que se pide AT
			AgendaSCSTLocal agendaSCSTLocal=agendaSCSTLocalHome.create(tr800e.getId_actuacion());
			agendaSCSTLocal.setPeticion_st(peticion_stLocal);			
			agendaSCSTLocal.setId_peticion_st(idPetActuacion);
			agendaSCSTLocal.setEstado(new Integer(ACTUACION_ABIERTA));
			Date hoy=new Date();
			agendaSCSTLocal.setFecha_ingreso(new Timestamp(hoy.getTime()));
			log.debug("5-------------------------------------------");
			
			
			//Fin - id_actuacion
			tr800e.setXA_ORDER_ATIS(peticion_stKey.cod_ave_cd.toString());
			tr800e.setXA_ORDER_NUMBER(idPetActuacion.toString());
			tr800e.setXA_CREATION_DATE(formatterDate.format(dateAhora));
			//este tag se encuentra vacio tr701EDateData.setBreakdownCommitmentDate("");
			tr800e.setDate(formatterDate.format(peticion_stLocal.getFec_apt_ave_ts()));
			//INICIO - XA_DAT_OP_DATO_CITA
			//logica para la generacion de el arreglo XA_DAT_OP_DATO_CITA el cual contiene arreglos de propriedades junto con su key y value
			datosOpcionalesArray = new ArrayList();
			otherElement.setKey("");
			otherElement.setValue("");
			datosOpcionalesArray.add(otherElement.getKey());
			datosOpcionalesArray.add(otherElement.getValue());
			otherType.setPropiedad(datosOpcionalesArray);
			tr800e.setXA_DAT_OP_DATO_CITA(otherType);
			//FIN - XA_DAT_OP_DATO_CITA
			tr800e.setXA_PROJECT_CODE("");
			tr800e.setXA_REINJECTED(null);//Solo aplica para VPI
			tr800e.setXA_DAT_OP_DATO_INST(null);//no es obligatorio a la fecha 18/06/2015
//			tr800e.setXA_MASSIVE_TYPE(peticion_stLocal.getCod_ave_mas_cd().toString());
			tr800e.setXA_REITERATION(null);//Asignaión pendiente
			tr800e.setXA_YOUNG_REPAIR(null);//Asignaión pendiente
			//INICIO - XA_DAT_OP_DATO_AVER
			//logica para la generacion de el arregloe XA_DAT_OP_DATO_AVER el cual contiene arreglos de propriedades junto con su key y value
			datosOpcionalesArray = new ArrayList();
			otherElement.setKey("");
			otherElement.setValue("");
			datosOpcionalesArray.add(otherElement.getKey());
			datosOpcionalesArray.add(otherElement.getValue());
			otherType.setPropiedad(datosOpcionalesArray);
			tr800e.setXA_DAT_OP_DATO_AVER(otherType);
			//FIN - XA_DAT_OP_DATO_AVER
			tr800e.setXA_POST_SALES_CODE(null);//asignación pendiente
			//Fin - Asignacion campos TOA - Juan David Grisales 2015-06-17
			log.debug("6-------------------------------------------");
//mapeo campos arreglo XA_REPAIR_INFO			
			if(peticion_stLocal.getIde_pro_cmr_cd().equals(ComunInterfaces.LBST)){
				descFamilia=ComunInterfaces.LB;//Se utiliza esto porque agenda pide LB y en soltec Línea Básica se registra como L.
			}else{
				descFamilia=peticion_stLocal.getIde_pro_cmr_cd();
			}

			xaRepairInfo.setCodigo_Apertura(peticion_stLocal.getCod_apt_ave_cd());
			xaRepairInfo.setDiagnostico_Primer_Nivel(peticion_stLocal.getDsc_rpt_pru_ds());
			xaRepairInfo.setDiagnostico_Segundo_Nivel(peticion_stLocal.getObs_cit_ds());
			xaRepairInfo.setCodigo_Prioridad("1");
			xaRepairInfo.setCategoria_Averia(peticion_stKey.cod_ave_cd.toString());
			xaRepairInfo.setObservaciones_Apertura(peticion_stLocal.getObs_ave_ds());
			
		//	for(Iterator itpruebas=pruebas.iterator();itpruebas.hasNext();){
			//xaRepairInfo.set
			//}
			
			xaRepairInfo.setObservaciones_Diagnostico_Segundo_Nivel(peticion_stLocal.getObs_cit_ds());
			
			//logica para la generacion de el arregloe setOtros_Telephon_Data el cual contiene arreglos de propriedades junto con su key y value
			datosOpcionalesArray = new ArrayList();
			otherElement.setKey("");
			otherElement.setValue("");
			datosOpcionalesArray.add(otherElement.getKey());
			datosOpcionalesArray.add(otherElement.getValue());
			otherType.setPropiedad(datosOpcionalesArray);
			xaRepairInfo.setOtros_Repair(otherType);
			log.debug("7-------------------------------------------");
			tr800e.setXA_NOTES(notaCliente(peticion_stLocal));
			tr800e.setXA_REPAIR_INFO(xaRepairInfo);
			//fin arreglo setOtros_Telephon_Data
//FIN MAPEO CAMPOS XA_REAPIR_INFO	
//Seeteo del tag XA_CENTRAL 		
			if(rlb!=null){
				if(rlb.getCentral()!=null && rlb.getCentral().intValue()!=0){
					tr800e.setXA_CENTRAL(""+rlb.getCentral());
				}else{
					tr800e.setXA_CENTRAL("");
					noTieneCentral = true;
				}
//			fin seteo tag XA_BOX_TYPE 
//			seteo del arreglo XA_TELEPHONE_DATA-------------------------------------------------------------------------------------------------
				log.debug("8-------------------------------------------");    
				if(rlb.getCentral()!=null && rlb.getCentral().intValue()!=0){
							    	xaTelephoneData.setCentral(""+rlb.getCentral());
							    }else{
							    	xaTelephoneData.setCentral("");
							    	//noTieneCentral = true;
							    }
							    if (rlb.getTelefono_asignado()!= null){
							    	xaTelephoneData.setTelefono(rlb.getTelefono_asignado().toString());
							    }else{
							    	xaTelephoneData.setTelefono("");
							    }
							    if (rlb.getLen()!=null){
							    	 xaTelephoneData.setLen(rlb.getLen());
							    }else {
							    	 xaTelephoneData.setLen("");
							    }
								if (rlb.getPosicion_horizontal()!=null){
									 xaTelephoneData.setPosicion_Horizontal(rlb.getPosicion_horizontal());
								}else{
									xaTelephoneData.setPosicion_Horizontal("");
								}
								if (rlb.getDist_prim()!=null){
									xaTelephoneData.setDistribuidor(rlb.getDist_prim().toString());
									
								}else{
									xaTelephoneData.setDistribuidor("");
								}
								if (rlb.getDesc_dist_prim()!=null){
									 xaTelephoneData.setDescripcion_Distribuidor(rlb.getDesc_dist_prim());
								}else{
									xaTelephoneData.setDescripcion_Distribuidor("");
								}
								
								if (rlb.getDir_distribuidor()!=null){
									xaTelephoneData.setDireccion_Distribuidor(rlb.getDir_distribuidor());
								}else{
									xaTelephoneData.setDireccion_Distribuidor("");
								}
								if (rlb.getListon()!=null){
									xaTelephoneData.setListon(rlb.getListon());
								}else{
									xaTelephoneData.setListon("");
								}
								if (rlb.getPar_liston()!=null){
									xaTelephoneData.setPar_liston(rlb.getPar_liston().toString());
								}else{
									xaTelephoneData.setPar_liston("");
								}
								if(rlb.getCable()!=null){
									 xaTelephoneData.setCable(rlb.getCable().toString());
								}else{
									 xaTelephoneData.setCable("");
								}
								if(rlb.getPar_cable()!=null){
									 xaTelephoneData.setPar_Cable(""+rlb.getPar_cable());
								}else{
									 xaTelephoneData.setPar_Cable("");
								}
								if(rlb.getArmario()!=null){
									xaTelephoneData.setArmario(rlb.getArmario());
								}else{
									xaTelephoneData.setArmario("");
								}
								if(rlb.getDir_armario()!=null){
									xaTelephoneData.setDireccion_Armario(rlb.getDir_armario());
								}else{
									xaTelephoneData.setDireccion_Armario("");
								}
								if(rlb.getCaja()!=null){
									xaTelephoneData.setCaja(rlb.getCaja());
								}else{
									xaTelephoneData.setCaja("");
								}
								if(rlb.getPar_caja()!=null){
									xaTelephoneData.setPar_caja(""+rlb.getPar_caja());
								}else{
									xaTelephoneData.setPar_caja("");
								}
								if(rlb.getDir_caja()!=null){
									 xaTelephoneData.setDireccion_caja(rlb.getDir_caja());
								}else{
									 xaTelephoneData.setDireccion_caja("");
								}
							    
								log.debug("9-------------------------------------------");   
							    if(rlb.getZonas_atendimiento()!=null&&rlb.getZonas_atendimiento().size()>0){
									Zonas_atendimientoLocal zonas_atendimientoLocal = (Zonas_atendimientoLocal) rlb.getZonas_atendimiento().iterator().next();
									Zonas_atendimientoKey zonas_atendimientoKey = (Zonas_atendimientoKey)zonas_atendimientoLocal.getPrimaryKey();
									xaTelephoneData.setZona_cobertura(""+zonas_atendimientoKey.id);
								}
								else{
									xaTelephoneData.setZona_cobertura("");
								}
						    }else{
								if(descFamilia.equals(ComunInterfaces.BA)||descFamilia.equals(ComunInterfaces.LB)){
									noTieneCentral = true;
								}
							}
						 
//			logica para la generacion de el arregloe setOtros_Telephon_Data el cual contiene arreglos de propriedades junto con su key y value
							datosOpcionalesArray = new ArrayList();
							otherElement.setKey("");
							otherElement.setValue("");
							datosOpcionalesArray.add(otherElement.getKey());
							datosOpcionalesArray.add(otherElement.getValue());
							otherType.setPropiedad(datosOpcionalesArray);
							xaTelephoneData.setOtros_Telephon_Data(otherType);
							
							tr800e.setXA_TELEPHONE_DATA(xaTelephoneData);
//			fin arreglo setOtros_Telephon_Data
//			Fin seteo Arreglo XA_TELEPHONE_DATA----------------------------------------------------------------------------------------------------
							
//			INICIO ARREGLO XA_BROADBAND_DATA
							/*Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/
							try{
								Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
								Producto_servicioKey productoServicioKey = new Producto_servicioKey(peticion_stLocal.getCod_pro_ser_cd());
								Producto_servicioLocal productoServicioLocal = productoServicioLocalHome.findByPrimaryKey(productoServicioKey);
											
								if (productoServicioLocal.getVelocidad() != null && productoServicioLocal.getVelocidad().length() > 0){
									xaBroadBandData.setVelocidad(productoServicioLocal.getVelocidad());
								}else{
									xaBroadBandData.setVelocidad("");
								}
							}catch(FinderException ex){
								log.debug("No se encontro información relacionada con la velocidad del PS, se setea valor vacío");
								xaBroadBandData.setVelocidad("");
							}
							
							log.debug("10-------------------------------------------");
							/*FIN Inclusión del envío de la velocidad cuando el PS la tiene definida en la tabla PRODUCTO_SERVICIO*/
							
							xaBroadBandData.setTipo_IP("");//tr701ETechnicalData.setIpType("");
							
							if (rba != null){
								if(rba.getPuerto_actual()!=null){
									xaBroadBandData.setPuerto(rba.getPuerto_actual());
								}else{
									xaBroadBandData.setPuerto("");
								}
								if(rba.getPost_actual()!=null){
									xaBroadBandData.setPOTs(rba.getPost_actual());
								}else{
									xaBroadBandData.setPOTs("");
								}
								if(rba.getAdsl_actual()!=null){
									String adslAux[] = rba.getAdsl_actual().split("-");
									xaBroadBandData.setADSL(rba.getAdsl_actual());
									xaBroadBandData.setRACK(adslAux[0]);
								}else{
									xaBroadBandData.setADSL("");
								}
								if(rba.getDir_ip_dslam_actual()!=null){
									xaBroadBandData.setDireccion_IP_DISLAM(rba.getDir_ip_dslam_actual());
								}else{
									xaBroadBandData.setDireccion_IP_DISLAM("");
								}
								if(rba.getDir_ip_wan_actual()!=null){
									xaBroadBandData.setDireccion_IP_WAN(rba.getDir_ip_wan_actual());
								}else{
									xaBroadBandData.setDireccion_IP_WAN("");
								}
								if(rba.getDir_ip_lan_actual()!=null){
									xaBroadBandData.setDireccion_IP_LAN(rba.getDir_ip_lan_actual());
								}else{
									xaBroadBandData.setDireccion_IP_LAN("");
								}
								if(rba.getMasc_lan_actual()!=null){
									xaBroadBandData.setMascara_LAN(rba.getMasc_lan_actual());
								}else{
									xaBroadBandData.setMascara_LAN("");
								}
								if(rba.getFrame_actual()!=null){
									xaBroadBandData.setFrame(rba.getFrame_actual());
									xaBroadBandData.setSUBRACK(rba.getFrame_actual());
								}else{
									xaBroadBandData.setFrame("");
								}
								if(rba.getSlot_actual()!=null){
									xaBroadBandData.setTarjeta_Slot(rba.getSlot_actual());
								}else{
									xaBroadBandData.setTarjeta_Slot("");
								}
								if(rba.getVpivci_actual()!=null){
									xaBroadBandData.setVPI_VCI_Cliente(rba.getVpivci_actual());
								}else{
									xaBroadBandData.setVPI_VCI_Cliente("");
								}
								if(rba.getVpivci_red_actual()!=null){
									xaBroadBandData.setVPI_VCI_Red(rba.getVpivci_red_actual());
								}else{
									xaBroadBandData.setVPI_VCI_Red("");
								}
								if(rba.getFather_email_actual()!=null){
									xaBroadBandData.setUsuario_Acceso(rba.getFather_email_actual());
								}else{
									xaBroadBandData.setUsuario_Acceso("");
								}
							}
							log.debug("11-------------------------------------------");
//			logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
							datosOpcionalesArray = new ArrayList();
							otherElement.setKey("");
							otherElement.setValue("");
							datosOpcionalesArray.add(otherElement.getKey());
							datosOpcionalesArray.add(otherElement.getValue());
							otherType.setPropiedad(datosOpcionalesArray);
							xaBroadBandData.setOtros_Datos_BA(otherType);
							tr800e.setXA_BROADBAND_DATA(xaBroadBandData);
//			fin arreglo setOtros_Datos_BA			
//			FIN ARREGLO XA_BROADBAND_DATA
//			seteo TAG XA_ID_PC_TV				
							datosOpcionalesArray = new ArrayList();
//							datosOpcionalesArray.add("0");
							//tr800e.setXA_ID_PCTV(datosOpcionalesArray);
							if(peticion_stLocal.getNum_ide_nu_tv()!=null && peticion_stLocal.getNum_ide_nu_tv().length()>0){
								datosOpcionalesArray.add(peticion_stLocal.getNum_ide_nu_tv());
								tr800e.setXA_ID_PCTV(datosOpcionalesArray);
							}else{
								datosOpcionalesArray.add("0");
								tr800e.setXA_ID_PCTV(datosOpcionalesArray);
							}
//			fin SETEO TAG XA_ID_PC_TV
//			inicio mapeo arreglo XA_TV_DATA
							//seteo TAG TEMATICO
							xaTvData.setTematicos(listaTematicos(peticion_stLocal));
							//FIN SETEO ag tematico
							//SETEO TAG DECOS_TYPE
							xaTvData.setTipo_Decos(tiposDeDeco(peticion_stLocal));
							//FIN SETEO DECOS_TYPE
							//SETEO TAG CANTIDAD_DECOS
							xaTvData.setCantidad_Decos(cantidadDeDecos(peticion_stLocal));
							//FIN SETEO TAG CANTIDAD_DECOS
							log.debug("2-------------------------------------------");
							//logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
							otrosDatosArray = new ArrayList();
							otherElement.setKey("");
							otherElement.setValue("");
							otrosDatosArray.add(otherElement.getKey());
							otrosDatosArray.add(otherElement.getValue());
							otherType.setPropiedad(otrosDatosArray);
							xaTvData.setOtros_Datos_TV(otherType);
							tr800e.setXA_BROADBAND_DATA(xaBroadBandData);
							//fin arreglo setOtros_Datos_BA	
							
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add(xaTvData);
							tr800e.setXA_TV_DATA(datosOpcionalesArray);
//			XA_TV_DATA
							
							log.debug("13-------------------------------------------");
//			Areeglo equipos_cliente		
							tr800e.setEQUIPOS_CLIENTE(equiposCliente(peticion_stLocal));
							log.debug("14-------------------------------------------");
//			fin Arreglo Equipos_Cliente
//			Fin seteo @dcardena----------------------------------------------------------------------------------------------------
							setearDiireccion(peticion_stLocal);
							setTipoAccionTOA(peticion_stLocal);
							finalizarProceso(tr800e, act);
					} catch (FinderException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("A ocurrido un error al consultar la averia" +idPetActuacion+ " " +e);
					} catch (NamingException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("A ocurrido un error al levantar el home la averia" +idPetActuacion+ " " +e);
					} catch (CreateException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("A ocurrido un error al crear actuacion" +idPetActuacion+ " " +e);
					} 
				}

	private void setearDiireccion(Peticion_stLocal peticion_stLocal2) {
		
		// TODO Apéndice de método generado automáticamente
		try {
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticion_stLocal2.getCod_loc())).getPrimaryKey();
			LocalidadLocal localidadLocal=localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticion_stLocal2.getCod_loc()));				
			
			DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			DepartamentoKey departamentoKey= (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticion_stLocal2.getCod_dpt())).getPrimaryKey();
			DepartamentoLocal departamentoLocal=departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticion_stLocal2.getCod_dpt()));				
				
			//DepartamentoLocal departamento = peticion_stLocal2.getFk_02_departamento();
			//DepartamentoKey departamentoKey = (DepartamentoKey) departamento.getPrimaryKey();
			
			xa_number_decoders=  new ArrayList();
			tr800e.setXA_CITY_CODE(localidadKey.cod_loc);
			tr800e.setCity(localidadLocal.getDescripcion_localidad());
			tr800e.setXA_STATE_CODE(departamentoKey.cod_dpt);
			tr800e.setState(departamentoLocal.getDescripcion_departamento());
			tr800e.setXA_NEIGHBORHOOD(peticion_stLocal2.getObs_ave_mas_ds());
			tr800e.setXA_QUADRANT("");
			if (peticion_stLocal2.getCoordenada_x() != null && peticion_stLocal2.getCoordenada_x().length()>0){
				tr800e.setAcoord_x(Double.parseDouble(peticion_stLocal2.getCoordenada_x()));
			}else{
				log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
				tr800e.setAcoord_x(0.0);
			}
			if (peticion_stLocal2.getCoordenada_x() != null && peticion_stLocal2.getCoordenada_x().length()>0){
				tr800e.setAcoord_x(Double.parseDouble(peticion_stLocal2.getCoordenada_x()));
			}else{
				log.debug("No se encuatra especificada la coordenada X se setea 0 por defecto para evitar conflicots con agenda SC");
				tr800e.setAcoord_x(0.0);
			}
			if (peticion_stLocal2.getCoordenada_y() != null && peticion_stLocal2.getCoordenada_y().length()>0){
				tr800e.setAcoord_y(Double.parseDouble(peticion_stLocal2.getCoordenada_y()));
			}else{
				log.debug("No se encuatra especificada la coordenada Y se setea 0 por defecto para evitar conflicots con agenda SC");
				tr800e.setAcoord_y(0.0);
			}
			Collection recursosLineaArray = peticion_stLocal2.getRecursos_linea_basica();
			Iterator iter = recursosLineaArray.iterator();
			Recursos_linea_basicaLocal recursosLineaLocal = null;
			
			if(iter != null && iter.hasNext()){
				recursosLineaLocal = (Recursos_linea_basicaLocal) iter.next();
				if(recursosLineaLocal.getDist_prim() != null && !recursosLineaLocal.getDist_prim().equals("")
						&& recursosLineaLocal.getArmario() != null && !recursosLineaLocal.getArmario().equals(""))
					tr800e.setXA_WORK_ZONE_KEY(recursosLineaLocal.getDist_prim()+"_"+recursosLineaLocal.getArmario());
				
			}else
				tr800e.setXA_WORK_ZONE_KEY(localidadLocal.getDescripcion_localidad()+"-"+localidadKey.cod_loc+"_"+peticion_stLocal2.getObs_ave_mas_ds());
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("error Direccion: "+e);
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("error Direccion: "+e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("error Direccion: "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("error Direccion: "+e);
		}
			
	}
	
	
	//funcion que calcula el tag nota para SOLTEC
	private String notaCliente (Peticion_stLocal peticion_stLocal) throws ATiempoAppEx, FinderException, NamingException{
		String descripcion = "";
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
			pruebaLineaLocal = prueba_lineaLocalHome.findByMaxFecha(idPetActuacion);
					
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
					xaRepairInfo.setDiagnostico_Segundo_Nivel(catalagoPruebaLineaLocal.getDescripcion());
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
						xaRepairInfo.setObservaciones_Diagnostico_Segundo_Nivel(pruebaLineaLocal.getObservacion());
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
							Collection tematicos = tematicoLocalHome.findByPeticion(idPetActuacion);
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
					return notaDescripcion;
	}

				
	private boolean cantidadDecosHC (Long petiNum,int famOpLocal,int cantDesisntalar) throws NamingException, FinderException{
					
		Deco_tarjetaLocalHome  deco_tarjetaLocalHome = (Deco_tarjetaLocalHome)HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
		Collection decosInstalados = deco_tarjetaLocalHome.findPeticion(petiNum);
		String tipodeco="";
		int cantidadDecos=0;
		if(famOpLocal==ComunInterfaces.familiaDecoHDTV){
			
			tipodeco="HD";
		}else if(famOpLocal==ComunInterfaces.familiaDecoPVRTV){
			
			tipodeco="PVR";
		}else if(famOpLocal==ComunInterfaces.familiaDecoTV){
			
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
		if(cantDesisntalar>cantidadDecos){	
			log.debug("el deco de tipo "+tipodeco+" esta descompensado se envia nota");
			cantDecosInst = cantidadDecos;
			cantDecosDesins=cantDesisntalar;
			return true;
		}else{
			log.debug("el deco de tipo "+tipodeco+" no esta descompensado");
			return false;

		}
	}
				
				private ArrayList equiposCliente(Peticion_stLocal peticion_stLocal) throws NamingException{
					log.debug("1euipos-------------------------------------------");
					Collection decosTarjetaPeticionList = peticion_stLocal.getDeco_tarjeta();
					Collection listaEquipos = new ArrayList();
					ElementoLocal elementoLocal =null;
					Deco_Tarjeta_Info_SapLocal infoSAPTmp = null;
					Deco_Tarjeta_Info_SapKey keyInfoSAPTmp = null;
					Deco_Tarjeta_Info_SapLocal infoSAPCard = null;
					Deco_Tarjeta_Info_SapKey keyInfoSAPCard = null;
					Deco_Tarjeta_Info_SapLocalHome deco_tar_inf_sapLocalHome=(Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
					
					for (Iterator decosTarjetasPeticionIter = decosTarjetaPeticionList.iterator(); decosTarjetasPeticionIter.hasNext();){
						Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosTarjetasPeticionIter.next();
						Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
						//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
						equiposCliente = new EQUIPOSCLIENTE();
						
						if (decoTarjetaLocal.getSerial_deco() != null && decoTarjetaLocal.getSerial_deco().length() > 0 && !decoTarjetaLocal.getSerial_deco().equals("0")){

							equiposCliente.setInvsn(decoTarjetaLocal.getSerial_deco());
							equiposCliente.setXI_BRAND(decoTarjetaLocal.getDeco_marca());
							if (decoTarjetaLocal.getDeco_reference()!=null && decoTarjetaLocal.getDeco_reference().length()>0){
								equiposCliente.setXI_MODEL(decoTarjetaLocal.getDeco_reference());
							}else{
								equiposCliente.setXI_MODEL("");
							}
							
							if (desHCDecoSTD.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add(ComunInterfaces.DECODTHSTD);
								equiposCliente.setInv_type(ComunInterfaces.DECODTHSTD);
							}else if (desHCDecoPVR.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add(ComunInterfaces.DECODTHPVR);
								equiposCliente.setInv_type(ComunInterfaces.DECODTHPVR);
							}else if (desHCDecoHDTV.equalsIgnoreCase(decoTarjetaLocal.getDeco_reference())){
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add(ComunInterfaces.DECOHD);
								equiposCliente.setInv_type(ComunInterfaces.DECOHD);
							}
							equiposCliente.setXI_SAP_CODE_DESCRIPTION(datosOpcionalesArray);
							
							//---------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add(decoTarjetaKey.id_tarjeta.toString());
							equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
							//--------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add(decoTarjetaKey.id_deco.toString());
							equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
							//--------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add("");
							equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
							//---------------------------------------------------
							
							// Datos del Deco
							try{			
								keyInfoSAPTmp = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_deco, idPetActuacion);
								infoSAPTmp = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPTmp);
						
								//------------------------------------------------------
								if(infoSAPTmp.getCentro_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPTmp.getCentro_sap());
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}
								//------------------------------------------------------				
								//------------------------------------------------------
								if(infoSAPTmp.getCod_lote_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPTmp.getCod_lote_sap());
									equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								}
								//------------------------------------------------------
								//------------------------------------------------------
								if(infoSAPTmp.getElement_pep_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPTmp.getElement_pep_sap());
									equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								}
								//------------------------------------------------------
								//------------------------------------------------------
								if(infoSAPTmp.getFlag_mat_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPTmp.getFlag_mat_sap());
									equiposCliente.setXI_SAP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP(datosOpcionalesArray);
								}
								//------------------------------------------------------

								
							} catch (FinderException e) {
								log.debug("No se encontraron Decos para deco con id: "+decoTarjetaKey.id_deco+". Y id de peticion: "+idPetActuacion);
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP(datosOpcionalesArray);
							} catch (Exception e) {
								log.error("TOAServiciosBean: Se presento Error seteando los datos de SAP para un Deco. "+e);
							}

							// Datos de la tarjeta
							try{
								keyInfoSAPCard = new Deco_Tarjeta_Info_SapKey(decoTarjetaKey.id_tarjeta, idPetActuacion);
								infoSAPCard = (Deco_Tarjeta_Info_SapLocal) deco_tar_inf_sapLocalHome.findByPrimaryKey(keyInfoSAPCard);
								//------------------------------------------------------
								if(infoSAPCard.getFec_cont_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getCentro_sap());
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}
								//------------------------------------------------------	
								//------------------------------------------------------
								if(infoSAPCard.getNum_material_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getNum_material_sap());
									equiposCliente.setXI_SAP_CODE_TJ(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_CODE_TJ(datosOpcionalesArray);
								}
								//------------------------------------------------------	
								//------------------------------------------------------
								if(infoSAPCard.getCentro_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getCentro_sap());
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								}
								//------------------------------------------------------	
								//------------------------------------------------------
								if(infoSAPCard.getAlmacen_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getAlmacen_sap());
									equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
								}
								//------------------------------------------------------
								//------------------------------------------------------
								if(infoSAPCard.getCod_lote_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getCod_lote_sap());
									equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								}
								//------------------------------------------------------
								//------------------------------------------------------
								if(infoSAPCard.getElement_pep_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getElement_pep_sap());
									equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								}
								//------------------------------------------------------
								//------------------------------------------------------
								if(infoSAPCard.getFlag_mat_sap() != null){
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(infoSAPCard.getFlag_mat_sap());
									equiposCliente.setXI_SAP(datosOpcionalesArray);
								}else{
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP(datosOpcionalesArray);
								}
								//------------------------------------------------------

							}catch (FinderException e) {
								log.debug("No se encontraron Tarjetas para Card con id: "+decoTarjetaKey.id_tarjeta+". Y id de peticion: "+idPetActuacion);
								//------------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								//------------------------------------------------------	
								//------------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
								//------------------------------------------------------	
								//------------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
								//------------------------------------------------------
								//------------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
								//------------------------------------------------------
								//-----------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
								//------------------------------------------------------
								//------------------------------------------------------
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setXI_SAP(datosOpcionalesArray);
								//------------------------------------------------------
							} catch (Exception e) {
								log.error("TOAServiciosBean: Se presento Error seteando los datos de SAP para una Tarjeta. "+e);
							}			
							/*FIN - RQ.8595 - mfmendez*/
							listaEquipos.add(equiposCliente);
						}
						
					}
					
					//Para los elementos de la petición
					Collection elementoPeticionList = peticion_stLocal.getElemento_peticion();
					for (Iterator elementoPeticionIter = elementoPeticionList.iterator(); elementoPeticionIter.hasNext();){
						Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal)elementoPeticionIter.next();
											
						//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
						equiposCliente = new EQUIPOSCLIENTE();
						if (elementoPeticionLocal.getSerial() != null && elementoPeticionLocal.getSerial().length() > 0 && !elementoPeticionLocal.getSerial().equals("0")){
							
							equiposCliente.setInvsn(elementoPeticionLocal.getSerial());
							equiposCliente.setXI_BRAND("");
							equiposCliente.setXI_MODEL("");
						
							try{
								Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
								Ps_Tipo_EqKey psTipoEquipoKey = new Ps_Tipo_EqKey(new Integer(elementoPeticionLocal.getPs_id().toString()),new Integer(elementoPeticionLocal.getTipo_elemento().toString()));
								Ps_Tipo_EqLocal psTipoEquipoLocal = psTipoEqLocalHome.findByPrimaryKey(psTipoEquipoKey);
								Elemento_agenda_scLocalHome elementoAgendaSCLocalHome = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
								Elemento_agenda_scKey elementoAgendaSCKey = new Elemento_agenda_scKey(psTipoEquipoLocal.getId_elemento_agenda());
								
								if (elementoAgendaSCKey.id_correlativo != null){
									Elemento_agenda_scLocal elementoAgendaSCLocal = elementoAgendaSCLocalHome.findByPrimaryKey(elementoAgendaSCKey);
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add(elementoAgendaSCLocal.getDesc_equipo());
									equiposCliente.setInv_type(elementoAgendaSCLocal.getDesc_equipo());
									
								}else{
									log.debug("No existe un equipo de agenda asociado al ps:"+elementoPeticionLocal.getPs_id()+" y tipo de elemento:"+elementoPeticionLocal.getTipo_elemento());
									datosOpcionalesArray = new ArrayList();
									datosOpcionalesArray.add("");
									equiposCliente.setInv_type("");
								}
								
								
							}catch(NullPointerException ex){
								log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio:"+ex);
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setInv_type("");
							}catch(Exception ex){
								log.debug("Ocurrió un problema detectando el tipo de equipos en la tr-701-e, se setea vacio");
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add("");
								equiposCliente.setInv_type("");
							}
							equiposCliente.setXI_SAP_CODE_DESCRIPTION(datosOpcionalesArray);
						//--------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
						//--------------------------------------------------
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add("");
						equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
						//--------------------------------------------------
						ElementoLocalHome elementoLocalHome;
						
						try {
							elementoLocalHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
							elementoLocal = elementoLocalHome.findElemento(elementoPeticionLocal.getTipo_elemento().longValue());
						
						} catch (NamingException e) {
							// TODO Bloque catch generado automáticamente
							log.debug("a ocurrido un error al intentar inicializar el ElementoLocalHome " + idPetActuacion+ " " +e);
						} catch (FinderException e) {
							// TODO Bloque catch generado automáticamente
							log.debug("a ocurrido un error al intentar consultar en la tabla Elemento " + idPetActuacion+ " " +e);
						}
						
						datosOpcionalesArray = new ArrayList();
						datosOpcionalesArray.add(elementoLocal.getDesc_elemento());
						equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
						//--------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getClase_mov_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getClase_mov_sap());
								equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getCentro_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getCentro_sap());
								equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_DISTRIBUTION_CENTER(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getAlmacen_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getAlmacen_sap());
								equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getCod_lote_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getCod_lote_sap());
								equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getElement_pep_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getElement_pep_sap());
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(elementoPeticionLocal.getFlag_mat_sap() != null){
								datosOpcionalesArray.add(elementoPeticionLocal.getFlag_mat_sap());
								equiposCliente.setXI_SAP(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							
							listaEquipos.add(equiposCliente);

						}
					}
					
					//Para los modems
					Collection modemPeticionList = peticion_stLocal.getModem();
					for (Iterator modemPeticionIter = modemPeticionList.iterator(); modemPeticionIter.hasNext();){
						ModemLocal modemLocal = (ModemLocal)modemPeticionIter.next();
						ModemKey modemKey = (ModemKey)modemLocal.getPrimaryKey();
						//se recontruye (limpia) la clase pojo para que no se repitan los datos en el arreglo
						equiposCliente = new EQUIPOSCLIENTE();
						//
						if (modemKey.serial != null && modemKey.serial.length() > 0 && !modemKey.serial.equals("0") && !modemKey.serial.equals("NO SERIAL")){	
							
							equiposCliente.setInvsn(modemKey.serial);
							equiposCliente.setXI_BRAND(modemLocal.getModem_marca());
							if (modemLocal.getModelo()!=null && modemLocal.getModelo().length()>0){
								equiposCliente.setXI_MODEL(modemLocal.getModelo());
							}else{
								equiposCliente.setXI_MODEL("");
							}
							
							if (modemLocal.getTipo() != null && modemLocal.getTipo().intValue()==ComunInterfaces.identificadorWiFi){
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add(ComunInterfaces.MODEM_WIFI);
								equiposCliente.setInv_type(ComunInterfaces.MODEM_WIFI);
								
							}else{
								datosOpcionalesArray = new ArrayList();
								datosOpcionalesArray.add(ComunInterfaces.MODEM_STD);
								equiposCliente.setInv_type(ComunInterfaces.MODEM_STD);
								
							}
							equiposCliente.setXI_SAP_CODE_DESCRIPTION(datosOpcionalesArray);
							//-----------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add("");
							equiposCliente.setXI_CARD_SERIAL_NUMBER(datosOpcionalesArray);
							//----------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add("");
							equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
							//----------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add("");
							equiposCliente.setXI_CAS_ID(datosOpcionalesArray);
							//--------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							datosOpcionalesArray.add("");
							equiposCliente.setXI_MATERIAL_CODE(datosOpcionalesArray);
							//--------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(modemLocal.getClase_mov_sap() != null){
								datosOpcionalesArray.add(modemLocal.getClase_mov_sap());
								equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_MOVIMIENTO(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(modemLocal.getAlmacen_sap() != null){
								datosOpcionalesArray.add(modemLocal.getAlmacen_sap());
								equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_BOD_CTISTA(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(modemLocal.getCod_lote_sap() != null){
								datosOpcionalesArray.add(modemLocal.getCod_lote_sap());
								equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_BULK_SAP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(modemLocal.getElement_pep_sap() != null){
								datosOpcionalesArray.add(modemLocal.getElement_pep_sap());
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP_PEP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------
							//-------------------------------------------------------------
							datosOpcionalesArray = new ArrayList();
							if(modemLocal.getFlag_mat_sap() != null){
								datosOpcionalesArray.add(modemLocal.getFlag_mat_sap());
								equiposCliente.setXI_SAP(datosOpcionalesArray);

							}else{
								datosOpcionalesArray.add("");
								equiposCliente.setXI_SAP(datosOpcionalesArray);
							}
							//-------------------------------------------------------------				
							listaEquipos.add(equiposCliente);
						}
					}
//					logica para la generacion de el arregloe setOtros_Datos_BA el cual contiene arreglos de propriedades junto con su key y value
//					datosOpcionalesArray = new ArrayList();
//					otherElement.setKey("");
//					otherElement.setValue("");
//					datosOpcionalesArray.add(otherElement.getKey());
//					datosOpcionalesArray.add(otherElement.getValue());
//					otherType.setPropiedad(datosOpcionalesArray);
//					equiposCliente.setXI_SAP_OTROS(otherType);
//			fin arreglo setOtros_Datos_BA
					return (ArrayList) listaEquipos;
					
				}
				
				
				private String cantidadDeDecos(Peticion_stLocal peticion_stLocal){
					
					Collection psPeticion=peticion_stLocal.getProducto_servicio_peticion();
					Iterator psPeticionIt=null;
					
					int contadorDecos=0;
					String numDecos="0";
					boolean solucionTV = false;
					boolean esTraslado = false;
					for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
						co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal producto_servicio_peticionLocal = (co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal)psPeticionIt.next();
						Familia_producto_servicio_stKey familia_producto_servicioKey =(Familia_producto_servicio_stKey)producto_servicio_peticionLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
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
					
					// mapeo cantidad decos 
					xa_number_decoders=  new ArrayList();
					xa_number_decoders.add(numDecos);
					tr800e.setXA_NUMBER_DECODERS(xa_number_decoders);
					//
					log.debug("El número de decos resultante es: "+numDecos);
					
					return numDecos;
				}
				
				private String tiposDeDeco(Peticion_stLocal peticion_stLocal)
				{
					Iterator psPeticionIt=null;
					Collection psPeticion=peticion_stLocal.getProducto_servicio_peticion();
					String tipoDeco="";
					boolean tieneHD=false;
					boolean tienePVR=false;
					boolean tieneSTD=false;
					for(psPeticionIt=psPeticion.iterator();psPeticionIt.hasNext();){
						co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal producto_servicio_peticionLocal = (co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal)psPeticionIt.next();
						Familia_producto_servicio_stKey familia_producto_servicioKey =(Familia_producto_servicio_stKey)  producto_servicio_peticionLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
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
					return tipoDeco;
				}
				
				
				private String listaTematicos(Peticion_stLocal peticion_stLocal){
					//Se obtienen los PS de la petíción
					String listaTematicosTV="";
					
					Collection productoServicioPetList=peticion_stLocal.getProducto_servicio_peticion();
					Iterator listaProductoServicioPetIt=null;
					
					for(listaProductoServicioPetIt=productoServicioPetList.iterator();listaProductoServicioPetIt.hasNext();){
						co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal  producto_servicio_peticionLocal=(co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal )listaProductoServicioPetIt.next();
						Producto_servicio_stLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio_st();
						Producto_servicio_stKey  producto_servicioKey=(Producto_servicio_stKey)producto_servicioLocal.getPrimaryKey();
						
						Familia_producto_servicio_stKey familia_producto_servicioKey = (Familia_producto_servicio_stKey)producto_servicioLocal.getFamilia_producto_servicio_st().getPrimaryKey();
						if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaTematicoTV){
							listaTematicosTV+=" " + producto_servicioLocal.getPs_nombre();
							
						}
					}
					return listaTematicosTV;
				}
				private int cantidadReintentosPGC(){
					//se declara una variable que cuenta los reintentos de la peticion en pgc
					int canPGC=0;
					return canPGC;
				}
				/**
				 * @param peticionLocal2
				 */
				private void setTipoAccionTOA(Peticion_stLocal peticionLocal2) {
					// TODO Apéndice de método generado automáticamente
				
					try {
						Collection psp = peticionLocal2.getProducto_servicio_peticion();
						int prioridadAnt = 6;
						String actividad = "";
						String psLinea = "";
						String psBA = "";
						String psTV = "";
						String psNK = "";
						String actividadFinal = "";
						String listaPs="";
						log.debug("Mspeo Tipo Accion TOA");
						for (Iterator iter = psp.iterator(); iter.hasNext();) {
							co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal  pspLocal = (co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal ) iter.next();
							Producto_servicio_stLocal psLocal = pspLocal.getProducto_servicio_st();
							Familia_producto_servicio_stKey familiakey = (Familia_producto_servicio_stKey)psLocal.getFamilia_producto_servicio_st().getPrimaryKey();
							Operacion_comercial_stKey opcoLocal = (Operacion_comercial_stKey)pspLocal.getOperacion_comercial_st().getPrimaryKey();
							Long operacionAverias = new Long (1000);
							
							Parametro_actLocal parametroActlocal = null;
							try {
								Parametro_actLocalHome parametroActhome = (Parametro_actLocalHome) HomeFactory.getHome(Parametro_actLocalHome.JNDI_NAME); 
								parametroActlocal = parametroActhome.findByPrimaryKey(new Parametro_actKey(operacionAverias));
							} catch (FinderException e) {
								// TODO Bloque catch generado automáticamente
								log.debug("PS: "+ opcoLocal.opco_id + " no tiene configuracion parametrizada en la tabla");
							}
							
							if(parametroActlocal.getAct_prioridad().intValue()< prioridadAnt){
								actividad = parametroActlocal.getAct_descripcion();
								actividadFinal = parametroActlocal.getAct_tipo_actuacion();
								prioridadAnt = parametroActlocal.getAct_prioridad().intValue();
							}
							tr800e.setXA_FAMILIA(psLocal.getFa_ps());
							if(psLocal.getFa_ps() != null){
								if(familiakey.faps_id.intValue() == familiaLinea || familiakey.faps_id.intValue() == familiaPcLinea)
									psLinea = psLocal.getFa_ps();
								if(familiakey.faps_id.intValue() == familiaBandaAncha || familiakey.faps_id.intValue() == familiaPcBA)
									psBA = psLocal.getFa_ps(); 
								if(familiakey.faps_id.intValue() == familiaPcBANaked || familiakey.faps_id.intValue() == familiaBandaAnchaNaked)
									psNK = psLocal.getFamilia_producto_servicio_st().getFaps_codigo();
								if(familiakey.faps_id.intValue()== familiaDecoTV 
										|| familiakey.faps_id.intValue() == familiaTV
										|| familiakey.faps_id.intValue() == familiaDecoHDTV
										|| familiakey.faps_id.intValue() == familiaDecoPVRTV)
									psTV = psLocal.getFa_ps();
							}
							
							listaPs=listaPs+psLocal.getPs_nombre();
						}
						
						if (listaPs.length()>0){
							listaPs = listaPs.substring(0,listaPs.length()-1);
						}
						actividadFinal = actividadFinal+psLinea+psBA+psNK+psTV;
						tr800e.setAworktype_label(actividad);
						tr800e.setXA_WORK_TYPE(actividadFinal);
						tr800e.setXA_DESCRIPTION(listaPs);
					} catch (EJBException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					} catch (NamingException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
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
						inicializarVariables();
						BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
						
						Collection listaPsPet=peticion_stLocal.getProducto_servicio_peticion();
						//Las peticiones de ST sólo tienen un ps, se itera sólo una vez.
						co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal producto_servicio_peticionLocal=(co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal)listaPsPet.iterator().next();
						Producto_servicio_stLocal producto_servicio_stLocal = (Producto_servicio_stLocal) producto_servicio_peticionLocal.getProducto_servicio_st();                                 
						Familia_producto_servicio_stLocal familia_producto_servicio_stLocal = producto_servicio_stLocal.getFamilia_producto_servicio_st();
						Familia_producto_servicio_stKey familia_producto_servicio_stKey=(Familia_producto_servicio_stKey)familia_producto_servicio_stLocal.getPrimaryKey();
						
						
						Long idFamilia=familia_producto_servicio_stKey.faps_id;
						String codActividadPE="";						
						codActividadPE=ComunInterfaces.PLANTA_EXTERNA_TOA;
						//solucion valores quemados con planta externa
						ActividadLocalHome actividadLocalHome =(ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
						ActividadLocal actividadLocal =actividadLocalHome.findByCodigoActividadIdAplicacion(codActividadPE,new Long (2));
//						idActividadPE=actividadLocal.getActi_id();
						ActividadKey actividadkey = (ActividadKey) actividadLocal.getPrimaryKey();
						Peticion_stKey peticion_stKey=(Peticion_stKey)peticion_stLocal.getPrimaryKey();                                  
						// fin solucion valor quemado id actividad
						BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByIdActIdPetiAp(actividadkey.act_id,peticion_stKey.cod_ave_cd,idAplicacion);
						
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
				 * @param tr800e2
				 * @throws ATiempoAppEx
				 */
				private void finalizarProceso(TR800E tr800e, ActividadEJBDTO act) throws ATiempoAppEx {
					// TODO Apéndice de método generado automáticamente
					log.debug("14-------------------------------------------");
					CommunicatorWSDelegate delegate = new CommunicatorWSDelegate();
					log.debug("Se envia TR800e hacia TOA: "+tr800e.toString());
					String respuesta = delegate.recibirMensaje(tr800e);
//					String respuesta = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\"><soapenv:Header/><soapenv:Body><man:CreacionServicioResponse><XA_SOURCE_SYSTEMR>XA_SOURCE_SYSTEMR</XA_SOURCE_SYSTEMR><id_actuacion>id_actuacion</id_actuacion><XA_ORDER_ATIEMPO>XA_ORDER_ATIEMPO</XA_ORDER_ATIEMPO><XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS><XA_ORDER_COD_ERR>0</XA_ORDER_COD_ERR><XA_ORDER_DESC_ERR>XA_ORDER_DESC_ERR</XA_ORDER_DESC_ERR></man:CreacionServicioResponse></soapenv:Body></soapenv:Envelope>";// para proceso de pruebas unitarias
					log.debug("Se recibe respuesta: "+respuesta);
					recepcionCrearActuacionTOA(respuesta, act, tr800e.getId_actuacion());
				}
				/* (sin Javadoc)
				 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionCrearActuacionTOA()
				 */
				public void recepcionCrearActuacionTOA(String respuesta, ActividadEJBDTO act, String idActuacion) throws ATiempoAppEx {
					// TODO Apéndice de método generado automáticamente
					try {
						
						//REQ TOA FASE III DCARDENA 
						AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);                                    
						//fin req TOA
						
						ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(act.getCodigoActividad());
						if(respuesta == null){
							act.setObservacion("No se pudo conectar con el servicio SOA");
							try {
								AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(idActuacion);
								agendaSCSTLocal.setEstado(new Integer(2));
//								BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaLocal.getPeti_numero(),idAplicacion);
							} catch (FinderException e1) {
								// TODO Bloque catch generado automáticamente
								log.debug("No se encontrò actuacion: "+ idActuacion);
							}

						}else{
							TR800SParser parser = new TR800SParser();
							TR800S tr800s = parser.parse(respuesta);
							String descripcionError = tr800s.getXAORDERDESCERR();
							if(descripcionError != null && descripcionError.length() > 170)
								descripcionError = descripcionError.substring(0,170);
							if (tr800s.getXAORDERCODERR().equals("0")){
								//continua flujo
								log.debug("Se recibe respuesta sin error: " + tr800s.getXAORDERDESCERR());
								act.setObservacion("Se recibe respuesta sin error: " + descripcionError);
//								act.setRealizarTerminoInmediato(true);
//								actividadEJB.terminar (act);
							}else{
								//PGI;
								log.debug("Se deriva a PGI: " + tr800s.getXAORDERDESCERR());
								act.setObservacion("Error en la respuesta de TOA: " + descripcionError);
								try {
									AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(idActuacion);
									agendaSCSTLocal.setEstado(new Integer(2));
//									BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaLocal.getPeti_numero(),idAplicacion);
								} catch (FinderException e1) {
									// TODO Bloque catch generado automáticamente
									log.debug("No se encontrò actuacion: "+ idActuacion);
								}
//								act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("ID_PGI_AVERIAS"));
//								act.setRealizarTerminoInmediato(true);
//								actividadEJB.terminar (act);
							}
							
							//REQ TOA FASE III DCARDENA 
							//se mapea en la descripcion de la actuacion del front si hubo error o fue ok la respuesta del osb
							AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByCodAveMaxFechaIngreso(act.getNumeroPeticion());                                
							agendaSCSTLocal.setMensaje_act("Se recibió respuesta de alta de actuación TOA ");
							//fin req TOA
							
						}
					} catch (ATiempoAppEx e) {
						// TODO Bloque catch generado automáticamente
						log.debug("Se genera error al finalizar la actividad" + e);
					} //catch (TnProcesoExcepcion e) {
//						// TODO Bloque catch generado automáticamente
//						log.debug("Se genera error al finalizar la actividad" + e);
					 catch (FinderException e) {
						// TODO Bloque catch generado automáticamente catch (FinderException e) {
					 	log.debug("Se genera error al finalizar la actividad" + e);
					} catch (NamingException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("Se genera error al finalizar la actividad" + e);
					} 


				}
				
	//funcion que retorna la actividad
	public String actividadActual (Long idpeticion){
		String actividad;	
		try {
			log.debug("Se consulta la actividad actual de la averia "+ idpeticion);
			co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocalHome	bitacora= (co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocalHome)HomeFactory.getHome(co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocalHome.JNDI_NAME);
			co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocal	bitacoraLocal = bitacora.findByPeticionActividad(idpeticion,new Long(ComunInterfaces.idActividadPETOA));
			actividad =bitacoraLocal.getAct_id().toString();
			log.debug("la actividad es "+actividad);
		} catch (FinderException e) {
			log.debug("no hay actvidad TOA planta Externa");
			actividad=null;
		} catch (NamingException e) {
			log.debug("no hay actvidad TOA planta Externa naming");
			actividad=null;
		}				
		return actividad;

	}

	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s) throws ATiempoAppEx {
		
		try{
			Deco_tarjetaLocalHome decoTarjetaLocalHome = (Deco_tarjetaLocalHome) HomeFactory.getHome (Deco_tarjetaLocalHome.JNDI_NAME);
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			
			String codAve = tr801s.getIdSchedule().substring(2,tr801s.getIdSchedule().indexOf("-"));
			Iterator decoTarjetaActualBDIterator=null;
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			
			Collection decosAgendaConError=new ArrayList();
			
			AgendaSCSTLocal agendaSCSTLocal = null;
			
			try{
				agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr801s.getIdSchedule());
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento");
			}
			
			if (agendaSCSTLocal != null && agendaSCSTLocal.getEstado().intValue() == ACTUACION_ABIERTA && tr801s.getEquipments()!=null){
				Peticion_stLocal peticionSTLocal = agendaSCSTLocal.getPeticion_st();
				Peticion_stKey peticionSTKey = (Peticion_stKey)peticionSTLocal.getPrimaryKey();
				//Se obtienen los pares decotarjeta que tiene el cliente.
				Collection decoTarjetaOriginal = peticionSTLocal.getDeco_tarjeta();
				ArrayList decoTarjetaInstall = new ArrayList();
				ArrayList decoTarjetaOld = new ArrayList();
				
				this.almacenarMensajeAgendaSC(idCorrelativoMensaje, tr801s.getIdSchedule(), tr801s.getApptNumber(), peticionSTKey);
				/* Esta seccion analiza compara cada deco que tiene el cliente contras los equipos que vienen en la tr801s 
				 * Si un deco del cliente no viene en los equipos de la tr801s analiza si su estado es diferente de  par no activo 
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
					
					Collection equipos = tr801s.getEquipments();
					for (Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
						TR801SEquipment equipo = (TR801SEquipment)equiposIterator.next();
						
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
				 * En esta seccion se analizan los equipos que vienen en la tr801s contra la coleccion de equipos que tiene el cliente y
				 * que se marcaron para eliminar en el primer ciclo de este algoritmo.
				 */
				Collection equipos = tr801s.getEquipments();
				Collection ps=peticionSTLocal.getProducto_servicio_peticion();                                       
				for(Iterator equiposIterator=equipos.iterator(); equiposIterator.hasNext();){
					TR801SEquipment equipo = (TR801SEquipment)equiposIterator.next();
					boolean estaSinInstalar = true;
					boolean estaNoOK = false;
					
					for (Iterator decoTarjetaOldIterator = decoTarjetaOld.iterator(); decoTarjetaOldIterator.hasNext();){
						DecoTarDTO decoTarjetaLocal = (DecoTarDTO)decoTarjetaOldIterator.next();
						//Se verifica si un equipo que se envia en la tr801s ya existia previamente y si tiene un estado de activacion fallido
						//En ese caso se vuelve a enviar a activar
						if (decoTarjetaLocal.getDeco().equals(equipo.getDecoCassId()) && decoTarjetaLocal.getTarjeta().equals(equipo.getCardSerialNumber())
								&& decoTarjetaLocal.getEstado().intValue() == ComunInterfaces.estadoParNoOk){
							estaNoOK = true;
						}
						//Se verifica si un equipo que se envia en la tr801s ya existia previamente y si tiene un estado de activacion OK
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
					DBManager dbSeq = new DBManager ();
					dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
					Long idTmpDecoTarjeta = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_DECO_TARJ")) ;
					tr801s.setId(tr801s.getId()+"-"+idCorrelativoMensaje);
					Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = tmp_deco_tarjetaLocalHome.create (idTmpDecoTarjeta,peticionSTLocal,XMLUtilities.marshall (tr801s)) ;
					
					RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
					//Se envía la tr017s
					recursosTVDelegate.enviaConfiguracionServiciosTVSoloEqAgendaSC(new Long(codAve).longValue(), decoTarjetaInstall);
				}else{
					RecursosBABean recursosBADelegate = new  RecursosBABean();
					log.debug("No se envía el mensaje: "+ tr801s.getId() +" a HC porque los equipos recibidos ya están instalados");
					this.enviaActivarDecosTarjetasTOA(tr801s.getIdSchedule(), tr801s.getId()+"-"+idCorrelativoMensaje);
				}
			}
			
			
			
		}catch(NamingException ex){
			log.debug("Error instanciado el bean en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}catch(CreateException ex){
			log.debug("Error creando elementos en la recepción de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @param idCorrelativo
	 * @param actDto
	 * @param 
	 * @param appNumber
	 * @param idSchedule
	 * @param peticionKey2
	 */
	private void almacenarMensajeAgendaSC(Long idCorrelativo, String idSchedule, String appNumber, Peticion_stKey peticionKey2) {
		// TODO Apéndice de método generado automáticamente
		try {
			
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mensajeEsperaLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			Mensaje_estadoKey mensaje_estadoKey =  (Mensaje_estadoKey) mensajeEsperaLocal.getPrimaryKey();
			Mensaje_estado_stLocalHome mensajeAgendaHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			
			Mensaje_estado_stLocal mensajeAgendaLocal = mensajeAgendaHome.create(idCorrelativo);
			mensajeAgendaLocal.setPeti_numero(peticionKey2.cod_ave_cd);
			mensajeAgendaLocal.setCod_estado(mensaje_estadoKey.cod_estado);
			mensajeAgendaLocal.setAppNumber(appNumber);
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
		}
	}

	/*private Mensaje_agenda_scLocal almacenarMensajeAgendaSC(Long idCorrelativo, String idSchedule, String appNumber, ActividadEJBDTO actDto, Peticion_stKey peticionKey2) {
		// TODO Apéndice de método generado automáticamente
		try {
			Mensaje_agenda_scLocalHome mensajeAgendaHome = (Mensaje_agenda_scLocalHome) HomeFactory.getHome(Mensaje_agenda_scLocalHome.JNDI_NAME);
			Mensaje_agenda_scLocal mensajeAgendaLocal = mensajeAgendaHome.create(idCorrelativo);
			mensajeAgendaLocal.setPeti_numero(peticionKey2.cod_ave_cd);
			mensajeAgendaLocal.setId_agenda(idSchedule);
			mensajeAgendaLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
			mensajeAgendaLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
			mensajeAgendaLocal.setApptNumber(appNumber);
			mensajeAgendaLocal.setReintentos(new Long("0"));
			return mensajeAgendaLocal;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		}
	}

	*/
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionActivarModemTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR802S)
	 */
	private Mensaje_estado_stLocal almacenarMensajeEstadoST(Long idCorrelativo,String appNumber, ActividadEJBDTO actDto, Peticion_stKey peticionKey2) {
		try {
			Mensaje_estado_stLocalHome mensajeEstadoSTHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal mensajeEstadoSTLocal = mensajeEstadoSTHome.create(idCorrelativo);
			mensajeEstadoSTLocal.setPeti_numero(peticionKey2.cod_ave_cd);
			mensajeEstadoSTLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
			mensajeEstadoSTLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
			mensajeEstadoSTLocal.setAppNumber(appNumber);
			mensajeEstadoSTLocal.setReintentos(new Long("0"));
			return mensajeEstadoSTLocal;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se genera un error al tratar de insertar en Mensaje_agenda_sc:"+e);
			return null;
		}
	}
//	public void recepcionActivarModemTOA1(TR802S tr802s) throws ATiempoAppEx {
//		// TODO Apéndice de método generado automáticamente
//		log.debug("Entro a recepcionActivarModemsAgendaSC en soltec de la actuación:"+tr802s.getId_schedule());
//		
//		try{
//			Peticion_stLocalHome peticionStLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
//			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
//			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
//			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
//			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
//			
//			AgendaSCSTLocal agendaSCSTLocal = null;
//			
//			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
//			
//			//Obtención de la información del agendamiento
//			agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr802s.getId_schedule());
//			
//			ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(agendaSCSTLocal.getPeticion_st());
//			
//			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
//			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad()); 
//			
//			if (agendaSCSTLocal.getEstado().intValue() == ACTUACION_ABIERTA && tr802s.getModem_serial()!=null){
//				RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
//				
//				//Asignación de variables del modem
//				ModemSTDTO modem = new ModemSTDTO();
//				modem.setNum_peticion(agendaSCSTLocal.getId_peticion_st());
//				modem.setMarca(tr802s.getModem_brand());
//				modem.setSerial(tr802s.getModem_serial());
//				modem.setModelo(tr802s.getModel_modem());
//				modem.setCod_material(tr802s.getMaterial_code());
//
//				if (tr802s.getModem_brand()!= null && tr802s.getModem_brand().equals(ComunInterfaces.MODEM_WIFI)){
//					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
//				}else if (tr802s.getModem_brand()!= null && (tr802s.getModem_brand().equals(ComunInterfaces.MODEM_STD)
//						|| tr802s.getModem_brand().equals(ComunInterfaces.MODEM_STD_2) 
//						|| tr802s.getModem_brand().equals(ComunInterfaces.MODEM_STD1P))){
//					modem.setTipo(new Long(ComunInterfaces.identificadorConvencional));
//				}else{
//					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
//				}
//				
//				try{
//					Peticion_stKey peticionST = new Peticion_stKey(agendaSCSTLocal.getId_peticion_st());
//					Peticion_stLocal peticionSTLocal = peticionStLocalHome.findByPrimaryKey(peticionST);
//					modem.setTelefono(new Long (peticionSTLocal.getNum_ide_nu()));
//				}catch(Exception ex){
//					log.debug("Se presento un error consultando el teléfono, se setea 0: "+ex);
//					modem.setTelefono(new Long("0"));
//				}
//				
//				
//				modem.setAccion(new Integer(ComunInterfaces.accionModemNoAction));
//				
//				Mensaje_estado_stLocal mensajeEstadoSTLocal= mensajeEstadoStLocalHome.create(new Long(tr802s.getId()));
//				mensajeEstadoSTLocal.setPeticion_st(agendaSCSTLocal.getPeticion_st());
//				
//				mensajeEstadoSTLocal.setCod_conector(new Integer(codigoConectorDos));
//				mensajeEstadoSTLocal.setFecha_envio(df.format (new java.util.Date ()));
//				
//				mensajeEstadoSTLocal.setCod_estado(new Integer(estadoEspera));					
//				mensajeEstadoSTLocal.setCod_actividad_generadora(actDto.getCodigoActividad());
//				
//				//Envío el mensaje de autoinstalación
//				rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), tr802s.getId()+"#"+tr802s.getId_schedule(),false);
//			}else{
//				log.debug("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
//				actDto.setObservacion("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
//				
//				this.enviaActivarModemTOA(tr802s.getId_schedule(), tr802s.getId(), null);
//				actividadEJB.grabarSinTerminar(actDto);
//			}
//		}catch(NamingException ex){
//			log.error("NamingException en recepcionActivarModemsAgendaSC()"+ex.toString());
//			ex.printStackTrace();
//		}catch(FinderException ex){
//			log.error("FinderException en recepcionActivarModemsAgendaSC()"+ex.toString());
//			ex.printStackTrace();                                        
//		}catch(CreateException ex){
//			log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
//			ex.printStackTrace();                                        
//		}catch(TnProcesoExcepcion ex){
//			log.error("CreateException en recepcionActivarModemsAgendaSC()"+ex.toString());
//			ex.printStackTrace();
//		}
//	}


	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionActivarModemTOA(co.com.telefonica.atiempo.interfaces.atiempo.TR802S)
	 */
	public void recepcionActivarModemTOA(TR802S tr802s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Entro a recepcionActivarModemsAgendaSC de la actuación:"+tr802s.getId_schedule());

		try{
			inicializarVariables();
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			Peticion_stLocalHome peticionSTLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome) HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome)HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome)HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			ModemLocalHome modemLocalHome = (ModemLocalHome)HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			Peticion_stLocal peticionSTLocal = null;
			AgendaSCSTLocal agendaSTSCLocal = null;
			Peticion_stKey peticion_stKey = null;
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
			
			//Obtención de la información del agendamiento
			try{
				agendaSTSCLocal = agendaSCSTLocalHome.findByPrimaryKey(tr802s.getId_schedule());
				peticion_stKey = new Peticion_stKey(agendaSTSCLocal.getId_peticion_st());
				peticionSTLocal = peticionSTLocalHome.findByPrimaryKey(peticion_stKey);
			}catch(FinderException ex){
				log.debug("No se encuentra el código de agendamiento, debe haber ocurrido un reagendamiento se procede a cerrar las actuaciones abiertas y crear esta");
				String idPeticionAux = tr802s.getId_schedule().substring(2,tr802s.getId_schedule().indexOf("-"));
				
				Collection agendaSCCollection = agendaSCSTLocalHome.findByCodAve(new Long(idPeticionAux));
				for (Iterator agendaSCIterator = agendaSCCollection.iterator(); agendaSCIterator.hasNext();){
					Agenda_scLocal agendaSCLocalAux = (Agenda_scLocal) agendaSCIterator.next();
					
					if (agendaSCLocalAux.getEstado().intValue() == ComunInterfaces.ACTUACION_ABIERTA){
						agendaSCLocalAux.setEstado(new Integer(ComunInterfaces.ACTUACION_REAGENDADA));
					}
				}
				
				peticion_stKey = new Peticion_stKey(new Long(idPeticionAux));
				peticionSTLocal = peticionSTLocalHome.findByPrimaryKey(peticion_stKey);
									
				agendaSTSCLocal = agendaSCSTLocalHome.create(tr802s.getId_schedule());
				agendaSTSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
				agendaSTSCLocal.setId_peticion_st(new Long(idPeticionAux));
				agendaSTSCLocal.setPeticion_st(peticionSTLocal);
				agendaSTSCLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			}
			
			//Obtención de la actividad
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSTSCLocal.getId_peticion_st(),idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(codActividad);
			
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
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSTSCLocal.getId_peticion_st(), codActividad,idCorrelativo,null);
			
			Long IdCorrelativoAgenda = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			this.almacenarMensajeAgendaSC(IdCorrelativoAgenda, tr802s.getId_schedule(), tr802s.getApptNumber(), peticion_stKey);

			if ((agendaSTSCLocal.getEstado().intValue() == ACTUACION_ABIERTA || agendaSTSCLocal.getEstado().intValue() == ACTUACION_REAGENDADA) && tr802s.getModem_serial()!=null){
				RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
				
				//Obtención de la información del teléfono asociado
				InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerDatosTecnicosLB(agendaSTSCLocal.getId_peticion_st());
				
				//Asignación de variables del modem
				ModemSTDTO modem = new ModemSTDTO();
				modem.setNum_peticion(agendaSTSCLocal.getId_peticion_st());
				modem.setMarca(tr802s.getModem_brand());
				modem.setSerial(tr802s.getModem_serial());
				
				//Se adiciona este cambio para tener en cuenta el codigo de material y el modelo del modem
				modem.setModelo(tr802s.getModel_modem());
				modem.setCod_material(tr802s.getMaterial_code());
				
				if (tr802s.getModem_type()!= null && tr802s.getModem_type().equals(ComunInterfaces.MODEM_WIFI)){
					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
				}else if (tr802s.getModem_type()!= null && 
						(tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD)||tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD_2)
								|| tr802s.getModem_type().equals(ComunInterfaces.MODEM_STD1P))){
					modem.setTipo(new Long(ComunInterfaces.identificadorConvencional));
				}else{
					modem.setTipo(new Long(ComunInterfaces.identificadorWiFi));
				}
				
				if (informacionTecnicaDTO.getTelefono()!= null){
					modem.setTelefono(informacionTecnicaDTO.getTelefono());
				}else{
					modem.setTelefono(new Long("0"));
				}
				
				modem.setAccion(new Integer(ComunInterfaces.accionModemNoAction));
				
				//Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(new Long(tr802s.getId()));
				//mensajeEstadoBALocal.setPeticion(agendaSCLocal.getPeticion());
				//mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				//mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
				
				//Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
				//mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);					
				//mensajeEstadoBALocal.setCod_actividad_generadora(codActividad);
				
				//Envío el mensaje de autoinstalación
			//rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), tr802s.getId()+"#"+tr802s.getIdSchedule(),false, false);
			//REQ BA NAKED 
			//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
				
				Collection modemArray = peticionSTLocal.getModem();
				for(Iterator iter = modemArray.iterator();iter.hasNext();){
					ModemLocal modemLocal = (ModemLocal) iter.next();
					ModemKey modemKey = (ModemKey) modemLocal.getPrimaryKey();
					if(!modemKey.serial.equals(modem.getSerial())){
						//
						modemLocal.setAccion(new Short(new Integer(ComunInterfaces.accionModemLiberar).shortValue()));
					}
				}
				Long  telAsignado = new Long (peticionSTLocal.getNum_ide_nu());
				ModemLocal modemLocal=modemLocalHome.create(modem.getSerial(),peticionSTLocal,telAsignado,new Short(modem.getAccion().shortValue()));
				modemLocal.setModem_marca(modem.getMarca());
				//ba naked fase II faltaba mapeo del tipo de modem
				modemLocal.setTipo(new Integer (modem.getTipo().intValue()));
				// fin ba naked
				modemLocal.setModelo(modem.getModelo());
				modemLocal.setCod_material(modem.getCod_material());
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
			aCSServicioDelegate.enviarAutoConfiguracion(modem,actDto.getCodigoActividad(), tr802s.getId()+"#"+tr802s.getId_schedule()+"@"+IdCorrelativoAgenda);
			//FIN REQ NAKED
			}else{
				log.debug("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
				actDto.setObservacion("El mensaje: "+tr802s.getId()+" no se puede procesar porque la actuación: "+tr802s.getId_schedule()+" ya se encuentra cerrada, o el mensaje no trae un serial de modem explícito");
					
				this.enviaActivarModemTOA(tr802s.getId_schedule(), tr802s.getId()+"-"+IdCorrelativoAgenda,null);
				
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
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaActivarDecosTarjetasTOA(java.lang.String, java.lang.String)
	 */
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try{
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
			
			Long codAve = new Long(idActuacion.substring(2,idActuacion.indexOf("-")));
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			ArrayList equipos = new ArrayList();
			TR801E tr801e = new TR801E();
			boolean tieneErrores = false;

			Collection agendaSCSTList=peticion_stLocal.getAgendascst();
			
			for (Iterator agendasSCIterator = agendaSCSTList.iterator(); agendasSCIterator.hasNext();){
				AgendaSCSTLocal agendaSCSTLocal = (AgendaSCSTLocal) agendasSCIterator.next();
				idActuacion = agendaSCSTLocal.getId_actuacion();
			}

			String idAux[] = idMensajePeticion.split("-");
			tr801e.setId(idAux[0]);	
			tr801e.setIdSourceSystem(sistemaAtiempo);
			tr801e.setIdSchedule(idActuacion);
			
			Collection decos = peticion_stLocal.getDeco_tarjeta();
			
			for (Iterator decosIterator = decos.iterator(); decosIterator.hasNext();){
				Deco_tarjetaLocal decoTarjetaLocal = (Deco_tarjetaLocal) decosIterator.next();
				Deco_tarjetaKey decoTarjetaKey = (Deco_tarjetaKey)decoTarjetaLocal.getPrimaryKey();
				
				if (decoTarjetaLocal.getEstado() != null && decoTarjetaLocal.getEstado().intValue() == estadoParNoOk){
					tr801e.setDescripcionError(decoTarjetaLocal.getSerial_deco() + " "+ decoTarjetaLocal.getMensaje_error());
					tieneErrores = true;
				}                                              
			}                                  
			if (tieneErrores){
				tr801e.setResponse("ERROR");
				tr801e.setError("9999");
				tr801e.setErrorMessage("Error en activación de Decos y Tarjetas.");
				tr801e.setDescripcionError("Error en activación de Decos y Tarjetas.");
			}else{
				tr801e.setResponse("OK");                                                       
				tr801e.setError("0");
				tr801e.setErrorMessage("");
				tr801e.setDescripcionError("");
			}                                                                                             
                        
			tr801e.setDestination(sistemaAgendaSC);
			tr801e.setSource(sistemaAtiempoSt);
			tr801e.setInterfaz("ACT_RES_ACTIVACION");
			tr801e.setVersion("1.0");
			
			String trXXXeClassName = tr801e.getClass().getName().split("\\.")[tr801e.getClass().getName().split("\\.").length-1]+"IN";
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			String repuestaInBound = null;
			Mensaje_estado_stLocalHome mensajeAgendaHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_estado_stLocal mensajeAgendaLocal = mensajeAgendaHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(idAux[1])));
			mensajeAgendaLocal.setObservaciones(tr801e.getDescripcionError());
			mensajeAgendaLocal.setReintentos(new Long(0));
			tr801e.setApptNumber(mensajeAgendaLocal.getAppNumber());
			GestionServiciosActivarST gestionServiciosVariado = new GestionServiciosActivarST();
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			while (mensajeAgendaLocal.getReintentos().intValue() < reintento && repuestaInBound == null){
				
				repuestaInBound = gestionServiciosVariado.servicioInBound(tr801e,idAplicacion);
				
				int reintentoAux = mensajeAgendaLocal.getReintentos().intValue()+1;
				mensajeAgendaLocal.setReintentos(new Long(reintentoAux));
			}
			
			
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensajeAgendaLocal.setReintentos(new Long(0));
				String repuestaOutBound = null;
				while (mensajeAgendaLocal.getReintentos().intValue() < reintento && repuestaOutBound == null){
					
					repuestaInBound = gestionServiciosVariado.servicioOutBound(tr801e,idAplicacion);
					int reintentoAux = mensajeAgendaLocal.getReintentos().intValue()+1;
					mensajeAgendaLocal.setReintentos(new Long(reintentoAux));
				}
				
			}
			
			log.debug("Se ha enviado con exito la tr-801-e, ahora se espera recibir un cierre de actuación....");
		}catch(NamingException ex){
			log.debug("Error instanciando el bean en el envío de activar decos tarjetas:" + ex);
			ex.printStackTrace();
		}catch(FinderException ex){
			log.debug("Error buscando elementos en el envío de activar decos Tarjetas: " + ex);
			ex.printStackTrace();
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaActivarModemTOA(java.lang.String, java.lang.String)
	 */
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion, TR135S tr135s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente

		try{
			inicializarVariables();
			Peticion_stLocalHome peticionSTLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_atisLocalHome peticionAtisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			AgendaSCSTLocalHome agendaSCSTLocalHome = (AgendaSCSTLocalHome)HomeFactory.getHome(AgendaSCSTLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome mensajeEstadoStLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			Mensaje_conf_ACSLocalHome mensajeEstadoConfACSLocalHome = (Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			
			boolean tieneErrores = false;
			ArrayList equipos = new ArrayList();
			TR802E tr802e = new TR802E();
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
			
			String aux []= idMensajePeticion.split("-"); 
			
			if (aux[1] !=null){
				mensajeEstadoSTKey = new Mensaje_estado_stKey(new Long(aux[1]));
				mensajeEstadoSTLocal = mensajeEstadoStLocalHome.findByPrimaryKey(mensajeEstadoSTKey);
			}
			
			tr802e.setIdSourceSystem(sistemaAtiempo);
			tr802e.setIdSchedule(idActuacion);
			
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
					if (tr135s.getErrorCode() != null && tr135s.getErrorCode().equals("1")){
						tieneErrores = true;
						
						error = tr135s.getErrorCode();
						descError = tr135s.getErrorDescription();
					}
				}
			}
			
			if (tieneErrores){
				tr802e.setResponse("ERROR");
				tr802e.setError(error);
				tr802e.setErrorMessage(descError);
				tr802e.setDescripcionError(descError);
				tr802e.setModem_serial(idModem);
				tr802e.setMaterial_code(codeMaterial);

				actDto.setObservacion("El modem "+idModem+" presentó errores en la configuración:"+descError, true);
				mensajeEstadoSTLocal.setCod_estado(new Integer(ComunInterfaces.estadoError));
				
				actividadEJB.grabarSinTerminar(actDto);
				
			}else{
				tr802e.setResponse("OK");	
				tr802e.setDescripcionError("");
				tr802e.setError("0");
				tr802e.setErrorMessage("");
				tr802e.setModem_serial(idModem);
				tr802e.setMaterial_code(codeMaterial);
				mensajeEstadoSTLocal.setCod_estado(new Integer(ComunInterfaces.estadoOk));
				
				actividadEJB.grabarSinTerminar(actDto);
			}
			
			
			tr802e.setId(aux[1]);
			tr802e.setDestination(sistemaAgendaSC);
			tr802e.setSource(sistemaAtiempo);
			tr802e.setInterfaz("RES_CONFIG_MODEM");
			tr802e.setVersion("1.0");
			tr802e.setApptNumber(mensajeEstadoSTLocal.getAppNumber());
			
			String trXXXeClassName = tr802e.getClass().getName().split("\\.")[tr802e.getClass().getName().split("\\.").length-1]+"IN";
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			mensajeEstadoSTLocal.setReintentos(new Long(0));
			GestionServiciosActivarBAST gestionServiciosVariado = new GestionServiciosActivarBAST();
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			
			String repuestaInBound = null;
			while (mensajeEstadoSTLocal.getReintentos().intValue() < reintento && repuestaInBound == null){
				
				repuestaInBound = gestionServiciosVariado.servicioInBound(tr802e,idAplicacion);
				
				int reintentoAux = mensajeEstadoSTLocal.getReintentos().intValue()+1;
				mensajeEstadoSTLocal.setReintentos(new Long(reintentoAux));
			}
			
			
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensajeEstadoSTLocal.setReintentos(new Long(0));
				String repuestaOutBound = null;
				while (mensajeEstadoSTLocal.getReintentos().intValue() < reintento && repuestaOutBound == null){
					
					repuestaInBound = gestionServiciosVariado.servicioOutBound(tr802e,idAplicacion);
					int reintentoAux = mensajeEstadoSTLocal.getReintentos().intValue()+1;
					mensajeEstadoSTLocal.setReintentos(new Long(reintentoAux));
				}
				
			}
			
			log.debug("Se ha enviado con exito la tr-802-e, quedo en espera de recibir un cierre de actuación....");
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
	 * @see co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTInterfaz#enviarCreacionActuacionST(java.lang.Long, co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void enviarCreacionActuacionST(Long idActuacion, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}
	public void refrescarRecursosSTB_BA( ITRxxxBase trXXXs){
		inicializarVariables();
		String nroPeticion = "";
		String trXXXsClassName = trXXXs.getClass().getName().split("\\.")[trXXXs.getClass().getName().split("\\.").length-1];
		String id="";
		String idSchedule="";
		Object xml=null;
		
		//Se valida el tipo de tr que se instancia
		if(trXXXsClassName.endsWith("TR803S")){
			nroPeticion= ((TR803S)trXXXs).getIdSchedule();
			TR803S tr803s=(TR803S)trXXXs;
			id=tr803s.getId();
			idSchedule=tr803s.getIdSchedule();
			xml=tr803s;
		}
			else if(trXXXsClassName.endsWith("TR804S")){
			nroPeticion= ((TR804S)trXXXs).getIdSchedule();
			TR804S tr804s=(TR804S)trXXXs;
			id=tr804s.getId();
			idSchedule=tr804s.getIdSchedule();
			xml=tr804s;
		}
		else{
			log.debug("El mensaje no es una TR803S o una TR804S");
			return;	
		}
		
		log.debug("Me llega en peticion NP:"+nroPeticion);
		Long idPeticion = new Long(nroPeticion.substring(2,nroPeticion.indexOf("-")));	
		co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces recursosInterfaces =  null;
		RecursosBAInterfaces recursosBAInterfaces = null;
		co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces pI = null;				
		boolean esGranite = false;
		
//		 SE VALIDA QUE TIPO DE LINEA ES
		try {
			pI = new PeticionesDelegate();				
			esGranite = pI.usaGranite(idPeticion);
		}
		catch (ATiempoAppEx e)
		{
			log.error(e);
		}
		try{
			recursosInterfaces = new RecursosDelegate();				
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(idPeticion,idAplicacion);
			
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
			
//			  CR-14657 Granite - adocarmo - inicio			
			
			if (esGranite) {
				recursosInterfaces.envioPuntosRedSTBGR(idPeticion.toString(),"RF", codActividad);				
			}
			else {
				if(trXXXsClassName.endsWith("TR803S"))
				recursosInterfaces.envioPuntosRedSTB(idPeticion.toString(),"RF",codActividad);				
			}
			
			if(trXXXsClassName.endsWith("TR804S")){
				recursosBAInterfaces = new RecursosBADelegate();
				// SIGRES
				recursosBAInterfaces.enviarConfiguracionActualBASigres(idPeticion.toString(),"");
				recursosBAInterfaces.enviarCuentaCorreo(idPeticion.toString(),"RF", codActividad);
				//fin sigres			
			}
			
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			
			//Varios Refresh - sfandino
			boolean noHayRegistro=false;
			try{
				
				Tmp_agenda_scLocal tmpAgendaLocal= tmpAgendaSCLocalHome.findbyPeti_numero(idPeticion);
				log.debug("Ya existe un registro con peti_num "+idPeticion+" se procede a eliminarlo y crear uno nuevo");
				tmpAgendaLocal.remove();
				noHayRegistro=true;
			}
			catch(ObjectNotFoundException e){
				log.debug("No existe registro en la tabla Tmp_agenda_sc con peti_num "+idPeticion+" se procede a crearlo");
				//Si no hay registros en la tabla se crea uno nuevo
				noHayRegistro=true;
			}  catch (RemoveException e) {
				
				log.debug("Error al eliminar el registro de la tabla tmp_agenda_sc: "+e);
				e.printStackTrace();
			}
			finally{
				if(noHayRegistro){
					Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (id));
					tmpAgendaSCLocal.setId_schedule(idSchedule);
					tmpAgendaSCLocal.setPeti_numero(idPeticion);
					tmpAgendaSCLocal.setXml(XMLUtilities.marshall (xml));
				}
				else{
				log.debug("Ocurrio un error al intentar eliminar el registro ya existente con peti_num = "+idPeticion+" de la tabla tmp_agenda_sc");	
				}
			}
			
		}
		catch (ATiempoAppEx e)
		{
			log.error(e);
		}
		catch (NamingException e) {
		// TODO Bloque catch generado automáticamente
			log.error(e);
		}
		catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.error(e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error(e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void refrescarRecursosSTB(TR803S tr803s) throws ATiempoAppEx{
		this.refrescarRecursosSTB_BA(tr803s);
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
	public void recibeActualizacionInventarioTV (TR805E tr805e) throws ATiempoAppEx
    {
		log.debug("Se recibe tr805e con id:"+tr805e.getId());
		try {
			inicializarVariables();
			Long idPeticion = new Long(tr805e.getIdSchedule().substring(2,tr805e.getIdSchedule().indexOf("-")));
			Peticion_stKey peticion_stKey = new Peticion_stKey(idPeticion);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			Mensaje_estadoLocal mensaje_estadoLocal = this.buscaMensajeEstado(estadoEspera);
			Mensaje_estadoKey mensajeEstadoKey = (Mensaje_estadoKey) mensaje_estadoLocal.getPrimaryKey();
			
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			Mensaje_estado_stLocal mensaje_estado_stLocal = mensaje_estado_stHome.create(idCorrelativoMensaje);
			mensaje_estado_stLocal.setPeticion_st(peticion_stLocal);
			mensaje_estado_stLocal.setFecha_envio(df.format (new java.util.Date ())) ;
			mensaje_estado_stLocal.setCod_estado(mensajeEstadoKey.cod_estado);
			mensaje_estado_stLocal.setObservaciones(tr805e.getId());
			
			tr805e.setId(idCorrelativoMensaje.toString());
			
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(idPeticion,idAplicacion);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
			mensaje_estado_stLocal.setCod_actividad_generadora(codActividad);
			
			RefrescarRecursosSTTOATVMQ enviarMensaje = new RefrescarRecursosSTTOATVMQ();
			enviarMensaje.enviarMensaje (tr805e) ;
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al ejecutar recibeActualizacionInventarioTV:"+e);
		}
    
    }

	public void enviaRefrescarRecursosTV(TR805S tr805s) throws ATiempoAppEx{

		log.debug("Se ingresa a enviaRefrescarRecursosTV con id:"+tr805s.getId());
		
		try {
			inicializarVariables ();
			Mensaje_estado_stLocal mensaje_estado_stLocal = this.buscaMensajeEstadoST(tr805s.getId());
			Mensaje_estado_stKey mensaje_estadoKey = (Mensaje_estado_stKey) mensaje_estado_stLocal.getPrimaryKey();
			
			if(mensaje_estado_stLocal.getCod_estado().intValue() == estadoEspera){
				Peticion_stLocal peticion_stLocal = mensaje_estado_stLocal.getPeticion_st();
				Peticion_stKey peticion_stKey = (Peticion_stKey) peticion_stLocal.getPrimaryKey();
				
				BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticion_stKey.cod_ave_cd,idAplicacion);
				
				String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
				String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
								
				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

				idCorrelativo=idCorrelativo.replaceAll("%2B","+");
				idCorrelativo=idCorrelativo.replaceAll("%2F","/");
				tr805s.setId(mensaje_estado_stLocal.getObservaciones());
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticion_stKey.cod_ave_cd, codActividad,idCorrelativo,null);
				Mensaje_estado_stLocal mensaje_estado_st = almacenarMensajeEstadoST(new Long(tr805s.getId()), tr805s.getApptNumber(), actDto, peticion_stKey);
				
				Collection errostoa = tr805s.getError();
				String mensajeError = null;
				for(Iterator iter = errostoa.iterator();iter.hasNext();){
					ErrorRefreshTOA errors = (ErrorRefreshTOA)iter.next();
					mensajeError = errors.getErrorcodemessage();
				}
				
				if(mensajeError != null){
					String mensajeAux [] = mensajeError.split("-");
					
					GestionRefrescarRecursosTVST gestionServiciosVariado = new GestionRefrescarRecursosTVST();
					String repuestaInBound = gestionServiciosVariado.servicioInBound(tr805s,idAplicacion, mensaje_estado_st.getReintentos(), mensajeAux[0], mensajeError);
					tr805s.setId(mensaje_estado_stLocal.getObservaciones());
					mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getInboundReintentos()));
					log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
					if(repuestaInBound != null){
						mensaje_estado_st.setReintentos(new Long(1));
						gestionServiciosVariado.servicioOutBound(tr805s,idAplicacion, mensaje_estado_st.getReintentos(),mensajeAux [0]  );
						mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getOutboundReintentos()));
					}
					
				}
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error NumberFormatException viaRefrescarRecursosTV "+ e);
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error EJBException enviaRefrescarRecursosTV "+ e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error FinderException enviaRefrescarRecursosTV "+ e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ATiempoAppEx enviaRefrescarRecursosTV "+ e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error TnProcesoExcepcion enviaRefrescarRecursosTV "+ e);
		}
	}
	
	public Long enviaRefrescarRecursosTV(long idPeticion, String idActividad) throws ATiempoAppEx{
		RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
		return null; //recursosTVDelegate.enviaRefrescarRecursosTV(idPeticion,idActividad);
	}
	
	public void recepcionCierreActuacionTOA(TR811S tr811s){
		try{
			inicializarVariables();
			String materialesDecos=VpistbbaConfig.getVariable("MATERIALES_DECOS");
			String materialesTarjetas=VpistbbaConfig.getVariable("MATERIALES_TARJETAS");
			String materialesModems=VpistbbaConfig.getVariable("MATERIALES_MODEMS");
			String materialesCamara = VpistbbaConfig.getVariable("MATERIALES_CAMARA");
			log.debug("MATERIALES_CAMARA"+materialesCamara);
			log.debug("M"+materialesDecos);
			log.debug("M"+materialesTarjetas);
			log.debug("M"+materialesModems);
			
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
			
			AgendaSCSTLocal agendaSCSTLocal = agendaSCSTLocalHome.findByPrimaryKey(tr811s.getIdSchedule());
			
			String codAveStr = tr811s.getIdSchedule().substring(2,tr811s.getIdSchedule().indexOf("-"));//agendaSCSTLocal.getId_peticion_st();
			
			Long codAve=new Long(codAveStr);
			
			Peticion_stKey peticion_stKey = new Peticion_stKey(codAve);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			/**
			 * Se guarda la tr711s para en la clase ConfiguracionServiciosTVSTServicio, evaluar si ya se ha recibido esta tr para la petición
			 * actual y así no volver a enviar la tr708e después de que se envía la tr017e de activación de decos y tarjetas. La tr708e sólo se
			 * envía en la activación de recursos de agenda SC, que es el método que está más arriba.
			 */         
			
			/**
			 * Se almacena la tr711s en 
			 * Tmp_deco_tarjetaLocal y hacer una validación en la clase ConfiguracionServiciosTVSTServicio, 
			 * para que no se vuelva a enviar la tr708e en este caso, ya que estamos en el cierre.
			 */
			DBManager dbSeq = new DBManager();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			Long idTmpDecoTarjeta = new Long (dbSeq.seqNextValLong ("CORRELATIVO_TMP_DECO_TARJ")) ;
			Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = tmp_deco_tarjetaLocalHome.create (idTmpDecoTarjeta,peticion_stLocal,XMLUtilities.marshall (tr811s)) ;
			
			/**
			 * Se evalúa el código de franqueo entrante.
			 */
			
			Codigos_franqueo_ok_agd_scLocalHome codFranqueoOKLocalHome = (Codigos_franqueo_ok_agd_scLocalHome) HomeFactory.getHome(Codigos_franqueo_ok_agd_scLocalHome.JNDI_NAME);
			String codigoBandeja = "";
			try{
				Codigos_franqueo_ok_agd_scKey codFranqueoOkKey = new Codigos_franqueo_ok_agd_scKey(tr811s.getPostageCode());
				Codigos_franqueo_ok_agd_scLocal codFranqueoOKLocal = codFranqueoOKLocalHome.findByPrimaryKey(codFranqueoOkKey);
				codigoBandeja = codFranqueoOKLocal.getBandeja();
			}catch(FinderException ex){
				log.debug("El codigo de franqueo: "+tr811s.getPostageCode()+" No se encuetra en la tabla Codigos_franqueo_ok_agd_sc se continua la activdad");

			}
			franqueoOK = true;
			String codigoFranqueo = tr811s.getPostageCode();
			Integer estadoAgendamiento = tr811s.getCodeStateSchedule();              
			
			//Req 4990 Se guardan mas datos de control para el cierre de la actuacion, se guardan independientemente del codigo
			//de franqueo
			agendaSCSTLocal.setNombre_contratista(tr811s.getTechnician().getTechnicianName());
			//cambiar lffecha
			agendaSCSTLocal.setFecha_mod(new Timestamp(new Date().getTime()));
			
			IncidentesDelegate incidentesDelegate = new IncidentesDelegate();
			String listaTiposEquipo=incidentesDelegate.recuperarParametroFromPropertiesBD("MATERIALES_EQUIPOS_ST");
			String [] listaTiposEquipoArray=listaTiposEquipo.split(",");
			
			if(agendaSCSTLocal.getEstado()!=null&&agendaSCSTLocal.getEstado().intValue()==ComunInterfaces.ACTUACION_ABIERTA){
				//Si llega mensaje de error, se cierra la actuación y se continúa la petición normalmente.
				if(franqueoOK){
					Collection materials = tr811s.getInstalledInventory().getMaterials();
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
							TR811SMaterials materials2 = (TR811SMaterials)materialsIt.next();
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
							}else{
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
									log.debug("NO SERIALIZADO");
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
						log.debug("Voy a actualizar sap y discos duros de decos para el agendamiento:"+tr811s.getIdSchedule());
						Deco_Tarjeta_Info_SapLocalHome dtisl = (Deco_Tarjeta_Info_SapLocalHome)HomeFactory.getHome(Deco_Tarjeta_Info_SapLocalHome.JNDI_NAME);
						Collection decosSap=dtisl.findByPeticion(codAve);
						Collection DecosYTarjetas=(Collection) listaDecos.clone();
						DecosYTarjetas.addAll(listaTarjetas);
						for (Iterator iter = DecosYTarjetas.iterator(); iter.hasNext();) {
							TR811SMaterials decoTr = (TR811SMaterials)  iter.next();
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
								TR811SMaterials deco = (TR811SMaterials)listaDecos.get(i);
								TR811SMaterials tarjeta = (TR811SMaterials)listaTarjetas.get(i);
								
								TR811SMaterials discoDuro = null;
								//Se verifica si la marca de deco soporta disco duro
								if(listaDecosDiscoDuro.contains(deco.getBrandEquipment()) && !listaDiscosDuros.isEmpty()){
									discoDuro = (TR811SMaterials)listaDiscosDuros.get(0);
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
					}
					
					ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(agendaSCSTLocal.getPeticion_st());
					this.setearDatosActividad(actDto);
					
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
							TR811SMaterials materialModemUtilizado = null;
							Short accion = new Short(new Integer(ComunInterfaces.accionModemConfigurado).shortValue());
							for(materialModemIterator=listaModems.iterator(); materialModemIterator.hasNext();){
								TR811SMaterials materialModem = (TR811SMaterials)materialModemIterator.next();
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
								ModemSTDTO modemDTO = new ModemSTDTO();
								modemLocal.setAccion(new Short(new Integer(ComunInterfaces.operacionParDesactivar).shortValue()));
								modemDTO.setAccion(new Integer(ComunInterfaces.accionModemLiberar));
								modemDTO.setNum_peticion(peticion_stKey.cod_ave_cd);
								modemDTO.setMarca(modemLocal.getModem_marca());
								modemDTO.setModelo(modemLocal.getModelo());
								modemDTO.setSerial(modemKey.serial);
								RecursosBAInterfaces rbaInterfaces = new RecursosBADelegate();
								//Ba naked fase II dcardenas se elimina autoconfigracion
								//rbaInterfaces.llamadoConfModemAutoInstalacion(modemDTO, actDto.getCodigoActividad(),tr811s.getId()+"#"+tr811s.getIdSchedule(),true);
								try{
									modemLocal.remove();
									modemLocal = null;
								}catch(Exception e){
									log.debug("No se puede eliminar el modem con serial: "+modemDTO.getSerial()+" porque ya fue eliminado");
								}
								log.debug("Se crea el modem, Serial: "+serialModemEnviado);
								
								// ba naked fase II dcardena
								// se cambia firma a booleano para detener la siguiente actividad hasta q se responda mensaje acs
								cierroActividad = !crearConfigurarModem(
										tr811s, modemLocalHome, agendaSCSTLocal, peticion_stLocal, actDto, materialModemUtilizado, accion);
								//modemLocal = modemNuevoLocal;
								
							}else if(serialModemActual == null && serialModemEnviado != null){
								
								
								log.debug("Se crea el modem, Serial: "+serialModemEnviado);
								// ba naked fase II dcardena
								// se cambia firma a booleano para detener la siguiente actividad hasta q se responda mensaje acs
								cierroActividad = !crearConfigurarModem(
										tr811s, modemLocalHome, agendaSCSTLocal, peticion_stLocal, actDto, materialModemUtilizado, accion);
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
								
								//Sergio 12-08-2015 se setea este dato, puede ser obligatorio.
								modemLocal.setPos_doc_sap(0);
								modemLocal.setNum_material_sap(materialModemUtilizado.getMaterial());
								modemLocal.setCentr_cost_sap(materialModemUtilizado.getCostCenter());
								modemLocal.setFlag_pet_curso(ComunInterfaces.FLAG_EQUIPO_PETICION);
							}
						}
		        	}
					
					
					if(listaEquiposNoSerializados != null && !listaEquiposNoSerializados.isEmpty()){
						for (Iterator iter = listaEquiposNoSerializados.iterator(); iter.hasNext();) {
							TR811SMaterials materialTR = (TR811SMaterials) iter.next();
							log.debug("Voy a configurar y registrar equipos no serializados para el agendamiento:"+tr811s.getIdSchedule());
							DBManager manager;
							manager=new DBManager();
							manager.setDataSource(DBManager.JDBC_VPISTBBA);
							Long bi_id=new Long(manager.seqNextValLong("VPI_ELEM_NO_SERIAL_SEQ"));
							co.com.telefonica.atiempo.soltec.ejb.eb.ElementoNoSerializadoLocalHome ensl = (ElementoNoSerializadoLocalHome)HomeFactory.getHome(ElementoNoSerializadoLocalHome.JNDI_NAME);
							ElementoNoSerializadoLocal elemento = ensl.create(bi_id,peticion_stLocal);
							Integer cantidad = materialTR.getMaterialAmount();
							if(cantidad!=null){
								elemento.setCantidad(new Long(cantidad.longValue()));
							}
							elemento.setCentrCostSap(materialTR.getCostCenter());
							elemento.setNumMaterialSap(materialTR.getMaterial());
						}
					}
					
					agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
					
					ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
					
					IActividadEJB actividadEJB = actividadFactorySTEJB.getActividad(actDto.getCodigoActividad());
					
					//dcardena ingreso soluciones TOA
					try{
						if (tr811s.getCodCompletRep()!=null && tr811s.getCodCompletRep().getCauseDamage() !=null && !tr811s.getCodCompletRep().getRepairClosCode().equals("")){
							//Proceso para agregar una solucion
							log.debug("Voy a ingresar una solucion con los datos: Localización:"+tr811s.getCodCompletRep().getRepairClosCode()+" y descripción de cierre:"+tr811s.getCodCompletRep().getCauseDamage());
							
							Codigo_cierreLocalHome codCierreLocalHome = (Codigo_cierreLocalHome)HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
							Codigo_cierre_peticionLocalHome codCierrePeticionLocalHome = (Codigo_cierre_peticionLocalHome)HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
							
							Codigo_cierreLocal codCierreLocal = codCierreLocalHome.findByDescripcion(tr811s.getCodCompletRep().getCauseDamage());
							Codigo_cierreKey codCierreKey = (Codigo_cierreKey)codCierreLocal.getPrimaryKey();
							Fecha fecCie= new Fecha(new Timestamp(new Date().getTime()));                                                           
							
							IncidentesInterfaces iI = new  IncidentesDelegate();
							iI.agregarSolucion(codAve,actDto.getCodigoActividad(),actDto.getIdUsuario(),codCierreKey.cod_cierre,fecCie.getFechaconFormato(9));
						}else if (tr811s.getCodCompletRep()!=null 
								&& tr811s.getCodCompletRep().getCorrectiveAction() !=null && !tr811s.getCodCompletRep().getCorrectiveAction().equals("")
								/*&& tr811s.getCodCompletRep().getUnrealizedCode() !=null && !tr811s.getCodCompletRep().getUnrealizedCode().equals("")*/){
							
							//Proceso para agregar un traslado de bandeja 
							log.debug("Voy a ingresar un traslado de bandeja con los datos: Area de traspaso:"+tr811s.getOtherData().getClosingRemarks()+" y diagnóstico:"+tr811s.getCodCompletRep().getCorrectiveAction());
							
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
							
							Catalago_prueba_lineaLocal catalogoPruebaLineaLocal = catalogoPruebaLineaLocalHome.findByDescription(tr811s.getCodCompletRep().getCorrectiveAction(),fam);
							Catalago_prueba_lineaKey catalogoKey = (Catalago_prueba_lineaKey)catalogoPruebaLineaLocal.getPrimaryKey();
							
							pruebaLineaDTO.setCodPrueba(catalogoKey.cod_prueba.toString()); 
							String obsTest = tr811s.getOtherData().getClosingRemarks();
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
							
							//if (tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_IT_AM)||tr711s.getItAnswer().equals(ComunInterfaces.RESULTADO_AP_MM)){
							//	esCierreAM = true;
							//}
							//actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba,"S");
						}else{
							log.debug("No se informò ningun proceso para la averìa: " +tr811s.getIdSchedule());
							
							actDto.setObservacion("No se informò ningun proceso para la averìa: " +tr811s.getIdSchedule());
							agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
							actividadEJB.grabarSinTerminar(actDto);
							cierroActividad = false;
						}
					}  catch (FinderException e) {
						log.warn("FinderException en Mejora en cierre IT() SOLTEC",e);
						actDto.setObservacion("No se cierra la petición porque ocurrió un problema identificando el cierre de la avería o el diagnóstico del técnico, valor a buscar: "+tr811s.getCodCompletRep().getCauseDamage());
						agendaSCSTLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_ABIERTA));
						actividadEJB.grabarSinTerminar(actDto);
						cierroActividad = false;
					}
					
					//End 

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
					if (!cierroActividad){
						actDto.setObservacion("En espera mensaje ACS");
						actDto.setGrabaEnBitacora(true);
						actividadEJB.grabarSinTerminar(actDto);
							log.debug("Quedo en espera de la respuesta de la activación del Modems en ACS "+codAve);
					}else{
						if (!esCierreAM){
							actividadEJB.terminar(actDto);
						}else{
							Timestamp fechaLimite = Timestamp.valueOf("2011-06-04 00:00:00");	
							Timestamp fechaPeticion = peticion_stLocal.getFec_apt_ave_ts();
							
							if (fechaPeticion.after(fechaLimite)){
								actividadEJB.terminar(actDto);
							}
						}
					}
				}else{//Fin if(franqueoOK)
				//DAVID: Dic 28 2010, si la actuación está abierta y el franqueo es no OK, entonces se cierra la actuación.
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
				log.debug("El mensaje de cierre con ID: "+tr811s.getId()+" no se tiene en cuenta, porque la actuación:"+tr811s.getIdSchedule()+" se encuentra cerrada");
				ActividadEJBDTO actDto = recuperaActividadDeBandejaIntegrada(peticion_stLocal);
				actDto.setObservacion("El mensaje de cierre con ID: "+tr811s.getId()+" no se tiene en cuenta, porque la actuación:"+tr811s.getIdSchedule()+" se encuentra cerrada");
			}
		}catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		}  catch (FinderException e) {
			log.warn("FinderException en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		} catch(Exception e){
			log.debug("Exception en recepcionCierreActuacionAgendaSC() SOLTEC",e);
		}	
		}
	/**
	 * @param actDto
	 */
	public void setearDatosActividad(ActividadEJBDTO actDto)throws ATiempoAppEx {
//		 TODO Apéndice de método generado automáticamente
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
		try {
			Estructura_InterfazLocalHome estructura_InterfazLocalHome= (Estructura_InterfazLocalHome)HomeFactory.getHome(Estructura_InterfazLocalHome.JNDI_NAME);
			Collection estructuraCollection = estructura_InterfazLocalHome.findByAci_id("WF", new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID)));
			log.debug("Se recorre encontraron un npùmero de campos: "+estructuraCollection.size());
			for (Iterator iter = estructuraCollection.iterator(); iter.hasNext();) {
				Estructura_InterfazLocal element = (Estructura_InterfazLocal) iter.next();
				Estructura_InterfazKey estructura_InterfazKey = (Estructura_InterfazKey) element.getPrimaryKey();
				actDto.setDato(estructura_InterfazKey.aci_tag,element.getAci_default_value());
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

	public void equiposEnAgendaSC(ArrayList listaEquiposAgendaSC, Peticion_stLocal peticion_stLocal,HashMap estadosHomologados){
		try{
			log.debug("Voy a configurar y registrar equipos para el agendamiento en ST..");                                                                          
			Elemento_agenda_scLocalHome elementoAgendaSC = (Elemento_agenda_scLocalHome) HomeFactory.getHome(Elemento_agenda_scLocalHome.JNDI_NAME);
			Tipo_Eq_ElementoLocalHome tipoEqElementoLocalHome = (Tipo_Eq_ElementoLocalHome) HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
			Ps_Tipo_EqLocalHome psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
			Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
			
			Iterator equiposAgendaIt=null;
			
			for(equiposAgendaIt=listaEquiposAgendaSC.iterator();equiposAgendaIt.hasNext();){
				TR811SMaterials equipoAgendaSC = (TR811SMaterials)equiposAgendaIt.next();
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
						
						yaEstaEnBD=true;
						break;
					}
				}
				
				//Si no exíste, entonces lo guardamos en la BD porque es un equipo nuevo que nos evían de Agenda SC.
				if(!yaEstaEnBD){
					String telefonoIt="0";
					if(peticion_stLocal.getNum_ide_nu()!=null){
						telefonoIt = peticion_stLocal.getNum_ide_nu();
					}
					//Para objetos elemento_peticionLocal se setea el campo telefon normal en 0 y el telefonoIt en Num_ide_nu
					Long telefonoNormal=new Long(0);
					
					Long tipoElemento = new Long (elementoAgendaSCLocal.getId_elemento_atiempo().longValue());                                                                                 
					co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionLocal elemento_peticionLocal = elementoPeticionLocalHome.create(equipoAgendaSC.getEquipmentSerialNumber(),peticion_stLocal,telefonoNormal,
							accion,tipoEquipoDesdePS.toString(),"8",tipoElemento,new Long(psId.toString()));
					elemento_peticionLocal.setTelefono_it(telefonoIt);
					
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
	private boolean esDiscoDuro(TR811SMaterials materials) {
		
		String tiposDiscoDuro = STConfig.getVariable("TIPOS_DISCOS_DUROS");
		if(tiposDiscoDuro != null){
			List listTiposDiscoDuro = Arrays.asList(tiposDiscoDuro.split(","));
			
			if(listTiposDiscoDuro.contains(materials.getTypeEquipment())){
				return true;
			}
		}
		return false;
	}
	
	private void camarasEnAgendaSC(ArrayList listaCamaras, Peticion_stLocal peticion_stLocal,HashMap estadosHomologados){
		Peticion_stKey pk= (Peticion_stKey) peticion_stLocal.getPrimaryKey();
		Long idPeticion = pk.cod_ave_cd;
		log.debug("Voy a configurar y registrar cámaras para el agendamiento en ST para la petición: "+idPeticion);
		for (int i = 0; i < listaCamaras.size(); i++) {
			TR811SMaterials materialCamara = (TR811SMaterials)listaCamaras.get(i);
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
									
									/*if(materialCamara.getExteCodeEquipment() != null){
										camaraLocal.setNumCelular(materialCamara.getExternalCodeEquipment());
									}*/
									//camaraLocal.setFecContSap(materialCamara.getPostingDateSAP());
									//camaraLocal.setClaseMovSap(materialCamara.getMoveTypeSAP());
									/*if(materialCamara.getMaterialCodeSAP() != null){
										camaraLocal.setPosDocSap(Integer.valueOf(materialCamara.getMaterialCode()));
									}else{*/
										camaraLocal.setPosDocSap(Integer.valueOf("0"));
									//}
									camaraLocal.setNumMaterialSap(materialCamara.getMaterial());
									/*camaraLocal.setCentroSap(materialCamara.getPlantSAP());
									camaraLocal.setAlmacenSap(materialCamara.getStorageSAP());
									camaraLocal.setCodLoteSap(materialCamara.getBatchCodeSAP());
									camaraLocal.setUndMedidaSap(materialCamara.getMeasurementUnit());*/
									camaraLocal.setCentrCostSap(materialCamara.getCostCenter());
									/*camaraLocal.setAreaFuncSap(materialCamara.getFuncAreaLongSAP());
									camaraLocal.setElementPepSap(materialCamara.getPepElementSAP());*/
									camaraLocal.setFlagPetCurso(ComunInterfaces.FLAG_EQUIPO_PETICION);
									//camaraLocal.setFlagMapSap(materialCamara.getFlagMatSAP());
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
	private void actualizarDatosSap(Deco_Tarjeta_Info_SapLocal decoSap,TR811SMaterials decoTr) {
	/*	decoSap.setFec_cont_sap(decoTr.getPostingDateSAP());
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
		*/
	}
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
			DBManager dbSeq = new DBManager();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
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
				
				if (opcoKey.opco_id.longValue() == OCAutoInstalacion.longValue()
						|| fampsKey.faps_id.intValue() == ComunInterfaces.familiaBandaAncha
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
			SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy") ;
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
	//BA naked FASE Dcardenas
	//se cambia la firma a boolean para asignar logica de espera en la bandeja
	private boolean crearConfigurarModem(TR811S tr811s, ModemLocalHome modemLocalHome, AgendaSCSTLocal agendaSCSTLocal, Peticion_stLocal peticion_stLocal, ActividadEJBDTO actDto, TR811SMaterials materialModemUtilizado, Short accion) throws CreateException, ATiempoAppEx {
		//se agrega falg boolean
		boolean envioMensACS=false;
		
		String telefonoModem="0";
		if(peticion_stLocal.getTel_cot_ds()!=null){
			telefonoModem=peticion_stLocal.getTel_cot_ds();
		}
		
		ModemLocal modemNuevoLocal = modemLocalHome.create(materialModemUtilizado.getEquipmentSerialNumber(), peticion_stLocal,new Long(telefonoModem), accion);
		log.debug("Se crea modem "+materialModemUtilizado.getEquipmentSerialNumber());
		modemNuevoLocal.setModem_marca(materialModemUtilizado.getBrandEquipment());								
		modemNuevoLocal.setCod_material(materialModemUtilizado.getCodeMaterial().toString());
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
		
		//rbaInterfaces.llamadoConfModemAutoInstalacion(modem, actDto.getCodigoActividad(), 
		//		tr811s.getId()+"#"+tr811s.getIdSchedule(),true);
		ACSServicioDelegate aCSServicioDelegate =new ACSServicioDelegate();
		//peticion_stKey = (Peticion_stKey) peticion_stLocal.getPrimaryKey();
		aCSServicioDelegate.enviarAutoConfiguracion(modem,actDto,peticion_stKey.cod_ave_cd,true);
		envioMensACS=true;
		
		return envioMensACS;
	}
	private Mensaje_estado_tvLocal buscaMensajeEstadoTv (String correlativo)
    {
        try
        {
        	Mensaje_estado_tvLocalHome msgEstadoTvLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome (Mensaje_estado_tvLocalHome.JNDI_NAME) ;
            Mensaje_estado_tvKey key = new Mensaje_estado_tvKey (new Long (correlativo)) ;
            
            Mensaje_estado_tvLocal mensajeEstadoTv = msgEstadoTvLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstadoTv) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstadoTv de llave=" + correlativo + " Exception: " + fex);
            return (null) ;
        }catch (NamingException e){
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }
	private Mensaje_estado_stLocal buscaMensajeEstadoST (String correlativo)
    {
        try
        {
        	Mensaje_estado_stLocalHome msgEstadoSTLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome (Mensaje_estado_stLocalHome.JNDI_NAME) ;
            Mensaje_estado_stKey key = new Mensaje_estado_stKey (new Long (correlativo)) ;
            
            Mensaje_estado_stLocal mensajeEstadoTv = msgEstadoSTLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstadoTv) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstadoTv de llave=" + correlativo + " Exception: " + fex);
            return (null) ;
        }catch (NamingException e){
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }
	
	public String getIdCorrelativoBintegrada(BintegradaLocal bintegradaLocal){
		String idCorrelativo=bintegradaLocal.getBi_url_detalle();
		int idInicio=idCorrelativo.indexOf("instanciaActividad");
		
		if(idInicio!=-1){
			idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
			int idFin=idCorrelativo.indexOf("&");
			if(idFin!=-1){
				idCorrelativo=idCorrelativo.substring(19,idFin);
			}
		}
		return idCorrelativo;
	}
	public String getCodActividadBintegrada(BintegradaLocal bintegradaLocal){
		String codActividad = bintegradaLocal.getBi_url_detalle();
		int idInicio = codActividad.indexOf("actividad");
		
		if(idInicio!=-1){
			codActividad=codActividad.substring(idInicio,codActividad.length());
			int idFin=codActividad.indexOf("&");
			if(idFin!=-1){
				codActividad=codActividad.substring(10,idFin);
				codActividad = codActividad.replace('+', ' ');
			}
		}
		
		return codActividad;
	}
	
	private Mensaje_estadoLocal buscaMensajeEstado (int llave)
    {
        try
        {
    		Mensaje_estadoLocalHome msgEstadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome (Mensaje_estadoLocalHome.JNDI_NAME) ;
            Mensaje_estadoKey key = new Mensaje_estadoKey (new Integer (llave)) ;
            
            Mensaje_estadoLocal mensajeEstado = msgEstadoLocalHome.findByPrimaryKey (key) ;
            
            return (mensajeEstado) ;
        }
        catch (FinderException fex)
        {
        	log.debug("No se encontro el MensajeEstado de llave=" + llave + " Exception: " + fex);
        	return (null) ;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			return (null) ;
        }
        
    }

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTInterfaz#recepcionActualizaInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR804S)
	 */
	public void recepcionActualizaInventarioBA(TR804S tr804s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		this.refrescarRecursosSTB_BA(tr804s);
	}

	public void enviaRefrescarRecursosSTB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
		
			log.debug("Entro a enviaRefrescarRecursosSTB...");
			Peticion_stLocal peticion_stLocal = recursos_linea_basicaLocal.getPeticion_st();
			peticion_stKey = (Peticion_stKey) peticion_stLocal.getPrimaryKey();
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(peticion_stKey.cod_ave_cd);
			
			TR803S tr803s = (TR803S) XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
			xaTelephoneData = setXA_TELEPHONE_DATA(recursos_linea_basicaLocal);
			
			Long idCorrelativoAgenda = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticion_stKey.cod_ave_cd,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticion_stKey.cod_ave_cd, codActividad,idCorrelativo,null);
			
			Mensaje_estado_stLocal mensaje_estado_st = almacenarMensajeEstadoST(idCorrelativoAgenda, tr803s.getApptNumber(), actDto, peticion_stKey);
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			GestionServiciosSTBBAST gestionServiciosVariado = new GestionServiciosSTBBAST();
			Long reintentos=mensaje_estado_st.getReintentos();
			String observaciones=mensaje_estado_st.getObservaciones();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr803s,idAplicacion, reintentos, observaciones, xaTelephoneData.toString(),"STB","0" );
			mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getInboundReintentos()));
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensaje_estado_st.setReintentos(new Long(0));
				gestionServiciosVariado.servicioOutBound(tr803s,idAplicacion,mensaje_estado_st.getReintentos(),"0");
				mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getOutboundReintentos()));
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#enviaRefrescarRecursosSTB(co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal)
	 */
	public void enviaRefrescarRecursosBA(Recursos_baLocal recursos_baLocal) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		try {
		
			log.debug("Entro a enviaRefrescarRecursosBA...");
			Peticion_stLocal peticion_stLocal = recursos_baLocal.getPeticion_st();
			peticion_stKey = (Peticion_stKey) peticion_stLocal.getPrimaryKey();
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(peticion_stKey.cod_ave_cd);
			
			TR804S tr804s = (TR804S) XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
			Long idPeticion = new Long(tr804s.getIdSchedule().substring(2,tr804s.getIdSchedule().indexOf("-")));;
			xaBroadBandData = setXA_BROADBAND_DATA(idPeticion);
			
			Long idCorrelativoAgenda = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(peticion_stKey.cod_ave_cd,idAplicacion);
			
			String idCorrelativo = this.getIdCorrelativoBintegrada(bintegradaLocal);
			String codActividad = this.getCodActividadBintegrada(bintegradaLocal);
							
			ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);

			idCorrelativo=idCorrelativo.replaceAll("%2B","+");
			idCorrelativo=idCorrelativo.replaceAll("%2F","/");
			
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticion_stKey.cod_ave_cd, codActividad,idCorrelativo,null);
			
			Mensaje_estado_stLocal mensaje_estado_st = almacenarMensajeEstadoST(idCorrelativoAgenda,  tr804s.getApptNumber(), actDto, peticion_stKey);
			Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
			
			GestionServiciosSTBBAST gestionServiciosVariado = new GestionServiciosSTBBAST();
			String repuestaInBound = gestionServiciosVariado.servicioInBound(tr804s,idAplicacion, mensaje_estado_st.getReintentos(), mensaje_estado_st.getObservaciones(), xaBroadBandData.toString(),"BA","0" );
			mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getInboundReintentos()));
			log.debug("Se recibe mensaje del servico InBound:"+repuestaInBound);
			
			if(repuestaInBound != null){
				mensaje_estado_st.setReintentos(new Long(0));
				gestionServiciosVariado.servicioOutBound(tr804s,idAplicacion, mensaje_estado_st.getReintentos(),"0");
				mensaje_estado_st.setReintentos(new Long(gestionServiciosVariado.getOutboundReintentos()));
			}
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		} catch (EJBException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug(e);
			e.printStackTrace();
		}
	}
	private XATELEPHONEDATA setXA_TELEPHONE_DATA(Recursos_linea_basicaLocal recursos_linea_basicaLocal) {
		// TODO Apéndice de método generado automáticamente
		
		log.debug("Entro a setear los valores de XATELEPHONEDATA");
		xaTelephoneData = new XATELEPHONEDATA();
		if(recursos_linea_basicaLocal.getArmario()!=null){
	    	xaTelephoneData.setArmario(""+recursos_linea_basicaLocal.getArmario());
	    }else{
	    	xaTelephoneData.setArmario("");
	    }
	    if (recursos_linea_basicaLocal.getCable()!= null){
	    	xaTelephoneData.setCable(recursos_linea_basicaLocal.getCable().toString());
	    }else{
	    	xaTelephoneData.setCable("");
	    }
	    if (recursos_linea_basicaLocal.getCaja()!=null){
	    	 xaTelephoneData.setCaja(recursos_linea_basicaLocal.getCaja());
	    }else {
	    	 xaTelephoneData.setCaja("");
	    }
		if (recursos_linea_basicaLocal.getCentral()!=null){
			 xaTelephoneData.setCentral(""+recursos_linea_basicaLocal.getCentral());
		}else{
			xaTelephoneData.setCentral("");
		}
		if (recursos_linea_basicaLocal.getPosicion_horizontal()!=null){
			 xaTelephoneData.setPosicion_Horizontal(recursos_linea_basicaLocal.getPosicion_horizontal());
		}else{
			xaTelephoneData.setPosicion_Horizontal("");
		}
		if (recursos_linea_basicaLocal.getDist_prim()!=null){
			xaTelephoneData.setDistribuidor(recursos_linea_basicaLocal.getDist_prim().toString());
			
		}else{
			xaTelephoneData.setDistribuidor("");
		}
		if (recursos_linea_basicaLocal.getDesc_dist_prim()!=null){
			 xaTelephoneData.setDescripcion_Distribuidor(recursos_linea_basicaLocal.getDesc_dist_prim());
		}else{
			xaTelephoneData.setDescripcion_Distribuidor("");
		}
		
		if (recursos_linea_basicaLocal.getDir_distribuidor()!=null){
			xaTelephoneData.setDireccion_Distribuidor(recursos_linea_basicaLocal.getDir_distribuidor());
		}else{
			xaTelephoneData.setDireccion_Distribuidor("");
		}
		if (recursos_linea_basicaLocal.getListon()!=null){
			xaTelephoneData.setListon(recursos_linea_basicaLocal.getListon());
		}else{
			xaTelephoneData.setListon("");
		}
		if (recursos_linea_basicaLocal.getPar_liston()!=null){
			xaTelephoneData.setPar_liston(recursos_linea_basicaLocal.getPar_liston().toString());
		}else{
			xaTelephoneData.setPar_liston("");
		}
		if(recursos_linea_basicaLocal.getCable()!=null){
			 xaTelephoneData.setCable(""+recursos_linea_basicaLocal.getCable());
		}else{
			 xaTelephoneData.setCable("");
		}
		if(recursos_linea_basicaLocal.getPar_cable()!=null){
			 xaTelephoneData.setPar_Cable(""+recursos_linea_basicaLocal.getPar_cable());
		}else{
			 xaTelephoneData.setPar_Cable("");
		}
		if(recursos_linea_basicaLocal.getArmario()!=null){
			xaTelephoneData.setArmario(recursos_linea_basicaLocal.getArmario());
		}else{
			xaTelephoneData.setArmario("");
		}
		if(recursos_linea_basicaLocal.getDir_armario()!=null){
			xaTelephoneData.setDireccion_Armario(recursos_linea_basicaLocal.getDir_armario());
		}else{
			xaTelephoneData.setDireccion_Armario("");
		}
		if(recursos_linea_basicaLocal.getCaja()!=null){
			xaTelephoneData.setCaja(recursos_linea_basicaLocal.getCaja());
		}else{
			xaTelephoneData.setCaja("");
		}
		if(recursos_linea_basicaLocal.getPar_caja()!=null){
			xaTelephoneData.setPar_caja(""+recursos_linea_basicaLocal.getPar_caja());
		}else{
			xaTelephoneData.setPar_caja("");
		}
		if(recursos_linea_basicaLocal.getDir_caja()!=null){
			 xaTelephoneData.setDireccion_caja(recursos_linea_basicaLocal.getDir_caja());
		}else{
			 xaTelephoneData.setDireccion_caja("");
		}
 
		xaTelephoneData.setDistancia_caja("");
		xaTelephoneData.setLatitud("");
		xaTelephoneData.setLongitud("");
		
		 if(recursos_linea_basicaLocal.getZonas_atendimiento()!=null&&recursos_linea_basicaLocal.getZonas_atendimiento().size()>0){
			Zonas_atendimientoLocal zonas_atendimientoLocal = (Zonas_atendimientoLocal) rlb.getZonas_atendimiento().iterator().next();
			Zonas_atendimientoKey zonas_atendimientoKey = (Zonas_atendimientoKey)zonas_atendimientoLocal.getPrimaryKey();
			xaTelephoneData.setZona_cobertura(""+zonas_atendimientoKey.id);
		}
		else{
			xaTelephoneData.setZona_cobertura("");
		}

		return xaTelephoneData;
	}
	private XABROADBANDDATA setXA_BROADBAND_DATA(Long idPeticion) {
		try {
			xaBroadBandData = new XABROADBANDDATA();
			peticion_stKey = new Peticion_stKey(idPeticion);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			try {
				Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome) HomeFactory
						.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioKey productoServicioKey = new Producto_servicioKey(
						peticion_stLocal.getCod_pro_ser_cd());
				Producto_servicioLocal productoServicioLocal = productoServicioLocalHome
						.findByPrimaryKey(productoServicioKey);

				if (productoServicioLocal.getVelocidad() != null
						&& productoServicioLocal.getVelocidad().length() > 0) {
					xaBroadBandData.setVelocidad(productoServicioLocal
							.getVelocidad());
				} else {
					xaBroadBandData.setVelocidad("");
				}
			} catch (FinderException ex) {
				log
						.debug("No se encontro información relacionada con la velocidad del PS, se setea valor vacío");
				xaBroadBandData.setVelocidad("");
			}
			Recursos_baLocal rba = null;
			Collection recursosBALista = peticion_stLocal.getRecursos_ba();
			if (recursosBALista != null && recursosBALista.size() > 0) {
				rba = (Recursos_baLocal) recursosBALista.iterator().next();
			}
			if (rba != null) {
				if (rba.getPuerto_actual() != null) {
					xaBroadBandData.setPuerto(rba.getPuerto_actual());
				} else {
					xaBroadBandData.setPuerto("");
				}
				if (rba.getPost_actual() != null) {
					xaBroadBandData.setPOTs(rba.getPost_actual());
				} else {
					xaBroadBandData.setPOTs("");
				}
				if (rba.getAdsl_actual() != null) {
					String adslAux[] = rba.getAdsl_actual().split("-");
					xaBroadBandData.setADSL(rba.getAdsl_actual());
					xaBroadBandData.setRACK(adslAux[0]);
				} else {
					xaBroadBandData.setADSL("");
				}
				if (rba.getDir_ip_dslam_actual() != null) {
					xaBroadBandData.setDireccion_IP_DISLAM(rba
							.getDir_ip_dslam_actual());
				} else {
					xaBroadBandData.setDireccion_IP_DISLAM("");
				}
				if (rba.getDir_ip_wan_actual() != null) {
					xaBroadBandData.setDireccion_IP_WAN(rba
							.getDir_ip_wan_actual());
				} else {
					xaBroadBandData.setDireccion_IP_WAN("");
				}
				if (rba.getDir_ip_lan_actual() != null) {
					xaBroadBandData.setDireccion_IP_LAN(rba
							.getDir_ip_lan_actual());
				} else {
					xaBroadBandData.setDireccion_IP_LAN("");
				}
				if (rba.getMasc_lan_actual() != null) {
					xaBroadBandData.setMascara_LAN(rba.getMasc_lan_actual());
				} else {
					xaBroadBandData.setMascara_LAN("");
				}
				if (rba.getFrame_actual() != null) {
					xaBroadBandData.setFrame(rba.getFrame_actual());
					xaBroadBandData.setSUBRACK(rba.getFrame_actual());
				} else {
					xaBroadBandData.setFrame("");
				}
				if (rba.getSlot_actual() != null) {
					xaBroadBandData.setTarjeta_Slot(rba.getSlot_actual());
				} else {
					xaBroadBandData.setTarjeta_Slot("");
				}
				if (rba.getVpivci_actual() != null) {
					xaBroadBandData.setVPI_VCI_Cliente(rba.getVpivci_actual());
				} else {
					xaBroadBandData.setVPI_VCI_Cliente("");
				}
				if (rba.getVpivci_red_actual() != null) {
					xaBroadBandData.setVPI_VCI_Red(rba.getVpivci_red_actual());
				} else {
					xaBroadBandData.setVPI_VCI_Red("");
				}
				if (rba.getFather_email_actual() != null) {
					xaBroadBandData.setUsuario_Acceso(rba
							.getFather_email_actual());
				} else {
					xaBroadBandData.setUsuario_Acceso("");
				}
			}

		} catch (FinderException ex) {
			log.debug(ex);
		} catch (NamingException ex) {
			log.debug(ex);
		}
		return xaBroadBandData;
	}

	//TOA se agrega validacion si es localidad TOA para el boton de refrescar ESB
	public boolean esLocalidadToa(Long idpeticion){
		boolean esToa=false;
		try {
			inicializarVariables();
			log.debug("Se va a validar si es TOA "+idpeticion);
			Peticion_stKey peticion_stKey = new Peticion_stKey(idpeticion);
			Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey);
			
			LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadLocal localidadlocal = localidadHome.findByPrimaryKey(new LocalidadKey(peticion_stLocal.getCod_loc()));
			log.debug("Se va a validar si la localidad "+peticion_stLocal.getCod_loc()+" es TOA de la averia "+idpeticion);	
			
			if(localidadlocal.getLocalidad_toa() != null && localidadlocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA){
				esToa=true;	
				log.debug("La localidad "+peticion_stLocal.getCod_loc()+" es de TOA");
			}
			
		} catch (FinderException e) {
			log.debug("La localidad no se encuentra en tabla de localidades"+e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("No se pudo levantar el localhome de localidad"+e);
		}
		return esToa;
	}
}