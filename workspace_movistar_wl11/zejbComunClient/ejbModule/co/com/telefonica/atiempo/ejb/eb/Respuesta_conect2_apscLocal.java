package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Respuesta_conect2_apsc
 */
public interface Respuesta_conect2_apscLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: dist_sec_asg
	 */
	public java.lang.Long getDist_sec_asg();
	/**
	 * Set accessor for persistent attribute: dist_sec_asg
	 */
	public void setDist_sec_asg(java.lang.Long newDist_sec_asg);
	/**
	 * Get accessor for persistent attribute: desc_dist_sec_adg
	 */
	public java.lang.String getDesc_dist_sec_adg();
	/**
	 * Set accessor for persistent attribute: desc_dist_sec_adg
	 */
	public void setDesc_dist_sec_adg(java.lang.String newDesc_dist_sec_adg);
	/**
	 * Get accessor for persistent attribute: armario_asg
	 */
	public java.lang.String getArmario_asg();
	/**
	 * Set accessor for persistent attribute: armario_asg
	 */
	public void setArmario_asg(java.lang.String newArmario_asg);
	/**
	 * Get accessor for persistent attribute: caja_asg
	 */
	public java.lang.String getCaja_asg();
	/**
	 * Set accessor for persistent attribute: caja_asg
	 */
	public void setCaja_asg(java.lang.String newCaja_asg);
	/**
	 * Get accessor for persistent attribute: par_caja_asg
	 */
	public java.lang.Long getPar_caja_asg();
	/**
	 * Set accessor for persistent attribute: par_caja_asg
	 */
	public void setPar_caja_asg(java.lang.Long newPar_caja_asg);
	/**
	 * Get accessor for persistent attribute: dist_prim_asg
	 */
	public java.lang.Long getDist_prim_asg();
	/**
	 * Set accessor for persistent attribute: dist_prim_asg
	 */
	public void setDist_prim_asg(java.lang.Long newDist_prim_asg);
	/**
	 * Get accessor for persistent attribute: liston_asg
	 */
	public java.lang.String getListon_asg();
	/**
	 * Set accessor for persistent attribute: liston_asg
	 */
	public void setListon_asg(java.lang.String newListon_asg);
	/**
	 * Get accessor for persistent attribute: par_liston_asg
	 */
	public java.lang.Long getPar_liston_asg();
	/**
	 * Set accessor for persistent attribute: par_liston_asg
	 */
	public void setPar_liston_asg(java.lang.Long newPar_liston_asg);
	/**
	 * Get accessor for persistent attribute: cable
	 */
	public java.lang.String getCable();
	/**
	 * Set accessor for persistent attribute: cable
	 */
	public void setCable(java.lang.String newCable);
	/**
	 * Get accessor for persistent attribute: par_cable
	 */
	public java.lang.Long getPar_cable();
	/**
	 * Set accessor for persistent attribute: par_cable
	 */
	public void setPar_cable(java.lang.Long newPar_cable);
	/**
	 * Get accessor for persistent attribute: cod_central
	 */
	public java.lang.Long getCod_central();
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public void setCod_central(java.lang.Long newCod_central);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: telefono_asg
	 */
	public java.lang.Long getTelefono_asg();
	/**
	 * Set accessor for persistent attribute: telefono_asg
	 */
	public void setTelefono_asg(java.lang.Long newTelefono_asg);
	/**
	 * Get accessor for persistent attribute: len
	 */
	public java.lang.String getLen();
	/**
	 * Set accessor for persistent attribute: len
	 */
	public void setLen(java.lang.String newLen);
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
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: ods_apsc
	 */
	public java.lang.Long getOds_apsc();
	/**
	 * Set accessor for persistent attribute: ods_apsc
	 */
	public void setOds_apsc(java.lang.Long newOds_apsc);
	/**
	 * This method was generated for supporting the relationship role named fk_01_resp_con2_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getFk_01_resp_con2_ap();
	/**
	 * This method was generated for supporting the relationship role named fk_01_resp_con2_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_01_resp_con2_ap(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_01_resp_con2_ap);
	/**
	 * This method was generated for supporting the relationship role named f_reference_23.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal getF_reference_23();
	/**
	 * This method was generated for supporting the relationship role named f_reference_23.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_reference_23(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aF_reference_23);
}
