/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class PerfilDTO {

	/**
	 * 
	 */
	public PerfilDTO() {
		super();
	}
	
	private Long perfId;
	private String perfDescripcion;
	
	
	/**
	 * @return
	 */
	public String getPerfDescripcion() {
		return perfDescripcion;
	}

	/**
	 * @return
	 */
	public Long getPerfId() {
		return perfId;
	}

	/**
	 * @param string
	 */
	public void setPerfDescripcion(String string) {
		perfDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setPerfId(Long long1) {
		perfId = long1;
	}

}
