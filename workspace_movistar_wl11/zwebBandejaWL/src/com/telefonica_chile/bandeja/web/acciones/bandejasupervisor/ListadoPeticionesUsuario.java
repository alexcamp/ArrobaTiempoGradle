package com.telefonica_chile.bandeja.web.acciones.bandejasupervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.web.MensajesWeb;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocal;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AplicacionDTO;
import com.telefonica_chile.bandeja.mantenedores.UsuarioBandejaSupervisorDTO;
import com.telefonica_chile.bandeja.supervisor.sessions.SupervisorException;
import com.telefonica_chile.bandeja.usuariossupervisadossession.UsuariosSupervisadosSessionLocal;
import com.telefonica_chile.bandeja.usuariossupervisadossession.UsuariosSupervisadosSessionLocalHome;
import com.telefonica_chile.bandeja.web.acciones.UtilesBandeja;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;

public class ListadoPeticionesUsuario {
	private HttpServletRequest request;
	private String username;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public ListadoPeticionesUsuario(
		HttpServletRequest request,
		String username) {
		this.request = request;
		this.username = username;
	}

	public String listarPeticiones(HashMap datosFiltro)
		throws SupervisorException, BandejaException {
		MensajesWeb mensajes = MensajesWeb.getInstance(request);
		Long idUsuario = null;
		Long idRol = null;
		Long usuarioSup = null;
		//UsuarioDTO usuario = null;
		
		try {
			//log.info("  ==  listarPeticiones() ==");
			idUsuario = new Long(request.getParameter("USUA_ID"));
			idRol = new Long(request.getParameter("idRol"));
			usuarioSup = new Long(request.getParameter("usuarioSup"));
			
			if (usuarioSup.equals("")) {
				usuarioSup = idUsuario;
			}
			UsuariosSessionLocal usuariosSession = getUsuariosSession();
			//usuario = usuariosSession.retornaUsuario(idUsuario);

			Tabla usuariosSupervisados =
				getBandejaSession().recuperaUsuariosSupervisados(
					username,
					idRol,
					getPaginaActual(1),
					datosFiltro);

			//Muestro todos los subordinados menos el "dueño" de las peticiones			
			for (Iterator iter = usuariosSupervisados.iterator();
				iter.hasNext();
				) {
				UsuarioBandejaSupervisorDTO element =
					(UsuarioBandejaSupervisorDTO) iter.next();
				if (element.getId().equals(idUsuario)) {
					iter.remove();
				}
			}

			UsuariosSupervisadosSessionLocalHome home =
				(UsuariosSupervisadosSessionLocalHome) HomeFactory.getHome(
					UsuariosSupervisadosSessionLocalHome.JNDI_NAME);
			UsuariosSupervisadosSessionLocal local = home.create();
			ArrayList usrSupervisados = local.getUsuarios(idRol, usuarioSup);

			request.setAttribute("usuariosSupervisados", usuariosSupervisados);
			request.setAttribute("usrSupervisados", usrSupervisados);
			//request.setAttribute("usuario", usuario);
			request.setAttribute("idRol", idRol);
			request.setAttribute("usuarioSup", usuarioSup);
			request.setAttribute("USUA_ID", idUsuario);

		} catch (NumberFormatException e) {
			mensajes.addError("No se pudo recuperar usuario: " + idUsuario);
			log.error("No se pudo recuperar usuario: " + idUsuario, e);
		} catch (Exception e) {
			log.error("Problemas con session usuario", e);
			mensajes.addError(
				"No se pudo recuperar informacion del usuario " + idUsuario);
		}

		if (!mensajes.hayErrores()) {
			int largoPagina = 10;
			if (request.getParameter("dpp") != null) {
				largoPagina =
					new Integer(request.getParameter("dpp")).intValue();
			}

			HashMap filtro = recuperaFiltrosFijos(request);

			filtro.put("USUA_ID", idUsuario.toString());

			//String actividades = getCondActividades(idRol);
			filtro.put("idRol", idRol);
			filtro.put("usuarioSup", idUsuario);

			// Los Ex-Filtros Variables.
			filtro.put(
				"TICA_ID",
				UtilesBandeja.sinNull(request.getParameter("TICA"), ""));
			filtro.put(
				"PCOM",
				UtilesBandeja.sinNull(request.getParameter("PCOM"), ""));
			filtro.put(
				"CCN",
				UtilesBandeja.sinNull(request.getParameter("CCN"), ""));

			try {
				BIntegradaSessionLocal bandejaSession = getBandejaSession();
				//log.info(" setearDatoTablaPeticion ==> " + filtro );
//				Tabla tabla =
//					(Tabla) bandejaSession.setearDatoTablaPeticion(
//						filtro,
//						getPaginaActual(0),
//						largoPagina);

				ArrayList listaGSeg = getLista("GRUPO_SEGMENTO");
				ArrayList listaAp = getLista("APLICACION");

				// Deberiamos setear los Nombres del GSEGMENTO a las peticos obtenidas.
				HashMap PR = new HashMap();
				for (int i = 0;
					listaGSeg != null && i < listaGSeg.size();
					i++) {
					SegmentoDTO sDto = (SegmentoDTO) listaGSeg.get(i);
					if (sDto == null || sDto.getSegmId() == null)
						continue;
					PR.put(
						"SEG_" + sDto.getSegmId(),
						sDto.getSegmDescripcion());
				}

				for (int i = 0; listaAp != null && i < listaAp.size(); i++) {
					AplicacionDTO apDto = (AplicacionDTO) listaAp.get(i);
					if (apDto == null || apDto.getApId() == null)
						continue;
					PR.put("AP_" + apDto.getApId(), apDto.getApNombre());
				}

//				ArrayList listaPetico = (ArrayList) tabla.getElementos();
//				for (int i = 0;
//					listaPetico != null && i < listaPetico.size();
//					i++) {
//					PeticionDTO pDto = (PeticionDTO) listaPetico.get(i);
//					if (pDto == null || pDto.getIdBloqueSegmento() == null)
//						continue;
//					pDto.setNombreBloqueSegmento(
//						(String) PR.get("SEG_" + pDto.getIdBloqueSegmento()));
//					pDto.setAplicacionNombre(
//						(String) PR.get("AP_" + pDto.getApId()));
//				}

				request.setAttribute("listSegmentos", listaGSeg);
//				request.setAttribute("peticiones", tabla);
				request.setAttribute("nombreTabla", "peticiones");

				UsuariosSessionLocal usuariosSession = getUsuariosSession();
				BandejaSessionLocalHome home =
					(BandejaSessionLocalHome) HomeFactory.getHome(
						BandejaSessionLocalHome.JNDI_NAME);
				BandejaSessionLocal bandeja = home.create();

				Long idBi;
				ArrayList listIdBi = new ArrayList();
//				for (Iterator it = tabla.iterator(); it.hasNext();) {
//					PeticionDTO peticion = (PeticionDTO) it.next();
//					listIdBi.add(peticion.getBiId());
//				}

				/**Listado Campos Variables y sus Valores**/
//				ArrayList listValoresVariables =
//					(ArrayList) usuariosSession.recuperarValoresVariables(
//						usuario.getId(),
//						listIdBi);
//				/**Listado Aplicaciones (Ambitos)**/
//				ArrayList listAplicaciones =
//					(ArrayList) bandejaSession.recuperarAplicaciones();
//				/**Listado Roles del Usuario**/
//				ArrayList listRolesUsuario =
//					(ArrayList) usuariosSession.recuperarRolesUsuario(
//						usuario.getId());
//				/**Listado Acciones**/
//				ArrayList listAcciones = null;
//				if (listRolesUsuario.size() > 0) {
//					RolDTO roles = new RolDTO();
//					roles = (RolDTO) listRolesUsuario.get(0);
//					listAcciones =
//						(ArrayList) bandeja.recuperarAcciones(roles.getId());
//					for (Iterator it = listAcciones.iterator();
//						it.hasNext();
//						) {
//						AccionMasivaDTO accion = (AccionMasivaDTO) it.next();
//					}
//				}

				// ENVÍO LAS URL CORRESPONDIENTE A CADA APLICACION para los menú de la cabecera
				UrlAplicaciones url = new UrlAplicaciones();
				String urlVPISTBBA = url.getUrl("VPISTBBA");
				HashMap mapAplicaciones = url.mapAplicaciones();
				request.setAttribute("urlVPISTBBA", urlVPISTBBA);
				request.setAttribute("mapAplicaciones", mapAplicaciones);
				//----------------------------------------------------
//				request.setAttribute("listAplicaciones", listAplicaciones);
//				request.setAttribute(
//					"listValoresVariables",
//					listValoresVariables);
//				request.setAttribute("listAcciones", listAcciones);

			} catch (Exception e) {
				mensajes.addError("Hubo problemas recuperando peticiones.");
				log.error("Hubo problemas recuperando peticiones.");
			}

		}

		if (!mensajes.hayErrores()) {
			return "listadoPeticiones";
		} else {
			return new ListadoUsuariosSupervisados(request, username)
				.listarUsuarios();
		}
	}

