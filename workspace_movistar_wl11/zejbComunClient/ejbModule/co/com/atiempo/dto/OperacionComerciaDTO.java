/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class OperacionComerciaDTO {

	/**
	 * 
	 */
	public OperacionComerciaDTO() {
		super();
	}

	private Long opcoId;
	private Integer titrId;
	private String opcoNombre;
	private String opcoTipo;

	

	/**
	 * @return
	 */
	public Long getOpcoId() {
		return opcoId;
	}
	
	/**
	 * @return
	 */
	public String getOpcoNombre() {
		return opcoNombre;
	}
	
	/**
	 * @return
	 */
	public String getOpcoTipo() {
		return opcoTipo;
	}
	
	/**
	 * @return
	 */
	public Integer getTitrId() {
		return titrId;
	}
	
	/**
	 * @param long1
	 */
	public void setOpcoId(Long long1) {
		opcoId = long1;
	}
	
	/**
	 * @param string
	 */
	public void setOpcoNombre(String string) {
		opcoNombre = string;
	}
	
	/**
	 * @param string
	 */
	public void setOpcoTipo(String string) {
		opcoTipo = string;
	}
	
	/**
	 * @param integer
	 */
	public void setTitrId(Integer integer) {
		titrId = integer;
	}

}
