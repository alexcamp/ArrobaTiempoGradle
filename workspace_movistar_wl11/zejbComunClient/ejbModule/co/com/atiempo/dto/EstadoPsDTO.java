/*
 * Created on 15-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class EstadoPsDTO {

	public EstadoPsDTO() {
		super();
	}
	
	private Long codEstadoCierre;
	private String descripcionEstadoCierre;
	
	

	/**
	 * @return
	 */
	public Long getCodEstadoCierre() {
		return codEstadoCierre;
	}

	/**
	 * @return
	 */
	public String getDescripcionEstadoCierre() {
		return descripcionEstadoCierre;
	}

	/**
	 * @param long1
	 */
	public void setCodEstadoCierre(Long long1) {
		codEstadoCierre = long1;
	}

	/**
	 * @param string
	 */
	public void setDescripcionEstadoCierre(String string) {
		descripcionEstadoCierre = string;
	}

}