	private BIntegradaSessionLocal getBandejaSession()
		throws NamingException, CreateException {
		BIntegradaSessionLocalHome home =
			(BIntegradaSessionLocalHome) HomeFactory.getHome(
				BIntegradaSessionLocalHome.JNDI_NAME);
		BIntegradaSessionLocal local = home.create();
		return local;
	}

	private UsuariosSessionLocal getUsuariosSession()
		throws NamingException, CreateException {
		UsuariosSessionLocalHome home =
			(UsuariosSessionLocalHome) HomeFactory.getHome(
				UsuariosSessionLocalHome.JNDI_NAME);
		UsuariosSessionLocal local = home.create();
		return local;
	}

	private int getPaginaActual(int nro) {
		int p = 1;
		if (nro == 0) {
			try {
				p = Integer.parseInt(request.getParameter("PAGINA_ACTUAL"));
			} catch (Exception e) {
			}
			if (p <= 0)
				p = 1;
		}
		return p;
	}
	
	
//10-04-2007 VMolina: estos metodos esta en el session BIntegrada
//	private String getCondActividades(Long idRol) throws SupervisorException {
//		String cond = "";
//		try {
//
//			//Determinamos si el rol pertenece a ATST. Si es así, buscamos todas las actividades de ATST
//			//y las retornamos, para no afectar el funcionamiento normal de BIntegrada. 
//			//Esto debe hacerse ya que ATST tiene más de un rol asociado a cada actividad, por lo que no podemos
//			//asociar los roles con actividad en la misma tabla de actividad.
//			//Pero como ATST tiene solo un rol por usuario, podemos asumir que cada usuario puede estar 
//			//en todas las posibles actividades de ATST, para efectos de filtro
//			String listaActATST = listaActividadesATST(idRol);
//			if (listaActATST != null)
//				return (listaActATST);
//
//			ActividadLocalHome home =
//				(ActividadLocalHome) HomeFactory.getHome(
//			ActividadLocalHome.JNDI_NAME);
//			Collection col = home.findActividades(idRol);
//			for (Iterator iter = col.iterator(); iter.hasNext();) {
//				ActividadLocal actEnt =
//					(ActividadLocal) iter.next();
//				cond += String.valueOf((Long) actEnt.getPrimaryKey()) + ",";
//			}
//
//			return cond.substring(0, cond.length() - 1);
//		} catch (Exception e) {
//			log.error(
//				"No se pudo recuperar en getActividades usuario " + idRol,
//				e);
//			throw new SupervisorException(
//				"No se pudo recuperar en getActividades usuario "
//					+ idRol
//					+ ": "
//					+ e);
//		}
//	}

