/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CausaDTO {

	/**
	 * 
	 */
	public CausaDTO() {
		super();
	}

	private int causId;
	private String causCodigo;
	private String causNombre;
	private String causCodigoIvr;
	


	/**
	 * @return
	 */
	public String getCausCodigo() {
		return causCodigo;
	}

	/**
	 * @return
	 */
	public String getCausCodigoIvr() {
		return causCodigoIvr;
	}

	/**
	 * @return
	 */
	public int getCausId() {
		return causId;
	}

	/**
	 * @return
	 */
	public String getCausNombre() {
		return causNombre;
	}

	/**
	 * @param string
	 */
	public void setCausCodigo(String string) {
		causCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setCausCodigoIvr(String string) {
		causCodigoIvr = string;
	}

	/**
	 * @param i
	 */
	public void setCausId(int i) {
		causId = i;
	}

	/**
	 * @param string
	 */
	public void setCausNombre(String string) {
		causNombre = string;
	}

}
