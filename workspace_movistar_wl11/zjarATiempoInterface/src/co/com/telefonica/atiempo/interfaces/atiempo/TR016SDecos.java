//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR016SDecos.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR016SDecos implements Serializable {

	private String casId;
	private String decoMarca;
	

	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR016SDecos) {
			TR016SDecos other = (TR016SDecos) arg0;
			return EqualityUtilities.equals(casId, other.casId) &&
					EqualityUtilities.equals(decoMarca, other.decoMarca);
		}
		return false;
	}

	
	public String getCasId() {
		return casId;
	}
	public void setCasId(String casId) {
		this.casId = casId;
	}
	public String getDecoMarca() {
		return decoMarca;
	}
	public void setDecoMarca(String decoMarca) {
		this.decoMarca = decoMarca;
	}
}
