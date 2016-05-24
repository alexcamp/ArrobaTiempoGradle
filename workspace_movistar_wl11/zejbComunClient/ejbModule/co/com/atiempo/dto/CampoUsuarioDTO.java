/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CampoUsuarioDTO {

	/**
	 * 
	 */
	public CampoUsuarioDTO() {
		super();
	}
	
	private int cvId;
	private Long usuaId;
	
	
	/**
	 * @return
	 */
	public int getCvId() {
		return cvId;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @param i
	 */
	public void setCvId(int i) {
		cvId = i;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

}
