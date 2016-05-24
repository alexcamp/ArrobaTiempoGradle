package com.telefonica_chile.bandeja.web.acciones.mantenedores.usuarios;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;
import com.telefonica_chile.bandeja.mantenedores.session.MantenedoresSessionLocal;
import com.telefonica_chile.bandeja.mantenedores.usuarios.SupervisoresManager;
import com.telefonica_chile.bandeja.mantenedores.usuarios.UsuarioManager;

public class ListadoUsuarios {
	private HttpServletRequest request;
	private MantenedoresSessionLocal mantenedores;
	public ListadoUsuarios(HttpServletRequest request, MantenedoresSessionLocal mantenedores) {
		this.request = request;
		this.mantenedores = mantenedores;
	}

	public String listarUsuarios() throws ManagerException {
		HashMap filtros = recuperaFiltroListado(request);
		int pagina = getPaginaActual(request);
		Tabla tabla = mantenedores.list(UsuarioManager.class, filtros, pagina);
		request.setAttribute("usuarios", tabla);
		request.setAttribute("nombreTabla", "usuarios");
		return null;
	}
	
	public String buscarSupervisores() throws ManagerException {
		HashMap filtros = recuperaFiltroSupervisores(request);
		int pagina = getPaginaActual(request);
		Tabla tabla = mantenedores.list(SupervisoresManager.class, filtros, pagina);
		request.setAttribute("usuarios", tabla);
		request.setAttribute("nombreTabla", "usuarios");
		return "resultadoBusquedaSupervisor";
	}


	private int getPaginaActual(HttpServletRequest request) {
		int p = 1;
		try { p = Integer.parseInt(request.getParameter("PAGINA_ACTUAL")); } catch (Exception e) { }
		if (p <= 0)
			p = 1;
		return p;
	}

	private HashMap recuperaFiltroListado(HttpServletRequest request) {
		HashMap map = new HashMap();

		long id = 0;
		try { id = Long.parseLong(request.getParameter("FILTRO_ID")); } catch (Exception e) {	}
		if (id > 0)
			map.put("FILTRO_ID", String.valueOf(id));

		String login = request.getParameter("FILTRO_LOGIN");
		if (login != null && login.trim().length() > 0)
			map.put("FILTRO_LOGIN", login);
		
		return map;
	}

	private HashMap recuperaFiltroSupervisores(HttpServletRequest request) {
		HashMap map = new HashMap();

		long idRolSup = 0;
		try { idRolSup = Long.parseLong(request.getParameter("idRolSup")); } catch (Exception e) { }
		if (idRolSup > 0)
			map.put("idRolSup", String.valueOf(idRolSup));

		long idUsuSub = 0;
		try { idUsuSub = Long.parseLong(request.getParameter("idUsuSub")); } catch (Exception e) {}
		if (idUsuSub > 0)
			map.put("idUsuSub", String.valueOf(idUsuSub));
		
		String usernameBusqueda = request.getParameter("usernameBusqueda");
		if (usernameBusqueda != null && usernameBusqueda.length() > 0)
			map.put("usernameBusqueda", usernameBusqueda);

		return map;
	}
}
