//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: SpecialService.java,v 1.1 2011/03/30 18:21:05 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class SpecialService implements Serializable {
	
	private String specialService;

	public int hashCode() {
		return 1;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof SpecialService) {
			SpecialService other = (SpecialService) arg0;
			return EqualityUtilities.equals(specialService,other.specialService);

		}
		return false;
	}

	/**
	 * @return
	 */
	public String getSpecialService() {
		return specialService;
	}


	/**
	 * @param i
	 */
	public void setSpecialService(String i) {
		specialService = i;
	}

}
