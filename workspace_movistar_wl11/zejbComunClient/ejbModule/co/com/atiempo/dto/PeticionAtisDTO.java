/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author Respinoza
 */
public class PeticionAtisDTO {

	/**
	 * 
	 */
	public PeticionAtisDTO() {
		super();
	}

	private Long codPetCd;
	private Long codCliCd;
	private Long codEmpCd;
	private Long codSgmCliCd;
	private Long codSbgCliCd;
	private String nomDs;
	private String priApeDs;
	private String segApeDs;
	private Long codCnlVenCd;
	private Long codFzaVenCd;
	private String nomIntDs;
	private String priApeIntDs;
	private String segApeIntDs;
	private Long codPetPdrCd;
	private Long codCtaCd;
	private Timestamp cmbEstPetFf;
	private String tipCliCd;
	private Long codSgmCtaCd;
	private Long codSbgCtaCd;
	private String telCotDs;
	private String nomStePetSn;
	private String priApePetSn;
	private String segApePetSn;
	private String obsPetDs;
	private String usrAltNo;
	private Timestamp fecSctPetFf;
	private String tipDocCd;
	private String numDocCliCd;
	private String digCtlDocCd;
	
	
	/**
	 * @return
	 */
	public Timestamp getCmbEstPetFf() {
		return cmbEstPetFf;
	}

	/**
	 * @return
	 */
	public Long getCodCliCd() {
		return codCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodCnlVenCd() {
		return codCnlVenCd;
	}

	/**
	 * @return
	 */
	public Long getCodCtaCd() {
		return codCtaCd;
	}

	/**
	 * @return
	 */
	public Long getCodEmpCd() {
		return codEmpCd;
	}

	/**
	 * @return
	 */
	public Long getCodFzaVenCd() {
		return codFzaVenCd;
	}

	/**
	 * @return
	 */
	public Long getCodPetCd() {
		return codPetCd;
	}

	/**
	 * @return
	 */
	public Long getCodPetPdrCd() {
		return codPetPdrCd;
	}

	/**
	 * @return
	 */
	public Long getCodSbgCliCd() {
		return codSbgCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodSbgCtaCd() {
		return codSbgCtaCd;
	}

	/**
	 * @return
	 */
	public Long getCodSgmCliCd() {
		return codSgmCliCd;
	}

	/**
	 * @return
	 */
	public Long getCodSgmCtaCd() {
		return codSgmCtaCd;
	}

	/**
	 * @return
	 */
	public String getDigCtlDocCd() {
		return digCtlDocCd;
	}

	/**
	 * @return
	 */
	public Timestamp getFecSctPetFf() {
		return fecSctPetFf;
	}

	/**
	 * @return
	 */
	public String getNomDs() {
		return nomDs;
	}

	/**
	 * @return
	 */
	public String getNomIntDs() {
		return nomIntDs;
	}

	/**
	 * @return
	 */
	public String getNomStePetSn() {
		return nomStePetSn;
	}

	/**
	 * @return
	 */
	public String getNumDocCliCd() {
		return numDocCliCd;
	}

	/**
	 * @return
	 */
	public String getObsPetDs() {
		return obsPetDs;
	}

	/**
	 * @return
	 */
	public String getPriApeDs() {
		return priApeDs;
	}

	/**
	 * @return
	 */
	public String getPriApeIntDs() {
		return priApeIntDs;
	}

	/**
	 * @return
	 */
	public String getPriApePetSn() {
		return priApePetSn;
	}

	/**
	 * @return
	 */
	public String getSegApeDs() {
		return segApeDs;
	}

	/**
	 * @return
	 */
	public String getSegApeIntDs() {
		return segApeIntDs;
	}

	/**
	 * @return
	 */
	public String getSegApePetSn() {
		return segApePetSn;
	}

	/**
	 * @return
	 */
	public String getTelCotDs() {
		return telCotDs;
	}

	/**
	 * @return
	 */
	public String getTipCliCd() {
		return tipCliCd;
	}

	/**
	 * @return
	 */
	public String getTipDocCd() {
		return tipDocCd;
	}

	/**
	 * @return
	 */
	public String getUsrAltNo() {
		return usrAltNo;
	}

	/**
	 * @param timestamp
	 */
	public void setCmbEstPetFf(Timestamp timestamp) {
		cmbEstPetFf = timestamp;
	}

	/**
	 * @param long1
	 */
	public void setCodCliCd(Long long1) {
		codCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCnlVenCd(Long long1) {
		codCnlVenCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodCtaCd(Long long1) {
		codCtaCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodEmpCd(Long long1) {
		codEmpCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodFzaVenCd(Long long1) {
		codFzaVenCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodPetCd(Long long1) {
		codPetCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodPetPdrCd(Long long1) {
		codPetPdrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSbgCliCd(Long long1) {
		codSbgCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSbgCtaCd(Long long1) {
		codSbgCtaCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSgmCliCd(Long long1) {
		codSgmCliCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodSgmCtaCd(Long long1) {
		codSgmCtaCd = long1;
	}

	/**
	 * @param string
	 */
	public void setDigCtlDocCd(String string) {
		digCtlDocCd = string;
	}

	/**
	 * @param timestamp
	 */
	public void setFecSctPetFf(Timestamp timestamp) {
		fecSctPetFf = timestamp;
	}

	/**
	 * @param string
	 */
	public void setNomDs(String string) {
		nomDs = string;
	}

	/**
	 * @param string
	 */
	public void setNomIntDs(String string) {
		nomIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setNomStePetSn(String string) {
		nomStePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setNumDocCliCd(String string) {
		numDocCliCd = string;
	}

	/**
	 * @param string
	 */
	public void setObsPetDs(String string) {
		obsPetDs = string;
	}

	/**
	 * @param string
	 */
	public void setPriApeDs(String string) {
		priApeDs = string;
	}

	/**
	 * @param string
	 */
	public void setPriApeIntDs(String string) {
		priApeIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setPriApePetSn(String string) {
		priApePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setSegApeDs(String string) {
		segApeDs = string;
	}

	/**
	 * @param string
	 */
	public void setSegApeIntDs(String string) {
		segApeIntDs = string;
	}

	/**
	 * @param string
	 */
	public void setSegApePetSn(String string) {
		segApePetSn = string;
	}

	/**
	 * @param string
	 */
	public void setTelCotDs(String string) {
		telCotDs = string;
	}

	/**
	 * @param string
	 */
	public void setTipCliCd(String string) {
		tipCliCd = string;
	}

	/**
	 * @param string
	 */
	public void setTipDocCd(String string) {
		tipDocCd = string;
	}

	/**
	 * @param string
	 */
	public void setUsrAltNo(String string) {
		usrAltNo = string;
	}

}
