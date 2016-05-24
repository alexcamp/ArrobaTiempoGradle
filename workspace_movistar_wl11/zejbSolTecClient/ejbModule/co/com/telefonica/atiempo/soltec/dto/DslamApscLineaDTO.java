/*
 * Created on 26-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author rodrigo
 */
public class DslamApscLineaDTO {
	
	Long 	idDslams;
	Long 	idConector;
	String 	ip;
	String 	tipoDslam;
	
	

	/**
	 * @return
	 */
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public Long getIdDslams() {
		return idDslams;
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
	public String getTipoDslam() {
		return tipoDslam;
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
	public void setIdDslams(Long long1) {
		idDslams = long1;
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
	public void setTipoDslam(String string) {
		tipoDslam = string;
	}

}
