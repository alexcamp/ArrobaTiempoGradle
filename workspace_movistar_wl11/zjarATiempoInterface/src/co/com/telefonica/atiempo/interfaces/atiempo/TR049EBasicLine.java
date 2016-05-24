/*
 * Created on Feb 18, 201
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendez
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR049EBasicLine implements Serializable {

	private int odsNumber;
	
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

	private Collection zonasAtendimiento;

	private String distributorAddress;

	private String closetAddress;

	private String boxAddress;

	private String horizontalPosition;

	private String stateLine;

	private String boxType;

	/**
	 *  
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 *  
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR049EBasicLine) {
			TR049EBasicLine other = (TR049EBasicLine) arg0;
			return super.equals(arg0)
					&& odsNumber == other.odsNumber
					&& reserveResult == other.reserveResult
					&& secondaryDistributor == other.secondaryDistributor
					&& EqualityUtilities.equals(distributorDescription,
							other.distributorDescription)
					&& EqualityUtilities.equals(closet, other.closet)
					&& EqualityUtilities.equals(box, other.box)
					&& boxPair == other.boxPair
					&& primaryDistributor == other.primaryDistributor
					&& EqualityUtilities.equals(strip, other.strip)
					&& stripPair == other.stripPair
					&& centralCode == other.centralCode
					&& EqualityUtilities.equals(centralDescription,
							other.centralDescription)
					&& EqualityUtilities.equals(cable, other.cable)
					&& cablePair == other.cablePair
					&& phoneNumber == other.phoneNumber
					&& EqualityUtilities.equals(len, other.len)
					&& EqualityUtilities.equals(zonasAtendimiento, other.zonasAtendimiento)
					&& EqualityUtilities.equals(distributorAddress,
							other.distributorAddress)
					&& EqualityUtilities.equals(closetAddress,
							other.closetAddress)
					&& EqualityUtilities.equals(boxAddress, other.boxAddress)
					&& EqualityUtilities.equals(horizontalPosition,
							other.horizontalPosition)
					&& EqualityUtilities.equals(stateLine, other.stateLine)
					&& EqualityUtilities.equals(boxType, other.boxType);

		} else {
			return false;
		}
	}

	/**
	 * @return Returns the box.
	 */
	public String getBox() {
		return box;
	}

	/**
	 * @param box
	 *            The box to set.
	 */
	public void setBox(String box) {
		this.box = box;
	}

	/**
	 * @return Returns the boxAddress.
	 */
	public String getBoxAddress() {
		return boxAddress;
	}

	/**
	 * @param boxAddress
	 *            The boxAddress to set.
	 */
	public void setBoxAddress(String boxAddress) {
		this.boxAddress = boxAddress;
	}

	/**
	 * @return Returns the boxPair.
	 */
	public int getBoxPair() {
		return boxPair;
	}

	/**
	 * @param boxPair
	 *            The boxPair to set.
	 */
	public void setBoxPair(int boxPair) {
		this.boxPair = boxPair;
	}

	/**
	 * @return Returns the boxType.
	 */
	public String getBoxType() {
		return boxType;
	}

	/**
	 * @param boxType
	 *            The boxType to set.
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	/**
	 * @return Returns the cable.
	 */
	public String getCable() {
		return cable;
	}

	/**
	 * @param cable
	 *            The cable to set.
	 */
	public void setCable(String cable) {
		this.cable = cable;
	}

	/**
	 * @return Returns the cablePair.
	 */
	public int getCablePair() {
		return cablePair;
	}

	/**
	 * @param cablePair
	 *            The cablePair to set.
	 */
	public void setCablePair(int cablePair) {
		this.cablePair = cablePair;
	}

	/**
	 * @return Returns the centralCode.
	 */
	public long getCentralCode() {
		return centralCode;
	}

	/**
	 * @param centralCode
	 *            The centralCode to set.
	 */
	public void setCentralCode(long centralCode) {
		this.centralCode = centralCode;
	}

	/**
	 * @return Returns the centralDescription.
	 */
	public String getCentralDescription() {
		return centralDescription;
	}

	/**
	 * @param centralDescription
	 *            The centralDescription to set.
	 */
	public void setCentralDescription(String centralDescription) {
		this.centralDescription = centralDescription;
	}

	/**
	 * @return Returns the closet.
	 */
	public String getCloset() {
		return closet;
	}

	/**
	 * @param closet
	 *            The closet to set.
	 */
	public void setCloset(String closet) {
		this.closet = closet;
	}

	/**
	 * @return Returns the closetAddress.
	 */
	public String getClosetAddress() {
		return closetAddress;
	}

	/**
	 * @param closetAddress
	 *            The closetAddress to set.
	 */
	public void setClosetAddress(String closetAddress) {
		this.closetAddress = closetAddress;
	}

	/**
	 * @return Returns the distributorAddress.
	 */
	public String getDistributorAddress() {
		return distributorAddress;
	}

	/**
	 * @param distributorAddress
	 *            The distributorAddress to set.
	 */
	public void setDistributorAddress(String distributorAddress) {
		this.distributorAddress = distributorAddress;
	}

	/**
	 * @return Returns the distributorDescription.
	 */
	public String getDistributorDescription() {
		return distributorDescription;
	}

	/**
	 * @param distributorDescription
	 *            The distributorDescription to set.
	 */
	public void setDistributorDescription(String distributorDescription) {
		this.distributorDescription = distributorDescription;
	}

	/**
	 * @return Returns the dslams.
	 */
	public Collection getZonasAtendimiento() {
		return zonasAtendimiento;
	}

	/**
	 * @param dslams
	 *            The dslams to set.
	 */
	public void setZonasAtendimiento(Collection zonasAtendimiento) {
		this.zonasAtendimiento = zonasAtendimiento;
	}

	/**
	 * @return Returns the horizontalPosition.
	 */
	public String getHorizontalPosition() {
		return horizontalPosition;
	}

	/**
	 * @param horizontalPosition
	 *            The horizontalPosition to set.
	 */
	public void setHorizontalPosition(String horizontalPosition) {
		this.horizontalPosition = horizontalPosition;
	}

	/**
	 * @return Returns the len.
	 */
	public String getLen() {
		return len;
	}

	/**
	 * @param len
	 *            The len to set.
	 */
	public void setLen(String len) {
		this.len = len;
	}

	/**
	 * @return Returns the phoneNumber.
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            The phoneNumber to set.
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return Returns the primaryDistributor.
	 */
	public long getPrimaryDistributor() {
		return primaryDistributor;
	}

	/**
	 * @param primaryDistributor
	 *            The primaryDistributor to set.
	 */
	public void setPrimaryDistributor(long primaryDistributor) {
		this.primaryDistributor = primaryDistributor;
	}

	/**
	 * @return Returns the reserveResult.
	 */
	public boolean isReserveResult() {
		return reserveResult;
	}

	/**
	 * @param reserveResult
	 *            The reserveResult to set.
	 */
	public void setReserveResult(boolean reserveResult) {
		this.reserveResult = reserveResult;
	}

	/**
	 * @return Returns the secondaryDistributor.
	 */
	public long getSecondaryDistributor() {
		return secondaryDistributor;
	}

	/**
	 * @param secondaryDistributor
	 *            The secondaryDistributor to set.
	 */
	public void setSecondaryDistributor(long secondaryDistributor) {
		this.secondaryDistributor = secondaryDistributor;
	}

	/**
	 * @return Returns the stateLine.
	 */
	public String getStateLine() {
		return stateLine;
	}

	/**
	 * @param stateLine
	 *            The stateLine to set.
	 */
	public void setStateLine(String stateLine) {
		this.stateLine = stateLine;
	}

	/**
	 * @return Returns the strip.
	 */
	public String getStrip() {
		return strip;
	}

	/**
	 * @param strip
	 *            The strip to set.
	 */
	public void setStrip(String strip) {
		this.strip = strip;
	}

	/**
	 * @return Returns the stripPair.
	 */
	public int getStripPair() {
		return stripPair;
	}

	/**
	 * @param stripPair The stripPair to set.
	 */
	public void setStripPair(int stripPair) {
		this.stripPair = stripPair;
	}
	/**
	 * @return Returns the odsNumber.
	 */
	public int getOdsNumber() {
		return odsNumber;
	}
	/**
	 * @param odsNumber The odsNumber to set.
	 */
	public void setOdsNumber(int odsNumber) {
		this.odsNumber = odsNumber;
	}
}