package com.telefonica_chile.bandeja.bintegrada.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.CanalDTO;
import co.com.atiempo.dto.DepartamentoDTO;
import co.com.atiempo.dto.EmpresaDTO;
import co.com.atiempo.dto.Grupos_especialesDTO;
import co.com.atiempo.dto.LocalidadDTO;
import co.com.atiempo.dto.MunicipioDTO;
import co.com.atiempo.dto.RangoDTO;
import co.com.atiempo.dto.Regla_RetencionesDTO;
import co.com.atiempo.dto.TecnicoDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaKey;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CanalLocal;
import co.com.telefonica.atiempo.ejb.eb.CanalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.EmpresaKey;
import co.com.telefonica.atiempo.ejb.eb.EmpresaLocal;
import co.com.telefonica.atiempo.ejb.eb.EmpresaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesLocal;
import co.com.telefonica.atiempo.ejb.eb.Grupos_EspecialesLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.MunicipioKey;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocal;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PerfilKey;
import co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadKey;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocal;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO;
import com.telefonica_chile.bandeja.dto.AplicacionDTO;
import com.telefonica_chile.bandeja.dto.AuxBandejaDTO;
import com.telefonica_chile.bandeja.dto.BiVsPetDTO;
import com.telefonica_chile.bandeja.dto.CodigoStDTO;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.dto.ReasignaResultDTO;
import com.telefonica_chile.bandeja.dto.UserCoDto;
import com.telefonica_chile.bandeja.mantenedores.TablaUsuariosSupervisados;
import com.telefonica_chile.bandeja.supervisor.sessions.SupervisorException;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class BIntegradaSessionBean implements javax.ejb.SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6861518936865656618L;
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private Wf_instancia_actividadLocalHome wfHome;
	private PeticionLocalHome peticionHome;

	private javax.ejb.SessionContext mySessionCtx;
	
	private static final Long AP_ID_SSDD = new Long(4);

	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	public void ejbCreate() throws javax.ejb.CreateException {
		try
		{
			wfHome=(Wf_instancia_actividadLocalHome) HomeFactory.getHome(Wf_instancia_actividadLocalHome.JNDI_NAME);
			peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		} catch (NamingException e)
		{
			log.fatal("NamingException",e);
		}
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbRemove() {
	}
	
//	/**Retornamos todos los Isp's**/
//	public ArrayList recuperarIsps() throws BandejaException{
//		return new ArrayList();		
//	}

	/**Retornamos todas las actividades */
	/*
	public ArrayList recuperarActividades() throws BandejaException{
		Collection listaAct = getActividadesEntity();			

		ArrayList lista = new ArrayList();
		for (Iterator iter = listaAct.iterator(); iter.hasNext();) {
			ActividadLocal actLocal = (ActividadLocal) iter.next();
			
			ActividadDTO dto = new ActividadDTO(); 
			ActividadKey actividadKey=(ActividadKey) actLocal.getPrimaryKey();
			dto.setId(actividadKey.act_id  );
			dto.setCodigo( actLocal.getAct_codigo() );
			dto.setDescripcion( actLocal.getAct_descripcion() );
			
			lista.add(dto);
		}
		return lista;		
	}
	*/
	
	/**Retornamos todos los Aplicaciones**/	
	public ArrayList recuperarAplicaciones() throws BandejaException {
		Collection listAplicacionesEjb = getAplicacionesEntity();		
	
		ArrayList listAplicaciones = new ArrayList();
		for (Iterator iter = listAplicacionesEjb.iterator(); iter.hasNext();) {
			AplicacionLocal objAplicacionEjb = (AplicacionLocal) iter.next();

			AplicacionDTO objAplicacion = new AplicacionDTO();
			AplicacionKey aplicacionKey=(AplicacionKey) objAplicacionEjb.getPrimaryKey();
			objAplicacion.setApId(aplicacionKey.ap_id);
			objAplicacion.setApNombre(objAplicacionEjb.getAp_nombre());
			try {
				objAplicacion.setApUrlBase(ApplicationConfig.getUrlBase(objAplicacion.getApNombre()));
			} catch (Exception e) {
				objAplicacion.setApUrlBase(objAplicacionEjb.getAp_url_base());			
			}
			listAplicaciones.add(objAplicacion);				
		}
		if(listAplicaciones.size()>0)
			Collections.sort(listAplicaciones);
		return listAplicaciones;		
	}
	
//	public ArrayList recuperarGrupoSegmento() throws BandejaException {
//		Collection listaGS = getGrupoSegmentoEntity();
//
//		ArrayList listado = new ArrayList();
//		for (Iterator iter = listaGS.iterator(); iter.hasNext();) {			
//			Grupo_segmentoLocal gsLocal = (Grupo_segmentoLocal) iter.next();				
//			SegmentoDTO gsDto = new SegmentoDTO();
//			Grupo_segmentoKey key=(Grupo_segmentoKey) gsLocal.getPrimaryKey();
//			Integer gsId = key.grse_id;
//			gsDto.setSegmId(new Long( gsId.intValue() ));
//			gsDto.setSegmDescripcion( gsLocal.getGrse_nombre() );
//
//			listado.add( gsDto );
//		}			 			
//		return listado;		
//	}
//	TODO CR-11458 PABLO L 
	
	/*
	  public ArrayList recuperarGruposEspeciales() throws BandejaException
	  {
		  ArrayList retorno=new ArrayList();
		  Collection listadocatalogosEjb = getGruposEspecialesEntity();
		  for(Iterator iterator=listadocatalogosEjb.iterator();iterator.hasNext();)
		  {
			  Grupos_EspecialesLocal local=(Grupos_EspecialesLocal)iterator.next();
			  retorno.add(local);
		  }		 
		 return retorno;
	  }
	  */
	  //Fin CR-11458
	
	  public ArrayList recuperarGruposEspeciales() throws BandejaException
	  {
		  ArrayList retorno=new ArrayList();
		  Collection listadocatalogosEjb = getGruposEspecialesEntity();
		  for(Iterator iterator=listadocatalogosEjb.iterator();iterator.hasNext();)
		  {
		  	  Grupos_EspecialesLocal local=(Grupos_EspecialesLocal)iterator.next();		 
		  	  Grupos_especialesDTO gruposEspecialesDTO= new Grupos_especialesDTO();
			  gruposEspecialesDTO.setId((Long)local.getPrimaryKey());
			  gruposEspecialesDTO.setDescripcion(local.getGrpe_descripcion());
			  gruposEspecialesDTO.setTipo(local.getGrpe_tipo());
			  retorno.add(gruposEspecialesDTO);
		  }		 
		 return retorno;		 
	  }
	  
	  
	//cr16847 p.cawen
	public ArrayList recuperarCanales() throws BandejaException{
		ArrayList retorno=new ArrayList();
		Collection listadoCanales = getCanalEntity();
		for(Iterator iterator = listadoCanales.iterator(); iterator.hasNext();){
			CanalLocal local = (CanalLocal)iterator.next();
			CanalDTO canalDTO = new CanalDTO();
			canalDTO.setCodCanal(local.getCod_cnl_ven_cd());
			canalDTO.setDescCanal(local.getDsc_cnl_ven_cd());			
			retorno.add(canalDTO);
		}
		return retorno;
	}
	//Fin cr16847 p.cawen
	
	//CR23110 - PCawen - Filtro por Regla
	public ArrayList recuperarReglasRet() throws BandejaException{
		ArrayList retorno=new ArrayList();
		Collection listadoReglas = getReglaRetEntity();
		for(Iterator iterator = listadoReglas.iterator(); iterator.hasNext();){
			Regla_RetencionesLocal local = (Regla_RetencionesLocal)iterator.next();
			Regla_RetencionesDTO reglaDTO = new Regla_RetencionesDTO();
			reglaDTO.setRegla_id(local.getRegla_id());
			reglaDTO.setDescripcion(local.getDescripcion());
			retorno.add(reglaDTO);

		}
		return retorno;
	}
	  
	public ArrayList recuperarDepartamento() throws BandejaException {
		Collection listaDP = getDepartamentoEntity();

		ArrayList listado = new ArrayList();
		for (Iterator iter = listaDP.iterator(); iter.hasNext();) {			
			DepartamentoLocal dpLocal = (DepartamentoLocal) iter.next();				
			DepartamentoKey key = (DepartamentoKey) dpLocal.getPrimaryKey();
			DepartamentoDTO departamentoDTO=new DepartamentoDTO();
			departamentoDTO.setCodDpt(key.cod_dpt);
			departamentoDTO.setNombreDepartamento(dpLocal.getNombre_departamento());
			listado.add( departamentoDTO );
		}			 			
		if(listado.size()>0)
			Collections.sort(listado);
		return listado;		
	}
	
	/**Obtener Peticiones de un Usuario**/
	public Tabla setearDatoTablaPeticion (HashMap filtros, int paginaActual , int paginacion,int orden ) throws BandejaException {
		//Tabla tabla = new TablaPeticion(paginaActual);
		Tabla tabla = new TablaBandeja(paginaActual,orden);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}
	}

