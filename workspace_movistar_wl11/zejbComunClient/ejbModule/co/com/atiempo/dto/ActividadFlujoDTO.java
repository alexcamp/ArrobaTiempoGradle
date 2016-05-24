/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ActividadFlujoDTO {

	/**
	 * 
	 */
	public ActividadFlujoDTO() {
		super();
	}
	
	private Integer actiId;
	private String actiCodigo;
	private String actiDescripcion;
	private Integer actiOrden;
	
	

	/**
	 * @return
	 */
	public String getActiCodigo() {
		return actiCodigo;
	}

	/**
	 * @return
	 */
	public String getActiDescripcion() {
		return actiDescripcion;
	}

	/**
	 * @return
	 */
	public Integer getActiId() {
		return actiId;
	}

	/**
	 * @return
	 */
	public Integer getActiOrden() {
		return actiOrden;
	}

	/**
	 * @param string
	 */
	public void setActiCodigo(String string) {
		actiCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setActiDescripcion(String string) {
		actiDescripcion = string;
	}

	/**
	 * @param integer
	 */
	public void setActiId(Integer integer) {
		actiId = integer;
	}

	/**
	 * @param integer
	 */
	public void setActiOrden(Integer integer) {
		actiOrden = integer;
	}

}
