/*
 * Created on Nov 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR047E extends RequestHeaderAgendaSC {
	private String action;
	private String user;
	private String password;
	private String productType;
	private String phone;
	private String name;
	private String mail;
	private String activationDate;
	private String address;
	private String state;
	private String city;
	private String userId;
	private String messageId;
	private String segment;
	private String activationUser;
	private String unregisterDate;
	private String newPhone;
	
	public int hashCode() {
		return phone.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR047E) {
			TR047E other = (TR047E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(action,other.action)
				&& EqualityUtilities.equals(user,other.user)
				&& EqualityUtilities.equals(password,other.password)
				&& EqualityUtilities.equals(productType,other.productType)
				&& EqualityUtilities.equals(phone,other.phone)
				&& EqualityUtilities.equals(name,other.name)
				&& EqualityUtilities.equals(mail,other.mail)
				&& EqualityUtilities.equals(activationDate,other.activationDate)
				&& EqualityUtilities.equals(address,other.address)
				&& EqualityUtilities.equals(state,other.state)
				&& EqualityUtilities.equals(city,other.city)
				&& EqualityUtilities.equals(userId,other.userId)
				&& EqualityUtilities.equals(messageId,other.messageId)
				&& EqualityUtilities.equals(segment,other.segment)
				&& EqualityUtilities.equals(activationUser,other.activationUser)
				&& EqualityUtilities.equals(unregisterDate,other.unregisterDate)
				&& EqualityUtilities.equals(newPhone,other.newPhone);
		}
		return false;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public String getActivationUser() {
		return activationUser;
	}
	public void setActivationUser(String activationUser) {
		this.activationUser = activationUser;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUnregisterDate() {
		return unregisterDate;
	}
	public void setUnregisterDate(String unregisterDate) {
		this.unregisterDate = unregisterDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
