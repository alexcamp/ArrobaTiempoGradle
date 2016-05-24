/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR032S extends ResponseHeader{

	private long atiempoRequestNumber;
	private String statusCode;
	private String ticketNumber;
	private String rangeZoneId;
	private String ip;
	private String subrack;
	private String slot;
	private String portId;	
	private String vpiVci;
	private String vpiVciNetwork;
	private String pots;
	private String adsl;
	private String ipLanMask;
	private String ipLan;
	private String ipWan;
	private TypeValueList others;
	private String action;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR032S) {
			TR032S other = (TR032S) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(statusCode,other.statusCode)
				&& EqualityUtilities.equals(ticketNumber,other.ticketNumber)
				&& EqualityUtilities.equals(rangeZoneId,other.rangeZoneId)
				&& EqualityUtilities.equals(ip,other.ip)
				&& EqualityUtilities.equals(adsl,other.adsl)
				&& EqualityUtilities.equals(pots,other.pots)
				&& EqualityUtilities.equals(vpiVci,other.vpiVci)
				&& EqualityUtilities.equals(vpiVciNetwork,other.vpiVciNetwork)
				&& EqualityUtilities.equals(slot,other.slot)
				&& EqualityUtilities.equals(subrack,other.subrack)			
				&& EqualityUtilities.equals(portId,other.portId)
				&& EqualityUtilities.equals(ipLanMask,other.ipLanMask)
				&& EqualityUtilities.equals(ipLan,other.ipLan)
				&& EqualityUtilities.equals(ipWan,other.ipWan)
				&& EqualityUtilities.equals(others,other.others)
				&& EqualityUtilities.equals(action,other.action);
		}
		return false;
	}
	

	/**
	 * @return
	 */
	public String getAdsl() {
		return adsl;
	}


	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return
	 */
	public String getIpLan() {
		return ipLan;
	}

	/**
	 * @return
	 */
	public String getIpLanMask() {
		return ipLanMask;
	}

	/**
	 * @return
	 */
	public String getIpWan() {
		return ipWan;
	}

	/**
	 * @return
	 */
	public TypeValueList getOthers() {
		return others;
	}

	/**
	 * @return
	 */
	public String getPortId() {
		return portId;
	}

	/**
	 * @return
	 */
	public String getPots() {
		return pots;
	}

	/**
	 * @return
	 */
	public String getRangeZoneId() {
		return rangeZoneId;
	}

	/**
	 * @return
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @return
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @return
	 */
	public String getVpiVci() {
		return vpiVci;
	}

	/**
	 * @return
	 */
	public String getVpiVciNetwork() {
		return vpiVciNetwork;
	}

	/**
	 * @param string
	 */
	public void setAdsl(String string) {
		adsl = string;
	}


	/**
	 * @param string
	 */
	public void setIp(String string) {
		ip = string;
	}

	/**
	 * @param string
	 */
	public void setIpLan(String string) {
		ipLan = string;
	}

	/**
	 * @param string
	 */
	public void setIpLanMask(String string) {
		ipLanMask = string;
	}

	/**
	 * @param string
	 */
	public void setIpWan(String string) {
		ipWan = string;
	}

	/**
	 * @param list
	 */
	public void setOthers(TypeValueList list) {
		others = list;
	}

	/**
	 * @param string
	 */
	public void setPortId(String string) {
		portId = string;
	}

	/**
	 * @param string
	 */
	public void setPots(String string) {
		pots = string;
	}

	/**
	 * @param string
	 */
	public void setRangeZoneId(String string) {
		rangeZoneId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string) {
		statusCode = string;
	}

	/**
	 * @param string
	 */
	public void setTicketNumber(String string) {
		ticketNumber = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVci(String string) {
		vpiVci = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciNetwork(String string) {
		vpiVciNetwork = string;
	}

	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

	/**
	 * @return
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @return
	 */
	public String getSubrack() {
		return subrack;
	}

	/**
	 * @param string
	 */
	public void setSlot(String string) {
		slot = string;
	}

	/**
	 * @param string
	 */
	public void setSubrack(String string) {
		subrack = string;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

}
