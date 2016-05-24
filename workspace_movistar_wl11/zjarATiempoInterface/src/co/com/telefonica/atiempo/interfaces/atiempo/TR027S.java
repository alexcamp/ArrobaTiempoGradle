//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR027S.java,v 1.1 2011/03/30 18:24:46 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR027S extends ResponseHeader2{

	private long atiempoRequestNumber;
	private long atisRequestNumber;
	private Collection equipments;
	
	public int hashCode(){
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR027S) {
			TR027S other = (TR027S) arg0;
			return super.equals(arg0)
				&& atiempoRequestNumber == other.atiempoRequestNumber
				&& EqualityUtilities.equals(equipments,other.equipments)
				&& atisRequestNumber == other.atisRequestNumber;
		}
		return false;
	}

	
	
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	public Collection getEquipments() {
		return equipments;
	}
	public void setEquipments(Collection equipments) {
		this.equipments = equipments;
	}
}
