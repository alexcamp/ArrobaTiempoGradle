package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;

/**
 * Presenta la vista inicial de la bandeja
 */
public class VerBandejaAccOld extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
//		try{
//			HttpSession session = request.getSession(true);
//			log.debug("--- Inicio VerBandejaAcc Gabriel---");		
//			/**Recuperar Usuario*/
//			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
//			UsuarioWeb usuario = control.getUsuario();
//			List listaPeticiones = new ArrayList();
//			List listaid = new ArrayList();  
//			String listaBi = new String();
//			int pagina = getPaginaActual(request);
//			log.debug("--- pagina ==> " + pagina);
//			//paginacion (datos por pagina)
//			String largoPaginacion = request.getParameter("dpp"); 
//			int intPaginacion = 10;
//			if (largoPaginacion != null && largoPaginacion.trim().length() > 0)
//				intPaginacion = Integer.parseInt(largoPaginacion);			
//			
//			BIntegradaSessionLocal bandejaSession = getBandejaSession();
//
//			UsuariosSessionLocal usuariosSession = getUsuariosSession();
//			
//			BandejaSessionLocalHome home =(BandejaSessionLocalHome) HomeFactory.getHome(BandejaSessionHome.JNDI_NAME);
//			BandejaSessionLocal bandeja =  home.create();
//			
//			 
//			
//			/**setear Datos a Tabla Peticion
//			 * para obtener Peticiones del Usuario
//			 * de acuerdo a filtros...**/
//			HashMap map = recuperaFiltrosFijos(request);
//			map.put("FILTROS_VARIABLES", recuperaFiltrosVariables(request));
//			map.put("USUA_ID", usuario.getId().toString());
//			//Tabla tabla = (Tabla)bandejaSession.setearDatoTablaPeticion(map,pagina,intPaginacion);
//			log.debug(" ( map  ) ==> (" + map +" ) ");
////			listaid = tabla.getListaIdElements();
//			log.debug(" listaid ==>"  +listaid.size());
//			for (Iterator it = listaid.iterator(); it.hasNext();) {
//					PeticionDTO p = (PeticionDTO) it.next();
//					//String id = (String) it.next();
//					listaBi  += p.getBiNroPeticion() + ";";
//			}
//			log.debug(" tabla.idElements ==>"  +listaBi);
//			
//			Long idBi;
//			ArrayList listIdBi = new ArrayList();
////			for (Iterator it = tabla.iterator(); it.hasNext();)
////			{
////					PeticionDTO peticion = (PeticionDTO) it.next();
////					log.debug("peticion.getBiId==>"+peticion.getBiId());
////					listIdBi.add(peticion.getBiId());
////			}
//			String listaPet = listaBi;
//			
//			TreeMap otrostipos= getTipoTrabajoOtros();
//			/**Listado Roles del Usuario**/
//			ArrayList listRolesUsuario = (ArrayList)usuariosSession.recuperarRolesUsuario(usuario.getId());
//			
//			/* Verifico si es candidato a logica push:
//			 * 1. el usuario debe poseer solo un rol
//			 * 2. el rol debe tener PUSH_APP_ID no nulo
//			 * 3. el usuario no debe ser supervisor
//			 */
//			try {
//				if (listRolesUsuario.size() == 1) {
//					RolDTO rol = (RolDTO)listRolesUsuario.get(0);
//					if (rol != null && rol.getPushApp() != null) {
//						Collection col = usuariosSession.recuperarSupervisoresRol(rol.getId());
//						boolean esSupervisor = false;
//						for (Iterator it = col.iterator(); it.hasNext();) {
//							UsuarioDTO u = (UsuarioDTO)it.next();
//							if (u.getId() == usuario.getId()) {
//								esSupervisor = true;
//							}
//						}
//						
//						/* ejecucion logica push */
//						if (!esSupervisor) {
//							log.info("Usuario en rol push, redireccionando a pushApp=[" + rol.getPushApp() + "]");
//							String url = ApplicationConfig.getUrlBase(rol.getPushApp());
//							url = url + "/BandejaPush.acc";
//							request.setAttribute("redirectBandejaPush", url);
//							return;
//						} else {
//							log.debug("Usuario supervisor, no aplica PUSH");
//						}
//					}
//				}
//				log.info("Usuario no en rol push, continua carga normal");
//			} catch (Exception e) {
//				log.error("Error verificando push: " + e, e);
//				log.info("Se continua carga de pagina");
//			}
//						
//			/**Listado Campos Variables y sus Valores**/
//			ArrayList listValoresVariables = (ArrayList)usuariosSession.recuperarValoresVariables(usuario.getId(),listIdBi);
//			
//			/**Listado Agencias**/
//			ArrayList listAgencias = new ArrayList();//(ArrayList)bandejaSession.recuperarAgencias();
//			
//			/**Listado Isp's**/
//			ArrayList listIsps = (ArrayList)bandejaSession.recuperarIsps();
//			
//			/**Listado Segmentos**/
//			//ArrayList listSegmentos = (ArrayList)usuariosSession.recuperarSegmentos();
//			ArrayList listSegmentos = getSegmentos();
//			
//			
//			/**Listado Roles del Usuario**/
//			//ArrayList listRolesUsuario = (ArrayList)usuariosSession.recuperarRolesUsuario(usuario.getId());
//			
//			/**Listado Aplicaciones (Ambitos)**/
//			ArrayList listAplicaciones = (ArrayList)bandejaSession.recuperarAplicaciones();
//			
//			/**Listado Acciones**/
//			ArrayList listAcciones=null;
//			if(listRolesUsuario.size()>0){
//				RolDTO roles = new RolDTO ();
//				roles = (RolDTO)listRolesUsuario.get(0);
//				listAcciones = (ArrayList)bandeja.recuperarAcciones(roles.getId());
//				for (Iterator it = listAcciones.iterator(); it.hasNext();) {
//					AccionMasivaDTO accion= (AccionMasivaDTO) it.next();
//					}
//			} 	
//			
//			UrlAplicaciones url = new UrlAplicaciones();
//			String urlVPISTBBA = url.getUrl("VPISTBBA");
//			HashMap mapAplicaciones = url.mapAplicaciones();
//			
//			ArrayList defsCampos = bandejaSession.recuperarCamposVariables();
//			
//			/**envio datos al JSP**/			
////			request.setAttribute("peticiones", tabla);
//			request.setAttribute("nombreTabla", "peticiones");
//			request.setAttribute("listAgencias",listAgencias);
//			request.setAttribute("listAcciones",listAcciones);
//			request.setAttribute("listIsps",listIsps);
//			request.setAttribute("listSegmentos",listSegmentos);
//			request.setAttribute("listRolesUsuario",listRolesUsuario);
//			request.setAttribute("listAplicaciones",listAplicaciones);			
//			request.setAttribute("listValoresVariables",listValoresVariables);
//			request.setAttribute("defsCampos", defsCampos);
//			request.setAttribute("mapAplicaciones", mapAplicaciones);
//			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
//			request.setAttribute("listaBi", listaBi);
//			request.setAttribute("listaPet", listaPet);
//			request.setAttribute("otrostipos", otrostipos);
//			request.setAttribute("usuario",usuario);
//			log.debug("--- Termino VerBandejaAcc ---");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private UsuariosSessionLocal getUsuariosSession() throws NamingException, CreateException {
		UsuariosSessionLocalHome home =
					(UsuariosSessionLocalHome)HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
		UsuariosSessionLocal local = home.create();
		return local;
	}

	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException {
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}
	
	private ArrayList getSegmentos() throws NamingException, CreateException {
		Collection col = null;
		ArrayList lista = new ArrayList(); 
//		SegmentoDTO objSegmento = new SegmentoDTO();  
//		GrupoSegmentoAgendaLocalHome gSegHome = (GrupoSegmentoAgendaLocalHome)
//				HomeFactory.getHome(GrupoSegmentoAgendaLocalHome.JNDI_NAME);
//		Integer SegmID =  new Integer(0);	
//		try {
//			col= gSegHome.findAllOrderByPorcentaje();
//			for ( Iterator it = col.iterator(); it.hasNext(); ) {
//				objSegmento = new SegmentoDTO();
//				GrupoSegmentoAgendaLocal Local = (GrupoSegmentoAgendaLocal) it.next();
//				SegmID=(Integer)Local.getPrimaryKey();
//				objSegmento.setSegmId(new Long(SegmID.longValue()));
//				objSegmento.setSegmDescripcion(Local.getNombre());
//				lista.add(objSegmento);
//			}				
//		} catch (FinderException e) {
//			log.debug("--- Error verBandejaAcc . ArrayList getSegmentos(), FinderException ---" + e.toString());
//		}
		
		return lista;
	}
	
	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
		
		HashMap map = new HashMap();
		String agencia = request.getParameter("agencia");
		if (agencia != null && agencia.trim().length() > 0)
			map.put("AGEN_ID", agencia);

		String segmento = request.getParameter("segmento");
		if (segmento != null && segmento.trim().length() > 0)
			map.put("SEGM_ID", segmento);
				
		String rutCliente = request.getParameter("rutCliente");			
		if (rutCliente != null && rutCliente.trim().length() > 0)
			map.put("BI_CLIENTE_RUT", rutCliente);
			
		String dvCliente = request.getParameter("dvCliente");
		if (dvCliente != null && dvCliente.trim().length() > 0)
			map.put("BI_CLIENTE_RUTDV", rutCliente);
		
		String prodServicio = request.getParameter("prodServicio");
		if (prodServicio != null && prodServicio.trim().length() > 0)
			map.put("BI_FAMILIA_PS", prodServicio);
		
		String aplicacion = request.getParameter("aplicacion");
		if (aplicacion != null && aplicacion.trim().length() > 0)
			map.put("AP_ID", aplicacion);
						
		String numPeticion = request.getParameter("numPeticion");
		if (numPeticion != null && numPeticion.trim().length() > 0)
			map.put("BI_NRO_PETICION", numPeticion);
		
		String rol = request.getParameter("rol");
		if (rol != null && rol.trim().length() > 0)
			map.put("ROL_ID", rol);
		
		String isp = request.getParameter("isp");
		if (isp != null && isp.trim().length() > 0)
			map.put("ISP_ID", isp);
		
		String areaFono = request.getParameter("areaFono");
		if (areaFono != null && areaFono.trim().length() > 0)
			map.put("BI_CLIENTE_AREA", areaFono);
		
		String numFono = request.getParameter("numFono");
		if (numFono != null && numFono.trim().length() > 0)
			map.put("BI_CLIENTE_SERVICIO", numFono);
			
		String otrostipos = request.getParameter("otrostipos");
		if (otrostipos != null && otrostipos!="" && otrostipos.trim().length() > 0)
			map.put("BI_ESTADO_PETICION",otrostipos);
			
		return (map);
		
	}
	
	private HashMap recuperaFiltrosVariables(HttpServletRequest request) {
		String keyPrefix = "cv_";
		String valuePrefix = "vv_";
		HashMap map = new HashMap();
		for (Iterator it = request.getParameterMap().keySet().iterator(); it.hasNext(); ) {
			String k = (String) it.next();
			String v = request.getParameter(k);
			
			if (k.startsWith(keyPrefix) && !v.equals("-")) {
				String idx = k.substring(keyPrefix.length(), k.length());
				String nombreCV = v;
				String valorCV = request.getParameter(valuePrefix + idx);
				if (valorCV.trim().length() > 0)
					map.put(nombreCV, valorCV);
			}
		}
		
		return map;
	}

	private int getPaginaActual(HttpServletRequest request) {
		int p = 1;
		try { p = Integer.parseInt(request.getParameter("PAGINA_ACTUAL")); } catch (Exception e) { }
		if (p <= 0)
			p = 1;
		return p;
	}
	
	private TreeMap getTipoTrabajoOtros(){
		TreeMap t = new TreeMap();
		t.put("EN CURSO",   " =1");
		t.put("EN REVERSA", " in (3,7)");
		return t;
	}
		
}
