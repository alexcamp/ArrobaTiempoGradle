/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class RolDTO {

	/**
	 * 
	 */
	public RolDTO() {
		super();
	}

	private Long rolId;
	private Integer idTipoRelacion;
	private Long IspId;
	private String rolNombre;
	private String rolVeSabana;
	private String rolCodigo;
	private Long pushAppId;
	private Long ApId;
	


	/**
	 * @return
	 */
	public Long getApId() {
		return ApId;
	}

	/**
	 * @return
	 */
	public Integer getIdTipoRelacion() {
		return idTipoRelacion;
	}

	/**
	 * @return
	 */
	public Long getIspId() {
		return IspId;
	}

	/**
	 * @return
	 */
	public Long getPushAppId() {
		return pushAppId;
	}

	/**
	 * @return
	 */
	public String getRolCodigo() {
		return rolCodigo;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public String getRolNombre() {
		return rolNombre;
	}

	/**
	 * @return
	 */
	public String getRolVeSabana() {
		return rolVeSabana;
	}

	/**
	 * @param long1
	 */
	public void setApId(Long long1) {
		ApId = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdTipoRelacion(Integer integer) {
		idTipoRelacion = integer;
	}

	/**
	 * @param long1
	 */
	public void setIspId(Long long1) {
		IspId = long1;
	}

	/**
	 * @param long1
	 */
	public void setPushAppId(Long long1) {
		pushAppId = long1;
	}

	/**
	 * @param string
	 */
	public void setRolCodigo(String string) {
		rolCodigo = string;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

	/**
	 * @param string
	 */
	public void setRolNombre(String string) {
		rolNombre = string;
	}

	/**
	 * @param string
	 */
	public void setRolVeSabana(String string) {
		rolVeSabana = string;
	}

}
