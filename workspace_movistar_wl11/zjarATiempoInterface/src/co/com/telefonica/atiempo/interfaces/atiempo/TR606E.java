//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR0606E.java 06/11/2014 14:46:28 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;


/**
* @author dcardena
*
*/
public class TR606E extends RequestHeader {
	String phoneNumber;
	String action;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR606E) {
			TR606E other = (TR606E) arg0;
			return super.equals(arg0)
			&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
			&& EqualityUtilities.equals(action, other.action);
			
			}
		return false;
	}
	
	/**
	 * @return Devuelve action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action El action a establecer.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Devuelve phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber El phoneNumber a establecer.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
