//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR035S.java,v 1.1 2011/03/30 18:25:35 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR035S extends ResponseHeader{
	
	private String serviceNumber;
	private String code;
	private String description;
	private String codeRangeZone;
	
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
	private String action;
	
	private Long PSAnterior;
	private Long PSActual;
	

	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR035S) {
			TR035S other = (TR035S) arg0;
			boolean s = super.equals(arg0)				
				&& EqualityUtilities.equals(serviceNumber, other.serviceNumber)
				&& EqualityUtilities.equals(code, other.code)
				&& EqualityUtilities.equals(description, other.description)
				&& EqualityUtilities.equals(codeRangeZone, other.codeRangeZone)
				&& EqualityUtilities.equals(ip, other.ip)
				&& EqualityUtilities.equals(subrack, other.subrack)
				&& EqualityUtilities.equals(slot, other.slot)
				&& EqualityUtilities.equals(portId, other.portId)
				&& EqualityUtilities.equals(adsl, other.adsl)
				&& EqualityUtilities.equals(pots, other.pots)
				&& EqualityUtilities.equals(vpiVciNetwork, other.vpiVciNetwork)
				&& EqualityUtilities.equals(vpiVci, other.vpiVci)
				&& EqualityUtilities.equals(ipLanMask, other.ipLanMask)
				&& EqualityUtilities.equals(ipLan, other.ipLan)
				&& EqualityUtilities.equals(ipWan, other.ipWan)
				&& EqualityUtilities.equals(action, other.action)
				&& EqualityUtilities.equals(PSAnterior, other.PSAnterior)
				&& EqualityUtilities.equals(PSActual, other.PSActual)
				;
				return s;
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
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getCodeRangeZone() {
		return codeRangeZone;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
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
	public String getServiceNumber() {
		return serviceNumber;
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
	public void setCode(String string) {
		code = string;
	}

	/**
	 * @param string
	 */
	public void setCodeRangeZone(String string) {
		codeRangeZone = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
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
	public void setServiceNumber(String string) {
		serviceNumber = string;
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
	 * @return Devuelve pSActual.
	 */
	public Long getPSActual() {
		return PSActual;
	}
	/**
	 * @param actual El pSActual a establecer.
	 */
	public void setPSActual(Long actual) {
		PSActual = actual;
	}
	/**
	 * @return Devuelve pSAnterior.
	 */
	public Long getPSAnterior() {
		return PSAnterior;
	}
	/**
	 * @param anterior El pSAnterior a establecer.
	 */
	public void setPSAnterior(Long anterior) {
		PSAnterior = anterior;
	}
}
