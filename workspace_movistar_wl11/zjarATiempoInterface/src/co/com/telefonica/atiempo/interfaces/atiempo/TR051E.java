/*
 * Created on Jan 12, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR051E extends RequestHeaderAgendaSC{
	
	private String accountExpiration;
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String country;
	private String state;
	private String zip;
	private Long telephone;
	private String comercialOperation;
	private String productService;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR051E){
			TR051E other = (TR051E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(accountExpiration, other.accountExpiration)
				&& EqualityUtilities.equals(address, other.address)
				&& EqualityUtilities.equals(city, other.city)
				&& EqualityUtilities.equals(comercialOperation, other.comercialOperation)
				&& EqualityUtilities.equals(country, other.country)
				&& EqualityUtilities.equals(email, other.email)
				&& EqualityUtilities.equals(firstname, other.firstname)
				&& EqualityUtilities.equals(lastname, other.lastname)
				&& EqualityUtilities.equals(password, other.password)
				&& EqualityUtilities.equals(productService, other.productService)
				&& EqualityUtilities.equals(state, other.state)
				&& EqualityUtilities.equals(telephone, other.state)
				&& EqualityUtilities.equals(username, other.username)
				&& EqualityUtilities.equals(zip, other.zip);
		}else{
			return false;
		}
	}
	/**
	 * @return Returns the accountExpiration.
	 */
	public String getAccountExpiration() {
		return accountExpiration;
	}
	/**
	 * @param accountExpiration The accountExpiration to set.
	 */
	public void setAccountExpiration(String accountExpiration) {
		this.accountExpiration = accountExpiration;
	}
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the comercialOperation.
	 */
	public String getComercialOperation() {
		return comercialOperation;
	}
	/**
	 * @param comercialOperation The comercialOperation to set.
	 */
	public void setComercialOperation(String comercialOperation) {
		this.comercialOperation = comercialOperation;
	}
	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the firstname.
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname The firstname to set.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return Returns the lastname.
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname The lastname to set.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the productService.
	 */
	public String getProductService() {
		return productService;
	}
	/**
	 * @param productService The productService to set.
	 */
	public void setProductService(String productService) {
		this.productService = productService;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the telephone.
	 */
	public Long getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone The telephone to set.
	 */
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
