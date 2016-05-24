//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR001E.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR001E extends ResponseHeader{
	private long atisRequestNumber;
	private Collection groups;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public Collection getGroups() {
		return groups;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setGroups(Collection collection) {
		groups = collection;
	}
	
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR001E) {
			TR001E other = (TR001E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(groups, other.groups);
		}
		return false;
	}

}
