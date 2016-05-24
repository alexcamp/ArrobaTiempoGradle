/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class FlujoDTO {

	/**
	 * 
	 */
	public FlujoDTO() {
		super();
	}

	private Integer flujId;
	private String flujCodigo;
	private String flujDescripcion;
	
	
	/**
	 * @return
	 */
	public String getFlujCodigo() {
		return flujCodigo;
	}

	/**
	 * @return
	 */
	public String getFlujDescripcion() {
		return flujDescripcion;
	}

	/**
	 * @return
	 */
	public Integer getFlujId() {
		return flujId;
	}

	/**
	 * @param string
	 */
	public void setFlujCodigo(String string) {
		flujCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setFlujDescripcion(String string) {
		flujDescripcion = string;
	}

	/**
	 * @param integer
	 */
	public void setFlujId(Integer integer) {
		flujId = integer;
	}

}
