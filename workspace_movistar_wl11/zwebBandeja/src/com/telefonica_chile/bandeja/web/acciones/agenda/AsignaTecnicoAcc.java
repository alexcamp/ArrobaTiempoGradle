package com.telefonica_chile.bandeja.web.acciones.agenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;
import com.telefonica_chile.bandeja.web.agenda.Agendamiento;
import com.telefonica_chile.bandeja.web.agenda.FechaCompromisoAgendamientoDTO;
import com.telefonica_chile.bandeja.web.agenda.FiltroAgendamientoDTO;
import com.telefonica_chile.bandeja.web.agenda.RangoAgendamientoDTO;
import com.telefonica_chile.bandeja.web.agenda.SegmentoAgendamientoDTO;
import com.telefonica_chile.bandeja.web.agenda.TipoAgendamientoDTO;

/**
 * Clase que Realiza el Agendamiento del Tecnico.
 * 
 */
public class AsignaTecnicoAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		HttpSession session = request.getSession(true);

		Long idUsuario = null;
		try {
			AtiempoControladorDeAplicacion control = (AtiempoControladorDeAplicacion) getControladorDeAplicacion();
			idUsuario = control.getUsuario().getId();
		} catch (Exception e) { 
		}

		try {
			// Obtengo todos los Parametros y Los imprimo.
			String str = "";
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String valor = request.getParameter(key);
				str += "'" + key + "'='" + valor + "'\n";
			}
			log.debug("Parametros Recibidos:\n" + str);

			// Obtengo los campos más importantes.
			
			String numeroPeticion = request.getParameter("NP");
			Long numPeticion = null;
			try {
				numPeticion = new Long(numeroPeticion);
			} catch (Exception e1) {
				numPeticion = new Long(0);
			}
			String area = request.getParameter("A");
			String fono = request.getParameter("F");
			String tica = request.getParameter("TC");
			String segmento = request.getParameter("SG");
			String codTerritorio = request.getParameter("AG");
			String plantaComercial = request.getParameter("PC");
			String puntoVenta = request.getParameter("PV");

			int cantPs = 0 ;
			try {
				cantPs = Integer.parseInt(request.getParameter("CP"));
			} catch (Exception e1) {
				cantPs = 0;
			}

			// obtengo Pagina Agenda. Veo si estoy Paginando
			String pagAgenda = request.getParameter("agenda_pagina");
			pagAgenda = (pagAgenda==null || "".equals(pagAgenda)) ? "0" : pagAgenda;
			
			int paginaAgenda = 0;
			try {
				paginaAgenda = Integer.parseInt(pagAgenda);
			} catch (Exception e1) {
				paginaAgenda = 0;
			}

			String fecMinima = request.getParameter("agenda_fec_minima");

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");

			FiltroAgendamientoDTO fAgenda = new FiltroAgendamientoDTO();
			fAgenda.setPlantaComercial( plantaComercial );
			fAgenda.setTica( tica );
			fAgenda.setPtoVenta( puntoVenta );
			fAgenda.setArmario("");

			//AgendamientoSessionLocal agendamientoLocal  = getAgendamientoSession();
			Agendamiento agendaLocal = new Agendamiento();

//			TODO: De adonde obtengo la Agencia.
			String codigoAgencia = "";
			fAgenda.setCodigoAgencia( codigoAgencia );

			// Busco El Grupo de Segmento
			SegmentoAgendamientoDTO segAgenda = agendaLocal.getSegmento( segmento, plantaComercial );
			fAgenda.setIdGrupoSegmento( segAgenda.getIdGrupoSegmento() );
			fAgenda.setIdSegmento( segAgenda.getIdSegmento() );

			ArrayList listaCodPS = new ArrayList();
			ArrayList listaOpCom = new ArrayList();
			for (int i=1; i<=cantPs; i++) {
				listaCodPS.add( request.getParameter("P_" + i) );
				listaOpCom.add( request.getParameter("O_" + i) );
			}
			// Buscar Listado de Familia de PS
			// TODO: Lista de Familia de PS. 
			ArrayList listaPS = null;//agendaLocal.getFamiliaPS( listaCodPS, listaOpCom  );
			for (int i=1; listaPS!=null && i<=listaPS.size(); i++) {
				listaCodPS.add( request.getParameter("P_" + i) );
			}

			fAgenda.setListaPS( listaPS );

		
			FechaCompromisoAgendamientoDTO datosCompromiso = buscarDatosCompromiso( request );

			TipoAgendamientoDTO tipoAgenda = new TipoAgendamientoDTO();
			tipoAgenda.setIdTipo(  datosCompromiso.getIdTipoAgenda() );

			log.debug("Agendando a Fecha: " +
				"[" + fecMinima + "," + datosCompromiso.getFechaCompromiso() + "]");


			Date fecMin  = sdf.parse( fecMinima );
			if ( fecMin.after( datosCompromiso.getFechaCompromiso() ) ) {
				log.debug("Agendando a una Fecha Menor que la Minima. " +
					"[" + fecMin + "," + datosCompromiso.getFechaCompromiso() + "]");
			}

			datosCompromiso.setConTecnico( request.getParameter("agenda_con_tecnico") );
			
			Integer idCausaCierre = null;
			try {
				idCausaCierre = new Integer(request.getParameter("agenda_causa_reagenda"));
				if (idCausaCierre == new Integer(-1))
					idCausaCierre = null;
			} catch (Exception e1) {
				idCausaCierre = null;
			}

			datosCompromiso.setIdCausaCierre( idCausaCierre );
			datosCompromiso.setIdPeticion(numPeticion);
			datosCompromiso.setIdUsuario( idUsuario );

			//agendamientoLocal.agendarUsuario(asigna, ag, 0); // Agendamos a un usuario Normal

			// Validamos si es Excepcion
