//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ErrorAux.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class ErrorAux implements Serializable {
	
	private long code;
	private String casId;
	private String card;
	private String errorCodeMessage;
	
	public long getCode() {
		return code;
	}

	public void setCode(long l) {
		code = l;
	}

	public String getCard() {
		return card;
	}

	public String getCasId() {
		return casId;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public void setCard(String string) {
		card = string;
	}

	public void setCasId(String string) {
		casId = string;
	}

	public void setErrorCodeMessage(String string) {
		errorCodeMessage = string;
	}
	
	public int hashCode(){
		return (int)code;
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof ErrorAux) {
			ErrorAux other = (ErrorAux) arg0;
			return code== other.code
			&& EqualityUtilities.equals(casId,other.casId)
			&& EqualityUtilities.equals(card, other.card)
			&& EqualityUtilities.equals(errorCodeMessage, other.errorCodeMessage);
		}
		return false;
	}

}
