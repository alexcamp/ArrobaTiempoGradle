//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SubRequest1.java,v 1.1 2011/03/30 18:21:05 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class SubRequest1 implements Serializable {
	
	private int atisSubRequestNumber;
	private String subRequestTypeError;
	private String subRequestCodeError;
	private String subRequestDescriptionError;
	private Date subRequestEndTime;

	public int getAtisSubRequestNumber() {
		return atisSubRequestNumber;
	}

	public String getSubRequestCodeError() {
		return subRequestCodeError;
	}

	public String getSubRequestDescriptionError() {
		return subRequestDescriptionError;
	}

	public Date getSubRequestEndTime() {
		return subRequestEndTime;
	}

	public String getSubRequestTypeError() {
		return subRequestTypeError;
	}

	public void setAtisSubRequestNumber(int i) {
		atisSubRequestNumber = i;
	}

	public void setSubRequestCodeError(String string) {
		subRequestCodeError = string;
	}

	public void setSubRequestDescriptionError(String string) {
		subRequestDescriptionError = string;
	}

	public void setSubRequestEndTime(Date date) {
		subRequestEndTime = date;
	}

	public void setSubRequestTypeError(String string) {
		subRequestTypeError = string;
	}
	
	public int hashCode(){
		return atisSubRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof SubRequest1) {
			SubRequest1 other = (SubRequest1) arg0;
			return 	atisSubRequestNumber == other.atisSubRequestNumber &&
				EqualityUtilities.equals(subRequestTypeError, other.subRequestTypeError) &&
				EqualityUtilities.equals(subRequestCodeError, other.subRequestCodeError) &&
				EqualityUtilities.equals(subRequestDescriptionError, other.subRequestDescriptionError) &&
				EqualityUtilities.equals(subRequestEndTime, other.subRequestEndTime);
		}
		
		return false;
	}	

}
