//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR012S.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR012S extends ResponseHeader {
	private long secondaryDistributor;
	private String secondaryDistributorDescription;
	private String closet;
	private String box;
	private int pairCobreBox;
	private long primaryDistributor;
	private String strip;
	private int pairCobreStrip;
	private long phoneCentral;
	private String phoneCentralDescription;
	private String inCable;
	private int pairCable;
	private int phoneNumber;
	private String len;
	private String distributorAddress;
	private String closetAddress;
	private String boxAddress; 
	private Collection services;
	private Collection dslams;
	private String primaryDistributorDescription;
	private String horizontalPosition;
	private long typeError;
	private String boxType;
	private String rack;
	private String subRack;
	private String fttciplb;
	private String fttcusu;
	private String fttcpwd;
	private String fttcslot;
	private String fttcfab;
	private String fttcmode;
	
	
	/**
	 * @return Devuelve fttcfab.
	 */
	public String getFttcfab() {
		return fttcfab;
	}
	/**
	 * @param fttcfab El fttcfab a establecer.
	 */
	public void setFttcfab(String fttcfab) {
		this.fttcfab = fttcfab;
	}
	/**
	 * @return Devuelve fttciplb.
	 */
	public String getFttciplb() {
		return fttciplb;
	}
	/**
	 * @param fttciplb El fttciplb a establecer.
	 */
	public void setFttciplb(String fttciplb) {
		this.fttciplb = fttciplb;
	}
	/**
	 * @return Devuelve fttcmode.
	 */
	public String getFttcmode() {
		return fttcmode;
	}
	/**
	 * @param fttcmode El fttcmode a establecer.
	 */
	public void setFttcmode(String fttcmode) {
		this.fttcmode = fttcmode;
	}
	/**
	 * @return Devuelve fttcpwd.
	 */
	public String getFttcpwd() {
		return fttcpwd;
	}
	/**
	 * @param fttcpwd El fttcpwd a establecer.
	 */
	public void setFttcpwd(String fttcpwd) {
		this.fttcpwd = fttcpwd;
	}
	/**
	 * @return Devuelve fttcslot.
	 */
	public String getFttcslot() {
		return fttcslot;
	}
	/**
	 * @param fttcslot El fttcslot a establecer.
	 */
	public void setFttcslot(String fttcslot) {
		this.fttcslot = fttcslot;
	}
	/**
	 * @return Devuelve fttcusu.
	 */
	public String getFttcusu() {
		return fttcusu;
	}
	/**
	 * @param fttcusu El fttcusu a establecer.
	 */
	public void setFttcusu(String fttcusu) {
		this.fttcusu = fttcusu;
	}
	/**
	 * @return Devuelve boxType.
	 */
	public String getBoxType() {
		return boxType;
	}
	/**
	 * @param boxType El boxType a establecer.
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public String getBox() {
		return box;
	}

	public String getBoxAddress() {
		return boxAddress;
	}

	public String getCloset() {
		return closet;
	}

	public String getClosetAddress() {
		return closetAddress;
	}

	public String getDistributorAddress() {
		return distributorAddress;
	}

	public Collection getDslams() {
		return dslams;
	}

	public String getInCable() {
		return inCable;
	}

	public String getLen() {
		return len;
	}

	public int getPairCobreBox() {
		return pairCobreBox;
	}

	public int getPairCobreStrip() {
		return pairCobreStrip;
	}

	public long getPhoneCentral() {
		return phoneCentral;
	}

	public String getPhoneCentralDescription() {
		return phoneCentralDescription;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public long getPrimaryDistributor() {
		return primaryDistributor;
	}

	public String getPrimaryDistributorDescription() {
		return primaryDistributorDescription;
	}

	public long getSecondaryDistributor() {
		return secondaryDistributor;
	}

	public String getSecondaryDistributorDescription() {
		return secondaryDistributorDescription;
	}

	public Collection getServices() {
		return services;
	}

	public String getStrip() {
		return strip;
	}

	public void setBox(String string) {
		box = string;
	}

	public void setBoxAddress(String string) {
		boxAddress = string;
	}

	public void setCloset(String string) {
		closet = string;
	}

	public void setClosetAddress(String string) {
		closetAddress = string;
	}

	public void setDistributorAddress(String string) {
		distributorAddress = string;
	}

	public void setDslams(Collection collection) {
		dslams = collection;
	}

	public void setInCable(String string) {
		inCable = string;
	}

	public void setLen(String string) {
		len = string;
	}

	public void setPairCobreBox(int i) {
		pairCobreBox = i;
	}

	public void setPairCobreStrip(int i) {
		pairCobreStrip = i;
	}

	public void setPhoneCentral(long i) {
		phoneCentral = i;
	}

	public void setPhoneCentralDescription(String string) {
		phoneCentralDescription = string;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setPrimaryDistributor(long i) {
		primaryDistributor = i;
	}

	public void setPrimaryDistributorDescription(String string) {
		primaryDistributorDescription = string;
	}

	public void setSecondaryDistributor(long i) {
		secondaryDistributor = i;
	}

	public void setSecondaryDistributorDescription(String string) {
		secondaryDistributorDescription = string;
	}

	public void setServices(Collection collection) {
		services = collection;
	}

	public void setStrip(String string) {
		strip = string;
	}
	
	public int hashCode(){
		return phoneNumber;
	}
	

	public int getPairCable() {
		return pairCable;
	}

	public void setPairCable(int i) {
		pairCable = i;
	}
	
	public String getHorizontalPosition() {
		return horizontalPosition;
	}

	public void setHorizontalPosition(String i) {
		horizontalPosition = i;
	}

	
	public long getTypeError() {
		return typeError;
	}

	
	public void setTypeError(long i) {
		typeError = i;
	}

	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR012S) {
			TR012S other = (TR012S) arg0;
			return super.equals(arg0)
				&& secondaryDistributor == other.secondaryDistributor
				&& EqualityUtilities.equals(secondaryDistributorDescription, other.secondaryDistributorDescription)
				&& EqualityUtilities.equals(closet, other.closet)
				&& EqualityUtilities.equals(box, other.box)
				&& pairCobreBox == other.pairCobreBox
				&& typeError == other.typeError
				&& primaryDistributor == primaryDistributor
				&& EqualityUtilities.equals(strip,other.strip)
				&& pairCobreStrip == other.pairCobreStrip
				&& phoneCentral == other.phoneCentral
				&& EqualityUtilities.equals(phoneCentralDescription, other.phoneCentralDescription)
				&& EqualityUtilities.equals(inCable, other.inCable)
				&& phoneNumber == phoneNumber
				&& EqualityUtilities.equals(len, other.len)
				&& EqualityUtilities.equals(distributorAddress, other.distributorAddress)
				&& EqualityUtilities.equals(closetAddress, other.closetAddress)
				&& EqualityUtilities.equals(boxAddress, other.boxAddress)
				&& EqualityUtilities.equals(services, other.services)
				&& EqualityUtilities.equals(dslams, other.dslams)
				&& EqualityUtilities.equals(rack, other.rack)
				&& EqualityUtilities.equals(subRack, other.subRack)
				&& EqualityUtilities.equals(primaryDistributorDescription, other.primaryDistributorDescription)
				&& pairCable == other.pairCable
				&& EqualityUtilities.equals(horizontalPosition, other.horizontalPosition)	&& EqualityUtilities.equals(fttciplb,other.fttciplb)
				&& EqualityUtilities.equals(fttcusu,other.fttcusu)
				&& EqualityUtilities.equals(fttcpwd,other.fttcpwd)
				&& EqualityUtilities.equals(fttcslot,other.fttcslot)
				&& EqualityUtilities.equals(fttcfab,other.fttcfab)
				&& EqualityUtilities.equals(fttcmode,other.fttcmode)
				;
			}
		return false;
	}

	/**
	 * @return Devuelve rack.
	 */
	public String getRack() {
		return rack;
	}
	/**
	 * @param rack El rack a establecer.
	 */
	public void setRack(String rack) {
		this.rack = rack;
	}
	/**
	 * @return Devuelve subRack.
	 */
	public String getSubRack() {
		return subRack;
	}
	/**
	 * @param subRack El subRack a establecer.
	 */
	public void setSubRack(String subRack) {
		this.subRack = subRack;
	}
}
