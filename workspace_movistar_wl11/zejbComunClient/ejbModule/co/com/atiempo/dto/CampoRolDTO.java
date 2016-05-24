/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CampoRolDTO {

	/**
	 * 
	 */
	public CampoRolDTO() {
		super();
	}
	
	private int cvId;
	private Long rolId;
	
	/**
	 * @return
	 */
	public int getCvId() {
		return cvId;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
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
	public void setRolId(Long long1) {
		rolId = long1;
	}

}
