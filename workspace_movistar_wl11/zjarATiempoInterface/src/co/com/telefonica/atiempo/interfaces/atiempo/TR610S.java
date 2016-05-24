/*
 * Created on Jun 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR610S extends ResponseHeaderAgendaSC {
	private long atiempoRequestNumber;
	private String clientIdType;
	private String clientIdNumber;
	private double salesValue;
	private int salesNumber;
	private int ratePlan;
	private String modemSerialNumber;
	private String icc;
	private String observation;
	
	public int hashCode() {
		return new Long (atiempoRequestNumber).hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR610S) {
			TR610S other = (TR610S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(new Long(atiempoRequestNumber), new Long(other.atiempoRequestNumber))
				&& EqualityUtilities.equals(clientIdType, other.clientIdType)
				&& EqualityUtilities.equals(clientIdNumber, other.clientIdNumber)
				&& EqualityUtilities.equals(new Double(salesValue), new Double (other.salesValue))
				&& EqualityUtilities.equals(new Integer(salesNumber), new Integer(other.salesNumber))
				&& EqualityUtilities.equals(new Integer(ratePlan), new Integer(other.ratePlan))
				&& EqualityUtilities.equals(modemSerialNumber, other.modemSerialNumber)
				&& EqualityUtilities.equals(icc, other.icc)
				&& EqualityUtilities.equals(observation, other.observation);
			}
		return false;
	}
	
	public String getClientIdNumber() {
		return clientIdNumber;
	}
	public void setClientIdNumber(String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}
	public String getClientIdType() {
		return clientIdType;
	}
	public void setClientIdType(String clientIdType) {
		this.clientIdType = clientIdType;
	}
	public String getIcc() {
		return icc;
	}
	public void setIcc(String icc) {
		this.icc = icc;
	}
	public String getModemSerialNumber() {
		return modemSerialNumber;
	}
	public void setModemSerialNumber(String modemSerialNumber) {
		this.modemSerialNumber = modemSerialNumber;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public int getRatePlan() {
		return ratePlan;
	}
	public void setRatePlan(int ratePlan) {
		this.ratePlan = ratePlan;
	}
	public int getSalesNumber() {
		return salesNumber;
	}
	public void setSalesNumber(int salesNumber) {
		this.salesNumber = salesNumber;
	}
	public double getSalesValue() {
		return salesValue;
	}
	public void setSalesValue(double salesValue) {
		this.salesValue = salesValue;
	}
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
}
