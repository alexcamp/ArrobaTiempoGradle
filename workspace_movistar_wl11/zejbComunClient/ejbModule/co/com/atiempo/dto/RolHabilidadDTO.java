/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class RolHabilidadDTO {

	/**
	 * 
	 */
	public RolHabilidadDTO() {
		super();
	}
	
	private Long rohaId;
	private Long RolId;
	private Long habiId;
	
	

	/**
	 * @return
	 */
	public Long getHabiId() {
		return habiId;
	}

	/**
	 * @return
	 */
	public Long getRohaId() {
		return rohaId;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return RolId;
	}

	/**
	 * @param long1
	 */
	public void setHabiId(Long long1) {
		habiId = long1;
	}

	/**
	 * @param long1
	 */
	public void setRohaId(Long long1) {
		rohaId = long1;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		RolId = long1;
	}

}
