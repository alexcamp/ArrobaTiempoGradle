/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ServicioBasicoSuplConec6ApscLineaDTO {

	/**
	 * 
	 */
	public ServicioBasicoSuplConec6ApscLineaDTO() {
		super();
	}
	
	private Long idServicioBasico;
	private Long idConector;
	private String codigoPs;
	
	
	/**
	 * @return
	 */
	public String getCodigoPs() {
		return codigoPs;
	}

	/**
	 * @return
	 */
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public Long getIdServicioBasico() {
		return idServicioBasico;
	}

	/**
	 * @param string
	 */
	public void setCodigoPs(String string) {
		codigoPs = string;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdServicioBasico(Long long1) {
		idServicioBasico = long1;
	}

}
