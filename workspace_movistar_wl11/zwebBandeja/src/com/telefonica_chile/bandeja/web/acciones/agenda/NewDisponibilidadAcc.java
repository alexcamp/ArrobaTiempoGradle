package com.telefonica_chile.bandeja.web.acciones.agenda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion;


/**
 * El EAC esta en linea con el cliente y corresponde ofrecer
 * opciones para visitas de instalacion.
 * 
 * Entrada:
 * 	- Peticion
 */
public class NewDisponibilidadAcc extends Accion {

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
			idUsuario = null;
		}

//		try {
//			// Obtengo todos los Parametros.
//			String str = "";
//			Enumeration e = request.getParameterNames();
//			while (e.hasMoreElements()) {
//				String key = (String) e.nextElement();
//				String valor = request.getParameter(key);
//				str += "'" + key + "'='" + valor + "'\n";
//			}
//			log.info("Parametros Recibidos:\n" + str);
//
//			String numeroPeticion = request.getParameter("NP");
//			Long nPetico =  null;
//			try {
//				nPetico = new Long(request.getParameter("NP"));
//			} catch (Exception e1) {
//				nPetico = new Long(0);
//			}
//
//			// Primero Vemos si está Agendando.
//			Agendamiento agendaLocal  = new Agendamiento();
//
//			String 	accion = request.getParameter("agenda_accion");
//			
//			if ( "CANCELAR".equals(accion) ) {
//				// Tengo que enviar el Mensaje de Cancelación
//				agendaLocal.enviaAgendamiento(nPetico);
//				setResultado("verCancelacion");
//				return;
//			}
//
//
//
//			String 	esReagenda = request.getParameter("agenda_es_reagenda");
//			
//			if ( esReagenda == null || "".equals(esReagenda) ) { 
//				ArrayList listaAgendamientos = agendaLocal.buscarAgendamiento( nPetico );
//				log.debug("Listado: " + listaAgendamientos.size());
//				if ( listaAgendamientos != null && listaAgendamientos.size()>0) {
//					// busco los Rangos.
//					ArrayList listaRangos = agendaLocal.getListaRangos();
//					ArrayList listaCausa = agendaLocal.getListaCausas();
//					
//					request.setAttribute("listadoAgenda", listaAgendamientos);
//					request.setAttribute("listadoRangos", listaRangos);
//					request.setAttribute("listadoCausas", listaCausa);
//					setResultado("listaAgendamientos");
//					return;
//				}
//			}
//
//			//Verificar que busqueda sea por Id  o Codigo ...
//			String codActividad = "Terreno";
//			String area = request.getParameter("A");
//			String fono = request.getParameter("F");
//			String sistema = request.getParameter("S");
//			//String tipoTrabajo = request.getParameter("TT");
//			String tica = request.getParameter("TC");
//			String segmento = request.getParameter("SG");
//			String plantaComercial = request.getParameter("PC")	;
//			String puntoVenta = request.getParameter("PV");
//			String codTerritorio = request.getParameter("AG");
//			String modeloNegocio = request.getParameter("MN");
//			String respAfac = request.getParameter("RF");
//			String respApel = request.getParameter("RP");
//			int cantPs = 0 ;
//			try {
//				cantPs = Integer.parseInt(request.getParameter("CP"));
//			} catch (Exception e1) {
//				cantPs = 0;
//			}
//
//			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
//			SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
//			SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
//
//			String uFechaComp = request.getParameter("UFC");
//			String uTipoAgenda = request.getParameter("UTA");
//
//			//TODO: Ver de donde se obtiene el codigo de Agencia.
//			String codLocalidad = request.getParameter("CL"); // Codigo Localidad.
//			String codDepto = request.getParameter("DP"); // Codigo Localidad.
//
//
//			// Seteamos los parametros que vienen
//			FiltroAgendamientoDTO fAgenda = new FiltroAgendamientoDTO();
//			fAgenda.setCodigoAgencia( codDepto );
//			fAgenda.setPlantaComercial( plantaComercial );
//			fAgenda.setTica( tica );
//			fAgenda.setPtoVenta( puntoVenta );
//			fAgenda.setArmario("");
//
//			// Con la TICA obtengo la Operacion Comercial.
//			String tipoTrabajo = getOpcomercialByTica(tica);
//			 
//			// obtengo Pagina Agenda. Veo si estoy Paginando
//			String pagAgenda = request.getParameter("agenda_pagina");
//			pagAgenda = (pagAgenda==null || "".equals(pagAgenda)) ? "0" : pagAgenda;
//			
//			int paginaAgenda = 0;
//			try {
//				paginaAgenda = Integer.parseInt(pagAgenda);
//			} catch (Exception e1) {
//				paginaAgenda = 0;
//			}
//
//			String fecMinima = request.getParameter("agenda_fec_minima");
//
//			// Busco El Grupo de Segmento
//			SegmentoAgendamientoDTO segAgenda = agendaLocal.getSegmento( segmento, plantaComercial );
//			fAgenda.setIdGrupoSegmento( segAgenda.getIdGrupoSegmento() );
//			fAgenda.setIdSegmento( segAgenda.getIdSegmento() );
//			fAgenda.setTipoTrabajo( tipoTrabajo );
//
//			ArrayList listaCodPS = new ArrayList();
//			ArrayList listaOpCom = new ArrayList();
//			for (int i=1; i<=cantPs; i++) {
////				listaCodPS.add( request.getParameter("P_" + i) );
//				PSAgendamientoDTO psDto = new PSAgendamientoDTO();
//				psDto.setPsTerreno( true );
//				psDto.setOpComercial( tipoTrabajo );
//				String codPs = request.getParameter("P_" + i);
//				psDto.setCodigoPS( codPs );
//				psDto.setCodFamiliaPS( codPs );
//				Long idPs = new Long( codPs );
//				psDto.setIdFamiliaPS( idPs );
//				psDto.setIdPs( idPs );
//				listaCodPS.add( psDto );
//			}
//			// Buscar Listado de Familia de PS
//			// TODO: Obtener el listado de PS (Familia PS)
////			for (int i=1; listaPS!=null && i<=listaPS.size(); i++) {
////				listaCodPS.add( request.getParameter("P_" + i) );
////			}
//
//			fAgenda.setListaPS( listaCodPS );
//
//			TipoAgendamientoDTO tipoAgenda = agendaLocal.getTipoAgendamiento( fAgenda );
//			log.debug("TipoAgendamiento: " + tipoAgenda );
//
//			// Aca Verifico si es Reagendamiento de tipo SLA, si es así.
//			// 1.- Cambio la Fecha de ingreso de la Petico.
//			// 2.- Si es SLA, hay que ver si venció.
//			String fCreacion = request.getParameter("FC");
//			Date fechaHoy = null;
//			Date fechaAhora = getNow();
//			if ( fCreacion != null && !"".equals(fCreacion) ) {
//				fechaHoy = sdf2.parse(fCreacion);
//				if (fechaAhora.after( fechaHoy )) {
//					// Tengo que ver si es SLA, valido que no se alla cumplido el Plazo Maximo.
//					if ( tipoAgenda.getIdTipo()!= null && tipoAgenda.getIdTipo().intValue()==1) {
//						Date fAux = agregaDiasDate(fechaHoy, tipoAgenda.getTiempoMaximo().intValue());
//						if (fechaAhora.after( fAux )) { // Esta Vencida
//							uFechaComp = sdf3.format(fAux);
//							esReagenda = "S";
//							uTipoAgenda = "1";
//							fechaHoy = fechaAhora;
//						}
//					} else
//						fechaHoy = fechaAhora;
//				}
//			} else
//				fechaHoy = fechaAhora;
//
//			log.debug("FECHA HOY = [" + fechaHoy + "," + fechaAhora + "]");
//
//			//String uFechaComp = "";
//			//String uTipoAgenda = "";
//			boolean soloFecha = false;
//
//			Date uFComp = null;
//			
//			if ( "S".equals(esReagenda) ) {
//				//fechaIngresoPetico = request.getParameter("RF");
//				//uFechaComp = request.getParameter("UFC");
//				//uTipoAgenda = request.getParameter("UTA");
//				if ("1".equals(uTipoAgenda)) {  // Es SLA. valido que no este vencido.
//					uFComp = sdf3.parse(uFechaComp);
//					if ( (fechaAhora).after( uFComp ) ) {  // Esta vencido, modifico para mostrar Disponibilidad
//						tipoAgenda.setIdTipo(new Long(2));
//						tipoAgenda.setTiempoMinimo( new Integer(1) );
//						tipoAgenda.setTiempoMaximo( new Integer(7) );
//					} else {
//						tipoAgenda.setIdTipo(new Long(2));
//						request.setAttribute("SLAVencido", "1");
//						soloFecha = true;
//					}
//				}
//			}
//
//			Long tc = tipoAgenda.getIdTipo();
//
//			Integer maxp = tipoAgenda.getTiempoMaximo();
//			int plazoMaximo = ( maxp==null ) ? 0 : maxp.intValue();
//
//			Integer minp = tipoAgenda.getTiempoMinimo();
//			Integer minMinp = tipoAgenda.getTiempoMaximo();
//			int plazoMinimo = ( minp==null ) ? 0 : minp.intValue();
//
//			Date fecMin = null; Date fecMax = null;
//			if ( paginaAgenda == 0 ) {
//				fecMin = agregaDiasDate( fechaHoy, plazoMinimo );
//			} else {
//				// Tengo Fecha Minima Guardada. La ocupo
//				Date fechaMinima = sdf.parse( fecMinima );
//				fecMin = agregaDiasDate( fechaMinima, paginaAgenda*7);
//			}
//			if ( soloFecha ) {
//				fecMin = uFComp;
//				log.debug("FEcha: '" + fecMin + "'"); 
//			}
//			
//			fecMax = agregaDiasDate( fecMin, plazoMaximo); 
//
//			fAgenda.setFechaMinimaCita( fecMin );
//			fAgenda.setFechaMaxima( fecMax );
//			
//			int minLlamada = 10;
//			int minEspecial = 241;
//			fAgenda.setFechaMinimaLlamada( agregaMinutosDate( fechaAhora, minLlamada) );
//			fAgenda.setFechaMinimaExcepcion( agregaMinutosDate( fechaAhora, minEspecial) ); 
//			
//			
//			log.debug("Fechas (Minima,Maxima) = (" + sdf.format(fecMin) + "," + sdf.format(fecMax) + ")" );
//			log.debug("TC  = (" + tc.intValue() + ")" );
//
//			if ( tc.intValue()== 1 || tc.intValue()==0) {
//				// Tipo Agendamiento de Plazo Maximo ( Si es 0 es Sin Terreno )
//				if ( tc.intValue()== 0 )
//					tipoAgenda.setIdTipo(new Long(1));
//
//				FechaCompromisoAgendamientoDTO datosCom = new FechaCompromisoAgendamientoDTO();
//				datosCom.setIdPeticion( nPetico );
//				datosCom.setFechaCompromiso( fecMax );
//				datosCom.setHoraDesde( null );
//				datosCom.setHoraHasta( null );
//				datosCom.setIdRango( new Integer(1) );
//				datosCom.setIdTipoAgenda( new Long(1) );
//				datosCom.setConTecnico( null );
//				// Seteamos el usuario que hace la asignacion.
//				datosCom.setIdUsuario( idUsuario );
//
//				agendaLocal.AsignarPeticion(fAgenda, datosCom);
//				
//				request.setAttribute("TipoAgendamiento", tipoAgenda);
//				request.setAttribute("FiltroAgendamiento", fAgenda);
//				request.setAttribute("DatosCompromiso", datosCom );
//
//				setResultado("asignacion");
//				return;
//				
//			} else if (tc.intValue() >= 2 ) {
//
//				// Aca hay que mostrar matriz de Disponibilidad
//				log.debug("Buscando con Disponibilidad. [" + fecMax + "," + nPetico + "]");
//				
//				ArrayList listaRangos = new ArrayList();
//				
//				ArrayList listaDisp = new ArrayList();
//				ArrayList listaFechas = new ArrayList();
//				ArrayList listaSegmentos = new ArrayList();
//				if ( !soloFecha ) {
//					listaRangos = agendaLocal.getRangos( fAgenda );
//					
//					for (int i=0; i < 7; i++) { 
//						Date aux = agregaDiasDate(fecMin, i);
//						listaFechas.add( aux );
//					}
//
//					if (plazoMaximo < 7)
//						fecMax = agregaDiasDate(fecMin, 7);
//					
//					listaDisp = agendaLocal.buscarDisponibilidad( fAgenda, fecMin, fecMax, listaRangos, segAgenda.getPorcentaje() );
//					listaSegmentos = agendaLocal.getListaSegmentos(plantaComercial, segAgenda.getIdGrupoSegmento());
//					
//				}
//
//				ArrayList listaCausas = agendaLocal.getListaCausas();
//
//				request.setAttribute("listaCausas", listaCausas);
//				request.setAttribute("TipoAgendamiento", tipoAgenda);
//				request.setAttribute("FiltroAgendamiento", fAgenda);
//				request.setAttribute("listaFechas", listaFechas);
//				request.setAttribute("listaRangos", listaRangos);
//				request.setAttribute("listaSegmentos", listaSegmentos);
//				request.setAttribute("listaDisponibilidad", listaDisp);
//
//				setResultado("verAgenda");
//				return;
//			}
//
//			setResultado("errorAgendamiento");
//			
//		} catch (Exception e) {
//			log.error("error del catch en Disponibilidad ", e);
//			request.setAttribute("msj", new String("Error al desplegar disponibilidad ..."));
//			setResultado("errorAgendamiento");
//		}

	}

