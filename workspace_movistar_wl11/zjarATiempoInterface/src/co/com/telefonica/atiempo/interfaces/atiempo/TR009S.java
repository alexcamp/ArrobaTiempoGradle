//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR009S.java,v 1.1 2011/03/30 18:23:28 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR009S extends RequestHeader{
	private boolean response;
	private long codIncAtis;
	
	
	public long getCodIncAtis() {
		return codIncAtis;
	}

	public boolean isResponse() {
		return response;
	}

	public void setCodIncAtis(long l) {
		codIncAtis = l;
	}

	public void setResponse(boolean b) {
		response = b;
	}
	
	public int hashCode(){
		return 0;
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR009S) {
			TR009S other = (TR009S) arg0;
			return super.equals(arg0)
				&& response == other.response
				&& codIncAtis == other.codIncAtis;
		}
		return false;
	}

}
