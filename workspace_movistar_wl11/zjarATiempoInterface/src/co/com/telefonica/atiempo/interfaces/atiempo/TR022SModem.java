//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR022SModem.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author ylapchik
* @version $Revision: 1.1 $
*/
public class TR022SModem implements Serializable {

	private String serial;
	private String brand;
	private String model;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR022SModem) {
			TR022SModem other = (TR022SModem) arg0;
			return EqualityUtilities.equals(serial, other.serial) &&
				EqualityUtilities.equals(model, other.model) &&
					EqualityUtilities.equals(brand, other.brand);
		}
		return false;
	}
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
}
