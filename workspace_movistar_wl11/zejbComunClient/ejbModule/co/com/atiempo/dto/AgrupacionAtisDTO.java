/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

import java.sql.Timestamp;

/**
 * @author Respinoza
 */
public class AgrupacionAtisDTO {

	/**
	 * 
	 */
	public AgrupacionAtisDTO() {
		super();
	}

	private Long codPetCd;
	private Integer codAgrSubNu;
	private Integer agrSubPdrCd;
	private Integer canArgHijNu;
	private String numIdeNu;
	private String ideProCmrCd;
	private Long tipOprCmrCd;
	private Long codProCmrCd;
	private Long proCmrPdrSn;
	private Long tipProCd;
	private Long sbtProCmrCd;
	private Long codTipUsoCd;
	private String nomTipUsoNo;
	private Long codDirCd; 
	private Long codDirSegCd;
	private Timestamp fecSlaEjeFf;
	private Timestamp cpsAgrSubFf;
	private String obsAgrSubDs;
	
	
	/**
	 * @return
	 */
	public Integer getAgrSubPdrCd() {
		return agrSubPdrCd;
	}

	/**
	 * @return
	 */
	public Integer getCanArgHijNu() {
		return canArgHijNu;
	}

	/**
	 * @return
	 */
	public Integer getCodAgrSubNu() {
		return codAgrSubNu;
	}

	/**
	 * @return
	 */
	public Long getCodDirCd() {
		return codDirCd;
	}

	/**
	 * @return
	 */
	public Long getCodDirSegCd() {
		return codDirSegCd;
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
	public Long getCodProCmrCd() {
		return codProCmrCd;
	}

	/**
	 * @return
	 */
	public Long getCodTipUsoCd() {
		return codTipUsoCd;
	}

	/**
	 * @return
	 */
	public Timestamp getCpsAgrSubFf() {
		return cpsAgrSubFf;
	}

	/**
	 * @return
	 */
	public Timestamp getFecSlaEjeFf() {
		return fecSlaEjeFf;
	}

	/**
	 * @return
	 */
	public String getIdeProCmrCd() {
		return ideProCmrCd;
	}

	/**
	 * @return
	 */
	public String getNomTipUsoNo() {
		return nomTipUsoNo;
	}

	/**
	 * @return
	 */
	public String getNumIdeNu() {
		return numIdeNu;
	}

	/**
	 * @return
	 */
	public String getObsAgrSubDs() {
		return obsAgrSubDs;
	}

	/**
	 * @return
	 */
	public Long getProCmrPdrSn() {
		return proCmrPdrSn;
	}

	/**
	 * @return
	 */
	public Long getSbtProCmrCd() {
		return sbtProCmrCd;
	}

	/**
	 * @return
	 */
	public Long getTipOprCmrCd() {
		return tipOprCmrCd;
	}

	/**
	 * @return
	 */
	public Long getTipProCd() {
		return tipProCd;
	}

	/**
	 * @param integer
	 */
	public void setAgrSubPdrCd(Integer integer) {
		agrSubPdrCd = integer;
	}

	/**
	 * @param integer
	 */
	public void setCanArgHijNu(Integer integer) {
		canArgHijNu = integer;
	}

	/**
	 * @param integer
	 */
	public void setCodAgrSubNu(Integer integer) {
		codAgrSubNu = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodDirCd(Long long1) {
		codDirCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodDirSegCd(Long long1) {
		codDirSegCd = long1;
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
	public void setCodProCmrCd(Long long1) {
		codProCmrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCodTipUsoCd(Long long1) {
		codTipUsoCd = long1;
	}

	/**
	 * @param timestamp
	 */
	public void setCpsAgrSubFf(Timestamp timestamp) {
		cpsAgrSubFf = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setFecSlaEjeFf(Timestamp timestamp) {
		fecSlaEjeFf = timestamp;
	}

	/**
	 * @param string
	 */
	public void setIdeProCmrCd(String string) {
		ideProCmrCd = string;
	}

	/**
	 * @param string
	 */
	public void setNomTipUsoNo(String string) {
		nomTipUsoNo = string;
	}

	/**
	 * @param string
	 */
	public void setNumIdeNu(String string) {
		numIdeNu = string;
	}

	/**
	 * @param string
	 */
	public void setObsAgrSubDs(String string) {
		obsAgrSubDs = string;
	}

	/**
	 * @param long1
	 */
	public void setProCmrPdrSn(Long long1) {
		proCmrPdrSn = long1;
	}

	/**
	 * @param long1
	 */
	public void setSbtProCmrCd(Long long1) {
		sbtProCmrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setTipOprCmrCd(Long long1) {
		tipOprCmrCd = long1;
	}

	/**
	 * @param long1
	 */
	public void setTipProCd(Long long1) {
		tipProCd = long1;
	}

}
