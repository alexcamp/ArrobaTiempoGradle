/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class AccionMasivaDTO {

	/**
	 * 
	 */
	public AccionMasivaDTO() {
		super();
	}
	
	private Long acmaId;
	private Long rolId;
	private String acmaDescripcion;
	private String acmaValor;
	private String acmaCierre;
	
	

	/**
	 * @return
	 */
	public String getAcmaCierre() {
		return acmaCierre;
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
	public String getAcmaValor() {
		return acmaValor;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @param string
	 */
	public void setAcmaCierre(String string) {
		acmaCierre = string;
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
	 * @param string
	 */
	public void setAcmaValor(String string) {
		acmaValor = string;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

}
