//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR034S.java,v 1.1 2011/03/30 18:25:35 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR034S extends ResponseHeader{
	
	private long atiempoRequestNumber;
	private String code;
	private String description;
	private String action;
	

	public void setAction(String action) {
		this.action = action;
	}
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR034S) {
			TR034S other = (TR034S) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(code, other.code)
				&& EqualityUtilities.equals(action, other.action)
				&& EqualityUtilities.equals(description, other.description)
				;
		}
		return false;
	}



	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param string
	 */
	public void setCode(String string) {
		code = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

}
