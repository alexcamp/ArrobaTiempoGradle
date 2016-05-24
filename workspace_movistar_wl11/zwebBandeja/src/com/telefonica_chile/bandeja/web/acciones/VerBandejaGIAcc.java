package com.telefonica_chile.bandeja.web.acciones;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocal;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AplicacionDTO;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.parametro.session.ParametroDTO;

public class VerBandejaGIAcc extends Accion{

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		Long idUsuario = null;
		try{
			HttpSession session = request.getSession(true);

			// Recuperamos el usuario de la Bandeja.
			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
			UsuarioWeb usuario = control.getUsuario();
			if ( usuario == null ) {
				log.info("No se encontro usuario en Session.");
			}

			idUsuario = usuario.getId();
			String sorden=request.getParameter("ordenaBandeja");
			Integer orden=null;
			if(sorden==null)
				orden=new Integer(0);
			else
				orden=new Integer(sorden);

			// Sacamos la pagina Actual y la Paginacion. 
			int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
			if ( pagina < 1 )
				++pagina;
			int intPaginacion = getInt(request.getParameter("dpp"), 10);
			if ( intPaginacion < 10 )
				intPaginacion = 10;;


			// Verificar si es PUSH o NO, esto deberia hacerse antes de cargar la bandeja y otros datos.
			/* Verifico si es candidato a logica push:
			 * 1. el usuario debe poseer solo un rol
			 * 2. el rol debe tener PUSH_APP_ID no nulo
			 * 3. el usuario no debe ser supervisor
			 */
			//ArrayList listRolesUsuario = usuario.getRoles();
			BIntegradaSessionLocal biLocal = getBandejaSession();
			UsuariosSessionLocal usuariosSession = getUsuariosSession();
			ArrayList listRolesUsuario = (ArrayList)usuariosSession.recuperarRolesUsuario(usuario.getId());
			boolean tienePerfilAdministrador=true;
			if(tienePerfilAdministrador)
				request.setAttribute("esAdministrador","1");
			else
				request.setAttribute("esAdministrador","0");

/*			for (int i=0; listRolesUsuario!=null && i<listRolesUsuario.size(); i++) {
				RolDTO rol = (RolDTO) listRolesUsuario.get(0);
				log.debug("ROL [" + rol.getId() + "," + rol.getCodigo() + "," + rol.getPushApp() + "]");
			}
*/
//			try {
//				if (listRolesUsuario.size() == 1) {
//					RolDTO rol = (RolDTO) listRolesUsuario.get(0);
//					if (rol != null && rol.getPushApp() != null) {
//						Collection col = usuariosSession.recuperarSupervisoresRol(rol.getId());
//						boolean esSupervisor = false;
//						for (Iterator it = col.iterator(); it.hasNext();) {
//							UsuarioDTO u = (UsuarioDTO)it.next();
//							if (u.getId() == usuario.getId()) {
//								esSupervisor = true;
//								break;
//							}
//						}
//
//						/* ejecucion logica push */
//						if ( !esSupervisor ) {
//							log.debug("Usuario en rol push, redireccionando a pushApp=[" + rol.getPushApp() + "]");
//							String url = ApplicationConfig.getUrlBase( rol.getPushApp() );
//							url = url + "/BandejaPush.acc";
//							request.setAttribute("redirectBandejaPush", url);
//							return;
//						} else {
//							log.debug("Usuario supervisor, no aplica PUSH");
//						}
//					}
//				}
//			} catch (Exception e) {
//				log.error("Error verificando push: " + e, e);
//			}

			List listaPeticiones = new ArrayList();
		
			BandejaSessionLocalHome bdHome =(BandejaSessionLocalHome) HomeFactory.getHome(BandejaSessionLocalHome.JNDI_NAME);
			BandejaSessionLocal bandeja =  bdHome.create();

			// Obtenemos los Filtros Variables y Fijos.
			HashMap map = recuperaFiltrosFijos(request);
			map.put("USUA_ID", usuario.getId().toString());
			
			// Obtengo los Datos de la Bandeja.
			
			Tabla tabla = (Tabla) biLocal.setearDatoTablaBandejaTorre(map,pagina,intPaginacion,orden.intValue());

			TreeMap listaFlujo = getTipoTrabajoOtros();
			
			ArrayList listaAg = new ArrayList();
			ArrayList listaIsp = new ArrayList();
			ArrayList listaAp = getLista( "APLICACION" );
			ArrayList listaSegmento = getLista( "SEGMENTO" );
			ArrayList listaDepartamento= getLista("DEPARTAMENTO");
			ArrayList listaMunicipio=null;
			ArrayList listaLocalidad=null;
			if(request.getParameter("departamento")!=null)
			{
				String cod_dpt=request.getParameter("departamento");
				if(!cod_dpt.equals("00") && !cod_dpt.equals(""))
				{
					listaMunicipio=getListaMunicipio(cod_dpt);
					listaLocalidad=new ArrayList();
				}
				else
				{
					listaMunicipio=new ArrayList();
					listaLocalidad=new ArrayList();
				}
			}
			else
			{
				listaMunicipio=new ArrayList();
				listaLocalidad=new ArrayList();
			}
			if(request.getParameter("municipio")!=null)
			{
				String cod_mun=request.getParameter("municipio");
				if(!cod_mun.equals("00000") && !cod_mun.equals(""))
					listaLocalidad=getListaLocalidad(cod_mun);
				else
					listaLocalidad=new ArrayList();
			}
			else
				listaLocalidad=new ArrayList();
			
			// Deberiamos setear los Nombres del GSEGMENTO a las peticiones obtenidas.
			HashMap PR = new HashMap(); 
			for (int i=0; listaSegmento!=null && i<listaSegmento.size(); i++) {
				SegmentoDTO sDto = (SegmentoDTO) listaSegmento.get(i);
				if ( sDto == null || sDto.getSegmId()==null )
					continue;
				PR.put( "SEG_"+sDto.getSegmId(), sDto.getSegmDescripcion() );
			}

			for (int i=0; listaAp!=null && i<listaAp.size(); i++) {
				AplicacionDTO apDto = (AplicacionDTO) listaAp.get(i);
				if ( apDto == null || apDto.getApId()==null )
					continue;
				PR.put( "AP_"+apDto.getApId(), apDto.getApNombre() );
			}

			ArrayList listIdBi = new ArrayList();			
			ArrayList listaPetico = (ArrayList) tabla.getElementos();
			for (int i=0; listaPetico!=null && i<listaPetico.size(); i++) {
				PeticionDTO pDto = (PeticionDTO) listaPetico.get(i);
				if ( pDto == null || pDto.getIdBloqueSegmento()==null )
					continue;
				pDto.setNombreBloqueSegmento( (String) PR.get( "SEG_"+ pDto.getIdBloqueSegmento() ) );
				pDto.setAplicacionNombre( (String) PR.get( "AP_"+ pDto.getApId() ) );
				listIdBi.add( pDto.getBiId() );
			}
			
			//TODO: Se saca el valor de los segundos de bloqueo del vpistbbaConfig.properties
			ParametroDTO paraDto = new ParametroDTO();
			paraDto.setNombreParametro("Segundos");
			paraDto.setValorParametro(VpistbbaConfig.getVariable("Segundos"));

			// TODO: Cambiar esto. Tambien Revisar la Bandeja Integrada y Bandeja Supervisor 
			// 1.- sacar los Campos Variables del Usuario.
			// 2.- Sacar los Valores Variables de el Listado de Peticiones.
			// 3.- Hacer Merge... 
			UsuariosSessionLocalHome uHome = (UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
			UsuariosSessionLocal uLocal = uHome.create();
			ArrayList listValoresVariables = (ArrayList) uLocal.recuperarValoresVariables(idUsuario, listIdBi);

			// Obtenemos listado de Acciones Masivas
			ArrayList listAcciones = null;
			
			if ( listRolesUsuario.size() > 0 )
			{
				listAcciones = (ArrayList) bandeja.recuperarAcciones(listRolesUsuario);
			} 	
			
			// Obtenemos Mapas de Aplciaciones (?).
			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			HashMap mapAplicaciones = url.mapAplicaciones();

			// Los valores Variables.
			request.setAttribute("listValoresVariables", listValoresVariables);

			/**envio datos al JSP**/			
			request.setAttribute("segundos", paraDto);
			request.setAttribute("peticiones", tabla);
			request.setAttribute("nombreTabla", "peticiones");
			request.setAttribute("listAgencias", listaAg);
			request.setAttribute("listAcciones",listAcciones);
			request.setAttribute("listIsps",listaIsp);
			request.setAttribute("listSegmentos",listaSegmento);
			request.setAttribute("listRolesUsuario",listRolesUsuario);
			request.setAttribute("listAplicaciones",listaAp);
			request.setAttribute("listaDepartamento",listaDepartamento);
			request.setAttribute("listaMunicipio",listaMunicipio);
			request.setAttribute("listLocalidad",listaLocalidad);			
			request.setAttribute("campoOrden",(String)map.get("campoOrden"));			
			request.setAttribute("tipoOrden",(String)map.get("tipoOrden"));			
			request.setAttribute("mapAplicaciones", mapAplicaciones);
			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
			request.setAttribute("listaFlujo", listaFlujo);
			
			request.setAttribute("usuario", usuario);			

		}
		catch (Exception e) {
			log.error("Error en VerBandeja. [" + idUsuario + "]: " + e.getMessage(), e);
			// Deberia mandarlo a una  pagina de Error. 
		}
	}

