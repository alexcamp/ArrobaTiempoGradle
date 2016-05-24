/*
 * Created on 17-jul-07
 */
package co.com.telefonica.atiempo.soltec.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author rodrigo
 */
public class PeticionStMasivaDTO {
	
	public PeticionStMasivaDTO() {
		super();
	}

	//Datos de la peticion
	private Long cod_ave_cd;
	private Long cod_cli_cd;
	private Long pro_ser_cto_cd;
	private String ide_pro_cmr_cd;
	private String num_ide_nu;
	private String num_ide_nu_tv;
	
	
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
	
	//Datos de la linea
	//Inf planta interna
	private Long central;
	private Long telefono;
	private String len;
	private String lecCont;
	private String posicionHorizontal;

	//Inf planta externa

	private String distr;
	private String descripcion;
	private String direcDistr;
	private String liston;
	private Long parListon;
	private Long cable;
	private Long parCable;
	private String armario;
	private String direcArmario;
	private String caja;
	private String direcCaja;
	private String parCaja;
	private Collection dslams=new ArrayList();
	private String ods;
	
	//Datos ADSL
	private Long idConector;
	private Integer codError;
	private String puertoActual;
	private String postActual;
	private String adslActual;
	private String mascLanActual;
	private String dirIpDslamActual;
	private String dirIpWanActual;
	private String frameActual;
	private String puertoNvo;
	private String postNvo;
	private String adslNvo;
	private String mascLanNva;
	private String dirIpDslamNva;
	private String dirIpWanNva;
	private String frameNvo;
	private Long correlativo;
	private Long petiNumero;
	private String vpiVciActual;
	private String vpiVciRedActual;
	private String slotActual;
	private String vpiVciNvo;
	private String dirIpLanNva;
	private String vpiVciRedNvo;
	private String slotNvo;
	private String descError;
	
	//Datos del agendamiento
	private Date fechaCompromiso;
	private String cdpeticion;
	private String horaDesde;
	private String horaHasta;
	private Long idTipoAgenda;
	private Integer idRango;
	private String nombreTipoAgenda;
	private String nombreRango;
	private String descripcionEstado;
	private String descripcionCausa;
	private String codSegmento;
	private String codDepto;
	private String codLocalidad;
	private String codCentral;
	private String tipoTrabajo;
	private Long idContratista = null;
	private String idTecnicoInicial = null;
	private String idTecnicoFinal = null;
	private String nombreContratista = null;
	private String nombreTecnicoInicial = null;
	private String nombreTecnicoFinal= null;
	private ArrayList listaPS = new ArrayList();
	
//	CR17031 pcawen
	private String descSgmto;
	private String descSubSgmto;
	
	
	
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
	/**
	 * @return Returns the descSgmto.
	 */
	public String getDescSgmto() {
		return descSgmto;
	}
	/**
	 * @param descSgmto The descSgmto to set.
	 */
	public void setDescSgmto(String descSgmto) {
		this.descSgmto = descSgmto;
	}
	/**
	 * @return Returns the descSubSgmto.
	 */
	public String getDescSubSgmto() {
		return descSubSgmto;
	}
	/**
	 * @param descSubSgmto The descSubSgmto to set.
	 */
	public void setDescSubSgmto(String descSubSgmto) {
		this.descSubSgmto = descSubSgmto;
	}
//	CR17031 - Fin
	/**
	 * @return
	 */
	public String getAdslActual() {
		return adslActual;
	}

	/**
	 * @return
	 */
	public String getAdslNvo() {
		return adslNvo;
	}

	/**
	 * @return
	 */
	public String getAju_flt_ser_in() {
		return aju_flt_ser_in;
	}

	/**
	 * @return
	 */
	public String getApt_ave_mas_ff() {
		return apt_ave_mas_ff;
	}

	/**
	 * @return
	 */
	public String getArmario() {
		return armario;
	}

	/**
	 * @return
	 */
	public Long getCable() {
		return cable;
	}

	/**
	 * @return
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @return
	 */
	public Long getCan_dia_ala_nu() {
		return can_dia_ala_nu;
	}

	/**
	 * @return
	 */
	public String getCct_doc_rte_cd() {
		return cct_doc_rte_cd;
	}

	/**
	 * @return
	 */
	public Long getCentral() {
		return central;
	}

	/**
	 * @return
	 */
	public String getCie_ave_mas_ff() {
		return cie_ave_mas_ff;
	}

