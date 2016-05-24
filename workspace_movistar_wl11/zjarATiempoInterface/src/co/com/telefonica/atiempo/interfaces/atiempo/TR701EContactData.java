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
public class TR701EContactData implements Serializable {
	private String contactName;
	private String contactMedia;
	private String contactPhone;
	private String contactEmail;
	private String contactCellPhone;
	private String datosAgendamiento;
	
	public int hashCode() {
		return contactPhone.hashCode() + contactCellPhone.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EContactData) {
			TR701EContactData other = (TR701EContactData) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(contactName, other.contactName)
				&& EqualityUtilities.equals(contactMedia, other.contactMedia)
				&& EqualityUtilities.equals(contactPhone, other.contactPhone)
				&& EqualityUtilities.equals(contactEmail, other.contactEmail)
				&& EqualityUtilities.equals(contactCellPhone, other.contactCellPhone);
			}
		return false;
	}
	
	public String getContactCellPhone() {
		return contactCellPhone;
	}
	public void setContactCellPhone(String contactCellPhone) {
		this.contactCellPhone = contactCellPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactMedia() {
		return contactMedia;
	}
	public void setContactMedia(String contactMedia) {
		this.contactMedia = contactMedia;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getDatosAgendamiento() {
		return datosAgendamiento;
	}
	public void setDatosAgendamiento(String datosAgendamiento) {
		this.datosAgendamiento = datosAgendamiento;
	}
}