/*	public java.sql.Date sumaDiasFecha( Date d, int sumar  ) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(GregorianCalendar.DAY_OF_YEAR, sumar);
		java.sql.Date f = new java.sql.Date(gc.getTime().getTime());
		return f;
	}
*/
	
	public Date agregaDiasDate(Date fecha, int dias) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
		gc.set(GregorianCalendar.MINUTE, 0);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MILLISECOND, 0);
		gc.add(GregorianCalendar.DAY_OF_YEAR, dias);
		return (gc.getTime());
	}
	
	public Date agregaMinutosDate(Date fecha, int minutos) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		//gc.set(GregorianCalendar.MINUTE);
		gc.add(GregorianCalendar.MINUTE, minutos);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MILLISECOND, 0);
		return (gc.getTime());
	}

	public Date getNow() {
		Calendar gc = Calendar.getInstance();
		// Le restamos 4 Horas, esto es GMT-4 (Chile)
		gc.add(Calendar.HOUR_OF_DAY, -4);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		log.debug("\nFECHA ACTUAL NOW = '" + gc.getTime());
		return (gc.getTime());
	}


//	private AgendamientoSessionLocal getAgendamientoSession() throws NamingException, RemoteException, CreateException {
//		AgendamientoSessionLocalHome home = (AgendamientoSessionLocalHome) HomeFactory.getHome(AgendamientoSessionLocalHome.JNDI_NAME);
//		AgendamientoSessionLocal local = home.create();
//		return local;
//	}

	private String getOpcomercialByTica(String tica) {
		String opDefault = "A";
		String lTraslado = ";AT;BT;MT;";
		String lRecambio = ";MC;";
		
		if (tica==null)
			return opDefault;
		
		if ( lTraslado.indexOf(tica) > 0 )
			return "T";
		if ( lRecambio.indexOf(tica) > 0 )
			return "R";
		if ( tica.startsWith("A") || tica.startsWith("D") )
			return "A";
		if ( tica.startsWith("B") )
			return "B";
		if ( tica.startsWith("M") )
			return "M";
		
		return opDefault;
	}

	private int parseInt(String str, int def) {
		if (str == null)
			return def;
		try {
			int x = Integer.parseInt(str);
			return x;
		} catch (Exception e) {
		}

		return def;
	}
}