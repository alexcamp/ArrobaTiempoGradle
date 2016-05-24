//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR060E.java 2013/09/23 14:46:28 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
* @author dcardena
*
*/
public class TR602E extends RequestHeader {
	private boolean processType;
	private String fatherAccount;
	private String password;
	private long atiempoRequestNumber;
	private String phoneNumber;
	private String locality;
	private long areaCode;
	private boolean controlAccount;
	private boolean precableada;
	private Collection specialService;
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR602E) {
			TR602E other = (TR602E) arg0;
			return super.equals(arg0)
				&& (processType == other.processType)
				&& EqualityUtilities.equals(fatherAccount, other.fatherAccount)
				&& EqualityUtilities.equals(password, other.password)
			&& (atiempoRequestNumber == other.atiempoRequestNumber)
			&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
			&& EqualityUtilities.equals(locality, other.locality)
			&& (areaCode == other.areaCode)
			&& (controlAccount == other.controlAccount)
			&& (precableada == other.precableada)
&& EqualityUtilities.equals(specialService, other.specialService);
			}
		return false;
	}
	
	/**
	 * @return Devuelve areaCode.
	 */
	public long getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode El areaCode a establecer.
	 */
	public void setAreaCode(long areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return Devuelve atiempoRequestRumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestRumber El atiempoRequestRumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestRumber) {
		this.atiempoRequestNumber = atiempoRequestRumber;
	}
	/**
	 * @return Devuelve fatherAccount.
	 */
	public String getFatherAccount() {
		return fatherAccount;
	}
	/**
	 * @param fatherAccount El fatherAccount a establecer.
	 */
	public void setFatherAccount(String fatherAccount) {
		this.fatherAccount = fatherAccount;
	}
	/**
	 * @return Devuelve locality.
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * @param locality El locality a establecer.
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * @return Devuelve password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password El password a establecer.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Devuelve phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber El phoneNumber a establecer.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Devuelve processType.
	 */
	public boolean isProcessType() {
		return processType;
	}
	/**
	 * @param processType El processType a establecer.
	 */
	public void setProcessType(boolean a) {
		this.processType = a;
	}
	
	
	
	/**
	 * @return Devuelve specialService.
	 */
	public Collection getSpecialService() {
		return specialService;
	}
	/**
	 * @param specialService El specialService a establecer.
	 */
	public void setSpecialService(Collection specialService) {
		this.specialService = specialService;
	}
	/**
	 * @return Devuelve cuentControl.
	 */
	public boolean isControlAccount() {
		return controlAccount;
	}
	/**
	 * @param cuentControl El cuentControl a establecer.
	 */
	public void setControlAccount(boolean cuentControl) {
		this.controlAccount = cuentControl;
	}
	/**
	 * @return Devuelve precableada.
	 */
	public boolean isPrecableada() {
		return precableada;
	}
	/**
	 * @param precableada El precableada a establecer.
	 */
	public void setPrecableada(boolean precableada) {
		this.precableada = precableada;
	}
}
