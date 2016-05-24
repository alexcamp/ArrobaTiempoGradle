package com.telefonica_chile.bandeja.datos.usuarios;

import java.io.Serializable;

public class SegmentoDTO implements Serializable,Comparable{
	private Long 	segmId = new Long(0);
	private String 	segmDescripcion = ""; 

	public Long getSegmId() {
		return segmId;
	}

	public void setSegmId(Long long1) {
		segmId = long1;
	}

	public String getSegmDescripcion() {
		return segmDescripcion;
	}

	public void setSegmDescripcion(String string) {
		segmDescripcion = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		SegmentoDTO otro=(SegmentoDTO) o;
		if(this.segmDescripcion!=null && otro.getSegmDescripcion()!=null)
			return this.segmDescripcion.compareTo(otro.getSegmDescripcion());
		return 0;
	}
}
