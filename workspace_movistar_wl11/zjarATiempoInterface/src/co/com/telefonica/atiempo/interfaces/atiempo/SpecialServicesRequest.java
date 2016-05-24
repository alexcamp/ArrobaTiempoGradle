//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SpecialServicesRequest.java,v 1.1 2011/03/30 18:21:05 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class SpecialServicesRequest extends SpecialService {
	
	private int action;

	public int hashCode() {
		return action;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof SpecialServicesRequest) {
			SpecialServicesRequest other = (SpecialServicesRequest) arg0;
			return super.equals(arg0)
				&& (action == other.action);

		}
		return false;
	}

	/**
	 * @return
	 */
	public int getAction() {
		return action;
	}


	/**
	 * @param i
	 */
	public void setAction(int i) {
		action = i;
	}


}
