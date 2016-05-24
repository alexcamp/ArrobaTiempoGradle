/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class PerfilUsuarioDTO {

	/**
	 * 
	 */
	public PerfilUsuarioDTO() {
		super();
	}

	private Long usuaId;
	private Long perfId;
	
	

	/**
	 * @return
	 */
	public Long getPerfId() {
		return perfId;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @param long1
	 */
	public void setPerfId(Long long1) {
		perfId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

}
