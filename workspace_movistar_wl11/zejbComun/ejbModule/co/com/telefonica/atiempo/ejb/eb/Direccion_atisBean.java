package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.CreateException;

import co.com.atiempo.dto.DireccionAtisDTO;

/**
 * Bean implementation class for Enterprise Bean: Direccion_atis
 */
public abstract class Direccion_atisBean implements javax.ejb.EntityBean {
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
	
	public Direccion_atisKey ejbCreate(Agrupacion_atisLocal atLocal,DireccionAtisDTO daDto,DepartamentoLocal dpLocal,LocalidadLocal locLocal) throws CreateException
	{
		Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) atLocal.getPrimaryKey();
		setFk_01_direc_atis_cod_agr_sub_nu(agrupacion_atisKey.cod_agr_sub_nu);
		setFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd(agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd);
		
		if (daDto.getDirNroVia1() !=null && !daDto.getDirNroVia1().equals("")){
			 setDir_nro_via_1(daDto.getDirNroVia1());
		}else{
			setDir_nro_via_1("-");
		}
		
		if (daDto.getDirLt1Via1() !=null && !daDto.getDirLt1Via1().equals("")){
			setDir_lt1_via_1(daDto.getDirLt1Via1());
		}else{
			setDir_lt1_via_1("-");
		}
		
		if (daDto.getDirLt2Via1() !=null && !daDto.getDirLt2Via1().equals("")){
			setDir_lt2_via_1(daDto.getDirLt2Via1());
		}else{
			setDir_lt2_via_1("-");
		}
		
		if (daDto.getDirZonVia1() !=null && !daDto.getDirZonVia1().equals("")){
			setDir_zon_via_1(daDto.getDirZonVia1());
		}else{
			setDir_zon_via_1("-");
		}
		
		if (daDto.getDirTipVia2() !=null && !daDto.getDirTipVia2().equals("")){
			setDir_tip_via_2(daDto.getDirTipVia2());
		}else{
			setDir_tip_via_2("-");
		}
		
		if (daDto.getDirNroVia2() !=null && !daDto.getDirNroVia2().equals("")){
			setDir_nro_via_2(daDto.getDirNroVia2());
		}else{
			setDir_nro_via_2("-");
		}
		
		if (daDto.getDirLt1Via2() !=null && !daDto.getDirLt1Via2().equals("")){
			setDir_lt1_via_2(daDto.getDirLt1Via2());
		}else{
			setDir_lt1_via_2("-");
		}
		
		if (daDto.getDirLt2Via2() !=null && !daDto.getDirLt2Via2().equals("")){
			setDir_lt2_via_2(daDto.getDirLt2Via2());
		}else{
			setDir_lt2_via_2("-");
		}
		
		if (daDto.getDirZonVia2() !=null && !daDto.getDirZonVia2().equals("")){
			setDir_zon_via_2(daDto.getDirZonVia2());
		}else{
			setDir_zon_via_2("-");
		}
		
		if (daDto.getDirTipLg1() !=null && !daDto.getDirTipLg1().equals("")){
			setDir_tip_lg1(daDto.getDirTipLg1());
		}else{
			setDir_tip_lg1("-");
		}
		
		if (daDto.getDirNroLg1() !=null && !daDto.getDirNroLg1().equals("")){
			setDir_nro_lg1(daDto.getDirNroLg1());
		}else{
			setDir_nro_lg1("-");
		}
		
		if (daDto.getDirTipLg2() !=null && !daDto.getDirTipLg2().equals("")){
			setDir_tip_lg2(daDto.getDirTipLg2());
		}else{
			setDir_tip_lg2("-");
		}
		
		if (daDto.getDirNroLg2() !=null && !daDto.getDirNroLg2().equals("")){
			setDir_nro_lg2(daDto.getDirNroLg2());
		}else{
			setDir_nro_lg2("-");
		}
		
		if (daDto.getDirTipLg3() !=null && !daDto.getDirTipLg3().equals("")){
			setDir_tip_lg3(daDto.getDirTipLg3());
		}else{
			setDir_tip_lg3("-");
		}
		
		if (daDto.getDirNroLg3() !=null && !daDto.getDirNroLg3().equals("")){
			setDir_nro_lg3(daDto.getDirNroLg3());
		}else{
			setDir_nro_lg3("-");
		}
		
		if (daDto.getDirTipVia1() !=null && !daDto.getDirTipVia1().equals("")){		
			setDir_tip_via_1(daDto.getDirTipVia1());
		}else{
			setDir_tip_via_1("");
		}
		
