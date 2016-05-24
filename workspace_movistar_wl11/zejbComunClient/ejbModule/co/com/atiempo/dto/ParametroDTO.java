/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ParametroDTO {

	/**
	 * 
	 */
	public ParametroDTO() {
		super();
	}

	private Long paraId;
	private String paraNombre;
	private String paraValor;
	
	
	/**
	 * @return
	 */
	public Long getParaId() {
		return paraId;
	}
	
	/**
	 * @return
	 */
	public String getParaNombre() {
		return paraNombre;
	}
	
	/**
	 * @return
	 */
	public String getParaValor() {
		return paraValor;
	}
	
	/**
	 * @param long1
	 */
	public void setParaId(Long long1) {
		paraId = long1;
	}
	
	/**
	 * @param string
	 */
	public void setParaNombre(String string) {
		paraNombre = string;
	}
	
	/**
	 * @param string
	 */
	public void setParaValor(String string) {
		paraValor = string;
	}

}
