//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR003S.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR003S extends ResponseHeader {
	private long secondaryDistributor;
	private String secondaryDistributorDescription;
	private String closet;
	private String box;
	private int boxPair;
	private long primaryDistributor;
	private String primaryDistributorDescription;
	private String strip;
	private int stripPair;
	private String cable;
	private int pairCable;
	private long centralCode;
	private String centralDescription;
	private int phoneNumber;
	private String len;
	private long atisRequestNumber;
	private int atisGroupNumber;
	private int atisSubrequestNumber;
	private int odsNumber;
	private long previousSecondaryDistributor;
	private String previousSecondaryDistributorDescription;
	private String previousCloset;
	private String previousBox;
	private int previousBoxPair;
	private long previousPrimaryDistributor;
	private String previousPrimaryDistributorDescription;
	private String previousStrip;
	private int previousStripPair;
	private String previousCable;
	private int previousCablePair;
	private long previousCentralCode;
	private String previousCentralDescription;
	private int previousPhoneNumber;
	private String previousLen;
	private String distributorAddress;
	private String closetAddress;
	private String boxAddress;
	private Collection dslams;
	private String cna;
	private int result;
	private String horizontalPosition;
	private String previousHorizontalPosition;
	private long typeError;
	private String  previousBoxType;
	private String boxType;
	private String stateLine;
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
	/**
	 * @return Devuelve previousBoxType.
	 */
	public String getPreviousBoxType() {
		return previousBoxType;
	}
	/**
	 * @param previousBoxType El previousBoxType a establecer.
	 */
	public void setPreviousBoxType(String previousBoxType) {
		this.previousBoxType = previousBoxType;
	}
	public int getAtisGroupNumber() {
		return atisGroupNumber;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public int getAtisSubrequestNumber() {
		return atisSubrequestNumber;
	}

	public String getBox() {
		return box;
	}

	public String getBoxAddress() {
		return boxAddress;
	}

	public int getBoxPair() {
		return boxPair;
	}

	public String getCable() {
		return cable;
	}

	public long getCentralCode() {
		return centralCode;
	}

	public String getCentralDescription() {
		return centralDescription;
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

	public String getLen() {
		return len;
	}

	public int getOdsNumber() {
		return odsNumber;
	}

	public int getPairCable() {
		return pairCable;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getPreviousBox() {
		return previousBox;
	}

	public int getPreviousBoxPair() {
		return previousBoxPair;
	}

	public String getPreviousCable() {
		return previousCable;
	}

	public int getPreviousCablePair() {
		return previousCablePair;
	}

	public long getPreviousCentralCode() {
		return previousCentralCode;
	}

	public String getPreviousCentralDescription() {
		return previousCentralDescription;
	}

	public String getPreviousCloset() {
		return previousCloset;
	}

	public String getPreviousLen() {
		return previousLen;
	}

	public int getPreviousPhoneNumber() {
		return previousPhoneNumber;
	}

	public long getPreviousPrimaryDistributor() {
		return previousPrimaryDistributor;
	}

	public String getPreviousPrimaryDistributorDescription() {
		return previousPrimaryDistributorDescription;
	}

	public long getPreviousSecondaryDistributor() {
		return previousSecondaryDistributor;
	}

	public String getPreviousSecondaryDistributorDescription() {
		return previousSecondaryDistributorDescription;
	}

	public String getPreviousStrip() {
		return previousStrip;
	}

	public int getPreviousStripPair() {
		return previousStripPair;
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

	public String getStrip() {
		return strip;
	}

	public int getStripPair() {
		return stripPair;
	}

	public void setAtisGroupNumber(int i) {
		atisGroupNumber = i;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setAtisSubrequestNumber(int i) {
		atisSubrequestNumber = i;
	}

	public void setBox(String string) {
		box = string;
	}

	public void setBoxAddress(String string) {
		boxAddress = string;
	}

	public void setBoxPair(int i) {
		boxPair = i;
	}

	public void setCable(String string) {
		cable = string;
	}

	public void setCentralCode(long i) {
		centralCode = i;
	}

	public void setCentralDescription(String string) {
		centralDescription = string;
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

	public void setLen(String string) {
		len = string;
	}

	public void setOdsNumber(int i) {
		odsNumber = i;
	}

	public void setPairCable(int i) {
		pairCable = i;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setPreviousBox(String string) {
		previousBox = string;
	}

	public void setPreviousBoxPair(int i) {
		previousBoxPair = i;
	}

	public void setPreviousCable(String string) {
		previousCable = string;
	}

	public void setPreviousCablePair(int i) {
		previousCablePair = i;
	}

	public void setPreviousCentralCode(long i) {
		previousCentralCode = i;
	}

	public void setPreviousCentralDescription(String string) {
		previousCentralDescription = string;
	}

	public void setPreviousCloset(String string) {
		previousCloset = string;
	}

	public void setPreviousLen(String string) {
		previousLen = string;
	}

	public void setPreviousPhoneNumber(int i) {
		previousPhoneNumber = i;
	}

	public void setPreviousPrimaryDistributor(long i) {
		previousPrimaryDistributor = i;
	}

	public void setPreviousPrimaryDistributorDescription(String string) {
		previousPrimaryDistributorDescription = string;
	}

	public void setPreviousSecondaryDistributor(long i) {
		previousSecondaryDistributor = i;
	}

	public void setPreviousSecondaryDistributorDescription(String string) {
		previousSecondaryDistributorDescription = string;
	}

	public void setPreviousStrip(String i) {
		previousStrip = i;
	}

	public void setPreviousStripPair(int i) {
		previousStripPair = i;
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

	public void setStrip(String i) {
		strip = i;
	}

	public void setStripPair(int i) {
		stripPair = i;
	}
	
	public int hashCode(){
		return this.phoneNumber;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR003S) {
			TR003S other = (TR003S) arg0;
			return super.equals(arg0)
				&& secondaryDistributor == other.secondaryDistributor
				&& EqualityUtilities.equals(secondaryDistributorDescription, other.secondaryDistributorDescription)
				&& EqualityUtilities.equals(closet, other.closet)
				&& EqualityUtilities.equals(box, other.box)
				&& boxPair == other.boxPair
				&& primaryDistributor == other.primaryDistributor
				&& EqualityUtilities.equals(primaryDistributorDescription, other.primaryDistributorDescription)
				&& EqualityUtilities.equals(strip,other.strip)
				&& stripPair == other.stripPair
				&& EqualityUtilities.equals(cable, other.cable)
				&& pairCable == pairCable
				&& centralCode == centralCode
				&& EqualityUtilities.equals(centralDescription, other.centralDescription)
				&& phoneNumber == phoneNumber
				&& EqualityUtilities.equals(len, other.len)
				&& atisRequestNumber == other.atisRequestNumber
				&& atisGroupNumber == other.atisGroupNumber
				&& atisSubrequestNumber == other.atisSubrequestNumber
				&& odsNumber == other.odsNumber
				&& previousSecondaryDistributor == previousSecondaryDistributor
				&& EqualityUtilities.equals(previousSecondaryDistributorDescription, other.previousSecondaryDistributorDescription)
				&& EqualityUtilities.equals(previousCloset, other.previousCloset)
				&& EqualityUtilities.equals(previousBox, other.previousBox)
				&& previousBoxPair == previousBoxPair
				&& previousPrimaryDistributor == previousPrimaryDistributor
				&& EqualityUtilities.equals(previousPrimaryDistributorDescription, other.previousPrimaryDistributorDescription)
				&& EqualityUtilities.equals(previousStrip,previousStrip)
				&& previousStripPair == previousStripPair
				&& EqualityUtilities.equals(previousCable, other.previousCable)
				&& previousCablePair == previousCablePair
				&& previousCentralCode == previousCentralCode
				&& EqualityUtilities.equals(previousCentralDescription, other.previousCentralDescription)
				&& previousPhoneNumber == previousPhoneNumber
				&& EqualityUtilities.equals(previousLen, other.previousLen)
				&& EqualityUtilities.equals(distributorAddress, distributorAddress)
				&& EqualityUtilities.equals(closetAddress, closetAddress)
				&& EqualityUtilities.equals(boxAddress, other.boxAddress)
				&& EqualityUtilities.equals(dslams, other.dslams)
				&& EqualityUtilities.equals(rack,other.rack)
				&& EqualityUtilities.equals(subRack,other.subRack)
				&& EqualityUtilities.equals(cna,other.cna) 
				&& result == other.result
				&& EqualityUtilities.equals(horizontalPosition,other.horizontalPosition)
				&& EqualityUtilities.equals(previousHorizontalPosition,other.previousHorizontalPosition)
				&& typeError == other.typeError
				&& EqualityUtilities.equals(fttciplb,other.fttciplb)
				&& EqualityUtilities.equals(fttcusu,other.fttcusu)
				&& EqualityUtilities.equals(fttcpwd,other.fttcpwd)
				&& EqualityUtilities.equals(fttcslot,other.fttcslot)
				&& EqualityUtilities.equals(fttcfab,other.fttcfab)
				&& EqualityUtilities.equals(fttcmode,other.fttcmode)
				;
		}
		return false;
	}
	public int getResult() {
		return result;
	}

	public void setResult(int i) {
		result = i;
	}

	public String getCna() {
		return cna;
	}

	public void setCna(String string) {
		cna = string;
	}
	
	public String getPreviousHorizontalPosition() {
		return previousHorizontalPosition;
	}
	
	public String getHorizontalPosition() {
		return horizontalPosition;
	}
	
	public void setPreviousHorizontalPosition(String string) {
		previousHorizontalPosition = string;
	}
	
	public void setHorizontalPosition(String string) {
		horizontalPosition = string;
	}
	
	/**
	 * @return
	 */
	public long getTypeError() {
		return typeError;
	}

	/**
	 * @param l
	 */
	public void setTypeError(long l) {
		typeError = l;
	}

	public String getStateLine() {
		return stateLine;
	}

	public void setStateLine(String string) {
		stateLine = string;
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
