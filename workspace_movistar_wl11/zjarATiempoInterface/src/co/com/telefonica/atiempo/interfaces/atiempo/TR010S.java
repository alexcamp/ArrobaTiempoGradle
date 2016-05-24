//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR010S.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR010S extends ResponseHeader {

	private boolean response;
	private int odsNumber;
	private long atisRequestNumber;
	private boolean reserveResult;
	private long secondaryDistributor;
	private String distributorDescription;
	private String closet;
	private String box;
	private int boxPair;
	private long primaryDistributor;
	private String strip;
	private int stripPair;
	private long centralCode;
	private String centralDescription;
	private String cable;
	private int cablePair;
	private int phoneNumber;
	private String len;
	private Collection dslams;
	private String distributorAddress;
	private String closetAddress;
	private String boxAddress;
	private String horizontalPosition;
	private long typeError;
	private String stateLine;
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
	public int hashCode() {
		return boxPair;
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR010S) {
			TR010S other = (TR010S) arg0;
			return super.equals(arg0)
				&& response == other.response
				&& odsNumber == other.odsNumber
				&& atisRequestNumber == other.atisRequestNumber
				&& reserveResult == other.reserveResult
				&& secondaryDistributor == other.secondaryDistributor 
				&& EqualityUtilities.equals(
			distributorDescription,
					other.distributorDescription)
				&& EqualityUtilities.equals(closet, other.closet)
				&& EqualityUtilities.equals(box, other.box)
				&& boxPair == other.boxPair
				&& primaryDistributor == other.primaryDistributor
				&& EqualityUtilities.equals(strip, other.strip)
				&& stripPair == other.stripPair
				&& centralCode == other.centralCode
				&& EqualityUtilities.equals(
			centralDescription,
					other.centralDescription)
				&& EqualityUtilities.equals(cable, other.cable)
				&& cablePair == other.cablePair
				&& phoneNumber == other.phoneNumber
				&& EqualityUtilities.equals(len, other.len)
				&& EqualityUtilities.equals(dslams, other.dslams)
				&& EqualityUtilities.equals(distributorAddress, other.distributorAddress)
				&& EqualityUtilities.equals(closetAddress, other.closetAddress)
				&& EqualityUtilities.equals(boxAddress, other.boxAddress)
				&& EqualityUtilities.equals(horizontalPosition, other.horizontalPosition)
				&& EqualityUtilities.equals(rack, other.rack)
				&& EqualityUtilities.equals(subRack, other.subRack)
				&& EqualityUtilities.equals(fttciplb,other.fttciplb)
				&& EqualityUtilities.equals(fttcusu,other.fttcusu)
				&& EqualityUtilities.equals(fttcpwd,other.fttcpwd)
				&& EqualityUtilities.equals(fttcslot,other.fttcslot)
				&& EqualityUtilities.equals(fttcfab,other.fttcfab)
				&& EqualityUtilities.equals(fttcmode,other.fttcmode)
				&& typeError == other.typeError
				
				;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getBox() {
		return box;
	}

	public int getBoxPair() {
		return boxPair;
	}

	public String getCable() {
		return cable;
	}

	public int getCablePair() {
		return cablePair;
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

	public String getDistributorDescription() {
		return distributorDescription;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public long getPrimaryDistributor() {
		return primaryDistributor;
	}

	public boolean isReserveResult() {
		return reserveResult;
	}

	public boolean isResponse() {
		return response;
	}

	public long getSecondaryDistributor() {
		return secondaryDistributor;
	}

	public String getStrip() {
		return strip;
	}

	public int getStripPair() {
		return stripPair;
	}

	public String getHorizontalPosition() {
		return horizontalPosition;
	}
	
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setBox(String string) {
		box = string;
	}

	public void setBoxPair(int i) {
		boxPair = i;
	}

	public void setCable(String string) {
		cable = string;
	}

	public void setCablePair(int string) {
		cablePair = string;
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

	public void setDistributorDescription(String string) {
		distributorDescription = string;
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

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setPrimaryDistributor(long i) {
		primaryDistributor = i;
	}

	public void setReserveResult(boolean b) {
		reserveResult = b;
	}

	public void setResponse(boolean b) {
		response = b;
	}

	public void setSecondaryDistributor(long i) {
		secondaryDistributor = i;
	}

	public void setStrip(String string) {
		strip = string;
	}

	public void setStripPair(int i) {
		stripPair = i;
	}

	public String getBoxAddress() {
		return boxAddress;
	}

	public String getClosetAddress() {
		return closetAddress;
	}

	public String getDistributorAddress() {
		return distributorAddress;
	}

	public void setBoxAddress(String string) {
		boxAddress = string;
	}

	public void setClosetAddress(String string) {
		closetAddress = string;
	}

	public void setDistributorAddress(String string) {
		distributorAddress = string;
	}

	public void setHorizontalPosition(String string) {
		horizontalPosition = string;
	}
	
	public long getTypeError() {
		return typeError;
	}

	public void setTypeError(long i) {
		typeError = i;
	}

	public String getStateLine() {
		return stateLine;
	}

	public void setStateLine(String stateLine) {
		this.stateLine = stateLine;
	}
	/**
	 * @return Devuelve fttciplb.
	 */

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