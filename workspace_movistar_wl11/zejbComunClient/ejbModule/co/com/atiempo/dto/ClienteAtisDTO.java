/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ClienteAtisDTO {

	/**
	 * 
	 */
	public ClienteAtisDTO() {
		super();
	}

	private Long clieAtisId;
	private String rutCliente;
	private String digVerifRutCliente;
	private String nomCliente;
	private String apeCliente;
	private String ape2Cliente;
	private Long msxCodSgmCliCd;
	private Long msxCodSbgCliCd;
	private String msxTipDocCd;
	private Long nroPeticion;
	
	
	

	/**
	 * @return
	 */
	public String getApe2Cliente() {
		return ape2Cliente;
	}

	/**
	 * @return
	 */
	public String getApeCliente() {
		return apeCliente;
	}

	/**
	 * @return
	 */
	public Long getClieAtisId() {
		return clieAtisId;
	}

	/**
	 * @return
	 */
	public String getDigVerifRutCliente() {
		return digVerifRutCliente;
	}

	/**
	 * @return
	 */
	public Long getMsxCodSbgCliCd() {
		return msxCodSbgCliCd;
	}

	/**
	 * @return
	 */
	public Long getMsxCodSgmCliCd() {
		return msxCodSgmCliCd;
	}

	/**
	 * @return
	 */
	public String getMsxTipDocCd() {
		return msxTipDocCd;
	}

	/**
	 * @return
	 */
	public String getNomCliente() {
		return nomCliente;
	}

	/**
	 * @return
	 */
	public Long getNroPeticion() {
		return nroPeticion;
	}

	/**
	 * @return
	 */
	public String getRutCliente() {
		return rutCliente;
	}

	/**
	 * @param string
	 */
	public void setApe2Cliente(String string) {
		ape2Cliente = string;
	}

	/**
	 * @param string
	 */
	public void setApeCliente(String string) {
		apeCliente = string;
	}

	/**
	 * @param long1
	 */
	public void setClieAtisId(Long long1) {
		clieAtisId = long1;
	}

	/**
	 * @param string
	 */
	public void setDigVerifRutCliente(String string) {
		digVerifRutCliente = string;
	}

	/**
	 * @param long1
	 */
	public void setMsxCodSbgCliCd(Long long1) {
		msxCodSbgCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setMsxCodSgmCliCd(Long long1) {
		msxCodSgmCliCd = long1;
	}

	/**
	 * @param string
	 */
	public void setMsxTipDocCd(String string) {
		msxTipDocCd = string;
	}

	/**
	 * @param string
	 */
	public void setNomCliente(String string) {
		nomCliente = string;
	}

	/**
	 * @param long1
	 */
	public void setNroPeticion(Long long1) {
		nroPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setRutCliente(String string) {
		rutCliente = string;
	}

}
