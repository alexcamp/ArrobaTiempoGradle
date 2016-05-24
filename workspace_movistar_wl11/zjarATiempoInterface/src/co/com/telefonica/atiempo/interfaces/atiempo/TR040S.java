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
public class TR040S extends ResponseHeader{

	private String statusCode;
	private long atiempoRequestNumber;
	private String ticketNumber;
	
	private String ip;
	private String slot;
	private String subrack;
	private String vpiVci;
	private String vpiVciNetwork;
	private String pots;
	private String adsl;
	
	private String portId;
	private String ipLanMask;
	private String ipLan;
	private String ipWan;
	private TypeValueList others;
	private String portModificationFlag;
	
	private String previousIp;
	private String previousPortId;
	private String previousSlot;
	private String previousSubrack;
	private String previousVpiVci;
	private String previousVpiVciNetwork;
	private String previousPots;
	private String previousAdsl;
	private String action;

	private String previousIpWan;
	private String previousIpLan;
	private String previousIpLanMask;

	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR040S) {
			TR040S other = (TR040S) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(statusCode,other.statusCode)
				&& EqualityUtilities.equals(subrack,other.subrack)
				&& EqualityUtilities.equals(slot,other.slot)
				&& EqualityUtilities.equals(previousSubrack,other.previousSubrack)
				&& EqualityUtilities.equals(previousSlot,other.previousSlot)
				&& EqualityUtilities.equals(ticketNumber,other.ticketNumber)
				&& EqualityUtilities.equals(vpiVci,other.vpiVci)
				&& EqualityUtilities.equals(vpiVciNetwork,other.vpiVciNetwork)
				&& EqualityUtilities.equals(pots,other.pots)
				&& EqualityUtilities.equals(adsl,other.adsl)
				&& EqualityUtilities.equals(ip,other.ip)
				&& EqualityUtilities.equals(portId,other.portId)
				&& EqualityUtilities.equals(ipLanMask,other.ipLanMask)
				&& EqualityUtilities.equals(ipLan,other.ipLan)
				&& EqualityUtilities.equals(ipWan,other.ipWan)
				&& EqualityUtilities.equals(others,other.others)
				&& EqualityUtilities.equals(portModificationFlag,other.portModificationFlag)
				&& EqualityUtilities.equals(previousIp,other.previousIp)
				&& EqualityUtilities.equals(previousVpiVci,other.previousVpiVci)
				&& EqualityUtilities.equals(previousVpiVciNetwork,other.previousVpiVciNetwork)
				&& EqualityUtilities.equals(previousPots,other.previousPots)
				&& EqualityUtilities.equals(previousAdsl,other.previousAdsl)
				&& EqualityUtilities.equals(previousPortId,other.previousPortId)
				&& EqualityUtilities.equals(action,other.action)
				&& EqualityUtilities.equals(previousIpWan,other.previousIpWan)
				&& EqualityUtilities.equals(previousIpLan,other.previousIpLan)
				&& EqualityUtilities.equals(previousIpLanMask,other.previousIpLanMask)
				;
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
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
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
	public String getPortModificationFlag() {
		return portModificationFlag;
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
	public String getPreviousAdsl() {
		return previousAdsl;
	}

	/**
	 * @return
	 */
	public String getPreviousIp() {
		return previousIp;
	}

	/**
	 * @return
	 */
	public String getPreviousPortId() {
		return previousPortId;
	}

	/**
	 * @return
	 */
	public String getPreviousPots() {
		return previousPots;
	}

	/**
	 * @return
	 */
	public String getPreviousVpiVci() {
		return previousVpiVci;
	}

	/**
	 * @return
	 */
	public String getPreviousVpiVciNetwork() {
		return previousVpiVciNetwork;
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
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
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
	public void setPortModificationFlag(String string) {
		portModificationFlag = string;
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
	public void setPreviousAdsl(String string) {
		previousAdsl = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousIp(String string) {
		previousIp = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousPortId(String string) {
		previousPortId = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousPots(String string) {
		previousPots = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousVpiVci(String string) {
		previousVpiVci = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousVpiVciNetwork(String string) {
		previousVpiVciNetwork = string;
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
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string) {
		statusCode = string;
	}

	/**
	 * @return
	 */
	public String getPreviousSlot() {
		return previousSlot;
	}

	/**
	 * @return
	 */
	public String getPreviousSubrack() {
		return previousSubrack;
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
	public void setPreviousSlot(String string) {
		previousSlot = string;
	}

	/**
	 * @param string
	 */
	public void setPreviousSubrack(String string) {
		previousSubrack = string;
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
	/**
	 * @return Returns the previousIpLan.
	 */
	public String getPreviousIpLan() {
		return previousIpLan;
	}
	/**
	 * @param previousIpLan The previousIpLan to set.
	 */
	public void setPreviousIpLan(String previousIpLan) {
		this.previousIpLan = previousIpLan;
	}
	/**
	 * @return Returns the previousIpLanMask.
	 */
	public String getPreviousIpLanMask() {
		return previousIpLanMask;
	}
	/**
	 * @param previousIpLanMask The previousIpLanMask to set.
	 */
	public void setPreviousIpLanMask(String previousIpLanMask) {
		this.previousIpLanMask = previousIpLanMask;
	}
	/**
	 * @return Returns the previousIpWan.
	 */
	public String getPreviousIpWan() {
		return previousIpWan;
	}
	/**
	 * @param previousIpWan The previousIpWan to set.
	 */
	public void setPreviousIpWan(String previousIpWan) {
		this.previousIpWan = previousIpWan;
	}
	}
