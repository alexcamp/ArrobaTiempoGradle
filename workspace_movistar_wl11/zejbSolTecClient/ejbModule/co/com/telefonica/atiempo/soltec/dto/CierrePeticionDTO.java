/*
 * Created on 15-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CierrePeticionDTO {
	
	String descActividad;
	String codCierre;
	String descCierre;
	String fechaCierre;
	String nombreUsuario;
	
	/*
	 *	CR - 00026148 - Jun 30, 2009
	 *		- German P.
	 */
	String necesitaModem;


	/**
	 * @return
	 */
	public String getDescCierre() {
		return descCierre;
	}

	/**
	 * @return
	 */
	public String getDescActividad() {
		return descActividad;
	}

	/**
	 * @return
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @return
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param string
	 */
	public void setDescCierre(String string) {
		descCierre = string;
	}

	/**
	 * @param string
	 */
	public void setDescActividad(String string) {
		descActividad = string;
	}

	/**
	 * @param string
	 */
	public void setFechaCierre(String string) {
		fechaCierre = string;
	}

	/**
	 * @param string
	 */
	public void setNombreUsuario(String string) {
		nombreUsuario = string;
	}

	/**
	 * @return
	 */
	public String getCodCierre() {
		return codCierre;
	}

	/**
	 * @param string
	 */
	public void setCodCierre(String string) {
		codCierre = string;
	}
	

	/**
	 * @return Returns the necesitaModem.
	 */
	public String getNecesitaModem() {
		return necesitaModem;
	}
	/**
	 * @param necesitaModem The necesitaModem to set.
	 */
	public void setNecesitaModem(String necesitaModem) {
		this.necesitaModem = necesitaModem;
	}
}
