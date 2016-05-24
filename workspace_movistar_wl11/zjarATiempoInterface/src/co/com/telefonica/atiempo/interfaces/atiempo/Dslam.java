//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Dslam.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class Dslam implements Serializable {
	
	private String ip;
	private String dslamType;
	private String port;
	private String pots;
	private String adsl;
	private String frame;

	public String getAdsl() {
		return adsl;
	}
	public String getDslamType() {
		return dslamType;
	}
	public String getFrame() {
		return frame;
	}
	public String getIp() {
		return ip;
	}
	public String getPort() {
		return port;
	}
	public String getPots() {
		return pots;
	}
	public void setAdsl(String string) {
		adsl = string;
	}
	public void setDslamType(String i) {
		dslamType = i;
	}
	public void setFrame(String string) {
		frame = string;
	}
	public void setIp(String string) {
		ip = string;
	}
	public void setPort(String string) {
		port = string;
	}
	public void setPots(String string) {
		pots = string;
	}
	
	public int hashCode() {
		return ip.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof Dslam) {
			Dslam other = (Dslam) arg0;
			return EqualityUtilities.equals(ip, other.ip) &&
				EqualityUtilities.equals(dslamType, other.dslamType) &&
				EqualityUtilities.equals(port, other.port) &&
				EqualityUtilities.equals(pots, other.pots) &&
				EqualityUtilities.equals(adsl, other.adsl) &&
				EqualityUtilities.equals(frame, other.frame);
		}
		
		return false;
	}
}
