/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class MenuDTO {

	/**
	 * 
	 */
	public MenuDTO() {
		super();
	}

	private Long mnId;
	private String mnDescripcion;
	private String mnUrl;
	private Long mnIdPadre;
	private Integer mnOrden;
	
	
	/**
	 * @return
	 */
	public String getMnDescripcion() {
		return mnDescripcion;
	}

	/**
	 * @return
	 */
	public Long getMnId() {
		return mnId;
	}

	/**
	 * @return
	 */
	public Long getMnIdPadre() {
		return mnIdPadre;
	}

	/**
	 * @return
	 */
	public Integer getMnOrden() {
		return mnOrden;
	}

	/**
	 * @return
	 */
	public String getMnUrl() {
		return mnUrl;
	}

	/**
	 * @param string
	 */
	public void setMnDescripcion(String string) {
		mnDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setMnId(Long long1) {
		mnId = long1;
	}

	/**
	 * @param long1
	 */
	public void setMnIdPadre(Long long1) {
		mnIdPadre = long1;
	}

	/**
	 * @param integer
	 */
	public void setMnOrden(Integer integer) {
		mnOrden = integer;
	}

	/**
	 * @param string
	 */
	public void setMnUrl(String string) {
		mnUrl = string;
	}

}