	/**
	 * @return
	 */
	public String getCod_apt_ave_cd() {
		return cod_apt_ave_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_are_ges_cd() {
		return cod_are_ges_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_ave_cd() {
		return cod_ave_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_ave_mas_cd() {
		return cod_ave_mas_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_ave_ori_sn() {
		return cod_ave_ori_sn;
	}

	/**
	 * @return
	 */
	public Long getCod_central() {
		return cod_central;
	}

	/**
	 * @return
	 */
	public String getCod_cie_ave_cd() {
		return cod_cie_ave_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_cli_cd() {
		return cod_cli_cd;
	}

	/**
	 * @return
	 */
	public String getCod_ctz_cd() {
		return cod_ctz_cd;
	}

	/**
	 * @return
	 */
	public String getCod_dpt() {
		return cod_dpt;
	}

	/**
	 * @return
	 */
	public String getCod_est_ave_cd() {
		return cod_est_ave_cd;
	}

	/**
	 * @return
	 */
	public String getCod_ext_loc_cd() {
		return cod_ext_loc_cd;
	}

	/**
	 * @return
	 */
	public String getCod_loc() {
		return cod_loc;
	}

	/**
	 * @return
	 */
	public Long getCod_pet_cd() {
		return cod_pet_cd;
	}

	/**
	 * @return
	 */
	public String getCod_pra_ave_cd() {
		return cod_pra_ave_cd;
	}

	/**
	 * @return
	 */
	public Long getCod_pro_ser_cd() {
		return cod_pro_ser_cd;
	}

	/**
	 * @return
	 */
	public String getCod_rel_cli_cd() {
		return cod_rel_cli_cd;
	}

	/**
	 * @return
	 */
	public String getCod_stm_ave_cd() {
		return cod_stm_ave_cd;
	}

	/**
	 * @return
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * @return
	 */
	public Long getCodPsAveria() {
		return codPsAveria;
	}

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public String getCps_cje_mas_ff() {
		return cps_cje_mas_ff;
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
	 * @return
	 */
	public String getDescError() {
		return descError;
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
	public String getDescPsAveria() {
		return descPsAveria;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public String getDirecArmario() {
		return direcArmario;
	}

	/**
	 * @return
	 */
	public String getDirecCaja() {
		return direcCaja;
	}

	/**
	 * @return
	 */
	public String getDirecDistr() {
		return direcDistr;
	}

	/**
	 * @return
	 */
	public String getDirIpDslamActual() {
		return dirIpDslamActual;
	}

	/**
	 * @return
	 */
	public String getDirIpDslamNva() {
		return dirIpDslamNva;
	}

	/**
	 * @return
	 */
	public String getDirIpLanNva() {
		return dirIpLanNva;
	}

	/**
	 * @return
	 */
	public String getDirIpWanActual() {
		return dirIpWanActual;
	}

	/**
	 * @return
	 */
	public String getDirIpWanNva() {
		return dirIpWanNva;
	}

	/**
	 * @return
	 */
	public String getDistr() {
		return distr;
	}

	/**
	 * @return
	 */
	public String getDsc_cmp_pri_ds() {
		return dsc_cmp_pri_ds;
	}

	/**
	 * @return
	 */
	public String getDsc_cmp_seg_ds() {
		return dsc_cmp_seg_ds;
	}

	/**
	 * @return
	 */
	public String getDsc_rpt_pru_ds() {
		return dsc_rpt_pru_ds;
	}

	/**
	 * @return
	 */
	public Collection getDslams() {
		return dslams;
	}

	/**
	 * @return
	 */
	public String getEst_ave_mas_cd() {
		return est_ave_mas_cd;
	}

	/**
	 * @return
	 */
	public Integer getEstado_peticion() {
		return estado_peticion;
	}

	/**
	 * @return
	 */
	public String getExt_ave_mas_ds() {
		return ext_ave_mas_ds;
	}

	/**
	 * @return
	 */
	public Timestamp getFec_apt_ave_ts() {
		return fec_apt_ave_ts;
	}

	/**
	 * @return
	 */
	public Timestamp getFec_cie_ave_ts() {
		return fec_cie_ave_ts;
	}

	/**
	 * @return
	 */
	public String getFec_cit_ff() {
		return fec_cit_ff;
	}

	/**
	 * @return
	 */
	public String getFecha_sol_falla() {
		return fecha_sol_falla;
	}

	/**
	 * @return
	 */
	public String getFrameActual() {
		return frameActual;
	}

	/**
	 * @return
	 */
	public String getFrameNvo() {
		return frameNvo;
	}

	/**
	 * @return
	 */
	public String getFrm_frm_ntf_cd() {
		return frm_frm_ntf_cd;
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
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public String getIde_pro_cmr_cd() {
		return ide_pro_cmr_cd;
	}

	/**
	 * @return
	 */
	public String getInd_age_cit_in() {
		return ind_age_cit_in;
	}

	/**
	 * @return
	 */
	public String getInd_apz_in() {
		return ind_apz_in;
	}

	/**
	 * @return
	 */
	public String getInd_ave_rei_in() {
		return ind_ave_rei_in;
	}

	/**
	 * @return
	 */
	public String getInd_cob_vst_in() {
		return ind_cob_vst_in;
	}

	/**
	 * @return
	 */
	public String getInd_dft_emo_in() {
		return ind_dft_emo_in;
	}

	/**
	 * @return
	 */
	public String getInd_end_rpt_in() {
		return ind_end_rpt_in;
	}

	/**
	 * @return
	 */
	public String getInd_equ_atc_in() {
		return ind_equ_atc_in;
	}

	/**
	 * @return
	 */
	public String getInd_imp_mtl_in() {
		return ind_imp_mtl_in;
	}

	/**
	 * @return
	 */
	public String getInd_ser_efe_in() {
		return ind_ser_efe_in;
	}

	/**
	 * @return
	 */
	public Integer getIng_cpe_inf_sn() {
		return ing_cpe_inf_sn;
	}

	/**
	 * @return
	 */
	public Timestamp getIte_alt_sis_ts() {
		return ite_alt_sis_ts;
	}

	/**
	 * @return
	 */
	public String getLecCont() {
		return lecCont;
	}

	/**
	 * @return
	 */
	public String getLen() {
		return len;
	}

	/**
	 * @return
	 */
	public String getListon() {
		return liston;
	}

	/**
	 * @return
	 */
	public Integer getLng_cpe_inf_sn() {
		return lng_cpe_inf_sn;
	}

	/**
	 * @return
	 */
	public Double getLng_ele_inf_cd() {
		return lng_ele_inf_cd;
	}

	/**
	 * @return
	 */
	public Double getLng_ele_inf_sn() {
		return lng_ele_inf_sn;
	}

	/**
	 * @return
	 */
	public String getMascLanActual() {
		return mascLanActual;
	}

	/**
	 * @return
	 */
	public String getMascLanNva() {
		return mascLanNva;
	}

	/**
	 * @return
	 */
	public String getMot_ave_mas_cd() {
		return mot_ave_mas_cd;
	}

	/**
	 * @return
	 */
	public String getMot_ccl_icd_cd() {
		return mot_ccl_icd_cd;
	}

	/**
	 * @return
	 */
	public String getNom_cal_ds() {
		return nom_cal_ds;
	}

	/**
	 * @return
	 */
	public String getNom_psn_cot_no() {
		return nom_psn_cot_no;
	}

	/**
	 * @return
	 */
	public String getNom_rte_sn() {
		return nom_rte_sn;
	}

	/**
	 * @return
	 */
	public String getNum_cal_nu() {
		return num_cal_nu;
	}

	/**
	 * @return
	 */
	public String getNum_doc_rte_nu() {
		return num_doc_rte_nu;
	}

	/**
	 * @return
	 */
	public String getNum_ide_nu() {
		return num_ide_nu;
	}

	/**
	 * @return
	 */
	public String getNum_seg_lin_ds() {
		return num_seg_lin_ds;
	}

	/**
	 * @return
	 */
	public String getObs_ave_ds() {
		return obs_ave_ds;
	}

	/**
	 * @return
	 */
	public String getObs_ave_mas_ds() {
		return obs_ave_mas_ds;
	}

	/**
	 * @return
	 */
	public String getObs_cit_ds() {
		return obs_cit_ds;
	}

	/**
	 * @return
	 */
	public String getOds() {
		return ods;
	}

	/**
	 * @return
	 */
	public String getOri_alt_icd_in() {
		return ori_alt_icd_in;
	}

	/**
	 * @return
	 */
	public Long getParCable() {
		return parCable;
	}

	/**
	 * @return
	 */
	public String getParCaja() {
		return parCaja;
	}

	/**
	 * @return
	 */
	public Long getParListon() {
		return parListon;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public String getPostActual() {
		return postActual;
	}

	/**
	 * @return
	 */
	public String getPostNvo() {
		return postNvo;
	}

	/**
	 * @return
	 */
	public String getPri_ape_rte_sn() {
		return pri_ape_rte_sn;
	}

	/**
	 * @return
	 */
	public Long getPro_ser_cto_cd() {
		return pro_ser_cto_cd;
	}

	/**
	 * @return
	 */
	public String getPuertoActual() {
		return puertoActual;
	}

	/**
	 * @return
	 */
	public String getPuertoNvo() {
		return puertoNvo;
	}

	/**
	 * @return
	 */
	public Long getPzo_max_rsl_nu() {
		return pzo_max_rsl_nu;
	}

	/**
	 * @return
	 */
	public Integer getReintento() {
		return reintento;
	}

	/**
	 * @return
	 */
	public String getRel_cli_otr_ds() {
		return rel_cli_otr_ds;
	}

	/**
	 * @return
	 */
	public String getRpt_pru_ave_cd() {
		return rpt_pru_ave_cd;
	}

	/**
	 * @return
	 */
	public String getSeg_ape_rte_sn() {
		return seg_ape_rte_sn;
	}

	/**
	 * @return
	 */
	public String getSeg_psn_cot_sn() {
		return seg_psn_cot_sn;
	}

	/**
	 * @return
	 */
	public String getSeg_tel_cot_sn() {
		return seg_tel_cot_sn;
	}

	/**
	 * @return
	 */
	public String getSlotActual() {
		return slotActual;
	}

	/**
	 * @return
	 */
	public String getSlotNvo() {
		return slotNvo;
	}

	/**
	 * @return
	 */
	public String getStm_ave_mas_cd() {
		return stm_ave_mas_cd;
	}

	/**
	 * @return
	 */
	public String getTel_cot_ds() {
		return tel_cot_ds;
	}

	/**
	 * @return
	 */
	public Long getTelefono() {
		return telefono;
	}

	/**
	 * @return
	 */
	public Timestamp getTim_fec_cps_ts() {
		return tim_fec_cps_ts;
	}

	/**
	 * @return
	 */
	public Timestamp getTim_ult_mod_ts() {
		return tim_ult_mod_ts;
	}

	/**
	 * @return
	 */
	public String getTip_ave_mas_cd() {
		return tip_ave_mas_cd;
	}

	/**
	 * @return
	 */
	public String getTip_cal_ati_cd() {
		return tip_cal_ati_cd;
	}

	/**
	 * @return
	 */
	public String getTip_doc_rte_cd() {
		return tip_doc_rte_cd;
	}

	/**
	 * @return
	 */
	public String getTip_mdi_cmc_cd() {
		return tip_mdi_cmc_cd;
	}

	/**
	 * @return
	 */
	public String getUsr_alt_no() {
		return usr_alt_no;
	}

	/**
	 * @return
	 */
	public String getUsr_ult_mod_no() {
		return usr_ult_mod_no;
	}

	/**
	 * @return
	 */
	public String getVpiVciActual() {
		return vpiVciActual;
	}

	/**
	 * @return
	 */
	public String getVpiVciNvo() {
		return vpiVciNvo;
	}

	/**
	 * @return
	 */
	public String getVpiVciRedActual() {
		return vpiVciRedActual;
	}

	/**
	 * @return
	 */
	public String getVpiVciRedNvo() {
		return vpiVciRedNvo;
	}

	/**
	 * @param string
	 */
	public void setAdslActual(String string) {
		adslActual = string;
	}

	/**
	 * @param string
	 */
	public void setAdslNvo(String string) {
		adslNvo = string;
	}

	/**
	 * @param string
	 */
	public void setAju_flt_ser_in(String string) {
		aju_flt_ser_in = string;
	}

	/**
	 * @param string
	 */
	public void setApt_ave_mas_ff(String string) {
		apt_ave_mas_ff = string;
	}

	/**
	 * @param string
	 */
	public void setArmario(String string) {
		armario = string;
	}

	/**
	 * @param long1
	 */
	public void setCable(Long long1) {
		cable = long1;
	}

	/**
	 * @param string
	 */
	public void setCaja(String string) {
		caja = string;
	}

	/**
	 * @param long1
	 */
	public void setCan_dia_ala_nu(Long long1) {
		can_dia_ala_nu = long1;
	}

	/**
	 * @param string
	 */
	public void setCct_doc_rte_cd(String string) {
		cct_doc_rte_cd = string;
	}

	/**
	 * @param long1
	 */
	public void setCentral(Long long1) {
		central = long1;
	}

	/**
	 * @param string
	 */
	public void setCie_ave_mas_ff(String string) {
		cie_ave_mas_ff = string;
	}

	/**
	 * @param string
	 */
	public void setCod_apt_ave_cd(String string) {
		cod_apt_ave_cd = string;
	}

	/**
	 * @param long1
	 */
	public void setCod_are_ges_cd(Long long1) {
		cod_are_ges_cd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCod_ave_cd(Long long1) {
		cod_ave_cd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCod_ave_mas_cd(Long long1) {
		cod_ave_mas_cd = long1;
	}

	/**
	 * @param long1
	 */
	public void setCod_ave_ori_sn(Long long1) {
		cod_ave_ori_sn = long1;
	}

	/**
	 * @param long1
	 */
	public void setCod_central(Long long1) {
		cod_central = long1;
	}

	/**
	 * @param string
	 */
	public void setCod_cie_ave_cd(String string) {
		cod_cie_ave_cd = string;
	}

	/**
	 * @param long1
	 */
	public void setCod_cli_cd(Long long1) {
		cod_cli_cd = long1;
	}

	/**
	 * @param string
	 */
	public void setCod_ctz_cd(String string) {
		cod_ctz_cd = string;
	}

	/**
	 * @param string
	 */
	public void setCod_dpt(String string) {
		cod_dpt = string;
	}

	/**
	 * @param string
	 */
	public void setCod_est_ave_cd(String string) {
		cod_est_ave_cd = string;
	}

	/**
	 * @param string
	 */
	public void setCod_ext_loc_cd(String string) {
		cod_ext_loc_cd = string;
	}

	/**
	 * @param string
	 */
	public void setCod_loc(String string) {
		cod_loc = string;
	}

	/**
	 * @param long1
	 */
	public void setCod_pet_cd(Long long1) {
		cod_pet_cd = long1;
	}

	/**
	 * @param string
	 */
	public void setCod_pra_ave_cd(String string) {
		cod_pra_ave_cd = string;
	}

	/**
	 * @param long1
	 */
	public void setCod_pro_ser_cd(Long long1) {
		cod_pro_ser_cd = long1;
	}

	/**
	 * @param string
	 */
	public void setCod_rel_cli_cd(String string) {
		cod_rel_cli_cd = string;
	}

	/**
	 * @param string
	 */
	public void setCod_stm_ave_cd(String string) {
		cod_stm_ave_cd = string;
	}

	/**
	 * @param integer
	 */
	public void setCodError(Integer integer) {
		codError = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodPsAveria(Long long1) {
		codPsAveria = long1;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param string
	 */
	public void setCps_cje_mas_ff(String string) {
		cps_cje_mas_ff = string;
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
	 * @param string
	 */
	public void setDescError(String string) {
		descError = string;
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
	public void setDescPsAveria(String string) {
		descPsAveria = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param string
	 */
	public void setDirecArmario(String string) {
		direcArmario = string;
	}

	/**
	 * @param string
	 */
	public void setDirecCaja(String string) {
		direcCaja = string;
	}

	/**
	 * @param string
	 */
	public void setDirecDistr(String string) {
		direcDistr = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpDslamActual(String string) {
		dirIpDslamActual = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpDslamNva(String string) {
		dirIpDslamNva = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpLanNva(String string) {
		dirIpLanNva = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpWanActual(String string) {
		dirIpWanActual = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpWanNva(String string) {
		dirIpWanNva = string;
	}

	/**
	 * @param string
	 */
	public void setDistr(String string) {
		distr = string;
	}

	/**
	 * @param string
	 */
	public void setDsc_cmp_pri_ds(String string) {
		dsc_cmp_pri_ds = string;
	}

	/**
	 * @param string
	 */
	public void setDsc_cmp_seg_ds(String string) {
		dsc_cmp_seg_ds = string;
	}

	/**
	 * @param string
	 */
	public void setDsc_rpt_pru_ds(String string) {
		dsc_rpt_pru_ds = string;
	}

	/**
	 * @param collection
	 */
	public void setDslams(Collection collection) {
		dslams = collection;
	}

	/**
	 * @param string
	 */
	public void setEst_ave_mas_cd(String string) {
		est_ave_mas_cd = string;
	}

	/**
	 * @param integer
	 */
	public void setEstado_peticion(Integer integer) {
		estado_peticion = integer;
	}

	/**
	 * @param string
	 */
	public void setExt_ave_mas_ds(String string) {
		ext_ave_mas_ds = string;
	}

	/**
	 * @param timestamp
	 */
	public void setFec_apt_ave_ts(Timestamp timestamp) {
		fec_apt_ave_ts = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setFec_cie_ave_ts(Timestamp timestamp) {
		fec_cie_ave_ts = timestamp;
	}

	/**
	 * @param string
	 */
	public void setFec_cit_ff(String string) {
		fec_cit_ff = string;
	}

	/**
	 * @param string
	 */
	public void setFecha_sol_falla(String string) {
		fecha_sol_falla = string;
	}

	/**
	 * @param string
	 */
	public void setFrameActual(String string) {
		frameActual = string;
	}

	/**
	 * @param string
	 */
	public void setFrameNvo(String string) {
		frameNvo = string;
	}

	/**
	 * @param string
	 */
	public void setFrm_frm_ntf_cd(String string) {
		frm_frm_ntf_cd = string;
	}

	/**
	 * @param string
	 */
	public void setGobs_ave_ds(String string) {
		gobs_ave_ds = string;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param string
	 */
	public void setIde_pro_cmr_cd(String string) {
		ide_pro_cmr_cd = string;
	}

	/**
	 * @param string
	 */
	public void setInd_age_cit_in(String string) {
		ind_age_cit_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_apz_in(String string) {
		ind_apz_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_ave_rei_in(String string) {
		ind_ave_rei_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_cob_vst_in(String string) {
		ind_cob_vst_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_dft_emo_in(String string) {
		ind_dft_emo_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_end_rpt_in(String string) {
		ind_end_rpt_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_equ_atc_in(String string) {
		ind_equ_atc_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_imp_mtl_in(String string) {
		ind_imp_mtl_in = string;
	}

	/**
	 * @param string
	 */
	public void setInd_ser_efe_in(String string) {
		ind_ser_efe_in = string;
	}

	/**
	 * @param integer
	 */
	public void setIng_cpe_inf_sn(Integer integer) {
		ing_cpe_inf_sn = integer;
	}

	/**
	 * @param timestamp
	 */
	public void setIte_alt_sis_ts(Timestamp timestamp) {
		ite_alt_sis_ts = timestamp;
	}

	/**
	 * @param string
	 */
	public void setLecCont(String string) {
		lecCont = string;
	}

	/**
	 * @param string
	 */
	public void setLen(String string) {
		len = string;
	}

	/**
	 * @param string
	 */
	public void setListon(String string) {
		liston = string;
	}

	/**
	 * @param integer
	 */
	public void setLng_cpe_inf_sn(Integer integer) {
		lng_cpe_inf_sn = integer;
	}

	/**
	 * @param double1
	 */
	public void setLng_ele_inf_cd(Double double1) {
		lng_ele_inf_cd = double1;
	}

	/**
	 * @param double1
	 */
	public void setLng_ele_inf_sn(Double double1) {
		lng_ele_inf_sn = double1;
	}

	/**
	 * @param string
	 */
	public void setMascLanActual(String string) {
		mascLanActual = string;
	}

	/**
	 * @param string
	 */
	public void setMascLanNva(String string) {
		mascLanNva = string;
	}

	/**
	 * @param string
	 */
	public void setMot_ave_mas_cd(String string) {
		mot_ave_mas_cd = string;
	}

	/**
	 * @param string
	 */
	public void setMot_ccl_icd_cd(String string) {
		mot_ccl_icd_cd = string;
	}

	/**
	 * @param string
	 */
	public void setNom_cal_ds(String string) {
		nom_cal_ds = string;
	}

	/**
	 * @param string
	 */
	public void setNom_psn_cot_no(String string) {
		nom_psn_cot_no = string;
	}

	/**
	 * @param string
	 */
	public void setNom_rte_sn(String string) {
		nom_rte_sn = string;
	}

	/**
	 * @param string
	 */
	public void setNum_cal_nu(String string) {
		num_cal_nu = string;
	}

	/**
	 * @param string
	 */
	public void setNum_doc_rte_nu(String string) {
		num_doc_rte_nu = string;
	}

	/**
	 * @param string
	 */
	public void setNum_ide_nu(String string) {
		num_ide_nu = string;
	}

	/**
	 * @param string
	 */
	public void setNum_seg_lin_ds(String string) {
		num_seg_lin_ds = string;
	}

	/**
	 * @param string
	 */
	public void setObs_ave_ds(String string) {
		obs_ave_ds = string;
	}

	/**
	 * @param string
	 */
	public void setObs_ave_mas_ds(String string) {
		obs_ave_mas_ds = string;
	}

	/**
	 * @param string
	 */
	public void setObs_cit_ds(String string) {
		obs_cit_ds = string;
	}

	/**
	 * @param string
	 */
	public void setOds(String string) {
		ods = string;
	}

	/**
	 * @param string
	 */
	public void setOri_alt_icd_in(String string) {
		ori_alt_icd_in = string;
	}

	/**
	 * @param long1
	 */
	public void setParCable(Long long1) {
		parCable = long1;
	}

	/**
	 * @param string
	 */
	public void setParCaja(String string) {
		parCaja = string;
	}

	/**
	 * @param long1
	 */
	public void setParListon(Long long1) {
		parListon = long1;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param string
	 */
	public void setPostActual(String string) {
		postActual = string;
	}

	/**
	 * @param string
	 */
	public void setPostNvo(String string) {
		postNvo = string;
	}

	/**
	 * @param string
	 */
	public void setPri_ape_rte_sn(String string) {
		pri_ape_rte_sn = string;
	}

	/**
	 * @param long1
	 */
	public void setPro_ser_cto_cd(Long long1) {
		pro_ser_cto_cd = long1;
	}

	/**
	 * @param string
	 */
	public void setPuertoActual(String string) {
		puertoActual = string;
	}

	/**
	 * @param string
	 */
	public void setPuertoNvo(String string) {
		puertoNvo = string;
	}

	/**
	 * @param long1
	 */
	public void setPzo_max_rsl_nu(Long long1) {
		pzo_max_rsl_nu = long1;
	}

	/**
	 * @param integer
	 */
	public void setReintento(Integer integer) {
		reintento = integer;
	}

	/**
	 * @param string
	 */
	public void setRel_cli_otr_ds(String string) {
		rel_cli_otr_ds = string;
	}

	/**
	 * @param string
	 */
	public void setRpt_pru_ave_cd(String string) {
		rpt_pru_ave_cd = string;
	}

	/**
	 * @param string
	 */
	public void setSeg_ape_rte_sn(String string) {
		seg_ape_rte_sn = string;
	}

	/**
	 * @param string
	 */
	public void setSeg_psn_cot_sn(String string) {
		seg_psn_cot_sn = string;
	}

	/**
	 * @param string
	 */
	public void setSeg_tel_cot_sn(String string) {
		seg_tel_cot_sn = string;
	}

	/**
	 * @param string
	 */
	public void setSlotActual(String string) {
		slotActual = string;
	}

	/**
	 * @param string
	 */
	public void setSlotNvo(String string) {
		slotNvo = string;
	}

	/**
	 * @param string
	 */
	public void setStm_ave_mas_cd(String string) {
		stm_ave_mas_cd = string;
	}

	/**
	 * @param string
	 */
	public void setTel_cot_ds(String string) {
		tel_cot_ds = string;
	}

	/**
	 * @param long1
	 */
	public void setTelefono(Long long1) {
		telefono = long1;
	}

	/**
	 * @param timestamp
	 */
	public void setTim_fec_cps_ts(Timestamp timestamp) {
		tim_fec_cps_ts = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTim_ult_mod_ts(Timestamp timestamp) {
		tim_ult_mod_ts = timestamp;
	}

	/**
	 * @param string
	 */
	public void setTip_ave_mas_cd(String string) {
		tip_ave_mas_cd = string;
	}

	/**
	 * @param string
	 */
	public void setTip_cal_ati_cd(String string) {
		tip_cal_ati_cd = string;
	}

	/**
	 * @param string
	 */
	public void setTip_doc_rte_cd(String string) {
		tip_doc_rte_cd = string;
	}

	/**
	 * @param string
	 */
	public void setTip_mdi_cmc_cd(String string) {
		tip_mdi_cmc_cd = string;
	}

	/**
	 * @param string
	 */
	public void setUsr_alt_no(String string) {
		usr_alt_no = string;
	}

	/**
	 * @param string
	 */
	public void setUsr_ult_mod_no(String string) {
		usr_ult_mod_no = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciActual(String string) {
		vpiVciActual = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciNvo(String string) {
		vpiVciNvo = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciRedActual(String string) {
		vpiVciRedActual = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciRedNvo(String string) {
		vpiVciRedNvo = string;
	}

	/**
	 * @return
	 */
	public String getCdpeticion() {
		return cdpeticion;
	}

	/**
	 * @return
	 */
	public String getCodCentral() {
		return codCentral;
	}

	/**
	 * @return
	 */
	public String getCodDepto() {
		return codDepto;
	}

	/**
	 * @return
	 */
	public String getCodLocalidad() {
		return codLocalidad;
	}

	/**
	 * @return
	 */
	public String getCodSegmento() {
		return codSegmento;
	}

	/**
	 * @return
	 */
	public String getDescripcionCausa() {
		return descripcionCausa;
	}

	/**
	 * @return
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}


	/**
	 * @return
	 */
	public String getHoraDesde() {
		return horaDesde;
	}

	/**
	 * @return
	 */
	public String getHoraHasta() {
		return horaHasta;
	}

	/**
	 * @return
	 */
	public Long getIdContratista() {
		return idContratista;
	}

	/**
	 * @return
	 */
	public Integer getIdRango() {
		return idRango;
	}

	/**
	 * @return
	 */
	public String getIdTecnicoFinal() {
		return idTecnicoFinal;
	}

	/**
	 * @return
	 */
	public String getIdTecnicoInicial() {
		return idTecnicoInicial;
	}

	/**
	 * @return
	 */
	public Long getIdTipoAgenda() {
		return idTipoAgenda;
	}

	/**
	 * @return
	 */
	public ArrayList getListaPS() {
		return listaPS;
	}

	/**
	 * @return
	 */
	public String getNombreContratista() {
		return nombreContratista;
	}

	/**
	 * @return
	 */
	public String getNombreRango() {
		return nombreRango;
	}

	/**
	 * @return
	 */
	public String getNombreTecnicoFinal() {
		return nombreTecnicoFinal;
	}

	/**
	 * @return
	 */
	public String getNombreTecnicoInicial() {
		return nombreTecnicoInicial;
	}

	/**
	 * @return
	 */
	public String getNombreTipoAgenda() {
		return nombreTipoAgenda;
	}

	/**
	 * @return
	 */
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	/**
	 * @param string
	 */
	public void setCdpeticion(String string) {
		cdpeticion = string;
	}

	/**
	 * @param string
	 */
	public void setCodCentral(String string) {
		codCentral = string;
	}

	/**
	 * @param string
	 */
	public void setCodDepto(String string) {
		codDepto = string;
	}

	/**
	 * @param string
	 */
	public void setCodLocalidad(String string) {
		codLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setCodSegmento(String string) {
		codSegmento = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCausa(String string) {
		descripcionCausa = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionEstado(String string) {
		descripcionEstado = string;
	}


	/**
	 * @param string
	 */
	public void setHoraDesde(String string) {
		horaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setHoraHasta(String string) {
		horaHasta = string;
	}

	/**
	 * @param long1
	 */
	public void setIdContratista(Long long1) {
		idContratista = long1;
	}

	/**
	 * @param integer
	 */
	public void setIdRango(Integer integer) {
		idRango = integer;
	}

	/**
	 * @param string
	 */
	public void setIdTecnicoFinal(String string) {
		idTecnicoFinal = string;
	}

	/**
	 * @param string
	 */
	public void setIdTecnicoInicial(String string) {
		idTecnicoInicial = string;
	}

	/**
	 * @param long1
	 */
	public void setIdTipoAgenda(Long long1) {
		idTipoAgenda = long1;
	}

	/**
	 * @param list
	 */
	public void setListaPS(ArrayList list) {
		listaPS = list;
	}

	/**
	 * @param string
	 */
	public void setNombreContratista(String string) {
		nombreContratista = string;
	}

	/**
	 * @param string
	 */
	public void setNombreRango(String string) {
		nombreRango = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTecnicoFinal(String string) {
		nombreTecnicoFinal = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTecnicoInicial(String string) {
		nombreTecnicoInicial = string;
	}

	/**
	 * @param string
	 */
	public void setNombreTipoAgenda(String string) {
		nombreTipoAgenda = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTrabajo(String string) {
		tipoTrabajo = string;
	}

	/**
	 * @return
	 */
	public Date getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @param date
	 */
	public void setFechaCompromiso(Date date) {
		fechaCompromiso = date;
	}

	/**
	 * @return
	 */
	public String getPosicionHorizontal() {
		return posicionHorizontal;
	}

	/**
	 * @param string
	 */
	public void setPosicionHorizontal(String string) {
		posicionHorizontal = string;
	}

}
