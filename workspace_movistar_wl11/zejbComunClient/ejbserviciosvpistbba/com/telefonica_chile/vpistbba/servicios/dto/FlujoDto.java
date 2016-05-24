package com.telefonica_chile.vpistbba.servicios.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author rbpizar
 */
public class FlujoDto implements Serializable {
	private ArrayList flujo;
	private HashMap ubicacion;

	
	public Iterator iterator() {
		ArrayList todos = new ArrayList();
		for (Iterator iter = flujo.iterator(); iter.hasNext();) {
			ArrayList element = (ArrayList) iter.next();
			for (Iterator iterator = element.iterator(); iterator.hasNext();) {
				Actividad actividad = (Actividad) iterator.next();
				todos.add(actividad);
			}
		}
		return todos.iterator();
	}
	/**
	 * @return
	 */
	public ArrayList getFlujo() {
		return flujo;
	}

	/**
	 * @return
	 */
	public HashMap getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param list
	 */
	public void setFlujo(ArrayList list) {
		flujo = list;
	}

	/**
	 * @param map
	 */
	public void setUbicacion(HashMap map) {
		ubicacion = map;
	}

}
