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

/**
 * @author lcaldera
 *
 */
public class VerBandejaEsperaTerraAcc extends Accion
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
	
		//						Sacamos la pagina Actual y la Paginacion. 
				int pagina = getInt(request.getParameter("PAGINA_ACTUAL"), 1);
				if ( pagina < 1 )
					 ++pagina;
				int intPaginacion = getInt(request.getParameter("dpp"), 10);
					if ( intPaginacion < 10 )
					 intPaginacion = 10;
	
				UsuariosSessionLocal usuariosSession = getUsuariosSession();
				TreeMap otrostipos = getTipoTrabajoOtros();	
	
				BandejaSessionLocalHome bdHome =(BandejaSessionLocalHome) HomeFactory.getHome(BandejaSessionLocalHome.JNDI_NAME);
				BandejaSessionLocal bandeja =  bdHome.create();
			
				HashMap map = recuperaFiltrosFijos(request);
				map.put("USUA_ID", usuario.getId().toString());
				
				BIntegradaSessionLocal biLocal = getBandejaSession();
				String sorden=request.getParameter("orden");
				Integer orden=null;
				if(sorden==null)
					orden=new Integer(1);
				else
					orden=new Integer(sorden);
			
				Tabla tabla = null;
				if(request.getParameter("realizarBusqueda")!=null)
					tabla =(Tabla) biLocal.setearDatoTablaEsperaTerra(map,pagina,intPaginacion,orden.intValue());
			
						
				//ArrayList listaAp = getLista( "APLICACION" );
				
				//ArrayList listaEmpresa = getLista("EMPRESA");
				
				ArrayList listaSegmento = getLista( "SEGMENTO" );
				ArrayList listaFiltroTecnicos=null;
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
			
				request.setAttribute("listaBackOffice", tabla);
				request.setAttribute("nombreTabla", "listaBackOffice");
				
				//request.setAttribute("listAplicaciones",listaAp);
				
				request.setAttribute("listaDepartamento",listaDepartamento);
				request.setAttribute("listaMunicipio",listaMunicipio);
				request.setAttribute("listLocalidad",listaLocalidad);		

				//request.setAttribute("mapAplicaciones", mapAplicaciones);
				
				request.setAttribute("urlVPISTBBA", urlVPISTBBA);
				request.setAttribute("otrostipos", otrostipos);
				request.setAttribute("listSegmentos",listaSegmento);

				request.setAttribute("usuario", usuario);
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
	
		private BIntegradaSessionLocal sbLocal = null;

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
	
		private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException
		{
			BIntegradaSessionLocalHome home =  
						(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
			BIntegradaSessionLocal local = home.create();
			return local;
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

			map.put( "AP_ID", UtilesBandeja.sinNull(request.getParameter("aplicacion"), "") );
			if(request.getParameter("segmento")!=null && !request.getParameter("segmento").equals(""))
				map.put( "SEGM_ID", UtilesBandeja.sinNull(request.getParameter("segmento"), "") );
		
			map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
			map.put("DIFDAYS",UtilesBandeja.sinNull(request.getParameter("DIFDAYS"),""));

			if(request.getParameter("familiaPetiAtis")!=null)
			{
				String fam = UtilesBandeja.sinNull(request.getParameter("familiaPetiAtis"), "");
				if(!fam.equals("00"))
				{
					map.put( "BI_FAMILIA_PS", fam );
				}
			}
		
			map.put( "fechaIni", UtilesBandeja.sinNull(request.getParameter("fechaIni"), "") );
			map.put( "fechaFin", UtilesBandeja.sinNull(request.getParameter("fechaFin"), "") );
		
			if(request.getParameter("estado")!=null && !request.getParameter("estado").equals("0"))
				map.put( "estado", UtilesBandeja.sinNull(request.getParameter("estado"), "") );
			
			if(request.getParameter("otrostipos")!=null && !request.getParameter("otrostipos").equals(""))
				map.put( "otrostipos", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );
		
			return (map);
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
			try
			{
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
	
		private TreeMap getTipoTrabajoOtros()
		{
			TreeMap t = new TreeMap();
			t.put("EN CURSO",   String.valueOf(ComunInterfaces.estadoPeticionEnCurso));
			t.put("CANCELADA", String.valueOf(ComunInterfaces.estadoPeticionCancelada));
			t.put("TERMINADA",String.valueOf(ComunInterfaces.estadoPeticionTerminada));
			return t;
		}
	}

