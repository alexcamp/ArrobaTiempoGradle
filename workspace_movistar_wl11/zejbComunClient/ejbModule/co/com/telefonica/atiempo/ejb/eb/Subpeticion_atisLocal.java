package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Subpeticion_atis
 */
public interface Subpeticion_atisLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_pro_ser_cd
	 */
	public java.lang.Long getCod_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: cod_pro_ser_cd
	 */
	public void setCod_pro_ser_cd(java.lang.Long newCod_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: pro_ser_cto_cd
	 */
	public java.lang.Long getPro_ser_cto_cd();
	/**
	 * Set accessor for persistent attribute: pro_ser_cto_cd
	 */
	public void setPro_ser_cto_cd(java.lang.Long newPro_ser_cto_cd);
	/**
	 * Get accessor for persistent attribute: can_pro_sub_nu
	 */
	public java.lang.Long getCan_pro_sub_nu();
	/**
	 * Set accessor for persistent attribute: can_pro_sub_nu
	 */
	public void setCan_pro_sub_nu(java.lang.Long newCan_pro_sub_nu);
	/**
	 * Get accessor for persistent attribute: ini_ser_pro_ff
	 */
	public java.sql.Timestamp getIni_ser_pro_ff();
	/**
	 * Set accessor for persistent attribute: ini_ser_pro_ff
	 */
	public void setIni_ser_pro_ff(java.sql.Timestamp newIni_ser_pro_ff);
	/**
	 * Get accessor for persistent attribute: fin_ser_pro_ff
	 */
	public java.sql.Timestamp getFin_ser_pro_ff();
	/**
	 * Set accessor for persistent attribute: fin_ser_pro_ff
	 */
	public void setFin_ser_pro_ff(java.sql.Timestamp newFin_ser_pro_ff);
	/**
	 * Get accessor for persistent attribute: cod_mot_sub_cd
	 */
	public java.lang.Long getCod_mot_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_mot_sub_cd
	 */
	public void setCod_mot_sub_cd(java.lang.Long newCod_mot_sub_cd);
	/**
	 * Get accessor for persistent attribute: cod_smt_sub_cd
	 */
	public java.lang.Integer getCod_smt_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_smt_sub_cd
	 */
	public void setCod_smt_sub_cd(java.lang.Integer newCod_smt_sub_cd);
	/**
	 * Get accessor for persistent attribute: cod_aco_equ_cd
	 */
	public java.lang.String getCod_aco_equ_cd();
	/**
	 * Set accessor for persistent attribute: cod_aco_equ_cd
	 */
	public void setCod_aco_equ_cd(java.lang.String newCod_aco_equ_cd);
	/**
	 * Get accessor for persistent attribute: obs_sub_ds
	 */
	public java.lang.String getObs_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_sub_ds
	 */
	public void setObs_sub_ds(java.lang.String newObs_sub_ds);
	/**
	 * Get accessor for persistent attribute: cod_opr_cmr_cd
	 */
	public java.lang.Long getCod_opr_cmr_cd();
	/**
	 * Set accessor for persistent attribute: cod_opr_cmr_cd
	 */
	public void setCod_opr_cmr_cd(java.lang.Long newCod_opr_cmr_cd);
	/**
	 * Get accessor for persistent attribute: num_cto_nu
	 */
	public java.lang.Long getNum_cto_nu();
	/**
	 * Set accessor for persistent attribute: num_cto_nu
	 */
	public void setNum_cto_nu(java.lang.Long newNum_cto_nu);
	/**
	 * Get accessor for persistent attribute: cod_sub_pdr_sn
	 */
	public java.lang.Integer getCod_sub_pdr_sn();
	/**
	 * Set accessor for persistent attribute: cod_sub_pdr_sn
	 */
	public void setCod_sub_pdr_sn(java.lang.Integer newCod_sub_pdr_sn);
	/**
	 * Get accessor for persistent attribute: num_crc_cnf_nu
	 */
	public java.lang.Integer getNum_crc_cnf_nu();
	/**
	 * Set accessor for persistent attribute: num_crc_cnf_nu
	 */
	public void setNum_crc_cnf_nu(java.lang.Integer newNum_crc_cnf_nu);
	/**
	 * Get accessor for persistent attribute: nom_pro_ser_no
	 */
	public java.lang.String getNom_pro_ser_no();
	/**
	 * Set accessor for persistent attribute: nom_pro_ser_no
	 */
	public void setNom_pro_ser_no(java.lang.String newNom_pro_ser_no);
	/**
	 * Get accessor for persistent attribute: tip_pro_ser_cd
	 */
	public java.lang.Long getTip_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_ser_cd
	 */
	public void setTip_pro_ser_cd(java.lang.Long newTip_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: cod_est_sub_cd
	 */
	public java.lang.String getCod_est_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_est_sub_cd
	 */
	public void setCod_est_sub_cd(java.lang.String newCod_est_sub_cd);
	/**
	 * Get accessor for persistent attribute: mot_est_sub_cd
	 */
	public java.lang.Long getMot_est_sub_cd();
	/**
	 * Set accessor for persistent attribute: mot_est_sub_cd
	 */
	public void setMot_est_sub_cd(java.lang.Long newMot_est_sub_cd);
	/**
	 * Get accessor for persistent attribute: nom_mot_no
	 */
	public java.lang.String getNom_mot_no();
	/**
	 * Set accessor for persistent attribute: nom_mot_no
	 */
	public void setNom_mot_no(java.lang.String newNom_mot_no);
	/**
	 * This method was generated for supporting the relationship role named fk_agru_sub.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Agrupacion_atisLocal getFk_agru_sub();
	/**
	 * This method was generated for supporting the relationship role named fk_agru_sub.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_agru_sub(
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal aFk_agru_sub);
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
	 * This method was generated for supporting the relationship role named subpeticion_caracteristicas.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getSubpeticion_caracteristicas();
	/**
	 * This method was generated for supporting the relationship role named subpeticion_caracteristicas.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setSubpeticion_caracteristicas(
		java.util.Collection aSubpeticion_caracteristicas);
	/**
	 * Get accessor for persistent attribute: id_srv_ref_cd
	 */
	public java.lang.String getId_srv_ref_cd();
	/**
	 * Set accessor for persistent attribute: id_srv_ref_cd
	 */
	public void setId_srv_ref_cd(java.lang.String newId_srv_ref_cd);
}
