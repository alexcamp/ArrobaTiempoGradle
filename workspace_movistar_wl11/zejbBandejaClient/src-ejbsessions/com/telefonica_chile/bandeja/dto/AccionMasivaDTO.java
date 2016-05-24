/*
 * Created on Jun 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.dto;
import java.io.Serializable;
/**
 * @author ptoliva
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaDTO implements Serializable{
	
	private Long 	acmaId = new Long(0);
	private Long 	acmaRol = new Long(0);
	private String  acmaDescripcion = "";
	private String  acamValor = "";
	private String  acamCierre = "";
	
	
	/**
	 * @return
	 */
	public String getAcamCierre() {
		return acamCierre;
	}

	/**
	 * @return
	 */
	public String getAcamValor() {
		return acamValor;
	}

	/**
	 * @return
	 */
	public String getAcmaDescripcion() {
		return acmaDescripcion;
	}

	/**
	 * @return
	 */
	public Long getAcmaId() {
		return acmaId;
	}

	/**
	 * @return
	 */
	public Long getAcmaRol() {
		return acmaRol;
	}

	
	/**
	 * @param string
	 */
	public void setAcamCierre(String string) {
		acamCierre = string;
	}

	/**
	 * @param string
	 */
	public void setAcamValor(String string) {
		acamValor = string;
	}

	/**
	 * @param string
	 */
	public void setAcmaDescripcion(String string) {
		acmaDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setAcmaId(Long long1) {
		acmaId = long1;
	}

	/**
	 * @param long1
	 */
	public void setAcmaRol(Long long1) {
		acmaRol = long1;
	}
	
	

	public boolean equals(Object obj)
	{
		if ((! (obj instanceof AccionMasivaDTO)) || (obj == null))
		   return (false) ;
        
		AccionMasivaDTO otro = (AccionMasivaDTO) obj ;
        if(this.acamValor==null || otro.getAcamValor()==null)
        	return false;
	   return (this.acamValor.equals(otro.getAcamValor() )) ;
	}

}
