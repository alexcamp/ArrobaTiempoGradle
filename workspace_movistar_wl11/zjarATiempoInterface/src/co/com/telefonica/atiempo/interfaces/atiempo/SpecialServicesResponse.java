//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SpecialServicesResponse.java,v 1.1 2011/03/30 18:21:05 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class SpecialServicesResponse extends SpecialService {
	
	private int actionResultNumber;
	private String actionResultDescription;

	public int hashCode() {
		return actionResultNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof SpecialServicesResponse) {
			SpecialServicesResponse other = (SpecialServicesResponse) arg0;
			return super.equals(arg0) &&
				(actionResultNumber == other.actionResultNumber) &&
				EqualityUtilities.equals(actionResultDescription,other.actionResultDescription)
				
				;

		}
		return false;
	}


	/**
	 * @return
	 */
	public String getActionResultDescription() {
		return actionResultDescription;
	}

	/**
	 * @return
	 */
	public int getActionResultNumber() {
		return actionResultNumber;
	}

	/**
	 * @param string
	 */
	public void setActionResultDescription(String string) {
		actionResultDescription = string;
	}

	/**
	 * @param i
	 */
	public void setActionResultNumber(int i) {
		actionResultNumber = i;
	}

}
