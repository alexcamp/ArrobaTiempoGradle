/*
 * Created on 16-05-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TematicoDTO {

	private Long idTematico;
	private String codTematico;
	private Long codAveCd;
	private String origen;
	private Long correlativo;
	
	/**
	 * @return
	 */
	public Long getCodAveCd() {
		return codAveCd;
	}

	/**
	 * @return
	 */
	public String getCodTematico() {
		return codTematico;
	}

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public Long getIdTematico() {
		return idTematico;
	}

	/**
	 * @return
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param long1
	 */
	public void setCodAveCd(Long long1) {
		codAveCd = long1;
	}

	/**
	 * @param string
	 */
	public void setCodTematico(String string) {
		codTematico = string;
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
	public void setIdTematico(Long long1) {
		idTematico = long1;
	}

	/**
	 * @param string
	 */
	public void setOrigen(String string) {
		origen = string;
	}

}
