package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

public class AplicacionDTO implements Serializable,Comparable{
	private Long 	apId = new Long(0);
	private String 	apNombre = "";
	private String 	apUrlReasignacion = "";
	private String 	apUrlBase = "";
	private String 	apUrlSupervisor = "";

	public Long getApId() {
		return apId;
	}

	public String getApNombre() {
		return apNombre;
	}

	public String getApUrlBase() {
		return apUrlBase;
	}

	public String getApUrlReasignacion() {
		return apUrlReasignacion;
	}

	public String getApUrlSupervisor() {
		return apUrlSupervisor;
	}

	public void setApId(Long long1) {
		apId = long1;
	}

	public void setApNombre(String string) {
		apNombre = string;
	}

	public void setApUrlBase(String string) {
		apUrlBase = string;
	}

	public void setApUrlReasignacion(String string) {
		apUrlReasignacion = string;
	}

	public void setApUrlSupervisor(String string) {
		apUrlSupervisor = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		AplicacionDTO otro=(AplicacionDTO) o;
		if(this.apNombre!=null && otro.getApNombre()!=null)
			return this.apNombre.compareTo(otro.getApNombre());
		return 0;
	}
}
