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
public class TR801SEquipment implements Serializable {
	
	private String decoCode;
	private String decoBrand;
	private String decoType;
	private String decoSerialNumber;
	private String decoCassId;
	private String cardCode;
	private String cardSerialNumber;

	
	public int hashCode() {
		return decoCode.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR801SEquipment) {
			TR801SEquipment other = (TR801SEquipment) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(decoCode, other.decoCode)
				&& EqualityUtilities.equals(decoBrand, other.decoBrand)
				&& EqualityUtilities.equals(decoType, other.decoType)
				&& EqualityUtilities.equals(decoSerialNumber, other.decoSerialNumber)
				&& EqualityUtilities.equals(decoCassId, other.decoCassId)
				&& EqualityUtilities.equals(cardCode, other.cardCode)
				&& EqualityUtilities.equals(cardSerialNumber, other.cardSerialNumber);
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
	public String getDecoBrand() {
		return decoBrand;
	}
	public void setDecoBrand(String decoBrand) {
		this.decoBrand = decoBrand;
	}
	public String getDecoCassId() {
		return decoCassId;
	}
	public void setDecoCassId(String decoCassId) {
		this.decoCassId = decoCassId;
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
	public String getDecoType() {
		return decoType;
	}
	public void setDecoType(String decoType) {
		this.decoType = decoType;
	}

}
