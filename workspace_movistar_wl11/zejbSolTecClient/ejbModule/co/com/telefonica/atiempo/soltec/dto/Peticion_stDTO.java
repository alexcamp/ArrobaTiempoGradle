package co.com.telefonica.atiempo.soltec.dto;

import java.sql.Timestamp;

/**
 * @author defuenz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Peticion_stDTO {

	/**
	 * 
	 */
	public Peticion_stDTO() {
		super();
	}
	
	private Long cod_ave_cd;
	private Long cod_cli_cd;
	private Long pro_ser_cto_cd;
	private String ide_pro_cmr_cd;
	private String num_ide_nu;
	private String cod_apt_ave_cd;
	private String cod_cie_ave_cd;
	private String cod_stm_ave_cd;
	private String rpt_pru_ave_cd;
	private String cod_est_ave_cd;
	private Long cod_pet_cd;
	private Long cod_ave_mas_cd;
	private Long cod_ave_ori_sn;
	private String tip_doc_rte_cd;
	private String num_doc_rte_nu;
	private String cct_doc_rte_cd;
	private String nom_rte_sn;
	private String pri_ape_rte_sn;
	private String seg_ape_rte_sn;
	private String cod_rel_cli_cd;
	private String rel_cli_otr_ds;
	private String nom_psn_cot_no;
	private String tel_cot_ds;
	private String seg_psn_cot_sn;
	private String seg_tel_cot_sn;
	private String tip_mdi_cmc_cd;
	private String cod_ctz_cd;
	private String cod_pra_ave_cd;
	private Long pzo_max_rsl_nu;
	private Long can_dia_ala_nu;
	private String ind_end_rpt_in;
	private String ind_cob_vst_in;
	private String ind_apz_in;
	private String ind_ave_rei_in;
	private String ind_dft_emo_in;
	private String aju_flt_ser_in;
	private String ind_imp_mtl_in;
	private String ind_ser_efe_in;
	private String ind_equ_atc_in;
	private String ind_age_cit_in;
	private String fec_cit_ff;
	private Timestamp tim_fec_cps_ts;
	private Timestamp fec_apt_ave_ts;
	private Timestamp fec_cie_ave_ts;
	private String num_seg_lin_ds;
	private String ori_alt_icd_in;
	private String mot_ccl_icd_cd;
	private String dsc_rpt_pru_ds;
	private String usr_alt_no;
	private String usr_ult_mod_no;
	private Timestamp ite_alt_sis_ts;
	private Timestamp tim_ult_mod_ts;
	private Double lng_ele_inf_cd;
	private String obs_cit_ds;
	private Double lng_ele_inf_sn;
	private String gobs_ave_ds;
	private String tip_ave_mas_cd;
	private String stm_ave_mas_cd;
	private Long cod_pro_ser_cd;
	private String est_ave_mas_cd;
	private String cps_cje_mas_ff;
	private String apt_ave_mas_ff;
	private String cie_ave_mas_ff;
	private String ext_ave_mas_ds;
	private Integer ing_cpe_inf_sn;
	private String obs_ave_mas_ds;
	private String mot_ave_mas_cd;
	private String tip_cal_ati_cd;
	private String nom_cal_ds;
	private String num_cal_nu;
	private String dsc_cmp_pri_ds;
	private String dsc_cmp_seg_ds;
	private String cod_ext_loc_cd;
	private String cod_dpt;
	private String cod_loc;
	private Long cod_central;
	private Integer reintento;
	private Integer estado_peticion;
	private String fecha_sol_falla;
	private String frm_frm_ntf_cd;
	private Long cod_are_ges_cd;
	private String obs_ave_ds;
	private Integer lng_cpe_inf_sn;
		
	private String descLocalidad;
	private String descCentral;
	private String descDepartamento;
	
	private Long codPsAveria;
	private String descPsAveria;
	
	
	private boolean estaEnBandeja=false;
	private String urlBandeja;
	
//	CR17031 pcawen
	private Long codSgmCtaCd;
	private Long codSbgCtaCd;
	
	//TCS-gquevedo Jun 1, 2009 CR24382 INICIO
	private Short fdlSoportado;
	//TCS-gquevedo Jun 1, 2009 CR24382 FIN
	
	//30/10/2013 (everis - JADN)
	private String num_ide_nu_tv;
	
	/**
	 * @return Returns the codSbgCtaCd.
	 */
	public Long getCodSbgCtaCd() {
		return codSbgCtaCd;
	}
	/**
	 * @param codSbgCtaCd The codSbgCtaCd to set.
	 */
	public void setCodSbgCtaCd(Long codSbgCtaCd) {
		this.codSbgCtaCd = codSbgCtaCd;
	}
	/**
	 * @return Returns the codSgmCtaCd.
	 */
	public Long getCodSgmCtaCd() {
		return codSgmCtaCd;
	}
	/**
	 * @param codSgmCtaCd The codSgmCtaCd to set.
	 */
	public void setCodSgmCtaCd(Long codSgmCtaCd) {
		this.codSgmCtaCd = codSgmCtaCd;
	}
