/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class UsuarioRolDTO {

	/**
	 * 
	 */
	public UsuarioRolDTO() {
		super();
	}
	
	private Long usuaId;
	private Long rolId;
	private int usroHabilitado;
	private Long usuaIdSup;
	private String rolSupervisor;
	
	
	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @return
	 */
	public String getRolSupervisor() {
		return rolSupervisor;
	}

	/**
	 * @return
	 */
	public int getUsroHabilitado() {
		return usroHabilitado;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @return
	 */
	public Long getUsuaIdSup() {
		return usuaIdSup;
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
	public void setRolSupervisor(String string) {
		rolSupervisor = string;
	}

	/**
	 * @param i
	 */
	public void setUsroHabilitado(int i) {
		usroHabilitado = i;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}

	/**
	 * @param long1
	 */
	public void setUsuaIdSup(Long long1) {
		usuaIdSup = long1;
	}

}
