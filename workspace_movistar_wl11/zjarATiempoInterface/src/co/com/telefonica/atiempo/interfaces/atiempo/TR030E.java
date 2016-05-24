//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR030E.java,v 1.1 2011/03/30 18:25:34 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;
import java.util.Collection;
/**
* @author ylapchik
* @version $Revision: 1.1 $
*/
public class TR030E extends RequestHeader{
	
	private long atiempoRequestNumber;	
	private String customerSegment;
	private String serviceType;
	private String ipServiceType;
	private String serviceNumber;
	private String terminalNumber;
	private Collection rangeZoneList;
	private TypeValueList pssbaDataList;
	private TypeValueList others;
	private String pcPadre;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR030E) {
			TR030E text = (TR030E) arg0;
			return super.equals(arg0)
				&& (this.atiempoRequestNumber == text.atiempoRequestNumber)
				&& EqualityUtilities.equals(this.customerSegment,text.customerSegment)
				&& EqualityUtilities.equals(this.pcPadre,text.pcPadre)
				&& EqualityUtilities.equals(this.serviceType,text.serviceType)
				&& (this.ipServiceType == text.ipServiceType)
				&& EqualityUtilities.equals(this.serviceNumber,text.serviceNumber)
				&& EqualityUtilities.equals(this.terminalNumber,text.terminalNumber)
				&& EqualityUtilities.equals(this.rangeZoneList,text.rangeZoneList)
				&& EqualityUtilities.equals(this.pssbaDataList,text.pssbaDataList)
				&& EqualityUtilities.equals(this.others,text.others)
				;
		}
		return false;
	}



	/**
	 * @return
	 */
	public String getCustomerSegment() {
		return customerSegment;
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
	public Collection getRangeZoneList() {
		return rangeZoneList;
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
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @return
	 */
	public String getTerminalNumber() {
		return terminalNumber;
	}

	/**
	 * @return
	 */
	public TypeValueList getOthers() {
		return others;
	}



	/**
	 * @param string
	 */
	public void setCustomerSegment(String string) {
		customerSegment = string;
	}


	/**
	 * @param collection
	 */
	public void setPssbaDataList(TypeValueList collection) {
		pssbaDataList = collection;
	}

	/**
	 * @param collection
	 */
	public void setRangeZoneList(Collection collection) {
		rangeZoneList = collection;
	}

	/**
	 * @param string
	 */
	public void setServiceNumber(String string) {
		serviceNumber = string;
	}

	/**
	 * @param string
	 */
	public void setServiceType(String string) {
		serviceType = string;
	}

	/**
	 * @param string
	 */
	public void setTerminalNumber(String string) {
		terminalNumber = string;
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

	
	/**
	 * @return Returns the ipServiceType.
	 */
	public String getIpServiceType() {
		return ipServiceType;
	}
	/**
	 * @param ipServiceType The ipServiceType to set.
	 */
	public void setIpServiceType(String ipServiceType) {
		this.ipServiceType = ipServiceType;
	}
	/**
	 * @return Devuelve pcPadre.
	 */
	public String getPcPadre() {
		return pcPadre;
	}
	/**
	 * @param pcPadre El pcPadre a establecer.
	 */
	public void setPcPadre(String pcPadre) {
		this.pcPadre = pcPadre;
	}
}
