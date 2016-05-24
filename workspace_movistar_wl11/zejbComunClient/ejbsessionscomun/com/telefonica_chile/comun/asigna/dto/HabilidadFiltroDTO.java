/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class HabilidadFiltroDTO implements Serializable {
	
	HashMap map = new HashMap();
	ArrayList filtro = new ArrayList();

	public HabilidadFiltroDTO() {
	}

	public void addFiltro(String id) {
		if ( id == null || "".equals(id) )
			return;
		filtro.add(id);
	}

	public ArrayList getFiltro() {
		return filtro;
	}

	public void setValue(String id, String str) {
		if ( id == null || "".equals(id) )
			return;
		if ( str == null || "".equals(str) )
			return;
		
		map.put(id, str);
	}

	public String getValue(String id) {
		if ( id == null || "".equals(id) )
			return "";
		String aux = (String) map.get(id);
		return aux;
	}

}
