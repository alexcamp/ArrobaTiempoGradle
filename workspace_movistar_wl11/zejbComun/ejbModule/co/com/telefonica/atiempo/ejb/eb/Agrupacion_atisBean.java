package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.AgrupacionAtisDTO;

/**
 * Bean implementation class for Enterprise Bean: Agrupacion_atis
 */
public abstract class Agrupacion_atisBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey ejbCreate(Peticion_atisLocal paLocal, AgrupacionAtisDTO atDto) throws javax.ejb.CreateException
	{
		setFk_pet_agrupacion_cod_pet_cd(atDto.getCodPetCd());
		setCod_agr_sub_nu(atDto.getCodAgrSubNu());
		setAgr_sub_pdr_cd(atDto.getAgrSubPdrCd());
		setCan_agr_hij_nu(atDto.getCanArgHijNu());
		setNum_ide_nu(atDto.getNumIdeNu());
		setIde_pro_cmr_cd(atDto.getIdeProCmrCd());
		setTip_opr_cmr_cd(atDto.getTipOprCmrCd());
		setCod_pro_cmr_cd(atDto.getCodProCmrCd());
		setPro_cmr_pdr_sn(atDto.getProCmrPdrSn());
		setTip_pro_cmr_cd(atDto.getTipProCd());
		setSbt_pro_cmr_cd(atDto.getSbtProCmrCd());
		setCod_tip_uso_cd(atDto.getCodTipUsoCd());
		setNom_tip_uso_no(atDto.getNomTipUsoNo());
		setCod_dir_cd(atDto.getCodDirCd());
		setCod_dir_seg_cd(atDto.getCodDirSegCd());
		setFec_sla_eje_ff(atDto.getFecSlaEjeFf());
		setCps_agr_sub_ff(atDto.getCpsAgrSubFf());
		setObs_agr_sub_ds(atDto.getObsAgrSubDs());
		setFk_pet_agrupacion_cod_pet_cd(atDto.getCodPetCd());
		return null;
	}
	
	public void ejbPostCreate(Peticion_atisLocal paLocal,AgrupacionAtisDTO atDto)
			throws javax.ejb.CreateException {
				setFk_pet_agrupacion(paLocal);
		}
	
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey ejbCreate(
		java.lang.Integer cod_agr_sub_nu,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Peticion_atisLocal argFk_pet_agrupacion)
		throws javax.ejb.CreateException {
		setCod_agr_sub_nu(cod_agr_sub_nu);
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Peticion_atisKey argFk_pet_agrupacionPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Peticion_atisKey) argFk_pet_agrupacion
				.getPrimaryKey();
		setFk_pet_agrupacion_cod_pet_cd(argFk_pet_agrupacionPK.cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_agr_sub_nu,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Peticion_atisLocal argFk_pet_agrupacion)
		throws javax.ejb.CreateException {
		setFk_pet_agrupacion(argFk_pet_agrupacion);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey ejbCreate(
		java.lang.Integer cod_agr_sub_nu,
		java.lang.Long fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException {
		setCod_agr_sub_nu(cod_agr_sub_nu);
		setFk_pet_agrupacion_cod_pet_cd(fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_agr_sub_nu,
		java.lang.Long fk_pet_agrupacion_cod_pet_cd)
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
	 * Get accessor for persistent attribute: cod_agr_sub_nu
	 */
	public abstract java.lang.Integer getCod_agr_sub_nu();
	/**
	 * Set accessor for persistent attribute: cod_agr_sub_nu
	 */
	public abstract void setCod_agr_sub_nu(java.lang.Integer newCod_agr_sub_nu);
	/**
	 * Get accessor for persistent attribute: agr_sub_pdr_cd
	 */
	public abstract java.lang.Integer getAgr_sub_pdr_cd();
	/**
	 * Set accessor for persistent attribute: agr_sub_pdr_cd
	 */
	public abstract void setAgr_sub_pdr_cd(java.lang.Integer newAgr_sub_pdr_cd);
	/**
	 * Get accessor for persistent attribute: can_agr_hij_nu
	 */
	public abstract java.lang.Integer getCan_agr_hij_nu();
	/**
	 * Set accessor for persistent attribute: can_agr_hij_nu
	 */
	public abstract void setCan_agr_hij_nu(java.lang.Integer newCan_agr_hij_nu);
	/**
	 * Get accessor for persistent attribute: num_ide_nu
	 */
	public abstract java.lang.String getNum_ide_nu();
	/**
	 * Set accessor for persistent attribute: num_ide_nu
	 */
	public abstract void setNum_ide_nu(java.lang.String newNum_ide_nu);
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public abstract java.lang.String getIde_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr_cd
	 */
	public abstract void setIde_pro_cmr_cd(java.lang.String newIde_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: tip_opr_cmr_cd
	 */
	public abstract java.lang.Long getTip_opr_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_opr_cmr_cd
	 */
	public abstract void setTip_opr_cmr_cd(java.lang.Long newTip_opr_cmr_cd);
	/**
	 * Get accessor for persistent attribute: cod_pro_cmr_cd
	 */
	public abstract java.lang.Long getCod_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: cod_pro_cmr_cd
	 */
	public abstract void setCod_pro_cmr_cd(java.lang.Long newCod_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: pro_cmr_pdr_sn
	 */
	public abstract java.lang.Long getPro_cmr_pdr_sn();
	/**
	 * Set accessor for persistent attribute: pro_cmr_pdr_sn
	 */
	public abstract void setPro_cmr_pdr_sn(java.lang.Long newPro_cmr_pdr_sn);
	/**
	 * Get accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public abstract java.lang.Long getTip_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public abstract void setTip_pro_cmr_cd(java.lang.Long newTip_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public abstract java.lang.Long getSbt_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public abstract void setSbt_pro_cmr_cd(java.lang.Long newSbt_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: cod_tip_uso_cd
	 */
	public abstract java.lang.Long getCod_tip_uso_cd();
	/**
	 * Set accessor for persistent attribute: cod_tip_uso_cd
	 */
	public abstract void setCod_tip_uso_cd(java.lang.Long newCod_tip_uso_cd);
	/**
	 * Get accessor for persistent attribute: nom_tip_uso_no
	 */
	public abstract java.lang.String getNom_tip_uso_no();
	/**
	 * Set accessor for persistent attribute: nom_tip_uso_no
	 */
	public abstract void setNom_tip_uso_no(java.lang.String newNom_tip_uso_no);
	/**
	 * Get accessor for persistent attribute: cod_dir_cd
	 */
	public abstract java.lang.Long getCod_dir_cd();
	/**
	 * Set accessor for persistent attribute: cod_dir_cd
	 */
	public abstract void setCod_dir_cd(java.lang.Long newCod_dir_cd);
	/**
	 * Get accessor for persistent attribute: cod_dir_seg_cd
	 */
	public abstract java.lang.Long getCod_dir_seg_cd();
	/**
	 * Set accessor for persistent attribute: cod_dir_seg_cd
	 */
	public abstract void setCod_dir_seg_cd(java.lang.Long newCod_dir_seg_cd);
	/**
	 * Get accessor for persistent attribute: fec_sla_eje_ff
	 */
	public abstract java.sql.Timestamp getFec_sla_eje_ff();
	/**
	 * Set accessor for persistent attribute: fec_sla_eje_ff
	 */
	public abstract void setFec_sla_eje_ff(
		java.sql.Timestamp newFec_sla_eje_ff);
	/**
	 * Get accessor for persistent attribute: cps_agr_sub_ff
	 */
	public abstract java.sql.Timestamp getCps_agr_sub_ff();
	/**
	 * Set accessor for persistent attribute: cps_agr_sub_ff
	 */
	public abstract void setCps_agr_sub_ff(
		java.sql.Timestamp newCps_agr_sub_ff);
	/**
	 * Get accessor for persistent attribute: obs_agr_sub_ds
	 */
	public abstract java.lang.String getObs_agr_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_agr_sub_ds
	 */
	public abstract void setObs_agr_sub_ds(java.lang.String newObs_agr_sub_ds);
	/**
	 * This method was generated for supporting the relationship role named fk_pet_agrupacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_pet_agrupacion(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal aFk_pet_agrupacion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDireccion_atis(
		java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named subpeticion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getSubpeticion_atis();
	/**
	 * This method was generated for supporting the relationship role named subpeticion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setSubpeticion_atis(
		java.util.Collection aSubpeticion_atis);
	/**
	 * Get accessor for persistent attribute: fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract java.lang.Long getFk_pet_agrupacion_cod_pet_cd();
	/**
	 * Set accessor for persistent attribute: fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract void setFk_pet_agrupacion_cod_pet_cd(
		java.lang.Long newFk_pet_agrupacion_cod_pet_cd);
	/**
	 * Get accessor for persistent attribute: fath_iden_line
	 */
	public abstract java.lang.String getFath_iden_line();
	/**
	 * Set accessor for persistent attribute: fath_iden_line
	 */
	public abstract void setFath_iden_line(java.lang.String newFath_iden_line);
}
