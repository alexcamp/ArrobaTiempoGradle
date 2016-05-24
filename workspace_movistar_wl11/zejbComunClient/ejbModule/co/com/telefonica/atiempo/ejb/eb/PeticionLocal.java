package co.com.telefonica.atiempo.ejb.eb;
import java.util.ArrayList;
/**
 * Local interface for Enterprise Bean: Peticion
 */
public interface PeticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * Get accessor for persistent attribute: espe_id
	 */
	public java.lang.Integer getEspe_id();
	/**
	 * Set accessor for persistent attribute: espe_id
	 */
	public void setEspe_id(java.lang.Integer newEspe_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public void setTica_id(java.lang.String newTica_id);
	/**
	 * Get accessor for persistent attribute: agen_id
	 */
	public java.lang.Long getAgen_id();
	/**
	 * Set accessor for persistent attribute: agen_id
	 */
	public void setAgen_id(java.lang.Long newAgen_id);
	/**
	 * Get accessor for persistent attribute: line_tras_id
	 */
	public java.lang.Long getLine_tras_id();
	/**
	 * Set accessor for persistent attribute: line_tras_id
	 */
	public void setLine_tras_id(java.lang.Long newLine_tras_id);
	/**
	 * Get accessor for persistent attribute: cod_cli_cd
	 */
	public java.lang.Long getCod_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_cli_cd
	 */
	public void setCod_cli_cd(java.lang.Long newCod_cli_cd);
	/**
	 * Get accessor for persistent attribute: peti_fecha_ingreso
	 */
	public java.sql.Timestamp getPeti_fecha_ingreso();
	/**
	 * Set accessor for persistent attribute: peti_fecha_ingreso
	 */
	public void setPeti_fecha_ingreso(java.sql.Timestamp newPeti_fecha_ingreso);
	/**
	 * Get accessor for persistent attribute: peti_fecha_compromiso
	 */
	public java.sql.Timestamp getPeti_fecha_compromiso();
	/**
	 * Set accessor for persistent attribute: peti_fecha_compromiso
	 */
	public void setPeti_fecha_compromiso(
		java.sql.Timestamp newPeti_fecha_compromiso);
	/**
	 * Get accessor for persistent attribute: peti_observacion
	 */
	public java.lang.String getPeti_observacion();
	/**
	 * Set accessor for persistent attribute: peti_observacion
	 */
	public void setPeti_observacion(java.lang.String newPeti_observacion);
	/**
	 * Get accessor for persistent attribute: peti_usuario_emisor
	 */
	public java.lang.String getPeti_usuario_emisor();
	/**
	 * Set accessor for persistent attribute: peti_usuario_emisor
	 */
	public void setPeti_usuario_emisor(java.lang.String newPeti_usuario_emisor);
	/**
	 * Get accessor for persistent attribute: peti_fecha_termino
	 */
	public java.sql.Timestamp getPeti_fecha_termino();
	/**
	 * Set accessor for persistent attribute: peti_fecha_termino
	 */
	public void setPeti_fecha_termino(java.sql.Timestamp newPeti_fecha_termino);
	/**
	 * Get accessor for persistent attribute: peti_id_instancia
	 */
	public java.lang.String getPeti_id_instancia();
	/**
	 * Set accessor for persistent attribute: peti_id_instancia
	 */
	public void setPeti_id_instancia(java.lang.String newPeti_id_instancia);
	/**
	 * Get accessor for persistent attribute: peti_rut_vendedor
	 */
	public java.lang.String getPeti_rut_vendedor();
	/**
	 * Set accessor for persistent attribute: peti_rut_vendedor
	 */
	public void setPeti_rut_vendedor(java.lang.String newPeti_rut_vendedor);
	/**
	 * Get accessor for persistent attribute: peti_causal_baja
	 */
	public java.lang.String getPeti_causal_baja();
	/**
	 * Set accessor for persistent attribute: peti_causal_baja
	 */
	public void setPeti_causal_baja(java.lang.String newPeti_causal_baja);
	/**
	 * Get accessor for persistent attribute: peti_tipo_hora
	 */
	public java.lang.String getPeti_tipo_hora();
	/**
	 * Set accessor for persistent attribute: peti_tipo_hora
	 */
	public void setPeti_tipo_hora(java.lang.String newPeti_tipo_hora);
	/**
	 * Get accessor for persistent attribute: peti_hora_inicio
	 */
	public java.sql.Timestamp getPeti_hora_inicio();
	/**
	 * Set accessor for persistent attribute: peti_hora_inicio
	 */
	public void setPeti_hora_inicio(java.sql.Timestamp newPeti_hora_inicio);
	/**
	 * Get accessor for persistent attribute: peti_hora_fin
	 */
	public java.sql.Timestamp getPeti_hora_fin();
	/**
	 * Set accessor for persistent attribute: peti_hora_fin
	 */
	public void setPeti_hora_fin(java.sql.Timestamp newPeti_hora_fin);
	/**
	 * Get accessor for persistent attribute: peti_numero_nueva
	 */
	public java.lang.Long getPeti_numero_nueva();
	/**
	 * Set accessor for persistent attribute: peti_numero_nueva
	 */
	public void setPeti_numero_nueva(java.lang.Long newPeti_numero_nueva);
	/**
	 * Get accessor for persistent attribute: peti_ooss
	 */
	public java.lang.Long getPeti_ooss();
	/**
	 * Set accessor for persistent attribute: peti_ooss
	 */
	public void setPeti_ooss(java.lang.Long newPeti_ooss);
	/**
	 * Get accessor for persistent attribute: peti_registro_alta
	 */
	public java.lang.Integer getPeti_registro_alta();
	/**
	 * Set accessor for persistent attribute: peti_registro_alta
	 */
	public void setPeti_registro_alta(java.lang.Integer newPeti_registro_alta);
	/**
	 * Get accessor for persistent attribute: peti_aviso_alta
	 */
	public java.lang.Integer getPeti_aviso_alta();
	/**
	 * Set accessor for persistent attribute: peti_aviso_alta
	 */
	public void setPeti_aviso_alta(java.lang.Integer newPeti_aviso_alta);
	/**
	 * Get accessor for persistent attribute: peti_fecha_modificacion
	 */
	public java.sql.Timestamp getPeti_fecha_modificacion();
	/**
	 * Set accessor for persistent attribute: peti_fecha_modificacion
	 */
	public void setPeti_fecha_modificacion(
		java.sql.Timestamp newPeti_fecha_modificacion);
	/**
	 * Get accessor for persistent attribute: peti_tipo
	 */
	public java.lang.String getPeti_tipo();
	/**
	 * Set accessor for persistent attribute: peti_tipo
	 */
	public void setPeti_tipo(java.lang.String newPeti_tipo);
	/**
	 * Get accessor for persistent attribute: cod_emp_cd
	 */
	public java.lang.Long getCod_emp_cd();
	/**
	 * Set accessor for persistent attribute: cod_emp_cd
	 */
	public void setCod_emp_cd(java.lang.Long newCod_emp_cd);
	/**
	 * Get accessor for persistent attribute: cod_sgm_cli_cd
	 */
	public java.lang.Long getCod_sgm_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_sgm_cli_cd
	 */
	public void setCod_sgm_cli_cd(java.lang.Long newCod_sgm_cli_cd);
	/**
	 * Get accessor for persistent attribute: cod_sbg_cli_cd
	 */
	public java.lang.Long getCod_sbg_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_sbg_cli_cd
	 */
	public void setCod_sbg_cli_cd(java.lang.Long newCod_sbg_cli_cd);
	/**
	 * Get accessor for persistent attribute: nom_ds
	 */
	public java.lang.String getNom_ds();
	/**
	 * Set accessor for persistent attribute: nom_ds
	 */
	public void setNom_ds(java.lang.String newNom_ds);
	/**
	 * Get accessor for persistent attribute: pri_ape_ds
	 */
	public java.lang.String getPri_ape_ds();
	/**
	 * Set accessor for persistent attribute: pri_ape_ds
	 */
	public void setPri_ape_ds(java.lang.String newPri_ape_ds);
	/**
	 * Get accessor for persistent attribute: seg_ape_ds
	 */
	public java.lang.String getSeg_ape_ds();
	/**
	 * Set accessor for persistent attribute: seg_ape_ds
	 */
	public void setSeg_ape_ds(java.lang.String newSeg_ape_ds);
	/**
	 * Get accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public java.lang.Long getCod_cnl_ven_cd();
	/**
	 * Set accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public void setCod_cnl_ven_cd(java.lang.Long newCod_cnl_ven_cd);
	/**
	 * Get accessor for persistent attribute: cod_fza_ven_cd
	 */
	public java.lang.Long getCod_fza_ven_cd();
	/**
	 * Set accessor for persistent attribute: cod_fza_ven_cd
	 */
	public void setCod_fza_ven_cd(java.lang.Long newCod_fza_ven_cd);
	/**
	 * Get accessor for persistent attribute: nom_int_ds
	 */
	public java.lang.String getNom_int_ds();
	/**
	 * Set accessor for persistent attribute: nom_int_ds
	 */
	public void setNom_int_ds(java.lang.String newNom_int_ds);
	/**
	 * Get accessor for persistent attribute: pri_ape_int_ds
	 */
	public java.lang.String getPri_ape_int_ds();
	/**
	 * Set accessor for persistent attribute: pri_ape_int_ds
	 */
	public void setPri_ape_int_ds(java.lang.String newPri_ape_int_ds);
	/**
	 * Get accessor for persistent attribute: seg_ape_int_ds
	 */
	public java.lang.String getSeg_ape_int_ds();
	/**
	 * Set accessor for persistent attribute: seg_ape_int_ds
	 */
	public void setSeg_ape_int_ds(java.lang.String newSeg_ape_int_ds);
	/**
	 * Get accessor for persistent attribute: cod_pet_pdr_cd
	 */
	public java.lang.Long getCod_pet_pdr_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_pdr_cd
	 */
	public void setCod_pet_pdr_cd(java.lang.Long newCod_pet_pdr_cd);
	/**
	 * Get accessor for persistent attribute: cod_cta_cd
	 */
	public java.lang.Long getCod_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_cta_cd
	 */
	public void setCod_cta_cd(java.lang.Long newCod_cta_cd);
	/**
	 * Get accessor for persistent attribute: cmb_est_pet_ff
	 */
	public java.sql.Timestamp getCmb_est_pet_ff();
	/**
	 * Set accessor for persistent attribute: cmb_est_pet_ff
	 */
	public void setCmb_est_pet_ff(java.sql.Timestamp newCmb_est_pet_ff);
	/**
	 * Get accessor for persistent attribute: tip_cli_cd
	 */
	public java.lang.String getTip_cli_cd();
	/**
	 * Set accessor for persistent attribute: tip_cli_cd
	 */
	public void setTip_cli_cd(java.lang.String newTip_cli_cd);
	/**
	 * Get accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public java.lang.Long getCod_sgm_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sgm_cta_cd
	 */
	public void setCod_sgm_cta_cd(java.lang.Long newCod_sgm_cta_cd);
	/**
	 * Get accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public java.lang.Long getCod_sbg_cta_cd();
	/**
	 * Set accessor for persistent attribute: cod_sbg_cta_cd
	 */
	public void setCod_sbg_cta_cd(java.lang.Long newCod_sbg_cta_cd);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds
	 */
	public java.lang.String getTel_cot_ds();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds
	 */
	public void setTel_cot_ds(java.lang.String newTel_cot_ds);
	/**
	 * Get accessor for persistent attribute: nom_ste_pet_sn
	 */
	public java.lang.String getNom_ste_pet_sn();
	/**
	 * Set accessor for persistent attribute: nom_ste_pet_sn
	 */
	public void setNom_ste_pet_sn(java.lang.String newNom_ste_pet_sn);
	/**
	 * Get accessor for persistent attribute: pri_ape_pet_sn
	 */
	public java.lang.String getPri_ape_pet_sn();
	/**
	 * Set accessor for persistent attribute: pri_ape_pet_sn
	 */
	public void setPri_ape_pet_sn(java.lang.String newPri_ape_pet_sn);
	/**
	 * Get accessor for persistent attribute: seg_ape_pet_sn
	 */
	public java.lang.String getSeg_ape_pet_sn();
	/**
	 * Set accessor for persistent attribute: seg_ape_pet_sn
	 */
	public void setSeg_ape_pet_sn(java.lang.String newSeg_ape_pet_sn);
	/**
	 * Get accessor for persistent attribute: obs_pet_ds
	 */
	public java.lang.String getObs_pet_ds();
	/**
	 * Set accessor for persistent attribute: obs_pet_ds
	 */
	public void setObs_pet_ds(java.lang.String newObs_pet_ds);
	/**
	 * Get accessor for persistent attribute: fec_sct_pet_ff
	 */
	public java.sql.Timestamp getFec_sct_pet_ff();
	/**
	 * Set accessor for persistent attribute: fec_sct_pet_ff
	 */
	public void setFec_sct_pet_ff(java.sql.Timestamp newFec_sct_pet_ff);
	/**
	 * Get accessor for persistent attribute: tip_doc_cd
	 */
	public java.lang.String getTip_doc_cd();
	/**
	 * Set accessor for persistent attribute: tip_doc_cd
	 */
	public void setTip_doc_cd(java.lang.String newTip_doc_cd);
	/**
	 * Get accessor for persistent attribute: num_doc_cli_cd
	 */
	public java.lang.String getNum_doc_cli_cd();
	/**
	 * Set accessor for persistent attribute: num_doc_cli_cd
	 */
	public void setNum_doc_cli_cd(java.lang.String newNum_doc_cli_cd);
	/**
	 * Get accessor for persistent attribute: dig_ctl_doc_cd
	 */
	public java.lang.String getDig_ctl_doc_cd();
	/**
	 * Set accessor for persistent attribute: dig_ctl_doc_cd
	 */
	public void setDig_ctl_doc_cd(java.lang.String newDig_ctl_doc_cd);
	/**
	 * Get accessor for persistent attribute: dir_tip_via_1
	 */
	public java.lang.String getDir_tip_via_1();
	/**
	 * Set accessor for persistent attribute: dir_tip_via_1
	 */
	public void setDir_tip_via_1(java.lang.String newDir_tip_via_1);
	/**
	 * Get accessor for persistent attribute: dir_nro_via_1
	 */
	public java.lang.String getDir_nro_via_1();
	/**
	 * Set accessor for persistent attribute: dir_nro_via_1
	 */
	public void setDir_nro_via_1(java.lang.String newDir_nro_via_1);
	/**
	 * Get accessor for persistent attribute: dir_lt1_via_1
	 */
	public java.lang.String getDir_lt1_via_1();
	/**
	 * Set accessor for persistent attribute: dir_lt1_via_1
	 */
	public void setDir_lt1_via_1(java.lang.String newDir_lt1_via_1);
	/**
	 * Get accessor for persistent attribute: dir_lt2_via_1
	 */
	public java.lang.String getDir_lt2_via_1();
	/**
	 * Set accessor for persistent attribute: dir_lt2_via_1
	 */
	public void setDir_lt2_via_1(java.lang.String newDir_lt2_via_1);
	/**
	 * Get accessor for persistent attribute: dir_zon_via_1
	 */
	public java.lang.String getDir_zon_via_1();
	/**
	 * Set accessor for persistent attribute: dir_zon_via_1
	 */
	public void setDir_zon_via_1(java.lang.String newDir_zon_via_1);
	/**
	 * Get accessor for persistent attribute: dir_tip_via_2
	 */
	public java.lang.String getDir_tip_via_2();
	/**
	 * Set accessor for persistent attribute: dir_tip_via_2
	 */
	public void setDir_tip_via_2(java.lang.String newDir_tip_via_2);
	/**
	 * Get accessor for persistent attribute: dir_nro_via_2
	 */
	public java.lang.String getDir_nro_via_2();
	/**
	 * Set accessor for persistent attribute: dir_nro_via_2
	 */
	public void setDir_nro_via_2(java.lang.String newDir_nro_via_2);
	/**
	 * Get accessor for persistent attribute: dir_lt1_via_2
	 */
	public java.lang.String getDir_lt1_via_2();
	/**
	 * Set accessor for persistent attribute: dir_lt1_via_2
	 */
	public void setDir_lt1_via_2(java.lang.String newDir_lt1_via_2);
	/**
	 * Get accessor for persistent attribute: dir_lt2_via_2
	 */
	public java.lang.String getDir_lt2_via_2();
	/**
	 * Set accessor for persistent attribute: dir_lt2_via_2
	 */
	public void setDir_lt2_via_2(java.lang.String newDir_lt2_via_2);
	/**
	 * Get accessor for persistent attribute: dir_zon_via_2
	 */
	public java.lang.String getDir_zon_via_2();
	/**
	 * Set accessor for persistent attribute: dir_zon_via_2
	 */
	public void setDir_zon_via_2(java.lang.String newDir_zon_via_2);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg1
	 */
	public java.lang.String getDir_tip_lg1();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg1
	 */
	public void setDir_tip_lg1(java.lang.String newDir_tip_lg1);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg1
	 */
	public java.lang.String getDir_nro_lg1();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg1
	 */
	public void setDir_nro_lg1(java.lang.String newDir_nro_lg1);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg2
	 */
	public java.lang.String getDir_tip_lg2();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg2
	 */
	public void setDir_tip_lg2(java.lang.String newDir_tip_lg2);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg2
	 */
	public java.lang.String getDir_nro_lg2();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg2
	 */
	public void setDir_nro_lg2(java.lang.String newDir_nro_lg2);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg3
	 */
	public java.lang.String getDir_tip_lg3();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg3
	 */
	public void setDir_tip_lg3(java.lang.String newDir_tip_lg3);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg3
	 */
	public java.lang.String getDir_nro_lg3();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg3
	 */
	public void setDir_nro_lg3(java.lang.String newDir_nro_lg3);
	/**
	 * Get accessor for persistent attribute: cod_ext_loc_cd
	 */
	public java.lang.String getCod_ext_loc_cd();
	/**
	 * Set accessor for persistent attribute: cod_ext_loc_cd
	 */
	public void setCod_ext_loc_cd(java.lang.String newCod_ext_loc_cd);
	/**
	 * Get accessor for persistent attribute: cod_ter_cd
	 */
	public java.lang.String getCod_ter_cd();
	/**
	 * Set accessor for persistent attribute: cod_ter_cd
	 */
	public void setCod_ter_cd(java.lang.String newCod_ter_cd);
	/**
	 * Get accessor for persistent attribute: cod_are_tel_cd
	 */
	public java.lang.String getCod_are_tel_cd();
	/**
	 * Set accessor for persistent attribute: cod_are_tel_cd
	 */
	public void setCod_are_tel_cd(java.lang.String newCod_are_tel_cd);
	/**
	 * Get accessor for persistent attribute: are_sn_tel_cd
	 */
	public java.lang.String getAre_sn_tel_cd();
	/**
	 * Set accessor for persistent attribute: are_sn_tel_cd
	 */
	public void setAre_sn_tel_cd(java.lang.String newAre_sn_tel_cd);
	/**
	 * Get accessor for persistent attribute: loc_ext_tel_cd
	 */
	public java.lang.String getLoc_ext_tel_cd();
	/**
	 * Set accessor for persistent attribute: loc_ext_tel_cd
	 */
	public void setLoc_ext_tel_cd(java.lang.String newLoc_ext_tel_cd);
	/**
	 * Get accessor for persistent attribute: tip_cal_atis_cd
	 */
	public java.lang.String getTip_cal_atis_cd();
	/**
	 * Set accessor for persistent attribute: tip_cal_atis_cd
	 */
	public void setTip_cal_atis_cd(java.lang.String newTip_cal_atis_cd);
	/**
	 * Get accessor for persistent attribute: nom_cal_ds
	 */
	public java.lang.String getNom_cal_ds();
	/**
	 * Set accessor for persistent attribute: nom_cal_ds
	 */
	public void setNom_cal_ds(java.lang.String newNom_cal_ds);
	/**
	 * Get accessor for persistent attribute: num_cal_nu
	 */
	public java.lang.String getNum_cal_nu();
	/**
	 * Set accessor for persistent attribute: num_cal_nu
	 */
	public void setNum_cal_nu(java.lang.String newNum_cal_nu);
	/**
	 * Get accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public java.lang.String getDsc_cmp_pri_ds();
	/**
	 * Set accessor for persistent attribute: dsc_cmp_pri_ds
	 */
	public void setDsc_cmp_pri_ds(java.lang.String newDsc_cmp_pri_ds);
	/**
	 * Get accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public java.lang.String getDsc_cmp_seg_ds();
	/**
	 * Set accessor for persistent attribute: dsc_cmp_seg_ds
	 */
	public void setDsc_cmp_seg_ds(java.lang.String newDsc_cmp_seg_ds);
	/**
	 * Get accessor for persistent attribute: cod_loc_cd
	 */
	public java.lang.Long getCod_loc_cd();
	/**
	 * Set accessor for persistent attribute: cod_loc_cd
	 */
	public void setCod_loc_cd(java.lang.Long newCod_loc_cd);
	/**
	 * Get accessor for persistent attribute: nom_slo_no
	 */
	public java.lang.String getNom_slo_no();
	/**
	 * Set accessor for persistent attribute: nom_slo_no
	 */
	public void setNom_slo_no(java.lang.String newNom_slo_no);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBitacora_peticion(java.util.Collection aBitacora_peticion);
	/**
	 * This method was generated for supporting the relationship role named fk_03_central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.CentralLocal getFk_03_central();
	/**
	 * This method was generated for supporting the relationship role named fk_03_central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_03_central(
		co.com.telefonica.atiempo.ejb.eb.CentralLocal aFk_03_central);
	/**
	 * This method was generated for supporting the relationship role named fk_02_departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.DepartamentoLocal getFk_02_departamento();
	/**
	 * This method was generated for supporting the relationship role named fk_02_departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_02_departamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aFk_02_departamento);
	/**
	 * This method was generated for supporting the relationship role named fk_01_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal getFk_01_localidad();
	/**
	 * This method was generated for supporting the relationship role named fk_01_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_01_localidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aFk_01_localidad);
	/**
	 * This method was generated for supporting the relationship role named fk_01_pet_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Peticion_atisLocal getFk_01_pet_atis();
	/**
	 * This method was generated for supporting the relationship role named fk_01_pet_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_01_pet_atis(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal aFk_01_pet_atis);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRespuesta_conect2_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRespuesta_conect2_apsc(
		java.util.Collection aRespuesta_conect2_apsc);
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion_flujo();
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_flujo(java.util.Collection aPeticion_flujo);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRecursos_linea_basica(
		java.util.Collection aRecursos_linea_basica);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_linea();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_linea(
		java.util.Collection aMensaje_estado_linea);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_stb
	 */
	public java.lang.String getNum_ide_nu_stb();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_stb
	 */
	public void setNum_ide_nu_stb(java.lang.String newNum_ide_nu_stb);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_tv
	 */
	public java.lang.String getNum_ide_nu_tv();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_tv
	 */
	public void setNum_ide_nu_tv(java.lang.String newNum_ide_nu_tv);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_ba();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_ba(java.util.Collection aMensaje_estado_ba);
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRecursos_ba();
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRecursos_ba(java.util.Collection aRecursos_ba);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_tv();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_tv(java.util.Collection aMensaje_estado_tv);
	/**
	 * This method was generated for supporting the relationship role named deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getDeco_tarjeta();
	/**
	 * This method was generated for supporting the relationship role named deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDeco_tarjeta(java.util.Collection aDeco_tarjeta);
	/**
	 * This method was generated for supporting the relationship role named tmp_deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTmp_deco_tarjeta();
	/**
	 * This method was generated for supporting the relationship role named tmp_deco_tarjeta.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTmp_deco_tarjeta(java.util.Collection aTmp_deco_tarjeta);
	/**
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTematico();
	/**
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTematico(java.util.Collection aTematico);
	/**
	 * @param string
	 * @return
	 */
	public String getAgrupacionesString(String delimitador);
	public boolean isPBX();
	public String obtenerCPINAGrupacionOriginal();
	public boolean setDatosPeticionBajaXTraslado() throws Exception;
	/**
	 * This method was generated for supporting the relationship role named modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getModem();
	/**
	 * This method was generated for supporting the relationship role named modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setModem(java.util.Collection aModem);
	public ArrayList getPsYTipoPcNoRealizado();
	public String getComercialProductIdentification();
	public int getpreviousServicePhone();
	/**
	 * This method was generated for supporting the relationship role named inventario_dth.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getInventario_dth();
	/**
	 * This method was generated for supporting the relationship role named inventario_dth.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setInventario_dth(java.util.Collection anInventario_dth);
	public String getNomTipoUso();
	public String getIdentificadorOriLinea();
	/**
	 * This method was generated for supporting the relationship role named altapctv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getAltapctv();
	/**
	 * This method was generated for supporting the relationship role named altapctv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAltapctv(java.util.Collection anAltapctv);
	/**
	 * This method was generated for supporting the relationship role named tmp_modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTmp_modem();
	/**
	 * This method was generated for supporting the relationship role named tmp_modem.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTmp_modem(java.util.Collection aTmp_modem);
	/**
	 * This method was generated for supporting the relationship role named controlvisita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getControlvisita();
	/**
	 * This method was generated for supporting the relationship role named controlvisita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setControlvisita(java.util.Collection aControlvisita);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_ic
	 */
	public java.lang.String getNum_ide_nu_ic();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_ic
	 */
	public void setNum_ide_nu_ic(java.lang.String newNum_ide_nu_ic);
	/**
	 * Get accessor for persistent attribute: tipoErrorId
	 */
	public java.lang.Long getTipoErrorId();
	/**
	 * Set accessor for persistent attribute: tipoErrorId
	 */
	public void setTipoErrorId(java.lang.Long newTipoErrorId);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds_1
	 */
	public java.lang.String getTel_cot_ds_1();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds_1
	 */
	public void setTel_cot_ds_1(java.lang.String newTel_cot_ds_1);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds_2
	 */
	public java.lang.String getTel_cot_ds_2();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds_2
	 */
	public void setTel_cot_ds_2(java.lang.String newTel_cot_ds_2);
	/**
	 * Get accessor for persistent attribute: hab_terra
	 */
	public java.lang.Integer getHab_terra();
	/**
	 * Set accessor for persistent attribute: hab_terra
	 */
	public void setHab_terra(java.lang.Integer newHab_terra);
	/**
 * @param graniteCode
	 */
	public void setGranite_code(int graniteCode);
	/**
	 * Get accessor for persistent attribute: granite_code
	 */
	public int getGranite_code();
	/**
	 * Get accessor for persistent attribute: regla_id
	 */
	public java.lang.Long getRegla_id();
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public void setRegla_id(java.lang.Long newRegla_id);
	/**
	 * This method was generated for supporting the relationship role named regla_retenciones.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal getRegla_retenciones();
	/**
	 * This method was generated for supporting the relationship role named regla_retenciones.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRegla_retenciones(
		co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal aRegla_retenciones);
			/**
	 * Get accessor for persistent attribute: inf_cicl_fac
	 */
	public java.lang.String getInf_cicl_fac();
	/**
	 * Set accessor for persistent attribute: inf_cicl_fac
	 */
	public void setInf_cicl_fac(java.lang.String newInf_cicl_fac);
    /**
     * This method was generated for supporting the relationship role named tmp_equipo.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public java.util.Collection getTmp_equipo();
    /**
     * This method was generated for supporting the relationship role named tmp_equipo.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public void setTmp_equipo(java.util.Collection aTmp_equipo);
    /**
     * This method was generated for supporting the relationship role named elemento_peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public java.util.Collection getElemento_peticion();
    /**
     * This method was generated for supporting the relationship role named elemento_peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public void setElemento_peticion(java.util.Collection anElemento_peticion);
	/**
	 * Get accessor for persistent attribute: jornada_agnd_sc
	 */
	public java.lang.String getJornada_agnd_sc();
	/**
	 * Set accessor for persistent attribute: jornada_agnd_sc
	 */
	public void setJornada_agnd_sc(java.lang.String newJornada_agnd_sc);
	/**
	 * Get accessor for persistent attribute: coord_x_agnd_sc
	 */
	public java.lang.String getCoord_x_agnd_sc();
	/**
	 * Set accessor for persistent attribute: coord_x_agnd_sc
	 */
	public void setCoord_x_agnd_sc(java.lang.String newCoord_x_agnd_sc);
	/**
	 * Get accessor for persistent attribute: coord_y_agnd_sc
	 */
	public java.lang.String getCoord_y_agnd_sc();
	/**
	 * Set accessor for persistent attribute: coord_y_agnd_sc
	 */
	public void setCoord_y_agnd_sc(java.lang.String newCoord_y_agnd_sc);
	/**
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getAgenda_sc();
	/**
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAgenda_sc(java.util.Collection anAgenda_sc);
	/**
	 * Get accessor for persistent attribute: estado_agend_sc
	 */
	public java.lang.Integer getEstado_agend_sc();
	/**
	 * Set accessor for persistent attribute: estado_agend_sc
	 */
	public void setEstado_agend_sc(java.lang.Integer newEstado_agend_sc);
	/**
	 * This method was generated for supporting the relationship role named tutor_web.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTutor_web();
	/**
	 * This method was generated for supporting the relationship role named tutor_web.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTutor_web(java.util.Collection aTutor_web);
	/**
	 * Get accessor for persistent attribute: pago_tv_sola_ok
	 */
	public java.lang.String getPago_tv_sola_ok();
	/**
	 * Set accessor for persistent attribute: pago_tv_sola_ok
	 */
	public void setPago_tv_sola_ok(java.lang.String newPago_tv_sola_ok);
	/**
	 * Get accessor for persistent attribute: fecha_pago_tv_sola
	 */
	public java.lang.String getFecha_pago_tv_sola();
	/**
	 * Set accessor for persistent attribute: fecha_pago_tv_sola
	 */
	public void setFecha_pago_tv_sola(java.lang.String newFecha_pago_tv_sola);
	/**
	 * Get accessor for persistent attribute: num_venta_int_movil
	 */
	public int getNum_venta_int_movil();
	/**
	 * Set accessor for persistent attribute: num_venta_int_movil
	 */
	public void setNum_venta_int_movil(int newNum_venta_int_movil);
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal getTmp_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTmp_agenda_sc(
		co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal aTmp_agenda_sc);
	/**
	 * This method was generated for supporting the relationship role named elementonoserializado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getElementonoserializado();
	/**
	 * This method was generated for supporting the relationship role named elementonoserializado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setElementonoserializado(
		java.util.Collection anElementonoserializado);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_pdg
	 */
	public java.lang.String getNum_ide_nu_pdg();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_pdg
	 */
	public void setNum_ide_nu_pdg(java.lang.String newNum_ide_nu_pdg);
	/**
	 * Get accessor for persistent attribute: control_rec_eq
	 */
	public java.lang.String getControl_rec_eq();
	/**
	 * Set accessor for persistent attribute: control_rec_eq
	 */
	public void setControl_rec_eq(java.lang.String newControl_rec_eq);
	/**
	 * Get accessor for persistent attribute: flag_cp
	 */
	public java.lang.String getFlag_cp();
	/**
	 * Set accessor for persistent attribute: flag_cp
	 */
	public void setFlag_cp(java.lang.String newFlag_cp);
	/**
	 * Get accessor for persistent attribute: fecha_entrega
	 */
	public java.sql.Timestamp getFecha_entrega();
	/**
	 * Set accessor for persistent attribute: fecha_entrega
	 */
	public void setFecha_entrega(java.sql.Timestamp newFecha_entrega);
	/**
	 * Get accessor for persistent attribute: nombre_entrega
	 */
	public java.lang.String getNombre_entrega();
	/**
	 * Set accessor for persistent attribute: nombre_entrega
	 */
	public void setNombre_entrega(java.lang.String newNombre_entrega);
	/**
	 * Get accessor for persistent attribute: cedula
	 */
	public java.lang.String getCedula();
	/**
	 * Set accessor for persistent attribute: cedula
	 */
	public void setCedula(java.lang.String newCedula);
	/**
	 * Get accessor for persistent attribute: estrategia
	 */
	public java.lang.String getEstrategia();
	/**
	 * Set accessor for persistent attribute: estrategia
	 */
	public void setEstrategia(java.lang.String newEstrategia);
	/**
	 * Get accessor for persistent attribute: tipo_desconcilia
	 */
	public java.lang.String getTipo_desconcilia();
	/**
	 * Set accessor for persistent attribute: tipo_desconcilia
	 */
	public void setTipo_desconcilia(java.lang.String newTipo_desconcilia);
	/**
	 * This method was generated for supporting the relationship role named instalaciones_vip.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getInstalaciones_vip();
	/**
	 * This method was generated for supporting the relationship role named instalaciones_vip.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setInstalaciones_vip(java.util.Collection anInstalaciones_vip);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_mp
	 */
	public java.lang.String getNum_ide_nu_mp();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_mp
	 */
	public void setNum_ide_nu_mp(java.lang.String newNum_ide_nu_mp);
}
