package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.SubpeticionAtisDTO;

/**
 * Bean implementation class for Enterprise Bean: Subpeticion_atis
 */
public abstract class Subpeticion_atisBean implements javax.ejb.EntityBean {
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
	
	public Subpeticion_atisKey ejbCreate(Agrupacion_atisLocal agrupacion_atisLocal,SubpeticionAtisDTO dto) throws javax.ejb.CreateException
	{
		Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion_atisLocal.getPrimaryKey();
		setFk_agru_sub_fk_pet_agrupacion_cod_pet_cd(agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd);
		setFk_agru_sub_cod_agr_sub_nu(agrupacion_atisKey.cod_agr_sub_nu);
		setCod_sub_cd(dto.getCodSubCd());
		setCod_pro_ser_cd(dto.getCodProSerCd());
		setPro_ser_cto_cd(dto.getProSerCtoCd());
		setCan_pro_sub_nu(dto.getCanProSubNu());
		setIni_ser_pro_ff(dto.getIniSerProFf());
		setFin_ser_pro_ff(dto.getFinSerProFf());
		setCod_mot_sub_cd(dto.getCodMotSubCd());
		setCod_smt_sub_cd(dto.getCodSmtSubCd());
		setCod_aco_equ_cd(dto.getCodAcoEquCd());
		setObs_sub_ds(dto.getObsSubDs());
		setCod_opr_cmr_cd(dto.getCodOprCmrCd());
		setNum_cto_nu(dto.getNumCtoNu());
		setCod_sub_pdr_sn(dto.getCodSubPdrSn());
		setNum_crc_cnf_nu(dto.getNumCrcCnfNu());
		setNom_pro_ser_no(dto.getNomProSerNo());
		setTip_pro_ser_cd(dto.getTipProSerCd());
		setCod_est_sub_cd(dto.getCodEstSubCd());
		setMot_est_sub_cd(dto.getMotEstSubCd());
		setNom_mot_no(dto.getNomMotNo());
		setId_srv_ref_cd(dto.getIdSrvRefCd());
		Subpeticion_atisKey key=new Subpeticion_atisKey();
//		Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion_atisLocal.getPrimaryKey();
//		key.cod_sub_cd=dto.getCodSubCd();
//		key.fk_agru_sub_cod_agr_sub_nu=agrupacion_atisKey.cod_agr_sub_nu;
//		key.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd=agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd;
//		return key;
//		setFk_agru_sub(agrupacion_atisLocal);
		return null;
	}
	
