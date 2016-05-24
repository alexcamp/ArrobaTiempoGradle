package com.tecnonautica.seguridad;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Usuario {
	private String login;
	private String clave;
	
	public Usuario(String login,String clave){
		this.login=login;
		this.clave=clave;
	}
	
	/**
	 * Returns the clave.
	 * @return String
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Returns the login.
	 * @return String
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the clave.
	 * @param clave The clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Sets the login.
	 * @param login The login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
