/*
 * Created on Jul 24, 2006
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.HashMap;

/**
 * @author Jorge Velasco / Rodrigo Espinoza --> NavigoGroup
 */
public class SesionTorreControlDTO {
	private HashMap hashFiltro = null;

	public String getCampo(String key) {
		if ( hashFiltro == null )
			return "";

		String aux = (String) hashFiltro.get(key);
		if ( aux == null )
			return "";
		return aux; 	
	}

	public void setCampo(String key, String value) {
		if ( hashFiltro == null )
			hashFiltro = new HashMap();

		if ( value==null || key==null )
			return;
		hashFiltro.put( key, value );
	}
}
