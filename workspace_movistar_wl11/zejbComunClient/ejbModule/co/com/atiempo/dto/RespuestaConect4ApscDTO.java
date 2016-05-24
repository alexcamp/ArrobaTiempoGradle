/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class RespuestaConect4ApscDTO {

	/**
	 * 
	 */
	public RespuestaConect4ApscDTO() {
		super();
	}
	

	private Long correlativo;
	private Long idConector;
	private String indError;
	private String mensajeError;
	private Long telefonoAsg;
	private Long petiNumero;
	
	

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
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
	public String getIndError() {
		return indError;
	}

	/**
	 * @return
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public Long getTelefonoAsg() {
		return telefonoAsg;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param string
	 */
	public void setIndError(String string) {
		indError = string;
	}

	/**
	 * @param string
	 */
	public void setMensajeError(String string) {
		mensajeError = string;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param long1
	 */
	public void setTelefonoAsg(Long long1) {
		telefonoAsg = long1;
	}

}
