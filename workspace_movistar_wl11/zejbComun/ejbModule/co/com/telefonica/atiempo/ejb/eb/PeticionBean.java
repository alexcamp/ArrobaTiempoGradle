package co.com.telefonica.atiempo.ejb.eb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: Peticion
 */
public abstract class PeticionBean implements javax.ejb.EntityBean {
	private static final Logger logger=Logger.getLogger(PeticionBean.class);
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
	/**
	 * ejbCreate
	 */
	
	public PeticionKey ejbCreate(long petinumero,Peticion_atisLocal peticion_atisLocal,LocalidadLocal localidadLocal,DepartamentoLocal departamentoLocal) throws javax.ejb.CreateException
	{
		setPeti_numero(new Long(petinumero));
		return null;
	}
	
	public void ejbPostCreate(long petinumero,Peticion_atisLocal peticion_atisLocal,LocalidadLocal localidadLocal,DepartamentoLocal departamentoLocal) throws javax.ejb.CreateException
	{
		setFk_01_pet_atis(peticion_atisLocal);
		setFk_01_localidad(localidadLocal);
		setFk_02_departamento(departamentoLocal);
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public abstract java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public abstract void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * Get accessor for persistent attribute: espe_id
	 */
	public abstract java.lang.Integer getEspe_id();
	/**
	 * Set accessor for persistent attribute: espe_id
	 */
	public abstract void setEspe_id(java.lang.Integer newEspe_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public abstract java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public abstract void setTica_id(java.lang.String newTica_id);
	/**
	 * Get accessor for persistent attribute: agen_id
	 */
	public abstract java.lang.Long getAgen_id();
	/**
	 * Set accessor for persistent attribute: agen_id
	 */
	public abstract void setAgen_id(java.lang.Long newAgen_id);
	/**
	 * Get accessor for persistent attribute: line_tras_id
	 */
	public abstract java.lang.Long getLine_tras_id();
	/**
	 * Set accessor for persistent attribute: line_tras_id
	 */
	public abstract void setLine_tras_id(java.lang.Long newLine_tras_id);
	/**
	 * Get accessor for persistent attribute: cod_cli_cd
	 */
	public abstract java.lang.Long getCod_cli_cd();
	/**
	 * Set accessor for persistent attribute: cod_cli_cd
	 */
	public abstract void setCod_cli_cd(java.lang.Long newCod_cli_cd);
	/**
	 * Get accessor for persistent attribute: peti_fecha_ingreso
	 */
	public abstract java.sql.Timestamp getPeti_fecha_ingreso();
	/**
	 * Set accessor for persistent attribute: peti_fecha_ingreso
	 */
	public abstract void setPeti_fecha_ingreso(
		java.sql.Timestamp newPeti_fecha_ingreso);
	/**
	 * Get accessor for persistent attribute: peti_fecha_compromiso
	 */
	public abstract java.sql.Timestamp getPeti_fecha_compromiso();
	/**
	 * Set accessor for persistent attribute: peti_fecha_compromiso
	 */
	public abstract void setPeti_fecha_compromiso(
		java.sql.Timestamp newPeti_fecha_compromiso);
	/**
	 * Get accessor for persistent attribute: peti_observacion
	 */
	public abstract java.lang.String getPeti_observacion();
	/**
	 * Set accessor for persistent attribute: peti_observacion
	 */
	public abstract void setPeti_observacion(
		java.lang.String newPeti_observacion);
	/**
	 * Get accessor for persistent attribute: peti_usuario_emisor
	 */
	public abstract java.lang.String getPeti_usuario_emisor();
	/**
	 * Set accessor for persistent attribute: peti_usuario_emisor
	 */
	public abstract void setPeti_usuario_emisor(
		java.lang.String newPeti_usuario_emisor);
	/**
	 * Get accessor for persistent attribute: peti_fecha_termino
	 */
	public abstract java.sql.Timestamp getPeti_fecha_termino();
	/**
	 * Set accessor for persistent attribute: peti_fecha_termino
	 */
	public abstract void setPeti_fecha_termino(
		java.sql.Timestamp newPeti_fecha_termino);
	/**
	 * Get accessor for persistent attribute: peti_id_instancia
	 */
	public abstract java.lang.String getPeti_id_instancia();
	/**
	 * Set accessor for persistent attribute: peti_id_instancia
	 */
	public abstract void setPeti_id_instancia(
		java.lang.String newPeti_id_instancia);
	/**
	 * Get accessor for persistent attribute: peti_rut_vendedor
	 */
	public abstract java.lang.String getPeti_rut_vendedor();
	/**
	 * Set accessor for persistent attribute: peti_rut_vendedor
	 */
	public abstract void setPeti_rut_vendedor(
		java.lang.String newPeti_rut_vendedor);
	/**
	 * Get accessor for persistent attribute: peti_causal_baja
	 */
	public abstract java.lang.String getPeti_causal_baja();
	/**
	 * Set accessor for persistent attribute: peti_causal_baja
	 */
	public abstract void setPeti_causal_baja(
		java.lang.String newPeti_causal_baja);
	/**
	 * Get accessor for persistent attribute: peti_tipo_hora
	 */
	public abstract java.lang.String getPeti_tipo_hora();
	/**
	 * Set accessor for persistent attribute: peti_tipo_hora
	 */
	public abstract void setPeti_tipo_hora(java.lang.String newPeti_tipo_hora);
	/**
	 * Get accessor for persistent attribute: peti_hora_inicio
	 */
	public abstract java.sql.Timestamp getPeti_hora_inicio();
	/**
	 * Set accessor for persistent attribute: peti_hora_inicio
	 */
	public abstract void setPeti_hora_inicio(
		java.sql.Timestamp newPeti_hora_inicio);
	/**
	 * Get accessor for persistent attribute: peti_hora_fin
	 */
	public abstract java.sql.Timestamp getPeti_hora_fin();
	/**
	 * Set accessor for persistent attribute: peti_hora_fin
	 */
	public abstract void setPeti_hora_fin(java.sql.Timestamp newPeti_hora_fin);
	/**
	 * Get accessor for persistent attribute: peti_numero_nueva
	 */
	public abstract java.lang.Long getPeti_numero_nueva();
	/**
	 * Set accessor for persistent attribute: peti_numero_nueva
	 */
	public abstract void setPeti_numero_nueva(
		java.lang.Long newPeti_numero_nueva);
	/**
	 * Get accessor for persistent attribute: peti_ooss
	 */
	public abstract java.lang.Long getPeti_ooss();
	/**
	 * Set accessor for persistent attribute: peti_ooss
	 */
	public abstract void setPeti_ooss(java.lang.Long newPeti_ooss);
	/**
	 * Get accessor for persistent attribute: peti_registro_alta
	 */
	public abstract java.lang.Integer getPeti_registro_alta();
	/**
	 * Set accessor for persistent attribute: peti_registro_alta
	 */
	public abstract void setPeti_registro_alta(
		java.lang.Integer newPeti_registro_alta);
	/**
	 * Get accessor for persistent attribute: peti_aviso_alta
	 */
	public abstract java.lang.Integer getPeti_aviso_alta();
	/**
	 * Set accessor for persistent attribute: peti_aviso_alta
	 */
	public abstract void setPeti_aviso_alta(
		java.lang.Integer newPeti_aviso_alta);
	/**
	 * Get accessor for persistent attribute: peti_fecha_modificacion
	 */
	public abstract java.sql.Timestamp getPeti_fecha_modificacion();
	/**
	 * Set accessor for persistent attribute: peti_fecha_modificacion
	 */
	public abstract void setPeti_fecha_modificacion(
		java.sql.Timestamp newPeti_fecha_modificacion);
	/**
	 * Get accessor for persistent attribute: peti_tipo
	 */
	public abstract java.lang.String getPeti_tipo();
	/**
	 * Set accessor for persistent attribute: peti_tipo
	 */
	public abstract void setPeti_tipo(java.lang.String newPeti_tipo);
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
	 * Get accessor for persistent attribute: dir_tip_via_1
	 */
	public abstract java.lang.String getDir_tip_via_1();
	/**
	 * Set accessor for persistent attribute: dir_tip_via_1
	 */
	public abstract void setDir_tip_via_1(java.lang.String newDir_tip_via_1);
	/**
	 * Get accessor for persistent attribute: dir_nro_via_1
	 */
	public abstract java.lang.String getDir_nro_via_1();
	/**
	 * Set accessor for persistent attribute: dir_nro_via_1
	 */
	public abstract void setDir_nro_via_1(java.lang.String newDir_nro_via_1);
	/**
	 * Get accessor for persistent attribute: dir_lt1_via_1
	 */
	public abstract java.lang.String getDir_lt1_via_1();
	/**
	 * Set accessor for persistent attribute: dir_lt1_via_1
	 */
	public abstract void setDir_lt1_via_1(java.lang.String newDir_lt1_via_1);
	/**
	 * Get accessor for persistent attribute: dir_lt2_via_1
	 */
	public abstract java.lang.String getDir_lt2_via_1();
	/**
	 * Set accessor for persistent attribute: dir_lt2_via_1
	 */
	public abstract void setDir_lt2_via_1(java.lang.String newDir_lt2_via_1);
	/**
	 * Get accessor for persistent attribute: dir_zon_via_1
	 */
	public abstract java.lang.String getDir_zon_via_1();
	/**
	 * Set accessor for persistent attribute: dir_zon_via_1
	 */
	public abstract void setDir_zon_via_1(java.lang.String newDir_zon_via_1);
	/**
	 * Get accessor for persistent attribute: dir_tip_via_2
	 */
	public abstract java.lang.String getDir_tip_via_2();
	/**
	 * Set accessor for persistent attribute: dir_tip_via_2
	 */
	public abstract void setDir_tip_via_2(java.lang.String newDir_tip_via_2);
	/**
	 * Get accessor for persistent attribute: dir_nro_via_2
	 */
	public abstract java.lang.String getDir_nro_via_2();
	/**
	 * Set accessor for persistent attribute: dir_nro_via_2
	 */
	public abstract void setDir_nro_via_2(java.lang.String newDir_nro_via_2);
	/**
	 * Get accessor for persistent attribute: dir_lt1_via_2
	 */
	public abstract java.lang.String getDir_lt1_via_2();
	/**
	 * Set accessor for persistent attribute: dir_lt1_via_2
	 */
	public abstract void setDir_lt1_via_2(java.lang.String newDir_lt1_via_2);
	/**
	 * Get accessor for persistent attribute: dir_lt2_via_2
	 */
	public abstract java.lang.String getDir_lt2_via_2();
	/**
	 * Set accessor for persistent attribute: dir_lt2_via_2
	 */
	public abstract void setDir_lt2_via_2(java.lang.String newDir_lt2_via_2);
	/**
	 * Get accessor for persistent attribute: dir_zon_via_2
	 */
	public abstract java.lang.String getDir_zon_via_2();
	/**
	 * Set accessor for persistent attribute: dir_zon_via_2
	 */
	public abstract void setDir_zon_via_2(java.lang.String newDir_zon_via_2);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg1
	 */
	public abstract java.lang.String getDir_tip_lg1();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg1
	 */
	public abstract void setDir_tip_lg1(java.lang.String newDir_tip_lg1);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg1
	 */
	public abstract java.lang.String getDir_nro_lg1();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg1
	 */
	public abstract void setDir_nro_lg1(java.lang.String newDir_nro_lg1);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg2
	 */
	public abstract java.lang.String getDir_tip_lg2();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg2
	 */
	public abstract void setDir_tip_lg2(java.lang.String newDir_tip_lg2);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg2
	 */
	public abstract java.lang.String getDir_nro_lg2();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg2
	 */
	public abstract void setDir_nro_lg2(java.lang.String newDir_nro_lg2);
	/**
	 * Get accessor for persistent attribute: dir_tip_lg3
	 */
	public abstract java.lang.String getDir_tip_lg3();
	/**
	 * Set accessor for persistent attribute: dir_tip_lg3
	 */
	public abstract void setDir_tip_lg3(java.lang.String newDir_tip_lg3);
	/**
	 * Get accessor for persistent attribute: dir_nro_lg3
	 */
	public abstract java.lang.String getDir_nro_lg3();
	/**
	 * Set accessor for persistent attribute: dir_nro_lg3
	 */
	public abstract void setDir_nro_lg3(java.lang.String newDir_nro_lg3);
	/**
	 * Get accessor for persistent attribute: cod_ext_loc_cd
	 */
	public abstract java.lang.String getCod_ext_loc_cd();
	/**
	 * Set accessor for persistent attribute: cod_ext_loc_cd
	 */
	public abstract void setCod_ext_loc_cd(java.lang.String newCod_ext_loc_cd);
	/**
	 * Get accessor for persistent attribute: cod_ter_cd
	 */
	public abstract java.lang.String getCod_ter_cd();
	/**
	 * Set accessor for persistent attribute: cod_ter_cd
	 */
	public abstract void setCod_ter_cd(java.lang.String newCod_ter_cd);
	/**
	 * Get accessor for persistent attribute: cod_are_tel_cd
	 */
	public abstract java.lang.String getCod_are_tel_cd();
	/**
	 * Set accessor for persistent attribute: cod_are_tel_cd
	 */
	public abstract void setCod_are_tel_cd(java.lang.String newCod_are_tel_cd);
	/**
	 * Get accessor for persistent attribute: are_sn_tel_cd
	 */
	public abstract java.lang.String getAre_sn_tel_cd();
	/**
	 * Set accessor for persistent attribute: are_sn_tel_cd
	 */
	public abstract void setAre_sn_tel_cd(java.lang.String newAre_sn_tel_cd);
	/**
	 * Get accessor for persistent attribute: loc_ext_tel_cd
	 */
	public abstract java.lang.String getLoc_ext_tel_cd();
	/**
	 * Set accessor for persistent attribute: loc_ext_tel_cd
	 */
	public abstract void setLoc_ext_tel_cd(java.lang.String newLoc_ext_tel_cd);
	/**
	 * Get accessor for persistent attribute: tip_cal_atis_cd
	 */
	public abstract java.lang.String getTip_cal_atis_cd();
	/**
	 * Set accessor for persistent attribute: tip_cal_atis_cd
	 */
	public abstract void setTip_cal_atis_cd(
		java.lang.String newTip_cal_atis_cd);
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
	 * Get accessor for persistent attribute: cod_loc_cd
	 */
	public abstract java.lang.Long getCod_loc_cd();
	/**
	 * Set accessor for persistent attribute: cod_loc_cd
	 */
	public abstract void setCod_loc_cd(java.lang.Long newCod_loc_cd);
	/**
	 * Get accessor for persistent attribute: nom_slo_no
	 */
	public abstract java.lang.String getNom_slo_no();
	/**
	 * Set accessor for persistent attribute: nom_slo_no
	 */
	public abstract void setNom_slo_no(java.lang.String newNom_slo_no);
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
	 * This method was generated for supporting the relationship role named fk_03_central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.CentralLocal getFk_03_central();
	/**
	 * This method was generated for supporting the relationship role named fk_03_central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_03_central(
		co.com.telefonica.atiempo.ejb.eb.CentralLocal aFk_03_central);
	/**
	 * This method was generated for supporting the relationship role named fk_02_departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_02_departamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aFk_02_departamento);
	/**
	 * This method was generated for supporting the relationship role named fk_01_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.LocalidadLocal getFk_01_localidad();
	/**
	 * This method was generated for supporting the relationship role named fk_01_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_01_localidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aFk_01_localidad);
	/**
	 * This method was generated for supporting the relationship role named fk_01_pet_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_01_pet_atis(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal aFk_01_pet_atis);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRespuesta_conect2_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRespuesta_conect2_apsc(
		java.util.Collection aRespuesta_conect2_apsc);
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
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_linea();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_linea(
		java.util.Collection aMensaje_estado_linea);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_stb
	 */
	public abstract java.lang.String getNum_ide_nu_stb();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_stb
	 */
	public abstract void setNum_ide_nu_stb(java.lang.String newNum_ide_nu_stb);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_tv
	 */
	public abstract java.lang.String getNum_ide_nu_tv();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_tv
	 */
	public abstract void setNum_ide_nu_tv(java.lang.String newNum_ide_nu_tv);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_ba();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_ba(
		java.util.Collection aMensaje_estado_ba);
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
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_tv();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_tv(
		java.util.Collection aMensaje_estado_tv);
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
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTematico();
	/**
	 * This method was generated for supporting the relationship role named tematico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTematico(java.util.Collection aTematico);
	
	public boolean isPBX()
	{
		for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
			if(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue()==ComunInterfaces.tipoPBX)
				return true;
		}
		return false;
	}
	public String getAgrupacionesString(String delimitador)
	{
		String retorno="";
		ArrayList lista=new ArrayList();
		for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
			Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
			Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion_atisLocal.getPrimaryKey();
			if(agrupacion_atisLocal.getAgr_sub_pdr_cd()!=null)
			{
				if(agrupacion_atisLocal.getAgr_sub_pdr_cd().intValue()!=0)
				{
					Integer groupCodePadre=agrupacion_atisLocal.getAgr_sub_pdr_cd();
					if(!lista.contains(groupCodePadre))
						lista.add(groupCodePadre);	
				}
				else
					if(!lista.contains(agrupacion_atisKey.cod_agr_sub_nu))
						lista.add(agrupacion_atisKey.cod_agr_sub_nu);
			}
			else
				if(!lista.contains(agrupacion_atisKey.cod_agr_sub_nu))
					lista.add(agrupacion_atisKey.cod_agr_sub_nu);
		}
		try
		{
			Collections.sort(lista);
			String del=null;
			if(delimitador!=null)
			{
				if(!delimitador.equals(""))
					del=delimitador;
				else
					del="-";
			}
			for(Iterator iterator=lista.iterator();iterator.hasNext();)
			{
				Integer cod_agr_sub_nu=(Integer) iterator.next();
				retorno+=cod_agr_sub_nu.toString();
				if(iterator.hasNext())
					retorno+=del;
			}
		}
		catch(Exception e)
		{
			logger.error("Exception",e);
		}
		return retorno;
	}
	
	public String obtenerCPINAGrupacionOriginal()
	{
		String retorno="";
		try
		{
			for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Agrupacion_atisLocal agrupacion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();
				retorno=agrupacion_atisLocal.getNum_ide_nu();
				break;
			}
			return retorno;
		}
		catch(Exception e)
		{
			logger.debug("Exception",e);
			return retorno;
		}
	}
	
	
	public String getIdentificadorOriLinea()
	{
		String retorno="";
		try
		{
			for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Familia_producto_servicioKey fKey=producto_servicio_peticionLocal.getFamiliaKey();
				int fa=fKey.faps_id.intValue();
				//REQ BA NAKED adicion familia PC/PS y PS naked
				if(fa!=ComunInterfaces.familiaIntEquipado && fa!=ComunInterfaces.familiaLinea && fa!=ComunInterfaces.familiaPcLinea && fa!=ComunInterfaces.familiaBandaAncha && fa!=ComunInterfaces.familiaPcBA && fa!=ComunInterfaces.familiaPcPsBANaked && fa!=ComunInterfaces.familiaBandaAnchaNaked)
					continue; 
				Agrupacion_atisLocal agrupacion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();
				retorno=agrupacion_atisLocal.getNum_ide_nu();
				break;
			}
			return retorno;
		}
		catch(Exception e)
		{
			logger.debug("Exception",e);
			return retorno;
		}
	}
	
	
	public String getNomTipoUso()
	{
		String retorno="";
		try
		{
			for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Agrupacion_atisLocal agrupacion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();
				retorno=agrupacion_atisLocal.getNom_tip_uso_no();
				break;
			}
			return retorno;
		}
		catch(Exception e)
		{
			logger.debug("Exception",e);
			return retorno;
		}
	}
	
	public boolean setDatosPeticionBajaXTraslado() throws Exception
	{
		//Primero verifico si es Traslado
		for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			if(operacion_comercialLocal.getOpco_tras()!=null)
			{
				if(operacion_comercialLocal.getOpco_tras().equals("T"))
				{
					if(operacion_comercialLocal.getOpco_tipo()!=null)
					{
						if(operacion_comercialLocal.getOpco_tipo().equals("ACP") || operacion_comercialLocal.getOpco_tipo().equals("A"))
							continue;//Necesito encontrar el Ps de Baja
						else//Encontré el ps de Baja
						{
							Agrupacion_atisLocal agrupacionBaja=producto_servicio_peticionLocal.getFk_01_subp_atis().getFk_agru_sub();
							//Ahora seteo los datos de la peticion Atiempo
							setearDireccionPetDeAgrupacion(agrupacionBaja.getDireccion_atis());
							return true;
						}
					}
					else
						throw new Exception("Error de Proceso No se puede Determinar tipo de Op");	
				}
			}
		}
		return false;
	}
	/**
	 * @param collection
	 */
	private void setearDireccionPetDeAgrupacion(Collection collection)
	{
		for(Iterator iterator=collection.iterator();iterator.hasNext();)
		{
			Direccion_atisLocal direccion_atisLocal=(Direccion_atisLocal) iterator.next();
			if (direccion_atisLocal.getDir_lt1_via_1() !=null && !direccion_atisLocal.getDir_lt1_via_1().equals(""))
			{
				setDir_lt1_via_1(direccion_atisLocal.getDir_lt1_via_1());
			}else
			{
				setDir_lt1_via_1("-");
			}

			if (direccion_atisLocal.getDir_lt2_via_1() !=null && !direccion_atisLocal.getDir_lt2_via_1().equals("")){
				setDir_lt2_via_1(direccion_atisLocal.getDir_lt2_via_1());
			}else{
				setDir_lt2_via_1("-");
			}


			setDir_nro_via_1(String.valueOf(direccion_atisLocal.getDir_nro_via_1()));

			if (direccion_atisLocal.getDir_lt1_via_1() !=null && !direccion_atisLocal.getDir_lt1_via_1().equals("")){
			setDir_lt1_via_1(direccion_atisLocal.getDir_lt1_via_1());
			}else{
	
				setDir_lt1_via_1("-");
			}

			if (direccion_atisLocal.getDir_lt2_via_1() !=null && !direccion_atisLocal.getDir_lt2_via_1().equals("")){
				setDir_lt2_via_1(direccion_atisLocal.getDir_lt2_via_1());
			}else{
				setDir_lt2_via_1("-");
			}

			if (direccion_atisLocal.getDir_zon_via_1() !=null && !direccion_atisLocal.getDir_zon_via_1().equals("")){
				setDir_zon_via_1(direccion_atisLocal.getDir_zon_via_1());
			}else{
				setDir_zon_via_1("-");
			}

			if (direccion_atisLocal.getDir_tip_via_1() !=null && !direccion_atisLocal.getDir_tip_via_1().equals("")){
				setDir_tip_via_1(direccion_atisLocal.getDir_tip_via_1());
			}else{
				setDir_tip_via_1("-");
			}

			setDir_nro_via_2(String.valueOf(direccion_atisLocal.getDir_nro_via_2()));

			if (direccion_atisLocal.getDir_zon_via_2() !=null && !direccion_atisLocal.getDir_zon_via_2().equals("")){
				setDir_zon_via_2(direccion_atisLocal.getDir_zon_via_2());
			}else{
				setDir_zon_via_2("-");
			}

			if (direccion_atisLocal.getDir_tip_via_2() !=null && !direccion_atisLocal.getDir_tip_via_2().equals("")){
				setDir_tip_via_2(direccion_atisLocal.getDir_tip_via_2());
			}else{
				setDir_tip_via_2("-");
			}

			if (direccion_atisLocal.getDir_lt1_via_2() !=null && !direccion_atisLocal.getDir_lt1_via_2().equals("")){
				setDir_lt1_via_2(direccion_atisLocal.getDir_lt1_via_2());
			}else{
				setDir_lt1_via_2("-");
			}

			if (direccion_atisLocal.getDir_lt2_via_2() !=null && !direccion_atisLocal.getDir_lt2_via_2().equals("")){
				setDir_lt2_via_2(direccion_atisLocal.getDir_lt2_via_2());
			}else{
				setDir_lt2_via_2("-");
			}

			if (direccion_atisLocal.getDir_tip_lg1() !=null && !direccion_atisLocal.getDir_tip_lg1().equals("")){
				setDir_tip_lg1(direccion_atisLocal.getDir_tip_lg1());
			}else{
				setDir_tip_lg1("-");
			}

			if (direccion_atisLocal.getDir_nro_lg1() !=null && !direccion_atisLocal.getDir_nro_lg1().equals("")){
				setDir_nro_lg1(direccion_atisLocal.getDir_nro_lg1());
			}else{
				setDir_nro_lg1("-");
			}

			if (direccion_atisLocal.getDir_tip_lg2() !=null && !direccion_atisLocal.getDir_tip_lg2().equals("")){
				setDir_tip_lg2(direccion_atisLocal.getDir_tip_lg2());
			}else{
				setDir_tip_lg2("-");
			}

			if (direccion_atisLocal.getDir_nro_lg2() !=null && !direccion_atisLocal.getDir_nro_lg2().equals("")){
				setDir_nro_lg2(direccion_atisLocal.getDir_nro_lg2());
			}else{
				setDir_nro_lg2("-");
			}

			if (direccion_atisLocal.getDir_tip_lg3() !=null && !direccion_atisLocal.getDir_tip_lg3().equals("")){
				setDir_tip_lg3(direccion_atisLocal.getDir_tip_lg3());
			}else{
				setDir_tip_lg3("-");
			}

			if (direccion_atisLocal.getDir_nro_lg3() !=null && !direccion_atisLocal.getDir_nro_lg3().equals("")){
				setDir_nro_lg3(direccion_atisLocal.getDir_nro_lg3());
			}else{
				setDir_nro_lg3("-");
			}

			setTip_cal_atis_cd(direccion_atisLocal.getTip_cal_atis_cd());
			setNom_cal_ds(direccion_atisLocal.getNom_cal_ds());
			setNum_cal_nu(direccion_atisLocal.getNum_cal_nu());
			setDsc_cmp_pri_ds(direccion_atisLocal.getDsc_cmp_pri_ds());
			setDsc_cmp_seg_ds(direccion_atisLocal.getDsc_cmp_seg_ds());
			setNom_slo_no(direccion_atisLocal.getNom_slo_no());
			setCod_ext_loc_cd(direccion_atisLocal.getCod_ext_loc_cd());
			setCod_ter_cd(direccion_atisLocal.getCod_ter_cd());
			break;		
		}
	}
	
	public ArrayList getPsYTipoPcNoRealizado()
	{
		ArrayList arrayList=new ArrayList();
		for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
			Producto_servicio_peticionKey producto_servicio_peticionKey=(Producto_servicio_peticionKey) producto_servicio_peticionLocal.getPrimaryKey();
//			if(producto_servicio_peticionLocal.getPspe_realizado().shortValue()==1)
//				continue;
			Producto_servicioLocal producto_servicioLocal=producto_servicio_peticionLocal.getProducto_servicio();
			Familia_producto_servicioLocal familia_producto_servicioLocal=producto_servicioLocal.getFamilia_producto_servicio();
			Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) familia_producto_servicioLocal.getPrimaryKey();
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) operacion_comercialLocal.getPrimaryKey();
			
			ProductoDTO objPS = new ProductoDTO();
			Producto_servicioKey key=(Producto_servicioKey) producto_servicioLocal.getPrimaryKey();
			objPS.setPetiNumero(producto_servicio_peticionKey.fk_psp_pet_peti_numero);
			objPS.setCorrelativo(producto_servicio_peticionKey.correlativo);
			objPS.setId(key.ps_id);
			objPS.setIdProducto(key.ps_id);
			objPS.setNombreProducto(producto_servicioLocal.getPs_nombre());
			objPS.setObservacion(producto_servicioLocal.getPs_observacion());
			objPS.setPcoObligatorio(new Byte(producto_servicioLocal.getPs_pco_obligatorio().byteValue()));
			objPS.setIdEmpresa(producto_servicioLocal.getEmpr_id());
			objPS.setIdAmbito(producto_servicioLocal.getAmbi_id());
			objPS.setIdGrupoProducto( producto_servicioLocal.getGrps_id() );
			objPS.setNombreFamiliaPS( familia_producto_servicioLocal.getFaps_nombre() );
			objPS.setIdFaps( familia_producto_servicioKey.faps_id );
			objPS.setCodigoFamiliaPS( familia_producto_servicioLocal.getFaps_codigo() );
			//REQ BA NAKED adicion familia PC/PS
			if(familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcLinea ||  familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcTV  || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcBA || familia_producto_servicioKey.faps_id.intValue()==ComunInterfaces.familiaPcPsBANaked)
			{
				objPS.setTipoPC(true);
				Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
				Agrupacion_atisLocal agrupacion_atisLocal=subpeticion_atisLocal.getFk_agru_sub();
				String descTipoPC="";
				try
				{
					Tipo_pcLocalHome tipoPC = (Tipo_pcLocalHome)HomeFactory.getHome(Tipo_pcLocalHome.JNDI_NAME);
					Tipo_pcKey tPCK = new Tipo_pcKey();
					tPCK.id_tipo_pc= new Integer(agrupacion_atisLocal.getTip_pro_cmr_cd().intValue());
					Tipo_pcLocal tipoPcLocal = tipoPC.findByPrimaryKey(tPCK);
					descTipoPC= tipoPcLocal.getNombre_pc();
					objPS.setValTipoPc(tipoPcLocal.getFamilia_pc());
					objPS.setIdentificadorProducto(agrupacion_atisLocal.getNum_ide_nu());
				}
				catch (Exception e)
				{
					descTipoPC="" + agrupacion_atisLocal.getTip_pro_cmr_cd();
				}
				objPS.setDescTipo(descTipoPC);

				String descSubTipoPC="";
				try
				{
		
					Subtipo_pcLocalHome sTipoPC = (Subtipo_pcLocalHome)HomeFactory.getHome(Subtipo_pcLocalHome.JNDI_NAME);
					Subtipo_pcKey stPCK = new Subtipo_pcKey();
					stPCK.id_subtipo_pc= new Integer(agrupacion_atisLocal.getSbt_pro_cmr_cd().intValue());
					Subtipo_pcLocal sTipoPcLocal = sTipoPC.findByPrimaryKey(stPCK);
					descSubTipoPC= sTipoPcLocal.getNombre_subtipo();
				}
				catch (Exception e)
				{
					descSubTipoPC="" + agrupacion_atisLocal.getSbt_pro_cmr_cd();
				}							
				objPS.setDescSubTipo(descSubTipoPC);
				Collection direcAtis = agrupacion_atisLocal.getDireccion_atis();
				Iterator iterDirec = direcAtis.iterator();
				if (iterDirec.hasNext())
				{
					Direccion_atisLocal dAtis = (Direccion_atisLocal) iterDirec.next();
					objPS.setDescSubLocalidad(dAtis.getNom_slo_no().toString());
					LocalidadLocal localidadAgrup = dAtis.getFk_02_localidad();
					if(localidadAgrup!=null)
					{
						LocalidadKey localidadKey=(LocalidadKey) localidadAgrup.getPrimaryKey();
						objPS.setCodLocalidad(localidadKey.cod_loc);
						objPS.setDescLocalidad(localidadAgrup.getDescripcion_localidad());
					}
					else
					{
						logger.debug("La direccion Atis de la Agrupacion no tiene localidad");
						objPS.setDescLocalidad("");
					}
					DepartamentoLocal departamentoLocal=dAtis.getFk_02_dir_depto();
					if(departamentoLocal!=null)
					{
						DepartamentoKey departamentoKey=(DepartamentoKey) departamentoLocal.getPrimaryKey();
						objPS.setCodDepartamento(departamentoKey.cod_dpt);
						objPS.setDescDepartamento(departamentoLocal.getDescripcion_departamento());
					}
					String dirInst = Utiles.sinNull(dAtis.getNom_cal_ds(),"") +" "+ Utiles.sinNull(dAtis.getNum_cal_nu(),"")+" "+Utiles.sinNull(dAtis.getDsc_cmp_pri_ds(),"")+" "+Utiles.sinNull(dAtis.getDsc_cmp_seg_ds(),"")+" "+ Utiles.sinNull(dAtis.getNom_slo_no(),"");
					objPS.setDireccion(dirInst);
					
				}
				try
				{
					Fecha fecha=new Fecha(agrupacion_atisLocal.getCps_agr_sub_ff());
					objPS.setFechaCompromiso(fecha.getFechaconFormato());
				}
				catch(Exception fe)
				{
					
				}
			}
			else
				objPS.setTipoPC(false);
			objPS.setId(key.ps_id);
			objPS.setIdProducto(key.ps_id);
			objPS.setNombreProducto(producto_servicioLocal.getPs_nombre());
			objPS.setObservacion(producto_servicioLocal.getPs_observacion());
			objPS.setPcoObligatorio(new Byte(producto_servicioLocal.getPs_pco_obligatorio().byteValue()));
			objPS.setIdEmpresa(producto_servicioLocal.getEmpr_id());
			objPS.setIdAmbito(producto_servicioLocal.getAmbi_id());
			objPS.setIdGrupoProducto( producto_servicioLocal.getGrps_id() );
			objPS.setNombreFamiliaPS( familia_producto_servicioLocal.getFaps_nombre());
			objPS.setIdFaps( familia_producto_servicioKey.faps_id );
			objPS.setCodigoFamiliaPS( familia_producto_servicioLocal.getFaps_codigo() );
			objPS.setIdOpComercial( operacion_comercialKey.opco_id);
			objPS.setOperacionComercial( operacion_comercialLocal.getOpco_nombre() );
			objPS.setTipoOperacionComercial( operacion_comercialLocal.getOpco_tipo() );
			objPS.setTrasladoOperCom(operacion_comercialLocal.getOpco_tras());
			objPS.setCantidad( producto_servicio_peticionLocal.getPspe_cantidad() );
						
			arrayList.add(objPS);
		}
		return arrayList;
	}
	
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
	
	
	public String getComercialProductIdentification()
	{
		for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
			Agrupacion_atisLocal agrupacion_atisLocal=local.getFk_01_subp_atis().getFk_agru_sub();
			return agrupacion_atisLocal.getIde_pro_cmr_cd();
		}
		return null;
	}
	
	public int getpreviousServicePhone()
	{
		int retorno=0;
		try
		{
			for(Iterator iterator=getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal local=(Producto_servicio_peticionLocal) iterator.next();
				for(Iterator iterator2=local.getFk_01_subp_atis().getSubpeticion_caracteristicas().iterator();iterator2.hasNext();)
				{
					Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal=(Subpeticion_caracteristicasLocal) iterator2.next();
					Subpeticion_caracteristicasKey key=(Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
					if(key.cod_crc_cd.longValue()!=new Long(VpistbbaConfig.getVariable("NUMTELEF")).longValue())
						continue;
					retorno=new Integer(subpeticion_caracteristicasLocal.getVal_ral_crc_cd()).intValue();
				}
			}
			logger.debug("El valor de retorno que tengo para la previousServicePhone es:"+retorno);
			return retorno;
		}
		catch(Exception e)
		{
			logger.debug(e);
			return 0;
		}
	}
	/**
	 * This method was generated for supporting the relationship role named inventario_dth.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getInventario_dth();
	/**
	 * This method was generated for supporting the relationship role named inventario_dth.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setInventario_dth(
		java.util.Collection anInventario_dth);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionKey ejbCreate(
		java.lang.Long peti_numero) throws javax.ejb.CreateException {
		setPeti_numero(peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long peti_numero)
		throws javax.ejb.CreateException {
	}
	/**
	 * This method was generated for supporting the relationship role named altapctv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAltapctv();
	/**
	 * This method was generated for supporting the relationship role named altapctv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAltapctv(java.util.Collection anAltapctv);
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
	 * Get accessor for persistent attribute: num_ide_nu_ic
	 */
	public abstract java.lang.String getNum_ide_nu_ic();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_ic
	 */
	public abstract void setNum_ide_nu_ic(java.lang.String newNum_ide_nu_ic);
	/**
	 * Get accessor for persistent attribute: tipoErrorId
	 */
	public abstract java.lang.Long getTipoErrorId();
	/**
	 * Set accessor for persistent attribute: tipoErrorId
	 */
	public abstract void setTipoErrorId(java.lang.Long newTipoErrorId);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds_1
	 */
	public abstract java.lang.String getTel_cot_ds_1();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds_1
	 */
	public abstract void setTel_cot_ds_1(java.lang.String newTel_cot_ds_1);
	/**
	 * Get accessor for persistent attribute: tel_cot_ds_2
	 */
	public abstract java.lang.String getTel_cot_ds_2();
	/**
	 * Set accessor for persistent attribute: tel_cot_ds_2
	 */
	public abstract void setTel_cot_ds_2(java.lang.String newTel_cot_ds_2);
	/*
	 * Get accessor for persistent attribute: granite_code
	 */
	public abstract int getGranite_code();
	/**
	 * Set accessor for persistent attribute: granite_code
	 */
	public abstract void setGranite_code(int newGranite_code);
	/**
	 * Get accessor for persistent attribute: regla_id
	 */
	public abstract java.lang.Long getRegla_id();
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public abstract void setRegla_id(java.lang.Long newRegla_id);
	/**
	 * This method was generated for supporting the relationship role named regla_retenciones.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal getRegla_retenciones();
	/**
	 * This method was generated for supporting the relationship role named regla_retenciones.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRegla_retenciones(
		co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal aRegla_retenciones);
	/**
	 * Get accessor for persistent attribute: inf_cicl_fac
	 */
	public abstract java.lang.String getInf_cicl_fac();
	/**
	 * Set accessor for persistent attribute: inf_cicl_fac
	 */
	public abstract void setInf_cicl_fac(java.lang.String newInf_cicl_fac);
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
	 * Get accessor for persistent attribute: hab_terra
	 */
	public abstract java.lang.Integer getHab_terra();
	/**
	 * Set accessor for persistent attribute: hab_terra
	 */
	public abstract void setHab_terra(java.lang.Integer newHab_terra);
	/**
	 * Get accessor for persistent attribute: jornada_agnd_sc
	 */
	public abstract java.lang.String getJornada_agnd_sc();
	/**
	 * Set accessor for persistent attribute: jornada_agnd_sc
	 */
	public abstract void setJornada_agnd_sc(java.lang.String newJornada_agnd_sc);
	/**
	 * Get accessor for persistent attribute: coord_x_agnd_sc
	 */
	public abstract java.lang.String getCoord_x_agnd_sc();
	/**
	 * Set accessor for persistent attribute: coord_x_agnd_sc
	 */
	public abstract void setCoord_x_agnd_sc(java.lang.String newCoord_x_agnd_sc);
	/**
	 * Get accessor for persistent attribute: coord_y_agnd_sc
	 */
	public abstract java.lang.String getCoord_y_agnd_sc();
	/**
	 * Set accessor for persistent attribute: coord_y_agnd_sc
	 */
	public abstract void setCoord_y_agnd_sc(java.lang.String newCoord_y_agnd_sc);
	/**
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAgenda_sc();
	/**
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAgenda_sc(java.util.Collection anAgenda_sc);
	/**
	 * Get accessor for persistent attribute: estado_agend_sc
	 */
	public abstract java.lang.Integer getEstado_agend_sc();
	/**
	 * Set accessor for persistent attribute: estado_agend_sc
	 */
	public abstract void setEstado_agend_sc(java.lang.Integer newEstado_agend_sc);
	/**
	 * This method was generated for supporting the relationship role named tutor_web.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTutor_web();
	/**
	 * This method was generated for supporting the relationship role named tutor_web.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTutor_web(java.util.Collection aTutor_web);
	/**
	 * Get accessor for persistent attribute: pago_tv_sola_ok
	 */
	public abstract java.lang.String getPago_tv_sola_ok();
	/**
	 * Set accessor for persistent attribute: pago_tv_sola_ok
	 */
	public abstract void setPago_tv_sola_ok(java.lang.String newPago_tv_sola_ok);
	/**
	 * Get accessor for persistent attribute: fecha_pago_tv_sola
	 */
	public abstract java.lang.String getFecha_pago_tv_sola();
	/**
	 * Set accessor for persistent attribute: fecha_pago_tv_sola
	 */
	public abstract void setFecha_pago_tv_sola(
		java.lang.String newFecha_pago_tv_sola);
	/**
	 * Get accessor for persistent attribute: num_venta_int_movil
	 */
	public abstract int getNum_venta_int_movil();
	/**
	 * Set accessor for persistent attribute: num_venta_int_movil
	 */
	public abstract void setNum_venta_int_movil(int newNum_venta_int_movil);
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal getTmp_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTmp_agenda_sc(
		co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal aTmp_agenda_sc);
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
	 * Get accessor for persistent attribute: num_ide_nu_pdg
	 */
	public abstract java.lang.String getNum_ide_nu_pdg();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_pdg
	 */
	public abstract void setNum_ide_nu_pdg(java.lang.String newNum_ide_nu_pdg);
	/**
	 * Get accessor for persistent attribute: control_rec_eq
	 */
	public abstract java.lang.String getControl_rec_eq();
	/**
	 * Set accessor for persistent attribute: control_rec_eq
	 */
	public abstract void setControl_rec_eq(java.lang.String newControl_rec_eq);
	/**
	 * Get accessor for persistent attribute: flag_cp
	 */
	public abstract java.lang.String getFlag_cp();
	/**
	 * Set accessor for persistent attribute: flag_cp
	 */
	public abstract void setFlag_cp(java.lang.String newFlag_cp);
	/**
	 * Get accessor for persistent attribute: fecha_entrega
	 */
	public abstract java.sql.Timestamp getFecha_entrega();
	/**
	 * Set accessor for persistent attribute: fecha_entrega
	 */
	public abstract void setFecha_entrega(java.sql.Timestamp newFecha_entrega);
	/**
	 * Get accessor for persistent attribute: nombre_entrega
	 */
	public abstract java.lang.String getNombre_entrega();
	/**
	 * Set accessor for persistent attribute: nombre_entrega
	 */
	public abstract void setNombre_entrega(java.lang.String newNombre_entrega);
	/**
	 * Get accessor for persistent attribute: cedula
	 */
	public abstract java.lang.String getCedula();
	/**
	 * Set accessor for persistent attribute: cedula
	 */
	public abstract void setCedula(java.lang.String newCedula);
	/**
	 * Get accessor for persistent attribute: estrategia
	 */
	public abstract java.lang.String getEstrategia();
	/**
	 * Set accessor for persistent attribute: estrategia
	 */
	public abstract void setEstrategia(java.lang.String newEstrategia);
	/**
	 * Get accessor for persistent attribute: tipo_desconcilia
	 */
	public abstract java.lang.String getTipo_desconcilia();
	/**
	 * Set accessor for persistent attribute: tipo_desconcilia
	 */
	public abstract void setTipo_desconcilia(
		java.lang.String newTipo_desconcilia);
	/**
	 * This method was generated for supporting the relationship role named instalaciones_vip.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getInstalaciones_vip();
	/**
	 * This method was generated for supporting the relationship role named instalaciones_vip.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setInstalaciones_vip(
		java.util.Collection anInstalaciones_vip);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_mp
	 */
	public abstract java.lang.String getNum_ide_nu_mp();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_mp
	 */
	public abstract void setNum_ide_nu_mp(java.lang.String newNum_ide_nu_mp);

}
