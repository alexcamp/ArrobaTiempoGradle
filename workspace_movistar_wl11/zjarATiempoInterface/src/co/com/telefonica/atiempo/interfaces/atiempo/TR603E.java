//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR518E.java 2013/09/23 14:46:28 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author dcardena
 *
 */
public class TR603E extends RequestHeader{
   private String operation;
	private String atiempoRequestNumber;
   private Collection equipment;

  private Collection endPointType;
  
 //    </activation:EndPointType>
  private Collection sippstData;


   
   
   public boolean equals(Object arg0) {
		if (arg0 instanceof TR603E) {
			TR603E other = (TR603E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(operation, other.operation)
				&& EqualityUtilities.equals(atiempoRequestNumber, other.atiempoRequestNumber)
&& EqualityUtilities.equals(equipment, other.equipment)
&& EqualityUtilities.equals(endPointType, other.endPointType)
&& EqualityUtilities.equals(sippstData, other.sippstData)
				;
			}
		return false;
	}
/**
 * @return Devuelve endPointType.
 */
public Collection getEndPointType() {
	return endPointType;
}
/**
 * @param endPointType El endPointType a establecer.
 */
public void setEndPointType(Collection endPointType) {
	this.endPointType = endPointType;
}
/**
 * @return Devuelve equipment.
 */
public Collection getEquipment() {
	return equipment;
}
/**
 * @param equipment El equipment a establecer.
 */
public void setEquipment(Collection equipment) {
	this.equipment = equipment;
}
/**
 * @return Devuelve operation.
 */
public String getOperation() {
	return operation;
}
/**
 * @param operation El operation a establecer.
 */
public void setOperation(String operation) {
	this.operation = operation;
}
/**
 * @return Devuelve sippstData.
 */
public Collection getSippstData() {
	return sippstData;
}
/**
 * @param sippstData El sippstData a establecer.
 */
public void setSippstData(Collection sippstData) {
	this.sippstData = sippstData;
}
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}}
