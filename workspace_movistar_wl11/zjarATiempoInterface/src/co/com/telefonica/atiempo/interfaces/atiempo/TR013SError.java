//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR013SError.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ilarranaga
 * @version $Revision: 1.1 $
 */
public class TR013SError implements Serializable {
	
	private long code;
	private String description;
	
	public long getCode() {
		return code;
	}
	public void setCode(long l) {
		code = l;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String string) {
		description = string;
	}
	
	public int hashCode(){
		return (int)code;
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR013SError) {
			TR013SError other = (TR013SError) arg0;
			return code== other.code &&
				EqualityUtilities.equals(description, other.description);
		}
		
		return false;
	}

}
