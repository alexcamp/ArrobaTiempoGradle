package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.PeticionAtisDTO;

/**
 * Bean implementation class for Enterprise Bean: Peticion_atis
 */
public abstract class Peticion_atisBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}

	public co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey ejbCreate(PeticionAtisDTO dto) throws javax.ejb.CreateException
	{
		setCod_pet_cd(dto.getCodPetCd());
		setCod_cli_cd(dto.getCodCliCd());
		setCod_emp_cd(dto.getCodEmpCd());
		setCod_sgm_cli_cd(dto.getCodSgmCliCd());
		setCod_sbg_cli_cd(dto.getCodSbgCliCd());
		//ingrese el cliente
		setNom_ds(dto.getNomDs());
		setPri_ape_ds(dto.getPriApeDs());
		setSeg_ape_ds(dto.getSegApeDs());
		//ingrese el contacto
		setNom_int_ds(dto.getNomIntDs());
		setPri_ape_int_ds(dto.getPriApeIntDs());
		setSeg_ape_int_ds(dto.getSegApeIntDs());
		setCod_cnl_ven_cd(dto.getCodCnlVenCd());
		setCod_fza_ven_cd(dto.getCodFzaVenCd());
		setCod_pet_pdr_cd(dto.getCodPetPdrCd());
		setCod_cta_cd(dto.getCodCtaCd());
		setCmb_est_pet_ff(dto.getCmbEstPetFf());
		setTip_cli_cd(dto.getTipCliCd());
		setCod_sgm_cta_cd(dto.getCodSgmCtaCd());
		setCod_sbg_cta_cd(dto.getCodSbgCtaCd());
		setTel_cot_ds(dto.getTelCotDs());
		setNom_ste_pet_sn(dto.getNomStePetSn());
		setPri_ape_pet_sn(dto.getPriApePetSn());
		setSeg_ape_pet_sn(dto.getSegApePetSn());
		setObs_pet_ds(dto.getObsPetDs());
		setUsr_alt_no(dto.getUsrAltNo());
		setFec_sct_pet_ff(dto.getFecSctPetFf());
		setTip_doc_cd(dto.getTipDocCd());
		setNum_doc_cli_cd(dto.getNumDocCliCd());
		setDig_ctl_doc_cd(dto.getDigCtlDocCd());
		
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(PeticionAtisDTO dto)
			throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * Get accessor for persistent attribute: cod_pet_cd
	 */
	public abstract java.lang.Long getCod_pet_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_cd
	 */
	public abstract void setCod_pet_cd(java.lang.Long newCod_pet_cd);
	/**
	 * Get accessor for persistent attribute: cod_cli_cd
	 */
	public abstract java.lang.Long getCod_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_cli_cd
	 */
	public abstract void setCod_cli_cd(java.lang.Long newCod_cli_cd);
	/**
	 * Get accessor for persistent attribute: cod_emp_cd
	 */
	public abstract java.lang.Long getCod_emp_cd();
	/**
	 * Set accessor for persistent attribute: cod_emp_cd
	 */
	public abstract void setCod_emp_cd(java.lang.Long newCod_emp_cd);
	/**
	 * Get accessor for persistent attribute: cod_sgm_cli_cd
	 */
	public abstract java.lang.Long getCod_sgm_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_sgm_cli_cd
	 */
	public abstract void setCod_sgm_cli_cd(java.lang.Long newCod_sgm_cli_cd);
	/**
	 * Get accessor for persistent attribute: cod_sbg_cli_cd
	 */
	public abstract java.lang.Long getCod_sbg_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_sbg_cli_cd
	 */
	public abstract void setCod_sbg_cli_cd(java.lang.Long newCod_sbg_cli_cd);
	/**
	 * Get accessor for persistent attribute: nom_ds
	 */
	public abstract java.lang.String getNom_ds();
	/**
	 * Set accessor for persistent attribute: nom_ds
	 */
	public abstract void setNom_ds(java.lang.String newNom_ds);
	/**
	 * Get accessor for persistent attribute: pri_ape_ds
	 */
	public abstract java.lang.String getPri_ape_ds();
	/**
	 * Set accessor for persistent attribute: pri_ape_ds
	 */
	public abstract void setPri_ape_ds(java.lang.String newPri_ape_ds);
	/**
	 * Get accessor for persistent attribute: seg_ape_ds
	 */
	public abstract java.lang.String getSeg_ape_ds();
	/**
	 * Set accessor for persistent attribute: seg_ape_ds
	 */
	public abstract void setSeg_ape_ds(java.lang.String newSeg_ape_ds);
	/**
	 * Get accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public abstract java.lang.Long getCod_cnl_ven_cd();
	/**
	 * Set accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public abstract void setCod_cnl_ven_cd(java.lang.Long newCod_cnl_ven_cd);
	/**
	 * Get accessor for persistent attribute: cod_fza_ven_cd
	 */
	public abstract java.lang.Long getCod_fza_ven_cd();
	/**
	 * Set accessor for persistent attribute: cod_fza_ven_cd
	 */
	public abstract void setCod_fza_ven_cd(java.lang.Long newCod_fza_ven_cd);
	/**
	 * Get accessor for persistent attribute: nom_int_ds
	 */
	public abstract java.lang.String getNom_int_ds();
	/**
	 * Set accessor for persistent attribute: nom_int_ds
	 */
	public abstract void setNom_int_ds(java.lang.String newNom_int_ds);
	/**
	 * Get accessor for persistent attribute: pri_ape_int_ds
	 */
	public abstract java.lang.String getPri_ape_int_ds();
	/**
	 * Set accessor for persistent attribute: pri_ape_int_ds
	 */
	public abstract void setPri_ape_int_ds(java.lang.String newPri_ape_int_ds);
	/**
	 * Get accessor for persistent attribute: seg_ape_int_ds
	 */
	public abstract java.lang.String getSeg_ape_int_ds();
	/**
	 * Set accessor for persistent attribute: seg_ape_int_ds
	 */
	public abstract void setSeg_ape_int_ds(java.lang.String newSeg_ape_int_ds);
	/**
	 * Get accessor for persistent attribute: cod_pet_pdr_cd
	 */
	public abstract java.lang.Long getCod_pet_pdr_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_pdr_cd
	 */
	public abstract void setCod_pet_pdr_cd(java.lang.Long newCod_pet_pdr_cd);
	/**
	 * Get accessor for persistent attribute: cod_cta_cd
	 */
	public abstract java.lang.Long getCod_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_cta_cd
	 */
	public abstract void setCod_cta_cd(java.lang.Long newCod_cta_cd);
	/**
	 * Get accessor for persistent attribute: cmb_est_pet_ff
	 */
	public abstract java.sql.Timestamp getCmb_est_pet_ff();
	/**
	 * Set accessor for persistent attribute: cmb_est_pet_ff
	 */
	public abstract void setCmb_est_pet_ff(
		java.sql.Timestamp newCmb_est_pet_ff);
	/**
	 * Get accessor for persistent attribute: tip_cli_cd
	 */
	public abstract java.lang.String getTip_cli_cd();
	/**
	 * Set accessor for persistent attribute: tip_cli_cd
	 */
	public abstract void setTip_cli_cd(java.lang.String newTip_cli_cd);
	/**
	 * Get accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public abstract java.lang.Long getCod_sgm_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public abstract void setCod_sgm_cta_cd(java.lang.Long newCod_sgm_cta_cd);
	/**
	 * Get accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public abstract java.lang.Long getCod_sbg_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public abstract void setCod_sbg_cta_cd(java.lang.Long newCod_sbg_cta_cd);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds
	 */
	public abstract java.lang.String getTel_cot_ds();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds
	 */
	public abstract void setTel_cot_ds(java.lang.String newTel_cot_ds);
	/**
	 * Get accessor for persistent attribute: nom_ste_pet_sn
	 */
	public abstract java.lang.String getNom_ste_pet_sn();
	/**
	 * Set accessor for persistent attribute: nom_ste_pet_sn
	 */
	public abstract void setNom_ste_pet_sn(java.lang.String newNom_ste_pet_sn);
	/**
	 * Get accessor for persistent attribute: pri_ape_pet_sn
	 */
	public abstract java.lang.String getPri_ape_pet_sn();
	/**
	 * Set accessor for persistent attribute: pri_ape_pet_sn
	 */
	public abstract void setPri_ape_pet_sn(java.lang.String newPri_ape_pet_sn);
	/**
	 * Get accessor for persistent attribute: seg_ape_pet_sn
	 */
	public abstract java.lang.String getSeg_ape_pet_sn();
	/**
	 * Set accessor for persistent attribute: seg_ape_pet_sn
	 */
	public abstract void setSeg_ape_pet_sn(java.lang.String newSeg_ape_pet_sn);
	/**
	 * Get accessor for persistent attribute: obs_pet_ds
	 */
	public abstract java.lang.String getObs_pet_ds();
	/**
	 * Set accessor for persistent attribute: obs_pet_ds
	 */
	public abstract void setObs_pet_ds(java.lang.String newObs_pet_ds);
	/**
	 * Get accessor for persistent attribute: usr_alt_no
	 */
	public abstract java.lang.String getUsr_alt_no();
	/**
	 * Set accessor for persistent attribute: usr_alt_no
	 */
	public abstract void setUsr_alt_no(java.lang.String newUsr_alt_no);
	/**
	 * Get accessor for persistent attribute: fec_sct_pet_ff
	 */
	public abstract java.sql.Timestamp getFec_sct_pet_ff();
	/**
	 * Set accessor for persistent attribute: fec_sct_pet_ff
	 */
	public abstract void setFec_sct_pet_ff(
		java.sql.Timestamp newFec_sct_pet_ff);
	/**
	 * Get accessor for persistent attribute: tip_doc_cd
	 */
	public abstract java.lang.String getTip_doc_cd();
	/**
	 * Set accessor for persistent attribute: tip_doc_cd
	 */
	public abstract void setTip_doc_cd(java.lang.String newTip_doc_cd);
	/**
	 * Get accessor for persistent attribute: num_doc_cli_cd
	 */
	public abstract java.lang.String getNum_doc_cli_cd();
	/**
	 * Set accessor for persistent attribute: num_doc_cli_cd
	 */
	public abstract void setNum_doc_cli_cd(java.lang.String newNum_doc_cli_cd);
	/**
	 * Get accessor for persistent attribute: dig_ctl_doc_cd
	 */
	public abstract java.lang.String getDig_ctl_doc_cd();
	/**
	 * Set accessor for persistent attribute: dig_ctl_doc_cd
	 */
	public abstract void setDig_ctl_doc_cd(java.lang.String newDig_ctl_doc_cd);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named agrupacion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAgrupacion_atis();
	/**
	 * This method was generated for supporting the relationship role named agrupacion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAgrupacion_atis(
		java.util.Collection anAgrupacion_atis);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey ejbCreate(
		java.lang.Long cod_pet_cd) throws javax.ejb.CreateException {
		setCod_pet_cd(cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_pet_cd)
		throws javax.ejb.CreateException {
	}
}
