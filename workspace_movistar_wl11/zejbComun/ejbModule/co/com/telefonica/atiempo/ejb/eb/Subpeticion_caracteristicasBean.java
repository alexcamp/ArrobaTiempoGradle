package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.SubpeticionCaracteristicasDTO;

/**
 * Bean implementation class for Enterprise Bean: Subpeticion_caracteristicas
 */
public abstract class Subpeticion_caracteristicasBean
	implements javax.ejb.EntityBean {
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
	
	public Subpeticion_caracteristicasKey ejbCreate(Subpeticion_atisLocal argFk_01_subpetcar,SubpeticionCaracteristicasDTO dto) throws javax.ejb.CreateException
	{
		Subpeticion_atisKey subpeticion_atisKey=(Subpeticion_atisKey) argFk_01_subpetcar.getPrimaryKey();
		Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) argFk_01_subpetcar.getFk_agru_sub().getPrimaryKey();
		setFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd(agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd);
		setFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu(subpeticion_atisKey.fk_agru_sub_cod_agr_sub_nu);
		setFk_01_subpetcar_cod_sub_cd(subpeticion_atisKey.cod_sub_cd);
		setCod_crc_cd(dto.getCodCrcCd());
		setNom_crc_no(dto.getNomCrcNo());
		setCod_tip_dat_cd(dto.getCodTipDatCd());
		setCod_val_crc_cd(dto.getCodValCrcCd());
		setVal_ini_crc_no(dto.getValIniCrcNo());
		setVal_fin_crc_no(dto.getValFinCrcNo());
		setVal_ral_crc_cd(dto.getValRalCrcCd());
		setLng_crc_nu(dto.getLngCrcNu());
		setFmt_vdo_crc_no(dto.getFmtVdoCrcNo());
		setMom_inf_crc_in(dto.getMomInfCrcIn());
//		Subpeticion_caracteristicasKey key=new Subpeticion_caracteristicasKey();
//		return key;
		return null;
	}
	
	public void ejbPostCreate(Subpeticion_atisLocal argFk_01_subpetcar,SubpeticionCaracteristicasDTO dto) throws javax.ejb.CreateException
	{
		setFk_01_subpetcar(argFk_01_subpetcar);
	}
	
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey ejbCreate(
		java.lang.Long cod_crc_cd,
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal argFk_01_subpetcar)
		throws javax.ejb.CreateException {
		setCod_crc_cd(cod_crc_cd);
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey argFk_01_subpetcarPK = (co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey) argFk_01_subpetcar
			.getPrimaryKey();
		setFk_01_subpetcar_cod_sub_cd(argFk_01_subpetcarPK.cod_sub_cd);
		setFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu(argFk_01_subpetcarPK.fk_agru_sub_cod_agr_sub_nu);
		setFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd(argFk_01_subpetcarPK.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long cod_crc_cd,
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal argFk_01_subpetcar)
		throws javax.ejb.CreateException {
		setFk_01_subpetcar(argFk_01_subpetcar);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey ejbCreate(
		java.lang.Long cod_crc_cd,
		java.lang.Integer fk_01_subpetcar_cod_sub_cd,
		java.lang.Integer fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu,
		java.lang.Long fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException {
		setCod_crc_cd(cod_crc_cd);
		setFk_01_subpetcar_cod_sub_cd(fk_01_subpetcar_cod_sub_cd);
		setFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu(fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu);
		setFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd(fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long cod_crc_cd,
		java.lang.Integer fk_01_subpetcar_cod_sub_cd,
		java.lang.Integer fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu,
		java.lang.Long fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
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
	 * Get accessor for persistent attribute: cod_crc_cd
	 */
	public abstract java.lang.Long getCod_crc_cd();
	/**
	 * Set accessor for persistent attribute: cod_crc_cd
	 */
	public abstract void setCod_crc_cd(java.lang.Long newCod_crc_cd);
	/**
	 * Get accessor for persistent attribute: nom_crc_no
	 */
	public abstract java.lang.String getNom_crc_no();
	/**
	 * Set accessor for persistent attribute: nom_crc_no
	 */
	public abstract void setNom_crc_no(java.lang.String newNom_crc_no);
	/**
	 * Get accessor for persistent attribute: cod_tip_dat_cd
	 */
	public abstract java.lang.String getCod_tip_dat_cd();
	/**
	 * Set accessor for persistent attribute: cod_tip_dat_cd
	 */
	public abstract void setCod_tip_dat_cd(java.lang.String newCod_tip_dat_cd);
	/**
	 * Get accessor for persistent attribute: cod_val_crc_cd
	 */
	public abstract java.lang.Long getCod_val_crc_cd();
	/**
	 * Set accessor for persistent attribute: cod_val_crc_cd
	 */
	public abstract void setCod_val_crc_cd(java.lang.Long newCod_val_crc_cd);
	/**
	 * Get accessor for persistent attribute: val_ini_crc_no
	 */
	public abstract java.lang.String getVal_ini_crc_no();
	/**
	 * Set accessor for persistent attribute: val_ini_crc_no
	 */
	public abstract void setVal_ini_crc_no(java.lang.String newVal_ini_crc_no);
	/**
	 * Get accessor for persistent attribute: val_fin_crc_no
	 */
	public abstract java.lang.String getVal_fin_crc_no();
	/**
	 * Set accessor for persistent attribute: val_fin_crc_no
	 */
	public abstract void setVal_fin_crc_no(java.lang.String newVal_fin_crc_no);
	/**
	 * Get accessor for persistent attribute: val_ral_crc_cd
	 */
	public abstract java.lang.String getVal_ral_crc_cd();
	/**
	 * Set accessor for persistent attribute: val_ral_crc_cd
	 */
	public abstract void setVal_ral_crc_cd(java.lang.String newVal_ral_crc_cd);
	/**
	 * Get accessor for persistent attribute: lng_crc_nu
	 */
	public abstract java.lang.Long getLng_crc_nu();
	/**
	 * Set accessor for persistent attribute: lng_crc_nu
	 */
	public abstract void setLng_crc_nu(java.lang.Long newLng_crc_nu);
	/**
	 * Get accessor for persistent attribute: fmt_vdo_crc_no
	 */
	public abstract java.lang.String getFmt_vdo_crc_no();
	/**
	 * Set accessor for persistent attribute: fmt_vdo_crc_no
	 */
	public abstract void setFmt_vdo_crc_no(java.lang.String newFmt_vdo_crc_no);
	/**
	 * Get accessor for persistent attribute: mom_inf_crc_in
	 */
	public abstract java.lang.String getMom_inf_crc_in();
	/**
	 * Set accessor for persistent attribute: mom_inf_crc_in
	 */
	public abstract void setMom_inf_crc_in(java.lang.String newMom_inf_crc_in);
	/**
	 * This method was generated for supporting the relationship role named fk_01_subpetcar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_atisLocal getFk_01_subpetcar();
	/**
	 * This method was generated for supporting the relationship role named fk_01_subpetcar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_01_subpetcar(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisLocal aFk_01_subpetcar);
	/**
	 * Get accessor for persistent attribute: fk_01_subpetcar_cod_sub_cd
	 */
	public abstract java.lang.Integer getFk_01_subpetcar_cod_sub_cd();
	/**
	 * Set accessor for persistent attribute: fk_01_subpetcar_cod_sub_cd
	 */
	public abstract void setFk_01_subpetcar_cod_sub_cd(
		java.lang.Integer newFk_01_subpetcar_cod_sub_cd);
	/**
	 * Get accessor for persistent attribute: fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu
	 */
	public abstract java
		.lang
		.Integer getFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu();
	/**
	 * Set accessor for persistent attribute: fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu
	 */
	public abstract void setFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu(
		java.lang.Integer newFk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu);
	/**
	 * Get accessor for persistent attribute: fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract java
		.lang
		.Long getFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd();
	/**
	 * Set accessor for persistent attribute: fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract void setFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd(
		java
			.lang
			.Long newFk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
}
