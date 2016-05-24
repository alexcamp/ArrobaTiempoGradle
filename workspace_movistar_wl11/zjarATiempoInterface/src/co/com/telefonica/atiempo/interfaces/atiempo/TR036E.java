//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR036E.java,v 1.1 2011/03/30 18:25:35 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR036E extends RequestHeader{
	
	private long atiempoRequestNumber;	
	private String serviceNumber;
	private TypeValueList pssbaDataList;
	private TypeValueList others;

	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR036E) {
			TR036E other = (TR036E) arg0;
			return super.equals(arg0)
				&& (this.atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(this.serviceNumber,other.serviceNumber)
				&& EqualityUtilities.equals(this.pssbaDataList,other.pssbaDataList)
				&& EqualityUtilities.equals(this.others,other.others)
				;
		}
		return false;
	}



	/**
	 * @return
	 */
	public TypeValueList getPssbaDataList() {
		return pssbaDataList;
	}

	/**
	 * @return
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}

	/**
	 * @return
	 */
	public TypeValueList getOthers() {
		return others;
	}



	/**
	 * @param collection
	 */
	public void setPssbaDataList(TypeValueList collection) {
		pssbaDataList = collection;
	}

	/**
	 * @param string
	 */
	public void setServiceNumber(String string) {
		serviceNumber = string;
	}

	/**
	 * @param collection
	 */
	public void setOthers(TypeValueList collection) {
		others = collection;
	}

	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

}