//	CR17031 - fin
		
	public Long getCod_ave_cd(){
		return cod_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_ave_cd
	 */
	public void setCod_ave_cd(Long newCod_ave_cd){
		cod_ave_cd=newCod_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_cli_cd
	 */
	public Long getCod_cli_cd(){
		return cod_cli_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_cli_cd
	 */
	public void setCod_cli_cd(Long newCod_cli_cd){
		cod_cli_cd=newCod_cli_cd;
	};
	/**
	 * Get accessor for persistent attribute: pro_ser_cto_cd
	 */
	public Long getPro_ser_cto_cd(){
		return pro_ser_cto_cd;
	};
	/**
	 * Set accessor for persistent attribute: pro_ser_cto_cd
	 */
	public void setPro_ser_cto_cd(Long newPro_ser_cto_cd){
		pro_ser_cto_cd=newPro_ser_cto_cd;
	};
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public String getIde_pro_cmr_cd(){
		return ide_pro_cmr_cd;
	};
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public void setIde_pro_cmr_cd(String newIde_pro_cmr_cd){
		ide_pro_cmr_cd=newIde_pro_cmr_cd;
	};
	/**
	 * Get accessor for persistent attribute: num_ide_nu
	 */
	public String getNum_ide_nu(){
		return num_ide_nu;
	};
	/**
	 * Set accessor for persistent attribute: num_ide_nu
	 */
	public void setNum_ide_nu(String newNum_ide_nu){
		num_ide_nu=newNum_ide_nu;
	};
	/**
	 * Get accessor for persistent attribute: cod_apt_ave_cd
	 */
	public String getCod_apt_ave_cd(){
		return cod_apt_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_apt_ave_cd
	 */
	public void setCod_apt_ave_cd(String newCod_apt_ave_cd){
		cod_apt_ave_cd=newCod_apt_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_cie_ave_cd
	 */
	public String getCod_cie_ave_cd(){
		return cod_cie_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_cie_ave_cd
	 */
	public void setCod_cie_ave_cd(String newCod_cie_ave_cd){
		cod_cie_ave_cd=newCod_cie_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_stm_ave_cd
	 */
	public String getCod_stm_ave_cd(){
		return cod_stm_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_stm_ave_cd
	 */
	public void setCod_stm_ave_cd(String newCod_stm_ave_cd){
		cod_stm_ave_cd=newCod_stm_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: rpt_pru_ave_cd
	 */
	public String getRpt_pru_ave_cd(){
		return rpt_pru_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: rpt_pru_ave_cd
	 */
	public void setRpt_pru_ave_cd(String newRpt_pru_ave_cd){
		rpt_pru_ave_cd=newRpt_pru_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_est_ave_cd
	 */
	public String getCod_est_ave_cd(){
		return cod_est_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_est_ave_cd
	 */
	public void setCod_est_ave_cd(String newCod_est_ave_cd){
		cod_est_ave_cd=newCod_est_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_pet_cd
	 */
	public Long getCod_pet_cd(){
		return cod_pet_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_pet_cd
	 */
	public void setCod_pet_cd(Long newCod_pet_cd){
		cod_pet_cd=newCod_pet_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_ave_mas_cd
	 */
	public  java.lang.Long getCod_ave_mas_cd(){
		return cod_ave_mas_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_ave_mas_cd
	 */
	public  void setCod_ave_mas_cd(java.lang.Long newCod_ave_mas_cd){
		cod_ave_mas_cd=newCod_ave_mas_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_ave_ori_sn
	 */
	public  java.lang.Long getCod_ave_ori_sn(){
		return cod_ave_ori_sn;
	};
	/**
	 * Set accessor for persistent attribute: cod_ave_ori_sn
	 */
	public  void setCod_ave_ori_sn(java.lang.Long newCod_ave_ori_sn){
		cod_ave_ori_sn=newCod_ave_ori_sn;
	};
	/**
	 * Get accessor for persistent attribute: tip_doc_rte_cd
	 */
	public  java.lang.String getTip_doc_rte_cd(){
		return tip_doc_rte_cd;
	};
	/**
	 * Set accessor for persistent attribute: tip_doc_rte_cd
	 */
	public  void setTip_doc_rte_cd(java.lang.String newTip_doc_rte_cd){
		tip_doc_rte_cd=newTip_doc_rte_cd;
	};
	/**
	 * Get accessor for persistent attribute: num_doc_rte_nu
	 */
	public  java.lang.String getNum_doc_rte_nu(){
		return num_doc_rte_nu;
	};
	/**
	 * Set accessor for persistent attribute: num_doc_rte_nu
	 */
	public  void setNum_doc_rte_nu(java.lang.String newNum_doc_rte_nu){
		num_doc_rte_nu=newNum_doc_rte_nu;
	};
	/**
	 * Get accessor for persistent attribute: cct_doc_rte_cd
	 */
	public  java.lang.String getCct_doc_rte_cd(){
		return cct_doc_rte_cd;
	};
	/**
	 * Set accessor for persistent attribute: cct_doc_rte_cd
	 */
	public  void setCct_doc_rte_cd(java.lang.String newCct_doc_rte_cd){
		cct_doc_rte_cd=newCct_doc_rte_cd;
	};
	/**
	 * Get accessor for persistent attribute: nom_rte_sn
	 */
	public  java.lang.String getNom_rte_sn(){
		return nom_rte_sn;
	};
	/**
	 * Set accessor for persistent attribute: nom_rte_sn
	 */
	public  void setNom_rte_sn(java.lang.String newNom_rte_sn){
		nom_rte_sn=newNom_rte_sn;
	};
	/**
	 * Get accessor for persistent attribute: pri_ape_rte_sn
	 */
	public  java.lang.String getPri_ape_rte_sn(){
		return pri_ape_rte_sn;
	};
	/**
	 * Set accessor for persistent attribute: pri_ape_rte_sn
	 */
	public  void setPri_ape_rte_sn(java.lang.String newPri_ape_rte_sn){
		pri_ape_rte_sn=newPri_ape_rte_sn;
	};
	/**
	 * Get accessor for persistent attribute: seg_ape_rte_sn
	 */
	public  java.lang.String getSeg_ape_rte_sn(){
		return seg_ape_rte_sn;
	};
	/**
	 * Set accessor for persistent attribute: seg_ape_rte_sn
	 */
	public  void setSeg_ape_rte_sn(java.lang.String newSeg_ape_rte_sn){
		seg_ape_rte_sn=newSeg_ape_rte_sn;
	};
	/**
	 * Get accessor for persistent attribute: cod_rel_cli_cd
	 */
	public  java.lang.String getCod_rel_cli_cd(){
		return cod_rel_cli_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_rel_cli_cd
	 */
	public  void setCod_rel_cli_cd(java.lang.String newCod_rel_cli_cd){
		cod_rel_cli_cd=newCod_rel_cli_cd;
	};
	/**
	 * Get accessor for persistent attribute: rel_cli_otr_ds
	 */
	public  java.lang.String getRel_cli_otr_ds(){
		return rel_cli_otr_ds;
	};
	/**
	 * Set accessor for persistent attribute: rel_cli_otr_ds
	 */
	public  void setRel_cli_otr_ds(java.lang.String newRel_cli_otr_ds){
		rel_cli_otr_ds=newRel_cli_otr_ds;
	};
	/**
	 * Get accessor for persistent attribute: nom_psn_cot_no
	 */
	public  java.lang.String getNom_psn_cot_no(){
		return nom_psn_cot_no;
	};
	/**
	 * Set accessor for persistent attribute: nom_psn_cot_no
	 */
	public  void setNom_psn_cot_no(java.lang.String newNom_psn_cot_no){
		nom_psn_cot_no=newNom_psn_cot_no;
	};
	/**
	 * Get accessor for persistent attribute: tel_cot_ds
	 */
	public  java.lang.String getTel_cot_ds(){
		return tel_cot_ds;
	};
	/**
	 * Set accessor for persistent attribute: tel_cot_ds
	 */
	public  void setTel_cot_ds(java.lang.String newTel_cot_ds){
		tel_cot_ds=newTel_cot_ds;
	};
	/**
	 * Get accessor for persistent attribute: seg_psn_cot_sn
	 */
	public  java.lang.String getSeg_psn_cot_sn(){
		return seg_psn_cot_sn;
	};
	/**
	 * Set accessor for persistent attribute: seg_psn_cot_sn
	 */
	public  void setSeg_psn_cot_sn(java.lang.String newSeg_psn_cot_sn){
		seg_psn_cot_sn=newSeg_psn_cot_sn;
	};
	/**
	 * Get accessor for persistent attribute: seg_tel_cot_sn
	 */
	public  java.lang.String getSeg_tel_cot_sn(){
		return seg_tel_cot_sn;
	};
	/**
	 * Set accessor for persistent attribute: seg_tel_cot_sn
	 */
	public  void setSeg_tel_cot_sn(java.lang.String newSeg_tel_cot_sn){
		seg_tel_cot_sn=newSeg_tel_cot_sn;
	};
	/**
	 * Get accessor for persistent attribute: tip_mdi_cmc_cd
	 */
	public  java.lang.String getTip_mdi_cmc_cd(){
		return tip_mdi_cmc_cd;
	};
	/**
	 * Set accessor for persistent attribute: tip_mdi_cmc_cd
	 */
	public  void setTip_mdi_cmc_cd(java.lang.String newTip_mdi_cmc_cd){
		tip_mdi_cmc_cd=newTip_mdi_cmc_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_ctz_cd
	 */
	public  java.lang.String getCod_ctz_cd(){
		return cod_ctz_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_ctz_cd
	 */
	public  void setCod_ctz_cd(java.lang.String newCod_ctz_cd){
		cod_ctz_cd=newCod_ctz_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_pra_ave_cd
	 */
	public  java.lang.String getCod_pra_ave_cd(){
		return cod_pra_ave_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_pra_ave_cd
	 */
	public  void setCod_pra_ave_cd(java.lang.String newCod_pra_ave_cd){
		cod_pra_ave_cd=newCod_pra_ave_cd;
	};
	/**
	 * Get accessor for persistent attribute: pzo_max_rsl_nu
	 */
	public  java.lang.Long getPzo_max_rsl_nu(){
		return pzo_max_rsl_nu;
	};
	/**
	 * Set accessor for persistent attribute: pzo_max_rsl_nu
	 */
	public  void setPzo_max_rsl_nu(java.lang.Long newPzo_max_rsl_nu){
		pzo_max_rsl_nu=newPzo_max_rsl_nu;
	};
	/**
	 * Get accessor for persistent attribute: can_dia_ala_nu
	 */
	public  java.lang.Long getCan_dia_ala_nu(){
		return can_dia_ala_nu;
	};
	/**
	 * Set accessor for persistent attribute: can_dia_ala_nu
	 */
	public  void setCan_dia_ala_nu(java.lang.Long newCan_dia_ala_nu){
		can_dia_ala_nu=newCan_dia_ala_nu;
	};
	/**
	 * Get accessor for persistent attribute: ind_end_rpt_in
	 */
	public  java.lang.String getInd_end_rpt_in(){
		return ind_end_rpt_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_end_rpt_in
	 */
	public  void setInd_end_rpt_in(java.lang.String newInd_end_rpt_in){
		ind_end_rpt_in=newInd_end_rpt_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_cob_vst_in
	 */
	public  java.lang.String getInd_cob_vst_in(){
		return ind_cob_vst_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_cob_vst_in
	 */
	public  void setInd_cob_vst_in(java.lang.String newInd_cob_vst_in){
		ind_cob_vst_in=newInd_cob_vst_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_apz_in
	 */
	public  java.lang.String getInd_apz_in(){
		return ind_apz_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_apz_in
	 */
	public  void setInd_apz_in(java.lang.String newInd_apz_in){
		ind_apz_in=newInd_apz_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_ave_rei_in
	 */
	public  java.lang.String getInd_ave_rei_in(){
		return ind_ave_rei_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_ave_rei_in
	 */
	public  void setInd_ave_rei_in(java.lang.String newInd_ave_rei_in){
		ind_ave_rei_in=newInd_ave_rei_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_dft_emo_in
	 */
	public  java.lang.String getInd_dft_emo_in(){
		return ind_dft_emo_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_dft_emo_in
	 */
	public  void setInd_dft_emo_in(java.lang.String newInd_dft_emo_in){
		ind_dft_emo_in=newInd_dft_emo_in;
	};
	/**
	 * Get accessor for persistent attribute: aju_flt_ser_in
	 */
	public  java.lang.String getAju_flt_ser_in(){
		return aju_flt_ser_in;
	};
	/**
	 * Set accessor for persistent attribute: aju_flt_ser_in
	 */
	public  void setAju_flt_ser_in(java.lang.String newAju_flt_ser_in){
		aju_flt_ser_in=newAju_flt_ser_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_imp_mtl_in
	 */
	public  java.lang.String getInd_imp_mtl_in(){
		return ind_imp_mtl_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_imp_mtl_in
	 */
	public  void setInd_imp_mtl_in(java.lang.String newInd_imp_mtl_in){
		ind_imp_mtl_in=newInd_imp_mtl_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_ser_efe_in
	 */
	public  java.lang.String getInd_ser_efe_in(){
		return ind_ser_efe_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_ser_efe_in
	 */
	public  void setInd_ser_efe_in(java.lang.String newInd_ser_efe_in){
		ind_ser_efe_in=newInd_ser_efe_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_equ_atc_in
	 */
	public  java.lang.String getInd_equ_atc_in(){
		return ind_equ_atc_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_equ_atc_in
	 */
	public  void setInd_equ_atc_in(java.lang.String newInd_equ_atc_in){
		ind_equ_atc_in=newInd_equ_atc_in;
	};
	/**
	 * Get accessor for persistent attribute: ind_age_cit_in
	 */
	public  java.lang.String getInd_age_cit_in(){
		return ind_age_cit_in;
	};
	/**
	 * Set accessor for persistent attribute: ind_age_cit_in
	 */
	public  void setInd_age_cit_in(java.lang.String newInd_age_cit_in){
		ind_age_cit_in=newInd_age_cit_in;
	};
	/**
	 * Get accessor for persistent attribute: fec_cit_ff
	 */
	public  java.lang.String getFec_cit_ff(){
		return fec_cit_ff;
	};
	/**
	 * Set accessor for persistent attribute: fec_cit_ff
	 */
	public  void setFec_cit_ff(java.lang.String newFec_cit_ff){
		fec_cit_ff=newFec_cit_ff;
	};

	/**
	 * Get accessor for persistent attribute: fec_apt_ave_ts
	 */
	public  java.sql.Timestamp getFec_apt_ave_ts(){
		return fec_apt_ave_ts;
	};
	/**
	 * Set accessor for persistent attribute: fec_apt_ave_ts
	 */
	public  void setFec_apt_ave_ts(java.sql.Timestamp newFec_apt_ave_ts){
		fec_apt_ave_ts=newFec_apt_ave_ts;
	};
	/**
	 * Get accessor for persistent attribute: fec_cie_ave_ts
	 */
	public  java.sql.Timestamp getFec_cie_ave_ts(){
		return fec_cie_ave_ts;
	};
	/**
	 * Set accessor for persistent attribute: fec_cie_ave_ts
	 */
	public  void setFec_cie_ave_ts(java.sql.Timestamp newFec_cie_ave_ts){
		fec_cie_ave_ts=newFec_cie_ave_ts;
	};
	/**
	 * Get accessor for persistent attribute: num_seg_lin_ds
	 */
	public  java.lang.String getNum_seg_lin_ds(){
		return num_seg_lin_ds;
	};
	/**
	 * Set accessor for persistent attribute: num_seg_lin_ds
	 */
	public  void setNum_seg_lin_ds(java.lang.String newNum_seg_lin_ds){
		num_seg_lin_ds=newNum_seg_lin_ds;
	};
	/**
	 * Get accessor for persistent attribute: ori_alt_icd_in
	 */
	public  java.lang.String getOri_alt_icd_in(){
		return ori_alt_icd_in;
	};
	/**
	 * Set accessor for persistent attribute: ori_alt_icd_in
	 */
	public  void setOri_alt_icd_in(java.lang.String newOri_alt_icd_in){
		ori_alt_icd_in=newOri_alt_icd_in;
	};
	/**
	 * Get accessor for persistent attribute: mot_ccl_icd_cd
	 */
	public  java.lang.String getMot_ccl_icd_cd(){
		return mot_ccl_icd_cd;
	};
	/**
	 * Set accessor for persistent attribute: mot_ccl_icd_cd
	 */
	public  void setMot_ccl_icd_cd(java.lang.String newMot_ccl_icd_cd){
		mot_ccl_icd_cd=newMot_ccl_icd_cd;
	};
	/**
	 * Get accessor for persistent attribute: dsc_rpt_pru_ds
	 */
	public  java.lang.String getDsc_rpt_pru_ds(){
		return dsc_rpt_pru_ds;
	};
	/**
	 * Set accessor for persistent attribute: dsc_rpt_pru_ds
	 */
	public  void setDsc_rpt_pru_ds(java.lang.String newDsc_rpt_pru_ds){
		dsc_rpt_pru_ds=newDsc_rpt_pru_ds;
	};
	/**
	 * Get accessor for persistent attribute: usr_alt_no
	 */
	public  java.lang.String getUsr_alt_no()
	{ return usr_alt_no;};
	/**
	 * Set accessor for persistent attribute: usr_alt_no
	 */
	public  void setUsr_alt_no(java.lang.String newUsr_alt_no){
		usr_alt_no=newUsr_alt_no;
	};
	/**
	 * Get accessor for persistent attribute: usr_ult_mod_no
	 */
	public  java.lang.String getUsr_ult_mod_no(){
		return usr_ult_mod_no;
	};
	/**
	 * Set accessor for persistent attribute: usr_ult_mod_no
	 */
	public  void setUsr_ult_mod_no(java.lang.String newUsr_ult_mod_no){
		usr_ult_mod_no=newUsr_ult_mod_no;
	};
	/**
	 * Get accessor for persistent attribute: ite_alt_sis_ts
	 */
	public  java.sql.Timestamp getIte_alt_sis_ts(){
		return ite_alt_sis_ts;
	};
	/**
	 * Set accessor for persistent attribute: ite_alt_sis_ts
	 */
	public  void setIte_alt_sis_ts(java.sql.Timestamp newIte_alt_sis_ts){
		ite_alt_sis_ts=newIte_alt_sis_ts;
	};
	/**
	 * Get accessor for persistent attribute: tim_ult_mod_ts
	 */
	public  java.sql.Timestamp getTim_ult_mod_ts(){
		return tim_ult_mod_ts;
	};
	/**
	 * Set accessor for persistent attribute: tim_ult_mod_ts
	 */
	public  void setTim_ult_mod_ts(java.sql.Timestamp newTim_ult_mod_ts){
		tim_ult_mod_ts=newTim_ult_mod_ts;
	};
	/**
	 * Get accessor for persistent attribute: lng_ele_inf_cd
	 */
	public  Double getLng_ele_inf_cd(){
		return lng_ele_inf_cd;
	};
	/**
	 * Set accessor for persistent attribute: lng_ele_inf_cd
	 */
	public  void setLng_ele_inf_cd(Double newLng_ele_inf_cd){
		lng_ele_inf_cd=newLng_ele_inf_cd;
	};
	/**
	 * Get accessor for persistent attribute: obs_cit_ds
	 */
	public  java.lang.String getObs_cit_ds(){
		return obs_cit_ds;
	};
	/**
	 * Set accessor for persistent attribute: obs_cit_ds
	 */
	public  void setObs_cit_ds(java.lang.String newObs_cit_ds){
		obs_cit_ds=newObs_cit_ds;
	};
	/**
	 * Get accessor for persistent attribute: lng_ele_inf_sn
	 */
	public  Double getLng_ele_inf_sn(){
		return lng_ele_inf_sn;
	};
	/**
	 * Set accessor for persistent attribute: lng_ele_inf_sn
	 */
	public  void setLng_ele_inf_sn(Double newLng_ele_inf_sn){
		lng_ele_inf_sn=newLng_ele_inf_sn;
	};
	/**
	 * Get accessor for persistent attribute: obs_ave_ds
	 */
	public  java.lang.String getObs_ave_ds(){
		return obs_ave_ds;
	};
	/**
	 * Set accessor for persistent attribute: obs_ave_ds
	 */
	public  void setObs_ave_ds(java.lang.String newObs_ave_ds){
		obs_ave_ds=newObs_ave_ds;
	};
	/**
	 * Get accessor for persistent attribute: tip_ave_mas_cd
	 */
	public  java.lang.String getTip_ave_mas_cd(){
		return tip_ave_mas_cd;
	};
	/**
	 * Set accessor for persistent attribute: tip_ave_mas_cd
	 */
	public  void setTip_ave_mas_cd(java.lang.String newTip_ave_mas_cd){
		tip_ave_mas_cd=newTip_ave_mas_cd;
	};
	/**
	 * Get accessor for persistent attribute: stm_ave_mas_cd
	 */
	public  java.lang.String getStm_ave_mas_cd(){
		return stm_ave_mas_cd;
	};
	/**
	 * Set accessor for persistent attribute: stm_ave_mas_cd
	 */
	public  void setStm_ave_mas_cd(java.lang.String newStm_ave_mas_cd){
		stm_ave_mas_cd=newStm_ave_mas_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_pro_ser_cd
	 */
	public  java.lang.Long getCod_pro_ser_cd(){
		return cod_pro_ser_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_pro_ser_cd
	 */
	public  void setCod_pro_ser_cd(java.lang.Long newCod_pro_ser_cd){
		cod_pro_ser_cd=newCod_pro_ser_cd;
	};
	/**
	 * Get accessor for persistent attribute: est_ave_mas_cd
	 */
	public  java.lang.String getEst_ave_mas_cd(){
		return est_ave_mas_cd;
	};
	/**
	 * Set accessor for persistent attribute: est_ave_mas_cd
	 */
	public  void setEst_ave_mas_cd(java.lang.String newEst_ave_mas_cd){
		est_ave_mas_cd=newEst_ave_mas_cd;
	};
	/**
	 * Get accessor for persistent attribute: cps_cje_mas_ff
	 */
	public  java.lang.String getCps_cje_mas_ff(){
		return cps_cje_mas_ff;
	};
	/**
	 * Set accessor for persistent attribute: cps_cje_mas_ff
	 */
	public  void setCps_cje_mas_ff(java.lang.String newCps_cje_mas_ff){
		cps_cje_mas_ff=newCps_cje_mas_ff;
	};
	/**
	 * Get accessor for persistent attribute: apt_ave_mas_ff
	 */
	public  java.lang.String getApt_ave_mas_ff(){
		return apt_ave_mas_ff;
	};
	/**
	 * Set accessor for persistent attribute: apt_ave_mas_ff
	 */
	public  void setApt_ave_mas_ff(java.lang.String newApt_ave_mas_ff){
		apt_ave_mas_ff=newApt_ave_mas_ff;
	};
	/**
	 * Get accessor for persistent attribute: cie_ave_mas_ff
	 */
	public  java.lang.String getCie_ave_mas_ff(){
		return cie_ave_mas_ff;
	};
	/**
	 * Set accessor for persistent attribute: cie_ave_mas_ff
	 */
	public  void setCie_ave_mas_ff(java.lang.String newCie_ave_mas_ff){
		cie_ave_mas_ff=newCie_ave_mas_ff;
	};
	/**
	 * Get accessor for persistent attribute: ext_ave_mas_ds
	 */
	public  java.lang.String getExt_ave_mas_ds(){
		return ext_ave_mas_ds;
	};
	/**
	 * Set accessor for persistent attribute: ext_ave_mas_ds
	 */
	public  void setExt_ave_mas_ds(java.lang.String newExt_ave_mas_ds){
		ext_ave_mas_ds=newExt_ave_mas_ds;
	};
	/**
	 * Get accessor for persistent attribute: lng_cpe_inf_sn
	 */
	public  java.lang.Integer getLng_cpe_inf_sn(){
		return lng_cpe_inf_sn;
	};
	/**
	 * Set accessor for persistent attribute: lng_cpe_inf_sn
	 */
	public  void setLng_cpe_inf_sn(java.lang.Integer newLng_cpe_inf_sn){
		lng_cpe_inf_sn=newLng_cpe_inf_sn;
	};
	/**
	 * Get accessor for persistent attribute: obs_ave_mas_ds
	 */
	public  java.lang.String getObs_ave_mas_ds(){
		return obs_ave_mas_ds;
	};
	/**
	 * Set accessor for persistent attribute: obs_ave_mas_ds
	 */
	public  void setObs_ave_mas_ds(java.lang.String newObs_ave_mas_ds){
		obs_ave_mas_ds=newObs_ave_mas_ds;	
	};
	/**
	 * Get accessor for persistent attribute: mot_ave_mas_cd
	 */
	public  java.lang.String getMot_ave_mas_cd(){
		return mot_ave_mas_cd;
	};
	/**
	 * Set accessor for persistent attribute: mot_ave_mas_cd
	 */
	public  void setMot_ave_mas_cd(java.lang.String newMot_ave_mas_cd){
		mot_ave_mas_cd=newMot_ave_mas_cd;
	};
	/**
	 * Get accessor for persistent attribute: tip_cal_ati_cd
	 */
	public  java.lang.String getTip_cal_ati_cd(){
		return tip_cal_ati_cd;
	};
	/**
	 * Set accessor for persistent attribute: tip_cal_ati_cd
	 */
	public  void setTip_cal_ati_cd(java.lang.String newTip_cal_ati_cd){
		tip_cal_ati_cd=newTip_cal_ati_cd;
	};
	/**
	 * Get accessor for persistent attribute: nom_cal_ds
	 */
	public  java.lang.String getNom_cal_ds(){
		return nom_cal_ds;
	};
	/**
	 * Set accessor for persistent attribute: nom_cal_ds
	 */
	public  void setNom_cal_ds(java.lang.String newNom_cal_ds){
		nom_cal_ds=newNom_cal_ds;
	};
	/**
	 * Get accessor for persistent attribute: num_cal_nu
	 */
	public  java.lang.String getNum_cal_nu(){
		return num_cal_nu;
	};
	/**
	 * Set accessor for persistent attribute: num_cal_nu
	 */
	public  void setNum_cal_nu(java.lang.String newNum_cal_nu){
		num_cal_nu=newNum_cal_nu;
	};
	/**
	 * Get accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public  java.lang.String getDsc_cmp_pri_ds(){
		return dsc_cmp_pri_ds;
	};
	/**
	 * Set accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public  void setDsc_cmp_pri_ds(java.lang.String newDsc_cmp_pri_ds){
		dsc_cmp_pri_ds=newDsc_cmp_pri_ds;
	};
	/**
	 * Get accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public  java.lang.String getDsc_cmp_seg_ds(){
		return dsc_cmp_seg_ds;
	};
	/**
	 * Set accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public  void setDsc_cmp_seg_ds(java.lang.String newDsc_cmp_seg_ds){
		dsc_cmp_seg_ds=newDsc_cmp_seg_ds;
	};
	/**
	 * Get accessor for persistent attribute: cod_ext_loc_cd
	 */
	public  java.lang.String getCod_ext_loc_cd(){
		return cod_ext_loc_cd;
	};
	/**
	 * Set accessor for persistent attribute: cod_ext_loc_cd
	 */
	public  void setCod_ext_loc_cd(java.lang.String newCod_ext_loc_cd){
		cod_ext_loc_cd=newCod_ext_loc_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_dpt
	 */
	public  java.lang.String getCod_dpt(){
		return cod_dpt;
	};
	/**
	 * Set accessor for persistent attribute: cod_dpt
	 */
	public  void setCod_dpt(java.lang.String newCod_dpt){
		cod_dpt=newCod_dpt;
	};
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public  java.lang.String getCod_loc(){
		return cod_loc;
	};
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public  void setCod_loc(java.lang.String newCod_loc){
		cod_loc=newCod_loc;
	};
	/**
	 * Get accessor for persistent attribute: cod_central
	 */
	public  java.lang.Long getCod_central(){
		return cod_central;	
	};
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public void setCod_central(java.lang.Long newCod_central){
		cod_central=newCod_central;
	};
	/**
	 * Get accessor for persistent attribute: reintento
	 */
	public java.lang.Integer getReintento(){
		return reintento;
	};
	/**
	 * Set accessor for persistent attribute: reintento
	 */
	public void setReintento(java.lang.Integer newReintento){
		reintento=newReintento;
	};
	/**
	 * Get accessor for persistent attribute: estado_peticion
	 */
	public java.lang.Integer getEstado_peticion(){
		return estado_peticion;
	};
	/**
	 * Set accessor for persistent attribute: estado_peticion
	 */
	public void setEstado_peticion(java.lang.Integer newEstado_peticion){
		estado_peticion=newEstado_peticion;
	};
	/**
	 * Get accessor for persistent attribute: fecha_sol_falla
	 */
	public java.lang.String getFecha_sol_falla(){
		return fecha_sol_falla;
	};
	/**
	 * Set accessor for persistent attribute: fecha_sol_falla
	 */
	public  void setFecha_sol_falla(java.lang.String newFecha_sol_falla){
		fecha_sol_falla=newFecha_sol_falla;
	};
	/**
	 * Get accessor for persistent attribute: frm_frm_ntf_cd
	 */
	public  java.lang.String getFrm_frm_ntf_cd(){
		return frm_frm_ntf_cd;
	};
	/**
	 * Set accessor for persistent attribute: frm_frm_ntf_cd
	 */
	public void setFrm_frm_ntf_cd(java.lang.String newFrm_frm_ntf_cd){
		frm_frm_ntf_cd=newFrm_frm_ntf_cd;
	};
	/**
	 * Get accessor for persistent attribute: cod_are_ges_cd
	 */
	public Long getCod_are_ges_cd(){
		return cod_are_ges_cd; 
	};
	/**
	 * Set accessor for persistent attribute: cod_are_ges_cd
	 */
	public  void setCod_are_ges_cd(Long newCod_are_ges_cd){
		cod_are_ges_cd=newCod_are_ges_cd;
	}
	/**
	 * @param timestamp
	 */


	/**
	 * @return
	 */
	public Timestamp getTim_fec_cps_ts() {
		return tim_fec_cps_ts;
	}

	/**
	 * @param timestamp
	 */
	public void setTim_fec_cps_ts(Timestamp timestamp) {
		tim_fec_cps_ts = timestamp;
	}

	/**
	 * @return
	 */
	public String getDescLocalidad() {
		return descLocalidad;
	}

	/**
	 * @return
	 */
	public String getGobs_ave_ds() {
		return gobs_ave_ds;
	}

	/**
	 * @return
	 */
	public Integer getIng_cpe_inf_sn() {
		return ing_cpe_inf_sn;
	}

	/**
	 * @param string
	 */
	public void setDescLocalidad(String string) {
		descLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setGobs_ave_ds(String string) {
		gobs_ave_ds = string;
	}

	/**
	 * @param integer
	 */
	public void setIng_cpe_inf_sn(Integer integer) {
		ing_cpe_inf_sn = integer;
	}

	/**
	 * @return
	 */
	public String getDescCentral() {
		return descCentral;
	}

	/**
	 * @return
	 */
	public String getDescDepartamento() {
		return descDepartamento;
	}

	/**
	 * @param string
	 */
	public void setDescCentral(String string) {
		descCentral = string;
	}

	/**
	 * @param string
	 */
	public void setDescDepartamento(String string) {
		descDepartamento = string;
	}

	/**
	 * @return
	 */
	public Long getCodPsAveria() {
		return codPsAveria;
	}

	/**
	 * @param long1
	 */
	public void setCodPsAveria(Long long1) {
		codPsAveria = long1;
	}

	/**
	 * @return
	 */
	public String getDescPsAveria() {
		return descPsAveria;
	}

	/**
	 * @param string
	 */
	public void setDescPsAveria(String string) {
		descPsAveria = string;
	}

	/**
	 * @return
	 */
	public boolean isEstaEnBandeja() {
		return estaEnBandeja;
	}

	/**
	 * @return
	 */
	public String getUrlBandeja() {
		return urlBandeja;
	}

	/**
	 * @param b
	 */
	public void setEstaEnBandeja(boolean b) {
		estaEnBandeja = b;
	}

	/**
	 * @param string
	 */
	public void setUrlBandeja(String string) {
		urlBandeja = string;
	}	
	
	/**
	 * @return Returns the fdlSoportado.
	 */
	public Short getFdlSoportado() {
		return fdlSoportado;
	}
	/**
	 * @param fdlSoportado The fdlSoportado to set.
	 */
	public void setFdlSoportado(Short fdlSoportado) {
		this.fdlSoportado = fdlSoportado;
	}

	/**
	 * @return Devuelve num_ide_nu_tv.
	 */
	public String getNum_ide_nu_tv() {
		return num_ide_nu_tv;
	}
	/**
	 * @param num_ide_nu_tv El num_ide_nu_tv a establecer.
	 */
	public void setNum_ide_nu_tv(String num_ide_nu_tv) {
		this.num_ide_nu_tv = num_ide_nu_tv;
	}
}
