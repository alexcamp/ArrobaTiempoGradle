//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TypeValue.java,v 1.1 2011/03/30 18:26:38 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TypeValue implements Serializable {
	
	private String name;
	private String value;


	public boolean equals(Object arg0) {
		if (arg0 instanceof TypeValue) {
			TypeValue other = (TypeValue) arg0;
			return EqualityUtilities.equals(name, other.name)
			&& EqualityUtilities.equals(value, other.value);
		}
		return false;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param list
	 */
	public void setName(String s) {
		name = s;
	}

	/**
	 * @param list
	 */
	public void setValue(String s) {
		value = s;
	}

}