	public void ejbPostCreate(Agrupacion_atisLocal agrupacion_atisLocal,SubpeticionAtisDTO dto) throws javax.ejb.CreateException
	{
		setFk_agru_sub(agrupacion_atisLocal);
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey ejbCreate(java.lang.Integer cod_sub_cd,
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal argFk_agru_sub)
		throws javax.ejb.CreateException {
		setCod_sub_cd(cod_sub_cd);
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey argFk_agru_subPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Agrupacion_atisKey) argFk_agru_sub
				.getPrimaryKey();
		setFk_agru_sub_cod_agr_sub_nu(argFk_agru_subPK.cod_agr_sub_nu);
		setFk_agru_sub_fk_pet_agrupacion_cod_pet_cd(
		argFk_agru_subPK.fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_sub_cd,
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal argFk_agru_sub)
		throws javax.ejb.CreateException {
		setFk_agru_sub(argFk_agru_sub);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey ejbCreate(
		java.lang.Integer cod_sub_cd,
		java.lang.Integer fk_agru_sub_cod_agr_sub_nu,
		java.lang.Long fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException {
		setCod_sub_cd(cod_sub_cd);
		setFk_agru_sub_cod_agr_sub_nu(fk_agru_sub_cod_agr_sub_nu);
		setFk_agru_sub_fk_pet_agrupacion_cod_pet_cd(fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_sub_cd,
		java.lang.Integer fk_agru_sub_cod_agr_sub_nu,
		java.lang.Long fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
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
	 * Get accessor for persistent attribute: cod_sub_cd
	 */
	public abstract java.lang.Integer getCod_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_sub_cd
	 */
	public abstract void setCod_sub_cd(java.lang.Integer newCod_sub_cd);
	/**
	 * Get accessor for persistent attribute: cod_pro_ser_cd
	 */
	public abstract java.lang.Long getCod_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: cod_pro_ser_cd
	 */
	public abstract void setCod_pro_ser_cd(java.lang.Long newCod_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: pro_ser_cto_cd
	 */
	public abstract java.lang.Long getPro_ser_cto_cd();
	/**
	 * Set accessor for persistent attribute: pro_ser_cto_cd
	 */
	public abstract void setPro_ser_cto_cd(java.lang.Long newPro_ser_cto_cd);
	/**
	 * Get accessor for persistent attribute: can_pro_sub_nu
	 */
	public abstract java.lang.Long getCan_pro_sub_nu();
	/**
	 * Set accessor for persistent attribute: can_pro_sub_nu
	 */
	public abstract void setCan_pro_sub_nu(java.lang.Long newCan_pro_sub_nu);
	/**
	 * Get accessor for persistent attribute: ini_ser_pro_ff
	 */
	public abstract java.sql.Timestamp getIni_ser_pro_ff();
	/**
	 * Set accessor for persistent attribute: ini_ser_pro_ff
	 */
	public abstract void setIni_ser_pro_ff(
		java.sql.Timestamp newIni_ser_pro_ff);
	/**
	 * Get accessor for persistent attribute: fin_ser_pro_ff
	 */
	public abstract java.sql.Timestamp getFin_ser_pro_ff();
	/**
	 * Set accessor for persistent attribute: fin_ser_pro_ff
	 */
	public abstract void setFin_ser_pro_ff(
		java.sql.Timestamp newFin_ser_pro_ff);
	/**
	 * Get accessor for persistent attribute: cod_mot_sub_cd
	 */
	public abstract java.lang.Long getCod_mot_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_mot_sub_cd
	 */
	public abstract void setCod_mot_sub_cd(java.lang.Long newCod_mot_sub_cd);
	/**
	 * Get accessor for persistent attribute: cod_smt_sub_cd
	 */
	public abstract java.lang.Integer getCod_smt_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_smt_sub_cd
	 */
	public abstract void setCod_smt_sub_cd(java.lang.Integer newCod_smt_sub_cd);
	/**
	 * Get accessor for persistent attribute: cod_aco_equ_cd
	 */
	public abstract java.lang.String getCod_aco_equ_cd();
	/**
	 * Set accessor for persistent attribute: cod_aco_equ_cd
	 */
	public abstract void setCod_aco_equ_cd(java.lang.String newCod_aco_equ_cd);
	/**
	 * Get accessor for persistent attribute: obs_sub_ds
	 */
	public abstract java.lang.String getObs_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_sub_ds
	 */
	public abstract void setObs_sub_ds(java.lang.String newObs_sub_ds);
	/**
	 * Get accessor for persistent attribute: cod_opr_cmr_cd
	 */
	public abstract java.lang.Long getCod_opr_cmr_cd();
	/**
	 * Set accessor for persistent attribute: cod_opr_cmr_cd
	 */
	public abstract void setCod_opr_cmr_cd(java.lang.Long newCod_opr_cmr_cd);
	/**
	 * Get accessor for persistent attribute: num_cto_nu
	 */
	public abstract java.lang.Long getNum_cto_nu();
	/**
	 * Set accessor for persistent attribute: num_cto_nu
	 */
	public abstract void setNum_cto_nu(java.lang.Long newNum_cto_nu);
	/**
	 * Get accessor for persistent attribute: cod_sub_pdr_sn
	 */
	public abstract java.lang.Integer getCod_sub_pdr_sn();
	/**
	 * Set accessor for persistent attribute: cod_sub_pdr_sn
	 */
	public abstract void setCod_sub_pdr_sn(java.lang.Integer newCod_sub_pdr_sn);
	/**
	 * Get accessor for persistent attribute: num_crc_cnf_nu
	 */
	public abstract java.lang.Integer getNum_crc_cnf_nu();
	/**
	 * Set accessor for persistent attribute: num_crc_cnf_nu
	 */
	public abstract void setNum_crc_cnf_nu(java.lang.Integer newNum_crc_cnf_nu);
	/**
	 * Get accessor for persistent attribute: nom_pro_ser_no
	 */
	public abstract java.lang.String getNom_pro_ser_no();
	/**
	 * Set accessor for persistent attribute: nom_pro_ser_no
	 */
	public abstract void setNom_pro_ser_no(java.lang.String newNom_pro_ser_no);
	/**
	 * Get accessor for persistent attribute: tip_pro_ser_cd
	 */
	public abstract java.lang.Long getTip_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_ser_cd
	 */
	public abstract void setTip_pro_ser_cd(java.lang.Long newTip_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: cod_est_sub_cd
	 */
	public abstract java.lang.String getCod_est_sub_cd();
	/**
	 * Set accessor for persistent attribute: cod_est_sub_cd
	 */
	public abstract void setCod_est_sub_cd(java.lang.String newCod_est_sub_cd);
	/**
	 * Get accessor for persistent attribute: mot_est_sub_cd
	 */
	public abstract java.lang.Long getMot_est_sub_cd();
	/**
	 * Set accessor for persistent attribute: mot_est_sub_cd
	 */
	public abstract void setMot_est_sub_cd(java.lang.Long newMot_est_sub_cd);
	/**
	 * Get accessor for persistent attribute: nom_mot_no
	 */
	public abstract java.lang.String getNom_mot_no();
	/**
	 * Set accessor for persistent attribute: nom_mot_no
	 */
	public abstract void setNom_mot_no(java.lang.String newNom_mot_no);
	/**
	 * This method was generated for supporting the relationship role named fk_agru_sub.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_agru_sub(
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal aFk_agru_sub);
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
	 * This method was generated for supporting the relationship role named subpeticion_caracteristicas.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getSubpeticion_caracteristicas();
	/**
	 * This method was generated for supporting the relationship role named subpeticion_caracteristicas.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setSubpeticion_caracteristicas(
		java.util.Collection aSubpeticion_caracteristicas);
	/**
	 * Get accessor for persistent attribute: fk_agru_sub_cod_agr_sub_nu
	 */
	public abstract java.lang.Integer getFk_agru_sub_cod_agr_sub_nu();
	/**
	 * Set accessor for persistent attribute: fk_agru_sub_cod_agr_sub_nu
	 */
	public abstract void setFk_agru_sub_cod_agr_sub_nu(
		java.lang.Integer newFk_agru_sub_cod_agr_sub_nu);
	/**
	 * Get accessor for persistent attribute: fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract java
		.lang
		.Long getFk_agru_sub_fk_pet_agrupacion_cod_pet_cd();
	/**
	 * Set accessor for persistent attribute: fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract void setFk_agru_sub_fk_pet_agrupacion_cod_pet_cd(
		java.lang.Long newFk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
	/**
	 * Get accessor for persistent attribute: id_srv_ref_cd
	 */
	public abstract java.lang.String getId_srv_ref_cd();
	/**
	 * Set accessor for persistent attribute: id_srv_ref_cd
	 */
	public abstract void setId_srv_ref_cd(java.lang.String newId_srv_ref_cd);
}
