/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class MensajeEstadoDTO {

	/**
	 * 
	 */
	public MensajeEstadoDTO() {
		super();
	}

	private Integer codEstado;
	private String nombreEstado;
	private String descripcion;
	
	

	/**
	 * @return
	 */
	public Integer getCodEstado() {
		return codEstado;
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
	public String getNombreEstado() {
		return nombreEstado;
	}

	/**
	 * @param integer
	 */
	public void setCodEstado(Integer integer) {
		codEstado = integer;
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
	public void setNombreEstado(String string) {
		nombreEstado = string;
	}

}
