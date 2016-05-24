/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class AplicacionDTO {

	/**
	 * 
	 */
	public AplicacionDTO() {
		super();
	}

	private Long apId;
	private String apNombre;
	private String apUrlReasignacion;
	private String apUrlBase;
	private String apUrlSupervisor;
	
	

	/**
	 * @return
	 */
	public Long getApId() {
		return apId;
	}

	/**
	 * @return
	 */
	public String getApNombre() {
		return apNombre;
	}

	/**
	 * @return
	 */
	public String getApUrlBase() {
		return apUrlBase;
	}

	/**
	 * @return
	 */
	public String getApUrlReasignacion() {
		return apUrlReasignacion;
	}

	/**
	 * @return
	 */
	public String getApUrlSupervisor() {
		return apUrlSupervisor;
	}

	/**
	 * @param long1
	 */
	public void setApId(Long long1) {
		apId = long1;
	}

	/**
	 * @param string
	 */
	public void setApNombre(String string) {
		apNombre = string;
	}

	/**
	 * @param string
	 */
	public void setApUrlBase(String string) {
		apUrlBase = string;
	}

	/**
	 * @param string
	 */
	public void setApUrlReasignacion(String string) {
		apUrlReasignacion = string;
	}

	/**
	 * @param string
	 */
	public void setApUrlSupervisor(String string) {
		apUrlSupervisor = string;
	}

}
