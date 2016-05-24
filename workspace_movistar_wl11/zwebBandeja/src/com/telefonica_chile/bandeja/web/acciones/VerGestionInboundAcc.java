/*
 * Created on Feb 16, 2005
 *
 * Clase ejecutora que ejerce la tarea de ver todas las cruzadas existentes
 */
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
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocal;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocalHome;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;
import com.telefonica_chile.comun.ComunInterfaces;

public class VerGestionInboundAcc extends Accion {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		try {
			HttpSession session = request.getSession(true);

			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			HashMap mapAplicaciones = url.mapAplicaciones();
			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
			request.setAttribute("mapAplicaciones", mapAplicaciones);
			

			// Obtengo el resto de los Fitltros.
			ParametrosTCSessionLocalHome ptcHome =  
						(ParametrosTCSessionLocalHome) HomeFactory.getHome(ParametrosTCSessionLocalHome.JNDI_NAME);			
			ParametrosTCSessionLocal ptcLocal = ptcHome.create();
			TreeMap agencias = ptcLocal.getNombreDepartamento(),
					tipotrabajo = new TreeMap(),
					
					// CR16429 FindAll - ana santos
					//familia = ptcLocal.getNombreFamiilaProductoServicio(),
					
					fecha = this.getFechaCompromiso(),
					otrostipos= this.getTipoTrabajoOtros();
			
			TreeMap listaFlujo = getTipoTrabajoOtros();
//			
//			request.setAttribute("listaFlujo", otrostipos);
			
			ArrayList listSegmentos = getLista( "SEGMENTO" );
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
			if(request.getParameter("municipio")!=null && !request.getParameter("municipio").equals(""))
			{
				String cod_mun=request.getParameter("municipio");
				if(!cod_mun.equals("00000"))
					listaLocalidad=getListaLocalidad(cod_mun);
				else
					listaLocalidad=new ArrayList();
			}
			else
				listaLocalidad=new ArrayList();

			// Seteamos los datos para los filtros
			request.setAttribute("fTC_Departamento", listaDepartamento);
			request.setAttribute("fTC_Segmento", listSegmentos);
			
			request.setAttribute("listaDepartamento",listaDepartamento);
			request.setAttribute("listaMunicipio",listaMunicipio);
			request.setAttribute("listLocalidad",listaLocalidad);
			
			request.setAttribute("fTC_Municipio", listaMunicipio);
			request.setAttribute("fTC_Localidad", listaLocalidad);
  			request.setAttribute("listaFlujo", listaFlujo);

		} catch (Exception e) {
			log.error("Exception en VerNuevaTorreControl", e);
		}
	}


	
	private TreeMap getFechaCompromiso(){
		TreeMap t = new TreeMap();
		t.put("Fecha Compromiso No Cumplida", "<0");
		t.put("Se Cumple Fecha Compromiso Hoy", "=0");
		t.put("Se Cumple Fecha Compromiso 5 Proximos Dias", "in (1,2,3,4,5) ");
		return t;
	}
	
	private TreeMap getTipoTrabajoOtros(){
		TreeMap t = new TreeMap();
		t.put("EN CURSO",   String.valueOf(ComunInterfaces.estadoPeticionEnCurso));
		t.put("EN REVERSA", String.valueOf(ComunInterfaces.estadoPeticionCancelada)+","+String.valueOf(ComunInterfaces.estadoPeticionTerminada));
		return t;
	}

	BIntegradaSessionLocal sbLocal = null;

	private void getSBLocal() throws NamingException, CreateException {
		BIntegradaSessionLocalHome biHome =  
					(BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		sbLocal = biHome.create();
	}

	private ArrayList getLista( String tipo ) {
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
		} catch (NamingException e) {
			log.error("NamingException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		} catch (CreateException e) {
			log.error("CreateException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		}

		try
		{
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();
			if("SEGMENTO".equals(tipo))
				listado=sbLocal.recuperaSegmentos();
			if("DEPARTAMENTO".equals(tipo))
				listado=sbLocal.recuperarDepartamento();

		} catch (Exception e)
		{
			log.error("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
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
}
