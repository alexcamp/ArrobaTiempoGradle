/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoKey;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Carga_maximaLocal;
import co.com.telefonica.atiempo.ejb.eb.Carga_maximaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Distribucion_carga_maximaLocal;
import co.com.telefonica.atiempo.ejb.eb.Distribucion_carga_maximaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_maximaLocal;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_maximaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_distribucion_carga_maximaLocal;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_distribucion_carga_maximaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_tipo_agendamientoLocal;
import co.com.telefonica.atiempo.ejb.eb.Excepcion_tipo_agendamientoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey;
import co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoLocal;
import co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_instalacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_instalacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 */
public class Agendamiento {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private HashMap esTerreno = null;

	public HashMap cargaPSInstalaciones(ArrayList listaCodPS) {
		HashMap map = new HashMap();
		try {
			Ps_instalacionLocalHome psHome = (Ps_instalacionLocalHome) HomeFactory.getHome(Ps_instalacionLocalHome.JNDI_NAME);
			Ps_instalacionLocal psLocal = null;

			for (int i = 0; listaCodPS != null && i < listaCodPS.size(); i++) {
				String codPS = (String) listaCodPS.get(i);

				Collection c = psHome.findByCodigoPS(new Long(codPS));
				for (Iterator it = c.iterator(); it.hasNext();) {
					psLocal = (Ps_instalacionLocal) it.next();
					map.put(psLocal.getPs_id() + "-" + psLocal.getTipo_oc(), "1");
				}
			}
		} catch (NamingException e) {
			log.info("Error Naming Exception: PSInstalacion");
		} catch (FinderException e) {
			log.info("Error Finder Exception: PSInstalacion findAll");
		} catch (Exception e) {
			log.info("Error Exception: PSInstalacion");
		}

		return map;
	}

	/*
	 * Entrega los Parametros para el Agendaiento
	 */
	public void enviaAgendamiento(Long numPetico) {
	}

	/*
	 * Entrega los datos del Grupo de Segmento para el Segmento dado.
	 */
	public SegmentoAgendamientoDTO getSegmento(String codigoSegmento, String pComercial) {
		if (codigoSegmento == null)
			return null;

		SegmentoAgendamientoDTO grupoSegmento = new SegmentoAgendamientoDTO();
		Integer idGrupo = null;
		try {
			Long idSegmento = new Long(codigoSegmento);

			try {
				Agrupacion_segmentoLocalHome aSegHome = (Agrupacion_segmentoLocalHome) HomeFactory.getHome(Agrupacion_segmentoLocalHome.JNDI_NAME);
				Agrupacion_segmentoKey agSegKey = new Agrupacion_segmentoKey(idSegmento);
				Agrupacion_segmentoLocal aSegLocal = aSegHome.findByPrimaryKey(agSegKey);
				idGrupo = aSegLocal.getGrse_id();
			} catch (NamingException e) {
				log.info("Error Naming Exception: AGrupacionSegmento");
			} catch (FinderException e) {
				log.info("Error Finder Exception: AgrupacionSegmento para Segmento (" + codigoSegmento + ")");
				idGrupo = new Integer(0);
			}

			Grupo_segmentoLocalHome gSegHome = (Grupo_segmentoLocalHome) HomeFactory.getHome(Grupo_segmentoLocalHome.JNDI_NAME);
			Grupo_segmentoKey grseKey = new Grupo_segmentoKey(idGrupo);
			Grupo_segmentoLocal gSegLocal = gSegHome.findByPrimaryKey(grseKey);

			grupoSegmento.setIdGrupoSegmento(idGrupo);
			grupoSegmento.setNombreGrupoSegmento(gSegLocal.getGrse_nombre());
			grupoSegmento.setPorcentaje(gSegLocal.getGrse_porcentaje());

		} catch (NamingException e) {
			log.info("Error Naming Exception: GrupoSegmento");
		} catch (FinderException e) {
			log.info("Error Finder Exception: GrupoSegmento para Segmento (" + codigoSegmento + ")");
		} catch (Exception e) {
			log.info("Error Exception: GrupoSegmento para Segmento (" + codigoSegmento + ")");
		}

		try {
			// Vemos si ha y Excepcion de Segmentos
			Excepcion_carga_segmentoLocalHome dHome = (Excepcion_carga_segmentoLocalHome) HomeFactory.getHome(Excepcion_carga_segmentoLocalHome.JNDI_NAME);
			Collection c = dHome.findByPlantaComercial(pComercial);
			for (Iterator it = c.iterator(); it.hasNext();) {
				Excepcion_carga_segmentoLocal dLocal = (Excepcion_carga_segmentoLocal) it.next();

				Integer idGrupoSegmento = (Integer) dLocal.getGrse_id();
				if (idGrupo.intValue() != idGrupoSegmento.intValue())
					continue;

				grupoSegmento.setIdGrupoSegmento(idGrupoSegmento);
				grupoSegmento.setPorcentaje(dLocal.getPorcentaje());
				grupoSegmento.setPorcentajeMinimo(dLocal.getPorcentaje_minimo());
			}
		} catch (NamingException e) {
		} catch (FinderException e) {
			log.info("Error Finder Exception: GrupoSegmentoEx para Segmento (" + pComercial + ")");
		} catch (Exception e) {
		}

		return grupoSegmento;
	}

	/*
	 * Entrega el Listado de Rangos para un Filtro dado
	 */
	public ArrayList getRangos(FiltroAgendamientoDTO fAgenda) {

		ArrayList lista = new ArrayList();
		ArrayList listaTodos = new ArrayList();
		RangoAgendamientoDTO r = null;
		HashMap auxRango = new HashMap();
		HashMap auxMap = new HashMap();

		try {
			RangoLocalHome rHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			Collection c1 = rHome.findAll();
			RangoLocal rLocal = null;
			for (Iterator it = c1.iterator(); it.hasNext();) {
				rLocal = (RangoLocal) it.next();
				//				String codPC = rLocal.getCodigoPlantaComercial();
				//				if (codPC != null && !codPC.equals( fAgenda.getPlantaComercial() ) )
				//					continue;
				r = new RangoAgendamientoDTO();
				Integer idRango = ((RangoKey) rLocal.getPrimaryKey()).id_rango;
				r.setIdRango(idRango);
				r.setDescripcionRango(rLocal.getNombre_rango());
				r.setHabilitado(new Byte("" + rLocal.getHabilitado()));
				r.setCodigoRango(rLocal.getCodigo_rango());
				r.setHoraDesde(rLocal.getHora_desde());
				r.setHoraHasta(rLocal.getHora_hasta());
				r.setIdPadre(rLocal.getId_padre());

				if (rLocal.getHabilitado() != null && rLocal.getHabilitado().intValue() == 1)
					listaTodos.add(r);
				if (auxRango.containsKey(idRango))
					lista.add(r);

				if (rLocal.getId_padre() != null) {
					ArrayList auxList = (ArrayList) auxMap.get(rLocal.getId_padre());
					if (auxList == null)
						auxList = new ArrayList();
					auxList.add(idRango);
					auxMap.put(rLocal.getId_padre(), auxList);
				}

			}

			if (lista.size() == 0)
				lista = listaTodos;

			for (int j = 0; j < lista.size(); j++) {
				RangoAgendamientoDTO rAux = (RangoAgendamientoDTO) lista.get(j);
				// Veo si es Padre, si lo es le agrego los Hijos...
				if (auxMap.containsKey(rAux.getIdRango()))
					rAux.setListaHijos((ArrayList) auxMap.get(rAux.getIdRango()));
				// Veo si es hijo, si lo es le agrego los Hermanos...
				if (rAux.getIdPadre() != null) {
					rAux.setListaHermanos((ArrayList) auxMap.get(rAux.getIdPadre()));
				}
			}

		} catch (NamingException e) {
			log.info("Error Naming Exception: Rango");
		} catch (FinderException e) {
			log.info("Error Finder Exception: Rango");
		} catch (Exception e) {
			log.error("Error Exception: Rango", e);
		}

		return lista;
	}

