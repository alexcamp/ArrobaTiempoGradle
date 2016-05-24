/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR701ECustomer implements Serializable {
	private Long code;
	private String idType;
	private String id;
	private String name;
	private String type;
	private String subSeg;
	private Long phoneNumber;
	private String email;
	
	public int hashCode() {
		return code.intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701ECustomer) {
			TR701ECustomer other = (TR701ECustomer) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(code, other.code)
				&& EqualityUtilities.equals(idType, other.idType)
				&& EqualityUtilities.equals(id, other.id)
				&& EqualityUtilities.equals(name, other.name)
				&& EqualityUtilities.equals(type, other.type)
				&& EqualityUtilities.equals(subSeg, other.subSeg)
				&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
				&& EqualityUtilities.equals(email, other.email);
			}
		return false;
	}

	
	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the idType.
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType The idType to set.
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the subSeg.
	 */
	public String getSubSeg() {
		return subSeg;
	}
	/**
	 * @param subSeg The subSeg to set.
	 */
	public void setSubSeg(String subSeg) {
		this.subSeg = subSeg;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
