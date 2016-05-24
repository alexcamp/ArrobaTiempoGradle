package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Recursos_linea_basica
 */
public interface Recursos_linea_basicaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: distribuidor_sec
	 */
	public java.lang.Long getDistribuidor_sec();
	/**
	 * Set accessor for persistent attribute: distribuidor_sec
	 */
	public void setDistribuidor_sec(java.lang.Long newDistribuidor_sec);
	/**
	 * Get accessor for persistent attribute: desc_distribuidor_sec
	 */
	public java.lang.String getDesc_distribuidor_sec();
	/**
	 * Set accessor for persistent attribute: desc_distribuidor_sec
	 */
	public void setDesc_distribuidor_sec(
		java.lang.String newDesc_distribuidor_sec);
	/**
	 * Get accessor for persistent attribute: armario
	 */
	public java.lang.String getArmario();
	/**
	 * Set accessor for persistent attribute: armario
	 */
	public void setArmario(java.lang.String newArmario);
	/**
	 * Get accessor for persistent attribute: caja
	 */
	public java.lang.String getCaja();
	/**
	 * Set accessor for persistent attribute: caja
	 */
	public void setCaja(java.lang.String newCaja);
	/**
	 * Get accessor for persistent attribute: par_caja
	 */
	public java.lang.Long getPar_caja();
	/**
	 * Set accessor for persistent attribute: par_caja
	 */
	public void setPar_caja(java.lang.Long newPar_caja);
	/**
	 * Get accessor for persistent attribute: dist_prim
	 */
	public java.lang.Long getDist_prim();
	/**
	 * Set accessor for persistent attribute: dist_prim
	 */
	public void setDist_prim(java.lang.Long newDist_prim);
	/**
	 * Get accessor for persistent attribute: desc_dist_prim
	 */
	public java.lang.String getDesc_dist_prim();
	/**
	 * Set accessor for persistent attribute: desc_dist_prim
	 */
	public void setDesc_dist_prim(java.lang.String newDesc_dist_prim);
	/**
	 * Get accessor for persistent attribute: liston
	 */
	public java.lang.String getListon();
	/**
	 * Set accessor for persistent attribute: liston
	 */
	public void setListon(java.lang.String newListon);
	/**
	 * Get accessor for persistent attribute: par_liston
	 */
	public java.lang.Long getPar_liston();
	/**
	 * Set accessor for persistent attribute: par_liston
	 */
	public void setPar_liston(java.lang.Long newPar_liston);
	/**
	 * Get accessor for persistent attribute: central
	 */
	public java.lang.Long getCentral();
	/**
	 * Set accessor for persistent attribute: central
	 */
	public void setCentral(java.lang.Long newCentral);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: cable
	 */
	public java.lang.Long getCable();
	/**
	 * Set accessor for persistent attribute: cable
	 */
	public void setCable(java.lang.Long newCable);
	/**
	 * Get accessor for persistent attribute: par_cable
	 */
	public java.lang.Long getPar_cable();
	/**
	 * Set accessor for persistent attribute: par_cable
	 */
	public void setPar_cable(java.lang.Long newPar_cable);
	/**
	 * Get accessor for persistent attribute: telefono_asignado
	 */
	public java.lang.Long getTelefono_asignado();
	/**
	 * Set accessor for persistent attribute: telefono_asignado
	 */
	public void setTelefono_asignado(java.lang.Long newTelefono_asignado);
	/**
	 * Get accessor for persistent attribute: len
	 */
	public java.lang.String getLen();
	/**
	 * Set accessor for persistent attribute: len
	 */
	public void setLen(java.lang.String newLen);
	/**
	 * Get accessor for persistent attribute: ind_error
	 */
	public java.lang.String getInd_error();
	/**
	 * Set accessor for persistent attribute: ind_error
	 */
	public void setInd_error(java.lang.String newInd_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: dir_distribuidor
	 */
	public java.lang.String getDir_distribuidor();
	/**
	 * Set accessor for persistent attribute: dir_distribuidor
	 */
	public void setDir_distribuidor(java.lang.String newDir_distribuidor);
	/**
	 * Get accessor for persistent attribute: dir_armario
	 */
	public java.lang.String getDir_armario();
	/**
	 * Set accessor for persistent attribute: dir_armario
	 */
	public void setDir_armario(java.lang.String newDir_armario);
	/**
	 * Get accessor for persistent attribute: dir_caja
	 */
	public java.lang.String getDir_caja();
	/**
	 * Set accessor for persistent attribute: dir_caja
	 */
	public void setDir_caja(java.lang.String newDir_caja);
	/**
	 * This method was generated for supporting the relationship role named dslam_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getDslam_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named dslam_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDslam_apsc_linea(java.util.Collection aDslam_apsc_linea);
	/**
	 * This method was generated for supporting the relationship role named servicio_basico_supl_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getServicio_basico_supl_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named servicio_basico_supl_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setServicio_basico_supl_apsc_linea(
		java.util.Collection aServicio_basico_supl_apsc_linea);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: posicion_horizontal
	 */
	public java.lang.String getPosicion_horizontal();
	/**
	 * Set accessor for persistent attribute: posicion_horizontal
	 */
	public void setPosicion_horizontal(java.lang.String newPosicion_horizontal);
	/**
	 * This method was generated for supporting the relationship role named zonas_atendimiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getZonas_atendimiento();
	/**
	 * This method was generated for supporting the relationship role named zonas_atendimiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setZonas_atendimiento(java.util.Collection aZonas_atendimiento);
	/**
	 * Get accessor for persistent attribute: cod_ave_cd
	 */
	public java.lang.Long getCod_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_ave_cd
	 */
	public void setCod_ave_cd(java.lang.Long newCod_ave_cd);
}
