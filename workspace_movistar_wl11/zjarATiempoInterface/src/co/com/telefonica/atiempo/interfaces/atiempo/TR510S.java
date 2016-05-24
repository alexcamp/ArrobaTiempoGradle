/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR510S extends ResponseHeader2{

	private boolean availabilityFlag; 
	private int odsNumber; 
	private long atiempoRequestNumber;
	private boolean reserveResult; 
	private long secundaryDistributor; 
	private String secundaryDistributorDescription;
	private String closet; 
	private String box;
	private int boxPair; 
	private long primaryDistributor; 
	private String strip;
	private int stripPair;
	private int centralCode; 
	private String centralDescription;
	private String cable; 
	private int cablePair; 
	private int phoneNumber;
	private String len;
	private String distributorAddress; 
	private String closetAddress; 
	private String boxAddress;
	private Collection dslams;
	private boolean centralConnection;
	private boolean eocEnable;
	private String boxType;
	private int boxDistance;
	private Float latitude;
	private Float longitude;
	private boolean dedicationPresence;
	private String closetDedicated;
	private String boxTypeDedicated;
	private String boxDedicated;
	private int boxPairDedicated;
	private long centralCodeDedicated;
	private String cableDedicated;
	private int cablePairDedicated;
	private String closetAddressDedicated;
	private String boxAddressDedicated;
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
	private int previousCentralCode;
	private String previousCentralDescription;
	private int previousPhoneNumber;
	private String previousLen;
	private boolean centralConnectionPreviousNumber;
	private int cna;
	private int result;
	private String horizontalPosition;
	private String previousHorizontalPosition;
	private String rack;
	private String subRack;

	private String fttciplb;
	private String fttcusu;
	private String fttcpwd;
	private String fttcslot;
	private String fttcfab;
	private String fttcmode;

	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR510S) {
				TR510S other = (TR510S) arg0;

				return super.equals(arg0)
					&& (availabilityFlag == other.availabilityFlag)
					&& (odsNumber == other.odsNumber)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (reserveResult == other.reserveResult)
					&& (secundaryDistributor == other.secundaryDistributor)
					&& EqualityUtilities.equals(secundaryDistributorDescription,other.secundaryDistributorDescription)
					&& EqualityUtilities.equals(closet,other.closet)
					&& EqualityUtilities.equals(box,other.box)
					&& EqualityUtilities.equals(horizontalPosition,other.horizontalPosition)
					&& EqualityUtilities.equals(previousHorizontalPosition,other.previousHorizontalPosition)
					&& (boxPair == other.boxPair)
					&& (primaryDistributor == other.primaryDistributor)
					&& EqualityUtilities.equals(strip,other.strip)
					&& (stripPair == other.stripPair)
					&& (centralCode == other.centralCode)
					&& EqualityUtilities.equals(centralDescription,other.centralDescription)
					&& EqualityUtilities.equals(cable,other.cable)
					&& (cablePair == other.cablePair)
					&& (phoneNumber == other.phoneNumber)
					&& EqualityUtilities.equals(len,other.len)
					&& EqualityUtilities.equals(distributorAddress,other.distributorAddress)
					&& EqualityUtilities.equals(closetAddress,other.closetAddress)
					&& EqualityUtilities.equals(boxAddress,other.boxAddress)
					&& EqualityUtilities.equals(dslams,other.dslams)
					&& (centralConnection == other.centralConnection)
					&& (eocEnable == other.eocEnable)
					&& EqualityUtilities.equals(boxType,other.boxType)
					&& (boxDistance == other.boxDistance)
					&& EqualityUtilities.equals(latitude,other.latitude)
					&& EqualityUtilities.equals(longitude,other.longitude)
					&& (dedicationPresence == other.dedicationPresence)
					&& EqualityUtilities.equals(closetDedicated,other.closetDedicated)
					&& EqualityUtilities.equals(boxTypeDedicated,other.boxTypeDedicated)
					&& (boxDedicated == other.boxDedicated)
					&& (boxPairDedicated == other.boxPairDedicated)
					&& (centralCodeDedicated == other.centralCodeDedicated)
					&& EqualityUtilities.equals(cableDedicated,other.cableDedicated)
					&& (cablePairDedicated == other.cablePairDedicated)
					&& EqualityUtilities.equals(closetAddressDedicated,other.closetAddressDedicated)
					&& EqualityUtilities.equals(boxAddressDedicated,other.boxAddressDedicated)
					&& (previousSecondaryDistributor == other.previousSecondaryDistributor)
					&& EqualityUtilities.equals(previousSecondaryDistributorDescription,other.previousSecondaryDistributorDescription)
					&& EqualityUtilities.equals(previousCloset,other.previousCloset)
					&& EqualityUtilities.equals(previousBox,other.previousBox)
					&& (previousBoxPair == other.previousBoxPair)
					&& (previousPrimaryDistributor == other.previousPrimaryDistributor)
					&& EqualityUtilities.equals(previousPrimaryDistributorDescription,other.previousPrimaryDistributorDescription)
					&& EqualityUtilities.equals(previousStrip,other.previousStrip)
					&& (previousStripPair == other.previousStripPair)
					&& EqualityUtilities.equals(previousCable,other.previousCable)
					&& (previousCablePair == other.previousCablePair)
					&& (previousCentralCode == other.previousCentralCode)
					&& EqualityUtilities.equals(previousCentralDescription,other.previousCentralDescription)
					&& (previousPhoneNumber == other.previousPhoneNumber)
					&& EqualityUtilities.equals(previousLen,other.previousLen)
					&& (centralConnectionPreviousNumber == other.centralConnectionPreviousNumber)
					&& (cna == other.cna)
					&& (result == other.result)
					&& EqualityUtilities.equals(fttciplb,other.fttciplb)
					&& EqualityUtilities.equals(fttcusu,other.fttcusu)
					&& EqualityUtilities.equals(fttcpwd,other.fttcpwd)
					&& EqualityUtilities.equals(fttcslot,other.fttcslot)
					&& EqualityUtilities.equals(fttcfab,other.fttcfab)
					&& EqualityUtilities.equals(fttcmode,other.fttcmode)
					&& EqualityUtilities.equals(rack,other.rack)
					&& EqualityUtilities.equals(subRack,other.subRack)
					;
					
			}
			return false;
		}
	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @return
	 */
	public boolean isAvailabilityFlag() {
		return availabilityFlag;
	}

	/**
	 * @return
	 */
	public String getBox() {
		return box;
	}

	/**
	 * @return
	 */
	public String getBoxAddress() {
		return boxAddress;
	}

	/**
	 * @return
	 */
	public String getBoxAddressDedicated() {
		return boxAddressDedicated;
	}

	/**
	 * @return
	 */
	public int getBoxDistance() {
		return boxDistance;
	}

	/**
	 * @return
	 */
	public int getBoxPair() {
		return boxPair;
	}

	/**
	 * @return
	 */
	public int getBoxPairDedicated() {
		return boxPairDedicated;
	}

	/**
	 * @return
	 */
	public String getBoxType() {
		return boxType;
	}

	/**
	 * @return
	 */
	public String getBoxTypeDedicated() {
		return boxTypeDedicated;
	}

	/**
	 * @return
	 */
	public String getCable() {
		return cable;
	}

	/**
	 * @return
	 */
	public String getCableDedicated() {
		return cableDedicated;
	}

	/**
	 * @return
	 */
	public int getCablePair() {
		return cablePair;
	}

	/**
	 * @return
	 */
	public int getCablePairDedicated() {
		return cablePairDedicated;
	}

	/**
	 * @return
	 */
	public int getCentralCode() {
		return centralCode;
	}

	/**
	 * @return
	 */
	public long getCentralCodeDedicated() {
		return centralCodeDedicated;
	}

	/**
	 * @return
	 */
	public boolean isCentralConnection() {
		return centralConnection;
	}

	/**
	 * @return
	 */
	public boolean isCentralConnectionPreviousNumber() {
		return centralConnectionPreviousNumber;
	}

	/**
	 * @return
	 */
	public String getCentralDescription() {
		return centralDescription;
	}

	/**
	 * @return
	 */
	public String getCloset() {
		return closet;
	}

	/**
	 * @return
	 */
	public String getClosetAddress() {
		return closetAddress;
	}

	/**
	 * @return
	 */
	public String getClosetAddressDedicated() {
		return closetAddressDedicated;
	}

	/**
	 * @return
	 */
	public String getClosetDedicated() {
		return closetDedicated;
	}

	/**
	 * @return
	 */
	public int getCna() {
		return cna;
	}

	/**
	 * @return
	 */
	public boolean isDedicationPresence() {
		return dedicationPresence;
	}

	/**
	 * @return
	 */
	public String getSecundaryDistributorDescription() {
		return secundaryDistributorDescription;
	}

	/**
	 * @return
	 */
	public String getDistributorAddress() {
		return distributorAddress;
	}

	/**
	 * @return
	 */
	public Collection getDslams() {
		return dslams;
	}

	/**
	 * @return
	 */
	public boolean isEocEnable() {
		return eocEnable;
	}

	/**
	 * @return
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * @return
	 */
	public String getLen() {
		return len;
	}

	/**
	 * @return
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * @return
	 */
	public int getOdsNumber() {
		return odsNumber;
	}

	/**
	 * @return
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return
	 */
	public String getPreviousBox() {
		return previousBox;
	}

	/**
	 * @return
	 */
	public int getPreviousBoxPair() {
		return previousBoxPair;
	}

	/**
	 * @return
	 */
	public String getPreviousCable() {
		return previousCable;
	}

	/**
	 * @return
	 */
	public int getPreviousCablePair() {
		return previousCablePair;
	}

	/**
	 * @return
	 */
	public int getPreviousCentralCode() {
		return previousCentralCode;
	}

	/**
	 * @return
	 */
	public String getPreviousCentralDescription() {
		return previousCentralDescription;
	}

	/**
	 * @return
	 */
	public String getPreviousCloset() {
		return previousCloset;
	}

	/**
	 * @return
	 */
	public String getPreviousLen() {
		return previousLen;
	}

	/**
	 * @return
	 */
	public int getPreviousPhoneNumber() {
		return previousPhoneNumber;
	}

	/**
	 * @return
	 */
	public long getPreviousPrimaryDistributor() {
		return previousPrimaryDistributor;
	}

	/**
	 * @return
	 */
	public String getPreviousPrimaryDistributorDescription() {
		return previousPrimaryDistributorDescription;
	}

	/**
	 * @return
	 */
	public long getPreviousSecondaryDistributor() {
		return previousSecondaryDistributor;
	}

	/**
	 * @return
	 */
	public String getPreviousSecondaryDistributorDescription() {
		return previousSecondaryDistributorDescription;
	}

	/**
	 * @return
	 */
	public String getPreviousStrip() {
		return previousStrip;
	}

	/**
	 * @return
	 */
	public int getPreviousStripPair() {
		return previousStripPair;
	}

	/**
	 * @return
	 */
	public long getPrimaryDistributor() {
		return primaryDistributor;
	}

	/**
	 * @return
	 */
	public boolean isReserveResult() {
		return reserveResult;
	}

	/**
	 * @return
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @return
	 */
	public long getSecundaryDistributor() {
		return secundaryDistributor;
	}

	/**
	 * @return
	 */
	public String getStrip() {
		return strip;
	}

	/**
	 * @return
	 */
	public int getStripPair() {
		return stripPair;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

	/**
	 * @param b
	 */
	public void setAvailabilityFlag(boolean b) {
		availabilityFlag = b;
	}

	/**
	 * @param string
	 */
	public void setBox(String string) {
		box = string;
	}

	/**
	 * @param string
	 */
	public void setBoxAddress(String string) {
		boxAddress = string;
	}

	/**
	 * @param string
	 */
	public void setBoxAddressDedicated(String string) {
		boxAddressDedicated = string;
	}

	/**
	 * @param i
	 */
	public void setBoxDistance(int i) {
		boxDistance = i;
	}

	/**
	 * @param i
	 */
	public void setBoxPair(int i) {
		boxPair = i;
	}

	/**
	 * @param i
	 */
	public void setBoxPairDedicated(int i) {
		boxPairDedicated = i;
	}

	/**
	 * @param string
	 */
	public void setBoxType(String string) {
		boxType = string;
	}

	/**
	 * @param string
	 */
	public void setBoxTypeDedicated(String string) {
		boxTypeDedicated = string;
	}

	/**
	 * @param string
	 */
	public void setCable(String string) {
		cable = string;
	}

	/**
	 * @param string
	 */
	public void setCableDedicated(String string) {
		cableDedicated = string;
	}

	/**
	 * @param i
	 */
	public void setCablePair(int i) {
		cablePair = i;
	}

	/**
	 * @param i
	 */
	public void setCablePairDedicated(int i) {
		cablePairDedicated = i;
	}

	/**
	 * @param i
	 */
	public void setCentralCode(int i) {
		centralCode = i;
	}

	/**
	 * @param l
	 */
	public void setCentralCodeDedicated(long l) {
		centralCodeDedicated = l;
	}

	/**
	 * @param b
	 */
	public void setCentralConnection(boolean b) {
		centralConnection = b;
	}

	/**
	 * @param b
	 */
	public void setCentralConnectionPreviousNumber(boolean b) {
		centralConnectionPreviousNumber = b;
	}

	/**
	 * @param string
	 */
	public void setCentralDescription(String string) {
		centralDescription = string;
	}

	/**
	 * @param string
	 */
	public void setCloset(String string) {
		closet = string;
	}

	/**
	 * @param string
	 */
	public void setClosetAddress(String string) {
		closetAddress = string;
	}

	/**
	 * @param string
	 */
	public void setClosetAddressDedicated(String string) {
		closetAddressDedicated = string;
	}

	/**
	 * @param string
	 */
	public void setClosetDedicated(String string) {
		closetDedicated = string;
	}

	/**
	 * @param i
	 */
	public void setCna(int i) {
		cna = i;
	}

	/**
	 * @param b
	 */
	public void setDedicationPresence(boolean b) {
		dedicationPresence = b;
	}

	/**
	 * @param string
	 */
	public void setSecundaryDistributorDescription(String string) {
		secundaryDistributorDescription = string;
	}

	/**
	 * @param string
	 */
	public void setDistributorAddress(String string) {
		distributorAddress = string;
	}

	/**
	 * @param collection
	 */
	public void setDslams(Collection collection) {
		dslams = collection;
	}

	/**
	 * @param b
	 */
	public void setEocEnable(boolean b) {
		eocEnable = b;
	}

	/**
	 * @param f
	 */
	public void setLatitude(Float f) {
		latitude = f;
	}

	/**
	 * @param string
	 */
	public void setLen(String string) {
		len = string;
	}

	/**
	 * @param f
	 */
	public void setLongitude(Float f) {
		longitude = f;
	}

	/**
	 * @param i
	 */
	public void setOdsNumber(int i) {
		odsNumber = i;
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	/**
	 * @param string
	 */
	public void setPreviousBox(String string) {
		previousBox = string;
	}

	/**
	 * @param i
	 */
	public void setPreviousBoxPair(int i) {
		previousBoxPair = i;
	}

	/**
	 * @param string
	 */
	public void setPreviousCable(String string) {
		previousCable = string;
	}

	/**
	 * @param i
	 */
	public void setPreviousCablePair(int i) {
		previousCablePair = i;
	}

	/**
	 * @param i
	 */
	public void setPreviousCentralCode(int i) {
		previousCentralCode = i;
	}

	/**
	 * @param string
	 */
	public void setPreviousCentralDescription(String string) {
		previousCentralDescription = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousCloset(String string) {
		previousCloset = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousLen(String string) {
		previousLen = string;
	}

	/**
	 * @param i
	 */
	public void setPreviousPhoneNumber(int i) {
		previousPhoneNumber = i;
	}

	/**
	 * @param i
	 */
	public void setPreviousPrimaryDistributor(long i) {
		previousPrimaryDistributor = i;
	}

	/**
	 * @param string
	 */
	public void setPreviousPrimaryDistributorDescription(String string) {
		previousPrimaryDistributorDescription = string;
	}

	/**
	 * @param i
	 */
	public void setPreviousSecondaryDistributor(long i) {
		previousSecondaryDistributor = i;
	}

	/**
	 * @param string
	 */
	public void setPreviousSecondaryDistributorDescription(String string) {
		previousSecondaryDistributorDescription = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousStrip(String string) {
		previousStrip = string;
	}

	/**
	 * @param i
	 */
	public void setPreviousStripPair(int i) {
		previousStripPair = i;
	}

	/**
	 * @param i
	 */
	public void setPrimaryDistributor(long i) {
		primaryDistributor = i;
	}

	/**
	 * @param b
	 */
	public void setReserveResult(boolean b) {
		reserveResult = b;
	}

	/**
	 * @param i
	 */
	public void setResult(int i) {
		result = i;
	}

	/**
	 * @param i
	 */
	public void setSecundaryDistributor(long i) {
		secundaryDistributor = i;
	}

	/**
	 * @param string
	 */
	public void setStrip(String string) {
		strip = string;
	}

	/**
	 * @param i
	 */
	public void setStripPair(int i) {
		stripPair = i;
	}

	/**
	 * @return Returns the horizontalPosition.
	 */
	public String getHorizontalPosition() {
		return horizontalPosition;
	}
	/**
	 * @param horizontalPosition The horizontalPosition to set.
	 */
	public void setHorizontalPosition(String horizontalPosition) {
		this.horizontalPosition = horizontalPosition;
	}
	/**
	 * @return Returns the previousHorizontalPosition.
	 */
	public String getPreviousHorizontalPosition() {
		return previousHorizontalPosition;
	}
	/**
	 * @param previousHorizontalPosition The previousHorizontalPosition to set.
	 */
	public void setPreviousHorizontalPosition(String previousHorizontalPosition) {
		this.previousHorizontalPosition = previousHorizontalPosition;
	}
	/**
	 * @return Returns the boxDedicated.
	 */
	public String getBoxDedicated() {
		return boxDedicated;
	}
	/**
	 * @param boxDedicated The boxDedicated to set.
	 */
	public void setBoxDedicated(String boxDedicated) {
		this.boxDedicated = boxDedicated;
	}
	/**
	 * @return Devuelve fttcfabr.
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
