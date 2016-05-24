//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Characteristic.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class Characteristic implements Serializable {
	
	private long code;
	private String name;
	private String dataType;
	private long codeValue;
	private String initialValue;
	private String finalValue;
	private String realValue;
	private long length;
	private String format;
	private String moment;
	
	public long getCode() {
		return code;
	}

	public long getCodeValue() {
		return codeValue;
	}

	public String getDataType() {
		return dataType;
	}

	public String getFinalValue() {
		return finalValue;
	}

	public String getFormat() {
		return format;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public long getLength() {
		return length;
	}

	public String getMoment() {
		return moment;
	}

	public String getName() {
		return name;
	}

	public String getRealValue() {
		return realValue;
	}

	public void setCode(long l) {
		code = l;
	}

	public void setCodeValue(long l) {
		codeValue = l;
	}

	public void setDataType(String string) {
		dataType = string;
	}

	public void setFinalValue(String string) {
		finalValue = string;
	}

	public void setFormat(String string) {
		format = string;
	}

	public void setInitialValue(String string) {
		initialValue = string;
	}

	public void setLength(long l) {
		length = l;
	}

	public void setMoment(String string) {
		moment = string;
	}

	public void setName(String string) {
		name = string;
	}

	public void setRealValue(String string) {
		realValue = string;
	}
	
	public int hashCode() {
			return name.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof Characteristic) {
			Characteristic other = (Characteristic) arg0;
			return code == other.code
				&& EqualityUtilities.equals(name, other.name)
				&& EqualityUtilities.equals(dataType, other.dataType)
				&& codeValue == other.codeValue
				&& EqualityUtilities.equals(initialValue, other.initialValue)
				&& EqualityUtilities.equals(finalValue, other.finalValue)
				&& EqualityUtilities.equals(realValue, other.realValue)
				&& length == other.length
				&& EqualityUtilities.equals(format, other.format)
				&& EqualityUtilities.equals(moment, other.moment);
		}
		return false;
	}

	

}
