//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: Address.java,v 1.1 2011/03/30 18:20:03 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.1 $
 */
public class Address implements Serializable {
	private String streetNumber;
	private String departmentCode;
	private String cityCode;
	private String streetName;
	private String pathType;
	private int pathNumber;
	private String firstPathCharacters;
	private String secondPathCharacters;
	private String pathZone;
	private String pathType2;
	private int pathNumber2;
	private String firstPathCharacters2;
	private String secondPathCharacters2;
	private String pathZone2;
	private String streetType;
	private String placeType1;
	private String placeNumber1;
	private String placeType2;
	private String placeNumber2;
	private String placeType3;
	private String placeNumber3;
	private String complementsDescription1;
	private String complementsDescription2;
	private String subCityName;
	private String externalCityCode;
	private String territory;
//	private int graniteCode;
	
	
	public String getCityCode() {
		return cityCode;
	}

	public String getComplementsDescription1() {
		return complementsDescription1;
	}

	public String getComplementsDescription2() {
		return complementsDescription2;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public String getExternalCityCode() {
		return externalCityCode;
	}

	public String getFirstPathCharacters() {
		return firstPathCharacters;
	}

	public String getFirstPathCharacters2() {
		return firstPathCharacters2;
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

	public String getPathZone2() {
		return pathZone2;
	}

	public String getSecondPathCharacters() {
		return secondPathCharacters;
	}

	public String getSecondPathCharacters2() {
		return secondPathCharacters2;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getStreetType() {
		return streetType;
	}

	public String getSubCityName() {
		return subCityName;
	}

	public String getTerritory() {
		return territory;
	}

	public void setCityCode(String string) {
		cityCode = string;
	}

	public void setComplementsDescription1(String string) {
		complementsDescription1 = string;
	}

	public void setComplementsDescription2(String string) {
		complementsDescription2 = string;
	}

	public void setDepartmentCode(String string) {
		departmentCode = string;
	}

	public void setExternalCityCode(String string) {
		externalCityCode = string;
	}

	public void setFirstPathCharacters(String string) {
		firstPathCharacters = string;
	}

	public void setFirstPathCharacters2(String string) {
		firstPathCharacters2 = string;
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

	public void setPathZone2(String string) {
		pathZone2 = string;
	}


	public void setSecondPathCharacters(String string) {
		secondPathCharacters = string;
	}

	public void setSecondPathCharacters2(String string) {
		secondPathCharacters2 = string;
	}

	public void setStreetName(String string) {
		streetName = string;
	}

	public void setStreetNumber(String string) {
		streetNumber = string;
	}

	public void setStreetType(String string) {
		streetType = string;
	}

	public void setSubCityName(String string) {
		subCityName = string;
	}

	public void setTerritory(String string) {
		territory = string;
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

	
	public int hashCode(){
		return this.streetNumber.hashCode();
	}
	
	public boolean equals(Object arg0){
		if (arg0 instanceof Address) {
			Address other = (Address) arg0;
			return EqualityUtilities.equals(streetNumber, other.streetNumber)
				&& EqualityUtilities.equals(departmentCode, other.departmentCode)
				&& EqualityUtilities.equals(cityCode, other.cityCode)
				&& EqualityUtilities.equals(streetName, other.streetName)
				&& EqualityUtilities.equals(pathType, other.pathType)
	//			&& graniteCode == other.graniteCode
				&& pathNumber == other.pathNumber
				&& EqualityUtilities.equals(firstPathCharacters, other.firstPathCharacters)
				&& EqualityUtilities.equals(secondPathCharacters, other.secondPathCharacters)
				&& EqualityUtilities.equals(pathZone, other.pathZone)
				&& EqualityUtilities.equals(pathType2, other.pathType2)
				&& pathNumber2 == other.pathNumber2
				&& EqualityUtilities.equals(firstPathCharacters2, other.firstPathCharacters2)
				&& EqualityUtilities.equals(secondPathCharacters2, other.secondPathCharacters2)
				&& EqualityUtilities.equals(pathZone2, other.pathZone2)
				&& EqualityUtilities.equals(streetType, other.streetType)
				&& EqualityUtilities.equals(placeType1, other.placeType1)
				&& EqualityUtilities.equals(placeNumber1, other.placeNumber1)
				&& EqualityUtilities.equals(placeType2, other.placeType2)
				&& EqualityUtilities.equals(placeNumber2, other.placeNumber2)
				&& EqualityUtilities.equals(placeType3, other.placeType3)
				&& EqualityUtilities.equals(placeNumber3, other.placeNumber3)
				&& EqualityUtilities.equals(complementsDescription1, other.complementsDescription1)
				&& EqualityUtilities.equals(complementsDescription2, other.complementsDescription2)
				&& EqualityUtilities.equals(subCityName, other.subCityName)
				&& EqualityUtilities.equals(externalCityCode, other.externalCityCode)
				&& EqualityUtilities.equals(territory, other.territory);
		}
		return false;
	}
	
	
		
/*	
	public int getGraniteCode() {
		return graniteCode;
	}

	public void setGraniteCode(int i) {
		graniteCode = i;
	}
*/
}
