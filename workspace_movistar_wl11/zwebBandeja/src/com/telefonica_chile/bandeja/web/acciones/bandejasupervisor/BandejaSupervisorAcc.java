package com.telefonica_chile.bandeja.web.acciones.bandejasupervisor;

import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.supervisor.sessions.SupervisorException;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocal;
import com.telefonica_chile.bandeja.torreControl.ParametrosTCSessionLocalHome;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.acciones.UtilesBandeja;
import com.telefonica_chile.bandeja.web.utiles.UrlAplicaciones;

public class BandejaSupervisorAcc extends Accion {
	public static final int ACCION_DESPLIEGA_FILTRO = 1;
	public static final int ACCION_LISTADO_USUARIOS = 2;
	public static final int ACCION_LISTADO_PETICIONES = 3;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		int accion = ACCION_DESPLIEGA_FILTRO;
		
		// ENVÍO LAS URL CORRESPONDIENTE A CADA APLICACION para los menú de la cabecera
		UrlAplicaciones url = new UrlAplicaciones();
		String urlVPISTBBA = url.getUrl("VPISTBBA");
		log.debug("urlVPISTBBA --> " + urlVPISTBBA );
		HashMap mapAplicaciones = url.mapAplicaciones();

		request.setAttribute("urlVPISTBBA", urlVPISTBBA);			
		request.setAttribute("mapAplicaciones", mapAplicaciones);
		

		try {
			  // Obtengo el resto de los Fitltros.
			  ParametrosTCSessionLocalHome ptcHome =  
						  (ParametrosTCSessionLocalHome) HomeFactory.getHome(ParametrosTCSessionLocalHome.JNDI_NAME);			
			  ParametrosTCSessionLocal ptcLocal = ptcHome.create();
			
			  TreeMap tipotrabajo = ptcLocal.getNombreTiopTrabajo(),
			  
			  		  // CR16429 FindAll - ana santos
					  //familia = ptcLocal.getNombreFamiilaProductoServicio(),
					  otrostipos= this.getTipoTrabajoOtros(),
					  fecha = this.getFechaCompromiso();
						
			
			  // Seteamos los datos para los filtros
			  log.debug("TIPO DE TRABAJO  => " + request.getParameter("tipoTrab"));
			  log.debug("FECHA COMPROMISO => " + request.getParameter("fechaCompromiso"));
              log.debug("FAMILIA	      => " + request.getParameter("familiaProd"));
			  log.debug("FLUJO            => " + request.getParameter("tiposFlujo"));

			  
			  request.setAttribute("tipotrabajo", tipotrabajo);
			  
			  // CR16429 FindAll - ana santos
			  //request.setAttribute("familia", familia);
			  
			  request.setAttribute("otrostipos", otrostipos);
			  request.setAttribute("fecha", fecha);
		
		} catch (Exception e1) {
			log.debug("Error ejecutamdo ParametrosTCSessionLocalHome " + e1.toString());
			//e1.printStackTrace();
		} 

		try { accion = Integer.parseInt(request.getParameter("accion")); 
			} catch (Exception e) { }

		try {
			execute(request, accion);
		
		} catch (Exception e) {
			log.error("", e);
		}
		
	}

	private void execute(HttpServletRequest request, int accion) throws BandejaException, SupervisorException {
		AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion)getControladorDeAplicacion();
		String username = control.getUsuario().getUsername();
		String resultado = null;
		HashMap datosFiltro = recuperaFiltrosFijos( request );
		
		switch(accion) {
			case ACCION_DESPLIEGA_FILTRO:
				break;
			case ACCION_LISTADO_USUARIOS:
			default:

			// Obtengo los Datos de para los Filtros.
	
				resultado = new ListadoUsuariosSupervisados(request, username).listarUsuarios2(datosFiltro);
				break;
			case ACCION_LISTADO_PETICIONES:
				resultado = new ListadoPeticionesUsuario(request, username).listarPeticiones(datosFiltro);
				break;
		}
		if (resultado != null)
			setResultado(resultado);

	}	



   private HashMap recuperaFiltrosFijos(HttpServletRequest request) {
	   HashMap map = new HashMap();
	   
	   map.put( "BI_TIPO_TRABAJO", UtilesBandeja.sinNull(request.getParameter("tipotrabajo"), "") );

	   String fam = UtilesBandeja.sinNull(request.getParameter("familia"), "");
	   if ( fam!=null && fam.length()>0 )
		   fam = "'%" + fam + "%'";
	   map.put( "BI_FAMILIA_PS", fam );
	   map.put( "BI_FECHA_COMPROMISO", UtilesBandeja.sinNull(request.getParameter("fecha"), "") );
	   log.debug("request.getParameter otrostipos" + request.getParameter("otrostipos"));
	   map.put( "BI_ESTADO_PETICION", UtilesBandeja.sinNull(request.getParameter("otrostipos"), "") );

	   return (map);
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
		t.put("EN CURSO",   "1");
		t.put("EN REVERSA", "3,7");
		return t;
	}


}
