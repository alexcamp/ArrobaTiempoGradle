/*
 * Created on 22-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author rodrigo
 */
public class CodigoStDto {
	
	String codigo;
	Integer tipo;
	String descripcion;
	Long correlativo;
	
	

	/**
	 * @return
	 */
	public String getCodigo() {
		return codigo;
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
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param integer
	 */
	public void setTipo(Integer integer) {
		tipo = integer;
	}

}
