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
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR514S extends ResponseHeader2{

	private long distributor;
	private String distributorDescription;
	private String closet;
	private String box;
	private int boxPair;
	private long primaryDistributor;
	private String primaryDistributorDescription;
	private String strip;
	private int stripPair;
	private int central;
	private String centralDescription;
	private String cable;
	private int cablePair;
	private int phoneNumber;
	private String distributorAddress;
	private String closetAddress;
	private String boxAddress;
	private String len;       
	private Collection specialServices;									
	private Collection dslams;
	private String horizontalPosition;	
	private String boxType;
	private int boxDistance;
	private Float latitude;
	private Float longitude;
	private boolean eoc;
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
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR514S) {
				TR514S other = (TR514S) arg0;
				return super.equals(arg0)
					&& (distributor == other.distributor)
					&& EqualityUtilities.equals(distributorDescription,distributorDescription)
					&& EqualityUtilities.equals(closet,other.closet)
					&& EqualityUtilities.equals(horizontalPosition,other.horizontalPosition)
					&& EqualityUtilities.equals(box,other.box)
					&& (boxPair == other.boxPair)
					&& (primaryDistributor == other.primaryDistributor)
					&& EqualityUtilities.equals(primaryDistributorDescription,other.primaryDistributorDescription)
					&& EqualityUtilities.equals(strip,other.strip)
					&& (stripPair == other.stripPair)
					&& (central == other.central)
					&& EqualityUtilities.equals(centralDescription,other.centralDescription)
					&& EqualityUtilities.equals(cable,other.cable)
					&& (cablePair == other.cablePair)
					&& (phoneNumber == other.phoneNumber)
					&& EqualityUtilities.equals(distributorAddress,other.distributorAddress)
					&& EqualityUtilities.equals(closetAddress,other.closetAddress)
					&& EqualityUtilities.equals(boxAddress,other.boxAddress)
					&& EqualityUtilities.equals(len,other.len)
					&& EqualityUtilities.equals(specialServices,other.specialServices)
					&& EqualityUtilities.equals(dslams,other.dslams)
					&& EqualityUtilities.equals(boxType,other.boxType)
					&& (boxDistance == other.boxDistance)
					&& EqualityUtilities.equals(latitude,other.latitude)
					&& EqualityUtilities.equals(longitude,other.longitude)
					&& (eoc == other.eoc)
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
	public String getBoxType() {
		return boxType;
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
	public int getCablePair() {
		return cablePair;
	}

	/**
	 * @return
	 */
	public int getCentral() {
		return central;
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
	public long getDistributor() {
		return distributor;
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
	public String getDistributorDescription() {
		return distributorDescription;
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
	public boolean isEoc() {
		return eoc;
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
	public int getPhoneNumber() {
		return phoneNumber;
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
	public String getPrimaryDistributorDescription() {
		return primaryDistributorDescription;
	}

	/**
	 * @return
	 */
	public Collection getSpecialServices() {
		return specialServices;
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
	 * @param string
	 */
	public void setBoxType(String string) {
		boxType = string;
	}

	/**
	 * @param string
	 */
	public void setCable(String string) {
		cable = string;
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
	public void setCentral(int i) {
		central = i;
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
	 * @param i
	 */
	public void setDistributor(long i) {
		distributor = i;
	}

	/**
	 * @param string
	 */
	public void setDistributorAddress(String string) {
		distributorAddress = string;
	}

	/**
	 * @param string
	 */
	public void setDistributorDescription(String string) {
		distributorDescription = string;
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
	public void setEoc(boolean b) {
		eoc = b;
	}

	/**
	 * @param float1
	 */
	public void setLatitude(Float float1) {
		latitude = float1;
	}

	/**
	 * @param string
	 */
	public void setLen(String string) {
		len = string;
	}

	/**
	 * @param float1
	 */
	public void setLongitude(Float float1) {
		longitude = float1;
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	/**
	 * @param i
	 */
	public void setPrimaryDistributor(long i) {
		primaryDistributor = i;
	}

	/**
	 * @param string
	 */
	public void setPrimaryDistributorDescription(String string) {
		primaryDistributorDescription = string;
	}

	/**
	 * @param collection
	 */
	public void setSpecialServices(Collection collection) {
		specialServices = collection;
	}

	/**
	 * @param string
	 */
	public void setStrip(String string) {
		strip = string;
	}

	/**
	 * @param string
	 */
	public void setStripPair(int i) {
		stripPair = i;
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
