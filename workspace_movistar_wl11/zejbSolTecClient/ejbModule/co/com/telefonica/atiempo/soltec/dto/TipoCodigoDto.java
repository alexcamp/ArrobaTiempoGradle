/*
 * Created on 22-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author rodrigo
 */
public class TipoCodigoDto {
	
	Integer tipo;
	String descripcion;
	String atributo;
	
	

	/**
	 * @return
	 */
	public String getAtributo() {
		return atributo;
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
	public void setAtributo(String string) {
		atributo = string;
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
