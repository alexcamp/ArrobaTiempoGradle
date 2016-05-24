package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.CameraRequest;
import co.com.telefonica.atiempo.interfaces.atiempo.CameraResponse;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.interfaces.atiempo.SpecialService;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR021E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR021S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR516E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR516S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719S;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.dto.DslamApscLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.ZonasAtendimientoDTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierre_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Dslam_apsc_lineaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Dslam_apsc_lineaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Dslam_apsc_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Servicio_basico_supl_apsc_lineaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Servicio_basico_supl_apsc_lineaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Zonas_atendimientoLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.MarcaLineaDefectuosaSTBGRMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.MarcaLineaDefectuosaSTBMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.MarcaLineaEnServicioSTBMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.PresenciaDigitalSTMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudPuntosRedSTBGRMQ;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.SolicitudPuntosRedSTBMQ;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.soltec.servicios.EnviarRefrecarDatosMQ;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.atiempo.BackendExecution;
import com.telefonica_chile.atiempo.trace.atiempo.BackendTraceUtil;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.comun.ComunInterfaces;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 19, 2009
/**
 * Bean implementation class for Enterprise Bean: RecursosServicios
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class RecursosServiciosBean
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
	implements RecursosInterfaces, SessionBean {

		protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
//		private Mensaje_estado_stLocalHome msgEstadoLineaLocalHome ;
//		private Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome;
//		private Peticion_stLocalHome peticionLocalHome ;
//		
//		private Dslam_apsc_lineaLocalHome dslam_apscLocalHome;
//		private Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome;
//		private ConectorLocalHome conectorLocalHome;
//		private LocalidadLocalHome localidadLocalHome;
//		private DepartamentoLocalHome departamentoLocalHome;
//		private CentralLocalHome centralLocalHome;
//		private Mensaje_estadoLocalHome mensaje_estadoLocalHome;
//		private Zonas_atendimientoLocalHome zonas_atendimientoLocalHome;
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
//			try {
//				
//			//	Creacion de los Home
//			msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
//			recursos_linea_basicaLocalHome = 	(Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
//			msgEstadoLineaLocalHome=(Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
//			peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
//
//			dslam_apscLocalHome = (Dslam_apsc_lineaLocalHome) HomeFactory.getHome(Dslam_apsc_lineaLocalHome.JNDI_NAME);
//			servicio_basico_supl_apsc_lineaLocalHome = (Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
//			conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
//			mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
//
//			localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
//			departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
//			centralLocalHome = (CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
//			zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);		
//			
//			} catch (NamingException e) {
//				log.error(" Creacion de Local Home Nulls",e);
//			}
			}
    
		/*
		 * Metodo validador Home
		 */
//		private void validaHome ()
//		throws ATiempoAppEx
//		{
//			// Validacion de los Home
//			if (
//				msgEstadoLineaLocalHome == null || recursos_linea_basicaLocalHome == null || 
//				mensaje_estadoLocalHome == null
//				||	peticionLocalHome == null 
//				|| localidadLocalHome == null 
//				|| departamentoLocalHome == null 
//				|| conectorLocalHome == null 
//				)
//				throw new ATiempoAppEx (ATiempoAppEx.NAMING);
//		}
    
		/**
		 * @param string
		 * @return
		 */
