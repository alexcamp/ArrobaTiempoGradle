/*
 * Created on Nov 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.dto.PeticionDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;
import com.telefonica_chile.comun.parametro.session.ParametroDTO;
/**
 * @author gavalen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListarBandejaIntegradaAcc  extends Accion {
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	// Esta Clase muestra un Resumen de Peticiones para Generar los Archivos del SSVA   	

	protected void ejecutar(HttpServletRequest request) throws Evento {
	
		try {
			HttpSession session = request.getSession(true);
			AtiempoControladorDeAplicacion control =(AtiempoControladorDeAplicacion) getControladorDeAplicacion();
			UsuarioWeb usuario = control.getUsuario();
			
			BIntegradaSessionLocal bLocal = getBandejaSession();

			HashMap map = recuperaFiltrosFijos(request);
			map.put("FILTROS_VARIABLES", recuperaFiltrosVariables(request));
			map.put("USUA_ID", usuario.getId().toString());

			Tabla tabla = (Tabla) bLocal.buscarListadoPeticiones(map);
			ArrayList listaId = (ArrayList) tabla.getListaIdElements();
			HashMap mapActividad = new HashMap();
			for (int i=0; listaId!=null && i<listaId.size(); i++) {
				PeticionDTO p = (PeticionDTO) listaId.get(i);
				Integer aux = ( Integer ) mapActividad.get( p.getActividadDescripcion() );
				//log.info("Encontrrado: [" + p.getActividadDescripcion() + "," + aux + "]");
				if (aux == null)
					mapActividad.put( p.getActividadDescripcion(), new Integer(1) );
				else
					mapActividad.put( p.getActividadDescripcion(), new Integer( aux.intValue()+ 1 ) );
			}
			
			//TODO: Se saca el valor de los segundos de bloqueo del vpistbbaConfig.properties
			ParametroDTO paraDto = new ParametroDTO();
			paraDto.setNombreParametro("Segundos");
			paraDto.setValorParametro(VpistbbaConfig.getVariable("Segundos"));
			
			UrlAplicaciones url = new UrlAplicaciones();
			String urlVPISTBBA = url.getUrl("VPISTBBA");
			request.setAttribute("subsegundos", paraDto);
			request.setAttribute("ListadoPeticiones", listaId );
			request.setAttribute("urlVPISTBBA", urlVPISTBBA );
			request.setAttribute("mapaActividades", mapActividad );
		}
		 catch (Exception e) {
		}

	}

	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException {
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		
		return local;
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
	
	

}	

