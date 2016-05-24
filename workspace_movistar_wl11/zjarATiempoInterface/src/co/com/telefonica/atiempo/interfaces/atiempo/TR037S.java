//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR037S.java,v 1.1 2011/03/30 18:25:34 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR037S extends ResponseHeader{
	
	private long atiempoRequestNumber;
	private String serviceNumber;
	private String code;
	private String description;
	
	private String ip;
	private String subrack;
	private String slot;
	private String portId;
	private String pots;
	private String adsl;	
	private String action;
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR037S) {
			TR037S other = (TR037S) arg0;
			return super.equals(arg0)
				&& (atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(serviceNumber, other.serviceNumber)
				&& EqualityUtilities.equals(slot, other.slot)
				&& EqualityUtilities.equals(subrack, other.subrack)
				&& EqualityUtilities.equals(code, other.code)
				&& EqualityUtilities.equals(pots, other.pots)
				&& EqualityUtilities.equals(adsl, other.adsl)
				&& EqualityUtilities.equals(ip, other.ip)
				&& EqualityUtilities.equals(portId, other.portId)
				&& EqualityUtilities.equals(description, other.description)
				&& EqualityUtilities.equals(action, other.action)
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
	public String getCode() {
		return code;
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
	public void setCode(String string) {
		code = string;
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
