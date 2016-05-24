//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR026E.java,v 1.1 2011/03/30 18:24:46 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR026E extends RequestHeader{

	private long atiempoRequestNumber;
	private long atisRequestNumber;
	private long productServiceCode;
	private long contractorId;
	private String department;
	private String location;
	private String inventoryType;
	private Collection equipments;
		
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR026E) {
			TR026E other = (TR026E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(equipments,other.equipments)
				&& EqualityUtilities.equals(inventoryType,other.inventoryType)			
				
				&& atiempoRequestNumber == other.atiempoRequestNumber
				&& productServiceCode == other.productServiceCode
				&& contractorId == other.contractorId

				&& EqualityUtilities.equals(department,other.department)
				&& EqualityUtilities.equals(location,other.location)				
				&& atisRequestNumber == other.atisRequestNumber
				
				;
			}
		return false;
	}


	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	public long getContractorId() {
		return contractorId;
	}
	public void setContractorId(long contractorId) {
		this.contractorId = contractorId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Collection getEquipments() {
		return equipments;
	}
	public void setEquipments(Collection equipments) {
		this.equipments = equipments;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getProductServiceCode() {
		return productServiceCode;
	}
	public void setProductServiceCode(long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
}
