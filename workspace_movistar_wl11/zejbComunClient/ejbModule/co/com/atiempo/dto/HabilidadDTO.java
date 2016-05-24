/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class HabilidadDTO {

	/**
	 * 
	 */
	public HabilidadDTO() {
		super();
	}

	private Long habiId;
	private String habiNombre;
	
	

	/**
	 * @return
	 */
	public Long getHabiId() {
		return habiId;
	}

	/**
	 * @return
	 */
	public String getHabiNombre() {
		return habiNombre;
	}

	/**
	 * @param long1
	 */
	public void setHabiId(Long long1) {
		habiId = long1;
	}

	/**
	 * @param string
	 */
	public void setHabiNombre(String string) {
		habiNombre = string;
	}

}
