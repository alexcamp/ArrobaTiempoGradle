package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Peticion_st
 */
public abstract class Peticion_stBean implements javax.ejb.EntityBean {
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
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey ejbCreate(
		java.lang.Long cod_ave_cd, Integer estado)
		throws javax.ejb.CreateException {
		setCod_ave_cd(cod_ave_cd);
		setEstado_peticion(estado);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_ave_cd,Integer estado)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey ejbCreate(
		java.lang.Long cod_ave_cd) throws javax.ejb.CreateException {
		setCod_ave_cd(cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_ave_cd)
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
	 * Get accessor for persistent attribute: cod_ave_cd
	 */
	public abstract java.lang.Long getCod_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_ave_cd
	 */
	public abstract void setCod_ave_cd(java.lang.Long newCod_ave_cd);
	/**
	 * Get accessor for persistent attribute: cod_cli_cd
	 */
	public abstract java.lang.Long getCod_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_cli_cd
	 */
	public abstract void setCod_cli_cd(java.lang.Long newCod_cli_cd);
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public abstract java.lang.String getIde_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public abstract void setIde_pro_cmr_cd(java.lang.String newIde_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: num_ide_nu
	 */
	public abstract java.lang.String getNum_ide_nu();
	/**
	 * Set accessor for persistent attribute: num_ide_nu
	 */
	public abstract void setNum_ide_nu(java.lang.String newNum_ide_nu);
	/**
	 * Get accessor for persistent attribute: cod_apt_ave_cd
	 */
	public abstract java.lang.String getCod_apt_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_apt_ave_cd
	 */
	public abstract void setCod_apt_ave_cd(java.lang.String newCod_apt_ave_cd);
	/**
	 * Get accessor for persistent attribute: cod_cie_ave_cd
	 */
	public abstract java.lang.String getCod_cie_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_cie_ave_cd
	 */
	public abstract void setCod_cie_ave_cd(java.lang.String newCod_cie_ave_cd);
	/**
	 * Get accessor for persistent attribute: cod_stm_ave_cd
	 */
	public abstract java.lang.String getCod_stm_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_stm_ave_cd
	 */
	public abstract void setCod_stm_ave_cd(java.lang.String newCod_stm_ave_cd);
	/**
	 * Get accessor for persistent attribute: rpt_pru_ave_cd
	 */
	public abstract java.lang.String getRpt_pru_ave_cd();
	/**
	 * Set accessor for persistent attribute: rpt_pru_ave_cd
	 */
	public abstract void setRpt_pru_ave_cd(java.lang.String newRpt_pru_ave_cd);
	/**
	 * Get accessor for persistent attribute: cod_are_ges_cd
	 */
	public abstract java.lang.Long getCod_are_ges_cd();
	/**
	 * Set accessor for persistent attribute: cod_are_ges_cd
	 */
	public abstract void setCod_are_ges_cd(java.lang.Long newCod_are_ges_cd);
	/**
	 * Get accessor for persistent attribute: cod_est_ave_cd
	 */
	public abstract java.lang.String getCod_est_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_est_ave_cd
	 */
	public abstract void setCod_est_ave_cd(java.lang.String newCod_est_ave_cd);
	/**
	 * Get accessor for persistent attribute: cod_pet_cd
	 */
	public abstract java.lang.Long getCod_pet_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_cd
	 */
	public abstract void setCod_pet_cd(java.lang.Long newCod_pet_cd);
	/**
	 * Get accessor for persistent attribute: cod_ave_mas_cd
	 */
	public abstract java.lang.Long getCod_ave_mas_cd();
	/**
	 * Set accessor for persistent attribute: cod_ave_mas_cd
	 */
	public abstract void setCod_ave_mas_cd(java.lang.Long newCod_ave_mas_cd);
	/**
	 * Get accessor for persistent attribute: cod_ave_ori_sn
	 */
	public abstract java.lang.Long getCod_ave_ori_sn();
	/**
	 * Set accessor for persistent attribute: cod_ave_ori_sn
	 */
	public abstract void setCod_ave_ori_sn(java.lang.Long newCod_ave_ori_sn);
	/**
	 * Get accessor for persistent attribute: tip_doc_rte_cd
	 */
	public abstract java.lang.String getTip_doc_rte_cd();
	/**
	 * Set accessor for persistent attribute: tip_doc_rte_cd
	 */
	public abstract void setTip_doc_rte_cd(java.lang.String newTip_doc_rte_cd);
	/**
	 * Get accessor for persistent attribute: num_doc_rte_nu
	 */
	public abstract java.lang.String getNum_doc_rte_nu();
	/**
	 * Set accessor for persistent attribute: num_doc_rte_nu
	 */
	public abstract void setNum_doc_rte_nu(java.lang.String newNum_doc_rte_nu);
	/**
	 * Get accessor for persistent attribute: cct_doc_rte_cd
	 */
	public abstract java.lang.String getCct_doc_rte_cd();
	/**
	 * Set accessor for persistent attribute: cct_doc_rte_cd
	 */
	public abstract void setCct_doc_rte_cd(java.lang.String newCct_doc_rte_cd);
	/**
	 * Get accessor for persistent attribute: nom_rte_sn
	 */
	public abstract java.lang.String getNom_rte_sn();
	/**
	 * Set accessor for persistent attribute: nom_rte_sn
	 */
	public abstract void setNom_rte_sn(java.lang.String newNom_rte_sn);
	/**
	 * Get accessor for persistent attribute: pri_ape_rte_sn
	 */
	public abstract java.lang.String getPri_ape_rte_sn();
	/**
	 * Set accessor for persistent attribute: pri_ape_rte_sn
	 */
	public abstract void setPri_ape_rte_sn(java.lang.String newPri_ape_rte_sn);
	/**
	 * Get accessor for persistent attribute: seg_ape_rte_sn
	 */
	public abstract java.lang.String getSeg_ape_rte_sn();
	/**
	 * Set accessor for persistent attribute: seg_ape_rte_sn
	 */
	public abstract void setSeg_ape_rte_sn(java.lang.String newSeg_ape_rte_sn);
	/**
	 * Get accessor for persistent attribute: cod_rel_cli_cd
	 */
	public abstract java.lang.String getCod_rel_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_rel_cli_cd
	 */
	public abstract void setCod_rel_cli_cd(java.lang.String newCod_rel_cli_cd);
	/**
	 * Get accessor for persistent attribute: rel_cli_otr_ds
	 */
	public abstract java.lang.String getRel_cli_otr_ds();
	/**
	 * Set accessor for persistent attribute: rel_cli_otr_ds
	 */
	public abstract void setRel_cli_otr_ds(java.lang.String newRel_cli_otr_ds);
	/**
	 * Get accessor for persistent attribute: frm_frm_ntf_cd
	 */
	public abstract java.lang.String getFrm_frm_ntf_cd();
	/**
	 * Set accessor for persistent attribute: frm_frm_ntf_cd
	 */
	public abstract void setFrm_frm_ntf_cd(java.lang.String newFrm_frm_ntf_cd);
	/**
	 * Get accessor for persistent attribute: nom_psn_cot_no
	 */
	public abstract java.lang.String getNom_psn_cot_no();
	/**
	 * Set accessor for persistent attribute: nom_psn_cot_no
	 */
	public abstract void setNom_psn_cot_no(java.lang.String newNom_psn_cot_no);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds
	 */
	public abstract java.lang.String getTel_cot_ds();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds
	 */
	public abstract void setTel_cot_ds(java.lang.String newTel_cot_ds);
	/**
	 * Get accessor for persistent attribute: seg_psn_cot_sn
	 */
	public abstract java.lang.String getSeg_psn_cot_sn();
	/**
	 * Set accessor for persistent attribute: seg_psn_cot_sn
	 */
	public abstract void setSeg_psn_cot_sn(java.lang.String newSeg_psn_cot_sn);
	/**
	 * Get accessor for persistent attribute: seg_tel_cot_sn
	 */
	public abstract java.lang.String getSeg_tel_cot_sn();
	/**
	 * Set accessor for persistent attribute: seg_tel_cot_sn
	 */
	public abstract void setSeg_tel_cot_sn(java.lang.String newSeg_tel_cot_sn);
	/**
	 * Get accessor for persistent attribute: tip_mdi_cmc_cd
	 */
	public abstract java.lang.String getTip_mdi_cmc_cd();
	/**
	 * Set accessor for persistent attribute: tip_mdi_cmc_cd
	 */
	public abstract void setTip_mdi_cmc_cd(java.lang.String newTip_mdi_cmc_cd);
	/**
	 * Get accessor for persistent attribute: cod_ctz_cd
	 */
	public abstract java.lang.String getCod_ctz_cd();
	/**
	 * Set accessor for persistent attribute: cod_ctz_cd
	 */
	public abstract void setCod_ctz_cd(java.lang.String newCod_ctz_cd);
	/**
	 * Get accessor for persistent attribute: cod_pra_ave_cd
	 */
	public abstract java.lang.String getCod_pra_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_pra_ave_cd
	 */
	public abstract void setCod_pra_ave_cd(java.lang.String newCod_pra_ave_cd);
	/**
	 * Get accessor for persistent attribute: pzo_max_rsl_nu
	 */
	public abstract java.lang.Long getPzo_max_rsl_nu();
	/**
	 * Set accessor for persistent attribute: pzo_max_rsl_nu
	 */
	public abstract void setPzo_max_rsl_nu(java.lang.Long newPzo_max_rsl_nu);
	/**
	 * Get accessor for persistent attribute: can_dia_ala_nu
	 */
	public abstract java.lang.Long getCan_dia_ala_nu();
	/**
	 * Set accessor for persistent attribute: can_dia_ala_nu
	 */
	public abstract void setCan_dia_ala_nu(java.lang.Long newCan_dia_ala_nu);
	/**
	 * Get accessor for persistent attribute: ind_env_rpt_in
	 */
	public abstract java.lang.String getInd_env_rpt_in();
	/**
	 * Set accessor for persistent attribute: ind_env_rpt_in
	 */
	public abstract void setInd_env_rpt_in(java.lang.String newInd_env_rpt_in);
	/**
	 * Get accessor for persistent attribute: ind_cob_vst_in
	 */
	public abstract java.lang.String getInd_cob_vst_in();
	/**
	 * Set accessor for persistent attribute: ind_cob_vst_in
	 */
	public abstract void setInd_cob_vst_in(java.lang.String newInd_cob_vst_in);
	/**
	 * Get accessor for persistent attribute: ind_apz_in
	 */
	public abstract java.lang.String getInd_apz_in();
	/**
	 * Set accessor for persistent attribute: ind_apz_in
	 */
	public abstract void setInd_apz_in(java.lang.String newInd_apz_in);
	/**
	 * Get accessor for persistent attribute: ind_ave_rei_in
	 */
	public abstract java.lang.String getInd_ave_rei_in();
	/**
	 * Set accessor for persistent attribute: ind_ave_rei_in
	 */
	public abstract void setInd_ave_rei_in(java.lang.String newInd_ave_rei_in);
	/**
	 * Get accessor for persistent attribute: ind_dft_emo_in
	 */
	public abstract java.lang.String getInd_dft_emo_in();
	/**
	 * Set accessor for persistent attribute: ind_dft_emo_in
	 */
	public abstract void setInd_dft_emo_in(java.lang.String newInd_dft_emo_in);
	/**
	 * Get accessor for persistent attribute: aju_flt_ser_in
	 */
	public abstract java.lang.String getAju_flt_ser_in();
	/**
	 * Set accessor for persistent attribute: aju_flt_ser_in
	 */
	public abstract void setAju_flt_ser_in(java.lang.String newAju_flt_ser_in);
	/**
	 * Get accessor for persistent attribute: ind_imp_mtl_in
	 */
	public abstract java.lang.String getInd_imp_mtl_in();
	/**
	 * Set accessor for persistent attribute: ind_imp_mtl_in
	 */
	public abstract void setInd_imp_mtl_in(java.lang.String newInd_imp_mtl_in);
	/**
	 * Get accessor for persistent attribute: ind_ser_efe_in
	 */
	public abstract java.lang.String getInd_ser_efe_in();
	/**
	 * Set accessor for persistent attribute: ind_ser_efe_in
	 */
	public abstract void setInd_ser_efe_in(java.lang.String newInd_ser_efe_in);
	/**
	 * Get accessor for persistent attribute: ind_equ_atc_in
	 */
	public abstract java.lang.String getInd_equ_atc_in();
	/**
	 * Set accessor for persistent attribute: ind_equ_atc_in
	 */
	public abstract void setInd_equ_atc_in(java.lang.String newInd_equ_atc_in);
	/**
	 * Get accessor for persistent attribute: ind_age_cit_in
	 */
	public abstract java.lang.String getInd_age_cit_in();
	/**
	 * Set accessor for persistent attribute: ind_age_cit_in
	 */
	public abstract void setInd_age_cit_in(java.lang.String newInd_age_cit_in);
	/**
	 * Get accessor for persistent attribute: fec_cit_ff
	 */
	public abstract java.lang.String getFec_cit_ff();
	/**
	 * Set accessor for persistent attribute: fec_cit_ff
	 */
	public abstract void setFec_cit_ff(java.lang.String newFec_cit_ff);
	/**
	 * Get accessor for persistent attribute: tim_fec_cps_ts
	 */
	public abstract java.sql.Timestamp getTim_fec_cps_ts();
	/**
	 * Set accessor for persistent attribute: tim_fec_cps_ts
	 */
	public abstract void setTim_fec_cps_ts(
		java.sql.Timestamp newTim_fec_cps_ts);
	/**
	 * Get accessor for persistent attribute: fec_apt_ave_ts
	 */
	public abstract java.sql.Timestamp getFec_apt_ave_ts();
	/**
	 * Set accessor for persistent attribute: fec_apt_ave_ts
	 */
	public abstract void setFec_apt_ave_ts(
		java.sql.Timestamp newFec_apt_ave_ts);
	/**
	 * Get accessor for persistent attribute: fec_cie_ave_ts
	 */
	public abstract java.sql.Timestamp getFec_cie_ave_ts();
	/**
	 * Set accessor for persistent attribute: fec_cie_ave_ts
	 */
	public abstract void setFec_cie_ave_ts(
		java.sql.Timestamp newFec_cie_ave_ts);
	/**
	 * Get accessor for persistent attribute: num_seg_lin_ds
	 */
	public abstract java.lang.String getNum_seg_lin_ds();
	/**
	 * Set accessor for persistent attribute: num_seg_lin_ds
	 */
	public abstract void setNum_seg_lin_ds(java.lang.String newNum_seg_lin_ds);
	/**
	 * Get accessor for persistent attribute: ori_alt_icd_in
	 */
	public abstract java.lang.String getOri_alt_icd_in();
	/**
	 * Set accessor for persistent attribute: ori_alt_icd_in
	 */
	public abstract void setOri_alt_icd_in(java.lang.String newOri_alt_icd_in);
	/**
	 * Get accessor for persistent attribute: mot_ccl_icd_cd
	 */
	public abstract java.lang.String getMot_ccl_icd_cd();
	/**
	 * Set accessor for persistent attribute: mot_ccl_icd_cd
	 */
	public abstract void setMot_ccl_icd_cd(java.lang.String newMot_ccl_icd_cd);
	/**
	 * Get accessor for persistent attribute: dsc_rpt_pru_ds
	 */
	public abstract java.lang.String getDsc_rpt_pru_ds();
	/**
	 * Set accessor for persistent attribute: dsc_rpt_pru_ds
	 */
	public abstract void setDsc_rpt_pru_ds(java.lang.String newDsc_rpt_pru_ds);
	/**
	 * Get accessor for persistent attribute: usr_alt_no
	 */
	public abstract java.lang.String getUsr_alt_no();
	/**
	 * Set accessor for persistent attribute: usr_alt_no
	 */
	public abstract void setUsr_alt_no(java.lang.String newUsr_alt_no);
	/**
	 * Get accessor for persistent attribute: usr_ult_mod_no
	 */
	public abstract java.lang.String getUsr_ult_mod_no();
	/**
	 * Set accessor for persistent attribute: usr_ult_mod_no
	 */
	public abstract void setUsr_ult_mod_no(java.lang.String newUsr_ult_mod_no);
	/**
	 * Get accessor for persistent attribute: ite_alt_sis_ts
	 */
	public abstract java.sql.Timestamp getIte_alt_sis_ts();
	/**
	 * Set accessor for persistent attribute: ite_alt_sis_ts
	 */
	public abstract void setIte_alt_sis_ts(
		java.sql.Timestamp newIte_alt_sis_ts);
	/**
	 * Get accessor for persistent attribute: tim_ult_mod_ts
	 */
	public abstract java.sql.Timestamp getTim_ult_mod_ts();
	/**
	 * Set accessor for persistent attribute: tim_ult_mod_ts
	 */
	public abstract void setTim_ult_mod_ts(
		java.sql.Timestamp newTim_ult_mod_ts);
	/**
	 * Get accessor for persistent attribute: lng_ele_inf_cd
	 */
	public abstract java.lang.Integer getLng_ele_inf_cd();
	/**
	 * Set accessor for persistent attribute: lng_ele_inf_cd
	 */
	public abstract void setLng_ele_inf_cd(java.lang.Integer newLng_ele_inf_cd);
	/**
	 * Get accessor for persistent attribute: obs_cit_ds
	 */
	public abstract java.lang.String getObs_cit_ds();
	/**
	 * Set accessor for persistent attribute: obs_cit_ds
	 */
	public abstract void setObs_cit_ds(java.lang.String newObs_cit_ds);
	/**
	 * Get accessor for persistent attribute: lng_ele_inf_sn
	 */
	public abstract java.lang.Integer getLng_ele_inf_sn();
	/**
	 * Set accessor for persistent attribute: lng_ele_inf_sn
	 */
	public abstract void setLng_ele_inf_sn(java.lang.Integer newLng_ele_inf_sn);
	/**
	 * Get accessor for persistent attribute: obs_ave_ds
	 */
	public abstract java.lang.String getObs_ave_ds();
	/**
	 * Set accessor for persistent attribute: obs_ave_ds
	 */
	public abstract void setObs_ave_ds(java.lang.String newObs_ave_ds);
	/**
	 * Get accessor for persistent attribute: tip_ave_mas_cd
	 */
	public abstract java.lang.String getTip_ave_mas_cd();
	/**
	 * Set accessor for persistent attribute: tip_ave_mas_cd
	 */
	public abstract void setTip_ave_mas_cd(java.lang.String newTip_ave_mas_cd);
	/**
	 * Get accessor for persistent attribute: stm_ave_mas_cd
	 */
	public abstract java.lang.String getStm_ave_mas_cd();
	/**
	 * Set accessor for persistent attribute: stm_ave_mas_cd
	 */
	public abstract void setStm_ave_mas_cd(java.lang.String newStm_ave_mas_cd);
	/**
	 * Get accessor for persistent attribute: cod_pro_ser_cd
	 */
	public abstract java.lang.Long getCod_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: cod_pro_ser_cd
	 */
	public abstract void setCod_pro_ser_cd(java.lang.Long newCod_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: est_ave_mas_cd
	 */
	public abstract java.lang.String getEst_ave_mas_cd();
	/**
	 * Set accessor for persistent attribute: est_ave_mas_cd
	 */
	public abstract void setEst_ave_mas_cd(java.lang.String newEst_ave_mas_cd);
	/**
	 * Get accessor for persistent attribute: cps_cje_mas_ff
	 */
	public abstract java.lang.String getCps_cje_mas_ff();
	/**
	 * Set accessor for persistent attribute: cps_cje_mas_ff
	 */
	public abstract void setCps_cje_mas_ff(java.lang.String newCps_cje_mas_ff);
	/**
	 * Get accessor for persistent attribute: apt_ave_mas_ff
	 */
	public abstract java.lang.String getApt_ave_mas_ff();
	/**
	 * Set accessor for persistent attribute: apt_ave_mas_ff
	 */
	public abstract void setApt_ave_mas_ff(java.lang.String newApt_ave_mas_ff);
	/**
	 * Get accessor for persistent attribute: cie_ave_mas_ff
	 */
	public abstract java.lang.String getCie_ave_mas_ff();
	/**
	 * Set accessor for persistent attribute: cie_ave_mas_ff
	 */
	public abstract void setCie_ave_mas_ff(java.lang.String newCie_ave_mas_ff);
	/**
	 * Get accessor for persistent attribute: ext_ave_mas_ds
	 */
	public abstract java.lang.String getExt_ave_mas_ds();
	/**
	 * Set accessor for persistent attribute: ext_ave_mas_ds
	 */
	public abstract void setExt_ave_mas_ds(java.lang.String newExt_ave_mas_ds);
	/**
	 * Get accessor for persistent attribute: lng_cpe_inf_sn
	 */
	public abstract java.lang.Integer getLng_cpe_inf_sn();
	/**
	 * Set accessor for persistent attribute: lng_cpe_inf_sn
	 */
	public abstract void setLng_cpe_inf_sn(java.lang.Integer newLng_cpe_inf_sn);
	/**
	 * Get accessor for persistent attribute: obs_ave_mas_ds
	 */
	public abstract java.lang.String getObs_ave_mas_ds();
	/**
	 * Set accessor for persistent attribute: obs_ave_mas_ds
	 */
	public abstract void setObs_ave_mas_ds(java.lang.String newObs_ave_mas_ds);
	/**
	 * Get accessor for persistent attribute: mot_ave_mas_cd
	 */
	public abstract java.lang.String getMot_ave_mas_cd();
	/**
	 * Set accessor for persistent attribute: mot_ave_mas_cd
	 */
	public abstract void setMot_ave_mas_cd(java.lang.String newMot_ave_mas_cd);
	/**
	 * Get accessor for persistent attribute: tip_cal_ati_cd
	 */
	public abstract java.lang.String getTip_cal_ati_cd();
	/**
	 * Set accessor for persistent attribute: tip_cal_ati_cd
	 */
	public abstract void setTip_cal_ati_cd(java.lang.String newTip_cal_ati_cd);
	/**
	 * Get accessor for persistent attribute: nom_cal_ds
	 */
	public abstract java.lang.String getNom_cal_ds();
	/**
	 * Set accessor for persistent attribute: nom_cal_ds
	 */
	public abstract void setNom_cal_ds(java.lang.String newNom_cal_ds);
	/**
	 * Get accessor for persistent attribute: num_cal_nu
	 */
	public abstract java.lang.String getNum_cal_nu();
	/**
	 * Set accessor for persistent attribute: num_cal_nu
	 */
	public abstract void setNum_cal_nu(java.lang.String newNum_cal_nu);
	/**
	 * Get accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public abstract java.lang.String getDsc_cmp_pri_ds();
	/**
	 * Set accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public abstract void setDsc_cmp_pri_ds(java.lang.String newDsc_cmp_pri_ds);
	/**
	 * Get accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public abstract java.lang.String getDsc_cmp_seg_ds();
	/**
	 * Set accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public abstract void setDsc_cmp_seg_ds(java.lang.String newDsc_cmp_seg_ds);
	/**
	 * Get accessor for persistent attribute: cod_ext_loc_cd
	 */
	public abstract java.lang.String getCod_ext_loc_cd();
	/**
	 * Set accessor for persistent attribute: cod_ext_loc_cd
	 */
	public abstract void setCod_ext_loc_cd(java.lang.String newCod_ext_loc_cd);
	/**
	 * Get accessor for persistent attribute: reintento
	 */
	public abstract java.lang.Integer getReintento();
	/**
	 * Set accessor for persistent attribute: reintento
	 */
	public abstract void setReintento(java.lang.Integer newReintento);
	/**
	 * Get accessor for persistent attribute: estado_peticion
	 */
	public abstract java.lang.Integer getEstado_peticion();
	/**
	 * Set accessor for persistent attribute: estado_peticion
	 */
	public abstract void setEstado_peticion(
		java.lang.Integer newEstado_peticion);
	/**
	 * Get accessor for persistent attribute: fecha_sol_falla
	 */
	public abstract java.lang.String getFecha_sol_falla();
	/**
	 * Set accessor for persistent attribute: fecha_sol_falla
	 */
	public abstract void setFecha_sol_falla(
		java.lang.String newFecha_sol_falla);
	/**
	 * Get accessor for persistent attribute: cod_ps_averia
	 */
	public abstract java.lang.Long getCod_ps_averia();
	/**
	 * Set accessor for persistent attribute: cod_ps_averia
	 */
	public abstract void setCod_ps_averia(java.lang.Long newCod_ps_averia);
	/**
	 * Get accessor for persistent attribute: cod_ps_tipo
	 */
	public abstract java.lang.Long getCod_ps_tipo();
	/**
	 * Set accessor for persistent attribute: cod_ps_tipo
	 */
	public abstract void setCod_ps_tipo(java.lang.Long newCod_ps_tipo);
	/**
	 * Get accessor for persistent attribute: pro_ser_cto_cd
	 */
	public abstract java.lang.Long getPro_ser_cto_cd();
	/**
	 * Set accessor for persistent attribute: pro_ser_cto_cd
	 */
	public abstract void setPro_ser_cto_cd(java.lang.Long newPro_ser_cto_cd);
	/**
	 * Get accessor for persistent attribute: cod_dpt
	 */
	public abstract java.lang.String getCod_dpt();
	/**
	 * Set accessor for persistent attribute: cod_dpt
	 */
	public abstract void setCod_dpt(java.lang.String newCod_dpt);
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public abstract java.lang.String getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.String newCod_loc);
	/**
	 * Get accessor for persistent attribute: cod_central
	 */
	public abstract java.lang.Long getCod_central();
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public abstract void setCod_central(java.lang.Long newCod_central);
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion_flujo();
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_flujo(
		java.util.Collection aPeticion_flujo);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBitacora_peticion(
		java.util.Collection aBitacora_peticion);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTematico();
	/**
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTematico(java.util.Collection aTematico);
	/**
	 * This method was generated for supporting the relationship role named deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDeco_tarjeta();
	/**
	 * This method was generated for supporting the relationship role named deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDeco_tarjeta(java.util.Collection aDeco_tarjeta);
	/**
	 * This method was generated for supporting the relationship role named modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getModem();
	/**
	 * This method was generated for supporting the relationship role named modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setModem(java.util.Collection aModem);
	/**
	 * This method was generated for supporting the relationship role named tmp_deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTmp_deco_tarjeta();
	/**
	 * This method was generated for supporting the relationship role named tmp_deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTmp_deco_tarjeta(
		java.util.Collection aTmp_deco_tarjeta);
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPrueba_linea();
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPrueba_linea(java.util.Collection aPrueba_linea);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_st();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_st(
		java.util.Collection aMensaje_estado_st);
	/**
	 * This method was generated for supporting the relationship role named codigo_diagnostico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_diagnosticoLocal getCodigo_diagnostico();
	/**
	 * This method was generated for supporting the relationship role named codigo_diagnostico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_diagnostico(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Codigo_diagnosticoLocal aCodigo_diagnostico);
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCodigo_cierre_peticion();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_cierre_peticion(
		java.util.Collection aCodigo_cierre_peticion);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRecursos_linea_basica(
		java.util.Collection aRecursos_linea_basica);
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRecursos_ba();
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRecursos_ba(java.util.Collection aRecursos_ba);
	/**
	 * This method was generated for supporting the relationship role named tmp_modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTmp_modem();
	/**
	 * This method was generated for supporting the relationship role named tmp_modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTmp_modem(java.util.Collection aTmp_modem);
	/**
	 * This method was generated for supporting the relationship role named controlvisita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getControlvisita();
	/**
	 * This method was generated for supporting the relationship role named controlvisita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setControlvisita(java.util.Collection aControlvisita);
	/**
	 * Get accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public abstract java.lang.Long getCod_sbg_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public abstract void setCod_sbg_cta_cd(java.lang.Long newCod_sbg_cta_cd);
	/**
	 * Get accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public abstract java.lang.Long getCod_sgm_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public abstract void setCod_sgm_cta_cd(java.lang.Long newCod_sgm_cta_cd);
	/**
	 * Get accessor for persistent attribute: fdl_soportado
	 */
	public abstract java.lang.Short getFdl_soportado();
	/**
	 * Set accessor for persistent attribute: fdl_soportado
	 */
	public abstract void setFdl_soportado(java.lang.Short newFdl_soportado);
    /**
     * This method was generated for supporting the relationship role named elemento_peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract java.util.Collection getElemento_peticion();
    /**
     * This method was generated for supporting the relationship role named elemento_peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract void setElemento_peticion(
        java.util.Collection anElemento_peticion);
    /**
     * This method was generated for supporting the relationship role named tmp_equipo.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract java.util.Collection getTmp_equipo();
    /**
     * This method was generated for supporting the relationship role named tmp_equipo.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract void setTmp_equipo(java.util.Collection aTmp_equipo);

	/**
	 * Get accessor for persistent attribute: id_cierre
	 */
	public abstract java.lang.Long getId_cierre();
	/**
	 * Set accessor for persistent attribute: id_cierre
	 */
	public abstract void setId_cierre(java.lang.Long newId_cierre);
	/**
	 * Get accessor for persistent attribute: regla_id
	 */
	public abstract java.lang.Long getRegla_id();
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public abstract void setRegla_id(java.lang.Long newRegla_id);
	/**
	 * Get accessor for persistent attribute: id_def_aper
	 */
	public abstract java.lang.String getId_def_aper();
	/**
	 * Set accessor for persistent attribute: id_def_aper
	 */
	public abstract void setId_def_aper(java.lang.String newId_def_aper);
	/**
	 * Get accessor for persistent attribute: granite_code
	 */
	public abstract java.lang.Integer getGranite_code();
	/**
	 * Set accessor for persistent attribute: granite_code
	 */
	public abstract void setGranite_code(java.lang.Integer newGranite_code);
	/**
	 * This method was generated for supporting the relationship role named agendascst.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAgendascst();
	/**
	 * This method was generated for supporting the relationship role named agendascst.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAgendascst(java.util.Collection anAgendascst);
	/**
	 * Get accessor for persistent attribute: coordenada_x
	 */
	public abstract java.lang.String getCoordenada_x();
	/**
	 * Set accessor for persistent attribute: coordenada_x
	 */
	public abstract void setCoordenada_x(java.lang.String newCoordenada_x);
	/**
	 * Get accessor for persistent attribute: coordenada_y
	 */
	public abstract java.lang.String getCoordenada_y();
	/**
	 * Set accessor for persistent attribute: coordenada_y
	 */
	public abstract void setCoordenada_y(java.lang.String newCoordenada_y);
	/**
	 * This method was generated for supporting the relationship role named elementonoserializado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getElementonoserializado();
	/**
	 * This method was generated for supporting the relationship role named elementonoserializado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setElementonoserializado(
		java.util.Collection anElementonoserializado);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_tv
	 */
	public abstract java.lang.String getNum_ide_nu_tv();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_tv
	 */
	public abstract void setNum_ide_nu_tv(java.lang.String newNum_ide_nu_tv);
}
