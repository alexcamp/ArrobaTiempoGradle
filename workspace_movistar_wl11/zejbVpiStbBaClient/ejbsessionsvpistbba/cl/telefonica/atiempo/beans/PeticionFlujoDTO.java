/*
 * Created on Mar 31, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cl.telefonica.atiempo.beans;

/**
 * @author fecabre
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PeticionFlujoDTO {

	/**
	 * 
	 */
	public PeticionFlujoDTO() {
	}
	
	Integer id = null;
	Long idPeticion = null;
	Long idOpco = null;
	Integer idActividad = null;
	Long idPs = null;
	Integer orden = null;
	String estado = null;
	
	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Integer getIdActividad() {
		return idActividad;
	}

	/**
	 * @return
	 */
	public Long getIdOpco() {
		return idOpco;
	}

	/**
	 * @return
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @return
	 */
	public Long getIdPs() {
		return idPs;
	}

	/**
	 * @return
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param integer
	 */
	public void setId(Integer integer) {
		id = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdActividad(Integer integer) {
		idActividad = integer;
	}

	/**
	 * @param long1
	 */
	public void setIdOpco(Long long1) {
		idOpco = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdPeticion(Long long1) {
		idPeticion = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdPs(Long long1) {
		idPs = long1;
	}

	/**
	 * @param integer
	 */
	public void setOrden(Integer integer) {
		orden = integer;
	}

}
