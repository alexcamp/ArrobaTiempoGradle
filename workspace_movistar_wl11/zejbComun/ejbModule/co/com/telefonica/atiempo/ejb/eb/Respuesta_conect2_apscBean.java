package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Respuesta_conect2_apsc
 */
public abstract class Respuesta_conect2_apscBean
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
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conect2_apscKey ejbCreate(
		java.lang.Long id_conector)
		throws javax.ejb.CreateException {
		setId_conector(id_conector);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_conector)
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
	 * Get accessor for persistent attribute: id_conector
	 */
	public abstract java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public abstract void setId_conector(java.lang.Long newId_conector);
	/**
	 * Get accessor for persistent attribute: dist_sec_asg
	 */
	public abstract java.lang.Long getDist_sec_asg();
	/**
	 * Set accessor for persistent attribute: dist_sec_asg
	 */
	public abstract void setDist_sec_asg(java.lang.Long newDist_sec_asg);
	/**
	 * Get accessor for persistent attribute: desc_dist_sec_adg
	 */
	public abstract java.lang.String getDesc_dist_sec_adg();
	/**
	 * Set accessor for persistent attribute: desc_dist_sec_adg
	 */
	public abstract void setDesc_dist_sec_adg(
		java.lang.String newDesc_dist_sec_adg);
	/**
	 * Get accessor for persistent attribute: armario_asg
	 */
	public abstract java.lang.String getArmario_asg();
	/**
	 * Set accessor for persistent attribute: armario_asg
	 */
	public abstract void setArmario_asg(java.lang.String newArmario_asg);
	/**
	 * Get accessor for persistent attribute: caja_asg
	 */
	public abstract java.lang.String getCaja_asg();
	/**
	 * Set accessor for persistent attribute: caja_asg
	 */
	public abstract void setCaja_asg(java.lang.String newCaja_asg);
	/**
	 * Get accessor for persistent attribute: par_caja_asg
	 */
	public abstract java.lang.Long getPar_caja_asg();
	/**
	 * Set accessor for persistent attribute: par_caja_asg
	 */
	public abstract void setPar_caja_asg(java.lang.Long newPar_caja_asg);
	/**
	 * Get accessor for persistent attribute: dist_prim_asg
	 */
	public abstract java.lang.Long getDist_prim_asg();
	/**
	 * Set accessor for persistent attribute: dist_prim_asg
	 */
	public abstract void setDist_prim_asg(java.lang.Long newDist_prim_asg);
	/**
	 * Get accessor for persistent attribute: liston_asg
	 */
	public abstract java.lang.String getListon_asg();
	/**
	 * Set accessor for persistent attribute: liston_asg
	 */
	public abstract void setListon_asg(java.lang.String newListon_asg);
	/**
	 * Get accessor for persistent attribute: par_liston_asg
	 */
	public abstract java.lang.Long getPar_liston_asg();
	/**
	 * Set accessor for persistent attribute: par_liston_asg
	 */
	public abstract void setPar_liston_asg(java.lang.Long newPar_liston_asg);
	/**
	 * Get accessor for persistent attribute: cable
	 */
	public abstract java.lang.String getCable();
	/**
	 * Set accessor for persistent attribute: cable
	 */
	public abstract void setCable(java.lang.String newCable);
	/**
	 * Get accessor for persistent attribute: par_cable
	 */
	public abstract java.lang.Long getPar_cable();
	/**
	 * Set accessor for persistent attribute: par_cable
	 */
	public abstract void setPar_cable(java.lang.Long newPar_cable);
	/**
	 * Get accessor for persistent attribute: cod_central
	 */
	public abstract java.lang.Long getCod_central();
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public abstract void setCod_central(java.lang.Long newCod_central);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public abstract java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public abstract void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: telefono_asg
	 */
	public abstract java.lang.Long getTelefono_asg();
	/**
	 * Set accessor for persistent attribute: telefono_asg
	 */
	public abstract void setTelefono_asg(java.lang.Long newTelefono_asg);
	/**
	 * Get accessor for persistent attribute: len
	 */
	public abstract java.lang.String getLen();
	/**
	 * Set accessor for persistent attribute: len
	 */
	public abstract void setLen(java.lang.String newLen);
	/**
	 * Get accessor for persistent attribute: dir_distribuidor
	 */
	public abstract java.lang.String getDir_distribuidor();
	/**
	 * Set accessor for persistent attribute: dir_distribuidor
	 */
	public abstract void setDir_distribuidor(
		java.lang.String newDir_distribuidor);
	/**
	 * Get accessor for persistent attribute: dir_armario
	 */
	public abstract java.lang.String getDir_armario();
	/**
	 * Set accessor for persistent attribute: dir_armario
	 */
	public abstract void setDir_armario(java.lang.String newDir_armario);
	/**
	 * Get accessor for persistent attribute: dir_caja
	 */
	public abstract java.lang.String getDir_caja();
	/**
	 * Set accessor for persistent attribute: dir_caja
	 */
	public abstract void setDir_caja(java.lang.String newDir_caja);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public abstract java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public abstract void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: ods_apsc
	 */
	public abstract java.lang.Long getOds_apsc();
	/**
	 * Set accessor for persistent attribute: ods_apsc
	 */
	public abstract void setOds_apsc(java.lang.Long newOds_apsc);
	/**
	 * This method was generated for supporting the relationship role named fk_01_resp_con2_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_01_resp_con2_ap(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_01_resp_con2_ap);
	/**
	 * This method was generated for supporting the relationship role named f_reference_23.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setF_reference_23(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aF_reference_23);
}
