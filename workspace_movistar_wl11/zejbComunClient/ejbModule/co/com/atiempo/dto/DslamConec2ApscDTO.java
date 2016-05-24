/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class DslamConec2ApscDTO {

	/**
	 * 
	 */
	public DslamConec2ApscDTO() {
		super();
	}

	private Long idDslam;
	private Long idConector;
	private String tipoConector;
	private String ip;
	


	/**
	 * @return
	 */
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public Long getIdDslam() {
		return idDslam;
	}

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return
	 */
	public String getTipoConector() {
		return tipoConector;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdDslam(Long long1) {
		idDslam = long1;
	}

	/**
	 * @param string
	 */
	public void setIp(String string) {
		ip = string;
	}

	/**
	 * @param string
	 */
	public void setTipoConector(String string) {
		tipoConector = string;
	}

}
