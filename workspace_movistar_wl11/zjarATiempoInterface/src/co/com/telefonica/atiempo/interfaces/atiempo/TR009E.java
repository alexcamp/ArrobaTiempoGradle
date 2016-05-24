//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR009E.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR009E extends ResponseHeader {
	private int phoneNumber;
	private int state;
	private long codIncAtis;
	
	
	public long getCodIncAtis() {
		return codIncAtis;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public int getState() {
		return state;
	}

	public void setCodIncAtis(long l) {
		codIncAtis = l;
	}

	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setState(int i) {
		state = i;
	}
	
	public int hashCode(){
		return phoneNumber;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR009E) {
			TR009E other = (TR009E) arg0;
			return super.equals(arg0)
				&& phoneNumber == other.phoneNumber
				&& state == other.state
				&& codIncAtis == other.codIncAtis;
		}
		return false;
	}

}
