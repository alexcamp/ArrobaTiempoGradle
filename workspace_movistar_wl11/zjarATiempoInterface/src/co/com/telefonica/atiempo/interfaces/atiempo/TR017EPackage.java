//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR017EPackage.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
 * @author gminos
 * @version $Revision: 1.1 $
 */
public class TR017EPackage implements Serializable {
	
	private long productServiceCode;
	private long commercialOperation;

	public long getProductServiceCode() {
		return productServiceCode;
	}

	public void setProductServiceCode(long l) {
		productServiceCode = l;
	}
	
	public long getCommercialOperation() {
		return commercialOperation;
	}

	public void setCommercialOperation(long l) {
		commercialOperation = l;
	}

	public int hashCode() {
		return (int)productServiceCode;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR017EPackage) {
			TR017EPackage other = (TR017EPackage) arg0;
			return productServiceCode == other.productServiceCode &&
				commercialOperation == other.commercialOperation;
		}
		return false;
	}	

}