	/*
	 * Retorna el Tipo de Agendamiento de acuerdo a los Filtros Ingresados.
	 */
	public TipoAgendamientoDTO getTipoAgendamiento(FiltroAgendamientoDTO fAgenda) {
		TipoAgendamientoDTO tipoAgenda = new TipoAgendamientoDTO();
		boolean esGaudi = false;

		// Ir a Tabla Agendamiento. Buscamos por Tipo Trabajo.
		String tipoTrabajo = fAgenda.getTipoTrabajo();

		try {
			Tipo_agendamiento_segmentoLocalHome agHome =
				(Tipo_agendamiento_segmentoLocalHome) HomeFactory.getHome(Tipo_agendamiento_segmentoLocalHome.JNDI_NAME);
			Tipo_agendamiento_segmentoLocal agLocal = null;

			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Si no hay Terreno, entonces mando el Default.
			if (listFamPs == null || listFamPs.size() == 0) {
				agLocal = agHome.findTipoAgendamientoNulo();
				log.info("Agendamiento SIN TERRENO [" + agLocal.getTiag_id() + "," + agLocal.getTiempo_minimo() + "," + agLocal.getTiempo_maximo() + "]");
				tipoAgenda.setIdTipo(new Long(0));
				//tipoAgenda.setIdTipo( agLocal.getIdTipoAgenda() );
				Integer tpoMin = agLocal.getTiempo_minimo();
				Integer tpoMax = agLocal.getTiempo_maximo();
				tipoAgenda.setTiempoMinimo(getValor(tpoMin, tipoAgenda.getTiempoMinimo(), 0));
				tipoAgenda.setMinimoTiempoMinimo(getValor(tpoMin, tipoAgenda.getMinimoTiempoMinimo(), 1));
				tipoAgenda.setTiempoMaximo(getValor(tpoMax, tipoAgenda.getTiempoMaximo(), 1));

				return (tipoAgenda);
			}

			// Busco por Tipo de Trabajo.
			Collection c = agHome.findTipoAgendamiento(fAgenda.getIdGrupoSegmento(), tipoTrabajo);
			if (log.isDebugEnabled())
				log.debug("Buscando TIPOAGENDA para [grupoSeg, opCom]= [" + fAgenda.getIdGrupoSegmento() + "," + tipoTrabajo + "]");
			for (Iterator it = c.iterator(); it.hasNext();) {
				agLocal = (Tipo_agendamiento_segmentoLocal) it.next();
				if (log.isDebugEnabled())
					log.debug("[" + agLocal.getTiag_id() + "," + agLocal.getTiempo_minimo() + "," + agLocal.getTiempo_maximo() + "]");
				tipoAgenda.setIdTipo(getValor(agLocal.getTiag_id(), tipoAgenda.getIdTipo(), 1));
				Integer tpoMin = agLocal.getTiempo_minimo();
				Integer tpoMax = agLocal.getTiempo_maximo();
				tipoAgenda.setTiempoMinimo(getValor(tpoMin, tipoAgenda.getTiempoMinimo(), 0));
				tipoAgenda.setMinimoTiempoMinimo(getValor(tpoMin, tipoAgenda.getMinimoTiempoMinimo(), 1));
				tipoAgenda.setTiempoMaximo(getValor(tpoMax, tipoAgenda.getTiempoMaximo(), 1));

			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		// Ir a Tabla Exception Agenda
		try {
			Excepcion_tipo_agendamientoLocalHome agExHome =
				(Excepcion_tipo_agendamientoLocalHome) HomeFactory.getHome(Excepcion_tipo_agendamientoLocalHome.JNDI_NAME);

			Excepcion_tipo_agendamientoLocal agExLocal = null;

			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			if (log.isDebugEnabled())
				log.debug("Buscando Total FamiliaPS " + listFamPs.size() + "]");
			int cantMatchOld = 0;
			boolean iniTipoAgenda = false;
			for (int j = 0; listFamPs != null && j < listFamPs.size(); j++) {
				Long idFamPS = (Long) listFamPs.get(j);

				if (log.isDebugEnabled())
					log.debug(
						"Buscando TIPOAGENDAEX para [grupoSeg, opCom, idFamPS]= [" + fAgenda.getIdGrupoSegmento() + "," + tipoTrabajo + "," + idFamPS + "]");
				Collection c =
					agExHome.findTipoAgendamiento(
						fAgenda.getIdGrupoSegmento(),
						fAgenda.getCodigoAgencia(),
						idFamPS,
						tipoTrabajo,
						fAgenda.getPlantaComercial(),
						fAgenda.getArmario(),
						fAgenda.getPtoVenta(),
						fAgenda.getTica());

				if (c == null)
					continue;

				if (c.size() > 0 && !iniTipoAgenda) {
					tipoAgenda = new TipoAgendamientoDTO();
					iniTipoAgenda = true;
				}
				for (Iterator it = c.iterator(); it.hasNext();) {
					if (log.isDebugEnabled())
						log.debug("Tengo TipoAgendaExcepcion. La valido contra las demás.");
					agExLocal = (Excepcion_tipo_agendamientoLocal) it.next();
					// cuento los Matches de ahora.
					int cantMatch = 0;
					cantMatch += (agExLocal.getCodigo_agencia() != null) ? 1 : 0;
					cantMatch += (agExLocal.getCodigo_familia_ps() != null) ? 1 : 0;
					cantMatch += (agExLocal.getCodigo_pcom() != null) ? 1 : 0;
					cantMatch += (agExLocal.getArmario() != null) ? 1 : 0;
					cantMatch += (agExLocal.getPunto_venta() != null) ? 1 : 0;
					cantMatch += (agExLocal.getTica() != null) ? 1 : 0;
					cantMatch += (agExLocal.getOperacion_comercial() != null) ? 1 : 0;
					// si es menor, no lo pesco.
					if (cantMatch < cantMatchOld)
						continue;

					// si es igual ?
					//	if (cantMatch == cantMatchOld)
					//		continue;

					cantMatchOld = cantMatch;

					tipoAgenda.setIdTipo(getValor(agExLocal.getTiag_id(), tipoAgenda.getIdTipo(), 1));
					Integer tpoMin = agExLocal.getTiempo_minimo();
					Integer tpoMax = agExLocal.getTiempo_maximo();
					tipoAgenda.setTiempoMinimo(getValor(tpoMin, tipoAgenda.getTiempoMinimo(), 1));
					tipoAgenda.setTiempoMaximo(getValor(tpoMax, tipoAgenda.getTiempoMaximo(), 1));
					tipoAgenda.setMinimoTiempoMinimo(getValor(tpoMin, tipoAgenda.getMinimoTiempoMinimo(), 1));
					// Pa los Dias Habilias.  Se hace un Match.
					tipoAgenda.seteaDias(agExLocal.getDias_habiles());

				}
			}
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (FinderException e1) {
			e1.printStackTrace();
		}
		// Seteo si es GAUDI.
		tipoAgenda.setAgendaGaudi(esGaudi);
		return (tipoAgenda);
	}

	private Integer getValor(Integer n1, Integer n2, int h) {
		if (n1 == null)
			return n2;
		if (n2 == null)
			return n1;

		Integer aux = null;
		if (n1.intValue() > n2.intValue()) {
			aux = (h == 0) ? n2 : n1;
		} else {
			aux = (h == 0) ? n1 : n2;
		}

		return aux;
	}

	private Long getValor(Long n1, Long n2, int h) {
		if (n1 == null)
			return n2;
		if (n2 == null)
			return n1;

		Long aux = null;
		if (n1.intValue() > n2.intValue()) {
			aux = (h == 0) ? n2 : n1;
		} else {
			aux = (h == 0) ? n1 : n2;
		}
		return aux;
	}

	/*
	 * Entrega la Carga Maxima Generica.
	 */
	public HashMap getCargaGenerica(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax) {

		HashMap m = new HashMap();

		try {
			Carga_maximaLocalHome cmHome = (Carga_maximaLocalHome) HomeFactory.getHome(Carga_maximaLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);
				Collection c = cmHome.findByFamiliaAgencia(idFam, fAgenda.getCodigoAgencia());
				for (Iterator it = c.iterator(); it.hasNext();) {
					Carga_maximaLocal cmLocal = (Carga_maximaLocal) it.next();
					Integer cant = cmLocal.getCarga_maxima();
					String idDia = cmLocal.getDia_semana();
					m.put(idDia + "-" + idFam, cant);
					if (log.isDebugEnabled())
						log.debug("Distribucion Carga Maxima[" + idDia + "," + cmLocal.getCarga_maxima() + "]");
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return m;
	}

	/*
	 * Entrega la Carga Maxima Especifica.
	 */
	public HashMap getCargaEspecifica(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax) {

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		HashMap m = new HashMap();

		try {
			Excepcion_carga_maximaLocalHome cmHome = (Excepcion_carga_maximaLocalHome) HomeFactory.getHome(Excepcion_carga_maximaLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);
				Collection c = cmHome.findByFamiliaPlantaFecha(idFam, fAgenda.getPlantaComercial(), fecMin, fecMax);
				for (Iterator it = c.iterator(); it.hasNext();) {
					Excepcion_carga_maximaLocal cmLocal = (Excepcion_carga_maximaLocal) it.next();
					Integer cant = cmLocal.getCarga_maxima();
					Date dia = cmLocal.getDia_especifico();
					String d = sdf.format(dia);
					m.put(d + "-" + idFam, cant);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return m;
	}

	/*
	 * Entrega la Distribucion Generica de la Carga Maxima.
	 */
	public HashMap getDistribucionGenerica(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax) {

		HashMap m = new HashMap();

		try {
			Distribucion_carga_maximaLocalHome cmHome = (Distribucion_carga_maximaLocalHome) HomeFactory.getHome(Distribucion_carga_maximaLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);
				if (log.isDebugEnabled())
					log.debug("Buscando Distribucion Carga Maxima[" + idFam + "," + fAgenda.getCodigoAgencia() + "]");
				// Buscamos por Codigo de Agencia
				Collection c = cmHome.findByFamiliaPlanta(idFam, fAgenda.getCodigoAgencia());
				for (Iterator it = c.iterator(); it.hasNext();) {
					Distribucion_carga_maximaLocal cmLocal = (Distribucion_carga_maximaLocal) it.next();
					String idDia = cmLocal.getDia_generico();

					HashMap aux = (HashMap) m.get(idDia + "-" + idFam);
					if (aux == null)
						aux = new HashMap();
					aux.put(cmLocal.getId_rango(), cmLocal.getPorcentaje());
					if (log.isDebugEnabled())
						log.debug("Distribucion Carga Maxima[" + idDia + "," + cmLocal.getId_rango() + "," + cmLocal.getPorcentaje() + "]");
					m.put(idDia + "-" + idFam, aux);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return m;
	}

	/*
	 * Entrega la Distribucion Especifica de la Carga Maxima.
	 */
	public HashMap getDistribucionEspecifica(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax) {

		HashMap m = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");

		try {
			Excepcion_distribucion_carga_maximaLocalHome cmHome =
				(Excepcion_distribucion_carga_maximaLocalHome) HomeFactory.getHome(Excepcion_distribucion_carga_maximaLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);
				Collection c = cmHome.findByFamiliaPlantaFecha(idFam, fAgenda.getPlantaComercial(), fecMin, fecMax);
				for (Iterator it = c.iterator(); it.hasNext();) {
					Excepcion_distribucion_carga_maximaLocal cmLocal = (Excepcion_distribucion_carga_maximaLocal) it.next();
					Date dia = cmLocal.getDia_especifico();
					String d = sdf.format(dia);

					HashMap aux = (HashMap) m.get(d + "-" + idFam);
					if (aux == null)
						aux = new HashMap();
					aux.put(cmLocal.getId_rango(), cmLocal.getPorcentaje());

					m.put(d + "-" + idFam, aux);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return m;
	}

	/*
	 * Entrega la Cantidad Ocupada.
	 */
	public HashMap getCargaOcupada(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax) {

		HashMap m = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Integer idSegmento = fAgenda.getIdGrupoSegmento();

		try {
			// Buscamos los Segmentos de Menor valor.
			//			Grupo_segmentoLocalHome gSegHome = (Grupo_segmentoLocalHome)
			//				HomeFactory.getHome(Grupo_segmentoLocalHome.JNDI_NAME);
			//			Collection c1 = gSegHome.findAllOrderByPorcentaje();

			Ocupacion_rangoLocalHome cmHome = (Ocupacion_rangoLocalHome) HomeFactory.getHome(Ocupacion_rangoLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);

				fecMax = agregaMinutosDate(fecMax, 240);
				if (log.isDebugEnabled())
					log.debug(" Buscando Ocupados [" + fecMin + "," + fecMax + "," + idFam + "," + fAgenda.getCodigoAgencia() + "]");

				Collection c = cmHome.findByFamiliaAgenciaFecha(idFam, fAgenda.getCodigoAgencia(), fecMin, fecMax);
				for (Iterator it = c.iterator(); it.hasNext();) {
					Ocupacion_rangoLocal cmLocal = (Ocupacion_rangoLocal) it.next();
					Date dia = cmLocal.getDia_especifico();
					String d = sdf.format(dia);
					// Hay que filtrar por los Segmentos Menores.
					// Si esta en el Segmento menor, entonces lo sumamos.
					HashMap aux = (HashMap) m.get(d + "-" + idFam);
					if (aux == null)
						aux = new HashMap();

					String key = "S_" + cmLocal.getGrse_id() + "_" + cmLocal.getId_rango() + "_" + idFam;
					if (log.isDebugEnabled())
						log.debug(" Encontrado Ocupado [" + key + "," + cmLocal.getDia_especifico() + "," + cmLocal.getCantidad_ocupada() + "]");
					Integer n1 = (Integer) aux.get(key);
					int x = (n1 == null) ? 0 : n1.intValue();
					int y = (cmLocal.getCantidad_ocupada() == null) ? 0 : cmLocal.getCantidad_ocupada().intValue();

					aux.put(key, new Integer(x + y));

					m.put(d + "-" + idFam, aux);

				}
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}
		return m;
	}

//	/*
//	 * Busca la Disponibilidad con las Cargas Maximas y Ocupadas.
//	 */
//	public ArrayList buscarDisponibilidad(FiltroAgendamientoDTO fAgenda, Date fecMin, Date fecMax, ArrayList rangos, Integer porcSegmento) {
//
//		HashMap Disponibilidad = null;
//		ArrayList listaDisp = new ArrayList();
//		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
//
//		// Obtenenmos la Carga Maxima
//		HashMap mGen = getCargaGenerica(fAgenda, fecMin, fecMax);
//		HashMap mEsp = getCargaEspecifica(fAgenda, fecMin, fecMax);
//
//		// Obtenemos las Distribuciones
//		HashMap distGen = getDistribucionGenerica(fAgenda, fecMin, fecMax);
//		HashMap distEsp = getDistribucionEspecifica(fAgenda, fecMin, fecMax);
//
//		// Obtenemos la Carga Ocupada
//		HashMap mOcup = getCargaOcupada(fAgenda, fecMin, fecMax);
//
//		// Combino Todo para Tener la Disponibilidad
//		DisponibilidadAgendamientoDTO disp = null;
//
//		// Aca tengo Que Obtener los Dias Festivos.
//		HashMap festivos = new HashMap();
//		try {
//			Dias_festivosLocalHome dfHome = (Dias_festivosLocalHome) HomeFactory.getHome(Dias_festivosLocalHome.JNDI_NAME);
//			Dias_festivosLocal dfLocal = null;
//			Collection c = dfHome.findAll();
//			for (Iterator it = c.iterator(); it.hasNext();) {
//				dfLocal = (Dias_festivosLocal) it.next();
//				if (dfLocal.getDia_especifico() == null)
//					continue;
//				festivos.put(sdf.format(dfLocal.getDia_especifico()), "1");
//			}
//		} catch (NamingException e) {
//		} catch (FinderException e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < 7; i++) {
//			Disponibilidad = new HashMap();
//			Date aux = agregaDiasDate(fecMin, i);
//			String f = sdf.format(aux);
//			int idDia = getDay(aux);
//			//int d = idDia; // Se considera como Festivo.
//			String d = getTipoDay(aux);
//			if (festivos.containsKey(f))
//				d = "F";
//			//d = 0; // Se considera como Festivo.
//
//			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
//			// Para Todos las Familia de PS.
//			for (int k = 0; listFamPs != null && k < listFamPs.size(); k++) {
//				Long idFam = (Long) listFamPs.get(k);
//
//				Integer cantTot = (Integer) mEsp.get(f + "-" + idFam);
//				if (cantTot == null)
//					cantTot = (Integer) mGen.get("" + d + "-" + idFam);
//				cantTot = (cantTot == null) ? (new Integer(0)) : cantTot;
//
//				disp = new DisponibilidadAgendamientoDTO();
//				disp.setDia(f);
//				//disp.setTipoDia( d );
//				//disp.setTipoDia( d );
//				disp.setIdDia(idDia);
//				disp.setCargaMaxima(cantTot);
//				disp.setPorcentajeSegmento(porcSegmento);
//				disp.setIdFamiliaPs(idFam);
//
//				if (log.isDebugEnabled())
//					log.debug("Disponibilidad: [Dia (d), Total] = [" + f + "(" + d + "," + idDia + ")," + cantTot + "]");
//				// Ahora obtenemos por Rango.
//				HashMap rAuxGen = (HashMap) distGen.get("" + d + "-" + idFam);
//				HashMap rAuxEsp = (HashMap) distEsp.get(f + "-" + idFam);
//				HashMap hOcup = (HashMap) mOcup.get(f + "-" + idFam);
//
//				rAuxGen = (rAuxGen == null) ? (new HashMap()) : rAuxGen;
//				rAuxEsp = (rAuxEsp == null) ? (new HashMap()) : rAuxEsp;
//				hOcup = (hOcup == null) ? (new HashMap()) : hOcup;
//
//				RangoAgendamientoDTO rAg;
//				for (int j = 0; rangos != null && j < rangos.size(); j++) {
//					rAg = new RangoAgendamientoDTO();
//					RangoAgendamientoDTO rAg2 = (RangoAgendamientoDTO) rangos.get(j);
//					if (rAg2 == null)
//						continue;
//					Integer idRango = rAg2.getIdRango();
//					Integer porc = (Integer) rAuxEsp.get(idRango);
//					if (porc == null)
//						porc = (Integer) rAuxGen.get(idRango);
//					if (porc == null)
//						porc = new Integer(0);
//
//					Integer porcP = null;
//					if (rAg2.getIdPadre() != null) {
//						porcP = (Integer) rAuxEsp.get(rAg2.getIdPadre());
//						if (porcP == null)
//							porcP = (Integer) rAuxGen.get(rAg2.getIdPadre());
//					}
//					Integer ocupSeg = (Integer) hOcup.get("S_" + fAgenda.getIdGrupoSegmento() + "_" + idRango + "_" + idFam);
//					if (ocupSeg == null)
//						ocupSeg = new Integer(0);
//
//					rAg.setIdRango(idRango);
//					rAg.setPorcentaje(porc);
//					rAg.setPorcentajePadre(porcP);
//					rAg.setCantidadOcupadaSegmento(ocupSeg);
//					rAg.setOcupadoSegmento(hOcup);
//					rAg.setListaHijos(rAg2.getListaHijos());
//					rAg.setListaHermanos(rAg2.getListaHermanos());
//					rAg.setIdPadre(rAg2.getIdPadre());
//
//					if (log.isDebugEnabled())
//						log.debug(
//							"Disponibilidad: [Dia, Total, Familia, Rango, %Rango, %Padre, OcupSegmento] = ["
//								+ f
//								+ ","
//								+ cantTot
//								+ ","
//								+ idFam
//								+ ","
//								+ idRango
//								+ ","
//								+ porc
//								+ ","
//								+ porcP
//								+ ","
//								+ ocupSeg
//								+ "]");
//					disp.setRango(rAg);
//				}
//
//				Disponibilidad.put(idFam, disp);
//			}
//
//			listaDisp.add(Disponibilidad);
//		}
//
//		return (listaDisp);
//	}

	public Date agregaDiasDate(Date fecha, int dias) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		gc.add(GregorianCalendar.DAY_OF_YEAR, dias);
		return (gc.getTime());
	}

	public Date agregaMinutosDate(Date fecha, int minutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.MINUTE, minutos);
		return (c.getTime());
	}

	public int getDay(Date fecha) {
		try {
			Calendar gc = Calendar.getInstance();
			gc.setTime(fecha);
			return (gc.get(Calendar.DAY_OF_WEEK) - 1);
		} catch (Exception e) {
			return -1;
		}
	}

	public String getTipoDay(Date fecha) {
		try {
			Calendar gc = Calendar.getInstance();
			gc.setTime(fecha);
			int idDia = gc.get(Calendar.DAY_OF_WEEK) - 1;
			if (idDia == 0)
				return "D";
			if (idDia == 6)
				return "S";

			return "H";

		} catch (Exception e) {
		}

		return "H";

	}

	public void AsignarPeticion(FiltroAgendamientoDTO fAgenda, FechaCompromisoAgendamientoDTO datosCompromiso) {

		// Tengo que verificar si es con Tecnico o NO.
		String conTecnico = datosCompromiso.getConTecnico();

		if (conTecnico != null && "1".equals(conTecnico)) {
			// Tengo que buscar Tecnico que lo pueda hacer.
			// 
			//datosCompromiso.setIdTecnico( new Long(0) );
			datosCompromiso.setIdTecnico(null);
		}
		// Grabamos el Compromiso de la Peticion
		grabaCompromiso(fAgenda, datosCompromiso);

		// Ahora sumamos en la Tabla de Ocupados.
		// Solo si no es TipoAgendamietno de LLamada
		Long tc = datosCompromiso.getIdTipoAgenda();
		if (tc != null && tc.intValue() != 0 && tc.intValue() != 1) {
			if (log.isDebugEnabled())
				log.debug("Sumando la Cantidad Ocupada");
			guardaOcupado(fAgenda, datosCompromiso);
		}

	}

	public void guardaOcupado(FiltroAgendamientoDTO fAgenda, FechaCompromisoAgendamientoDTO datosCompromiso) {
		seteaOcupado(fAgenda, datosCompromiso, 1);
	}

	public void seteaOcupado(FiltroAgendamientoDTO fAgenda, FechaCompromisoAgendamientoDTO datosCompromiso, int val) {
		Date fecCompromiso = datosCompromiso.getFechaCompromiso();
		Integer idRango = datosCompromiso.getIdRango();
		String pComercial = fAgenda.getPlantaComercial();
		String codAgencia = fAgenda.getCodigoAgencia();
		Integer idGrupoSeg = fAgenda.getIdGrupoSegmento();

		try {
			Ocupacion_rangoLocalHome oHome = (Ocupacion_rangoLocalHome) HomeFactory.getHome(Ocupacion_rangoLocalHome.JNDI_NAME);
			ArrayList listFamPs = fAgenda.getListaFamiliaPS();
			// Para Todos las Familia de PS.
			Ocupacion_rangoLocal oLocal = null;
			// A Todas las Familias de PS  les aumento el Cupo
			for (int i = 0; listFamPs != null && i < listFamPs.size(); i++) {
				Long idFam = (Long) listFamPs.get(i);

				try {
					if (log.isDebugEnabled())
						log.debug(
							"[idRango, idFam, pComerical, fecCompromiso, idGrupoSegmento] = ["
								+ idRango
								+ ","
								+ idFam
								+ ","
								+ codAgencia
								+ ","
								+ fecCompromiso
								+ ","
								+ idGrupoSeg
								+ ","
								+ val
								+ "]");

					oLocal = oHome.findByRangoFamiliaAgenciaDiaSegmento(idRango, idFam, codAgencia, fecCompromiso, idGrupoSeg);
					Integer oc = oLocal.getCantidad_ocupada();
					int valor = (oc == null) ? 0 : oc.intValue();
					if (val == -1 && valor == 0)
						valor = 0;
					else
						valor = valor + val;

					oLocal.setCantidad_ocupada(new Integer(valor));

				} catch (FinderException e) {
					if (log.isDebugEnabled())
						log.debug("Se debe agregar el recurso en la BD: insert");
					if (val == -1)
						return;
					try {
						DBManager manager = new DBManager();
						manager.setDataSource(DBManager.JDBC_VPISTBBA);
						Long idOcup = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_OCUPACION_RANGO"));

						oHome.create(idOcup, idRango, idFam, pComercial, fecCompromiso, new Integer(1), idGrupoSeg, codAgencia);

					} catch (CreateException e1) {
						e1.printStackTrace();
					}
				}
			}
		} catch (NamingException e) {
			log.error("No se pudo actualizar el Valor");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Graba el Compromiso de la Peticion
	 */
	public void grabaCompromiso(FiltroAgendamientoDTO fAgenda, FechaCompromisoAgendamientoDTO datosCompromiso) {
		Date fecCompromiso = datosCompromiso.getFechaCompromiso();
		Integer idRango = datosCompromiso.getIdRango();
		Integer idGrupoSeg = fAgenda.getIdGrupoSegmento();
		Long idTipoAgenda = datosCompromiso.getIdTipoAgenda();
		String pComercial = fAgenda.getPlantaComercial();
		Long numPetico = datosCompromiso.getIdPeticion();
		String hDesde = datosCompromiso.getHoraDesde();
		String hHasta = datosCompromiso.getHoraHasta();
		String userMac = datosCompromiso.getUserMac();
		String codAgencia = fAgenda.getCodigoAgencia();
		Integer idCausaCierre = datosCompromiso.getIdCausaCierre();
		Long idUsuario = datosCompromiso.getIdUsuario();
		Long idGaudi = datosCompromiso.getIdGaudi();

		Long idTecnico = null;
		datosCompromiso.setIdTecnico(idTecnico);
		if (idRango == null)
			idRango = new Integer(1);

		try {
			Compromiso_peticionLocalHome aHome = (Compromiso_peticionLocalHome) HomeFactory.getHome(Compromiso_peticionLocalHome.JNDI_NAME);
			Compromiso_peticionLocal aLocal = null;
			try {
				// Tengo que eliminar el Agendamiento Anterior.
				Collection c = aHome.findByPeticion(numPetico);
				for (Iterator it = c.iterator(); it.hasNext();) {
					aLocal = (Compromiso_peticionLocal) it.next();
					if (aLocal.getEstado() == null || (1 == aLocal.getEstado().intValue())) {
						if (log.isDebugEnabled())
							log.debug("Eliminado Compromiso [" + aLocal.getPeti_numero() + "," + aLocal.getEstado() + "]");
						aLocal.setEstado(new Short("0"));
						// Tengo que dar la disponibilidad del Dia y rango
						if (log.isDebugEnabled())
							log.debug("DEVUELVO LA DISPONIBILIDAD [" + aLocal.getDia_especifico() + "," + aLocal.getId_rango() + "]");
						datosCompromiso.setFechaCompromiso(aLocal.getDia_especifico());
						datosCompromiso.setIdRango(aLocal.getId_rango());
						Long tc = aLocal.getTiag_id();
						// Solo devuelvo si el anterior era con Rango/Cita/Especial.
						if (tc.intValue() == 0 || tc.intValue() == 1)
							continue;

						seteaOcupado(fAgenda, datosCompromiso, -1);
					}
				}
				datosCompromiso.setFechaCompromiso(fecCompromiso);
				datosCompromiso.setIdRango(idRango);

				DBManager manager = new DBManager();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
				Long idComp = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_COMPROMISO_PETICION"));

				if (log.isDebugEnabled())
					log.debug(
						"Asignacion Petico = ["
							+ idTipoAgenda
							+ ","
							+ idRango
							+ ","
							+ numPetico
							+ ","
							+ pComercial
							+ ","
							+ fecCompromiso
							+ ","
							+ idTecnico
							+ ","
							+ hDesde
							+ ","
							+ hHasta
							+ ","
							+ userMac
							+ ","
							+ codAgencia
							+ ","
							+ idGrupoSeg
							+ ","
							+ idCausaCierre
							+ ","
							+ idUsuario
							+ "]");
				aLocal =
					aHome.create(
						idComp,
						idTipoAgenda,
						idRango,
						numPetico,
						pComercial,
						fecCompromiso,
						hDesde,
						hHasta,
						userMac,
						codAgencia,
						idGrupoSeg,
						idCausaCierre);
			} catch (CreateException e1) {
				log.error("CreateException CompromisoPeticion [" + numPetico + "] : " + e1.getMessage(), e1);
				try {
					DBManager manager = new DBManager();
					manager.setDataSource(DBManager.JDBC_VPISTBBA);
					Long idComp = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_COMPROMISO_PETICION"));

					aLocal =
						aHome.create(
							idComp,
							idTipoAgenda,
							idRango,
							numPetico,
							pComercial,
							fecCompromiso,
							hDesde,
							hHasta,
							userMac,
							codAgencia,
							idGrupoSeg,
							idCausaCierre);

					log.error("Peticion Comprometida [" + numPetico + "]");
				} catch (CreateException e2) {
					log.error("CreateException CompromisoPeticion (2) [" + numPetico + "] : " + e2.getMessage());
				}
			} catch (FinderException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			log.error("Error: NamingException. No se pudo Generar el Compromiso");
		}
	}

	public ArrayList buscarAgendamiento(Long idPetico) {
		ArrayList lista = null;

		try {
			Compromiso_peticionLocalHome aHome = (Compromiso_peticionLocalHome) HomeFactory.getHome(Compromiso_peticionLocalHome.JNDI_NAME);
			Compromiso_peticionLocal aLocal = null;
			Collection c1 = aHome.findByPeticion(idPetico);
			lista = new ArrayList();
			for (Iterator it = c1.iterator(); it.hasNext();) {
				aLocal = (Compromiso_peticionLocal) it.next();
				if (log.isDebugEnabled())
					log.debug("Agregando ['" + aLocal.getPeti_numero() + "," + aLocal.getDia_especifico() + "]");

				FechaCompromisoAgendamientoDTO dto = new FechaCompromisoAgendamientoDTO();
				dto.setFechaCompromiso(aLocal.getDia_especifico());
				dto.setHoraDesde(aLocal.getHora_desde());
				dto.setHoraHasta(aLocal.getHora_hasta());
				dto.setIdPeticion(aLocal.getPeti_numero());
				dto.setUserMac(aLocal.getUser_mac());
				dto.setIdTipoAgenda(aLocal.getTiag_id());
				dto.setIdRango(aLocal.getId_rango());
				dto.setEstado(new Byte("" + aLocal.getEstado()));
				dto.setIdCausaCierre(aLocal.getCare_id());
				dto.setFechaAtencion(aLocal.getFecha());
				dto.setIdUsuario(aLocal.getUsua_id());

				lista.add(dto);
			}
			return (lista);

		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		} catch (FinderException e) {
			e.printStackTrace();
			return null;
		}
	}

	public FechaCompromisoAgendamientoDTO buscarUltimoAgendamiento(Long idPetico) {
		FechaCompromisoAgendamientoDTO dto = null;

		try {
			Compromiso_peticionLocalHome aHome = (Compromiso_peticionLocalHome) HomeFactory.getHome(Compromiso_peticionLocalHome.JNDI_NAME);
			Compromiso_peticionLocal aLocal = aHome.findAgendamientoActivo(idPetico);

			dto = new FechaCompromisoAgendamientoDTO();
			dto.setFechaCompromiso(aLocal.getFecha());
			dto.setHoraDesde(aLocal.getHora_desde());
			dto.setHoraHasta(aLocal.getHora_hasta());
			dto.setIdPeticion(aLocal.getPeti_numero());
			dto.setUserMac(aLocal.getUser_mac());
			dto.setIdTipoAgenda(aLocal.getTiag_id());
			dto.setIdRango(aLocal.getId_rango());
			dto.setEstado(new Byte("" + aLocal.getEstado()));
			dto.setIdCausaCierre(aLocal.getCare_id());
			dto.setFechaAtencion(aLocal.getFecha());
			dto.setIdUsuario(aLocal.getUsua_id());

			return (dto);

		} catch (NamingException e) {
			return null;
		} catch (FinderException e) {
			e.printStackTrace();
			return null;
		}
	}

//	public boolean validarUsuarioMAC(String user, String pass) {
//		if (pass == null || "".equals(pass))
//			return false;
//		try {
//			Usuario_macLocalHome uHome = (Usuario_macLocalHome) HomeFactory.getHome(Usuario_macLocalHome.JNDI_NAME);
//			Usuario_macLocal uLocal = null;
//			uLocal = uHome.findByUsername(user);
//
//			if (pass.equals(uLocal.getPassword()))
//				return true;
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (FinderException e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}

	// CR16429 FindAll - ana santos
	/*public ArrayList getListaCausas() {
		ArrayList lista = new ArrayList();

		try {
			Causa_reagendamientoLocalHome crHome = (Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
			Causa_reagendamientoLocal crLocal = null;
			Collection c = crHome.findAll();
			HashMap aux = null;
			for (Iterator it = c.iterator(); it.hasNext();) {
				crLocal = (Causa_reagendamientoLocal) it.next();
				aux = new HashMap();
				Causa_reagendamientoKey crKey = (Causa_reagendamientoKey) crLocal.getPrimaryKey();
				aux.put("KEY", crKey.care_id);
				aux.put("VALUE", crLocal.getCare_codigo() + " - " + crLocal.getCare_descripcion());
				lista.add(aux);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return lista;
	}*/

	public ArrayList getListaRangos() {
		ArrayList lista = new ArrayList();

		try {
			RangoLocalHome rHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			RangoLocal rLocal = null;
			Collection c = rHome.findAll();
			HashMap aux = null;
			for (Iterator it = c.iterator(); it.hasNext();) {
				rLocal = (RangoLocal) it.next();
				RangoAgendamientoDTO rDto = new RangoAgendamientoDTO();
				RangoKey rKey = (RangoKey) rLocal.getPrimaryKey();
				Integer idRango = rKey.id_rango;
				rDto.setIdRango(idRango);
				rDto.setHoraDesde(rLocal.getHora_desde());
				rDto.setHoraHasta(rLocal.getHora_hasta());
				rDto.setCodigoRango(rLocal.getCodigo_rango());
				rDto.setIdPadre(rLocal.getId_padre());
				lista.add(rDto);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}

		return lista;
	}

	/*
	 * Entrega los datos del Grupo de Segmento para el Segmento dado.
	 */
	public ArrayList getListaSegmentos(String pComercial, Integer idGrupo) {
		ArrayList listaSeg = new ArrayList();
		HashMap mapExc = new HashMap();

		SegmentoAgendamientoDTO grupoSegmento = null;
		Integer valorPorcentaje = null;
		// Primero Sacamos los valores de los Segmentos de excepcion y lo Dejamos en un Hash.
		try {
			Excepcion_carga_segmentoLocalHome dHome = (Excepcion_carga_segmentoLocalHome) HomeFactory.getHome(Excepcion_carga_segmentoLocalHome.JNDI_NAME);
			Collection c = dHome.findByPlantaComercial(pComercial);
			if (c != null) {
				for (Iterator it = c.iterator(); it.hasNext();) {
					// Tenemos Segmentos Excepcion para la P. Comercial.
					Excepcion_carga_segmentoLocal dLocal = (Excepcion_carga_segmentoLocal) it.next();
					grupoSegmento = new SegmentoAgendamientoDTO();
					Integer idGrupoSegmento = (Integer) dLocal.getGrse_id();
					grupoSegmento.setIdGrupoSegmento(idGrupoSegmento);
					grupoSegmento.setPorcentaje(dLocal.getPorcentaje());
					grupoSegmento.setPorcentajeMinimo(dLocal.getPorcentaje_minimo());

					if (log.isDebugEnabled())
						log.debug("Segmento EXC: [" + idGrupoSegmento + "," + grupoSegmento.getPorcentaje() + "," + grupoSegmento.getPorcentajeMinimo() + "]");
					mapExc.put(idGrupoSegmento, grupoSegmento);
				}
			}
		} catch (NamingException e) {
		} catch (FinderException e) {
			if (log.isDebugEnabled())
				log.debug("Error Finder Exception: GrupoSegmentoEx para Segmento (" + pComercial + ")");
		} catch (Exception e) {
		}

		try {
			Grupo_segmentoLocalHome gSegHome = (Grupo_segmentoLocalHome) HomeFactory.getHome(Grupo_segmentoLocalHome.JNDI_NAME);
			Collection c = gSegHome.findAllOrderByPorcentaje();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Grupo_segmentoLocal gSegLocal = (Grupo_segmentoLocal) it.next();
				Grupo_segmentoKey gsKey = (Grupo_segmentoKey) gSegLocal.getPrimaryKey();
				Integer idGrupoSegmento = gsKey.grse_id;
				if (mapExc.containsKey(idGrupoSegmento)) {
					grupoSegmento = (SegmentoAgendamientoDTO) mapExc.get(idGrupoSegmento);
				} else {
					grupoSegmento = new SegmentoAgendamientoDTO();
					grupoSegmento.setIdGrupoSegmento(idGrupoSegmento);
					grupoSegmento.setNombreGrupoSegmento(gSegLocal.getGrse_nombre());
					grupoSegmento.setPorcentaje(gSegLocal.getGrse_porcentaje());
					grupoSegmento.setPorcentajeMinimo(gSegLocal.getGrse_porcentaje_minimo());
				}

				if (idGrupo.intValue() == grupoSegmento.getIdGrupoSegmento().intValue())
					valorPorcentaje = grupoSegmento.getPorcentaje();
				listaSeg.add(grupoSegmento);

				if (log.isDebugEnabled())
					log.debug("Segmento: [" + idGrupoSegmento + "," + gSegLocal.getGrse_porcentaje() + "," + gSegLocal.getGrse_porcentaje_minimo() + "," + "]");
			}
		} catch (NamingException e) {
			log.info("Error Naming Exception: GrupoSegmento");
		} catch (FinderException e) {
			log.info("Error Finder Exception: All GrupoSegmento");
		} catch (Exception e) {
			log.info("Error Exception: AllGrupoSegmentoSegmento");
		}

		// Ordenamos el Lisatdo de Seg y calculamos cuales son los Segmentos Mayores al que estoy Agendando
		ArrayList listFinal = new ArrayList();
		if (valorPorcentaje == null)
			valorPorcentaje = new Integer(0);

		for (int i = 0; listaSeg != null && i < listaSeg.size(); i++) {
			SegmentoAgendamientoDTO sDto = (SegmentoAgendamientoDTO) listaSeg.get(i);
			if (sDto == null || sDto.getIdGrupoSegmento() == null)
				continue;

			// Seteamos si es Mayor 
			sDto.setSegmentoMayor(sDto.getPorcentaje().intValue() >= valorPorcentaje.intValue());

			//			log.debug("Ordenand SEG.: [" + i + "," + sDto.getIdGrupoSegmento() + "," + sDto.getPorcentaje() + 
			//			"," + sDto.getPorcentajeMinimo() + "," + sDto.isSegmentoMayor() + "]");

			ArrayList listAux = new ArrayList();
			boolean agregado = false;
			for (int j = 0; j < listFinal.size(); j++) {
				SegmentoAgendamientoDTO dtoAux = (SegmentoAgendamientoDTO) listFinal.get(j);
				if (sDto.getPorcentaje().intValue() < dtoAux.getPorcentaje().intValue() && !agregado) {
					listAux.add(sDto);
					agregado = true;
				}
				listAux.add(dtoAux);
			}
			if (!agregado)
				listAux.add(sDto);

			listFinal = listAux;
		}

		// Solo Logs.
		/*		for (int j=0; j<listFinal.size(); j++) {
					SegmentoAgendamientoDTO sDto = (SegmentoAgendamientoDTO) listFinal.get(j);
					log.debug("ORDEN FINAL SEG.: [" + j + "," + sDto.getIdGrupoSegmento() + "," + sDto.getPorcentaje() + 
					"," + sDto.getPorcentajeMinimo() + "," + sDto.isSegmentoMayor() + "]");
				}
		*/

		return listFinal;
	}

	/*
	 * Entrega los datos del Grupo de Segmento para el Segmento dado.
	 */
	public ArrayList getListaSegmentosOld(String pComercial, Integer idGrupo) {
		//HashMap map = new HashMap();
		ArrayList map = new ArrayList();

		SegmentoAgendamientoDTO grupoSegmento = null;
		boolean esMayor = false;
		try {
			Grupo_segmentoLocalHome gSegHome = (Grupo_segmentoLocalHome) HomeFactory.getHome(Grupo_segmentoLocalHome.JNDI_NAME);
			Collection c = gSegHome.findAllOrderByPorcentaje();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Grupo_segmentoLocal gSegLocal = (Grupo_segmentoLocal) it.next();
				grupoSegmento = new SegmentoAgendamientoDTO();
				Grupo_segmentoKey gsKey = (Grupo_segmentoKey) gSegLocal.getPrimaryKey();
				Integer idGrupoSegmento = gsKey.grse_id;
				grupoSegmento.setIdGrupoSegmento(idGrupoSegmento);
				grupoSegmento.setNombreGrupoSegmento(gSegLocal.getGrse_nombre());
				grupoSegmento.setPorcentaje(gSegLocal.getGrse_porcentaje());
				grupoSegmento.setPorcentajeMinimo(gSegLocal.getGrse_porcentaje_minimo());
				grupoSegmento.setSegmentoMayor(esMayor);
				map.add(grupoSegmento);
				if (log.isDebugEnabled())
					log.debug(
						"Segmento: ["
							+ idGrupoSegmento
							+ ","
							+ gSegLocal.getGrse_porcentaje()
							+ ","
							+ gSegLocal.getGrse_porcentaje_minimo()
							+ ","
							+ esMayor
							+ "]");
				if (idGrupo.intValue() == idGrupoSegmento.intValue())
					esMayor = true;
			}
		} catch (NamingException e) {
			log.info("Error Naming Exception: GrupoSegmento");
		} catch (FinderException e) {
			log.info("Error Finder Exception: All GrupoSegmento");
		} catch (Exception e) {
			log.info("Error Exception: AllGrupoSegmentoSegmento");
		}
		try {
			// Vemos si ha y Excepcion de Segmentos
			esMayor = false;
			Excepcion_carga_segmentoLocalHome dHome = (Excepcion_carga_segmentoLocalHome) HomeFactory.getHome(Excepcion_carga_segmentoLocalHome.JNDI_NAME);
			Collection c = dHome.findByPlantaComercial(pComercial);
			if (c != null && c.size() > 0)
				map = new ArrayList();
			for (Iterator it = c.iterator(); it.hasNext();) {
				Excepcion_carga_segmentoLocal dLocal = (Excepcion_carga_segmentoLocal) it.next();
				grupoSegmento = new SegmentoAgendamientoDTO();
				Integer idGrupoSegmento = (Integer) dLocal.getGrse_id();
				grupoSegmento.setIdGrupoSegmento(idGrupoSegmento);
				grupoSegmento.setPorcentaje(dLocal.getPorcentaje());
				grupoSegmento.setPorcentajeMinimo(dLocal.getPorcentaje_minimo());
				grupoSegmento.setSegmentoMayor(esMayor);

				if (log.isDebugEnabled())
					log.debug(
						"Segmento EXC: ["
							+ idGrupoSegmento
							+ ","
							+ grupoSegmento.getPorcentaje()
							+ ","
							+ grupoSegmento.getPorcentajeMinimo()
							+ ","
							+ esMayor
							+ "]");

				map.add(grupoSegmento);
				if (idGrupo.intValue() == idGrupoSegmento.intValue())
					esMayor = true;
			}

		} catch (NamingException e) {
		} catch (FinderException e) {
			log.info("Error Finder Exception: GrupoSegmentoEx para Segmento (" + pComercial + ")");
		} catch (Exception e) {
		}

		return map;
	}

	/*
	 * Metodo que entrega la Disponiblidad en un Rango Dado.
	 */
	public int getDisponibilidad(DisponibilidadAgendamientoDTO dispAg, Integer idRango, ArrayList listaSegmentos) {
		Integer tt = dispAg.getCargaMaxima();
		RangoAgendamientoDTO r = dispAg.getRango(idRango);

		if (r == null || tt == null || tt.intValue() == 0)
			return -1;

		Integer pc = (r.getPorcentaje() == null) ? (new Integer(100)) : r.getPorcentaje();

		double m = (double) tt.intValue();
		double p = (double) pc.intValue();
		double q = (dispAg.getPorcentajeSegmento() == null) ? 100 : ((double) dispAg.getPorcentajeSegmento().intValue());

		m = (double) ((int) ((m * p) / 100)); // Total del Rango

		// Calculo losOcupados de los rangos menor igual al actual.
		double pasados = 0;
		double ocu = 0;
		HashMap map = r.getOcupadoSegmento();
		double ocpD = 0;
		double ocpH = 0;
		for (int i = 0; i < listaSegmentos.size(); i++) {
			SegmentoAgendamientoDTO seg = (SegmentoAgendamientoDTO) listaSegmentos.get(i);
			String key = "S_" + seg.getIdGrupoSegmento() + "_" + idRango;
			Integer ocp = (Integer) map.get(key);
			ocp = (ocp == null) ? new Integer(0) : ocp;
			ocpD = ocp.intValue();
			if (r.getIdPadre() != null) {
				// Aca Tengo que sumar lo del Padre. (50% ?)
				String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + r.getIdPadre();
				Integer ocAux = (Integer) map.get(key1);
				
				if (ocAux != null)
					ocpD = ocpD + ((double) ocAux.intValue() * 0.5);
			}

			// TODO: Tengo que ver lo de los Hijos:::-(((((
			if (r.getListaHijos() != null) {
				// Aca Tengo que sumar lo de los Hijos.
				for (int k = 0; k < r.getListaHijos().size(); k++) {
					Integer id = (Integer) r.getListaHijos().get(k);
					String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + id;
					Integer ocAux = (Integer) map.get(key1);
					if (ocAux != null)
						ocpD = ocpD + ocAux.intValue();
				}
			}

			// TODO: Tengo que ver lo de los Hermanos:::-(((((
			if (r.getListaHermanos() != null) {
				// Aca Tengo que sumar lo de los Hijos.
				for (int k = 0; k < r.getListaHermanos().size(); k++) {
					Integer id = (Integer) r.getListaHermanos().get(k);
					String key1 = "S_" + seg.getIdGrupoSegmento() + "_" + id;
					Integer ocAux = (Integer) map.get(key1);
					if (ocAux != null)
						ocpH = ocpH + ocAux.intValue();
				}
			}

			if (seg.isSegmentoMayor()) {
				// Tengo que ver si hay quienes se pasaron. (Minimo reserv - Ocupado)
				Integer n1 = seg.getPorcentajeMinimo();
				n1 = (n1 == null) ? new Integer(0) : n1;
				double dif = (n1.intValue() * m / 100) - ocpD;
				if (dif < 0)
					pasados += dif;
			} else {
				// Sumo los ocupados de los Segmentos Menores.
				ocu += ocpD;
			}
		}

		double d = (m * q / 100) - ocu + pasados;

		double aux = ((double) 100 * d / m);

		int res = (int) (aux * 100);

		return (res);
	}

	private Long getCodigoOperCom(String opCom) {
		Long opDefault = new Long(1);

		if ("A".equals(opCom))
			return new Long(1);
		if ("B".equals(opCom))
			return new Long(2);
		if ("M".equals(opCom))
			return new Long(1);
		if ("T".equals(opCom))
			return new Long(1);
		if ("R".equals(opCom))
			return new Long(1);

		return opDefault;
	}

	public boolean getPSTerreno(Long codPs, Long idOperCom) {
		if (esTerreno == null || esTerreno.isEmpty())
			return false;

		return esTerreno.containsKey("" + codPs + "-" + idOperCom);
	}

}