	/**
	 * @param cod_mun
	 * @return
	 */
	private ArrayList getListaLocalidad(String cod_mun)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaLocalidadesPorMunicipio(cod_mun);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}

	/**
	 * @param cod_dpt
	 * @return
	 */
	private ArrayList getListaMunicipio(String cod_dpt)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaMunicipiosPorDepto(cod_dpt);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}

	private UsuariosSessionLocal getUsuariosSession() throws NamingException, CreateException {
		UsuariosSessionLocalHome home =
					(UsuariosSessionLocalHome)HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
		UsuariosSessionLocal local = home.create();
		return local;
	}

	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}


	private void getSBLocal() throws NamingException, CreateException {
		BIntegradaSessionLocalHome biHome =  
					(BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		sbLocal = biHome.create();
	}

	BIntegradaSessionLocal sbLocal = null;

	private ArrayList getLista( String tipo )
	{
		ArrayList listado = new ArrayList();

		try {
			if ( sbLocal == null )
				getSBLocal();
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		}

		try {
			/*
			if ( "LOCALIDAD".equals( tipo ) )
				listado = sbLocal.recuperarLocalidadesCodNombre();
			*/	
			if ( "SEGMENTO".equals( tipo ) )
				listado = sbLocal.recuperaSegmentos();
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();
			if("DEPARTAMENTO".equals(tipo))
				listado=sbLocal.recuperarDepartamento();

		} catch (BandejaException e) {
			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}
	
	private HashMap recuperaFiltrosFijos(HttpServletRequest request)
	{
		
		HashMap map = new HashMap();
		
		if(request.getParameter("departamento")!=null)
		{
			String departamento=request.getParameter("departamento");
			if(!departamento.equals("00"))
			{
				map.put("COD_DPT", UtilesBandeja.sinNull(request.getParameter("departamento"),""));
			}
		}
		if(request.getParameter("localidad")!=null)
		{
			String localidad=request.getParameter("localidad");
			if(!localidad.equals("00000000"))
			{
				map.put("COD_LOC", UtilesBandeja.sinNull(request.getParameter("localidad"),""));
			}
		}
		
		map.put("fTC_Actividad",UtilesBandeja.sinNull(request.getParameter("fTC_Actividad"),"4"));
		
		map.put("BI_NRO_PETICION_ATIS", UtilesBandeja.sinNull(request.getParameter("numPeticionaATIS"),""));

		map.put( "BI_NRO_PETICION", UtilesBandeja.sinNull(request.getParameter("numPeticion"), "") );
		if(request.getParameter("rutCliente")!=null)
		{
			map.put( "BI_CLIENTE_RUT", UtilesBandeja.sinNull(request.getParameter("rutCliente"), "") );
			if(request.getParameter("dvCliente")!=null)
				map.put( "BI_CLIENTE_RUTDV", UtilesBandeja.sinNull(request.getParameter("dvCliente"), "") );	
		}
		map.put( "AP_ID", UtilesBandeja.sinNull(request.getParameter("aplicacion"), "") );
		map.put( "AGEN_ID", UtilesBandeja.sinNull(request.getParameter("agencia"), "") );
		map.put( "SEGM_ID", UtilesBandeja.sinNull(request.getParameter("segmento"), "") );

		map.put( "ROL_ID", UtilesBandeja.sinNull(request.getParameter("rol"), "") );
		map.put( "ISP_ID", UtilesBandeja.sinNull(request.getParameter("isp"), "") );
		map.put( "BI_CLIENTE_AREA", UtilesBandeja.sinNull(request.getParameter("areaFono"), "") );
		map.put( "BI_CLIENTE_SERVICIO", UtilesBandeja.sinNull(request.getParameter("numFono"), "") );
		map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );

		if(request.getParameter("familiaPetiAtis")!=null)
		{
			String fam = UtilesBandeja.sinNull(request.getParameter("familiaPetiAtis"), "");
			if(!fam.equals("00"))
			{
				map.put( "BI_FAMILIA_PS", fam );
			}
		}
		

		map.put( "TICA_ID", UtilesBandeja.sinNull(request.getParameter("TICA"), "") );
		map.put( "PCOM", UtilesBandeja.sinNull(request.getParameter("PCOM"), "") );
		map.put( "CCN", UtilesBandeja.sinNull(request.getParameter("CCN"), "") );

		map.put( "tipoOrden", UtilesBandeja.sinNull(request.getParameter("tipoOrden"), "") );
		map.put( "campoOrden", UtilesBandeja.sinNull(request.getParameter("campoOrden"), "") );
		
		if(request.getParameter("opcocat")!=null && !request.getParameter("opcocat").equals("0"))
			map.put("opcocat",request.getParameter("opcocat"));
			
		return (map);
		
	}

	private int getInt(String str, int def) {
		if ( str==null )
			return def;

		try { 
			return ( Integer.parseInt( str ) );
		} catch (Exception e) {
		}
		return def;
	}


	private TreeMap getTipoTrabajoOtros(){
		TreeMap t = new TreeMap();
		t.put("EN CURSO",   String.valueOf(ComunInterfaces.estadoPeticionEnCurso));
		t.put("EN REVERSA", String.valueOf(ComunInterfaces.estadoPeticionCancelada)+","+String.valueOf(ComunInterfaces.estadoPeticionTerminada));
		return t;
	}

}
