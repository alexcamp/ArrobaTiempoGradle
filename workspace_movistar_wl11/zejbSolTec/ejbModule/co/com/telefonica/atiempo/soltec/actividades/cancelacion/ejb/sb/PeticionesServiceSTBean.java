package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.MensajeConfModemACSDTO;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.EmpresaKey;
import co.com.telefonica.atiempo.ejb.eb.EmpresaLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.Mensaje_conf_ACSLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
/**
 * Bean implementation class for Enterprise Bean: PeticionesServiceST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 19, 2009
public class PeticionesServiceSTBean extends SessionBeanAdapter implements PeticionesInterfaces {
	private javax.ejb.SessionContext mySessionCtx;
	
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
	//private BintegradaLocalHome bintegradaHome;
	//CR23110
	//private Regla_RetencionesLocalHome reglaRetencionHome;
	
	private Logger log=Logger.getLogger (PeticionesServiceSTBean.class);
	/**
	 * getSessionContext
	 */
	public PeticionesServiceSTBean () throws ATiempoAppEx
	{
    	
	}
    
	/*
	 * Metodo validador Home
	 */
	
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		try
		{

			UsuarioLocalHome usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(idUsuario));
			if(usuarioLocal.getEmpresa()==null)
			{
				log.debug("NO ENCONTRE EMPRESAS EN PeticionesServiceSTBean");
				return new InformacionEmpresaDTO();
			}
			InformacionEmpresaDTO empresaDTO=new InformacionEmpresaDTO();
			EmpresaLocal empresaLocal=usuarioLocal.getEmpresa();
			EmpresaKey empresaKey=(EmpresaKey) empresaLocal.getPrimaryKey();
			empresaDTO.setEmprId(empresaKey.empr_id);
			empresaDTO.setEmprNombre(empresaLocal.getEmpresa_nombre());
			empresaDTO.setEmprDescripcion(empresaLocal.getEmpresa_descripcion());
			return empresaDTO;
		}
		catch(FinderException e)
		{
			return new InformacionEmpresaDTO();
		}
		catch(Exception e)
		{
			return new InformacionEmpresaDTO();
		}
	}
	
	public ArrayList buscarPorCliente(String rutCli, String rutDv){
		
		ArrayList listaPeticiones = new ArrayList();
		
		try {
			Collection c = null;
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
	
			c = peticion_stLocalHome.findNumClieDv(rutCli, rutDv);
		

			if ( c != null ) {
				for (Iterator it=c.iterator(); it.hasNext(); ) {
					Peticion_stLocal pLocalSt = (Peticion_stLocal) it.next();
					Peticion_stDTO pDtost = new Peticion_stDTO();
					Peticion_stKey pKeySt = (Peticion_stKey) pLocalSt.getPrimaryKey();
		
					pDtost.setCod_ave_cd(pKeySt.cod_ave_cd);
					pDtost.setFec_apt_ave_ts( pLocalSt.getFec_apt_ave_ts() );
					pDtost.setTim_fec_cps_ts( pLocalSt.getTim_fec_cps_ts() );
			
					listaPeticiones.add( pDtost );
		
				}
			}
		} catch (EJBException e) {
		} catch (FinderException e) {
		} catch (NamingException e) {
		}

		return listaPeticiones;
		
	}
	
	public ArrayList buscarPorIDPC(String idPC){
		
		ArrayList listaPeticiones = new ArrayList();
		
		try {
			Collection c = null;
			
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
	
			c = peticion_stLocalHome.findByIDPC(idPC);
		

			if ( c != null ) {
				for (Iterator it=c.iterator(); it.hasNext(); ) {
					Peticion_stLocal pLocalSt = (Peticion_stLocal) it.next();
					Peticion_stDTO pDtost = new Peticion_stDTO();
					Peticion_stKey pKeySt = (Peticion_stKey) pLocalSt.getPrimaryKey();
		
					pDtost.setCod_ave_cd(pKeySt.cod_ave_cd);
					pDtost.setFec_apt_ave_ts( pLocalSt.getFec_apt_ave_ts() );
					pDtost.setTim_fec_cps_ts( pLocalSt.getTim_fec_cps_ts() );
			
					listaPeticiones.add( pDtost );
		
				}
			}
		} catch (EJBException e) {
		} catch (FinderException e) {
		} catch (NamingException e) {
		}

		return listaPeticiones;
		
	}	

	public ArrayList buscarAv(Long codAveCd, String rutCli, String rutDv, String idPC)
	{
		ArrayList listaPeticiones = new ArrayList();
		
		try {
			
			BintegradaLocalHome	bintegradaHome=(BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = null;
			BintegradaLocal bintegradaLocal=null;
			Peticion_stLocalHome peticion_stLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			if(codAveCd!=null)
			{
				log.debug("Busco por numero incidente");
				Peticion_stLocal local=peticion_stLocalHome.findByPrimaryKey(new Peticion_stKey(codAveCd));
				Peticion_stDTO pDtost = new Peticion_stDTO();
				Peticion_stKey pKeySt = (Peticion_stKey) local.getPrimaryKey();

				pDtost.setCod_ave_cd(pKeySt.cod_ave_cd);
				pDtost.setFec_apt_ave_ts( local.getFec_apt_ave_ts() );
				pDtost.setTim_fec_cps_ts( local.getTim_fec_cps_ts() );
				
				try
				{
					bintegradaLocal=bintegradaHome.findByVisiblePetApl(pKeySt.cod_ave_cd,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
					log.debug("Esta En Bandeja!!!");
					pDtost.setEstaEnBandeja(true);
					pDtost.setUrlBandeja(bintegradaLocal.getBi_url_detalle());
					listaPeticiones.add( pDtost );
				}
				catch(FinderException fff)
				{
					log.debug("No esta en bandeja!!!");
					pDtost.setEstaEnBandeja(false);
					listaPeticiones.add( pDtost );
				}
				return listaPeticiones;

			}
			if(rutCli!=null && !rutCli.trim().equals(""))
			{
				log.debug("Busco por Rut");
				c = peticion_stLocalHome.findNumClieDv(rutCli, rutDv);
				if ( c != null )
				{
					for (Iterator it=c.iterator(); it.hasNext(); )
					{
						Peticion_stLocal pLocalSt = (Peticion_stLocal) it.next();
						Peticion_stDTO pDtost = new Peticion_stDTO();
						Peticion_stKey pKeySt = (Peticion_stKey) pLocalSt.getPrimaryKey();

						pDtost.setCod_ave_cd(pKeySt.cod_ave_cd);
						pDtost.setFec_apt_ave_ts( pLocalSt.getFec_apt_ave_ts() );
						pDtost.setTim_fec_cps_ts( pLocalSt.getTim_fec_cps_ts() );
						try
						{
							bintegradaLocal=bintegradaHome.findByVisiblePetApl(pKeySt.cod_ave_cd,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
							log.debug("Esta En Bandeja!!!");
							pDtost.setEstaEnBandeja(true);
							pDtost.setUrlBandeja(bintegradaLocal.getBi_url_detalle());
						}
						catch(FinderException fff)
						{
							log.debug("No esta en bandeja!!!");
							pDtost.setEstaEnBandeja(false);	
						}
						listaPeticiones.add( pDtost );
					}
				}
				return listaPeticiones;
			}
			if(idPC!=null && !idPC.trim().equals(""))
			{
				log.debug("Busco por IDPC");
				c = peticion_stLocalHome.findByIDPC(idPC);
				if ( c != null )
				{
					for (Iterator it=c.iterator(); it.hasNext(); )
					{
						Peticion_stLocal pLocalSt = (Peticion_stLocal) it.next();
						Peticion_stDTO pDtost = new Peticion_stDTO();
						Peticion_stKey pKeySt = (Peticion_stKey) pLocalSt.getPrimaryKey();

						pDtost.setCod_ave_cd(pKeySt.cod_ave_cd);
						pDtost.setFec_apt_ave_ts( pLocalSt.getFec_apt_ave_ts() );
						pDtost.setTim_fec_cps_ts( pLocalSt.getTim_fec_cps_ts() );
						try
						{
							bintegradaLocal=bintegradaHome.findByVisiblePetApl(pKeySt.cod_ave_cd,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
							log.debug("Esta En Bandeja!!!");
							pDtost.setEstaEnBandeja(true);
							pDtost.setUrlBandeja(bintegradaLocal.getBi_url_detalle());
						}
						catch(FinderException fff)
						{
							log.debug("No esta en bandeja!!!");
							pDtost.setEstaEnBandeja(false);	
						}
						listaPeticiones.add( pDtost );
					}
				}
				return listaPeticiones;
			}
		} catch (EJBException e) {
		} catch (FinderException e) {
		} catch (NamingException e) {
		}
		
		return listaPeticiones;
	}
	
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx{
		try{
			Peticion_stLocalHome peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Long todasGrantite=new Long (VpistbbaConfig.getVariable("GRANITE"));
			if (ComunInterfaces.sinGranite== todasGrantite.longValue()){
				
				if(peticionHome==null)
					peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
				Peticion_stLocal peticionLocal=peticionHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));				
				
				//Si el valor es uno es porque usa Granite
				if(peticionLocal.getGranite_code() != null && peticionLocal.getGranite_code().longValue()==ComunInterfaces.conGranite){
					return true;
				}else{
					return false;
				}
			}else{
				//Si entra al else ya estan todas las localidades por Granite
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Exception",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	/**
	 * @param numero peticion atiempo
	 * Metodo que valida si una peticion esta siendo impactada por una regla de retencion y si es asi setea la misma en la tabla peticion 
	 * @author 801936
	 */
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx {
		Regla_RetencionesLocal reglaRetencion=null;
		try {
			Peticion_stLocalHome peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
			if(peticionHome==null)
				peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			
			
			String activo = STConfig.getVariable("REGLASST");
			if(activo!=null && activo.equalsIgnoreCase("1")){
				
				reglaRetencion=buscarRegla( peticion);
								
				if(reglaRetencion!=null){
					log.debug("La icidencia: "+ peticion+" esta siendo afectada por la regla: "+ reglaRetencion.getDescripcion());
					Peticion_stLocal  peticionLocal=peticionHome.findByPrimaryKey(new Peticion_stKey(peticion));
					peticionLocal.setRegla_id(reglaRetencion.getRegla_id());
					
				}else{
					log.debug("No hay ninguna regla de retencion que afecte la peticion");
				}				
								
			}else{
				log.debug("No se esta validando ninguna regla de retencion ya que la variable no existe o no esta activa la validacion");
			}
		} catch (Exception e) {
			
			log.debug("Error intentando verificar regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	
		return reglaRetencion;
	}
	/* 
	* se encarga de marcar una Incidencia como afectada por Granite
	 */
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx{
		
		try{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Peticion_stLocalHome peticionHome = null;//(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			LocalidadLocalHome localidadHome=null;
			Long todasGrantite=new Long (VpistbbaConfig.getVariable("GRANITE"));
			if (ComunInterfaces.sinGranite== todasGrantite.longValue()){
				
				if(peticionHome==null)
					peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
				Peticion_stLocal peticionLocal=peticionHome.findByPrimaryKey(new Peticion_stKey(nroPeticion));				
				
				if(localidadHome==null)
					localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			
				LocalidadLocal localidadLocal=localidadHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc()));				
			
				//Si el valor es uno es porque usa Granite
				if(localidadLocal.getGranite_code()==ComunInterfaces.conGranite){
					log.debug("marcando la peticion: "+ nroPeticion+" como GRANITE");
					peticionLocal.setGranite_code(new Integer(localidadLocal.getGranite_code()));
				}else{
					//RETA
					peticionLocal.setGranite_code(new Integer(localidadLocal.getGranite_code()));
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Error validando si la peticion esta siendo afectada por granite",e);
			throw new ATiempoAppEx(e.getMessage());
		}
	}
	
	
	
	private Regla_RetencionesLocal buscarRegla(Long peticion) throws ATiempoAppEx {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql = null;
		String aster="*";
		Regla_RetencionesLocal reglaLocal=null;
		
		try {
			Peticion_stLocalHome peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			
			if(peticionStHome==null)
				peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
		
			Peticion_stLocal peticionLocal=peticionStHome.findByPrimaryKey(new Peticion_stKey(peticion));		
			
			conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			
			
			sql = armarSQLCandidatosOpt(); 
			pStmt = conn.prepareStatement(sql);
				pStmt.setLong(1, ComunInterfaces.estadoRelaretencionActiva);
				pStmt.setLong(2, new Long("2").longValue());
				
				DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
				DepartamentoKey departamentoKey = (DepartamentoKey) departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticionLocal.getCod_dpt())).getPrimaryKey();
				pStmt.setString(3,departamentoKey.cod_dpt);
				
				pStmt.setString(4,aster);
				
				LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
				LocalidadKey localidadKey=(LocalidadKey) localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc())).getPrimaryKey();
				pStmt.setString(5,localidadKey.cod_loc);
				
				pStmt.setString(6,aster);
//				pStmt.setString(7,peticionLocal.getTica_id());
				pStmt.setString(7,"P");
				pStmt.setString(8,aster);
				
				
				//---Omologacion para productos ST---
				String producto = "*";
				String familiaInci=peticionLocal.getIde_pro_cmr_cd();
				if(familiaInci!=null)
				{
					if(familiaInci.equals("L"))
						producto="LB";
					else
						producto = familiaInci;
//					else if(familiaInci.equals("BA"))
//					producto="BA";
//					else if(familiaInci.equals("TV"))
//					producto="TV";
				}
				//------
				
				pStmt.setString(9,producto);
				pStmt.setString(10,aster);
				pStmt.setTimestamp(11,new Fecha().getTimestamp());

			
			rs = pStmt.executeQuery();
			Long idRegla = null;
			if (rs.next()) {
				idRegla = new Long(rs.getLong(1));
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Regla_RetencionesLocalHome reglaRetencionHome = (Regla_RetencionesLocalHome) HomeFactory.getHome(Regla_RetencionesLocalHome.JNDI_NAME);
			
				reglaLocal=reglaRetencionHome.findByPrimaryKey(new Regla_RetencionesKey(idRegla));		
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
			
		} catch (NamingException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		} catch (FinderException e) {
			e.printStackTrace();
			log.debug("Error de sql buscando regla de retencion",e);
			throw new ATiempoAppEx(e.getMessage());
		} finally {
			closeJDBCResources(conn, pStmt, rs);
		}
		return reglaLocal;
	}
/**
	 * @param conn
	 * @param pStmt
	 * @param rs
	 */
	private final void closeJDBCResources(Connection conn, PreparedStatement pStmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pStmt != null) pStmt.close();
		} catch (SQLException e1) {
			log.error("Cerrando resultSet o stmt", e1);
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e1) {
			log.error("Cerrando conexion", e1);
		}
	}
/**
	 * @param hab
	 * @param parametros
	 * @return
	 */
	private String armarSQLCandidatosOpt() {
		
		StringBuffer query = new StringBuffer();
		query.append(SQL_USRS_COMUNES);
		return query.toString();
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces#recuperarParametroFromPropertiesBD(java.lang.String)
	 */
	public String recuperarParametroFromPropertiesBD(String codigo) {
		String valor=null;
		try {
			Properties_BDLocalHome properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal properties_BDLocal = properties_BDLocalHome.findByPrimaryKey(codigo);			
			valor=properties_BDLocal.getValor();

		}catch(NamingException e){
			log.debug("NamingException en recuperarParametroFromPropertiesBD "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarParametroFromPropertiesBD "+e.toString());
			return null;
		}
		return valor;
	}

	/**
	 * Raúl: recupera la lista de mensajes configuracion modem ACS.
	 * @param petiNumero
	 * @return
	 */
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero) {
		
		ArrayList listaRetorno = new ArrayList();
		MensajeConfModemACSDTO mensajeConfModemACSDTO = null;
		
		try {
			Mensaje_conf_ACSLocalHome mensajeConfACSLocalHome =(Mensaje_conf_ACSLocalHome)HomeFactory.getHome(Mensaje_conf_ACSLocalHome.JNDI_NAME);
			Collection tmpModemList = mensajeConfACSLocalHome.findByPetiNumero(petiNumero);
			
			Mensaje_estado_stLocalHome mensajeEstadoSTLocalHome = (Mensaje_estado_stLocalHome)HomeFactory.getHome(Mensaje_estado_stLocalHome.JNDI_NAME);
			
			for( Iterator tmpModemIter = tmpModemList.iterator(); tmpModemIter.hasNext(); ){
				Mensaje_conf_ACSLocal mensajeConfACSLocal = (Mensaje_conf_ACSLocal)tmpModemIter.next();		
				mensajeConfModemACSDTO = new MensajeConfModemACSDTO();
				try{
					TR135S tr135s = (TR135S)XMLUtilities.unmarshall(mensajeConfACSLocal.getXml());				
					
					//Mensaje_estado_stKey mensajeSTKey = new Mensaje_estado_stKey(new Long(tr135s.getId()));
					//Mensaje_estado_stLocal mensajeEstadoSTLocal = mensajeEstadoSTLocalHome.findByPrimaryKey(mensajeSTKey);
					
					mensajeConfModemACSDTO.setId(new Long(tr135s.getId()));
					mensajeConfModemACSDTO.setPetiNumero(petiNumero);
					mensajeConfModemACSDTO.setFechaEnvio(mensajeConfACSLocal.getFecha_ingreso());
					mensajeConfModemACSDTO.setModemProcesado(mensajeConfACSLocal.getSerial_modem());
					
					if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParDesactivar).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Baja");
					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.operacionParActivar).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Alta");
					}else if (mensajeConfACSLocal.getAccion().equals(new Integer(ComunInterfaces.altaMigracionPS).toString())){
						mensajeConfModemACSDTO.setOperacionMensaje("Traslado");
					}else if (mensajeConfACSLocal.getAccion().equals("Nuevo")){
						mensajeConfModemACSDTO.setOperacionMensaje("Alta");
					}else{
						mensajeConfModemACSDTO.setOperacionMensaje("No hay acción definida");
					}
					
					if (tr135s.getErrorCode() != null && tr135s.getErrorCode().equals("1")){
						mensajeConfModemACSDTO.setEstadoMensaje("Error");
					}else{
						mensajeConfModemACSDTO.setEstadoMensaje("OK");
					}
					
					mensajeConfModemACSDTO.setDescMensaje(tr135s.getErrorDescription());
					
					listaRetorno.add(mensajeConfModemACSDTO);
				}catch(ClassCastException ex){
					log.debug("El mensaje no corresponde a la tr-135-s, no se procesa");
				}catch(ATiempoAppEx ex){
					log.debug("ATiempoAppEx en recuperarListaMensajesConfModemsACS ");
				}
			}
			return listaRetorno;
		}catch(NamingException e){
			log.debug("NamingException en recuperarListaMensajesConfModemsACS "+e.toString());
			return null;
		}catch(FinderException e){
			log.debug("FinderException en recuperarListaMensajesConfModemsACS "+e.toString());
			return null;
		}
	}

private static final String SQL_USRS_COMUNES = "select * from atiempo.regla_retenciones re where re.estado=? and re.ap_id= ? "+
												"and re.cod_dpt in (?,?)and re.cod_loc in (?,?) and "+
												"re.tica_id in (?,?)and re.peti_id_instancia in(?,?) "+
												"and ? between RE.FECHA_DESDE and RE.FECHA_HASTA";

}
