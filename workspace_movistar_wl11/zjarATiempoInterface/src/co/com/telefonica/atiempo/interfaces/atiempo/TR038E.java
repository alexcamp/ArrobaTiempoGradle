//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR038E.java,v 1.1 2011/03/30 18:25:34 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;
import java.util.Collection;
/**
* @author ylapchik
* @version $Revision: 1.1 $
*/
public class TR038E extends RequestHeader{
	
	private long atiempoRequestNumber;	
	private String customerSegment;
	private long serviceType;
	private String ipServiceType;
	private long previousServiceType;
	private String serviceNumber;
	private String terminalNumber;
	private Collection rangeZoneList;
	private TypeValueList pssbaDataList;
	private TypeValueList pssbaRollbackDataList;
	private TypeValueList others;
	private String ipAmount;
	private String comercialOperation;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof TR038E) {
			TR038E other = (TR038E) arg0;
			return super.equals(arg0)
				&& (this.atiempoRequestNumber == other.atiempoRequestNumber)
				&& EqualityUtilities.equals(this.customerSegment,other.customerSegment)
				&& (this.serviceType == other.serviceType)
				&& (this.ipServiceType == other.ipServiceType)
				&& (this.previousServiceType == other.previousServiceType)
				&& EqualityUtilities.equals(this.serviceNumber,other.serviceNumber)
				&& EqualityUtilities.equals(this.terminalNumber,other.terminalNumber)
				&& EqualityUtilities.equals(this.rangeZoneList,other.rangeZoneList)
				&& EqualityUtilities.equals(this.pssbaDataList,other.pssbaDataList)
				&& EqualityUtilities.equals(this.others,other.others)
				&& EqualityUtilities.equals(this.pssbaRollbackDataList,other.pssbaRollbackDataList)
				;
		}
		return false;
	}


	public long getPreviousServiceType() {
		return previousServiceType;
	}
	public void setPreviousServiceType(long previousServiceType) {
		this.previousServiceType = previousServiceType;
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
	public long getServiceType() {
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
	public void setCustomerSegment(String str) {
		customerSegment = str;
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
	public void setServiceType(long l) {
		serviceType = l;
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
	public TypeValueList getPssbaRollbackDataList() {
		return pssbaRollbackDataList;
	}

	/**
	 * @param collection
	 */
	public void setPssbaRollbackDataList(TypeValueList collection) {
		pssbaRollbackDataList = collection;
	}

	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param i
	 */
	public void setAtiempoRequestNumber(long i) {
		atiempoRequestNumber = i;
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
	 * @return Returns the comercialOperation.
	 */
	public String getComercialOperation() {
		return comercialOperation;
	}
	/**
	 * @param comercialOperation The comercialOperation to set.
	 */
	public void setComercialOperation(String comercialOperation) {
		this.comercialOperation = comercialOperation;
	}
	/**
	 * @return Returns the ipAmount.
	 */
	public String getIpAmount() {
		return ipAmount;
	}
	/**
	 * @param ipAmount The ipAmount to set.
	 */
	public void setIpAmount(String ipAmount) {
		this.ipAmount = ipAmount;
	}
}
