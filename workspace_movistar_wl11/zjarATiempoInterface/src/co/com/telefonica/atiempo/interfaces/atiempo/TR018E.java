//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR018E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR018E extends RequestHeader{
	private long atisRequestNumber;
	private String pcId;
	private Collection cards;
	private Collection equipments;

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getPcId() {
		return pcId;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setPcId(String string) {
		pcId = string;
	}
	public int hashCode(){
		return pcId.hashCode();
	}
	public boolean equals(Object arg0){
		if (arg0 instanceof TR018E) {
			TR018E other = (TR018E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(pcId, other.pcId)
				&& EqualityUtilities.equals(cards, other.cards)
				&& EqualityUtilities.equals(equipments, other.equipments);
		}
		return false;
	}

	public Collection getEquipments() {
		return equipments;
	}

	public void setEquipments(Collection collection) {
		equipments = collection;
	}

	public Collection getCards() {
		return cards;
	}

	public void setCards(Collection collection) {
		cards = collection;
	}

}
