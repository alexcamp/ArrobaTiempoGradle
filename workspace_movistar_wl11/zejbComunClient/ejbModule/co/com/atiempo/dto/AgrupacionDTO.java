/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class AgrupacionDTO {

	/**
	 * 
	 */
	public AgrupacionDTO() {
		super();
	}

	private Long agId;
	private Long agIdPadre;
	private String agNombre;
	
	

	/**
	 * @return
	 */
	public Long getAgId() {
		return agId;
	}

	/**
	 * @return
	 */
	public Long getAgIdPadre() {
		return agIdPadre;
	}

	/**
	 * @return
	 */
	public String getAgNombre() {
		return agNombre;
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
	public void setAgIdPadre(Long long1) {
		agIdPadre = long1;
	}

	/**
	 * @param string
	 */
	public void setAgNombre(String string) {
		agNombre = string;
	}

}
