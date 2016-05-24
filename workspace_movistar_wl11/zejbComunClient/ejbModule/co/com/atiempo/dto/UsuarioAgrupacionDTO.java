/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class UsuarioAgrupacionDTO {

	/**
	 * 
	 */
	public UsuarioAgrupacionDTO() {
		super();
	}
	
	private Long usuaId;
	private Long agId;
	
	

	/**
	 * @return
	 */
	public Long getAgId() {
		return agId;
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
	public void setAgId(Long long1) {
		agId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

}
