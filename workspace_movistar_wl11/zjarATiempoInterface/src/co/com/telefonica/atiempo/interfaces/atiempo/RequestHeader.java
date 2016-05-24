//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: RequestHeader.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ilarranaga
 * @version $Revision: 1.1 $
 */
public class RequestHeader implements Serializable {
	
	private String id;
	
	public void setId(String string) {
		id = string;
	}
	public String getId() {
		return id;
	}
	
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public boolean equals(Object object) {
		if (object instanceof RequestHeader) {
			RequestHeader other = (RequestHeader) object;
		
			return EqualityUtilities.equals(id, other.id);
		}
	
		return false;
	}

}
