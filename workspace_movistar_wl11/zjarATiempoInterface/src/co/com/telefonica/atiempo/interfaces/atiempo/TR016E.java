//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR016E.java,v 1.1 2011/03/30 18:24:02 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR016E extends RequestHeader {
	
	private long atisRequestNumber;
	private long contractorId;
	private String decoFinalDigitsSerial;
	private String decoReference;
	private String cardFinalDigits;
	
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public String getCardFinalDigits() {
		return cardFinalDigits;
	}
	public long getContractorId() {
		return contractorId;
	}
	public String getDecoFinalDigitsSerial() {
		return decoFinalDigitsSerial;
	}
	public String getDecoReference() {
			return decoReference;
	}
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}
	public void setCardFinalDigits(String cardFinalDigits) {
		this.cardFinalDigits = cardFinalDigits;
	}
	public void setContractorId(long l) {
		contractorId = l;
	}
	public void setDecoFinalDigitsSerial(String decoFinalDigitsSerial) {
		this.decoFinalDigitsSerial = decoFinalDigitsSerial;
	}
	public void setDecoReference(String decoReference) {
			this.decoReference = decoReference;
	}
	
	public int hashCode(){
		return (int) contractorId;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR016E) {
			TR016E other = (TR016E) arg0;
			return super.equals(arg0) &&
				atisRequestNumber == other.atisRequestNumber &&
				contractorId== other.contractorId &&
				EqualityUtilities.equals(decoReference, other.decoReference) &&
				EqualityUtilities.equals(decoFinalDigitsSerial, other.decoFinalDigitsSerial) &&
				EqualityUtilities.equals(cardFinalDigits, other.cardFinalDigits);
		}
		
		return false;
	}
	

	

}
