package co.com.telefonica.acs;


import java.io.Serializable;

public class ParametrosMotiveDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String urlWebService;
	private String userWebService;
	private String passwdWebService;
	private String certifacadeWebService;
	private String protocolModem;
	private String securityModem;
	private String nameWifi;
	private String passwordModem;
	
	
	
	
	public String getPasswordModem() {
		return passwordModem;
	}
	public void setPasswordModem(String passwordModem) {
		this.passwordModem = passwordModem;
	}
	
	public String getCertifacadeWebService() {
		return certifacadeWebService;
	}
	public void setCertifacadeWebService(String certifacadeWebService) {
		this.certifacadeWebService = certifacadeWebService;
	}
	public String getNameWifi() {
		return nameWifi;
	}
	public void setNameWifi(String nameWifi) {
		this.nameWifi = nameWifi;
	}
	public String getPasswdWebService() {
		return passwdWebService;
	}
	public void setPasswdWebService(String passwdWebService) {
		this.passwdWebService = passwdWebService;
	}
	public String getProtocolModem() {
		return protocolModem;
	}
	public void setProtocolModem(String protocolModem) {
		this.protocolModem = protocolModem;
	}
	public String getSecurityModem() {
		return securityModem;
	}
	public void setSecurityModem(String securityModem) {
		this.securityModem = securityModem;
	}
	public String getUrlWebService() {
		return urlWebService;
	}
	public void setUrlWebService(String urlWebService) {
		this.urlWebService = urlWebService;
	}
	public String getUserWebService() {
		return userWebService;
	}
	public void setUserWebService(String userWebService) {
		this.userWebService = userWebService;
	}

}
