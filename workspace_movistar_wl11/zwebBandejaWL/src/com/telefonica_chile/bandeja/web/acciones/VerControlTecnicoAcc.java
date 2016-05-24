package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.RangoDTO;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocal;
import com.telefonica_chile.bandeja.datos.bandeja.BandejaSessionLocalHome;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;
import com.telefonica_chile.bandeja.dto.ControlTecnicoDTO;
import com.telefonica_chile.bandeja.dto.MatrizTecnicaDTO;
import com.telefonica_chile.bandeja.dto.ResultMatrizDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;

public class VerControlTecnicoAcc extends Accion
{

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
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
			
//			Sacamos la pagina Actual y la Paginacion. 
			int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
			if ( pagina < 1 )
				 ++pagina;
			int intPaginacion = getInt(request.getParameter("dpp"), 10);
				if ( intPaginacion < 10 )
				 intPaginacion = 10;
			
			UsuariosSessionLocal usuariosSession = getUsuariosSession();
			
			BandejaSessionLocalHome bdHome =(BandejaSessionLocalHome) HomeFactory.getHome(BandejaSessionLocalHome.JNDI_NAME);
			BandejaSessionLocal bandeja =  bdHome.create();
					
			HashMap map = recuperaFiltrosFijos(request);
			map.put("USUA_ID", usuario.getId().toString());
			
				
			
			BIntegradaSessionLocal biLocal = getBandejaSession();
			ArrayList listRangos=getLista("RANGO");
			
			Tabla tabla = null;
			if(request.getParameter("filtroInicial")!=null)
			{
				tabla= (Tabla) biLocal.setearDatoTablaControlTecnico(map,pagina,intPaginacion);
				
				List listaMatriz=tabla.getListaopcional();
				ResultMatrizDTO matrizDTO=new ResultMatrizDTO();
				for(Iterator iterator=tabla.iterator();iterator.hasNext();)
				{
					ControlTecnicoDTO controlTecnicoDTO=(ControlTecnicoDTO)iterator.next();
					String codTecnico=controlTecnicoDTO.getCodTecnico();
					for(Iterator iterator2=listRangos.iterator();iterator2.hasNext();)
					{
						RangoDTO rangoDTO=(RangoDTO)iterator2.next();
						Integer idRango=rangoDTO.getIdRango();
						int cantidad=cantidadPeticionTecnicoEnRango(codTecnico,idRango,listaMatriz);
						matrizDTO.addResultadoTec(codTecnico,idRango,cantidad);
					}
				}

				request.setAttribute("resultMatriz",matrizDTO);
			}
					
			ArrayList listaAp = getLista( "APLICACION" );
			ArrayList listaEmpresa = getLista("EMPRESA");
			ArrayList listaFiltroTecnicos=null;
			if(request.getParameter("idContratista")!=null && !request.getParameter("idContratista").equals(""))
			{
				try
				{
					Long idContratista=new Long(request.getParameter("idContratista"));
					log.debug("Id de contratista:"+idContratista);
					listaFiltroTecnicos=getListaTecnico(idContratista);
				}
				catch(NumberFormatException nf)
				{
					nf.printStackTrace();
					listaFiltroTecnicos=getLista("TECNICO");
				}
			}
			else
				listaFiltroTecnicos=getLista("TECNICO");
			ArrayList listaDepartamento= getLista("DEPARTAMENTO");
			
			ArrayList listaMunicipio=null;
			ArrayList listaLocalidad=null;
			if(request.getParameter("departamento")!=null)
			{
				String cod_dpt=request.getParameter("departamento");
				if(!cod_dpt.equals("00"))
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
				if(!cod_mun.equals("00000"))
					listaLocalidad=getListaLocalidad(cod_mun);
				else
					listaLocalidad=new ArrayList();
			}
			else
				listaLocalidad=new ArrayList();
			
			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			
			//HashMap mapAplicaciones = url.mapAplicaciones();
			
			request.setAttribute("listaTecnicos", tabla);
			request.setAttribute("nombreTabla", "listaTecnicos");
			request.setAttribute("listAplicaciones",listaAp);
			request.setAttribute("listaDepartamento",listaDepartamento);
			request.setAttribute("listaMunicipio",listaMunicipio);
			request.setAttribute("listLocalidad",listaLocalidad);
			request.setAttribute("listRangos",listRangos);
			request.setAttribute("listaFiltroTecnicos",listaFiltroTecnicos);
			request.setAttribute("listaEmpresa",listaEmpresa);			
			
			//request.setAttribute("mapAplicaciones", mapAplicaciones);
			
			request.setAttribute("urlVPISTBBA", urlVPISTBBA);
			
			request.setAttribute("usuario", usuario);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
		}
		
	}

	private int cantidadPeticionTecnicoEnRango(String codTecnico, Integer idRango, List listaMatriz)
	{
		int total=0;
		for(Iterator iterator=listaMatriz.iterator();iterator.hasNext();)
		{
			MatrizTecnicaDTO matrizTecnicaDTO=(MatrizTecnicaDTO) iterator.next();
			if(matrizTecnicaDTO.getCodTecnico().equals(codTecnico) && matrizTecnicaDTO.getIdRango().equals(idRango))
				total++;
		}
		return total;
	}

	/**
	 * @param idContratista
	 * @return
	 */
	private ArrayList getListaTecnico(Long idContratista)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaTecnicosDeContratista(idContratista);
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
	 * @param request
	 * @return
	 */
	private HashMap recuperaFiltrosFijos(HttpServletRequest request)
	{
		HashMap map = new HashMap();
		
		if(request.getParameter("codTecnico")!=null)
		{
			String codTecnico=request.getParameter("codTecnico");
			if(!codTecnico.equals("0"))
			{
				map.put("COD_TECNICO",UtilesBandeja.sinNull(request.getParameter("codTecnico"),""));				
			}
		}
		
		map.put("AP_ID",UtilesBandeja.sinNull(request.getParameter("aplicacion"),""));
		
		if(request.getParameter("idContratista")!=null)
			map.put("ID_CONTRA",UtilesBandeja.sinNull(request.getParameter("idContratista"),""));
		if(request.getParameter("fechaCompromiso")!=null)
			map.put("FECHA_COM", UtilesBandeja.sinNull(request.getParameter("fechaCompromiso"),""));
		else
			map.put("FECHA_COM",new Fecha().getFechaconFormato(8));//dd/MM/yyyy
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
		return (map);
		
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
	
	BIntegradaSessionLocal sbLocal = null;

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
	
	private void getSBLocal() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome biHome = (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);
		sbLocal = biHome.create();
	}
	
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
	
	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}
}
