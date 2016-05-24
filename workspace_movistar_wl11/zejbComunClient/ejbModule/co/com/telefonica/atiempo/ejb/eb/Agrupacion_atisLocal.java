package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Agrupacion_atis
 */
public interface Agrupacion_atisLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: agr_sub_pdr_cd
	 */
	public java.lang.Integer getAgr_sub_pdr_cd();
	/**
	 * Set accessor for persistent attribute: agr_sub_pdr_cd
	 */
	public void setAgr_sub_pdr_cd(java.lang.Integer newAgr_sub_pdr_cd);
	/**
	 * Get accessor for persistent attribute: can_agr_hij_nu
	 */
	public java.lang.Integer getCan_agr_hij_nu();
	/**
	 * Set accessor for persistent attribute: can_agr_hij_nu
	 */
	public void setCan_agr_hij_nu(java.lang.Integer newCan_agr_hij_nu);
	/**
	 * Get accessor for persistent attribute: num_ide_nu
	 */
	public java.lang.String getNum_ide_nu();
	/**
	 * Set accessor for persistent attribute: num_ide_nu
	 */
	public void setNum_ide_nu(java.lang.String newNum_ide_nu);
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public java.lang.String getIde_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public void setIde_pro_cmr_cd(java.lang.String newIde_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: tip_opr_cmr_cd
	 */
	public java.lang.Long getTip_opr_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_opr_cmr_cd
	 */
	public void setTip_opr_cmr_cd(java.lang.Long newTip_opr_cmr_cd);
	/**
	 * Get accessor for persistent attribute: cod_pro_cmr_cd
	 */
	public java.lang.Long getCod_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: cod_pro_cmr_cd
	 */
	public void setCod_pro_cmr_cd(java.lang.Long newCod_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: pro_cmr_pdr_sn
	 */
	public java.lang.Long getPro_cmr_pdr_sn();
	/**
	 * Set accessor for persistent attribute: pro_cmr_pdr_sn
	 */
	public void setPro_cmr_pdr_sn(java.lang.Long newPro_cmr_pdr_sn);
	/**
	 * Get accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public java.lang.Long getTip_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public void setTip_pro_cmr_cd(java.lang.Long newTip_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public java.lang.Long getSbt_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public void setSbt_pro_cmr_cd(java.lang.Long newSbt_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: cod_tip_uso_cd
	 */
	public java.lang.Long getCod_tip_uso_cd();
	/**
	 * Set accessor for persistent attribute: cod_tip_uso_cd
	 */
	public void setCod_tip_uso_cd(java.lang.Long newCod_tip_uso_cd);
	/**
	 * Get accessor for persistent attribute: nom_tip_uso_no
	 */
	public java.lang.String getNom_tip_uso_no();
	/**
	 * Set accessor for persistent attribute: nom_tip_uso_no
	 */
	public void setNom_tip_uso_no(java.lang.String newNom_tip_uso_no);
	/**
	 * Get accessor for persistent attribute: cod_dir_cd
	 */
	public java.lang.Long getCod_dir_cd();
	/**
	 * Set accessor for persistent attribute: cod_dir_cd
	 */
	public void setCod_dir_cd(java.lang.Long newCod_dir_cd);
	/**
	 * Get accessor for persistent attribute: cod_dir_seg_cd
	 */
	public java.lang.Long getCod_dir_seg_cd();
	/**
	 * Set accessor for persistent attribute: cod_dir_seg_cd
	 */
	public void setCod_dir_seg_cd(java.lang.Long newCod_dir_seg_cd);
	/**
	 * Get accessor for persistent attribute: fec_sla_eje_ff
	 */
	public java.sql.Timestamp getFec_sla_eje_ff();
	/**
	 * Set accessor for persistent attribute: fec_sla_eje_ff
	 */
	public void setFec_sla_eje_ff(java.sql.Timestamp newFec_sla_eje_ff);
	/**
	 * Get accessor for persistent attribute: cps_agr_sub_ff
	 */
	public java.sql.Timestamp getCps_agr_sub_ff();
	/**
	 * Set accessor for persistent attribute: cps_agr_sub_ff
	 */
	public void setCps_agr_sub_ff(java.sql.Timestamp newCps_agr_sub_ff);
	/**
	 * Get accessor for persistent attribute: obs_agr_sub_ds
	 */
	public java.lang.String getObs_agr_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_agr_sub_ds
	 */
	public void setObs_agr_sub_ds(java.lang.String newObs_agr_sub_ds);
	/**
	 * This method was generated for supporting the relationship role named fk_pet_agrupacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Peticion_atisLocal getFk_pet_agrupacion();
	/**
	 * This method was generated for supporting the relationship role named fk_pet_agrupacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_pet_agrupacion(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal aFk_pet_agrupacion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDireccion_atis(java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named subpeticion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getSubpeticion_atis();
	/**
	 * This method was generated for supporting the relationship role named subpeticion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setSubpeticion_atis(java.util.Collection aSubpeticion_atis);
	/**
	 * Get accessor for persistent attribute: fath_iden_line
	 */
	public java.lang.String getFath_iden_line();
	/**
	 * Set accessor for persistent attribute: fath_iden_line
	 */
	public void setFath_iden_line(java.lang.String newFath_iden_line);
}
