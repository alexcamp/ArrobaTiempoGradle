//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR052E.java,v 1.2 2011/04/18 16:10:16 lfmartinez Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author fmaceachen
 * @version $Revision: 1.2 $
 */
public class TR052E extends RequestHeaderAgendaSC {
	
	private long atisRequestNumber;
	private String cpSource;
	private String entryDate;
	private int causeId;
	private String causeDescription;
	private String central;
	private String district;
	private String closet;
	private String box;
	private String distributorCode;
	private String primaryCable;
	private String secondaryCable;
	private String segment;
	private String error;
	private Collection attendanceAreas;
	
	/**
	 * @return Returns the hashCode 
	 */
	public int hashCode() {
		return causeId;
	}
	
	
	/**
	 * @return Returns the closet.
	 */
	public String getCloset() {
		return closet;
	}
	/**
	 * @return true si es igual al objeto arg0, false en caso contrario
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR052E) {
			TR052E other = (TR052E) arg0;
			return super.equals(arg0)
				&& atisRequestNumber == other.atisRequestNumber
				&& EqualityUtilities.equals(cpSource,other.cpSource)
				&& EqualityUtilities.equals(entryDate,other.entryDate)
				&& causeId == other.causeId
				&& EqualityUtilities.equals(causeDescription,other.causeDescription)
				&& EqualityUtilities.equals(central,other.central)
				&& EqualityUtilities.equals(district,other.district)
				&& EqualityUtilities.equals(closet,other.closet)
				&& EqualityUtilities.equals(box,other.box)
				&& EqualityUtilities.equals(distributorCode,other.distributorCode)
				&& EqualityUtilities.equals(primaryCable,other.primaryCable)
				&& EqualityUtilities.equals(secondaryCable,other.secondaryCable)
				&& EqualityUtilities.equals(segment,other.segment)
				&& EqualityUtilities.equals(error,other.error)
				&& EqualityUtilities.equals(attendanceAreas,other.attendanceAreas);
		}
		return false;
	}
	/**
	 * @param closet The closet to set.
	 */
	public void setCloset(String armario) {
		this.closet = armario;
	}
	/**
	 * @return Returns the primaryCable.
	 */
	public String getPrimaryCable() {
		return primaryCable;
	}
	/**
	 * @param primaryCable The primaryCable to set.
	 */
	public void setPrimaryCable(String cablePrimario) {
		this.primaryCable = cablePrimario;
	}
	/**
	 * @return Returns the secondaryCable.
	 */
	public String getSecondaryCable() {
		return secondaryCable;
	}
	/**
	 * @param secondaryCable The secondaryCable to set.
	 */
	public void setSecondaryCable(String cableSecundario) {
		this.secondaryCable = cableSecundario;
	}
	/**
	 * @return Returns the box.
	 */
	public String getBox() {
		return box;
	}
	/**
	 * @param box The box to set.
	 */
	public void setBox(String caja) {
		this.box = caja;
	}
	/**
	 * @return Returns the central.
	 */
	public String getCentral() {
		return central;
	}
	/**
	 * @param central The central to set.
	 */
	public void setCentral(String central) {
		this.central = central;
	}
	/**
	 * @return Returns the causeDescription.
	 */
	public String getCauseDescription() {
		return causeDescription;
	}
	/**
	 * @param causeDescription The causeDescription to set.
	 */
	public void setCauseDescription(String descripcionCausal) {
		this.causeDescription = descripcionCausal;
	}
	/**
	 * @return Returns the distributorCode.
	 */
	public String getDistributorCode() {
		return distributorCode;
	}
	/**
	 * @param distributorCode The distributorCode to set.
	 */
	public void setDistributorCode(String distribuidor) {
		this.distributorCode = distribuidor;
	}
	/**
	 * @return Returns the district.
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district The district to set.
	 */
	public void setDistrict(String distrito) {
		this.district = distrito;
	}
	/**
	 * @return Returns the error.
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error The error to set.
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return Returns the entryDate.
	 */
	public String getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(String fechaIngreso) {
		this.entryDate = fechaIngreso;
	}
	/**
	 * @return Returns the causeId.
	 */
	public int getCauseId() {
		return causeId;
	}
	/**
	 * @param causeId The causeId to set.
	 */
	public void setCauseId(int idCausal) {
		this.causeId = idCausal;
	}
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(long idPeticionAtis) {
		this.atisRequestNumber = idPeticionAtis;
	}
	/**
	 * @return Returns the cpSource.
	 */
	public String getCpSource() {
		return cpSource;
	}
	/**
	 * @param cpSource The cpSource to set.
	 */
	public void setCpSource(String origenCp) {
		this.cpSource = origenCp;
	}
	/**
	 * @return Returns the segment.
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * @param segment The segment to set.
	 */
	public void setSegment(String segmento) {
		this.segment = segmento;
	}		
	/**
	 * @return Returns the attendanceAreas.
	 */
	public Collection getAttendanceAreas() {
		return attendanceAreas;
	}
	/**
	 * @param attendanceAreas The attendanceAreas to set.
	 */
	public void setAttendanceAreas(Collection attendanceAreas) {
		this.attendanceAreas = attendanceAreas;
	}
}
