//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR027E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR027E extends RequestHeader{

	private long atiempoRequestNumber;
	private long atisRequestNumber;
	private String pcId;	
	private long productServiceCode;
	

	public int hashCode() {
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR027E) {
			TR027E other = (TR027E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(pcId,other.pcId)
				&& atiempoRequestNumber == other.atiempoRequestNumber
				&& atisRequestNumber == other.atisRequestNumber
				&& productServiceCode == other.productServiceCode
								
				;
			}
		return false;
	}

	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public long getProductServiceCode() {
		return productServiceCode;
	}
	public void setProductServiceCode(long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
}