//			if ( datosCompromiso.getIdTipoAgenda().intValue() == 4) {
//				String userMac = request.getParameter("agenda_username_mac");
//				String passMaC = request.getParameter("agenda_password_mac");
//				datosCompromiso.setUserMac(userMac);
//			
//				if ( ! agendaLocal.validarUsuarioMAC(userMac, passMaC) ) {
//					log.info("Sin Permisos de USUARIO MAC");
//					request.setAttribute("msj", new String("Username ingresado no tiene los privilegios necesarios"));
//					setResultado("errorAgendamiento");
//					return;
//				}
//
//			}
			datosCompromiso.setIdPeticion( new Long(numeroPeticion) );
			

			agendaLocal.AsignarPeticion(fAgenda, datosCompromiso);

			ArrayList listaRangos = agendaLocal.getListaRangos();
				
			// Asigno a un Uusario Supervisor
			request.setAttribute("listadoRangos", listaRangos);
			request.setAttribute("TipoAgendamiento", tipoAgenda);
			request.setAttribute("FiltroAgendamiento", fAgenda);
			request.setAttribute("DatosCompromiso", datosCompromiso );

			setResultado("asignacion");
			
		} catch (Exception e) {
			log.error("Error del catch en Asignacion ", e);
			request.setAttribute("msj", new String("Error al desplegar disponibilidad ..."));
			setResultado("errorAgendamiento");
		}



	}

	private FechaCompromisoAgendamientoDTO obtenerDatosFecha( HttpServletRequest req, String base, String formatoFecha ) {
		FechaCompromisoAgendamientoDTO datosComp = new FechaCompromisoAgendamientoDTO();
		String fComp =  req.getParameter("agenda_" + base + "_fecha");
		String hhDesde = req.getParameter("agenda_" + base + "_hh_desde");
		String mmDesde = req.getParameter("agenda_" + base + "_mm_desde");
		String hhHasta = req.getParameter("agenda_" + base + "_hh_hasta");
		String mmHasta = req.getParameter("agenda_" + base + "_mm_hasta");
		hhDesde = (hhDesde==null) ? "00" : hhDesde;
		mmDesde = (mmDesde==null) ? "00" : mmDesde;
		hhHasta = (hhHasta==null) ? "00" : hhHasta;
		mmHasta = (mmHasta==null) ? "00" : mmHasta;
		String horaDesde = hhDesde + ":" + mmDesde;
		String horaHasta = hhHasta + ":" + mmHasta;

		if ( fComp == null || "".equals(fComp) )
			return null;

		Date fecCompromiso = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
			fecCompromiso = sdf.parse(fComp);
		} catch (ParseException e) {
			log.error("No se pudo Parsear la Fecha de Compromiso : ['" + fComp + "', '" + formatoFecha + "']");
		} 

		datosComp.setFechaCompromiso( fecCompromiso );
		datosComp.setHoraDesde( horaDesde );
		datosComp.setHoraHasta( horaHasta );

		log.debug("Agenda Solicitada: [" + base + "," + fComp + "," + horaDesde + "," + horaDesde + "]");
		
		return (datosComp);
	}
	
	private FechaCompromisoAgendamientoDTO buscarDatosCompromiso( HttpServletRequest req ) {
		FechaCompromisoAgendamientoDTO datosComp = null;
		
		String conTecnico = req.getParameter("agenda_con_tecnico");

		Integer idRango = null;
		Long idTipoAgenda = null;

		// Busco si hay Reagendamiento para SLA.
		datosComp = obtenerDatosFecha(req, "sla", "dd/MM/yyyy");
		if (datosComp != null) {
			datosComp.setIdRango( new Integer(1) );
			datosComp.setIdTipoAgenda( new Long(1) );
			return (datosComp);
		}

		// Busco si hay Llamada.
		datosComp = obtenerDatosFecha(req, "llamada", "dd/MM/yyyy");
		if (datosComp != null) {
			datosComp.setIdRango( getRangoAprox(datosComp.getHoraDesde(), datosComp.getHoraHasta()) );
			datosComp.setIdTipoAgenda( new Long(0) );
			return (datosComp);
		}
		datosComp = obtenerDatosFecha(req, "especial", "dd/MM/yyyy");
		if (datosComp != null) {
			datosComp.setIdRango( getRangoAprox(datosComp.getHoraDesde(), datosComp.getHoraHasta()) );
			datosComp.setIdTipoAgenda( new Long(4) );
			return (datosComp);
		}
		// Busco Cita .
		datosComp = obtenerDatosFecha(req, "cita", "dd/MM/yyyy");
		if (datosComp != null) {
			datosComp.setIdRango( getRangoAprox(datosComp.getHoraDesde(), datosComp.getHoraHasta()) );
			datosComp.setIdTipoAgenda( new Long(3) );
			return (datosComp);
		}

		datosComp = obtenerDatosRango(req, "ddMMyy");
		if (datosComp != null) {
			datosComp.setIdTipoAgenda( new Long(2) );
			return (datosComp);
		}
		
		return (datosComp);
	}

	private FechaCompromisoAgendamientoDTO obtenerDatosRango( HttpServletRequest req, String formatoFecha) {

		FechaCompromisoAgendamientoDTO datosComp = new FechaCompromisoAgendamientoDTO();
		String rangoComp = "";
		int cRango = 0;
		int cFechas = 0;
		try {
			cRango = Integer.parseInt(  req.getParameter("agenda_cantidad_rango") );
		} catch (Exception e) {
			cRango = 0;
		}
		try {
			cFechas = Integer.parseInt(  req.getParameter("agenda_cantidad_fechas") );
		} catch (Exception e) {
			cFechas = 0;
		}
		String fechaComp = "";
		for (int i=0; i<cRango; i++) {
			for (int j=0; j<cFechas; j++) {
				if ( req.getParameter("agenda_radio_"+ i + "_" + j) != null ) {
					// Tengo el seleccionado
					fechaComp = req.getParameter("agenda_fecha_"+ i + "_" + j);
					rangoComp = req.getParameter("agenda_rango_"+ i + "_" + j);
					log.debug("Agenda Solicitada: [" + fechaComp + "," + rangoComp + "]");
					break;
				}
			}
		}

		if ( fechaComp == null || "".equals(fechaComp) )
			return null;

		Date fecCompromiso = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
			fecCompromiso = sdf.parse(fechaComp);
		} catch (ParseException e) {
			log.error("No se pudo Parsear la Fecha de Compromiso : ['" + fechaComp + "', '" + formatoFecha + "']");
		} 

		datosComp.setFechaCompromiso( fecCompromiso );
		datosComp.setIdRango( new Integer(rangoComp) );
		datosComp.setHoraDesde("");
		datosComp.setHoraHasta("");
		
		return (datosComp);
	}

	private Integer getRangoAprox(String horaDesde, String horaHasta) {
		Agendamiento agLocal = new Agendamiento();
		ArrayList lista = agLocal.getListaRangos();
		int hI = StringToInt( horaDesde.substring(0,2) );
		int hF = StringToInt( horaHasta.substring(0,2) );
		
		for (int i=0; i<lista.size(); i++) {
			RangoAgendamientoDTO dto = (RangoAgendamientoDTO) lista.get(i);
			int hD = StringToInt( dto.getHoraDesde().substring(0, 2) );
			int hH = StringToInt( dto.getHoraHasta().substring(0, 2) );
			log.debug("Buscando Rango: " + hD + "," + hH + "," + hI);
			if (hD <= hI && hI <= hH)
				return dto.getIdRango();
		}

		return new Integer(1);
	}

	private int StringToInt( String str ) {
		try {
			return ( Integer.parseInt(str) );
		} catch( Exception e ) {
			return 0;
		}
	}
	
//	private AgendamientoSessionLocal getAgendamientoSession() throws NamingException, RemoteException, CreateException {
//		AgendamientoSessionLocalHome home = (AgendamientoSessionLocalHome) HomeFactory.getHome(AgendamientoSessionLocalHome.JNDI_NAME);
//		AgendamientoSessionLocal local = home.create();
//		return local;
//	}

}