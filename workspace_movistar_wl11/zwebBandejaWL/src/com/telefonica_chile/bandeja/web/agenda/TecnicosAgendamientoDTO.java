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

import org.apache.log4j.Logger;

public class TecnicosAgendamientoDTO {
	
	Long idTecnico = null;
	Long idSupervisor = null;

	HashMap habilidades = new HashMap();
	HashMap carga = new HashMap();
	ArrayList citas = new ArrayList();
	private Logger log = Logger.getLogger(TecnicosAgendamientoDTO.class);


	public TecnicosAgendamientoDTO() {
	}

	public Integer getCarga( Integer rango, String tipo ) {
		Integer aux = null;
		if (carga!= null)
			aux = (Integer) carga.get(tipo + "_" + rango);

		return aux;
	}

	public void addCarga( Integer rango, String tipo, Integer valor ) {
		if (carga!= null)
			carga.put(tipo + "_" + rango, valor);
	}

	public void agregaCarga( Integer rango, String tipo, int valor ) {
		if (carga!= null) {
			Integer res = (Integer) carga.get(tipo + "_" + rango);
			if (res == null)
				res = new Integer(0);
			
			
			carga.put(tipo + "_" + rango, new Integer( res.intValue() + valor) );
		}
	}

	public void setCarga( HashMap map ) {
		carga = map;
	}

	public void setHabilidades( HashMap map ) {
		habilidades = map;
	}

	public boolean esHabilidad( Long key, String valor ) {
		HashMap aux = null;
		if (habilidades != null)
			aux = (HashMap) habilidades.get(key);

		if ( aux == null ) {
			log.debug("HABILIDAD NULA [" + key + "]");
			return false;
		}
		
		String aux2 = (String) aux.get(valor);
		if (aux2 != null)
			return true;

		log.debug("HABILIDAD NO SETEADA [" + valor + "]");
		return false;
	}

	public void addHabilidad( Long key, String valor ) {
		if (habilidades == null)
			habilidades = new HashMap();

		HashMap aux = (HashMap) habilidades.get(key);
		if (aux == null)
			aux = new HashMap();
		
		aux.put(valor, "1");

		habilidades.put(key, aux);
	}

	public void addCita( String valor ) {
		if (citas != null)
			citas.add(valor);
	}

	/**
	 * @return
	 */
	public Long getIdSupervisor() {
		return idSupervisor;
	}

	/**
	 * @return
	 */
	public Long getIdTecnico() {
		return idTecnico;
	}

	/**
	 * @param long1
	 */
	public void setIdSupervisor(Long long1) {
		idSupervisor = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdTecnico(Long long1) {
		idTecnico = long1;
	}

	/**
	 * @return
	 */
	public ArrayList getCitas() {
		return citas;
	}

	/**
	 * @param list
	 */
	public void setCitas(ArrayList list) {
		citas = list;
	}

}
