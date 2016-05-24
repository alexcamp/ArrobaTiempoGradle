package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Respuesta_conec6_apsc_linea
 */
public abstract class Respuesta_conec6_apsc_lineaBean
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
		.Respuesta_conec6_apsc_lineaKey ejbCreate(java.lang.Long id_conector)
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
	 * Get accessor for persistent attribute: distribuidor_sec
	 */
	public abstract java.lang.Long getDistribuidor_sec();
	/**
	 * Set accessor for persistent attribute: distribuidor_sec
	 */
	public abstract void setDistribuidor_sec(
		java.lang.Long newDistribuidor_sec);
	/**
	 * Get accessor for persistent attribute: desc_distribuidor_sec
	 */
	public abstract java.lang.String getDesc_distribuidor_sec();
	/**
	 * Set accessor for persistent attribute: desc_distribuidor_sec
	 */
	public abstract void setDesc_distribuidor_sec(
		java.lang.String newDesc_distribuidor_sec);
	/**
	 * Get accessor for persistent attribute: armario
	 */
	public abstract java.lang.String getArmario();
	/**
	 * Set accessor for persistent attribute: armario
	 */
	public abstract void setArmario(java.lang.String newArmario);
	/**
	 * Get accessor for persistent attribute: caja
	 */
	public abstract java.lang.String getCaja();
	/**
	 * Set accessor for persistent attribute: caja
	 */
	public abstract void setCaja(java.lang.String newCaja);
	/**
	 * Get accessor for persistent attribute: par_caja
	 */
	public abstract java.lang.Long getPar_caja();
	/**
	 * Set accessor for persistent attribute: par_caja
	 */
	public abstract void setPar_caja(java.lang.Long newPar_caja);
	/**
	 * Get accessor for persistent attribute: dist_prim
	 */
	public abstract java.lang.Long getDist_prim();
	/**
	 * Set accessor for persistent attribute: dist_prim
	 */
	public abstract void setDist_prim(java.lang.Long newDist_prim);
	/**
	 * Get accessor for persistent attribute: desc_dist_prim
	 */
	public abstract java.lang.String getDesc_dist_prim();
	/**
	 * Set accessor for persistent attribute: desc_dist_prim
	 */
	public abstract void setDesc_dist_prim(java.lang.String newDesc_dist_prim);
	/**
	 * Get accessor for persistent attribute: liston
	 */
	public abstract java.lang.String getListon();
	/**
	 * Set accessor for persistent attribute: liston
	 */
	public abstract void setListon(java.lang.String newListon);
	/**
	 * Get accessor for persistent attribute: par_liston
	 */
	public abstract java.lang.Long getPar_liston();
	/**
	 * Set accessor for persistent attribute: par_liston
	 */
	public abstract void setPar_liston(java.lang.Long newPar_liston);
	/**
	 * Get accessor for persistent attribute: central
	 */
	public abstract java.lang.Long getCentral();
	/**
	 * Set accessor for persistent attribute: central
	 */
	public abstract void setCentral(java.lang.Long newCentral);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public abstract java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public abstract void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: cable
	 */
	public abstract java.lang.Long getCable();
	/**
	 * Set accessor for persistent attribute: cable
	 */
	public abstract void setCable(java.lang.Long newCable);
	/**
	 * Get accessor for persistent attribute: par_cable
	 */
	public abstract java.lang.Long getPar_cable();
	/**
	 * Set accessor for persistent attribute: par_cable
	 */
	public abstract void setPar_cable(java.lang.Long newPar_cable);
	/**
	 * Get accessor for persistent attribute: telefono_asignado
	 */
	public abstract java.lang.Long getTelefono_asignado();
	/**
	 * Set accessor for persistent attribute: telefono_asignado
	 */
	public abstract void setTelefono_asignado(
		java.lang.Long newTelefono_asignado);
	/**
	 * Get accessor for persistent attribute: len
	 */
	public abstract java.lang.String getLen();
	/**
	 * Set accessor for persistent attribute: len
	 */
	public abstract void setLen(java.lang.String newLen);
	/**
	 * Get accessor for persistent attribute: id_conector
	 */
	public abstract java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public abstract void setId_conector(java.lang.Long newId_conector);
	/**
	 * Get accessor for persistent attribute: ind_error
	 */
	public abstract java.lang.String getInd_error();
	/**
	 * Set accessor for persistent attribute: ind_error
	 */
	public abstract void setInd_error(java.lang.String newInd_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public abstract java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public abstract void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * This method was generated for supporting the relationship role named f_pk_respuesta_con.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal getF_pk_respuesta_con();
	/**
	 * This method was generated for supporting the relationship role named f_pk_respuesta_con.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_pk_respuesta_con(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aF_pk_respuesta_con);
	/**
	 * This method was generated for supporting the relationship role named dslam_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDslam_conec6_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named dslam_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDslam_conec6_apsc_linea(
		java.util.Collection aDslam_conec6_apsc_linea);
}