//	/**Obtener Listado BIntegrada**/
//	public ArrayList getListadoBIntegrada (HashMap filtros) throws BandejaException {
//		//Tabla tabla = new TablaPeticion(paginaActual);
//		TablaBandeja tabla = new TablaBandeja();
//		ArrayList listaPet=null;
//		try {
//			listaPet=tabla.getListadoBIntegrada(filtros);
//			return listaPet;
//		} catch (Exception e) {
//			log.warn("Problemas recuperando listado de Bandeja Integrada", e );
//			throw new BandejaException("No se pudo recuperar listado de Bandeja Integrada", e);
//		}
//	}

//	/**Obtener Listado VV**/
//	public HashMap getListadoVV (HashMap filtros) throws BandejaException {
//		//Tabla tabla = new TablaPeticion(paginaActual);
//		TablaBandeja tabla = new TablaBandeja();
//		HashMap listaVV=null;
//		try {
//			listaVV=tabla.getListadoVV(filtros);
//			return listaVV;
//		} catch (Exception e) {
//			log.warn("Problemas recuperando listado de Bandeja Integrada", e );
//			throw new BandejaException("No se pudo recuperar listado de Bandeja Integrada", e);
//		}
//	}

	/** Obtener Peticiones de un Usuario **/
	public Tabla buscarListadoPeticiones(HashMap filtros) throws BandejaException {
		Tabla tabla = new TablaPeticion();
		try {
			tabla.retrieveListado(filtros);
			return tabla;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}
	}

	public ArrayList listaPeticion (HashMap filtros, int paginaActual , int paginacion ) throws BandejaException {
		log.info("ArrayList listaPeticion");
		ArrayList lista = new ArrayList(); 
		Tabla tabla = new TablaPeticion(paginaActual);
		tabla.setLargoPagina(paginacion);
		try {
			lista =(ArrayList) tabla.retrieveList(filtros);
			return lista;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}
	}
	