	/**
	 *  Metodo que retorna la lista de todas las actividades pertenecientes a ATST.
	 *  Devuelve null si el rol no es de ATST
	 *  @author iescobar@interplanet.cl 
	 *  @param idRol
	 *  @return
	 */
//	private String listaActividadesATST(Long idRol) {
//		String listaAct = null;
//		String cond = "";
//		// Verifico que el rol sea de ATST
//		try {
//			//Buscamos el id de la aplicacion ATST 
//			AplicacionLocalHome aplicacionEntityLocalHome =
//				(AplicacionLocalHome) HomeFactory.getHome(
//					AplicacionLocalHome.JNDI_NAME);
//			AplicacionLocal aplicacionEntityLocal =
//				aplicacionEntityLocalHome.findByNombre("ATST");
//			Long idAplicacion = (Long) aplicacionEntityLocal.getPrimaryKey();
//
//			//Buscamos el rol correspondiente al idRol		
//			RolLocalHome rolEntityLocalHome =
//				(RolLocalHome) HomeFactory.getHome(
//					RolLocalHome.JNDI_NAME);
//			RolLocal rolEntityLocal =
//				rolEntityLocalHome.findByPrimaryKey(new RolKey(idRol));
//
//			// Si es ATST: Obtengo todas las actividades de ATST. Creo el string y lo devuelvo. Sino devuelvo null
//			AplicacionKey aplicacionKey= (AplicacionKey) rolEntityLocal.getFk_ap_2_rol().getPrimaryKey();
//			if (idAplicacion.equals(aplicacionKey.ap_id))
//			{
//				ActividadLocalHome home =
//					(ActividadLocalHome) HomeFactory.getHome(
//						ActividadLocalHome.JNDI_NAME);
//				Collection col = home.findByAplicacion(idAplicacion);
//				for (Iterator iter = col.iterator(); iter.hasNext();) {
//					ActividadLocal actEnt =
//						(ActividadLocal) iter.next();
//					cond += String.valueOf((Long) actEnt.getPrimaryKey()) + ",";
//					listaAct = cond.substring(0, cond.length() - 1);
//				}
//			}
//		} catch (Exception e) {
//			log.debug("Error al obtener listaActividadesATST", e);
//		}
//		return listaAct;
//	}

	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {

		HashMap map = new HashMap();

		String rutCliente = request.getParameter("rutCliente");
		if (rutCliente != null && rutCliente.trim().length() > 0)
			map.put("BI_CLIENTE_RUT", rutCliente);

		String dvCliente = request.getParameter("dvCliente");
		if (dvCliente != null && dvCliente.trim().length() > 0)
			map.put("BI_CLIENTE_RUTDV", rutCliente);

		String numPeticion = request.getParameter("numPeticion");
		if (numPeticion != null && numPeticion.trim().length() > 0)
			map.put("BI_NRO_PETICION", numPeticion);

		String areaFono = request.getParameter("areaFono");
		if (areaFono != null && areaFono.trim().length() > 0)
			map.put("BI_CLIENTE_AREA", areaFono);

		String numFono = request.getParameter("numFono");
		if (numFono != null && numFono.trim().length() > 0)
			map.put("BI_CLIENTE_SERVICIO", numFono);

		map.put(
			"BI_TIPO_TRABAJO",
			UtilesBandeja.sinNull(request.getParameter("tipotrabajo"), ""));
		String fam = UtilesBandeja.sinNull(request.getParameter("familia"), "");
		if (fam != null && fam.length() > 0)
			fam = "'%" + fam + "%'";
		map.put("BI_FAMILIA_PS", fam);
		map.put(
			"BI_FECHA_COMPROMISO",
			UtilesBandeja.sinNull(request.getParameter("fecha"), ""));
		map.put(
			"BI_ESTADO_PETICION",
			UtilesBandeja.sinNull(request.getParameter("otrostipos"), ""));

		return (map);

	}

	private void getSBLocal() throws NamingException, CreateException {
		BIntegradaSessionLocalHome biHome =
			(BIntegradaSessionLocalHome) HomeFactory.getHome(
				BIntegradaSessionLocalHome.JNDI_NAME);
		sbLocal = biHome.create();
	}

	BIntegradaSessionLocal sbLocal = null;

	private ArrayList getLista(String tipo) {
		ArrayList listado = new ArrayList();

		try {
			if (sbLocal == null)
				getSBLocal();
		} catch (NamingException e) {
			log.info(
				"NamingException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		} catch (CreateException e) {
			log.info(
				"CreateException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		}

		try {
			if ("APLICACION".equals(tipo))
				listado = sbLocal.recuperarAplicaciones();

		} catch (BandejaException e) {
			log.info(
				"BandejaException. No se pudo Cargar Listado ["
					+ tipo
					+ "]: "
					+ e.getMessage());
		}

		return listado;
	}

}