//		public boolean validadorInBoolean(String pss, String idFlujo) {
//		
//					PreparedStatement pstmt=null;
//					ResultSet rs=null;
//					Connection con = null;
//					int rowscount = 0;
//					try{
//						con = dbSeq.getConn();
//						pstmt= con.prepareStatement("SELECT * FROM VPISTBBA.FLUJO_PROD_SERV_OPER_COM FP, VPISTBBA.FLUJO_DEFINICION FD"+
//						"WHERE PRSE_ID IN ("+pss+")AND FP.FLUJ_ID=FD.FLUJ_ID AND ACTI_ID="+ idFlujo);
//						rs=pstmt.executeQuery();
//						while(rs.next()){
//							rowscount++;
//						}
//
//					}catch (Exception exc) {
//					
//						log.warn("Problema ejecutando la Consulta " + "SELECT * FROM VPISTBBA.FLUJO_PROD_SERV_OPER_COM FP, VPISTBBA.FLUJO_DEFINICION FD"+
//						"WHERE PRSE_ID IN ("+pss+")AND FP.FLUJ_ID=FD.FLUJ_ID AND ACTI_ID="+ idFlujo, exc);
//						if (exc instanceof SQLException)
//							throw new DBException((SQLException) exc);
//						else
//							throw new DBException(exc.getMessage());
//					} finally {
//					try { if (rs != null) rs.close(); } catch (Exception e) { }
//					try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
//					try { if (con != null && !con.isClosed()) dbSeq.close(); } catch (Exception e) { }
//					}
//				
//				if (rowscount > 1){
//					return false;
//				}
//				
//				return true;
//		}
		
	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx{

			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try{	

				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);

			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
			
			log.debug("Id de Mensaje de STB " + tr012s.getId());
			Mensaje_estado_stKey key = new Mensaje_estado_stKey(Long.valueOf(tr012s.getId()));
			Mensaje_estado_stLocal mensajeEstadoLineaLocal ;
	            
		   try {
					Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
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
						+ XMLUtilities.marshall(tr012s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr012s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			//Validacion del estado de la respuesta 
			if (mensajeEstadoLineaLocal.getCod_estado().intValue() == estadoOk ) {
				log.warn(
					"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr012s));
				return;
			}
			
			Peticion_stLocal petST = mensajeEstadoLineaLocal.getPeticion_st();
			
			if(tr012s.isError() != true){
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
				Mensaje_estado_stLocal mensajeEstadoLineaLocalFor = null;
				boolean insert = false;
				Collection recursosLineaBasica = petST.getRecursos_linea_basica();
				log.debug("Existen Recursos " + recursosLineaBasica.size());
				if ( recursosLineaBasica.size() == 0){
					insert = true;
				}else{
					log.debug("Actualiza Recursos STB");
					insert = false;
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}
				if (insert){
					log.debug("Inserta Recursos STB");
					Long idConDos =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos,petST);
				}


//				recursos_linea_basicaLocal.setMensaje_estado_st(mensajeEstadoLineaLocal);
				
				recursos_linea_basicaLocal.setArmario(tr012s.getCloset());
				try {
					recursos_linea_basicaLocal.setCable(new Long(tr012s.getInCable()));
				} catch (NumberFormatException e)
				{
				}
				recursos_linea_basicaLocal.setCaja(tr012s.getBox());
				try
				{
						CentralLocalHome centralLocalHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
					log.debug("Actualizo la central en Recursos.");
					CentralKey centralKey=new CentralKey(new Long(tr012s.getPhoneCentral()));
					CentralLocal centralLocal=centralLocalHome.findByPrimaryKey(centralKey);
					recursos_linea_basicaLocal.setCentral(centralKey.cod_central);
					Peticion_stLocal petLocal=mensajeEstadoLineaLocal.getPeticion_st();  
					petLocal.setCod_central(centralKey.cod_central);
					//Se actualiza la central en la bandeja si es que hay alguna publicacion.
					BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
					BIntegradaSessionLocal biLocal = biHome.create();
					log.debug("Actualizo la central en la bandeja.");
					biLocal.cambiarCentralPeticion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID)),((Peticion_stKey)petLocal.getPrimaryKey()).cod_ave_cd,centralLocal);
					
				}
				catch(Exception fe)
				{
					log.debug("No se pudo actualizar el valor de la central");
				}
				recursos_linea_basicaLocal.setDesc_central(String.valueOf(tr012s.getPhoneCentralDescription()));
				recursos_linea_basicaLocal.setDesc_dist_prim(tr012s.getPrimaryDistributorDescription());
				recursos_linea_basicaLocal.setDesc_distribuidor_sec(tr012s.getSecondaryDistributorDescription());
				recursos_linea_basicaLocal.setDist_prim(new Long(tr012s.getPrimaryDistributor()));
				recursos_linea_basicaLocal.setDistribuidor_sec(new Long(tr012s.getSecondaryDistributor()));
				recursos_linea_basicaLocal.setLen(tr012s.getLen());
				recursos_linea_basicaLocal.setListon(tr012s.getStrip());
				recursos_linea_basicaLocal.setPar_liston(new Long(tr012s.getPairCobreStrip()));
				recursos_linea_basicaLocal.setTelefono_asignado(new Long(tr012s.getPhoneNumber()));
				recursos_linea_basicaLocal.setPar_cable(new Long(tr012s.getPairCable()));
				recursos_linea_basicaLocal.setDir_armario(tr012s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_distribuidor(tr012s.getDistributorAddress());
				recursos_linea_basicaLocal.setDir_caja(tr012s.getBoxAddress());
				recursos_linea_basicaLocal.setPar_caja(new Long(tr012s.getPairCobreBox()));
				recursos_linea_basicaLocal.setPosicion_horizontal(tr012s.getHorizontalPosition());
				
				if (tr012s.getDslams() != null){
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")|| mensajeEstadoLineaLocal.getAccion().equals("F")))
					{
						log.debug("Tengo que borrar los Dyslam que tenia antes");
						borrarDslams(recursos_linea_basicaLocal);
						borrarZonas(recursos_linea_basicaLocal);
						
					}
						Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					
					for (Iterator iter = tr012s.getDslams().iterator(); iter.hasNext();){
						// TCS - sigres
						
						/* 
						Long idDslam =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						Dslam_apsc_lineaLocal dslam_apscLocal = dslam_apscLocalHome.create(idDslam);
						dslam_apscLocal.setIp(dslam1.getIp());
						dslam_apscLocal.setTipo_dslam(String.valueOf(dslam1.getDslamType()));
						dslam_apscLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						*/
						log.debug("SIGRES: por agregar "+tr012s.getDslams().size()+" zonas de atendimiento");
						Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						Zonas_atendimientoLocal zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
						zona_atendimientoLocal.setIp(dslam1.getIp());
						zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						
						// fin sigres												
					}
				}
				if (tr012s.getServices() != null){
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")|| mensajeEstadoLineaLocal.getAccion().equals("F")))
					{
						log.debug("Tengo que borrar los SS que tenia antes");
						borrarSS(recursos_linea_basicaLocal);
					}
						Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome = (Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
					
					for (Iterator iter = tr012s.getServices().iterator(); iter.hasNext();){
						Long idService =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						String servicio = (String) iter.next();
						Servicio_basico_supl_apsc_lineaLocal servicio_basico_supl_apsc_lineaLocal = 
										servicio_basico_supl_apsc_lineaLocalHome.create(idService);
						servicio_basico_supl_apsc_lineaLocal.setCodigo_ps(servicio);
						servicio_basico_supl_apsc_lineaLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
					
				}
			
				mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				
				if(mensajeEstadoLineaLocal.getAccion() != null && mensajeEstadoLineaLocal.getAccion().equals("F"))
					enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
				
				log.debug("Acción Mensaje Estado ST: "+mensajeEstadoLineaLocal.getAccion());
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")||mensajeEstadoLineaLocal.getAccion().equals("F")))
					return;
					
				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				//TODO q hace esto?
//				actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_obt_ptos_red, "N");
				actividadEJB.terminar(actDto);
				
			}else{
				//@idrincon req 5935 ajuste flujo it apcsc
				if(tr012s.getTypeError() == ComunInterfaces.TIMEOUT_ERROR ){
					log.info("Se deriva a solucion recursos st, por error de time out");
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
					actDto.setObservacion("Error al consultar puntos de red tr012s "+tr012s.getErrorMessage());
					//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
					//actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
					//solucion_rec_st
					//actividadEJB.terminar(actDto);
					
					//METODO QUE ENVIA A PGI LA AVERIA
					hayPGIAveria("0001",tr012s.getErrorMessage(),"TR012S",mensajeEstadoLineaLocal.getPeti_numero().toString(),mensajeEstadoLineaLocal.getCod_actividad_generadora()  ,mensajeEstadoLineaLocal);
					return;
					//FIN REQ TOA FASE III 
				}else{
				//fin
					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
					mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")|| mensajeEstadoLineaLocal.getAccion().equals("F"))){
						if(mensajeEstadoLineaLocal.getAccion().equals("F")){
							enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
						}
						return;	
					}
									
//					//Por ahora cierra igual que un caso positivo				
//					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
//					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
//					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
//					actDto.setObservacion("Error en la Obtencion de Recursos STB. " + tr012s.getErrorMessage());
//					//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC
//					//actDto.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "18");
//					actividadEJB.terminar(actDto);
					
//					METODO QUE ENVIA A PGI LA AVERIA
					hayPGIAveria(Long.toString(tr012s.getTypeError()),tr012s.getErrorMessage(),"TR012S",mensajeEstadoLineaLocal.getPeti_numero().toString(),mensajeEstadoLineaLocal.getCod_actividad_generadora()  ,mensajeEstadoLineaLocal);
					return;
					//FIN REQ TOA FASE III 
				}
			}
		
		} catch (NumberFormatException e) {
			log.error("NumberFormatException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
		} catch (FinderException e) {
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER);
		} catch (CreateException e) {
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE);
		} catch (TnProcesoExcepcion e) {
			log.error("TnProcesoExcepcion:",e);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS);
		} catch (Exception e) {
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
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
		} catch (NamingException e)
		{
			e.printStackTrace();	
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}catch(Exception e)
		{
			log.error("",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
		}
	}
	
	public void consultaPuntosRedSTBGR(TR514S tr514s) throws ATiempoAppEx{

		// @Trace - ana santos - 20 Nov - Inicio 
		BackendExecution bExecution = null;
		
        try
        {
			bExecution = BackendTraceUtil.initExecution(tr514s, log);			
			bExecution.setIdMensajeOp(tr514s.getId());			
			bExecution.startOperation();
	
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);

			Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
			Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
			Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
		
			log.debug("Id de Mensaje de STB " + tr514s.getId());
			Mensaje_estado_stKey key = new Mensaje_estado_stKey(Long.valueOf(tr514s.getId()));
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
						+ XMLUtilities.marshall(tr514s));
				throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr514s.getId(),ATiempoAppEx.EXCEPTION);
			}
			
			//Validacion del estado de la respuesta 
			if (mensajeEstadoLineaLocal.getCod_estado().intValue() == estadoOk ) {
				log.warn(
					"El estado de la respuesta es diferente para ser procesado\n "
						+ XMLUtilities.marshall(tr514s));
				return;
			}
			
			Peticion_stLocal petST = mensajeEstadoLineaLocal.getPeticion_st();
			
			if(tr514s.getErrorCode() == 0){
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
				Mensaje_estado_stLocal mensajeEstadoLineaLocalFor = null;
				boolean insert = false;
				Collection recursosLineaBasica = petST.getRecursos_linea_basica();
				log.debug("Existen Recursos " + recursosLineaBasica.size());
				if ( recursosLineaBasica.size() == 0){
					insert = true;
				}else{
					log.debug("Actualiza Recursos STB");
					insert = false;
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursosLineaBasica.iterator().next();
				}
				if (insert){
					log.debug("Inserta Recursos STB");
					Long idConDos =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
					Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
					recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.create(idConDos,petST);
				}
	
	
	//			recursos_linea_basicaLocal.setMensaje_estado_st(mensajeEstadoLineaLocal);
				
				recursos_linea_basicaLocal.setArmario(tr514s.getCloset());
				try {
					//recursos_linea_basicaLocal.setCable(new Long(tr514s.getInCable()));
					recursos_linea_basicaLocal.setCable(new Long(tr514s.getCable()));
				} catch (NumberFormatException e)
				{
				}
				recursos_linea_basicaLocal.setCaja(tr514s.getBox());
				try
				{
					CentralLocalHome centralLocalHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
					log.debug("Actualizo la central en Recursos.");
					//CentralKey centralKey=new CentralKey(new Long(tr514s.getPhoneCentral()));
					CentralKey centralKey=new CentralKey(new Long(tr514s.getCentral()));
					CentralLocal centralLocal=centralLocalHome.findByPrimaryKey(centralKey);
					recursos_linea_basicaLocal.setCentral(centralKey.cod_central);
					Peticion_stLocal petLocal=mensajeEstadoLineaLocal.getPeticion_st();  
					petLocal.setCod_central(centralKey.cod_central);
					//Se actualiza la central en la bandeja si es que hay alguna publicacion.
					BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
					BIntegradaSessionLocal biLocal = biHome.create();
					log.debug("Actualizo la central en la bandeja.");
					biLocal.cambiarCentralPeticion(new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID)),((Peticion_stKey)petLocal.getPrimaryKey()).cod_ave_cd,centralLocal);
					
				}catch (NamingException e)
				{
					log.error(" Creacion de Local Home Nulls",e);
				}
				catch(Exception fe)
				{
					log.debug("No se pudo actualizar el valor de la central");
				}
				//recursos_linea_basicaLocal.setDesc_central(String.valueOf(tr514s.getPhoneCentralDescription()));
				recursos_linea_basicaLocal.setDesc_central(String.valueOf(tr514s.getCentralDescription()));
				recursos_linea_basicaLocal.setDesc_dist_prim(tr514s.getPrimaryDistributorDescription());
				//recursos_linea_basicaLocal.setDesc_distribuidor_sec(tr514s.getSecondaryDistributorDescription());
				recursos_linea_basicaLocal.setDesc_distribuidor_sec(tr514s.getDistributorDescription());
				recursos_linea_basicaLocal.setDist_prim(new Long(tr514s.getPrimaryDistributor()));
				//recursos_linea_basicaLocal.setDistribuidor_sec(new Long(tr514s.getSecondaryDistributor()));	GERMAN
				recursos_linea_basicaLocal.setDistribuidor_sec(new Long(tr514s.getDistributor()));	
				recursos_linea_basicaLocal.setLen(tr514s.getLen());
				recursos_linea_basicaLocal.setListon(tr514s.getStrip());
				//recursos_linea_basicaLocal.setPar_liston(new Long(tr514s.getPairCobreStrip()));
				recursos_linea_basicaLocal.setPar_liston(new Long(tr514s.getStripPair()));
				recursos_linea_basicaLocal.setTelefono_asignado(new Long(tr514s.getPhoneNumber()));
				//recursos_linea_basicaLocal.setPar_cable(new Long(tr514s.getPairCable()));
				recursos_linea_basicaLocal.setPar_cable(new Long(tr514s.getCablePair()));
				recursos_linea_basicaLocal.setDir_armario(tr514s.getClosetAddress());
				recursos_linea_basicaLocal.setDir_distribuidor(tr514s.getDistributorAddress());
				recursos_linea_basicaLocal.setDir_caja(tr514s.getBoxAddress());
				//recursos_linea_basicaLocal.setPar_caja(new Long(tr514s.getPairCobreBox()));
				recursos_linea_basicaLocal.setPar_caja(new Long(tr514s.getBoxPair()));
				recursos_linea_basicaLocal.setPosicion_horizontal(tr514s.getHorizontalPosition());
				
