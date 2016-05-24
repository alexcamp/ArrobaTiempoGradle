//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR022E.java,v 1.1 2011/03/30 18:24:44 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author gmarcone
 * @version $Revision: 1.1 $
 */
public class TR022E extends RequestHeader{

	private long atisRequestNumber;
	private String modemFinalDigits;
	private String cityCode;
	private long id_plan;
	private Integer modemType;
	private long contractorId;
	
		
	/**
	 * @return Devuelve contractorId.
	 */
	public long getContractorId() {
		return contractorId;
	}
	/**
	 * @param contractorId El contractorId a establecer.
	 */
	public void setContractorId(long contractorId) {
		this.contractorId = contractorId;
	}
	public int hashCode() {
		return (int)atisRequestNumber;
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR022E) {
			TR022E other = (TR022E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
			    && EqualityUtilities.equals(cityCode,other.cityCode)
				&& EqualityUtilities.equals(modemType,other.modemType)
			    && EqualityUtilities.equals(modemFinalDigits,other.modemFinalDigits)
				&& id_plan == other.id_plan;
		}
		return false;
	}

	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getModemFinalDigits() {
		return modemFinalDigits;
	}

	public void setAtisRequestNumber(long l) {
		atisRequestNumber = l;
	}

	public void setCityCode(String i) {
		cityCode = i;
	}

	public void setModemFinalDigits(String i) {
		modemFinalDigits = i;
	}


	public long getId_plan() {
		return id_plan;
	}

		
	public Integer getModemType() {
		return modemType;
	}
	public void setModemType(Integer modemType) {
		this.modemType = modemType;
	}
	public void setId_plan(long l) {
		id_plan = l;
	}
}
