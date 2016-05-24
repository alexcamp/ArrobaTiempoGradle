package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Direccion_ps_peticion
 */
public abstract class Direccion_ps_peticionBean
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
	public co.com.telefonica.atiempo.ejb.eb.Direccion_ps_peticionKey ejbCreate(
		java.lang.Long dir_ps_peti_id)
		throws javax.ejb.CreateException {
		setDir_ps_peti_id(dir_ps_peti_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long dir_ps_peti_id)
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
	 * Get accessor for persistent attribute: dir_ps_peti_id
	 */
	public abstract java.lang.Long getDir_ps_peti_id();
	/**
	 * Set accessor for persistent attribute: dir_ps_peti_id
	 */
	public abstract void setDir_ps_peti_id(java.lang.Long newDir_ps_peti_id);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: msx_num_cal_nu
	 */
	public abstract java.lang.String getMsx_num_cal_nu();
	/**
	 * Set accessor for persistent attribute: msx_num_cal_nu
	 */
	public abstract void setMsx_num_cal_nu(java.lang.String newMsx_num_cal_nu);
	/**
	 * Get accessor for persistent attribute: dir_depar_cd
	 */
	public abstract java.lang.String getDir_depar_cd();
	/**
	 * Set accessor for persistent attribute: dir_depar_cd
	 */
	public abstract void setDir_depar_cd(java.lang.String newDir_depar_cd);
	/**
	 * Get accessor for persistent attribute: dir_loc_cd
	 */
	public abstract java.lang.String getDir_loc_cd();
	/**
	 * Set accessor for persistent attribute: dir_loc_cd
	 */
	public abstract void setDir_loc_cd(java.lang.String newDir_loc_cd);
	/**
	 * Get accessor for persistent attribute: msx_nom_cal_ds
	 */
	public abstract java.lang.String getMsx_nom_cal_ds();
	/**
	 * Set accessor for persistent attribute: msx_nom_cal_ds
	 */
	public abstract void setMsx_nom_cal_ds(java.lang.String newMsx_nom_cal_ds);
	/**
	 * Get accessor for persistent attribute: dir_tp_via
	 */
	public abstract java.lang.String getDir_tp_via();
	/**
	 * Set accessor for persistent attribute: dir_tp_via
	 */
	public abstract void setDir_tp_via(java.lang.String newDir_tp_via);
	/**
	 * Get accessor for persistent attribute: dir_nro_via
	 */
	public abstract java.lang.Integer getDir_nro_via();
	/**
	 * Set accessor for persistent attribute: dir_nro_via
	 */
	public abstract void setDir_nro_via(java.lang.Integer newDir_nro_via);
	/**
	 * Get accessor for persistent attribute: dir_letra1_via
	 */
	public abstract java.lang.String getDir_letra1_via();
	/**
	 * Set accessor for persistent attribute: dir_letra1_via
	 */
	public abstract void setDir_letra1_via(java.lang.String newDir_letra1_via);
	/**
	 * Get accessor for persistent attribute: dir_letra2_via
	 */
	public abstract java.lang.String getDir_letra2_via();
	/**
	 * Set accessor for persistent attribute: dir_letra2_via
	 */
	public abstract void setDir_letra2_via(java.lang.String newDir_letra2_via);
	/**
	 * Get accessor for persistent attribute: dir_letra_zona_via
	 */
	public abstract java.lang.String getDir_letra_zona_via();
	/**
	 * Set accessor for persistent attribute: dir_letra_zona_via
	 */
	public abstract void setDir_letra_zona_via(
		java.lang.String newDir_letra_zona_via);
	/**
	 * Get accessor for persistent attribute: dir_tp_via2
	 */
	public abstract java.lang.String getDir_tp_via2();
	/**
	 * Set accessor for persistent attribute: dir_tp_via2
	 */
	public abstract void setDir_tp_via2(java.lang.String newDir_tp_via2);
	/**
	 * Get accessor for persistent attribute: dir_nro_via2
	 */
	public abstract java.lang.Integer getDir_nro_via2();
	/**
	 * Set accessor for persistent attribute: dir_nro_via2
	 */
	public abstract void setDir_nro_via2(java.lang.Integer newDir_nro_via2);
	/**
	 * Get accessor for persistent attribute: dir_letra1_via2
	 */
	public abstract java.lang.String getDir_letra1_via2();
	/**
	 * Set accessor for persistent attribute: dir_letra1_via2
	 */
	public abstract void setDir_letra1_via2(
		java.lang.String newDir_letra1_via2);
	/**
	 * Get accessor for persistent attribute: dir_letra2_via2
	 */
	public abstract java.lang.String getDir_letra2_via2();
	/**
	 * Set accessor for persistent attribute: dir_letra2_via2
	 */
	public abstract void setDir_letra2_via2(
		java.lang.String newDir_letra2_via2);
	/**
	 * Get accessor for persistent attribute: dir_letra_zona_via2
	 */
	public abstract java.lang.String getDir_letra_zona_via2();
	/**
	 * Set accessor for persistent attribute: dir_letra_zona_via2
	 */
	public abstract void setDir_letra_zona_via2(
		java.lang.String newDir_letra_zona_via2);
	/**
	 * Get accessor for persistent attribute: dir_tipo_lugar_1
	 */
	public abstract java.lang.String getDir_tipo_lugar_1();
	/**
	 * Set accessor for persistent attribute: dir_tipo_lugar_1
	 */
	public abstract void setDir_tipo_lugar_1(
		java.lang.String newDir_tipo_lugar_1);
	/**
	 * Get accessor for persistent attribute: dir_nro_lugar_1
	 */
	public abstract java.lang.String getDir_nro_lugar_1();
	/**
	 * Set accessor for persistent attribute: dir_nro_lugar_1
	 */
	public abstract void setDir_nro_lugar_1(
		java.lang.String newDir_nro_lugar_1);
	/**
	 * Get accessor for persistent attribute: dir_tipo_lugar_2
	 */
	public abstract java.lang.String getDir_tipo_lugar_2();
	/**
	 * Set accessor for persistent attribute: dir_tipo_lugar_2
	 */
	public abstract void setDir_tipo_lugar_2(
		java.lang.String newDir_tipo_lugar_2);
	/**
	 * Get accessor for persistent attribute: dir_nro_lugar_2
	 */
	public abstract java.lang.String getDir_nro_lugar_2();
	/**
	 * Set accessor for persistent attribute: dir_nro_lugar_2
	 */
	public abstract void setDir_nro_lugar_2(
		java.lang.String newDir_nro_lugar_2);
	/**
	 * Get accessor for persistent attribute: dir_tipo_lugar_3
	 */
	public abstract java.lang.String getDir_tipo_lugar_3();
	/**
	 * Set accessor for persistent attribute: dir_tipo_lugar_3
	 */
	public abstract void setDir_tipo_lugar_3(
		java.lang.String newDir_tipo_lugar_3);
	/**
	 * Get accessor for persistent attribute: dir_nro_lugar_3
	 */
	public abstract java.lang.String getDir_nro_lugar_3();
	/**
	 * Set accessor for persistent attribute: dir_nro_lugar_3
	 */
	public abstract void setDir_nro_lugar_3(
		java.lang.String newDir_nro_lugar_3);
	/**
	 * Get accessor for persistent attribute: msx_tip_cal_ati_cd
	 */
	public abstract java.lang.String getMsx_tip_cal_ati_cd();
	/**
	 * Set accessor for persistent attribute: msx_tip_cal_ati_cd
	 */
	public abstract void setMsx_tip_cal_ati_cd(
		java.lang.String newMsx_tip_cal_ati_cd);
	/**
	 * Get accessor for persistent attribute: msx_dsc_cmp_pri_ds
	 */
	public abstract java.lang.String getMsx_dsc_cmp_pri_ds();
	/**
	 * Set accessor for persistent attribute: msx_dsc_cmp_pri_ds
	 */
	public abstract void setMsx_dsc_cmp_pri_ds(
		java.lang.String newMsx_dsc_cmp_pri_ds);
	/**
	 * Get accessor for persistent attribute: msx_dsc_cmp_seg_ds
	 */
	public abstract java.lang.String getMsx_dsc_cmp_seg_ds();
	/**
	 * Set accessor for persistent attribute: msx_dsc_cmp_seg_ds
	 */
	public abstract void setMsx_dsc_cmp_seg_ds(
		java.lang.String newMsx_dsc_cmp_seg_ds);
	/**
	 * Get accessor for persistent attribute: msx_nom_slo_no
	 */
	public abstract java.lang.String getMsx_nom_slo_no();
	/**
	 * Set accessor for persistent attribute: msx_nom_slo_no
	 */
	public abstract void setMsx_nom_slo_no(java.lang.String newMsx_nom_slo_no);
	/**
	 * Get accessor for persistent attribute: msx_cod_ext_loc_cd
	 */
	public abstract java.lang.String getMsx_cod_ext_loc_cd();
	/**
	 * Set accessor for persistent attribute: msx_cod_ext_loc_cd
	 */
	public abstract void setMsx_cod_ext_loc_cd(
		java.lang.String newMsx_cod_ext_loc_cd);
	/**
	 * Get accessor for persistent attribute: msx_cod_ter_cd
	 */
	public abstract java.lang.String getMsx_cod_ter_cd();
	/**
	 * Set accessor for persistent attribute: msx_cod_ter_cd
	 */
	public abstract void setMsx_cod_ter_cd(java.lang.String newMsx_cod_ter_cd);
	/**
	 * Get accessor for persistent attribute: msx_cod_dir_cd
	 */
	public abstract java.lang.Long getMsx_cod_dir_cd();
	/**
	 * Set accessor for persistent attribute: msx_cod_dir_cd
	 */
	public abstract void setMsx_cod_dir_cd(java.lang.Long newMsx_cod_dir_cd);
	/**
	 * Get accessor for persistent attribute: msx_cod_dir_seg_cd
	 */
	public abstract java.lang.Long getMsx_cod_dir_seg_cd();
	/**
	 * Set accessor for persistent attribute: msx_cod_dir_seg_cd
	 */
	public abstract void setMsx_cod_dir_seg_cd(
		java.lang.Long newMsx_cod_dir_seg_cd);
}
