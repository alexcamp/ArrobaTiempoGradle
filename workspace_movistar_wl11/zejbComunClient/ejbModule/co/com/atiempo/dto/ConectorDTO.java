/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ConectorDTO {

	/**
	 * 
	 */
	public ConectorDTO() {
		super();
	}

	private Integer codConector;
	private String NombreConector;
	private String descripcion;
	
	

	/**
	 * @return
	 */
	public Integer getCodConector() {
		return codConector;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public String getNombreConector() {
		return NombreConector;
	}

	/**
	 * @param integer
	 */
	public void setCodConector(Integer integer) {
		codConector = integer;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param string
	 */
	public void setNombreConector(String string) {
		NombreConector = string;
	}

}
