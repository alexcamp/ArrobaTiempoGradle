/*
 * Creado el 14/12/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR057E extends RequestHeaderAgendaSC {
	private long atisRequestNumber;

	private long atiempoRequestNumber;

	private int commercialOperation;

	private int psCode;

	private String fatherAccount;

	private String city;

	private String address;
	
	private String recordSpace;

	private Collection cameras;

	/**
	 * @param cameras
	 *            El cameras a establecer.
	 */
	public void setCameras(Collection cameras) {
		this.cameras = cameras;
	}

	/**
	 * @return Devuelve cameras.
	 */
	public Collection getCameras() {
		return cameras;
	}

	/**
	 * @param atisRequestNumber
	 *            El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}

	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @param atiempoRequestNumber
	 *            El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}

	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param commercialOperation
	 *            El commercialOperation a establecer.
	 */
	public void setCommercialOperation(int commercialOperation) {
		this.commercialOperation = commercialOperation;
	}

	/**
	 * @return Devuelve commercialOperation.
	 */
	public int getCommercialOperation() {
		return commercialOperation;
	}

	/**
	 * @param psCode
	 *            El psCode a establecer.
	 */
	public void setPsCode(int psCode) {
		this.psCode = psCode;
	}

	/**
	 * @return Devuelve psCode.
	 */
	public int getPsCode() {
		return psCode;
	}

	/**
	 * @param fatherAccount
	 *            El fatherAccount a establecer.
	 */
	public void setFatherAccount(String fatherAccount) {
		this.fatherAccount = fatherAccount;
	}

	/**
	 * @return Devuelve fatherAccount.
	 */
	public String getFatherAccount() {
		return fatherAccount;
	}

	/**
	 * @param city
	 *            El city a establecer.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Devuelve city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param address
	 *            El address a establecer.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return Devuelve address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param recordSpace El recordSpace a establecer.
	 */
	public void setRecordSpace(String recordSpace) {
		this.recordSpace = recordSpace;
	}

	/**
	 * @return Devuelve recordSpace.
	 */
	public String getRecordSpace() {
		return recordSpace;
	}

}