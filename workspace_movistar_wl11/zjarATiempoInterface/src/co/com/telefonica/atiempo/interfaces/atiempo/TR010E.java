//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR010E.java,v 1.1 2011/03/30 18:24:04 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class TR010E extends RequestHeader {
	private String pathType;
	private int pathNumber;
	private String firstPathCharacters;
	private String secondPathCharacters;
	private String pathZone;
	private String pathType2;
	private int pathNumber2;
	private String firstPathCharacters2;
	private String secondPathCharacters2;
	private char pathZone2;
	private long atisRequestNumber;
	private int phoneNumber;
	private String clientName;
	private String clientDocument;
	private long productServiceCode;
	private long comercialOperation;
	private String installAddress;
	private String addressNumber;
	private char addressCharacters;
	private int department;
	private int city;
	private long useType;
	private long comercialProductType;
	private long comercialProductSubType;
	private String placeType1;
	private String placeNumber1;
	private String placeType2;
	private String placeNumber2;
	private String placeType3;
	private String placeNumber3;
	private long atisNumber;
	private String projectCode;
//	public int hashCode() {
//	return addressNumber;
//}

public boolean equals(Object arg0) {
	if (arg0 instanceof TR010E) {
		TR010E other = (TR010E) arg0;
		return super.equals(arg0)
			&& EqualityUtilities.equals(pathType, other.pathType)
			&& pathNumber == other.pathNumber
			&& EqualityUtilities.equals(
				firstPathCharacters,
				other.firstPathCharacters)
			&& EqualityUtilities.equals(
				secondPathCharacters,
				other.secondPathCharacters)
			&& EqualityUtilities.equals(pathZone, other.pathZone)
			&& EqualityUtilities.equals(pathType2, other.pathType2)
			&& EqualityUtilities.equals(
				firstPathCharacters2,
				other.firstPathCharacters2)
			&& pathNumber2 == other.pathNumber2
			&& EqualityUtilities.equals(
				secondPathCharacters2,
				other.secondPathCharacters2)
			&& EqualityUtilities.equals(clientDocument, other.clientDocument)
			&& pathZone2 == other.pathZone2
			&& atisRequestNumber == other.atisRequestNumber
			&& phoneNumber == other.phoneNumber
			&& EqualityUtilities.equals(installAddress, other.installAddress)
			&& productServiceCode ==
				other.productServiceCode
			&& department == other.department
			&& phoneNumber == other.phoneNumber
			&& comercialOperation == other.comercialOperation
			&& EqualityUtilities.equals(installAddress, other.installAddress)
			&& addressCharacters == other.addressCharacters
			&& department == other.department
			&& city == other.city
			&& useType == other.useType
			&& comercialProductType == other.comercialProductType
			&& comercialProductSubType == other.comercialProductSubType
			&& EqualityUtilities.equals(placeType1, other.placeType1)
			&& EqualityUtilities.equals(placeNumber1, other.placeNumber1)
			&& EqualityUtilities.equals(placeType2, other.placeType2)
			&& EqualityUtilities.equals(placeNumber2, other.placeNumber2)
			&& EqualityUtilities.equals(placeType3, other.placeType3)
			&& EqualityUtilities.equals(placeNumber3, other.placeNumber3)
			&& EqualityUtilities.equals(clientDocument, other.clientDocument)
			&& addressNumber == other.addressNumber
			&& atisNumber == other.atisNumber;
	}
	return false;
}

	public char getAddressCharacters() {
		return addressCharacters;
	}

	/**
	 * Devuelve el número ATIEMPO
	 * @return atisRequestNumber
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * Setea el número ATIEMPO
	 * @param l 
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}
	
	public int getCity() {
		return city;
	}

	public String getClientDocument() {
		return clientDocument;
	}

	public long getComercialOperation() {
		return comercialOperation;
	}

	public long getComercialProductSubType() {
		return comercialProductSubType;
	}

	public long getComercialProductType() {
		return comercialProductType;
	}

	public int getDepartment() {
		return department;
	}

	public String getFirstPathCharacters() {
		return firstPathCharacters;
	}

	public String getFirstPathCharacters2() {
		return firstPathCharacters2;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public int getPathNumber() {
		return pathNumber;
	}

	public int getPathNumber2() {
		return pathNumber2;
	}

	public String getPathType() {
		return pathType;
	}

	public String getPathType2() {
		return pathType2;
	}

	public String getPathZone() {
		return pathZone;
	}

	public char getPathZone2() {
		return pathZone2;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getPlaceNumber1() {
		return placeNumber1;
	}

	public String getPlaceNumber2() {
		return placeNumber2;
	}

	public String getPlaceNumber3() {
		return placeNumber3;
	}

	public String getPlaceType1() {
		return placeType1;
	}

	public String getPlaceType2() {
		return placeType2;
	}

	public String getPlaceType3() {
		return placeType3;
	}

	public long getProductServiceCode() {
		return productServiceCode;
	}

	public String getSecondPathCharacters() {
		return secondPathCharacters;
	}

	public String getSecondPathCharacters2() {
		return secondPathCharacters2;
	}

	public long getUseType() {
		return useType;
	}

	public void setAddressCharacters(char c) {
		addressCharacters = c;
	}

	public void setCity(int i) {
		city = i;
	}

	public void setClientDocument(String string) {
		clientDocument = string;
	}
	
	public void setComercialOperation(long string) {
		comercialOperation = string;
	}

	public void setComercialProductSubType(long l) {
		comercialProductSubType = l;
	}

	public void setComercialProductType(long l) {
		comercialProductType = l;
	}

	public void setDepartment(int i) {
		department = i;
	}

	public void setFirstPathCharacters(String string) {
		firstPathCharacters = string;
	}

	public void setFirstPathCharacters2(String string) {
		firstPathCharacters2 = string;
	}

	public void setInstallAddress(String string) {
		installAddress = string;
	}

	public void setPathNumber(int i) {
		pathNumber = i;
	}

	public void setPathNumber2(int i) {
		pathNumber2 = i;
	}

	public void setPathType(String string) {
		pathType = string;
	}

	public void setPathType2(String string) {
		pathType2 = string;
	}

	public void setPathZone(String string) {
		pathZone = string;
	}

	public void setPathZone2(char c) {
		pathZone2 = c;
	}
	
	public void setPhoneNumber(int i) {
		phoneNumber = i;
	}

	public void setPlaceNumber1(String string) {
		placeNumber1 = string;
	}

	public void setPlaceNumber2(String string) {
		placeNumber2 = string;
	}

	public void setPlaceNumber3(String string) {
		placeNumber3 = string;
	}

	public void setPlaceType1(String string) {
		placeType1 = string;
	}

	public void setPlaceType2(String string) {
		placeType2 = string;
	}

	public void setPlaceType3(String string) {
		placeType3 = string;
	}

	public void setProductServiceCode(long string) {
		productServiceCode = string;
	}

	public void setSecondPathCharacters(String string) {
		secondPathCharacters = string;
	}

	public void setSecondPathCharacters2(String string) {
		secondPathCharacters2 = string;
	}

	public void setUseType(long l) {
		useType = l;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String string) {
		addressNumber = string;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String string) {
		clientName = string;
	}

	/**
	 * Devuelve el número ATIS
	 * @return Returns the atisNumber.
	 */
	public long getAtisNumber() {
		return atisNumber;
	}
	
	/**
	 * Setea el número ATIS
	 * @param atisNumber The atisNumber to set.
	 */
	public void setAtisNumber(long atisNumber) {
		this.atisNumber = atisNumber;
	}
	/**
	 * @return Returns the projectCode.
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode The projectCode to set.
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
}
