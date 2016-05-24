/*
 * Created on Aug 26, 2010
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
public class TR708EEquipment implements Serializable {
	private String decoCode;
	private String decoSerialNumber;
	private String cardCode;
	private String cardSerialNumber;
	private String descripcionError;
	
	public int hashCode() {
		return decoSerialNumber.hashCode() + cardSerialNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR708EEquipment) {
			TR708EEquipment other = (TR708EEquipment) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(decoCode, other.decoCode)
				&& EqualityUtilities.equals(decoSerialNumber, other.decoSerialNumber)
				&& EqualityUtilities.equals(cardCode, other.cardCode)
				&& EqualityUtilities.equals(cardSerialNumber, other.cardSerialNumber)
				&& EqualityUtilities.equals(descripcionError, other.descripcionError);
			}
		return false;
	}
	
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardSerialNumber() {
		return cardSerialNumber;
	}
	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}
	public String getDecoCode() {
		return decoCode;
	}
	public void setDecoCode(String decoCode) {
		this.decoCode = decoCode;
	}
	public String getDecoSerialNumber() {
		return decoSerialNumber;
	}
	public void setDecoSerialNumber(String decoSerialNumber) {
		this.decoSerialNumber = decoSerialNumber;
	}
	public String getDescripcionError() {
		return descripcionError;
	}
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
}
