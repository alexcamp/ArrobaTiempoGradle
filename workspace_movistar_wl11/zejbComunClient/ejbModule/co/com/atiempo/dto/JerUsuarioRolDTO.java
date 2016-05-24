/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class JerUsuarioRolDTO {

	/**
	 * 
	 */
	public JerUsuarioRolDTO() {
		super();
	}

	private Long jerSupUid;
	private Long jerSubUid;
	private Long jerRol;
	


	/**
	 * @return
	 */
	public Long getJerRol() {
		return jerRol;
	}

	/**
	 * @return
	 */
	public Long getJerSubUid() {
		return jerSubUid;
	}

	/**
	 * @return
	 */
	public Long getJerSupUid() {
		return jerSupUid;
	}

	/**
	 * @param long1
	 */
	public void setJerRol(Long long1) {
		jerRol = long1;
	}

	/**
	 * @param long1
	 */
	public void setJerSubUid(Long long1) {
		jerSubUid = long1;
	}

	/**
	 * @param long1
	 */
	public void setJerSupUid(Long long1) {
		jerSupUid = long1;
	}

}