		if (daDto.getDirTipVia1() !=null && !daDto.getDirTipVia1().equals("")){	
			setDir_zon_via_1(daDto.getDirZonVia1());
		}else{
			setDir_zon_via_1("-");
		}
		

		setCod_ext_loc_cd(daDto.getCodExtLocCd());
		setCod_ter_cd(daDto.getCodTerCd());
		setCod_are_tel_cd(daDto.getCodAreTelCd());
		setAre_sn_tel_cd(daDto.getAreSnTelCd());
		setLoc_ext_tel_cd(daDto.getLocExtTelCd());
		setTip_cal_atis_cd(daDto.getTipCalAtisCd());
		setNom_cal_ds(daDto.getNomCalDs());
		setNum_cal_nu(daDto.getNumCalNu());
		setDsc_cmp_pri_ds(daDto.getDscCmpPriDs());
		setDsc_cmp_seg_ds(daDto.getDscCmpSegDs());
		setCod_loc_cd(daDto.getCodLocCd());
		setNom_slo_no(daDto.getNomsloNo());
//		Direccion_atisKey key=new Direccion_atisKey();
//		key.fk_01_direc_atis_cod_agr_sub_nu=agrupacion_atisKey.cod_agr_sub_nu;
//		key.fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd=agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd;
//		key.privateSetFk_01_direc_atisKey(agrupacion_atisKey);
//		setFk_01_direc_atis(atLocal);
//		setFk_02_dir_depto(dpLocal);
//		setFk_02_localidad(locLocal);
		return null;
	}
	
	public void ejbPostCreate(Agrupacion_atisLocal atLocal,DireccionAtisDTO daDto,DepartamentoLocal dpLocal,LocalidadLocal locLocal) throws CreateException
	{
		Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) atLocal.getPrimaryKey();
		setFk_01_direc_atis(atLocal);
		setFk_02_dir_depto(dpLocal);
		setFk_02_localidad(locLocal);
		setFk_01_direc_atis_cod_agr_sub_nu(agrupacion_atisKey.cod_agr_sub_nu);
		setFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd(agrupacion_atisKey.fk_pet_agrupacion_cod_pet_cd);
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisLocal argFk_01_direc_atis)
		throws javax.ejb.CreateException {
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisKey argFk_01_direc_atisPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Agrupacion_atisKey) argFk_01_direc_atis
				.getPrimaryKey();
		setFk_01_direc_atis_cod_agr_sub_nu(argFk_01_direc_atisPK.cod_agr_sub_nu);
		setFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd(argFk_01_direc_atisPK.fk_pet_agrupacion_cod_pet_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisLocal argFk_01_direc_atis)
		throws javax.ejb.CreateException {
		setFk_01_direc_atis(argFk_01_direc_atis);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey ejbCreate(
		java.lang.Integer fk_01_direc_atis_cod_agr_sub_nu,
		java.lang.Long fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException {
		setFk_01_direc_atis_cod_agr_sub_nu(fk_01_direc_atis_cod_agr_sub_nu);
		setFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd(fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd);
		return null;
	}

	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer fk_01_direc_atis_cod_agr_sub_nu,
		java.lang.Long fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd)
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
	 * This method was generated for supporting the relationship role named fk_02_dir_depto.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.DepartamentoLocal getFk_02_dir_depto();
	/**
	 * This method was generated for supporting the relationship role named fk_02_dir_depto.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_02_dir_depto(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aFk_02_dir_depto);
	/**
	 * This method was generated for supporting the relationship role named fk_02_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.LocalidadLocal getFk_02_localidad();
	/**
	 * This method was generated for supporting the relationship role named fk_02_localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_02_localidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aFk_02_localidad);
	/**
	 * This method was generated for supporting the relationship role named fk_01_direc_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Agrupacion_atisLocal getFk_01_direc_atis();
	/**
	 * This method was generated for supporting the relationship role named fk_01_direc_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_01_direc_atis(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisLocal aFk_01_direc_atis);
	/**
	 * Get accessor for persistent attribute: fk_01_direc_atis_cod_agr_sub_nu
	 */
	public abstract java.lang.Integer getFk_01_direc_atis_cod_agr_sub_nu();
	/**
	 * Set accessor for persistent attribute: fk_01_direc_atis_cod_agr_sub_nu
	 */
	public abstract void setFk_01_direc_atis_cod_agr_sub_nu(
		java.lang.Integer newFk_01_direc_atis_cod_agr_sub_nu);
	/**
	 * Get accessor for persistent attribute: fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract java
		.lang
		.Long getFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd();
	/**
	 * Set accessor for persistent attribute: fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd
	 */
	public abstract void setFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd(
		java.lang.Long newFk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd);
}
