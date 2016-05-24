package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocal;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;
import com.telefonica_chile.comun.ComunInterfaces;

public class VerBandejaSupCoAcc extends Accion{

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento {
		Long idUsuario = null;
		try
		{
			HttpSession session = request.getSession(true);

			// Recuperamos el usuario de la Bandeja.
			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
			UsuarioWeb usuario = control.getUsuario();
			if ( usuario == null ) {
				log.info("No se encontro usuario en Session.");
			}
	
			idUsuario = usuario.getId();
	
			//Sacamos la pagina Actual y la Paginacion.

			int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
			if ( pagina < 1 )
				 ++pagina;
			int intPaginacion = getInt(request.getParameter("dpp"), 10);
				if ( intPaginacion < 10 )
				 intPaginacion = 10;
	
			UsuariosSessionLocal usuariosSession = getUsuariosSession();
			TreeMap otrostipos = getTipoTrabajoOtros();
			ArrayList listRolesUsuario = (ArrayList)usuariosSession.recuperaRolesUsuariosQueSuperviso(usuario.getId());	
	
			BandejaSessionLocalHome bdHome =(BandejaSessionLocalHome) HomeFactory.getHome(BandejaSessionLocalHome.JNDI_NAME);
			BandejaSessionLocal bandeja =  bdHome.create();
			
			HashMap map = recuperaFiltrosFijos(request,usuario);
			
			map.put("USUA_ID", usuario.getId().toString());
	
			BIntegradaSessionLocal biLocal = getBandejaSession();

			Tabla tabla =(Tabla) biLocal.setearDatoTablaSupCo(map,pagina,intPaginacion);
			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			
			//HashMap mapAplicaciones = url.mapAplicaciones();
			
			//ArrayList listaAp = getLista( "APLICACION" );
			
			request.setAttribute("listaBandejaSupCo", tabla);
			request.setAttribute("nombreTabla", "listaBandejaSupCo");
			
			//request.setAttribute("listAplicaciones",listaAp);
			
			request.setAttribute("listRolesUsuario",listRolesUsuario);
			log.debug("Usuario id:"+usuario.getId());
			request.setAttribute("usua_id",usuario.getId().toString());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
		}
	}

	private int getInt(String str, int def)
	{
		if ( str==null )
			return def;

		try { 
			return ( Integer.parseInt( str ) );
		} catch (Exception e) {
		}
		return def;
	}
	
	private UsuariosSessionLocal getUsuariosSession() throws NamingException, CreateException
	{
		UsuariosSessionLocalHome home = (UsuariosSessionLocalHome)HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
		UsuariosSessionLocal local = home.create();
		return local;
	}

	private TreeMap getTipoTrabajoOtros()
	{
		TreeMap t = new TreeMap();
		t.put("EN CURSO",   String.valueOf(ComunInterfaces.estadoPeticionEnCurso));
		t.put("CANCELADA", String.valueOf(ComunInterfaces.estadoPeticionCancelada));
		return t;
	}
	
	private HashMap recuperaFiltrosFijos(HttpServletRequest request,UsuarioWeb usuario)
	{
		HashMap map = new HashMap();
		
		if(request.getParameter("otrostipos")!=null && !request.getParameter("otrostipos").equals(""))
			map.put( "otrostipos", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
			
		if(request.getParameter("rol")!=null && !request.getParameter("rol").equals(""))
			map.put("rol",UtilesBandeja.sinNull(request.getParameter("rol"),""));
		map.put("usua_id",usuario.getId().toString());
		return (map);
	}
	
	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}
	
	private BIntegradaSessionLocal sbLocal = null;
	
	private void getSBLocal() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
		sbLocal = biHome.create();
	}
	
	private ArrayList getLista( String tipo )
	{
		ArrayList listado = new ArrayList();

		try
		{
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
			if("RANGO".equals(tipo))
				listado=sbLocal.recuperaRangos();
			if("TECNICO".equals(tipo))
				listado=sbLocal.recuperaTecnicosTodos();
			if("EMPRESA".equals(tipo))
				listado=sbLocal.recuperaEmpresasTodas();

		} catch (BandejaException e) {
			log.info("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}
}
