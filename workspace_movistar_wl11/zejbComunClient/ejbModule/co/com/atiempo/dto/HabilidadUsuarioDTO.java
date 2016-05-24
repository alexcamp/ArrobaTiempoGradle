/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class HabilidadUsuarioDTO {

	/**
	 * 
	 */
	public HabilidadUsuarioDTO() {
		super();
	}

	private Long husuId;
	private Long usuaId;
	private Long habiId;
	private String hUsuValor;



	/**
	 * @return
	 */
	public Long getHabiId() {
		return habiId;
	}

	/**
	 * @return
	 */
	public Long getHusuId() {
		return husuId;
	}

	/**
	 * @return
	 */
	public String getHUsuValor() {
		return hUsuValor;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @param long1
	 */
	public void setHabiId(Long long1) {
		habiId = long1;
	}

	/**
	 * @param long1
	 */
	public void setHusuId(Long long1) {
		husuId = long1;
	}

	/**
	 * @param string
	 */
	public void setHUsuValor(String string) {
		hUsuValor = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

}
