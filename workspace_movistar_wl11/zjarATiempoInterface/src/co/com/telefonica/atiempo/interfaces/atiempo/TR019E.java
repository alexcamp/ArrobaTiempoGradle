//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR019E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR019E extends RequestHeader{
	private long atisRequestNumber;
	private String pcId;
	 
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getPcId() {
		return pcId;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setPcId(String string) {
		pcId = string;
	}
	public int hashCode(){
		return pcId.hashCode();
	}
	public boolean equals(Object arg0){
		if (arg0 instanceof TR019E) {
			TR019E other = (TR019E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(pcId, other.pcId);
		}
		return false;
	}

}
