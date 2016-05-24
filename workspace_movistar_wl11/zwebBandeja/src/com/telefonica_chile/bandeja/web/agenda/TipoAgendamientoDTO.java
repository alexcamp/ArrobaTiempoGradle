/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class TipoAgendamientoDTO {
	
	Long idTipo = null;
	String nombreAgendamiento = null;
	
	Integer tiempoMinimo = null;
	Integer minimoTiempoMinimo = null;
	Integer tiempoMaximo = null;
	ArrayList listaDias = new ArrayList();
	HashMap diasHab = new HashMap();
	boolean agendaGaudi = false;

	public TipoAgendamientoDTO() {
	}

	/**
	 * @return
	 */
	public void seteaDias(String dias) {
		if ( dias == null )
			return;
		String[] aux = split(dias, ',');
		if (aux == null)
			return;
		boolean hayDias = !diasHab.isEmpty();
		if ( hayDias )
			listaDias = new ArrayList();

		for (int i=0; i<aux.length; i++) {
			 if ( !hayDias || "1".equals((String) diasHab.get(aux[i])) )
				listaDias.add( aux[i] );
		}
		diasHab = new HashMap();
		for (int i=0; i<listaDias.size(); i++) {
			diasHab.put( (String) listaDias.get(i), "1" );
		}
		
	}

	/**
	 * @return
	 */
	public ArrayList getListaDias() {
		return listaDias;
	}

	/**
	 * @return
	 */
	public Long getIdTipo() {
		return idTipo;
	}

	/**
	 * @return
	 */
	public String getNombreAgendamiento() {
		return nombreAgendamiento;
	}

	/**
	 * @return
	 */
	public Integer getTiempoMaximo() {
		return tiempoMaximo;
	}

	/**
	 * @return
	 */
	public Integer getTiempoMinimo() {
		return tiempoMinimo;
	}

	/**
	 * @param list
	 */
	public void setDias(ArrayList list) {
		listaDias = list;
	}

	/**
	 * @param integer
	 */
	public void setIdTipo(Long long1) {
		idTipo = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreAgendamiento(String string) {
		nombreAgendamiento = string;
	}

	/**
	 * @param integer
	 */
	public void setTiempoMaximo(Integer integer) {
		tiempoMaximo = integer;
	}

	/**
	 * @param integer
	 */
	public void setTiempoMinimo(Integer integer) {
		tiempoMinimo = integer;
	}

	public static String[] split(String string, char delim) {
		if (string == null)
			return null;

		StringTokenizer st = new StringTokenizer(string, String.valueOf(delim));
		int n = st.countTokens();
		if (n == 0)
			return null;
		
		String[] arr = new String[n];
		for (int i = 0; st.hasMoreTokens(); ) {
			arr[i++] = st.nextToken();
		}
		return arr;
	}

	public String toString() {
		return ( "idTipo = '" + idTipo + "'\n" +
			"Nombre = '" + nombreAgendamiento + "'\n" +
			"TiempoMin = '" + tiempoMinimo + "'\n" +
			"TiempoMax = '" + tiempoMaximo + "'\n" );
	}
	/**
	 * @return
	 */
	public Integer getMinimoTiempoMinimo() {
		return minimoTiempoMinimo;
	}

	/**
	 * @param integer
	 */
	public void setMinimoTiempoMinimo(Integer integer) {
		minimoTiempoMinimo = integer;
	}

	/**
	 * @return
	 */
	public boolean isAgendaGaudi() {
		return agendaGaudi;
	}

	/**
	 * @param b
	 */
	public void setAgendaGaudi(boolean b) {
		agendaGaudi = b;
	}

}