//	public ArrayList recuperarCamposVariables() throws BandejaException {
//		ArrayList lista = new ArrayList();
//		try {
//			Campo_variableLocalHome home
//					= (Campo_variableLocalHome)HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
//			Collection c = home.findAll();
//			for (Iterator it = c.iterator(); it.hasNext(); ) {
//				Campo_variableLocal campoEjb = (Campo_variableLocal) it.next();
//				Campo_variableKey pk = (Campo_variableKey)campoEjb.getPrimaryKey();
//
//				CampoDTO campo = new CampoDTO();
//				campo.setId(pk.cv_id.shortValue());
//				campo.setNombreInterno(campoEjb.getCv_nombre_int());
//				campo.setNombreExterno(campoEjb.getCv_nombre_ext());
//
//				lista.add(campo);
//			}
//		} catch (Exception e) {
//			log.warn("Problemas recuperando campos variables", e);
//			throw new BandejaException("Problemas recuperando campos variables");
//		}
//		return lista;
//	}

	public boolean nuevaClave(String username, String email) throws BandejaException, UsuarioNoEncontradoException {
		ClaveHelper helper = new ClaveHelper();
		return helper.nuevaClave(username, email);
	}
	
	public boolean cambiaClaveUsuario(Long idUsuario, String oldClave, String newClave) throws BandejaException, UsuarioNoAutenticadoException, UsuarioNoEncontradoException {
		//ClaveHelper helper = new ClaveHelper();
		ClaveHelper helper = new ClaveHelper("cambiaClave");
		return helper.cambiaClaveUsuario(idUsuario, oldClave, newClave);
	}

	public Tabla recuperaUsuariosSupervisados(String loginUsuario, Long idRol, int pagina, HashMap datosFiltro) throws SupervisorException {
		UsuarioLocal usuarioEntity = getUsuarioEntity(loginUsuario);
		//String actividades = getCondActividades(idRol);
		
		Tabla tabla = new TablaUsuariosSupervisados(pagina);
		HashMap filtro = new HashMap();
		UsuarioKey usuarioKey=(UsuarioKey) usuarioEntity.getPrimaryKey();
		filtro.put("idUsuario", ""+usuarioKey.usua_id);
		filtro.put("idRol", "" + idRol);
		//filtro.put("actividades", actividades);

		filtro.put("tipo_trabajo", ""+datosFiltro.get("BI_TIPO_TRABAJO"));
		filtro.put("familia", ""+datosFiltro.get("BI_FAMILIA_PS"));
		filtro.put("fecha_compromiso", ""+datosFiltro.get("BI_FECHA_COMPROMISO"));
		filtro.put("estado_peticion", ""+datosFiltro.get("BI_ESTADO_PETICION"));

		
		try {
			tabla.retrieve(filtro);
		} catch (TablaException e) {
			log.warn("Problemas recuperando supervisores", e);
			throw new SupervisorException("Problemas recuperando supervisores: " + e);
		}
		return tabla;
	}



	public Tabla recuperaUsuariosSupervisadosByPaginacion(String loginUsuario, Long idRol, int pagina, int paginacion) throws SupervisorException {

		UsuarioLocal usuarioEntity = getUsuarioEntity(loginUsuario);
		//String actividades = getCondActividades(idRol);

		Tabla tabla = new TablaUsuariosSupervisados(pagina);
		
		//para el filtro se ocupa el metodo getPeticosAsignadas de la clase TablaUsuariosSupervisados
		tabla.setLargoPagina(paginacion);
		HashMap filtro = new HashMap();
		UsuarioKey usuarioKey=(UsuarioKey) usuarioEntity.getPrimaryKey();
		filtro.put("idUsuario", ""+usuarioKey.usua_id);
		filtro.put("idRol", ""+idRol);
		//filtro.put("actividades", actividades);
		
		try {
			tabla.retrieve(filtro);
		} catch (TablaException e) {
			log.warn("Problemas recuperando supervisores", e);
			throw new SupervisorException("Problemas recuperando supervisores: " + e);
		}
		return tabla;
	}


	public Tabla recuperaUsuariosSupervisadosByPaginacion(String loginUsuario, Long idRol, int pagina, int paginacion, HashMap datosFiltro) throws SupervisorException {
		
		UsuarioLocal usuarioEntity = getUsuarioEntity(loginUsuario);
		//String actividades = getCondActividades(idRol);

		Tabla tabla = new TablaUsuariosSupervisados(pagina);
		
		//para el filtro se ocupa el metodo getPeticosAsignadas de la clase TablaUsuariosSupervisados
		tabla.setLargoPagina(paginacion);
		HashMap filtro = new HashMap();
		
		UsuarioKey usuarioKey=(UsuarioKey) usuarioEntity.getPrimaryKey();
		filtro.put("idUsuario", ""+usuarioKey.usua_id);
		filtro.put("idRol", ""+idRol);
		//filtro.put("actividades", actividades);
		
		filtro.put("tipo_trabajo", ""+datosFiltro.get("BI_TIPO_TRABAJO"));
		filtro.put("familia", ""+datosFiltro.get("BI_FAMILIA_PS"));
		filtro.put("fecha_compromiso", ""+datosFiltro.get("BI_FECHA_COMPROMISO"));
		filtro.put("estado_peticion", ""+datosFiltro.get("BI_ESTADO_PETICION"));
		
		
		try {
			tabla.retrieve(filtro);
		} catch (TablaException e) {
			log.warn("Problemas recuperando supervisores", e);
			throw new SupervisorException("Problemas recuperando supervisores: " + e);
		}
		return tabla;
	}

	
	private UsuarioLocal getUsuarioEntity(String loginUsuario) throws SupervisorException {
		try {
			UsuarioLocalHome home = (UsuarioLocalHome)
				HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuarioEntity = home.findByLogin(loginUsuario);
			return usuarioEntity;
		} catch (Exception e) {
			log.error("No se pudo recuperar usuario " + loginUsuario, e);
			throw new SupervisorException("No se pudo recuperar usuario " + loginUsuario + ": " + e);
		}
	}
	
	private String getCondActividades(Long idRol) throws SupervisorException {
		String cond="";
		try {
			
			//Determinamos si el rol pertenece a ATST. Si es as�, buscamos todas las actividades de ATST
			//y las retornamos, para no afectar el funcionamiento normal de BIntegrada. 
			//Esto debe hacerse ya que ATST tiene m�s de un rol asociado a cada actividad, por lo que no podemos
			//asociar los roles con actividad en la misma tabla de actividad.
			//Pero como ATST tiene solo un rol por usuario, podemos asumir que cada usuario puede estar 
			//en todas las posibles actividades de ATST, para efectos de filtro
/*
 *  10-4-2007 VMolina: Atiempo Colombia 
 *  Cada actividad est� asociada a un rol			
*/
			String listaActATST=listaActividadesATST(idRol);
			if(listaActATST!=null)	
			   return(listaActATST);   					
						
// Si no esta en ST, busco en VPI						
			Long idAplicacionST= new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));						
			ActividadLocalHome home = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			Collection col= home.findActividades(idRol,idAplicacionST);
			for (Iterator iter = col.iterator(); iter.hasNext();) {
				ActividadLocal actEnt = (ActividadLocal)iter.next();
				ActividadKey actividadKey=(ActividadKey) actEnt.getPrimaryKey();
				cond+=String.valueOf((Long)actividadKey.act_id ) + ",";
			}
				  
			return cond.substring(0,cond.length()-1);
		} catch (Exception e) {
			log.error("No se pudo recuperar en getActividades usuario " + idRol, e);
			return "-100";
		}
	}
		
	/**
	 *  Metodo que retorna la lista de todas las actividades pertenecientes a ATST.
	 *  Devuelve null si el rol no es de ATST
	 *  @param idRol
	 *  @return
	 * 
	 *  10-4-2007 VMolina: Atiempo Colombia 
	 *  Cada actividad est� asociada a un rol
	 */	
	public String listaActividadesATST(Long idRol){
		log.debug("Obteniendo lista de actividades ATST: rol=[" + idRol + "]");
		String listaAct=null;
		String cond="";
		// Verifico que el rol sea de ATST
		try {
			//Buscamos el id de la aplicacion ATST
			Long idAplicacionST= new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
			
			//Buscamos el rol correspondiente al idRol
//			RolLocalHome rolEntityLocalHome = (RolLocalHome) HomeFactory.getHome(RolLocalHome.JNDI_NAME);
//			RolKey rolKey=new RolKey(idRol);
//			RolLocal rolEntityLocal = rolEntityLocalHome.findByPrimaryKey(rolKey);
			 
			// Si es ATST: Obtengo todas las actividades de ATST. Creo el string y lo devuelvo. Sino devuelvo null 
//			if (idAplicacionST.equals((Long)rolEntityLocal.getFk_ap_2_rol().getPrimaryKey())) {
				ActividadLocalHome home = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				Collection col= home.findActividades( idRol, idAplicacionST);
				for (Iterator iter = col.iterator(); iter.hasNext();) {
					ActividadLocal actEnt = (ActividadLocal)iter.next();
					ActividadKey actividadKey=(ActividadKey) actEnt.getPrimaryKey();
					cond+=String.valueOf((Long) actividadKey.act_id) + ",";
					listaAct=cond.substring(0,cond.length()-1);
				}
//			}
		} catch (Exception e) {
			log.debug("Error al obtener listaActividadesATST",e);
		} 
		return listaAct;
	}

	private Collection getGrupoSegmentoEntity() throws BandejaException {
		try {
			Grupo_segmentoLocalHome gsHome = 
				(Grupo_segmentoLocalHome) HomeFactory.getHome(Grupo_segmentoLocalHome.JNDI_NAME);
			return gsHome.findAllOrderByPorcentaje();
		} catch (Exception e) {
			log.warn("Problemas Recuperando GrupoSegmento.", e);
			throw new BandejaException("Problemas recuperando GrupoSegmento", e);
		}
	}

	private Collection getDepartamentoEntity() throws BandejaException {
		try {
			DepartamentoLocalHome dpHome = 
				(DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			return dpHome.findAll();
		} catch (Exception e) {
			log.warn("Problemas Recuperando Departamento.", e);
			throw new BandejaException("Problemas recuperando Departamento", e);
		}
	}


	private Collection getAplicacionesEntity() throws BandejaException {
		try {
			AplicacionLocalHome home
						= (AplicacionLocalHome) HomeFactory.getHome(AplicacionLocalHome.JNDI_NAME);
			return home.findAll();
		} catch (Exception e) {
			log.warn("Problemas recuperando home aplicacion: " + AplicacionLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Aplicaciones", e);
		}
	}
	
	/*
	private Collection getActividadesEntity() throws BandejaException {
		try {
			ActividadLocalHome actHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			return actHome.findAll();
		} catch (Exception e) {
			log.warn("Problemas recuperando home Actividad: " + ActividadLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Actividades", e);
		}
	}
	*/
	
//	TODO PABLO L CR-11458
	 private Collection getGruposEspecialesEntity() throws BandejaException {
	 try {
		Grupos_EspecialesLocalHome grupoHome = (Grupos_EspecialesLocalHome) HomeFactory.getHome(Grupos_EspecialesLocalHome.JNDI_NAME);
		//return grupoHome.findAll();
		return grupoHome.findByTipo("Bandeja");
	 } catch (Exception e) {
	 //log.warn("Problemas recuperando home Catalogo: " + CatalogoLocalHome.JNDI_NAME, e);
	  throw new BandejaException("Problemas recuperando Catalogos", e);
	 }
			
	}
	
	//cr16847 p.cawen
	private Collection getCanalEntity() throws BandejaException{
		try{
			CanalLocalHome canalHome= (CanalLocalHome) HomeFactory.getHome(CanalLocalHome.JNDI_NAME);
			return canalHome.findAll();
		}catch (Exception e){
			throw new BandejaException("Problemas recuperando Canales", e);
		}
	}
	
	//CR23110 - PCawen - Filtro por regla
	private Collection getReglaRetEntity() throws BandejaException{
		try{
			Regla_RetencionesLocalHome reglasRetHome= (Regla_RetencionesLocalHome) HomeFactory.getHome(Regla_RetencionesLocalHome.JNDI_NAME);
			return reglasRetHome.findByEstado(new Integer(1));
		}catch (Exception e){
			throw new BandejaException("Problemas recuperando Regla Retenciones", e);
		}		
	}
	
	public ArrayList getPeticiones(Vector arregloPeticiones) throws BandejaException{
	
		ArrayList peticiones = new ArrayList();	
		PeticionDTO peticionDTO = new PeticionDTO();
		try {
			BintegradaLocalHome home = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			AplicacionLocalHome apphome = (AplicacionLocalHome) HomeFactory.getHome(AplicacionLocalHome.JNDI_NAME);
			
			for (int i = 0; i < arregloPeticiones.size(); i++) {
				Long idBintegrada = new Long(arregloPeticiones.get(i).toString());
				BintegradaKey bintegradaKey=new BintegradaKey(idBintegrada);		
				BintegradaLocal bintegradaEntityLocal = home.findByPrimaryKey(bintegradaKey);
				
				peticionDTO = new PeticionDTO();
				BintegradaKey key=(BintegradaKey) bintegradaEntityLocal.getPrimaryKey();
				AplicacionKey aplicacionKey=(AplicacionKey) bintegradaEntityLocal.getFk_bintegrada_ap().getPrimaryKey();
				UsuarioKey usuarioKey=(UsuarioKey) bintegradaEntityLocal.getFk_bi_usuario().getPrimaryKey();
				peticionDTO.setBiId(key.bi_id);
				peticionDTO.setApId(aplicacionKey.ap_id);
				peticionDTO.setUsuaId(usuarioKey.usua_id);
				peticionDTO.setBiNroPeticion(bintegradaEntityLocal.getBi_nro_peticion());
				peticionDTO.setBiClienteNombre(bintegradaEntityLocal.getBi_cliente_nombre());
				peticionDTO.setBiClienteApellidos(bintegradaEntityLocal.getBi_cliente_apellidos());
				peticionDTO.setBiClienteArea(bintegradaEntityLocal.getBi_cliente_area());
				peticionDTO.setBiClienteRut(bintegradaEntityLocal.getBi_cliente_rut());
				peticionDTO.setBiClienteRutDv(bintegradaEntityLocal.getBi_cliente_rutdv());
				peticionDTO.setBiFamiliaPs(bintegradaEntityLocal.getBi_familia_ps());
				peticionDTO.setBiUrlDetalle(bintegradaEntityLocal.getBi_url_detalle());
				peticionDTO.setEstadoPeticion(bintegradaEntityLocal.getBi_estado_peticion());
				peticionDTO.setTipoTrabajo(bintegradaEntityLocal.getBi_tipo_trabajo());
				peticionDTO.setBiClienteServicio(bintegradaEntityLocal.getBi_cliente_servicio());						
				peticionDTO.setBiFechaCompromiso(bintegradaEntityLocal.getBi_fecha_compromiso());
				peticionDTO.setBiFechaInicio(bintegradaEntityLocal.getBi_fecha_inicio());
				peticionDTO.setBiFechaAsignacion(bintegradaEntityLocal.getBi_fecha_asignacion());				
				
				Collection valoresVariables = bintegradaEntityLocal.getValor_variable();
				if (valoresVariables.size() > 0) {
					String[][] valor = new String[valoresVariables.size()][2];
					int iValor = 0;
					for (Iterator iter = valoresVariables.iterator(); iter.hasNext();) {
						Valor_variableLocal element = (Valor_variableLocal) iter.next();
						valor[iValor][0] = element.getFk_valcampo_campo().getCv_nombre_int();
						valor[iValor][1] = element.getValor();
						iValor++;
					}
					
					peticionDTO.setValoresVariables(valor);
				}
				
				ActividadLocal actividadLocal = bintegradaEntityLocal.getFk_bi_act();
				ActividadKey actividadKey=(ActividadKey) actividadLocal.getPrimaryKey();
				peticionDTO.setActividadId(String.valueOf(actividadKey.act_id));
				peticionDTO.setActividadCodigo(actividadLocal.getAct_codigo());
				peticionDTO.setActividadNombreReversa(actividadLocal.getAct_nombre_reversa());				
				
				RolLocal  rolLocal = actividadLocal.getFk_act_rol();
				RolKey rolKey=(RolKey) rolLocal.getPrimaryKey();
				peticionDTO.setRolId(String.valueOf(rolKey.rol_id));
				peticionDTO.setRolCodigo(rolLocal.getRol_codigo());
				peticionDTO.setRolNombre(rolLocal.getRol_nombre());				
				
				UsuarioLocal usuarioLocal = bintegradaEntityLocal.getFk_bi_usuario();
				peticionDTO.setUsuaLogin(usuarioLocal.getUsua_login());
				peticionDTO.setUsuaNombre(usuarioLocal.getUsua_nombre());
								
				SegmentoLocal segmentoLocal = bintegradaEntityLocal.getFk_bint_segmento();
				SegmentoKey segmentoKey=(SegmentoKey) segmentoLocal.getPrimaryKey();
				peticionDTO.setBiSegmentoId(segmentoKey.segm_id);
				peticionDTO.setBiSegmentoCliente(segmentoLocal.getSegm_codigo());									

//				peticionDTO.setFechaActual();
//				peticionDTO.setFechaFinal();
//				peticionDTO.setBiUrlPuntaje();
//				peticionDTO.setBiPrioridad();
//				peticionDTO.setBiSemaforoActividad();
//				peticionDTO.setBiSemaforoCompromiso();
//				peticionDTO.setOoss();
//				peticionDTO.setEstadoPeticionNombre();
//				peticionDTO.setIdTica();
//				peticionDTO.setCentral();
//				peticionDTO.setTz();
				
				AplicacionLocal aplicacion = apphome.findByPrimaryKey(new AplicacionKey(peticionDTO.getApId()));
				peticionDTO.setAplicacionCodigo(aplicacion.getAp_nombre());								
											
				peticiones.add(peticionDTO);			
			}
			
			
		} catch (Exception e) {
			log.warn("Problemas recuperando home BintegradaEntityLocalHome: " + BintegradaLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Colecci�n de Peticiones", e);
		}
		return peticiones;
	}

	
	public Vector getPeticionesUsuario(String numId, Long idRol) throws BandejaException{
	
			Vector peticiones = new Vector();	
			
			try {
				BintegradaLocalHome home = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				
				Long idBintegrada = new Long(numId);
				Collection c = home.findIdUsuario(idBintegrada,idRol);
				
				for (Iterator iter = c.iterator(); iter.hasNext();) {
					PeticionDTO dato = new PeticionDTO();
					BintegradaLocal local = (BintegradaLocal)iter.next();
					BintegradaKey bintegradaKey=(BintegradaKey) local.getPrimaryKey();
					peticiones.add(bintegradaKey.bi_id);
				}				
					
			} catch (Exception e) {
				log.warn("getPeticionesUsuario : (" + numId + ") Problemas recuperando home BintegradaEntityLocalHome: " + BintegradaLocalHome.JNDI_NAME, e);
				throw new BandejaException("getPeticionesUsuario Problemas recuperando Colecci�n de Peticiones", e);
			}
			return peticiones;
	}
	
	/**
	 * Obtiene el campo URL para una peticion dada.
	 * 
	 * @param idBintegrada id de la entrada en bintegrada
	 * @return el campo URL de la tabla bintegrada para esa peticion
	 * @throws BandejaException si la peticion no se encuentra u ocurre un error.
	 */
	public String getURLDetallePeticion(Long idBintegrada) throws BandejaException {
		try {
			BintegradaLocalHome home = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			BintegradaKey bintegradaKey=new BintegradaKey(idBintegrada);
			BintegradaLocal bintegrada = home.findByPrimaryKey(bintegradaKey);
			
			return bintegrada.getBi_url_detalle();
		} catch (Exception e)
		{
			throw new BandejaException("Error al obtener URL para peticion id=[" + idBintegrada + "]");
		}
	}
	
	public void setearFechaApertura(Long idActividad, Long idPeticion,Long apId) throws NamingException, FinderException{
			
		BintegradaLocalHome bHome = 
			(BintegradaLocalHome) HomeFactory.getHome
				(BintegradaLocalHome.JNDI_NAME );
		BintegradaLocal bLocal = bHome.findByIdActIdPetiAp(idActividad, idPeticion,apId);

		// Solo lo actualizamos si la Fecha de Apertura es Nula.
		if ( bLocal.getBi_fecha_apertura() == null )
			bLocal.setBi_fecha_apertura(new Fecha().getTimestamp());
	}
	
	public void setearFechaInicio(Long idActividad, Long idPeticion,Long apId) throws NamingException, FinderException{
			
		BintegradaLocalHome home = 
			(BintegradaLocalHome) HomeFactory.getHome
				(BintegradaLocalHome.JNDI_NAME );
		BintegradaLocal bLocal = home.findByIdActIdPetiAp(idActividad, idPeticion,apId);
		
  		bLocal.setBi_fecha_inicio(new Fecha().getTimestamp());
	}
	
	
	public ArrayList obtenerBIntegrada(String area, String telefono)throws NamingException, FinderException{
		ArrayList lista =null;
		
		return lista;
	}
	
	
	public void cambiaJerarquiaUsuario(Long usuaId, Long idRolnicial, Long idRolFinal) {
		
		DBManager db = new DBManager();
		db.setDataSource("jdbc/bandeja");
		
		/* Elimina la jerarquia parqa su rol actual */
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM ATIEMPO.JER_USUARIO_ROL WHERE JER_SUB_UID = ");
		buffer.append(usuaId);
		buffer.append(" AND JER_ROL = ");
		buffer.append(idRolnicial);
		
		db.ejecutaUpdate(buffer.toString());
		
		/* Busca el supervisor de su nuevo rol. Asume que todos los subditos del mismo rol
		 * tienen el mismo supervisor, por lo tanto, se usa solo el primero.
		 */
		buffer = new StringBuffer();
		buffer.append("SELECT JER_SUP_UID FROM ATIEMPO.JER_USUARIO_ROL WHERE JER_ROL = ");
		buffer.append(idRolFinal);
		//Gustavo - CR 16440 - Cambio la llamada al metodo de ejecuci�n de ejecutaConsultaHash a selectReadUncommited para setear readOnly y nivel de aislamiento
		Hashtable salida = db.selectReadUncommitted(buffer.toString());
		String[] sup = (String[]) salida.get("JER_SUP_UID");
		
		Long idSup = new Long(0);
		for (int  i = 0; sup != null && i < sup.length; i++) {
			idSup = new Long(sup[i]);
			break;
		}
		
		/* Elimina la jerarquia parqa su rol de destino (evita problemas de PK) */
		buffer = new StringBuffer();
		buffer.append("DELETE FROM ATIEMPO.JER_USUARIO_ROL WHERE JER_SUP_UID = ");
		buffer.append(idSup);
		buffer.append(" AND JER_SUB_UID = ");
		buffer.append(usuaId);
		buffer.append(" AND JER_ROL = ");
		buffer.append(idRolFinal);
		
		db.ejecutaUpdate(buffer.toString());		
		
		/* Crea una nueva jerarquia dado el nuevo rol y su supervisor asociado */
		buffer = new StringBuffer();
		buffer.append("INSERT INTO ATIEMPO.JER_USUARIO_ROL (JER_SUP_UID, JER_SUB_UID, JER_ROL) VALUES (");
		buffer.append(idSup);
		buffer.append(",");
		buffer.append(usuaId);
		buffer.append(",");
		buffer.append(idRolFinal);
		buffer.append(")");
		
		db.ejecutaUpdate(buffer.toString());
	}
	
	/**
	 * Cambia el usuario asignado de la peticion especificada, solo si se encuentra una y solo una peticion; si se encuentran 0 o mas de una peticion, se entrega un error.
	 * 
	 * @param idAplicacion aplicacion a la cual pertenece la peticion
	 * @param idPeticion numero de peticion a cambiar
	 * @param idUsuario identificador del nuevo usuario al cual se le asignara la peticion
	 * @throws BandejaException si no se encontraron peticiones; si se encontro mas de una peticion; o si algun otro error ocurre.
	 */
	public void cambiarUsuariodePeticion(Long idAplicacion, Long idPeticion, Long idUsuario) throws BandejaException {
		try {
			BintegradaLocalHome bintegradaEntityLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = bintegradaEntityLocalHome.findByIdAplicacionNroPeticion(idAplicacion, idPeticion);
			if (c.size() > 1) {
				throw new BandejaException("Se encontro mas de una peticion con el identificador " + idPeticion + " en la bandeja");
			}
			if (c.size() <= 0) {
				throw new BandejaException("No se encontraron peticion con el identificador " + idPeticion + " en la bandeja");
			}
			BintegradaLocal bintegradaEntityLocal = (BintegradaLocal)c.iterator().next();			
			UsuarioLocalHome usuarioEntityLocalHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioKey usuarioKey=new UsuarioKey(idUsuario);
			UsuarioLocal usuarioEntityLocal = usuarioEntityLocalHome.findByPrimaryKey(usuarioKey);
			bintegradaEntityLocal.setFk_bi_usuario(usuarioEntityLocal);
		} catch (BandejaException be) {
			throw be;
		} catch (Exception e) {
			throw new BandejaException("Error: " + e, e);
		}
	}

	public void cambiarCentralPeticion(Long idAplicacion, Long idPeticion, CentralLocal central) throws BandejaException {
		try {
			BintegradaLocalHome bintegradaEntityLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = bintegradaEntityLocalHome.findByIdAplicacionNroPeticion(idAplicacion, idPeticion);
			if (c.size() <= 0) {
				//Naipe que hacer.
				return;
			}
			for(Iterator iter = c.iterator();iter.hasNext();){
				BintegradaLocal bintegradaEntityLocal = (BintegradaLocal)iter.next();		
				bintegradaEntityLocal.setCentral(central);
				bintegradaEntityLocal.setDesc_central(central.getDesc_central());
			}
		} catch (Exception e) {
			throw new BandejaException("Error: " + e, e);
		}
	}
    /**
     * Este metodo se encarga de llenar CORRECTAMENTE todos los datos del DTO PeticionDTO, cosa que nadie se digno a hacer. Si falta algun dato, algun relacion,
     * etc; el campo queda en NULL.
     * 
     * @param bintegradaEntityLocal
     *            entity que representa una entrada en la tabla bintegrada.
     * @return un objeto PeticionDTO con la mayor cantidad de datos llenados posibles.
     */
    private PeticionDTO createPeticionDTO(BintegradaLocal bintegradaEntityLocal) {
        PeticionDTO p = new PeticionDTO();

        try {
            ActividadLocal actividadEntityLocal = bintegradaEntityLocal.getFk_bi_act();
            AplicacionLocal aplicacionEntityLocal = bintegradaEntityLocal.getFk_bintegrada_ap();
            RolLocal rolEntityLocal = actividadEntityLocal.getFk_act_rol();
            SegmentoLocal segmentoEntityLocal = bintegradaEntityLocal.getFk_bint_segmento();
            UsuarioLocal usuarioEntityLocal = bintegradaEntityLocal.getFk_bi_usuario();
            Collection valoresVariablesCollection = bintegradaEntityLocal.getValor_variable();
			BintegradaKey bintegradaKey=(BintegradaKey) bintegradaEntityLocal.getPrimaryKey();
			AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEntityLocal.getPrimaryKey();
			UsuarioKey usuarioKey=(UsuarioKey) bintegradaEntityLocal.getFk_bi_usuario().getPrimaryKey();
            Long biId = bintegradaKey.bi_id;
            Long apId = (Long) (aplicacionEntityLocal != null ? aplicacionKey.ap_id : null);
            Long usuaId = usuarioKey.usua_id;
            String usuaLogin = (usuarioEntityLocal != null ? usuarioEntityLocal.getUsua_login() : null);
            String usuaNombre = (usuarioEntityLocal != null ? usuarioEntityLocal.getUsua_nombre() : null);
            Long biNroPeticion = bintegradaEntityLocal.getBi_nro_peticion();
            Date biFechaCompromiso = bintegradaEntityLocal.getBi_fecha_compromiso();
            String biClienteNombre = bintegradaEntityLocal.getBi_cliente_nombre();
            String biClienteApellidos = bintegradaEntityLocal.getBi_cliente_apellidos();
            String biClienteServicio = bintegradaEntityLocal.getBi_cliente_servicio();
            String biClienteArea = bintegradaEntityLocal.getBi_cliente_area();
            String biClienteRut = bintegradaEntityLocal.getBi_cliente_rut();
            String biClienteRutDv = bintegradaEntityLocal.getBi_cliente_rutdv();
            String biSegmentoCliente = (segmentoEntityLocal != null ? segmentoEntityLocal.getSegm_codigo() : null);
            String biFamiliaPs = bintegradaEntityLocal.getBi_familia_ps();
            String biUrlDetalle = bintegradaEntityLocal.getBi_url_detalle();
			Integer biVisible = bintegradaEntityLocal.getBi_visible();
            /*
             * TODO: (amartoq) EL CAMPO biUrlPuntaje ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            String biUrlPuntaje = null;
            Date biFechaInicio = bintegradaEntityLocal.getBi_fecha_inicio();
            Date biFechaApertura = bintegradaEntityLocal.getBi_fecha_apertura();
            Date biFechaAsignacion = bintegradaEntityLocal.getBi_fecha_asignacion();
            /*
             * TODO: (amartoq) EL CAMPO biPrioridad ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            String biPrioridad = null;
            /*
             * TODO: (amartoq) EL CAMPO biSemaforoActividad ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String biSemaforoActividad = null;
            /*
             * TODO: (amartoq) EL CAMPO biSemaforoCompromiso ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String biSemaforoCompromiso = null;
            RolKey rolKey=(RolKey) rolEntityLocal.getPrimaryKey();
            String rolId = (rolEntityLocal != null ? rolKey.rol_id + "" : null);
            String rolCodigo = (rolEntityLocal != null ? rolEntityLocal.getRol_codigo() : null);
            String rolNombre = (rolEntityLocal != null ? rolEntityLocal.getRol_nombre() : null);
            String actividadId = (actividadEntityLocal != null ? actividadEntityLocal.getPrimaryKey() + "" : null);
            String actividadCodigo = (actividadEntityLocal != null ? actividadEntityLocal.getAct_codigo() : null);
            String actividadDescripcion = (actividadEntityLocal != null ? actividadEntityLocal.getAct_descripcion() : null);
            /*
             * TODO: (amartoq) EL CAMPO aplicacionNombre ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            String aplicacionNombre = null;
            String aplicacionCodigo = (aplicacionEntityLocal != null ? aplicacionEntityLocal.getAp_nombre() : null);
            /*
             * TODO: (amartoq) EL CAMPO fechaActual ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            Calendar fechaActual = null;
            /*
             * TODO: (amartoq) EL CAMPO fechaFinal ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            Calendar fechaFinal = null;
            Integer estadoPeticion = bintegradaEntityLocal.getBi_estado_peticion();
            Integer tipoTrabajo = bintegradaEntityLocal.getBi_tipo_trabajo();
            String actividadNombreReversa = (actividadEntityLocal != null ? actividadEntityLocal.getAct_nombre_reversa() : null);
            /*
             * TODO: (amartoq) EL CAMPO ooss ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String ooss = null;
            SegmentoKey segmentoKey=(SegmentoKey) segmentoEntityLocal.getPrimaryKey();
            Long biSegmentoId = (Long) (segmentoEntityLocal != null ? segmentoKey.segm_id : null);
            /*
             * TODO: (amartoq) EL CAMPO estadoPeticionNombre ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            String estadoPeticionNombre = null;
            /*
             * TODO: (amartoq) EL CAMPO idTica ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String idTica = null;
            /*
             * TODO: (amartoq) EL CAMPO central ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String central = null;
            String[][] valoresVariables = null;
            ;
            if (valoresVariablesCollection.size() > 0) {
                valoresVariables = new String[valoresVariablesCollection.size()][2];
                int i = 0;
                for (Iterator iter = valoresVariablesCollection.iterator(); iter.hasNext(); i++) {
                    Valor_variableLocal element = (Valor_variableLocal) iter.next();
                    valoresVariables[i][0] = element.getFk_valcampo_campo().getCv_nombre_int();
                    valoresVariables[i][1] = element.getValor();
                }
            }
            /*
             * TODO: (amartoq) EL CAMPO nombreBloqueSegmento ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String nombreBloqueSegmento = null;
            /*
             * TODO: (amartoq) EL CAMPO idBloqueSegmento ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            Long idBloqueSegmento = null;
            /*
             * TODO: (amartoq) EL CAMPO idTipoAgendamiento ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            Long idTipoAgendamiento = null;
            /*
             * TODO: (amartoq) EL CAMPO nombreTipoAgendamiento ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String nombreTipoAgendamiento = null;
            /*
             * TODO: (amartoq) EL CAMPO idRango ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            Integer idRango = null;
            /*
             * TODO: (amartoq) EL CAMPO nombreRango ESTA EN PeticionDTO Y NADIE LO ESTA USANDO
             */
            String nombreRango = null;
            /*
             * TODO: (amartoq) EL CAMPO horaDesde ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String horaDesde = null;
            /*
             * TODO: (amartoq) EL CAMPO horaHasta ESTA EN PeticionDTO, PERO NO SE COMO OBTENERLO DESDE ACA
             */
            String horaHasta = null;

            p.setActividadCodigo(actividadCodigo);
            p.setActividadDescripcion(actividadDescripcion);
            p.setActividadId(actividadId);
            p.setActividadNombreReversa(actividadNombreReversa);
            p.setApId(apId);
            p.setAplicacionCodigo(aplicacionCodigo);
            p.setAplicacionNombre(aplicacionNombre);
            p.setBiClienteApellidos(biClienteApellidos);
            p.setBiClienteArea(biClienteArea);
            p.setBiClienteNombre(biClienteNombre);
            p.setBiClienteRut(biClienteRut);
            p.setBiClienteRutDv(biClienteRutDv);
            p.setBiClienteServicio(biClienteServicio);
            p.setBiFamiliaPs(biFamiliaPs);
            p.setBiId(biId);
            p.setBiNroPeticion(biNroPeticion);
            p.setBiPrioridad(biPrioridad);
            p.setBiSegmentoCliente(biSegmentoCliente);
            p.setBiSegmentoId(biSegmentoId);
            p.setBiSemaforoActividad(biSemaforoActividad);
            p.setBiSemaforoCompromiso(biSemaforoCompromiso);
            p.setBiUrlDetalle(biUrlDetalle);
            p.setBiUrlPuntaje(biUrlPuntaje);
            p.setFechaActual(fechaActual);
            p.setFechaFinal(fechaFinal);
            p.setCentral(central);
            p.setBiFechaApertura(biFechaApertura);
            p.setBiFechaAsignacion(biFechaAsignacion);
            p.setBiFechaCompromiso(biFechaCompromiso);
            p.setBiFechaInicio(biFechaInicio);
            p.setEstadoPeticion(estadoPeticion);
            p.setEstadoPeticionNombre(estadoPeticionNombre);
            p.setHoraDesde(horaDesde);
            p.setHoraHasta(horaHasta);
            p.setIdBloqueSegmento(idBloqueSegmento);
            p.setIdRango(idRango);
            p.setIdTica(idTica);
            p.setIdTipoAgendamiento(idTipoAgendamiento);
            p.setNombreBloqueSegmento(nombreBloqueSegmento);
            p.setNombreRango(nombreRango);
            p.setNombreTipoAgendamiento(nombreTipoAgendamiento);
            p.setOoss(ooss);
            p.setRolCodigo(rolCodigo);
            p.setRolId(rolId);
            p.setRolNombre(rolNombre);
            p.setTipoTrabajo(tipoTrabajo);
            p.setUsuaId(usuaId);
            p.setUsuaLogin(usuaLogin);
            p.setUsuaNombre(usuaNombre);
            p.setValoresVariables(valoresVariables);
			p.setBiVisible(biVisible);
			p.setVisible(biVisible);
        } catch (Throwable t) {
            log.error("OMITIENDO ERROR INCREIBLE, Este metodo no PUEDE fallar!: " + t, t);
        }
        return p;
    }

    /**
     * Retorna una ArrayList de objetos PeticionDTO, con las peticiones encontradas. Si no se encontraron peticiones, se retorna una lista vacia.
     * 
     * @param idAplicacion
     *            identificador de la aplicacion.
     * @param nroPeticion
     *            numero de la peticion
     * @return un ArrayList de objetos PeticionDTO.
     * @throws BandejaException
     *             si algun error ocurre.
     * @author amartoq
     */
    public ArrayList getPeticionesByIdAplicacionIdNroPeticion(Long idAplicacion, Long nroPeticion) throws BandejaException {
        try {
            ArrayList retList = new ArrayList();

            BintegradaLocalHome bintegradaLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
            Collection c = bintegradaLocalHome.findByIdAplicacionNroPeticion(idAplicacion, nroPeticion);

            for (Iterator it = c.iterator(); it.hasNext();) {
                BintegradaLocal local = (BintegradaLocal) it.next();
                PeticionDTO peticionDTO = createPeticionDTO(local);
                if ( log.isDebugEnabled() )
                	log.debug("Agregando peticion biId=[" + peticionDTO.getBiId() + "]");
                retList.add(peticionDTO);
            }

            return retList;
        } catch (Exception e) {
            String msg = "Error fatal al buscar peticiones idAplicacion=[" + idAplicacion + "] nroPeticion=[" + nroPeticion + "] en bandeja: " + e;

            log.error(msg, e);
            throw new BandejaException(msg, e);
        }
    }

    /**
     * Retorna una ArrayList de objetos PeticionDTO, con las peticiones encontradas. Si no se encontraron peticiones, se retorna una lista vacia.
     * 
     * @param idAplicacion
     *            identificador de la aplicacion.
     * @param nroPeticion
     *            numero de la peticion
     * @return un ArrayList de objetos PeticionDTO.
     * @throws BandejaException
     *             si algun error ocurre.
     * @author amartoq
     */
    public ArrayList getPeticionesVisiblesByIdAplicacionIdNroPeticion(Long idAplicacion, Long nroPeticion) throws BandejaException {
        try {
            ArrayList retList = new ArrayList();
    
            BintegradaLocalHome bintegradaLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
            Collection c = bintegradaLocalHome.findVisiblesByIdAplicacionNroPeticion(idAplicacion, nroPeticion);
    
            for (Iterator it = c.iterator(); it.hasNext();) {
                BintegradaLocal bintegradaEntityLocal = (BintegradaLocal) it.next();
                PeticionDTO peticionDTO = createPeticionDTO(bintegradaEntityLocal);
                if (log.isDebugEnabled() )
                	log.debug("Agregando peticion biId=[" + peticionDTO.getBiId() + "]");
                retList.add(peticionDTO);
            }
    
            return retList;
        } catch (Exception e) {
            String msg = "Error fatal al buscar peticiones idAplicacion=[" + idAplicacion + "] nroPeticion=[" + nroPeticion + "] en bandeja: " + e;
    
            log.error(msg, e);
            throw new BandejaException(msg, e);
        }
    }

    /**
     * Elimina una entrada en la bandeja utilizando el identificador de la entrada.
     * 
     * @param id
     *            identificador de la entrada a eliminar.
     * @throws BandejaException
     *             si la entrada no existe u otro error ocurre.
     * @author amartoq
     */
    /*
	public ArrayList recuperarLocalidadesCodNombre() throws BandejaException
	{
		ArrayList retorno=new ArrayList();
		Collection listadoAgenciasEjb = getLocalidadesEntity();
		for(Iterator iterator=listadoAgenciasEjb.iterator();iterator.hasNext();)
		{
			LocalidadLocal local=(LocalidadLocal)iterator.next();
			LocalidadKey localidadKey=(LocalidadKey) local.getPrimaryKey();
			LocalidadDTO localidadDTO=new LocalidadDTO();
			localidadDTO.setNombreLocalidad(local.getNombre_localidad());
			localidadDTO.setCodLoc(localidadKey.cod_loc);
			retorno.add(localidadDTO);
		}
		Collections.sort(retorno);
		return retorno;
	}
	*/
  
	/*
	public ArrayList recuperarLocalidades() throws BandejaException
	{
		ArrayList retorno=new ArrayList();
		Collection listadoAgenciasEjb = getLocalidadesEntity();
		for(Iterator iterator=listadoAgenciasEjb.iterator();iterator.hasNext();)
		{
			LocalidadLocal local=(LocalidadLocal)iterator.next();
			LocalidadKey localidadKey=(LocalidadKey) local.getPrimaryKey();
			LocalidadDTO localidadDTO=new LocalidadDTO();
			localidadDTO.setNombreProvincia(local.getNombre_provincia());
			localidadDTO.setNombreMunicipio(local.getNombre_municipio());
			localidadDTO.setNombreLocalidad(local.getNombre_localidad());
			localidadDTO.setDescripcionLocalidad(local.getDescripcion_localidad());
			localidadDTO.setCodLoc(localidadKey.cod_loc);
			localidadDTO.setIndPresuscr(local.getInd_presuscr());
			localidadDTO.setIndSeleccMarcado(local.getInd_selecc_marcado());
			localidadDTO.setCodFormatoDireccion(local.getCod_formato_direccion());
			retorno.add(localidadDTO);
		}
		Collections.sort(retorno);
		return retorno;
	}
*/
	/**
	 * @return
	 */
	
	/*
	private Collection getLocalidadesEntity() throws BandejaException
	{
		try
		{
			LocalidadLocalHome localidadLocalHome=(LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			return localidadLocalHome.findAll();
		} catch (Exception e)
		{
			log.warn("Problemas recuperando home localidades: " + LocalidadLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando localidades", e);
		}
	}
	*/
	public ArrayList recuperaMunicipiosPorDepto(String cod_dpt)
	{
		 try
		 {
		 	ArrayList arrayList=new ArrayList();
		 	DepartamentoLocalHome departamentoLocalHome=(DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
		 	DepartamentoLocal departamentoLocal=departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(cod_dpt));
		 	Collection listaMunicipios=departamentoLocal.getMunicipio();
		 	if(listaMunicipios==null)
		 		return new ArrayList();
		 	for(Iterator iterator=listaMunicipios.iterator();iterator.hasNext();)
		 	{
		 		MunicipioLocal municipioLocal=(MunicipioLocal) iterator.next();
		 		MunicipioDTO municipioDto=new MunicipioDTO();
		 		MunicipioKey municipioKey=(MunicipioKey) municipioLocal.getPrimaryKey();
		 		municipioDto.setCod_mun(municipioKey.cod_mun); 
		 		municipioDto.setNombre_municipio(municipioLocal.getNombre_municipio());
		 		municipioDto.setDescripcion_municipio(municipioLocal.getDescripcion_municipio());
		 		municipioDto.setCod_dpt(cod_dpt);
		 		arrayList.add(municipioDto);
		 	}
		 	if(arrayList.size()>0)
		 		Collections.sort(arrayList);
		 	return arrayList;
		 }
		 catch(Exception e)
		 {
		 	e.printStackTrace();
		 	log.debug("Exception",e);
		 	return new ArrayList();
		 }
	}
	
	public ArrayList recuperaLocalidadesPorMunicipio(String cod_mun)
	{
		try
		 {
		 	//log.debug("Recuperando localidades de Municipio:"+cod_mun);
			ArrayList arrayList=new ArrayList();
			MunicipioLocalHome municipioLocalHome=(MunicipioLocalHome) HomeFactory.getHome(MunicipioLocalHome.JNDI_NAME);
			MunicipioLocal municipioLocal=municipioLocalHome.findByPrimaryKey(new MunicipioKey(cod_mun));
			Collection listaLocalidades=municipioLocal.getLocalidad();
			if(listaLocalidades==null)
				return new ArrayList();
			for(Iterator iterator=listaLocalidades.iterator();iterator.hasNext();)
			{
				LocalidadLocal localidadLocal=(LocalidadLocal) iterator.next();
				LocalidadDTO localidadDTO=new LocalidadDTO();
				LocalidadKey localidadKey=(LocalidadKey) localidadLocal.getPrimaryKey();
				localidadDTO.setCodLoc(localidadKey.cod_loc);
				localidadDTO.setNombreLocalidad(localidadLocal.getNombre_localidad());
				localidadDTO.setDescripcionLocalidad(localidadLocal.getDescripcion_localidad());
				localidadDTO.setCod_mun(cod_mun);
				arrayList.add(localidadDTO);
			}
			if(arrayList.size()>0)
				Collections.sort(arrayList);
			return arrayList;
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
			log.debug("Exception",e);
			return new ArrayList();
		 }
	}
	
	
	public ArrayList recuperaSegmentos()
	{
		try
		 {
			ArrayList arrayList=new ArrayList();
			SegmentoLocalHome segmentoLocalHome=(SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			Collection list=segmentoLocalHome.findAll();
			for(Iterator iterator=list.iterator();iterator.hasNext();)
			{
				SegmentoLocal segmentoLocal=(SegmentoLocal) iterator.next();
				SegmentoKey segmentoKey=(SegmentoKey) segmentoLocal.getPrimaryKey();
				SegmentoDTO segmentoDTO=new SegmentoDTO();
				segmentoDTO.setSegmId(segmentoKey.segm_id);
				segmentoDTO.setSegmDescripcion(segmentoLocal.getSegm_descripcion());
				arrayList.add(segmentoDTO);				
			}
			Collections.sort(arrayList);
			return arrayList;
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
			log.debug("Exception",e);
			return new ArrayList();
		 }	
	}
	
	
	public Tabla setearDatoTablaControlTecnico (HashMap filtros, int paginaActual , int paginacion ) throws BandejaException
	{
		Tabla tabla = new TablaControlTecnico(paginaActual);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}
	}
	
	public ArrayList recuperaRangos()
	{
		try
		{
			ArrayList retorno=new ArrayList();
			RangoLocalHome rangoLocalHome=(RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			Collection listaRangos=rangoLocalHome.findAll();
			for(Iterator iterator=listaRangos.iterator();iterator.hasNext();)
			{
				RangoLocal local=(RangoLocal) iterator.next();
				RangoKey key=(RangoKey) local.getPrimaryKey();
				RangoDTO rangoDTO=new RangoDTO();
				rangoDTO.setIdRango(key.id_rango);
				rangoDTO.setHabilitado(local.getHabilitado());
				rangoDTO.setNombreRango(local.getNombre_rango());
				rangoDTO.setCodigoPcom(local.getCodigo_pcom());
				rangoDTO.setHoraDesde(local.getHora_desde());
				rangoDTO.setHoraHasta(local.getHora_hasta());
				rangoDTO.setCodigoRango(local.getCodigo_rango());
				rangoDTO.setIdPadre(local.getId_padre());
				retorno.add(rangoDTO);	
			}
			return retorno;
		}
		catch(Exception e)
		{
			log.debug(e);
			return new ArrayList();
		}
	}
	
	public ArrayList recuperaTecnicosTodos()
	{
		try
		{
			ArrayList retorno=new ArrayList();
			TecnicoLocalHome home=(TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			Collection listaTecnicos=home.findAllHab();
			for(Iterator iterator=listaTecnicos.iterator();iterator.hasNext();)
			{
				TecnicoLocal local=(TecnicoLocal) iterator.next();
				TecnicoKey key=(TecnicoKey) local.getPrimaryKey();
				TecnicoDTO tecnicoDTO=new TecnicoDTO();
				tecnicoDTO.setCodTecnico(key.cod_tecnico);
				tecnicoDTO.setNombre(local.getNombre());
				tecnicoDTO.setApellido(local.getApellido());
				retorno.add(tecnicoDTO);	
			}
			Collections.sort(retorno);
			return retorno;
		}
		catch(Exception e)
		{
			log.debug(e);
			return new ArrayList();
		}
	}
	
	public ArrayList recuperaTecnicosDeContratista(Long idContratista)
	{
		try
		{
			ArrayList retorno=new ArrayList();
			TecnicoLocalHome tecnicoHome=(TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			Collection c=tecnicoHome.findByEmpresa(idContratista);
			for(Iterator iterator=c.iterator();iterator.hasNext();)
			{
				TecnicoLocal tecnicoLocal=(TecnicoLocal) iterator.next();
				TecnicoKey key=(TecnicoKey) tecnicoLocal.getPrimaryKey();
				TecnicoDTO tecnicoDTO=new TecnicoDTO();
				tecnicoDTO.setCodTecnico(key.cod_tecnico);
				tecnicoDTO.setNombre(tecnicoLocal.getNombre());
				tecnicoDTO.setApellido(tecnicoLocal.getApellido());
				retorno.add(tecnicoDTO);
			}
			Collections.sort(retorno);
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			return new ArrayList();
		}
	}
	
	public ArrayList recuperaEmpresasTodas()
	{
		try
		{
			ArrayList retorno=new ArrayList();
			EmpresaLocalHome empresaLocalHome=(EmpresaLocalHome) HomeFactory.getHome(EmpresaLocalHome.JNDI_NAME);
			Collection listaEmpresa=empresaLocalHome.findAll();
			for(Iterator iterator=listaEmpresa.iterator();iterator.hasNext();)
			{
				EmpresaLocal empresaLocal=(EmpresaLocal) iterator.next();
				EmpresaDTO empresaDTO=new EmpresaDTO();
				EmpresaKey empresaKey=(EmpresaKey) empresaLocal.getPrimaryKey();
				empresaDTO.setEmprId(empresaKey.empr_id);
				empresaDTO.setEmpresaNombre(empresaLocal.getEmpresa_nombre());
				empresaDTO.setEmpresaDescripcion(empresaLocal.getEmpresa_descripcion());
				retorno.add(empresaDTO);
			}
			Collections.sort(retorno);
			return retorno;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			log.debug(exception);
			return new ArrayList();
		}
	}
	
	public Tabla setearDatoTablaBackOffice(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
	{
		Tabla tabla = new TablaBackOffice(paginaActual,orden);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}		
	}
	
	public Tabla setearDatoTablaEsperaBA(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
	{
		Tabla tabla = new TablaEsperaBA(paginaActual,orden);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e) {
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}		
	}
	
	public Tabla setearDatoTablaEsperaTerra(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
		{
			Tabla tabla = new TablaEsperaTerra(paginaActual,orden);
			tabla.setLargoPagina(paginacion);
			try
			{
				tabla.retrieve(filtros);
				return tabla;
			} catch (TablaException e) {
				log.warn("Problemas recuperando listado de peticiones", e );
				throw new BandejaException("No se pudo recuperar peticiones", e);
			}		
		}
		
	// adocarmo - CR9664 - inicio 	
	public Tabla setearDatoTablaEsperaAula365(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
		{
			Tabla tabla = new TablaEsperaAula365(paginaActual,orden);
			tabla.setLargoPagina(paginacion);
			try
			{
				tabla.retrieve(filtros);
				return tabla;
			} catch (TablaException e) {
				log.warn("Problemas recuperando listado de peticiones", e );
				throw new BandejaException("No se pudo recuperar peticiones", e);
			}		
		}		
	// adocarmo - CR9664 - fin	
		
	public Tabla setearDatoTablaSupCo(HashMap filtros, int paginaActual , int paginacion) throws BandejaException
	{
		Tabla tabla = new TablaBandejaSupervisor(paginaActual);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e)
		{
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}	
	}
	
	public ArrayList recuperaUsuariosQueSuperviso(Long idUsuario) throws BandejaException
	{
		try
		{
			ArrayList losquetengo=new ArrayList();
			ArrayList retorno=new ArrayList();
			Jer_usuario_rolLocalHome home=(Jer_usuario_rolLocalHome) HomeFactory.getHome(Jer_usuario_rolLocalHome.JNDI_NAME);
			Collection listaSupervisados=home.findSupervisados(idUsuario);
			for(Iterator iterator=listaSupervisados.iterator();iterator.hasNext();)
			{
				Jer_usuario_rolLocal local=(Jer_usuario_rolLocal) iterator.next();
				UsuarioLocal usuario=local.getFk_jerusuarol_usu2();
				UsuarioKey key=(UsuarioKey) usuario.getPrimaryKey();
				if(key.usua_id.intValue()==0)
					continue;
				if(losquetengo.contains(key.usua_id))
					continue;
				UserCoDto dto=new UserCoDto();
				dto.setUsuaId(key.usua_id);
				dto.setUsuaLogin(usuario.getUsua_login());
				dto.setUsuaNombre(usuario.getUsua_nombre());
				dto.setUsuaApePaterno(usuario.getUsua_ape_paterno());
				dto.setUsuaApeMaterno(usuario.getUsua_ape_materno());
				retorno.add(dto);
				losquetengo.add(key.usua_id);
			}
			Collections.sort(retorno);
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			return new ArrayList();
		}
	}
	
	
	private static final String sqlReasignaBitacoraVPI="update vpistbba.BITACORA_PETICION set USUA_ID=?," +
	"BIPE_OBSERVACION=? where PETI_NUMERO=? and BIPE_FECHA_FIN is null " +
	" and USUA_ID=?";
	
	private static final String sqlReasignaBitacoraST="update soltec.BITACORA_PETICION " +
	" set USUA_ID=?,BIPE_OBSERVACION=? where COD_AVE_CD=? and BIPE_FECHA_FIN is null " +
	" and USUA_ID=?";
	
	public ReasignaResultDTO reasignaPeticionesUsuario(Long usuaSupervisor,Long usuarioActual,Long usuarioNuevo,ArrayList peticiones) throws BandejaException
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try
		{
			conn=DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			ReasignaResultDTO resultDTO=new ReasignaResultDTO();
			resultDTO.setIdUsuarioSupervisor(usuaSupervisor);
			UsuarioLocalHome home=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			BintegradaLocalHome bHome=(BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			UsuarioLocal uActual=home.findByPrimaryKey(new UsuarioKey(usuarioActual)); 
			UsuarioLocal uNuevo=home.findByPrimaryKey(new UsuarioKey(usuarioNuevo));
			String observacion=".Reasignada de "+uActual.getUsua_login()+" a "+uNuevo.getUsua_login();
			resultDTO.setIdUserIni(usuarioActual);
			resultDTO.setIdUserNew(usuarioNuevo);
			resultDTO.setLoginUserIni(uActual.getUsua_login());
			resultDTO.setLoginUserNew(uNuevo.getUsua_login());
			Vector roles=new Vector();
			Short usro_habilitado = null;
			for(Iterator iterator2=uNuevo.getUsuario_rol_1().iterator();iterator2.hasNext();)
			{
				Usuario_rolLocal local=(Usuario_rolLocal) iterator2.next();
				usro_habilitado = local.getUsro_habilitado();
				if(usro_habilitado!=null && usro_habilitado.shortValue()==1)
				{
					RolKey rolTiene=(RolKey) local.getFk_usuarol_rol().getPrimaryKey();
					roles.add(rolTiene.rol_id);
				}
			}

			for(Iterator iterator=peticiones.iterator();iterator.hasNext();)
			{
				BiVsPetDTO biVsPetDTO=(BiVsPetDTO) iterator.next(); 
				try
				{
					BintegradaLocal bi=bHome.findByPrimaryKey(new BintegradaKey(biVsPetDTO.getBiNumero()));
					//chequeo simple de seguridad
					UsuarioLocal uu=bi.getFk_bi_usuario();
					UsuarioKey uuk=(UsuarioKey) uu.getPrimaryKey();
					if(usuarioActual.longValue()!=uuk.usua_id.longValue())	
						return null;
					Long rolEnQueEsta=bi.getRol_id();
					//log.debug("Rol en que esta:"+rolEnQueEsta);
					if(roles.contains(rolEnQueEsta))
					{
						//aki hace la reasignacion.
						bi.setFk_bi_usuario(uNuevo);
						try
						{
							AplicacionKey aplicacionKey=(AplicacionKey) bi.getFk_bintegrada_ap().getPrimaryKey();
							switch(aplicacionKey.ap_id.intValue())
							{
								case 3:
									pstmt=conn.prepareStatement(sqlReasignaBitacoraVPI);
									pstmt.setLong(1,usuarioNuevo.longValue());
									pstmt.setString(2,observacion);
									pstmt.setLong(3,biVsPetDTO.getPetiNumero().longValue());
									pstmt.setLong(4,usuarioActual.longValue());
									pstmt.executeUpdate();
									break;
								case 2:
									pstmt=conn.prepareStatement(sqlReasignaBitacoraST);
									pstmt.setLong(1,usuarioNuevo.longValue());
									pstmt.setString(2,observacion);
									pstmt.setLong(3,biVsPetDTO.getPetiNumero().longValue());
									pstmt.setLong(4,usuarioActual.longValue());
									pstmt.executeUpdate();
									break;
							}
						}
						catch(Exception e)
						{
							log.debug("No fue posible realizar la reasignacion de la bitacora");
						}
						
						resultDTO.addPeticion(""+bi.getBi_nro_peticion_atis()+"-"+bi.getBi_nro_peticion());
					}
					else
						resultDTO.addNoReasignada(""+bi.getBi_nro_peticion_atis()+"-"+bi.getBi_nro_peticion());
				}
				catch(FinderException fe)
				{
					log.debug("No se encontro bi:"+biVsPetDTO.getBiNumero());
				}
			}
			return resultDTO;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e);
			throw new BandejaException(e);
		}
		finally
		{
			try
			{
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				throw new BandejaException(e);
			}
		}
	}
	
	public Tabla setearDatoTablaBandejaTorre(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
	{
		Tabla tabla = new TablaBandejaTorre(paginaActual,orden);
		tabla.setLargoPagina(paginacion);
		try
		{
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e)
		{
			log.warn("Problemas recuperando listado de peticiones", e );
			throw new BandejaException("No se pudo recuperar peticiones", e);
		}
	}
	
	public boolean esAdministrador(Long idUsuario,Long idPerfil) throws BandejaException
	{
		try
		{
			Perfil_usuarioLocalHome home=(Perfil_usuarioLocalHome) HomeFactory.getHome(Perfil_usuarioLocalHome.JNDI_NAME);
			Collection perfilesUsuarios=home.findByUsuario(idUsuario);
			for(Iterator iterator=perfilesUsuarios.iterator();iterator.hasNext();)
			{
				Perfil_usuarioLocal local=(Perfil_usuarioLocal) iterator.next();
				PerfilKey key=(PerfilKey) local.getFk_perfusu_perf().getPrimaryKey();
				if(key.perf_id.longValue()==idPerfil.longValue())
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			log.debug(e);
			return false;
		}
	}
	
	public AuxBandejaDTO recuperaAuxBandeja(Long nroPeticion,String aula)  throws BandejaException
	{
		try
		{
			if(wfHome==null)
				wfHome=(Wf_instancia_actividadLocalHome) HomeFactory.getHome(Wf_instancia_actividadLocalHome.JNDI_NAME);
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey key=new PeticionKey(nroPeticion);
			PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(key);
			AuxBandejaDTO auxBandejaDTO=new AuxBandejaDTO(nroPeticion);
			
//			CR9664 - adocarmo - inicio
//			String codActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONFIGTERR");
			String codActividad ="";
			if (aula.equals("S")) {
				codActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONFIGAULA");	
			}
			else {
				codActividad=VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONFIGTERR");
			}
//			CR9664 - adocarmo - fin			
			
			String idProceso=VpistbbaConfig.getVariable("ID_TEMPLATE");
			Wf_instancia_actividadLocal local=wfHome.findInstanciaByProcesoTemplateActividad(idProceso,nroPeticion,codActividad);
			Wf_instancia_actividadKey wf_instancia_actividadKey=(Wf_instancia_actividadKey) local.getPrimaryKey();
			auxBandejaDTO.setActividad(local.getCodigo_actividad());
			auxBandejaDTO.setEstadoPet(peticionLocal.getEspe_id());
			auxBandejaDTO.setFamiliaPs(peticionLocal.getPeti_id_instancia());
			auxBandejaDTO.setInstanciaActividad(wf_instancia_actividadKey.id_instancia_actividad);
			auxBandejaDTO.setTemplate(idProceso);
			return auxBandejaDTO;
		}
		catch(Exception e)
		{
			log.error("Exception",e);
			throw new BandejaException(e);
		}
	}
	
	
	//TODO: CR11267 adecarlini.
	private static final String sqlCategoriaAveria= "select correlativo, codigo, tipo, descripcion from soltec.codigo_st cs "+
		"where cs.tipo in (select tc.tipo from soltec.tipo_codigo tc where atributo like "+"'"+"%COD_CTZ_CD%"+"'"+")";
		
	private static final String sqlPrioridadAveria= "select correlativo, codigo, tipo, descripcion from soltec.codigo_st cs where cs.tipo in"+
	"(select tc.tipo from soltec.tipo_codigo tc where atributo like "+"'"+"%COD_PRA_AVE_CD%"+"'"+")";

	private static ArrayList prioridadAveria = null;
	private static ArrayList categoriaAveria = null;
	
	public ArrayList recuperaPrioridadAveria(){
		ArrayList list = (prioridadAveria == null)?recuperaCodigoStAveria(sqlPrioridadAveria):prioridadAveria;
		return list;
	}
	public ArrayList recuperaCategoriaAveria(){
		ArrayList list = (categoriaAveria == null)?recuperaCodigoStAveria(sqlCategoriaAveria):categoriaAveria;
		return list;
	}

	
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - INICIO
	public Tabla setearDatoTablaEsperaSigres(HashMap filtros, int paginaActual , int paginacion,int orden) throws BandejaException
		{
			Tabla tabla = new TablaEsperaSigres(paginaActual,orden);
			tabla.setLargoPagina(paginacion);
			try
			{
				tabla.retrieve(filtros);
				return tabla;
			} catch (TablaException e) {
				log.warn("Problemas recuperando listado de peticiones", e );
				throw new BandejaException("No se pudo recuperar peticiones", e);
		}		
	}
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - FIN

	private ArrayList recuperaCodigoStAveria(String stmt){
		String sqlStatement = stmt;
		ArrayList listCodigoAveria = new ArrayList();
			
		try{				
			Connection conn = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			PreparedStatement pstmt = conn.prepareStatement(sqlStatement);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				CodigoStDTO codigoST = new CodigoStDTO();
				codigoST.setCorrelativo(rs.getLong(1));
				codigoST.setCodigo(rs.getString(2));
				codigoST.setTipo(rs.getInt(3));
				codigoST.setDescripcion(rs.getString(4));
				listCodigoAveria.add(codigoST);					
			};
		}catch(Exception e){
			log.error(e);
		}
		return listCodigoAveria;	
	//fin
	}

	/**
	 * @return
	 */
	public ArrayList recuperarLocalidadesCodNombre() {
		// TODO Auto-generated method stub
		return null;
	}
}
