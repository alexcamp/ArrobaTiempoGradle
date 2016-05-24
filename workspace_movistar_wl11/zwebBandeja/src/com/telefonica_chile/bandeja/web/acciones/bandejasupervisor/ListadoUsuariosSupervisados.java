package com.telefonica_chile.bandeja.web.acciones.bandejasupervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.mantenedores.UsuarioBandejaSupervisorDTO;
import com.telefonica_chile.bandeja.supervisor.sessions.SupervisorException;

public class ListadoUsuariosSupervisados {
	private HttpServletRequest request;
	private String username;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public ListadoUsuariosSupervisados(HttpServletRequest request, String username) {
		this.request = request;
		this.username = username;
			
	}
	
	/**
	 * Recupera un listado de los usuarios supervisados por mi (login de sesion), con la *cantidad* de peticiones
	 * que los tienen como responsables.
	 * 
	 * @return Resultado (para Accion.setResultado(x))
	 */
	public String listarUsuarios() throws SupervisorException, BandejaException {
		Long idUsuario = null;
		
		int pagina = this.getPaginaActual(request);
		int paginacion = 10;
		
		idUsuario = new Long(request.getParameter("USUA_ID"));
		
		if (request.getParameter("dpp")!=null) {
			paginacion = new Integer(request.getParameter("dpp")).intValue() ;
		}

		Long idRol = getRol();
		
		Tabla usuariosSupervisados = getBandejaSession().recuperaUsuariosSupervisadosByPaginacion(username, idRol, pagina, paginacion);
		//Map habilitados = getBandejaSession().recuperaHabilitadosUsuarioRol(idRol);
		//TODO: Ver esto
		List habilitados = new ArrayList();//getBandejaSession().recuperaHabilitadosUsuarioRol(idRol);

		if( log.isDebugEnabled() )
			log.debug("Cantidad de habilitados para rol["+idRol+"] ="+habilitados.size());
		
		for (Iterator it = usuariosSupervisados.iterator();it.hasNext();) {
			UsuarioBandejaSupervisorDTO u = (UsuarioBandejaSupervisorDTO)it.next();
			Long idUsu = u.getId();
			if (habilitados.contains(idUsu))
			{
				if( log.isDebugEnabled() )
					log.debug("Listado contiene al usuario["+idUsu+"].");
				u.setHabilitado("S");
			}
			else
			{
				if( log.isDebugEnabled() )
					log.debug("Listado NO contiene al usuario["+idUsu+"].");
				u.setHabilitado("N");	
			}
			
			if ( log.isDebugEnabled() )
				log.debug("USUARIO["+idUsu+"], HABILITADO["+u.getHabilitado()+"]");
		}
		

		log.debug("VOY A ENVIAR A FILTRAR ==>"+paginacion+"<== páginas");
				
		request.setAttribute("usuariosSupervisados", usuariosSupervisados);
		request.setAttribute("nombreTabla", "usuariosSupervisados");
		request.setAttribute("idRol", idRol);

		return null;
	}

//-------------------------------------------------------


  public String listarUsuarios2(HashMap datosFiltro) throws SupervisorException, BandejaException {
	  Long idUsuario = null;
		
	  int pagina = this.getPaginaActual(request);
	  int paginacion = 10;
		
	  idUsuario = new Long(request.getParameter("USUA_ID"));
		
	  if (request.getParameter("dpp")!=null) {
		  paginacion = new Integer(request.getParameter("dpp")).intValue() ;
	  }

	  Long idRol = getRol();
		
	  Tabla usuariosSupervisados = getBandejaSession().recuperaUsuariosSupervisadosByPaginacion(username, idRol, pagina, paginacion,datosFiltro);
	  	
	  //Map habilitados = getBandejaSession().recuperaHabilitadosUsuarioRol(idRol);
		
		//TODO: ver esto
	  List habilitados = new ArrayList();//getBandejaSession().recuperaHabilitadosUsuarioRol(idRol);

	  if( log.isDebugEnabled() )
		  log.debug("Cantidad de habilitados para rol["+idRol+"] ="+habilitados.size());
		
	  for (Iterator it = usuariosSupervisados.iterator();it.hasNext();) {
		  UsuarioBandejaSupervisorDTO u = (UsuarioBandejaSupervisorDTO)it.next();
		  Long idUsu = u.getId();
		  if (habilitados.contains(idUsu))
		  {
			  if( log.isDebugEnabled() )
				  log.debug("Listado contiene al usuario["+idUsu+"].");
			  u.setHabilitado("S");
		  }
		  else
		  {
			  if( log.isDebugEnabled() )
				  log.debug("Listado NO contiene al usuario["+idUsu+"].");
			  u.setHabilitado("N");	
		  }
			
		  if ( log.isDebugEnabled() )
			  log.debug("USUARIO["+idUsu+"], HABILITADO["+u.getHabilitado()+"]");
	  }
		

	  log.debug("VOY A ENVIAR A FILTRAR ==>"+paginacion+"<== páginas");
				
	  request.setAttribute("usuariosSupervisados", usuariosSupervisados);
	  request.setAttribute("nombreTabla", "usuariosSupervisados");
	  request.setAttribute("idRol", idRol);

	  return null;
  }

//------------------------------------------------------



	private Long getRol() throws SupervisorException {
		Long idRol = null;
		try {
			idRol = new Long(request.getParameter("idRol"));
		} catch (Exception e) {
			throw new SupervisorException("No se pudo recuperar rol", e);
		}
		return idRol;
	}

	public int getPaginaActual(HttpServletRequest request) {
		int pagina = 1;
		try { pagina = Integer.parseInt(request.getParameter("PAGINA_ACTUAL")); } catch (Exception e) { }
		
		return pagina;
	}

	public BIntegradaSessionLocal getBandejaSession() throws BandejaException {
		try {
			BIntegradaSessionLocalHome home = (BIntegradaSessionLocalHome)
					HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
			BIntegradaSessionLocal local = home.create();
			return local;
		} catch (Exception e) {
			throw new BandejaException("Problemas obteniendo BIntegradaSession", e);
		}
	}
}
