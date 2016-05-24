package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.HashMap;

public class AgenciaDTO implements Serializable{
	
//	private Long 	agenId = new Long(0);
	private String	agenId = "";
	private String  agenCodigo = "";
	private String  agenDescripcion = "";
	
	private HashMap valoresTc = null;

	public String getAgenCodigo() {
		return agenCodigo;
	}

	public String getAgenDescripcion() {
		return agenDescripcion;
	}

//	public Long getAgenId() {
//		return agenId;
//	}

	public void setAgenCodigo(String string) {
		agenCodigo = string;
	}

	public void setAgenDescripcion(String string) {
		agenDescripcion = string;
	}

//	public void setAgenId(Long long1) {
//		agenId = long1;
//	}

	public void addCantidad(String key, int cantidad) {
		if ( cantidad == 0 || key == null)
			return;

		if ( valoresTc == null )
			valoresTc = new HashMap();
		
		Integer aux = (Integer) valoresTc.get( key );
		Integer total = null;
		if ( aux == null || aux.intValue()==0)
			total = new Integer( cantidad );
		else
			total = new Integer( aux.intValue() + cantidad );

		valoresTc.put( key, total);
	}

	public Integer getCantidad(String key) {
		if ( valoresTc == null )
			return new Integer(0);
		
		Integer aux = (Integer) valoresTc.get( key );
		if ( aux == null )
			return new Integer(0);

		return  aux;
	}
	
	
/**
 * @return
 */
public String getAgenId() {
	return agenId;
}

/**
 * @param string
 */
public void setAgenId(String string) {
	agenId = string;
}

}
