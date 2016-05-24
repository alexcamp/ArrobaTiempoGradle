/*
 * Created on Feb 18, 201
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendez
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR049EWideBand implements Serializable {

	private String ticketNumber;

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

	private String portModificationFlag;

	private String previousIp;

	private String previousSubrack;
	
	private String previousSlot;

	private String previousPortId;

	private String previousVpiVci;

	private String previousVpiVciNetwork;

	private String previousPots;

	private String previousAdsl;

	private String previousIpLanMask;

	private String previousIpLan;
	
	private String previousIpWan;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR049EWideBand) {
			TR049EWideBand other = (TR049EWideBand) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(subrack, other.subrack)
					&& EqualityUtilities.equals(slot, other.slot)
					&& EqualityUtilities.equals(previousSubrack,
							other.previousSubrack)
					&& EqualityUtilities.equals(previousSlot,
							other.previousSlot)
					&& EqualityUtilities.equals(ticketNumber,
							other.ticketNumber)
					&& EqualityUtilities.equals(vpiVci, other.vpiVci)
					&& EqualityUtilities.equals(vpiVciNetwork,
							other.vpiVciNetwork)
					&& EqualityUtilities.equals(pots, other.pots)
					&& EqualityUtilities.equals(adsl, other.adsl)
					&& EqualityUtilities.equals(ip, other.ip)
					&& EqualityUtilities.equals(portId, other.portId)
					&& EqualityUtilities.equals(ipLanMask, other.ipLanMask)
					&& EqualityUtilities.equals(ipLan, other.ipLan)
					&& EqualityUtilities.equals(ipWan, other.ipWan)
					&& EqualityUtilities.equals(portModificationFlag,
							other.portModificationFlag)
					&& EqualityUtilities.equals(previousIp, other.previousIp)
					&& EqualityUtilities.equals(previousVpiVci,
							other.previousVpiVci)
					&& EqualityUtilities.equals(previousVpiVciNetwork,
							other.previousVpiVciNetwork)
					&& EqualityUtilities.equals(previousPots,
							other.previousPots)
					&& EqualityUtilities.equals(previousAdsl,
							other.previousAdsl)
					&& EqualityUtilities.equals(previousPortId,
							other.previousPortId)
					&& EqualityUtilities.equals(previousIpWan,
							other.previousIpWan)
					&& EqualityUtilities.equals(previousIpLan,
							other.previousIpLan)
					&& EqualityUtilities.equals(previousIpLanMask,
							other.previousIpLanMask);
		} else {
			return false;
		}
	}

	/**
	 * @return Returns the adsl.
	 */
	public String getAdsl() {
		return adsl;
	}

	/**
	 * @param adsl
	 *            The adsl to set.
	 */
	public void setAdsl(String adsl) {
		this.adsl = adsl;
	}

	/**
	 * @return Returns the ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            The ip to set.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return Returns the ipLan.
	 */
	public String getIpLan() {
		return ipLan;
	}

	/**
	 * @param ipLan
	 *            The ipLan to set.
	 */
	public void setIpLan(String ipLan) {
		this.ipLan = ipLan;
	}

	/**
	 * @return Returns the ipLanMask.
	 */
	public String getIpLanMask() {
		return ipLanMask;
	}

	/**
	 * @param ipLanMask
	 *            The ipLanMask to set.
	 */
	public void setIpLanMask(String ipLanMask) {
		this.ipLanMask = ipLanMask;
	}

	/**
	 * @return Returns the ipWan.
	 */
	public String getIpWan() {
		return ipWan;
	}

	/**
	 * @param ipWan
	 *            The ipWan to set.
	 */
	public void setIpWan(String ipWan) {
		this.ipWan = ipWan;
	}

	/**
	 * @return Returns the portId.
	 */
	public String getPortId() {
		return portId;
	}

	/**
	 * @param portId
	 *            The portId to set.
	 */
	public void setPortId(String portId) {
		this.portId = portId;
	}

	/**
	 * @return Returns the portModificationFlag.
	 */
	public String getPortModificationFlag() {
		return portModificationFlag;
	}

	/**
	 * @param portModificationFlag
	 *            The portModificationFlag to set.
	 */
	public void setPortModificationFlag(String portModificationFlag) {
		this.portModificationFlag = portModificationFlag;
	}

	/**
	 * @return Returns the pots.
	 */
	public String getPots() {
		return pots;
	}

	/**
	 * @param pots
	 *            The pots to set.
	 */
	public void setPots(String pots) {
		this.pots = pots;
	}

	/**
	 * @return Returns the previousAdsl.
	 */
	public String getPreviousAdsl() {
		return previousAdsl;
	}

	/**
	 * @param previousAdsl
	 *            The previousAdsl to set.
	 */
	public void setPreviousAdsl(String previousAdsl) {
		this.previousAdsl = previousAdsl;
	}

	/**
	 * @return Returns the previousIp.
	 */
	public String getPreviousIp() {
		return previousIp;
	}

	/**
	 * @param previousIp
	 *            The previousIp to set.
	 */
	public void setPreviousIp(String previousIp) {
		this.previousIp = previousIp;
	}

	/**
	 * @return Returns the previousIpLan.
	 */
	public String getPreviousIpLan() {
		return previousIpLan;
	}

	/**
	 * @param previousIpLan
	 *            The previousIpLan to set.
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
	 * @param previousIpLanMask
	 *            The previousIpLanMask to set.
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
	 * @param previousIpWan
	 *            The previousIpWan to set.
	 */
	public void setPreviousIpWan(String previousIpWan) {
		this.previousIpWan = previousIpWan;
	}

	/**
	 * @return Returns the previousPortId.
	 */
	public String getPreviousPortId() {
		return previousPortId;
	}

	/**
	 * @param previousPortId
	 *            The previousPortId to set.
	 */
	public void setPreviousPortId(String previousPortId) {
		this.previousPortId = previousPortId;
	}

	/**
	 * @return Returns the previousPots.
	 */
	public String getPreviousPots() {
		return previousPots;
	}

	/**
	 * @param previousPots
	 *            The previousPots to set.
	 */
	public void setPreviousPots(String previousPots) {
		this.previousPots = previousPots;
	}

	/**
	 * @return Returns the previousSlot.
	 */
	public String getPreviousSlot() {
		return previousSlot;
	}

	/**
	 * @param previousSlot
	 *            The previousSlot to set.
	 */
	public void setPreviousSlot(String previousSlot) {
		this.previousSlot = previousSlot;
	}

	/**
	 * @return Returns the previousSubrack.
	 */
	public String getPreviousSubrack() {
		return previousSubrack;
	}

	/**
	 * @param previousSubrack
	 *            The previousSubrack to set.
	 */
	public void setPreviousSubrack(String previousSubrack) {
		this.previousSubrack = previousSubrack;
	}

	/**
	 * @return Returns the previousVpiVci.
	 */
	public String getPreviousVpiVci() {
		return previousVpiVci;
	}

	/**
	 * @param previousVpiVci
	 *            The previousVpiVci to set.
	 */
	public void setPreviousVpiVci(String previousVpiVci) {
		this.previousVpiVci = previousVpiVci;
	}

	/**
	 * @return Returns the previousVpiVciNetwork.
	 */
	public String getPreviousVpiVciNetwork() {
		return previousVpiVciNetwork;
	}

	/**
	 * @param previousVpiVciNetwork
	 *            The previousVpiVciNetwork to set.
	 */
	public void setPreviousVpiVciNetwork(String previousVpiVciNetwork) {
		this.previousVpiVciNetwork = previousVpiVciNetwork;
	}

	/**
	 * @return Returns the slot.
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @param slot
	 *            The slot to set.
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}

	/**
	 * @return Returns the subrack.
	 */
	public String getSubrack() {
		return subrack;
	}

	/**
	 * @param subrack
	 *            The subrack to set.
	 */
	public void setSubrack(String subrack) {
		this.subrack = subrack;
	}

	/**
	 * @return Returns the ticketNumber.
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @param ticketNumber
	 *            The ticketNumber to set.
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * @return Returns the vpiVci.
	 */
	public String getVpiVci() {
		return vpiVci;
	}

	/**
	 * @param vpiVci
	 *            The vpiVci to set.
	 */
	public void setVpiVci(String vpiVci) {
		this.vpiVci = vpiVci;
	}

	/**
	 * @return Returns the vpiVciNetwork.
	 */
	public String getVpiVciNetwork() {
		return vpiVciNetwork;
	}

	/**
	 * @param vpiVciNetwork
	 *            The vpiVciNetwork to set.
	 */
	public void setVpiVciNetwork(String vpiVciNetwork) {
		this.vpiVciNetwork = vpiVciNetwork;
	}
}