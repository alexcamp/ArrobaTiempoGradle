package co.com.telefonica.atiempo.vpistbba.recursos.ejb;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PeticionDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey;
import co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscLocal;
import co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_psKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaKey;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Servicio_basico_supl_conec6_apsc_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Servicio_basico_supl_conec6_apsc_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ZonasCoberturaPSMarcaLocal;
import co.com.telefonica.atiempo.ejb.eb.ZonasCoberturaPSMarcaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Zonas_atendimientoLocal;
import co.com.telefonica.atiempo.ejb.eb.Zonas_atendimientoLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.CameraRequest;
import co.com.telefonica.atiempo.interfaces.atiempo.CameraResponse;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.interfaces.atiempo.EndPointTypeFTTC;
import co.com.telefonica.atiempo.interfaces.atiempo.EquipmentFTTC;
import co.com.telefonica.atiempo.interfaces.atiempo.Localidad;
import co.com.telefonica.atiempo.interfaces.atiempo.ProductoServicioTR;
import co.com.telefonica.atiempo.interfaces.atiempo.SippstDataFTTC;
import co.com.telefonica.atiempo.interfaces.atiempo.SpecialService;
import co.com.telefonica.atiempo.interfaces.atiempo.SpecialServicesRequest;
import co.com.telefonica.atiempo.interfaces.atiempo.SpecialServicesResponse;
import co.com.telefonica.atiempo.interfaces.atiempo.TR002E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR002S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR003S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR047E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR047S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049EAssignedNumber;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049EBasicLine;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049EWideBand;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR050E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR050S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR052E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR055E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR055S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR056E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR056S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR511E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR511S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR513E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR513S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR518E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR518S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR601E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR601S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR603E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR603S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR604E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR604S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR613E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR613S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR614E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR614S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.control.Password;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANLocal;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANLocalHome;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.AsignacionRecursosGraniteSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.AsignacionRecursosSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.ConfiguracionAutomaticaSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.CreaOdsGraniteSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.LiberacionRecursoGraniteSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.LiberacionRecursoSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudAltamiraSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudPuntosRedGraniteSTBMQ;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.SolicitudPuntosRedSTBMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocal;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocalHome;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.senders.SenderFactory;
import co.com.telefonica.atiempo.vpistbba.senders.SenderTr601Base;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarCamaraAgendaSCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ActivarLineasTroncalSipMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfCamaraZTEMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfClienteZTEMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfMediacionMovilMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfPaqueteMovilMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaEOCMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaIMSMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfiguracionAutomaticaMSANMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfigurarNapsterMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfigurarPdVAMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfigurarPresenciaDigitalMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultarCliPotConstructoraMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.ConsultarTroncalSipMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.DesconfiguracionAutomaticaIMSMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarRefrecarDatosMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.EnviarTutorWebMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.RecargaFijaMovilMQ;
import co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Tipo_prod_tutor_webLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webLocalHome;

import com.tecnonautica.utiles.db.DBException;
import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;

//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class RecursosServiciosBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements RecursosInterfaces {
		
		protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;

//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//		private Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome ;
//		private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome;
//		private PeticionLocalHome peticionLocalHome ;
//		private LocalidadLocalHome localidadLocalHome;
//		private ConectorLocalHome conectorLocalHome;
//		private Mensaje_estadoLocalHome mensajeEstadoLocalHome ;
//		private PeticionesServicesLocalHome peticionesServicesLocalHome;
//		private Producto_servicioLocalHome producto_servicioLocalHome;
//		private UsuarioLocalHome usuarioHome;
//		private Catalogo_causalLocalHome catalogo_causalHome;
//		private Estado_psLocalHome estado_psHome;
//		private Causal_peticionLocalHome causal_peticionHome;
//		private CentralLocalHome centralHome;
//		
//		private Estado_ps_peticionLocalHome estado_ps_peticionHome;
//		private Dslam_conec9_apscLocalHome dslam_conec9_apscLocalHome;
//		private Zonas_atendimientoLocalHome zonas_atendimientoLocalHome;
//		private Servicio_basico_supl_conec6_apsc_lineaLocalHome servicio_basico_supl_conec6_apsc_lineaLocalHome;
		
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
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
			
			// TODO: revisar este formato
			df = new SimpleDateFormat ("dd/MM/yyyy") ;
			
			buscaHome ();
		}
    
    	/*
    	 * Metodo Generador de Home
    	 */
		private void buscaHome (){
//			TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//			try {
//				
//			//	Creacion de los Home
//			msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
//			recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
//			mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
//			peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
//			conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
//			producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//			
//			estado_ps_peticionHome = (Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
//			dslam_conec9_apscLocalHome = (Dslam_conec9_apscLocalHome) HomeFactory.getHome(Dslam_conec9_apscLocalHome.JNDI_NAME);
//			servicio_basico_supl_conec6_apsc_lineaLocalHome = (Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
//			peticionesServicesLocalHome = (PeticionesServicesLocalHome)  HomeFactory.getHome(PeticionesServicesLocalHome.JNDI_NAME);
//			usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
//			catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
//			estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
//			causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
//			centralHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
////			TODO - Inicio - agonzalez- 28/04/2008- Zonas de Atendimiento		
//			zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
//			peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome (PeticionLocalHome.JNDI_NAME) ;
//			} catch (NamingException e) {
//				log.debug(" Creacion de Local Home Nulls");
//			}
			}
    
    	/*
    	 * Metodo validador Home
    	 */
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//		private void validaHome ()
//		throws ATiempoAppEx
//		{
//			// Validacion de los Home
//			if (msgEstadoLineaLocalHome == null || recursos_linea_basicaLocalHome == null || mensajeEstadoLocalHome == null || peticionLocalHome == null ||
//				localidadLocalHome == null || conectorLocalHome == null 
//				|| estado_ps_peticionHome == null
//				|| dslam_conec9_apscLocalHome == null
//				|| servicio_basico_supl_conec6_apsc_lineaLocalHome == null
//				|| peticionesServicesLocalHome == null
//				|| producto_servicioLocalHome == null)
//				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
//		}

	// REQ FTTC 31/03/2014 dcardena 
	// funcion que valida si los datos son FTTC o no
   public String validaFTTC(String fabricante ,String modelo ,String slot ,String password ,
   		String usuario ,String ipLb,String codAct, 
		Mensaje_estado_lineaLocal mensajeEstadoLineaLocal) throws NumberFormatException, Exception
   {
   
	   	//se crea variable la cual retorna la accion fttc,LB o PGI
	   	String accion ="";
	   	//se valida que si alguno de los capos contiene datos es FTTC
	   	if((password !=null && !password.equals(""))||(usuario != null && !usuario.equals(""))
	   			|| (ipLb != null && !ipLb.equals("") && !ipLb.equals("0")))
	   	{
	   		//se valida que si algun campo viene null o vacio se deriva a PGI
	   		if(ipLb==null ||ipLb.equals("")|| fabricante==null||fabricante.equals("")
	   				||modelo==null|| modelo.equals("")||slot==null||slot.equals("")||
	   				password==null||password.equals("")||usuario==null||usuario.equals(""))
	   		{
	   			//se setea PGI
	   			accion="PGI";
	   			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				log.debug("Fueron encontrados algunos campos FTTC con datos, todos los campos FTTC deben contener informacion. Se redirige a PGI.");
				actDto.setObservacion("Fueron encontrados algunos campos FTTC con datos, todos los campos FTTC deben contener informacion. Se redirige a PGI.",true );
				insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long (701),actDto.getIdActividadFlujo());
				actividadEJB.terminar(actDto);	

			//else que indica que todos los campos contienen datos FTTC
			}else{
				// se setea FTTC
				accion="FTTC";	
			}
	   	//else que indica que no llegaron campos FTTC es linea LB
	   	}else{
	   		//se setea LB
	   		accion="LB";
	   	}
	   	//se retorna la accion
	   	log.debug("Accion es : "+accion);
	   	return accion;
	   
   }
		
	/**
	 * Metodo que recive la respuesta del legado y genera las actividades de WF, segun los Id Actividad
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#asignarRecurso(java.lang.Integer, java.lang.Integer, java.lang.Long, co.com.telefonica.atiempo.vpistbba.dto.RecursoAPSCDTO)
	 */
	public void asignarRecursoSTB(TR010S tr010s) throws ATiempoAppEx {
		
		
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		//@idrincon req - 1036 -28/10/2010
		boolean cambioTelefono = false;
		//fin req -28/10/2010
		
		try
		{
						
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			
			bExecution = BackendTraceUtil.initExecution(tr010s, log);
			bExecution.setNumPetAtiempo(new Long(tr010s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr010s.getId());
			bExecution.startOperation();
			
			String codAct = "";
			Fecha fecha=new Fecha();
			
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			
			Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr010s.getId()));
		    Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
	            
		   try
		   {
				mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
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
				log.debug(
					"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr010s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr010s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
			
			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
				log.debug(
					"Es estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr010s));
				return;
			}
			
			//Obtengo el codigo de la actividad generadora
			codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();

			Recursos_linea_basicaLocal recursos_linea_basicaLocal;
			Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
			
			Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			
			if ( recursosLineaBasica.size() == 0){
				Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
				recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos);
			}else{
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
			}			
			
			recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
			recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);
			
			//TODO: Raúl Triviño - 00029652 - Adición del estado de la línea para guardarlo en la tabla
			recursos_linea_basicaLocal.setEst_linea(tr010s.getStateLine());
			//End TODO
			
			String msgErrorODSCentral = "";
			//Si no vine la ods o si no viene la central... no se guarda la ODS. Esto permite reintentar.
			if(tr010s.getOdsNumber()>0 ){

				recursos_linea_basicaLocal.setOds_apsc(new Long(tr010s.getOdsNumber()));
				
			}else{
				if (tr010s.getOdsNumber() < 1){
					msgErrorODSCentral=msgErrorODSCentral + ".Incorrecta ODS=" + tr010s.getOdsNumber();
				}
			}
			
			if (tr010s.getCentralCode() < 1){
				msgErrorODSCentral=msgErrorODSCentral + ".Incorrecta Central=" + tr010s.getCentralCode();
			}
				
			// Si la respuesta es incorrecta o la central = 0 se va a la asginacion manual
			log.debug("ERROR: " + tr010s.isError());
			log.debug("RESPONSE: " + tr010s.isResponse());
			log.debug("RESERVE RESULT: " + tr010s.isReserveResult());
			log.debug("central code: " + tr010s.getCentralCode());
			if (tr010s.isError() == true || tr010s.isResponse() == false || tr010s.isReserveResult() == false || tr010s.getCentralCode() < 1 ){
				if(mensajeEstadoLineaLocal.getAccion()!=null &&(mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
					if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
						log.debug("La actividad corresponde a TOA");
						TOADelegate toaDelegate = new TOADelegate();
						toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
					}else{
						enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
					}
					return;
				}
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				mensajeEstadoLineaLocal.setDesc_error(tr010s.getErrorMessage());

				log.debug("Entramos a la Generacionde Actividades de Asignacion Manual Cod.Actividad : " + codAct);
				mensajeEstadoLineaLocal.setF_reference_14(mensajeManualLocal);

				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
				// Si se usa este conector en la actvidad de Puntos de red BA es porque es una consulta.
				if (VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct)){
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					actDto.setObservacion("Error en la Consulta de Recursos." + tr010s.getErrorMessage() + msgErrorODSCentral);
				}
				else{
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
					actDto.setObservacion("Error en la Asignacion de Recursos. " + tr010s.getErrorMessage() + msgErrorODSCentral);
				}
				
				actividadEJB.terminar(actDto);
 
				return;
				
			}
			
			//Recupero peticion
			PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();

			//Asigno Central
			CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
			CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(new CentralKey(new Long(tr010s.getCentralCode())));
			peticionLocal.setFk_03_central(centralLocal);
		//se agrega validacion de baja para que no repise los datos anteriormente obtenidos en oiptener puintos de red
		if(!peticionLocal.getTica_id().equals(ComunInterfaces.opCoTipoBaja)){
			
			/*  
			 *  CR 00024071 - 2009/04/20 - 3
			 *		Se envia a Gestion de Recursos si el número de teléfono es no valido - German P.
			 */
			String dsTelefono = String.valueOf(tr010s.getPhoneNumber());
			if (dsTelefono.equals("-1")){
				// Error: enviar a gestion de recurso
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
				actDto.setObservacion("Error en la Asignacion de Recursos. El formato del número de teléfono del Cliente no es correcto.");
				actividadEJB.terminar(actDto);
				return;
			}
			
			//peticionLocal.setNum_ide_nu_stb(dsTelefono);
			
			//@idrincon - req 1038 1038 28/10/2010
			String telefonoOld = null;
			RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
			boolean esTrasladoba = recursosBADelegate.esTrasladoBa(mensajeEstadoLineaLocal.getPeti_numero());
			boolean esTrasladolb = tieneTrasladoLB(mensajeEstadoLineaLocal.getPeti_numero());
			boolean esTrasladotv = recursosBADelegate.esTrasladoTv(mensajeEstadoLineaLocal.getPeti_numero());
			boolean traslado = false;
			
			if( esTrasladoba || esTrasladolb || esTrasladotv ){
				traslado = true;
			}
			
			if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")) && traslado){
				telefonoOld = this.consultarTelefono(mensajeEstadoLineaLocal.getPeti_numero());
				peticionLocal.setNum_ide_nu_stb(dsTelefono);
				if(telefonoOld != null && telefonoOld.length() > 0){
					if( !dsTelefono.equalsIgnoreCase(telefonoOld)){
						cambioTelefono = true;
					}
				}else{
					if (dsTelefono != null && dsTelefono.length() > 0){
						cambioTelefono = true;
					}
				}
			}else{
				telefonoOld = peticionLocal.getNum_ide_nu_stb();
				peticionLocal.setNum_ide_nu_stb(dsTelefono);
				if(!telefonoOld.equalsIgnoreCase(dsTelefono) && telefonoOld != null && !telefonoOld.equals("")){
//					fin req 1038 28/10/2010
					recursos_linea_basicaLocal.setTelefono_ant(new Long(telefonoOld));
					log.debug("Se agregan los valores asignados en anteriores");
					asignarValoreRLB(recursos_linea_basicaLocal);

				}
					
			}
			
            //Req. 13420 - Jesus Carvajal - 10/04/2012
			if (tr010s.getStateLine() != null && tr010s.getStateLine().equalsIgnoreCase(ComunInterfaces.ESTADO_LINEA_DEDICADO)){
				recursos_linea_basicaLocal.setInd_dedicado(new Short("1"));
				recursos_linea_basicaLocal.setCaja_dedicado(tr010s.getBox());
				recursos_linea_basicaLocal.setArmario_dedicado(tr010s.getCloset());
				recursos_linea_basicaLocal.setPar_caja_dedicado(new Long (tr010s.getBoxPair()));
				recursos_linea_basicaLocal.setCable_dedicado(tr010s.getCable());
				recursos_linea_basicaLocal.setPar_cable_dedicado(new Long (tr010s.getCablePair()));
				recursos_linea_basicaLocal.setDir_armario_dedicado(tr010s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_caja_dedicado(tr010s.getBoxAddress());
				recursos_linea_basicaLocal.setTipo_caja_dedicado(tr010s.getBoxType());
				recursos_linea_basicaLocal.setCod_central_dedicado(new Long (tr010s.getCentralCode()));
				log.debug("Colocados los datos en el front de Red Dedicada");
				
			}
			else{
				recursos_linea_basicaLocal.setInd_dedicado(new Short("0"));
				recursos_linea_basicaLocal.setCaja_asg(tr010s.getBox());
				recursos_linea_basicaLocal.setArmario_asg(tr010s.getCloset());
				recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr010s.getBoxPair()));
				recursos_linea_basicaLocal.setCable(tr010s.getCable());
				recursos_linea_basicaLocal.setPar_cable(new Long(tr010s.getCablePair()));
				recursos_linea_basicaLocal.setDir_armario(tr010s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_caja(tr010s.getBoxAddress());
				recursos_linea_basicaLocal.setTipo_caja(tr010s.getBoxType());
				recursos_linea_basicaLocal.setCod_central(new Long(tr010s.getCentralCode()));
				log.debug("Colocados los datos en el front de Red Origen");
			}
			//Req. 13420 - Fin
			
			//@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
			//se realiza validacion para saber si algun campo FTTC contiene datos
			//se agrega nueva validacion 31/03/2014 cardena
			//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
			String accion = validaFTTC(tr010s.getFttcfab(), tr010s.getFttcmode() , tr010s.getFttcslot() ,
					tr010s.getFttcpwd() , tr010s.getFttcusu() , tr010s.getFttciplb(),codAct,mensajeEstadoLineaLocal);
			
			if(accion.equals("LB"))
			{	
				// este else espara saber si no trae equipos fttc
				log.debug("No Trae Equipos FTTC");
				if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&!recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
				{
					recursos_linea_basicaLocal.setRec_fttc_asg("N");	
				}
			}else if(accion.equals("PGI")){	
				return;							
			}else if(accion.equals("FTTC")){
			log.debug("Trae Equipos FTTC-");
			//se instancia la clase del password FTTC
			int longitud=0;
								
			Password generarPass = new  Password();
		
			//se valida que tipo de operacion comercial llega 1 es alta 
			
				//se trae de la tabla propertiesBD la longitud del password
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
				// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
				recursos_linea_basicaLocal.setFttciplb_asg(tr010s.getFttciplb());
				recursos_linea_basicaLocal.setFttcpwd_asg(tr010s.getFttcpwd());
				recursos_linea_basicaLocal.setFttcusu_asg(tr010s.getFttcusu());
				recursos_linea_basicaLocal.setFttcslot_asg(tr010s.getFttcslot());
				recursos_linea_basicaLocal.setFttcfab_asg(tr010s.getFttcfab());
				recursos_linea_basicaLocal.setFttcmode_asg(tr010s.getFttcmode());
				//el campo que nos dice que hay equipos De tipo FTTC
				recursos_linea_basicaLocal.setRec_fttc_asg("S");
				recursos_linea_basicaLocal.setRec_fttc_ant("N");
				//se genera el password y se le envia la longitud
				longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
				recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
				log.debug(tr010s.getFttciplb());
				log.debug(tr010s.getFttcpwd());
				log.debug(tr010s.getFttcusu());
				log.debug(tr010s.getFttcslot());
				log.debug(tr010s.getFttcfab());
				log.debug(tr010s.getFttcmode());
		
				
						}
						
							// fin - requerimiento - requerimiento fttc 	
							
							
			
			
			recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr010s.getSecondaryDistributor()));
			//TODO: Falta el campo en la Interface : 
			recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr010s.getDistributorDescription());
			recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr010s.getPrimaryDistributor()));
			//TODO: Falta el campo en la Interface : recursos_linea_basicaLocal.setDesc_dist_prim_asg(tr010s.getDistributorDescription());
			
			recursos_linea_basicaLocal.setListon_asg(tr010s.getStrip());
			recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr010s.getStripPair()));
			recursos_linea_basicaLocal.setDesc_central(tr010s.getCentralDescription());
			
			//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
			
			recursos_linea_basicaLocal.setRack(tr010s.getRack().toString());
			recursos_linea_basicaLocal.setSubRack(tr010s.getSubRack().toString());
			/*
			 * CR 00024071 - 2009/04/20 - 3 - German P.
			 * 		Se cambia para control del numero de telefono
			 */
			//recursos_linea_basicaLocal.setTelefono_asg(new Long(tr010s.getPhoneNumber()));
			recursos_linea_basicaLocal.setTelefono_asg(new Long(dsTelefono));
			tr010s.setReserveResult(false);
			//recursos_linea_basicaLocal.setTelefono_ant(new Long(tr010s.get));			
			recursos_linea_basicaLocal.setLen(tr010s.getLen());
			recursos_linea_basicaLocal.setDir_distribuidor(tr010s.getDistributorAddress());
			recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr010s.getHorizontalPosition());
			
			mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
			
			//Req 14209 - Jesus Carvajal - 19042012
			recursos_linea_basicaLocal.setFecha_asig_recurso(fecha.getTimestamp());
			//Fin Req 14209
			
			if ((tr010s.getDslams() == null) || (tr010s.getDslams().size()<1))
			{
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
					if(mensajeEstadoLineaLocal.getAccion().equals("F")){
						if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
							log.debug("La actividad corresponde a TOA");
							TOADelegate toaDelegate = new TOADelegate();
							toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
						}else{
							enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
						}
					}
						return; 
				}
				//Si no viene Dslam y la consulta la invoca Puntos de red BA, entonces se deriva a PGI
				if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct)){
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					//	agregado adecarlini	
					String codError = String.valueOf(tr010s.getTypeError());
					String nombreClase=TR010S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR010S.class.getName());
					if(errorLegado != null){
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, codAct, errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, codAct, new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());											
					}
					//
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					//modificado adecarlini
					//insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, codAct, new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());
					//fin
					log.debug("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
					actDto.setObservacion("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
					actividadEJB.terminar(actDto);
					return;
				}
			}
			else
			{
				ArrayList listaZonasOld = new ArrayList();
				ArrayList listaZonasNew = new ArrayList();
				
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")))
				{
					listaZonasOld = this.consultaZonasAtendimiento(mensajeEstadoLineaLocal.getPeti_numero());
					
					log.debug("Tengo que borrar los Dslam que tenia antes");
					borrarDslams(recursos_linea_basicaLocal);
					borrarZonas(recursos_linea_basicaLocal);//correccion defecto 21331 
				}
				log.debug("Cantidad de Dslam " + tr010s.getDslams().size());
				//*****cambio
				//TODO - Inicio - gonzalez- 12/06/2008 - Zonas de Atendimiento
		
				
				Zonas_atendimientoLocal zona_atendimientoLocal=null;
				borrarZonas(recursos_linea_basicaLocal);
				for (Iterator iter = tr010s.getDslams().iterator(); iter.hasNext();){
					Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Dslam1 dslam1 = (Dslam1) iter.next();
					if (dslam1 == null ){
						log.debug("Las Zonas vienen NULL");
					}else{
						log.debug("Las Zonas vienen OK");
					}
					Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					if(zona_atendimientoLocal==null){
						//Se valida que es la primera zona 
						zona_atendimientoLocal=validarPSPromocion(peticionLocal,dslam1,recursos_linea_basicaLocal);	
					}else{
						zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
						zona_atendimientoLocal.setIp(dslam1.getIp());
						zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
					
				}


				if(mensajeEstadoLineaLocal.getAccion()!=null &&(mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
					//@idrincon -req 1038 28/10/2010 se agrega if de validacion
					if( !cambioTelefono ){
						recursos_linea_basicaLocal.setCambio_zonas("false");
						listaZonasNew = this.consultaZonasAtendimiento(mensajeEstadoLineaLocal.getPeti_numero());
						
						if (listaZonasOld.size() != listaZonasNew.size()){
							recursos_linea_basicaLocal.setCambio_zonas("true");
							log.debug("ES diferente: true");
						}else{
							masterFor: for(int i  = 0; i < listaZonasOld.size(); i++){	
								String zonaLocalOld = (String)listaZonasOld.get(i);
								
								slaveFor: for(int j  = 0; j < listaZonasNew.size(); j++){
									String zonaLocalNew = (String)listaZonasNew.get(j);
									
									if (zonaLocalOld.equals(zonaLocalNew)){
										continue masterFor;
									}
								}
								
								log.debug("ES diferente: true");
								recursos_linea_basicaLocal.setCambio_zonas("true");
								break masterFor;
							}
						}
					}else{
						log.debug("RecursosServiciosBean: asignarRecursoSTB: recursos_linea_basicaLocal.setCambio_zonas: true");
						recursos_linea_basicaLocal.setCambio_zonas("true");
					}
					//fin req 1038 28/10/2010
				}
				
//				  for (Iterator iter = tr010s.getDslams().iterator();	iter.hasNext();) {
//					Long idDslam =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
//
//					Dslam1 dslam = (Dslam1) iter.next();
//					if (dslam == null ){
//						log.debug("El Dslam viene NULL");
//					}else{
//						log.debug("El Dslam viene OK");
//					}
//					
//					Dslam_conec9_apscLocal dslam_conec9_apscLocal  = dslam_conec9_apscLocalHome.create(idDslam);
//					dslam_conec9_apscLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
//					log.debug(" La ip del Dslam "+dslam.getIp() );
//					log.debug(" El Tipo del Dslam " + dslam.getDslamType());
//					dslam_conec9_apscLocal.setIp(dslam.getIp());
//					dslam_conec9_apscLocal.setTipo_dslam(dslam.getDslamType());
//				}
//				TODO -Fin - gonzalez- 12/06/2008 - Zonas de Atendimiento
			}			
		
		
			
			if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
				if(mensajeEstadoLineaLocal.getAccion().equals("F")){
					if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
						log.debug("La actividad corresponde a TOA");
						TOADelegate toaDelegate = new TOADelegate();
						toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
					}else{
						enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
					}
				}
				return;
			}
		}
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
			actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
			actividadEJB.terminar(actDto);
		
			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
					
			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				
			} catch (NamingException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			}
			catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			} 
			finally{  
				bExecution.endAll();
				// CR15338 - @Trace - ana santos - 04/08 - FIN 
			}
			
	}
	
	/**
	 * @param recursos_linea_basicaLocal
	 */
	private void asignarValoreRLB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) {
		// TODO Apéndice de método generado automáticamente
		recursos_linea_basicaLocal.setCable_ant(recursos_linea_basicaLocal.getCable());
		recursos_linea_basicaLocal.setArmario_ant(recursos_linea_basicaLocal.getArmario_asg());
		recursos_linea_basicaLocal.setCaja_asg(recursos_linea_basicaLocal.getCaja_asg_ant());
		recursos_linea_basicaLocal.setCentral_ant(recursos_linea_basicaLocal.getCod_central());
		recursos_linea_basicaLocal.setDesc_central(recursos_linea_basicaLocal.getDesc_central_ant());
		recursos_linea_basicaLocal.setPosicion_horizontal_ant(recursos_linea_basicaLocal.getPosicion_horizontal_asg());
		recursos_linea_basicaLocal.setDesc_dist_prim_asg(recursos_linea_basicaLocal.getDesc_dist_prim_ant());
		recursos_linea_basicaLocal.setDist_sec_ant(recursos_linea_basicaLocal.getDist_sec_asg());
		recursos_linea_basicaLocal.setDist_prim_ant(recursos_linea_basicaLocal.getDist_prim_asg());
		recursos_linea_basicaLocal.setListon_asg(recursos_linea_basicaLocal.getListon_asg_ant());
		recursos_linea_basicaLocal.setLen_anterior(recursos_linea_basicaLocal.getLen());
		recursos_linea_basicaLocal.setPar_cable_ant(recursos_linea_basicaLocal.getPar_cable());
		recursos_linea_basicaLocal.setPar_caja_ant(recursos_linea_basicaLocal.getPar_caja_asg());
		recursos_linea_basicaLocal.setPar_liston_ant(recursos_linea_basicaLocal.getPar_liston_asg());
	}
	
	/**
	 * @param recursos_linea_basicaLocal
	 */
	private void borrarDslams(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx
	{
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
		Recursos_linea_basicaKey key=(Recursos_linea_basicaKey) recursos_linea_basicaLocal.getPrimaryKey();
			try
			{
			Dslam_conec9_apscLocalHome dslam_conec9_apscLocalHome=(Dslam_conec9_apscLocalHome) HomeFactory.getHome(Dslam_conec9_apscLocalHome.JNDI_NAME);
			
			Collection listaDslams=dslam_conec9_apscLocalHome.findByConector(key.id_conector);
			for(Iterator iterator=listaDslams.iterator();iterator.hasNext();)
			{
				Dslam_conec9_apscLocal dslam_conec9_apscLocal=(Dslam_conec9_apscLocal) iterator.next();
				dslam_conec9_apscLocal.remove();
			}
		}
		catch (FinderException e1)
		{
			log.error("",e1);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e1);
		} catch (EJBException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		} catch (RemoveException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		}
		catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		
	}

	/**
	 * @param recursos_linea_basicaLocal
	 */
	private void borrarZonas(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx
	{
		Recursos_linea_basicaKey key=(Recursos_linea_basicaKey) recursos_linea_basicaLocal.getPrimaryKey();
		
			try
			{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Zonas_atendimientoLocalHome zonas_atendimientoLocalHome=(Zonas_atendimientoLocalHome) HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
			
			Collection listaDslams=zonas_atendimientoLocalHome.findByConector(key.id_conector);
			for(Iterator iterator=listaDslams.iterator();iterator.hasNext();)
			{
				Zonas_atendimientoLocal zona_atendimientoLocal=(Zonas_atendimientoLocal) iterator.next();
			    zona_atendimientoLocal.remove();
			}
		}
		catch (FinderException e1)
		{
			log.error("",e1);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e1);
		} catch (EJBException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		} catch (RemoveException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		}
		catch (NamingException e)
		{
			e.printStackTrace();	
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		
	}
	/*
	 * Metodo encargado de crear el mesaje que se envia al legado por MQ
	 *  (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#enviarRecursoSTB(java.lang.Long, java.lang.String)
	 */
	public void enviarRecursoSTB(Long peticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx{
		
		String tmpCarac;
		try{

			TR010E tr010e = new TR010E();
			boolean tienePCNaked = false;
			boolean tieneVOIPNaked = false;
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			
			Caracteristicas_lineaLocalHome caracteristicasLocalHome = (Caracteristicas_lineaLocalHome)HomeFactory.getHome(Caracteristicas_lineaLocalHome.JNDI_NAME);
			PeticionKey peticionKey = new PeticionKey(peticion);
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
//			//arreglo direcciones 
//			Long numeroAtis=(new Long (0));
//			Collection s=peticionLocal.getFk_01_pet_atis().getAgrupacion_atis();
//			for(Iterator iter3 = s.iterator();iter3.hasNext();)
//			{
//				Agrupacion_atisLocal agrupacion_atisLocal= (Agrupacion_atisLocal) iter3.next();
//				Agrupacion_atisKey ak=(Agrupacion_atisKey)	agrupacion_atisLocal.getPrimaryKey();
//				numeroAtis=ak.fk_pet_agrupacion_cod_pet_cd;
//			}
//			CorregirDireccionPeticionTraslado(numeroAtis,peticion);
//			//------------------------
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			Long ps_id=null;

			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
			{
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();

				//Yumbleiner Calderon CR7390
				if (operacion_comercialKey.opco_id.longValue() == ComunInterfaces.opCoCambioTitular){
					if(peticionesDelegate.pasaPSyOcXActividad(peticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
					{
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() 
								|| fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							ps_id=productoServicoKey.ps_id;
							break;
						}										
					}
				}

				if(peticionesDelegate.pasaPSyOcXActividad(peticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
				{
					//se agrega familia naked para generar enviar TR010E
					Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue()){
//							|| fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
//							|| fPSK.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked){
						ps_id=productoServicoKey.ps_id;
						break;	
					}
						//Se agrega la nueva familia PC / PS NAKED para poder generar la TR510E
						if(fPSK.faps_id.intValue() == ComunInterfaces.familiaPcBANaked){
							tienePCNaked = true;
							//ps_Naked = productoServicoKey.ps_id;
						}
						if(fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VOIP_APSC");
							ps_id = new Long(propertiesDBLocal.getValor());
							tieneVOIPNaked = true;
							//peticionLocal.setNum_ide_nu_stb("0");
							break;	
						}
															
				}
			}
			
			if(ps_id==null && !tienePCNaked){
				log.debug("Ningun Ps tipo PC de la peticion: "+peticionLocal.getNum_ide_nu_stb()+"  invoca la actividad Asignar Recurso para Granite. No se envia mensaje a APSC.");
				return;
			}else{
				if(tienePCNaked && !tieneVOIPNaked){
					Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VIRTUAL_APSC");
					ps_id = new Long(propertiesDBLocal.getValor());
				}
			}

			
			Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
			
			// Se modifica el objeto TR010E, para ser enviado
			tr010e.setId(IdCorrelativo.toString());
			tr010e.setPathType(peticionLocal.getDir_tip_via_1());
			tr010e.setPathNumber(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_1())));
			tr010e.setFirstPathCharacters(peticionLocal.getDir_lt1_via_1());
			tr010e.setSecondPathCharacters(peticionLocal.getDir_lt2_via_1());
			tr010e.setPathZone(peticionLocal.getDir_zon_via_1());
			tr010e.setPathType2(peticionLocal.getDir_tip_via_2());
			tr010e.setPathNumber2(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_2())));
			tr010e.setFirstPathCharacters2(peticionLocal.getDir_lt1_via_2());
			tr010e.setSecondPathCharacters2(peticionLocal.getDir_lt2_via_2());
			String dir_zon_via_2 = peticionLocal.getDir_zon_via_2();
			try
			{
				if(dir_zon_via_2!=null && !dir_zon_via_2.trim().equals("")) {
					tr010e.setPathZone2(dir_zon_via_2.charAt(0));
				}else{
					tr010e.setPathZone2('-');
				}
				
			}
			catch(Exception e)
			{
				log.info("El valor de Dir Zon Via 2 no es valido:"+dir_zon_via_2);
			}

			Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
			
			//Cambio realizado el 180507 : : Se envia al APSC el Numero de Peticion en ves del Numero Atis
			tr010e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
			
			//Para la asignacion de recursos el numero debe ir blanco, pues todavia no se me asigna un numero.
			String phoneNumber = peticionLocal.getNum_ide_nu_stb();
			if (phoneNumber!=null && !phoneNumber.trim().equals("")){
				if (phoneNumber.length()>8){ 
					phoneNumber=phoneNumber.substring(0,8);
				}
			}			
			else{
				phoneNumber="0";
			}	
			tr010e.setPhoneNumber(Integer.parseInt(phoneNumber));
					
			String nombre = Utiles.sinNull(peticionLocal.getNom_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getPri_ape_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getSeg_ape_ds().trim(),"");
			
			if (nombre!=null && ! nombre.trim().equals (""))
			{
				if(nombre.length()>40)
					nombre=nombre.substring(0,39);
				tr010e.setClientName(nombre);
			}else{
				tr010e.setClientName("-");
			}
			
			
			String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
			
			if (clientDocument!=null && ! clientDocument.trim().equals ("")) 
                        {
				if (clientDocument.length()>20){
					clientDocument=clientDocument.substring(0,20);
				}
			}
			else{
				clientDocument="-";
			}
			
			tr010e.setClientDocument(clientDocument);
			
			tr010e.setProductServiceCode(ps_id.longValue());
			tr010e.setComercialOperation(operacion_comercialKey.opco_id.longValue());
			
			String installAddress = Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(peticionLocal.getNom_slo_no(),"");
			if(installAddress==null || installAddress.trim().equals("")) {
				installAddress="-";
			}
			tr010e.setInstallAddress(installAddress);
			
			String address = Utiles.sinNull(peticionLocal.getNum_cal_nu(),"");
			String addressChar = "";
			String addressNumber = "";
			
			try{

				if (address!=null && !address.trim().equals("")){
	
						addressNumber= (String) Utiles.getSacarLetraNumero(address).get("numero");
						addressChar = (String) Utiles.getSacarLetraNumero(address).get("letra");
				}
				else{
					addressNumber="-";
					addressChar = "-";
				}			
				
				tr010e.setAddressCharacters(addressChar.charAt(0));
				if(addressNumber!=null && addressNumber.length()>3)
					addressNumber=addressNumber.substring(0,3);
				
				try{
					int addressNumberInt=Integer.parseInt(addressNumber);
					addressNumber=addressNumberInt+"";
				}catch(NumberFormatException e){
					log.info("El address number no tiene un formato de número: "+e);
					addressNumber="0";
				}				
				
				tr010e.setAddressNumber(addressNumber);
				
			}catch(Exception e){
				
				log.info("El valor de Num cal nu no es valido:"+peticionLocal.getDir_zon_via_2());
			}

			DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
			tr010e.setDepartment(Integer.parseInt(departamentoKey.cod_dpt));
			
			LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
//			 Inicio CR 26747 - German P: Se obtienen la localidad y la sublocalidad de la tabla de Mapeo Localidades
			String subLocalidad = peticionLocal.getNom_slo_no();
            InfoComunColombiaBusinessInterface infoComunColombiaBI;
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			Long codLocApsc = infoComunColombiaBI.obtenerCodigoAPSC(new Long(localidadKey.cod_loc), subLocalidad);
//			tr010e.setCity(Integer.parseInt(localidadKey.cod_loc));
			tr010e.setCity(codLocApsc.intValue());
			// Fin CR 26747 - German P


			tr010e.setUseType(producto_servicio_peticionLocal.getCod_tip_uso().longValue());
//			tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
//			tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
		
//			Requerimiento Naked: Se calcula la caracteristica y subcaracteristica de la tabla Caracteristicas_linea: CR7
			try {
				Caracteristicas_lineaKey key = new Caracteristicas_lineaKey(ps_id);
				Caracteristicas_lineaLocal caracteristicasLocal = caracteristicasLocalHome.findByPrimaryKey(key);
				if(caracteristicasLocal.getCaracteristica() != null && caracteristicasLocal.getSub_caracteristica() != null){
					tr010e.setComercialProductType(caracteristicasLocal.getCaracteristica().longValue());
					tr010e.setComercialProductSubType(caracteristicasLocal.getSub_caracteristica().longValue());
				}else{
					tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
					tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				}
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
//				Requerimiento Naked: Si no encuentra registro en la tabla se deja el valor que se trae de ATIS: CR7
				log.debug("No se encuetra el PS registrado en la tabla Caracteristica_Linea se setea el valor que llega de ATIS");
				tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
				tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
			}
                        // fbd: 26-04-2007: 
                        // - como no se podia enviar en estos campos la "cedula catastral" se puserion de acuerdo
                        //   enviar informacion adicional de la direccion.
                        // - pero APSC no implemento este cambio y da problemas
                        //
                        // solucion: se manda temporalmente campos vacios hasta saber si mandamos la 
                        // famosa "cedula catastra" o el truco de la direccion
                                           
			tr010e.setPlaceNumber1(peticionLocal.getDir_nro_lg1());
			tr010e.setPlaceNumber2(peticionLocal.getDir_nro_lg2());
			tr010e.setPlaceNumber3(peticionLocal.getDir_nro_lg3());
			tr010e.setPlaceType1(peticionLocal.getDir_tip_lg1());
			tr010e.setPlaceType2(peticionLocal.getDir_tip_lg2());
			tr010e.setPlaceType3(peticionLocal.getDir_tip_lg3());
			
			/**
			 * Obtención del número Atis asociado a la petición (RQ 4659)
			 */
			Peticion_atisLocal peticionAtis = peticionLocal.getFk_01_pet_atis();			
			Peticion_atisKey key = (Peticion_atisKey)peticionAtis.getPrimaryKey();
			
			if(key.cod_pet_cd != null)
				tr010e.setAtisNumber(key.cod_pet_cd.longValue());
			else
				tr010e.setAtisNumber(0);
						
			//CR-7390 - Yumbleiner - Linea Precableada
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
			{
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				tmpCarac = obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT")));
				if (tmpCarac == null){
					tr010e.setProjectCode("");
				}else{
					tr010e.setProjectCode(tmpCarac);
					break;
				}
			}
			
			//.
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
			mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
			
			int areaPhone= 0;
			int numeroPhone= 0;
			if (phoneNumber.length()>1){
				areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				numeroPhone=Integer.parseInt(phoneNumber.substring(1));
			}
			mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
			mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
			
			AsignacionRecursosSTBMQ asignacionRecursosSTBMQ = new AsignacionRecursosSTBMQ();
			asignacionRecursosSTBMQ.enviarMensaje(tr010e);
			
				
			} catch (NumberFormatException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (FinderException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (NamingException e) {
				log.error("Creacion de Local Home Null",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
			}
			catch(Exception e)
			{
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			} 
		
	}

	public void consultaRecursoSTB_BA(Long peticion, String idActividad, String codActividad) throws ATiempoAppEx{
		
		try{
			log.debug("Se genera consulta de recursos para la actividad: "+codActividad);


			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			boolean esRefrescar=false;
			boolean tieneVOIPNaked = false;
			Caracteristicas_lineaLocalHome caracteristicasLocalHome = (Caracteristicas_lineaLocalHome)HomeFactory.getHome(Caracteristicas_lineaLocalHome.JNDI_NAME);
			String idActividadTem = "";
			if(idActividad != null &&(idActividad.equals("")||idActividad.equals("F")))
			{
				esRefrescar=true;
				idActividadTem = idActividad;
				
			}
			
			if(codActividad != null){
				idActividad = codActividad;
			}else{
				idActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA");
			}
			TR010E tr010e = new TR010E();
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			
			PeticionKey peticionKey = new PeticionKey(peticion);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
			
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			Operacion_comercialKey operacion_comercialKey=null;
			
			Long ps_id=null;

			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			
			//En esta actividad no puedo preguntar por el ps que llama a la actividad 
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
			{
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
				operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				
				//Se agrega la nueva familia PC / PS NAKED para poder generar la TR010E
				if(fPSK.faps_id.intValue() == ComunInterfaces.familiaPcBANaked){
					tieneVOIPNaked = true;
					//ps_Naked = productoServicoKey.ps_id;
				}
				
				//se agrega familia PC para que ejecute la activida con cualquier ps de naked
				if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaBandaAnchaNaked).longValue() ||fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() )
				{
					if
					(
						producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tras()!=null && producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tras().equals("T")
						&& producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo()!=null 
						&& ( producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals("B") || producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals("BCP") )
					)
						continue;
					if(fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VOIP_APSC");
						ps_id = new Long(propertiesDBLocal.getValor());
						tieneVOIPNaked = true;
						//peticionLocal.setNum_ide_nu_stb("0");
						break;	
					}
					ps_id=productoServicoKey.ps_id;
					break;
				}	
			}
						
			if(ps_id==null && !tieneVOIPNaked){
				log.debug("Ningun Ps tipo PC de la peticion: "+peticionLocal.getNum_ide_nu_stb()+"  invoca la actividad Asignar Recurso para Granite. No se envia mensaje a APSC.");
				return;
			}else{
				if(tieneVOIPNaked){
					Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
					Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VIRTUAL_APSC");
					ps_id = new Long(propertiesDBLocal.getValor());
				}
			}

			
			// Se modifica el objeto TR010E, para ser enviado
			tr010e.setId(IdCorrelativo.toString());
			tr010e.setPathType(peticionLocal.getDir_tip_via_1());
			tr010e.setPathNumber(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_1())));
			tr010e.setFirstPathCharacters(peticionLocal.getDir_lt1_via_1());
			tr010e.setSecondPathCharacters(peticionLocal.getDir_lt2_via_1());
			tr010e.setPathZone(peticionLocal.getDir_zon_via_1());
			tr010e.setPathType2(peticionLocal.getDir_tip_via_2());
			tr010e.setPathNumber2(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_2())));
			tr010e.setFirstPathCharacters2(peticionLocal.getDir_lt1_via_2());
			tr010e.setSecondPathCharacters2(peticionLocal.getDir_lt2_via_2());
			try
			{
				if(peticionLocal.getDir_zon_via_2()!=null && !peticionLocal.getDir_zon_via_2().trim().equals("")) {
					tr010e.setPathZone2(peticionLocal.getDir_zon_via_2().charAt(0));
				}else{
					tr010e.setPathZone2('-');
				}
				
			}
			catch(Exception e)
			{
				log.info("El valor de Dir Zon Via 2 no es valido:"+peticionLocal.getDir_zon_via_2());
			}

			Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
			
			//Cambio realizado el 180507 : : Se envia al APSC el Numero de Peticion en ves del Numero Atis
			tr010e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
			
			//Para la asignacion de recursos el numero debe ir blanco, pues todavia no se me asigna un numero.
			String phoneNumber = peticionLocal.getNum_ide_nu_stb();
			if (phoneNumber!=null && !phoneNumber.trim().equals("") && !esRefrescar)
			{
				if (phoneNumber.length()>8){ 
					phoneNumber=phoneNumber.substring(0,8);
				}
			}			
			else
			{
				phoneNumber="0";
			}	
			tr010e.setPhoneNumber(Integer.parseInt(phoneNumber));
					
			String nombre = Utiles.sinNull(peticionLocal.getNom_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getPri_ape_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getSeg_ape_ds().trim(),"");
			
			if (nombre!=null && ! nombre.trim().equals (""))
			{
				if(nombre.length()>40)
					nombre=nombre.substring(0,39);
				tr010e.setClientName(nombre);
			}else{
				tr010e.setClientName("-");
			}
			
			
			String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
			
			if (clientDocument!=null && ! clientDocument.trim().equals ("")) 
						{
				if (clientDocument.length()>20){
					clientDocument=clientDocument.substring(0,20);
				}
			}
			else{
				clientDocument="-";
			}
			
			tr010e.setClientDocument(clientDocument);
			
			tr010e.setProductServiceCode(ps_id.longValue());
			tr010e.setComercialOperation(operacion_comercialKey.opco_id.longValue());
			
			String installAddress = Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(peticionLocal.getNom_slo_no(),"");
			if(installAddress==null || installAddress.trim().equals("")) {
				installAddress="-";
			}
			tr010e.setInstallAddress(installAddress);
			
			String addressNumber = Utiles.sinNull(peticionLocal.getNum_cal_nu(),"");
			
			try{

				if (addressNumber!=null && !addressNumber.trim().equals("")){
					if (addressNumber.length()>3){
						
						addressNumber=addressNumber.substring(0,3);
					}
				}
				else
				{
					addressNumber="0";
				}			
				
				tr010e.setAddressCharacters(addressNumber.charAt(0));
				
				try{
					int addressNumberInt=Integer.parseInt(addressNumber);
					addressNumber=addressNumberInt+"";
				}catch(NumberFormatException e){
					log.info("El address number no tiene un formato de número consultaRecursoSTB_BA(): "+e);
					addressNumber="0";
				}
				
				tr010e.setAddressNumber(addressNumber);
				
			}catch(Exception e){
				
				log.info("El valor de Num cal nu no es valido:"+peticionLocal.getDir_zon_via_2());
			}

			DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
			tr010e.setDepartment(Integer.parseInt(departamentoKey.cod_dpt));
			
			LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			
//			 Inicio CR 26747 - German P: Se obtienen la localidad y la sublocalidad de la tabla de Mapeo Localidades
			String subLocalidad = peticionLocal.getNom_slo_no();
           InfoComunColombiaBusinessInterface infoComunColombiaBI;
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			Long codLocApsc = infoComunColombiaBI.obtenerCodigoAPSC(new Long(localidadKey.cod_loc), subLocalidad);
//			tr010e.setCity(Integer.parseInt(localidadKey.cod_loc));
			tr010e.setCity(codLocApsc.intValue());
			// Fin CR 26747 - German P

			//Estos valores estan a fierro, la idea es solo rellenar el mensaje pues ya se tienen los recursos
			tr010e.setUseType(3);
//			tr010e.setComercialProductType(1);
//			tr010e.setComercialProductSubType(1);
		
//			Requerimiento Naked: Se calcula la caracteristica y subcaracteristica de la tabla Caracteristicas_linea: CR7
			try {
				Caracteristicas_lineaKey key = new Caracteristicas_lineaKey(ps_id);
				Caracteristicas_lineaLocal caracteristicasLocal = caracteristicasLocalHome.findByPrimaryKey(key);
				if(caracteristicasLocal.getCaracteristica() != null && caracteristicasLocal.getSub_caracteristica() != null){
					tr010e.setComercialProductType(caracteristicasLocal.getCaracteristica().longValue());
					tr010e.setComercialProductSubType(caracteristicasLocal.getSub_caracteristica().longValue());
				}else{
					tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
					tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				}
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
//				Requerimiento Naked: Si no encuentra registro en la tabla se deja el valor que se trae de ATIS: CR7
				log.debug("No se encuetra el PS registrado en la tabla Caracteristica_Linea se setea el valor que llega de ATIS");
				tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
				tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
			}
						// fbd: 26-04-2007: 
						// - como no se podia enviar en estos campos la "cedula catastral" se puserion de acuerdo
						//   enviar informacion adicional de la direccion.
						// - pero APSC no implemento este cambio y da problemas
						//
						// solucion: se manda temporalmente campos vacios hasta saber si mandamos la 
						// famosa "cedula catastra" o el truco de la direccion
                                           
			tr010e.setPlaceNumber1(peticionLocal.getDir_nro_lg1());
			tr010e.setPlaceNumber2(peticionLocal.getDir_nro_lg2());
			tr010e.setPlaceNumber3(peticionLocal.getDir_nro_lg3());
			tr010e.setPlaceType1(peticionLocal.getDir_tip_lg1());
			tr010e.setPlaceType2(peticionLocal.getDir_tip_lg2());
			tr010e.setPlaceType3(peticionLocal.getDir_tip_lg3());
			

			
			//CR-7390 - Yumbleiner - Linea Precableada
			String tmpCarac;
			for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
			{
				producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
				tmpCarac = obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT")));
				if (tmpCarac == null){
					tr010e.setProjectCode("");
				}else{
					tr010e.setProjectCode(tmpCarac);
					break;
				}
			}
			
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);

			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
//			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
//			mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
			if(esRefrescar){
				if(idActividadTem.equals(""))
					mensajeEstadoLineaLocal.setAccion("R");
				else{
					if(idActividadTem.equals("F"))
						mensajeEstadoLineaLocal.setAccion(idActividadTem);
				}
			}
				
						
			
			int areaPhone= 0;
			int numeroPhone= 0;
			if (phoneNumber.length()>1){
				areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				numeroPhone=Integer.parseInt(phoneNumber.substring(1));
			}
			mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
			mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
			
			AsignacionRecursosSTBMQ asignacionRecursosSTBMQ = new AsignacionRecursosSTBMQ();
			asignacionRecursosSTBMQ.enviarMensaje(tr010e);
			
				
			} catch (NumberFormatException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
			} catch (CreateException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
			} catch (FinderException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (NamingException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
			}
			catch(Exception e)
			{
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			} 
		
	}
	
		/**
		 * @param string
		 * @return
		 */
		public boolean validadorInBoolean(String pss, String idFlujo) {
		
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					Connection con = null;
					int rowscount = 0;
					try{
						//Gustavo - CR 16440 - Modifico sentencia para ejecutar el PreparedStatement
						String sqlFlujoProdSerOpCo = "SELECT * FROM VPISTBBA.FLUJO_PROD_SERV_OPER_COM FP, VPISTBBA.FLUJO_DEFINICION FD"+
						"WHERE PRSE_ID IN ( ? )AND FP.FLUJ_ID=FD.FLUJ_ID AND ACTI_ID= ? ";

						con = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
						pstmt= con.prepareStatement(sqlFlujoProdSerOpCo);
						pstmt.setString(1,pss);
						pstmt.setString(2,idFlujo);
						rs=pstmt.executeQuery();
						while(rs.next()){
							rowscount++;
						}

					}catch (Exception exc) {
					
						log.warn("Problema ejecutando la Consulta " + "SELECT * FROM VPISTBBA.FLUJO_PROD_SERV_OPER_COM FP, VPISTBBA.FLUJO_DEFINICION FD"+
						"WHERE PRSE_ID IN ("+pss+")AND FP.FLUJ_ID=FD.FLUJ_ID AND ACTI_ID="+ idFlujo, exc);
						if (exc instanceof SQLException)
							throw new DBException((SQLException) exc);
						else
							throw new DBException(exc.getMessage());
					} finally {
					try { if (rs != null) rs.close(); } catch (Exception e) { }
					try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
					try { if (con != null) con.close(); } catch (Exception e) { }
					}
				
				if (rowscount > 1){
					return false;
				}
				
				return true;
		}
		
	public void asignarRecursoManualSTB(TR003S tr003s) throws ATiempoAppEx
	{

		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
	
		try
		{		
			bExecution = BackendTraceUtil.initExecution(tr003s, log);
			bExecution.setNumPetAtiempo(new Long(tr003s.getAtisRequestNumber()));
			bExecution.setIdMensajeOp(tr003s.getId());
			bExecution.startOperation();
			
			Fecha fecha=new Fecha();
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
		
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeError=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManual=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

		   Recursos_linea_basicaLocal recursos_linea_basicaLocal;
	   
		   try
			 {
		   		Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findOds(new Long(tr003s.getOdsNumber()));
			 }
			 catch (FinderException fex)
			 {
				recursos_linea_basicaLocal = null ;
			 }
	   
					/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 */
			if (recursos_linea_basicaLocal == null) {
				log.debug(
					" Este Mensaje es para Reinyeccion por Atiempo, por no tener ODS Registrada "+ XMLUtilities.marshall(tr003s));
				log.debug("Ods no existe o ODS duplicada en recursos_linea_basica");
				throw new ATiempoAppEx("Ods no existe o ODS duplicada en recursos_linea_basica:"+tr003s.getOdsNumber(),ATiempoAppEx.EXCEPTION);
			}
	
	
			Mensaje_estado_lineaLocal mensaje_estado_lineaLocal = recursos_linea_basicaLocal.getMensaje_estado_linea();
		
			if (mensaje_estado_lineaLocal == null) {
				log.debug("No Existe Mensaje en la base de enviados\n "	+ XMLUtilities.marshall(tr003s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr003s.getId(),ATiempoAppEx.EXCEPTION);
			}	
			
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensaje_estado_lineaLocal.getF_reference_14().getPrimaryKey() ;
		
			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() != estadoEsperaManual ) {
				log.debug(
					"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr003s));
				return;
			}
			
			
			
		//Si no hay errores de ningun tipo
		if (tr003s.isError() != true && tr003s.getResult() == 1) {
		
			//Recupero peticion
			PeticionLocal peticionLocal = mensaje_estado_lineaLocal.getPeticion();
			if(peticionLocal.getTica_id().equals("P") || (recursos_linea_basicaLocal.getCod_central() != null  && recursos_linea_basicaLocal.getCod_central().intValue() != -1)){
				log.debug("Se agregan los valores asignados en anteriores");
				asignarValoreRLB(recursos_linea_basicaLocal);
			}
			/*  
			 *  CR 00024071 - 2009/04/20 - 3
			 *		Se envia a Gestion de Recursos si el número de teléfono es no valido - German P.
			 */
			String dsTelefono = String.valueOf(tr003s.getPhoneNumber());
			if (dsTelefono.equals("-1")){
				// enviar a gestion de recurso
				// Se obtiene el codigo de actividad - German P.
				String cod_actividad = null;
				if (mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA"))){
					cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_MANUAL");
					log.debug("1 cod_actividad .."+cod_actividad);
				}else{
				    log.debug("3 cod_actividad .."+cod_actividad);
				    cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_GEST_ASIGNACION");
					log.debug("No existe ninguna peticion definida para la Asignacion");
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
					actDto.setObservacion("Error en la Asignacion Manual de Recursos.CNA=" + tr003s.getCna() + 	"." + " Error en el formato del Número de Teléfono del Cliente.");
					actividadEJB.grabarSinTerminar(actDto);

					return;	
				}
				mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
				actDto.setObservacion("Error en la Asignacion Manual de Recursos.CNA=" + tr003s.getCna() + 
						"." + " Error en el formato del Número de Teléfono del Cliente.");
				actividadEJB.terminar(actDto);
				return;
			}
			
			peticionLocal.setNum_ide_nu_stb(dsTelefono);

//			Req. 13420 - Jesus Carvajal - 26/03/2012
			if (tr003s.getStateLine() != null && tr003s.getStateLine().equalsIgnoreCase(ComunInterfaces.ESTADO_LINEA_DEDICADO)){
				recursos_linea_basicaLocal.setInd_dedicado(new Short("1"));
				recursos_linea_basicaLocal.setCaja_dedicado(tr003s.getBox());
				recursos_linea_basicaLocal.setArmario_dedicado(tr003s.getCloset());
				recursos_linea_basicaLocal.setPar_caja_dedicado(new Long (tr003s.getBoxPair()));
				recursos_linea_basicaLocal.setCable_dedicado(tr003s.getCable());
				recursos_linea_basicaLocal.setPar_cable_dedicado(new Long (tr003s.getPairCable()));
				recursos_linea_basicaLocal.setDir_armario_dedicado(tr003s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_caja_dedicado(tr003s.getBoxAddress());
                //DAVID: Se adicionan los siguientes campos, Septiembre 10 de 2010.
				recursos_linea_basicaLocal.setTipo_caja_dedicado(tr003s.getPreviousBoxType());
				recursos_linea_basicaLocal.setCod_central_dedicado(new Long (tr003s.getCentralCode()));
				log.debug("Colocados los datos en el front de Red Dedicada");

			}else{
				recursos_linea_basicaLocal.setInd_dedicado(new Short("0"));
				recursos_linea_basicaLocal.setCaja_asg(tr003s.getBox());
				recursos_linea_basicaLocal.setArmario_asg(tr003s.getCloset());
				recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr003s.getBoxPair()));
				recursos_linea_basicaLocal.setCable(tr003s.getCable());
				recursos_linea_basicaLocal.setPar_cable(new Long(tr003s.getPairCable()));
				recursos_linea_basicaLocal.setDir_armario(tr003s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_caja(tr003s.getBoxAddress());
				//DAVID: Se adicionan los siguientes campos, Septiembre 10 de 2010.
				recursos_linea_basicaLocal.setTipo_caja(tr003s.getBoxType());
				recursos_linea_basicaLocal.setCod_central(new Long(tr003s.getCentralCode()));
				log.debug("Colocados los datos en el front de Red Origen");

			}
			//Fin Req 13420
			
			recursos_linea_basicaLocal.setOds_apsc(new Long(tr003s.getOdsNumber()));
			recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr003s.getSecondaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr003s.getSecondaryDistributorDescription());
			recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr003s.getPrimaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_prim_asg(tr003s.getPrimaryDistributorDescription());
			recursos_linea_basicaLocal.setListon_asg(String.valueOf(tr003s.getStrip()));
			recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr003s.getStripPair()));
			
			//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
			
			recursos_linea_basicaLocal.setRack(tr003s.getRack().toString());
			recursos_linea_basicaLocal.setSubRack(tr003s.getSubRack().toString());
			
			CentralLocalHome centralLocalHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
			CentralLocal centralLocal=centralLocalHome.findByPrimaryKey(new CentralKey(new Long(tr003s.getCentralCode())));
			recursos_linea_basicaLocal.getPeticion().setFk_03_central(centralLocal);
			recursos_linea_basicaLocal.setDesc_central(tr003s.getCentralDescription());
			
			//recursos_linea_basicaLocal.setTelefono_asg(new Long(tr003s.getPhoneNumber()));
			recursos_linea_basicaLocal.setTelefono_asg(new Long(dsTelefono));
			recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr003s.getHorizontalPosition());
			recursos_linea_basicaLocal.setLen(tr003s.getLen());
			recursos_linea_basicaLocal.setPeti_numero(new Long(tr003s.getAtisRequestNumber()));
			recursos_linea_basicaLocal.setDir_distribuidor(tr003s.getDistributorAddress());
			
			//Recursos Linea Antigua
			recursos_linea_basicaLocal.setDist_sec_ant(new Long(tr003s.getPreviousSecondaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_sec_ant(tr003s.getPreviousSecondaryDistributorDescription());
			recursos_linea_basicaLocal.setArmario_ant(tr003s.getPreviousCloset());
			recursos_linea_basicaLocal.setCaja_asg_ant(tr003s.getPreviousBox());
			recursos_linea_basicaLocal.setPar_caja_ant(new Long(tr003s.getPreviousBoxPair()));
			recursos_linea_basicaLocal.setDist_prim_ant(new Long(tr003s.getPreviousPrimaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_prim_ant(tr003s.getPrimaryDistributorDescription());
			recursos_linea_basicaLocal.setListon_asg_ant(tr003s.getPreviousStrip());
			recursos_linea_basicaLocal.setPar_liston_ant(new Long(tr003s.getPreviousStripPair()));
			recursos_linea_basicaLocal.setCable_ant(tr003s.getPreviousCable());
			recursos_linea_basicaLocal.setPar_cable_ant(new Long(tr003s.getPreviousCablePair())); 
			if(new Long(tr003s.getPreviousCentralCode()).intValue() != 0)
				recursos_linea_basicaLocal.setCentral_ant(new Long(tr003s.getPreviousCentralCode()));
			recursos_linea_basicaLocal.setDesc_central_ant(tr003s.getPreviousCentralDescription());
			if(tr003s.getPreviousPhoneNumber() != 0)
				recursos_linea_basicaLocal.setTelefono_ant(new Long(tr003s.getPreviousPhoneNumber()));
			recursos_linea_basicaLocal.setLen_anterior(tr003s.getPreviousLen());
			recursos_linea_basicaLocal.setPosicion_horizontal_ant(tr003s.getPreviousHorizontalPosition());
			
			//Req 14209 - Jesus Carvajal - 19042012
			recursos_linea_basicaLocal.setFecha_asig_recurso(fecha.getTimestamp());
			//Fin Req 14209
			//@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
			//se realiza validacion para saber si algun campo FTTC contiene datos
			//se agrega nueva validacion 31/03/2014 cardena
			//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
			String accion = validaFTTC(tr003s.getFttcfab(), tr003s.getFttcmode() , tr003s.getFttcslot() ,
					tr003s.getFttcpwd() , tr003s.getFttcusu() , tr003s.getFttciplb(),mensaje_estado_lineaLocal.getCod_actividad_generadora(),mensaje_estado_lineaLocal);
			
			if(accion.equals("LB"))
			{	
				// este else espara saber si no trae equipos fttc
				log.debug("No Trae Equipos FTTC");
				if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
				{
					
				}else{
				recursos_linea_basicaLocal.setRec_fttc_asg("N");	
				}
			}else if(accion.equals("PGI")){	
				return;							
			}else if(accion.equals("FTTC")){
			log.debug("Trae Equipos FTTC-");
			//se instancia la clase del password FTTC
			int longitud=0;
								
			Password generarPass = new  Password();
		
		//se valida que tipo de operacion comercial llega 1 es alta 
	if (peticionLocal.getTica_id().equals("A")){
		//se trae de la tabla propertiesBD la longitud del password
		Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
		// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
		recursos_linea_basicaLocal.setFttciplb_asg(tr003s.getFttciplb());
		recursos_linea_basicaLocal.setFttcpwd_asg(tr003s.getFttcpwd());
		recursos_linea_basicaLocal.setFttcusu_asg(tr003s.getFttcusu());
		recursos_linea_basicaLocal.setFttcslot_asg(tr003s.getFttcslot());
		recursos_linea_basicaLocal.setFttcfab_asg(tr003s.getFttcfab());
		recursos_linea_basicaLocal.setFttcmode_asg(tr003s.getFttcmode());
		//el campo que nos dice que hay equipos De tipo FTTC
		recursos_linea_basicaLocal.setRec_fttc_asg("S");
		recursos_linea_basicaLocal.setRec_fttc_ant("N");
		//se genera el password y se le envia la longitud
		longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
		recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
		log.debug(tr003s.getFttciplb());
		log.debug(tr003s.getFttcpwd());
		log.debug(tr003s.getFttcusu());
		log.debug(tr003s.getFttcslot());
		log.debug(tr003s.getFttcfab());
		log.debug(tr003s.getFttcmode());
		
	//si la operacion comercial es diferente de 1 puede ser un traslado o una baja
	}else if(  peticionLocal.getTica_id().equals("B") ||peticionLocal.getTica_id().equals("P") ||peticionLocal.getTica_id().equals("T"))
	{
		
//		este if es para validar si ya tiene fttc asignados si no solo se insertan los equipos en asignados
		if(recursos_linea_basicaLocal.getFttciplb_asg()==null||recursos_linea_basicaLocal.getFttcpwd_asg()==null
				||recursos_linea_basicaLocal.getFttcusu_asg()==null||recursos_linea_basicaLocal.getFttcslot_asg()==null
				||recursos_linea_basicaLocal.getFttcfab_asg()==null||recursos_linea_basicaLocal.getFttcmode_asg()==null)
        {
//			se trae de la tabla propertiesBD la longitud del password
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
			log.debug("Trae nuevos Equipos FTTC-");
			// se guardan los nuevos equipos en los campos asignados			
			recursos_linea_basicaLocal.setFttciplb_asg(tr003s.getFttciplb());
			recursos_linea_basicaLocal.setFttcpwd_asg(tr003s.getFttcpwd());
			recursos_linea_basicaLocal.setFttcusu_asg(tr003s.getFttcusu());
			recursos_linea_basicaLocal.setFttcslot_asg(tr003s.getFttcslot());
			recursos_linea_basicaLocal.setFttcfab_asg(tr003s.getFttcfab());
			recursos_linea_basicaLocal.setFttcmode_asg(tr003s.getFttcmode());
			recursos_linea_basicaLocal.setRec_fttc_asg("S");
			recursos_linea_basicaLocal.setRec_fttc_ant("N");
//			se genera el password y se le envia la longitud
			longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
			recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
			log.debug(tr003s.getFttciplb());
			log.debug(tr003s.getFttcpwd());
			log.debug(tr003s.getFttcusu());
			log.debug(tr003s.getFttcslot());
			log.debug(tr003s.getFttcfab());
			log.debug(tr003s.getFttcmode());
        }else{
		//se valida si los equipos que llegan son diferentes a los asignados
		if(!recursos_linea_basicaLocal.getFttciplb_asg().equals(tr003s.getFttciplb())||!recursos_linea_basicaLocal.getFttcpwd_asg().equals(tr003s.getFttcpwd())
			||!recursos_linea_basicaLocal.getFttcusu_asg().equals(tr003s.getFttcusu())||!recursos_linea_basicaLocal.getFttcslot_asg().equals(tr003s.getFttcslot())
				||!recursos_linea_basicaLocal.getFttcfab_asg().equals(tr003s.getFttcfab())||!recursos_linea_basicaLocal.getFttcmode_asg().equals(tr003s.getFttcmode()))
		{
			
		
			log.debug("Trae nuevos Equipos FTTC");
			//se guardan los equipos asignados en el los campos de antiguos y los equipos nuevos en los campos de asignados
			recursos_linea_basicaLocal.setFttciplb_ant(recursos_linea_basicaLocal.getFttciplb_asg());
			recursos_linea_basicaLocal.setFttcpwd_ant(recursos_linea_basicaLocal.getFttcpwd_asg());
			recursos_linea_basicaLocal.setFttcusu_ant(recursos_linea_basicaLocal.getFttcusu_asg());
			recursos_linea_basicaLocal.setFttcslot_ant(recursos_linea_basicaLocal.getFttcslot_asg());
			recursos_linea_basicaLocal.setFttcfab_ant(recursos_linea_basicaLocal.getFttcfab_asg());
			recursos_linea_basicaLocal.setFttcmode_ant(recursos_linea_basicaLocal.getFttcmode_asg());	
			recursos_linea_basicaLocal.setRec_fttc_ant(recursos_linea_basicaLocal.getRec_fttc_asg());
			
			// se guardan los nuevos equipos en los campos asignados					
			recursos_linea_basicaLocal.setFttciplb_asg(tr003s.getFttciplb());
			recursos_linea_basicaLocal.setFttcpwd_asg(tr003s.getFttcpwd());
			recursos_linea_basicaLocal.setFttcusu_asg(tr003s.getFttcusu());
			recursos_linea_basicaLocal.setFttcslot_asg(tr003s.getFttcslot());
			recursos_linea_basicaLocal.setFttcfab_asg(tr003s.getFttcfab());
			recursos_linea_basicaLocal.setFttcmode_asg(tr003s.getFttcmode());
			recursos_linea_basicaLocal.setRec_fttc_asg("S");
						
			log.debug(tr003s.getFttcpwd());
			log.debug(tr003s.getFttcusu());
			log.debug(tr003s.getFttcslot());
			log.debug(tr003s.getFttcfab());
			log.debug(tr003s.getFttcmode());
			}else{
				
				log.debug("-No cambio de equipo");
			}
		} 
		}
		}
		
			// fin - requerimiento - requerimiento fttc 	
			
				
			
			
				
			if(tr003s.getDslams() != null){
				log.debug("Cantidad de Dslam " + tr003s.getDslams().size());
// 				inicio- gonzalez - 12/06/2008 -zonas de atendimiento				
				Zonas_atendimientoLocal zona_atendimientoLocal=null;
				borrarZonas(recursos_linea_basicaLocal);
				for (Iterator iter = tr003s.getDslams().iterator(); iter.hasNext();){
					Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Dslam dslam1 = (Dslam) iter.next();
					if (dslam1 == null ){
						log.debug("El Dslam viene NULL");
					}else{
						log.debug("El Dslam viene OK");
					}
					Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					if(zona_atendimientoLocal==null){
						//Se valida que es la primera zona 
						Dslam1 d=new Dslam1();
						d.setIp(dslam1.getIp());
						d.setDslamType(dslam1.getDslamType());
						zona_atendimientoLocal=validarPSPromocion(peticionLocal,d,recursos_linea_basicaLocal);	
					}else{
						zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
						zona_atendimientoLocal.setIp(dslam1.getIp());
						zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
				}


			
			}
		}else{
			if (tr003s.getOdsNumber() != 0)	
				recursos_linea_basicaLocal.setOds_apsc(new Long(tr003s.getOdsNumber()));
		}
	
			String cod_actividad = null;
		
			log.debug("Inicio de Condiciones de Actividad");
		
			if (mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA"))){
			
				cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_MANUAL");
				//Chequea que valor este campo cuando la reserva manual de recurso no fue exitosa
	//						  if (tr003s.isError()== true) { El campo error no importa en el cierre, solo importa
	
				if (tr003s.getResult() == 2)
				{
					log.debug("Entramos a la Generacion de Actividades de Gestion Asignacion Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeManual);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
	
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
					actDto.setObservacion("Error en la Asignacion Manual de Recursos.CNA=" + tr003s.getCna());
					recursos_linea_basicaLocal.setCna(tr003s.getCna());
				
					//No va al cierre directo por lo tanto no le asigno causas
	//							insertarCausalesCnaPeticion(mensaje_estado_lineaLocal,cod_actividad,tr003s,actividadEJB.getIdActividadFlujo());
					actividadEJB.terminar(actDto);
					return;
				}
				else if (tr003s.getResult() == 3)
				{
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
						
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);

					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
	//							actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("El APSC no puede asignar recursos de linea.Se deriva a PGI.CNA=" + tr003s.getCna());
					insertarCausalesCnaPeticion(mensaje_estado_lineaLocal,cod_actividad,new Long(tr003s.getCna()),actDto.getIdActividadFlujo());
					recursos_linea_basicaLocal.setCna(tr003s.getCna());
					recursos_linea_basicaLocal.setTiene_cna("S");
					actividadEJB.terminar(actDto);
					return;
				}
				else if (tr003s.isError()){
					//Se va a Gestion Manual
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);

					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
					actDto.setObservacion("Error en la asignacion manual de recursos.");
					actividadEJB.terminar(actDto);
					return;					
				}
			}
			else if (mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_MANUAL"))){
			
				cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_GEST_ASIGNACION");
				//Chequea que valor este campo cuando la reserva manual de recurso no fue exitosa
				if (tr003s.getResult() == 2) {//Mientras sea error distinto de 3, se queda pegada en esta activida
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeManual);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
//					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
	
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);

	//				actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
	//				actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
	//				actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "19");
					actDto.setObservacion("Error en la Asignacion Manual de Recursos.CNA=" + tr003s.getCna());
					recursos_linea_basicaLocal.setCna(tr003s.getCna());
					actividadEJB.grabarSinTerminar(actDto);
					return;
				}else if (tr003s.getResult() == 3 ) {
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
	//							actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
	
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("El APSC no puede asignar recursos de linea.Se deriva a PGI.CNA=" + tr003s.getCna());
					
					// TODO: agregado adecarlini
					PeticionLocal peticionLocal = mensaje_estado_lineaLocal.getPeticion(); //aa				
					//Long codError = new Long(tr003s.getTypeError());
					String codError = "1";
					String nombreClase=TR003S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					if (errorLegado != null){ 
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal, mensaje_estado_lineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal, mensaje_estado_lineaLocal.getCod_actividad_generadora(), new Long(tr003s.getCna()), actDto.getIdActividadFlujo());
					}
					
					// fin agregado
					
					//TODO modifica adecarlini
					//insertarCausalesCnaPeticion(mensaje_estado_lineaLocal,cod_actividad,new Long(tr003s.getCna()),actDto.getIdActividadFlujo());
					//fin modificado
					recursos_linea_basicaLocal.setCna(tr003s.getCna());
					recursos_linea_basicaLocal.setTiene_cna("S");
					actividadEJB.terminar(actDto);
					return;
					
					
				}else if (tr003s.isError()){
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					//mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
				
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					//solo grabo la bitacora, no cierro la actividad
	//							actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
	//							actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
	//							actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "19");
					actDto.setObservacion("Error en la Asignacion Manual de Recursos.CNA=" + tr003s.getCna());
					recursos_linea_basicaLocal.setCna(tr003s.getCna());
					actividadEJB.grabarSinTerminar(actDto);						
					return;					
				}
			}else{
				log.debug("No existe ninguna peticion definida para la Asignacion");
				return;	
			}
	
			log.debug("Actividad Generadora : " + cod_actividad);
			mensaje_estado_lineaLocal.setF_reference_14(mesajeOkLocal);
			mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
	 
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
			actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
			actividadEJB.terminar(actDto);
		
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);		
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
	}
	
	}


	/* CR-14657 Granite - Pablo L - 16/10/2008 */
	/*___Gestion de Recursos__*/
	public void asignarRecursoManualSTBGR(TR510S tr510s) throws ATiempoAppEx
	{		 
		BackendExecution bExecution = null;	
		try
		{		
			log.debug("ENTRO A ASIGNACION MANUAL RECURSOS GRANITE STB");
			bExecution = BackendTraceUtil.initExecution(tr510s, log);			
			bExecution.setIdMensajeOp(tr510s.getId());
			bExecution.setNumPetAtiempo(new Long(tr510s.getAtiempoRequestNumber()));
			bExecution.startOperation();
			
			Fecha fecha=new Fecha();
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
		
			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeError=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estadoLocal mensajeManual=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

		    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
		   
		    //CR-25069
			String codError = "1";
			String nombreClase=TR510S.class.getName();
			nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
			if(tr510s.getErrorCode()>0){
				codError= String.valueOf( tr510s.getErrorCode());
			}
			ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
			
			 
		   try
			 {
		   		Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findOds(new Long(tr510s.getOdsNumber()));
			 }
			 catch (FinderException fex)
			 {
				recursos_linea_basicaLocal = null ;
			 }
			/*
			 * Validacion de existencia de la respuesta en la Base de Datos 
			 * en la tabla Mensaje_Estado_Linea
			 **/
			if (recursos_linea_basicaLocal == null) {
				log.debug(
					" Este Mensaje es para Reinyeccion por Atiempo, por no tener ODS Registrada "
						+ XMLUtilities.marshall(tr510s));
				log.debug("Ods no existe o ODS duplicada en recursos_linea_basica");
				throw new ATiempoAppEx("Ods no existe o ODS duplicada en recursos_linea_basica:"+tr510s.getOdsNumber(),ATiempoAppEx.EXCEPTION);
			}
			Mensaje_estado_lineaLocal mensaje_estado_lineaLocal = recursos_linea_basicaLocal.getMensaje_estado_linea();
		
			if (mensaje_estado_lineaLocal == null) {
				log.debug(
					"No Existe Mensaje en la base de enviados\n "
						+ XMLUtilities.marshall(tr510s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr510s.getId(),ATiempoAppEx.EXCEPTION);
			}	
			
			//Req. 13417 - Jesus Carvajal - 29/05/2012
			//Cambio de len desde la PDA
			if (tr510s.getResult() == 4){
				recursos_linea_basicaLocal.setLen(tr510s.getLen());
				log.debug("Se actualizo el LEN por cambio desde la PDA");
				return;
			}
			//Fin Req. 13417

			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensaje_estado_lineaLocal.getF_reference_14().getPrimaryKey() ;		
			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() != estadoEsperaManual ) {
				log.debug(
					"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr510s));
				return;
			}
			
			//Recupero peticion
		PeticionLocal peticionLocal = mensaje_estado_lineaLocal.getPeticion();
		
		//Req. 13146 - Jesus Carvajal - 28/05/2012
		//Actualizar coordenadas en la tabla peticion dadas por SIRS 
		if (peticionLocal.getCoord_x_agnd_sc() == null || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0.0") 
				|| peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("") || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0")){
			peticionLocal.setCoord_x_agnd_sc(tr510s.getLongitude().toString());
			peticionLocal.setCoord_y_agnd_sc(tr510s.getLatitude().toString());
			recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr510s.getLongitude().toString()));
			recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr510s.getLatitude().toString()));
			log.debug("Se actualizo longitud y latitud en tabla peticion y tabla rlb");
		}else{
			recursos_linea_basicaLocal.setLongitud(new BigDecimal(peticionLocal.getCoord_x_agnd_sc()));
			recursos_linea_basicaLocal.setLatitud(new BigDecimal(peticionLocal.getCoord_y_agnd_sc()));
			log.debug("Ya existe longitud y latitud en tabla peticion y se actualiza tabla rlb");
		}
		//Fin Req. 13146
			
		//Si no hay errores de ningun tipo
		if (tr510s.getErrorCode() == 0 && tr510s.getResult() == 1) {		
			peticionLocal.setNum_ide_nu_stb(String.valueOf(tr510s.getPhoneNumber()));		
			recursos_linea_basicaLocal.setOds_apsc(new Long(tr510s.getOdsNumber()));
			recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr510s.getSecundaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr510s.getSecundaryDistributorDescription());
			recursos_linea_basicaLocal.setArmario_asg(tr510s.getCloset());
			recursos_linea_basicaLocal.setCaja_asg(tr510s.getBox());
			recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr510s.getBoxPair()));
			recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr510s.getPrimaryDistributor()));
			log.debug("se esta seteando en Primary Distributor Asg() "+tr510s.getPrimaryDistributor());
			//recursos_linea_basicaLocal.setDesc_dist_prim_asg(tr510s.getPrimaryDistributorDescription()); Aclaraciones
			recursos_linea_basicaLocal.setListon_asg(String.valueOf(tr510s.getStrip()));
			recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr510s.getStripPair()));
			recursos_linea_basicaLocal.setCable(tr510s.getCable());
			recursos_linea_basicaLocal.setPar_cable(new Long(tr510s.getCablePair()));
			recursos_linea_basicaLocal.setCod_central(new Long(tr510s.getCentralCode()));
			CentralLocalHome centralLocalHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
			CentralLocal centralLocal=centralLocalHome.findByPrimaryKey(new CentralKey(new Long(tr510s.getCentralCode())));
			recursos_linea_basicaLocal.getPeticion().setFk_03_central(centralLocal);
			recursos_linea_basicaLocal.setDesc_central(tr510s.getCentralDescription());
			recursos_linea_basicaLocal.setTelefono_asg(new Long(tr510s.getPhoneNumber()));
			
			
			// Visualizacion campo posicion horizontal - adocarmo - inicio
			recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr510s.getHorizontalPosition());
			// Visualizacion campo posicion horizontal - adocarmo - fin
			
			recursos_linea_basicaLocal.setLen(tr510s.getLen());
			//recursos_linea_basicaLocal.setPeti_numero(new Long(tr510s.getAtisRequestNumber())); Ya ingresado
			recursos_linea_basicaLocal.setDir_distribuidor(tr510s.getDistributorAddress());
			recursos_linea_basicaLocal.setDir_armario(tr510s.getClosetAddress());
			recursos_linea_basicaLocal.setDir_caja(tr510s.getBoxAddress());		
			//Recursos Linea Antigua
			recursos_linea_basicaLocal.setDist_sec_ant(new Long(tr510s.getPreviousSecondaryDistributor()));
			recursos_linea_basicaLocal.setDesc_dist_sec_ant(tr510s.getPreviousSecondaryDistributorDescription());
			recursos_linea_basicaLocal.setArmario_ant(tr510s.getPreviousCloset());
			recursos_linea_basicaLocal.setCaja_asg_ant(tr510s.getPreviousBox());
			recursos_linea_basicaLocal.setPar_caja_ant(new Long(tr510s.getPreviousBoxPair()));
			recursos_linea_basicaLocal.setDist_prim_ant(new Long(tr510s.getPreviousPrimaryDistributor()));
			recursos_linea_basicaLocal.setListon_asg_ant(tr510s.getPreviousStrip());
			recursos_linea_basicaLocal.setPar_liston_ant(new Long(tr510s.getPreviousStripPair()));
			recursos_linea_basicaLocal.setCable_ant(tr510s.getPreviousCable());
			recursos_linea_basicaLocal.setPar_cable_ant(new Long(tr510s.getPreviousCablePair()));      
			recursos_linea_basicaLocal.setCentral_ant(new Long(tr510s.getPreviousCentralCode()));
			recursos_linea_basicaLocal.setDesc_central_ant(tr510s.getPreviousCentralDescription());
			if(recursos_linea_basicaLocal.getTelefono_ant()==null || recursos_linea_basicaLocal.getTelefono_ant().longValue()==0
					||tr510s.getPreviousPhoneNumber()!=0){
			//Para no borrar el valor cuando se refresca desde la pagina 
				recursos_linea_basicaLocal.setTelefono_ant(new Long(tr510s.getPreviousPhoneNumber()));
			}
			recursos_linea_basicaLocal.setLen_anterior(tr510s.getPreviousLen());
			// datos red dedicada

			recursos_linea_basicaLocal.setTipo_caja(tr510s.getBoxType());
			recursos_linea_basicaLocal.setArmario_dedicado(tr510s.getClosetDedicated());
			recursos_linea_basicaLocal.setCable_dedicado(tr510s.getCableDedicated());
			recursos_linea_basicaLocal.setDir_armario_dedicado(tr510s.getClosetAddressDedicated());
			recursos_linea_basicaLocal.setDir_caja_dedicado(tr510s.getBoxAddressDedicated());
			recursos_linea_basicaLocal.setTipo_caja_dedicado(tr510s.getBoxTypeDedicated());
			recursos_linea_basicaLocal.setCaja_dedicado(tr510s.getBoxDedicated());
			
			//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
			
			recursos_linea_basicaLocal.setRack(tr510s.getRack().toString());
			recursos_linea_basicaLocal.setSubRack(tr510s.getSubRack().toString());	

			// seteo si la central soporta configuración autmática
			if (tr510s.isEocEnable()) {
				recursos_linea_basicaLocal.setConfig_automatica(new Short((short)1));
			}else {
				recursos_linea_basicaLocal.setConfig_automatica(new Short((short)0));
			}
			recursos_linea_basicaLocal.setPar_cable_dedicado(new Long(tr510s.getCablePairDedicated()));
			recursos_linea_basicaLocal.setPar_caja_dedicado(new Long(tr510s.getBoxPairDedicated()));

			recursos_linea_basicaLocal.setDistancia_caja(new Integer(tr510s.getBoxDistance()));

			if(tr510s.isDedicationPresence())
				recursos_linea_basicaLocal.setInd_dedicado(new Short("1"));
			else
				recursos_linea_basicaLocal.setInd_dedicado(new Short("0"));
			
			//Req 14209 - Jesus Carvajal - 19042012
			recursos_linea_basicaLocal.setFecha_asig_recurso(fecha.getTimestamp());
			//Fin Req 14209
			
			/* YUMBLEINER CALDERON
			recursos_linea_basicaLocal.setCod_central_dedicado(new Long(String.valueOf(tr510s.getCentralCodeDedicated())));
						
			//agonz AT-2171 - latitud_longitud no se ven en bandeja - 07/05/2009
			recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr510s.getLongitude().toString()));
			recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr510s.getLatitude().toString()));


			// Falta agregar este campo a la tabla recursos_linea_basica y al entity bean 
			//recursos_linea_basicaLocal.setCentralSoportaConfAutomatica(tr510s.isEocEnable());
			*/
			
			if(tr510s.getDslams() != null){
				log.debug("Cantidad de Dslam " + tr510s.getDslams().size());
// 				inicio- gonzalez - 12/06/2008 -zonas de atendimiento
				Zonas_atendimientoLocal zona_atendimientoLocal=null;
				borrarZonas(recursos_linea_basicaLocal);
				for (Iterator iter = tr510s.getDslams().iterator(); iter.hasNext();){
					Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Dslam1 dslam1 = (Dslam1) iter.next();
					if (dslam1 == null ){
						log.debug("El Dslam viene NULL");
					}else{
						log.debug("El Dslam viene OK");
					}
					Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					if(zona_atendimientoLocal==null){
						//Se valida que es la primera zona 
						zona_atendimientoLocal=validarPSPromocion(peticionLocal,dslam1,recursos_linea_basicaLocal);	
					}else{
						zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
						zona_atendimientoLocal.setIp(dslam1.getIp());
						zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
				}
			}
		}else{
			
			if (tr510s.getOdsNumber() != 0)
				recursos_linea_basicaLocal.setOds_apsc(new Long(tr510s.getOdsNumber()));
		}	
			String cod_actividad = null;		
			log.debug("Inicio de Condiciones de Actividad");		
			if (mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA"))){			
				cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_MANUAL");
				//Chequea que valor este campo cuando la reserva manual de recurso no fue exitosa
	//			El campo error no importa en el cierre, solo importa	
				if (tr510s.getResult() == 2)
				{
					log.debug("Entramos a la Generacion de Actividades de Gestion Asignacion Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeManual);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);	
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
					if(errorLegado!=null)
						actDto.setObservacion("Error en la Asignacion Manual de Recursos. Codigo Error = " + errorLegado.getCodigoError());
					else
						actDto.setObservacion("Error en la Asignacion Manual de Recursos.");
					//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));				
					//No va al cierre directo por lo tanto no le asigno causas
					actividadEJB.terminar(actDto);
					return;
				}
				else if (tr510s.getResult() == 3)
				{
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);						
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					if(errorLegado!=null){
						actDto.setObservacion("SIRS no puede asignar recursos de linea.Se deriva a PGI. Codigo Error = " + errorLegado.getCodigoError());
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal,cod_actividad,errorLegado.getIdCausa(),actDto.getIdActividadFlujo());
					}else{
						actDto.setObservacion("SIRS no puede asignar recursos de linea.Se deriva a PGI.");
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal,cod_actividad,new Long (codError),actDto.getIdActividadFlujo());
					}
					
					//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));
					//recursos_linea_basicaLocal.setTiene_cna("S");
					actividadEJB.terminar(actDto);
					return;
				}
				// Error = true
				else if (tr510s.getErrorCode() == 1){
					//Se va a Gestion Manual
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
					actDto.setObservacion("Error en la asignacion manual de recursos.");
					actividadEJB.terminar(actDto);
					return;					
				}
			}
			else if (mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_MANUAL"))){			
				cod_actividad = VpistbbaConfig.getVariable("COD_ACTIVIDAD_GEST_ASIGNACION");
				if (tr510s.getResult() == 2) {
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeManual);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));	
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					if(errorLegado!=null)
						actDto.setObservacion("Error en la Asignacion Manual de Recursos. Codigo Error = " + errorLegado.getCodigoError());
					else
						actDto.setObservacion("Error en la Asignacion Manual de Recursos.");
					//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));
					actividadEJB.grabarSinTerminar(actDto);
					return;
				}else if (tr510s.getResult() == 3 ) {
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actDto.setObservacion("SIRS no puede asignar recursos de linea.Se deriva a PGI. Codigo Error = " + errorLegado.getCodigoError());
//					PeticionLocal peticionLocal = mensaje_estado_lineaLocal.getPeticion(); 
//					String codError = "1";
//					String nombreClase=TR510S.class.getName();
//					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
//					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					if (errorLegado != null){ 
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal, mensaje_estado_lineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensaje_estado_lineaLocal, mensaje_estado_lineaLocal.getCod_actividad_generadora(), new Long(codError), actDto.getIdActividadFlujo());
					}
//					recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));
//					recursos_linea_basicaLocal.setTiene_cna("S");
					actividadEJB.terminar(actDto);
					return;
				}
				//Error = true
				else if (tr510s.getErrorCode() != 0){
					log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + cod_actividad);
					mensaje_estado_lineaLocal.setF_reference_14(mensajeError);
					mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
					if(errorLegado!=null)
						actDto.setObservacion("Error en la Asignacion Manual de Recursos. Codigo Error = " + errorLegado.getCodigoError());
					else
						actDto.setObservacion("Error en la Asignacion Manual de Recursos.");
					//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));
					actividadEJB.grabarSinTerminar(actDto);						
					return;					
				}
			}else{
				log.debug("No existe ninguna peticion definida para la Asignacion");
				return;	
			}			
			

			//@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
			//se realiza validacion para saber si algun campo FTTC contiene datos
			//se agrega nueva validacion 31/03/2014 cardena
			//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
			String accion = validaFTTC(tr510s.getFttcfab(), tr510s.getFttcmode() , tr510s.getFttcslot() ,
					tr510s.getFttcpwd() , tr510s.getFttcusu() , tr510s.getFttciplb(),cod_actividad,mensaje_estado_lineaLocal);
			
			if(accion.equals("LB"))
			{	
				// este else espara saber si no trae equipos fttc
				log.debug("No Trae Equipos FTTC");
				if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
				{
					
				}else{
				recursos_linea_basicaLocal.setRec_fttc_asg("N");	
				}					
			}else if(accion.equals("PGI")){	
				return;				
			}else if(accion.equals("FTTC")){
			log.debug("Trae Equipos FTTC-");
			//se instancia la clase del password FTTC
			int longitud=0;
								
			Password generarPass = new  Password();
		
		//se trae de la tabla propertiesBD la longitud del password
		Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
		// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
		recursos_linea_basicaLocal.setFttciplb_asg(tr510s.getFttciplb());
		recursos_linea_basicaLocal.setFttcpwd_asg(tr510s.getFttcpwd());
		recursos_linea_basicaLocal.setFttcusu_asg(tr510s.getFttcusu());
		recursos_linea_basicaLocal.setFttcslot_asg(tr510s.getFttcslot());
		recursos_linea_basicaLocal.setFttcfab_asg(tr510s.getFttcfab());
		recursos_linea_basicaLocal.setFttcmode_asg(tr510s.getFttcmode());
		//el campo que nos dice que hay equipos De tipo FTTC
		recursos_linea_basicaLocal.setRec_fttc_asg("S");
		recursos_linea_basicaLocal.setRec_fttc_ant("N");
		//se genera el password y se le envia la longitud
		longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
		recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
		log.debug(tr510s.getFttciplb());
		log.debug(tr510s.getFttcpwd());
		log.debug(tr510s.getFttcusu());
		log.debug(tr510s.getFttcslot());
		log.debug(tr510s.getFttcfab());
		log.debug(tr510s.getFttcmode());
		
	
		}	
			// fin - requerimiento - requerimiento fttc 	
			log.debug("Actividad Generadora : " + cod_actividad);
			mensaje_estado_lineaLocal.setF_reference_14(mesajeOkLocal);
			mensaje_estado_lineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensaje_estado_lineaLocal.setCod_actividad_generadora(cod_actividad);
			if(mensaje_estado_lineaLocal.getAccion() != null && (mensaje_estado_lineaLocal.getAccion().equals("F")||mensaje_estado_lineaLocal.getAccion().equals("R"))){
				if(mensaje_estado_lineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
					log.debug("La actividad corresponde a TOA");
					TOADelegate toaDelegate = new TOADelegate();
					toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
				}else{
					enviarRefrecarDatos(mensaje_estado_lineaLocal.getPeti_numero());
				}
				return;
			}
				
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(cod_actividad);
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensaje_estado_lineaLocal.getPeti_numero(), cod_actividad);
			actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
			actividadEJB.terminar(actDto);		
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);		
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (NamingException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
	}
	
	}

	
	
		/**
		* @param mensaje_estado_lineaLocal
		* @param cod_actividad
		*/
	private void insertarCausalesCnaPeticion(Mensaje_estado_lineaLocal mensaje_estado_lineaLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws NumberFormatException, Exception 
	{
		PeticionLocal peticionLocal=mensaje_estado_lineaLocal.getPeticion();
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
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
		UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);

		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		
		Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		
		Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
		
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
	
	public InformacionTecnicaDTO obtenerRecursosTecnicos( Long idPeticion ) throws ATiempoAppEx
	{

		InformacionTecnicaDTO infoTecDto = new InformacionTecnicaDTO();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			CentralLocalHome centralHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
			Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			CentralLocal central=null;
			Recursos_linea_basicaLocal rlbLocal = recursos_linea_basicaLocalHome.findByPeticion( idPeticion );
			Recursos_linea_basicaKey rlbKey=(Recursos_linea_basicaKey) rlbLocal.getPrimaryKey();
			//Seteo Linea Nueva
			infoTecDto.LineaNueva.setOds( Utiles.sinNull(rlbLocal.getOds_apsc(), "") );
			infoTecDto.LineaNueva.setCdCentral( rlbLocal.getCod_central() );
			
			if(rlbLocal.getCod_central()!=null)
			{
				central=centralHome.findByPrimaryKey(new CentralKey(rlbLocal.getCod_central()));
				infoTecDto.LineaNueva.setCentral( Utiles.sinNull(central.getDesc_central(), "") );
			}
			
			
			infoTecDto.LineaNueva.setTelefono( Utiles.sinNull(rlbLocal.getTelefono_asg(), "") );
			
			// que muestro aqui cual lectura de contador: ver con jorge donde guarda la lectura de contador
			infoTecDto.LineaNueva.setLecCont( Utiles.sinNull(rlbLocal.getLen_anterior(), "") );
			infoTecDto.LineaNueva.setLen( Utiles.sinNull(rlbLocal.getLen(), "") );

			infoTecDto.LineaNueva.setDistr( Utiles.sinNull(rlbLocal.getDist_prim_asg(), "") );//AT-2147 faltan mostrar datos distribuidor
			//infoTecDto.LineaNueva.setDistr( Utiles.sinNull(rlbLocal.getDist_sec_asg(), "") );
			infoTecDto.LineaNueva.setDescripcion( Utiles.sinNull(rlbLocal.getDesc_dist_prim_asg(), "") );
			infoTecDto.LineaNueva.setDirecDistr( Utiles.sinNull(rlbLocal.getDir_distribuidor(), "") );
			infoTecDto.LineaNueva.setListon( Utiles.sinNull(rlbLocal.getListon_asg(), "") );
			infoTecDto.LineaNueva.setParListon( Utiles.sinNull(rlbLocal.getPar_liston_asg(), "") );

			
			infoTecDto.LineaNueva.setCable( Utiles.sinNull(rlbLocal.getCable(), "") );
			infoTecDto.LineaNueva.setParCable( Utiles.sinNull(rlbLocal.getPar_cable(), "") );
			infoTecDto.LineaNueva.setArmario( Utiles.sinNull(rlbLocal.getArmario_asg(), "") );
			infoTecDto.LineaNueva.setDirecArmario( Utiles.sinNull(rlbLocal.getDir_armario(), "") );

			infoTecDto.LineaNueva.setParCaja( Utiles.sinNull(rlbLocal.getPar_caja_asg(), "") );
			infoTecDto.LineaNueva.setCaja( Utiles.sinNull(rlbLocal.getCaja_asg(), "") );
			infoTecDto.LineaNueva.setDirecCaja(Utiles.sinNull(rlbLocal.getDir_caja(),""));
			infoTecDto.LineaNueva.setDistr( Utiles.sinNull(rlbLocal.getDist_sec_asg(), "") );
			infoTecDto.LineaNueva.setPosicionHorizontal(Utiles.sinNull(rlbLocal.getPosicion_horizontal_asg(), ""));
			
			//TODO: 20/10/2009 - Raúl Triviño - requerimiento 00029652
			infoTecDto.LineaNueva.setEstadoLinea(Utiles.sinNull(rlbLocal.getEst_linea(), ""));
			//End TODO
			
			infoTecDto.LineaNueva.setArmario_dedicado(Utiles.sinNull(rlbLocal.getArmario_dedicado(),""));
			infoTecDto.LineaNueva.setCable_dedicado(Utiles.sinNull(rlbLocal.getCable_dedicado(),""));
			infoTecDto.LineaNueva.setCaja_dedicado(Utiles.sinNull(rlbLocal.getCaja_dedicado(),""));
			infoTecDto.LineaNueva.setDirecArmario_dedicado(Utiles.sinNull(rlbLocal.getDir_armario_dedicado(),""));
			infoTecDto.LineaNueva.setDirecCaja_dedicado(Utiles.sinNull(rlbLocal.getDir_caja_dedicado(),""));
			infoTecDto.LineaNueva.setTipoCaja(Utiles.sinNull(rlbLocal.getTipo_caja(),""));
			infoTecDto.LineaNueva.setTipoCaja_dedicado(Utiles.sinNull(rlbLocal.getTipo_caja_dedicado(),""));
			infoTecDto.LineaNueva.setParCable_dedicado(Utiles.sinNull(rlbLocal.getPar_cable_dedicado(),""));
			infoTecDto.LineaNueva.setParCaja_dedicado(Utiles.sinNull(rlbLocal.getPar_caja_dedicado(),""));
			infoTecDto.LineaNueva.setDistancia_caja(rlbLocal.getDistancia_caja());
			infoTecDto.LineaNueva.setIndDedicado(Utiles.sinNull(rlbLocal.getInd_dedicado(),""));
			
			//agonz AT-2171 - latitud_longitud no se ven en bandeja07/05/2009 

			infoTecDto.LineaNueva.setCdCentral_dedicada(Utiles.sinNull(rlbLocal.getCod_central_dedicado(),""));

			infoTecDto.LineaNueva.setLatitud(Utiles.sinNull(rlbLocal.getLatitud(),""));
			infoTecDto.LineaNueva.setLongitud(Utiles.sinNull(rlbLocal.getLongitud(),""));

			
			Collection dslamsLineaNueva = new ArrayList();
			//Aqui voy a cambiar para sacar los dslam en el mismo orden que llegaron y se 
			//guardaron .
			Dslam_conec9_apscLocalHome dslam_conec9_apscLocalHome=(Dslam_conec9_apscLocalHome) HomeFactory.getHome(Dslam_conec9_apscLocalHome.JNDI_NAME);
			//Iterator iterLN=rlbLocal.getDslam_conec9_apsc().iterator();
			Iterator iterLN=dslam_conec9_apscLocalHome.findByConector(rlbKey.id_conector).iterator();
			while (iterLN.hasNext())
			{
				Dslam_conec9_apscLocal unDslamLocal = (Dslam_conec9_apscLocal) iterLN.next();
				Dslam1 unDslam = new Dslam1();
				unDslam.setDslamType(unDslamLocal.getTipo_dslam());
				unDslam.setIp(unDslamLocal.getIp());
				dslamsLineaNueva.add(unDslam);
			}
			infoTecDto.LineaNueva.setDslams(dslamsLineaNueva);
			
// 			TODO - Inicio - agonzalez- 28/04/2008- Zonas de Atendimiento 
			Collection zonasLineaNueva = new ArrayList();
			
			Zonas_atendimientoLocalHome	zonas_atendimientoLocalHome=(Zonas_atendimientoLocalHome) HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
			Iterator iterL=zonas_atendimientoLocalHome.findByConector(rlbKey.id_conector).iterator();
			Dslam1 unDslam;
			while (iterL.hasNext())
			{
				Zonas_atendimientoLocal zonaLocal = (Zonas_atendimientoLocal) iterL.next();
				unDslam = new Dslam1();
				unDslam.setIp(zonaLocal.getIp());
				zonasLineaNueva.add(unDslam);		
			}
			infoTecDto.LineaNueva.setZonas(zonasLineaNueva);
//			TODO - Fin - agonzalez- 28/04/2008- Zonas de Atendimiento 
//			Seteo Linea Existente			
			Long telefono_ant = rlbLocal.getTelefono_ant();
			if ((telefono_ant!=null) && (telefono_ant.longValue()!= 0))
			{
				infoTecDto.LineaExistente.setOds("");
				infoTecDto.LineaExistente.setCdCentral( rlbLocal.getCentral_ant());
				if(rlbLocal.getCentral_ant()!=null)
				{
					central=centralHome.findByPrimaryKey(new CentralKey(rlbLocal.getCentral_ant()));
					infoTecDto.LineaExistente.setCentral( Utiles.sinNull(central.getDesc_central(), "") );
				}
				
				infoTecDto.LineaExistente.setTelefono( Utiles.sinNull(telefono_ant, "") );
			
				infoTecDto.LineaExistente.setLecCont("");
				infoTecDto.LineaExistente.setLen(Utiles.sinNull(rlbLocal.getLen_anterior(),""));

				infoTecDto.LineaExistente.setDistr( Utiles.sinNull(rlbLocal.getDist_sec_ant(), "") );
				infoTecDto.LineaExistente.setDescripcion( Utiles.sinNull(rlbLocal.getDesc_dist_prim_ant(), "") );
				infoTecDto.LineaExistente.setDirecDistr("");
				infoTecDto.LineaExistente.setListon( Utiles.sinNull(rlbLocal.getListon_asg_ant(), ""));
				infoTecDto.LineaExistente.setParListon(Utiles.sinNull(rlbLocal.getPar_liston_ant(), ""));

			
				infoTecDto.LineaExistente.setCable( Utiles.sinNull(rlbLocal.getCable_ant(), "") );
				infoTecDto.LineaExistente.setParCable( Utiles.sinNull(rlbLocal.getPar_cable_ant(), "") );
				infoTecDto.LineaExistente.setArmario( Utiles.sinNull(rlbLocal.getArmario_ant(), "") );
				infoTecDto.LineaExistente.setDirecArmario("");

				infoTecDto.LineaExistente.setParCaja( Utiles.sinNull(rlbLocal.getPar_caja_ant(), "") );
				infoTecDto.LineaExistente.setCaja( Utiles.sinNull(rlbLocal.getCaja_asg_ant(), "") );
				infoTecDto.LineaExistente.setDirecCaja("");
				infoTecDto.LineaExistente.setPosicionHorizontal( Utiles.sinNull(rlbLocal.getPosicion_horizontal_ant(), "") );
				
				Collection dslamsLineaExistente = new ArrayList();
				Iterator iterLE=rlbLocal.getDslam_conec9_apsc().iterator();
				//TODO: VMM No hay dslam para la Linea Existente , hay solo para la linea nueva.
//				while (iterLE.hasNext()){
//					Dslam_conec9_apscLocal unDslamLocal = (Dslam_conec9_apscLocal) iterLE.next();
//					Dslam1 unDslam = new Dslam1();
//					unDslam.setDslamType(unDslamLocal.getTipo_dslam());
//					unDslam.setIp(unDslamLocal.getIp());
//					dslamsLineaExistente.add(unDslam);
//				}
				infoTecDto.LineaExistente.setDslams(dslamsLineaExistente);
				
			}else
			{
				//log.info("No Hay Linea Existente");
				infoTecDto.LineaExistente=null;
			}

		} catch (FinderException e) {
			log.info("No se encontro RecursosLineaBasica [" + idPeticion + "]"+e);			
			infoTecDto.LineaExistente=null;
		} catch (NamingException e)
		{
			log.error("Naming Exception [" + idPeticion + "]");
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}

		return infoTecDto;
	}
	
	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx{
		
		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		BackendExecution bExecution = null;
		boolean cambioTelefono = false;

		try
		{		
			bExecution = BackendTraceUtil.initExecution(tr012s, log);			
			bExecution.setIdMensajeOp(tr012s.getId());
			bExecution.startOperation();

			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009

			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);

			Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr012s.getId()));
			
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
	            
		   try
		   {
		   		Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
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
				log.debug(
					"No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr012s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr012s.getId(),ATiempoAppEx.EXCEPTION);
			}
			bExecution.setNumPetAtiempo(mensajeEstadoLineaLocal.getPeti_numero());
			String codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
			boolean esRefresco=false;
			if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")))
				esRefresco=true;
			
			
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
			//Validacion del estado de la respuesta 
			if (mensaje_estadoKey.cod_estado.intValue() == estadoOk ) {
				log.debug(
					"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr012s));
				return;
			}
			
			if(tr012s.isError() != true && tr012s.getPhoneCentral() > 0){
				
				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				
				Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
	
				if ( recursosLineaBasica.size() == 0){
		
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal  = recursos_linea_basicaLocalHome.create(idConDos);
	
				}else{
		
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)  recursosLineaBasica.iterator().next();
					//req FTTC
					//hayAsignados=true;
				}
				
				
				PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
				//Asigno Central
				CentralKey ck = new CentralKey();
				ck.cod_central=new Long (tr012s.getPhoneCentral());
				CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(ck);
				peticionLocal.setFk_03_central(centralLocal);
				//Se actualiza la central en la bandeja si es que hay alguna publicacion.
				BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
				BIntegradaSessionLocal biLocal = biHome.create();
				biLocal.cambiarCentralPeticion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)),((PeticionKey)peticionLocal.getPrimaryKey()).peti_numero,centralLocal);
				// requ fttc se valida si no hay euqipos asignados para que no sobre escriba la informacion
//				if(!hayAsignados)
//				{
				//Los recursos se ponen en recursos asignados, igual que la asignacion de recursos
				recursos_linea_basicaLocal.setPeticion(peticionLocal);
				recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);

				
				recursos_linea_basicaLocal.setArmario_asg(tr012s.getCloset());
				recursos_linea_basicaLocal.setDir_armario(tr012s.getClosetAddress());
				recursos_linea_basicaLocal.setCable(tr012s.getInCable());
				recursos_linea_basicaLocal.setCaja_asg(tr012s.getBox());
				recursos_linea_basicaLocal.setDir_caja(tr012s.getBoxAddress());
				recursos_linea_basicaLocal.setDesc_central(String.valueOf(tr012s.getPhoneCentralDescription()));
				recursos_linea_basicaLocal.setCod_central(new Long((tr012s.getPhoneCentral())));
				recursos_linea_basicaLocal.setDesc_dist_prim_asg(tr012s.getPrimaryDistributorDescription());
				recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr012s.getSecondaryDistributorDescription());
				recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr012s.getPrimaryDistributor()));
				recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr012s.getSecondaryDistributor()));
				recursos_linea_basicaLocal.setDir_distribuidor(tr012s.getDistributorAddress());
				recursos_linea_basicaLocal.setLen(tr012s.getLen());
				recursos_linea_basicaLocal.setListon_asg(tr012s.getStrip());
				//}		
				//@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
				//se realiza validacion para saber si algun campo FTTC contiene datos
				//se agrega nueva validacion 31/03/2014 cardena
				//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
				String accion = validaFTTC(tr012s.getFttcfab(), tr012s.getFttcmode() , tr012s.getFttcslot() ,
						tr012s.getFttcpwd() , tr012s.getFttcusu() , tr012s.getFttciplb(),codAct,mensajeEstadoLineaLocal);
				
				if(accion.equals("LB"))
				{	
					// este else espara saber si no trae equipos fttc
					log.debug("No Trae Equipos FTTC");

					if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
					{
						
					}else{
					recursos_linea_basicaLocal.setRec_fttc_asg("N");	
					}
					}else if(accion.equals("PGI")){	
					return;								
				}else if(accion.equals("FTTC")){
				log.debug("Trae Equipos FTTC-");
				//se instancia la clase del password FTTC
				int longitud=0;
									
				Password generarPass = new  Password();
			
			//se valida que tipo de operacion comercial llega 1 es alta 
		if (peticionLocal.getTica_id().equals("A")){
			//se trae de la tabla propertiesBD la longitud del password
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
			recursos_linea_basicaLocal.setFttciplb_asg(tr012s.getFttciplb());
			recursos_linea_basicaLocal.setFttcpwd_asg(tr012s.getFttcpwd());
			recursos_linea_basicaLocal.setFttcusu_asg(tr012s.getFttcusu());
			recursos_linea_basicaLocal.setFttcslot_asg(tr012s.getFttcslot());
			recursos_linea_basicaLocal.setFttcfab_asg(tr012s.getFttcfab());
			recursos_linea_basicaLocal.setFttcmode_asg(tr012s.getFttcmode());
			//el campo que nos dice que hay equipos De tipo FTTC
			recursos_linea_basicaLocal.setRec_fttc_asg("S");
			//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
			recursos_linea_basicaLocal.setRack(tr012s.getRack().toString());
			recursos_linea_basicaLocal.setSubRack(tr012s.getSubRack().toString());
			if(recursos_linea_basicaLocal.getRec_fttc_ant()!=null&&recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))
			{
				
			}else{
				recursos_linea_basicaLocal.setRec_fttc_ant("N");	
			}
			//se genera el password y se le envia la longitud
			longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
			recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
			log.debug(tr012s.getFttciplb());
			log.debug(tr012s.getFttcpwd());
			log.debug(tr012s.getFttcusu());
			log.debug(tr012s.getFttcslot());
			log.debug(tr012s.getFttcfab());
			log.debug(tr012s.getFttcmode());
			
			
		//si la operacion comercial es diferente de 1 puede ser un traslado o una baja
		}else if(peticionLocal.getTica_id().equals("B"))
		{
//			se trae de la tabla propertiesBD la longitud del password
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
			log.debug("Treae Equipos FTTC de baja");
			// se guardan los nuevos equipos en los campos asignados			
			recursos_linea_basicaLocal.setFttciplb_asg(tr012s.getFttciplb());
			recursos_linea_basicaLocal.setFttcpwd_asg(tr012s.getFttcpwd());
			recursos_linea_basicaLocal.setFttcusu_asg(tr012s.getFttcusu());
			recursos_linea_basicaLocal.setFttcslot_asg(tr012s.getFttcslot());
			recursos_linea_basicaLocal.setFttcfab_asg(tr012s.getFttcfab());
			recursos_linea_basicaLocal.setFttcmode_asg(tr012s.getFttcmode());
			recursos_linea_basicaLocal.setRec_fttc_asg("S");
			//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
			recursos_linea_basicaLocal.setRack(tr012s.getRack().toString());
			recursos_linea_basicaLocal.setSubRack(tr012s.getSubRack().toString());
			if(recursos_linea_basicaLocal.getRec_fttc_ant()!=null&&recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))
			{
				
			}else{
				recursos_linea_basicaLocal.setRec_fttc_ant("N");	
			}
		
//			se genera el password y se le envia la longitud
			longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
			recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
			log.debug(tr012s.getFttciplb());
			log.debug(tr012s.getFttcpwd());
			log.debug(tr012s.getFttcusu());
			log.debug(tr012s.getFttcslot());
			log.debug(tr012s.getFttcfab());
			log.debug(tr012s.getFttcmode());
		}else if (peticionLocal.getTica_id().equals("P") ||peticionLocal.getTica_id().equals("T"))
		{
//			se trae de la tabla propertiesBD la longitud del password
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			Collection ps = peticionLocal.getProducto_servicio_peticion();
			String operacionComercial = "";
			boolean traslado=false;
			boolean alta=false;
			if(ps!=null && ps.size()>0){
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
				
					//guardamos en un iterator los ps
					Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
					Operacion_comercialLocal operacion_comercial= (Operacion_comercialLocal) element.getOperacion_comercial();
					operacionComercial+=(operacion_comercial.getOpco_tipo()+" "+operacion_comercial.getOpco_tras()+" ");
					log.debug("operacion comercial "+operacionComercial);
					if(operacion_comercial.getOpco_tras()!=null && operacion_comercial.getOpco_tras().equals("T") &&
							!(operacion_comercial.getOpco_tipo().equals("ACP") || operacion_comercial.getOpco_tipo().equals("BCP"))){
						traslado=true;
					}
					if(operacion_comercial.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)
							|| operacion_comercial.getOpco_tipo().equals("ACP")
							|| operacion_comercial.getOpco_tipo().equals("BCP"))
					{
						alta=true;
					}
					}
				}
			
			//se inserta en anteriores cuando hay Traslados ACP BCP o Baja SVA unicamente
			if(traslado||(!traslado && !alta))
			{
				log.debug("Operacion comercial marcada como Posventa (T) o Posventa (B) Se optienen los recursos en Anteriores");
				recursos_linea_basicaLocal.setFttciplb_ant(tr012s.getFttciplb());
				recursos_linea_basicaLocal.setFttcpwd_ant(tr012s.getFttcpwd());
				recursos_linea_basicaLocal.setFttcusu_ant(tr012s.getFttcusu());
				recursos_linea_basicaLocal.setFttcslot_ant(tr012s.getFttcslot());
				recursos_linea_basicaLocal.setFttcfab_ant(tr012s.getFttcfab());
				recursos_linea_basicaLocal.setFttcmode_ant(tr012s.getFttcmode());	
				recursos_linea_basicaLocal.setRec_fttc_ant("S");
				//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
//				recursos_linea_basicaLocal.setRack_ant(tr012s.getRack().toString());
//				recursos_linea_basicaLocal.setSubRack_ant(tr012s.getSubRack().toString());
			}else{
			if(alta) 
				{
				//cuando es una postventa y es una alta (SVAS)se registra en asignados
				log.debug("Se insertan recursos en asignados (ALTA) en posventa");
				recursos_linea_basicaLocal.setFttciplb_asg(tr012s.getFttciplb());
				recursos_linea_basicaLocal.setFttcpwd_asg(tr012s.getFttcpwd());
				recursos_linea_basicaLocal.setFttcusu_asg(tr012s.getFttcusu());
				recursos_linea_basicaLocal.setFttcslot_asg(tr012s.getFttcslot());
				recursos_linea_basicaLocal.setFttcfab_asg(tr012s.getFttcfab());
				recursos_linea_basicaLocal.setFttcmode_asg(tr012s.getFttcmode());
				recursos_linea_basicaLocal.setRec_fttc_asg("S");
				//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
				recursos_linea_basicaLocal.setRack(tr012s.getRack().toString());
				recursos_linea_basicaLocal.setSubRack(tr012s.getSubRack().toString());
			}
			}
//			se genera el password y se le envia la longitud
			longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
			recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));				
		}
		}
				// fin - requerimiento - requerimiento fttc 	
				
				//@idrincon - req 1038 - 29/10/2010
				String telefonoNew = String.valueOf(tr012s.getPhoneNumber());
				RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
				boolean esTrasladoba = recursosBADelegate.esTrasladoBa(mensajeEstadoLineaLocal.getPeti_numero());
				boolean esTrasladolb = tieneTrasladoLB(mensajeEstadoLineaLocal.getPeti_numero());
				boolean esTrasladotv = recursosBADelegate.esTrasladoTv(mensajeEstadoLineaLocal.getPeti_numero());
				boolean traslado = false;

				if( esTrasladoba || esTrasladolb || esTrasladotv ){
					traslado = true;
				}
				
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")) && traslado){
					String telefonoOld = this.consultarTelefono(mensajeEstadoLineaLocal.getPeti_numero());
					//peticionLocal.setNum_ide_nu_stb(dsTelefono);
					recursos_linea_basicaLocal.setTelefono_asg(new Long(tr012s.getPhoneNumber()));
					if(telefonoOld != null ){
						if( telefonoNew.equalsIgnoreCase(telefonoOld)){
							cambioTelefono = true;
						}
					}else{
						log.debug("RecursosServiciosBean: asignarRecursoSTB: telefonoOld: "+telefonoOld+ " cambioTelefono: "+cambioTelefono);
					}
				}else{
					recursos_linea_basicaLocal.setTelefono_asg(new Long(tr012s.getPhoneNumber()));
				}
				
				//recursos_linea_basicaLocal.setTelefono_asg(new Long(tr012s.getPhoneNumber()));
//				fin - req 1038 29/10/2010
//				// requ fttc se valida si no hay euqipos asignados para que no sobre escriba la informacion
//				if(!hayAsignados)
//				{
				recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr012s.getPairCobreBox()));
				recursos_linea_basicaLocal.setPar_cable(new Long(tr012s.getPairCable()));
				recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr012s.getPairCobreStrip()));
				recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr012s.getHorizontalPosition());
				
				//DAVID: Se adiciona siguiente campo, Septiembre 10 de 2010				
				recursos_linea_basicaLocal.setTipo_caja(tr012s.getBoxType());
//				}
				if (tr012s.getServices() != null)
				{
					//if(esRefresco)
					//{
//						log.debug("Tengo que borrar los Servicios Suplementarios que tenia antes");
						borrarSS(recursos_linea_basicaLocal);
					//}
						Servicio_basico_supl_conec6_apsc_lineaLocalHome servicio_basico_supl_conec6_apsc_lineaLocalHome = (Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
					for (Iterator iter = tr012s.getServices().iterator(); iter.hasNext();)
					{
						Long idService =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						String servicio= (String) iter.next();
						Servicio_basico_supl_conec6_apsc_lineaLocal servicio_basico_supl_conec6_apsc_lineaLocal = 
						servicio_basico_supl_conec6_apsc_lineaLocalHome.create(idService);
						servicio_basico_supl_conec6_apsc_lineaLocal.setCodigo_ps(servicio);
						servicio_basico_supl_conec6_apsc_lineaLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
				}
			
				mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				PeticionKey peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB  actividadEJB=null;
				ActividadEJBDTO actDto=null;

				if(!esRefresco){
					actividadEJB = actividadFactoryEJB.getActividad(codAct);
					actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codAct);
				}
				
				if (tr012s.getDslams() == null || tr012s.getDslams().size()<1)
				{
					//Si no viene Dslam y la consulta la invoca Puntos de red BA, entonces se deriva a PGI
					// y se asocia la causal de no dslam.//Leonardo
					if(esRefresco)
						return;
					if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct))
					{
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						log.debug("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
						//modificado adecarlini
						//insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());
						//fin 
						actDto.setObservacion("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
//						agregado adecarlini	
						String codError = String.valueOf(tr012s.getTypeError());
						String nombreClase=TR012S.class.getName();
						nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					    //ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR012S.class.getName());
					    if(errorLegado != null){
					       peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						   insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					    }else{
							insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());
					    }
									  //
					}					
				}else{
					
					ArrayList listaZonasOld = new ArrayList();
					ArrayList listaZonasNew = new ArrayList();
					
					if(esRefresco){
						listaZonasOld = this.consultaZonasAtendimiento(peticionKey.peti_numero);
					}
						
					borrarDslams(recursos_linea_basicaLocal);
					borrarZonas(recursos_linea_basicaLocal);
					
					//correccion defecto 21331 
//					for (Iterator iter = tr012s.getDslams().iterator(); iter.hasNext();){
//						Long idDslam =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
//						Dslam1 dslam1 = (Dslam1) iter.next();
//						Dslam_conec9_apscLocal dslam_conec9_apscLocal = dslam_conec9_apscLocalHome.create(idDslam);
//						dslam_conec9_apscLocal.setIp(dslam1.getIp());
//						dslam_conec9_apscLocal.setTipo_dslam(dslam1.getDslamType());
//						dslam_conec9_apscLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
//					}
//					TODO - Inicio - agonzalez- 28/04/2008- Zonas de Atendimiento
		
					Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					Zonas_atendimientoLocal zona_atendimientoLocal=null;
					for (Iterator iter = tr012s.getDslams().iterator(); iter.hasNext();){
						Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						if(zona_atendimientoLocal==null){
							//Se valida que es la primera zona 
							zona_atendimientoLocal=validarPSPromocion(peticionLocal,dslam1,recursos_linea_basicaLocal);	
						}else{
							zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
							zona_atendimientoLocal.setIp(dslam1.getIp());
							zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						}
					}
					if(esRefresco){
						//@idrincon - req 1038 - 29/10/2010 se agrega if de validacion
						if(!cambioTelefono){
							recursos_linea_basicaLocal.setCambio_zonas("false");
							listaZonasNew = this.consultaZonasAtendimiento(peticionKey.peti_numero);
							
							if (listaZonasOld.size() != listaZonasNew.size()){
								recursos_linea_basicaLocal.setCambio_zonas("true");
								log.debug("ES diferente: true");
							}else{
								masterFor: for(int i  = 0; i < listaZonasOld.size(); i++){	
									String zonaLocalOld = (String)listaZonasOld.get(i);
									
									slaveFor: for(int j  = 0; j < listaZonasNew.size(); j++){
										String zonaLocalNew = (String)listaZonasNew.get(j);
										
										if (zonaLocalOld.equals(zonaLocalNew)){
											continue masterFor;
										}
									}
									
									log.debug("ES diferente: true");
									recursos_linea_basicaLocal.setCambio_zonas("true");
									break masterFor;
								}
							}
						}else{
							log.debug("RecursosServiciosBean: consultaPuntosRedSTB: else cambio telefono: es diferente: true");
							recursos_linea_basicaLocal.setCambio_zonas("true");
						}
						//fin req 1038 - 29/10/2010
					}
					
//					TODO -Fin - agonzalez- 28/04/2008- Zonas de Atendimiento
				}
				if(esRefresco)
					return;
				if(actividadEJB!=null)
					actividadEJB.terminar(actDto);		
			}else
			{
				if(esRefresco)
					return;
				mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				
				//modificado adecarlini
				PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
				PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				//
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codAct);
				
				//agregado adecarlini	
				String codError = String.valueOf(tr012s.getTypeError());
				String nombreClase=TR012S.class.getName();
				nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
				ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
				//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR012S.class.getName());
				if (errorLegado != null){ 
					peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
				}else{
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				}
				//
				
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				if (tr012s.getPhoneCentral() < 1)
				{
					actDto.setObservacion(tr012s.getErrorMessage()+". Central = 0. Se redirige a PGI.");
				}
				else
				{
					actDto.setObservacion(tr012s.getErrorMessage()+". Error de mensajeria. Se redirige a PGI.");
				}
				//modificado adecarlini
				//insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
				//fin
				actividadEJB.terminar(actDto);
					
			}	
		
		} catch (NumberFormatException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (TnProcesoExcepcion e) {
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
		} catch (NamingException e)
		{
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
		catch(Exception e)
		{
			bExecution.setErrorOp(true);
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		finally{  
			bExecution.endAll();
			// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}

	}

	private void borrarSS(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx
	{
		Recursos_linea_basicaKey key=(Recursos_linea_basicaKey) recursos_linea_basicaLocal.getPrimaryKey();
		
			try
			{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Servicio_basico_supl_conec6_apsc_lineaLocalHome servicio_basico_supl_conec6_apsc_lineaLocalHome=(Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
			
			Collection listaSS=servicio_basico_supl_conec6_apsc_lineaLocalHome.findByConector(key.id_conector);
			for(Iterator iterator=listaSS.iterator();iterator.hasNext();)
			{
				Servicio_basico_supl_conec6_apsc_lineaLocal servicio_basico_supl_conec6_apsc_linea=(Servicio_basico_supl_conec6_apsc_lineaLocal) iterator.next();
				servicio_basico_supl_conec6_apsc_linea.remove();
			}
		}
		catch (FinderException e1)
		{
			log.error("",e1);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e1);
		} catch (EJBException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		} catch (RemoveException e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		}
		catch (NamingException e)
		{
			log.error("Creacion de Local Home Null",e);	
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioPuntosRedSTB(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void envioPuntosRedSTB(String peticion, String idActividad) throws ATiempoAppEx {
		this.envioPuntosRedSTB(null,peticion,idActividad);
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.solicitud.SolicitudPuntosRedInterfaces#envioPuntosRedSTB(java.lang.String, java.lang.String)
	 */
	public void envioPuntosRedSTB(String telefonoConsulta, String peticion, String idActividad) throws ATiempoAppEx {

		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			boolean esRefrescar=false;
			if(idActividad.equals(""))
			{
				esRefrescar=true;
				idActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA");
			}
		
			TR012E tr012e = new TR012E();
			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			PeticionKey peticionKey = new PeticionKey(new Long(peticion));
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
		
			//Proceso para la validacion de los tipos de ps, que estan en el mensaje
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
		
			Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
			tr012e.setId(IdCorrelativo.toString());
			
			//Telefono a consultar por defecto
			String phoneNumber = peticionLocal.getNum_ide_nu_stb();
			//Si viene un telefono, se consulta por ese telefono y se asocia a la peticion
			if (telefonoConsulta != null && !"".equals(telefonoConsulta.trim())){
					phoneNumber = telefonoConsulta;							
			}			
			try
			{
				
				if (phoneNumber!=null && !phoneNumber.trim().equals(""))
				{
					if (phoneNumber.length()>8)
					{ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else
				{
					phoneNumber="0";
				}
				
				tr012e.setPhoneNumber(new Integer(phoneNumber).intValue());
			}
			catch(Exception e)
			{
				phoneNumber="0";
				log.info("No Hay PhoneNumber Valido en la peticion."+peticionLocal.getNum_ide_nu_stb());
			}

			DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
			tr012e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());
		
			LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			// Inicio CR 26747 - German P: Se obtienen la localidad y la sublocalidad de la tabla de Mapeo Localidades
			String subLocalidad = peticionLocal.getNom_slo_no();
            InfoComunColombiaBusinessInterface infoComunColombiaBI;
			infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			Long codLocApsc = infoComunColombiaBI.obtenerCodigoAPSC(new Long(localidadKey.cod_loc), subLocalidad);
			//tr012e.setCity(new Integer(localidadKey.cod_loc).intValue());
			tr012e.setCity(codLocApsc.intValue());
			// Fin CR 26747			
		
			// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
			mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
			Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
			mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
			mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
			mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
			
			if(esRefrescar)
				mensajeEstadoLineaLocal.setAccion("R");
			
			mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
			if(peticionLocal.getCod_are_tel_cd()!=null)
				mensajeEstadoLineaLocal.setArea(new Integer(peticionLocal.getCod_are_tel_cd()));
			if(peticionLocal.getTel_cot_ds()!=null)
				mensajeEstadoLineaLocal.setTelefono(new Long(phoneNumber));
		
			SolicitudPuntosRedSTBMQ solicitudPuntosRedSTBMQ = new SolicitudPuntosRedSTBMQ();
			solicitudPuntosRedSTBMQ.enviarMensaje(tr012e);
		
		
			
		} catch (NumberFormatException e) {
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		
		} catch (FinderException e) {
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (NamingException e) {
			log.error("Creacion de Local Home Null",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING);
		}
		catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
		
		public void envioLiberacionRecursos(Long peticion, String idActividad, Integer actividadFlujo)throws ATiempoAppEx {
			
			try{

				//	TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionesServicesLocalHome peticionesServicesLocalHome = (PeticionesServicesLocalHome)  HomeFactory.getHome(PeticionesServicesLocalHome.JNDI_NAME);
				Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);		
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
	
				TR002E tr002e = new TR002E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionKey peticionKey = new PeticionKey(peticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				
				//Object PsVsOcVO
				PeticionesServicesLocal peticionesServicesLocal = peticionesServicesLocalHome.create();
				ArrayList flujoPeticiones = peticionesServicesLocal.listaPsDePeticionQuePasaPorActividad(peticion, actividadFlujo);
				 
				PsVsOcVO psprim;
				Producto_servicioLocal producto_servicioLocal = null;
				
				String codCausa = null;
				int size1 = -1;
				int size2 = -1;
				Long psId = null;
				
				psprim = (PsVsOcVO) flujoPeticiones.iterator().next();
				producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				
				//AT-1426 - Stall ConcreteProducto_servicio.getEstado_ps_peticion() - guido - 20-Jun-2008 - Inicio
				
				Collection coll1 = peticionLocal.getProducto_servicio_peticion();
				
				Iterator itPsp = coll1.iterator();
				while (itPsp.hasNext()) {
					Producto_servicio_peticionLocal psp =(Producto_servicio_peticionLocal) itPsp.next();
					if (psprim.getPsId().equals(psp.getPsId())) {
						Collection coll2 = psp.getEstado_ps_peticion();
						/*
						 * CR 00024071 - 2009/04/29
						 * 		Se controlan error en obtención de datos - German P.  
						 */
						if(coll2 != null){
							Estado_ps_peticionLocal estado_ps_peticionLocal =(Estado_ps_peticionLocal) coll2.iterator().next();
							if (estado_ps_peticionLocal != null){
								size2 = coll2.size();
								codCausa = String.valueOf(estado_ps_peticionLocal.getCod_causal());
								break;
							}
						}
					}
				}
				
				size1 = coll1.size();
				coll1 = null;
				
				if (codCausa == null){
					// se obtiene valor por defecto de properties
					codCausa = VpistbbaConfig.getVariable("COD_CAUSA");
					log.debug("Realizando reversa - No se encontro el codCausa, se setea por defecto codCausa="+codCausa);
				}
				
//				Collection estadosPsPet = estado_ps_peticionHome.findByPeticionPS(peticion, psprim.getPsId());
				log.debug("Realizando reversa - creando tr002  codCausa="+codCausa+" psId=" + psId + " peticion=" + peticion + " size1=" + size1 + " size2=" + size2);				
				
				tr002e.setId(IdCorrelativo.toString());
				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
			
				tr002e.setAtisRequestNumber(peticion_atisKey.cod_pet_cd.longValue());

				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr002e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());

				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();

				// Inicio CR 26747 - German P: Se obtienen la localidad y la sublocalidad de la tabla de Mapeo Localidades
				String subLocalidad = peticionLocal.getNom_slo_no();
	            InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				Long codLocApsc = infoComunColombiaBI.obtenerCodigoAPSC(new Long(localidadKey.cod_loc), subLocalidad);
				//tr002e.setCity(Integer.parseInt(localidadKey.cod_loc));
				tr002e.setCity(codLocApsc.intValue());
				// Fin CR 26747
				
				Collection colConect2 = peticionLocal.getRecursos_linea_basica () ;
            
				   if (colConect2.size () != 1)
				   {
					   String error = "La peticion " + peticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
					   throw new ATiempoAppEx (error) ;
				   }
				   
				Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;
				tr002e.setCni(codCausa);
//				AT-1426 - Stall - FIN
				tr002e.setOdsNumber(recursosStb.getOds_apsc().intValue());
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				if(peticionLocal.getCod_are_tel_cd()!=null)
					mensajeEstadoLineaLocal.setArea(new Integer(peticionLocal.getCod_are_tel_cd()));
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if(peticionLocal.getTel_cot_ds()!=null)
					mensajeEstadoLineaLocal.setTelefono(new Long(phoneNumber));
					
				
				LiberacionRecursoSTBMQ recursoSTBMQ = new LiberacionRecursoSTBMQ();
				recursoSTBMQ.enviarMensaje(tr002e);
				
				
			} catch (NumberFormatException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);

			} catch (FinderException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (NamingException e) {
				log.error("Creacion de Local Home Null",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING);
			}
			catch(Exception e)
			{
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}	
		}
		
		
		public void recepcionLiberacionRecursos(TR002S tr002s)throws ATiempoAppEx {
			
			// CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
		
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr002s, log);
				bExecution.setNumPetAtiempo(new Long(tr002s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr002s.getId());
				bExecution.startOperation();
				
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr002s.getId()));
	
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
	    
			   try
			   {
			   		Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
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
					log.debug(
						"No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr002s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr002s.getId(),ATiempoAppEx.EXCEPTION);
				}
	
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk ) {
					log.debug(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr002s));
					return;
				}
				log.debug("Estoy recepcionando un tr002s con valor de error:"+tr002s.isError());
				if(tr002s.isError() != true && tr002s.isResponse() == true ){
					
					Recursos_linea_basicaLocal recursos_linea_basicaLocal;
	
					Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();

					if ( recursosLineaBasica.size() == 0){

						Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
						recursos_linea_basicaLocal  = recursos_linea_basicaLocalHome.create(idConDos);

					}else{

						recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)  recursosLineaBasica.iterator().next();

					}

					recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
					recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);
					
					mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					PeticionKey peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();

					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLineaLocal.getCod_actividad_generadora());
					
					actDto.setObservacion(". Liberacion de Recursos OK");
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N" );
					actividadEJB.terminar(actDto);

				}else
				{
	
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					
					//modificado adecarlini
					PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
					PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
					//fin modificado
					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLineaLocal.getCod_actividad_generadora());

					//Se quiebra el flujo de Asignacion Manual
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N" );
					//En la liberacion ya vienen los ps con Errores. Por lo tanto se mantienen esas causas y no se pisan con una causa nueva por falla en la liberacion de recursos
					//insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actividadEJB.getIdActividadFlujo());
					//Se toma el flujo de PGI
					String observacion="";
					if(tr002s.getErrorMessage()!=null)
						observacion=tr002s.getErrorMessage();
					actDto.setObservacion(observacion+". Falla en la liberación de Recursos. Se deriva a PGI");
					
					//agregado por adecarlini
					String codError = String.valueOf(tr002s.getTypeError());
					String nombreClase=TR002S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR002S.class.getName());
					if (errorLegado != null){
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					}
			    	// fin agregado
									
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					actividadEJB.terminar(actDto);
				}
					
			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);

			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
				
			} catch (CreateException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
				
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS);
			}
			catch (NamingException e)
			{
				log.error(" Creacion de Local Home Null",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}
			catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}			
			finally{  
				bExecution.endAll();
				// CR15338 - @Trace - ana santos - 04/08 - FIN 
		}
		
		}
		
		public void eliminarLecturaContador(Long idPeticion) throws ATiempoAppEx
		{
			try
			{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal=recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
				recursos_linea_basicaLocal.setCont_linea_act(null);
				recursos_linea_basicaLocal.setCont_linea_nueva(null);
			}
			catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}
			catch(FinderException fe)
			{
						
			}
		}
		
		public void actualizarLecturaContador(Long idPeticion, String lecContadorDesde, String lecContadorHasta) throws ATiempoAppEx
		{
			try
			{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal=recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
				log.debug("Lec Contador Desde:"+lecContadorDesde);
				log.debug("Lec Contador Hasta:"+lecContadorHasta);
				if(lecContadorDesde!=null && !lecContadorDesde.equals(""))
				{
					try
					{
						recursos_linea_basicaLocal.setCont_linea_act(new Long(lecContadorDesde));
					}
					catch(NumberFormatException numberFormatException)
					{
						log.debug("NumberFormatException",numberFormatException);
					}
				}
				if(lecContadorHasta!=null && !lecContadorHasta.equals(""))
				{
					try
					{
						recursos_linea_basicaLocal.setCont_linea_nueva(new Long(lecContadorHasta));
					}
					catch(NumberFormatException numberFormatException)
					{
						log.debug("NumberFormatException",numberFormatException);
					}
				}		
			}
			catch (FinderException e)
			{
				log.debug("No se realiza Actualizacion Lectura Contador no hay recursos linea basica");
			}
			catch(NumberFormatException e)
			{
				log.debug("No se realiza Actualizacion Lectura Contador pork los datos son NULOS o NO NUMERICOS");
			}
			catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}
		

		}
		
		public ArrayList obtenerListaServiciosSuplementarios( Long idPeticion ) {

			ArrayList lista = new ArrayList();

			try {
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal rlbLocal = recursos_linea_basicaLocalHome.findByPeticion( idPeticion );
				Collection c = rlbLocal.getServicio_basico_supl_conec6_apsc_linea();
				
				if ( c==null || c.size()==0 )
					return lista;
				
				Servicio_basico_supl_conec6_apsc_lineaLocal ssbaLocal;
				for (Iterator it=c.iterator(); it.hasNext(); ) {
					ssbaLocal = (Servicio_basico_supl_conec6_apsc_lineaLocal) it.next();
					if ( ssbaLocal != null ) {
						lista.add( ssbaLocal.getCodigo_ps() );
					}
				}

			} catch (FinderException e) {
				log.debug("No se encontro RecursosLineaBasica [" + idPeticion + "]");
			}catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
			}

			return lista;
		}

		private String getNombreBandeja(String plataforma){
			return VpistbbaConfig.getVariable("BANDEJA_"+plataforma);
		}

		// TCS - error legado
		private ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
			ErrorLegadoLocal errorLegado = null;
			try{
				ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
				errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
			} catch (javax.ejb.FinderException e) {
				e.printStackTrace();
			} catch (NamingException e) {
		 //TODO ver que pasa con esta excepcion
				e.printStackTrace();
			}
			return errorLegado;
		}

// CR20948 - Altamira - guido - 20/Abr/2009 - INICIO
	//TODO: 18/11/2009 - Raúl Erensto Triviño - Ajuste por defecto reportado Puntos a ser tenidos en Cuenta 
	//	para Revisión por el Ingeniero Responsable de la Plataforma Altamira!..
		
		public void altamiraEnvioTr601(ActividadEJBDTO act, String atiempoServiceName, boolean reversa) throws ATiempoAppEx {
			Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
			SenderTr601Base sender = (SenderTr601Base) SenderFactory.getSender(atiempoServiceName);
			//sender.setRecursosLinea(this);
			sender.setActividadEJBDTO(act);
			sender.setReversa(reversa);
			Long petiNumero = act.getNumeroPeticion();
			int tipoActividad = 0;
							
			boolean inhibir = altamiraInhibirEnvioTr601(act, sender);
			if (inhibir) {
				log.debug("Se inhibe envio tr601 petiNumero=" + petiNumero + " sender=" + sender);
				return;
			}
				
			TR601E tr601e = new TR601E();
			try {
				
				Long idMsg = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionKey peticionKey = new PeticionKey(petiNumero);
				
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Causal_peticionLocalHome causalPeticionLocalHome = (Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
				Ciclos_facturacionLocalHome ciclosFacturacionLocalHome = (Ciclos_facturacionLocalHome) HomeFactory.getHome(Ciclos_facturacionLocalHome.JNDI_NAME);
				
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				boolean bEnvio = false;
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();	

				//TODO: 18022010 - RETA - Ajuste para tener en cuenta los cambios de prepago
				//Obtención del indicador del tipo de actividad (1: Baja, 2: Alta y prepago, 0: No identificado)
				if ((act.getCodigoActividad().toUpperCase().indexOf(ComunInterfaces.BAJ_SERVICIO) >= 0)){
					tipoActividad = 1;
				} else if ((act.getCodigoActividad().toUpperCase().indexOf(ComunInterfaces.ALT_SERVICIO) >= 0)
						||(act.getCodigoActividad().toUpperCase().indexOf(ComunInterfaces.ALT_SERVICIO_CAMBIO_TIPO_PREPAGO) >= 0)
						||(act.getCodigoActividad().toUpperCase().indexOf(ComunInterfaces.ALT_SERVICIO_CAMBIO_NUMERO) >= 0)
						|| (act.getCodigoActividad().toUpperCase().indexOf(ComunInterfaces.BAJ_SERVICIO_DESACTIVACION_CLAVE) >= 0)){
					tipoActividad = 2;
				}
				//End TODO

				
				recorrePS : for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					log.debug("Creando TR601e para petiNumero=" + petiNumero + " atiempoServiceName=" + atiempoServiceName + " actividad=" + act.getCodigoActividad() + " reversa=" + reversa);

					bEnvio = false;
					
					switch(tipoActividad){
						case 1:
							//if ((familiaps = 1)  && (tipoOperComer = 'B' || tipoOperComer = 'BCP') && (nompreps like 'control')) || (familiaps = 300 nompreps like 'prepag'))
							if ((producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() == ComunInterfaces.familiaLinea 
							          && (producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBaja)
							          		||producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoBajaCambioProd))
									  && (producto_servicio_peticionLocal.getProducto_servicio().getPs_nombre().toLowerCase().indexOf(ComunInterfaces.BanderaControl) != -1))
							          || ( (producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() == ComunInterfaces.familiaPcLinea || producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() == ComunInterfaces.familiaIP ) 
							              && producto_servicio_peticionLocal.getProducto_servicio().getPs_nombre().toLowerCase().indexOf(ComunInterfaces.BanderaPrepago) != -1)){
							            bEnvio = true;
							     }    
						break;
						
						case 2:
							 //if (familiaps = 1  && tipoOperComer = 'A' || tipoOperComer = 'ACP' && nompreps like 'control') || (familiaps = 300 nompreps like 'prepag')
							if ((producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() == ComunInterfaces.familiaLinea 
							          && (producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta)
							          		||producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(ComunInterfaces.opCoTipoAltaCambioProd))
                                      && (producto_servicio_peticionLocal.getProducto_servicio().getPs_nombre().toLowerCase().indexOf(ComunInterfaces.BanderaControl) != -1))
							          || (producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() == ComunInterfaces.familiaPcLinea
							              && producto_servicio_peticionLocal.getProducto_servicio().getPs_nombre().toLowerCase().indexOf(ComunInterfaces.BanderaPrepago) != -1)){
							            bEnvio = true;
							     }
						break;
					}
								     
					if (bEnvio){
							Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
							DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
							LocalidadKey localidadKey = (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
							
							tr601e.setAtiempoServiceName(sender.getATiempoServiceName());
							tr601e.setAtiempoRequestNumber(petiNumero);
							tr601e.setAtisRequestNumber(peticion_atisKey.cod_pet_cd);
							tr601e.setId(String.valueOf(idMsg));
							tr601e.setDepartment(new Integer(departamentoKey.cod_dpt)); // TODO: ALT - Verificar si todo @Tiempo mandeja el codDep como string entonces mandarlo string 

							// Setea datos particulaes de cada operacion comercial (o lo que lo es lo mismo propios de cada servicio altamira a invocar)  
							sender.setParticularData(tr601e);
							tr601e.setPsCode(producto_servicio_peticionLocal.getPsId().toString());
							sender.createMensajeEstadoLinea(idMsg, tr601e.getPhoneNumber());
							
							// CR 27638 - German P.
							// si es actividad dasabilitar ir a buscar el valor
							// Aqui se setaria el codigo secreto, el mismo se deberia de obtener de alguna tabla a establecer
							if (act.getCodigoActividad().equals(ComunInterfaces.COD_DESACT_CLAVE_SECRETA)){
								Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
								Recursos_linea_basicaLocal rLocal = recursos_linea_basicaLocalHome.findByPeticion( petiNumero );
								tr601e.setSecretCode(rLocal.getSecret_code());
							}else if (act.getCodigoActividad().equals(ComunInterfaces.COD_ALTA_CLAVE_SECRETA)){
								tr601e.setSecretCode(ComunInterfaces.CODIGO_SECRETO_DEFAULT); // valor por defecto
							}
							
							//TODO: Raúl Triviño: Validación del ciclo de usuario
							if (producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(opCoTipoAlta) 
									|| producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo().equals(opCoTipoAltaCambioProd)){
								try{
									Fecha fecha = new Fecha();
									Timestamp laFecha  = fecha.getTimestamp();
																		
									Integer dia = new Integer(laFecha.getDate());
									Ciclos_facturacionLocal ciclosLocal=ciclosFacturacionLocalHome.findDayInClycle(dia,dia);
									Ciclos_facturacionKey ciclosKey = (Ciclos_facturacionKey)ciclosLocal.getPrimaryKey();
									
									tr601e.setCycle(ciclosKey.ciclo.toString());
								}catch(FinderException ex){
									log.debug("No se enviará el ciclo, porque no se encuentra el rango especificado");
								}catch(Exception ex){
									log.debug("Error en el proceso de obtención de ciclo");
								}
							}
							
							
							
							LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
							LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(localidadKey);
							
							tr601e.setCityType(localidadLocal.getTipo_loc());
												
							SolicitudAltamiraSTBMQ soliAltamira = new SolicitudAltamiraSTBMQ();
							soliAltamira.enviarMensaje(tr601e);
							act.setObservacion("Se envió a ALTAMIRA " + atiempoServiceName);
							break recorrePS;
					}else{
						log.debug("No se envía TR601e para petiNumero=" + petiNumero + " familia =" + producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue() 
								+ " operacion comercial=" + producto_servicio_peticionLocal.getIdOperacionComercial().longValue() );
					}
				}
			} catch(Exception e) {
				String s = "Error al popular y enviar TR601e petiNumero=" + petiNumero + " atiempoServiceName=" + atiempoServiceName;
				log.error(s, e);
				act.setObservacion("Error al enviar a ALTAMIRA ["+atiempoServiceName+"] (" + e.getMessage() +")");
				throw new ATiempoAppEx(s, e);
			}
		}

		
		
		
		private boolean altamiraInhibirEnvioTr601(ActividadEJBDTO act, SenderTr601Base sender) throws ATiempoAppEx {
		Long petiNumero = act.getNumeroPeticion();
		
		try {
			if (sender.inhibirEnvio()) {
				act.setRealizarTerminoInmediato(true);
				// TODO: Verificar con algun ejemplo si va mensaje en la bitacora
				act.setObservacion(sender.getMensajeInhibicion());
				return true;
			}
						
			return false;
		} catch (Exception e) {
			String s = "Error verificar inhibicion TR601e petiNumero=" + petiNumero;
			log.error(s, e);
			act.setObservacion("Error al verificar inhibicion TR601e (" + e.getMessage() +")");
			throw new ATiempoAppEx(s, e);
		}
	}
//	 CR20948 - Altamira - guido - 11/May/2009 - FIN
	
//	 CR20948 - Altamira - adocarmo - 20/Abr/2009 - INICIO
	public void altamiraRecepcionTr601(TR601S tr601s) throws ATiempoAppEx {

		// @Trace
		BackendExecution bExecution = null;		
		PeticionKey peticionKey = null;
		
		try	{
			Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			
			bExecution = BackendTraceUtil.initExecution(tr601s, log);
			bExecution.setIdMensajeOp(tr601s.getId());
			bExecution.setNumPetAtiempo(tr601s.getAtiempoRequestNumber());
			bExecution.startOperation();
			
			Mensaje_estadoLocalHome mensajeEstadoLocalHome  = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
			boolean requestInProgress = false;
			String stage = tr601s.getRequestStage();
			if (stage != null && stage.equalsIgnoreCase("IN_PROGRESS")) {
				requestInProgress = true;
			}
			// Respuesta OK
			Mensaje_estadoLocal mensajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			// Esperando respuesta sincrona (primera respuesta)
			Mensaje_estadoLocal mensajeEspera=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera)));
			// Esperando respuesta asincrona (segunda respuesta)
			//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
			//Mensaje_estadoLocal mensajeACK=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoACK)));
			
			// Respuesta Error
			Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr601s.getId()));
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
			
			try   {
				mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
			} catch (FinderException fex) {
				mensajeEstadoLineaLocal = null ;
			}
			if (mensajeEstadoLineaLocal == null) {
				log.debug("altamiraRecepcionTr601() No Existe Respuesta en la base de enviados\n "
						+ XMLUtilities.marshall(tr601s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados para msg id: "+tr601s.getId(),ATiempoAppEx.EXCEPTION);
			}
			Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
			int estadoMensaje = mensaje_estadoKey.cod_estado.intValue();
			// Si no estoy esperando ninguna respuesta termino
			//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
			//if (!((estadoMensaje == estadoEspera) || (estadoMensaje == estadoACK))) {
			if (estadoMensaje != estadoEspera) {
				log.debug("El estado de la respuesta es diferente para ser procesado ("+mensaje_estadoKey.cod_estado+")\n "	);
				return;
			}
			peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoLineaLocal.getCod_actividad_generadora());		
			
			String msgError = ( tr601s.getErrorMessage() != null ? tr601s.getErrorMessage() : "");    
			// ******* Esperando primera respuesta - Altamira request IN_PROGRESS ******* 
			//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
			//if (estadoMensaje == estadoEspera) {
			if (requestInProgress) {
				// Error en primera respuesta
				if (tr601s.getErrorCode() != 0) { // AT-2408 - Por aqui ya no deberia entrar, no puede darse que halla un error y que el request siga in progress. 
					//marco quiebre????????????????????
					//seteo en las obs de la bitacora el nro de actuacion y el nombre del tuxedo
					actDto.setObservacion(" Error en respuesta SINCRONA del Servicio Tuxedo "+ tr601s.getAltamiraServiceName() + " - " + msgError + ". Nro de actuacion: " + tr601s.getActionSecuence());
					//seteo estado del mensaje en estadoError
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					//derivo a aprovisionamiento red inteligente
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int, ComunInterfaces.WF_VALUE_ALTAMIRA_APROVISIONAMIENTO_RI);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					actividadEJB.terminar(actDto);
				} else {
					//seteo en las obs de la bitacora el nro de actuacion y el nombre del tuxedo
					actDto.setObservacion(" Invocación correcta al Servicio Tuxedo "+ tr601s.getAltamiraServiceName() + ". Nro de actuacion: " + tr601s.getActionSecuence());
					actualizaObsEnBitacoraSinTerminar(actDto,false);			
					//seteo estado del mensaje en estadoACK
					//AT-2408 - YA NO SE USA ESTE ESTADO - mensajeEstadoLineaLocal.setF_reference_14(mensajeACK);					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int,"X");					
				}
			} else {// ******* Esperando segunda respuesta - Altamira request CLOSED ******* 
			//AT-2408 - guido - Corrección por concurrencia en recepecion ambas respuestas 601 - 16/Jul/2009
			//if (estadoMensaje == estadoACK) {
				// Error en segunda respuesta
				if (tr601s.getErrorCode() != 0) {
					//marco quiebre??????????????????
					//seteo en las obs de la bitacora el nro de actuacion y el nombre del tuxedo
					//AT-2408 - A partir de la correccion, no hay forma de distinguir el error de que tipo de respuesta es
					//actDto.setObservacion(" Error en respuesta ASINCRONA del Servicio Tuxedo "+ tr601s.getAltamiraServiceName() + " - " + msgError + ". Nro de actuacion: " + tr601s.getActionSecuence());
					actDto.setObservacion(" Error en la invocación o ejecución de Sevicio Tuxedo: "+ tr601s.getAltamiraServiceName() + " - " + msgError + ". Nro de actuacion: " + tr601s.getActionSecuence());
					//seteo estado del mensaje en estadoError					
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					//derivo a aprovisionamiento red inteligente. ojo cambiar segun cual es la act padre
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int, ComunInterfaces.WF_VALUE_ALTAMIRA_APROVISIONAMIENTO_RI);
				} else {
					//seteo en las obs de la bitacora el nro de actuacion y el nombre del tuxedo
					actDto.setObservacion(" Ejecución correcta del Servicio Tuxedo "+ tr601s.getAltamiraServiceName() + ". Nro de actuación: " + tr601s.getActionSecuence());
					//seteo estado del mensaje en estadoOK
					mensajeEstadoLineaLocal.setF_reference_14(mensajeOkLocal);
					//derivo a aprovisionamiento red inteligente. ojo cambiar segun cual es la act padre
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int, "X");

					// CR 27638 - German P.
					// Se obtiene el codigo secreto si la actividad es alta_codigo_secreto
					if (actDto.getCodigoActividad().equals(ComunInterfaces.COD_ALTA_CLAVE_SECRETA)){
						try {
							Recursos_linea_basicaLocal rLocal = recursos_linea_basicaLocalHome.findByPeticion( peticionKey.peti_numero );
							rLocal.setSecret_code(tr601s.getSecretCode());
						} catch (FinderException e1) {
							log.debug("Se crea registro en Recursos Linea Basica para la peticion: " + peticionKey.peti_numero + ". " + e1.getMessage());
							Recursos_linea_basicaLocal rLocal = recursos_linea_basicaLocalHome.create( peticionKey.peti_numero );
							rLocal.setSecret_code(tr601s.getSecretCode());
						}
					}
					
				}
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				actividadEJB.terminar(actDto);
			}					
		} catch(Exception e) {
			// NullPointer AT-2288 - guido - 4/Jun/2009
			String identificador = null;
			if (peticionKey != null) {
				identificador = "peti_numero=" + peticionKey.peti_numero;
			} else {
				identificador = "msgId=" + tr601s.getId();
			}
			String s = "Error en recepcion de la respuesta de Altamira " + identificador;			
			log.error(s, e);
			throw new ATiempoAppEx(s, e);
		 } finally{  
			bExecution.endAll();
			// CR15338 - @Trace  FIN 
		}
	}

	/**
	 * @param actDto
	 */
	private void actualizaObsEnBitacoraSinTerminar(ActividadEJBDTO actDto, boolean overwriteObs) {
		BitacoraLocalHome creadorBitacoraT;
		try {
			creadorBitacoraT = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
			BitacoraLocal bpT;
			bpT = creadorBitacoraT.create();
			log.debug("Agrego Observacion Bitacora Actividad:" + actDto.getCodigoActividad() + ":" + actDto.getNumeroPeticion());
			boolean resultGrabaBita = bpT.actualizaObservacionBitacora(actDto.getNumeroPeticion(), actDto.getActividadBD().getIdActividad(), actDto.getObservacion(),overwriteObs);
		} catch (NamingException e1) {
			log.error("Error al actualizar observacion en Bitacora",e1);
			e1.printStackTrace();
		}catch (CreateException e2) {
			log.error("Error al actualizar observacion en Bitacora",e2);
			e2.printStackTrace();
		}
	}	
		//AT-2068 - devuelve true en caso de que la Familia sea de PcLinea y haya reinstalacion - 14 abril 2009
		private boolean tieneReinstalacion(Long nroPet) throws FinderException, NamingException
		{
				boolean tieneLB=false;
				boolean tieneReinstalacion=false;
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(nroPet));
				for(Iterator iterator2=local.getProducto_servicio_peticion().iterator();iterator2.hasNext();)
				{
					Producto_servicio_peticionLocal local2=(Producto_servicio_peticionLocal) iterator2.next();
					Producto_servicioLocal ps=local2.getProducto_servicio();
					Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) ps.getFamilia_producto_servicio().getPrimaryKey();
					Operacion_comercialLocal opco=local2.getOperacion_comercial();
					int idFamiliaPs=familia_producto_servicioKey.faps_id.intValue();
					
									
					if(idFamiliaPs==familiaPcLinea || idFamiliaPs==familiaIP )
					{	 log.debug("tieneReinstalacion opco.getOpco_tras()= "+opco.getOpco_tras()+" .. opco.getOpco_tipo()= "+opco.getOpco_tipo());
						 if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
							tieneReinstalacion=true;
					}
				}
				return tieneReinstalacion;
			}

		// CRXXX - adocarmo - inicio
		public void enviarRecursoGraniteSTB(Long numeroPeticion, String idActividad, Integer idActividadFlujo)throws ATiempoAppEx {
			try{

				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				TR510E tr510e = new TR510E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				boolean pasaPorActualizarInventario = false;
				boolean tienePCNaked = false;
				boolean tieneVOIPNaked = false;
				Long ps_Naked = null;
				
				PeticionKey peticionKey = new PeticionKey(numeroPeticion);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				Caracteristicas_lineaLocalHome caracteristicasLocalHome = (Caracteristicas_lineaLocalHome)HomeFactory.getHome(Caracteristicas_lineaLocalHome.JNDI_NAME);
				
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				Long ps_id=null;
				
				SpecialServicesRequest srs = null;
				ArrayList psServiciosSuplementarios = new ArrayList();		
				int opComercial = 0;

				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();

				/*
				 * GESTION DE BAJAS
				 */
				if(peticionLocal.getTica_id().equals("B")){
					tr510e.setInterfaz("BajaSIRSAsig");
				}else if(peticionLocal.getTica_id().equals("T")){
					tr510e.setInterfaz("TransferenciaSIRSAsig");
				}else{
					tr510e.setInterfaz("AltaSIRSAsig");
				}
				
				tr510e.setSource("ATIEMPO");
				tr510e.setVersion("1.0");
				tr510e.setDestination("ESB");
				
				//TODO: 10022010 - ajuste para corrección debe enviar solo los ps con familia 1 en los servicios especiales
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					
					opComercial = operacion_comercialKey.opco_id.intValue();
					
					Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					
					if(peticionesDelegate.pasaPSyOcXActividad(numeroPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo)){
						//Se agrega la nueva familia PC / PS NAKED para poder generar la TR510E
						if(fPSK.faps_id.intValue() == ComunInterfaces.familiaPcBANaked){
							tienePCNaked = true;
							//ps_Naked = productoServicoKey.ps_id;
						}
						if(fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							srs = new SpecialServicesRequest();
							srs.setSpecialService(productoServicoKey.ps_id.toString());
							srs.setAction(opComercial);	
							psServiciosSuplementarios.add(srs);
							tieneVOIPNaked = true;
							log.debug("Tiene PC de VOIP NAKED");
						}
							
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue()
								|| fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							ps_id=productoServicoKey.ps_id;
							break;
						}										
					} 
					// CRXXX - adocarmo - inicio
					else {
						pasaPorActualizarInventario = false;
						//Se agrega esta validación para incluir los ps que llaman la actividad de actualizar inventario
						if(peticionesDelegate.pasaPSyOcXActividad(numeroPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,ComunInterfaces.idActividadFlujoActInventarioSTB)){
							pasaPorActualizarInventario = true;
						}						
						
						if (pasaPorActualizarInventario){
							if(producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == cambioNumero ){
								log.debug("El Serv Sup: " + productoServicoKey.ps_id.toString() + " - " + "no se envia a configurar ya que es un cambio de numero");
							}else{
								
								srs = new SpecialServicesRequest();
								if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
									
									Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
									if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError){
										log.debug("El Serv Sup: " + productoServicoKey.ps_id.toString() + " - " + "no se envia a configurar ya que esta Cancelado");
										srs=null;
									}else{											
										srs.setSpecialService(productoServicoKey.ps_id.toString());
										srs.setAction(opComercial);										
									}
									
								}else{
									srs.setSpecialService(productoServicoKey.ps_id.toString());
									srs.setAction(opComercial);					
									
								}
								if(fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked)
									ps_id=productoServicoKey.ps_id;
								
								if(srs!=null){
									if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaLinea).longValue()){
										psServiciosSuplementarios.add(srs);
										log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());	
									}
								}
							}
						}
					}						
					// CRXXX - adocarmo - fin	
					
				}
				
				if(ps_id==null && !tienePCNaked){
					log.debug("Ningun Ps tipo PC de la peticion: "+numeroPeticion+"  invoca la actividad Asignar Recurso para Granite. No se envia mensaje a APSC.");
					return;
				}else{
					if(tienePCNaked && !tieneVOIPNaked){
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VIRTUAL");
						ps_id = new Long(propertiesDBLocal.getValor());
						srs = new SpecialServicesRequest();
						srs.setSpecialService(ps_id.toString());
						srs.setAction(opComercial);	
						psServiciosSuplementarios.add(srs);
						log.debug("Tiene PC de NAKED");
					}
				}
				//End TODO
				
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				
				// Se modifica el objeto TR510E, para ser enviado
				
				log.debug( "Cant de Servicios Suplementarios: " + psServiciosSuplementarios.size());
				
				tr510e.setSpecialServices(psServiciosSuplementarios);
				
				tr510e.setId(IdCorrelativo.toString());
				tr510e.setPathType(peticionLocal.getDir_tip_via_1());
				tr510e.setPathNumber(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_1())));
				tr510e.setFirstPathCharacters(peticionLocal.getDir_lt1_via_1());
				tr510e.setSecondPathCharacters(peticionLocal.getDir_lt2_via_1());
				tr510e.setPathZone(peticionLocal.getDir_zon_via_1().toCharArray()[0]);
				tr510e.setPathType2(peticionLocal.getDir_tip_via_2());
				tr510e.setPathNumber2(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_2())));
				tr510e.setFirstPathCharacters2(peticionLocal.getDir_lt1_via_2());
				tr510e.setSecondPathCharacters2(peticionLocal.getDir_lt2_via_2());
				tr510e.setAtiempoRequestNumber(peticionKey.peti_numero);
				tr510e.setUseType(producto_servicio_peticionLocal.getCod_tip_uso().longValue());
//				tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
//				tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				
				//Requerimiento Naked: Se calcula la caracteristica y subcaracteristica de la tabla Caracteristicas_linea: CR7
				try {
					Caracteristicas_lineaKey key = new Caracteristicas_lineaKey(ps_id);
					Caracteristicas_lineaLocal caracteristicasLocal = caracteristicasLocalHome.findByPrimaryKey(key);
					if(caracteristicasLocal.getCaracteristica() != null && caracteristicasLocal.getSub_caracteristica() != null){
						tr510e.setComercialProductType(caracteristicasLocal.getCaracteristica());
						tr510e.setComercialProductSubType(caracteristicasLocal.getSub_caracteristica());
					}else{
						tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
						tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
					}
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
//					Requerimiento Naked: Si no encuentra registro en la tabla se deja el valor que se trae de ATIS: CR7
					log.debug("No se encuetra el PS registrado en la tabla Caracteristica_Linea se setea el valor que llega de ATIS");
					tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
					tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				}
					
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();

				//CR-7390 - Yumbleiner - Linea Precableada
				String tmpCarac;
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
				{
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					tmpCarac = obtenerCaracteristicaPeticion(producto_servicio_peticionLocal, new Long (VpistbbaConfig.getVariable("CODPROJECT")));
					if (tmpCarac == null){
						tr510e.setProjectCode("");
					}else{
						tr510e.setProjectCode(tmpCarac);
						break;
					}
				}
				
				
				try{
					if(peticionLocal.getDir_zon_via_2()!=null && !peticionLocal.getDir_zon_via_2().trim().equals("")) {
						tr510e.setPathZone2(peticionLocal.getDir_zon_via_2().charAt(0));
					}else{
						tr510e.setPathZone2('-');
					}
					
				}
				catch(Exception e){
					log.info("El valor de Dir Zon Via 2 no es valido:"+peticionLocal.getDir_zon_via_2());
				}

				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				
				//Cambio realizado el 180507 : : Se envia al APSC el Numero de Peticion en ves del Numero Atis
				tr510e.setAtisRequestNumber(peticion_atisKey.cod_pet_cd.longValue());
				
				//Para la asignacion de recursos el numero debe ir blanco, pues todavia no se me asigna un numero.
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}	
				tr510e.setPhoneNumber(Integer.parseInt(phoneNumber));
						
				String nombre = Utiles.sinNull(peticionLocal.getNom_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getPri_ape_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getSeg_ape_ds().trim(),"");
				
				if (nombre!=null && ! nombre.trim().equals ("")){
					if(nombre.length()>40)
						nombre=nombre.substring(0,39);
					tr510e.setClientName(nombre);
				}else{
					tr510e.setClientName("-");
				}
				
				
				String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
				
				if (clientDocument!=null && ! clientDocument.trim().equals ("")){
					if (clientDocument.length()>20){
						clientDocument=clientDocument.substring(0,20);
					}
				}
				else{
					clientDocument="-";
				}
				
				tr510e.setClientDocument(clientDocument);
				
				tr510e.setProductServiceCode(ps_id.longValue());
				long opCoId = operacion_comercialKey.opco_id.longValue();
				tr510e.setComercialOperation(opCoId);
				
				String installAddress = Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(peticionLocal.getNom_slo_no(),"");
				if(installAddress==null || installAddress.trim().equals("")) {
					installAddress="-";
				}
				tr510e.setInstallAddress(installAddress);
				
				String address = Utiles.sinNull(peticionLocal.getNum_cal_nu(),"");
				String addressChar = "";
				String addressNumber = "";
				
				try{

					if (address!=null && !address.trim().equals("")){
						addressNumber= (String) Utiles.getSacarLetraNumero(address).get("numero");
						addressChar = (String) Utiles.getSacarLetraNumero(address).get("letra");
					}
					else{
						addressNumber="0";
						addressChar = "-";
					}			
					
					tr510e.setAddressCharacters(addressChar.charAt(0));
					if(addressNumber!=null && addressNumber.length()>3)
						addressNumber=addressNumber.substring(0,3);
					
					try{
						Integer validador = new Integer(addressNumber);
					}catch(NumberFormatException nm){
						addressNumber = "0";
					}
					
					try{
						tr510e.setAddressNumber(addressNumber);
					}catch(NumberFormatException nm){
						tr510e.setAddressNumber("0");
					}
				}catch(Exception e){
					
					log.info("El valor de Num cal nu no es valido:"+peticionLocal.getDir_zon_via_2());
				}

				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr510e.setDepartment(Integer.parseInt(departamentoKey.cod_dpt));
				
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr510e.setCity(Integer.parseInt(localidadKey.cod_loc));

				                                           
				tr510e.setPlaceNumber1(peticionLocal.getDir_nro_lg1());
				tr510e.setPlaceNumber2(peticionLocal.getDir_nro_lg2());
				tr510e.setPlaceNumber3(peticionLocal.getDir_nro_lg3());
				tr510e.setPlaceType1(peticionLocal.getDir_tip_lg1());
				tr510e.setPlaceType2(peticionLocal.getDir_tip_lg2());
				tr510e.setPlaceType3(peticionLocal.getDir_tip_lg3());
				
				tr510e.setZone(0);
				//tr510e.setUseType(0);
				//AT-1892 -- No se enviaba sub-city -- Pablo L
				tr510e.setSubCity(peticionLocal.getNom_slo_no());
				tr510e.setSpaceNumber(0);
				tr510e.setSector(0);
				/*Pablo L - Granite*/
				int cantPBX = cantPbx(peticionLocal , numeroPeticion);
				tr510e.setRequestPbx(cantPBX);
				/*PBX - Pablo L - Granite*/
				//tr510e.setRequestPbx(0);
				//AT-1956 13 marzo 2009
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				//tr510e.setRequestDateString(petAtis.getFec_sct_pet_ff().toString());
				tr510e.setRequestDate(petAtis.getFec_sct_pet_ff());
				
				
				
				
//				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
//				PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(numeroPeticion);
//				InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
				//Juan AT-2153 este valor solo se manda para PBX y troncales estabamos obteniendo mal el valor
//				int previousPhone = peticionLocal.getpreviousServicePhone();				
//				tr510e.setPreviousPhoneNumber(previousPhone);
				int previousPhone = Integer.parseInt(retornarNumeroPBX(numeroPeticion));	
				
				//if (peticionDTO.getNroPiloto()!= null && !peticionDTO.getNroPiloto().equalsIgnoreCase("") ){
					//previousPhone=Integer.parseInt(peticionDTO.getNroPiloto());
				//}
				
				
				if(tieneReinstalacion(numeroPeticion)){
					log.debug("Tiene Reinstalacion | nro Peticion:.."+numeroPeticion+" previousPhone:.. "+previousPhone);
					tr510e.setPreviousPhoneNumber(Integer.parseInt(phoneNumber));
				}else{
					log.debug("NO Tiene Reinstalacion | nro Peticion:.."+numeroPeticion+" previousPhone:.. "+previousPhone);
					tr510e.setPreviousPhoneNumber(previousPhone);
				}
				
				tr510e.setEnhancementNumber(0);
				tr510e.setBlockSide("");
				tr510e.setBlock(0);
				tr510e.setNumberClient(petAtis.getCod_cli_cd()); // AT-2089 -- Pablo L -- 16/04/2009
				tr510e.setProductServiceFamily(peticionLocal.getPeti_id_instancia());
				//CR 25393 - Gustavo
				Integer ods =Integer.valueOf("0");
				try {
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					Recursos_linea_basicaLocal rlbLocal = recursos_linea_basicaLocalHome.findByPeticion( numeroPeticion );
					
					if(rlbLocal!=null && rlbLocal.getOds_apsc()!=null){
						ods = Integer.valueOf(rlbLocal.getOds_apsc().toString());
						
					}
					else{
						
						log.debug("La petición [" + numeroPeticion +"] no tiene ODS.  Se envía '0'");
					}
				} catch (FinderException e1) {
					
				}
				tr510e.setOdsNumber(ods);
				//Fin CR 25393
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				
				int areaPhone= 0;
				int numeroPhone= 0;
				if (phoneNumber.length()>1){
					areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
					numeroPhone=Integer.parseInt(phoneNumber.substring(1));
				}
				mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
				mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
				
				AsignacionRecursosGraniteSTBMQ asignacionRecursosGraniteSTBMQ = new AsignacionRecursosGraniteSTBMQ();
				asignacionRecursosGraniteSTBMQ.enviarMensaje(tr510e);
				
					
				} catch (NumberFormatException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				} catch (CreateException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
				} catch (FinderException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				} catch (NamingException e) {
					log.error("Creacion de Local Home Null",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
				}
				catch(Exception e)
				{
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
			
		}		
		// CRXXX - adocarmo - fin
		
		
		public int cantPbx(PeticionLocal peticionLocal , Long numeroPeticion){
			int cantPBX = 0;
			
			Properties_BDLocalHome propertiesBDLocalHome = null;
			Properties_BDLocal propertiesDBLocal = null;
			try {
				propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("OPCO_ID_BAJA_PBX");
			} catch (FinderException e) {
				log.error("No se encuentra en la tabla PROPERTIES_BD la propiedad OPCO_ID_BAJA_PBX. ",e);
			} catch (NamingException e) {
				log.error("Error al instaciar EJB de Properties_BD. ",e);
			}
			Collection opco = new ArrayList();
			if(propertiesDBLocal != null && !propertiesDBLocal.equals("")){
				String opcoBaja = propertiesDBLocal.getValor();
				String[] opcoBajaArray = opcoBaja.split(",");
				for (int i = 0; i < opcoBajaArray.length; i++) {
					opco.add(new Long(opcoBajaArray[i].toString()));
				}
			}
			
			Peticion_atisLocal peticion_atisLocal = peticionLocal.getFk_01_pet_atis();
			Long sbt_pro_cmr_cd = null;
			Long opcoId = null;
			for(Iterator iterator2 = peticion_atisLocal.getPeticion().iterator();iterator2.hasNext();)
			{
				PeticionLocal pLocal=(PeticionLocal) iterator2.next();
				PeticionKey key=(PeticionKey) pLocal.getPrimaryKey();
				Agrupacion_atisLocal agrupacion_atisLocal = null;
				Producto_servicio_peticionLocal pp=null;
				for(Iterator iterator3=peticionLocal.getProducto_servicio_peticion().iterator();iterator3.hasNext();){
					pp=(Producto_servicio_peticionLocal) iterator3.next();
					agrupacion_atisLocal = pp.getFk_01_subp_atis().getFk_agru_sub();
					Subpeticion_atisLocal subpeticionAtis = pp.getFk_01_subp_atis();
					opcoId = subpeticionAtis.getCod_opr_cmr_cd();
					if(agrupacion_atisLocal != null 
							&& agrupacion_atisLocal.getTip_pro_cmr_cd().intValue()==tipoPBX
							&& !opco.contains(opcoId)){
						sbt_pro_cmr_cd = agrupacion_atisLocal.getSbt_pro_cmr_cd();
						opcoId = agrupacion_atisLocal.getTip_opr_cmr_cd();
						if((sbt_pro_cmr_cd.intValue()==subTipoPbxPilotoNR || sbt_pro_cmr_cd.intValue()==subTipoPbxPilotoAutoConsumo 
								||sbt_pro_cmr_cd.intValue()==subTipoPbxTroncalNR ||sbt_pro_cmr_cd.intValue()==subTipoPbxTroncalAutoconsumo)){
							//Aqui cuento el numero de lineas PBX
							log.info("Estoy en PBX");
							cantPBX++;
							continue;
						}
					}
				}
			}
			log.info("cantidad de PBX: "+ cantPBX);
			return cantPBX;
			
			
		}

		public String retornarNumeroPBX( Long numeroPeticion) throws ATiempoAppEx{
			String cantPBX= "0";
			try {
				
				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
				PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(numeroPeticion);
				InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
				
				String nroPiloto = peticionDTO.getNroPiloto();
				if (nroPiloto!= null && !nroPiloto.equalsIgnoreCase("") ){
					cantPBX=nroPiloto;
					log.info("numero de  PBX Piloto: "+ cantPBX);
					if("".equalsIgnoreCase(cantPBX)){
						cantPBX= "0";
					}
					return cantPBX;
				}
				
				
				PeticionKey peticionKey = new PeticionKey(numeroPeticion);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				Peticion_atisLocal peticion_atisLocal = peticionLocal.getFk_01_pet_atis();
				int intValue = 0;
				for(Iterator iterator2 = peticion_atisLocal.getPeticion().iterator();iterator2.hasNext();)
				{
					PeticionLocal pLocal=(PeticionLocal) iterator2.next();
					PeticionKey key=(PeticionKey) pLocal.getPrimaryKey();
					Agrupacion_atisLocal agrupacion_atisLocal = null;
					Producto_servicio_peticionLocal pp=null;
					for(Iterator iterator3=peticionLocal.getProducto_servicio_peticion().iterator();iterator3.hasNext();){
							pp=(Producto_servicio_peticionLocal) iterator3.next();
							agrupacion_atisLocal = pp.getFk_01_subp_atis().getFk_agru_sub();
									
					
							if(agrupacion_atisLocal != null && agrupacion_atisLocal.getTip_pro_cmr_cd().intValue()==tipoPBX){
							
								intValue = agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue();
								if(intValue==subTipoPbxPilotoNR || intValue==subTipoPbxPilotoAutoConsumo ||intValue==subTipoPbxTroncalNR ||intValue==subTipoPbxTroncalAutoconsumo){
									//Aqui tengo el Piloto Padre
									log.info("Estoy en piloto Padre");
									cantPBX=agrupacion_atisLocal.getFath_iden_line();
									log.info("Estoy en piloto Padre numero "+cantPBX);
									break;
								}
								
							}
					}
				}
				log.info("numero de  PBX: "+ cantPBX);
			} catch (EJBException e) {
				throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION, e);
			} catch (FinderException e) {
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			} catch (NamingException e){
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			} catch (ATiempoAppEx e) {
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}
			if("".equalsIgnoreCase(cantPBX)){
				cantPBX= "0";
			}
			return cantPBX;
			
			
		}
		/* 
		 * Asignar Recursos para Granite - CR-14657 - Granite - agonz - 16/10/2008
		 */

	public void asignarRecursoGraniteSTB(TR510S tr510s) throws ATiempoAppEx {
			
//			 CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;
				
			log.debug("ENTRO A ASIGNACION AUTOMATICA RECURSOS GRANITE STB");
			
			try
			{
				bExecution = BackendTraceUtil.initExecution(tr510s, log);
				bExecution.setIdMensajeOp(tr510s.getId());
				bExecution.setNumPetAtiempo(new Long(tr510s.getAtiempoRequestNumber()));
				bExecution.startOperation();
				
				Fecha fecha=new Fecha();
				
				String codAct = "";
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr510s.getId()));
			    Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
		            
			   try
			   {
			   		Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
			   }
			   catch (FinderException fex)
			   {
					mensajeEstadoLineaLocal = null ;
			   }
			   
				
				// Validacion de existencia de la respuesta en la Base de Datos 
				// en la tabla Mensaje_Estado_Linea
				 
				if (mensajeEstadoLineaLocal == null) {
					log.debug(
						"No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr510s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr510s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug(
						"Es estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr510s));
					return;
				}
				
				//Obtengo el codigo de la actividad generadora
				codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();

				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
				
				
				if ( recursosLineaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos);
				}else{
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}			
				
				recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
				recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);
				
				String msgErrorODSCentral = "";
				//Si no vine la ods o si no viene la central... no se guarda la ODS. Esto permite reintentar.
				if(tr510s.getOdsNumber()>0 ){

					recursos_linea_basicaLocal.setOds_apsc(new Long(tr510s.getOdsNumber()));
					
				}else{
					
					if (tr510s.getOdsNumber() < 1){
						msgErrorODSCentral=msgErrorODSCentral + ".Incorrecta ODS=" + tr510s.getOdsNumber();
					}
				}
				
				if (tr510s.getCentralCode() < 1){
					msgErrorODSCentral=msgErrorODSCentral + ".Incorrecta Central=" + tr510s.getCentralCode();
				}
					
				//Recupero peticion
				PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
				
				//Req. 13146 - Jesus Carvajal - 28/05/2012
				//Actualizar coordenadas en la tabla peticion dadas por SIRS 
				if (peticionLocal.getCoord_x_agnd_sc() == null || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0.0") 
						|| peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("") || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0")){
					peticionLocal.setCoord_x_agnd_sc(tr510s.getLongitude().toString());
					peticionLocal.setCoord_y_agnd_sc(tr510s.getLatitude().toString());
					recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr510s.getLongitude().toString()));
					recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr510s.getLatitude().toString()));
					log.debug("Se actualizo longitud y latitud en tabla peticion y tabla rlb");
				}else{
					recursos_linea_basicaLocal.setLongitud(new BigDecimal(peticionLocal.getCoord_x_agnd_sc()));
					recursos_linea_basicaLocal.setLatitud(new BigDecimal(peticionLocal.getCoord_y_agnd_sc()));
					log.debug("Ya existe longitud y latitud en tabla peticion y se actualiza tabla rlb");
				}
				//Fin Req. 13146
				
				// Si la respuesta es incorrecta o la central = 0 se va a la asginacion manual
				
				if (tr510s.getResult() != 1 && tr510s.getErrorCode() == 0) {
					log.debug("Inicio de Condiciones de Actividad");
					
					if (mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA"))){
						if (tr510s.getResult() == 2){
							log.debug("Entramos a la Generacion de Actividades de Gestion Asignacion Cod.Actividad : " + codAct);
							mensajeEstadoLineaLocal.setF_reference_14(mensajeManualLocal);
							mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
							mensajeEstadoLineaLocal.setCod_actividad_generadora(codAct);	
							
							ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
							IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
							ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
							
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "S");
							actDto.setObservacion("Error en la Asignacion Manual de Recursos.");

							//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));				
							//No va al cierre directo por lo tanto no le asigno causas
							actividadEJB.terminar(actDto);
							return;
						}
					}

				}else if (tr510s.getErrorCode() != 0 || tr510s.isReserveResult() == false || tr510s.getCentralCode() < 1 || tr510s.getOdsNumber() < 1){
					
					//TODO: 11022010 - RETA - Ajuste para redireccionar a PGI
					if (tr510s.getResult() == 3){
						log.debug("Entramos a la Generacion de Actividades de Cierre Cod.Actividad : " + codAct);
						mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
						mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						mensajeEstadoLineaLocal.setCod_actividad_generadora(codAct);		
						
						ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
						ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
						
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.gestion_recursos_stb, "N");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						
						actDto.setObservacion("Error en la Asignacion de Recursos. " + tr510s.getErrorMessage() + msgErrorODSCentral);
																	
						//recursos_linea_basicaLocal.setCna(String.valueOf(tr510s.getCna()));
						//recursos_linea_basicaLocal.setTiene_cna("S");
						actividadEJB.terminar(actDto);
						return;
					}else{
						if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
								if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
									log.debug("La actividad corresponde a TOA");
									TOADelegate toaDelegate = new TOADelegate();
									toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
								}else{
									enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
								}
							
								return; 
						}
							
						mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
						mensajeEstadoLineaLocal.setDesc_error(tr510s.getErrorMessage());

						log.debug("Entramos a la Generacionde Actividades de Asignacion Manual Cod.Actividad : " + codAct);
						mensajeEstadoLineaLocal.setF_reference_14(mensajeManualLocal);

						ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
						ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
						// Si se usa este conector en la actvidad de Puntos de red BA es porque es una consulta.
						//AT-2016 cuando el error es 10003 se debe ir a PGI
						if (VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct) || (tr510s.getErrorCode()==10003)){
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");//Defecto de loop de Asignacion de Recursos STB
							insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
							actDto.setObservacion("Error en la Consulta de Recursos." + tr510s.getErrorMessage() + msgErrorODSCentral);
						}else{
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");
							actDto.setObservacion("Error en la Asignacion de Recursos. " + tr510s.getErrorMessage() + msgErrorODSCentral);
						}
							
						actividadEJB.terminar(actDto);
			 
						return;
					}
					//End TODO
				}
				
				//Asigno Central
				CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(new CentralKey(new Long(tr510s.getCentralCode())));
				peticionLocal.setFk_03_central(centralLocal);
						
				peticionLocal.setNum_ide_nu_stb(String.valueOf(tr510s.getPhoneNumber()));
				//***
				boolean esTraslado = tieneTrasladoLB(new Long (tr510s.getAtiempoRequestNumber()));
				boolean esCambioNumero = tieneCambioNumeroLB(new Long (tr510s.getAtiempoRequestNumber()));
				boolean esAltaComun = tieneAltaComun(new Long (tr510s.getAtiempoRequestNumber()));
				boolean esBajaComun = tieneBajaComun(new Long (tr510s.getAtiempoRequestNumber()));
				//@idrincon req 1038 - 29/10/2010
				boolean cambioTelefono = false;
				String telefonoNew = String.valueOf(tr510s.getPhoneNumber());
				//fin 29/10/2010
				
				///***
				if (esTraslado||esCambioNumero||esAltaComun || esBajaComun){
					
					recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr510s.getSecundaryDistributor()));
					
					recursos_linea_basicaLocal.setArmario_asg(tr510s.getCloset());
					recursos_linea_basicaLocal.setCaja_asg(tr510s.getBox());
					recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr510s.getBoxPair()));
					recursos_linea_basicaLocal.setDir_caja(tr510s.getBoxAddress());
					recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr510s.getPrimaryDistributor()));//AT-2147 faltan mostrar datos 
					recursos_linea_basicaLocal.setListon_asg(tr510s.getStrip());
					recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr510s.getStripPair()));
					recursos_linea_basicaLocal.setCable(tr510s.getCable());
					recursos_linea_basicaLocal.setPar_cable(new Long(tr510s.getCablePair()));
					recursos_linea_basicaLocal.setCod_central(new Long(tr510s.getCentralCode()));
					recursos_linea_basicaLocal.setDesc_central(tr510s.getCentralDescription());
					
					recursos_linea_basicaLocal.setTelefono_asg(new Long(tr510s.getPhoneNumber()));
										
					//@idrincon req 1038 - 29/10/2010
					RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
					boolean esTrasladoba = recursosBADelegate.esTrasladoBa(mensajeEstadoLineaLocal.getPeti_numero());
					boolean esTrasladolb = tieneTrasladoLB(mensajeEstadoLineaLocal.getPeti_numero());
					boolean esTrasladotv = recursosBADelegate.esTrasladoTv(mensajeEstadoLineaLocal.getPeti_numero());
					boolean traslado = false;

					if( esTrasladoba || esTrasladolb || esTrasladotv ){
						traslado = true;
					}
					
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")) && traslado ){
						String telefonoOld = this.consultarTelefono(mensajeEstadoLineaLocal.getPeti_numero());
						recursos_linea_basicaLocal.setTelefono_asg(new Long(telefonoNew));
						if(telefonoOld != null ){
							if( telefonoNew.equalsIgnoreCase(telefonoOld)){
								cambioTelefono = true;
							}
						}else{
							log.debug("RecursosServiciosBean: asignarRecursoSTB: telefonoOld: "+telefonoOld+ " cambioTelefono: "+cambioTelefono);
						}
					}else{
						recursos_linea_basicaLocal.setTelefono_asg(new Long(tr510s.getPhoneNumber()));
					}
					//fin req 1038 -29/10/2010
					
					recursos_linea_basicaLocal.setLen(tr510s.getLen());
					recursos_linea_basicaLocal.setDir_distribuidor(tr510s.getDistributorAddress());
					recursos_linea_basicaLocal.setDir_armario(tr510s.getClosetAddress());
					
					// Visualizacion campo posicion horizontal - adocarmo - inicio
					recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr510s.getHorizontalPosition());
					// Visualizacion campo posicion horizontal - adocarmo - fin
					
					recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr510s.getSecundaryDistributorDescription());
					recursos_linea_basicaLocal.getPeticion().setFk_03_central(centralLocal);
					//Recursos Linea Antigua
					recursos_linea_basicaLocal.setDist_sec_ant(new Long(tr510s.getPreviousSecondaryDistributor()));
					recursos_linea_basicaLocal.setDesc_dist_sec_ant(tr510s.getPreviousSecondaryDistributorDescription());
					recursos_linea_basicaLocal.setArmario_ant(tr510s.getPreviousCloset());
					recursos_linea_basicaLocal.setCaja_asg_ant(tr510s.getPreviousBox());
					recursos_linea_basicaLocal.setPar_caja_ant(new Long(tr510s.getPreviousBoxPair()));
					recursos_linea_basicaLocal.setDist_prim_ant(new Long(tr510s.getPreviousPrimaryDistributor()));
					recursos_linea_basicaLocal.setListon_asg_ant(tr510s.getPreviousStrip());
					recursos_linea_basicaLocal.setPar_liston_ant(new Long(tr510s.getPreviousStripPair()));
					recursos_linea_basicaLocal.setCable_ant(tr510s.getPreviousCable());
					recursos_linea_basicaLocal.setPar_cable_ant(new Long(tr510s.getPreviousCablePair()));      
					recursos_linea_basicaLocal.setCentral_ant(new Long(tr510s.getPreviousCentralCode()));
					recursos_linea_basicaLocal.setDesc_central_ant(tr510s.getPreviousCentralDescription());
					recursos_linea_basicaLocal.setTelefono_ant(new Long(tr510s.getPreviousPhoneNumber()));
					recursos_linea_basicaLocal.setLen_anterior(tr510s.getPreviousLen());
					
					recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr510s.getLongitude().toString()));
					recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr510s.getLatitude().toString()));
				}else{
					
					recursos_linea_basicaLocal.setDist_sec_ant(new Long(tr510s.getSecundaryDistributor()));
					recursos_linea_basicaLocal.setDesc_dist_sec_ant(tr510s.getSecundaryDistributorDescription());
					recursos_linea_basicaLocal.setArmario_ant(tr510s.getCloset());
					recursos_linea_basicaLocal.setCaja_asg_ant(tr510s.getBox());
					recursos_linea_basicaLocal.setPar_caja_ant(new Long(tr510s.getBoxPair()));
					recursos_linea_basicaLocal.setDist_prim_ant(new Long(tr510s.getPrimaryDistributor()));
					recursos_linea_basicaLocal.setListon_asg_ant(tr510s.getStrip());
					recursos_linea_basicaLocal.setPar_liston_ant(new Long(tr510s.getStripPair()));
					recursos_linea_basicaLocal.setCable_ant(tr510s.getPreviousCable());
					recursos_linea_basicaLocal.setPar_cable_ant(new Long(tr510s.getCablePair()));      
					recursos_linea_basicaLocal.setCentral_ant(new Long(tr510s.getCentralCode()));
					recursos_linea_basicaLocal.setDesc_central_ant(tr510s.getCentralDescription());
					recursos_linea_basicaLocal.setTelefono_ant(new Long(tr510s.getPhoneNumber()));
					recursos_linea_basicaLocal.setLen_anterior(tr510s.getLen());
					
				}				
				recursos_linea_basicaLocal.setCaja_dedicado(tr510s.getBoxDedicated());
				recursos_linea_basicaLocal.setArmario_dedicado(tr510s.getClosetDedicated());
				recursos_linea_basicaLocal.setCable_dedicado(tr510s.getCableDedicated());
				recursos_linea_basicaLocal.setDir_armario_dedicado(tr510s.getClosetAddressDedicated());
				recursos_linea_basicaLocal.setDir_caja_dedicado(tr510s.getBoxAddressDedicated());
				recursos_linea_basicaLocal.setTipo_caja_dedicado(tr510s.getBoxTypeDedicated());
				recursos_linea_basicaLocal.setTipo_caja(tr510s.getBoxType());
				recursos_linea_basicaLocal.setPar_cable_dedicado(new Long(tr510s.getCablePairDedicated()));
				recursos_linea_basicaLocal.setPar_caja_dedicado(new Long(tr510s.getBoxPairDedicated()));
				recursos_linea_basicaLocal.setDistancia_caja(new Integer(tr510s.getBoxDistance()));

				
				// seteo si la central soporta configuración autmática
				if (tr510s.isEocEnable()) {
					recursos_linea_basicaLocal.setConfig_automatica(new Short((short)1));
				}else {
					recursos_linea_basicaLocal.setConfig_automatica(new Short((short)0));
				}

				if(tr510s.isCentralConnection()){
					recursos_linea_basicaLocal.setCentral_connection(Integer.valueOf("1"));
				}else{
					recursos_linea_basicaLocal.setCentral_connection(Integer.valueOf("0"));
				}

				if(tr510s.isDedicationPresence())
					recursos_linea_basicaLocal.setInd_dedicado(new Short("1"));
				else
					recursos_linea_basicaLocal.setInd_dedicado(new Short("0"));

				// datos red dedicada
				
				//Req 14209 - Jesus Carvajal - 19042012
				recursos_linea_basicaLocal.setFecha_asig_recurso(fecha.getTimestamp());
				//Fin Req 14209
				
				
				//AT-1995- agonz - 25 marzo 2009 
				recursos_linea_basicaLocal.setCod_central_dedicado(new Long(String.valueOf(tr510s.getCentralCodeDedicated())));

				//agonz AT-2171 - latitud_longitud no se ven en bandeja07/05/2009
				recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr510s.getLongitude().toString()));
				recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr510s.getLatitude().toString()));
			
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
							

//				@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
				//se realiza validacion para saber si algun campo FTTC contiene datos
				//se agrega nueva validacion 31/03/2014 cardena
				//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
				String accion = validaFTTC(tr510s.getFttcfab(), tr510s.getFttcmode() , tr510s.getFttcslot() ,
						tr510s.getFttcpwd() , tr510s.getFttcusu() , tr510s.getFttciplb(),codAct,mensajeEstadoLineaLocal);
				
				if(accion.equals("LB"))
				{	
					// este else espara saber si no trae equipos fttc
					log.debug("No Trae Equipos FTTC");
					if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
					{
						
					}else{
					recursos_linea_basicaLocal.setRec_fttc_asg("N");	
					}
				}else if(accion.equals("PGI")){	
					return;						
				}else if(accion.equals("FTTC")){
				log.debug("Trae Equipos FTTC-");
				//se instancia la clase del password FTTC
				int longitud=0;
									
				Password generarPass = new  Password();
			
	
			//se trae de la tabla propertiesBD la longitud del password
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
			recursos_linea_basicaLocal.setFttciplb_asg(tr510s.getFttciplb());
			recursos_linea_basicaLocal.setFttcpwd_asg(tr510s.getFttcpwd());
			recursos_linea_basicaLocal.setFttcusu_asg(tr510s.getFttcusu());
			recursos_linea_basicaLocal.setFttcslot_asg(tr510s.getFttcslot());
			recursos_linea_basicaLocal.setFttcfab_asg(tr510s.getFttcfab());
			recursos_linea_basicaLocal.setFttcmode_asg(tr510s.getFttcmode());
			//el campo que nos dice que hay equipos De tipo FTTC
			recursos_linea_basicaLocal.setRec_fttc_asg("S");
			recursos_linea_basicaLocal.setRec_fttc_ant("N");
			//se genera el password y se le envia la longitud
			longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
			recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
			log.debug(tr510s.getFttciplb());
			log.debug(tr510s.getFttcpwd());
			log.debug(tr510s.getFttcusu());
			log.debug(tr510s.getFttcslot());
			log.debug(tr510s.getFttcfab());
			log.debug(tr510s.getFttcmode());
			
			}
				// fin - requerimiento - requerimiento fttc 	
				
				//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
				
				recursos_linea_basicaLocal.setRack(tr510s.getRack().toString());
				recursos_linea_basicaLocal.setSubRack(tr510s.getSubRack().toString());	
				if ((tr510s.getDslams() == null) || (tr510s.getDslams().size()<1))
				{
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
						if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
							log.debug("La actividad corresponde a TOA");
							TOADelegate toaDelegate = new TOADelegate();
							toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
						}else{
							enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
						}
							return; 
					}
					//Si no viene Dslam y la consulta la invoca Puntos de red BA, entonces se deriva a PGI
					if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct)){
						ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
						ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
						//	agregado adecarlini	
						String codError = String.valueOf(tr510s.getErrorCode());
						String nombreClase = TR510S.class.getName();
						nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
						ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

						//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR010S.class.getName());
						if(errorLegado != null){
							peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
							insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, codAct, errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						}else{
							insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, codAct, new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());											
						}
					
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");//Defecto de loop de Asignacion de Recursos STB
						log.debug("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
						actDto.setObservacion("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
						actividadEJB.terminar(actDto);
						return;
					}
				}
				else{
					ArrayList listaZonasOld = new ArrayList();
					ArrayList listaZonasNew = new ArrayList();
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")))
					{
						listaZonasOld = this.consultaZonasAtendimiento(mensajeEstadoLineaLocal.getPeti_numero());
						
						log.debug("Tengo que borrar los Dslam que tenia antes");
						borrarDslams(recursos_linea_basicaLocal);
						borrarZonas(recursos_linea_basicaLocal);//correccion defecto 21331 
					}
					log.debug("Cantidad de Dslam " + tr510s.getDslams().size());
					
//					TODO - Inicio - gonzalez- 12/06/2008 - Zonas de Atendimiento
			
					Zonas_atendimientoLocal zona_atendimientoLocal=null;
					borrarZonas(recursos_linea_basicaLocal);
					
					for (Iterator iter = tr510s.getDslams().iterator(); iter.hasNext();){
						Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						if (dslam1 == null ){
							log.debug("Las Zonas vienen NULL");
						}else{
							log.debug("Las Zonas vienen OK");
						}
						Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
						if(zona_atendimientoLocal==null){
							//Se valida que es la primera zona 
							zona_atendimientoLocal=validarPSPromocion(peticionLocal,dslam1,recursos_linea_basicaLocal);	
						}else{
							zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
							zona_atendimientoLocal.setIp(dslam1.getIp());
							zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						}
					}

					
					if(mensajeEstadoLineaLocal.getAccion()!=null &&(mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
						//@idrincon - req 1038 - 29/10/2010 se agrega if de validacion
						if(!cambioTelefono){
							recursos_linea_basicaLocal.setCambio_zonas("false");
							listaZonasNew = this.consultaZonasAtendimiento(mensajeEstadoLineaLocal.getPeti_numero());
							
							if (listaZonasOld.size() != listaZonasNew.size()){
								recursos_linea_basicaLocal.setCambio_zonas("true");
								log.debug("ES diferente: true");
							}else{
								masterFor: for(int i  = 0; i < listaZonasOld.size(); i++){	
									String zonaLocalOld = (String)listaZonasOld.get(i);
									
									slaveFor: for(int j  = 0; j < listaZonasNew.size(); j++){
										String zonaLocalNew = (String)listaZonasNew.get(j);
										
										if (zonaLocalOld.equals(zonaLocalNew)){
											continue masterFor;
										}
									}
									
									log.debug("ES diferente: true");
									recursos_linea_basicaLocal.setCambio_zonas("true");
									break masterFor;
								}
							}
						}else{
							recursos_linea_basicaLocal.setCambio_zonas("true");
						}
						//fin - req 1038 - 29/10/2010
					}
//					TODO -Fin - gonzalez- 12/06/2008 - Zonas de Atendimiento
				}		
				
				
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F"))){
					if(mensajeEstadoLineaLocal.getAccion().equals("F")){
						if(mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)){
							log.debug("La actividad corresponde a TOA");
							TOADelegate toaDelegate = new TOADelegate();
							toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
						}else{
							enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
						}
					}
					return; 
				}
					
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
				actividadEJB.terminar(actDto);
			
			
				} catch (NumberFormatException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				
				} catch (CreateException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
						
				} catch (FinderException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				
				} catch (TnProcesoExcepcion e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
					
				} catch (NamingException e) {
					bExecution.setErrorOp(true);
					log.error("Creacion de Local Home Nulls",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				}
				catch(Exception e)
				{
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
				finally{  
					bExecution.endAll();
					// CR15338 - @Trace - ana santos - 04/08 - FIN 
				}
		}

		/* 
		 * Obtener Puntos BA para Granite - CR-14657 - Granite - agonz - 20/10/2008
		 */
		public void consultaRecursoGraniteSTB_BA(Long peticion, String idActividad, String codActividad) throws ATiempoAppEx {
			try{
				
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				boolean esRefrescar=false;
				boolean tienePC = false;
				boolean tienenBANaked = false;
				String idActividadTem = "";
				if(idActividad.equals("")||idActividad.equals("F"))
				{
					esRefrescar=true;
					idActividadTem = idActividad;
					
				}
				
				if(codActividad != null){
					idActividad = codActividad;
				}else{
					idActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_ASIG_AUTOMATICA");
				}
				TR510E tr510e = new TR510E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				PeticionKey peticionKey = new PeticionKey(peticion);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
				Caracteristicas_lineaLocalHome caracteristicasLocalHome = (Caracteristicas_lineaLocalHome)HomeFactory.getHome(Caracteristicas_lineaLocalHome.JNDI_NAME);
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				Operacion_comercialKey operacion_comercialKey=null;
				
				Long ps_id=null;

				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
				tr510e.setInterfaz("RefreshSIRS");
				tr510e.setSource("ATIEMPO");
				tr510e.setVersion("1.0");
				tr510e.setDestination("ESB");
				
				//En esta actividad no puedo preguntar por el ps que llama a la actividad 
				Operacion_comercialLocal operacion_comercial = null;
				String opco_tipo = null;
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
				{
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					operacion_comercial = producto_servicio_peticionLocal.getOperacion_comercial();
					operacion_comercialKey=(Operacion_comercialKey) operacion_comercial.getPrimaryKey();
					Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					
					if(fPSK.faps_id.intValue() == ComunInterfaces.familiaPcBANaked)
						tienenBANaked = true;
					
					if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcPsBANaked).longValue()||fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue()|| fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() ){
						opco_tipo = operacion_comercial.getOpco_tipo();
						if(operacion_comercial.getOpco_tras()!=null && operacion_comercial.getOpco_tras().equals("T")
							&& opco_tipo!=null && ( opco_tipo.equals("B") || opco_tipo.equals("BCP")))
							continue;
						ps_id=productoServicoKey.ps_id;
						tienePC = true;
						break;
					}	
				}
				
				if (!tienePC){
					log.debug("La petición no trae PC, se entra a buscar un PS Línea");
					for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
					{
						producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
						Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
						operacion_comercial = producto_servicio_peticionLocal.getOperacion_comercial();
						operacion_comercialKey=(Operacion_comercialKey) operacion_comercial.getPrimaryKey();
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						//se agrega la familia NAKED PS VOIP
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaLinea).longValue()
								||fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							opco_tipo = operacion_comercial.getOpco_tipo();
							if(operacion_comercial.getOpco_tras()!=null && operacion_comercial.getOpco_tras().equals("T")
								&& opco_tipo!=null && ( opco_tipo.equals("B") || opco_tipo.equals("BCP")))
								continue;
							ps_id=productoServicoKey.ps_id;
							break;
						}	
					}
				}
							
				if(ps_id==null){
					if(tienenBANaked){
						log.debug("Se envía PS Virtual");
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VIRTUAL");
						tr510e.setProductServiceCode(new Long(propertiesDBLocal.getValor()));
					}
					log.debug("Ningun Ps tipo PC de la peticion: "+peticion+"  invoca la actividad Obtener Puntos Red BA. No se envia mensaje a APSC.");
					return;
				}else{
						tr510e.setProductServiceCode(ps_id.longValue());
				}
				
				// Se modifica el objeto TR010E, para ser enviado
				tr510e.setId(IdCorrelativo.toString());
				tr510e.setPathType(peticionLocal.getDir_tip_via_1());
				tr510e.setPathNumber(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_1())));
				tr510e.setFirstPathCharacters(peticionLocal.getDir_lt1_via_1());
				tr510e.setSecondPathCharacters(peticionLocal.getDir_lt2_via_1());
				tr510e.setPathZone(peticionLocal.getDir_zon_via_1().toCharArray()[0]);
				tr510e.setPathType2(peticionLocal.getDir_tip_via_2());
				tr510e.setPathNumber2(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_2())));
				tr510e.setFirstPathCharacters2(peticionLocal.getDir_lt1_via_2());
				tr510e.setSecondPathCharacters2(peticionLocal.getDir_lt2_via_2());
				tr510e.setAtiempoRequestNumber(peticion.longValue());		
				tr510e.setNumberClient(0);
				
				try
				{
					if(peticionLocal.getDir_zon_via_2()!=null && !peticionLocal.getDir_zon_via_2().trim().equals("")) {
						tr510e.setPathZone2(peticionLocal.getDir_zon_via_2().charAt(0));
					}else{
						tr510e.setPathZone2('-');
					}
					
				}
				catch(Exception e)
				{
					log.info("El valor de Dir Zon Via 2 no es valido:"+peticionLocal.getDir_zon_via_2());
				}

				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				
				// Error reportadado por Padilla/Sandra a Juan P. - 27/Jul-2009 - agonz
				//tr510e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
				tr510e.setAtisRequestNumber(peticion_atisKey.cod_pet_cd.longValue());
				
				//Para la asignacion de recursos el numero debe ir blanco, pues todavia no se me asigna un numero.
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals(""))
				{
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else
				{
					phoneNumber="0";
				}	
				tr510e.setPhoneNumber(Integer.parseInt(phoneNumber));
						
				String nombre = Utiles.sinNull(peticionLocal.getNom_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getPri_ape_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getSeg_ape_ds().trim(),"");
				
				if (nombre!=null && ! nombre.trim().equals (""))
				{
					if(nombre.length()>40)
						nombre=nombre.substring(0,39);
					tr510e.setClientName(nombre);
				}else{
					tr510e.setClientName("-");
				}
				
				
				String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
				
				if (clientDocument!=null && ! clientDocument.trim().equals ("")) 
							{
					if (clientDocument.length()>20){
						clientDocument=clientDocument.substring(0,20);
					}
				}
				else{
					clientDocument="-";
				}
				
				tr510e.setClientDocument(clientDocument);
				
				
				tr510e.setComercialOperation(operacion_comercialKey.opco_id.longValue());
				
				String installAddress = Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(peticionLocal.getNom_slo_no(),"");
				if(installAddress==null || installAddress.trim().equals("")) {
					installAddress="-";
				}
				tr510e.setInstallAddress(installAddress);
				
				String addressNumber = Utiles.sinNull(peticionLocal.getNum_cal_nu(),"");
				
				try{
					if (addressNumber!=null && !addressNumber.trim().equals("")){
						if (addressNumber.length()>3){
							addressNumber=addressNumber.substring(0,3);
						}
					}
					else{
						addressNumber="0";
					}			
					
					tr510e.setAddressCharacters(addressNumber.charAt(0));

					//TODO: RETA - 12022010 - Ajuste para que la interfaz incluya el campo address Number 
					try{
						tr510e.setAddressNumber(addressNumber);
					}catch(NumberFormatException nm){
						tr510e.setAddressNumber("0");
					}
					//End TODO
					
				}catch(Exception e){
					log.info("El valor de Num cal nu no es valido:"+peticionLocal.getNum_cal_nu());
				}

				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr510e.setDepartment(Integer.parseInt(departamentoKey.cod_dpt));
				
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr510e.setCity(Integer.parseInt(localidadKey.cod_loc));
				tr510e.setUseType(producto_servicio_peticionLocal.getCod_tip_uso().longValue());
//				tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
//				tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				//Requerimiento Naked: Se calcula la caracteristica y subcaracteristica de la tabla Caracteristicas_linea: CR7
				try {
					Caracteristicas_lineaKey key = new Caracteristicas_lineaKey(ps_id);
					Caracteristicas_lineaLocal caracteristicasLocal = caracteristicasLocalHome.findByPrimaryKey(key);
					if(caracteristicasLocal.getCaracteristica() != null && caracteristicasLocal.getSub_caracteristica() != null){
						tr510e.setComercialProductType(caracteristicasLocal.getCaracteristica());
						tr510e.setComercialProductSubType(caracteristicasLocal.getSub_caracteristica());
					}else{
						tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
						tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
					}
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
//					Requerimiento Naked: Si no encuentra registro en la tabla se deja el valor que se trae de ATIS: CR7
					log.debug("No se encuetra el PS registrado en la tabla Caracteristica_Linea se setea el valor que llega de ATIS");
					tr510e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
					tr510e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				}
				                                           
				tr510e.setPlaceNumber1(peticionLocal.getDir_nro_lg1());
				tr510e.setPlaceNumber2(peticionLocal.getDir_nro_lg2());
				tr510e.setPlaceNumber3(peticionLocal.getDir_nro_lg3());
				tr510e.setPlaceType1(peticionLocal.getDir_tip_lg1());
				tr510e.setPlaceType2(peticionLocal.getDir_tip_lg2());
				tr510e.setPlaceType3(peticionLocal.getDir_tip_lg3());
				
				tr510e.setZone(0);
				//tr510e.setUseType(0);
				//AT-1892 -- No se enviaba sub-city -- Pablo L
				tr510e.setSubCity(peticionLocal.getNom_slo_no());
				tr510e.setSpaceNumber(0);
				tr510e.setSector(0);
				/* Granite - PBX - Pablo L*/
				int cantPBX = cantPbx(peticionLocal , peticion);
				tr510e.setRequestPbx(cantPBX);
				
				//AT-1956 13 marzo 2009
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				//tr510e.setRequestDateString(petAtis.getFec_sct_pet_ff().toString());
				tr510e.setRequestDate(petAtis.getFec_sct_pet_ff());
//				Pablo L -- Correccion bug Granite
				
//				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
//				PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(peticion);
//				InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
				//Juan AT-2153 este valor solo se manda para PBX y troncales estabamos obteniendo mal el valor
//				int previousPhone = peticionLocal.getpreviousServicePhone();				
//				tr510e.setPreviousPhoneNumber(previousPhone);
				int previousPhone = Integer.parseInt(retornarNumeroPBX(peticion));	
//				if (peticionDTO.getNroPiloto()!= null && !peticionDTO.getNroPiloto().equalsIgnoreCase("") ){
//					previousPhone=Integer.parseInt(peticionDTO.getNroPiloto());
//				}
				
				tr510e.setPreviousPhoneNumber(previousPhone);
				
				tr510e.setEnhancementNumber(0);
				tr510e.setBlockSide("");
				tr510e.setBlock(0);
				tr510e.setProductServiceFamily(peticionLocal.getPeti_id_instancia());                             
//				CR 25393 - Gustavo
				Integer ods =Integer.valueOf("0");
				try {
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					Recursos_linea_basicaLocal rlbLocal = recursos_linea_basicaLocalHome.findByPeticion( peticion );
					
					if(rlbLocal!=null && rlbLocal.getOds_apsc()!=null){
						ods = Integer.valueOf(rlbLocal.getOds_apsc().toString());
						
					}
					else{
						
						log.debug("La petición [" + peticion +"] no tiene ODS.  Se envía '0'");
					}
				} catch (FinderException e1) {
					
				}
				tr510e.setOdsNumber(ods);
				//Fin CR 25393
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
//				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
//				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				if(esRefrescar){
					if(idActividadTem.equals("F"))
						mensajeEstadoLineaLocal.setAccion(idActividadTem);
					else
						mensajeEstadoLineaLocal.setAccion("R");
				}
											
				int areaPhone= 0;
				int numeroPhone= 0;
				if (phoneNumber.length()>1){
					areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
					numeroPhone=Integer.parseInt(phoneNumber.substring(1));
				}
				mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
				mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
				
				AsignacionRecursosGraniteSTBMQ asignacionRecursosGraniteSTBMQ = new AsignacionRecursosGraniteSTBMQ();
				asignacionRecursosGraniteSTBMQ.enviarMensaje(tr510e);
				
					
				} catch (NumberFormatException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				} catch (CreateException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
				} catch (FinderException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				} catch (NamingException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
				}
				catch(Exception e)
				{
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				}
			
		}


		
		public void envioPuntosRedGraniteSTB(String peticion, String idActividad) throws ATiempoAppEx {
			this.envioPuntosRedGraniteSTB(null,peticion,idActividad);
		}
		
		
		public void envioPuntosRedGraniteSTB(String telefonoConsulta,String peticion , String idActividad) throws ATiempoAppEx {
			try
			{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				boolean esRefrescar=false;
				if(idActividad.equals(""))
				{
					esRefrescar=true;
					idActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA");
				}
			
				TR514E tr514e = new TR514E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionKey peticionKey = new PeticionKey(new Long(peticion));
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();
			
				//Proceso para la validacion de los tipos de ps, que estan en el mensaje
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
			
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				tr514e.setId(IdCorrelativo.toString());
				
				//Telefono a consultar por defecto
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				
				log.debug("PHONE NUMBER:" + phoneNumber);
				
				
				//Si viene un telefono, se consulta por ese telefono y se asocia a la peticion
				if (telefonoConsulta != null && !"".equals(telefonoConsulta.trim())){
						phoneNumber = telefonoConsulta;							
				}			
				try
				{
					
					if (phoneNumber!=null && !phoneNumber.trim().equals(""))
					{
						if (phoneNumber.length()>8)
						{ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else
					{
						phoneNumber="0";
					}
					
					tr514e.setPhoneNumber(new Integer(phoneNumber).intValue());
				}
				catch(Exception e)
				{
					phoneNumber="0";
					log.info("No Hay PhoneNumber Valido en la peticion."+peticionLocal.getNum_ide_nu_stb());
				}

				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr514e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());
			
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr514e.setCity(new Integer(localidadKey.cod_loc).intValue());
				tr514e.setRequestDate(new java.util.Date ());
				tr514e.setAtiempoRequestNumber(new Long(peticion));
				tr514e.setSubCity("");
				

			
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				
				if(esRefrescar)
					mensajeEstadoLineaLocal.setAccion("R");
				
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				if(peticionLocal.getCod_are_tel_cd()!=null)
					mensajeEstadoLineaLocal.setArea(new Integer(peticionLocal.getCod_are_tel_cd()));
				if(peticionLocal.getTel_cot_ds()!=null)
					mensajeEstadoLineaLocal.setTelefono(new Long(phoneNumber));
			
				SolicitudPuntosRedGraniteSTBMQ solicitudPuntosRedGraniteSTBMQ = new SolicitudPuntosRedGraniteSTBMQ();
				solicitudPuntosRedGraniteSTBMQ.enviarMensaje(tr514e);
				
				

				
			} catch (NumberFormatException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			
			} catch (FinderException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (NamingException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING);
			}
			catch(Exception e)
			{
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}
			
		}

		public void consultaPuntosRedGraniteSTB(TR514S tr514s) throws ATiempoAppEx {
//			 CR15338 - @Trace - ana santos - 04/08 - Inicio 
			BackendExecution bExecution = null;

			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr514s, log);			
				bExecution.setIdMensajeOp(tr514s.getId());			
				bExecution.startOperation();
				
				Fecha fecha=new Fecha();

				//	TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr514s.getId()));
				
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
		            
			   try
			   {
			   		Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
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
					log.debug(
						"No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr514s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr514s.getId(),ATiempoAppEx.EXCEPTION);
				}
				bExecution.setNumPetAtiempo(mensajeEstadoLineaLocal.getPeti_numero());
				String codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
				boolean esRefresco=false;
				if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
					esRefresco=true;
				
				
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk ) {
					log.debug(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr514s));
					return;
				}
				
				if(tr514s.getErrorCode()==0  && tr514s.getPhoneNumber() > 0){
					
					Recursos_linea_basicaLocal recursos_linea_basicaLocal;
					
					Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
//					boolean hayAsignados = false;
					if ( recursosLineaBasica.size() == 0){
			
						Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
						recursos_linea_basicaLocal  = recursos_linea_basicaLocalHome.create(idConDos);
		
					}else{
						recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)  recursosLineaBasica.iterator().next();
						//req FTTC
//						hayAsignados=true;
					}
					
					
					PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
					
					//Req. 13146 - Jesus Carvajal - 28/05/2012
					//Actualizar coordenadas en la tabla peticion dadas por SIRS 
					if (peticionLocal.getCoord_x_agnd_sc() == null || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0.0") 
							|| peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("") || peticionLocal.getCoord_x_agnd_sc().equalsIgnoreCase("0")){
						peticionLocal.setCoord_x_agnd_sc(tr514s.getLongitude().toString());
						peticionLocal.setCoord_y_agnd_sc(tr514s.getLatitude().toString());
						recursos_linea_basicaLocal.setLongitud(new java.math.BigDecimal(tr514s.getLongitude().toString()));
						recursos_linea_basicaLocal.setLatitud(new java.math.BigDecimal(tr514s.getLatitude().toString()));
						log.debug("Se actualizo longitud y latitud en tabla peticion y tabla rlb");
					}else{
						recursos_linea_basicaLocal.setLongitud(new BigDecimal(peticionLocal.getCoord_x_agnd_sc()));
						recursos_linea_basicaLocal.setLatitud(new BigDecimal(peticionLocal.getCoord_y_agnd_sc()));
						log.debug("Ya existe longitud y latitud en tabla peticion y se actualiza tabla rlb");
					}
					//Fin Req. 13146
					
					//Asigno Central
					
					CentralKey ck = new CentralKey();
					ck.cod_central = new Long (tr514s.getCentral());
					CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
					CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(ck);
					peticionLocal.setFk_03_central(centralLocal);
					//Se actualiza la central en la bandeja si es que hay alguna publicacion.
					BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
					BIntegradaSessionLocal biLocal = biHome.create();
					biLocal.cambiarCentralPeticion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)),((PeticionKey)peticionLocal.getPrimaryKey()).peti_numero,centralLocal);
					 //requ fttc se valida si no hay euqipos asignados para que no sobre escriba la informacion
//					if(!hayAsignados)
//					{
					//Los recursos se ponen en recursos asignados, igual que la asignacion de recursos
					recursos_linea_basicaLocal.setPeticion(peticionLocal);
					recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);

					
					recursos_linea_basicaLocal.setArmario_asg(tr514s.getCloset());
					recursos_linea_basicaLocal.setDir_armario(tr514s.getClosetAddress());
					recursos_linea_basicaLocal.setCable(tr514s.getCable());
					
					recursos_linea_basicaLocal.setCaja_asg(tr514s.getBox());
					recursos_linea_basicaLocal.setDir_caja(tr514s.getBoxAddress());
					recursos_linea_basicaLocal.setDesc_central(String.valueOf(tr514s.getCentralDescription()));
					recursos_linea_basicaLocal.setCod_central(new Long((tr514s.getCentral())));
					recursos_linea_basicaLocal.setDesc_dist_prim_asg(tr514s.getPrimaryDistributorDescription());
					recursos_linea_basicaLocal.setDesc_dist_sec_adg(tr514s.getDistributorDescription());
					recursos_linea_basicaLocal.setDist_prim_asg(new Long(tr514s.getPrimaryDistributor()));
					recursos_linea_basicaLocal.setDist_sec_asg(new Long(tr514s.getPrimaryDistributor()));
					recursos_linea_basicaLocal.setDir_distribuidor(tr514s.getDistributorAddress());
					recursos_linea_basicaLocal.setLen(tr514s.getLen());
					recursos_linea_basicaLocal.setListon_asg(tr514s.getStrip());
					recursos_linea_basicaLocal.setTelefono_asg(new Long(tr514s.getPhoneNumber()));
					recursos_linea_basicaLocal.setPar_caja_asg(new Long(tr514s.getBoxPair()));
					recursos_linea_basicaLocal.setPar_cable(new Long(tr514s.getCablePair()));
					recursos_linea_basicaLocal.setPar_liston_asg(new Long(tr514s.getStripPair()));
					recursos_linea_basicaLocal.setPosicion_horizontal_asg(tr514s.getHorizontalPosition());
					recursos_linea_basicaLocal.setPosicion_horizontal_ant(tr514s.getHorizontalPosition());
//					}
					//Req. 13146 - Jesus Carvajal - 16/03/2012	
					recursos_linea_basicaLocal.setLongitud(new BigDecimal(tr514s.getLongitude().toString()));
					recursos_linea_basicaLocal.setLatitud(new BigDecimal(tr514s.getLatitude().toString()));
					log.debug("Actualiza latitud y longitud en la tabla recursos_linea_basica");
					//Fin Req. 13146
					
//					Req 14209 - Jesus Carvajal - 19042012
					recursos_linea_basicaLocal.setFecha_asig_recurso(fecha.getTimestamp());
					//Fin Req 14209
					
					//Req 13420 - Jesus Carvajal - 09052012
					recursos_linea_basicaLocal.setInd_dedicado(new Short("0"));
					//Fin Req 13420
					//@dcardena -req FTTC 26/09/2013 se agregan los campos nuevos para FTTC
					//se realiza validacion para saber si algun campo FTTC contiene datos
					//se agrega nueva validacion 31/03/2014 cardena
					//funcion que valida los campos fttc y retorna si es LB,FTTC o deriva a PGI
					String accion = validaFTTC(tr514s.getFttcfab(), tr514s.getFttcmode() , tr514s.getFttcslot() ,
							tr514s.getFttcpwd() , tr514s.getFttcusu() , tr514s.getFttciplb(),codAct,mensajeEstadoLineaLocal);
					
					if(accion.equals("LB"))
					{	
						// este else espara saber si no trae equipos fttc
						log.debug("No Trae Equipos FTTC");
						if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null&&recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))
						{
							
						}else{
						recursos_linea_basicaLocal.setRec_fttc_asg("N");	
						}
					}else if(accion.equals("PGI")){	
						return;											
					}else if(accion.equals("FTTC")){
					log.debug("Trae Equipos FTTC-");
					//se instancia la clase del password FTTC
					int longitud=0;
										
					Password generarPass = new  Password();
				
				//se valida que tipo de operacion comercial llega 1 es alta 
			if (peticionLocal.getTica_id().equals("A")){
				//se trae de la tabla propertiesBD la longitud del password
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
				// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
				recursos_linea_basicaLocal.setFttciplb_asg(tr514s.getFttciplb());
				recursos_linea_basicaLocal.setFttcpwd_asg(tr514s.getFttcpwd());
				recursos_linea_basicaLocal.setFttcusu_asg(tr514s.getFttcusu());
				recursos_linea_basicaLocal.setFttcslot_asg(tr514s.getFttcslot());
				recursos_linea_basicaLocal.setFttcfab_asg(tr514s.getFttcfab());
				recursos_linea_basicaLocal.setFttcmode_asg(tr514s.getFttcmode());
				//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
				recursos_linea_basicaLocal.setRack(tr514s.getRack().toString());
				recursos_linea_basicaLocal.setSubRack(tr514s.getSubRack().toString());	
				
				//el campo que nos dice que hay equipos De tipo FTTC
				recursos_linea_basicaLocal.setRec_fttc_asg("S");
				recursos_linea_basicaLocal.setRec_fttc_ant("N");
				//se genera el password y se le envia la longitud
				longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
				recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
				log.debug(tr514s.getFttciplb());
				log.debug(tr514s.getFttcpwd());
				log.debug(tr514s.getFttcusu());
				log.debug(tr514s.getFttcslot());
				log.debug(tr514s.getFttcfab());
				log.debug(tr514s.getFttcmode());
				
			//si la operacion comercial es diferente de 1 puede ser un traslado o una baja
//				
			}else if(peticionLocal.getTica_id().equals("B"))
			{
//				se trae de la tabla propertiesBD la longitud del password
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
				// se inserta en la tabla recursos_linea_basica los campos FTTC Asignados
				log.debug("Treae Equipos FTTC de baja");
				// se guardan los nuevos equipos en los campos asignados			
				recursos_linea_basicaLocal.setFttciplb_asg(tr514s.getFttciplb());
				recursos_linea_basicaLocal.setFttcpwd_asg(tr514s.getFttcpwd());
				recursos_linea_basicaLocal.setFttcusu_asg(tr514s.getFttcusu());
				recursos_linea_basicaLocal.setFttcslot_asg(tr514s.getFttcslot());
				recursos_linea_basicaLocal.setFttcfab_asg(tr514s.getFttcfab());
				recursos_linea_basicaLocal.setFttcmode_asg(tr514s.getFttcmode());
				recursos_linea_basicaLocal.setRec_fttc_asg("S");
				//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
				recursos_linea_basicaLocal.setRack(tr514s.getRack().toString());
				recursos_linea_basicaLocal.setSubRack(tr514s.getSubRack().toString());	
				if(recursos_linea_basicaLocal.getRec_fttc_ant()!=null&&recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))
				{
					
				}else{
					recursos_linea_basicaLocal.setRec_fttc_ant("N");	
				}
			
//				se genera el password y se le envia la longitud
				longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
				recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
				log.debug(tr514s.getFttciplb());
				log.debug(tr514s.getFttcpwd());
				log.debug(tr514s.getFttcusu());
				log.debug(tr514s.getFttcslot());
				log.debug(tr514s.getFttcfab());
				log.debug(tr514s.getFttcmode());
				
			}else if (peticionLocal.getTica_id().equals("P") ||peticionLocal.getTica_id().equals("T"))
			{
//				se trae de la tabla propertiesBD la longitud del password
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
				Collection ps = peticionLocal.getProducto_servicio_peticion();
				String operacionComercial = "";
				boolean traslado=false;
				boolean alta=false;
				if(ps!=null && ps.size()>0){
					for (Iterator iter = ps.iterator(); iter.hasNext();) {
					
						//guardamos en un iterator los ps
						Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
						Operacion_comercialLocal operacion_comercial= (Operacion_comercialLocal) element.getOperacion_comercial();
						operacionComercial+=(operacion_comercial.getOpco_tipo()+" "+operacion_comercial.getOpco_tras()+" ");
						log.debug("operacion comercial "+operacionComercial);
						if(operacion_comercial.getOpco_tras()!=null && operacion_comercial.getOpco_tras().equals("T")){
							traslado=true;
						}
						if(operacion_comercial.getOpco_tipo().equals(ComunInterfaces.opCoTipoAlta) 
								|| operacion_comercial.getOpco_tipo().equals("ACP")
								|| operacion_comercial.getOpco_tipo().equals("BCP"))
						{
							alta=true;
						}
						}
					}
				
				//se inserta en anteriores cuando hay Traslados ACP BCP o Baja SVA unicamente
				if(traslado||(!traslado && !alta))
				{
					log.debug("Operacion comercial marcada como Posventa (T) o Posventa (B) Se optienen los recursos en Anteriores");
					recursos_linea_basicaLocal.setFttciplb_ant(tr514s.getFttciplb());
					recursos_linea_basicaLocal.setFttcpwd_ant(tr514s.getFttcpwd());
					recursos_linea_basicaLocal.setFttcusu_ant(tr514s.getFttcusu());
					recursos_linea_basicaLocal.setFttcslot_ant(tr514s.getFttcslot());
					recursos_linea_basicaLocal.setFttcfab_ant(tr514s.getFttcfab());
					recursos_linea_basicaLocal.setFttcmode_ant(tr514s.getFttcmode());	
					recursos_linea_basicaLocal.setRec_fttc_ant("S");
					//Cambio para requertimiento FTTC se agrega el rack y subRack anterior
//					recursos_linea_basicaLocal.setRack_ant(tr514s.getRack().toString());
//					recursos_linea_basicaLocal.setSubRack_ant(tr514s.getSubRack().toString());	
				}else{
				if(alta) 
					{
					//cuando es una postventa y es una alta (SVAS)se registra en asignados
					log.debug("Se insertan recursos en asignados (ALTA) en posventa");
					recursos_linea_basicaLocal.setFttciplb_asg(tr514s.getFttciplb());
					recursos_linea_basicaLocal.setFttcpwd_asg(tr514s.getFttcpwd());
					recursos_linea_basicaLocal.setFttcusu_asg(tr514s.getFttcusu());
					recursos_linea_basicaLocal.setFttcslot_asg(tr514s.getFttcslot());
					recursos_linea_basicaLocal.setFttcfab_asg(tr514s.getFttcfab());
					recursos_linea_basicaLocal.setFttcmode_asg(tr514s.getFttcmode());
					recursos_linea_basicaLocal.setRec_fttc_asg("S");
					//Cambio par requerimiento de FTTC donde se agrega rack y sub-rack Cesar Remigio
					recursos_linea_basicaLocal.setRack(tr514s.getRack().toString());
					recursos_linea_basicaLocal.setSubRack(tr514s.getSubRack().toString());	
				}
				}
//				se genera el password y se le envia la longitud
				longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
				recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));				
			}
			}
					// fin - requerimiento - requerimiento fttc 	
					
					
					
					
					
					if (tr514s.getSpecialServices() != null)
					{
						//if(esRefresco)
						//{
//							log.debug("Tengo que borrar los Servicios Suplementarios que tenia antes");
							borrarSS(recursos_linea_basicaLocal);
						//}
							Servicio_basico_supl_conec6_apsc_lineaLocalHome servicio_basico_supl_conec6_apsc_lineaLocalHome = (Servicio_basico_supl_conec6_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_conec6_apsc_lineaLocalHome.JNDI_NAME);
						for (Iterator iter = tr514s.getSpecialServices().iterator(); iter.hasNext();)
						{
							Long idService =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
							
							SpecialService specialService = (SpecialService) iter.next();
							String servicio=  specialService.getSpecialService();
							//String servicio= (String) iter.next();
							Servicio_basico_supl_conec6_apsc_lineaLocal servicio_basico_supl_conec6_apsc_lineaLocal = 
							servicio_basico_supl_conec6_apsc_lineaLocalHome.create(idService);
							servicio_basico_supl_conec6_apsc_lineaLocal.setCodigo_ps(servicio);
							servicio_basico_supl_conec6_apsc_lineaLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						}
					}
					
			//CR14657 - Granite - adocarmo - inicio
					// seteo si la central soporta configuración autmática
					if (tr514s.isEoc()) {
						recursos_linea_basicaLocal.setConfig_automatica(new Short((short)1));
					}else {
						recursos_linea_basicaLocal.setConfig_automatica(new Short((short)0));
					}					
			//CR14657 - Granite - adocarmo - fin			
					
					mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					PeticionKey peticionKey = (PeticionKey) mensajeEstadoLineaLocal.getPeticion().getPrimaryKey();
					
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB  actividadEJB=null;
					ActividadEJBDTO actDto=null;

					if(!esRefresco){
						actividadEJB = actividadFactoryEJB.getActividad(codAct);
						actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codAct);
					}
					
					if (tr514s.getDslams() == null || tr514s.getDslams().size()<1)
					{
						//Si no viene Dslam y la consulta la invoca Puntos de red BA, entonces se deriva a PGI
						// y se asocia la causal de no dslam.//Leonardo
						if(esRefresco)
							return;
						if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_PUNTOS_RED_BA").equals(codAct))
						{
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
							log.debug("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
							//modificado adecarlini
							//insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());
							//fin 
							actDto.setObservacion("No vienen Dslam en los recursos asignados. Se redirige a PGI.");
//							agregado adecarlini	
							String codError = String.valueOf(tr514s.getErrorCode());
							String nombreClase=TR514S.class.getName();
							nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
							ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);

						    //ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR514S.class.getName());
						    if(errorLegado != null){
						       peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
							   insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						    }else{
								insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, actDto.getCodigoActividad(), new Long(VpistbbaConfig.getVariable("COD_CAU_NO_DSLAM")), actDto.getIdActividadFlujo());
						    }

						}					
					}else
					{

						borrarDslams(recursos_linea_basicaLocal);
						borrarZonas(recursos_linea_basicaLocal);

//						TODO - Inicio - agonzalez- 28/04/2008- Zonas de Atendimiento
						Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
						Zonas_atendimientoLocal zona_atendimientoLocal=null;
						for (Iterator iter = tr514s.getDslams().iterator(); iter.hasNext();){
							Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
							Dslam1 dslam1 = (Dslam1) iter.next();
							if(zona_atendimientoLocal==null){
								//Se valida que es la primera zona 
								zona_atendimientoLocal=validarPSPromocion(peticionLocal,dslam1,recursos_linea_basicaLocal);	
							}else{
								zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
								zona_atendimientoLocal.setIp(dslam1.getIp());
								zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
							}
						}
//						TODO -Fin - agonzalez- 28/04/2008- Zonas de Atendimiento
					}
					if(esRefresco)
						return;
					if(actividadEJB!=null)
						actividadEJB.terminar(actDto);		
				}else
				{
					if(esRefresco)
						return;
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					
					//modificado adecarlini
					PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
					PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
					//
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, codAct);
					
					//agregado adecarlini	
					String codError = String.valueOf(tr514s.getErrorCode());
					String nombreClase=TR514S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					//ErrorLegadoLocal errorLegado = getErrorLegado(codError,TR514S.class.getName());
					if (errorLegado != null){ 
						if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr514s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					//
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					if (tr514s.getPhoneNumber() < 1)
					{
						actDto.setObservacion(tr514s.getErrorMessage()+". Central = 0. Se redirige a "+bandeja+".");
					}
					else
					{
						actDto.setObservacion(tr514s.getErrorMessage()+". Error de mensajeria. Se redirige a "+bandeja+".");
					}
					//modificado adecarlini
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					//fin
					actividadEJB.terminar(actDto);
						
				}
				
			
			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			/*} catch (CreateException e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);*/
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
			} catch (NamingException e)
			{
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
			}
			catch(Exception e)
			{
				bExecution.setErrorOp(true);
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}
			finally{  
				bExecution.endAll();
			}
			
		}	

	
		/**
		 * @param recursos_linea_basicaLocal
		 * @param tr514s
		 * @return
		 */
		public boolean recursosIgualesFTTC(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx{
			// TODO Apéndice de método generado automáticamente
			
			if(recursos_linea_basicaLocal.getFttciplb_asg()!=null && recursos_linea_basicaLocal.getFttcpwd_asg() != null && recursos_linea_basicaLocal.getFttcusu_asg() != null
					&& recursos_linea_basicaLocal.getFttcslot_asg() != null && recursos_linea_basicaLocal.getFttcfab_asg() != null && recursos_linea_basicaLocal.getFttcmode_asg() != null)
			{
				if(!recursos_linea_basicaLocal.getFttciplb_asg().equals(recursos_linea_basicaLocal.getFttciplb_ant()))
					return false;
				if(!recursos_linea_basicaLocal.getFttcpwd_asg().equals(recursos_linea_basicaLocal.getFttcpwd_ant()))
					return false;
				if(!recursos_linea_basicaLocal.getFttcusu_asg().equals(recursos_linea_basicaLocal.getFttcusu_ant()))
					return false;
				if(!recursos_linea_basicaLocal.getFttcslot_asg().equals(recursos_linea_basicaLocal.getFttcslot_ant()))
					return false;
				if(!recursos_linea_basicaLocal.getFttcfab_asg().equals(recursos_linea_basicaLocal.getFttcfab_ant()))
					return false;
				if(!recursos_linea_basicaLocal.getFttcmode_asg().equals(recursos_linea_basicaLocal.getFttcmode_ant()))
					return false;
			}else{
				return false;
			}
				
			return true;
		}

		public boolean enviaDesconfigurarConfiguracionAutomaticaSTB (ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				
				Long idPeticion = act.getNumeroPeticion();
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				
				TR511E tr511e = new TR511E();
				ArrayList psServiciosSuplementarios = new ArrayList();
				Long psLineaBasicaId=null;
				Long psId=null;
				int cantPsLineaBasica = 0;
				SpecialServicesRequest srs = null;
				int opComercial = 6;

				/*
				 * GESTION DE BAJAS
				 */
				if(peticionLocal.getTica_id().equals("B")){
					tr511e.setInterfaz("BajaSIRSConfig");
				}else{
					tr511e.setInterfaz("AltaSIRSConfig");
				}

				tr511e.setSource("ATIEMPO");
				tr511e.setVersion("1.0");
				tr511e.setDestination("ESB");
				
				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		
				// no obligatorios, los cargo en 0 para que en la tr aparezca el tag
				tr511e.setNewCategory(0);
				tr511e.setNewSubCategory(0);
				tr511e.setOperationType(0);
				
				int cantPBX = cantPbx(peticionLocal , idPeticion);
				boolean esTraslado = tieneTrasladoLB(idPeticion);				
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					opComercial = operacion_comercialKey.opco_id.intValue();
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
						psId=productoServicoKey.ps_id;
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();

						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() ){
							if (!ComunInterfaces.operacionBCP.equals(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo())){
							//At-2067 - 14 abril 2009
								psLineaBasicaId=psId;
								boolean cruzada= esCruzada(idPeticion);
								log.debug("Peticion :.."+act.getNumeroPeticion()+"  y es Cruzada :.."+ cruzada);
								if(opComercial==8 && cruzada){
									opComercial=6; //caso desconfigurar 
								}
								
								if(tr511e.getInterfaz().equals("BajaSIRSConfig") && esTraslado){
									opComercial=6; //Si es pbx y es una baja se desconfigura
								}

								log.debug("Se settea en Operation Type :.."+ opComercial);
								tr511e.setOperationType(opComercial);//AT-1996
								tr511e.setNewCategory(producto_servicio_peticionLocal.getTip_pro_cmr_cd());
								tr511e.setNewSubCategory(producto_servicio_peticionLocal.getSbt_pro_cmr_cd());
								
								cantPsLineaBasica++;
							}
						}else {

								
								if(producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == cambioNumero ){
									log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar/desconfigurar ya que es un cambio de numero");
								}else{
									
									srs = new SpecialServicesRequest();
									if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
										Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
										if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError){
											log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que esta Cancelado");
											srs=null;
										}else{											
											srs.setSpecialService(psId.toString());
											srs.setAction(opComercial);										
										}
																			
									}else{
										srs.setSpecialService(psId.toString());
										srs.setAction(opComercial);					
										
									}
									
									if(srs!=null){
										psServiciosSuplementarios.add(srs);
										log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());
									}
								}
						}						
					}
				}
				
				if (cantPsLineaBasica > 1) {
					log.error("Hay mas de 1 PS con famila " + ComunInterfaces.familiaPcLinea + ". No se envia mensaje");
					return false;
				}
				
				if (cantPsLineaBasica == 0 && psServiciosSuplementarios.size() == 0) {
					log.error("No hay PSs. No se envia mensaje" + ComunInterfaces.familiaPcLinea);
					return false;
				}
				
				log.debug( "Cant de Servicios Suplementarios: " + psServiciosSuplementarios.size());	
				
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr511e.setId(String.valueOf(IdCorrelativo));
				
				tr511e.setAtiempoRequestNumber(act.getNumeroPeticion());
				tr511e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());
				
				// Seteo RequestDate con la fecha de ingreso de la peticion en ATIS
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				tr511e.setRequestDate(petAtis.getFec_sct_pet_ff()); 

				Recursos_linea_basicaLocal rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());

				if (rlb.getOds_apsc()!=null) {
					tr511e.setOdsNumber(rlb.getOds_apsc().intValue());
				}else {
					log.error("No hay ODS." + ComunInterfaces.familiaPcLinea);
					tr511e.setOdsNumber(0);
				}
				
				boolean esCruzada=false;
				if( rlb.getCentral_connection()!=null){
				 	esCruzada = rlb.getCentral_connection().intValue()==1;
				}
				log.debug("esCruzada .."+esCruzada);
				esTraslado = tieneTrasladoLB(idPeticion);
				boolean esCambioNumero = tieneCambioNumeroLB(idPeticion);
				log.debug("esTraslado .."+esTraslado);
				log.debug("esCambioNumero .."+esCambioNumero);
				log.debug("rlb.getTelefono_asg() .."+rlb.getTelefono_asg());
				log.debug("rlb.getTelefono_ant() .."+rlb.getTelefono_ant());
				log.debug("REVERSA .."+act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa));
				
				
				if (rlb.getTelefono_ant()!=null) {
					tr511e.setPhoneNumber(rlb.getTelefono_ant().intValue());
				}else {
					log.error("No hay tel del abonado. No se envia mensaje" + ComunInterfaces.familiaPcLinea);
					return false;
				}	
				asignarTelefonosGranite( tr511e, act, true);

				tr511e.setSpecialServices(psServiciosSuplementarios);
				
				cantPBX = cantPbx(peticionLocal , idPeticion);
				tr511e.setRequestPbx(cantPBX);
				if(cantPBX > 0){
					PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
					PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(act.getNumeroPeticion());
					InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
					
					String nroPiloto = peticionDTO.getNroPiloto();
					
					if(nroPiloto != null && !nroPiloto.trim().equals("")){
						try{
							Integer piloto = new Integer(nroPiloto);
							tr511e.setPreviousNumber(new Integer(nroPiloto));
						}catch(NumberFormatException nfe){
							log.error("Error al convertir a numero el piloto: " + nroPiloto);
						}
					}
					
				}


				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());

				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
				ConfiguracionAutomaticaSTBMQ configuracionAutomaticaSTBMQ= new ConfiguracionAutomaticaSTBMQ();
				configuracionAutomaticaSTBMQ.enviarMensaje(tr511e);
				
				log.debug("Se setearon tr511e.setPhoneNumber .."+tr511e.getPhoneNumber());
				log.debug("Se setearon tr511e.setPreviousNumber .."+tr511e.getPreviousNumber());
				return true;
			}catch (Exception e)
			{
				log.error("Error al enviar Configuracion .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}
		
		/**
		 * AT-2148 error en reversas 
		 * @param act
		 * @return 	1 para el caso de que no se debe enviar la Reversa y se termina la actividad con error , 
		 * 			2 para el caso de que se debe enviar la Reversa de Configurar,
		 *  		3 para el caso de no se debe enviar la Reversa y se termina ok 
		 */
		
		public int enviaReversaConfiguracionAutomaticaSTB (ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				
				Long idPeticion = act.getNumeroPeticion();
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				
				TR511E tr511e = new TR511E();
				
				/*
				 * GESTION DE BAJAS
				 */
				if(peticionLocal.getTica_id().equals("B")){
					tr511e.setInterfaz("BajaSIRSConfig");
				}else{
					tr511e.setInterfaz("AltaSIRSConfig");
				}

				tr511e.setSource("ATIEMPO");
				tr511e.setVersion("1.0");
				tr511e.setDestination("ESB");
				
				ArrayList psServiciosSuplementarios = new ArrayList();
				Long psLineaBasicaId=null;
				Long psId=null;
				int cantPsLineaBasica = 0;
				SpecialServicesRequest srs = null;
				int opComercial = 0;
				int opComercialaux = 0;
				
				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		
				// no obligatorios, los cargo en 0 para que en la tr aparezca el tag
				tr511e.setNewCategory(0);
				tr511e.setNewSubCategory(0);
				tr511e.setOperationType(0);
				Long categoryBaja=null;
				Long subCategoryBaja=null;
				
				Operacion_comercialLocal operacion_comercial = null;
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					//Correccion AT-2353 No se envia desconfiguracion automatica - 17/06/2009
					opComercialaux = operacion_comercialKey.opco_id.intValue();
					operacion_comercial = producto_servicio_peticionLocal.getOperacion_comercial();
					if(operacion_comercial.getOpco_rev()!= null)
						opComercial = operacion_comercial.getOpco_rev().intValue();
					
					//opComercialaux=producto_servicio_peticionLocal.getOperacion_comercial().
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
						psId=productoServicoKey.ps_id;
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();

						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue()){
							
							if (!ComunInterfaces.operacionBCP.equals(operacion_comercial.getOpco_tipo())){
							//At-2067 - 14 abril 2009
								psLineaBasicaId=psId;
								boolean cruzada= esCruzada(idPeticion);
								log.debug("Peticion :.."+act.getNumeroPeticion()+"  y es Cruzada :.."+ cruzada+", opComercialaux = "+opComercialaux+", opComercial = "+opComercial);
//								Correccion AT-2353 No se envia desconfiguracion automatica - 17/06/2009
								if(opComercialaux == 8 && cruzada){
									opComercial=6; //caso desconfigurar 
										
								}
								if(opComercialaux == 8 && !cruzada){
									opComercial=8; 
								}
								
								if(opComercial >0){
									/////
									if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
										Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
										if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()!=estadoCierreError){
											opComercial=0;
											log.debug("Se settea en Operation Type en cero ya que el ps no esta cancelado:.."+ opComercial);
											tr511e.setOperationType(opComercial);
										}else{
											log.debug("Se settea en Operation Type :.."+ opComercial);
											tr511e.setOperationType(opComercial);
										}
									}else{
										opComercial=0;
										log.debug("Se settea en Operation Type en cero ya que el ps no esta cancelado:.."+ opComercial);
										tr511e.setOperationType(opComercial);
									}
									
									tr511e.setNewCategory(producto_servicio_peticionLocal.getTip_pro_cmr_cd());
									tr511e.setNewSubCategory(producto_servicio_peticionLocal.getSbt_pro_cmr_cd());
								}else{	
									return 3;	
								}
								cantPsLineaBasica++;
							}else{
								categoryBaja=producto_servicio_peticionLocal.getTip_pro_cmr_cd();
								subCategoryBaja=producto_servicio_peticionLocal.getSbt_pro_cmr_cd();
							}
						}else {
															
								if(producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == cambioNumero ||opComercial >0){
									log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar/desconfigurar ya que es un cambio de numero op no tiene operacion de reversa");
									
								}else{
									
									srs = new SpecialServicesRequest();
									if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
										Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
										if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()!=estadoCierreError){
											log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que no esta Cancelado");
											srs=null;
										}else{											
											srs.setSpecialService(psId.toString());
											srs.setAction(opComercial);										
										}
																			
									}else{
										log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que no esta Cancelado");
										srs=null;
									//	srs.setSpecialService(psId.toString());
										//srs.setAction(opComercial);					
										
									}
									
									if(srs!=null){
										psServiciosSuplementarios.add(srs);
										log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());
									}
								}
						}						
					}
				}
				
				if(categoryBaja!=null &&subCategoryBaja!=null ){
					log.debug("Seteando los valores de reversa de Cambio de Producto Peticion:" + idPeticion);
					tr511e.setNewCategory(categoryBaja);
					tr511e.setNewSubCategory(subCategoryBaja);
					
				}
				
				if (cantPsLineaBasica > 1) {
					log.error("Hay mas de 1 PS con famila " + ComunInterfaces.familiaPcLinea + ". No se envia mensaje");
					return 1;
				}
				
				if (cantPsLineaBasica == 0 && psServiciosSuplementarios.size() == 0) {
					log.error("No hay PSs. No se envia mensaje" + ComunInterfaces.familiaPcLinea);
					return 1;
				}
				
				log.debug( "Cant de Servicios Suplementarios: " + psServiciosSuplementarios.size());	
				
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr511e.setId(String.valueOf(IdCorrelativo));
				tr511e.setAtiempoRequestNumber(act.getNumeroPeticion());
				tr511e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());
				
				// Seteo RequestDate con la fecha de ingreso de la peticion en ATIS
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				tr511e.setRequestDate(petAtis.getFec_sct_pet_ff()); 

				Recursos_linea_basicaLocal rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());

				if (rlb.getOds_apsc()!=null) {
					tr511e.setOdsNumber(rlb.getOds_apsc().intValue());
				}else {
					log.error("No hay ODS." + ComunInterfaces.familiaPcLinea);
					tr511e.setOdsNumber(0);
				}
				
				
				boolean esCruzada=false;
				 if( rlb.getCentral_connection()!=null){
				 	esCruzada = rlb.getCentral_connection().intValue()==1;
				}
				
				boolean esTraslado = tieneTrasladoLB(idPeticion);
				boolean esCambioNumero = tieneCambioNumeroLB(idPeticion);
				log.debug("esCruzada .."+esCruzada);
				log.debug("esTraslado .."+esTraslado);
				log.debug("esCambioNumero .."+esCambioNumero);
				log.debug("rlb.getTelefono_asg() .."+rlb.getTelefono_asg());
				log.debug("rlb.getTelefono_ant() .."+rlb.getTelefono_ant());
				
				
				asignarTelefonosGranite( tr511e, act, false);
	
				tr511e.setSpecialServices(psServiciosSuplementarios);

				
				int cantPBX = cantPbx(peticionLocal , idPeticion);
				tr511e.setRequestPbx(cantPBX);

				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());

				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
				ConfiguracionAutomaticaSTBMQ configuracionAutomaticaSTBMQ= new ConfiguracionAutomaticaSTBMQ();
				configuracionAutomaticaSTBMQ.enviarMensaje(tr511e);
				
				log.debug("Se setearon tr511e.setPhoneNumber .."+tr511e.getPhoneNumber());
				log.debug("Se setearon tr511e.setPreviousNumber .."+tr511e.getPreviousNumber());
				return 2;
			}catch (Exception e)
			{
				log.error("Error al enviar Configuracion .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}		
		
//		AT-1995- agonz - 25 marzo 2009 
		public boolean esCruzada(Long nroPeticion)throws ATiempoAppEx{
			try {
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				boolean cruzada = false;
				PeticionKey peticionKey = new PeticionKey(nroPeticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);			
				Recursos_linea_basicaLocal rbLocal = (Recursos_linea_basicaLocal) peticionLocal.getRecursos_linea_basica().iterator().next();
				 if (rbLocal!=null && rbLocal.getCentral_connection()!=null && rbLocal.getCentral_connection().intValue()== 1) {
					cruzada = true;
				}	
				log.debug("Es Cruzada = "+cruzada);
				return cruzada;
			}
			catch(Exception e){
				e.printStackTrace();
				log.debug("Exception",e);
				throw new ATiempoAppEx(e.getMessage());
			} 
		}
		
		/**
		 * @author juan 
		 */
		
		public boolean tieneCambioNumeroLB(Long idPeticion) throws ATiempoAppEx{		
			boolean resultado=false;
			try {
				InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
			
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++){
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				log.debug("Operacion Comercial..: "+ psDto.getIdOpComercial()+" Peticion..:" + idPeticion +" Peticion..:" +psDto.getIdFaps().intValue());
				if ( psDto.getIdFaps().intValue()== familiaPcLinea && psDto.getIdOpComercial().longValue() == cambioNumero) {
						resultado= true;	
				}
			}
			} catch (ATiempoAppEx e) {
				log.debug("Error validando si tiene cambio de Numero..:",e);
				e.printStackTrace();
			}
			log.debug(" tieneCambioNumeroLB()..:" + resultado);
			return resultado;	
		} 
		

		public boolean tieneAltaComun(Long idPeticion) throws ATiempoAppEx{
			

			
			PeticionLocal  peticionLocal;
			try {
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			String opco_tipo = null;
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				opco_tipo = operacion_comercialLocal.getOpco_tipo();
				log.debug("Operacion Comercial..: "+ operacion_comercialLocal.getOpco_nombre()+" Peticion..:" + idPeticion +" tipo de operacion Comercial" + opco_tipo);
				
				if(operacion_comercialLocal.getOpco_tras()==null && opco_tipo!= null &&opco_tipo.equals("A"))				{
					return true;
				}
			}
			} catch (FinderException e) {
				log.debug("No se pudo validar si es alta comun",e);
				throw new ATiempoAppEx ("No se pudo validar si es alta comun");
				
			}catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}
			return false;
			}
		
		public boolean tieneBajaComun(Long idPeticion) throws ATiempoAppEx{
			

			
			PeticionLocal  peticionLocal;
			try {
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
			String opco_tipo = null;
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				opco_tipo = operacion_comercialLocal.getOpco_tipo();
				log.debug("Operacion Comercial..: "+ operacion_comercialLocal.getOpco_nombre()+" Peticion..:" + idPeticion +" tipo de operacion Comercial" + opco_tipo);
				
				if(opco_tipo!= null &&opco_tipo.equals("B")){
					return true;
				}
			}
			} catch (FinderException e) {
				log.debug("No se pudo validar si es alta comun",e);
				throw new ATiempoAppEx ("No se pudo validar si es alta comun");
				
			}catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}
			return false;
			}
		
		
		/**
		 * @return true en caso de  que la peticion sea de Traslado
		 * @throws ATiempoAppEx
		 */
		
		public boolean tieneTrasladoLB(Long idPeticion) throws ATiempoAppEx{		
			boolean esTraslado = false;
			try {

				InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
				
				Long idOpComercial = null;
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++){
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
					idOpComercial = psDto.getIdOpComercial();
					log.debug("Operacion Comercial..: "+ idOpComercial+" Peticion..:" + idPeticion +" Peticion..:" +psDto.getIdFaps().intValue());
					if (psDto.getIdFaps().intValue()== familiaPcLinea &&( idOpComercial.longValue() == altaTraslado|| idOpComercial.longValue() == bajaTraslado)) {
						esTraslado= true;	
					}
				}
				
			} catch (ATiempoAppEx e) {
				log.debug("Error validando si tiene Traslado..:",e);
				e.printStackTrace();
			}
			log.debug(" tieneTraslado()..:" + esTraslado);
			return esTraslado;	
		}
		
		/**
		 * @return true en caso de  que la peticion tiene cambio de producto
		 * @throws ATiempoAppEx
		 */
		
		public boolean tieneCambioProducto(Long idPeticion) throws ATiempoAppEx{		
			boolean esCambioProducto = false;
			try {

				InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
				
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++){
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
					log.debug("Operacion Comercial..: "+ psDto.getIdOpComercial()+" Peticion..:" + idPeticion +" Peticion..:" +psDto.getIdFaps().intValue());
					
					
					
					if ( (psDto.getIdFaps().intValue()== familiaPcLinea || psDto.getIdFaps().intValue()== familiaIP )&& (ComunInterfaces.operacionACP.equals(psDto.getTipoOperacionComercial())||ComunInterfaces.operacionBCP.equals(psDto.getTipoOperacionComercial()))) {
						esCambioProducto= true;
						
					}
				}
				
			} catch (ATiempoAppEx e) {
				log.debug("Error validando si tiene Cambio de Producto..:",e);
				
			}
			log.debug("Peticion: "+idPeticion +"Tiene Cambio de Producto"+ esCambioProducto);
			return esCambioProducto;	
		}
		
		
		public boolean esBajaLB(Long idPeticion) throws ATiempoAppEx{		
			boolean esBaja = false;
			try {

				InfoComunColombiaBusinessInterface infoComunColombiaBI;
				infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(idPeticion);
				
				for (int i=0; listadoPS!=null && i<listadoPS.size(); i++){
					ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
					log.debug("Operacion Comercial..: "+ psDto.getIdOpComercial()+" Peticion..:" + idPeticion +" Peticion..:" +psDto.getIdFaps().intValue());
					if ((psDto.getIdFaps().intValue()== familiaPcLinea || psDto.getIdFaps().intValue()== familiaIP ) && psDto.getIdOpComercial().longValue() == bajaSTB) {
						esBaja= true;	
					}
				}
				
			} catch (ATiempoAppEx e) {
				log.debug("Error validando si tiene Baja..:",e);
				
			}
			log.debug(" tieneTraslado()..:" + esBaja);
			return esBaja;	
		}
		
		
	
		public boolean enviaConfiguracionAutomaticaSTB (ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				Long idPeticion = act.getNumeroPeticion();
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				
				TR511E tr511e = new TR511E();
				/*
				 * GESTION DE BAJAS
				 */
				if(peticionLocal.getTica_id().equals("B")){
					tr511e.setInterfaz("BajaSIRSConfig");
				}else{
					tr511e.setInterfaz("AltaSIRSConfig");
				}

				tr511e.setSource("ATIEMPO");
				tr511e.setVersion("1.0");
				tr511e.setDestination("ESB");
				

				ArrayList psServiciosSuplementarios = new ArrayList();
				Long psLineaBasicaId=null;
				Long psId=null;
				int cantPsLineaBasica = 0;
				SpecialServicesRequest srs = null;
				int opComercial=0;
				
				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		
				// no obligatorios, los cargo en 0 para que en la tr aparezca el tag
				tr511e.setNewCategory(0);
				tr511e.setNewSubCategory(0);
				tr511e.setOperationType(0);
				boolean esBaja=false;
				int cantPBX = cantPbx(peticionLocal , idPeticion);
				boolean esTraslado = tieneTrasladoLB(idPeticion);
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					
					opComercial = operacion_comercialKey.opco_id.intValue();
					
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
						psId=productoServicoKey.ps_id;
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() ){
							if (!ComunInterfaces.operacionBCP.equals(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo())){
							//At-2067 - 14 abril 2009
								psLineaBasicaId=psId;
								boolean cruzada= esCruzada(idPeticion);
								if(opComercial==8 && cruzada){ 
									opComercial=1;//AT-2005 setear el valor de alta
								}	
								
								if(esTraslado && tr511e.getInterfaz().equals("AltaSIRSConfig")){
									opComercial=1; //Si es pbx y es una alta se configura
								}
								
								tr511e.setOperationType(opComercial);
								tr511e.setNewCategory(producto_servicio_peticionLocal.getTip_pro_cmr_cd());
								tr511e.setNewSubCategory(producto_servicio_peticionLocal.getSbt_pro_cmr_cd());
								if (opComercial==6)
									esBaja=true;
								
								cantPsLineaBasica++;
							}
						}else {
															
								if(producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == cambioNumero ){
									log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que es un cambio de numero");
								}else{
									
									srs = new SpecialServicesRequest();
									if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
										Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
										if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError){
											log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que esta Cancelado");
											srs=null;
										}else{											
											srs.setSpecialService(psId.toString());
											srs.setAction(opComercial);										
										}
																			
									}else{
										srs.setSpecialService(psId.toString());
										srs.setAction(opComercial);					
										
									}
									
									if(srs!=null){
										psServiciosSuplementarios.add(srs);
										log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());
									}
								}
						}						
					}
				}
				
				if (cantPsLineaBasica > 1) {
					log.error("Hay mas de 1 PS con famila " + ComunInterfaces.familiaPcLinea + ". No se envia mensaje");
					return false;
				}
				
				if (cantPsLineaBasica == 0 && psServiciosSuplementarios.size() == 0) {
					log.error("No hay PSs. No se envia mensaje" + ComunInterfaces.familiaPcLinea);
					return false;
				}
				
				log.debug( "Cant de Servicios Suplementarios: " + psServiciosSuplementarios.size());	
				
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr511e.setId(String.valueOf(IdCorrelativo));
				
				tr511e.setAtiempoRequestNumber(act.getNumeroPeticion()); 
				tr511e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());
				
				// Seteo RequestDate con la fecha de ingreso de la peticion en ATIS
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				tr511e.setRequestDate(petAtis.getFec_sct_pet_ff()); 
										
				Recursos_linea_basicaLocal rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				
				
				if (rlb.getOds_apsc()!=null) {
					tr511e.setOdsNumber(rlb.getOds_apsc().intValue());
				}else {
					log.error("No hay ODS. " + ComunInterfaces.familiaPcLinea);
					tr511e.setOdsNumber(0);
				}				
				cantPBX = cantPbx(peticionLocal , idPeticion);
				
				asignarTelefonosGranite( tr511e, act, esBaja);
														
				tr511e.setSpecialServices(psServiciosSuplementarios);
				tr511e.setRequestPbx(cantPBX);
				
				if(cantPBX > 0){
					PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
					PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(act.getNumeroPeticion());
					InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
					
					String nroPiloto = peticionDTO.getNroPiloto();
					
					if(nroPiloto != null && !nroPiloto.trim().equals("")){
						try{
							Integer piloto = new Integer(nroPiloto);
							tr511e.setPreviousNumber(new Integer(nroPiloto));
						}catch(NumberFormatException nfe){
							log.error("Error al convertir a numero el piloto: " + nroPiloto);
						}
					}
				}
								
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());
	
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
				ConfiguracionAutomaticaSTBMQ configuracionAutomaticaSTBMQ= new ConfiguracionAutomaticaSTBMQ();
				configuracionAutomaticaSTBMQ.enviarMensaje(tr511e);
				log.debug("Se setearon tr511e.setPhoneNumber .."+tr511e.getPhoneNumber());
				log.debug("Se setearon tr511e.setPreviousNumber .."+tr511e.getPreviousNumber());
								
				return true;
				
			}catch (Exception e){
				log.error("Error al enviar Configuracion Automatica .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}
		
		public void asignarTelefonosGranite(TR511E tr511e,ActividadEJBDTO act,boolean esBaja) throws ATiempoAppEx { 
			
			
			try{
				Long idPeticion = act.getNumeroPeticion();
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
						
				Recursos_linea_basicaLocal rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				
				//Creo las variables que se van a cargar
				int PhoneNumber=0;
				int PreviousNumber=0;
				
				int valor=99;
				// Cargo las variables a tener en Cuenta
				
				boolean esTraslado = tieneTrasladoLB(idPeticion);
				
				if (esTraslado){
					valor=1;
				}
				
				boolean esCambioProducto = tieneCambioProducto(idPeticion);
				if (esCambioProducto){
					valor=3;
				}
				boolean esCambioNumero = tieneCambioNumeroLB(idPeticion);
				
				if (esCambioNumero){
					valor=2;
				}
				
				int cantPBX = cantPbx(peticionLocal , idPeticion);
				String reversa=act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa);
				boolean esCruzada=false;
						
				 if( rlb.getCentral_connection()!=null){
				 	esCruzada = rlb.getCentral_connection().intValue()==1;
				}
				
				log.debug("esCruzada .."+esCruzada);
				log.debug("esBaja .."+esBaja);
				log.debug("esTraslado .."+esTraslado);
				log.debug("esCambioNumero .."+esCambioNumero);
				log.debug("REVERSA .."+ reversa);
				
				/// Fin del Cargo de Variales
				if(cantPBX==0||(cantPBX>0 && esTraslado) ){
					switch (valor) {
						
						case 1://Es traslado con Cambio de Numero
							if (!"S".equalsIgnoreCase(reversa)){
								if(!esBaja){
									log.info("Alta Traslado");
								 PhoneNumber= rlb.getTelefono_asg().intValue();
								 PreviousNumber=0;
								}else{
									log.info("Baja Traslado");
								 PhoneNumber=rlb.getTelefono_ant().intValue();
								 PreviousNumber=0;
								}
							}else{
								log.info("Reversa del Alta de traslado");
								PhoneNumber= rlb.getTelefono_asg().intValue();
								PreviousNumber=0;
								
							}
							break;
						
						case 2:
							log.info("Es Cambio de Numero  solo"); 
							
							if (!"S".equalsIgnoreCase(reversa)){
								if(!esCruzada){
									PhoneNumber= rlb.getTelefono_asg().intValue();
									PreviousNumber=rlb.getTelefono_ant().intValue();
								}else{
									if(!esBaja){
										//TODO: 05012010 - Raul Triviño - Ajuste Error envio tr511
										String phoneNumberAux = peticionLocal.getNum_ide_nu_stb();						
										PhoneNumber= Integer.parseInt(phoneNumberAux);
										PreviousNumber=0;
										//End TODO

									}else{
										PhoneNumber= rlb.getTelefono_ant().intValue();
										PreviousNumber=0;
									}
								}
								
							}else{
									//Reversa del Cambio de Numero
								if(!esCruzada){
									PreviousNumber= rlb.getTelefono_asg().intValue();
									PhoneNumber=rlb.getTelefono_ant().intValue();
								}else{
									 PhoneNumber= rlb.getTelefono_asg().intValue();
									 PreviousNumber=0;
								}
							}
							break;		
							
						case 3:
							log.info(" es cambio de Producto");
//							Telefono a consultar por defecto
							String phoneNumberAux = peticionLocal.getNum_ide_nu_stb();
							
								if (!"S".equalsIgnoreCase(reversa)){
									PhoneNumber= Integer.parseInt(phoneNumberAux);
									PreviousNumber=0;
								}else{
									PhoneNumber= Integer.parseInt(phoneNumberAux);
									PreviousNumber=0;
								}
							
							
							break;
						case 99:
							if (!"S".equalsIgnoreCase(reversa)){
								if (rlb.getTelefono_asg()!=null && !esBaja) {
									log.info("Es un Alta");
									PhoneNumber=rlb.getTelefono_asg().intValue();
								}else {
									log.info("Es una Baja");								
									if (rlb.getTelefono_asg() != null && rlb.getTelefono_asg().longValue() != 0){
										PhoneNumber=rlb.getTelefono_asg().intValue();
									}else{
										PhoneNumber=rlb.getTelefono_ant().intValue(); 
									}									
									break;
								}							
							}else{
								if (!esBaja) {
									//es un Alta de Linea en reversa 
									PhoneNumber=rlb.getTelefono_asg().intValue();
								}
							}
					default:
						break;
					}
				}else{
					/// Es PBX y no es traslado de PBX
					if(valor!=3&& !esBaja){
						valor=4;//Alta PBX
						
					}
					if(esBaja){
						valor=5;//Baja PBX
					}
					
					switch (valor) {
					
					case 3://Es cambio de Producto
						log.info("Cambio de Producto con PBX");
						String phoneNumberAux1 = peticionLocal.getNum_ide_nu_stb();

						PreviousNumber=Integer.parseInt(retornarNumeroPBX(idPeticion));	
						log.info("Cambio de Producto con PBX seteando el previus "+ PreviousNumber);
							if (!"S".equalsIgnoreCase(reversa)){
								PhoneNumber= Integer.parseInt(phoneNumberAux1);
								
							}else{
								PhoneNumber= Integer.parseInt(phoneNumberAux1);
								
							}
						break;
					
					case 4://Alta PBX
							
						if (!"S".equalsIgnoreCase(reversa)){
							log.info("Alta de PBX");								
								 PhoneNumber= rlb.getTelefono_asg().intValue();
								 PreviousNumber=0;
						}else{
							log.info("Reversa del Alta Troncal PBX");
								 PhoneNumber= rlb.getTelefono_asg().intValue();
								 PreviousNumber=0;
						}
						break;		
						
					case 5:
							log.info("Baja PBX");
							String phoneNumberAux = peticionLocal.getNum_ide_nu_stb();						
							PhoneNumber= Integer.parseInt(phoneNumberAux);
							PreviousNumber=0;
						
						break;
					
				default:
					break;
				}
					
					
				}
				
				//TODO: Se modifica para obtener siempre el campo Num_ide_nu_stb de acuerdo a solicitud del Lun 28/12/2009 20:11
				String phoneNumberAux = peticionLocal.getNum_ide_nu_stb();
				//End TODO
				
				tr511e.setPhoneNumber(PhoneNumber);
				tr511e.setPreviousNumber(PreviousNumber);			

				log.debug("Se setearon tr511e.setPhoneNumber .."+tr511e.getPhoneNumber());
				log.debug("Se setearon tr511e.setPreviousNumber .."+tr511e.getPreviousNumber());
				
				
				
			}catch (Exception e){
				log.error("Error al enviar Configuracion Automatica .",e);
				throw new ATiempoAppEx (ATiempoAppEx.EXCEPTION,e);
			}
			
			
		}
		

		public void enviarRecursoSTB2(Long peticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx{
			
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Caracteristicas_lineaLocalHome caracteristicasLocalHome = (Caracteristicas_lineaLocalHome)HomeFactory.getHome(Caracteristicas_lineaLocalHome.JNDI_NAME);

				TR010E tr010e = new TR010E();
				boolean tienePCNaked = false;
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				PeticionKey peticionKey = new PeticionKey(peticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				Long ps_id=null;

				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
				
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();)
				{
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					if(peticionesDelegate.pasaPSyOcXActividad(peticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,idActividadFlujo))
					{
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						//se agrega familia PS/PC NAked
						
//						Se agrega la nueva familia PC / PS NAKED para poder generar la TR510E
						if(fPSK.faps_id.intValue() == ComunInterfaces.familiaPcBANaked){
							tienePCNaked = true;
						}
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() 
								|| fPSK.faps_id.intValue() == ComunInterfaces.familiaBandaAnchaNaked
								|| fPSK.faps_id.intValue() == ComunInterfaces.familiaPcPsBANaked){
							ps_id=productoServicoKey.ps_id;
							break;
						}										
					}
				}
				
				if(ps_id==null && !tienePCNaked){
					log.debug("Ningun Ps tipo PC de la peticion: "+peticionLocal.getNum_ide_nu_stb()+"  invoca la actividad Asignar Recurso para Granite. No se envia mensaje a APSC.");
					return;
				}else{
					if(tienePCNaked){
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("PS_DEFAULT_VIRTUAL");
						ps_id = new Long(propertiesDBLocal.getValor());
					}
				}

				
				Operacion_comercialKey operacion_comercialKey = (Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
				
				// Se modifica el objeto TR010E, para ser enviado
				tr010e.setId(IdCorrelativo.toString());
				tr010e.setPathType(peticionLocal.getDir_tip_via_1());
				tr010e.setPathNumber(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_1())));
				tr010e.setFirstPathCharacters(peticionLocal.getDir_lt1_via_1());
				tr010e.setSecondPathCharacters(peticionLocal.getDir_lt2_via_1());
				tr010e.setPathZone(peticionLocal.getDir_zon_via_1());
				tr010e.setPathType2(peticionLocal.getDir_tip_via_2());
				tr010e.setPathNumber2(Integer.parseInt(Utiles.ConversorCero(peticionLocal.getDir_nro_via_2())));
				tr010e.setFirstPathCharacters2(peticionLocal.getDir_lt1_via_2());
				tr010e.setSecondPathCharacters2(peticionLocal.getDir_lt2_via_2());
				String dir_zon_via_2 = peticionLocal.getDir_zon_via_2();
				try
				{
					if(dir_zon_via_2!=null && !dir_zon_via_2.trim().equals("")) {
						tr010e.setPathZone2(dir_zon_via_2.charAt(0));
					}else{
						tr010e.setPathZone2('-');
					}
					
				}
				catch(Exception e)
				{
					log.info("El valor de Dir Zon Via 2 no es valido:"+dir_zon_via_2);
				}

				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				
				//Cambio realizado el 180507 : : Se envia al APSC el Numero de Peticion en ves del Numero Atis
				tr010e.setAtisRequestNumber(peticionKey.peti_numero.longValue());
				
				//Para la asignacion de recursos el numero debe ir blanco, pues todavia no se me asigna un numero.
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}	
				tr010e.setPhoneNumber(Integer.parseInt(phoneNumber));
						
				String nombre = Utiles.sinNull(peticionLocal.getNom_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getPri_ape_ds().trim(),"")+" "+Utiles.sinNull(peticionLocal.getSeg_ape_ds().trim(),"");
				
				if (nombre!=null && ! nombre.trim().equals (""))
				{
					if(nombre.length()>40)
						nombre=nombre.substring(0,39);
					tr010e.setClientName(nombre);
				}else{
					tr010e.setClientName("-");
				}
				
				
				String clientDocument = Utiles.sinNull(peticionLocal.getNum_doc_cli_cd(),"")+ Utiles.sinNull(peticionLocal.getDig_ctl_doc_cd(),"");
				
				if (clientDocument!=null && ! clientDocument.trim().equals ("")) 
	                        {
					if (clientDocument.length()>20){
						clientDocument=clientDocument.substring(0,20);
					}
				}
				else{
					clientDocument="-";
				}
				
				tr010e.setClientDocument(clientDocument);
				
				tr010e.setProductServiceCode(ps_id.longValue());
				tr010e.setComercialOperation(operacion_comercialKey.opco_id.longValue());
				
				String installAddress = Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(peticionLocal.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(peticionLocal.getNom_slo_no(),"");
				if(installAddress==null || installAddress.trim().equals("")) {
					installAddress="-";
				}
				tr010e.setInstallAddress(installAddress);
				
				String address = Utiles.sinNull(peticionLocal.getNum_cal_nu(),"");
				String addressChar = "";
				String addressNumber = "";
				
				try{

					if (address!=null && !address.trim().equals("")){
		
							addressNumber= (String) Utiles.getSacarLetraNumero(address).get("numero");
							addressChar = (String) Utiles.getSacarLetraNumero(address).get("letra");
					}
					else{
						addressNumber="-";
						addressChar = "-";
					}			
					
					tr010e.setAddressCharacters(addressChar.charAt(0));
					if(addressNumber!=null && addressNumber.length()>3)
						addressNumber=addressNumber.substring(0,3);
										
					try{
						int addressNumberInt=Integer.parseInt(addressNumber);
						addressNumber=addressNumberInt+"";
					}catch(NumberFormatException e){
						log.info("El address number no tiene un formato de número enviarRecursoSTB2(): "+e);
						addressNumber="0";
					}
					tr010e.setAddressNumber(addressNumber);
					
				}catch(Exception e){
					
					log.info("El valor de Num cal nu no es valido:"+dir_zon_via_2);
				}

				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr010e.setDepartment(Integer.parseInt(departamentoKey.cod_dpt));
				
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr010e.setCity(Integer.parseInt(localidadKey.cod_loc));

				tr010e.setUseType(producto_servicio_peticionLocal.getCod_tip_uso().longValue());
//				tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
//				tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
			
//				Requerimiento Naked: Se calcula la caracteristica y subcaracteristica de la tabla Caracteristicas_linea: CR7
				try {
					Caracteristicas_lineaKey key = new Caracteristicas_lineaKey(ps_id);
					Caracteristicas_lineaLocal caracteristicasLocal = caracteristicasLocalHome.findByPrimaryKey(key);
					if(caracteristicasLocal.getCaracteristica() != null && caracteristicasLocal.getSub_caracteristica() != null){
						tr010e.setComercialProductType(caracteristicasLocal.getCaracteristica().longValue());
						tr010e.setComercialProductSubType(caracteristicasLocal.getSub_caracteristica().longValue());
					}else{
						tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
						tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
					}
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
//					Requerimiento Naked: Si no encuentra registro en la tabla se deja el valor que se trae de ATIS: CR7
					log.debug("No se encuetra el PS registrado en la tabla Caracteristica_Linea se setea el valor que llega de ATIS");
					tr010e.setComercialProductType(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
					tr010e.setComercialProductSubType(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
				}			
	                        // fbd: 26-04-2007: 
	                        // - como no se podia enviar en estos campos la "cedula catastral" se puserion de acuerdo
	                        //   enviar informacion adicional de la direccion.
	                        // - pero APSC no implemento este cambio y da problemas
	                        //
	                        // solucion: se manda temporalmente campos vacios hasta saber si mandamos la 
	                        // famosa "cedula catastra" o el truco de la direccion
	                                           
				tr010e.setPlaceNumber1(peticionLocal.getDir_nro_lg1());
				tr010e.setPlaceNumber2(peticionLocal.getDir_nro_lg2());
				tr010e.setPlaceNumber3(peticionLocal.getDir_nro_lg3());
				tr010e.setPlaceType1(peticionLocal.getDir_tip_lg1());
				tr010e.setPlaceType2(peticionLocal.getDir_tip_lg2());
				tr010e.setPlaceType3(peticionLocal.getDir_tip_lg3());
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				
				int areaPhone= 0;
				int numeroPhone= 0;
				if (phoneNumber.length()>1){
					areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
					numeroPhone=Integer.parseInt(phoneNumber.substring(1));
				}
				mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
				mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
				
				AsignacionRecursosSTBMQ asignacionRecursosSTBMQ = new AsignacionRecursosSTBMQ();
				asignacionRecursosSTBMQ.enviarMensaje(tr010e);
				
					
				} catch (NumberFormatException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				} catch (CreateException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
				} catch (FinderException e) {
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				} catch (NamingException e) {
					log.error("Creacion de Local Home Nulls",e);
					throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
				}
				catch(Exception e)
				{
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
			
		}
		

		public void recepcionConfiguracionAutomaticaSTB(TR511S tr511s) throws ATiempoAppEx {
		
			BackendExecution bExecution = null;
				
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr511s, log);				
				bExecution.setIdMensajeOp(tr511s.getId());
				bExecution.setNumPetAtiempo(new Long(tr511s.getAtiempoRequestNumber()));
				bExecution.startOperation();
				
				String codAct = "";
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				
				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr511s.getId()));
			    Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
		            
			   try
			   {
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
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
					log.debug(
						"No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr511s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr511s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				
				Mensaje_estadoKey  mensaje_estadoKey= (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug(
						"Es estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr511s));
					return;
				}
				
				//Obtengo el codigo de la actividad generadora
				codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();

				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
				
				if ( recursosLineaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos);
				}else{
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}			
				
				recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
				recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);


				if(tr511s.getOdsNumber() > 0)//AT-2128
						recursos_linea_basicaLocal.setOds_apsc(new Long(tr511s.getOdsNumber()));//At-2018
										
				boolean hayErrorServicioSuplementario=false;
				String descripErrores="";
				if ( tr511s.getSpecialServices()!=null){
					for (Iterator iter = tr511s.getSpecialServices().iterator(); iter.hasNext();)
					{
						SpecialServicesResponse specialService = (SpecialServicesResponse) iter.next();
					
						if(specialService.getActionResultNumber()!=0){
							String desc=specialService.getActionResultDescription();
							descripErrores+=desc;
							hayErrorServicioSuplementario=true;
						}
							if(iter.hasNext())
								descripErrores+=",";
					}
				}
		
				if (tr511s.getErrorCode() > 0 || hayErrorServicioSuplementario){
					
					PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(mensajeEstadoLineaLocal.getPeti_numero()));
					for(Iterator iterator=petLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
					{
						Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal) iterator.next();
						Operacion_comercialLocal opco=psp.getOperacion_comercial();
						Familia_producto_servicioKey familiakey = psp.getFamiliaKey();
						if(familiakey.faps_id.equals(new Long(ComunInterfaces.familiaPcLinea))){
							if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null ))//ALTA
								petLocal.setTica_id("A");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
								petLocal.setTica_id("T");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
								petLocal.setTica_id("P");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
								petLocal.setTica_id("R");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
								petLocal.setTica_id("S");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
								petLocal.setTica_id("B");
							else 
								petLocal.setTica_id("P");
						}
					}
					
					
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					mensajeEstadoLineaLocal.setDesc_error(tr511s.getErrorMessage());

					log.debug("Entramos a la Generacionde Actividades de Asignacion Manual Cod.Actividad : " + codAct);
					mensajeEstadoLineaLocal.setF_reference_14(mensajeManualLocal);

					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					
					//Correccion AT-1963 Y AT-2000
					if (tr511s.getErrorCode()==10003){
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "NO");
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}else{
						if(actDto.getCodigoActividad().equals("")|| actDto.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA"))){
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_DESCONFIG_STB);
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_desconf_stb, "DESCSTB");
							log.debug("Se va a desconfigurar Manual");
						}else{
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
							actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
							log.debug("Se va a configurar Manual");
						}
					}
					if(tr511s.getErrorCode() > 0 && hayErrorServicioSuplementario){
						
						actDto.setObservacion("Error en la Configuracion Automatica de STB. " + tr511s.getErrorMessage()+". "+ tr511s.getErrorCode()+"  "+  descripErrores + ". ");
					}else{
						if(hayErrorServicioSuplementario){
						
							actDto.setObservacion("Error en la Configuracion Automatica de STB. " + descripErrores + ". ");
						}else{
							actDto.setObservacion("Error en la Configuracion Automatica de STB. " + tr511s.getErrorMessage()+". "+ tr511s.getErrorCode()+ ". ");
						
						}
					}
						
					actividadEJB.terminar(actDto);
	 
					return;
					
				}
				
				//Recupero peticion
				PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();

				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				
				PeticionesInterfaces pI = new PeticionesDelegate();
				boolean trasladoConOrigenyDestinoIguales = pI.esTrasladoConIgualOrigenYDestino(mensajeEstadoLineaLocal.getPeti_numero());
				boolean trasladoConIgualLen = pI.esTrasladoConIgualLen(mensajeEstadoLineaLocal.getPeti_numero());
				//Si se hizo una baja por traslado con cambio solo de len, se envia el alta
				if(trasladoConOrigenyDestinoIguales && !trasladoConIgualLen && peticionLocal.getTica_id().equals("A")){
					RecursosInterfaces rBAI= new RecursosDelegate();
					//Se envía la configuración
					boolean resp =rBAI.enviaConfiguracionAutomaticaSTB(actDto);
					if (resp){
						actDto.setObservacion("Se envia el mensaje de Configuración Automatica STB. Solo cambio de Len");
						peticionLocal.setTica_id("P");
						return;
					}else{
						actDto.setObservacion("No se envia el mensaje de Configuración Automatica STB. Revise si no hay PSs, si no hay tel del abonado, si hay mas de 1 PS con famila");					
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
						actDto.setRealizarTerminoInmediato(true);	
						return;
					}
				}
	
				actividadEJB.terminar(actDto);
			
				} catch (NumberFormatException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				
				} catch (CreateException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
						
				} catch (FinderException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				
				} catch (TnProcesoExcepcion e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
					
				}
				catch (NamingException e)
				{
					log.error(" Creacion de Local Home Nulls",e);
					throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
				}
				catch(Exception e)
				{
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
				finally{  
					bExecution.endAll();
					
				}
		}

		/* agonz
		 * Asignacion de Recursos LB (Reversa)
		 */
		public void envioLiberacionRecursosGranite(Long peticion, String idActividad, Integer actividadFlujo) throws ATiempoAppEx {
			try{

				//	TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009	
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionesServicesLocalHome peticionesServicesLocalHome = (PeticionesServicesLocalHome)  HomeFactory.getHome(PeticionesServicesLocalHome.JNDI_NAME);
				Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);		
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
	
				TR513E tr513e = new TR513E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionKey peticionKey = new PeticionKey(peticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				
				//Object PsVsOcVO
				PeticionesServicesLocal peticionesServicesLocal = peticionesServicesLocalHome.create();
				ArrayList flujoPeticiones = peticionesServicesLocal.listaPsDePeticionQuePasaPorActividad(peticion, actividadFlujo);
				PsVsOcVO psprim = (PsVsOcVO) flujoPeticiones.iterator().next();
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psprim.getPsId()));
				 
				//AT-1426 - Stall ConcreteProducto_servicio.getEstado_ps_peticion() - guido - 20-Jun-2008 - Inicio
				
				String codCausa = null;
				Collection coll1 = peticionLocal.getProducto_servicio_peticion();
				int size2 = -1;
				Iterator itPsp = coll1.iterator();
				while (itPsp.hasNext()) {
					Producto_servicio_peticionLocal psp =(Producto_servicio_peticionLocal) itPsp.next();
					if (psprim.getPsId().equals(psp.getPsId())) {
						Collection coll2 = psp.getEstado_ps_peticion();
						size2 = coll2.size();
						Estado_ps_peticionLocal estado_ps_peticionLocal =(Estado_ps_peticionLocal) coll2.iterator().next();
						codCausa = String.valueOf(estado_ps_peticionLocal.getCod_causal());
						break;
					}
				}
//				Collection estadosPsPet = estado_ps_peticionHome.findByPeticionPS(peticion, psprim.getPsId());
				log.debug("Realizando reversa - creando tr513  codCausa="+codCausa+" psId=" + psprim.getPsId() + " peticion=" + peticion + " size1=" + coll1.size() + " size2=" + size2);
				coll1 = null;
				
				tr513e.setId(IdCorrelativo.toString());
				Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				tr513e.setAtisRequestNumber(peticion_atisKey.cod_pet_cd.longValue());
				
				tr513e.setAtiempoRequestNumber(peticionKey.peti_numero);
				
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				tr513e.setRequestDate(petAtis.getFec_sct_pet_ff()); 
				
				//tr513e.setRequestDateString(petAtis.getFec_sct_pet_ff().toString());
				
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}
				
				DepartamentoKey departamentoKey = (DepartamentoKey) peticionLocal.getFk_02_departamento().getPrimaryKey();
				tr513e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());

				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr513e.setCity(Integer.parseInt(localidadKey.cod_loc));
				if(codCausa!= null)
					tr513e.setCni(Integer.parseInt(codCausa));
				
				Collection colConect2 = peticionLocal.getRecursos_linea_basica () ;
            
				   if (colConect2.size () != 1)
				   {
					   String error = "La peticion " + peticion + " referencia a " + colConect2.size () + " registro(s) 'recursos linea basica': no se de donde sacar el ods" ;
					   throw new ATiempoAppEx (error) ;
				   }
				   
				Recursos_linea_basicaLocal recursosStb = (Recursos_linea_basicaLocal) colConect2.iterator ().next () ;

//				AT-1426 - Stall - FIN
				tr513e.setOdsNumber(recursosStb.getOds_apsc().intValue());
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				Familia_producto_servicioLocal familia_producto_servicioLocal = (Familia_producto_servicioLocal) producto_servicioLocal.getFamilia_producto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
				if(peticionLocal.getCod_are_tel_cd()!=null)
					mensajeEstadoLineaLocal.setArea(new Integer(peticionLocal.getCod_are_tel_cd()));
				if(peticionLocal.getTel_cot_ds()!=null)
					mensajeEstadoLineaLocal.setTelefono(new Long(phoneNumber));
					
				
				LiberacionRecursoGraniteSTBMQ recursoSTBMQ = new LiberacionRecursoGraniteSTBMQ();
				recursoSTBMQ.enviarMensaje(tr513e);
				
				
			} catch (NumberFormatException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);

			} catch (FinderException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (NamingException e) {
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.NAMING);
			}
			catch(Exception e)
			{
				log.error("",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
			}	
			
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#reversaAsignarRecursoGraniteSTB(co.com.telefonica.atiempo.interfaces.atiempo.TR513S)
		 */
		public void reversaAsignarRecursoGraniteSTB(TR513S tr513s) throws ATiempoAppEx { 		
			BackendExecution bExecution = null;
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr513s, log);
				bExecution.setIdMensajeOp(tr513s.getId());				
				bExecution.setNumPetAtiempo(new Long(tr513s.getAtiempoRequestNumber()));
				bExecution.startOperation();
				
				String codAct = "";
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				
				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				//Mensaje_estadoLocal mensajeManualLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr513s.getId()));
			    Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
		            
			   try{
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
			   }catch (FinderException fex){
					mensajeEstadoLineaLocal = null ;
			   }
			   
				
				// Validacion de existencia de la respuesta en la Base de Datos 
				// en la tabla Mensaje_Estado_Linea
				 
				if (mensajeEstadoLineaLocal == null) {
					log.debug(
						"No Existe Respuesta en la base de enviados\n "
							+ XMLUtilities.marshall(tr513s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr513s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				
				Mensaje_estadoKey  mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug(
						"Es estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr513s));
					return;
				}
				
				//Obtengo el codigo de la actividad generadora
				codAct = mensajeEstadoLineaLocal.getCod_actividad_generadora();

				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
				
				if ( recursosLineaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos);
				}else{
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}			
				
				recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
				recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);
				
				
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
				
				
				if (tr513s.getErrorCode() != 0){

					mensajeEstadoLineaLocal.setDesc_error(tr513s.getErrorMessage());

					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					// Si se usa este conector en la actvidad de Puntos de red BA es porque es una consulta.
					
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						
					PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();

					String codError = String.valueOf(tr513s.getErrorCode());
					String nombreClase=TR513S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					String observacion="";
					if(tr513s.getErrorMessage()!=null)
						observacion=tr513s.getErrorMessage();
					actDto.setObservacion(observacion+". Falla en la reversa de Asignacion de Recursos. Se deriva a PGI");

					if (errorLegado != null){ 
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					//
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
					
					actividadEJB.terminar(actDto);
					
					return;
				}
				PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();
						
				
				
				if(mensajeEstadoLineaLocal.getAccion()!=null && mensajeEstadoLineaLocal.getAccion().equals("R"))
					return; 
				
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				
				//TODO: RETA: 23022010 - Ajuste para no enviar a asugnación manual 
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb,"N");
				//End TODO
			
				actividadEJB.terminar(actDto);
				
				} catch (NumberFormatException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				
				} catch (CreateException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
						
				} catch (FinderException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				
				} catch (TnProcesoExcepcion e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				}
				catch(Exception e)
				{
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
				finally{  
					bExecution.endAll();
				}
			
		}
		
		/**
		 * AT-2104
		 * Devuelve true en caso de que la peticion contenga algun pc de linea 
		 * @param idPeticion
		 */
		public boolean tienePcLinea(Long idPeticion)throws ATiempoAppEx {
			
            boolean tienePc = false;
			PeticionKey key = new PeticionKey (idPeticion);
            PeticionLocal peticion;
			try {
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				peticion = peticionLocalHome.findByPrimaryKey (key);
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;

				for(Iterator iter = peticion.getProducto_servicio_peticion().iterator();iter.hasNext();){
						
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();	
					if (producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue()==familiaPcLinea || producto_servicio_peticionLocal.getFamiliaKey().faps_id.longValue()==familiaIP )
						tienePc = true;
				}

			} catch (FinderException e) {
				log.debug("Error buscando la peticion ..:"+idPeticion,e);
				e.printStackTrace();
			} catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			} 
			return tienePc;
		}			
		
		
		
		public int enviaACrearODS (ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				
				Long idPeticion = act.getNumeroPeticion();
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				Recursos_linea_basicaLocal rlb=null;
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				String reversa=act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa);
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				TR517E tr517e = new TR517E();
				int operacion=ComunInterfaces.SS_granite;
				
				try {
					 rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				} catch (FinderException e1) {
					
				}catch (RuntimeException e) {
					
				}
					
				
				
				if("S".equalsIgnoreCase(reversa)){
					if(rlb!=null && rlb.getOds_apsc()!=null && rlb.getOds_apsc().longValue()>0 &&rlb.getCrea_ods_granite()!=null&& rlb.getCrea_ods_granite().longValue()!=1) {
							return 3;
						}
					int estadoActual = peticionLocal.getEspe_id().intValue();
						
					
					boolean resultado=estaOKPeticion(act);
					log.info("Estado peticion..:"+estadoActual+ "..Tamaño del size..:"+act.getPsOk().size()+" resultado..:"+ resultado);
					if (resultado){
						return 4;
					}
					operacion =ComunInterfaces.reversa_Granite;
					
				}else{
					if(rlb!=null && rlb.getOds_apsc()!=null && rlb.getOds_apsc().longValue()>0){
						return 2;
					}
				}

				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();

				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				tr517e.setId(String.valueOf(IdCorrelativo));
				tr517e.setAtiempoRequestNumber(act.getNumeroPeticion()); 
				tr517e.setAtisRequestNumber(petAtisK.cod_pet_cd);
				tr517e.setOdsNumber(new Integer("0"));

				//Se setea la categoria y la subcategoria
				boolean esTraslado = tieneTrasladoLB(idPeticion);
				// no obligatorios, los cargo en 0 para que en la tr aparezca el tag
				tr517e.setNewCategory(new Long("0"));
				tr517e.setNewSubCategory(new Long("0"));
				tr517e.setRequestType(new Long("0"));
	
				for (Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal = (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())
							&& fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue()
							&& !ComunInterfaces.operacionBCP.equals(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo())
							&& !ComunInterfaces.OPERACION_BAJA.equals(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo())){
						Long opComercial = operacion_comercialKey.opco_id;
						log.debug("Se settea en Operation Type :.."+ opComercial);
						tr517e.setRequestType(opComercial);//AT-1996
						tr517e.setNewCategory(producto_servicio_peticionLocal.getTip_pro_cmr_cd());
						tr517e.setNewSubCategory(producto_servicio_peticionLocal.getSbt_pro_cmr_cd());
					}
				}
				
				if(rlb!=null && rlb.getOds_apsc()!=null && rlb.getOds_apsc().longValue()>0){
					tr517e.setOdsNumber( new Integer (rlb.getOds_apsc().intValue()));
				}
						
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals(""))
				{
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else
				{
					phoneNumber="0";
				}	
				
				tr517e.setPhoneNumber(new Integer(phoneNumber));
				tr517e.setOperationType(new Integer(operacion));
				
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLineaLocal.setPeti_numero(idPeticion);

				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
							
				
				int areaPhone= 0;
				int numeroPhone= 0;
				if (phoneNumber.length()>1){
					areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
					numeroPhone=Integer.parseInt(phoneNumber.substring(1));
				}
				mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
				mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
				
				CreaOdsGraniteSTBMQ creaOdsSTBMQ = new CreaOdsGraniteSTBMQ();
				creaOdsSTBMQ.enviarMensaje(tr517e);
			
				return 1;
			}catch (Exception e)
			{
				log.error("Error al enviar creacion de ODS .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}
		
		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#reversaAsignarRecursoGraniteSTB(co.com.telefonica.atiempo.interfaces.atiempo.TR513S)
		 */
		public void respuestaCreaOdsGranite(TR517S tr517s) throws ATiempoAppEx { 		
			
			BackendExecution bExecution = null;
			
			try
			{		
				bExecution = BackendTraceUtil.initExecution(tr517s, log);
				bExecution.setIdMensajeOp(tr517s.getId());				
				bExecution.setNumPetAtiempo(tr517s.getAtiempoRequestNumber());
				bExecution.startOperation();
				
				String codAct = "";
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				
				Mensaje_estadoLocal mesajeOkLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeErrorLocal=mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr517s.getId()));
			    Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;
		            
			   try{
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
			   }catch (FinderException fex){
					mensajeEstadoLineaLocal = null ;
			   }
			   
				
				// Validacion de existencia de la respuesta en la Base de Datos 
				// en la tabla Mensaje_Estado_Linea
				 
				if (mensajeEstadoLineaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr517s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr517s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				
				Mensaje_estadoKey  mensaje_estadoKey = (Mensaje_estadoKey) mensajeEstadoLineaLocal.getF_reference_14().getPrimaryKey() ;
				
				//Validacion del estado de la respuesta 
				if (mensaje_estadoKey.cod_estado.intValue() == estadoOk || mensaje_estadoKey.cod_estado.intValue() == estadoEsperaManual) {
					log.debug("Es estado de la respuesta es diferente para ser procesado\n " + XMLUtilities.marshall(tr517s));
					return;
				}
				
				//Obtengo el codigo de la actividad generadora
				codAct = mensajeEstadoLineaLocal.getCod_actividad_generadora();

				Recursos_linea_basicaLocal recursos_linea_basicaLocal;
				Collection recursosLineaBasica = mensajeEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
				
				if ( recursosLineaBasica.size() == 0){
					Long idConDos = new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos);
				}else{
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}			
				
				recursos_linea_basicaLocal.setPeticion(mensajeEstadoLineaLocal.getPeticion());
				recursos_linea_basicaLocal.setMensaje_estado_linea(mensajeEstadoLineaLocal);
				
				
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				mensajeEstadoLineaLocal.setF_reference_14(mesajeOkLocal);
				boolean  noOds=false;
				if(tr517s.getErrorCode()==0 && tr517s.getOdsNumber()!= null && tr517s.getOdsNumber().longValue()==0){
					noOds=true;
				}
				
				if (tr517s.getErrorCode() != 0 ){//|| noOds){

					mensajeEstadoLineaLocal.setDesc_error(tr517s.getErrorMessage());

					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					// Si se usa este conector en la actvidad de Puntos de red BA es porque es una consulta.
					
					mensajeEstadoLineaLocal.setF_reference_14(mensajeErrorLocal);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						
					PeticionLocal peticionLocal = mensajeEstadoLineaLocal.getPeticion();

					String codError = String.valueOf(tr517s.getErrorCode());
					String nombreClase=TR513S.class.getName();
					nombreClase=com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
					ErrorLegadoLocal errorLegado = getErrorLegado(codError,nombreClase);
					
					//AT-2142 error no se ve en la aplicacion
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if (errorLegado != null){ 
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr517s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
						plataforma = errorLegado.getPlataforma();
						bandeja = getNombreBandeja(plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					}
					
					String observacion="";
					if(tr517s.getErrorMessage()!=null)
						observacion=tr517s.getErrorMessage();
					if(!noOds){
						actDto.setObservacion(observacion+". Falla en la creacion de la Orden de Servicio. Se deriva a "+bandeja);
					}else{
						actDto.setObservacion(observacion+". Falla en la creacion de la Orden de Servicio. Se deriva a "+bandeja+" ODS es cero");
					}
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actividadEJB.terminar(actDto);
					
					return;
				}else{
					recursos_linea_basicaLocal.setOds_apsc(new Long (tr517s.getOdsNumber().longValue()));
					//Seteo en true la variable de recurso de line basica
					recursos_linea_basicaLocal.setCrea_ods_granite(new Short ("1"));
				}
				
								
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				actividadEJB.terminar(actDto);
				
				} catch (NumberFormatException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT, e);
				
				} catch (CreateException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
						
				} catch (FinderException e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
				
				} catch (TnProcesoExcepcion e) {
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS, e);
				}
				catch(Exception e)
				{
					bExecution.setErrorOp(true);
					log.error("",e);
					throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
				} 
				finally{  
					bExecution.endAll();
				}
			
		}
				
		
		public boolean estaOKPeticion (ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				
				Long idPeticion = act.getNumeroPeticion();
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - Jun 2, 2009
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
				
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();			
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
				
			
				Long psId=null;
						
				
				PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		
				
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					
									
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
						psId=productoServicoKey.ps_id;
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						
						if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
							Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
							if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()!=estadoCierreError){
								
								return true;
							}
													
						}else{
								return true;			
										
						}
							
					}
				}
			
				return false;
				
			} catch (NamingException e){
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}catch (Exception e){
				log.error("Error al enviar Configuracion Automatica .",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
			}
		}
		
		//TODO: 26/01/2009 Requerimiento Req_2009_00031777_CambioVelocidadEnVuelo - RETA
		public ArrayList consultaZonasAtendimiento(Long idPeticion){
			Collection listaZonas= new ArrayList(); 
			ArrayList listaIP= new ArrayList();
			try{
				Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(idPeticion);
				Recursos_linea_basicaKey key = (Recursos_linea_basicaKey)linea_basicaLocal.getPrimaryKey();
				
				Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
				listaZonas = zonas_atendimientoLocalHome.findByConector(key.id_conector);
				
				for(Iterator iter = listaZonas.iterator();iter.hasNext(); )
				{	
					Zonas_atendimientoLocal zonaLocal = (Zonas_atendimientoLocal) iter.next();
					listaIP.add(zonaLocal.getIp());
				}
				
				return listaIP;
			}catch (Exception e){
				log.debug(e);
			}
			return null;
		}
		//End TODO
		
		/**
		 * Retorna el telefono que se encuentra en la tabla
		 * @author idrincon
		 * Date 28/10/2010
		 */
		private String consultarTelefono( Long idPeticion ){
			
			String telefonoAsignado = null;
			
			try {
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion( idPeticion );
				Recursos_linea_basicaKey  recursos_linea_basicaKey = ( Recursos_linea_basicaKey ) recursos_linea_basicaLocal.getPrimaryKey();
				
				telefonoAsignado = recursos_linea_basicaLocal.getTelefono_asg().toString();
				return telefonoAsignado;
				
			} catch (NamingException ne) {
				log.debug("RecursosServiciosBean: consultarTelefono: NamingException: "+ne);
				return telefonoAsignado;
			} catch (FinderException fe) {
				log.debug("RecursosServiciosBean: consultarTelefono: FinderException: "+fe);
				return telefonoAsignado;
			}
		}
		//fin req 1036 - 28/10/2010
		
		public void enviaMensajeTutorWeb(Long idPeticion,String idActividad, boolean yaEnvioPrimerMensajeMT, ActividadEJBDTO act) throws ATiempoAppEx {
			try{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome mensajeEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome = (Mensaje_estadoLocalHome)HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Tipo_prod_tutor_webLocalHome tipoProdTutorWebLocalHome = (Tipo_prod_tutor_webLocalHome) HomeFactory.getHome(Tipo_prod_tutor_webLocalHome.JNDI_NAME);
				Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
				Producto_servicioLocalHome productoServicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				
				boolean presentaErrorDatos = false;
				Familia_producto_servicioKey familiaKey = null;
				String operacionComercialPCLinea = null;
				String psAsistencia = null;
				Agrupacion_atisLocal agrupacionLocal  = null;
				TR047E tr047e = new TR047E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Integer phoneNumber = null;
				
				boolean esFamiliaEQ = false;
				
				Properties_BDLocal propertiesDBLocalUserTutorWeb = propertiesBDLocalHome.findByPrimaryKey("USUARIO_TUTOR_WEB");
				Properties_BDLocal propertiesDBLocalClaveTutorWeb = propertiesBDLocalHome.findByPrimaryKey("CLAVE_TUTOR_WEB");
				
				PeticionKey peticionKey = new PeticionKey(idPeticion);
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				//req SVAs dacardena
				Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
				//fin SVAs
				
				Collection listaPS = peticionLocal.getPeticion_flujo();
				
				for (Iterator listaPSItertator = listaPS.iterator(); listaPSItertator.hasNext();){
					
					
					Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)listaPSItertator.next();
										
					if (peticionFlujoLocal.getIdActividad().toString().equals(ComunInterfaces.ID_ACTIVIDAD_ASISTENCIA.toString())){
						Long psId = peticionFlujoLocal.getPrse_id();
						Operacion_comercialLocal opcoLocal =  peticionFlujoLocal.getFk_opco_2_pefl();
						
						Producto_servicioKey productoServicioKey = new Producto_servicioKey(psId);
						Producto_servicioLocal productoServicioLocal = productoServicioLocalHome.findByPrimaryKey(productoServicioKey);
						
												
						Familia_producto_servicioLocal fapsLocal = productoServicioLocal.getFamilia_producto_servicio();
						familiaKey = (Familia_producto_servicioKey) fapsLocal.getPrimaryKey();
						
						if(familiaKey.faps_id.intValue() == ComunInterfaces.familiaIntEquipado)
							esFamiliaEQ = true;
						
						if (familiaKey.faps_id != null && familiaKey.faps_id.intValue() == ComunInterfaces.familiaPS_SVA){
							
							/*RQ.6142 - mfmendez - WebService Aula - WebTutor*/
							RecursosBAInterfaces recBAInterface = new RecursosBADelegate();

							String ocSVA = recBAInterface.validaOperacionComercialSVA(act, act.getNumeroPeticion());
							
							if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_NO_APLICA_MODIFICACION_SVA)){				
								operacionComercialPCLinea = ComunInterfaces.OPERACION_MODIFICAR;
							}else{
								operacionComercialPCLinea = opcoLocal.getOpco_web_tutor();								
							}
							psAsistencia = psId.toString();
							/*FIN RQ.6142 - mfmendez - WebService Aula - WebTutor*/
						}
					}
				}
				
				if (operacionComercialPCLinea!=null && operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_ALTA)){
					tr047e.setUser(propertiesDBLocalUserTutorWeb.getValor());
					tr047e.setPassword(propertiesDBLocalClaveTutorWeb.getValor());
					
					Tipo_prod_tutor_webKey tutorWebKey = new Tipo_prod_tutor_webKey(psAsistencia);
					
					try{
						Tipo_prod_tutor_webLocal tipoProdTutorWebLocal = tipoProdTutorWebLocalHome.findByPrimaryKey(tutorWebKey);
						tr047e.setProductType(tipoProdTutorWebLocal.getProduct_type());
					}catch(FinderException ex){
						log.debug("No se encontró el ps configurado en la tabla  Tipo_prod_tutor_web, se pide configurarlo:"+ex);
						tr047e.setProductType("");
					}
// requerimiento SVAs dcardema
//se cambia la logica de phone = IDPC
//					//DAVID: Enero 18 2010
//					boolean asignoTelefono = false;
//					String telefonoAntOAsig="0";
//					Collection recursosLBList = peticionLocal.getRecursos_linea_basica();
//					for (Iterator recursosLineaIterator = recursosLBList.iterator();recursosLineaIterator.hasNext();){
//						Recursos_linea_basicaLocal recursosLineaLocal = (Recursos_linea_basicaLocal) recursosLineaIterator.next();
//						
//						if (recursosLineaLocal.getTelefono_ant() != null && recursosLineaLocal.getTelefono_ant().intValue() != 0){
//							telefonoAntOAsig = recursosLineaLocal.getTelefono_ant().toString();
//							asignoTelefono=true;
//						}else{
//							telefonoAntOAsig = recursosLineaLocal.getTelefono_asg().toString();
//							asignoTelefono=true;
//						}
//					}
//					
//					//Raúl: Se adiciona esta línea en caso que no venga un teléfono asociado.
//					if (!asignoTelefono){
//						telefonoAntOAsig = peticionLocal.getNum_ide_nu_stb();
//					}
					
					
					
					//				Requerimiento SVAs se modifica logica del tag phone-number = idpc @cardena
					//				validamos si el numero esta nulo o si contiene datos
					
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
					
					
					log.debug("Se setea a la TR047E el valor phoneNumber: " + phoneNumber);
					tr047e.setPhone(phoneNumber.toString()); //Estaba: peticionLocal.getNum_ide_nu_stb(). Se asigna el teléfono anterior, si este no es 0, si es así, se asigna el 
					//teléfono asignado.
					//Fin, DAVID: Enero 18 2010
//					fin requerimiento SVAs
					tr047e.setName(peticionLocal.getNom_ds());
					
					Peticion_atisLocal peticionAtisLocal = peticionLocal.getFk_01_pet_atis();
					Peticion_atisKey peticionAtisKey = (Peticion_atisKey)peticionAtisLocal.getPrimaryKey();
					
					Collection agrupacionAtisList = peticionAtisLocal.getAgrupacion_atis();
					for (Iterator iterAgrupacionAtis = agrupacionAtisList.iterator(); iterAgrupacionAtis.hasNext();){
						Agrupacion_atisLocal agrupacionAtisLocal = (Agrupacion_atisLocal) iterAgrupacionAtis.next();
						
						Collection subpeticionAtisList = agrupacionAtisLocal.getSubpeticion_atis();
						
						for (Iterator iterSubpeticionAtis = subpeticionAtisList.iterator(); iterSubpeticionAtis.hasNext();){
							Subpeticion_atisLocal subpeticionAtisLocal = (Subpeticion_atisLocal) iterSubpeticionAtis.next();
							
							Collection subpeticionCaracList = subpeticionAtisLocal.getSubpeticion_caracteristicas();
							
							for (Iterator iterSubpeticionCarac = subpeticionCaracList.iterator(); iterSubpeticionCarac.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaracLocal = (Subpeticion_caracteristicasLocal) iterSubpeticionCarac.next();
								Subpeticion_caracteristicasKey subpeticionCaracKey = (Subpeticion_caracteristicasKey)subpeticionCaracLocal.getPrimaryKey();
								
								if (subpeticionCaracKey.getCod_crc_cd().intValue() == ComunInterfaces.caracteristicaMailAsistencia){
									tr047e.setMail(subpeticionCaracLocal.getVal_ral_crc_cd());//recursos ba father email nuevo
								}
							}
						}
					}
					
									
					tr047e.setActivationDate(formatter.format(new Timestamp(new Date().getTime())));
					tr047e.setAddress(Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ Utiles.sinNull(peticionLocal.getNum_cal_nu(),""));

					DepartamentoLocal deptoLocal =  peticionLocal.getFk_02_departamento();
					DepartamentoKey deptoKey = (DepartamentoKey)deptoLocal.getPrimaryKey();
					tr047e.setState(deptoKey.cod_dpt);
					
					LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
					LocalidadKey localidadKey = (LocalidadKey)localidadLocal.getPrimaryKey();
					tr047e.setCity(localidadKey.cod_loc);
					
					tr047e.setUserId(peticionLocal.getNum_doc_cli_cd());
					tr047e.setMessageId(peticionAtisKey.cod_pet_cd.toString());
					tr047e.setSegment(peticionLocal.getCod_sgm_cta_cd().toString());
					
					Collection recursosBAList = peticionLocal.getRecursos_ba();
					if (recursosBAList.size()>0) {
					for (Iterator recursosBAIterator = recursosBAList.iterator();recursosBAIterator.hasNext();)
						{
						Recursos_baLocal recursosBALocal = (Recursos_baLocal) recursosBAIterator.next();
						tr047e.setActivationUser(recursosBALocal.getFather_email_nuevo());
						}
					}
					else
						log.debug("TutorWeb: No se encontraron recursos de BA en la peticion");
					
					tr047e.setAction("ALTA");
				}else if (operacionComercialPCLinea!=null && (operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_MODIFICAR))
						|| (!yaEnvioPrimerMensajeMT && operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_MODIFICACION_TRANSACCION))){
					
					
					tr047e.setUser(propertiesDBLocalUserTutorWeb.getValor());
					tr047e.setPassword(propertiesDBLocalClaveTutorWeb.getValor());
					
					Tipo_prod_tutor_webKey tutorWebKey = new Tipo_prod_tutor_webKey(psAsistencia);
					
					try{
						Tipo_prod_tutor_webLocal tipoProdTutorWebLocal = tipoProdTutorWebLocalHome.findByPrimaryKey(tutorWebKey);
						tr047e.setProductType(tipoProdTutorWebLocal.getProduct_type());
					}catch(FinderException ex){
						log.debug("No se encontró el ps configurado en la tabla  Tipo_prod_tutor_web, se pide configurarlo:"+ex);
						tr047e.setProductType("");
					}
					
					
					
////					DAVID: Enero 18 2010
//					String telefonoAntOAsig="0";
//					Collection recursosLBList = peticionLocal.getRecursos_linea_basica();
//					for (Iterator recursosLineaIterator = recursosLBList.iterator();recursosLineaIterator.hasNext();){
//						Recursos_linea_basicaLocal recursosLineaLocal = (Recursos_linea_basicaLocal) recursosLineaIterator.next();
//						
//						if (recursosLineaLocal.getTelefono_ant() != null && recursosLineaLocal.getTelefono_ant().intValue() != 0)
//							telefonoAntOAsig = recursosLineaLocal.getTelefono_ant().toString();
//						else{
//							telefonoAntOAsig = recursosLineaLocal.getTelefono_asg().toString();
//						}
//					}
//					
//							
					//				Requerimiento SVAs se modifica logica del tag phone-number = idpc @cardena
					//				validamos si el numero esta nulo o si contiene datos
					
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
										
					}else{
						log.debug("El IDPC TV y STB estan vacios se seteara el numero Atis en el phone number "+petAtisK.cod_pet_cd.intValue());
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						phoneNumber=new Integer(petAtisK.cod_pet_cd.intValue()); //Atis request number
					}
					
					
					log.debug("Se setea a la TR047E el valor phoneNumber: " + phoneNumber);
					tr047e.setPhone(phoneNumber.toString()); //Estaba: peticionLocal.getNum_ide_nu_stb(). Se asigna el teléfono anterior, si este no es 0, si es así, se asigna el 
					//tr047e.setPhone(telefonoAntOAsig); //Estaba: peticionLocal.getNum_ide_nu_stb(). Se asigna el teléfono anterior, si este no es 0, si es así, se asigna el 
					//teléfono asignado.
//					tr047e.setPhone(peticionLocal.getNum_ide_nu_stb());//---------Telefono anterior es 0 = telefono asig
					//Fin, DAVID: Enero 18 2010

					tr047e.setName(peticionLocal.getNom_ds());
					
					Peticion_atisLocal peticionAtisLocal = peticionLocal.getFk_01_pet_atis();
					Peticion_atisKey peticionAtisKey = (Peticion_atisKey)peticionAtisLocal.getPrimaryKey();
					
					Collection agrupacionAtisList = peticionAtisLocal.getAgrupacion_atis();
					for (Iterator iterAgrupacionAtis = agrupacionAtisList.iterator(); iterAgrupacionAtis.hasNext();){
						Agrupacion_atisLocal agrupacionAtisLocal = (Agrupacion_atisLocal) iterAgrupacionAtis.next();
						
						Collection subpeticionAtisList = agrupacionAtisLocal.getSubpeticion_atis();
						
						for (Iterator iterSubpeticionAtis = subpeticionAtisList.iterator(); iterSubpeticionAtis.hasNext();){
							Subpeticion_atisLocal subpeticionAtisLocal = (Subpeticion_atisLocal) iterSubpeticionAtis.next();
							
							Collection subpeticionCaracList = subpeticionAtisLocal.getSubpeticion_caracteristicas();
							
							for (Iterator iterSubpeticionCarac = subpeticionCaracList.iterator(); iterSubpeticionCarac.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaracLocal = (Subpeticion_caracteristicasLocal) iterSubpeticionCarac.next();
								Subpeticion_caracteristicasKey subpeticionCaracKey = (Subpeticion_caracteristicasKey)subpeticionCaracLocal.getPrimaryKey();
								
								if (subpeticionCaracKey.getCod_crc_cd().intValue() == ComunInterfaces.caracteristicaMailAsistencia){
									tr047e.setMail(subpeticionCaracLocal.getVal_ral_crc_cd());
								}
							}
						}
					}
					
					tr047e.setAddress(Utiles.sinNull(peticionLocal.getNom_cal_ds(),"") +" "+ 
							Utiles.sinNull(peticionLocal.getNum_cal_nu(),""));

					DepartamentoLocal deptoLocal =  peticionLocal.getFk_02_departamento();
					DepartamentoKey deptoKey = (DepartamentoKey)deptoLocal.getPrimaryKey();
					tr047e.setState(deptoKey.cod_dpt);
					
					LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
					LocalidadKey localidadKey = (LocalidadKey)localidadLocal.getPrimaryKey();
					tr047e.setCity(localidadKey.cod_loc);
					
					tr047e.setUserId(peticionLocal.getNum_doc_cli_cd());
					tr047e.setMessageId(peticionAtisKey.cod_pet_cd.toString());
					tr047e.setSegment(peticionLocal.getCod_sgm_cta_cd().toString());
					
					Collection recursosBAList = peticionLocal.getRecursos_ba();
					for (Iterator recursosBAIterator = recursosBAList.iterator();recursosBAIterator.hasNext();){
						Recursos_baLocal recursosBALocal = (Recursos_baLocal) recursosBAIterator.next();
						
						tr047e.setActivationUser(recursosBALocal.getFather_email_nuevo());
					}

					
					tr047e.setAction("MODIFICAR");
				}else if (operacionComercialPCLinea!=null && operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_BAJA)){
					tr047e.setUser(propertiesDBLocalUserTutorWeb.getValor());
					tr047e.setPassword(propertiesDBLocalClaveTutorWeb.getValor());
					
//					if (peticionLocal.getNum_ide_nu_stb() != null || peticionLocal.getNum_ide_nu_stb().length()>0){
//						tr047e.setPhone(peticionLocal.getNum_ide_nu_stb());
//					}else{
//						tr047e.setPhone("0");
//					}
					
					//				Requerimiento SVAs se modifica logica del tag phone-number = idpc @cardena
					//				validamos si el numero esta nulo o si contiene datos
				
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
										
					}else{
						log.debug("El IDPC TV y STB estan vacios se seteara el numero Atis en el phone number "+petAtisK.cod_pet_cd.intValue());
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						phoneNumber=new Integer(petAtisK.cod_pet_cd.intValue()); //Atis request number
					}
					
					
					log.debug("Se setea a la TR047E el valor phoneNumber: " + phoneNumber);
					tr047e.setPhone(phoneNumber.toString()); //Estaba: peticionLocal.getNum_ide_nu_stb(). Se asigna el teléfono anterior, si este no es 0, si es así, se asigna el 
					//teléfono asignado.
					
					tr047e.setUnregisterDate(formatter.format(new Timestamp(new Date().getTime())));
					tr047e.setAction("BAJA");
				}else if (operacionComercialPCLinea!=null && (operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_TRANSACCION))
						|| (yaEnvioPrimerMensajeMT && (operacionComercialPCLinea.equals(ComunInterfaces.OPERACION_MODIFICACION_TRANSACCION)))){
					tr047e.setUser(propertiesDBLocalUserTutorWeb.getValor());
					tr047e.setPassword(propertiesDBLocalClaveTutorWeb.getValor());
					
					String telefonoOld = new String();
					String telefonoNew = new String();
					
//					Collection recursosLineaList = peticionLocal.getRecursos_linea_basica();
//					for (Iterator recursosLineaIterator = recursosLineaList.iterator();recursosLineaIterator.hasNext();){
//						Recursos_linea_basicaLocal recursosLineaLocal = (Recursos_linea_basicaLocal) recursosLineaIterator.next();
//						
//						if (recursosLineaLocal.getTelefono_ant() != null && recursosLineaLocal.getTelefono_ant().intValue() != 0)
//							telefonoOld = recursosLineaLocal.getTelefono_ant().toString();
//						else{
//							Collection prodServPeticionList = peticionLocal.getProducto_servicio_peticion();
//							
//							for (Iterator prodServPeticionIterator = prodServPeticionList.iterator(); prodServPeticionIterator.hasNext();){
//								Producto_servicio_peticionLocal prodServPeticionLocal = (Producto_servicio_peticionLocal) prodServPeticionIterator.next();
//														
//								if (prodServPeticionLocal.getFamiliaKey().faps_id.intValue() == ComunInterfaces.familiaAsistencia){
//									agrupacionLocal  = prodServPeticionLocal.getFk_01_subp_atis().getFk_agru_sub();
//								}
//							}
//							
//							telefonoOld = agrupacionLocal.getNum_ide_nu();
//						}
//							
//						
//						if (recursosLineaLocal.getTelefono_asg() != null)
//							telefonoNew = recursosLineaLocal.getTelefono_asg().toString();
//						else
//							telefonoNew = "0";
//					}
					
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
										
					}else{
						log.debug("El IDPC TV y STB estan vacios se seteara el numero Atis en el phone number "+petAtisK.cod_pet_cd.intValue());
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						phoneNumber=new Integer(petAtisK.cod_pet_cd.intValue()); //Atis request number
					}
					
					
					log.debug("Se setea a la TR047E el valor phoneNumber: " + phoneNumber);
					telefonoOld=phoneNumber.toString();
					telefonoNew=idPeticion.toString();
					if (telefonoOld == null || telefonoOld.equals("0") ||  telefonoNew == null || telefonoNew.equals("0")
							|| telefonoOld.equals(telefonoNew)){
						presentaErrorDatos = true;
					}
					
					tr047e.setPhone(telefonoOld);
					tr047e.setNewPhone(telefonoNew);
					tr047e.setAction("TRANSACCION");
				}
				
				
				if (operacionComercialPCLinea!=null){
					
					tr047e.setId(IdCorrelativo.toString());
					tr047e.setDestination(sistemaAgendaSC);
					tr047e.setSource(sistemaAtiempo);
					tr047e.setInterfaz(SISTEMA_TUTOR);
					tr047e.setVersion("1.0");
					
					String valorYaEnvioPrimerMensajeMT = String.valueOf(yaEnvioPrimerMensajeMT);
					
					Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= mensajeEstadoLineaLocalHome.create(IdCorrelativo);
					mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
					mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familiaKey.faps_id.intValue()));
					mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
					mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
					mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
					mensajeEstadoLineaLocal.setCod_actividad_generadora(idActividad);
					mensajeEstadoLineaLocal.setDesc_error(operacionComercialPCLinea+":"+valorYaEnvioPrimerMensajeMT);
				
					//Se cambia logica por Requerimiento SVAs dcardena
					//String phoneNumber = peticionLocal.getNum_ide_nu_stb(); 
					String phoneStringNumber = phoneNumber.toString();
					int areaPhone= 0;
					int numeroPhone= 0;
					if (phoneStringNumber.length()>1){
						areaPhone=Integer.parseInt(phoneStringNumber.substring(0,1));
						numeroPhone=Integer.parseInt(phoneStringNumber.substring(1));
					}
					mensajeEstadoLineaLocal.setArea(new Integer(areaPhone));
					mensajeEstadoLineaLocal.setTelefono(new Long(numeroPhone));
					//fin SVAs
					
					if (!presentaErrorDatos){
						EnviarTutorWebMQ enviaTutorWebMQ = new EnviarTutorWebMQ();
						enviaTutorWebMQ.enviarMensaje(tr047e);
					}else{
						act.setObservacion("No se envía mensaje hacia tutor Web porque la operación es una TRANSACCIÓN y los" +
						" campos teléfono anterior, teléfono nuevo estan nulos o son iguales, por favor revise.");
						act.setRealizarTerminoInmediato(true);
					}
					
				}else{
					log.debug("No se envía mensaje a tutor Web, pues no se ha podido establecer la acción");
				}
				
			}catch(NamingException ex){
				log.debug("enviaMensajeTutorWeb: NamingException: "+ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("enviaMensajeTutorWeb: FinderException: "+ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("enviaMensajeTutorWeb: CreateException: "+ex);
				ex.printStackTrace();
			}/*catch(TnProcesoExcepcion ex){
				log.debug("enviaMensajeTutorWeb: TnProcesoExcepcion: "+ex);
				ex.printStackTrace();
			}*/
			
		}

		public void respuestaMensajeTutorWeb(TR047S tr047s) throws ATiempoAppEx {
			//Jesus Carvajal - 11/04/2021
			DBManager manager=new DBManager ();
			manager.setDataSource (DBManager.JDBC_VPISTBBA);
			Integer correlativo = null;
			//Fin
			
			try{
				Mensaje_estado_lineaLocalHome mensajeEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome)HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Tutor_webLocalHome tutorWebLocalHome = (Tutor_webLocalHome) HomeFactory.getHome(Tutor_webLocalHome.JNDI_NAME);
				String codAct = "";
				
				Mensaje_estado_lineaKey mensajeEstadoLineaKey = new Mensaje_estado_lineaKey(new Long(tr047s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal = mensajeEstadoLineaLocalHome.findByPrimaryKey(mensajeEstadoLineaKey);
				
				codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
				String resultadoMT[] = mensajeEstadoLineaLocal.getDesc_error().split(":"); 
				
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
				
				if (tr047s.getStatus().equals("Ok")){
					String observacionWebService = new String();
					/*
					Integer correlativo = new Integer ("1");
					try{
						Tutor_webLocal tutorWebLocal = tutorWebLocalHome.findMaxCorrelation();
					
						Tutor_webKey tutorWebKey = (Tutor_webKey)tutorWebLocal.getPrimaryKey();
						if (tutorWebKey.correlativo != null){
							correlativo = new Integer (tutorWebKey.correlativo.intValue()+1);
						}
					}catch(NullPointerException ex){
						log.debug("Se fue por NullPointerException, se setea como correlativo 1: "+ex);
						correlativo = new Integer ("1");
					}catch(FinderException ex){
						log.debug("Se fue por FinderException, se setea como correlativo 1: "+ex);
						correlativo = new Integer ("1");
					}*/
					
					//Jesus Carvajal - 11/04/2012
					correlativo = new Integer (manager.seqNextValInteger("CORRELATIVO_TUTOR_WEB"));
					//Fin.

					log.debug("El correlativo es: "+correlativo);
					Tutor_webLocal tutorWeb= tutorWebLocalHome.create(mensajeEstadoLineaLocal.getPeti_numero(), correlativo);
					tutorWeb.setLicense_code(tr047s.getLicenseCode());
					tutorWeb.setCancelation_date(tr047s.getCancelationDate());
					
					if (tr047s.getLicenseCode() != null && tr047s.getLicenseCode().length()>0){
						observacionWebService = "La respuesta del webSerive es: Codigo Licencia:"+tr047s.getLicenseCode();
					}else if (tr047s.getCancelationDate() != null && tr047s.getCancelationDate().length()>0){
						observacionWebService = "La respuesta del webSerive es: Fecha cancelación:"+tr047s.getCancelationDate();
					}
					
					actDto.setObservacion(observacionWebService);
					
					if (resultadoMT[0].equals(OPERACION_MODIFICACION_TRANSACCION) && resultadoMT[1].equals("false")){
						enviaMensajeTutorWeb(mensajeEstadoLineaLocal.getPeti_numero(),codAct, true, actDto);
					}
				}else{
					ErrorLegadoLocal errorLegado = getErrorLegado(tr047s.getError(),"TR047S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr047s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
					mensajeEstadoLineaLocal.setId_error(tr047s.getError());
					mensajeEstadoLineaLocal.setDesc_error(tr047s.getErrorMessage());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en la el proceso de Tutor Web. " + tr047s.getStatus() + " Se envia a " + bandeja);
				}
				
				actividadEJB.terminar(actDto);
				
			}catch(NamingException ex){
				log.debug("respuestaMensajeTutorWeb: NamingException: "+ex);
				ex.printStackTrace();
			}catch(FinderException ex){
				log.debug("respuestaMensajeTutorWeb: FinderException: "+ex);
				ex.printStackTrace();
			}catch(TnProcesoExcepcion ex){
				log.debug("respuestaMensajeTutorWeb: TnProcesoExcepcion: "+ex);
				ex.printStackTrace();
			}catch(CreateException ex){
				log.debug("respuestaMensajeTutorWeb: CreateException: "+ex);
				ex.printStackTrace();
			}catch(Exception ex){
				log.debug("respuestaMensajeTutorWeb: Exception: "+ex);
				ex.printStackTrace();
			}
		}

	//@idrincon req 3274
	//@mfmendez req 4656
	public boolean enviarConsultaTroncalSip(Long numeroAtis, String idMensaje) {
		log.debug("ingresa a enviarConsultaTroncalSip.");
		boolean error = false;
		try {
				
			TR049E tr049e =  new TR049E();
			ArrayList listaNumeros = new ArrayList();
				
			CentralLocalHome central = (CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);				
				
			Peticion_atisLocalHome  peticion_atisLocalHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);			
			Peticion_atisKey petAtisKey = new Peticion_atisKey(numeroAtis);
			Peticion_atisLocal petAtisLocal = peticion_atisLocalHome.findByPrimaryKey(petAtisKey);
			Collection peticionesAtis = petAtisLocal.getPeticion();
			
			for (Iterator iter = peticionesAtis.iterator(); iter.hasNext();) {
				PeticionLocal peticionLocal = (PeticionLocal) iter.next();
												
				Collection collectionPeticiones = peticionLocal
						.getRecursos_linea_basica();
				TR049EAssignedNumber assignedNumber = new TR049EAssignedNumber();

				if (collectionPeticiones.size() > 0) {
					for (Iterator iterator = collectionPeticiones.iterator(); iterator.hasNext();) {
						Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iterator.next();
						assignedNumber.setAtiempoNumber(recursos_linea_basicaLocal.getPeti_numero().toString());
						if (recursos_linea_basicaLocal.getTelefono_ant() != null) {
							assignedNumber.setOldPhoneNumber(recursos_linea_basicaLocal.getTelefono_ant().toString());
						} else {
							assignedNumber.setOldPhoneNumber("0");
						}

						if (recursos_linea_basicaLocal.getTelefono_asg() != null) {
							assignedNumber.setNewPhoneNumber(recursos_linea_basicaLocal.getTelefono_asg().toString());
						} else {
							assignedNumber.setNewPhoneNumber("0");
						}

						/**
						 * Datos de linea basica
						 */
						TR049EBasicLine lineaBasica = new TR049EBasicLine();

						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getOds_apsc() != null)
							lineaBasica.setOdsNumber(recursos_linea_basicaLocal.getOds_apsc().intValue());
						else
							lineaBasica.setOdsNumber(0);						
													
						/* boolean reserveResult */
						lineaBasica.setReserveResult(false);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDist_sec_asg() != null)
							lineaBasica.setSecondaryDistributor(recursos_linea_basicaLocal.getDist_sec_asg().longValue());
						else
							lineaBasica.setSecondaryDistributor(0);
												
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDesc_dist_prim_asg() != null)
							lineaBasica.setDistributorDescription(recursos_linea_basicaLocal.getDesc_dist_prim_asg());
						else
							lineaBasica.setDistributorDescription("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getArmario_asg() != null)
							lineaBasica.setCloset(recursos_linea_basicaLocal.getArmario_asg());
						else
							lineaBasica.setCloset("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCaja_asg() != null)
							lineaBasica.setBox(recursos_linea_basicaLocal.getCaja_asg());
						else
							lineaBasica.setBox("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getPar_caja_asg() != null)
							lineaBasica.setBoxPair(recursos_linea_basicaLocal.getPar_caja_asg().intValue());
						else
							lineaBasica.setBoxPair(0);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDist_prim_asg() != null)
							lineaBasica.setPrimaryDistributor(recursos_linea_basicaLocal.getDist_prim_asg().longValue());
						else
							lineaBasica.setPrimaryDistributor(0);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getListon_asg() != null)
							lineaBasica.setStrip(recursos_linea_basicaLocal.getListon_asg());
						else
							lineaBasica.setStrip("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getPar_liston_asg() != null)
							lineaBasica.setStripPair(recursos_linea_basicaLocal.getPar_liston_asg().intValue());
						else
							lineaBasica.setStripPair(0);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCod_central() != null)
							lineaBasica.setCentralCode(recursos_linea_basicaLocal.getCod_central().longValue());
						else
							lineaBasica.setCentralCode(0);
						
						/* String centralDescription */
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCod_central() != null){
							CentralKey centralKey = new CentralKey(recursos_linea_basicaLocal.getCod_central());
							CentralLocal centralLocal = central.findByPrimaryKey(centralKey);
							if(centralLocal != null && centralLocal.getDesc_central() != null)
								lineaBasica.setCentralDescription(centralLocal.getDesc_central());
							else
								lineaBasica.setCentralDescription("");
						}else{
							lineaBasica.setCentralDescription("");
						}										

						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCable() != null)
							lineaBasica.setCable(recursos_linea_basicaLocal.getCable());
						else
							lineaBasica.setCable("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getPar_cable() != null)
							lineaBasica.setCablePair(recursos_linea_basicaLocal.getPar_cable().intValue());
						else
							lineaBasica.setCablePair(0);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getTelefono_asg() != null)
							lineaBasica.setPhoneNumber(recursos_linea_basicaLocal.getTelefono_asg().intValue());
						else
							lineaBasica.setPhoneNumber(0);
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getLen() != null)
							lineaBasica.setLen(recursos_linea_basicaLocal.getLen());
						else
							lineaBasica.setLen("");

						Collection zonasAten = new ArrayList();
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getZonas_atendimiento() != null){
							Collection zonasAtendimiento = recursos_linea_basicaLocal.getZonas_atendimiento();							
							for (Iterator iter1 = zonasAtendimiento.iterator(); iter1
									.hasNext();) {
								Zonas_atendimientoLocal zona = (Zonas_atendimientoLocal) iter1.next();
								if(zona != null){
									if (zona.getIp() != null)
										zonasAten.add(zona.getIp());
									else 
										zonasAten.add("");
								}								
							}
							lineaBasica.setZonasAtendimiento(zonasAten);
						}else{
							lineaBasica.setZonasAtendimiento(zonasAten);
						}

						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDir_distribuidor() != null)
							lineaBasica.setDistributorAddress(recursos_linea_basicaLocal.getDir_distribuidor());
						else
							lineaBasica.setDistributorAddress("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDir_armario() != null)
							lineaBasica.setClosetAddress(recursos_linea_basicaLocal.getDir_armario());
						else
							lineaBasica.setClosetAddress("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDir_caja() != null)
							lineaBasica.setBoxAddress(recursos_linea_basicaLocal.getDir_caja());
						else
							lineaBasica.setBoxAddress("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getPosicion_horizontal_asg() != null)
							lineaBasica.setHorizontalPosition(recursos_linea_basicaLocal.getPosicion_horizontal_asg());
						else
							lineaBasica.setHorizontalPosition("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getEst_linea() != null)
							lineaBasica.setStateLine(recursos_linea_basicaLocal.getEst_linea());
						else
							lineaBasica.setStateLine("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getTipo_caja() != null)
							lineaBasica.setBoxType(recursos_linea_basicaLocal.getTipo_caja());
						else
							lineaBasica.setBoxType("");
						
						assignedNumber.setBasicLine(lineaBasica);
					}
				}
				/**
				 * Datos de Banda Ancha
				 */
				RecursosBADelegate delegate = new RecursosBADelegate();				
				TR049EWideBand bandaAncha = delegate.getWideBand(peticionLocal);
				if(bandaAncha != null)
					assignedNumber.setWideBand(bandaAncha);
				else 
					assignedNumber.setWideBand(new TR049EWideBand());
				
				listaNumeros.add(assignedNumber);
			}
			
			tr049e.setId(idMensaje);
			tr049e.setSource("ATIEMPO");
			tr049e.setInterfaz("RES_NUM_ASIGNADOS");
			tr049e.setDestination("ESB");
			tr049e.setError("");
			tr049e.setErrorMessage("");
			tr049e.setVersion("1.0");

			tr049e.setAssignedNumbers(listaNumeros);

			ConsultarTroncalSipMQ consultarTroncalSipMQ = new ConsultarTroncalSipMQ();
			consultarTroncalSipMQ.enviarMensaje(tr049e);
				
		} catch (NamingException ne) {
			error = true;
			log.debug("enviarConsultaTroncalSip: NamingException: " + ne);
			return error;
		} catch (FinderException fe) {
			error = true;
			log.debug("enviarConsultaTroncalSip: FinderException: " + fe);
			return error;
		} catch (ATiempoAppEx ae) {
			error = true;
			log.debug("enviarConsultaTroncalSip: ATiempoAppEx: " + ae);
			return error;
		}
		return error;
	}

		//@idrincon req 3274
		public void respuestaConsultaTroncalSip(TR049S tr049s) throws ATiempoAppEx {
			log.debug("Entro a respuestaConsultaTroncalSip: "+tr049s.getAtisRequestNumber());
			BackendExecution bExecution = null;
			try{
				bExecution = BackendTraceUtil.initExecution(tr049s, log);
				bExecution.setNumPetAtiempo( new Long(tr049s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr049s.getId());
				bExecution.startOperation();
				
				Long atisRequestNumber = new Long(tr049s.getAtisRequestNumber());

				 boolean error = this.enviarConsultaTroncalSip(atisRequestNumber, tr049s.getId());
				 
				 if(error){
				 	TR049E tr049e =  new TR049E();
				 	
				 	tr049e.setId(tr049s.getId());
					tr049e.setSource("ATIEMPO");
					tr049e.setInterfaz("RES_NUM_ASIGNADOS");
					tr049e.setDestination("ESB");
					tr049e.setError("1");
					tr049e.setErrorMessage("Error en la consulta TroncalSip");
					tr049e.setVersion("1.0");
					
				 	ConsultarTroncalSipMQ consultarTroncalSipMQ = new ConsultarTroncalSipMQ();
					consultarTroncalSipMQ.enviarMensaje(tr049e);
				 }
				 
			}finally{  
				bExecution.endAll();
			}
		}
		
		//@idrincon req 3274
		public boolean enviarActivarLineasTroncalSip(TR050S tr050s)throws ATiempoAppEx{
			log.debug("Entro a respuesta activar lineas troncal sip ");
			boolean error = false;
			try{
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(tr050s.getAtiempoRequestNumber())));
				PeticionKey  peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				
				//implementacion del llamado de la actividad donde me encuentro
				Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				BintegradaLocal bintegradaLocal = bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero, idAplicacion );
				
				String idCorrelativo = bintegradaLocal.getBi_url_detalle();
				int idInicio = idCorrelativo.indexOf("instanciaActividad");
				
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
				
				//Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				//Mensaje_estado_lineaKey mensaje_estado_lineaKey = new Mensaje_estado_lineaKey(Long.valueOf(tr050s.getId()));
				//Mensaje_estado_lineaLocal mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(mensaje_estado_lineaKey);
				///////////////
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				//mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));//verificar
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(codActividad);
				
				//busco el que inserte
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome2 = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaKey mensaje_estado_lineaKey2 = new Mensaje_estado_lineaKey(IdCorrelativo);
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal2 = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(mensaje_estado_lineaKey2);
				
				///////////////
				String strPsTroncalSip = VpistbbaConfig.getVariable("PS_TRONCAL_SIP");
				String[] arrayPsTroncalSip = strPsTroncalSip.split(",");
				
				Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				Collection collectionProductoServioPeticion = psPetHome.findAllByPetiNumero(new Long(tr050s.getAtiempoRequestNumber()));
				for (Iterator iter = collectionProductoServioPeticion.iterator(); iter.hasNext();) {
					Producto_servicio_peticionLocal productoServicioPeticionlocal = (Producto_servicio_peticionLocal) iter.next();
					for (int i = 0; i < arrayPsTroncalSip.length; i++) {
						if(productoServicioPeticionlocal.getPsId().equals(new Long(arrayPsTroncalSip[i]))){
							if(!this.tieneBandaAnchaCorporativa(tr050s)){
								String fecha = tr050s.getActivationDate();
								if(fecha != null){
									DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
									Date date = dateFormat.parse(fecha);
									SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
									productoServicioPeticionlocal.setPspe_fecha_fin(new Fecha(formatter.format(date),"dd/MM/yyyy HH:mm" ).getTimestamp());
								}
							}
						}
					}
				}

				if(codActividad.equalsIgnoreCase(TRONCAL_SIP)){//validar si la actividad esta en configurara troncal sip si si bien si no retornar la ctivida y error
					if(tr050s.getAction().equals("2")){//SE DERIVA A PGC
						log.debug("Se deriva a control instalacion. troncal sip.");
						actDto.setObservacion("Se envía la petición a PGC.");
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal2, mensajeEstadoLineaLocal2.getCod_actividad_generadora(), new Long(603) ,actDto.getIdActividadFlujo());
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
						actividadEJB.terminar(actDto);
					}else{//LA ACTIVIDAD CONTINUA DE FORMA AUTOMATICA
						log.debug("Termina la actividad para troncal sip");
						actDto.setObservacion("Se mueve la petición, por SISGOT.");
						actividadEJB.terminar(actDto);
					}
				}else{
					error = true;
					return error;
				}
			} catch (NamingException ne) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+ne);
				return error;
			} catch (FinderException fe) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+fe);
				return error;
			} catch (TnProcesoExcepcion te) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+te);
				return error;
			} catch (ATiempoAppEx ae) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+ae);
				return error;
			} catch (NumberFormatException nfe) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+nfe);
				return error;
			} catch (Exception e) {
				error = true;
				log.debug("RecursosServiciosBean: enviarActivarLineasTroncalSip: "+e);
				return error;
			}
			//se cierra despues de enviar la tr
			return error;
		}

		/**
		 * Función para determinar si una petición tiene ps's de banda ancha corporativa que se encuentra en properties 
		 * @return true si la petición tiene por lo menos un ps de banda ancha
		 * @return false si la petición no tiene ningun ps de banda ancha
		 */
		private boolean tieneBandaAnchaCorporativa(TR050S tr050s){
			boolean tieneBA = false;
			
			try {
				String strPsAdslCorporativo = VpistbbaConfig.getVariable("PS_ADSL_CORPORATIVO");
				String[] arrayPsBACorporativa = strPsAdslCorporativo.split(",");
				
				Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);				
				Collection collectionProductoServioPeticion = psPetHome.findAllByPetiNumero(new Long(tr050s.getAtiempoRequestNumber()));
				
				for (Iterator iter = collectionProductoServioPeticion.iterator(); iter.hasNext() && !tieneBA;) {
					Producto_servicio_peticionLocal productoServicioPeticionlocal = (Producto_servicio_peticionLocal) iter.next();
					for (int i = 0; i < arrayPsBACorporativa.length; i++) {
						if(productoServicioPeticionlocal.getPsId().equals(new Long(arrayPsBACorporativa[i]))){
							tieneBA = true;
						}
					}
				}
				
			} catch (NamingException e) {
				log.debug("RecursosServiciosBean: tieneBandaAncha: "+e);
			} catch (NumberFormatException e) {
				log.debug("RecursosServiciosBean: tieneBandaAncha: "+e);
			} catch (FinderException e) {
				log.debug("RecursosServiciosBean: tieneBandaAncha: "+e);
			} catch (Exception e) {
				log.debug("RecursosServiciosBean: tieneBandaAncha: "+e);
			} 
			
			return tieneBA;
		}
		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaActivarLineasTRoncalSip(co.com.telefonica.atiempo.interfaces.atiempo.TR050S)
		 * req 3274
		 */
		public void respuestaActivarLineasTRoncalSip(TR050S tr050s) throws ATiempoAppEx {
			log.debug("Entro a respuestaActivarLineasTRoncalSip: "+tr050s.getAtisRequestNumber());
			BackendExecution bExecution = null;
			try{
				bExecution = BackendTraceUtil.initExecution(tr050s, log);
				bExecution.setNumPetAtiempo( new Long(tr050s.getAtisRequestNumber()));
				bExecution.setIdMensajeOp(tr050s.getId());
				bExecution.startOperation();
				
				TR050E tr050e= new TR050E();
				boolean error = this.enviarActivarLineasTroncalSip(tr050s);
				String actividadActual = recuperarActividad(tr050s);
					
				
				if(error){
					tr050e.setError("1");
					tr050e.setErrorMessage("Se presento un error durante el proceso");
					tr050e.setStatusRequest(actividadActual);
				}else{
					tr050e.setError("0");
					tr050e.setErrorMessage("OK");
					tr050e.setStatusRequest("");
				}
				
				tr050e.setId(tr050s.getId());
				tr050e.setSource("ATIEMPO");
				tr050e.setInterfaz("RES_ACTIVAR_LINEAS");
				tr050e.setDestination("ESB");
				tr050e.setVersion("1.0");
				
				ActivarLineasTroncalSipMQ activarLineasTroncalSipMQ = new ActivarLineasTroncalSipMQ();
				activarLineasTroncalSipMQ.enviarMensaje(tr050e);
			}finally{
				bExecution.endAll();
			}
		}
		
		
		private String recuperarActividad(TR050S tr050s){
			log.debug("Entro recuperarActividad");
			String codActividad = "";
			String nombreActividad = "";
			try {
				BintegradaLocalHome bintegradaLocalHome;
				bintegradaLocalHome = (BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(new Long(tr050s.getAtiempoRequestNumber())));
				PeticionKey  peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
				
				//implementacion del llamado de la actividad donde me encuentro
				Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				BintegradaLocal bintegradaLocal = bintegradaLocalHome.findByVisiblePetApl(peticionKey.peti_numero, idAplicacion );
				
				String idCorrelativo = bintegradaLocal.getBi_url_detalle();
				int idInicio = idCorrelativo.indexOf("instanciaActividad");
				
				if(idInicio!=-1){
					idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
					int idFin=idCorrelativo.indexOf("&");
					if(idFin!=-1){
						idCorrelativo=idCorrelativo.substring(19,idFin);
					}
				}
				
				codActividad = bintegradaLocal.getBi_url_detalle();
				idInicio = codActividad.indexOf("actividad");
				
				if(idInicio!=-1){
					codActividad=codActividad.substring(idInicio,codActividad.length());
					int idFin=codActividad.indexOf("&");
					if(idFin!=-1){
						codActividad=codActividad.substring(10,idFin);
						codActividad = codActividad.replace('+', ' ');
					}
				}
				
				String[] nombreActividadArr = codActividad.split("\\.");
				nombreActividad = nombreActividadArr[nombreActividadArr.length-1];
				
			} catch (NamingException e) {
				log.debug("RecursosServiciosBean: recuperarActividad: "+e);
			} catch (NumberFormatException ne) {
				log.debug("RecursosServiciosBean: recuperarActividad: "+ne);
			} catch (FinderException fe) {
				log.debug("RecursosServiciosBean: recuperarActividad: "+fe);
			}catch(Exception e){
				log.debug("RecursosServiciosBean: recuperarActividad: "+e);
			}
			return nombreActividad;
		}
		
		//@mfmendez req 4659
			
		/**
		 * Metodo para enviar el mensaje TR052E cuando se presente un quiebre de un ps
		 * 	que sea de familia BA o LB
		 * @author mfmendez
		 * @param numeroPeticion, el identificador de la petición atiempo
		 * @param tr052e, el objeto con algunos de los datos que se deben enviar
		 * @return error, indica si se presento error en el envio del mensaje (true) o no (false)
		 */		
		public boolean enviarInformeQuiebreFamiliaBAoLB(Long numeroPeticion, TR052E tr052e) {
			log.debug("ingresa a enviarInformeQuiebreFamiliaBAoLB.");
			
			boolean error = false;
			Long IdCorrelativo = new Long(0);
			try {							
				//DATOS DEL CUERPO DEL MENSAJE
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey key = new PeticionKey (numeroPeticion) ;
		        PeticionLocal peticionLocal = peticionHome.findByPrimaryKey (key) ;		              
																					
				if(peticionLocal != null){									
						if(peticionLocal.getGranite_code() == ComunInterfaces.conGranite)
							tr052e.setCpSource(ComunInterfaces.LEGADO_SIRS);
						else
							tr052e.setCpSource(ComunInterfaces.LEGADO_APSC);
				}else{
					tr052e.setCpSource("");
				}
				
				
				Collection collectionPeticiones = peticionLocal.getRecursos_linea_basica();						
				boolean itero = false;
				if (collectionPeticiones != null && collectionPeticiones.size() > 0) {
					for (Iterator iterator = collectionPeticiones.iterator(); iterator.hasNext() && !itero;) {
						itero = true;
						Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iterator.next();

						
						//Obtención del número Atis asociado a la petición (RQ 4659)						
						Peticion_atisLocal peticionAtis = peticionLocal.getFk_01_pet_atis();			
						Peticion_atisKey keyAtis = (Peticion_atisKey)peticionAtis.getPrimaryKey();
						
						if(keyAtis.cod_pet_cd != null)
							tr052e.setAtisRequestNumber(keyAtis.cod_pet_cd.longValue());
						else
							tr052e.setAtisRequestNumber(0);
						
						//
						if(peticionLocal != null && peticionLocal.getPeti_fecha_ingreso() != null){
							Date fecEntryDate = new Date(peticionLocal.getPeti_fecha_ingreso().getTime() + (peticionLocal.getPeti_fecha_ingreso().getNanos() / 1000000));
							SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
							formatoFecha.setLenient(false);							
							tr052e.setEntryDate(formatoFecha.format(fecEntryDate));
							//tr052e.setEntryDate(peticionLocal.getPeti_fecha_ingreso());
						} else{
							tr052e.setEntryDate(null);
						}						
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCod_central() != null)
							tr052e.setCentral(recursos_linea_basicaLocal.getCod_central().toString());
						else
							tr052e.setCentral("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDist_prim_asg() != null)
							tr052e.setDistrict(recursos_linea_basicaLocal.getDist_prim_asg().toString());
						else
							tr052e.setDistrict("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getArmario_asg() != null)
							tr052e.setCloset(recursos_linea_basicaLocal.getArmario_asg());
						else
							tr052e.setCloset("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCaja_asg() != null)
							tr052e.setBox(recursos_linea_basicaLocal.getCaja_asg());
						else
							tr052e.setBox("");
																						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getDist_sec_asg() != null)
							tr052e.setDistributorCode(recursos_linea_basicaLocal.getDist_sec_asg().toString());
						else
							tr052e.setDistributorCode("");
						
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getCable() != null)
							tr052e.setPrimaryCable(recursos_linea_basicaLocal.getCable());
						else
							tr052e.setPrimaryCable("");
						
						//No existe el cable secundario.
						tr052e.setSecondaryCable("");
						
						if(peticionLocal != null && peticionLocal.getCod_sgm_cta_cd() != null)
							tr052e.setSegment(peticionLocal.getCod_sgm_cta_cd().toString());
						else
							tr052e.setSegment("");
												
						//Se indico que debia ir vacio
						tr052e.setError("");
						
						Collection zonasAten = new ArrayList();
						if(recursos_linea_basicaLocal != null && recursos_linea_basicaLocal.getZonas_atendimiento() != null){
							Collection zonasAtendimiento = recursos_linea_basicaLocal.getZonas_atendimiento();							
							for (Iterator iter1 = zonasAtendimiento.iterator(); iter1
									.hasNext();) {
								Zonas_atendimientoLocal zona = (Zonas_atendimientoLocal) iter1.next();
								if(zona != null){
									if (zona.getIp() != null)
										zonasAten.add(zona.getIp());
									else 
										zonasAten.add("");
								}								
							}
							tr052e.setAttendanceAreas(zonasAten);
						}else{
							tr052e.setAttendanceAreas(zonasAten);
						}
					}
				}
				
//				DATOS DEL ENCABEZADO DE AGENDA SC
				IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				tr052e.setId(IdCorrelativo.toString());
				tr052e.setSource("ATIEMPO");
				tr052e.setInterfaz("CLIENTE_POTENCIAL");
				tr052e.setDestination("ESB");
				tr052e.setError("");
				tr052e.setVersion("1.0");
				
				log.debug("El correlativo del mensaje TR052E que se va a enviar es " + IdCorrelativo);
				
				//DATOS DE LA TABLA MENSAJE_ESTADO_LINEA
		        Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				
				mensajeEstadoLineaLocal.setPeti_numero(numeroPeticion);
				//Familia_producto_servicioKey familia_producto_servicioKey = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
				//mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familia_producto_servicioKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));//verificar
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(1))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(ComunInterfaces.ESTADO_CIERRE);

				ConsultarCliPotConstructoraMQ consultarCliPotConstructoraMQ = new ConsultarCliPotConstructoraMQ();
				consultarCliPotConstructoraMQ.enviarMensaje(tr052e);
					
			} catch (NamingException ne) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: NamingException: " + ne);
				return error;
			} catch (FinderException fe) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: FinderException: " + fe);
				return error;
			} catch (NullPointerException npe) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: NullPointerException: " + npe);
				return error;
			}catch (ATiempoAppEx ae) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: ATiempoAppEx: " + ae);
				return error;
			} catch (CreateException ce) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: CreateException: " + ce);
				return error;
			} catch (Exception e) {
				error = true;
				log.debug("enviarInformeQuiebreFamiliaBAoLB: Exception: " + e);
				return error;
			}
			return error;
		}

		public String obtenerCaracteristicaPeticion(Producto_servicio_peticionLocal producto_servicio_peticionLocal, Long caracteristica){
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
		 * @param peticionLocal
		 * @throws Exception
		 * 
		 */
		private Zonas_atendimientoLocal validarPSPromocion(PeticionLocal peticionLocal) throws Exception {
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalUserTutorWeb = propertiesBDLocalHome.findByPrimaryKey("PS_MARCA_CAMBIO_ZONA");
			long psMarca=Long.parseLong(propertiesDBLocalUserTutorWeb.getValor());
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection productosServPet=peticionLocal.getProducto_servicio_peticion();
			for (Iterator iter = productosServPet.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
				long psId=psp.getPsId().longValue();
				if(psId==psMarca){
		//			11
				}
			}
			return null;
			
		}

		
		
		/**
		 * @param peticionLocal
		 * @param zona_atendimientoLocal
		 * @param dslam1
		 * @param recursos_linea_basicaLocal
		 * @throws Exception
		 * 
		 */
		private Zonas_atendimientoLocal validarPSPromocion(PeticionLocal peticionLocal, Dslam1 dslam, Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws Exception {
			Long idZona=new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
			Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocalHome psPetHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Collection productosServPet=peticionLocal.getProducto_servicio_peticion();
			boolean estaPSMarca=false; 
			String idZonaAtendimiento=null;
			ZonasCoberturaPSMarcaLocalHome zonaCoberturaLHome= (ZonasCoberturaPSMarcaLocalHome)HomeFactory.getHome(ZonasCoberturaPSMarcaLocalHome.JNDI_NAME);
			ZonasCoberturaPSMarcaLocal acpsml;
			for (Iterator iter = productosServPet.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iter.next();
				try{
					acpsml=  zonaCoberturaLHome.findByZonaAnterior(dslam.getIp(),psp.getPsId().longValue());
					idZonaAtendimiento= acpsml.getZonaNueva();
					break; 
				}catch (FinderException fe){
					
				}
			}
			
			if(idZonaAtendimiento==null){
				log.debug("No se encontro una zona para el ps de marca el dslam ip   "+dslam.getIp());
				LocalidadKey lk = (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				log.debug("Se busca la zona por localidad "+lk.cod_loc);
				Collection c=  zonaCoberturaLHome.findByLocalidad(lk.cod_loc);
				if(c!=null && !c.isEmpty()){
					
					for (Iterator iter = c.iterator(); iter.hasNext()&& idZonaAtendimiento==null;) {
						ZonasCoberturaPSMarcaLocal zonaPSMarca = (ZonasCoberturaPSMarcaLocal) iter.next();
						for (Iterator iterPSP = productosServPet.iterator(); iterPSP.hasNext();) {
							Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) iterPSP.next();
							if(zonaPSMarca.getPsMarca()==psp.getPsId().longValue()){
								idZonaAtendimiento=zonaPSMarca.getZonaNueva();
								break;
							}  
						}
					}
				}
				if(idZonaAtendimiento==null){
					idZonaAtendimiento= dslam.getIp();
				}
			}
			
			//Se crea el objeto de zona. 
			Zonas_atendimientoLocal zona_atendimientoLocal=zonas_atendimientoLocalHome.create(idZona);
			zona_atendimientoLocal.setIp(idZonaAtendimiento);
			zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
		
			return zona_atendimientoLocal;
			
		}
		/*RQ.10402 - mfmendez*/
		public boolean validarEnvioMensajesPresenciaDigital (Collection mensajesEnviados, String psLocal) 
		{
			try {
				Mensaje_estado_baLocal mensajeEnviadoLocal=null; 

				for(Iterator o = mensajesEnviados.iterator(); o.hasNext();){
				     mensajeEnviadoLocal = (Mensaje_estado_baLocal) o.next();
				     Mensaje_estadoKey mensajeEstadoKey = new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk));
				     if (mensajeEnviadoLocal.getDesc_error().equals(psLocal) 
				       && mensajeEnviadoLocal.getMensaje_estado().getPrimaryKey().equals(mensajeEstadoKey)){
				      return true;
				    }
				 }
				return false;
			} catch (Exception e) {
				log.error("Error validando mensajes enviados Presencia Digital",e);
				return false;
			}
		}

		public void configurarPresenciaDigital(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
			try{
				log.debug("Ingresa a configurarPresenciaDigital, para la petición Atiempo No. "+nroPeticion);
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(nroPeticion);
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        //Se genera y almacena el IdPC que se va a usar para idetificar la peticion con Brm
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;
		        if(peticionLocal.getNum_ide_nu_pdg()==null || peticionLocal.getNum_ide_nu_pdg().equals("")){
		        	peticionLocal.setNum_ide_nu_pdg(ComunInterfaces.PREFIJO_PDG.concat(atis_number.toString()));
		        }
				/*Datos del body*/
				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
	        
				/*Se saca la operacion comercial dandole prioridad a las de alta*/
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				
								

				/*
				 * JOSE BAEZ - INDRA 2012
				 */
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				String[] listaTiposEquipo=incidentesDelegate.recuperarParametroFromPropertiesBD("PS_PADRE_PDG").split(",");
				
				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					listaProdSerPeticion= obtenerPSPeticion(listaProdSerPeticion,nroPeticion, act);
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					Long ps_id_pdg=null;
					
					validar_pdg : 
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();

						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal.getProducto_servicio().getPrimaryKey();
						ps_id_pdg = product_serv_key.ps_id;
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();
						
						for (int j = 0 ; j<listaTiposEquipo.length; j++) {
							if (listaTiposEquipo[j].equals(ps_id_pdg.toString())) {
								break validar_pdg;
								
							}
						}
					}
					/*llamado al metodo de envio del mensaje para el PS padre*/
					//se valida que no se haya enviado ese mensaje antes, para los reintentos desde PGI Y PGC

					
					
			        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			        Collection mensajeEstadoBa;
			        try {
			        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPetiNumero(nroPeticion);
			        } catch (FinderException e1) {
			        	mensajeEstadoBa = null;
			        	log.error("No Existe Respuesta en la base de enviados\n " + nroPeticion);
			            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + nroPeticion, ATiempoAppEx.FINDER);
			        }
										
			        
			        
			        if (!validarEnvioMensajesPresenciaDigital(mensajeEstadoBa,ps_id_pdg.toString())) 
			        	enviarMensajePresenciaDigitalPorPS(act, peticionLocal, nroPeticion, actGeneradora, prodServPeticionLocal, subpeticionAtisLocal, subpeticionCaracteristicasPS);
					
			        //Se elimina el PS padre de lo que se recibio
					listaProdSerPeticion.remove(prodServPeticionLocal);
					
					//se recorre nuevamente para enviar el restante de los PS
					ps_id_pdg = null;
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();

						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal.getProducto_servicio().getPrimaryKey();
						ps_id_pdg = product_serv_key.ps_id;
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();

						//se validan mensajes antes de enviar
				        if (!validarEnvioMensajesPresenciaDigital(mensajeEstadoBa,ps_id_pdg.toString()))						
						enviarMensajePresenciaDigitalPorPS(act, peticionLocal, nroPeticion, actGeneradora, prodServPeticionLocal, subpeticionAtisLocal, subpeticionCaracteristicasPS);
					}
					
					
				}
			} catch (FinderException e) {
	            log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 		
		}
		
		/**
		 * Obtiene una lista de ps coincidentes en las dos colecciones pasadas como parámetros
		 * @param listaProdSerPeticion Lista de productor servicio petici+on
		 * @param id_ps Lista de PS
		 * @return Una lista con los objetos coincidentes
		 * @throws Exception Cualquier error no contemplado en la operación
		 */
		private Collection obtenerPSPeticion(Collection listaProdSerPeticion, String[] id_ps) throws Exception {
			Collection result = new ArrayList();
    		for (Iterator prodPeticionIterator = listaProdSerPeticion.iterator(); prodPeticionIterator.hasNext();) {
    			Producto_servicio_peticionLocal prodServPeticionLocal = (Producto_servicio_peticionLocal) prodPeticionIterator.next();
				Long psID= prodServPeticionLocal.getPsId();
				for (int i = 0; i < id_ps.length; i++) {
					Long id = Long.valueOf(id_ps[i]);
					if(id.equals(psID)){
						result.add(prodServPeticionLocal);
						break;
					}
				}
    		}
			return result;
		}
		
		/**
		 * @param listaProdSerPeticion
		 * @param act
		 * @param nroPeticion
		 * @return
		 * @throws Exception
		 */
		private Collection obtenerPSPeticion(Collection listaProdSerPeticion, Long nroPeticion, ActividadEJBDTO act) throws Exception {
			Collection retorno= new ArrayList();
			Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
    		Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(nroPeticion, act.getActividadBD().getIdActFlujo());
    		for (Iterator prodPeticionIterator = listaProdSerPeticion.iterator(); prodPeticionIterator.hasNext();) {
    			Producto_servicio_peticionLocal prodServPeticionLocal = (Producto_servicio_peticionLocal) prodPeticionIterator.next();
				Long psID= prodServPeticionLocal.getPsId();
			
	    		for (Iterator petFlujoIterator = peticionFlujoList.iterator(); petFlujoIterator.hasNext();) {
					Peticion_flujoLocal petFlujo = (Peticion_flujoLocal) petFlujoIterator.next();
					if(petFlujo.getPrse_id().equals(psID)){
						retorno.add(prodServPeticionLocal);
						break;
					}
				}
    		}
			return retorno;
		}

		/**
		 * Metodo privado para el envio de cada mensaje de presencia digital gestionada
		 * @param act
		 * @param peticionLocal
		 * @param nroPeticion
		 * @param actGeneradora
		 * @param prodServPetiLocal
		 * @param subpeticionAtisLocal
		 * @param subpeticionCaracteristicas
		 * @throws ATiempoAppEx
		 */
		private void enviarMensajePresenciaDigitalPorPS(ActividadEJBDTO act, PeticionLocal peticionLocal, Long nroPeticion, String actGeneradora, 
				Producto_servicio_peticionLocal prodServPetiLocal, Subpeticion_atisLocal subpeticionAtisLocal, Collection subpeticionCaracteristicas) throws ATiempoAppEx{
			try{
				log.debug("Ingresa a enviarMensajePresenciaDigitalPorPS, para la petición Atiempo No. "+nroPeticion);
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
		        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		        
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(nroPeticion);
		        	
	        	TR054E object = new TR054E();
		        /*Datos del encabezado*/
				object.setId(idCorrelativoMensaje.toString());
				object.setDestination("ESB");
				object.setSource(ComunInterfaces.sistemaAtiempo);
				object.setInterfaz(ComunInterfaces.INTERFAZ_PRESENCIA_DIGITAL);
				object.setVersion("1.0");		
				
				
				/*Datos del body*/
				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
				
				if(pkAtis.cod_pet_cd != null)
					object.setAtisRequestNumber(pkAtis.cod_pet_cd.longValue());
				else
					object.setAtisRequestNumber(0);
				
				object.setAtiempoRequestNumber(nroPeticion.longValue());
				
				Long idOpCo = prodServPetiLocal.getIdOperacionComercial();
				Operacion_comercialLocal opCoLocal = prodServPetiLocal.getOperacion_comercial();
				
				if(opCoLocal != null){
					Operacion_comercialKey opCoKey = (Operacion_comercialKey) opCoLocal.getPrimaryKey();
					Long opCoId = opCoKey.opco_id;
					object.setCommercialOperation(opCoId.intValue());
				}
				
				Long codPS = subpeticionAtisLocal.getCod_pro_ser_cd();
				if(codPS != null)
					object.setCodePlan(codPS.toString());
//				idpc
				String idpc_pdg = peticionLocal.getNum_ide_nu_pdg();
								
				object.setIdPC(idpc_pdg);
				

				object.setNit(peticionLocal.getNum_doc_cli_cd());
				
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
				if(localidadLocal != null && localidadLocal.getPrimaryKey() != null){
					LocalidadKey localidadKey = (LocalidadKey) localidadLocal.getPrimaryKey();
					object.setCity(localidadKey.cod_loc);					
				}
				
				object.setClientName(peticionLocal.getNom_ds());
				
				if(peticionLocal.getPeti_fecha_ingreso() != null){
					Fecha fechaUtil = new Fecha(peticionLocal.getPeti_fecha_ingreso());
					object.setSaleDate(fechaUtil.getFechaconFormato(ComunInterfaces.FORMATO_FECHA));
				}

				if(peticionLocal.getNom_int_ds() != null)
					object.setContactName(peticionLocal.getNom_int_ds());
				else if(peticionLocal.getNom_ds() != null)
					object.setContactName(peticionLocal.getNom_ds());
				
				if(peticionLocal.getNom_cal_ds() != null)
					object.setAddress(peticionLocal.getNom_cal_ds());
				
				if(subpeticionCaracteristicas != null && subpeticionCaracteristicas.size() > 0){
					
					for(Iterator i = subpeticionCaracteristicas.iterator(); i.hasNext();){
						Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) i.next();
						
						if(subpeticionCaractLocal != null){
							Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
							
							if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_CUENTA_CORREO)){
								object.setClientEmail(subpeticionCaractLocal.getVal_ral_crc_cd());
								object.setContactEmail(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_INDICATIVO_TELEFONICO)){
								object.setIndicative(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_UNO)){
								object.setMessageOne(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_DOS)){
								object.setMessageTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_TRES)){
								object.setMessageThree(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_NUMERO_TELEFONICO)){
								object.setPhone(subpeticionCaractLocal.getVal_ral_crc_cd());
								//object.setContactPhone(subpeticionCaractLocal.getVal_ral_crc_cd());
								object.setCellphone(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_UNO)){
								object.setKeyWordOne(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_DOS)){ //JOSE BAEZ REQ 12804
								object.setKeyWordTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_TRES)){ //JOSE BAEZ REQ 12804
								object.setKeyWordThree(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_UNO)){
								object.setPromotionOne(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_DOS)){	//JOSE BAEZ REQ 12804
								object.setPromotionTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_TRES)){ //JOSE BAEZ REQ 12804
								object.setPromotionThree(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_REGION_PAUTA)){
								object.setRegion(subpeticionCaractLocal.getVal_ral_crc_cd());
							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.URL)){
								object.setUrl(subpeticionCaractLocal.getVal_ral_crc_cd());
							}
						}
					}					
				}
				
//				PARA ENVIAR EL CELULAR SE PREGUNTA POR TODOS LOS CAMPOS EN LA TR DE TELEFONOS
				//if ()
					//peticionLocal.get
					
				
				//object.setCellphone("celular");
				
				if(object.getPhone() == null && peticionLocal.getNum_ide_nu_stb() != null)
					object.setPhone(peticionLocal.getNum_ide_nu_stb());
				
				if(object.getContactPhone() == null && peticionLocal.getTel_cot_ds() != null)
					object.setContactPhone(peticionLocal.getTel_cot_ds());

		        /**/	        		
        		ConfigurarPresenciaDigitalMQ presenciaDigital = new ConfigurarPresenciaDigitalMQ();
        		presenciaDigital.enviarMensaje(object);
		        
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion(peticionLocal);
		        msgLocal.setCod_actividad_generadora(actGeneradora);
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setMensaje_estado(mensajeEspera);		
		        /**
		         * SE SACA EL PS DE LA PETICION CON EL PRIMARY KEY Y SE ALMACENA EN LA BD
		         */
		        Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPetiLocal.getProducto_servicio().getPrimaryKey();
				Long ps_id_pdg = product_serv_key.ps_id;
				msgLocal.setDesc_error(ps_id_pdg.toString());

//				log.error("Error al enviar la solicitud de Vista Global de VPI por no tener número de abonado ni idPC, para la petición: "+ nroPeticion +". Se deriva a PGI.");
//				insertarCausalesCnaPeticion(peticionLocal,actGeneradora,/*Quiebre por defecto*/new Long(701),act.getIdActividadFlujo());
//				
//				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
//				act.setObservacion("Error al enviar la solicitud de Vista Global por no contener los datos necesarios para la consulta, se deriva a PGI.", true);
//				act.setRealizarTerminoInmediato(true);		       
			}catch (FinderException e) {
	            log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (CreateException e) {
	        	log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuracion de Presencia Digital.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 		
		}
		
		public void procesarRespuestaConfiguracionPresenciaDigital(TR054S tr054s) throws ATiempoAppEx{
			BackendExecution bExecution = null;
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			
		        bExecution = BackendTraceUtil.initExecution(tr054s, log);		       
		        bExecution.setNumPetAtiempo(new Long(tr054s.getAtiempoRequestNumber()));
		        bExecution.setIdMensajeOp(tr054s.getId());
		        bExecution.startOperation();
		        
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr054s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr054s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr054s.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto ;
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr054s != null && tr054s.getError() != null && tr054s.getError().equals("0")){
		    		/*Procesamiento de la respuesta - No se debe procesar nada, solo cerrar la actividad*/
		        	/*Cierre de la actividad exitoso*/
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr054s.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeOk);
					actDto.setObservacion("Se recibe confirmación del PS "+mensajeEstadoBa.getDesc_error(), false);
		        }else{	        	
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					/*Inserción del quiebre*/
					//insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));		
					mensajeEstadoBa.setId_error(tr054s.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
					actDto.setObservacion("Error en configuracion, Respuesta del PS "+ mensajeEstadoBa.getDesc_error() +" : "+tr054s.getError()+" - "+tr054s.getErrorMessage(), false);
					
					errorLegado = getErrorLegado(tr054s.getError(),"TR054S");
					if(errorLegado != null){
						Long quiebre = errorLegado.getIdCausa();
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),quiebre,mensajeEstadoBa.getDesc_error());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					}
		        }
		    	//validamos el resto de los mensajes esten recibidos y correctos
		    	Collection mensajesPeticion = mensajeEstadoBaLocalHome.findPeticionActividad(actDto.getNumeroPeticion(),actDto.getCodigoActividad());
		    	Collection mensajesPeticion2=new ArrayList();
		    	mensajesPeticion2.addAll(mensajesPeticion);
		    	int total_mensajes = 0, mensajes_esperando=0;
		    	String destino_existente=null;
		    	for(Iterator k = mensajesPeticion2.iterator(); k.hasNext();){
		    		
		    		Mensaje_estado_baLocal	mensajeRespuestaPDG = (Mensaje_estado_baLocal) k.next();  
					if (((Mensaje_estadoKey)mensajeRespuestaPDG.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoEspera))){
						actividadEJB.grabarSinTerminar(actDto);
						return;
					}else if(((Mensaje_estadoKey)mensajeRespuestaPDG.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoError))){
						boolean hayPsOk=false;
						for(Iterator mensajesIT = mensajesPeticion2.iterator(); mensajesIT.hasNext();){
							total_mensajes++;
							
							//Se busca en el historial de mensajes si ya se envio un mensaje para el PS(desc_error)y esta en estado OK para no adicionarlo
							//al arreglo de mensajes con error porque es un reintento
							Mensaje_estado_baLocal	mensajeRespuestaPDG2= (Mensaje_estado_baLocal) mensajesIT.next();
							
							//log.error("mensajeRespuestaPDG2.getCod_familia_ps():--->"+mensajeRespuestaPDG2.getCod_familia_ps().toString());
							/*if (mensajeRespuestaPDG2.getId_error().equals("0")) {
								
							}*/
							log.error("mensajes total:"+total_mensajes);
							
							log.error("mensajeRespuestaPDG2.getDesc_error():"+mensajeRespuestaPDG2.getDesc_error()+" ,mensajeRespuestaPDG2.getId_error():"+mensajeRespuestaPDG2.getId_error());
							
							//log.error("")
							
							if (((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado.toString().equals("3")) {
								mensajes_esperando++;
							}
							
							log.error("mensajeRespuestaPDG2.getDesc_error():"+mensajeRespuestaPDG2.getDesc_error()+" ,mensajeRespuestaPDG2.getId_error():"+mensajeRespuestaPDG2.getId_error());
							
							log.error("((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado"+((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado);
							
							if(mensajeRespuestaPDG2.getDesc_error().equals(mensajeRespuestaPDG.getDesc_error()) &&
									((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoOk))){
								hayPsOk=true;
							}
							
						}
						if(!hayPsOk){
							if (destino_existente==null || !destino_existente.equals(VpistbbaConfig.getVariable("IDPGC"))) {
								errorLegado = getErrorLegado(mensajeRespuestaPDG.getId_error(),"TR054S");
								String destino = errorLegado.getPlataforma();
								destino_existente=destino;
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, destino);	
							}
						}
						
						log.error("mensajes_esperando:"+mensajes_esperando);
						
					}
		    	}					

		    	
		    	
		    	
		    	//se cierra la actividad finalmente
		    	
		    	actividadEJB.terminar (actDto);
		    	
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion con id: " + tr054s.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} catch (CreateException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			}  catch (ATiempoAppEx e) {
				throw e;
			} finally{
				bExecution.endAll();		
			}		
		}
		/*FIN - RQ.10402 - mfmendez*/
		
		private void insertarCausalesCnaPeticion(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, String ps) throws ATiempoAppEx, NamingException, CreateException, FinderException 
		{
//			PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome = (Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome = (Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome = (Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
			if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!= ComunInterfaces.estadoPeticionEnCurso)
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
			
			log.debug("cod_actividad que llega:"+idAct);
			
			
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
				Producto_servicioKey producto_servicioKey=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
				Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
				Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
				if(producto_servicioKey.ps_id.toString().equals(ps))
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
						
						log.debug("No entro con size 0:"+peticionKey.peti_numero+"estado:"+estado_psLocal);
					}
				}
			}
		}
		

		public void procesarRespuestaConfiguracionPdVA(TR055S tr055s) throws ATiempoAppEx{
			BackendExecution bExecution = null;

			try {
				Mensaje_estado_lineaLocalHome mensajeEstadoLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
		        Mensaje_estado_lineaLocal mensajeEstado;
		        try {
		        	mensajeEstado = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estado_lineaKey(new Long(tr055s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstado = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr055s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr055s.getId(), ATiempoAppEx.FINDER);
		        }
		        bExecution = BackendTraceUtil.initExecution(tr055s, log);		       
		        bExecution.setNumPetAtiempo(mensajeEstado.getPeticion());
		        bExecution.setIdMensajeOp(tr055s.getId());
		        bExecution.startOperation();  			
		        
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstado.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto ;	   		    	
		    	
		    	if(tr055s != null && tr055s.getError() != null && tr055s.getError().equals("0")){
		    		/*Procesamiento de la respuesta - No se debe procesar nada, solo cerrar la actividad*/
		        	/*Cierre de la actividad exitoso*/
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstado.getPeticion().getPrimaryKey()).peti_numero, mensajeEstado.getCod_actividad_generadora());
					mensajeEstado.setId_error(tr055s.getError());
					mensajeEstado.setDesc_error(tr055s.getErrorMessage());
					mensajeEstado.setFecha_cierre(df.format(new java.util.Date()));
					actDto.setObservacion("Se recibe confirmación del correcto envío de la información de Configuracion Puesto de voz avanzado.", true);
					actividadEJB.terminar (actDto);
		        }else{	        	
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstado.getPeticion().getPrimaryKey()).peti_numero, mensajeEstado.getCod_actividad_generadora());
					/*Inserción del quiebre*/
					insertarCausalesCnaPeticion(mensajeEstado.getPeticion(),mensajeEstado.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					
					ErrorLegadoLocal errorLegado = getErrorLegado(tr055s.getError(),"TR055S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr055s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					mensajeEstado.setId_error(tr055s.getError());
					mensajeEstado.setDesc_error(tr055s.getErrorMessage());
					mensajeEstado.setFecha_cierre(df.format(new java.util.Date()));
					actDto.setObservacion("Error al recibir respuesta del envío de la información de PDV, se deriva a "+bandeja+". Respuesta: "+tr055s.getError()+" - "+tr055s.getErrorMessage(), true);
					actividadEJB.terminar (actDto);
		        }
			} catch (Exception e) {
				log.error("Error procesando la respuesta TR055S o cerrando la peticion.",e);
	            throw new ATiempoAppEx("Error procesando la respuesta TR055S o cerrando la peticion con id: " + tr055s.getId() +".", e);
			} finally{
				bExecution.endAll();		
			}		
		}

		private void insertarCausalesCnaPeticion(PeticionLocal peticionLocal, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx, NamingException, CreateException, FinderException 
		{
//			PeticionLocal peticionLocal=mensajeEstadoBaLocal.getPeticion();
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome = catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome = (Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome = (Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome = (Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			PeticionKey peticionKey=(PeticionKey) peticionLocal.getPrimaryKey();
			if(peticionLocal.getEspe_id()!=null && peticionLocal.getEspe_id().intValue()!= ComunInterfaces.estadoPeticionEnCurso)
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
			
			log.debug("cod_actividad que llega:"+idAct);
			
			
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
						
						log.debug("Entro con size mayor a 0:"+peticionKey.peti_numero+"estado:"+estado_psLocal);
						
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
						
						log.debug("No entro con size 0:"+peticionKey.peti_numero+"estado:"+estado_psLocal.getDescripcion_estado_cierre());
					}
				}
			}
		}
		
		/**
		 * @param numeroPeticion
		 * @return
		 */
		public void configurarPdVa(ActividadEJBDTO act, Long numeroPeticion,Integer actiID,String codigoActividad) {
			try{
				String servicePhone=null;
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Peticion_flujoLocalHome peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				
				/*para calcular la operacion comercial*/
				PeticionKey key = new PeticionKey(numeroPeticion);
				PeticionLocal petilocal = peticionHome.findByPrimaryKey(key);
				PeticionKey peticionKey=(PeticionKey) petilocal.getPrimaryKey();
				
				Collection listaProdSerPeticion = petilocal.getProducto_servicio_peticion();
				listaProdSerPeticion= obtenerPSPeticion(listaProdSerPeticion,numeroPeticion, act);
				Producto_servicio_peticionLocal prodServPeticionLocal=null;
				for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
					prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
				}
				
				Collection peticionFlujoList = peticionFlujoLocalHome.findActividad(numeroPeticion, actiID);
				Collection productosServicio= new ArrayList();
				Long idOpCo = prodServPeticionLocal.getIdOperacionComercial();
				Operacion_comercialLocal opCoLocal = prodServPeticionLocal.getOperacion_comercial();				

				for (Iterator it = peticionFlujoList.iterator(); it.hasNext();) {
					Peticion_flujoLocal pf = (Peticion_flujoLocal) it.next();
					ProductoServicioTR prodTR=new ProductoServicioTR();
					prodTR.setPs(pf.getPrse_id().toString());
					productosServicio.add(prodTR);
					if(opCoLocal != null){
						Operacion_comercialKey opCoKey = (Operacion_comercialKey) opCoLocal.getPrimaryKey();
						String opCoId = opCoKey.opco_id.toString();
						prodTR.setOperacionComercial(opCoId);
					}
				}
				
				
				if(peticionHome==null)
					peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				log.info("Peticion Buscar:" + numeroPeticion);
				PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(new PeticionKey(numeroPeticion));
				
				
				TR055E tr055e= new TR055E();
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Collection localidades= new ArrayList();
				String codLoc= ((LocalidadKey)peticionLocal.getFk_01_localidad().getPrimaryKey()).cod_loc;
				Localidad l = new Localidad();
				l.setLocalidad(codLoc);
				localidades.add(l);
				 /*Datos del encabezado*/
				tr055e.setId(idCorrelativoMensaje.toString());
				tr055e.setDestination("ESB");
				tr055e.setSource(ComunInterfaces.sistemaAtiempo);
				tr055e.setInterfaz(ComunInterfaces.INTERFAZ_PDVA);
				tr055e.setVersion("1.0");	
				
				
				
				/* JOSE BAEZ
				 * PARA NUEVOS CAMPOS EN LA TR
				 */
				tr055e.setAtiempoRequestNumber(numeroPeticion.longValue());
				Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
				
				if(pkAtis.cod_pet_cd != null)
					tr055e.setAtisRequestNumber(pkAtis.cod_pet_cd.longValue());
				else
					tr055e.setAtisRequestNumber(0);
				/*
				 * FIN DE NUEVOS CAMPOS TR-055 JOSE BAEZ
				 */
				
				log.info("Setiado los valores atis y atiempo:" + numeroPeticion);
				
				Peticion_atisLocal peticionAtis = peticionLocal.getFk_01_pet_atis();
				tr055e.setNombreCliente(peticionAtis.getNom_ds());
				String apellido1=peticionAtis.getPri_ape_ds();
				apellido1 = apellido1==null?"":apellido1;
				String apellido2=peticionAtis.getSeg_ape_ds();
				apellido2=apellido2==null || apellido2.equals("")?"":" "+apellido2;
				tr055e.setApellidoCliente(apellido1+apellido2);
				tr055e.setLocalidades(localidades);
				tr055e.setNit(peticionLocal.getNum_doc_cli_cd());
				tr055e.setProductosServicio(productosServicio);
				
				/* 
				 * JOSE BAEZ 
				 * ENVIAR EL NUMERO DE TELEFONO ASIGNADO.
				 */
				
				/*Mensaje_estado_lineaLocal msjEstadoLineaLocal ;
				msjEstadoLineaLocal=null;
				Collection recursosLineaBasica = msjEstadoLineaLocal.getPeticion().getRecursos_linea_basica();
				*/
				
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
				for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); ) {
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
				}

				if(recursos_linea_basicaLocal!=null && recursos_linea_basicaLocal.getTelefono_asg()!=null) {
				log.info("calculando telefono ");
				servicePhone = String.valueOf(recursos_linea_basicaLocal.getTelefono_asg());
				}
				
				log.info("Telefono asignado:"+servicePhone);
				
				//Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				
				
				tr055e.setTelefonoPadre(servicePhone);
				
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(codigoActividad);
				
	
				ConfigurarPdVAMQ configurarPdVAMQ = new ConfigurarPdVAMQ();
				configurarPdVAMQ.enviarMensaje(tr055e);

			}catch (Exception e) {
				log.error("Error en el envio de la tr055 peticion "+numeroPeticion,e);
			}

		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarMediacionMovil()
		 */
		public void configurarMediacionMovil(ActividadEJBDTO act) throws ATiempoAppEx {
			try{
				log.debug("Ingresa a configurarMediacionMovil, para la petición Atiempo No. "+ act.getNumeroPeticion());
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(act.getNumeroPeticion());
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;

				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
	        
				/*Se saca la operacion comercial dandole prioridad a las de alta*/
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long caractCeluFijo = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CARACT_CELUFIJO"));
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				Recursos_linea_basicaLocalHome recursosLineaBLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recusosLineaBasicaLocal = recursosLineaBLocalHome.findByPeticion(act.getNumeroPeticion());

				TR612E tr = new TR612E();
				tr.setId(idCorrelativoMensaje.toString());
				tr.setAtisRequestNumber(atis_number.longValue());
				tr.setAtiempoRequestNumber(act.getNumeroPeticion().longValue());
				tr.setFatherPhoneNumber(recusosLineaBasicaLocal.getTelefono_asg().toString());
				tr.setDestination("ESB");
				tr.setInterfaz(ComunInterfaces.INTERFAZ_CONF_MEDIACION_MOVIL);
				tr.setSource(ComunInterfaces.sistemaAtiempo);
				tr.setVersion("1.0");
				
				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					listaProdSerPeticion= obtenerPSPeticion(listaProdSerPeticion,act.getNumeroPeticion(), act);
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					Long ps_id_pdg=null;
					
					validar_pdg : 
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						
						tr.setPsCode(prodServPeticionLocal.getPsId().toString());
						tr.setOcCode(prodServPeticionLocal.getIdOperacionComercial().toString());
						
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();

						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal.getProducto_servicio().getPrimaryKey();
						ps_id_pdg = product_serv_key.ps_id;
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();
						
						if(subpeticionCaracteristicasPS != null && subpeticionCaracteristicasPS.size() > 0){
							
							for(Iterator j = subpeticionCaracteristicasPS.iterator(); j.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) j.next();
								if(subpeticionCaractLocal != null){
									Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
									if(caractCeluFijo.equals(subpeticionCaractKey.getCod_crc_cd())){
										tr.setCeluFijoPhoneNumber(subpeticionCaractLocal.getVal_ral_crc_cd());
										break validar_pdg;
									}
								}
							}
						}
					}
					
					Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
					Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
			        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
					Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion(peticionLocal);
			        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setMensaje_estado(mensajeEspera);
			        
			        ConfMediacionMovilMQ mq  = new ConfMediacionMovilMQ();
			        mq.enviarMensaje(tr);

				}
			} catch (FinderException e) {
	            log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 		
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#recibirResConfMediacionMovil()
		 */
		public void recibirResConfMediacionMovil(TR612S tr) throws ATiempoAppEx {
			BackendExecution bExecution = null;
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			
		        bExecution = BackendTraceUtil.initExecution(tr, log);		       
		        bExecution.setIdMensajeOp(tr.getId());
		        bExecution.startOperation();
		        
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto ;
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr != null && tr.getError() != null && tr.getError().equals("0")){
		        	/*Cierre de la actividad exitoso*/
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeOk);
					actDto.setObservacion("Se recibe confirmación del PS "+tr.getResponse(), false);
		        }else{	        	
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
					errorLegado = getErrorLegado(tr.getError(),"TR612S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
					}
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en configuracion, Respuesta del PS "+ mensajeEstadoBa.getDesc_error() 
							+" : "+tr.getError()+" - "+tr.getErrorMessage()+" Se deriva a "+bandeja, false);
					Long quiebre = errorLegado.getIdCausa();
					insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),quiebre,mensajeEstadoBa.getDesc_error());
		        }
	    	
		    	actividadEJB.terminar (actDto);
		    	
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR612S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR612S o cerrando la peticion con id: " + tr.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR612S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR612S o cerrando la peticion. con id: " + tr.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR612S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR612S o cerrando la peticion. con id: " + tr.getId() +".", e);
			} catch (CreateException e) {
				log.error("Error procesando la respuesta TR612S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR612S o cerrando la peticion. con id: " + tr.getId() +".", e);
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR612S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR612S o cerrando la peticion. con id: " + tr.getId() +".", e);
			}  catch (ATiempoAppEx e) {
				throw e;
			} finally{
				bExecution.endAll();		
			}		
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarPaqueteMovil(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
		 */
		public void configurarPaqueteMovil(ActividadEJBDTO act) throws ATiempoAppEx{
			try{
				log.debug("Ingresa a configurarPaqueteMovil, para la petición Atiempo No. "+ act.getNumeroPeticion());
		        
				Long nroPeticion = act.getNumeroPeticion();
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(act.getNumeroPeticion());
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;

				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
	        
				/*Se saca la operacion comercial dandole prioridad a las de alta*/
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long caractPaqueteMovil = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CARACT_PAQUETE_MOVIL"));
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				Recursos_linea_basicaLocalHome recursosLineaBLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recusosLineaBasicaLocal = recursosLineaBLocalHome.findByPeticion(act.getNumeroPeticion());
				
				String numeroTelefono = recusosLineaBasicaLocal.getTelefono_asg().toString();
				String nuevoNumeroTelefono = null;
				if(recusosLineaBasicaLocal.getTelefono_ant() != null){
					nuevoNumeroTelefono = recusosLineaBasicaLocal.getTelefono_ant().toString();
				}
				

				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					listaProdSerPeticion= obtenerPSPeticion(listaProdSerPeticion,act.getNumeroPeticion(), act);
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					Long ps_id_pdg=null;
					
					Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = 
						(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			        Collection mensajeEstadoBa;
			        try {
			        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPetiNumero(nroPeticion);
			        } catch (FinderException e1) {
			        	mensajeEstadoBa = null;
			        	log.error("No Existe Respuesta en la base de enviados\n " + nroPeticion);
			            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + nroPeticion, ATiempoAppEx.FINDER);
			        }
			        
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();

						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal
							.getProducto_servicio().getPrimaryKey();
						ps_id_pdg = product_serv_key.ps_id;
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();

						//se validan mensajes antes de enviar
				        if (!validarEnvioMensajesPaqueteMovil(mensajeEstadoBa,ps_id_pdg.toString())){
				        	enviarMensajePaqueteMovilPorPS(act, peticionLocal, prodServPeticionLocal, subpeticionAtisLocal, 
				        			subpeticionCaracteristicasPS, numeroTelefono, nuevoNumeroTelefono);
				        }
				        	
					}
				}
			} catch (FinderException e) {
	            log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuración Mediación Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 		
		}
		
		/**
		 * Método para enviar un mensaje de configuracion de paquete fijo movil
		 * @param act Actividad
		 * @param peticionLocal Peticion
		 * @param prodServPetiLocal Producto servicio peticion
		 * @param subpeticionAtisLocal Subpeticion
		 * @param subpeticionCaracteristicas Caracteristicas de la subpeticion
		 * @param numeroTelefono Numero de telefono
		 * @throws ATiempoAppEx Si ocurre un error al enviar el mensaje
		 */
		private void enviarMensajePaqueteMovilPorPS(ActividadEJBDTO act, PeticionLocal peticionLocal, 
				Producto_servicio_peticionLocal prodServPetiLocal, Subpeticion_atisLocal subpeticionAtisLocal, 
				Collection subpeticionCaracteristicas, String numeroTelefono, String nuevoNumeroTelefono) throws ATiempoAppEx{
			try{
				log.debug("Ingresa a enviarMensajePaqueteMovilPorPS, para la petición Atiempo No. "+act.getNumeroPeticion());
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
		        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		        
		        
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(act.getNumeroPeticion());
		        	
	        	TR613E tr613e = new TR613E();
		        /*Datos del encabezado*/
				tr613e.setId(idCorrelativoMensaje.toString());
				tr613e.setDestination("ESB");
				tr613e.setSource(ComunInterfaces.sistemaAtiempo);
				tr613e.setInterfaz(ComunInterfaces.INTERFAZ_CONF_PAQUETE_MOVIL);
				tr613e.setVersion("1.0");		
				
				tr613e.setNumeroTelefonoFijo(numeroTelefono);
				if(nuevoNumeroTelefono != null && !nuevoNumeroTelefono.trim().equals("")){
					tr613e.setNuevoTelefonoFijo(nuevoNumeroTelefono);	
				}
				
				/*Datos del body*/
				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
				
				if(pkAtis.cod_pet_cd != null){
					tr613e.setAtisRequestNumber(pkAtis.cod_pet_cd);
				}else{
					tr613e.setAtisRequestNumber(new Long(0));
				}
				
				tr613e.setAtiempoRequestNumber(act.getNumeroPeticion());
				
				Long idOpCo = prodServPetiLocal.getIdOperacionComercial();
				Operacion_comercialLocal opCoLocal = prodServPetiLocal.getOperacion_comercial();
				
				if(opCoLocal != null){
					Operacion_comercialKey opCoKey = (Operacion_comercialKey) opCoLocal.getPrimaryKey();
					Long opCoId = opCoKey.opco_id;
					tr613e.setOperacion(opCoId.toString());
				}
				
				Long codPS = subpeticionAtisLocal.getCod_pro_ser_cd();
				if(codPS != null){
					tr613e.setCodigoPlan(codPS.toString());
				}
								
				PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
				String caracteristica = peticionesDelegate.recuperarParametroFromPropertiesBD("CARACT_PAQUETE_MOVIL");
				Long caractPaqueteMovil = new Long(0L);
				if(caracteristica != null){
					try{
						caractPaqueteMovil = new Long(caracteristica);
					}catch(NumberFormatException nfe){
						log.debug("El valor en PROPERTIES_BD para CARACT_PAQUETE_MOVIL, no tiene formato de numero valido.");
					}
				}
				
				if(subpeticionCaracteristicas != null && subpeticionCaracteristicas.size() > 0){
					for(Iterator i = subpeticionCaracteristicas.iterator(); i.hasNext();){
						Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) i.next();
						if(subpeticionCaractLocal != null){
							Subpeticion_caracteristicasKey subpeticionCaractKey = 
								(Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
							if(subpeticionCaractKey.cod_crc_cd.equals(caractPaqueteMovil)){
								tr613e.setNumeroTelefonoMovil(subpeticionCaractLocal.getVal_ral_crc_cd());
								break;
							}
						}
					}					
				}
				
				ConfPaqueteMovilMQ paqueteMovilMQ = new ConfPaqueteMovilMQ();
				paqueteMovilMQ.enviarMensaje(tr613e);
		        
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome =  
		        	(Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion(peticionLocal);
		        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setMensaje_estado(mensajeEspera);		
		        /**
		         * SE SACA EL PS DE LA PETICION CON EL PRIMARY KEY Y SE ALMACENA EN LA BD
		         */
		        Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPetiLocal.getProducto_servicio().getPrimaryKey();
				Long ps_id_pdg = product_serv_key.ps_id;
				msgLocal.setDesc_error(ps_id_pdg.toString());

			}catch (FinderException e) {
	            log.error("Error al enviar Configuracion de Paquete Movil PS.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (CreateException e) {
	        	log.error("Error al enviar Configuracion de Paquete Movil PS.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuracion de Paquete Movil PS.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuracion de Paquete Movil PS.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 		
		}
		
		public boolean validarEnvioMensajesPaqueteMovil(Collection mensajesEnviados, String psLocal) 
		{
			try {
				Mensaje_estado_baLocal mensajeEnviadoLocal=null; 

				for(Iterator o = mensajesEnviados.iterator(); o.hasNext();){
				     mensajeEnviadoLocal = (Mensaje_estado_baLocal) o.next();
				     Mensaje_estadoKey mensajeEstadoKey = new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk));
				     if (mensajeEnviadoLocal.getDesc_error().equals(psLocal) 
				       && mensajeEnviadoLocal.getMensaje_estado().getPrimaryKey().equals(mensajeEstadoKey)){
				      return true;
				    }
				 }
				return false;
			} catch (Exception e) {
				log.error("Error validando mensajes enviados Paquete fijo movil",e);
				return false;
			}
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#recibirResConPaqueteMovil(co.com.telefonica.atiempo.interfaces.atiempo.TR613S)
		 */
		public void recibirResConPaqueteMovil(TR613S tr613s) throws ATiempoAppEx{
			BackendExecution bExecution = null;
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			
		        bExecution = BackendTraceUtil.initExecution(tr613s, log);		       
		        bExecution.setNumPetAtiempo(tr613s.getAtiempoRequestNumber());
		        bExecution.setIdMensajeOp(tr613s.getId());
		        bExecution.startOperation();
		        
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr613s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr613s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr613s.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto ;
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr613s != null && tr613s.getError() != null && tr613s.getError().equals("0")){
		    		/*Procesamiento de la respuesta - No se debe procesar nada, solo cerrar la actividad*/
		        	/*Cierre de la actividad exitoso*/
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr613s.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeOk);
					actDto.setObservacion("Se recibe confirmación del PS "+tr613s.getDescripcion(), false);
		        }else{	        	
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					/*Inserción del quiebre*/
					//insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));		
					mensajeEstadoBa.setId_error(tr613s.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
					actDto.setObservacion("Error en configuracion, Respuesta del PS "
							+ mensajeEstadoBa.getDesc_error() +" : "+tr613s.getError()+" - "+tr613s.getErrorMessage(), false);
					
					errorLegado = getErrorLegado(tr613s.getError(),"TR613S");
					if(errorLegado != null){
						Long quiebre = errorLegado.getIdCausa();
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),quiebre,mensajeEstadoBa.getDesc_error());
					}else{
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					}
		        }
		    	//validamos el resto de los mensajes esten recibidos y correctos
		    	Collection mensajesPeticion = mensajeEstadoBaLocalHome.findPeticionActividad(actDto.getNumeroPeticion(),actDto.getCodigoActividad());
		    	Collection mensajesPeticion2=new ArrayList();
		    	mensajesPeticion2.addAll(mensajesPeticion);
		    	int total_mensajes = 0, mensajes_esperando=0;
		    	String destino_existente=null;
		    	for(Iterator k = mensajesPeticion2.iterator(); k.hasNext();){
		    		
		    		Mensaje_estado_baLocal	mensajeRespuestaPDG = (Mensaje_estado_baLocal) k.next();  
					if (((Mensaje_estadoKey)mensajeRespuestaPDG.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoEspera))){
						actividadEJB.grabarSinTerminar(actDto);
						return;
					}else if(((Mensaje_estadoKey)mensajeRespuestaPDG.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoError))){
						boolean hayPsOk=false;
						for(Iterator mensajesIT = mensajesPeticion2.iterator(); mensajesIT.hasNext();){
							total_mensajes++;
							
							//Se busca en el historial de mensajes si ya se envio un mensaje para el PS(desc_error)y esta en estado OK para no adicionarlo
							//al arreglo de mensajes con error porque es un reintento
							Mensaje_estado_baLocal	mensajeRespuestaPDG2= (Mensaje_estado_baLocal) mensajesIT.next();
							
							//log.error("mensajeRespuestaPDG2.getCod_familia_ps():--->"+mensajeRespuestaPDG2.getCod_familia_ps().toString());
							/*if (mensajeRespuestaPDG2.getId_error().equals("0")) {
								
							}*/
							log.error("mensajes total:"+total_mensajes);
							
							log.error("mensajeRespuestaPDG2.getDesc_error():"+mensajeRespuestaPDG2.getDesc_error()+" ,mensajeRespuestaPDG2.getId_error():"+mensajeRespuestaPDG2.getId_error());
							
							//log.error("")
							
							if (((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado.toString().equals("3")) {
								mensajes_esperando++;
							}
							
							log.error("mensajeRespuestaPDG2.getDesc_error():"+mensajeRespuestaPDG2.getDesc_error()+" ,mensajeRespuestaPDG2.getId_error():"+mensajeRespuestaPDG2.getId_error());
							
							log.error("((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado"+((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado);
							
							if(mensajeRespuestaPDG2.getDesc_error().equals(mensajeRespuestaPDG.getDesc_error()) &&
									((Mensaje_estadoKey)mensajeRespuestaPDG2.getMensaje_estado().getPrimaryKey()).cod_estado.equals(new Integer (ComunInterfaces.estadoOk))){
								hayPsOk=true;
							}
							
						}
						if(!hayPsOk){
							String destino = VpistbbaConfig.getVariable("IDPGC");
							if (destino_existente==null || !destino_existente.equals(VpistbbaConfig.getVariable("IDPGC"))) {
								errorLegado = getErrorLegado(mensajeRespuestaPDG.getId_error(),"TR613S");
								if(errorLegado != null){
									if(errorLegado.getPlataforma() == null || errorLegado.getPlataforma().trim().equals("")){
										actDto.setObservacion("Error: "
								 				+ errorLegado.getCodigoError() + "Descripcion: "+tr613s.getErrorMessage());
										actividadEJB.terminar (actDto);
										return;
									}
									destino = errorLegado.getPlataforma();
								}
								destino_existente=destino;
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, destino);	
							}
						}
						log.error("mensajes_esperando:"+mensajes_esperando);
					}
		    	}					
		    	//se cierra la actividad finalmente
		    	actividadEJB.terminar (actDto);
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR613S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR613S o cerrando la peticion con id: " + tr613s.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR613S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR613S o cerrando la peticion. con id: " + tr613s.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR613S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR613S o cerrando la peticion. con id: " + tr613s.getId() +".", e);
			} catch (CreateException e) {
				log.error("Error procesando la respuesta TR613S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR613S o cerrando la peticion. con id: " + tr613s.getId() +".", e);
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR613S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR613S o cerrando la peticion. con id: " + tr613s.getId() +".", e);
			}  catch (ATiempoAppEx e) {
				throw e;
			} finally{
				bExecution.endAll();		
			}		
		}
		
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configuracionFijaMovil(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
		 */
		public void recargaFijaMovil(ActividadEJBDTO act) throws ATiempoAppEx, NumberFormatException {
			// TODO Apéndice de método generado automáticamente
			log.debug("Ingresa a RecargaFijaMovil, para la petición Atiempo No. "+ act.getNumeroPeticion());
			try{
				boolean error=false;
				Long caracteristica;
		        /*obtiene la peticion Local de acuerdo al numero que ingresa al metodo*/
		        PeticionKey key = new PeticionKey(act.getNumeroPeticion());
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;

				/*obtiene el id de la peticion Atis*/
		        Peticion_atisLocal petAtisLocal = peticionLocal.getFk_01_pet_atis();
		        Peticion_atisKey pkAtis = (Peticion_atisKey)petAtisLocal.getPrimaryKey();
	        
				/*Se saca la operacion comercial dandole prioridad a las de alta*/
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long celularFijoMovil = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CELULAR_FIJOMOVIL"));
				//Long valorRecarga = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("VALOR_FIJOMOVIL"));

				TR614E tr614e = new TR614E();
				tr614e.setId(idCorrelativoMensaje.toString());
				tr614e.setAtisRequestNumber(atis_number);
				tr614e.setAtiempoRequestNumber(act.getNumeroPeticion());
				tr614e.setFijoPhoneNumber("1");
				tr614e.setDestination("ESB");
				tr614e.setInterfaz(ComunInterfaces.INTERFAZ_RECARGA_FIJA_MOVIL);
				tr614e.setSource(ComunInterfaces.sistemaAtiempo);
				tr614e.setVersion("1.0");
				
				tr614e.setValor("1");
				
				Ciclos_facturacionLocalHome cicloFacturacionLocalHome=(Ciclos_facturacionLocalHome) HomeFactory.getHome(Ciclos_facturacionLocalHome.JNDI_NAME);
				Ciclos_facturacionLocal cicloFacturacionLocal = null;
				
				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					listaProdSerPeticion= obtenerPSPeticion(listaProdSerPeticion,act.getNumeroPeticion(), act);
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					Long ps_id_pdg=null;
					
					validar_pdg : 
					for(Iterator producto = listaProdSerPeticion.iterator(); producto.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) producto.next();
						
						tr614e.setCodigoPlan(prodServPeticionLocal.getPsId().toString());
						tr614e.setOperacion(prodServPeticionLocal.getIdOperacionComercial().toString());
						
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();

						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal.getProducto_servicio().getPrimaryKey();
						ps_id_pdg = product_serv_key.ps_id;
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();
						
						
						
						if(subpeticionCaracteristicasPS != null && subpeticionCaracteristicasPS.size() > 0){
							
							for(Iterator peticion = subpeticionCaracteristicasPS.iterator(); peticion.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) peticion.next();
								if(subpeticionCaractLocal != null){
									Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
									caracteristica=subpeticionCaractKey.getCod_crc_cd();
									if(celularFijoMovil.equals(caracteristica)){
										tr614e.setMovilPhoneNumber(subpeticionCaractLocal.getVal_ral_crc_cd());
									}
									/*if(valorRecarga.equals(caracteristica)){
										tr614e.setValor(subpeticionCaractLocal.getVal_ral_crc_cd());
									}*/
								}
							}
							//Valida ciclo de facturación;
							try{
								cicloFacturacionLocal = cicloFacturacionLocalHome.findByPrimary(Integer.parseInt(peticionLocal.getInf_cicl_fac()));
								tr614e.setDiaRecarga(cicloFacturacionLocal.getCorte().toString());
							}catch (FinderException e) {
								Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
								Mensaje_estadoLocal mensajeError = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
						        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
								Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
								msgLocal.setPeticion(peticionLocal);
							    msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
							    msgLocal.setFecha_envio(df.format(new java.util.Date()));
							    msgLocal.setMensaje_estado(mensajeError);
							    ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
								IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (act.getCodigoActividad()) ;
						    	insertarCausalesCnaPeticion(msgLocal.getPeticion(),act.getCodigoActividad(),/*Quiebre por defecto*/new Long(701),act.getIdActividadFlujo());
						    	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
								msgLocal.setId_error("1");
								msgLocal.setDesc_error("No se encuentra registro en ciclo facturación numero: "+peticionLocal.getInf_cicl_fac());
								msgLocal.setFecha_cierre(df.format(new java.util.Date()));
								act.setObservacion("Error al enviar la información de la Recarga fija móvil, se deriva a PGI. Respuesta: "+"1"+" - "+"No se encuentra registro en ciclo facturación numero: "+peticionLocal.getInf_cicl_fac(), true);
								act.setRealizarTerminoInmediato(true);
								actividadEJB.terminar (act);
							}
							
							// Envio de tr
							if(!tr614e.getOperacion().equals("70")){
								Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
								Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
						        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
								Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
						        msgLocal.setPeticion(peticionLocal);
						        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
						        msgLocal.setFecha_envio(df.format(new java.util.Date()));
						        msgLocal.setMensaje_estado(mensajeEspera);
						        
						        RecargaFijaMovilMQ mq  = new RecargaFijaMovilMQ();
						        mq.enviarMensaje(tr614e);
								}else{
									if(tr614e.getOperacion().equals("70")&&listaProdSerPeticion.size()==1){
										Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
										Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
								        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
										Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
								        msgLocal.setPeticion(peticionLocal);
								        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
								        msgLocal.setFecha_envio(df.format(new java.util.Date()));
								        msgLocal.setMensaje_estado(mensajeEspera);
								        
								        RecargaFijaMovilMQ mq  = new RecargaFijaMovilMQ();
								        mq.enviarMensaje(tr614e);
									}
								}
						}
						
					}
				}			
				} catch (FinderException e) {
	            log.error("Error al enviar Recarga Fija Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Recarga Fija Movil.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Recarga Fija Movil.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 
		}
		
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#respuestaFijaMovil(co.com.telefonica.atiempo.interfaces.atiempo.TR614S)
		 */
		public void respuestaFijaMovil(TR614S tr614s) throws ATiempoAppEx{
			// TODO Apéndice de método generado automáticamente
			log.debug("Ingresa a configuracionFijaMovil, para la petición Atiempo No. "+ tr614s.getAtiempoRequestNumber());
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				Mensaje_estadoLocal mensajeManual = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
			
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr614s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr614s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr614s.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto = null ;
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr614s != null && tr614s.getError() != null && tr614s.getError().equals("0")){
		        	/*Cierre de la actividad exitoso*/
		    		if(tr614s.getEstado().equals("1")){
						mensajeEstadoBa.setId_error(tr614s.getError());
		    			actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
						mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
						mensajeEstadoBa.setMensaje_estado(mensajeOk);
						actDto.setObservacion("Se recibe confirmación del PS "+tr614s.getError(), false);
						actividadEJB.terminar(actDto);
		    		}else if(tr614s.getEstado().equals("3")){
		    			actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
		    			actDto.setObservacion("Estado en Proceso= " + tr614s.getErrorMessage()+". ");
						actividadEJB.grabarSinTerminar(actDto);
		    		}
		        }else{	  
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
		        	errorLegado = getErrorLegado(tr614s.getError(),"TR614S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							actDto.setObservacion("Error: "
					 				+ errorLegado.getCodigoError() + "Descripcion: "+tr614s.getErrorMessage());
							actividadEJB.terminar (actDto);
							return;
						}
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
						/*Inserción del quiebre*/
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),errorLegado.getIdCausa(),actDto.getIdActividadFlujo());
					}else{
						/*Inserción del quiebre*/
						insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
					}
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					mensajeEstadoBa.setId_error(tr614s.getError());
					mensajeEstadoBa.setDesc_error(tr614s.getErrorMessage());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					actDto.setObservacion("Error al recibir respuesta del envío de la información de Recarga Fija Móvil, se deriva a "+bandeja+". Respuesta: "+tr614s.getError()+" - "+tr614s.getErrorMessage(), true);
					actividadEJB.terminar (actDto);
		        }
		    	
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR614S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR614S o cerrando la peticion con id: " + tr614s.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR614S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR614S o cerrando la peticion. con id: " + tr614s.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR614S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR614S o cerrando la peticion. con id: " + tr614s.getId() +".", e);
			} catch (CreateException e) {
				log.error("Error procesando la respuesta TR614S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR614S o cerrando la peticion. con id: " + tr614s.getId() +".", e);
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR614S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR614S o cerrando la peticion. con id: " + tr614s.getId() +".", e);
			}  catch (ATiempoAppEx e) {
				throw e;
			} 			
		}

		public void configurarClienteZTE(ActividadEJBDTO act) throws ATiempoAppEx {
			try{
				log.debug("Ingresa a configuración Cliente ZTE (Monitoreo remoto), para la petición Atiempo No. "+ act.getNumeroPeticion());
		        PeticionKey key = new PeticionKey(act.getNumeroPeticion());
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;

				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long caracteristicaMail = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CARACT_CLIENTE_ZTE_MAIL"));
				String[] id_ps = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CONF_CLIENTE_ZTE").split(",");
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				
				Recursos_linea_basicaLocalHome recursosLineaBLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Long telefono = null;
				try{
				Recursos_linea_basicaLocal recursosLineaBasicaLocal = recursosLineaBLocalHome.findByPeticion(act.getNumeroPeticion());
				telefono = recursosLineaBasicaLocal.getTelefono_asg();
				}catch(Exception ex){
					log.debug("No se encontraron recursos de línea básica para la petición: "+act.getNumeroPeticion());
				}
			
				TR056E tr = new TR056E();
				tr.setId(idCorrelativoMensaje.toString());
				tr.setAtisRequestNumber(atis_number.longValue());
				tr.setAtiempoRequestNumber(act.getNumeroPeticion().longValue());
				tr.setDestination("ESB");
				tr.setInterfaz(ComunInterfaces.INTERFAZ_CONF_CLIENTE_ZTE);
				tr.setSource(ComunInterfaces.sistemaAtiempo);
				tr.setVersion("1.0");
				
				tr.setClientName(peticionLocal.getNom_ds());
				tr.setPhoneNumber(telefono==null?"":telefono.toString());
				tr.setMobilePhone("1");
				
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
				if(localidadLocal != null && localidadLocal.getPrimaryKey() != null){
					LocalidadKey localidadKey = (LocalidadKey) localidadLocal.getPrimaryKey();
					tr.setCity(localidadKey.cod_loc);					
				}
				
				Collection listaRecursosBa = peticionLocal.getRecursos_ba();
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						tr.setFatherAccount(element.getFather_email_nuevo());
					}
				}
				if(tr.getFatherAccount()==null){
					throw new ATiempoAppEx("No se pudo obtener el father account de RecursosBa para la petición: "+ act.getNumeroPeticion());
				}
				
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					listaProdSerPeticion = obtenerPSPeticion(listaProdSerPeticion,act.getNumeroPeticion(), act);
					listaProdSerPeticion = obtenerPSPeticion(listaProdSerPeticion,id_ps);
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					Long ps_id=null;
					
					validar_pdg : 
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						
						tr.setCommercialOperation(prodServPeticionLocal.getIdOperacionComercial().intValue());
						
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();
						Producto_servicioKey product_serv_key = (Producto_servicioKey) prodServPeticionLocal.getProducto_servicio().getPrimaryKey();
						ps_id = product_serv_key.ps_id;
						log.debug("CONF_CLIENTE_ZTE PS: "+ ps_id);
						tr.setPsCode(ps_id.intValue());
						
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();
						
						if(subpeticionCaracteristicasPS != null && subpeticionCaracteristicasPS.size() > 0){
							
							for(Iterator j = subpeticionCaracteristicasPS.iterator(); j.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) j.next();
								if(subpeticionCaractLocal != null){
									Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
									if(caracteristicaMail.equals(subpeticionCaractKey.getCod_crc_cd())){
										tr.setEmail(subpeticionCaractLocal.getVal_ral_crc_cd());
										break validar_pdg;
									}
								}
							}
						}
					}
					
					Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
					Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
			        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
					Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
			        msgLocal.setPeticion(peticionLocal);
			        msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
			        msgLocal.setFecha_envio(df.format(new java.util.Date()));
			        msgLocal.setMensaje_estado(mensajeEspera);
			        
			        ConfClienteZTEMQ mq  = new ConfClienteZTEMQ();
			        if(tr.getCommercialOperation() == ComunInterfaces.altaMigracionPS){
			        	cambioDePlan(act.getNumeroPeticion(),tr,peticionLocal, act);
			        }else{
			        	mq.enviarMensaje(tr);
			        }

				}
			} catch (FinderException e) {
	            log.error("Error al enviar Configuración Cliente ZTE.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuración Cliente ZTE.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuración Cliente ZTE.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 	
		}
		
		private void cambioDePlan(Long petiNumero,TR056E tr056e, PeticionLocal peticionLocal, ActividadEJBDTO act) throws Exception{
			Collection camarasAtiempo = obtenerCamaras(petiNumero);
	        Collection camarasPeticion = new ArrayList();
			if(camarasAtiempo!=null && !camarasAtiempo.isEmpty()){
				TR057E tr = new TR057E();
				Long idCorrelativoMensaje = new Long(tr056e.getId());
				tr.setId(idCorrelativoMensaje.toString());
				tr.setAtisRequestNumber(tr056e.getAtisRequestNumber());
				tr.setAtiempoRequestNumber(petiNumero.longValue());
				tr.setDestination("ESB");
				tr.setInterfaz(ComunInterfaces.INTERFAZ_CONF_CAMARA_ZTE);
				tr.setSource(ComunInterfaces.sistemaAtiempo);
				tr.setVersion("1.0");
				tr.setPsCode(tr056e.getPsCode());
				tr.setRecordSpace(String.valueOf(tr056e.getPsCode()));
				tr.setCommercialOperation(tr056e.getCommercialOperation());
				
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
				if(localidadLocal != null && localidadLocal.getPrimaryKey() != null){
					LocalidadKey localidadKey = (LocalidadKey) localidadLocal.getPrimaryKey();
					tr.setCity(localidadKey.cod_loc);
				}
				
				if(peticionLocal.getNom_cal_ds() != null){
					tr.setAddress(peticionLocal.getNom_cal_ds());
				}
				
				Collection listaRecursosBa = peticionLocal.getRecursos_ba();
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						tr.setFatherAccount(element.getFather_email_nuevo());
					}
				}
				if(tr.getFatherAccount()==null){
					throw new ATiempoAppEx("No se pudo obtener el father account de RecursosBa para la petición: "+ petiNumero);
				}
				
		        for (Iterator iter = camarasAtiempo.iterator(); iter.hasNext();) {
					CamaraLocal camaraAtiempo = (CamaraLocal) iter.next();
					if(camaraAtiempo.getCameraState().equals(ComunInterfaces.CAMARA_ACTIVA)||
							camaraAtiempo.getCameraState().equals(ComunInterfaces.CAMARA_ERROR)||
							camaraAtiempo.getCameraState().equals(ComunInterfaces.CAMARA_PENDIENTE_CAMBIO_PLAN)){//Solo las que están activas
						CameraRequest camaraPeticion = new CameraRequest();
						setCamaraRequest(camaraAtiempo,camaraPeticion);
						camaraPeticion.setCameraState(ComunInterfaces.CAMARA_PENDIENTE_CAMBIO_PLAN);//Pendiente por cambio de plan
						camaraAtiempo.setCameraState(ComunInterfaces.CAMARA_PENDIENTE_CAMBIO_PLAN);
						camaraAtiempo.setCameraDescription("Pendiente activación cambio de plan");
						camarasPeticion.add(camaraPeticion);
					}
				}
		        if(camarasPeticion.isEmpty()){
		        	String codAct = "";
					ActividadEJBDTO actDto;
					Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			        Mensaje_estado_baLocal mensajeEstadoBa;
			        Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			        Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
			        try {
			        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr.getId())));
			        } catch (FinderException e1) {
			        	mensajeEstadoBa = null;
			        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr));
			            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr.getId(), ATiempoAppEx.FINDER);
			        }
					codAct=mensajeEstadoBa.getCod_actividad_generadora();
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					actDto.setObservacion("Error: Al generar la TR056E. ");
					insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),new Long(1000),actDto.getIdActividadFlujo());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					mensajeEstadoBa.setId_error("");
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
					actividadEJB.terminar (actDto);
		        }else
		        	tr.setCameras(camarasPeticion);
		        /*Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
				Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion(peticionLocal);
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setMensaje_estado(mensajeEspera);*/
		        ConfCamaraZTEMQ mq  = new ConfCamaraZTEMQ();
		        mq.enviarMensaje(tr);
			}
		}

		/*
		 * (sin Javadoc)
		 * 
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaClienteZTE(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
		 */
		public void respuestaClienteZTE(TR056S tr) throws ATiempoAppEx {
			log.debug("Ingresa a configuracionClienteZTE, para la petición Atiempo No. "+ tr.getAtiempoRequestNumber());
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				Mensaje_estadoLocal mensajeManual = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
			
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		        ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB () ;
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad (mensajeEstadoBa.getCod_actividad_generadora ()) ;
		    	ActividadEJBDTO actDto = null ;
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr != null && tr.getError() != null && tr.getError().equals("0")){
		        	/*Cierre de la actividad exitoso*/
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeOk);
					Long idOpeComercial = obtenerOperacionComercialPeticion(actDto.getNumeroPeticion());
					String valorBitacora = "Proceso ejecutado.";
					PeticionKey key = (PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey();
			        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
					
			        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
					boolean esBaja = false;
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						if(prodServPeticionLocal.getIdOperacionComercial().intValue() == ComunInterfaces.bajaSTB)
							esBaja = true;
					};
					if(esBaja){
						Collection camarasAtiempo = obtenerCamaras(new Long (tr.getAtiempoRequestNumber()));
						 for (Iterator iter = camarasAtiempo.iterator(); iter.hasNext();) {
							CamaraLocal camaraAtiempo = (CamaraLocal) iter.next();
							if(camaraAtiempo.getCameraState().equals(ComunInterfaces.CAMARA_ACTIVA)||
									camaraAtiempo.getCameraState().equals(ComunInterfaces.CAMARA_ERROR)){//Solo las que están activas
								CameraRequest camaraPeticion = new CameraRequest();
								camaraAtiempo.setCameraState(ComunInterfaces.CAMARA_PENDIENTE_INACTIVACION);
								camaraAtiempo.setAccion(new Short ("2"));
							}
						}
					}
					//1,2,73 Operaciones comerciales de altas
					if(idOpeComercial.intValue() == 1 || idOpeComercial.intValue() == 2 || idOpeComercial.intValue() == 73){
						valorBitacora = "Se recibe confirmación de creación del cliente, el usuario de acceso es: "+ tr.getCustomerCode();
					}
					actDto.setObservacion(valorBitacora, true);
		        }else{	        	
		        	/*Cierre de la actividad con error*/	        	
					actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
					errorLegado = getErrorLegado(tr.getError(),"TR056S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
					if(errorLegado != null){
						if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
							mensajeEstadoBa.setId_error(tr.getError());
							mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
							mensajeEstadoBa.setMensaje_estado(mensajeOk);
							Long idOpeComercial = obtenerOperacionComercialPeticion(actDto.getNumeroPeticion());
							String valorBitacora = "Proceso ejecutado.";
							//1,2,73 Operaciones comerciales de altas
							if(idOpeComercial.intValue() == 1 || idOpeComercial.intValue() == 2 || idOpeComercial.intValue() == 73){
								valorBitacora = "Se recibe confirmación de creación del cliente, el usuario de acceso es: "+ tr.getCustomerCode();
							}
							actDto.setObservacion(valorBitacora, true);
							actividadEJB.terminar (actDto);
							return;
						}
						bandeja	= getNombreBandeja(plataforma);
					}
					log.debug("Plataforma: "+plataforma);
					log.debug("Bandeja: "+bandeja);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					actDto.setObservacion("Error en configuracion, Respuesta del PS "+ mensajeEstadoBa.getDesc_error() +
							" : "+tr.getError()+" - "+tr.getErrorMessage()+" Se deriva a "+bandeja);
					Long quiebre = errorLegado!=null?errorLegado.getIdCausa():new Long(701);//701 quiebre por defecto
					log.debug("Quiebre: "+quiebre);
					insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),quiebre,actDto.getIdActividadFlujo());
		        }
		    	actividadEJB.terminar (actDto);
		        
			} catch (ATiempoAppEx e) {
				throw e;
			}  catch (Exception e) {
				log.error("Error procesando la respuesta TR056S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR056S o cerrando la peticion. con id: " + tr.getId() +".", e);
			} 		
		}
		
		public Long obtenerOperacionComercialPeticion(Long petiNumero) throws Exception{
			PeticionLocalHome peticionLH = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionLH.findByPrimaryKey(new PeticionKey(petiNumero));
			Collection productoServicioPeticion = peticionLocal.getProducto_servicio_peticion();
			Long idOperacionComercial = Long.valueOf("0");
			for (Iterator iter = productoServicioPeticion.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal proServPeti = (Producto_servicio_peticionLocal) iter.next();
				 idOperacionComercial = proServPeti.getIdOperacionComercial();
				 break;
			}
			return idOperacionComercial;
		}
		
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarCamaraZTE(co.com.telefonica.atiempo.actividades.ActividadEJBDTOlang.String)
		 */
		public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx {
			try{
				log.debug("Ingresa a configuración Cámara ZTE (Monitoreo remoto), para la petición Atiempo No. "+ numPeticion);
		        PeticionKey key = new PeticionKey(numPeticion);
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(key);
				
		        Peticion_atisKey peticion_atisKey=(Peticion_atisKey) peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		        Long atis_number = peticion_atisKey.cod_pet_cd;

				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long caracteristicaRecordSpace = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CARACT_CLIENTE_ZTE_RECORD_SPACE"));
				
				TR057E tr = new TR057E();
				tr.setId(idCorrelativoMensaje.toString());
				tr.setAtisRequestNumber(atis_number.longValue());
				tr.setAtiempoRequestNumber(numPeticion.longValue());
				tr.setDestination("ESB");
				tr.setInterfaz(ComunInterfaces.INTERFAZ_CONF_CAMARA_ZTE);
				tr.setSource(ComunInterfaces.sistemaAtiempo);
				tr.setVersion("1.0");
				
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
				if(localidadLocal != null && localidadLocal.getPrimaryKey() != null){
					LocalidadKey localidadKey = (LocalidadKey) localidadLocal.getPrimaryKey();
					tr.setCity(localidadKey.cod_loc);
				}
				
				if(peticionLocal.getNom_cal_ds() != null){
					tr.setAddress(peticionLocal.getNom_cal_ds());
				}
				
				Collection listaRecursosBa = peticionLocal.getRecursos_ba();
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						tr.setFatherAccount(element.getFather_email_nuevo());
					}
				}
				if(tr.getFatherAccount()==null){
					throw new ATiempoAppEx("No se pudo obtener el father account de RecursosBa para la petición: "+ numPeticion);
				}
				
				Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
				if(listaProdSerPeticion != null && listaProdSerPeticion.size() > 0){
					Producto_servicio_peticionLocal prodServPeticionLocal=null;
					Subpeticion_atisLocal subpeticionAtisLocal=null;
					Collection subpeticionCaracteristicasPS=null;
					String msjenviado=null;
					
					validar_pdg : 
					for(Iterator i = listaProdSerPeticion.iterator(); i.hasNext();){
						prodServPeticionLocal = (Producto_servicio_peticionLocal) i.next();
						
						tr.setCommercialOperation(prodServPeticionLocal.getIdOperacionComercial().intValue());
						
						subpeticionAtisLocal = prodServPeticionLocal.getFk_01_subp_atis();
						
						subpeticionCaracteristicasPS = subpeticionAtisLocal.getSubpeticion_caracteristicas();
						if(subpeticionCaracteristicasPS != null && subpeticionCaracteristicasPS.size() > 0){
							for(Iterator j = subpeticionCaracteristicasPS.iterator(); j.hasNext();){
								Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) j.next();
								if(subpeticionCaractLocal != null){
									Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
									if(caracteristicaRecordSpace.equals(subpeticionCaractKey.getCod_crc_cd())){
										tr.setRecordSpace(subpeticionCaractLocal.getVal_ral_crc_cd());
										tr.setPsCode(Integer.valueOf(subpeticionCaractLocal.getVal_ral_crc_cd()).intValue());
										break validar_pdg;
									}
								}
							}
						}
					}
				}
				
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
				Mensaje_estado_baLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion(peticionLocal);
		        //msgLocal.setCod_actividad_generadora(act.getCodigoActividad());
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setMensaje_estado(mensajeEspera);
		        
		        Collection camarasAtiempo = obtenerCamaras(numPeticion);
		        Collection camarasPeticion = new ArrayList();
		        for (Iterator iter = camarasAtiempo.iterator(); iter.hasNext();) {
					CamaraLocal camaraAtiempo = (CamaraLocal) iter.next();
					CameraRequest camaraPeticion = new CameraRequest();
					setCamaraRequest(camaraAtiempo,camaraPeticion);
					camarasPeticion.add(camaraPeticion);
				}
		        tr.setCameras(camarasPeticion);
		        
		        ConfCamaraZTEMQ mq  = new ConfCamaraZTEMQ();
		        mq.enviarMensaje(tr);

			} catch (FinderException e) {
	            log.error("Error al enviar Configuración Camará ZTE.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	        } catch (ATiempoAppEx e) {
				log.error("Error al enviar Configuración Camará ZTE.", e);
	            throw e;
			} catch (Exception e) {
				log.error("Error al enviar Configuración Camará ZTE.", e);
	            throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} 	
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaCamaraZTE(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
		 */
		public void respuestaCamaraZTE(TR057S tr) throws ATiempoAppEx {
			log.debug("Ingresa a respuestaCamaraZTE, para la petición Atiempo No. "+ tr.getAtiempoRequestNumber());
			try {
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeOk = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoOk)));
				Mensaje_estadoLocal mensajeError = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoError)));
				Mensaje_estadoLocal mensajeManual = mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEsperaManual)));
			
		        Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
		        Mensaje_estado_baLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr.getId(), ATiempoAppEx.FINDER);
		        }
		        			
				Long numPeticion = Long.valueOf(tr.getAtiempoRequestNumber());
				Collection listaRecursosBa = mensajeEstadoBa.getPeticion().getRecursos_ba();
				String fatherAccount = null;
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						fatherAccount = element.getFather_email_nuevo();
					}
				}

				Collection camarasRespuesta = new ArrayList();
				CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				boolean cambioPlan = false;
				for (Iterator iterator = tr.getCameras().iterator(); iterator.hasNext();) {
					CameraResponse camaraRespuesta = (CameraResponse) iterator.next();
					CamaraKey camaraKey = new CamaraKey(numPeticion,camaraRespuesta.getCameraSerial());
					CamaraLocal camara = camaraLocalHome.findByPrimaryKey(camaraKey);
					if(!cambioPlan && camara.getCameraState().equals(ComunInterfaces.CAMARA_PENDIENTE_CAMBIO_PLAN)) cambioPlan = true;
					camara.setCameraState(camaraRespuesta.getCameraState());
					camara.setCameraDescription(camaraRespuesta.getCameraDescription());
					if(tr.getError()!=null && tr.getError().equals("0")){
						camara.setCameraDescription(camaraRespuesta.getCameraDescription()+ " User: "+ fatherAccount);
					}
				}
				
				if(!cambioPlan){
			    	Tmp_agenda_scLocalHome tmpAgendaLocalHome = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			    	Tmp_agenda_scLocal agenda = tmpAgendaLocalHome.findbyPeti_numero(numPeticion);
					TR709S tr709s = (TR709S)XMLUtilities.unmarshall(agenda.getXml());
					Collection camarasAtiempo = camaraLocalHome.findByPeticion(numPeticion);
					for (Iterator iter = tr709s.getCameras().iterator(); iter.hasNext();) {
						CameraRequest camaraPeticion = (CameraRequest) iter.next();
						boolean encontrada = false;
						forCamarasRespuesta:
						for (Iterator iterator = tr.getCameras().iterator(); iterator.hasNext();) {
							CameraResponse camaraRespuesta = (CameraResponse) iterator.next();
							CamaraKey camaraKey = new CamaraKey(numPeticion,camaraRespuesta.getCameraSerial());
							CamaraLocal camara = camaraLocalHome.findByPrimaryKey(camaraKey);
							camara.setCameraState(camaraRespuesta.getCameraState());
							camara.setCameraDescription(camaraRespuesta.getCameraDescription());
							if(tr.getError()!=null && tr.getError().equals("0")){
								camara.setCameraDescription(camaraRespuesta.getCameraDescription()+ " User: "+ fatherAccount);
								camaraRespuesta.setCameraDescription(camara.getCameraDescription());
							}
							if(camaraRespuesta.getCameraSerial().equals(camaraPeticion.getCameraSerial())){
								camarasRespuesta.add(camaraRespuesta);
								encontrada = true;
								break forCamarasRespuesta;
							}
						}
						if(!encontrada){//Sí no es encontrada en la tr057s actual se busca en atiempo la información.
							forCamaraAtiempo:
							for (Iterator iterator = camarasAtiempo.iterator(); iterator.hasNext();) {
								CamaraLocal camaraAtiempo = (CamaraLocal) iterator.next();
								CamaraKey key = (CamaraKey) camaraAtiempo.getPrimaryKey();
								if(camaraPeticion.getCameraSerial().equals(key.cameraSerial)){
									CameraResponse camaraRespuesta = new CameraResponse();
									setCamaraResponse(camaraAtiempo,camaraRespuesta);
									camarasRespuesta.add(camaraRespuesta);
									break forCamaraAtiempo;
								}
							}
						}
					}

					TR709E tr709e = crearTr709e(tr.getError(),tr.getErrorMessage(),camarasRespuesta,tr709s);
					agenda.remove();
			    	enviarActivarCamaraAgendaSC(tr709e);
				}
				
				if(cambioPlan){
					String codAct = "";
					ActividadEJBDTO actDto;
					codAct=mensajeEstadoBa.getCod_actividad_generadora();
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					if(tr.getError() != null && tr.getError().equals("0")){
						mensajeEstadoBa.setId_error(tr.getError());
						mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
						mensajeEstadoBa.setMensaje_estado(mensajeOk);
						actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
						actDto.setObservacion("Se envían cámaras a configuración de monitoreo remoto por PS cambio de plan.", true);
						//actDto.setRealizarTerminoInmediato(true);
						actividadEJB.terminar (actDto);
					}else{
						/*Cierre de la actividad con error*/
			        	ErrorLegadoLocal errorLegado =null;
						actDto = actividadEJB.getActividadEJBDTO(((PeticionKey)mensajeEstadoBa.getPeticion().getPrimaryKey()).peti_numero, mensajeEstadoBa.getCod_actividad_generadora());
			        	errorLegado = getErrorLegado(tr.getError(),"TR057S");
						String plataforma = VpistbbaConfig.getVariable("IDPGI");
						String bandeja = "PGI";
						if(errorLegado != null){
							if(errorLegado.getPlataforma() == null  || errorLegado.getPlataforma().trim().equals("")){
								actDto.setObservacion("Error: "
						 				+ errorLegado.getCodigoError() + "Descripcion: "+tr.getErrorMessage());
								actividadEJB.terminar (actDto);
								return;
							}
							plataforma = errorLegado.getPlataforma(); 
							bandeja	= getNombreBandeja(plataforma); 
							/*Inserción del quiebre*/
							insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),errorLegado.getIdCausa(),actDto.getIdActividadFlujo());
						}else{
							/*Inserción del quiebre*/
							insertarCausalesCnaPeticion(mensajeEstadoBa.getPeticion(),mensajeEstadoBa.getCod_actividad_generadora(),/*Quiebre por defecto*/new Long(701),actDto.getIdActividadFlujo());
						}
						
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						mensajeEstadoBa.setId_error(tr.getError());
						mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
						mensajeEstadoBa.setMensaje_estado(mensajeError);
						actDto.setObservacion("Se recibe respuesta de error: "+tr.getErrorMessage());
						actividadEJB.terminar (actDto);
					}
				
				}else{
					if(tr.getError() != null && tr.getError().equals("0")){
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeOk);
					}else{	        	
					mensajeEstadoBa.setId_error(tr.getError());
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setMensaje_estado(mensajeError);
		        }
				}
		    	
			} catch (ATiempoAppEx e) {
				throw e;
			}  catch (Exception e) {
				log.error("Error procesando la respuesta TR057S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR057S o cerrando la peticion. con id: " + tr.getId() +".", e);
			} 				
		}

		/**
		 * Realiza la recepción de la tr709s en donde se realizan las peticiones de activación de cámaras
		 * para monitoreo remoto
		 */
		public void recepcionActivarCamaraAgendaSC(TR709S tr) throws ATiempoAppEx {
			String peticionAgenda = tr.getIdSchedule();
			Long numPeticion = new Long(peticionAgenda.substring(2,peticionAgenda.indexOf("-")));
			Collection camarasPeticion = new ArrayList();
			try{
				deltaCamaras(numPeticion,tr.getCameras());
				configurarCamaraZTE(numPeticion);
				Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
				Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (tr.getId()));
				tmpAgendaSCLocal.setId_schedule(tr.getIdSchedule());
				tmpAgendaSCLocal.setPeti_numero(numPeticion);
				tmpAgendaSCLocal.setXml(XMLUtilities.marshall(tr));
			}catch (Exception e)
			{
				log.error(e);
				enviarActivarCamaraAgendaSC(crearTr709e("1","Atiempo. Error en la recepción de activación de cámaras, ver log para detalles.",null,tr));
			}
		}
		
		private TR709E crearTr709e(String error, String errorMessage, Collection camaras,TR709S tr709s){
			Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			TR709E tr = new TR709E();
			tr.setDestination("ESB");
			tr.setError(error);
			tr.setErrorMessage(errorMessage);
			tr.setId(tr709s.getId());
			tr.setInterfaz(ComunInterfaces.INTERFAZ_ACT_CAMARA_ZTE);
			tr.setSource(ComunInterfaces.sistemaAtiempo);
			tr.setVersion("1.0");
			tr.setCameras(camaras);
			tr.setIdSchedule(tr709s.getIdSchedule());
			tr.setIdSourceSystem(tr709s.getIdSourceSystem());
			tr.setResponse(error);
			return tr;
		}
		
		/**
		 * Obtiene las camaras en atiempo de acuerdo al número de petición enviado como parámetro
		 * @param numeroPeticion Número de la petición de atiempo
		 * @return Un collectión con las cámaras encontradas o nul en caso contrario
		 */
		private Collection obtenerCamaras(Long numeroPeticion){
			CamaraLocalHome camaraLocalHome;
			Collection camaras = null;
			try {
				camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
				camaras = camaraLocalHome.findByPeticion(numeroPeticion);
			} catch (NamingException e) {
				log.error(e);
			} catch (FinderException e) {
				log.debug("No se encontraron cámaras para la petición: "+ numeroPeticion);
			}
			return camaras;
		}
		
		/**
		 * Realiza un delta de cámaras entre aquellas que llegan en la tr709s y las que existen en atiempo.
		 * @param numPeticion Número de la petición de atiempo
		 * @param camarasPeticion Camaras que llegan en la petición
		 * @throws Exception
		 * @throws Exception Cualquier error no contemplado en la operación
		 */
		private void deltaCamaras(Long numPeticion, Collection camarasPeticion) throws Exception{
			Collection camarasAtiempo = obtenerCamaras(numPeticion);
			Map encontradas = new TreeMap();
			for (Iterator iter = camarasPeticion.iterator(); iter.hasNext();) {
				CameraRequest camaraPeticion = (CameraRequest) iter.next();
				if(camarasAtiempo!= null && camarasAtiempo.size() > 0){//Sí existen cámaras registradas en atiempo se hace el delta
					breakCamaras:
					for (Iterator iterator = camarasAtiempo.iterator(); iterator.hasNext();) {
						CamaraLocal camaraAtiempo = (CamaraLocal) iterator.next();
						CamaraKey key = (CamaraKey) camaraAtiempo.getPrimaryKey();
						if(!encontradas.containsKey(key.cameraSerial)){//Sí no está dentro de las encontradas se intenta inactivar
							camaraAtiempo.setCameraDescription("Pendiente por inactivación");
							camaraAtiempo.setCameraState(ComunInterfaces.CAMARA_PENDIENTE_INACTIVACION);
						}
						if(key.cameraSerial.equals(camaraPeticion.getCameraSerial())&& camaraAtiempo.getCameraState()!= ComunInterfaces.CAMARA_ACTIVA ){//Sí la cámara es encontrada se intenta activar
							if(!camaraPeticion.getCameraState().equals(ComunInterfaces.CAMARA_ACTIVA)){//Solo se manda a activar sí no está ya activa
								setCamaraAtiempo(camaraAtiempo,camaraPeticion,"Pendiente por activación",ComunInterfaces.CAMARA_PENDIENTE_ACTIVACION);
								encontradas.put(key.cameraSerial, camaraAtiempo);
							}
							break breakCamaras;
						}
					}
				}
				if(!encontradas.containsKey(camaraPeticion.getCameraSerial())){//Sí la cámara no fue encontrada en atiempo entonces se crea y se almacena
					CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
					CamaraLocal camaraAtiempo = camaraLocalHome.create(numPeticion,camaraPeticion.getCameraSerial());
					setCamaraAtiempo(camaraAtiempo,camaraPeticion,"Pendiente por activación",ComunInterfaces.CAMARA_PENDIENTE_ACTIVACION);
				}
			}
		}

		private void setCamaraRequest(CamaraLocal camaraAtiempo, CameraRequest camaraPeticion){
			camaraPeticion.setCameraBrand(camaraAtiempo.getCameraBrand());
			camaraPeticion.setCameraCode(camaraAtiempo.getCameraCode());
			camaraPeticion.setCameraModel(camaraAtiempo.getCameraModel());
			camaraPeticion.setCameraState(camaraAtiempo.getCameraState());
			camaraPeticion.setCameraType(camaraAtiempo.getCameraType());
			CamaraKey key = (CamaraKey) camaraAtiempo.getPrimaryKey();
			camaraPeticion.setCameraSerial(key.cameraSerial);
		}
		
		private void setCamaraAtiempo(CamaraLocal camaraAtiempo, CameraRequest camaraPeticion,String cameraDescription, Integer cameraState){
			camaraAtiempo.setCameraBrand(camaraPeticion.getCameraBrand());
			camaraAtiempo.setCameraCode(camaraPeticion.getCameraCode());
			camaraAtiempo.setCameraDescription(cameraDescription);
			camaraAtiempo.setCameraModel(camaraPeticion.getCameraModel());
			camaraAtiempo.setCameraState(cameraState);
			camaraAtiempo.setCameraType(camaraPeticion.getCameraType());
		}
		
		private void setCamaraResponse(CamaraLocal camaraAtiempo, CameraResponse camaraResponse){
			CamaraKey key = (CamaraKey) camaraAtiempo.getPrimaryKey();
			camaraResponse.setCameraCode(camaraAtiempo.getCameraCode());
			camaraResponse.setCameraDescription(camaraAtiempo.getCameraDescription());
			camaraResponse.setCameraSerial(key.cameraSerial);
			camaraResponse.setCameraState(camaraAtiempo.getCameraState());
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#activarCamaraAgendaSC(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
		 */
		public void enviarActivarCamaraAgendaSC(TR709E tr) throws ATiempoAppEx {
			ActivarCamaraAgendaSCMQ mq = new ActivarCamaraAgendaSCMQ();
			mq.enviarMensaje(tr);
		}
	
		public void enviarRefrecarDatos(Long idPeticion) throws ATiempoAppEx{
			// TODO Apéndice de método generado automáticamente
			try {
				// TODO Apéndice de método generado automáticamente
				log.debug("Entro a enviarRefrecarDatos ");
				String datos;
				Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
				Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(idPeticion);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(idPeticion);
				String central = "";
				if(recursos_linea_basicaLocal.getCentral_connection() != null)
					central = recursos_linea_basicaLocal.getCentral_connection().toString();
				datos = "1. Central: "+ central+";";
				String numeroTelefono = "";
				if(recursos_linea_basicaLocal.getTelefono_asg() != null)
					numeroTelefono = recursos_linea_basicaLocal.getTelefono_asg().toString();
				datos = datos + "2. Numero de Telefono: "+ numeroTelefono+";";
				String len = recursos_linea_basicaLocal.getLen();
				datos = datos + "3. Len: "+ len +";";
				//String lecturaContador = recursos_linea_basicaLocal.getDir_ip_dslam_actual();
				String posicionHorizontal = recursos_linea_basicaLocal.getPosicion_horizontal_asg();
				datos = datos + "4. Posicion Horizontal: "+ posicionHorizontal +";";
				String distribuidor = "";
				if(recursos_linea_basicaLocal.getDist_prim_asg() != null)
					distribuidor = recursos_linea_basicaLocal.getDist_prim_asg().toString();
				datos = datos + "5. Distribuidor: "+ distribuidor +";";
				String descripcion = "";
				if(recursos_linea_basicaLocal.getDist_sec_asg() != null)
					descripcion = recursos_linea_basicaLocal.getDist_sec_asg().toString();
				datos = datos + "6. Descripcion: "+ descripcion+";";
				String direccionDistribuidor = recursos_linea_basicaLocal.getDir_distribuidor();
				datos = datos + "7. Direccion del Distribuidor: "+ direccionDistribuidor+";";
				String liston = recursos_linea_basicaLocal.getListon_asg();
				datos = datos + "8. Liston: "+ liston+";";
				String parListon = "";
				if(recursos_linea_basicaLocal.getPar_liston_asg() != null)
					parListon = recursos_linea_basicaLocal.getPar_liston_asg().toString();
				datos = datos + "9. Par liston: "+ parListon+";";
				String cable = "";
				if(recursos_linea_basicaLocal.getCable() != null)
					cable = recursos_linea_basicaLocal.getCable().toString();
				datos = datos + "10. Cable: "+ cable+";";
				String parCable = "";
				if(recursos_linea_basicaLocal.getPar_cable() != null)
					parCable = recursos_linea_basicaLocal.getPar_cable().toString();
				datos = datos + "11. Par Cable: "+ parCable+";";
				String armario = recursos_linea_basicaLocal.getArmario_asg();
				datos = datos + "12. Armario: "+ armario+";";
				String direccionArmario = recursos_linea_basicaLocal.getDir_armario();
				datos = datos + "13. Direccion del Armario: "+ direccionArmario+";";
				String codigoCaja = recursos_linea_basicaLocal.getCaja_asg();
				datos = datos + "14. Codigo de la Caja: "+ codigoCaja+";";
				String parCaja = "";
				if(recursos_linea_basicaLocal.getPar_caja_asg() != null)
					parCaja = recursos_linea_basicaLocal.getPar_caja_asg().toString();
				datos = datos + "15. Par de la Caja: "+ parCaja+";";
				String dirCaja = recursos_linea_basicaLocal.getDir_caja();
				datos = datos + "15. Direccion de la Caja: "+ dirCaja;
				
				TR719S tr719s = (TR719S)XMLUtilities.unmarshall(tmp_agenda_scLocal.getXml());
				TR719E tr719e =  new TR719E();
				tr719e.setId(tr719s.getId());
				tr719e.setDestination(sistemaAgendaSC);
				tr719e.setSource(sistemaAtiempo);
				tr719e.setInterfaz("ACT_RES_REFRESHSTB");
				tr719e.setVersion("1.0");
				tr719e.setId_sistema_origen("ATIEMPO");
				tr719e.setId_actuacion(tr719s.getId_actuacion());
				tr719e.setNotif_refresh(datos);
				if(recursos_linea_basicaLocal.getResult_accion()!= null){
					tr719e.setDescripcion(recursos_linea_basicaLocal.getResult_accion().toString());
				}
				
				
				if(recursos_linea_basicaLocal.getInd_dedicado()!= null){
					tr719e.setCodigo_error(recursos_linea_basicaLocal.getInd_dedicado().toString());
				}
				tmp_agenda_scLocal.remove();
				EnviarRefrecarDatosMQ enviarRefrecarDatosMQ = new co.com.telefonica.atiempo.vpistbba.servicios.EnviarRefrecarDatosMQ();
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
			} catch (EJBException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error instanciando el bean en el Recursos_baLocalHome:" + e);
				e.printStackTrace();
			} catch (RemoveException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error removiendo el bean en el Recursos_baLocalHome:" + e);
				e.printStackTrace();
			}
		}

		//Requerimiento configurar Napster David Cardenas 28/02/2014
		public void configurarNapster(ActividadEJBDTO act) throws ATiempoAppEx {
			try 
			{
				log.debug("Entro a Configurar/Desconfigurar Napster");
				//instaciamos la cola a la que vamos a enviar el mensaje
				ConfigurarNapsterMQ mq  = new ConfigurarNapsterMQ();
				//instanciamos la clase de la TR604E con al cual seteamos los campos
		        TR604E tr604e = new TR604E();
		        //creamos el correlativo
		    	Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		       	
		    	//intanciamos el localhome el cual interpreta la tabla peticion
		        PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        //traemos la primary key de la tabla peticion por medio del numero de la peticion
		        PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
		        //guardamos la consulta en la clase local la cual contiene todos los campos de la tabla peticion
		        PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
		        
				//intanciamos el localhome el cual interpreta la tabla producto serivicio peticion
				Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				//consultamos y guardamos los ps en de la peticion en una colletion
				Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
		        
				//intanciamos el localhome el cual interpreta la tabla  propertiesBD
				Properties_BDLocal propertiesDBLocal = null;
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				//consultamos los SVAS de Napster en la tabla propertiesBD
				propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("SVASNapster");
				
				
				//creamos variables
					//variable para almacenar la operacion comercial del sva
					String operacionComercial="";
					//variable para almacenar el sva
					String pspNapster="";
					//codigo de la caracterisca 34 = a correo de usuario
					Long codigoCaracteristica;
					//nombre de la caracteristica en este se gurada el nombre
					String nombreCaracteristica="";
					//variable para guardar el correo del usuario
					String correo="";
					//variable para guardar el correo del usuario
					String errorDescripcion="";
					//variable para saber si fue encontrado el correo del usuario
					boolean hayCorreo =false;
					//variable para saber si ya fue consultado el correo
					boolean consultaCorreo=false;
					//variabla para saber si hay SVA en la peticion
					boolean haySVA=false;
					//variabla para saber se deriba a PGI
					boolean hayPGI=false;
				
				//se valida que en la colletion hayan ps	
				if(ps!=null && ps.size()>0)
				{
					log.debug("Hay Producto Servicios "+ ps.size());
					//se recorre el colletion de ps de la tabla producto servico peticion
					for (Iterator iter = ps.iterator(); iter.hasNext();) 
					{
						//cada ps que se obtiene se almacena en la clase Producto_servicio_peticionLocal para poder obtener los datos de la tabla producto servicio
						Producto_servicio_peticionLocal elementproduct = (Producto_servicio_peticionLocal) iter.next();
						//se obtiene la operacion comercial del sva
						Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) elementproduct.getOperacion_comercial().getPrimaryKey();
						
						//se valida si el ps es una sva familia 316
						if(elementproduct.getFamiliaKey().faps_id.intValue() == 316)
						{
							log.debug("Hay Servicios Suplementarios "+ elementproduct.getPsId());
//							//se valida si no hay correo y si no se a consultado aun el correo
							if(!hayCorreo && consultaCorreo==false)
							{
								//obtenemos la subpeticion atis
								elementproduct.getFk_01_subp_atis();
								//se obtiene por medio de la subpeticion atis la tabla subpeticion_caracteristicas
								Subpeticion_atisLocal subPet = elementproduct.getFk_01_subp_atis();							
								//se las caracteristicas obtenidas se almacenas en una colletion
								Collection subPetCaracteristicas =subPet.getSubpeticion_caracteristicas();
								// se seea en true la consulta de correo para no volver a ejecutar estas consultas
								consultaCorreo=true;
//								variable para contar cuantas veces se ha consultado el correo
								int contador =1;
								//se valida si hay caracteristicas
								if(subPetCaracteristicas!=null && subPetCaracteristicas.size()>0)
								{
									log.debug("Hay caracteristicas en la peticion,se esta consultando el correo del usuario");
									// se recorren las caracterisiticas obtenidas
									for (Iterator iterCaract = subPetCaracteristicas.iterator(); iterCaract.hasNext();contador++) 
									{				
										//guardamos en la clase Subpeticion_caracteristicasLocal cada caracteristica obtenida de la cual obtendremos los datos de la tabla Subpeticion_caracteristicas
										Subpeticion_caracteristicasLocal elementCaract = (Subpeticion_caracteristicasLocal) iterCaract.next();
										Subpeticion_caracteristicasKey subpeticionCaracKey = (Subpeticion_caracteristicasKey)elementCaract.getPrimaryKey();
										
										//se alamacenan los datos que nesitamos en nuestras variables
										codigoCaracteristica=subpeticionCaracKey.getCod_crc_cd();
										nombreCaracteristica=elementCaract.getNom_crc_no();
										correo=elementCaract.getVal_ral_crc_cd();
										
										log.debug("Se encontro la siguiente caracteristica en la peticion: "+ elementCaract.getNom_crc_no());
										//se valida que el codigoCaracteristica = 34 y nombreCaracteristica = DIRECC ELECTRONICA y que ademas haya un corrreo electronico
										if(codigoCaracteristica.intValue() == ComunInterfaces.caracteristicaMailAsistencia && correo!=null&&!correo.equals(""))
										{
											//seteamos el correo
											correo=elementCaract.getVal_ral_crc_cd();
											tr604e.setEmailAddress(correo);
											//seteamos true en hayCorreo
											hayCorreo=true;
											log.debug("El correo del usuario fue encontrado: "+correo);
											break;
										}else{
											//se valida si ya se termino de recorrer las caracteristcia
											if(contador == subPetCaracteristicas.size()&&!hayCorreo)
											{
												log.debug("El correo del usuario no fue encontrado para configurar Napster, se deriva a la bandeja PGI");
												errorDescripcion="El correo del usuario no fue encontrado para configurar Napster, se deriva a la bandeja PGI";
												hayPGI=true;								
											}
										}
										
									}
								}else{
									//else que indica que no hay caracteristicas dentro de las cuales debe estar el correo, se deriba PGI
									log.debug("No hay caracteristicas en la peticion y el correo del usuario no puede ser consultado para configurar Napster");
									errorDescripcion="No hay caracteristicas en la peticion y el correo del usuario no puede ser consultado para configurar Napster";
									hayPGI=true;
								}
							}else{
								//else que indica que ya se consulto el correo ahora validamos que si haya correo, si no se deriva a PGI
								if(!hayCorreo)
								{
								log.debug("El correo del usuario no fue encontrado para configurar Napster, se deriva a la bandeja PGI");
								errorDescripcion="El correo del usuario no fue encontrado para configurar Napster, se deriva a la bandeja PGI";
								hayPGI=true;
								}
							}
							//se almacena el sva de producto_servicio_peticion
							pspNapster=elementproduct.getPsId().toString();
							//se almacena la operacion comercial
							operacionComercial=operacion_comercialKey.opco_id.toString();
							//se valida si hay datos en la consulta de la tabla properties DB
							if(propertiesDBLocal == null || propertiesDBLocal.equals(""))
							{//else que indica que no hay PS y SVAS Napster en la tabla PropertiesBD
								log.debug("No hay SVAS de Napster en la tabla PropertiesBD");
								throw new FinderException("No hay SVAS de Napster en la tabla PropertiesBD");
								
							}else{
//								guardamos el valor de la consulta 
								String arregloSVA= propertiesDBLocal.getValor();
								log.debug("Hay SVAS Napster en Properties: "+arregloSVA);
								//este valor dividimos por las comas y luego lo almacenamos en un arreglo
								String[] svaNapsterArray = arregloSVA.split(",");
								//recorremos el arreglo
								for (int i = 0; i < svaNapsterArray.length; i++)
									{
									//guardamos en una variable el primer sva de napster
									String svaNapster=svaNapsterArray[i].toString();
									log.debug("¿Los SVAS son iguales? PSP: "+pspNapster+" y el SVANapster: "+ svaNapster);
										// lo comparamos con el que tenemos en el iterator de producto_servicio_peticion
										if(pspNapster.equals(svaNapster))
										{
											log.debug("El SVA "+pspNapster+" con operacion comercial "+operacionComercial+" es de Napster ");
											//si son iguales lo seteamos en la TR604E en el campo PS-ID junto con la operacion comercial
											tr604e.setPsId(pspNapster);
											tr604e.setOperacioncomercial(operacionComercial);
											//variable que indica que si hay SVA
											haySVA=true;
										}
									}	
								}
						}
					}
				}else{
					//else que indica que no hay PS y SVAS en la peticion
					log.debug("No se encontraron PS o SVAS en la peticion");
					throw new FinderException("No se encontraron PS o SVAS en la tabla producto_servicio_peticion");
				}
			
				//setemoas el correlativo en la TR604E
				tr604e.setId(idCorrelativoMensaje.toString());
				//seteamos el numero de la peticion en la TR604E
				tr604e.setAtiempoRequestNumber(act.getNumeroPeticion().toString());
				
				//seteamos setPartnerUserId,setPassword,setDocument en la TR604E
				tr604e.setPartnerUserId(peticionLocal.getNum_doc_cli_cd());
				tr604e.setPassword(peticionLocal.getNum_doc_cli_cd());
				tr604e.setDocument(peticionLocal.getNum_doc_cli_cd());
				
				//setemoas el primer nombre
				tr604e.setFirstName(peticionLocal.getNom_ds());
				//validamos si el primer apellido no contiene datos
				if(peticionLocal.getPri_ape_ds()==null || peticionLocal.getPri_ape_ds().equals(""))
				{
					//setemoas el segundo apellido si no hay primer apellido
					tr604e.setLastName(peticionLocal.getSeg_ape_ds());
				}else{
					// sino setemoas el primer apellido
					tr604e.setLastName(peticionLocal.getPri_ape_ds());
				}
				//consultamos el origin code ne la tabla propertiesBD
				propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR604E_origin_code");
				//setemoas el valor de la consulta
				tr604e.setOriginCode(propertiesDBLocal.getValor());
				
//				validamos si el numero esta nulo o si contiene datos
				if(peticionLocal.getNum_ide_nu_stb()!=null && !peticionLocal.getNum_ide_nu_stb().equals("")){
					//else que nos informa que hay numero en STB
					log.debug("Hay numero Telefonico de STB: "+peticionLocal.getNum_ide_nu_stb());
					tr604e.setPhoneNumber(peticionLocal.getNum_ide_nu_stb());
				}else if(peticionLocal.getNum_ide_nu_tv()!=null && !peticionLocal.getNum_ide_nu_tv().equals("") )
				{
					log.debug("Hay telefono en TV se debe Dar formato: "+peticionLocal.getNum_ide_nu_tv());
					//si contiene datos se debe eliminar TV y el numero final
					String phoneNumberTV=peticionLocal.getNum_ide_nu_tv();
					String phoneNumber=phoneNumberTV.substring(3,phoneNumberTV.length()-2);
					tr604e.setPhoneNumber(phoneNumber);		
				}else{
					//else que nos informa que no hay numero en TV y nu en STB entoces es un error
					log.debug("El campo Num_ide_nu_tv o Num_ide_nu_stb de la peticion no contienen datos");
					throw new NullPointerException("El campo Num_ide_nu o Num_ide_nu_stb de la peticion no contienen datos");
				}
//				intanciamos el localhome el cual interpreta la tabla mensaje_estado_linea
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				//creamos un registro para ingresar la informacion de la activadad
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
				//validamos si no hay SVA y si hay error en PGI para derivar a la bandeja PGI
				log.debug("Validamos si hay SVA: "+haySVA+" y si hay error en PGI: "+hayPGI);
				if(haySVA==false || hayPGI==true)
				{
					
					//si no hay SVAS se setea un error
					if(!haySVA)
					{
						log.debug("No hay SVA de Napster en la peticion o en propertiesBD");
						errorDescripcion="No hay SVA de Napster en la peticion o en propertiesBD";
					}
					// se crean variables parametrizacion en PGI
					Long idCausal=new Long (701);
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
//					seteamos los datos como activdad familia  demas campos necesarios en la tabla mensaje estado linea
					mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
					mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
					mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
					mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
					mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
					//insertamos la descripcion del error 
					mensajeEstadoLineaLocal.setDesc_error(errorDescripcion);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, act.getCodigoActividad(),idCausal, act.getIdActividadFlujo());
					act.setObservacion(errorDescripcion+" Se envia a " + bandeja );
					act.setRealizarTerminoInmediato(true);
					log.debug("Se deriva a Bandeja PGI");
					//actividadEJB.terminar(act);
					return;	
				}else{ 
					
					//seteamos los datos como activdad familia  demas campos necesarios en la tabla mensaje estado linea
					mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
					mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
					mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
					mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
					mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
					//enviamos la TR por la cola y continuara a procesar la respuesta de broker
					log.debug("Se envia TR604E hacia broker");
				    mq.enviarMensaje(tr604e);
				}	
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente 
				log.error("Error al consultar la petición: " + e);
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error en Naming Configurar Napster: "+e);
				e.printStackTrace();
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error al ingresar datos en la tabla Mensaje estado liena: "+e);
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error al Transfomar Numero: "+e);
				e.printStackTrace();			
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				log.error("Error en Configurar Napster: "+e);
				e.printStackTrace();
			}
	    }

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaConfigurarNapster(co.com.telefonica.atiempo.interfaces.atiempo.TR060S)
		 */
		public void respuestaConfigurarNapster(TR604S tr604s) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			log.debug("Respuesta ConfigurarNapster");	
			try{
				
				String codAct = "";
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr604s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;

				try
				{
					//validamos si se setearon los datos de salida de la TR para saber el estado de la ptecion y la activadad
				 	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
					} 
				catch (FinderException fex)
					{
						mensajeEstadoLineaLocal = null ;
				
					// Validacion de existencia de la respuesta en la Base de Datos 
					// en la tabla Mensaje_Estado_Linea
					if (mensajeEstadoLineaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr604s));
									throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr604s.getId(),ATiempoAppEx.EXCEPTION);
								}
					}
				
					//seteamos la activadad generadora
					codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
					//setemoass las tablas y campso para continuar los flujos y activadades
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					
					//intanciamos la tanbla error legado en la cual se validara los errores que nos envia broker 
					//consultamos si nos llego un error en la tabla neviando el codigo y la TR
					ErrorLegadoLocal errorLegado =null;
					errorLegado = getErrorLegado(tr604s.getErrorCode(),"TR604S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
				
							//si la respuesta de codigo erre es = 0 continua con sus activdaddes normales
					if (!tr604s.isError()) {
					log.debug("Respuesta de Napster OK");
					actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_asig_rec_stb, "N");
					actDto.setObservacion(".");
					actividadEJB.terminar(actDto);
					//se setea el error en mensaje estado linea pero no se cierra la activad
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Se termino actividad Napster");
					return;
					//si no trajo algo diferente a null es que encontro un error Propio de IMS se deriba a PGI
					}else if(errorLegado != null){
					
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
						mensajeEstadoLineaLocal.setId_error(tr604s.getErrorCode());
						mensajeEstadoLineaLocal.setDesc_error(tr604s.getErrorCodeMessage());
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						actDto.setObservacion("Error en el proceso de Napster. " + tr604s.getErrorCodeMessage() + " Se envia a " + bandeja );
						actividadEJB.terminar(actDto);
						return;
				}else{
					plataforma = "18";
					bandeja	= getNombreBandeja(plataforma); 
					mensajeEstadoLineaLocal.setId_error(tr604s.getErrorCode());
					mensajeEstadoLineaLocal.setDesc_error(tr604s.getErrorCodeMessage());
					
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(701), actDto.getIdActividadFlujo());
					actDto.setObservacion("Error no encontrado en Error_Legado verificar el proceso de Napster. " + tr604s.getErrorCodeMessage() + " Se envia a " + bandeja );
					
					actividadEJB.terminar(actDto);
					return;
				}

					
				} catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Configurar Napster"+e);
					e.printStackTrace();
				} catch (TnProcesoExcepcion e) {
					log.debug("Error en Respuesta Configurar Napster"+e);
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				}
//	Fin requeriento Napster	
			
		//REQ FTTC
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configuracionAutIMS(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
		 */
		public void configuracionAutIMS(ActividadEJBDTO act, String operacionComercial) throws ATiempoAppEx {
			try {
				log.debug("inicia Mensaje Configuracion IMS "+act.getNumeroPeticion());
				//instaciamos la cola a la que vamos a enviar el mensaje
				ArrayList special = new ArrayList();
				ConfiguracionAutomaticaIMSMQ mq  = new ConfiguracionAutomaticaIMSMQ();
				TR602E tr602e = new TR602E();
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			 
				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
				Collection ps = peticionesInterface.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
				PsVsOcVO psVsOcVO=null;
				tr602e.setProcessType(false);
				boolean hayCambioProd = false;
//				intanciamos la tabla peticion
			    PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			    PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			    PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					psVsOcVO = (PsVsOcVO) iter.next();
					Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psVsOcVO.getPsId()));
					//se agrega validacion para identificar un cambio de produto
					Operacion_comercialLocalHome operacion_comercialLocalHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
					Operacion_comercialLocal Operacion_comercialLocal = operacion_comercialLocalHome.findByPrimaryKey(new Operacion_comercialKey(psVsOcVO.getOpComId()));
					if((Operacion_comercialLocal.getOpco_tipo().equals(opCoTipoAltaCambioProd)
							||Operacion_comercialLocal.getOpco_tipo().equals(opCoTipoBajaCambioProd))
							&& Operacion_comercialLocal.getOpco_tras() != null
							&& Operacion_comercialLocal.getOpco_tras().equals(opCoTras_Traslado)){
						hayCambioProd=true;
					}
					
					log.debug("Se compara tipo oc: "+ operacionComercial+ " con: "+ psVsOcVO.getOpComTipo());
					if (peticionLocal.getTica_id().equals("P")||peticionLocal.getTica_id().equals("T")){
						 tr602e.setProcessType(false);
					}else{
						tr602e.setProcessType(true);
					}
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaPcLinea && operacionComercial.equals(psVsOcVO.getOpComTipo())){
						

					if (producto_servicioLocal.getPs_nombre().equals("LINEA PRECABLEADA")
							&& (operacionComercial.equals(ComunInterfaces.opCoTipoAlta) ||operacionComercial.equals(ComunInterfaces.opCoTipoAltaCambioProd)))
					{
						   tr602e.setPrecableada(true);
					}else
					{
						   tr602e.setPrecableada(false);
					}
					if (producto_servicioLocal.getPs_nombre().equals("LINEA CUENTA CONTROLADA"))
					{
						  tr602e.setControlAccount(true);
						   
					}else
					{
						  tr602e.setControlAccount(false);
					}
					operacionComercial=psVsOcVO.getOpComTipo();
					//break;
					}else{
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaLinea
							&& producto_servicioLocal.getInf_fttc()!= null &&  producto_servicioLocal.getInf_fttc().intValue() == 1){
//						tr602e.setProcessType(false);
						operacionComercial=psVsOcVO.getOpComTipo();
						log.debug("operacion comercial "+operacionComercial+" con producto "+psVsOcVO.getPsId());
						special.add(psVsOcVO.getPsId());
					}
				}
			}
			tr602e.setId(idCorrelativoMensaje.toString());
			String phoneNumber =  recursos_linea_basicaLocal.getTelefono_asg().toString();
			if(!psVsOcVO.getOpComTipo().equals("ACP")|| act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			     {
			       tr602e.setFatherAccount(recursos_linea_basicaLocal.getFttcusu_asg());
			     }else{
			     	tr602e.setFatherAccount(recursos_linea_basicaLocal.getFttcusu_ant());
			     }

			LocalidadLocal direccion  = null;
			     
			int areaPhone =0;
			if (phoneNumber!=null && !phoneNumber.trim().equals("")){
				areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
				if (phoneNumber.length()>8){
					phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}
			tr602e.setPhoneNumber(phoneNumber);
			
			tr602e.setPassword(recursos_linea_basicaLocal.getPassword_fttc());
			tr602e.setAtiempoRequestNumber(act.getNumeroPeticion().longValue());
			LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			tr602e.setLocality(localidadKey.cod_loc);
			tr602e.setAreaCode(areaPhone);
			//enviamos el arreglo de ps
			tr602e.setSpecialService(special);
			 	
			Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			
			//seteamos los datos como activdad familia  demas campos necesarios en la tabla mensaje estado linea
			Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
			mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
			//mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familiaKey.faps_id.intValue()));
			mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
			//enviamos la TR por la cola y continuara a procesar la respuesta de broker
			mq.enviarMensaje(tr602e);
			      
			}catch (FinderException e) {
				// TODO Bloque catch generado automáticamente 
				log.debug("Error al buscar la petición en Recursos_linea_basica_local:" + e);
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en Desconfigurar IMS"+e);
				e.printStackTrace();
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en Desconfigurar IMS"+e);
				e.printStackTrace();
			}
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaConfiguracionAutIMS(co.com.telefonica.atiempo.interfaces.atiempo.TR060S)
		 */
		public void respuestaConfiguracionAutIMS(TR602S tr602s) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			log.debug("respuestaConfigurarAutIMS");	
			try{
				
				String codAct = "";
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr602s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;

				
				try
				{
					//validamos si se setearon los datos de salida de la TR para saber el estado de la ptecion y la activadad
				 	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
					
					} 
				catch (FinderException fex)
					{
						mensajeEstadoLineaLocal = null ;
				
					// Validacion de existencia de la respuesta en la Base de Datos 
					// en la tabla Mensaje_Estado_Linea
					if (mensajeEstadoLineaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr602s));
									throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr602s.getId(),ATiempoAppEx.EXCEPTION);
								}
					}
					//seteamos la activadad generadora
					codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
					//setemoass las tablas y campso para continuar los flujos y activadades
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					
					//intanciamos la tanbla error legado en la cual se validara los errores que nos envia broker 
					//consultamos si nos llego un error en la tabla neviando el codigo y la TR
					
					ErrorLegadoLocal errorLegado = getErrorLegado(Long.toString(tr602s.getErrorCode()),"TR602S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
				
							//si la respuesta de codigo erre es = 0 continua con sus activdaddes normales
					if (!tr602s.isError()) {
					log.debug("Respuesta de IMS OK");
					actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_asig_rec_stb, "N");
					actDto.setObservacion(".");
					actividadEJB.terminar(actDto);
					//se setea el error en mensaje estado linea pero no se cierra la activad
					//mensajeEstadoLineaLocal.setF_reference_14(1);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Se termino activida IMS");
					return;
					//si no trajo algo diferente a null es que encontro un error Propio de IMS se deriba a PGI
					}else if(errorLegado != null){
					
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
//						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						mensajeEstadoLineaLocal.setId_error(Long.toString(tr602s.getErrorCode()));
						mensajeEstadoLineaLocal.setDesc_error(tr602s.getErrorCodeMessage());
						//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb , "N");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						actDto.setObservacion("Error en el proceso de Desconfigurar IMS. " + tr602s.getErrorCodeMessage() + " Se envia a " + bandeja );
						//actDto.setRealizarTerminoInmediato(true);
						actividadEJB.terminar(actDto);
						return;
				}else{
					// si el error es diferente de 0 pero no se encontro en la tabla es un error de Broker y procedemos a deribas a gestion de abonados	
					log.debug("Error de IMS entra a(Gestion de abonados)");
					//actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);

					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					mensajeEstadoLineaLocal.setCod_actividad_generadora(codAct);
					mensajeEstadoLineaLocal.setDesc_error(tr602s.getErrorCodeMessage());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
					actDto.setObservacion("Error de respuesta con IMS "+ tr602s.getErrorCodeMessage() +" se redirige hacia gestion de abonados");
					log.debug("Se redirige a Gestion de Abonados Automaticamente");	
					actividadEJB.terminar(actDto);
					return;
				}

					
				} catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Configurar IMS"+e);
					e.printStackTrace();
				} catch (TnProcesoExcepcion e) {
					log.debug("Error en Respuesta Configurar IMS"+e);
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				}
		
		
		public void desconfiguracionAutIMS(ActividadEJBDTO act, String operacionComercial) throws ATiempoAppEx {
			try {
				log.debug("inicia Mensaje Desconfiguracion IMS "+act.getNumeroPeticion());
				ArrayList special = new ArrayList();
				DesconfiguracionAutomaticaIMSMQ mq  = new DesconfiguracionAutomaticaIMSMQ();
				TR602E tr602e = new TR602E();
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				 
				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
				Collection ps = peticionesInterface.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
				PsVsOcVO psVsOcVO=null;
				boolean hayCambioProd = false;
//				intanciamos la tabla peticion
			    PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			    PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			    PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					psVsOcVO = (PsVsOcVO) iter.next();
					Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psVsOcVO.getPsId()));
									
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaPcLinea && operacionComercial.equals(psVsOcVO.getOpComTipo()))
					{
						if (peticionLocal.getTica_id().equals("P")||peticionLocal.getTica_id().equals("T")){
							 tr602e.setProcessType(false);
						}else{
							tr602e.setProcessType(true);
					}
						if (producto_servicioLocal.getPs_nombre().equals("LINEA PRECABLEADA"))
						{
							   tr602e.setPrecableada(true);
						}else
						{
							   tr602e.setPrecableada(false);
						}
						if (producto_servicioLocal.getPs_nombre().equals("LINEA CUENTA CONTROLADA"))
						{
							  tr602e.setControlAccount(true);
							   
						}else
						{
							  tr602e.setControlAccount(false);
						}
						operacionComercial=psVsOcVO.getOpComTipo();
						break;
					}else{
						if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaLinea){
							tr602e.setProcessType(false);
							operacionComercial=psVsOcVO.getOpComTipo();
							log.debug("operacion comercial "+operacionComercial);
							special.add(psVsOcVO.getPsId());
						}
					}
				}

				tr602e.setId(idCorrelativoMensaje.toString());
				
				String phoneNumber = recursos_linea_basicaLocal.getTelefono_asg().toString();
				if(psVsOcVO.getOpComId()!= null && psVsOcVO.getOpComId().equals("")
						&& psVsOcVO.getOpComId().equals(new Long (ComunInterfaces.cambioNumero))){
					log.debug("hay solo cambio de numero se envia el telefono anterior "+recursos_linea_basicaLocal.getTelefono_ant()+"para la peticion "+act.getNumeroPeticion());
					phoneNumber =  recursos_linea_basicaLocal.getTelefono_ant().toString();
				}else{
					log.debug("no hay operacion de cambio de numero se envia el telefono ASG "+recursos_linea_basicaLocal.getTelefono_asg()+"para la peticion "+act.getNumeroPeticion());
					
					 phoneNumber =  recursos_linea_basicaLocal.getTelefono_asg().toString();
				}
				
				if(peticionLocal.getTica_id().equals("B")&& psVsOcVO.getOpComTipo().equals("B")&&!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			      {
			        tr602e.setFatherAccount(recursos_linea_basicaLocal.getFttcusu_asg());
			      }else{
			      	tr602e.setFatherAccount(recursos_linea_basicaLocal.getFttcusu_ant());
			      }

			    LocalidadLocal direccion  = null;
			    int areaPhone =0;
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					areaPhone=Integer.parseInt(phoneNumber.substring(0,1));
					if (phoneNumber.length()>8){
						phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}
				tr602e.setPhoneNumber(phoneNumber);
				
				tr602e.setPassword(recursos_linea_basicaLocal.getPassword_fttc());
			    tr602e.setAtiempoRequestNumber(act.getNumeroPeticion().longValue());
			    LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
			    tr602e.setLocality(localidadKey.cod_loc);
			    tr602e.setAreaCode(areaPhone);
			    //enviamos el arreglo de ps
			    tr602e.setSpecialService(special);
			  	
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				//seteamos los datos como activdad familia  demas campos necesarios en la tabla mensaje estado linea
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
				mensajeEstadoLineaLocal.setPeti_numero(peticionKey.peti_numero);
				//mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(familiaKey.faps_id.intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				//enviamos la TR por la cola y continuara a procesar la respuesta de broker
			    mq.enviarMensaje(tr602e);
			      
			}catch (FinderException e) {
				// TODO Bloque catch generado automáticamente 
				log.debug("Error al buscar la petición en Recursos_linea_basica_local:" + e);
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en Desconfigurar IMS"+e);
				e.printStackTrace();
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en Desconfigurar IMS"+e);
				e.printStackTrace();
			}
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaConfiguracionAutIMS(co.com.telefonica.atiempo.interfaces.atiempo.TR060S)
		 */
		public void respuestaDesconfiguracionAutIMS(TR602S tr602s) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			log.debug("respuestaDesconfigurarAutIMS");	
			try{
				
				String codAct = "";
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr602s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;

				
				try
				{
					//validamos si se setearon los datos de salida de la TR para saber el estado de la ptecion y la activadad
				 	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
					
					}
				catch (FinderException fex)
					{
						mensajeEstadoLineaLocal = null ;
				
					// Validacion de existencia de la respuesta en la Base de Datos 
					// en la tabla Mensaje_Estado_Linea
					if (mensajeEstadoLineaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr602s));
									throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr602s.getId(),ATiempoAppEx.EXCEPTION);
								}
					}
					//seteamos la activadad generadora
					codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
					//setemoass las tablas y campso para continuar los flujos y activadades
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					
					//intanciamos la tanbla error legado en la cual se validara los errores que nos envia broker 
					//consultamos si nos llego un error en la tabla neviando el codigo y la TR
					ErrorLegadoLocal errorLegado = getErrorLegado(Long.toString(tr602s.getErrorCode()),"TR602S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
				
							//si la respuesta de codigo erre es = 0 continua con sus activdaddes normales
					if (!tr602s.isError()) {
					log.debug("Respuesta de IMS OK");
					actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_asig_rec_stb, "N");
					actDto.setObservacion(".");
					actividadEJB.terminar(actDto);
					//se setea el error en mensaje estado linea pero no se cierra la activad
					//mensajeEstadoLineaLocal.setF_reference_14(1);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Se termino activida Desconfigurar IMS");
					return;
					//si no trajo algo diferente a null es que encontro un error Propio de IMS se deriba a PGI
					}else if(errorLegado != null){
					
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
//						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						mensajeEstadoLineaLocal.setId_error(Long.toString(tr602s.getErrorCode()));
						mensajeEstadoLineaLocal.setDesc_error(tr602s.getErrorCodeMessage());
						//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb , "N");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						actDto.setObservacion("Error en el proceso de Desconfigurar IMS. " + tr602s.getErrorCodeMessage() + " Se envia a " + bandeja );
						//actDto.setRealizarTerminoInmediato(true);
						actividadEJB.terminar(actDto);
						return;
				}else{

							// si el error es diferente de 0 pero no se encontro en la tabla es un error de Broker y procedemos a deribas a gestion de abonados	
								log.debug("Error de IMS entra a(Gestion de abonados)");
								//actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
	
								mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
								mensajeEstadoLineaLocal.setCod_actividad_generadora(codAct);
								mensajeEstadoLineaLocal.setDesc_error(tr602s.getErrorCodeMessage());
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
								actDto.setObservacion("Error de respuesta con IMS "+ tr602s.getErrorCodeMessage() +" se redirige hacia gestion de abonados");
								log.debug("Se redirige a Gestion de Abonados Automaticamente");	
								actividadEJB.terminar(actDto);
								return;
				}

					
				} catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Desconfigurar IMS"+e);
					e.printStackTrace();
				} catch (TnProcesoExcepcion e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Desconfigurar IMS"+e);
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				}

					
			
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configuracionAutMSAN(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
		 */
		public void configuracionAutMSAN(ActividadEJBDTO act) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			
			log.debug("Entro a configuracionAutMSAN");
			ArrayList equipment=new ArrayList();
			ArrayList endPointType=new ArrayList();
			ArrayList sippstData=new ArrayList();
			
			try {
				//se inicia la tabla recursos_linea_basica
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
				Collection ps = peticionesInterface.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
				PsVsOcVO psVsOcVO=null;
				String operacionComercial="";
				String Operacion = null;
				String hotTime = "";
				boolean isPrecableada = false;
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
//				if(recursos_linea_basicaLocal.getRack() == null || recursos_linea_basicaLocal.getRack().equals("")
//						|| recursos_linea_basicaLocal.getSubRack() == null || recursos_linea_basicaLocal.getSubRack().equals("")){
//					String mensaje = "Error en el proceso de Configuracion/Desconfiguracion MSAN.  La informacion no contiene RACK o SUBRACK";
//					log.debug("Error al setear RACK y SUBRACK. Se deriva a PGI");
//					
//					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
//					return;
//				}
				
				Tecnologias_MSANLocalHome conf_tecnologia = (Tecnologias_MSANLocalHome) HomeFactory.getHome(Tecnologias_MSANLocalHome.JNDI_NAME);
				Tecnologias_MSANLocal conf_tecnologiaLocal;
				try{
					conf_tecnologiaLocal = conf_tecnologia.findByEquipo(recursos_linea_basicaLocal.getFttcmode_asg());
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente 
					String mensaje = "Error en el proceso de Configuracion/Desconfiguracion MSAN.  No se encontro el equipo";
					log.debug("Error en el proceso de Configuracion/Desconfiguracion MSAN.  No se encontro el equipo");
					
					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
					return;
				}
				String len =recursos_linea_basicaLocal.getLen();
				int portTemp = Integer.parseInt(len.substring(len.length()-2,len.length()));
				if(portTemp >63){
					String mensaje = "Se redirige a Gestion de Abonados Automaticamente porque el puerto no es valido";
					enviarGestionAbonados(mensajeEstadoLineaLocal,mensaje, act);
					return;
				}
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					psVsOcVO = (PsVsOcVO) iter.next();
					Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psVsOcVO.getPsId()));
									
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaPcLinea)
					{
						log.debug("Se valida "+ psVsOcVO.getFaPsId()+" con familia 300");
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						//validamos si el nombre del ps es linea precableada para setar el campo
						if (producto_servicioLocal.getPs_nombre().equals("LINEA PRECABLEADA"))
						{
							log.debug("El Ps "+ psVsOcVO.getPsId()+" pertenece a una linea precableada");
							Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Operation_Alta_Precableada");
							Operacion = propertiesDBLocal.getValor();
							propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Hottime_Alta");
							hotTime = propertiesDBLocal.getValor();
							isPrecableada = true;
						}else {
							Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Operation_Alta");
							Operacion = propertiesDBLocal.getValor();
						}
						operacionComercial = psVsOcVO.getOpComTipo().toString();
						break;
					}else{
						if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaLinea)
								operacionComercial = psVsOcVO.getOpComTipo().toString();
					}
				}
				if(!camposFTTCok(recursos_linea_basicaLocal,act,true,operacionComercial)){
					String mensaje = "Inventario mal definido";
					log.debug("Inventario mal definido se envia a PGI");
					
					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
					return;
				}
				equipment.add(setEquipmentFTTC(recursos_linea_basicaLocal, operacionComercial, act,true));
				endPointType.add(setEndPointTypeFTTC(recursos_linea_basicaLocal,operacionComercial, len.substring(len.length()-2,len.length()),act,true));
				sippstData.add(setSippstDataFTTC(conf_tecnologiaLocal,recursos_linea_basicaLocal,hotTime,isPrecableada));
				
				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(psVsOcVO.getFaPsId().intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
				enviarTR603E(Operacion,act.getNumeroPeticion(),idCorrelativoMensaje,equipment,endPointType,sippstData);
				
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al instanciar un objeto: "+e);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en la busqueda del recurso: "+e);
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en el formato de la variable: "+e);
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en la actividad Director de Flujos.CONF_AUT_MSAN.CONF_AUT_MSAN: "+e);
				}
			
			
			
		}
		
		public void desconfiguracionAutMSAN(ActividadEJBDTO act) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			
			log.debug("Entro a desconfiguracionAutMSAN");
			ArrayList equipment=new ArrayList();
			ArrayList endPointType=new ArrayList();
			ArrayList sippstData=new ArrayList();
			
			try {
				//se inicia la tabla recursos_linea_basica
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
				PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
				Collection ps = peticionesInterface.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
				PsVsOcVO psVsOcVO=null;
				String operacionComercial="";
				String Operacion = null;
				String hotTime = "";
				boolean isPrecableada = false;
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(idCorrelativoMensaje);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
//				if(recursos_linea_basicaLocal.getRack() == null || recursos_linea_basicaLocal.getRack().equals("")
//						|| recursos_linea_basicaLocal.getSubRack() == null || recursos_linea_basicaLocal.getSubRack().equals("")){
//					String mensaje = "Error en el proceso de Configuracion/Desconfiguracion MSAN.  La informacion no contiene RACK o SUBRACK";
//					log.debug("Error al setear RACK y SUBRACK. Se deriva a PGI");
//					
//					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
//					return;
//				}
//				
				Tecnologias_MSANLocalHome conf_tecnologia = (Tecnologias_MSANLocalHome) HomeFactory.getHome(Tecnologias_MSANLocalHome.JNDI_NAME);
				Tecnologias_MSANLocal conf_tecnologiaLocal;
				try{
					conf_tecnologiaLocal = conf_tecnologia.findByEquipo(recursos_linea_basicaLocal.getFttcmode_asg());
				} catch (FinderException e) {
					// TODO Bloque catch generado automáticamente 
					String mensaje = "Error en el proceso de Configuracion/Desconfiguracion MSAN.  No se encontro el equipo";
					log.debug("Error en el proceso de Configuracion/Desconfiguracion MSAN.  No se encontro el equipo");
					
					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
					return;
				}
				String len =recursos_linea_basicaLocal.getLen();
				int portTemp = Integer.parseInt(len.substring(len.length()-2,len.length()));
				if(portTemp >63){
					String mensaje = "Se redirige a Gestion de Abonados Automaticamente porque el puerto no es valido";
					enviarGestionAbonados(mensajeEstadoLineaLocal,mensaje, act);
					return;
				}
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
					psVsOcVO = (PsVsOcVO) iter.next();
					Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
					Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psVsOcVO.getPsId()));
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaPcLinea)
					{
						log.debug("Se valida "+ psVsOcVO.getFaPsId()+" con familia 300");
						Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
						//Se valida si el nombre del ps es linea precableada para setar el campo
						if (producto_servicioLocal.getPs_nombre().equals("LINEA PRECABLEADA"))
						{
							log.debug("El Ps "+ psVsOcVO.getPsId()+" pertenece a una linea precableada");
							Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Operation_Baja_Precableada");
							Operacion = propertiesDBLocal.getValor();
							propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Hottime_Baja");
							hotTime = propertiesDBLocal.getValor();
							isPrecableada = true;
						}else {
							Properties_BDLocal propertiesDBLocal = propertiesBDLocalHome.findByPrimaryKey("TR603_Operation_Baja");
							Operacion = propertiesDBLocal.getValor();
						}
						operacionComercial = psVsOcVO.getOpComTipo().toString();
						break;
					}else{
						if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaLinea)
								operacionComercial = psVsOcVO.getOpComTipo().toString();
					}
				}
				if(!camposFTTCok(recursos_linea_basicaLocal,act,false,operacionComercial)){
					String mensaje = "Inventario mal definido";
					log.debug("Inventario mal definido se envia a PGI");
					
					derivarPGI(act, mensaje, new Long("701"),mensajeEstadoLineaLocal, act.getNumeroPeticion());
					return;
				}
				equipment.add(setEquipmentFTTC(recursos_linea_basicaLocal, operacionComercial, act,false));
				endPointType.add(setEndPointTypeFTTC(recursos_linea_basicaLocal,operacionComercial, len.substring(len.length()-2,len.length()), act,false));
				sippstData.add(setSippstDataFTTC(conf_tecnologiaLocal,recursos_linea_basicaLocal,hotTime,isPrecableada));
				
				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());
				mensajeEstadoLineaLocal.setCod_familia_ps(new Integer(psVsOcVO.getFaPsId().intValue()));
				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				
				enviarTR603E(Operacion,act.getNumeroPeticion(),idCorrelativoMensaje,equipment,endPointType,sippstData);
				
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al instanciar un objeto: "+e);
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en la busqueda del recurso: "+e);
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en el formato de la variable: "+e);
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				log.debug("error en la actividad Director de Flujos.CONF_AUT_MSAN.CONF_AUT_MSAN: "+e);
				}
			
			
			
		}
		/**
		 * @param operacion
		 * @param numeroPeticion
		 * @param idCorrelativoMensaje
		 * @param equipment
		 * @param endPointType
		 * @param sippstData
		 */
		private void enviarTR603E(String operacion, Long numeroPeticion, Long idCorrelativoMensaje, ArrayList equipment, ArrayList endPointType, ArrayList sippstData) {
			// TODO Apéndice de método generado automáticamente
			
			try {
				ConfiguracionAutomaticaMSANMQ mq  = new ConfiguracionAutomaticaMSANMQ();
				TR603E tr603e = new TR603E();
				tr603e.setOperation(operacion);
				tr603e.setAtiempoRequestNumber(numeroPeticion.toString());
				tr603e.setId(idCorrelativoMensaje.toString());
				tr603e.setEquipment(equipment);
				tr603e.setEndPointType(endPointType);
				tr603e.setSippstData(sippstData);
				 mq.enviarMensaje(tr603e);
			} catch (ATiempoAppEx e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error al enviar tr603e: "+e);
			}

		}

		/**
		 * @param conf_tecnologiaLocal
		 * @param recursos_linea_basicaLocal
		 * @param isPrecableada
		 * @param hotTime
		 * @return
		 */
		private Object setSippstDataFTTC(Tecnologias_MSANLocal conf_tecnologiaLocal, Recursos_linea_basicaLocal recursos_linea_basicaLocal, String hotTime, boolean isPrecableada) {
			// TODO Apéndice de método generado automáticamente
			
			SippstDataFTTC sippstDataFTTC = new SippstDataFTTC();
			sippstDataFTTC.setPhonenumber(recursos_linea_basicaLocal.getTelefono_asg().toString());
			sippstDataFTTC.setAuthuser(recursos_linea_basicaLocal.getTelefono_asg().toString());
			sippstDataFTTC.setAuthpass(recursos_linea_basicaLocal.getPassword_fttc());
			sippstDataFTTC.setCallhold(conf_tecnologiaLocal.getCallhold());
			sippstDataFTTC.setThreeparty(conf_tecnologiaLocal.getThreeparty());
			sippstDataFTTC.setCw(conf_tecnologiaLocal.getCw());
			sippstDataFTTC.setCt(conf_tecnologiaLocal.getCt());
			sippstDataFTTC.setDc(conf_tecnologiaLocal.getDc());
			sippstDataFTTC.setConf(conf_tecnologiaLocal.getConf());
			
			if(isPrecableada){
				sippstDataFTTC.setHotlinenum(recursos_linea_basicaLocal.getTelefono_asg().toString());
				sippstDataFTTC.setHottime(hotTime);
				sippstDataFTTC.setHotline("enable");
			}else{
				sippstDataFTTC.setHotlinenum("");
				sippstDataFTTC.setHottime("");
				sippstDataFTTC.setHotline("");
			}
			
			return sippstDataFTTC;
		}

		/**
		 * @param mensajeEstadoLineaLocal
		 * @param mensaje
		 * @param act
		 */
		private void enviarGestionAbonados(Mensaje_estado_lineaLocal mensajeEstadoLineaLocal, String mensaje, ActividadEJBDTO act) {
			// TODO Apéndice de método generado automáticamente
			
			log.debug("Error de MSAN entra(Gestion de abonados)");
			mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
			mensajeEstadoLineaLocal.setDesc_error(mensaje);

			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
			act.setObservacion(mensaje);
			log.debug("Se redirige a Gestion de Abonados Automaticamente");	
			act.setRealizarTerminoInmediato(true);
		}

		/**
		 * @param recursos_linea_basicaLocal
		 * @param operacionComercial
		 * @param act
		 * @param string
		 * @return
		 * @throws Exception
		 * @throws NumberFormatException
		 * @throws NamingException
		 * @throws FinderException
		 */
		
		
		//metodo que quita los ceros a la izquierda de un numero
		private long quitarCeros(String numero){
			
			long numeroSinCeros=0;
			
			for (int x =0;x<numero.length();x++)
			{
				if(numero.charAt(x)=='0')
				{
					 numero.substring((x+1), numero.length());
				}else{
					numeroSinCeros=new Long (numero).longValue();
					break;
				}
			}			
			return numeroSinCeros;
		}
	// metodo que valida si el caracter solo contiene numeros en caso contrario reotna false y se iria a PGI
		private boolean sonNumeros(String numero){
			boolean esNumero=true;
			if(numero.length()==0)
			{
				return false;
			}
				for (int x =0;x<numero.length();x++)
				{
					for(int y=0;y<=9;y++)
					{
						int digito=(int) Character.getNumericValue(numero.charAt(x));
						if(digito==y)
						{
							esNumero=true;
							break;
						}else{
							esNumero=false;
						}
					}
					if(!esNumero){
						return false;
					}
				}	
				
		return esNumero;
		}
		//se crea metodo q valida que los campos rack sub rack y slot no sean nulo retorna true si esta bien,false si estan vacios y se iria aPGI
		private boolean camposFTTCok(Recursos_linea_basicaLocal rlb, ActividadEJBDTO act, boolean esConf,String operacionComercial) throws FinderException, NamingException{
			  PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			    PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			    PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			    
			boolean ok=true;
			if(rlb.getRack() == null || rlb.getRack().equals("")
					|| rlb.getSubRack() == null || rlb.getSubRack().equals("")||!sonNumeros(rlb.getRack())|| !sonNumeros(rlb.getSubRack())){
				return false;
				}
			
			if(((peticionLocal.getTica_id().equals("P")&& esConf||peticionLocal.getTica_id().equals("T")&& esConf)
					||(peticionLocal.getTica_id().equals("A")&&operacionComercial.equals("A"))
							||(peticionLocal.getTica_id().equals("B")&&operacionComercial.equals("B"))
								&&!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")))
		      {
				if(rlb.getFttcslot_asg()==null||rlb.getFttcslot_asg().equals("")||!sonNumeros(rlb.getFttcslot_asg())){
					return false;
				}
		      }else{
		      	if(rlb.getFttcslot_ant()==null||rlb.getFttcslot_ant().equals("")||!sonNumeros(rlb.getFttcslot_ant())){
					return false;
				}
		      }
			return ok;
		}
		private Object setEndPointTypeFTTC(Recursos_linea_basicaLocal recursos_linea_basicaLocal, String operacionComercial, String port, ActividadEJBDTO act,boolean esConf) throws NamingException, FinderException {
			// TODO Apéndice de método generado automáticamente
		    PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		    PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
		    PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
		
			EndPointTypeFTTC endPointTypeFTTC = new EndPointTypeFTTC();
			
			endPointTypeFTTC.setRack(quitarCeros(recursos_linea_basicaLocal.getRack().toString()));
			endPointTypeFTTC.setSubrack(quitarCeros(recursos_linea_basicaLocal.getSubRack().toString()));
			log.debug("Se setean valores para tipo de OC "+operacionComercial);
			log.debug("Se setean valores para tipo oc de la peticion "+peticionLocal.getTica_id());
			if(( esConf &&!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
					||(peticionLocal.getTica_id().equals("B")&&operacionComercial.equals("B"))
					||(!operacionComercial.equals("ACP")&& act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")))
			//if(!operacionComercial.equals("BCP") && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
		      {
				endPointTypeFTTC.setSlot(quitarCeros(recursos_linea_basicaLocal.getFttcslot_asg().toString()));
		      }else{
		      	endPointTypeFTTC.setSlot(quitarCeros(recursos_linea_basicaLocal.getFttcslot_ant().toString()));
		      }
			endPointTypeFTTC.setPort(port);
			endPointTypeFTTC.setDesc("null");
			endPointTypeFTTC.setVpi("8");
			endPointTypeFTTC.setVci("35");
			
			return endPointTypeFTTC;
			
		}

		/**
		 * @param recursos_linea_basicaLocal
		 * @param operacionComercial
		 * @param act
		 * @param psVsOcVO
		 * @return
		 * @throws FinderException
		 * @throws NamingException
		 */
		private Object setEquipmentFTTC(Recursos_linea_basicaLocal recursos_linea_basicaLocal, String operacionComercial, ActividadEJBDTO act, boolean esConf) throws FinderException, NamingException {
			// TODO Apéndice de método generado automáticamente
		    PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		    PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
		    PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
		    
			EquipmentFTTC equipmentFTTC = new EquipmentFTTC();
			log.debug("Se setean valores para tipo de OC "+operacionComercial);
			log.debug("Se setean valores para tipo oc de la peticion "+peticionLocal.getTica_id());
			if(( esConf && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
							||(peticionLocal.getTica_id().equals("B")&& operacionComercial.equals("B"))
							||(!operacionComercial.equals("ACP")&& act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")))
			//if(!operacionComercial.equals("BCP") && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")
			//		&& psVsOcVO.getOpComId().intValue() == ComunInterfaces.ID_OP_BAJA_TRASLADO)
		      {
				equipmentFTTC.setVendor(recursos_linea_basicaLocal.getFttcfab_asg());
				equipmentFTTC.setModel(recursos_linea_basicaLocal.getFttcmode_asg());	
				equipmentFTTC.setIp(recursos_linea_basicaLocal.getFttciplb_asg());
				equipmentFTTC.setTelnetuser(recursos_linea_basicaLocal.getFttcusu_asg());
				equipmentFTTC.setTelnetpassword(recursos_linea_basicaLocal.getFttcpwd_asg());
		      }else{
		      	equipmentFTTC.setVendor(recursos_linea_basicaLocal.getFttcfab_ant());
		      	equipmentFTTC.setModel(recursos_linea_basicaLocal.getFttcmode_ant());	
		      	equipmentFTTC.setIp(recursos_linea_basicaLocal.getFttciplb_ant());
		      	equipmentFTTC.setTelnetuser(recursos_linea_basicaLocal.getFttcusu_ant());
		      	equipmentFTTC.setTelnetpassword(recursos_linea_basicaLocal.getFttcpwd_ant());
		      }
			equipmentFTTC.setNetwork("");
			equipmentFTTC.setName("name");
			equipmentFTTC.setPassword(recursos_linea_basicaLocal.getPassword_fttc());
			return equipmentFTTC;
		}

		/**
		 * @param act
		 * @param mensaje
		 * @param long1
		 * @param mensajeEstadoLineaLocal
		 * @param numeroPeticion
		 * @throws Exception
		 * @throws NumberFormatException
		 */
		private void derivarPGI(ActividadEJBDTO act, String mensaje, Long causal, Mensaje_estado_lineaLocal mensajeEstadoLineaLocal, Long numeroPeticion) throws NumberFormatException, Exception {
			// TODO Aapéndice de método generado automáticamente
			String plataforma = VpistbbaConfig.getVariable("IDPGI");
			String bandeja = "PGI";
			
			mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
			mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
			mensajeEstadoLineaLocal.setDesc_error(mensaje);
			mensajeEstadoLineaLocal.setPeti_numero(numeroPeticion);

			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
			insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), causal, act.getIdActividadFlujo());
			act.setObservacion(mensaje);
			act.setRealizarTerminoInmediato(true);
			log.debug("finalizo proceso para derivar a PGI");
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaConfiguracionAutMSAN(co.com.telefonica.atiempo.interfaces.atiempo.TR518S)
		 */
		public void respuestaConfiguracionAutMSAN(TR603S tr603s) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			log.debug("respuesta Configuracion/Desconfiguracion AutMSAN");	
			
			try{
				
				String codAct = "";
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr603s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;

				
				try
				{
					//validamos si se setearon los datos de salida de la TR para saber el estado de la ptecion y la activadad
				 	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
					
					}
				catch (FinderException fex)
					{
						mensajeEstadoLineaLocal = null ;
				
					// Validacion de existencia de la respuesta en la Base de Datos 
					// en la tabla Mensaje_Estado_Linea
					if (mensajeEstadoLineaLocal == null) {
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr603s));
									throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr603s.getId(),ATiempoAppEx.EXCEPTION);
								}
					}
					//seteamos la activadad generadora
					codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
					//setemoass las tablas y campso para continuar los flujos y activadades
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					
					//intanciamos la tanbla error legado en la cual se validara los errores que nos envia broker 
					//consultamos si nos llego un error en la tabla neviando el codigo y la TR
					ErrorLegadoLocal errorLegado = getErrorLegado(Long.toString(tr603s.getErrorCode()),"TR603S");
					String plataforma = VpistbbaConfig.getVariable("IDPGI");
					String bandeja = "PGI";
				
							//si la respuesta de codigo erre es = 0 continua con sus activdaddes normales
					if (!tr603s.isError()) {
					log.debug("Respuesta de MSAN OK");
					actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_asig_rec_stb, "N");
					actDto.setObservacion(".");
					actividadEJB.terminar(actDto);
					//se setea el error en mensaje estado linea pero no se cierra la activad
					//mensajeEstadoLineaLocal.setF_reference_14(1);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					log.debug("Se termino activida Configuracion/Desconfiguracion MSAN");
					return;
					//si no trajo algo diferente a null es que encontro un error Propio de IMS se deriba a PGI
					}else if(errorLegado != null){
					
						plataforma = errorLegado.getPlataforma(); 
						bandeja	= getNombreBandeja(plataforma); 
//						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO")), actDto.getIdActividadFlujo());
						mensajeEstadoLineaLocal.setId_error(Long.toString(tr603s.getErrorCode()));
						mensajeEstadoLineaLocal.setDesc_error(tr603s.getErrorCodeMessage());
						//actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb , "N");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
						insertarCausalesCnaPeticion(mensajeEstadoLineaLocal, mensajeEstadoLineaLocal.getCod_actividad_generadora(), errorLegado.getIdCausa(), actDto.getIdActividadFlujo());
						actDto.setObservacion("Error en el proceso de Configuracion/Desconfiguracion MSAN. " + tr603s.getErrorCodeMessage() + " Se envia a " + bandeja );
						//actDto.setRealizarTerminoInmediato(true);
						actividadEJB.terminar(actDto);
						return;
				}else{
//				 si el error es diferente de 0 pero no se encontro en la tabla es un error de Broker y procedemos a deribas a gestion de abonados	
					
							
							// si el error es diferente de 0 pero no se encontro en la tabla es un error de Broker y procedemos a deribas a gestion de abonados	
								log.debug("Error de MSAN entra(Gestion de abonados)");
								actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
	
								mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
								mensajeEstadoLineaLocal.setCod_actividad_generadora(codAct);
								mensajeEstadoLineaLocal.setDesc_error(tr603s.getErrorCodeMessage());
								//actDto1.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "S");	
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
								actDto.setObservacion("Error de respuesta con MSAN "+ tr603s.getErrorCodeMessage() +" se redirige hacia gestion de abonados");
								log.debug("Se redirige a Gestion de Abonados Automaticamente");	
								actividadEJB.terminar(actDto);
								return;
				}

					
				} catch (NamingException e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Configuracion/Desconfiguracion MSAN"+e);
					e.printStackTrace();
				} catch (TnProcesoExcepcion e) {
					// TODO Bloque catch generado automáticamente
					log.debug("Error en Respuesta Configuracion/Desconfiguracion MSAN"+e);
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				}	
		// fin REQ FTTC

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#CorregirDireccionPeticionTraslado(java.lang.Long, java.lang.Long)
		 */
		//dcardena Funcion que corrige las direcciones de la tabla peticion especialmente para los traslados donde en la tabla peticion se estan
		//seteando la direccion de origen en vez de la direccion de destino de la instalacion
	
		public void CorregirDireccionPeticionTraslado(Long numeroAtis, Long petiNumero) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
				try{
					
					log.debug("se va a verificar la direccion de la peticio"+petiNumero);
					//se consulta la peticion Atis	    
				    Peticion_atisLocalHome peticion_atisLocalHome=(Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				    Peticion_atisKey peticion_atisKey=new Peticion_atisKey(numeroAtis);
				    Peticion_atisLocal peticion_atisLocal = peticion_atisLocalHome.findByPrimaryKey(peticion_atisKey);
				    //se obtiene la agrupacion atis de la llave FK
				    Collection agrupaciones = peticion_atisLocal.getAgrupacion_atis();
				    //recorremos las agrupaciones
					for (Iterator iter = agrupaciones.iterator(); iter.hasNext();) 
					{
						log.debug("Hay agrupaciones en la peticion"+petiNumero);
						//tomamos una por una 
						Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) iter.next();
						//validamos si es 6 osea alta traslado
						if(agrupacion_atisLocal.getTip_opr_cmr_cd().equals(new Long(6))){
							//si es elta obtenemos las direcciones
							log.debug("hay agrupacion de alta traslado en la peticion "+petiNumero);
							Collection direcciones=agrupacion_atisLocal.getDireccion_atis();
							//recoremos las direcciones
							for (Iterator iter2 = direcciones.iterator(); iter2.hasNext();) 
							{
								log.debug("hay direcciones en la peticion "+petiNumero);
								//obtenemos cada direccion
								Direccion_atisLocal dir =(Direccion_atisLocal) iter2.next();
								//validamos si es 6 apra obtener la irecciones de alta
								if(dir.getFk_01_direc_atis().getTip_opr_cmr_cd().equals(new Long (6)))
								{
									log.debug("se va a actualizar la direccion en la peticion "+petiNumero);
									//si es 6 reemplazamos los datos en la tabla peticion
									PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
									PeticionLocal peticionLocal =peticionLocalHome.findByPetiNumero(petiNumero);
									
									peticionLocal.setDir_tip_via_1(dir.getDir_tip_via_1());
									peticionLocal.setDir_nro_via_1(dir.getDir_nro_via_1());
									peticionLocal.setDir_lt1_via_1(dir.getDir_lt1_via_1());
									peticionLocal.setDir_lt2_via_1(dir.getDir_lt2_via_1());
									if (dir.getDir_zon_via_1() !=null && !dir.getDir_zon_via_1().equals("")){
										peticionLocal.setDir_zon_via_1(dir.getDir_zon_via_1()); 
									}else{
										peticionLocal.setDir_zon_via_1("-");
									}
									if (dir.getDir_tip_via_2() !=null && !dir.getDir_tip_via_2().equals("")){
										peticionLocal.setDir_tip_via_2(dir.getDir_tip_via_2());
									}else{
										peticionLocal.setDir_tip_via_2("-");
									}
									peticionLocal.setDir_tip_via_2(dir.getDir_tip_via_2());
									peticionLocal.setDir_nro_via_2(dir.getDir_nro_via_2());
									peticionLocal.setDir_lt1_via_2(dir.getDir_lt1_via_2());
									peticionLocal.setDir_lt2_via_2(dir.getDir_lt2_via_2());
									peticionLocal.setDir_zon_via_2(dir.getDir_zon_via_2());
									peticionLocal.setDir_tip_lg1(dir.getDir_tip_lg1());
									peticionLocal.setDir_nro_lg1(dir.getDir_nro_lg1());
									peticionLocal.setDir_tip_lg2(dir.getDir_tip_lg2());
									peticionLocal.setDir_nro_lg2(dir.getDir_nro_lg2());
									peticionLocal.setDir_tip_lg3(dir.getDir_tip_lg3());
									peticionLocal.setDir_nro_lg3(dir.getDir_nro_lg3());
									peticionLocal.setCod_ext_loc_cd(dir.getCod_ext_loc_cd());
									peticionLocal.setCod_ter_cd(dir.getCod_ter_cd());
									peticionLocal.setCod_are_tel_cd(dir.getCod_are_tel_cd());
									peticionLocal.setAre_sn_tel_cd(dir.getAre_sn_tel_cd());
									peticionLocal.setLoc_ext_tel_cd(dir.getLoc_ext_tel_cd());
									peticionLocal.setTip_cal_atis_cd(dir.getTip_cal_atis_cd());
									peticionLocal.setNom_cal_ds(dir.getNom_cal_ds());
									peticionLocal.setNum_cal_nu(dir.getNum_cal_nu());
									peticionLocal.setDsc_cmp_pri_ds( dir.getDsc_cmp_pri_ds());
									peticionLocal.setDsc_cmp_seg_ds(dir.getDsc_cmp_seg_ds());
									peticionLocal.setCod_loc_cd(dir.getCod_loc_cd()); 
									peticionLocal.setNom_slo_no(dir.getNom_slo_no());
									log.debug("se refresco con exito "+petiNumero);	
									break;
								}
							}
						}
					}
				   // p.getAgrupacion_atis(	
				}catch (Exception e) {
					log.debug("Error obteniendo la direccion revisar la direccion de la peticion "+petiNumero+" atis"+numeroAtis+" Error"+e);
			 	}			
			}
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#CorregirDireccionPeticionTraslado(java.lang.Long, java.lang.Long)
		 */
		//dcardena Funcion que asigna la direccion de la tabla peticion (direccion que llega de atis OK) se almacena en direccion Atis como direccion de Instlacion
		//con formato OK
		public void actualizarDireccionAtis(Long numeroAtis, Long petiNumero) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
				try{
					
					log.debug("se va a Alacenar la direccion de la tabla peticion en la tabla direccion Atis: "+petiNumero);
					//se consulta la peticion Atis	    
				    Peticion_atisLocalHome peticion_atisLocalHome=(Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				    Peticion_atisKey peticion_atisKey=new Peticion_atisKey(numeroAtis);
				    Peticion_atisLocal peticion_atisLocal = peticion_atisLocalHome.findByPrimaryKey(peticion_atisKey);
				    //se obtiene la agrupacion atis de la llave FK
				    Collection agrupaciones = peticion_atisLocal.getAgrupacion_atis();
				    //recorremos las agrupaciones
					for (Iterator iter = agrupaciones.iterator(); iter.hasNext();) 
					{
						log.debug("Hay agrupaciones en la peticion "+petiNumero);
						//tomamos una por una 
						Agrupacion_atisLocal agrupacion_atisLocal = (Agrupacion_atisLocal) iter.next();
						//validamos si es 6 osea alta traslado
						if(agrupacion_atisLocal.getTip_opr_cmr_cd().equals(new Long(6))){
							//si es elta obtenemos las direcciones
							log.debug("hay agrupacion de alta traslado en la peticion "+petiNumero);
							Collection direcciones=agrupacion_atisLocal.getDireccion_atis();
							//recoremos las direcciones
							for (Iterator iter2 = direcciones.iterator(); iter2.hasNext();) 
							{
								log.debug("hay direcciones en la direccion atis "+petiNumero);
								//obtenemos cada direccion
								Direccion_atisLocal dir =(Direccion_atisLocal) iter2.next();
								//validamos si es 6 apra obtener la irecciones de alta
								if(dir.getFk_01_direc_atis().getTip_opr_cmr_cd().equals(new Long (6)))
								{
									log.debug("se va a actualizar la direccion en la tabla direccion atis "+petiNumero);
									//si es 6 reemplazamos los datos en la tabla peticion
									PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
									PeticionLocal peticionLocal =peticionLocalHome.findByPetiNumero(petiNumero);
									dir.setDir_tip_via_1(peticionLocal.getDir_tip_via_1()); 					
									dir.setDir_nro_via_1(peticionLocal.getDir_nro_via_1()); 					
									dir.setDir_lt1_via_1(peticionLocal.getDir_lt1_via_1()); 					
									dir.setDir_lt2_via_1(peticionLocal.getDir_lt2_via_1()); 					
									if (peticionLocal.getDir_zon_via_1() !=null && !peticionLocal.getDir_zon_via_1().equals("")){
										dir.setDir_zon_via_1(peticionLocal.getDir_zon_via_1()); 
									}else{
										dir.setDir_zon_via_1("-");
									}
									
									dir.setDir_tip_via_2(peticionLocal.getDir_tip_via_2()); 					
									dir.setDir_nro_via_2(peticionLocal.getDir_nro_via_2()); 					
									dir.setDir_lt1_via_2(peticionLocal.getDir_lt1_via_2()); 					
									dir.setDir_lt2_via_2(peticionLocal.getDir_lt2_via_2()); 					
									dir.setDir_zon_via_2(peticionLocal.getDir_zon_via_2()); 					
									dir.setDir_tip_lg1(peticionLocal.getDir_tip_lg1());
									dir.setDir_nro_lg1(peticionLocal.getDir_nro_lg1());
									dir.setDir_tip_lg2(peticionLocal.getDir_tip_lg2());
									dir.setDir_nro_lg2(peticionLocal.getDir_nro_lg2());
									dir.setDir_tip_lg3(peticionLocal.getDir_tip_lg3());
									dir.setDir_nro_lg3(peticionLocal.getDir_nro_lg3());
									dir.setCod_ext_loc_cd(peticionLocal.getCod_ext_loc_cd());
									dir.setCod_ter_cd(peticionLocal.getCod_ter_cd());
									dir.setCod_are_tel_cd(peticionLocal.getCod_are_tel_cd());
									dir.setAre_sn_tel_cd(peticionLocal.getAre_sn_tel_cd());
									dir.setLoc_ext_tel_cd(peticionLocal.getLoc_ext_tel_cd());
									dir.setTip_cal_atis_cd(peticionLocal.getTip_cal_atis_cd());
									dir.setNom_cal_ds(peticionLocal.getNom_cal_ds());
									dir.setNum_cal_nu(peticionLocal.getNum_cal_nu());
									dir.setDsc_cmp_pri_ds(peticionLocal.getDsc_cmp_pri_ds());
									dir.setDsc_cmp_seg_ds(peticionLocal.getDsc_cmp_seg_ds());
									dir.setCod_loc_cd(peticionLocal.getCod_loc_cd()); 
									dir.setNom_slo_no(peticionLocal.getNom_slo_no());					 							
									log.debug("se actualiza direccion atis con exito "+petiNumero);	
									break;
								}
							}
						}
					}
				   // p.getAgrupacion_atis(	
				}catch (Exception e) {
					log.debug("Error obteniendo la direccion revisar la direccion de la peticion "+petiNumero+" atis"+numeroAtis+" Error"+e);
			 	}			
			}

		public void ConfiguracionAutomaticaEOC(ActividadEJBDTO act) throws ATiempoAppEx {
			
			try{
				log.debug("Entro a Configuracion/Desconfiguracion AutomaticaEOC");
				ConfiguracionAutomaticaEOCMQ mq  = new ConfiguracionAutomaticaEOCMQ();
		        TR518E tr518e = new TR518E();
		        
		        Long numPeticion = act.getNumeroPeticion();
		        				

				
		    	PeticionLocalHome peticionHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		    	PeticionLocal peticionLocal = peticionHome.findByPrimaryKey(new PeticionKey(numPeticion));
		    	Peticion_atisKey petAtisK = (Peticion_atisKey)peticionLocal.getFk_01_pet_atis().getPrimaryKey();
		    	
		    	PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
				Agrupacion_atisLocalHome agrupacion_atisLocalHome = (Agrupacion_atisLocalHome) HomeFactory.getHome(Agrupacion_atisLocalHome.JNDI_NAME);
				Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
				Mensaje_estadoLocalHome mensajeEstadoLocalHome=(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				
				Producto_servicio_peticionLocal producto_servicio_peticionLocal  = null;
		    	Long idPeticion = act.getNumeroPeticion();

				/*
				 * GESTION DE BAJAS
				 */
				if(peticionLocal.getTica_id().equals("B")){
					tr518e.setInterfaz("BajaSIRSAsig");
				}else if(peticionLocal.getTica_id().equals("T")){
					tr518e.setInterfaz("TransferenciaSIRSAsig");
				}else{
					tr518e.setInterfaz("AltaSIRSAsig");
				}
				
				tr518e.setSource("ATIEMPO");
				tr518e.setVersion("1.0");
				tr518e.setDestination("ESB");
		    	
		    	
		    	tr518e.setNewSubCategory(0);
				tr518e.setOperationType(0);
				Collection ppsArray = peticionLocal.getProducto_servicio_peticion();
		    	Operacion_comercialLocal operacion_comercial = null;
		    	
		    	PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
		    	
		    	Long categoryBaja=null;
				Long subCategoryBaja=null;
				ArrayList psServiciosSuplementarios = new ArrayList();
				Long psLineaBasicaId=null;
				Long psId=null;
				int cantPsLineaBasica = 0;
				SpecialServicesRequest srs = null;
				//int opComercial = 0;
				int opComercialaux = 0;
				int opComercial = 0;
				
				int cantPBX = cantPbx(peticionLocal , idPeticion);
				boolean esTraslado = tieneTrasladoLB(idPeticion);
				boolean esBaja = false;
				boolean esTrasladoBaja = false;
				String tipoOperacion = "";
				for(Iterator iter = ppsArray.iterator();iter.hasNext();){
					producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					
					opComercial = operacion_comercialKey.opco_id.intValue();
					opComercialaux = operacion_comercialKey.opco_id.intValue();
					tipoOperacion = producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo();
					if (tipoOperacion.equals(ComunInterfaces.opCoTipoBaja))
						esBaja=true;
					else 
						esBaja=false;
					if(peticionesDelegate.pasaPSyOcXActividad(idPeticion,productoServicoKey.ps_id,operacion_comercialKey.opco_id,act.getIdActividadFlujo())){
						psId=productoServicoKey.ps_id;
						Familia_producto_servicioKey fPSK = (Familia_producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
						if(fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaPcLinea).longValue() || fPSK.faps_id.longValue() == new Long(ComunInterfaces.familiaIP).longValue() ){
							if (!ComunInterfaces.operacionBCP.equals(producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tipo())){
							//At-2067 - 14 abril 2009
								psLineaBasicaId=psId;
								boolean cruzada= esCruzada(idPeticion);
								
								tr518e.setOperationType(opComercial);
								tr518e.setNewCategory(producto_servicio_peticionLocal.getTip_pro_cmr_cd().longValue());
								tr518e.setNewSubCategory(producto_servicio_peticionLocal.getSbt_pro_cmr_cd().longValue());
								if((act.getCodigoActividad().equals("")|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
										|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA")))
										&& producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == ComunInterfaces.bajaTraslado){
									esTrasladoBaja = true;
								}
								cantPsLineaBasica++;
							}
						}else {
															
								if(producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == cambioNumero ){
									log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que es un cambio de numero");
								}else{
									
									srs = new SpecialServicesRequest();
									if(producto_servicio_peticionLocal.getEstado_ps_peticion().size()>0){
										
										Estado_ps_peticionLocal estado_ps_peticionLocal=(Estado_ps_peticionLocal) producto_servicio_peticionLocal.getEstado_ps_peticion().iterator().next();
										if(estado_ps_peticionLocal.getCod_estado_cierre().intValue()==estadoCierreError){
											log.debug("El Serv Sup: " + psId.toString() + " - " + "no se envia a configurar ya que esta Cancelado");
											srs=null;
										}else{											
											if((act.getCodigoActividad().equals("")|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
													|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA")))
													&& producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == ComunInterfaces.bajaTraslado){
												srs.setSpecialService(psId.toString());
												srs.setAction(2);
												esTrasladoBaja = true;
											}else{
												if(!(act.getCodigoActividad().equals("")|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
														|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA")))
														&& producto_servicio_peticionLocal.getIdOperacionComercial().longValue()!=ComunInterfaces.bajaTraslado){
													srs.setSpecialService(psId.toString());
												if (esBaja)
													srs.setAction(2);
												else
													srs.setAction(1);
												}
											}
										}
																			
									}else{
										//Cesar Remigio
										if((act.getCodigoActividad().equals("")|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
												|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA")))
												&& producto_servicio_peticionLocal.getIdOperacionComercial().longValue() == ComunInterfaces.bajaTraslado){
											srs.setSpecialService(psId.toString());
											srs.setAction(2);
											esTrasladoBaja = true;
										}else{
											if(!(act.getCodigoActividad().equals("")|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
													|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA")))
													&& producto_servicio_peticionLocal.getIdOperacionComercial().longValue()!=ComunInterfaces.bajaTraslado){
												srs.setSpecialService(psId.toString());
											if (esBaja)
												srs.setAction(2);
											else
												srs.setAction(1);
											}
											
											log.debug("Se setean los servicios suplementarios");
										}
														
										
									}
									
									if(srs!=null){

										psServiciosSuplementarios.add(srs);
										log.debug("Serv Sup: " + srs.getSpecialService() + " - " + "Action: " + srs.getAction());
									}
								}
						}						
					}
				}
			
				tr518e.setSpecialServicesType(psServiciosSuplementarios);
				if(categoryBaja!=null &&subCategoryBaja!=null ){
					log.debug("Seteando los valores de reversa de Cambio de Producto Peticion:" + idPeticion);
					tr518e.setNewCategory(categoryBaja.longValue());
					tr518e.setNewSubCategory(subCategoryBaja.longValue());
					
				}
				Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
				//guardamos los ps en de la ptecion en un arreglo
				Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
				//recorremos el arreglo
				if(ps!=null && ps.size()>0){
						for (Iterator iter = ps.iterator(); iter.hasNext();) {						
							//guardamos en un iterator los ps
						Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
						Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
						//validamos si la familia del ps es 300
						if(element.getFamiliaKey().faps_id.intValue() == 300)
						{
							tr518e.setProductServiceCode(element.getPsId().longValue());
						}
						}
				}
				
			
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				log.debug( "Cant de Servicios Suplementarios: " + psServiciosSuplementarios.size());	
				
				
				// Seteo RequestDate con la fecha de ingreso de la peticion en ATIS
				Peticion_atisLocalHome petAtisHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
				Peticion_atisLocal petAtis = petAtisHome.findByPrimaryKey(petAtisK);
				tr518e.setRequestDate(petAtis.getFec_sct_pet_ff()); 

				Recursos_linea_basicaLocal rlb = recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());

				if (rlb.getOds_apsc()!=null) {
					tr518e.setOdsNumber(rlb.getOds_apsc().intValue());
				}else {
					log.debug("No hay ODS para la Peticiòn." );
					//tr518e.setOdsNumber(0);
				}
				boolean esCruzada=false;
				 if( rlb.getCentral_connection()!=null){
				 	esCruzada = rlb.getCentral_connection().intValue()==1;
				}
				
				boolean esCambioNumero = tieneCambioNumeroLB(idPeticion);
				log.debug("esCruzada .."+esCruzada);
				log.debug("esTraslado .."+esTraslado);
				log.debug("esCambioNumero .."+esCambioNumero);
				log.debug("rlb.getTelefono_asg() .."+rlb.getTelefono_asg());
				log.debug("rlb.getTelefono_ant() .."+rlb.getTelefono_ant());
				
				
				String phoneNumber = peticionLocal.getNum_ide_nu_stb();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if(rlb.getTelefono_ant()!=null && esCambioNumero){
						phoneNumber= rlb.getTelefono_ant().toString();
					}else{
						if(esTrasladoBaja && rlb.getTelefono_ant()!=null){
							phoneNumber = rlb.getTelefono_ant().toString();
						}
					}
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}	
				tr518e.setPhoneNumber(Integer.parseInt(phoneNumber));
				if(cantPBX > 0){
					tr518e.setRequestPbx(cantPBX);//se setea la cantidad de PBX
					PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
					PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(act.getNumeroPeticion());
					InformacionPeticionDTO informacionPeticion = new InformacionPeticionDTO();
					
					String nroPiloto = peticionDTO.getNroPiloto();
					
					if(nroPiloto != null && !nroPiloto.trim().equals("")){
						try{
							Integer piloto = new Integer(nroPiloto);
							tr518e.setPreviousNumber(Long.parseLong(nroPiloto));//Se obtiene la lìnea Pliloto
						}catch(NumberFormatException nfe){
							log.error("Error al convertir a numero el piloto: " + nroPiloto);
						}
					}
					
				}	
			
					CentralLocalHome central = (CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);		
			    			
					
					/* String centralDescription */
					if(act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_STB"))
										|| act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESCONFIGURAR_AUTOMATICA"))){
						if(rlb != null && rlb.getCod_central() != null){
							CentralKey centralKey = new CentralKey(rlb.getCentral_ant());
							CentralLocal centralLocal = central.findByPrimaryKey(centralKey);
							if(centralLocal != null && centralLocal.getDesc_central() != null)
								tr518e.setCentralDescription(centralLocal.getDesc_central());
							else
								tr518e.setCentralDescription("");
						}else{
							tr518e.setCentralDescription("");
						}
					}else{
						if(rlb != null && rlb.getCod_central() != null){
							CentralKey centralKey = new CentralKey(rlb.getCod_central());
							CentralLocal centralLocal = central.findByPrimaryKey(centralKey);
							if(centralLocal != null && centralLocal.getDesc_central() != null)
								tr518e.setCentralDescription(centralLocal.getDesc_central());
							else
								tr518e.setCentralDescription("");
						}else{
							tr518e.setCentralDescription("");
						}
					}
					
					
				tr518e.setRemoteDescription(rlb.getDesc_central());	
				
				//cambio producto
				if(opComercialaux == 69 || opComercialaux == 70)
				{
				Peticion_atisLocal peticionAtisLocal = peticionLocal.getFk_01_pet_atis();
				Peticion_atisKey peticionAtisKey = (Peticion_atisKey)peticionAtisLocal.getPrimaryKey();
		    	Collection agrupacionAtisList = peticionAtisLocal.getAgrupacion_atis();
				for (Iterator iterAgrupacionAtis = agrupacionAtisList.iterator(); iterAgrupacionAtis.hasNext();){
					Agrupacion_atisLocal agrupacionAtisLocal = (Agrupacion_atisLocal) iterAgrupacionAtis.next();
					
					Collection subpeticionAtisList = agrupacionAtisLocal.getSubpeticion_atis();
					tr518e.setPreviousCategory(agrupacionAtisLocal.getTip_pro_cmr_cd().longValue());
					tr518e.setPreviousSubCategory(agrupacionAtisLocal.getSbt_pro_cmr_cd().longValue());
					
					}
				}
		
				if(rlb != null && rlb.getLen() != null){
					tr518e.setLen(rlb.getLen());
				}
					
				if(rlb != null && rlb.getDist_prim_asg() != null){
					tr518e.setPrimaryDistributor(rlb.getDist_prim_asg().toString());
				}
				
				LocalidadKey localidadKey= (LocalidadKey) peticionLocal.getFk_01_localidad().getPrimaryKey();
				tr518e.setCity(Integer.parseInt(localidadKey.cod_loc));
				
				
				if (rlb.getTelefono_asg() != null && rlb.getTelefono_ant() != null && esCambioNumero) {
					tr518e.setNewPhoneNumber(rlb.getTelefono_asg().longValue());
				}

				tr518e.setNumberClient(petAtis.getCod_cli_cd().longValue());
		    	tr518e.setId(IdCorrelativo.toString());
		    	tr518e.setAtiempoRequestNumber(numPeticion.longValue());
		    	tr518e.setAtisRequestNumber(petAtisK.cod_pet_cd.longValue());

				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);

				mensajeEstadoLineaLocal.setPeti_numero(act.getNumeroPeticion());

				mensajeEstadoLineaLocal.setF_reference_13(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))));
				mensajeEstadoLineaLocal.setFecha_envio(df.format (new java.util.Date ()));
				mensajeEstadoLineaLocal.setF_reference_14(mensajeEstadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEspera))));
				mensajeEstadoLineaLocal.setCod_actividad_generadora(act.getCodigoActividad());
				mq.enviarMensaje(tr518e);
					log.debug("Se setearon tr518e.setPhoneNumber .."+tr518e.getPhoneNumber());
					log.debug("Se setearon tr518e.setPreviousNumber .."+tr518e.getPreviousNumber());
					
			} catch (FinderException e) {
				// TODO Bloque catch generado automáticamente 
				log.debug("Error al buscar la petición en Recursos_linea_basica_Local:" + e);
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.debug("Error en Configuracion/Desconfiguracion EOC"+e);
				e.printStackTrace();
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}

			}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaConfigurarSTBMSAN(co.com.telefonica.atiempo.interfaces.atiempo.TR059S)
		 */
		public void respuestaConfiguracionAutomaticaEOC(TR518S tr518s) throws ATiempoAppEx {
			log.debug("respuesta Configuracion/Desconfiguracion Automatica EOC");			
			try{
				
				String codAct = "";
				Mensaje_estado_lineaKey key = new Mensaje_estado_lineaKey(Long.valueOf(tr518s.getId()));
				Mensaje_estado_lineaLocal mensajeEstadoLineaLocal ;

	
				try
				{
					//validamos si se setearon los datos de salida de la TR para saber el estado de la ptecion y la activadad
				 	Mensaje_estado_lineaLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_lineaLocalHome) HomeFactory.getHome( Mensaje_estado_lineaLocalHome.JNDI_NAME);
					mensajeEstadoLineaLocal = (Mensaje_estado_lineaLocal) msgEstadoLineaLocalHome.findByPrimaryKey(key);
					codAct = mensajeEstadoLineaLocal.getCod_actividad_generadora();
					
//					setemoass las tablas y campso para continuar los flujos y activadades
					ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codAct);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), codAct);
					

					//seteamos la activadad generadora
					codAct=mensajeEstadoLineaLocal.getCod_actividad_generadora();
					
					boolean hayErrorServicioSuplementario=false;
					String descripErrores="";
					if ( tr518s.getSpecialServicesType()!=null){
						for (Iterator iter = tr518s.getSpecialServicesType().iterator(); iter.hasNext();)
						{
							SpecialServicesResponse specialService = (SpecialServicesResponse) iter.next();
						
							if(specialService.getActionResultNumber()!=0){
								String desc=specialService.getActionResultDescription();
								descripErrores+=desc;
								hayErrorServicioSuplementario=true;
								log.debug("Hay errores en PS: " + tr518s.getSpecialServicesType());
							}
								if(iter.hasNext())
									descripErrores+=",";
						}
					}
					
					
					if (tr518s.getErrorCode() > 0 || hayErrorServicioSuplementario){
						log.debug("Respuesta de EOC Con error");
						PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
						PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(mensajeEstadoLineaLocal.getPeti_numero()));
						for(Iterator iterator=petLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();)
						{
							Producto_servicio_peticionLocal psp=(Producto_servicio_peticionLocal) iterator.next();
							Operacion_comercialLocal opco=psp.getOperacion_comercial();
							
							if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && (opco.getOpco_tras()==null) )//ALTA
								petLocal.setTica_id("A");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("R"))//TRANSFERENCIA TECNICA
								petLocal.setTica_id("T");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("A") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//REINSTALACION
								petLocal.setTica_id("P");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("R"))//RECONEXION
								petLocal.setTica_id("R");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("S"))//SUSPENSION
								petLocal.setTica_id("S");
							else if(opco.getOpco_tipo()!=null && opco.getOpco_tipo().equals("B") && opco.getOpco_tras()!=null && opco.getOpco_tras().equals("N"))//RETIROS
								petLocal.setTica_id("B");
							else 
								petLocal.setTica_id("P");
						}
						
						
						mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						mensajeEstadoLineaLocal.setCod_actividad_generadora(mensajeEstadoLineaLocal.getCod_actividad_generadora());
						mensajeEstadoLineaLocal.setDesc_error(tr518s.getErrorMessage());
				
						if(tr518s.getErrorCode() > 0 && hayErrorServicioSuplementario){
							
							actDto.setObservacion("Error en la Configuracion/Desconfiguracion Automatica de EOC. " + tr518s.getErrorMessage()+"  "+  descripErrores + ". ");
						}else{
							if(hayErrorServicioSuplementario){
							
								actDto.setObservacion("Error en la Configuracion/Desconfiguracion Automatica de EOC. " + descripErrores + ". ");
							}else{
								actDto.setObservacion("Error en la Configuracion/Desconfiguracion Automatica de EOC. " + tr518s.getErrorMessage()+". ");
							
							}
						}
												
						//actDto.setGrabaEnBitacora(false); // creo que a este seteo no se le da corte, pero la idea es que no imprima en bitacora si no es granite
						// Si la peticion no es de Granite termina y va  aconf manual sin grabar en bitacora
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
						log.debug("Se Deriva A Gestion De Abonados");
						actDto.setObservacion("Se deriva a gestion Abonados");//s
						try
						{						
							Bitacora_peticionLocalHome bitacora_peticionLocalHome=(Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
							//se debe parametrizar el 1600 por el acti id de la peticion 
							Bitacora_peticionLocal bi=bitacora_peticionLocalHome.findByPeticionActividad(actDto.getNumeroPeticion(),new Long(1600));
							bi.setBipe_fecha_fin(new Fecha().getTimestamp());
							bi.setBipe_observacion(tr518s.getErrorMessage());
							actDto.setObservacion("Se deriva a gestion Abonados");
						}
						catch(FinderException fe)
						{
						    log.debug("No se encontro el registro en bitacora. Nro peticion : "+ actDto.getNumeroPeticion()+" y idAct: "+1600+"  "+ fe);						
						}
						actDto.setRealizarTerminoInmediato(true);	
						actDto.setGrabaEnBitacora(true);
						actividadEJB.terminar(actDto);	
						mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						return;
						
					}else{
						log.debug("Respuesta de EOC OK");
						actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_asig_rec_stb, "N");
						actDto.setObservacion(".");
						actividadEJB.terminar(actDto);
						mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
						log.debug("Se termino activida Configuracion/Desconfiguracion automatica EOC");
						return;
					}
					
					}
				catch (FinderException fex)
					{
					log.debug("No Existe Respuesta en la base de enviados\n "+ XMLUtilities.marshall(tr518s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr518s.getId(),ATiempoAppEx.EXCEPTION);
					}
					
		}catch (Exception e) {
			// TODO: handle exception
			log.debug("Excepticon EOC "+e);
		}
			}
}