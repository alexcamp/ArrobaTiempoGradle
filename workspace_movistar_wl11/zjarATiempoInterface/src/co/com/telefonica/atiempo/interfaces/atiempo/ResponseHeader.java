//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ResponseHeader.java,v 1.1 2011/03/30 18:21:06 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ilarranaga
 * @version $Revision: 1.1 $
 */
public class ResponseHeader implements Serializable {
	
	private String id;
	private boolean error;
	private String errorMessage;
	
	public void setId(String string) {
		id = string;
	}
	public String getId() {
		return id;
	}
	
	public void setError(boolean b) {
		error = b;
	}
	public boolean isError() {
		return error;
	}

	public void setErrorMessage(String string) {
		errorMessage = string;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
	
	public boolean equals(Object object) {
		if (object instanceof ResponseHeader) {
			ResponseHeader other = (ResponseHeader) object;
			
			return EqualityUtilities.equals(id, other.id) &&
				error == other.error &&
				EqualityUtilities.equals(errorMessage, other.errorMessage);
		}
		
		return false;
	}

}
