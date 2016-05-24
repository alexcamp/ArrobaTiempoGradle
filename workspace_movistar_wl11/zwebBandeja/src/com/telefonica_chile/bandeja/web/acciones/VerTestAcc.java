/*
 * Created on Feb 16, 2005
 *
 * Clase ejecutora que ejerce la tarea de ver todas las cruzadas existentes
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocal;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocalHome;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;

//import com.telefonica_chile.bandeja.web.UsuarioWeb;

//import com.ibm.

/**
 * @author jmvelasc
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VerTestAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		try {
			HttpSession session = request.getSession(true);
			log.debug("VerNuevaTorreControlAcc");


			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			HashMap mapAplicaciones = url.mapAplicaciones();
			request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
			request.setAttribute("mapAplicaciones", mapAplicaciones);


			TorreControlSessionLocalHome tcHome = 
				(TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal tcLocal = tcHome.create();

			//Se agrega parametro para determinar aplicacion a la cual se le mostrara su torre de control 
			String appId = request.getParameter("appid");
			if ( appId==null || appId.length()==0 )
				appId = "3";
			request.setAttribute("appid", appId);
			
			// Obtengo los Datos de para los Filtros.
			HashMap filtro = recuperaFiltrosFijos( request );
			if ( filtro.get("AP_ID") != null )
				filtro.put("AP_ID", appId);
			
			// Obtengo Listado de Agencias.
			ArrayList agLista = getLista( "AGENCIA" );

			request.setAttribute("listaAgencias", agLista);

			// Obtengo el resto de los Fitltros.
			ParametrosTCSessionLocalHome ptcHome =  
						(ParametrosTCSessionLocalHome) HomeFactory.getHome(ParametrosTCSessionLocalHome.JNDI_NAME);			
			ParametrosTCSessionLocal ptcLocal = ptcHome.create();

			TreeMap agencias = new TreeMap(),
					tipotrabajo = ptcLocal.getNombreTiopTrabajo(),
					
					//segmento = ptcLocal.getNombreSegmento(),
					
					// CR16429 FindAll - ana santos
					//familia = ptcLocal.getNombreFamiilaProductoServicio(),
					
					aplicacion = ptcLocal.getNombreAplicacion(),
					isp = new TreeMap(),
					fecha = this.getFechaCompromiso(),
					otrostipos= this.getTipoTrabajoOtros();

			//ArrayList listSegmentos = getSegmentos();
			ArrayList listSegmentos = getLista( "GRUPO_SEGMENTO" );

			// Seteamos los datos para los filtros
			request.setAttribute("listSegmentos", listSegmentos);
			request.setAttribute("tipotrabajo", tipotrabajo);
			
			//request.setAttribute("segmento", segmento);
			
			// CR16429 FindAll - ana santos
			//request.setAttribute("familia", familia);
			
			request.setAttribute("aplicacion", aplicacion);
			request.setAttribute("agencias", agencias);
			request.setAttribute("isp", isp);
			request.setAttribute("fecha", fecha);
			request.setAttribute("otrostipos", otrostipos);


		} catch (Exception e) {
			log.error("Exception en VerNuevaTorreControl", e);
		}
			
		log.debug("Fin VerNuevaTorreControlAcc2");
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

	//
	private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
		HashMap map = new HashMap();

		map.put( "AP_ID", UtilesBandeja.sinNull(request.getParameter("aplicacion"), "") );
		map.put( "AGEN_ID", UtilesBandeja.sinNull(request.getParameter("agencia"), "") );
		map.put( "ACT_TC_ID", UtilesBandeja.sinNull(request.getParameter("actividad"), "") );
		map.put( "ACTIVIDAD_ID", UtilesBandeja.sinNull(request.getParameter("actividadFiltro"), "") );
		map.put( "BI_TIPO_TRABAJO", UtilesBandeja.sinNull(request.getParameter("tipo_trabajo"), "") );

		String fam = UtilesBandeja.sinNull(request.getParameter("familia"), "");
		if ( fam!=null && fam.length()>0 )
			fam = "'%" + fam + "%'";
		map.put( "BI_FAMILIA_PS", fam );

		map.put( "SEGM_ID", UtilesBandeja.sinNull(request.getParameter("segmento"), "") );
		map.put( "BI_FECHA_COMPROMISO", UtilesBandeja.sinNull(request.getParameter("fecha"), "") );
		map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );

		return (map);
	}
	
	private TreeMap getFechaCompromiso(){
		TreeMap t = new TreeMap();
		t.put("Fecha Compromiso No Cumplida", "<-1");
		t.put("Se Cumple Fecha Compromiso Hoy", "=0");
		t.put("Se Cumple Fecha Compromiso 5 Proximos Dias", "in (1,2,3,4,5) ");
		return t;
	}
	
	private TreeMap getTipoTrabajoOtros(){
		TreeMap t = new TreeMap();
		t.put("EN CURSO",   " =1");
		t.put("EN REVERSA", " in (3,7)");
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
		} catch (Exception e) {
			log.error("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}
}
