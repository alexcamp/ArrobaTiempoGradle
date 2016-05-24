//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR013S.java,v 1.1 2011/03/30 18:24:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR013S extends ResponseHeader {
	private long atisRequestNumber;
	private int status;
	private Collection errors;
	private String actualPort;
	private String actualPots;
	private String actualAdsl;
	private String actualLanMask;
	private String actualDslamIp;
	private String actualLanIp;
	private String actualWanIp;
	private String actualFrame;
	private String actualVpiVci;
	private String actualVpiVciNetwork;
	private String actualSlot;
	
	private String newPortNumber;
	private String newPotsNumber;
	private String newAdslNumber;
	private String newLanMaskNumber;
	private String newDslamIpSelectNumber;
	private String newClientLanIpNumber;
	private String newClientWanIpNumber;
	private String newFrameNumber;
	
	private String newVpiVci;
	private String newVpiVciNetwork;
	private String newSlot;
	private String fatherEmail;
	
	
	public String getActualAdsl() {
		return actualAdsl;
	}
	public String getActualDslamIp() {
		return actualDslamIp;
	}
	public String getActualFrame() {
		return actualFrame;
	}
	public String getActualLanIp() {
		return actualLanIp;
	}
	public String getActualLanMask() {
		return actualLanMask;
	}
	public String getActualPort() {
		return actualPort;
	}
	public String getActualSlot() {
		return actualSlot;
	}
	public String getActualVpiVci() {
		return actualVpiVci;
	}
	public String getActualVpiVciNetwork() {
		return actualVpiVciNetwork;
	}
	public String getActualWanIp() {
		return actualWanIp;
	}
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public Collection getErrors() {
		return errors;
	}
	public String getNewAdslNumber() {
		return newAdslNumber;
	}
	public String getNewClientLanIpNumber() {
		return newClientLanIpNumber;
	}
	public String getNewClientWanIpNumber() {
		return newClientWanIpNumber;
	}
	public String getNewDslamIpSelectNumber() {
		return newDslamIpSelectNumber;
	}
	public String getNewFrameNumber() {
		return newFrameNumber;
	}
	public String getNewLanMaskNumber() {
		return newLanMaskNumber;
	}
	public String getNewPortNumber() {
		return newPortNumber;
	}
	public String getNewPotsNumber() {
		return newPotsNumber;
	}
	public String getNewSlot() {
		return newSlot;
	}
	public String getNewVpiVci() {
		return newVpiVci;
	}
	public String getFatherEmail() {
		return fatherEmail;
	}
	public String getNewVpiVciNetwork() {
		return newVpiVciNetwork;
	}
	public int getStatus() {
		return status;
	}
	public void setActualAdsl(String string) {
		actualAdsl = string;
	}
	public void setActualDslamIp(String string) {
		actualDslamIp = string;
	}
	public void setActualFrame(String string) {
		actualFrame = string;
	}
	public void setActualLanIp(String string) {
		actualLanIp = string;
	}
	public void setActualLanMask(String string) {
		actualLanMask = string;
	}
	public void setActualPort(String string) {
		actualPort = string;
	}
	public void setActualSlot(String string) {
		actualSlot = string;
	}
	public void setActualVpiVci(String string) {
		actualVpiVci = string;
	}
	public void setActualVpiVciNetwork(String string) {
		actualVpiVciNetwork = string;
	}
	public void setActualWanIp(String string) {
		actualWanIp = string;
	}
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}
	public void setErrors(Collection collection) {
		errors = collection;
	}
	public void setNewAdslNumber(String string) {
		newAdslNumber = string;
	}
	public void setNewClientLanIpNumber(String string) {
		newClientLanIpNumber = string;
	}
	public void setNewClientWanIpNumber(String string) {
		newClientWanIpNumber = string;
	}
	public void setNewDslamIpSelectNumber(String string) {
		newDslamIpSelectNumber = string;
	}
	public void setNewFrameNumber(String string) {
		newFrameNumber = string;
	}
	public void setNewLanMaskNumber(String string) {
		newLanMaskNumber = string;
	}
	public void setNewPortNumber(String string) {
		newPortNumber = string;
	}
	public void setNewPotsNumber(String string) {
		newPotsNumber = string;
	}
	public void setNewSlot(String string) {
		newSlot = string;
	}
	public void setFatherEmail(String string) {
		fatherEmail = string;
	}
	public void setNewVpiVci(String string) {
		newVpiVci = string;
	}
	public void setNewVpiVciNetwork(String string) {
		newVpiVciNetwork = string;
	}
	public void setStatus(int i) {
		status = i;
	}
	public String getActualPots() {
		return actualPots;
	}
	public void setActualPots(String string) {
		actualPots = string;
	}
	
	public int hashCode(){
		return this.status;
	}

	public boolean equals(Object arg0){
		if (arg0 instanceof TR013S) {
			TR013S other = (TR013S) arg0;
			return super.equals(arg0) &&
				atisRequestNumber == other.atisRequestNumber &&
				status == other.status &&
				EqualityUtilities.equals(errors, other.errors) &&
				EqualityUtilities.equals(actualPort, other.actualPort) &&
				EqualityUtilities.equals(actualPots, other.actualPots) &&
				EqualityUtilities.equals(actualAdsl, other.actualAdsl) &&
				EqualityUtilities.equals(actualLanMask, other.actualLanMask) &&
				EqualityUtilities.equals(actualDslamIp,other.actualDslamIp) &&
				EqualityUtilities.equals(actualLanIp, other.actualLanIp) &&
				EqualityUtilities.equals(actualWanIp, other.actualWanIp) &&
				EqualityUtilities.equals(actualFrame, other.actualFrame) &&
				EqualityUtilities.equals(actualVpiVci, other.actualVpiVci) &&
				EqualityUtilities.equals(actualVpiVciNetwork, other.actualVpiVciNetwork) &&
				EqualityUtilities.equals(actualSlot, other.actualSlot) &&
				EqualityUtilities.equals(newPortNumber, other.newPortNumber) &&
				EqualityUtilities.equals(newPotsNumber, other.newPotsNumber) &&
				EqualityUtilities.equals(newAdslNumber, other.newAdslNumber) &&
				EqualityUtilities.equals(newLanMaskNumber, other.newLanMaskNumber) &&
				EqualityUtilities.equals(newDslamIpSelectNumber, other.newDslamIpSelectNumber) &&
				EqualityUtilities.equals(newClientLanIpNumber, other.newClientLanIpNumber) &&
				EqualityUtilities.equals(newClientWanIpNumber, other.newClientWanIpNumber) &&
				EqualityUtilities.equals(newFrameNumber, other.newFrameNumber) &&
				EqualityUtilities.equals(newVpiVci, other.newVpiVci) &&
				EqualityUtilities.equals(newVpiVciNetwork, other.newVpiVciNetwork) &&
				EqualityUtilities.equals(newSlot, other.newSlot) &&
				EqualityUtilities.equals(fatherEmail, other.fatherEmail);
		}
		return false;
	}


}