//				Req. 13146 - Jesus Carvajal - 27/03/2012	
				petST.setCoordenada_x(tr514s.getLongitude().toString());
				petST.setCoordenada_y(tr514s.getLatitude().toString());
				log.debug("Actualiza latitud y longitud en la tabla peticion_st");
				//Fin Req. 13146
				
				if (tr514s.getDslams() != null){
					
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("R")|| mensajeEstadoLineaLocal.getAccion().equals("F")))
					{
						log.debug("Tengo que borrar los Dyslam que tenia antes");
						borrarDslams(recursos_linea_basicaLocal);
					}
					Zonas_atendimientoLocalHome zonas_atendimientoLocalHome = (Zonas_atendimientoLocalHome)HomeFactory.getHome(Zonas_atendimientoLocalHome.JNDI_NAME);
					for (Iterator iter = tr514s.getDslams().iterator(); iter.hasNext();){
						// TCS - sigres
						
						/* 
						Long idDslam =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						Dslam_apsc_lineaLocal dslam_apscLocal = dslam_apscLocalHome.create(idDslam);
						dslam_apscLocal.setIp(dslam1.getIp());
						dslam_apscLocal.setTipo_dslam(String.valueOf(dslam1.getDslamType()));
						dslam_apscLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						*/
						log.debug("SIGRES: por agregar "+tr514s.getDslams().size()+" zonas de atendimiento");
						Long idZonaAtendimiento =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						Dslam1 dslam1 = (Dslam1) iter.next();
						Zonas_atendimientoLocal zona_atendimientoLocal = zonas_atendimientoLocalHome.create(idZonaAtendimiento);
						zona_atendimientoLocal.setIp(dslam1.getIp());
						zona_atendimientoLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
						
						// fin sigres												
					}
				}
				//if (tr514s.getServices() != null){
				if (tr514s.getSpecialServices() != null){
					
					if(mensajeEstadoLineaLocal.getAccion() != null && mensajeEstadoLineaLocal.getAccion().equals("F"))
						enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
					if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("F") ||mensajeEstadoLineaLocal.getAccion().equals("R")))
					{
						log.debug("Tengo que borrar los SS que tenia antes");
						borrarSS(recursos_linea_basicaLocal);
					}
					Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome = (Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
					//for (Iterator iter = tr012s.getServices().iterator(); iter.hasNext();){
					for (Iterator iter = tr514s.getSpecialServices().iterator(); iter.hasNext();){	
						Long idService =	new Long(dbSeq.seqNextValLong("CORRELATIVO_APSC"));
						SpecialService s = (SpecialService) iter.next();
						String servicio = s.getSpecialService();
						//String servicio = (String) iter.next();
						Servicio_basico_supl_apsc_lineaLocal servicio_basico_supl_apsc_lineaLocal = 
										servicio_basico_supl_apsc_lineaLocalHome.create(idService);
						servicio_basico_supl_apsc_lineaLocal.setCodigo_ps(servicio);
						servicio_basico_supl_apsc_lineaLocal.setRecursos_linea_basica(recursos_linea_basicaLocal);
					}
					
				}
			
				mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
				
				if(mensajeEstadoLineaLocal.getAccion() != null && mensajeEstadoLineaLocal.getAccion().equals("F"))
					enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("F") ||mensajeEstadoLineaLocal.getAccion().equals("R"))){
					if(mensajeEstadoLineaLocal.getAccion().equals("R")){	
						if (mensajeEstadoLineaLocal.getCod_actividad_generadora().equals(ComunInterfaces.PLANTA_EXTERNA_TOA)) {
								log.debug("La actividad corresponde a TOA");
								ServicioTOASTDelegate toaDelegate = new ServicioTOASTDelegate();
								toaDelegate.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
							} else {
								enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
							}
							return;
							}
				}
				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoLineaLocal.getCod_actividad_generadora());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), mensajeEstadoLineaLocal.getCod_actividad_generadora());
				//TODO q hace esto?
	//			actividadEJB.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_obt_ptos_red, "N");
				actividadEJB.terminar(actDto);
				
			}else{
			
				//REQ TOA FASE III Dcardena
				//Logica para derivar a la nueva bandeja PGI averias
				mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
				mensajeEstadoLineaLocal.setFecha_cierre(df.format (new java.util.Date ()));
					
				if(mensajeEstadoLineaLocal.getAccion() != null && mensajeEstadoLineaLocal.getAccion().equals("F"))
					enviarRefrecarDatos(mensajeEstadoLineaLocal.getPeti_numero());
				if(mensajeEstadoLineaLocal.getAccion()!=null && (mensajeEstadoLineaLocal.getAccion().equals("F") ||mensajeEstadoLineaLocal.getAccion().equals("R")))
					return;	
				
				//METODO QUE ENVIA A PGI LA AVERIA
				hayPGIAveria(Integer.toString(tr514s.getErrorCode()),tr514s.getErrorMessage(),"TR514S",mensajeEstadoLineaLocal.getPeti_numero().toString(),mensajeEstadoLineaLocal.getCod_actividad_generadora()  ,mensajeEstadoLineaLocal);
				return;
				//FIN REQ TOA FASE III 
			}
	
	} catch (NumberFormatException e) {
		bExecution.setErrorOp(true);
		log.error("NumberFormatException:",e);
		throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
	} catch (FinderException e) {
		bExecution.setErrorOp(true);
		log.error("FinderException:",e);
		throw new ATiempoAppEx(ATiempoAppEx.FINDER);
	} catch (CreateException e) {
		bExecution.setErrorOp(true);
		log.error("CreateException:",e);
		throw new ATiempoAppEx(ATiempoAppEx.CREATE);
	} catch (TnProcesoExcepcion e) {
		bExecution.setErrorOp(true);
		log.error("TnProcesoExcepcion:",e);
		throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS);
	} catch (Exception e) {
		bExecution.setErrorOp(true);
		log.error("Exception:",e);
		throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
	} finally{  
		bExecution.endAll();
		// @Trace - ana santos - 20 Nov - FIN
	}


}
	
	/**
	 * @param recursos_linea_basicaLocal
	 */
	private void borrarDslams(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx
	{
		Recursos_linea_basicaKey key=(Recursos_linea_basicaKey) recursos_linea_basicaLocal.getPrimaryKey();
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try
			{
			Dslam_apsc_lineaLocalHome dslam_apscLocalHome=(Dslam_apsc_lineaLocalHome) HomeFactory.getHome(Dslam_apsc_lineaLocalHome.JNDI_NAME);
			Collection listaDslams= dslam_apscLocalHome.findConector(key.id_conector);
			for(Iterator iterator=listaDslams.iterator();iterator.hasNext();)
			{
				Dslam_apsc_lineaLocal dslam_apsc_lineaLocal =(Dslam_apsc_lineaLocal) iterator.next();
				dslam_apsc_lineaLocal.remove();
			}
		}catch (NamingException e)
		{
			log.error("NamingException:",e);	
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		} catch (EJBException e)
		{
			log.error("EJBException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		} catch (RemoveException e)
		{
			log.error("RemoveException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		} catch (Exception e) {
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}
	
	private void borrarSS(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx
	{
		Recursos_linea_basicaKey key=(Recursos_linea_basicaKey) recursos_linea_basicaLocal.getPrimaryKey();
		
			try
			{
			Servicio_basico_supl_apsc_lineaLocalHome servicio_basico_supl_apsc_lineaLocalHome=(Servicio_basico_supl_apsc_lineaLocalHome) HomeFactory.getHome(Servicio_basico_supl_apsc_lineaLocalHome.JNDI_NAME);
			Collection listaSS= servicio_basico_supl_apsc_lineaLocalHome.findConector(key.id_conector);
			for(Iterator iterator=listaSS.iterator();iterator.hasNext();)
			{
				Servicio_basico_supl_apsc_lineaLocal servicio_basico_supl_apsc_lineaLocal=(Servicio_basico_supl_apsc_lineaLocal) iterator.next();
				servicio_basico_supl_apsc_lineaLocal.remove();
			}
		}catch (NamingException e)
		{
			log.error("NamingException",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			log.debug("FinderException",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		} catch (EJBException e)
		{
			log.debug("EJBException",e);
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
		} catch (RemoveException e)
		{
			log.debug("RemoveException",e);
			throw new ATiempoAppEx(ATiempoAppEx.REMOVEEXCEPTION,e);
		} catch (Exception e) {
			log.debug("Exception",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
		}
	}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.solicitud.SolicitudPuntosRedInterfaces#envioPuntosRedSTB(java.lang.String, java.lang.String)
		 */
		public void envioPuntosRedSTB(String peticion, String idActividad, String codActividad) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			try{

				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				String tmpIdAct ="";
				boolean esRefrescar=false;
				if(idActividad.equals("")||idActividad.equals("F")||idActividad.equals("RF"))
				{
					esRefrescar=true;
					tmpIdAct = idActividad;
					
				}
				
				log.debug("refrescando recursos?"+ esRefrescar);
				if(codActividad != null){
					idActividad = codActividad;
				}else{
					idActividad=idActividad=STConfig.getVariable("COD_ACTIVIDAD_REC_STB_AUTOMATICA");
				}
				
				
				TR012E tr012e = new TR012E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(new Long(peticion)));
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
	
				//TODO: Se debe realizar una Validacion de los PS
			
				//Operacion_comercial_stKey operacion_comercialKey = (Operacion_comercial_stKey) producto_servicio_peticionstLocal.getOperacion_comercial_st().getPrimaryKey();
				tr012e.setId(IdCorrelativo.toString());
				//TODO: nuevo campo , revisar .
				try
				{
					//TODO Analizar el tema de base de datos de STB y TV
					String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
					
					tr012e.setPhoneNumber(new Integer(phoneNumber).intValue());
				}
				catch(NumberFormatException e)
				{
					log.warn("No Hay PhoneNumber Valido en la peticion."+peticionLocal.getNum_ide_nu());
				}
				
				DepartamentoKey departamentoKey = (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticionLocal.getCod_dpt())).getPrimaryKey();
				tr012e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());
			
				LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
				tr012e.setCity(new Integer(localidadKey.cod_loc).intValue());
			
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLocal.setPeticion_st(peticionLocal);

				Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));

				ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))).getPrimaryKey();
				mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
				
				if(esRefrescar){
					if(tmpIdAct.equals("F")){
						mensajeEstadoLocal.setAccion(tmpIdAct);
					}else
						mensajeEstadoLocal.setAccion("R");
				}
					
							
				mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				//TODO: Revisar luego estos campos y su mapeo.
				if(peticionLocal.getCod_are_ges_cd()!=null)
					mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				if(peticionLocal.getTel_cot_ds()!=null)
					mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
			
				SolicitudPuntosRedSTBMQ solicitudPuntosRedSTBMQ = new SolicitudPuntosRedSTBMQ();
				solicitudPuntosRedSTBMQ.enviarMensajeReplyToQ(tr012e);
				
			} catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		}
		
		public void envioPuntosRedSTBGR(String peticion, String idActividad, String codActividad) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			try{

				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				String tmpIdAct = "";
				boolean esRefrescar=false;
				if(idActividad.equals("")||idActividad.equals("RF"))
				{
					esRefrescar=true;
					tmpIdAct = idActividad;
					
				}
				
				log.debug("refrescando recursos?"+ esRefrescar);
				if(codActividad != null){
					idActividad = codActividad;
				}else{
					idActividad=idActividad=STConfig.getVariable("COD_ACTIVIDAD_REC_STB_AUTOMATICA");
				}
				
				TR514E tr514e = new TR514E();
				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(new Long(peticion)));
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
	
				//TODO: Se debe realizar una Validacion de los PS
			
				//Operacion_comercial_stKey operacion_comercialKey = (Operacion_comercial_stKey) producto_servicio_peticionstLocal.getOperacion_comercial_st().getPrimaryKey();
				 tr514e.setId(IdCorrelativo.toString());
				//TODO: nuevo campo , revisar .
				try
				{
					//TODO Analizar el tema de base de datos de STB y TV
					String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
					
					tr514e.setPhoneNumber(new Integer(phoneNumber).intValue());
				}
				catch(NumberFormatException e)
				{
					log.warn("No Hay PhoneNumber Valido en la peticion."+peticionLocal.getNum_ide_nu());
				}
				
				DepartamentoKey departamentoKey = (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticionLocal.getCod_dpt())).getPrimaryKey();
				tr514e.setDepartment(new Integer(departamentoKey.cod_dpt).intValue());
			
				LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
				tr514e.setCity(new Integer(localidadKey.cod_loc).intValue());
				tr514e.setAtiempoRequestNumber(new Long(peticion));
				tr514e.setSubCity("");
				tr514e.setRequestDate(new java.util.Date ());
				
				// Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				mensajeEstadoLocal.setPeticion_st(peticionLocal);

				Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));

				ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorSeis))).getPrimaryKey();
				mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
				
				if(esRefrescar){
					if(tmpIdAct.equals("F"))
						mensajeEstadoLocal.setAccion("F");
					else
						mensajeEstadoLocal.setAccion("R");
				}
					
							
				mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				//TODO: Revisar luego estos campos y su mapeo.
				if(peticionLocal.getCod_are_ges_cd()!=null)
					mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				if(peticionLocal.getTel_cot_ds()!=null)
					mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
			
				SolicitudPuntosRedSTBGRMQ solicitudPuntosRedSTBGRMQ = new SolicitudPuntosRedSTBGRMQ();
				solicitudPuntosRedSTBGRMQ.enviarMensajeReplyToQ(tr514e);
				
			} catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		}

		/* (no Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#envioMarcaLineaDefectuosa(java.lang.String, java.lang.String)
		 */
		public void envioMarcaLineaDefectuosa(String peticion, String idActividad) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try{
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
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

				//TODO: Se debe realizar una Validacion de los PS
				/*TR009E tr009e = new TR009E(); 
				tr009e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
				tr009e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr009e.setState(2);
				tr009e.setCodIncAtis(new Long(peticionLocal.getCod_apt_ave_cd()).longValue());*/
				
				
				TR021E tr021e = new TR021E(); 
				tr021e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
				tr021e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr021e.setStatus(3);//Defectuoso
				tr021e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
				
				
					
				 //			Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				 Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				 mensajeEstadoLocal.setPeticion_st(peticionLocal);
	
				 Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				 mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
	
				 ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorOcho))).getPrimaryKey();
				 mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
		
				 mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				 Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				 mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				 mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				 //TODO: Revisar luego estos campos y su mapeo.
				 if(peticionLocal.getCod_are_ges_cd()!=null)
				 mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				 if(peticionLocal.getTel_cot_ds()!=null)
				 mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
	
				MarcaLineaDefectuosaSTBMQ marcaLineaDefectuosaSTBMQ = new MarcaLineaDefectuosaSTBMQ();
				marcaLineaDefectuosaSTBMQ.enviarMensajeReplyToQ(tr021e);
			 } catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			 } catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			 } catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		}
		
		public void envioMarcaLineaDefectuosaGR(String peticion, String idActividad) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try{
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
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
				TR516E tr516e = new TR516E(); 
				tr516e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if (phoneNumber.length()>8){ 
						phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}	
				tr516e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr516e.setStatus(3);//Defectuoso
				tr516e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
					
				//AT-2161 Correccion de marcar avería
				LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
				tr516e.setCity(new Integer(localidadKey.cod_loc).intValue());
				tr516e.setRequestDate(new java.util.Date ());

					
				 //			Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				 Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				 mensajeEstadoLocal.setPeticion_st(peticionLocal);
	
				 Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				 mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));
	
				 ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorOcho))).getPrimaryKey();
				 mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);
		
				 mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				 Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				 mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				 mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				 //TODO: Revisar luego estos campos y su mapeo.
				 if(peticionLocal.getCod_are_ges_cd()!=null)
				 mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				 if(peticionLocal.getTel_cot_ds()!=null)
				 mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
	
				MarcaLineaDefectuosaSTBGRMQ marcaLineaDefectuosaSTBGRMQ = new MarcaLineaDefectuosaSTBGRMQ();
				marcaLineaDefectuosaSTBGRMQ.enviarMensajeReplyToQ(tr516e);
			 } catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			 } catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			 } catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		}


		/* (no Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#envioMarcaLineaEnServicio(java.lang.String, java.lang.String)
		 */
		public void envioMarcaLineaEnServicioGR(String peticion, String idActividad) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try{
				
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
				Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();
				
				 Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
				Producto_servicio_stKey productoServicostKey = null;

				 for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){

					producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
					productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();

				 }
				TR516E tr516e = new TR516E(); 
				tr516e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
				if (phoneNumber!=null && !phoneNumber.trim().equals("")){
					if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
					}
				}			
				else{
					phoneNumber="0";
				}	
				tr516e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr516e.setStatus(1);//En Servicio
				tr516e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
				
				//AT-2161 Correccion de marcar avería
				LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
				tr516e.setCity(new Integer(localidadKey.cod_loc).intValue());
				tr516e.setRequestDate(new java.util.Date ());
		
				 //			Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				 Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				 mensajeEstadoLocal.setPeticion_st(peticionLocal);

				 Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				 mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));

				 ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorOcho))).getPrimaryKey();
				 mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);

				 mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				 Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				 mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				 mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				 //TODO: Revisar luego estos campos y su mapeo.
				 if(peticionLocal.getCod_are_ges_cd()!=null)
				 mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				 if(peticionLocal.getTel_cot_ds()!=null)
				 	mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());
				 
				 MarcaLineaDefectuosaSTBGRMQ marcaLineaEnServicioSTBGRMQ = new MarcaLineaDefectuosaSTBGRMQ();
				 marcaLineaEnServicioSTBGRMQ.enviarMensajeReplyToQ(tr516e);

			//	 MarcaLineaEnServicioSTBMQ marcaLineaEnServicioSTBMQ = new MarcaLineaEnServicioSTBMQ();
			//	 marcaLineaEnServicioSTBMQ.enviarMensajeReplyToQ(tr516e);
	
			} catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
			    log.error("Exception:",e);
			    throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		
		}
		
		public void envioMarcaLineaEnServicio(String peticion, String idActividad) throws ATiempoAppEx {
			// TODO Apéndice de método generado automáticamente
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				ConectorLocalHome conectorLocalHome = (ConectorLocalHome) HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);

				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));

				Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				Peticion_stKey peticion_stkey = new Peticion_stKey(new Long(peticion));
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
				Collection producto_servicio_peticionstArray = peticionLocal.getProducto_servicio_peticion();

				//Proceso para la validacion de los tipos de ps, que estan en el mensaje
