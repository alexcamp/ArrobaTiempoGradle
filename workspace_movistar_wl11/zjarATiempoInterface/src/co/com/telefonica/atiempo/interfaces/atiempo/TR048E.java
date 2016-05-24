/*
 * Creado el 19/11/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR048E extends RequestHeaderAgendaSC{

	private String atisRequestNumber;
	private String username;
	private String password;
	private String email;
	private String comercialOperation;
	private String productService;
	private int phoneNumber;
	private String segment;
	private String subSegment;
	
	public int hashCode(){
		return  super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR048E) {
				TR048E other = (TR048E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
				 	&& EqualityUtilities.equals(username, other.username)
					&& EqualityUtilities.equals(password, other.password)
					&& EqualityUtilities.equals(email, other.email)
					&& EqualityUtilities.equals(comercialOperation, other.comercialOperation);
			}
			return false;
		}
	

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Devuelve comercialOperation.
	 */
	public String getComercialOperation() {
		return comercialOperation;
	}
	/**
	 * @param comercialOperation El comercialOperation a establecer.
	 */
	public void setComercialOperation(String comercialOperation) {
		this.comercialOperation = comercialOperation;
	}
	/**
	 * @return Devuelve email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Devuelve password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password El password a establecer.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Devuelve productService.
	 */
	public String getProductService() {
		return productService;
	}
	/**
	 * @param productService El productService a establecer.
	 */
	public void setProductService(String productService) {
		this.productService = productService;
	}
	/**
	 * @return Devuelve username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username El username a establecer.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * @return Returns the segmento.
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * @param segmento The segmento to set.
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}
	/**
	 * @return Returns the subSegmento.
	 */
	public String getSubSegment() {
		return subSegment;
	}
	/**
	 * @param subSegmento The subSegmento to set.
	 */
	public void setSubSegment(String subSegment) {
		this.subSegment= subSegment;
	}
	
	
	/**
	 * @return Returns the phoneNumber.
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
