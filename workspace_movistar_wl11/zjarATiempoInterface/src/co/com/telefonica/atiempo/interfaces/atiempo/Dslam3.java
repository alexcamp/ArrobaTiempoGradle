//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Dslam3.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class Dslam3 implements Serializable {
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String string) {
		ip = string;
	}
	public int hashCode(){
		return ip.hashCode();
	}
	public boolean equals(Object arg0) {
	if (arg0 instanceof Dslam3) {
		Dslam3 other = (Dslam3) arg0;
		return EqualityUtilities.equals(ip, other.ip);
	}
	return false;
}

}