//				 StringBuffer append = new StringBuffer();
//				 boolean bip = true;
				 Producto_servicio_peticionLocal producto_servicio_peticionstLocal  = null;
				Producto_servicio_stKey productoServicostKey = null;

				 for(Iterator iter = producto_servicio_peticionstArray.iterator();iter.hasNext();){

					producto_servicio_peticionstLocal= (Producto_servicio_peticionLocal) iter.next();
					productoServicostKey=(Producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getPrimaryKey();

//						 if(bip){
//							 append.append(productoServicostKey.ps_id);
//							 bip = false;
//						 }else{
//							 append.append(" ,"+ productoServicostKey.ps_id .toString());
//						 }

				 }

				//TODO: Se debe realizar una Validacion de los PS
				/*TR009E tr009e = new TR009E(); 
				tr009e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
				tr009e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr009e.setState(1);
				tr009e.setCodIncAtis(new Long(peticionLocal.getCod_apt_ave_cd()).longValue());*/
				
				
				TR021E tr021e = new TR021E(); 
				tr021e.setId(IdCorrelativo.toString());
				String phoneNumber = peticionLocal.getNum_ide_nu();
					if (phoneNumber!=null && !phoneNumber.trim().equals("")){
						if (phoneNumber.length()>8){ 
							phoneNumber=phoneNumber.substring(0,8);
						}
					}			
					else{
						phoneNumber="0";
					}	
				tr021e.setPhoneNumber(new Integer(phoneNumber).intValue());
				tr021e.setStatus(1);//En Servicio
				tr021e.setAtisRequestNumber(peticion_stkey.cod_ave_cd.longValue());
				
		
				 //			Insertamos el Registro de Mesajeria en la Tabla Mensaje Estado Linea
				 Mensaje_estado_stLocal mensajeEstadoLocal= msgEstadoLineaLocalHome.create(IdCorrelativo);
				 mensajeEstadoLocal.setPeticion_st(peticionLocal);

				 Familia_producto_servicio_stKey familia_producto_serviciostKey = (Familia_producto_servicio_stKey) producto_servicio_peticionstLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getPrimaryKey();
				 mensajeEstadoLocal.setCod_familia_ps(new Integer(familia_producto_serviciostKey.faps_id.intValue()));

				 ConectorKey conectorKey = (ConectorKey) conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorOcho))).getPrimaryKey();
				 mensajeEstadoLocal.setCod_conector(conectorKey.cod_conector);

				 mensajeEstadoLocal.setFecha_envio(df.format (new java.util.Date ()));
				 Mensaje_estadoKey mensaje_estado_stKey =  (Mensaje_estadoKey) mensajeManualLocal.getPrimaryKey();
				 mensajeEstadoLocal.setCod_estado(mensaje_estado_stKey.cod_estado);
				 mensajeEstadoLocal.setCod_actividad_generadora(idActividad);
				 //TODO: Revisar luego estos campos y su mapeo.
				 if(peticionLocal.getCod_are_ges_cd()!=null)
				 mensajeEstadoLocal.setArea(new Integer(peticionLocal.getCod_are_ges_cd().intValue()));
				 if(peticionLocal.getTel_cot_ds()!=null)
				 	mensajeEstadoLocal.setTelefono(peticionLocal.getTel_cot_ds());

				 MarcaLineaEnServicioSTBMQ marcaLineaEnServicioSTBMQ = new MarcaLineaEnServicioSTBMQ();
				 marcaLineaEnServicioSTBMQ.enviarMensajeReplyToQ(tr021e);
	
			} catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (CreateException e) {
				log.error("CreateException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
			    log.error("Exception:",e);
			    throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
		
		}


		/* (no Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#recibeMarcaLinea(co.com.telefonica.atiempo.interfaces.atiempo.TR021S)
		 */
		public void recibeMarcaLinea(TR021S tr021s) throws ATiempoAppEx {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
			try{	
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome=(Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			
				Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
	
				Mensaje_estado_stKey key = new Mensaje_estado_stKey(Long.valueOf(tr021s.getId()));
				
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
							+ XMLUtilities.marshall(tr021s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr021s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				//Validacion del estado de la respuesta 
				if (mensajeEstadoLineaLocal.getCod_estado().intValue() == estadoOk ) {
					log.warn(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr021s));
					return;
				}
				String actGen="";
				actGen=mensajeEstadoLineaLocal.getCod_actividad_generadora();
				if(tr021s.isResponse() && !tr021s.isError()){
				
					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actGen);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(),actGen);
					if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_MARCA_AVERIA_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.actualiza_inv,"S");
						actDto.setObservacion("Se ha seteado el daño(averia) en el inventario.Se traspasa a la plataforma");
					}else if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_ACT_INV_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"N");
						actDto.setObservacion("Actualizacion Inventario ok");
					}
					actividadEJB.terminar(actDto);
				}else{
					
					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actGen);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), actGen);
					if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_MARCA_AVERIA_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"S");
						actDto.setObservacion("No se ha seteado el daño(averia) en el inventario.Se devuelve a Diagnostico y Control");
					}else if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_ACT_INV_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"S");
						actDto.setObservacion("No se ha actualizado el inventario. Se deriva a Control Inventario");
					}
					actividadEJB.terminar(actDto);
				}
		
			} catch (NumberFormatException e) {
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (TnProcesoExcepcion e) {
				log.error("TnProcesoExcepcion:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}

		}
		
		public void recibeMarcaLineaGR(TR516S tr516s) throws ATiempoAppEx {

			// @Trace - ana santos - 20 Nov - Inicio 
			BackendExecution bExecution = null;
			try{	
				bExecution = BackendTraceUtil.initExecution(tr516s, log);				
				bExecution.setIdMensajeOp(tr516s.getId());			
				bExecution.startOperation();
				
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estado_stLocalHome msgEstadoLineaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome( Mensaje_estado_stLocalHome.JNDI_NAME);
				
				Mensaje_estadoLocal mesajeOkLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoOk)));
				Mensaje_estadoLocal mensajeManualLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoEsperaManual)));
				Mensaje_estadoLocal mensajeErrorLocal=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(estadoError)));
	
				Mensaje_estado_stKey key = new Mensaje_estado_stKey(Long.valueOf(tr516s.getId()));
				
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
							+ XMLUtilities.marshall(tr516s));
					throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:"+tr516s.getId(),ATiempoAppEx.EXCEPTION);
				}
				
				//Validacion del estado de la respuesta 
				if (mensajeEstadoLineaLocal.getCod_estado().intValue() == estadoOk ) {
					log.warn(
						"El estado de la respuesta es diferente para ser procesado\n "
							+ XMLUtilities.marshall(tr516s));
					return;
				}
				String actGen="";
				actGen=mensajeEstadoLineaLocal.getCod_actividad_generadora();
				//if(tr021s.isResponse() && !tr021s.isError()){
				if(tr516s.getErrorCode() == 0){
				
					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mesajeOkLocal.getPrimaryKey()).cod_estado);
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actGen);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(),actGen);
					if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_MARCA_AVERIA_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"N");
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.actualiza_inv,"S");
						actDto.setObservacion("Se ha seteado el daño(averia) en el inventario.Se traspasa a la plataforma");
					}else if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_ACT_INV_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"N");
						actDto.setObservacion("Actualizacion Inventario ok");
					}
					actividadEJB.terminar(actDto);
				}else{
					
					mensajeEstadoLineaLocal.setCod_estado(((Mensaje_estadoKey)mensajeErrorLocal.getPrimaryKey()).cod_estado);
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actGen);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(mensajeEstadoLineaLocal.getPeti_numero(), actGen);
					if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_MARCA_AVERIA_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.marca_averia_stb,"S");
						actDto.setObservacion("No se ha seteado el daño(averia) en el inventario.Se devuelve a Diagnostico y Control");
					}else if (actGen.equals(STConfig.getVariable("COD_ACTIVIDAD_ACT_INV_STB"))){
						actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"S");
						actDto.setObservacion("No se ha actualizado el inventario. Se deriva a Control Inventario");
					}
					actividadEJB.terminar(actDto);
				}
		
			} catch (NumberFormatException e) {
				bExecution.setErrorOp(true);
				log.error("NumberFormatException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.NUMBER_FORMAT);
			} catch (FinderException e) {
				bExecution.setErrorOp(true);
				log.error("FinderException:",e);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (TnProcesoExcepcion e) {
				bExecution.setErrorOp(true);
				log.error("TnProcesoExcepcion:",e);
				throw new ATiempoAppEx(ATiempoAppEx.CREATE);
			} catch (Exception e) {
				bExecution.setErrorOp(true);
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			} finally{  
				bExecution.endAll();
				// @Trace - ana santos - 20 Nov - FIN 
			}

		}

		public boolean esMarcadaAveriaSTB(Long idPeticion )  throws ATiempoAppEx{
			boolean hayMarca=false;
			try{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 25, 2009
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey peticion_stkey = new Peticion_stKey(idPeticion);
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticion_stkey);
				Collection mensajesInv = peticionLocal.getMensaje_estado_st();
				for(Iterator iterInv=mensajesInv.iterator();iterInv.hasNext();){
					Mensaje_estado_stLocal msgLocal = (Mensaje_estado_stLocal) iterInv.next();
					if (msgLocal.getCod_actividad_generadora()!=null && msgLocal.getCod_actividad_generadora().equals(STConfig.getVariable("COD_ACTIVIDAD_MARCA_AVERIA_STB"))){
						if(msgLocal.getCod_estado().intValue() == estadoOk ){
							hayMarca=true;
						}
					}
				}
			}catch(Exception e){
				log.error("Exception:",e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
			}
			return hayMarca;
		}
		
		public Collection getModemSt(Long peticion) throws ATiempoAppEx{
		
				Collection modems = null;
				try{
				ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
				modems = modemLocalHome.findPeticion(peticion);
		
		
				}catch (Exception e) {
					log.error("Exception:", e);
					throw new ATiempoAppEx(ATiempoAppEx.FINDER);
				}
				return modems;
		}
	
		public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx{
			InformacionTecnicaDTO datosTecnicosDto = null;
		
			try {
				Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(new Peticion_stKey(codAveOri));
				
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
				
				Collection recLinea = peticion_stLocal.getRecursos_linea_basica();
				if ( recLinea==null || recLinea.size()==0 ){
					log.debug("No existen recursos STB para esta Peticion:" + codAveOri);
				}else{
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recLinea.iterator().next();

					// PRUEBA - JAVIER Y FEDERICO
					Long idConector = ((Recursos_linea_basicaKey)recursos_linea_basicaLocal.getPrimaryKey()).id_conector;

					log.debug("Existen recursos para esta Peticion STB " + recursos_linea_basicaLocal.getTelefono_asignado());
					datosTecnicosDto = new InformacionTecnicaDTO();
					datosTecnicosDto.setCentral(recursos_linea_basicaLocal.getCentral());
					datosTecnicosDto.setCentralDesc(recursos_linea_basicaLocal.getDesc_central());
					datosTecnicosDto.setTelefono(recursos_linea_basicaLocal.getTelefono_asignado());
					datosTecnicosDto.setLen(recursos_linea_basicaLocal.getLen());
					datosTecnicosDto.setDistr(Utiles.sinNull(recursos_linea_basicaLocal.getDist_prim(), ""));
					datosTecnicosDto.setDescripcionDistr(recursos_linea_basicaLocal.getDesc_dist_prim());
					datosTecnicosDto.setCable(recursos_linea_basicaLocal.getCable());
					datosTecnicosDto.setCaja(recursos_linea_basicaLocal.getCaja());
					datosTecnicosDto.setDirecDistr(recursos_linea_basicaLocal.getDir_distribuidor());
					datosTecnicosDto.setArmario(recursos_linea_basicaLocal.getArmario());
					datosTecnicosDto.setDirecCaja(recursos_linea_basicaLocal.getDir_caja());
					datosTecnicosDto.setDirecArmario(recursos_linea_basicaLocal.getDir_armario());
					datosTecnicosDto.setListon(recursos_linea_basicaLocal.getListon());
					datosTecnicosDto.setParListon(recursos_linea_basicaLocal.getPar_liston());
					datosTecnicosDto.setParCable(recursos_linea_basicaLocal.getPar_cable());
					datosTecnicosDto.setParCaja(Utiles.sinNull(recursos_linea_basicaLocal.getPar_caja(),""));
					datosTecnicosDto.setPosicionHorizontal(Utiles.sinNull(recursos_linea_basicaLocal.getPosicion_horizontal(),""));
					
					Collection listaDslams=recursos_linea_basicaLocal.getDslam_apsc_linea();
					ArrayList dslamLinea=new ArrayList();
					for(Iterator iterator=listaDslams.iterator();iterator.hasNext();)
					{
						Dslam_apsc_lineaLocal dslam_apsc_lineaLocal=(Dslam_apsc_lineaLocal) iterator.next();
						DslamApscLineaDTO dslamDto = new DslamApscLineaDTO();
						Dslam_apsc_lineaKey dslam_apsc_lineaKey=(Dslam_apsc_lineaKey) dslam_apsc_lineaLocal.getPrimaryKey();
						dslamDto.setIdDslams(dslam_apsc_lineaKey.id_dslam);
						
						//Recursos_linea_basicaKey recursos_linea_basicaKey=(Recursos_linea_basicaKey) dslam_apsc_lineaLocal.getRecursos_linea_basica().getPrimaryKey();
						dslamDto.setIdConector(idConector);
						dslamDto.setIp(dslam_apsc_lineaLocal.getIp());
						dslamDto.setTipoDslam(dslam_apsc_lineaLocal.getTipo_dslam());
					
						dslamLinea.add(dslamDto);
					}							
					datosTecnicosDto.setDslams(dslamLinea);
			   		
			   		// inicio - SIGRES - Zonas de atendimiento
				    Collection listaZonas = recursos_linea_basicaLocal.getZonas_atendimiento();
					ArrayList zonasAtendimiento = new ArrayList();
					
					for(Iterator iterator = listaZonas.iterator();iterator.hasNext();)
					{
						Zonas_atendimientoLocal zonasAtendimientoLocal=(Zonas_atendimientoLocal) iterator.next();
						ZonasAtendimientoDTO zonasDto = new ZonasAtendimientoDTO();
						
						Zonas_atendimientoKey zonasKey = (Zonas_atendimientoKey)zonasAtendimientoLocal.getPrimaryKey();
						zonasDto.setId(zonasKey.id);
						zonasDto.setIdConector(zonasAtendimientoLocal.getId_conector());
						zonasDto.setIp(zonasAtendimientoLocal.getIp());						
						zonasAtendimiento.add(zonasDto);
					}							
					datosTecnicosDto.setZonasAtendimiento(zonasAtendimiento);
					// fin - SIGRES -Zonas de atendimiento			   		
				}
			} catch (NamingException e) {
				log.warn("NamingException",e);
				datosTecnicosDto = null;
				//throw new ATiempoAppEx(ATiempoAppEx.NAMING);
			} catch (FinderException e) {
				log.warn("FinderException",e);
				datosTecnicosDto = null;
				//throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			} catch (Exception e) {
				log.warn("Exception",e);
				datosTecnicosDto = null;
				//throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION);
			}
			//si hay error se manda un null, pero la pagina carga igual con datos en blanco
		return datosTecnicosDto; 	
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
		
		public void configurarPresenciaDigital(ActividadEJBDTO ac, Long nroPeticion, String actGeneradora) throws ATiempoAppEx{
			try{
				log.debug("Ingresa a enviarMensajePresenciaDigitalPorPS, para la petición Atiempo No. "+nroPeticion);
				Mensaje_estadoLocalHome mensaje_estadoLocalHome = (Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
				Mensaje_estadoLocal mensajeEspera = mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
		        Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		        
		    	TR054E object = new TR054E();
		        /*Datos del encabezado*/
				object.setId(idCorrelativoMensaje.toString());
				object.setDestination("ESB");
				object.setSource(ComunInterfaces.sistemaAtiempoSt);
				object.setInterfaz(ComunInterfaces.INTERFAZ_PRESENCIA_DIGITAL);
				object.setVersion("1.0");		
				
				object.setCommercialOperation(ComunInterfaces.OPCO_PDG_ST);
				object.setAtisRequestNumber(nroPeticion.longValue());
				
				object.setAtiempoRequestNumber(nroPeticion.longValue());
				
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));
				
				Long codPS = peticionLocal.getCod_pro_ser_cd();
				if(codPS != null)
					object.setCodePlan(codPS.toString());
//				idpc
				String idpc_pdg = peticionLocal.getNum_ide_nu();
								
				object.setIdPC(idpc_pdg);
				

				object.setNit(peticionLocal.getNum_doc_rte_nu());
				
				object.setCity(peticionLocal.getCod_loc());
				
				object.setClientName(peticionLocal.getNom_cal_ds());
				
				if(peticionLocal.getFecha_sol_falla() != null){
					object.setSaleDate(peticionLocal.getFecha_sol_falla());
				}

//				if(peticionLocal.getNom_int_ds() != null)
//					object.setContactName(peticionLocal.getNom_int_ds());
//				else if(peticionLocal.getNom_ds() != null)
//					object.setContactName(peticionLocal.getNom_ds());
				
				if(peticionLocal.getNom_cal_ds() != null)
					object.setAddress(peticionLocal.getNom_cal_ds());
				object.setOpeningCode(peticionLocal.getCod_apt_ave_cd());
				if (peticionLocal.getRpt_pru_ave_cd() == null)
					object.setDiagnosticCode("0");
				else
					object.setDiagnosticCode(peticionLocal.getRpt_pru_ave_cd());
				object.setBreakdownCode(nroPeticion.toString());
				object.setObservationsOne(peticionLocal.getObs_ave_ds());
				object.setObservationsTwo(peticionLocal.getObs_ave_ds());
				object.setObservationsThree(peticionLocal.getObs_ave_ds());
				
//				if(subpeticionCaracteristicas != null && subpeticionCaracteristicas.size() > 0){
//					
//					for(Iterator i = subpeticionCaracteristicas.iterator(); i.hasNext();){
//						Subpeticion_caracteristicasLocal subpeticionCaractLocal = (Subpeticion_caracteristicasLocal) i.next();
//						
//						if(subpeticionCaractLocal != null){
//							Subpeticion_caracteristicasKey subpeticionCaractKey = (Subpeticion_caracteristicasKey) subpeticionCaractLocal.getPrimaryKey();
//							
//							if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_CUENTA_CORREO)){
//								object.setClientEmail(subpeticionCaractLocal.getVal_ral_crc_cd());
//								object.setContactEmail(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_INDICATIVO_TELEFONICO)){
//								object.setIndicative(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_UNO)){
//								object.setMessageOne(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_DOS)){
//								object.setMessageTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_MENSAJE_VOLANTE_TRES)){
//								object.setMessageThree(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_NUMERO_TELEFONICO)){
//								object.setPhone(subpeticionCaractLocal.getVal_ral_crc_cd());
//								//object.setContactPhone(subpeticionCaractLocal.getVal_ral_crc_cd());
//								object.setCellphone(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_UNO)){
//								object.setKeyWordOne(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_DOS)){ //JOSE BAEZ REQ 12804
//								object.setKeyWordTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PALABRA_CLAVE_TRES)){ //JOSE BAEZ REQ 12804
//								object.setKeyWordThree(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_UNO)){
//								object.setPromotionOne(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_DOS)){	//JOSE BAEZ REQ 12804
//								object.setPromotionTwo(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_PROMOCION_TRES)){ //JOSE BAEZ REQ 12804
//								object.setPromotionThree(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.CARACT_PDG_REGION_PAUTA)){
//								object.setRegion(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}else if(subpeticionCaractKey.cod_crc_cd.equals(ComunInterfaces.URL)){
//								object.setUrl(subpeticionCaractLocal.getVal_ral_crc_cd());
//							}
//						}
//					}					
//				}
				
//				PARA ENVIAR EL CELULAR SE PREGUNTA POR TODOS LOS CAMPOS EN LA TR DE TELEFONOS
				//if ()
					//peticionLocal.get
					
				
				//object.setCellphone("celular");
				
//				if(object.getPhone() == null && peticionLocal.getNum_ide_nu_stb() != null)
//					object.setPhone(peticionLocal.getNum_ide_nu_stb());
				
				if(object.getContactPhone() == null && peticionLocal.getTel_cot_ds() != null)
					object.setContactPhone(peticionLocal.getTel_cot_ds());

		        /**/	        		
        		PresenciaDigitalSTMQ presenciaDigital = new PresenciaDigitalSTMQ();
        		presenciaDigital.enviarMensaje(object);
		        
		        Mensaje_estado_stLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
				Mensaje_estado_stLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion_st(peticionLocal);
		        msgLocal.setCod_actividad_generadora(actGeneradora);
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
		        
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
			
		        bExecution = BackendTraceUtil.initExecution(tr054s, log);		       
		        bExecution.setNumPetAtiempo(new Long(tr054s.getAtiempoRequestNumber()));
		        bExecution.setIdMensajeOp(tr054s.getId());
		        bExecution.startOperation();
		        
		        Mensaje_estado_stLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
		        Mensaje_estado_stLocal mensajeEstadoBa;
		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr054s.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr054s));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr054s.getId(), ATiempoAppEx.FINDER);
		        }
		        Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
		        ActividadLocalHome actividadLocalHome=(ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				ActividadLocal actividadLocal =  actividadLocalHome.findByCodigoActividadIdAplicacion(
						mensajeEstadoBa.getCod_actividad_generadora(),idAplicacion);
				ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();

		        ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(mensajeEstadoBa.getCod_actividad_generadora ());
				ActividadEJBDTO actDto =recuperaActividadDeBandejaIntegrada(mensajeEstadoBa.getPeticion_st(),mensajeEstadoBa.getCod_actividad_generadora(),
						actividadKey.act_id);
		    	ErrorLegadoLocal errorLegado =null;
		    	
		    	if(tr054s != null && tr054s.getError() != null && tr054s.getError().equals("0")){
		    		/*Procesamiento de la respuesta - No se debe procesar nada, solo cerrar la actividad*/
		        	/*Cierre de la actividad exitoso*/
					
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setCod_estado(new Integer(ComunInterfaces.estadoOk));
					log.debug("Voy a ingresar una solucion de la Averia de Presencia Digital :" + tr054s.getAtisRequestNumber()+ "Con Codigo Cierre BRM :" + tr054s.getAtiempoRequestNumber());
					try{
						Codigo_cierreLocalHome codCierreLocalHome = (Codigo_cierreLocalHome)HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
						Codigo_cierre_peticionLocalHome codCierrePeticionLocalHome = (Codigo_cierre_peticionLocalHome)HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
						Codigo_cierreLocal codCierreLocal = codCierreLocalHome.findByDescripcion(new String("EXPLIC METODO DE ACCESO-CONSULTA USUARIO"));
						Codigo_cierreKey codCierreKey = (Codigo_cierreKey)codCierreLocal.getPrimaryKey();
						Fecha fecCie= new Fecha(new Timestamp(new Date().getTime()));                                                           
						IncidentesInterfaces iI = new  IncidentesDelegate();
						iI.agregarSolucion(new Long(tr054s.getAtisRequestNumber()),actDto.getCodigoActividad(),actDto.getIdUsuario(),codCierreKey.cod_cierre,fecCie.getFechaconFormato(9));
					}catch (FinderException f){
						log.debug("No Se encuentra Parametriazado el Codigo de Cierre de BRM");
					}
					actDto.setObservacion("Se finaliza la actividad por mensaje de confirmacion "+tr054s.getError()+" - "+tr054s.getErrorMessage(), false);
					actividadEJB.terminar (actDto);
		        }else{	        	
		    		mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setCod_estado(new Integer(ComunInterfaces.estadoError));
					actDto.setObservacion("Error en configuracion, error "+tr054s.getError()+" - "+tr054s.getErrorMessage(), false);
					actividadEJB.grabarSinTerminar(actDto);
		        }
		    					
		    	
			} catch (EJBException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion con id: " + tr054s.getId() +".", e);
			} catch (TnProcesoExcepcion e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} catch (NamingException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} catch (ATiempoAppEx e) {
				throw e;
			} catch (FinderException e) {
				log.error("Error procesando la respuesta TR054S o cerrando la peticion.");
	            throw new ATiempoAppEx("Error procesando la respuesta TR054S o cerrando la peticion. con id: " + tr054s.getId() +".", e);
			} finally{
				bExecution.endAll();		
			}		
		}
		/**
		 * DAVID: recupera la actividad actual por medio del correlativo almacenado en la tabla bandejaIntegrada, es necesario para actividades manuales
		 * que se gestionan desde el código tal como se hace para las de planta externa en agenda SC.
		 * @param peticion_stLocal
		 * @return
		 * @throws ATiempoAppEx
		 */
		public ActividadEJBDTO recuperaActividadDeBandejaIntegrada(Peticion_stLocal peticion_stLocal,String codActividadPE,Long idActividadPE)throws ATiempoAppEx{
			Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
			ActividadEJBDTO actDto=null;
			try{
				BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				
				Peticion_stKey peticion_stKey=(Peticion_stKey)peticion_stLocal.getPrimaryKey();                                  
				
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

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
		 */
		public void enviarRefrecarDatos(Long idPeticion) {
			// TODO Apéndice de método generado automáticamente
			try {
				// TODO Apéndice de método generado automáticamente
				log.debug("Entro a enviarRefrecarDatos ");
				String datos;
				Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
				Tmp_agenda_scLocal tmp_agenda_scLocal = tmpAgendaSCLocalHome.findbyPeti_numero(idPeticion);
				Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				String central = "";
				Recursos_linea_basicaLocal recursos_linea_basicaLocal = recursos_linea_basicaLocalHome.findbyCod_ave_cd(idPeticion);
				if(recursos_linea_basicaLocal.getCentral() != null)
					central = recursos_linea_basicaLocal.getCentral().toString();
				datos = "1. Central: "+ central+";";
				String numeroTelefono = "";
				if(recursos_linea_basicaLocal.getTelefono_asignado() != null)
					numeroTelefono = recursos_linea_basicaLocal.getTelefono_asignado().toString();
				datos = datos + "2. Numero de Telefono: "+ numeroTelefono+";";
				String len = recursos_linea_basicaLocal.getLen();
				datos = datos + "3. Len: "+ len +";";
				//String lecturaContador = recursos_linea_basicaLocal.getDir_ip_dslam_actual();
				String posicionHorizontal = recursos_linea_basicaLocal.getPosicion_horizontal();
				datos = datos + "4. Posicion Horizontal: "+ posicionHorizontal +";";
				String distribuidor = "";
				if(recursos_linea_basicaLocal.getDistribuidor_sec() != null)
					distribuidor = recursos_linea_basicaLocal.getDistribuidor_sec().toString();
				datos = datos + "5. Distribuidor: "+ distribuidor +";";
				String descripcion = recursos_linea_basicaLocal.getDesc_distribuidor_sec();
				datos = datos + "6. Descripcion: "+ descripcion+";";
				String direccionDistribuidor = recursos_linea_basicaLocal.getDir_distribuidor();
				datos = datos + "7. Direccion del Distribuidor: "+ direccionDistribuidor+";";
				String liston = recursos_linea_basicaLocal.getListon();
				datos = datos + "8. Liston: "+ liston+";";
				String parListon = "";
				if(recursos_linea_basicaLocal.getPar_liston() != null)
					parListon = recursos_linea_basicaLocal.getPar_liston().toString();
				datos = datos + "9. Par liston: "+ parListon+";";
				String cable = "";
				if(recursos_linea_basicaLocal.getCable() != null)
					cable = recursos_linea_basicaLocal.getCable().toString();
				datos = datos + "10. Cable: "+ cable+";";
				String parCable = "";
				if(recursos_linea_basicaLocal.getPar_cable() != null)
					parCable = recursos_linea_basicaLocal.getPar_cable().toString();
				datos = datos + "11. Par Cable: "+ parCable+";";
				String armario = recursos_linea_basicaLocal.getArmario();
				datos = datos + "12. Armario: "+ armario+";";
				String direccionArmario = recursos_linea_basicaLocal.getDir_armario();
				datos = datos + "13. Direccion del Armario: "+ direccionArmario+";";
				String codigoCaja = recursos_linea_basicaLocal.getCaja();
				datos = datos + "14. Codigo de la Caja: "+ codigoCaja+";";
				String parCaja = "";
				if(recursos_linea_basicaLocal.getPar_cable() != null)
					parCaja = recursos_linea_basicaLocal.getPar_caja().toString();
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
				tr719e.setId_sistema_origen("ATIEMPO_ST");
				tr719e.setId_actuacion(tr719s.getId_actuacion());
				tr719e.setNotif_refresh(datos);
				tr719e.setDescripcion(recursos_linea_basicaLocal.getMensaje_error());
				
				if(recursos_linea_basicaLocal.getInd_error()!= null){
					tr719e.setCodigo_error(recursos_linea_basicaLocal.getInd_error().toString());
				}
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
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#isIDPC(java.lang.Long)
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
		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarCamaraZTE(co.com.telefonica.atiempo.actividades.ActividadEJBDTOlang.String)
		 */
		public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx {
			try{
				log.debug("Ingresa a configuración Cámara ZTE (Monitoreo remoto), para la petición Atiempo No. "+ numPeticion);
				Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(numPeticion));
				
				Long idCorrelativoMensaje = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				Long caracteristicaRecordSpace = new Long(incidentesDelegate.recuperarParametroFromPropertiesBD("CARACT_CLIENTE_ZTE_RECORD_SPACE"));
				
				TR057E tr = new TR057E();
				tr.setId(idCorrelativoMensaje.toString());
				tr.setAtisRequestNumber(numPeticion.longValue());
				tr.setAtiempoRequestNumber(numPeticion.longValue());
				tr.setDestination("ESB");
				tr.setInterfaz(ComunInterfaces.INTERFAZ_CONF_CAMARA_ZTE_ST);
				tr.setSource(ComunInterfaces.sistemaAtiempoSt);
				tr.setVersion("1.0");
				
				if(peticionLocal.getCod_pro_ser_cd()!=null){
					tr.setPsCode(peticionLocal.getCod_pro_ser_cd().intValue());
				}
				
				if(peticionLocal.getCod_loc() != null){
					LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
					LocalidadKey localidadKey= (LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
					tr.setCity(localidadKey.cod_loc);
				}
				
				if(peticionLocal.getNom_cal_ds() != null){
					tr.setAddress(peticionLocal.getNom_cal_ds());
				}
				
				Collection listaRecursosBa = peticionLocal.getRecursos_ba();
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						tr.setFatherAccount(element.getFather_email_actual());
					}
				}
				if(tr.getFatherAccount()==null){
					throw new ATiempoAppEx("No se pudo obtener el father account de RecursosBa para la petición: "+ numPeticion);
				}
				
				//Se quema un ps de monitoreo por defecto. Falta definir como traerlo.
				tr.setRecordSpace(peticionLocal.getCod_pro_ser_cd().toString());
				
				/*Collection listaProdSerPeticion = peticionLocal.getProducto_servicio_peticion();
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
				}*/
				
				Mensaje_estado_stLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
				Mensaje_estado_stLocal msgLocal = mensajeEstadoBaLocalHome.create(idCorrelativoMensaje);
		        msgLocal.setPeticion_st(peticionLocal);
		        msgLocal.setFecha_envio(df.format(new java.util.Date()));
		        msgLocal.setCod_estado(new Integer(ComunInterfaces.estadoEspera));
		        
		        Collection camarasAtiempo = obtenerCamaras(numPeticion);
		        Collection camarasPeticion = new ArrayList();
		        for (Iterator iter = camarasAtiempo.iterator(); iter.hasNext();) {
					CamaraLocal camaraAtiempo = (CamaraLocal) iter.next();
					CameraRequest camaraPeticion = new CameraRequest();
					setCamaraRequest(camaraAtiempo,camaraPeticion);
					camarasPeticion.add(camaraPeticion);
				}
		        tr.setCameras(camarasPeticion);
		        
		        co.com.telefonica.atiempo.soltec.servicios.ConfCamaraZTEMQ mq  = new co.com.telefonica.atiempo.soltec.servicios.ConfCamaraZTEMQ();
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
		        Mensaje_estado_stLocalHome mensajeEstadoBaLocalHome = mensajeEstadoBaLocalHome = (Mensaje_estado_stLocalHome) HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
		        Mensaje_estado_stLocal mensajeEstadoBa;

		        try {
		        	mensajeEstadoBa = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_stKey(new Long(tr.getId())));
		        } catch (FinderException e1) {
		        	mensajeEstadoBa = null;
		        	log.error("No Existe Respuesta en la base de enviados\n " + XMLUtilities.marshall(tr));
		            throw new ATiempoAppEx("No Existe Respuesta en la base de enviados:" + tr.getId(), ATiempoAppEx.FINDER);
		        }
		        			
		    	Long numPeticion = Long.valueOf(tr.getAtiempoRequestNumber());
		    	Tmp_agenda_scLocalHome tmpAgendaLocalHome = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
		    	Tmp_agenda_scLocal agenda = tmpAgendaLocalHome.findbyPeti_numero(numPeticion);
				TR709S tr709s = (TR709S)XMLUtilities.unmarshall(agenda.getXml());
				
				Collection listaRecursosBa = mensajeEstadoBa.getPeticion_st().getRecursos_ba();
				String fatherAccount = null;
				if(listaRecursosBa!=null && listaRecursosBa.size()>0){
					for (Iterator iter = listaRecursosBa.iterator(); iter.hasNext();) {
						Recursos_baLocal element = (Recursos_baLocal) iter.next();
						fatherAccount = element.getFather_email_actual();
					}
				}
				
				Collection camarasRespuesta = new ArrayList();
				CamaraLocalHome camaraLocalHome = (CamaraLocalHome) HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
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
				
		    	if(tr.getError() != null && tr.getError().equals("0")){
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setCod_estado(new Integer(ComunInterfaces.estadoOk));
		        }else{	        	
					mensajeEstadoBa.setFecha_cierre(df.format(new java.util.Date()));
					mensajeEstadoBa.setCod_estado(new Integer(ComunInterfaces.estadoError));
					mensajeEstadoBa.setObservaciones(tr.getErrorMessage());
		        }
		    	
				TR709E tr709e = crearTr709e(tr.getError(),tr.getErrorMessage(),camarasRespuesta,tr709s);
				agenda.remove();
		    	enviarActivarCamaraAgendaSC(tr709e);
		    	
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
						if(key.cameraSerial.equals(camaraPeticion.getCameraSerial())){//Sí la cámara es encontrada se intenta activar
							if(camaraPeticion.getCameraState().intValue()!=1){//Solo se manda a activar sí no está ya activa
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
			co.com.telefonica.atiempo.soltec.servicios.ActivarCamaraAgendaSCMQ mq = new co.com.telefonica.atiempo.soltec.servicios.ActivarCamaraAgendaSCMQ();
			mq.enviarMensaje(tr);
		}
		
		public ErrorLegadoLocal getErrorLegado(String codigoError,String codigoTr){
			ErrorLegadoLocal errorLegado = null;
			try{
				ErrorLegadoLocalHome errorLegadoHome = (ErrorLegadoLocalHome) HomeFactory.getHome( ErrorLegadoLocalHome.JNDI_NAME);
				errorLegado = errorLegadoHome.findByErrorTr(codigoError, codigoTr);
			} catch (javax.ejb.FinderException e) {
				log.debug("ha ocurrido una excepcion al consultar error en error legado"+e);
			} catch (NamingException e) {
		 //TODO ver que pasa con esta excepcion
				log.debug("ha ocurrido una excepcion allevantar el localhome de error legado"+e);
			}
			return errorLegado;
		}
		//REQ TOA FASE III DCARDENA FUNCION QUE DERIVA A PGI AVERIAS
		public void hayPGIAveria (String codigoError,String ErrorMesage,String codigoTR,String codigoAveria,String codigoActividadGeneradora,Mensaje_estado_stLocal meST){
			//intanciamos la tanbla error legado en la cual se validara los errores que nos envia broker 
			//consultamos si nos llego un error en la tabla neviando el codigo y la TR
			try{
				log.info("INICIA FUNCION DERIVA A PGI Averias "+"0"+codigoError +"--"+ErrorMesage+"--"+codigoTR+"--"+codigoAveria+"--"+codigoActividadGeneradora);
				ErrorLegadoLocal errorLegado = getErrorLegado("0"+codigoError,codigoTR);
				String plataforma = errorLegado.getPlataforma(); 
				String bandeja	= VpistbbaConfig.getVariable("BANDEJA_"+plataforma); 
				// se obtiene los datos de la actividad
				ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codigoActividadGeneradora);
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(new Long(codigoAveria), codigoActividadGeneradora);
				log.info("Se deriva PGI_AVERIAS por error de mensaje "+codigoActividadGeneradora +" "+ ErrorMesage+" en la averia "+ codigoAveria);
				// se registra la observacion del error en la tabla mensaje estado ST
				if(ErrorMesage.length()>90)
					ErrorMesage = ErrorMesage.substring(0,90);
				if(meST!=null){
					meST.setObservaciones(codigoError + ErrorMesage);
				}
				// se setea la informacion de forzar la actividad PGI_AVERIAS
				actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
				actDto.setObservacion("Error en la Obtencion de Recursos " + ErrorMesage + " Se envia a la bandeja de " + bandeja );
				actividadEJB.terminar(actDto);
			//FIN REQ TOA FASE III
			}catch(Exception e){
				log.debug("ha ocurrido una excepcion al derivar aPGI Averias"+ codigoAveria + " error "+e);
			}
		}
}
