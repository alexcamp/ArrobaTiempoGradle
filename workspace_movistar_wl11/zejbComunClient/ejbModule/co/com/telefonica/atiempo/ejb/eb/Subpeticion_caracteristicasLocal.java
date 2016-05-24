package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Subpeticion_caracteristicas
 */
public interface Subpeticion_caracteristicasLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nom_crc_no
	 */
	public java.lang.String getNom_crc_no();
	/**
	 * Set accessor for persistent attribute: nom_crc_no
	 */
	public void setNom_crc_no(java.lang.String newNom_crc_no);
	/**
	 * Get accessor for persistent attribute: cod_tip_dat_cd
	 */
	public java.lang.String getCod_tip_dat_cd();
	/**
	 * Set accessor for persistent attribute: cod_tip_dat_cd
	 */
	public void setCod_tip_dat_cd(java.lang.String newCod_tip_dat_cd);
	/**
	 * Get accessor for persistent attribute: cod_val_crc_cd
	 */
	public java.lang.Long getCod_val_crc_cd();
	/**
	 * Set accessor for persistent attribute: cod_val_crc_cd
	 */
	public void setCod_val_crc_cd(java.lang.Long newCod_val_crc_cd);
	/**
	 * Get accessor for persistent attribute: val_ini_crc_no
	 */
	public java.lang.String getVal_ini_crc_no();
	/**
	 * Set accessor for persistent attribute: val_ini_crc_no
	 */
	public void setVal_ini_crc_no(java.lang.String newVal_ini_crc_no);
	/**
	 * Get accessor for persistent attribute: val_fin_crc_no
	 */
	public java.lang.String getVal_fin_crc_no();
	/**
	 * Set accessor for persistent attribute: val_fin_crc_no
	 */
	public void setVal_fin_crc_no(java.lang.String newVal_fin_crc_no);
	/**
	 * Get accessor for persistent attribute: val_ral_crc_cd
	 */
	public java.lang.String getVal_ral_crc_cd();
	/**
	 * Set accessor for persistent attribute: val_ral_crc_cd
	 */
	public void setVal_ral_crc_cd(java.lang.String newVal_ral_crc_cd);
	/**
	 * Get accessor for persistent attribute: lng_crc_nu
	 */
	public java.lang.Long getLng_crc_nu();
	/**
	 * Set accessor for persistent attribute: lng_crc_nu
	 */
	public void setLng_crc_nu(java.lang.Long newLng_crc_nu);
	/**
	 * Get accessor for persistent attribute: fmt_vdo_crc_no
	 */
	public java.lang.String getFmt_vdo_crc_no();
	/**
	 * Set accessor for persistent attribute: fmt_vdo_crc_no
	 */
	public void setFmt_vdo_crc_no(java.lang.String newFmt_vdo_crc_no);
	/**
	 * Get accessor for persistent attribute: mom_inf_crc_in
	 */
	public java.lang.String getMom_inf_crc_in();
	/**
	 * Set accessor for persistent attribute: mom_inf_crc_in
	 */
	public void setMom_inf_crc_in(java.lang.String newMom_inf_crc_in);
	/**
	 * This method was generated for supporting the relationship role named fk_01_subpetcar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
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
	public void setFk_01_subpetcar(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisLocal aFk_01_subpetcar);
}
